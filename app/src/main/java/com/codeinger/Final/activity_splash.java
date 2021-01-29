package com.codeinger.Final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeinger.Final.R;

public class activity_splash extends AppCompatActivity {

    private static int SPLASH_DISPLAY_LENGTH = 2000;
    Animation topAnim, bottomAnim;
    ImageView imge;
    TextView logo, slogan;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //  Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //  Hooks
        imge = findViewById(R.id.imge);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        imge.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
        sp = getSharedPreferences("Mysp", MODE_PRIVATE);
        if (sp.contains("name")) {
            Intent i = new Intent(activity_splash.this, mainboard.class);
            startActivity(i);
            finish();
        }else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Go to Login board activity with animation */
                    Intent intent = new Intent(activity_splash.this, LoginActivity.class);
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(imge, "imageLogo");
                    pairs[1] = new Pair<View, String>(logo, "textLogo");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity_splash.this, pairs);
                    startActivity(intent, options.toBundle());
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }
}