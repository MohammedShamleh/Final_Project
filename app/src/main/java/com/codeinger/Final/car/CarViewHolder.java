package com.codeinger.Final.car;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeinger.Final.R;


public class CarViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView namecar;
    ImageView image;
    TextView model;
    TextView price;

    public CarViewHolder(@NonNull View itemView) {

        super(itemView);
        view = itemView;
        namecar = itemView.findViewById(R.id.name);
        image = itemView.findViewById(R.id.imageView);
        model = itemView.findViewById(R.id.model);
        price = itemView.findViewById(R.id.price);
    }
}
