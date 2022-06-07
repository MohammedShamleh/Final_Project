package com.codeinger.Final;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {
    //  Variables
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtnGo, regBtnLogin;
    ImageView logoImage;
    WifiReceiver myReceiver = new WifiReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = firebaseFirestore.getInstance();
        // Buttn Already Have an account? LogIn

        regBtnLogin.setOnClickListener(view -> {
            onBackPressed();
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(myReceiver, filter);
        }


    }

    public void initViews() {
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
    }

    void Toast_Error(String message) {
        Toast toast = new Toast(SignUpActivity.this);

        View view = LayoutInflater.from(SignUpActivity.this)
                .inflate(R.layout.field, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();
    }

    public class WifiReceiver extends BroadcastReceiver {
        private Context mContext;

        @Override
        public void onReceive(Context context, Intent intent) {
            mContext = context;
            regBtnGo.setOnClickListener(view -> {

                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        String Name = regName.getEditText().getText().toString();
                        String Username = regUsername.getEditText().getText().toString();
                        String Email = regEmail.getEditText().getText().toString();
                        String PhoneNo = regPhoneNo.getEditText().getText().toString();
                        String Password = regPassword.getEditText().getText().toString();
                        if (TextUtils.isEmpty(Name)) {
                            if (Locale.getDefault().getLanguage().equals("en")) {
                                regName.setError("Name field must not be empty");
                            } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                regName.setError("يجب ألا يكون حقل الاسم فارغًا");
                            }
                            return;
                        } else if (TextUtils.isEmpty(Username)) {
                            if (Locale.getDefault().getLanguage().equals("en")) {
                                regUsername.setError("User Name field must not be empty");
                            } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                regUsername.setError("يجب ألا يكون حقل اسم المستخدم فارغًا");
                            }
                            return;


                        } else if (TextUtils.isEmpty(Email)) {

                            if (Locale.getDefault().getLanguage().equals("en")) {
                                regEmail.setError("Email field must not be empty");
                            } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                regEmail.setError("يجب ألا يكون حقل البريد الإلكتروني فارغًا");
                            }
                            return;
                        } else if (TextUtils.isEmpty(PhoneNo)) {

                            if (Locale.getDefault().getLanguage().equals("en")) {
                                regPhoneNo.setError("Phone Number field must not be empty");
                            } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                regPhoneNo.setError("يجب ألا يكون حقل رقم الهاتف فارغًا");
                            }
                            return;
                        } else if (TextUtils.isEmpty(Password)) {
                            if (Locale.getDefault().getLanguage().equals("en")) {
                                regPassword.setError("Password field must not be empty");
                            } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                regPassword.setError("يجب ألا يكون حقل كلمة المرور فارغًا");
                            }
                            return;

                        } else {
                            firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                                if (user != null) {
                                                    HashMap<String, String> data = new HashMap<>();
                                                    data.put("FullName", Name);
                                                    data.put("uid", user.getUid());
                                                    data.put("Username", Username);
                                                    data.put("Email", Email);
                                                    data.put("PhoneNo", PhoneNo);
                                           firebaseFirestore.collection("user")
                                                   .add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                                            if (task.isSuccessful()) {
                                                                startActivity(new Intent(SignUpActivity.this, mainboard.class));
                                                                finish();
                                                            }
                                                        }
                                                    });
                                                }
                                            } else {
                                                if (Locale.getDefault().getLanguage().equals("en")) {
                                                    Toast_Error("Registration failed, please make sure the input is correct");
                                                } else if (Locale.getDefault().getLanguage().equals("ar")) {
                                                    Toast_Error("فشل التسجيل ، يرجى التأكد من صحة الإدخال");
                                                }

                                            }
                                        }
                                    });
                        }
                    } else {
                        if (Locale.getDefault().getLanguage().equals("en")) {
                            Toast_Error("Check your internet connection");
                        } else if (Locale.getDefault().getLanguage().equals("ar")) {
                            Toast_Error("تحقق من اتصالك بالإنترنت");
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

}