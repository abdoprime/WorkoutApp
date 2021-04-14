package com.example.workoutapp;
import java.util.ArrayList;
import java.io.*;

public class Workout implements Serializable{
    private static final long serialVersionUID = 6529685098667754690L;
    public String name;
    public int year;
    public int month;
    public int day;
    public ArrayList<Exercise> exercises;

    public Workout(String name, int year, int month, int day, ArrayList<Exercise> exercises)
    {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.exercises = exercises;
    }

}
