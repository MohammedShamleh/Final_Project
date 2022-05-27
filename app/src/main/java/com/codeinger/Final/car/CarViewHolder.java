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
    TextView Transmission;
    ImageView image;


    public CarViewHolder(@NonNull View itemView) {

        super(itemView);
        view = itemView;
        namecar = itemView.findViewById(R.id.carname);
        image = itemView.findViewById(R.id.imageView);
        Transmission = itemView.findViewById(R.id.Transmission);


    }
}
