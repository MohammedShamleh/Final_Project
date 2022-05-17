package com.codeinger.Final.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codeinger.Final.LoginActivity;
import com.codeinger.Final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AccountFragment extends Fragment {
    SharedPreferences sp;
    Button btnlogout , btnUpdate;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView tvFullName,tvEmail,tvUserName,tvPhpne;

    public AccountFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initViews(view);


       firebaseAuth = FirebaseAuth.getInstance();
       firebaseFirestore = FirebaseFirestore.getInstance();
       firebaseFirestore.collection("user")
               .whereEqualTo("uid",firebaseAuth.getCurrentUser().getUid())
               .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
             if(task.isSuccessful()){
                 for (QueryDocumentSnapshot q: task.getResult()) {
                     tvFullName.setText(q.getData().get("FullName").toString());
                     tvEmail.setText(q.getData().get("Email").toString());
                     tvPhpne.setText(q.getData().get("PhoneNo").toString());
                     tvUserName.setText(q.getData().get("Username").toString());
                  }
             }
           }
       });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit  = sp.edit();
                edit.remove("name");
                edit.clear();
                edit.apply();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        return view;
    }
    public void initViews(View view){

        sp = this.getActivity().getSharedPreferences("Mysp", 0);

        btnlogout = view.findViewById(R.id.btnlogout);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        tvFullName = view.findViewById(R.id.tvFullName);
        tvEmail = view.findViewById(R.id.tvemail);
        tvPhpne = view.findViewById(R.id.tvPhpne);
        tvUserName = view.findViewById(R.id.tvusername);
    }

}