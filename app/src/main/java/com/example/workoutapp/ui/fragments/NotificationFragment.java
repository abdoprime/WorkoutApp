package com.example.workoutapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutapp.AppFileManager;
import com.example.workoutapp.History;
import com.example.workoutapp.HistoryAdapter;
import com.example.workoutapp.R;
import com.example.workoutapp.Workout;
import com.example.workoutapp.WorkoutAdapter;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);
        String filedir = requireActivity().getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir,0);
        RecyclerView recyclerView;
        recyclerView = root.findViewById(R.id.historyView);
        int i = 0;
        ArrayList<History> histories = appFile.returnHistoryList();
        HistoryAdapter histAdapter = new HistoryAdapter(requireContext(), histories);
        recyclerView.setAdapter(histAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return root;
    }
}