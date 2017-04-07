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

        View view = inflater.inflate(R.layout.fragment_view_panel, container, false);

        init(view);

        return view;

    }

    public void init(View v) {

        mOperand1TV = (TextView) v.findViewById(R.id.operand1Line);

        mOperand2TV = (TextView) v.findViewById(R.id.operand2Line);

        mOperatorTV = (TextView) v.findViewById(R.id.operatorLine);

        resetCalculator();
    }


    public void addValueToOperand2(String pValue) {

        if (mEqualToPressed) {
            resetCalculator();
            mEqualToPressed = false;
        }

        if (getOperand2Value().equals("0"))
            mOperand2TV.setText(pValue);
        else
            mOperand2TV.setText((new StringBuilder(getOperand2Value()).append(pValue)).toString());

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

        if (lDoubleValue % 1 == 0){

            DecimalFormat df = new DecimalFormat("###.#");
            return df.format(lDoubleValue);

        }
        else
            return String.valueOf(lDoubleValue);
    }


    public String getOperand1Value() {
        return mOperand1TV.getText().toString();
    }


    public void resetCalculator() {

        mOperand2 = 0;
        mOperand1 = 0;

        mOperand1TV.setText("");
        mOperatorTV.setText("");
        mOperand2TV.setText(getResources().getText(R.string.default_value).toString());
        mEqualToPressed = false;
    }


    public void deleteClicked() {

        if (mOperand2TV.length() != 0)
            mOperand2TV.setText(getOperand2Value().substring(0, mOperand2TV.length() - 1));


        if (getOperand2Value().length() == 0)
            mOperand2TV.setText(getResources().getText(R.string.default_value));


    }


    public void operatorClicked(MainActivity.OperatorsEnum operator) {

        if (mEqualToPressed) {

            resetCalculator();

            mOperand1 = mResult;
            mOperatorTV.setText(getOperator(operator));
            mCurrentOperator = operator;
            mOperand1TV.setText(getDoubleValue(String.valueOf(mResult)));

            mEqualToPressed = false;

        } else {

            if (!getOperand1Value().equals("")) {
                mOperand1 = Double.valueOf(getOperand1Value());
            }

            mOperand2 = Double.valueOf(getOperand2Value());

            if(mCurrentOperator!=null)
                mResult = Calculator.calculate(mOperand1, mOperand2, mCurrentOperator);
            else
                mResult = Calculator.calculate(mOperand1, mOperand2, operator);

// TODO: Check it

            mCurrentOperator = operator;

            if (getOperand1Value().equals("")) {
                mOperand1TV.setText(getDoubleValue(String.valueOf(mOperand2)));

            } else {
                mOperand1TV.setText(getDoubleValue(String.valueOf(mResult)));
            }

            mOperatorTV.setText(getOperator(operator));
            mOperand2TV.setText(getResources().getText(R.string.default_value));
        }
    }


    public void initiateCalculation() {

        if(mCurrentOperator!=null) {

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