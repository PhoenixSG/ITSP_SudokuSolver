package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class S3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3);


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