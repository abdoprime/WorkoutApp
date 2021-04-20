package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseClass extends AppCompatActivity {

    TextView textViewToChange;
    static int workoutIndex = 0, exerciseIndex = 0, size = 0;
    String name = "";
    Workout workout = null;

    private Button start, end;
    private CountDownTimer timer;
    int startTime;
    int hoursLeft, minutesLeft, secondsLeft;
    TextView hoursLeftText, minutesLeftText, secondsLeftText;
    int totalSecondsLeft;

    static int hour = 0, minutes = 0, seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_page);

        hour = getIntent().getIntExtra("HOUR", hour);
        minutes = getIntent().getIntExtra("MIN", minutes);
        seconds = getIntent().getIntExtra("SEC", seconds);

        workoutIndex = getIntent().getIntExtra("INDEX", workoutIndex);

        System.out.println("Time: " + hour + "/" + minutes + "/" + seconds + " |");

        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir, 0);

        workout = appFile.returnWorkout(workoutIndex);

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

        if (workout.exercises.get(exerciseIndex).name.equals("Run"))
        {
            findViewById(R.id.startBtn).setVisibility(View.VISIBLE);

            findViewById(R.id.SetsReps).setVisibility(View.INVISIBLE);
            findViewById(R.id.numOfSets).setVisibility(View.INVISIBLE);
            findViewById(R.id.numOfReps).setVisibility(View.INVISIBLE);

            textViewToChange = findViewById(R.id.HoursLeft);
            textViewToChange.setText(String.format("%02d",(workout.exercises.get(exerciseIndex).hours)));

            textViewToChange = findViewById(R.id.MinutesLeft);
            textViewToChange.setText(String.format("%02d",(workout.exercises.get(exerciseIndex).minutes)));

            textViewToChange = findViewById(R.id.SecondsLeft);
            textViewToChange.setText(String.format("%02d",(workout.exercises.get(exerciseIndex).seconds)));
        }
        else
        {
            findViewById(R.id.startBtn).setVisibility(View.INVISIBLE);
            findViewById(R.id.MinutesLeft).setVisibility(View.INVISIBLE);
            findViewById(R.id.SecondsLeft).setVisibility(View.INVISIBLE);
            findViewById(R.id.HoursLeft).setVisibility(View.INVISIBLE);
            findViewById(R.id.colonSign).setVisibility(View.INVISIBLE);
            findViewById(R.id.colonSign2).setVisibility(View.INVISIBLE);

            findViewById(R.id.SetsReps).setVisibility(View.VISIBLE);
            findViewById(R.id.numOfSets).setVisibility(View.VISIBLE);
            findViewById(R.id.numOfReps).setVisibility(View.VISIBLE);

            textViewToChange = findViewById(R.id.numOfSets);
            textViewToChange.setText(String.valueOf(workout.exercises.get(exerciseIndex).sets));

            textViewToChange = findViewById(R.id.numOfReps);
            textViewToChange.setText(String.valueOf(workout.exercises.get(exerciseIndex).reps));
        }

        hoursLeftText = findViewById(R.id.HoursLeft);
        minutesLeftText = findViewById(R.id.MinutesLeft);
        secondsLeftText = findViewById(R.id.SecondsLeft);
        setupButtons();

        findViewById(R.id.cancel).setOnClickListener(v -> returnHome());
        findViewById(R.id.nextBtn).setOnClickListener(v -> nextExercise());
        findViewById(R.id.backBtn).setOnClickListener(v -> backExercise());
        findViewById(R.id.FinishBtn).setOnClickListener(v -> FinishWorkout());
    }

    private void finishTimer(String message) {
        Toast.makeText(ExerciseClass.this,
                message, Toast.LENGTH_SHORT).show();
        findViewById(R.id.startBtn).setVisibility(View.VISIBLE);
        findViewById(R.id.endBtn).setVisibility(View.INVISIBLE);

        textViewToChange = findViewById(R.id.MinutesLeft);
        textViewToChange.setText(String.format("%02d",(workout.exercises.get(exerciseIndex).minutes)));

        textViewToChange = findViewById(R.id.SecondsLeft);
        textViewToChange.setText(String.format("%02d",(workout.exercises.get(exerciseIndex).seconds)));
    }

    private void updateTimeRemaining(long millisUntilFinished) {
        totalSecondsLeft = (int) millisUntilFinished / 1000;
        hoursLeft = totalSecondsLeft / 3600;
        minutesLeft = (totalSecondsLeft % 3600) / 60;
        secondsLeft = totalSecondsLeft % 60;

        hoursLeftText.setText(String.format("%02d", hoursLeft));
        minutesLeftText.setText(String.format("%02d", minutesLeft));
        secondsLeftText.setText(String.format("%02d", secondsLeft));
    }

    private void setupButtons() {
        start = findViewById(R.id.startBtn);
        end = findViewById(R.id.endBtn);
        start.setOnClickListener(v -> {
            startTime = 0;
            startTime += workout.exercises.get(exerciseIndex).seconds * 1000;
            startTime += workout.exercises.get(exerciseIndex).minutes * 60 * 1000;
            startTime += workout.exercises.get(exerciseIndex).hours * 60 * 60 * 1000;

            start.setVisibility(View.INVISIBLE);
            end.setVisibility(View.VISIBLE);

            timer = new CountDownTimer(startTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimeRemaining(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    finishTimer("Running done");
                }
            }.start();
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                finishTimer("Timer stopped");
            }
        });
    }

    public void returnHome() {
        exerciseIndex = 0;
        Intent intent = new Intent(this, WorkoutPage.class);
        intent.putExtra("INDEX", workoutIndex);
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
        intent.putExtra("WORKOUT", workoutIndex);
        intent.putExtra("HOUR", hour);
        intent.putExtra("MIN", minutes);
        intent.putExtra("SEC", seconds);
        startActivity(intent);
    }
}
