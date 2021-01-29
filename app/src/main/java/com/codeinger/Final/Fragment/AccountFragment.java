package com.codeinger.Final.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codeinger.Final.LoginActivity;
import com.codeinger.Final.R;

public class AccountFragment extends Fragment {
    SharedPreferences sp;
    public AccountFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        sp = this.getActivity().getSharedPreferences("Mysp", 0);
        Button btnlogout = view.findViewById(R.id.button);
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
}