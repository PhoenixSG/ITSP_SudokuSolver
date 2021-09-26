package com.silentsquad.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class S3 extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
        }

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        ImageButton clickAPicButton = (ImageButton) findViewById(R.id.clickAPicButton);

        ImageButton selectionButton = (ImageButton) findViewById(R.id.selectionButton);

        ImageButton menuBtn = (ImageButton) findViewById(R.id.menuBtn);

        clickAPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraScreen = new Intent(getApplicationContext(), com.silentsquad.sudokusolver.S4.class);
                startActivity(openCameraScreen);
                finish();

            }
        });

        selectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openListScreen = new Intent(getApplicationContext(), com.silentsquad.sudokusolver.S4beta.class);
                startActivity(openListScreen);
                finish();

            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent openFirstScreen = new Intent(getApplicationContext(), com.silentsquad.sudokusolver.MainActivity.class);
//                startActivity(openFirstScreen);
                finish();
            }
        });

    }
}