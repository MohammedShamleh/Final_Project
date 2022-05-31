package com.codeinger.Final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.codeinger.Final.Fragment.AccountFragment;
import com.codeinger.Final.Fragment.FavoriteFragment;
import com.codeinger.Final.Fragment.HomeFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class mainboard extends AppCompatActivity {

    private MeowBottomNavigation bnv_Main;
    TextView name ;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_main);
        name = findViewById(R.id.name);
        bnv_Main = findViewById(R.id.bnv_Main);
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.ic_favorite));
        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.person));
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("user")
                .whereEqualTo("uid", firebaseAuth.getCurrentUser().getUid())
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot q : task.getResult()) {
                    name.setText(q.getData().get("FullName").toString());

                }
            }
        });
        bnv_Main.show(1,false);
        replace(new HomeFragment());
        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new HomeFragment());
                        break;

                    case 2:
                        replace(new FavoriteFragment());
                        break;

                    case 3:
                        replace(new AccountFragment());
                        break;

                }
                return null;
            }
        });




    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}