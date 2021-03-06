package com.example.workoutapp;
import java.io.*;
import java.util.ArrayList;

public class AppFileManager {

    public AppFile appFile;
    String filepath = "app.saved";
    public AppFileManager(String fileDir, int toDelete){
        filepath = fileDir + filepath;
        File file = new File(filepath);
        if(toDelete == 1)
        {
            file.delete();
        }
        boolean exists = file.exists();
        if(!exists)
        {
            try
            {
                System.out.println("Went into Try");
                FileOutputStream fileOut = new FileOutputStream(filepath);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

                //public Exercise(String name, char type, int sets, int reps)
                Exercise ex1 = new Exercise("Run",'t',0,15,0);
                Exercise ex2 = new Exercise("Pushups",'s',3, 10);
                Exercise ex3 = new Exercise("Squats",'s',3, 10);
                ArrayList<Exercise> exList = new ArrayList<>();
                exList.add(ex1);
                exList.add(ex2);
                exList.add(ex3);

                //Workout(String name, int year, int month, int day, ArrayList<Exercise> exercises)
                Workout exWorkout = new Workout("Example Workout", 2021,4,12,exList);
                ArrayList<Workout> workoutList = new ArrayList<>();
                workoutList.add(exWorkout);

                //public History(String name, int year, int month, int day, int hours, int minutes, int seconds, Workout workout)
                History exHist = new History("Example History", 2021, 4, 12, 0, 30, 0, exWorkout);
                ArrayList<History> histList = new ArrayList<>();
                histList.add(exHist);

                appFile = new AppFile(workoutList,histList);
                objectOut.writeObject(appFile);
                objectOut.close();
            }
            catch (Exception ex) {
                System.out.println("Catch 1");
                ex.printStackTrace();
                System.out.println(ex);
            }
        }
        else
        {
            try {
                System.out.println("Went into Try 2");
                FileInputStream fileIn = new FileInputStream(filepath);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                System.out.println("The Object has been read from the file");
                objectIn.close();
                appFile = (AppFile) obj;

            } catch (Exception ex) {
                System.out.println("Catch 2");
                ex.printStackTrace();
            }
        }
    }

    private void saveFile()
    {
        File file = new File(filepath);
        try{
            file.delete();
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(appFile);
            objectOut.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public void newWorkout(Workout workout)
    {
        System.out.println("Manager: " + workout.name + " " + workout.year + " " + workout.month + " " + workout.day + " " + " end");
        appFile.newWorkout(workout);
        this.saveFile();
    }

    public ArrayList<Workout> returnWorkoutList()
    {
        return appFile.workouts;
    }

    public ArrayList<History> returnHistoryList()
    {
        return appFile.histories;
    }

    public void deleteWorkout(int index)
    {
        appFile.deleteWorkout(index);
        this.saveFile();
    }

    public void replaceWorkout(Workout workout, int index)
    {
        appFile.replaceWorkout(workout,index);
        this.saveFile();
    }

    public Workout returnWorkout(int index)
    {
        return appFile.returnWorkout(index);
    }

    public void newHistory(History history)
    {
        appFile.newHistory(history);
        this.saveFile();
    }

    public History returnHistory(int index)
    {
        return appFile.returnHistory(index);
    }

    public void deleteHistory(int index)
    {
        appFile.deleteHistory(index);
        this.saveFile();
    }
}
