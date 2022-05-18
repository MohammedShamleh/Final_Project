package com.codeinger.Final.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codeinger.Final.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class UpdateFragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextInputLayout updName,updusername,updEmail,updPhoneNo;
    Button updBtn;
    public UpdateFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view =  inflater.inflate(R.layout.fragment_update, container, false);
        initViews(view);
        firebaseAuth = FirebaseAuth.getInstance();
        updBtn = view.findViewById(R.id.updBtn);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("user")
                .whereEqualTo("uid", firebaseAuth.getCurrentUser().getUid())
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot q : task.getResult()) {
                    updName.setHint(q.getData().get("FullName").toString());
                    updusername.setHint(q.getData().get("Email").toString());
                    updEmail.setHint(q.getData().get("PhoneNo").toString());
                    updPhoneNo.setHint(q.getData().get("Username").toString());
                }
            }
        });

        updBtn.setOnClickListener(v -> {

            getActivity().onBackPressed();
        });
        return view;
    }
    public void initViews(View view){
        updBtn = view.findViewById(R.id.updBtn);
        updName = view.findViewById(R.id.updName);
        updusername = view.findViewById(R.id.updusername);
        updEmail = view.findViewById(R.id.updEmail);
        updPhoneNo = view.findViewById(R.id.updPhoneNo);
    }
}