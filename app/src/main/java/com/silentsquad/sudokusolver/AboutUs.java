package com.silentsquad.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        MainApplication app = (com.silentsquad.sudokusolver.MainApplication) getApplication();
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
        setContentView(R.layout.activity_about_us);
        ImageView tv = (ImageView) findViewById(R.id.aboutus);

        ImageButton menuBtn2 = (ImageButton) findViewById(R.id.menuBtn2);

        menuBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent openFirstScreen = new Intent(getApplicationContext(), com.silentsquad.sudokusolver.MainActivity.class);
//                startActivity(openFirstScreen);
                finish();
            }
        });

    }

}