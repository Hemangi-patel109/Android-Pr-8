package com.example.myapplication8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication8.R;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Set up button click listeners
        setButtonListeners();
    }

    private void setButtonListeners() {
        int[] numberButtons = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.zero};
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(this::onNumberClick);
        }

        findViewById(R.id.add).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.subtract).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.multiply).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.divide).setOnClickListener(this::onOperatorClick);

        findViewById(R.id.equals).setOnClickListener(this::onEqualsClick);
        findViewById(R.id.Clear).setOnClickListener(this::onClearClick);
        findViewById(R.id.dot).setOnClickListener(this::onDotClick);
    }

    private void onNumberClick(View view) {
        Button button = (Button) view;
        if (isOperatorClicked) {
            currentInput = "";
            isOperatorClicked = false;
        }
        currentInput += button.getText().toString();
        display.setText(currentInput);
    }

    private void onOperatorClick(View view) {
        Button button = (Button) view;
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            operator = button.getText().toString();
            isOperatorClicked = true;
        }
        currentInput += button.getText().toString();
        display.setText(currentInput);
    }

    private void onEqualsClick(View view) {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "x":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operator = "";
        }
    }

    private void onClearClick(View view) {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        display.setText("0");
    }

    private void onDotClick(View view) {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            display.setText(currentInput);
        }
    }
}
