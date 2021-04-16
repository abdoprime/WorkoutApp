package com.example.workoutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
//        String time = ex.hours +"h " + ex.minutes + "m " + ex.seconds + "s";
        System.out.println("THE EXERCISE IS: "+ ex.sets);
        holder.name.setText(ex.name);
//        holder.sets.setText("Sets: " + ex.sets);
//        holder.reps.setText("Reps: " + ex.reps);

        holder.editSets.setText(String.valueOf(ex.sets));
        holder.editReps.setText(String.valueOf(ex.reps));

        if(ex.type == 't')
        {
            holder.sets.setVisibility(View.INVISIBLE);
            holder.reps.setVisibility(View.INVISIBLE);
            holder.editSets.setVisibility(View.INVISIBLE);
            holder.editReps.setVisibility(View.INVISIBLE);

            holder.time.setVisibility(View.VISIBLE);
            holder.hh.setVisibility(View.VISIBLE);
            holder.mm.setVisibility(View.VISIBLE);
            holder.ss.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.time.setVisibility(View.INVISIBLE);
            holder.hh.setVisibility(View.INVISIBLE);
            holder.mm.setVisibility(View.INVISIBLE);
            holder.ss.setVisibility(View.INVISIBLE);

            holder.sets.setVisibility(View.VISIBLE);
            holder.reps.setVisibility(View.VISIBLE);
            holder.editSets.setVisibility(View.VISIBLE);
            holder.editReps.setVisibility(View.VISIBLE);
        }
        holder.hh.setText(String.format("%d", ex.hours));
        holder.mm.setText(String.format("%d", ex.minutes));
        holder.ss.setText(String.format("%d", ex.seconds));

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
        TextView name, sets, reps, time;
        EditText hh, mm, ss, editSets, editReps;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exName);
            sets = itemView.findViewById(R.id.exSets);
            reps = itemView.findViewById(R.id.exReps);
            editSets = itemView.findViewById(R.id.EditSets);
            editReps = itemView.findViewById(R.id.EditReps);

            time = itemView.findViewById(R.id.exTime);
            hh = itemView.findViewById(R.id.HH);
            mm = itemView.findViewById(R.id.MM);
            ss = itemView.findViewById(R.id.SS);

            image = itemView.findViewById(R.id.exImg);

            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();

            });
        }
    }
}
