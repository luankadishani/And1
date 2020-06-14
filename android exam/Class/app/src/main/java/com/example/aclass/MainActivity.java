package com.example.aclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    TextView tv;
    int SPLASH_TIME = 3000; //it shows the picture for 3 sec before entering the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tvw);
        iv=(ImageView)findViewById(R.id.img);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.lefttoright);

        Animation animation1= AnimationUtils.loadAnimation(this,R.anim.lefttoright);


        iv.startAnimation(animation);
        tv.startAnimation(animation1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(MainActivity.this, Home.class);
                startActivity(mySuperIntent);

                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();

            }
        }, SPLASH_TIME);

    }
}