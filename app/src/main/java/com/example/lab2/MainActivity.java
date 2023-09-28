package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private Button calculateButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.editTextWeight);
        heightEditText = findViewById(R.id.editTextHeight);
        calculateButton = findViewById(R.id.buttonCalculate);
        resetButton = findViewById(R.id.buttonReset);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input
                String weightStr = weightEditText.getText().toString();
                String heightStr = heightEditText.getText().toString();

                if (weightStr.isEmpty() || heightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter weight and height", Toast.LENGTH_SHORT).show();
                    return;
                }

                int weight = Integer.parseInt(weightStr);
                int height = Integer.parseInt(heightStr);

                // Check if weight and height are within valid range
                if (weight < 20 || weight > 200 || height < 80 || height > 300) {
                    Toast.makeText(MainActivity.this, "Weight and height should be within valid range", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Navigate to the second page (page2)
                Intent intent = new Intent(MainActivity.this, page2.class);
                intent.putExtra("weight", weight);
                intent.putExtra("height", height);
                startActivity(intent);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset weight and height to zero
                weightEditText.setText("0");
                heightEditText.setText("0");
            }
        });
    }
}
