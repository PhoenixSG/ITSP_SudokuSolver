package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class S3 extends AppCompatActivity {

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
        setContentView(R.layout.activity_s3);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Button clickAPicButton = (Button) findViewById(R.id.clickAPicButton);

        Button selectionButton = (Button) findViewById(R.id.selectionButton);

        clickAPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraScreen = new Intent(getApplicationContext(), com.example.sudokusolver.S4.class);
                startActivity(openCameraScreen);
            }
        });

        selectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openListScreen = new Intent(getApplicationContext(), com.example.sudokusolver.S4beta.class);
                startActivity(openListScreen);
            }
        });

    }
}