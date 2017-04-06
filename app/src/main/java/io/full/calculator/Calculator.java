package io.full.calculator;

/**
 * Created by user on 06/04/17.
 */

public class Calculator {

    public static double calculate(String operand1, String operand2, MainActivity.OperatorsEnum operator) {

        double value1 = convertToDouble(operand1);

        double value2 = convertToDouble(operand2);

        switch (operator) {
            case ADD:
                return value1 + value2;

            case SUBTRACT:

                return value1 - value2;

            case MULTIPLY:

                return value1 * value2;

            case DIVISION:

                return value1 / value2;

            case MODULO:

                return value1 % value2;
        }
        return 0;

    }

    private static double convertToDouble(String value) {

        return Double.valueOf(value);

    }
}
