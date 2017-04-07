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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        FragmentManager lFragmentManager = getFragmentManager();
        FragmentTransaction lFragmentTransaction = lFragmentManager.beginTransaction();


        lFragmentTransaction.add(R.id.view_panel_container, mViewFragment);

        lFragmentTransaction.add(R.id.control_panel_container, mControlFragment);

        lFragmentTransaction.commit();

    }

    private void init() {

        mViewFragment = new ViewFragment();
        mControlFragment = new ControlFragment();

    }



    @Override
    public void onButtonClicked(int pId) {

        switch (pId) {

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
                mViewFragment.operatorClicked(OperatorsEnum.ADD);
                break;

            case R.id.operation_subtract:
                mViewFragment.operatorClicked(OperatorsEnum.SUBTRACT);
                break;

            case R.id.operation_multiply:
                mViewFragment.operatorClicked(OperatorsEnum.MULTIPLY);
                break;

            case R.id.operation_division:
                mViewFragment.operatorClicked(OperatorsEnum.DIVISION);
                break;

            case R.id.operation_modulo:
                mViewFragment.operatorClicked(OperatorsEnum.MODULO);
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

            case R.id.plus_or_minus:
                break;

        }
    }

}
