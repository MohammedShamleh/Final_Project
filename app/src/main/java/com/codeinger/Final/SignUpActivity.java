package com.codeinger.Final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    //  Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtnGo, regBtnLogin;
    ImageView logoImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
          // Buttn Already Have an account? LogIn
        regBtnLogin.setOnClickListener(view -> {
            /* Go to LoginActivity with animation */
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View, String>(logoImage, "imageLogo");
            pairs[1] = new Pair<View, String>(regBtnGo, "sginin");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
            startActivity(intent,options.toBundle());
            finish();
        });
        regBtnGo.setOnClickListener(view -> {
          String Name =regName.getEditText().getText().toString();
          String Username =regUsername.getEditText().getText().toString();
          String Email =regEmail.getEditText().getText().toString();
          String PhoneNo =regPhoneNo.getEditText().getText().toString();
            if (TextUtils.isEmpty(Name)) {
                regName.setError("Name field must not be empty");
                return;
            }else if(TextUtils.isEmpty(Username)){
                regUsername.setError("Name field must not be empty");
                return;
            }else if(TextUtils.isEmpty(Email)){
                regEmail.setError("Name field must not be empty");
                return;
            }
            else if(TextUtils.isEmpty(PhoneNo)){
                regPhoneNo.setError("Name field must not be empty");
                return;
            }else{
                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
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
}