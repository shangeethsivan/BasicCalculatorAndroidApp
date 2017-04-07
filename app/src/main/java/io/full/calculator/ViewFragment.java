package io.full.calculator;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;


public class ViewFragment extends Fragment {

    TextView mOperand1TV;

    TextView mOperatorTV;

    TextView mOperand2TV;

    double mOperand1;
    double mOperand2;
    double mResult;

    boolean mEqualToPressed = false;

    MainActivity.OperatorsEnum mCurrentOperator;

    public ViewFragment() {

        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View lRootView = inflater.inflate(R.layout.fragment_view_panel, container, false);

        init(lRootView);

        return lRootView;

    }

    public void init(View pView) {

        mOperand1TV = (TextView) pView.findViewById(R.id.operand1Line);

        mOperand2TV = (TextView) pView.findViewById(R.id.operand2Line);

        mOperatorTV = (TextView) pView.findViewById(R.id.operatorLine);

        resetCalculator();

    }


    public void addValueToOperand2(String pValue) {

        if (mEqualToPressed) {
            resetCalculator();
            mEqualToPressed = false;
        }

        if (getOperand2Value().equals("0") && pValue.equals("0"))
            return;

        if (getOperand2Value().equals(""))
            mOperand2TV.setText(pValue);
        else{
            if(!getOperand2Value().equals("0"))
                mOperand2TV.setText((new StringBuilder(getOperand2Value()).append(pValue)).toString());
            else
                mOperand2TV.setText(pValue);
        }

    }


    public String getOperand2Value() {
        return mOperand2TV.getText().toString();
    }


    public void addDecimal() {

        if (!getOperand2Value().contains("."))
            addValueToOperand2(".");

    }

    private String getDoubleValue(String pStringValue) {

        Double lDoubleValue = Double.valueOf(pStringValue);

        if (lDoubleValue % 1 == 0) {

            DecimalFormat lDecFormat = new DecimalFormat("###.#");
            return lDecFormat.format(lDoubleValue);

        } else
        {
            DecimalFormat lDecFormat = new DecimalFormat("###.##########");
            return lDecFormat.format(lDoubleValue);
        }
    }


    public String getOperand1Value() {
        return mOperand1TV.getText().toString();
    }


    public void resetCalculator() {

        mOperand2 = 0;
        mOperand1 = 0;

        mOperand1TV.setText("");
        mOperatorTV.setText("");
        mOperand2TV.setText(getResources().getText(R.string.default_value));

        mEqualToPressed = false;
    }


    public void deleteClicked() {

        if (!mEqualToPressed) {

            if (mOperand2TV.length() != 0)
                mOperand2TV.setText(getOperand2Value().substring(0, mOperand2TV.length() - 1));

            if (getOperand2Value().length() == 0)
                mOperand2TV.setText(getResources().getText(R.string.default_value));

        }
    }


    public void operatorClicked(MainActivity.OperatorsEnum pOperator) {

        if(pOperator == MainActivity.OperatorsEnum.SUBTRACT && getOperand2Value().equals("")){

            mOperand1TV.setText("0");
            mOperatorTV.setText(getOperator(pOperator));
            mOperand2TV.setText("");
            mCurrentOperator = pOperator;

        }
        if (!getOperand2Value().equals("")) {
            if (mEqualToPressed) {

                resetCalculator();

                mOperand1 = mResult;
                mOperatorTV.setText(getOperator(pOperator));
                mCurrentOperator = pOperator;
                mOperand1TV.setText(getDoubleValue(String.valueOf(mResult)));

                mEqualToPressed = false;

            } else {

                if (!getOperand1Value().equals("")) {
                    mOperand1 = Double.valueOf(getOperand1Value());
                }

                mOperand2 = Double.valueOf(getOperand2Value());

                if (mCurrentOperator != null)
                    mResult = Calculator.calculate(mOperand1, mOperand2, mCurrentOperator);
                else
                    mResult = Calculator.calculate(mOperand1, mOperand2, pOperator);

                mCurrentOperator = pOperator;

                if (getOperand1Value().equals("")) {
                    mOperand1TV.setText(getDoubleValue(String.valueOf(mOperand2)));

                } else {
                    mOperand1TV.setText(getDoubleValue(String.valueOf(mResult)));
                }

                mOperatorTV.setText(getOperator(pOperator));
                mOperand2TV.setText(getResources().getText(R.string.default_value));
            }
        }
    }


    public void initiateCalculation() {

        if (mCurrentOperator != null && !getOperand2Value().equals("")) {

            if (!mEqualToPressed) {

                if (getOperand1Value().length() != 0)
                    mOperand1 = Double.valueOf(getOperand1Value());

                mOperand2 = Double.valueOf(getOperand2Value());

                mResult = Calculator.calculate(mOperand1, mOperand2, mCurrentOperator);

                mOperatorTV.setText(getOperator(mCurrentOperator) + getOperand2Value());
                mOperand2TV.setText(getDoubleValue(String.valueOf(mResult)));

                mEqualToPressed = true;
                mCurrentOperator = null;
            }
        }
    }


    private String getOperator(MainActivity.OperatorsEnum operatorsEnum) {

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
}