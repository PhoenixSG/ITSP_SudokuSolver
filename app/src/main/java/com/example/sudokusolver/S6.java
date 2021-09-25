package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class S6<clockTextView> extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s6);

        if(getIntent().hasExtra("image path")){
            String pathofimage = getIntent().getExtras().getString("image path");

        }

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proceed=new Intent(getApplicationContext(), com.example.sudokusolver.S5.class);
                startActivity(proceed);
            }
        });

    }

}