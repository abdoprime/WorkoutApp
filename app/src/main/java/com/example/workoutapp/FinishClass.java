package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FinishClass extends AppCompatActivity {

    Workout workout = null;
    int hours = 0, minutes = 0, seconds = 0, workoutIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_class);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");
        String strDate = dateFormat.format(date);
        String[] array = strDate.split("-");

        int hour = getIntent().getIntExtra("HOUR", workoutIndex), min = getIntent().getIntExtra("MIN", workoutIndex),
                sec = getIntent().getIntExtra("SEC", workoutIndex);

        timeDifference(hour, Integer.parseInt(array[0]), min, Integer.parseInt(array[1]), sec, Integer.parseInt(array[2]));

        System.out.println("Old: " + hour + "/" + min + "/" + sec + "|");
        System.out.println("Old: " + Integer.parseInt(array[0]) + "/" + Integer.parseInt(array[1]) + "/" + Integer.parseInt(array[2]) + "|");
        System.out.println("Time: " + hours + "/" + minutes + "/" + seconds + "|");

        workoutIndex = getIntent().getIntExtra("WORKOUT", workoutIndex);

        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir, 0);

        workout = appFile.returnWorkout(workoutIndex);

        TextView textViewToChange = findViewById(R.id.WorkoutTitle);
        textViewToChange.setText(String.format("%s\nCompleted!", workout.name));

        textViewToChange = findViewById(R.id.FinishedTime);
        textViewToChange.setText(String.format("%02d : %02d", minutes, seconds));

        findViewById(R.id.noBtn).setOnClickListener(v -> returnHome());
        findViewById(R.id.yesBtn).setOnClickListener(v -> saveWorkoutToHistory());
    }
    public void returnHome() {
        Toast.makeText(FinishClass.this,
                "Workout not saved", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void saveWorkoutToHistory(){
        Toast.makeText(FinishClass.this,
                "Workout saved", Toast.LENGTH_SHORT).show();

        if (workout != null) {
            String filedir = getFilesDir().getPath();
            AppFileManager appFile = new AppFileManager(filedir, 0);

            History history = new History(workout.name, workout.year, workout.month, workout.day, hours, minutes, seconds, workout);
            appFile.newHistory(history);
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void timeDifference(int startHour, int endHour, int startMin, int endMin, int startSec, int endSec)
    {
        // if start second is greater
        // convert minute of stop into seconds
        // and add seconds to stop second
        if(startSec > endSec){
            --endMin;
            endSec += 60;
        }

        seconds = endSec - startSec;

        // if start minute is greater
        // convert stop hour into minutes
        // and add minutes to stop minutes
        if(startMin > endMin){
            --endHour;
            endMin += 60;
        }

        minutes = endMin - startMin;
        hours = endHour - startHour;
    }
}
