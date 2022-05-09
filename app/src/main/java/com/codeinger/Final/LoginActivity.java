package com.codeinger.Final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sp ;
    private FirebaseAuth firebaseAuth;
    TextInputLayout user_password,Email;
    ImageView imge;
    TextView logo;
    Button Sign_Up,Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        firebaseAuth = FirebaseAuth.getInstance();
        initViews();
        sp = getSharedPreferences("Mysp", Context.MODE_PRIVATE);
        Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Go to SignUpActivity with animation */

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View, String>(imge, "imageLogo");
                pairs[1] = new Pair<View, String>(logo, "textLogo");
                pairs[2] = new Pair<View, String>(Sign_Up, "sinup");
                pairs[3] = new Pair<View, String>(Login, "sginin");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, options.toBundle());


            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getEditText().getText().toString();
                String password = user_password.getEditText().getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Username cannot be empty");
                }else if(TextUtils.isEmpty(password)){
                    user_password.setError("Password cannot be empty");
                }else{
                    firebaseAuth.signInWithEmailAndPassword(email,password )
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putString("name", email);
                                        editor.putString("pass", password);
                                        editor.commit();
                                        Intent intent = new Intent(LoginActivity.this, mainboard.class);
                                        Pair[] pairs = new Pair[0];
                                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                                        startActivity(intent, options.toBundle());
                                        finish();
                                     }else{
                                            Toast_Error("Login Field");
                                    }
                                }
                            });


                }


            }
        });


    }
    public void initViews(){
        //  Hooks to all xml elements activity_sign_up.xml

        Email = findViewById(R.id.Email);

        user_password = findViewById(R.id.password);

        imge = findViewById(R.id.imageLogo);

        logo = findViewById(R.id.textLogo);

        Sign_Up = findViewById(R.id.Sign_Up);

        Login = findViewById(R.id.Login);
    }
    void Toast_True(String message) {
        Toast toast = new Toast(LoginActivity.this);

        View view = LayoutInflater.from(LoginActivity.this)
                .inflate(R.layout.toast_layout, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();
    } void Toast_Error(String message) {
        Toast toast = new Toast(LoginActivity.this);

        View view = LayoutInflater.from(LoginActivity.this)
                .inflate(R.layout.field, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();
    }
}