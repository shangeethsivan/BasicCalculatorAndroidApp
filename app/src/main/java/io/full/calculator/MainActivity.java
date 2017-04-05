package io.full.calculator;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    enum operatorsEnum {ADD, SUBTRACT, MULTIPLY, DIVISION, MODULO}

    TextView backLine;
    TextView operatorLine;
    TextView mainLine;

    operatorsEnum currentOperator ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backLine = (TextView) findViewById(R.id.backLine);
        operatorLine = (TextView) findViewById(R.id.operator);
        mainLine = (TextView) findViewById(R.id.mainLine);

    }

    public void addNumber(String number) {

        mainLine.setText(mainLine.getText() + number);

    }

    public void resetCalculator() {

        backLine.setText("");
        operatorLine.setText("");
        mainLine.setText("");

    }

    public void deleteClicked() {

        if (mainLine.length() != 0)
            mainLine.setText(mainLine.getText().toString().substring(0, mainLine.length() - 1));

    }

    public void operatorClicked(operatorsEnum operator) {

        String operationCharacter = "";
        switch (operator) {
            case ADD:
                operationCharacter = "+";
                currentOperator = operatorsEnum.ADD;
                break;
            case SUBTRACT:
                operationCharacter = "-";
                currentOperator = operatorsEnum.SUBTRACT;
                break;
            case MULTIPLY:
                operationCharacter = "*";
                currentOperator = operatorsEnum.MULTIPLY;
                break;
            case DIVISION:
                operationCharacter = "/";
                currentOperator = operatorsEnum.DIVISION;
                break;
            case MODULO:
                operationCharacter = "%";
                currentOperator = operatorsEnum.MODULO;
                break;
        }

        operatorLine.setText(operationCharacter);

        if(backLine.getText().length() == 0)
            backLine.setText(mainLine.getText());

    }


    public void processClickEvent(View view) {

        int id = view.getId();

        if(mainLine.getText().equals("0") && mainLine.getText().length() == 1)
            mainLine.setText("");

        switch (id) {

            case R.id.number_0:
                if (mainLine.getText().length() != 1 && !mainLine.getText().equals("0"))
                    addNumber("0");
                break;

            case R.id.number_1:
                addNumber("1");
                break;

            case R.id.number_2:
                addNumber("2");
                break;

            case R.id.number_3:
                addNumber("3");
                break;

            case R.id.number_4:
                addNumber("4");
                break;

            case R.id.number_5:
                addNumber("5");
                break;

            case R.id.number_6:
                addNumber("6");
                break;

            case R.id.number_7:
                addNumber("7");
                break;

            case R.id.number_8:
                addNumber("8");
                break;

            case R.id.number_9:
                addNumber("9");
                break;

            case R.id.decimalPoint:
                addNumber(".");
                break;

            case R.id.operation_add:
                operatorClicked(operatorsEnum.ADD);
                break;
            case R.id.operation_subtract:

                operatorClicked(operatorsEnum.SUBTRACT);
                break;
            case R.id.operation_multiply:

                operatorClicked(operatorsEnum.MULTIPLY);
                break;
            case R.id.operation_modulo:

                operatorClicked(operatorsEnum.MODULO);
                break;
            case R.id.operation_delete:
                deleteClicked();
                break;

            case R.id.operation_clear:
                resetCalculator();
                break;

        }

    }

    public static class ControlPanelFragment extends Fragment {


        public ControlPanelFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_control_panel, container, false);
        }


    }


    public static class ViewPanelFragment extends Fragment {

        public ViewPanelFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_view_panel, container, false);
        }

    }

}
