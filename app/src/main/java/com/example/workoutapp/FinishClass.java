package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FinishClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_class);

        String name = getIntent().getStringExtra("NAME");

        TextView textViewToChange = findViewById(R.id.WorkoutTitle);
        textViewToChange.setText(name);

        findViewById(R.id.noBtn).setOnClickListener(v -> returnHome());
    }
    public void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
