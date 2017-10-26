package io.full.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.junit.runners.Parameterized.Parameters;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import io.full.calculator.ui.MainActivity;
import io.full.calculator.util.Calculator;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(Parameterized.class)
public class ExampleUnitTest {

    private double d1;
    private double d2;
    private double d3;

    public ExampleUnitTest(double[] d) {
        this.d1 = d[0];
        this.d2 = d[1];
        this.d3 = d[2];
    }

    @Parameters
    public static Collection<double[]> testConditions() {

        double expectedOutputs[][] = {
                {-1.0, 1,-2},
                {5.0, 2,3},
                {2.0, 1,1},
                {11.0, 5,6}
        };

        return Arrays.asList(expectedOutputs);
    }



    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(d1, Calculator.calculate(d2, d3, MainActivity.OperatorsEnum.ADD),0.01);

    }

}