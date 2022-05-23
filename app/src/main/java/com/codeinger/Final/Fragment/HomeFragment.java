package com.codeinger.Final.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codeinger.Final.R;
import com.codeinger.Final.car.Car;
import com.codeinger.Final.car.CarAdapter;
import com.codeinger.Final.mainboard;

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
        data(data);

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

    public static void data(ArrayList<Car> data) {
        data.add(new Car(R.drawable.car_1, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_2, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_3, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_4, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_5, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_6, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_7, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_8, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_9, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_1, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_10, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_11, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_3, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_6, "Honda", "Manual", "Subject to availability", "21"));
        data.add(new Car(R.drawable.car_9, "Honda", "Manual", "Subject to availability", "21"));
    }
}