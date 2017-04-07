package io.full.calculator;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity implements ControlFragment.OnButtonClickListener {


    enum OperatorsEnum {ADD, SUBTRACT, MULTIPLY, DIVISION, MODULO}

    private ViewFragment mViewFragment;
    private ControlFragment mControlFragment;


    OperatorsEnum currentOperator;

    boolean operationPerformed = false;
    boolean decimalAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.add(R.id.view_panel_container, mViewFragment);

        fragmentTransaction.add(R.id.control_panel_container, mControlFragment);

        fragmentTransaction.commit();

    }

    private void init() {

        mViewFragment = new ViewFragment();
        mControlFragment = new ControlFragment();

    }



    @Override
    public void onButtonClicked(int id) {

        switch (id) {

            case R.id.number_0:
                mViewFragment.addValueToOperand2("0");
                break;

            case R.id.number_1:
                mViewFragment.addValueToOperand2("1");
                break;

            case R.id.number_2:
                mViewFragment.addValueToOperand2("2");
                break;

            case R.id.number_3:
                mViewFragment.addValueToOperand2("3");
                break;

            case R.id.number_4:
                mViewFragment.addValueToOperand2("4");
                break;

            case R.id.number_5:
                mViewFragment.addValueToOperand2("5");
                break;

            case R.id.number_6:
                mViewFragment.addValueToOperand2("6");
                break;

            case R.id.number_7:
                mViewFragment.addValueToOperand2("7");
                break;

            case R.id.number_8:
                mViewFragment.addValueToOperand2("8");
                break;

            case R.id.number_9:
                mViewFragment.addValueToOperand2("9");
                break;

            case R.id.decimalPoint:
                mViewFragment.addDecimal();
                break;

            case R.id.operation_add:
                mViewFragment.operatorClicked(MainActivity.OperatorsEnum.ADD);
                break;

            case R.id.operation_subtract:
                mViewFragment.operatorClicked(MainActivity.OperatorsEnum.SUBTRACT);
                break;

            case R.id.operation_multiply:
                mViewFragment.operatorClicked(MainActivity.OperatorsEnum.MULTIPLY);
                break;

            case R.id.operation_division:
                mViewFragment.operatorClicked(MainActivity.OperatorsEnum.DIVISION);
                break;

            case R.id.operation_modulo:
                mViewFragment.operatorClicked(MainActivity.OperatorsEnum.MODULO);
                break;

            case R.id.operation_delete:
                mViewFragment.deleteClicked();
                break;

            case R.id.operation_clear:
                mViewFragment.resetCalculator();
                break;

            case R.id.operation_equalTo:
               mViewFragment.initiateCalculation();
                break;

        }
    }


/*
    private void addValueToOperand2(String pNumber) {

        mOperator2TextView.setText((new StringBuilder(mOperator2TextView.getText()).append(pNumber)).toString());

    }

    private void resetCalculator() {

        mOperand1TV.setText(" ");
        mOperatorTV.setText(" ");
        mOperator2TextView.setText(getResources().getText(R.string.default_value));

        decimalAdded = false;
        operationPerformed = false;

    }


    private void mViewFragment.operatorClicked(OperatorsEnum operator) {


        if (operationPerformed) {

            mOperand1TV.setText(mOperator2TextView.getText().toString());
            mOperatorTV.setText(getOperator(operator));
            mOperator2TextView.setText(getResources().getText(R.string.default_value));
            operationPerformed = false;

//        } else if (getOperand2Value().equals("0") || getOperand2Value().equals("") &&  mOperand1TV.getText().toString().equals("") || mOperand1TV.getText().toString().equals(" ")) {
//
//            mOperand1TV.setText("0");
//            mOperatorTV.setText(getOperator(operator));
//            mOperand2TV.setText(getResources().getText(R.string.default_value));
//            operationPerformed = false;

        }
        else if(mOperator2TextView.getText().toString().equals("") && "+-".contains(getOperator(operator)) ){
            addValueToOperand2(getOperator(operator));
        }
            else{

//            if (getOperand2Value().length() == 0 && mOperand1TV.getText().toString().equals(" ") ) {
//
//                if(operator == OperatorsEnum.ADD || operator == OperatorsEnum.SUBTRACT)
//                    addValueToOperand2(getOperator(operator));
//
//            } else if(getOperand2Value().equals("+") || getOperand2Value().equals("-")){
//                mOperand1TV.setText("0");
//                mOperatorTV.setText(getOperator(operator));
//                mOperand2TV.setText(getResources().getText(R.string.default_value));
//                operationPerformed = false;
//            }
//            else {

            mOperatorTV.setText(getOperator(operator));

            if (mOperand1TV.getText().length() == 0 || mOperand1TV.getText().toString().equals(" ")) {
                mOperand1TV.setText(mOperator2TextView.getText().toString());
                mOperator2TextView.setText(getResources().getText(R.string.default_value));
                decimalAdded = false;
            }

            currentOperator = operator;
            //TODO: Add zero to back stack
        }
    }
//    }



    private void initiateCalculation() {

        if(!operationPerformed){

        if (mOperator2TextView.getText().toString().length() != 0 && mOperand1TV.getText().toString().length() != 0 && !mOperand1TV.getText().toString().equals(" ")) {

            double result = Calculator.calculate(mOperand1TV.getText().toString(), mOperator2TextView.getText().toString(), currentOperator);

            mOperatorTV.setText((new StringBuilder(getOperator(currentOperator)).append(mOperator2TextView.getText().toString())).toString());

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
*/


}
