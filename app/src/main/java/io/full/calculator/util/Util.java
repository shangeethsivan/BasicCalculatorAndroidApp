package io.full.calculator.util;

import java.text.DecimalFormat;

import io.full.calculator.ui.MainActivity;

/**
 * Created by user on 07/04/17.
 */

public class Util {

    public String getOperatorSymbolAsString(MainActivity.OperatorsEnum operatorsEnum) {

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



    public String formatDoubleValue(String pStringValue) {

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

}
