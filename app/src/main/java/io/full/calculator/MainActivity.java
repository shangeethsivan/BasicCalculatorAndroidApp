package io.full.calculator;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    enum operatorsEnum {ADD, SUBTRACT, MULTIPLY, DIVISION, MODULO}

    TextView backLine;
    TextView operatorLine;
    TextView mainLine;

    operatorsEnum currentOperator;

    boolean decimalAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        backLine = (TextView) findViewById(R.id.backLine);
        operatorLine = (TextView) findViewById(R.id.operator);
        mainLine = (TextView) findViewById(R.id.mainLine);

    }

    public void addNumberToMainLine(String number) {

        mainLine.setText(mainLine.getText() + number);

    }

    public void resetCalculator() {

        backLine.setText("");
        operatorLine.setText("");
        mainLine.setText(getResources().getText(R.string.default_value));

        decimalAdded = false;

    }

    public void deleteClicked() {

        if (mainLine.length() != 0) {

            if (mainLine.getText().toString().substring(mainLine.length() - 1).equals(".")) {
                decimalAdded = false;
            }

            mainLine.setText(mainLine.getText().toString().substring(0, mainLine.length() - 1));
        }

        if (mainLine.getText().length() == 0) {
            mainLine.setText(getResources().getText(R.string.default_value));
        }


    }

    public void operatorClicked(operatorsEnum operator) {

        String operationCharacter = "";

        switch (operator) {
            case ADD:
                operationCharacter = "+";
                currentOperator = operatorsEnum.ADD;
                break;
            case SUBTRACT:
                operationCharacter = "-";
                currentOperator = operatorsEnum.SUBTRACT;
                break;
            case MULTIPLY:
                operationCharacter = "*";
                currentOperator = operatorsEnum.MULTIPLY;
                break;
            case DIVISION:
                operationCharacter = "/";
                currentOperator = operatorsEnum.DIVISION;
                break;
            case MODULO:
                operationCharacter = "%";
                currentOperator = operatorsEnum.MODULO;
                break;
        }

        operatorLine.setText(operationCharacter);
        String a = mainLine.getText().toString();

        Log.e("LOGGER", "operatorClicked: " + a);
        if (backLine.getText().length() == 0) {
            backLine.setText(mainLine.getText().toString());
            mainLine.setText(getResources().getText(R.string.default_value));
            decimalAdded = false;
        }
        //TODO: Add zero to back stack

    }


    public void processClickEvent(View view) {

        int id = view.getId();

        if (mainLine.getText().equals("0") && mainLine.getText().length() == 1 && id != R.id.decimalPoint)
            mainLine.setText("");

        switch (id) {

            case R.id.number_0:
                if (mainLine.getText().length() != 1 && !mainLine.getText().equals("0"))
                    addNumberToMainLine("0");
                break;

            case R.id.number_1:
                addNumberToMainLine("1");
                break;

            case R.id.number_2:
                addNumberToMainLine("2");
                break;

            case R.id.number_3:
                addNumberToMainLine("3");
                break;

            case R.id.number_4:
                addNumberToMainLine("4");
                break;

            case R.id.number_5:
                addNumberToMainLine("5");
                break;

            case R.id.number_6:
                addNumberToMainLine("6");
                break;

            case R.id.number_7:
                addNumberToMainLine("7");
                break;

            case R.id.number_8:
                addNumberToMainLine("8");
                break;

            case R.id.number_9:
                addNumberToMainLine("9");
                break;

            case R.id.decimalPoint:

                if (!decimalAdded) {
                    addNumberToMainLine(".");
                    decimalAdded = true;
                }

                break;

            case R.id.operation_add:
                operatorClicked(operatorsEnum.ADD);
                break;

            case R.id.operation_subtract:
                operatorClicked(operatorsEnum.SUBTRACT);
                break;

            case R.id.operation_multiply:
                operatorClicked(operatorsEnum.MULTIPLY);
                break;

            case R.id.operation_division:
                operatorClicked(operatorsEnum.DIVISION);
                break;

            case R.id.operation_modulo:
                operatorClicked(operatorsEnum.MODULO);
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

    private String getOperator(operatorsEnum operatorsEnum) {

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
        if (mainLine.getText().toString().length() != 0 && backLine.getText().toString().length() != 0) {

            double result = Calculator.calculate(backLine.getText().toString(), mainLine.getText().toString(), currentOperator);

            operatorLine.setText(getOperator(currentOperator) + mainLine.getText().toString());

            mainLine.setText(String.valueOf(result));

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
