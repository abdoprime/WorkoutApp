package com.example.workoutapp;
import java.io.*;

public class History implements Serializable{
    private static final long serialVersionUID = 6529685098267703690L;
    public String name;
    public int year;
    public int month;
    public int day;

    public int hours;
    public int minutes;
    public int seconds;

    public Workout performed; //performed workout

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
