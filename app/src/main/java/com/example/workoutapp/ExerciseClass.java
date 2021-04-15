package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseClass extends AppCompatActivity {

    TextView textViewToChange;
    static int workoutIndex = 3, exerciseIndex = 0, size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_page);

        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir);

        // Get the index from the Button by id onClick
        // Send the index to the returnWorkout function

        Workout workout = appFile.returnWorkout(workoutIndex);

//        workout.exercises.get(index).name

        size = workout.exercises.size();

        textViewToChange = findViewById(R.id.WorkoutTitle);
        textViewToChange.setText(workout.name);

        textViewToChange = findViewById(R.id.ExerciseTitle);
        textViewToChange.setText(workout.exercises.get(exerciseIndex).name);

        System.out.println("size " + workout.exercises.size() + " -----");

        if (size-1 == exerciseIndex) findViewById(R.id.nextBtn).setVisibility(View.INVISIBLE);
        else findViewById(R.id.nextBtn).setVisibility(View.VISIBLE);

        findViewById(R.id.cancel).setOnClickListener(v -> returnHome());
        findViewById(R.id.nextBtn).setOnClickListener(v -> nextExercise());
    }

    public void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void nextExercise() {
        if (exerciseIndex != size-1)
        {
            exerciseIndex++;
            Intent intent = new Intent(this, ExerciseClass.class);
            startActivity(intent);
        }
        else
        {
            exerciseIndex = 0;
        }
    }
}
