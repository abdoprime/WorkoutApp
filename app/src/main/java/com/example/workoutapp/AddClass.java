package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
                    "Workout : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();

            if (spinner.getSelectedItem().equals("Run"))
            {
                findViewById(R.id.sets).setVisibility(View.INVISIBLE);
                findViewById(R.id.reps).setVisibility(View.INVISIBLE);
                findViewById(R.id.NumberOfSets).setVisibility(View.INVISIBLE);
                findViewById(R.id.NumberOfReps).setVisibility(View.INVISIBLE);

                findViewById(R.id.RunTime).setVisibility(View.VISIBLE);
                findViewById(R.id.TimeHeading).setVisibility(View.VISIBLE);
            }
            else {
                findViewById(R.id.sets).setVisibility(View.VISIBLE);
                findViewById(R.id.reps).setVisibility(View.VISIBLE);
                findViewById(R.id.NumberOfSets).setVisibility(View.VISIBLE);
                findViewById(R.id.NumberOfReps).setVisibility(View.VISIBLE);

                findViewById(R.id.RunTime).setVisibility(View.INVISIBLE);
                findViewById(R.id.TimeHeading).setVisibility(View.INVISIBLE);
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
        ArrayList<Exercise> exercisesList = new ArrayList<>();

        findViewById(R.id.SaveBtn).setOnClickListener(v -> {
            EditText ed = findViewById(R.id.WorkoutName);
            String exerciseName = ed.getText().toString();

            if (exerciseName.equals(""))
            {
                Toast.makeText(AddClass.this,
                        "File name cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);
                String[] array = strDate.split("-");

                int year = Integer.parseInt(array[0]), month = Integer.parseInt(array[1]),
                        day = Integer.parseInt(array[2]);

                System.out.println(year + "/" + month + "/" + day);
                Workout workout = new Workout(exerciseName, year, month, day, exercisesList);









                if (workout != null)
                {
                    String filedir = getFilesDir().getPath();
                    AppFileManager appFile = new AppFileManager(filedir,0);
                    System.out.println("Exercise: " + workout.name + " " + workout.year + " " + workout.month + " " + workout.day + " " + " end");
                    appFile.newWorkout(workout);
                }
                else
                {
                    System.out.println("Workout null");
                }
                ed.getText().clear();
                returnHome();





            }
        });

        btnSubmit = findViewById(R.id.CreateBtn);
        btnSubmit.setOnClickListener(v -> {
            if (exercisesList.isEmpty())
            {
                Toast.makeText(AddClass.this,
                        "No workouts to create", Toast.LENGTH_SHORT).show();
            }
            else {

                findViewById(R.id.transparency).setVisibility(View.VISIBLE);

                findViewById(R.id.saveViewBox).setVisibility(View.VISIBLE);
                findViewById(R.id.workoutHeading).setVisibility(View.VISIBLE);
                findViewById(R.id.WorkoutName).setVisibility(View.VISIBLE);
                findViewById(R.id.SaveBtn).setVisibility(View.VISIBLE);
                findViewById(R.id.cancelBtn).setVisibility(View.VISIBLE);

                findViewById(R.id.cancel).setVisibility(View.INVISIBLE);
                findViewById(R.id.CreateBtn).setVisibility(View.INVISIBLE);
                findViewById(R.id.AddBtn).setVisibility(View.INVISIBLE);
                findViewById(R.id.workout_spinner).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.cancelBtn).setOnClickListener(v -> {
            EditText ed = findViewById(R.id.WorkoutName);
            ed.getText().clear();

            findViewById(R.id.transparency).setVisibility(View.INVISIBLE);

            findViewById(R.id.saveViewBox).setVisibility(View.INVISIBLE);
            findViewById(R.id.workoutHeading).setVisibility(View.INVISIBLE);
            findViewById(R.id.WorkoutName).setVisibility(View.INVISIBLE);
            findViewById(R.id.SaveBtn).setVisibility(View.INVISIBLE);
            findViewById(R.id.cancelBtn).setVisibility(View.INVISIBLE);

            findViewById(R.id.cancel).setVisibility(View.VISIBLE);
            findViewById(R.id.CreateBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.AddBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.workout_spinner).setVisibility(View.VISIBLE);
        });

        // Add button listener to add the exercises
        findViewById(R.id.AddBtn).setOnClickListener(v -> {

            // Get the id of the edit text
            EditText ed = findViewById(R.id.NumberOfReps);
            String temp = ed.getText().toString();
            int reps = 1, sets = 1, time = 1;
            Exercise exercise;

            if (spinner.getSelectedItem().equals("Run"))
            {
                ed = findViewById(R.id.RunTime);
                temp = ed.getText().toString();

                if (!"".equals(temp)) time = (Integer.parseInt(temp));

                exercise = new Exercise("Run", 't', 0, time, 0);

                ed.getText().clear();
            }
            else {
                // check for empty string
                if (!"".equals(temp)) reps = (Integer.parseInt(temp));

                // clear the text after added
                ed.getText().clear();

                ed = findViewById(R.id.NumberOfSets);
                temp = ed.getText().toString();

                if (!"".equals(temp)) sets = (Integer.parseInt(temp));

                ed.getText().clear();

                exercise = new Exercise((String) spinner.getSelectedItem(), 's', sets, reps);
            }

            Toast.makeText(AddClass.this,
                    "Workout Added", Toast.LENGTH_SHORT).show();

            exercisesList.add(exercise);
//            System.out.println("UserValues " + reps + " String: " + sets + " end " + time);
        });
    }

    public void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
