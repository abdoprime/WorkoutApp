package com.example.workoutapp;
import java.io.*;

public class Exercise implements Serializable{
    private static final long serialVersionUID = 6529683098267753690L;
    public String name;  //contains name of exercise

    public char type;  //contains type of exercise, 's' for sets/reps, 't' for time

    public int hours = 0;
    public int minutes = 0;
    public int seconds = 0;

    public int sets = 0;
    public int reps = 0;

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
