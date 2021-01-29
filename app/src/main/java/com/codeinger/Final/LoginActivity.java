package com.codeinger.Final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextInputLayout user_name = findViewById(R.id.username);
        TextInputLayout user_password = findViewById(R.id.password);
        ImageView imge = findViewById(R.id.imageLogo);
        TextView logo = findViewById(R.id.textLogo);
        Button Sign_Up = findViewById(R.id.Sign_Up);
        Button Login = findViewById(R.id.Login);
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
                finish();

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user_name.getEditText().getText().toString();
                String password = user_password.getEditText().getText().toString();
                if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123")) {

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", username);
                    editor.putString("pass", password);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, mainboard.class);
                    Pair[] pairs = new Pair[4];
                    pairs[0] = new Pair<View, String>(imge, "imageLogo");
                    pairs[1] = new Pair<View, String>(logo, "textLogo");
                    pairs[2] = new Pair<View, String>(Sign_Up, "sinup");
                    pairs[3] = new Pair<View, String>(Login, "sginin");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Login failed", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}