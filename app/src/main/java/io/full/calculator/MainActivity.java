package io.full.calculator;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    enum OperatorsEnum {ADD, SUBTRACT, MULTIPLY, DIVISION, MODULO}

    TextView mOperand1TextView;

    TextView mOperatorTextView;

    TextView mOperator2TextView;

    OperatorsEnum currentOperator;

    boolean operationPerformed = false;
    boolean decimalAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        mOperand1TextView = (TextView) findViewById(R.id.backLine);
        mOperatorTextView = (TextView) findViewById(R.id.operator);
        mOperator2TextView = (TextView) findViewById(R.id.mainLine);

    }


    public void processClickEvent(View view) {

        int id = view.getId();

        if (mOperator2TextView.getText().equals("0") && mOperator2TextView.getText().length() == 1 && id != R.id.decimalPoint && id != R.id.operation_equalTo)
            mOperator2TextView.setText("");

        switch (id) {

            case R.id.number_0:
                if (!mOperator2TextView.getText().equals("0"))
                    addValueToMainLine("0");
                break;

            case R.id.number_1:
                addValueToMainLine("1");
                break;

            case R.id.number_2:
                addValueToMainLine("2");
                break;

            case R.id.number_3:
                addValueToMainLine("3");
                break;

            case R.id.number_4:
                addValueToMainLine("4");
                break;

            case R.id.number_5:
                addValueToMainLine("5");
                break;

            case R.id.number_6:
                addValueToMainLine("6");
                break;

            case R.id.number_7:
                addValueToMainLine("7");
                break;

            case R.id.number_8:
                addValueToMainLine("8");
                break;

            case R.id.number_9:
                addValueToMainLine("9");
                break;

            case R.id.decimalPoint:
                if(!mOperator2TextView.getText().toString().contains(".")){
                    addValueToMainLine(".");
                }
                break;

            case R.id.operation_add:
                operatorClicked(OperatorsEnum.ADD);
                break;

            case R.id.operation_subtract:
                operatorClicked(OperatorsEnum.SUBTRACT);
                break;

            case R.id.operation_multiply:
                operatorClicked(OperatorsEnum.MULTIPLY);
                break;

            case R.id.operation_division:
                operatorClicked(OperatorsEnum.DIVISION);
                break;

            case R.id.operation_modulo:
                operatorClicked(OperatorsEnum.MODULO);
                break;

            case R.id.operation_delete:
                deleteClicked();
                break;

            case R.id.operation_clear:
                resetCalculator();
                break;

            case R.id.operation_equalTo:
                initiateCalculation();
                break;

        }

    }

    private void addValueToMainLine(String pNumber) {

        mOperator2TextView.setText((new StringBuilder(mOperator2TextView.getText()).append(pNumber)).toString());

    }

    private void resetCalculator() {

        mOperand1TextView.setText(" ");
        mOperatorTextView.setText(" ");
        mOperator2TextView.setText(getResources().getText(R.string.default_value));

        decimalAdded = false;
        operationPerformed = false;

    }

    private void deleteClicked() {

        if (mOperator2TextView.length() != 0) {

            if (mOperator2TextView.getText().toString().substring(mOperator2TextView.length() - 1).equals(".")) {
                decimalAdded = false;
            }

            mOperator2TextView.setText(mOperator2TextView.getText().toString().substring(0, mOperator2TextView.length() - 1));
        }

        if (mOperator2TextView.getText().length() == 0) {
            mOperator2TextView.setText(getResources().getText(R.string.default_value));
        }


    }

    private void operatorClicked(OperatorsEnum operator) {


        if (operationPerformed) {

            mOperand1TextView.setText(mOperator2TextView.getText().toString());
            mOperatorTextView.setText(getOperator(operator));
            mOperator2TextView.setText(getResources().getText(R.string.default_value));
            operationPerformed = false;

//        } else if (mOperator2TextView.getText().toString().equals("0") || mOperator2TextView.getText().toString().equals("") &&  mOperand1TextView.getText().toString().equals("") || mOperand1TextView.getText().toString().equals(" ")) {
//
//            mOperand1TextView.setText("0");
//            mOperatorTextView.setText(getOperator(operator));
//            mOperator2TextView.setText(getResources().getText(R.string.default_value));
//            operationPerformed = false;

        }
        else if(mOperator2TextView.getText().toString().equals("") && "+-".contains(getOperator(operator)) ){
            addValueToMainLine(getOperator(operator));
        }
            else{

//            if (mOperator2TextView.getText().toString().length() == 0 && mOperand1TextView.getText().toString().equals(" ") ) {
//
//                if(operator == OperatorsEnum.ADD || operator == OperatorsEnum.SUBTRACT)
//                    addValueToMainLine(getOperator(operator));
//
//            } else if(mOperator2TextView.getText().toString().equals("+") || mOperator2TextView.getText().toString().equals("-")){
//                mOperand1TextView.setText("0");
//                mOperatorTextView.setText(getOperator(operator));
//                mOperator2TextView.setText(getResources().getText(R.string.default_value));
//                operationPerformed = false;
//            }
//            else {

            mOperatorTextView.setText(getOperator(operator));

            if (mOperand1TextView.getText().length() == 0 || mOperand1TextView.getText().toString().equals(" ")) {
                mOperand1TextView.setText(mOperator2TextView.getText().toString());
                mOperator2TextView.setText(getResources().getText(R.string.default_value));
                decimalAdded = false;
            }

            currentOperator = operator;
            //TODO: Add zero to back stack
        }
    }
//    }


    private String getOperator(OperatorsEnum operatorsEnum) {

        switch (operatorsEnum) {

            case ADD:
                return "+";

            case SUBTRACT:
                return "-";

            case MULTIPLY:
                return "*";

            case DIVISION:
                return "/";

            case MODULO:
                return "%";
        }
        return null;
    }

    private void initiateCalculation() {

        if(!operationPerformed){

        if (mOperator2TextView.getText().toString().length() != 0 && mOperand1TextView.getText().toString().length() != 0 && !mOperand1TextView.getText().toString().equals(" ")) {

            double result = Calculator.calculate(mOperand1TextView.getText().toString(), mOperator2TextView.getText().toString(), currentOperator);

            mOperatorTextView.setText((new StringBuilder(getOperator(currentOperator)).append(mOperator2TextView.getText().toString())).toString());

            if (result % 1 == 0)
                mOperator2TextView.setText(String.valueOf((int) result));
            else {
                mOperator2TextView.setText(String.valueOf(result));
                decimalAdded = true;
            }

            operationPerformed = true;
            }
        }
    }


    public static class ControlPanelFragment extends Fragment {

        public ControlPanelFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_control_panel, container, false);
        }


    }


    public static class ViewPanelFragment extends Fragment {

        public ViewPanelFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_view_panel, container, false);
        }

    }

}
