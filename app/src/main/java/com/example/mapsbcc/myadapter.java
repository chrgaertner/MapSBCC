package com.example.mapsbcc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {


    location data[];

    // Create a constructor and assign the parameter to the local variable location data[]
    public myadapter(location[] data) {
        this.data = data;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    @Override
    // Sets text in our textview to Address, Latitude, Longitude through the functionality we have in our location class
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.t1.setText(data[position].getAddress());
        holder.t2.setText(data[position].getLat());
        holder.t3.setText(data[position].getLon());
    }

    @Override
    // Store the length of our data variable
    public int getItemCount() {
        return data.length;
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
        }
    }
}
