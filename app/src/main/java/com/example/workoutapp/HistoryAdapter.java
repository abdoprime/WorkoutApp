package com.example.workoutapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{
    ArrayList<History> histories;
    Context context;
    public HistoryAdapter(Context ct, ArrayList<History> hists)
    {
        this.histories = hists;
        this.context = ct;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        History hist = histories.get(position);
        String date = hist.year + "/" + hist.month + "/" + hist.day;
        String time = hist.hours +"h " + hist.minutes + "m " + hist.seconds + "s";
        holder.name.setText(hist.name);
        holder.date.setText(date);
        holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, date,time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.histName);
            date = itemView.findViewById(R.id.histDate);
            time = itemView.findViewById(R.id.histTime);

            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();

            });
        }
    }
}
