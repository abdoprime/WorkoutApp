package com.example.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddClass extends AppCompatActivity {

    private Button bt;
    private Spinner spinner;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);

        spinner = findViewById(R.id.workout_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.workout_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        bt = findViewById(R.id.cancel);
        bt.setOnClickListener(v -> returnHome());
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();

            if (spinner.getSelectedItem().equals("Run"))
            {
                findViewById(R.id.sets).setVisibility(View.INVISIBLE);
                findViewById(R.id.reps).setVisibility(View.INVISIBLE);
                findViewById(R.id.NumberOfSets).setVisibility(View.INVISIBLE);
                findViewById(R.id.NumberOfReps).setVisibility(View.INVISIBLE);

                findViewById(R.id.RunTime).setVisibility(View.VISIBLE);
            }
            else {
                findViewById(R.id.sets).setVisibility(View.VISIBLE);
                findViewById(R.id.reps).setVisibility(View.VISIBLE);
                findViewById(R.id.NumberOfSets).setVisibility(View.VISIBLE);
                findViewById(R.id.NumberOfReps).setVisibility(View.VISIBLE);

                findViewById(R.id.RunTime).setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = findViewById(R.id.workout_spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        btnSubmit = findViewById(R.id.CreateBtn);
        btnSubmit.setOnClickListener(v -> {

        });
    }

    public void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
