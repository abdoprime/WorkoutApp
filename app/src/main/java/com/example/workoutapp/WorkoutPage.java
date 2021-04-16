package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkoutPage extends AppCompatActivity {
    int index = 0, size = 0;
    TextView backgroundText,msgText;
    View transparency;
    Button yesBtn, noBtn;
    ArrayList<Exercise> exercises;
    String workoutName = "";
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir, 0);
        super.onCreate(savedInstanceState);

        index = getIntent().getIntExtra("INDEX", index);

        setContentView(R.layout.workout_page);

        Workout workout = appFile.returnWorkout(index);
        exercises = workout.exercises;

        size = exercises.size();

        getSupportActionBar().setTitle(String.valueOf(workout.name));

        workoutName = workout.name;
        year = workout.year;
        month = workout.month;
        day = workout.day;

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.exerciseView);
        ExerciseAdapter exAdapter = new ExerciseAdapter(this, exercises);
        recyclerView.setAdapter(exAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backgroundText = findViewById(R.id.backgroundText);
        msgText = findViewById(R.id.deleteMsg);
        yesBtn = findViewById(R.id.yesButton);
        noBtn = findViewById(R.id.noButton);
        transparency = findViewById(R.id.transparency2);

        noBtn.setVisibility(View.INVISIBLE);
        yesBtn.setVisibility(View.INVISIBLE);
        msgText.setVisibility(View.INVISIBLE);
        backgroundText.setVisibility(View.INVISIBLE);
    }
    public void deleteThis(View view) {
        noBtn.setVisibility(View.VISIBLE);
        yesBtn.setVisibility(View.VISIBLE);
        msgText.setVisibility(View.VISIBLE);
        backgroundText.setVisibility(View.VISIBLE);
        transparency.setVisibility(View.VISIBLE);

        findViewById(R.id.runButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.deleteButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.editButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.backButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.backSign).setVisibility(View.INVISIBLE);
    }

    public void backThis(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void noDelete(View view)
    {
        noBtn.setVisibility(View.INVISIBLE);
        yesBtn.setVisibility(View.INVISIBLE);
        msgText.setVisibility(View.INVISIBLE);
        backgroundText.setVisibility(View.INVISIBLE);
        transparency.setVisibility(View.INVISIBLE);

        findViewById(R.id.runButton).setVisibility(View.VISIBLE);
        findViewById(R.id.deleteButton).setVisibility(View.VISIBLE);
        findViewById(R.id.editButton).setVisibility(View.VISIBLE);
        findViewById(R.id.backButton).setVisibility(View.VISIBLE);
        findViewById(R.id.backSign).setVisibility(View.VISIBLE);
    }

    public void yesDelete(View view)
    {
        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir, 0);
        appFile.deleteWorkout(index);

        Toast.makeText(WorkoutPage.this,
                "Workout deleted successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startWorkout(View view) {
        System.out.println("this ran");

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
        String strDate = dateFormat.format(date);
        String[] array = strDate.split("-");

        int hour = Integer.parseInt(array[0]), minutes = Integer.parseInt(array[1]),
                seconds = Integer.parseInt(array[2]);

        Intent intent = new Intent(this, ExerciseClass.class);
        intent.putExtra("INDEX", index);
        intent.putExtra("HOUR", hour);
        intent.putExtra("MIN", minutes);
        intent.putExtra("SEC", seconds);
        startActivity(intent);
    }

    public void editWorkout(View view)
    {
        findViewById(R.id.runButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.deleteButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.editButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.backButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.backSign).setVisibility(View.INVISIBLE);

        findViewById(R.id.cancelEdit).setVisibility(View.VISIBLE);
        findViewById(R.id.saveEdit).setVisibility(View.VISIBLE);

        findViewById(R.id.EditSets).setEnabled(true);
        findViewById(R.id.EditReps).setEnabled(true);
        findViewById(R.id.HH).setEnabled(true);
        findViewById(R.id.MM).setEnabled(true);
        findViewById(R.id.SS).setEnabled(true);
    }

    public void cancelEdit(View view)
    {
        findViewById(R.id.runButton).setVisibility(View.VISIBLE);
        findViewById(R.id.deleteButton).setVisibility(View.VISIBLE);
        findViewById(R.id.editButton).setVisibility(View.VISIBLE);
        findViewById(R.id.backButton).setVisibility(View.VISIBLE);
        findViewById(R.id.backSign).setVisibility(View.VISIBLE);

        findViewById(R.id.cancelEdit).setVisibility(View.INVISIBLE);
        findViewById(R.id.saveEdit).setVisibility(View.INVISIBLE);

        findViewById(R.id.EditSets).setEnabled(false);
        findViewById(R.id.EditReps).setEnabled(false);
        findViewById(R.id.HH).setEnabled(false);
        findViewById(R.id.MM).setEnabled(false);
        findViewById(R.id.SS).setEnabled(false);
    }

    public void saveEdit(View view)
    {
        ArrayList<Exercise> exercisesList = new ArrayList<>();

        Exercise exercise;
        EditText ed = findViewById(R.id.HH);
        String temp = ed.getText().toString();

        int hour, minutes, seconds, sets, reps;

        for (int i = 0; i < size; i++) {
            hour = Integer.parseInt(temp);

            ed = findViewById(R.id.MM);
            temp = ed.getText().toString();

            minutes = Integer.parseInt(temp);

            ed = findViewById(R.id.SS);
            temp = ed.getText().toString();

            seconds = Integer.parseInt(temp);

            ed = findViewById(R.id.EditSets);
            temp = ed.getText().toString();

            sets = Integer.parseInt(temp);

            ed = findViewById(R.id.EditReps);
            temp = ed.getText().toString();

            reps = Integer.parseInt(temp);

            if (i == 0) {
                if (exercises.get(i).name.equals("Run"))
                    exercise = new Exercise("Run", 't', hour, minutes, seconds);
                else
                    exercise = new Exercise(exercises.get(i).name, 's', sets, reps);
            }
            else
            {
                if (exercises.get(i).name.equals("Run"))
                    exercise = new Exercise(exercises.get(i).name, 't', exercises.get(i).hours, exercises.get(i).minutes, exercises.get(i).seconds);
                else
                    exercise = new Exercise(exercises.get(i).name, 's', exercises.get(i).sets, exercises.get(i).reps);
            }

            exercisesList.add(exercise);
        }
        Workout workout = new Workout(workoutName, year, month, day, exercisesList);

        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir, 0);
        appFile.replaceWorkout(workout, index);

        Intent intent = new Intent(this, WorkoutPage.class);
        intent.putExtra("INDEX", index);
        startActivity(intent);
    }
}
