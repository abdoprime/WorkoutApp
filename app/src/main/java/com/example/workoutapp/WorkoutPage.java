package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkoutPage extends AppCompatActivity {
    int index;
    TextView backgroundText,msgText;
    Button yesBtn, noBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir, 0);
        super.onCreate(savedInstanceState);
        index = getIntent().getIntExtra("INDEX", index);
        setContentView(R.layout.workout_page);
        Workout workout = appFile.returnWorkout(index);
        ArrayList<Exercise> exercises = workout.exercises;
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.exerciseView);
        ExerciseAdapter exAdapter = new ExerciseAdapter(this, exercises);
        recyclerView.setAdapter(exAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backgroundText = findViewById(R.id.backgroundText);
        msgText = findViewById(R.id.deleteMsg);
        yesBtn = findViewById(R.id.yesButton);
        noBtn = findViewById(R.id.noButton);
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
    }

    public void yesDelete(View view)
    {
        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir, 0);
        appFile.deleteWorkout(index);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startWorkout(View view) {
        System.out.println("this ran");
        Intent intent = new Intent(this, ExerciseClass.class);
        intent.putExtra("INDEX", index);
        startActivity(intent);
    }

}
