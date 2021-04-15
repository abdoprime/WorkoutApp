package com.example.workoutapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    ArrayList<Workout> workouts;
    Context context;
    public MyAdapter(Context ct, ArrayList<Workout> wrks)
    {
        this.workouts = wrks;
        this.context = ct;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workout_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Workout wrk = workouts.get(position);
        String date = wrk.year + "/" + wrk.month + "/" + wrk.day;
        holder.name.setText(wrk.name);
        holder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.wrkName);
            date = itemView.findViewById(R.id.wrkDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();

                }
            });

        }
    }
}
