package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseClass extends AppCompatActivity {

    TextView textViewToChange;
    static int workoutIndex = 0, exerciseIndex = 0, size = 0;
    String name = "";

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

        name = workout.name;

        textViewToChange = findViewById(R.id.WorkoutTitle);
        textViewToChange.setText(workout.name);

        textViewToChange = findViewById(R.id.ExerciseTitle);
        textViewToChange.setText(workout.exercises.get(exerciseIndex).name);

        if (size-1 == exerciseIndex)
        {
            findViewById(R.id.nextBtn).setVisibility(View.INVISIBLE);
            findViewById(R.id.FinishBtn).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.nextBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.FinishBtn).setVisibility(View.INVISIBLE);
        }

        if (exerciseIndex == 0) findViewById(R.id.backBtn).setVisibility(View.INVISIBLE);
        else findViewById(R.id.backBtn).setVisibility(View.VISIBLE);

        if (workout.exercises.get(exerciseIndex).name.equals("Run")) findViewById(R.id.startBtn).setVisibility(View.VISIBLE);
        else findViewById(R.id.startBtn).setVisibility(View.INVISIBLE);

        findViewById(R.id.cancel).setOnClickListener(v -> returnHome());
        findViewById(R.id.nextBtn).setOnClickListener(v -> nextExercise());
        findViewById(R.id.backBtn).setOnClickListener(v -> backExercise());
        findViewById(R.id.FinishBtn).setOnClickListener(v -> FinishWorkout());
    }

    public void returnHome() {
        exerciseIndex = 0;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void nextExercise() {
        exerciseIndex++;
        Intent intent = new Intent(this, ExerciseClass.class);
        startActivity(intent);
    }
    public void backExercise() {
        exerciseIndex--;
        Intent intent = new Intent(this, ExerciseClass.class);
        startActivity(intent);
    }
    public void FinishWorkout() {
        exerciseIndex = 0;
        Intent intent = new Intent(this, FinishClass.class);
        intent.putExtra("NAME", name + "\nCompleted!");
        startActivity(intent);
    }
}
