package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class page2 extends AppCompatActivity {

    private int weight;  // Weight in kilograms
    private int height;  // Height in centimeters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        // Retrieve weight and height from the intent
        Intent intent = getIntent();
        weight = intent.getIntExtra("weight", 0);
        height = intent.getIntExtra("height", 0);

        // Convert height to meters
        double heightInMeters = height / 100.0;

        // Calculate BMI
        int bmi = calculateBMI(weight, heightInMeters);

        // Display BMI and BMI range
        displayBMIAndRange(bmi);

        Button finishButton = findViewById(R.id.buttonFinish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish this activity and go back to the first page
                Intent intent = new Intent(page2.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("reset", true);  // Signal to reset weight and height
                startActivity(intent);
                finish();
            }
        });
    }

    private int calculateBMI(int weight, double height) {
        // Check for division by zero
        if (height == 0) {
            return 0;
        }

        // Calculate BMI and round to the nearest whole number
        double bmi = weight / (height * height);
        return (int) Math.round(bmi);
    }

    private void displayBMIAndRange(int bmi) {
        TextView bmiResultTextView = findViewById(R.id.textViewBMIResult);

        // Display the BMI
        bmiResultTextView.setText("BMI: " + bmi);

        // Determine BMI range and set the corresponding message
        String bmiRangeMessage = getBMIRangeMessage(bmi);
        TextView bmiRangeTextView = findViewById(R.id.textViewBMIRange);
        bmiRangeTextView.setText(bmiRangeMessage);
    }

    private String getBMIRangeMessage(int bmi) {
        if (bmi < 18.5) {
            return "You're in the underweight range";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "You're in the healthy weight range";
        } else if (bmi >= 25 && bmi < 30) {
            return "You're in the overweight range";
        } else {
            return "BMI out of range";
        }
    }
}
