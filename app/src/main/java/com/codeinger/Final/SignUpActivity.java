package com.codeinger.Final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    //  Variables
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtnGo, regBtnLogin;
    ImageView logoImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = firebaseFirestore.getInstance();
          // Buttn Already Have an account? LogIn
        regBtnLogin.setOnClickListener(view -> {
           onBackPressed();
        });
        regBtnGo.setOnClickListener(view -> {
          String Name =regName.getEditText().getText().toString();
          String Username =regUsername.getEditText().getText().toString();
          String Email =regEmail.getEditText().getText().toString();
          String PhoneNo =regPhoneNo.getEditText().getText().toString();
          String Password =regPassword.getEditText().getText().toString();
            if (TextUtils.isEmpty(Name)) {
                regName.setError("Name field must not be empty");
                return;
            }else if(TextUtils.isEmpty(Username)){
                regUsername.setError("User Name field must not be empty");
                return;
            }else if(TextUtils.isEmpty(Email)){
                regEmail.setError("Email field must not be empty");
                return;
            }
            else if(TextUtils.isEmpty(PhoneNo)){
                regPhoneNo.setError("Phone Number field must not be empty");
                return;
            } else if(TextUtils.isEmpty(Password)){
                regPassword.setError("Password field must not be empty");
                return;
            }else{
                firebaseAuth.createUserWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             HashMap<String,String> data = new HashMap<>();
                             data.put("FullName",Name);
                             data.put("Username",Username);
                             data.put("Email",Email);
                             data.put("PhoneNo",PhoneNo);

                             firebaseFirestore.collection("user").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                 @Override
                                 public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful()){
                                        Toast_True("Welcome"+Name);
                                         startActivity(new Intent(SignUpActivity.this,mainboard.class));
                                        finish();
                                    }
                                 }
                             });
                            

                         }else{
                             Toast_Error("Registration failed, please make sure the input is correct");
                       //      Toast.makeText(SignUpActivity.this, "No", Toast.LENGTH_SHORT).show();

                         }
                    }
                });
            }


        });


    }
    public void initViews(){
        //  Hooks to all xml elements activity_sign_up.xml
        regName = findViewById(R.id.regName);
        regUsername = findViewById(R.id.regUsername);
        regEmail = findViewById(R.id.regEmail);
        regPhoneNo = findViewById(R.id.regPhoneNo);
        regPassword = findViewById(R.id.regPassword);
        regBtnGo = findViewById(R.id.regBtnGo);
        regBtnLogin = findViewById(R.id.regBtnLogin);
        logoImage = findViewById(R.id.logoImage);
    }
    void Toast_True(String message) {
        Toast toast = new Toast(SignUpActivity.this);

        View view = LayoutInflater.from(SignUpActivity.this)
                .inflate(R.layout.toast_layout, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();
    } void Toast_Error(String message) {
        Toast toast = new Toast(SignUpActivity.this);

        View view = LayoutInflater.from(SignUpActivity.this)
                .inflate(R.layout.field, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();
    }

}