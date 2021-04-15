package com.example.workoutapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutapp.AppFileManager;
import com.example.workoutapp.History;
import com.example.workoutapp.WorkoutAdapter;
import com.example.workoutapp.HistoryAdapter;
import com.example.workoutapp.R;
import com.example.workoutapp.Workout;

import java.util.ArrayList;

public class HomeFragment  extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);String filedir = requireActivity().getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir,0);
        RecyclerView recyclerView;
        recyclerView = root.findViewById(R.id.recyclerView);
        int i = 0;
        ArrayList<Workout> workouts = appFile.returnWorkoutList();
        WorkoutAdapter myAdapter = new WorkoutAdapter(requireContext(), workouts);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return root;
    }
}