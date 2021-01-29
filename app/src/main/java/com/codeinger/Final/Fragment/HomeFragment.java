package com.codeinger.Final.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeinger.Final.R;
import com.codeinger.Final.car.Car;
import com.codeinger.Final.car.CarAdapter;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<Car> data = new ArrayList<>();
        data.add(new Car(R.drawable.car_1, "Honda", "2017", 3456.0));
        data.add(new Car(R.drawable.car_2, "Romeo", "2012", 3566.0));
        data.add(new Car(R.drawable.car_3, "BMW", "2016", 8456.0));
        data.add(new Car(R.drawable.car_4, "Bentley", "2019", 7456.0));
        data.add(new Car(R.drawable.car_5, "Ford", "1999", 3656.0));
        data.add(new Car(R.drawable.car_6, "Kia", "2021", 5676.0));
        data.add(new Car(R.drawable.car_7, "Cadillac", "2015", 9856.0));
        data.add(new Car(R.drawable.car_8, "Datsun ", "2014", 57456.0));
        data.add(new Car(R.drawable.car_9, "Lexus", "2011", 3667.0));
        data.add(new Car(R.drawable.car_10, "Nissan", "2020", 7756.0));
        RecyclerView rv = view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        CarAdapter carAdapter = new CarAdapter(data);
        rv.setAdapter(carAdapter);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        return view;
    }
}