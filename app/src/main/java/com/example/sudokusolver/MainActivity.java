package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.example.sudokusolver.S5.SHARED_PREFS;
import static com.example.sudokusolver.S5.RESU;

public class MainActivity extends AppCompatActivity {

    static boolean resume_button;



    @Override
    protected void onStart() {
        super.onStart();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        resume_button = sharedPreferences.getBoolean(RESU,false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainApplication app = (MainApplication) getApplication();
        app.ring.pause();
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        resume_button = sharedPreferences.getBoolean(RESU,false);





        ImageButton resumeBtn = (ImageButton) findViewById(R.id.resumeBtn) ;
        ImageButton newgameBtn = (ImageButton) findViewById(R.id.newgameBtn) ;
        ImageButton instructionsBtn = (ImageButton) findViewById(R.id.instructionsBtn) ;
        ImageButton aboutUsBtn = (ImageButton) findViewById(R.id.statisticsBtn) ;
        ImageButton settingsBtn = (ImageButton) findViewById(R.id.solveBtn) ;

        resumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resume_button) {
                    Intent openResumegame = new Intent(getApplicationContext(), com.example.sudokusolver.S5.class);
                    openResumegame.putExtra("solved", "000000000000000000000000000000000000000000000000000000000000000000000000000000000");
                    openResumegame.putExtra("unsolved", "000000000000000000000000000000000000000000000000000000000000000000000000000000000");
                    openResumegame.putExtra("fromS4beta", false);
                    startActivity(openResumegame);
                }
                else Toast.makeText(getApplicationContext(),"No Previous Sudoku",Toast.LENGTH_SHORT).show();
            }
        });

        newgameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openNewgame = new Intent(getApplicationContext(),com.example.sudokusolver.S3.class) ;
                startActivity(openNewgame);
            }
        });
        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openInstructions = new Intent(getApplicationContext(),com.example.sudokusolver.Instructions.class) ;
                startActivity(openInstructions);
            }

        });
        aboutUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenaboutUs = new Intent(getApplicationContext(),com.example.sudokusolver.AboutUs.class) ;
                startActivity(OpenaboutUs);
            }
        });
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSettings = new Intent(getApplicationContext(),com.example.sudokusolver.Settings.class) ;
                startActivity(openSettings);

            }
        });
    }



}