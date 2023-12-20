package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import android.graphics.Point;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    float firstOperand, secondOperand;
    boolean emptyFirst = true, emptySecond = true;
    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        TextView screen = (TextView) findViewById(R.id.screen);
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        if (width > height) {
            Guideline guidline_devider = (Guideline) findViewById(R.id.guidline_devider);
            ConstraintLayout.LayoutParams params_devider = (ConstraintLayout.LayoutParams) guidline_devider.getLayoutParams();
            params_devider.guidePercent = 0.315f;


            Guideline guidline_r4 = (Guideline) findViewById(R.id.guidline_r4);
            ConstraintLayout.LayoutParams params_r4 = (ConstraintLayout.LayoutParams) guidline_r4.getLayoutParams();
            params_r4.guidePercent = 0.449f;
            guidline_r4.setLayoutParams(params_r4);

            Guideline guidline_r3 = (Guideline) findViewById(R.id.guidline_r3);
            ConstraintLayout.LayoutParams params_r3 = (ConstraintLayout.LayoutParams) guidline_r3.getLayoutParams();
            params_r3.guidePercent = 0.583f;
            guidline_r3.setLayoutParams(params_r3);

            Guideline guidline_r2 = (Guideline) findViewById(R.id.guidline_r2);
            ConstraintLayout.LayoutParams params_r2 = (ConstraintLayout.LayoutParams) guidline_r2.getLayoutParams();
            params_r2.guidePercent = 0.717f;
            guidline_r2.setLayoutParams(params_r2);

            Guideline guidline_r1 = (Guideline) findViewById(R.id.guidline_r1);
            ConstraintLayout.LayoutParams params_r1 = (ConstraintLayout.LayoutParams) guidline_r1.getLayoutParams();
            params_r1.guidePercent = 0.851f;
            guidline_r1.setLayoutParams(params_r1);
        }


    }

    public void onClickOperations(View view) {
        TextView screen = (TextView) findViewById(R.id.screen);
        if (!operation.isEmpty() && !emptyFirst && emptySecond) {
            noFullReset();
        }
        switch (view.getId()) {
            case R.id.add:
                operation = "+";
                break;
            case R.id.sub:
                operation = "-";
                break;
            case R.id.mul:
                operation = "*";
                break;
            case R.id.frac:
                operation = "/";
                break;
            case R.id.pow:
                operation = "^";
                break;
        }
        if (firstOperand % 1 == 0.0F) {
            int intFirstOperand = (int) firstOperand;
            screen.setText(intFirstOperand + operation);
            checkOperators();
        }
        else {
            screen.setText(firstOperand + operation);
            checkOperators();
        }
    }


    public void onClickSqrt(View view){
        TextView screen = (TextView) findViewById(R.id.screen);
        if (emptyFirst) {
            operation = "sqrt";
            checkOperators();
            noFullReset();
            screen.setText(Float.toString(firstOperand));
        } else if (!operation.isEmpty() && !emptyFirst && emptySecond) {
            try {
                checkOperators();
                noFullReset();
                operation = "sqrt";
                checkOperators();
                noFullReset();
                screen.setText(Float.toString(firstOperand));
            } catch (Exception ex){}

        }
    }
    public void onClickNumbers(View view) {
        TextView screen = (TextView) findViewById(R.id.screen);
        switch (view.getId()) {
            case R.id.one:
                screen.setText(screen.getText() + "1");
                break;
            case R.id.two:
                screen.setText(screen.getText() + "2");
                break;
            case R.id.three:
                screen.setText(screen.getText() + "3");
                break;
            case R.id.four:
                screen.setText(screen.getText() + "4");
                break;
            case R.id.five:
                screen.setText(screen.getText() + "5");
                break;
            case R.id.six:
                screen.setText(screen.getText() + "6");
                break;
            case R.id.seven:
                screen.setText(screen.getText() + "7");
                break;
            case R.id.eight:
                screen.setText(screen.getText() + "8");
                break;
            case R.id.nine:
                screen.setText(screen.getText() + "9");
                break;
            case R.id.zero:
                    screen.setText(screen.getText() + "0");
                break;
            case R.id.doubleZero:
                    screen.setText(screen.getText() + "00");
                break;
            case R.id.dot:
                screen.setText(screen.getText() + ".");
                break;
        }
        checkOperators();
    }

    private void checkOperators() {
        TextView screen = (TextView) findViewById(R.id.screen);
        if (operation.isEmpty() && (emptySecond)) {
            firstOperand = Float.parseFloat((String) screen.getText());
        } else if (!operation.isEmpty() && emptyFirst) {
            emptyFirst = false;
        } else if (!operation.isEmpty() && !emptyFirst && operation!="sqrt") {
            String screenInfo = (String) screen.getText();
            String nowString = ((String) screen.getText()).substring(screenInfo.indexOf(operation) + 1);
            secondOperand = Float.parseFloat(nowString);
        } else if (!operation.isEmpty() && !emptyFirst && emptySecond) {
            emptySecond = false;
        }
    }

    private float calculate() {
        float ans = 0;
        switch (operation) {
            case "+":
                ans = firstOperand + secondOperand;
                break;
            case "-":
                ans = firstOperand - secondOperand;
                break;
            case "*":
                ans = firstOperand * secondOperand;
                break;
            case "/":
                ans = firstOperand / secondOperand;
                break;
            case "sqrt":
                ans = (float) Math.sqrt(firstOperand);
                break;
            case "^":
                ans = (float) Math.pow(firstOperand, secondOperand);
                break;
        }
        return ans;
    }

    public void onClickReset(View view) {
        TextView screen = (TextView) findViewById(R.id.screen);
        screen.setText("");
        emptySecond = true;
        emptyFirst = true;
        operation = "";
        firstOperand=0;
        secondOperand=0;
    }

    public void onClickEq(View view) {
        TextView screen = (TextView) findViewById(R.id.screen);
        if (!operation.isEmpty() && !emptyFirst && emptySecond) {
            float calc = calculate();
            if (calc % 1 == 0.0F) {
                int intCalc = (int) calc;
                screen.setText(Integer.toString(intCalc));
            }
            else {
                screen.setText(Float.toString(calc));
            }
            firstOperand = calculate();
            emptyFirst = true;
            emptySecond = true;
        }
    }

    private void noFullReset() {
        firstOperand = calculate();
        emptyFirst = true;
        emptySecond = true;
        secondOperand = 0;
    }
}