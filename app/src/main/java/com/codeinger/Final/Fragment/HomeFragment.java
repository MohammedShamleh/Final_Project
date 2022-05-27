package com.codeinger.Final.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codeinger.Final.R;
import com.codeinger.Final.car.Car;
import com.codeinger.Final.car.CarAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<Car> cars = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("cars");
        Task<DataSnapshot> task = ref.get();
        RecyclerView rv = view.findViewById(R.id.rv);

        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "dtat sss ", Toast.LENGTH_SHORT).show();

                    Iterable<DataSnapshot> data = task.getResult().getChildren();
                    for (DataSnapshot snap : data) {
                        Car s = snap.getValue(Car.class);
                        cars.add(s);

                    }
                    CarAdapter adapter = new CarAdapter(cars);
                    rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    rv.setLayoutManager(layoutManager);

                }
            }
        });

        return view;
    }

//    public static void data(ArrayList<Car> data) {
//
//        data.add(new Car(R.drawable.car_1, "Honda", "Manual"));
//        data.add(new Car(R.drawable.car_1, "Honda", "Manual"));
//        data.add(new Car(R.drawable.car_1, "Honda", "Manual"));
//        data.add(new Car(R.drawable.car_1, "Honda", "Manual"));
//        data.add(new Car(R.drawable.car_1, "Honda", "Manual"));
//        data.add(new Car(R.drawable.car_1, "Honda", "Manual"));
//        data.add(new Car(R.drawable.car_1, "Honda", "Manual"));
//
//    }


}