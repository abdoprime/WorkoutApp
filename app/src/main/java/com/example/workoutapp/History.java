package com.example.workoutapp;

import java.util.ArrayList;

public class History {
    String name;
    int year;
    int month;
    int day;

    int hours;
    int minutes;
    int seconds;

    Workout performed; //performed workout

    public History(String name, int year, int month, int day, int hours, int minutes, int seconds, Workout workout)
    {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        this.performed = workout;
    }
}
