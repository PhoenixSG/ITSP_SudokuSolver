package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        TextView tv = (TextView) findViewById(R.id.textView6);
        tv.setMovementMethod(new ScrollingMovementMethod());

        Button instructions2 = (Button) findViewById(R.id.buttoninstructions2);

        instructions2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(getApplicationContext(), Instructions2.class);
                 startActivity(intent);
                 finish();
            }
        });




    }
}