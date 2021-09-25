package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class S4beta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s4beta);

        Button gotoMainScreenS4beta = (Button) findViewById(R.id.gotoMainScreenS4beta);


        gotoMainScreenS4beta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMainScreen = new Intent(getApplicationContext(), com.example.sudokusolver.S5.class);
                startActivity(openMainScreen);
            }
        });
    }
}