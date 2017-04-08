package io.full.calculator.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import io.full.calculator.R;
import io.full.calculator.util.Calculator;
import io.full.calculator.util.Util;


public class ViewFragment extends Fragment {

    TextView mOperand1TV;

    TextView mOperatorTV;

    TextView mOperand2TV;

    double mOperand1;
    double mOperand2;
    double mResult;

    boolean mEqualToPressed = false;

    private Util mUtilObject;

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

        mOperand2 = 0;
        mOperand1 = 0;

        mEqualToPressed = false;

        mUtilObject = new Util();

        mOperand1TV = (TextView) pView.findViewById(R.id.operand1Line);

        mOperand2TV = (TextView) pView.findViewById(R.id.operand2Line);

        mOperatorTV = (TextView) pView.findViewById(R.id.operatorLine);

//        resetCalculator();

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
        else {
            if (!getOperand2Value().equals("0"))
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

    public String getOperand1Value() {
        return mOperand1TV.getText().toString();
    }


    public void resetCalculator() {

        mOperand1TV.setText("");
        mOperatorTV.setText("");
        mOperand2TV.setText("");

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

        if (!getOperand2Value().equals("")) {

            if (mEqualToPressed) {

                resetCalculator();

                mOperand1 = mResult;
                mOperatorTV.setText(mUtilObject.getOperatorSymbolAsString(pOperator));
                mCurrentOperator = pOperator;
                mOperand1TV.setText(mUtilObject.formatDoubleValue(String.valueOf(mResult)));

                mEqualToPressed = false;

            } else {

                if (!getOperand1Value().equals("")) {
                    mOperand1 = convertToDouble(getOperand2Value());
                }

                mOperand2 = convertToDouble(getOperand2Value());

                if(Double.isNaN(mOperand2)){
                    return;
                }

                if (mCurrentOperator != null)
                    mResult = Calculator.calculate(mOperand1, mOperand2, mCurrentOperator);
                else
                    mResult = Calculator.calculate(mOperand1, mOperand2, pOperator);

                mCurrentOperator = pOperator;

                if (getOperand1Value().equals("")) {
                    mOperand1TV.setText(mUtilObject.formatDoubleValue(String.valueOf(mOperand2)));

                } else {
                    mOperand1TV.setText(mUtilObject.formatDoubleValue(String.valueOf(mResult)));
                }

                mOperatorTV.setText(mUtilObject.getOperatorSymbolAsString(pOperator));
                mOperand2TV.setText(getResources().getText(R.string.default_value));
            }
        } else if (!getOperand1Value().equals((""))) {

            mCurrentOperator = pOperator;
            mOperatorTV.setText(mUtilObject.getOperatorSymbolAsString(pOperator));
        }
    }

    public void posNegClicked() {

        if (!mEqualToPressed) {
            if (!getOperand2Value().contains("-"))
                mOperand2TV.setText(new StringBuilder("-").append(getOperand2Value()));
            else if (getOperand2Value().contains("-"))
                mOperand2TV.setText(getOperand2Value().substring(1));
        }

    }


    public void initiateCalculation() {

        if (mCurrentOperator != null && !getOperand2Value().equals("")) {

            if (!mEqualToPressed) {

                if (getOperand1Value().length() != 0)
                    mOperand1 = Double.valueOf(getOperand1Value());

                if (!getOperand2Value().equals("")) {
                    mOperand2 = convertToDouble(getOperand2Value());

                    if(Double.isNaN(mOperand2)){
                        return;
                    }
                }
                else
                    mOperand2 = 0;

                mResult = Calculator.calculate(mOperand1, mOperand2, mCurrentOperator);

                if("+-*/%".contains(String.valueOf(mOperand2).substring(0,1))){

                    mOperatorTV.setText((new StringBuilder(mUtilObject.getOperatorSymbolAsString(mCurrentOperator))
                            .append("(")
                            .append(mUtilObject.formatDoubleValue(String.valueOf(mOperand2)))
                            .append(")")).toString());
                }else{
                    mOperatorTV.setText((new StringBuilder(mUtilObject.getOperatorSymbolAsString(mCurrentOperator))
                            .append(mUtilObject.formatDoubleValue(String.valueOf(mOperand2)))).toString());
                }

                mOperand2TV.setText(mUtilObject.formatDoubleValue(String.valueOf(mResult)));

                mEqualToPressed = true;
                mCurrentOperator = null;
            }
        }
    }

    public double convertToDouble(String number){

        double lConvertedValue ;

        try{
            lConvertedValue = Double.valueOf(number);
        }
        catch (NumberFormatException e){

            Toast.makeText(getActivity(), "Enter a valid Number", Toast.LENGTH_SHORT).show();
            mOperand2TV.setText("");

            return Double.NaN;
        }
        return lConvertedValue;
    }


}