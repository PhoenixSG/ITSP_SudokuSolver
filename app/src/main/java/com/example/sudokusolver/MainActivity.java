package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        MainApplication app = (MainApplication) getApplication();
        app.ring.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApplication app = (MainApplication) getApplication();
        app.ring.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainApplication app = (MainApplication) getApplication();
        app.ring.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MainApplication application = (MainApplication) getApplication();
        application.ring.start();
        application.ring.setLooping(true);



        ImageButton resumeBtn = (ImageButton) findViewById(R.id.resumeBtn) ;
        ImageButton newgameBtn = (ImageButton) findViewById(R.id.newgameBtn) ;
        ImageButton instructionsBtn = (ImageButton) findViewById(R.id.instructionsBtn) ;
        ImageButton statisticsBtn = (ImageButton) findViewById(R.id.statisticsBtn) ;
        ImageButton settingsBtn = (ImageButton) findViewById(R.id.solveBtn) ;

        resumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        newgameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openNewgame = new Intent(getApplicationContext(),com.example.sudokusolver.S3.class) ;
                startActivity(openNewgame);
            }
        });

    }


}