package io.full.calculator.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import io.full.calculator.AnalyticsHelperApplication;
import io.full.calculator.R;


public class MainActivity extends AppCompatActivity implements ControlFragment.OnButtonClickListener {


    private Tracker mTracker;

    public enum OperatorsEnum {ADD, SUBTRACT, MULTIPLY, DIVISION, MODULO}

    private ViewFragment mViewFragment;
    private ControlFragment mControlFragment;
    private static final String TAG = "MainActivity";


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

        AnalyticsHelperApplication lAnalyticsHelperApplication = (AnalyticsHelperApplication) getApplication();
        mTracker = lAnalyticsHelperApplication.getDefaultTracker();

        mTracker.setScreenName("Home~" + TAG);
        mTracker.send(new HitBuilders.EventBuilder().build());

    }

    private void init() {

        mViewFragment = new ViewFragment();
        mControlFragment = new ControlFragment();

    }



    @Override
    public void onButtonClicked(int pId) {

        char mValueClicked = '~';

        switch (pId) {

            case R.id.number_0:
                mValueClicked = 0;
                mViewFragment.addValueToOperand2("0");
                break;

            case R.id.number_1:
                mValueClicked = 1;
                mViewFragment.addValueToOperand2("1");
                break;

            case R.id.number_2:
                mValueClicked = 2;
                mViewFragment.addValueToOperand2("2");
                break;

            case R.id.number_3:
                mValueClicked = 3;
                mViewFragment.addValueToOperand2("3");
                break;

            case R.id.number_4:
                mValueClicked = 4;
                mViewFragment.addValueToOperand2("4");
                break;

            case R.id.number_5:
                mValueClicked = 5;
                mViewFragment.addValueToOperand2("5");
                break;

            case R.id.number_6:
                mValueClicked = 6;
                mViewFragment.addValueToOperand2("6");
                break;

            case R.id.number_7:
                mValueClicked = 7;
                mViewFragment.addValueToOperand2("7");
                break;

            case R.id.number_8:
                mValueClicked = 8;
                mViewFragment.addValueToOperand2("8");
                break;

            case R.id.number_9:
                mValueClicked = 8;
                mViewFragment.addValueToOperand2("9");
                break;

            case R.id.decimalPoint:
                mValueClicked = '.';
                mViewFragment.addDecimal();
                break;

            case R.id.operation_add:
                mValueClicked = '+';
                mViewFragment.operatorClicked(OperatorsEnum.ADD);
                break;

            case R.id.operation_subtract:
                mValueClicked = '-';
                mViewFragment.operatorClicked(OperatorsEnum.SUBTRACT);
                break;

            case R.id.operation_multiply:
                mValueClicked = '*';
                mViewFragment.operatorClicked(OperatorsEnum.MULTIPLY);
                break;

            case R.id.operation_division:
                mValueClicked = '/';
                mViewFragment.operatorClicked(OperatorsEnum.DIVISION);
                break;

            case R.id.operation_modulo:
                mValueClicked = '%';
                mViewFragment.operatorClicked(OperatorsEnum.MODULO);
                break;

            case R.id.operation_delete:
                mValueClicked = 'D';
                mViewFragment.deleteClicked();
                break;

            case R.id.operation_clear:
                mValueClicked = 'C';
                mViewFragment.resetCalculator();
                break;

            case R.id.operation_equalTo:
                mValueClicked = '=';
               mViewFragment.initiateCalculation();
                break;

            case R.id.plus_or_minus:
                mValueClicked = 'p';
                mViewFragment.posNegClicked();
                break;

        }

        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Button Clicked")
                .setAction("Clicked On :"+ mValueClicked).build());
    }

}
