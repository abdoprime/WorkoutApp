package com.example.workoutapp;
import java.util.ArrayList;

public class Workout {
    String name;
    int year;
    int month;
    int day;
    ArrayList<Exercise> exercises;

    public Workout(String name, int year, int month, int day, ArrayList<Exercise> exercises)
    {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.exercises = exercises;
    }

}
