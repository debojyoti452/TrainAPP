package dev452.app.trainapp.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



import dev452.app.trainapp.R;
import dev452.app.trainapp.libs.LineTextView;

public class SplashScreen extends Activity {
    private static final long SPLASH_TIME_OUT = 2000;
    private LineTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.textview);
//        final ImageView imageView = findViewById(R.id.imageView);
//        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.anim_down);
//        imageView.setAnimation(anim1);
//
//        ProgressBar mprogressBar =  findViewById(R.id.progressBar);
//        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
//        anim.setDuration(5000);
//        anim.setInterpolator(new DecelerateInterpolator());
//        anim.start();
       textView.animateText("Hello World");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }
        },SPLASH_TIME_OUT);
    }
}
