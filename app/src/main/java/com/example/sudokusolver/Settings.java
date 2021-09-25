package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import static com.example.sudokusolver.S5.RESU;
import static com.example.sudokusolver.S5.SHARED_PREFS;

public class Settings extends AppCompatActivity {

    Switch swi;

    @Override
    protected void onStart() {
        super.onStart();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
            swi.setChecked(true);
        }
        else swi.setChecked(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
            swi.setChecked(true);
        }
        else swi.setChecked(false);
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
        setContentView(R.layout.activity_settings);

        MainApplication app = (MainApplication) getApplication();

        ImageButton menuBtn2 = (ImageButton) findViewById(R.id.menuBtn2);

        swi = (Switch) findViewById(R.id.switch1);
        swi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!swi.isChecked()){
                    app.ring.pause();
                    app.ring_start = false;
                }
                else {
                    app.ring.start();
                    app.ring_start =true;
                }

            }
        });
        menuBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFirstScreen = new Intent(getApplicationContext(), com.example.sudokusolver.MainActivity.class);
                startActivity(openFirstScreen);

            }
        });

    }
}