package io.full.calculator;

/**
 * Created by user on 06/04/17.
 */

public class Calculator {

    public static double calculate(double pValue1, double pValue2, MainActivity.OperatorsEnum pOperator) {

        switch (pOperator) {
            case ADD:
                return pValue1 + pValue2;

            case SUBTRACT:

                return pValue1 - pValue2;

            case MULTIPLY:

                return pValue1 * pValue2;

            case DIVISION:

                return pValue1 / pValue2;

            case MODULO:

                return pValue1 % pValue2;
        }
        return 0;

    }
}
