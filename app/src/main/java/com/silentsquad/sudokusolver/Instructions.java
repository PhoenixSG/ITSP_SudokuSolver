package com.silentsquad.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Instructions extends AppCompatActivity {
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
        setContentView(R.layout.activity_instructions);

        ImageButton menuBtn2 = (ImageButton) findViewById(R.id.menuBtn2);

        ImageButton instructions2 = (ImageButton) findViewById(R.id.howtoplayBtn);

        instructions2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(getApplicationContext(), Instructions2.class);
                 startActivity(intent);
                 finish();
            }
        });

        menuBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent openFirstScreen = new Intent(getApplicationContext(), com.silentsquad.sudokusolver.MainActivity.class);
//                startActivity(openFirstScreen);
                finish() ;

            }
        });




    }
}