package com.example.workoutapp;
import java.util.ArrayList;
import java.io.*;

public class AppFile implements Serializable{
    public ArrayList<Workout> workouts;
    public ArrayList<History> histories;
    private static final long serialVersionUID = 6529685098267753690L;

    public AppFile(ArrayList<Workout> workouts, ArrayList<History> histories)
    {
        this.workouts = workouts;
        this.histories = histories;
    }

    public void newWorkout(Workout workout)
    {
        this.workouts.add(workout);
    }

    public void deleteWorkout(int index)
    {
        this.workouts.remove(index);
    }

    public void replaceWorkout(Workout workout, int index)
    {
        this.workouts.set(index, workout);
    }

    public Workout returnWorkout(int index)
    {
        Workout wrk = workouts.get(index);
        return wrk;
    }

    public void newHistory(History history)
    {
        this.histories.add(history);
    }

    public History returnHistory(int index)
    {
        History hist = histories.get(index);
        return hist;
    }

    public void deleteHistory(int index)
    {
        this.histories.remove(index);
    }



}
