package com.example.workoutapp;

public class Exercise {
    String name;  //contains name of exercise

    char type;  //contains type of exercise, 's' for sets/reps, 't' for time

    int hours = 0;
    int minutes = 0;
    int seconds = 0;

    int sets = 0;
    int reps = 0;

    public Exercise(String name, char type, int sets, int reps)
    {
        this.name = name;
        this.type = type;
        this.sets = sets;
        this.reps = reps;
    }

    public Exercise(String name, char type, int hours, int minutes, int seconds)
    {
        this.name = name;
        this.type = type;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }



}
