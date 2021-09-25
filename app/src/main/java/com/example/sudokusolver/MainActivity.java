package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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