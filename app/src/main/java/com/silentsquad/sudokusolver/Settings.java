package com.silentsquad.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {

    static Boolean MusicState=true ;

    @Override
    protected void onStart() {
        super.onStart();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
            MusicState = true ;
        }
        else {
            MusicState = false ;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApplication app = (MainApplication) getApplication();
        if(app.ring_start) {
            app.ring.start();
            MusicState = true ;
        }
        else {
            MusicState = false ;
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
        setContentView(R.layout.activity_settings);

        MainApplication app = (MainApplication) getApplication();

        ImageButton menuBtn2 = (ImageButton) findViewById(R.id.menuBtn2);
        ImageButton musicbtn = (ImageButton) findViewById(R.id.musicbtn) ;

        if(MusicState) musicbtn.setImageResource(R.drawable.musicbtn2);
        else musicbtn.setImageResource(R.drawable.musicbtn1);

        musicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MusicState) {
                    MusicState=false ;
                    musicbtn.setImageResource(R.drawable.musicbtn1);
                    app.ring.pause();
                    app.ring_start = false;
                }
                else {
                    app.ring.start();
                    app.ring_start =true;
                    MusicState=true ;
                    musicbtn.setImageResource(R.drawable.musicbtn2);
                }


            }
        });
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