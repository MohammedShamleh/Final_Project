package com.codeinger.Final;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private FirebaseAuth firebaseAuth;
    TextInputLayout user_password, Email;
    ImageView imge;
    TextView logo;
    Button Sign_Up, Login;
    WifiReceiver myReceiver = new WifiReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("Mysp", Context.MODE_PRIVATE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        firebaseAuth = FirebaseAuth.getInstance();
        initViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(myReceiver, filter);
        }


    }

    public void initViews() {
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
    }

    void Toast_Error(String message) {
        Toast toast = new Toast(LoginActivity.this);

        View view = LayoutInflater.from(LoginActivity.this)
                .inflate(R.layout.field, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    public class WifiReceiver extends BroadcastReceiver {

        private Context mContext;

        @Override
        public void onReceive(Context context, Intent intent) {
            mContext = context;


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
                    if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.isConnected()) {
                            String email = Email.getEditText().getText().toString();
                            String password = user_password.getEditText().getText().toString();
                            if (TextUtils.isEmpty(email)) {
                                if (Locale.getDefault().getLanguage().equals("en")) {
                                    Email.setError("Username cannot be empty");

                                } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                    Email.setError("اسم المستخدم لا يمكن أن يكون فارغا");
                                }

                            } else if (TextUtils.isEmpty(password)) {
                                if (Locale.getDefault().getLanguage().equals("en")) {
                                    user_password.setError("Password cannot be empty");

                                } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                    user_password.setError("كلمة المرور لا يمكن أن تكون فارغة");

                                }

                            } else {
                                firebaseAuth.signInWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    SharedPreferences.Editor editor = sp.edit();
                                                    editor.putString("name", email);
                                                    editor.putString("pass", password);
                                                    editor.commit();
                                                    Intent intent = new Intent(LoginActivity.this, mainboard.class);
                                                    Pair[] pairs = new Pair[0];
                                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                                                    startActivity(intent, options.toBundle());
                                                    finish();
                                                } else {
                                                    if (Locale.getDefault().getLanguage().equals("en")) {
                                                        Toast_Error("Login Field");

                                                    } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                                        Toast_Error("فشل تسجيل الدخول ، تأكد من الإيميل أو كلمة المرور");
                                                    }

                                                }
                                            }
                                        });
                            }
                        } else {

                            if (Locale.getDefault().getLanguage().equals("en")) {
                                Toast_Error("Check your internet connection");
                            }
                            else if (Locale.getDefault().getLanguage().equals("ar")) {
                                Toast_Error("تحقق من اتصالك بالإنترنت");

                            }
                        }

                    }


                }
            });


        }
    }
}