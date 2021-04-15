package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseClass extends AppCompatActivity {

    TextView textViewToChange;
    static int workoutIndex = 0, exerciseIndex = 0, size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_page);

        workoutIndex = getIntent().getIntExtra("INDEX", workoutIndex);

        System.out.println("id: " + workoutIndex);

        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir, 0);

        Workout workout = appFile.returnWorkout(workoutIndex);

        size = workout.exercises.size();

        System.out.println("size: " + size);

        textViewToChange = findViewById(R.id.WorkoutTitle);
        textViewToChange.setText(workout.name);

        textViewToChange = findViewById(R.id.ExerciseTitle);
        textViewToChange.setText(workout.exercises.get(exerciseIndex).name);

        if (size-1 == exerciseIndex) findViewById(R.id.nextBtn).setVisibility(View.INVISIBLE);
        else findViewById(R.id.nextBtn).setVisibility(View.VISIBLE);

        findViewById(R.id.cancel).setOnClickListener(v -> returnHome());
        findViewById(R.id.nextBtn).setOnClickListener(v -> nextExercise());
    }

    public void returnHome() {
        exerciseIndex = 0;
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
