package com.example.workoutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder>{
    ArrayList<Exercise> exercises;
    Context context;
    public ExerciseAdapter(Context ct, ArrayList<Exercise> hists)
    {
        this.exercises = hists;
        this.context = ct;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Exercise ex = exercises.get(position);
        String time = ex.hours +"h " + ex.minutes + "m " + ex.seconds + "s";
        System.out.println("THE EXERCISE IS: "+ ex.sets);
        holder.name.setText(ex.name);
        holder.sets.setText("Sets: " + ex.sets);
        holder.reps.setText("Reps: " + ex.reps);
        if(ex.type == 't')
        {
            holder.sets.setVisibility(View.INVISIBLE);
            holder.reps.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.time.setVisibility(View.INVISIBLE);
        }
        holder.time.setText(time);
        switch(ex.name)
        {
            case "Run":
                holder.image.setImageResource(R.drawable.running);
                break;
            case "Squats":
                holder.image.setImageResource(R.drawable.squat);
                break;
            case "Push ups":
                holder.image.setImageResource(R.drawable.pushup);
                break;
            default:
                holder.image.setImageResource(R.drawable.pushup);
        }

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, sets, reps,time;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exName);
            sets = itemView.findViewById(R.id.exSets);
            reps = itemView.findViewById(R.id.exReps);
            time = itemView.findViewById(R.id.exTime);
            image = itemView.findViewById(R.id.exImg);

            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();

            });
        }
    }
}
