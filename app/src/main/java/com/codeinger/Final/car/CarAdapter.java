package com.codeinger.Final.car;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codeinger.Final.R;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    ArrayList<Car> cars;
    Context context;

    public CarAdapter(ArrayList<Car> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.mylist, parent, false);
        return new CarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.namecar.setText(car.getName());

        holder.Transmission.setText(car.getTransmission());
        if (!car.getImg().isEmpty())
            Glide.with(holder.itemView).load(car.getImg()).into(holder.image);    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
