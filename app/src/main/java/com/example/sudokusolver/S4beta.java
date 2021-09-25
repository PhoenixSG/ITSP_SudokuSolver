package com.example.sudokusolver;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class S4beta extends AppCompatActivity {

    Button displayData;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Double output=0.0;

    int[][] board = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},

            {2, 3, 1, 5, 6, 4, 8, 9, 7},
            {5, 6, 4, 8, 9, 7, 2, 3, 1},
            {8, 9, 7, 2, 3, 1, 5, 6, 4},

            {3, 1, 2, 6, 4, 5, 9, 7, 8},
            {6, 4, 5, 9, 7, 8, 3, 1, 2},
            {9, 7, 8, 3, 1, 2, 6, 4, 5}
    };

    Random random = new Random();
    String solvedsudoku="", unsolvedsudoku ="";

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
        setContentView(R.layout.activity_s4beta);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        displayData = (Button) findViewById(R.id.gotoMainScreenS4beta);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);



        displayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                solvedsudoku="";
                unsolvedsudoku ="";

                try {
                    output = getlevel();
                }
                catch (Exception e) {
                    Toast.makeText(S4beta.this, "PLEASE SELECT ONE", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        solvedsudoku = solvedsudoku + board[i][j];
                    }
                }

                for(int i=0; i<81; i++){
                    if(random.nextDouble()<output){
                        unsolvedsudoku = unsolvedsudoku +solvedsudoku.charAt(i);
                    }
                    else{
                        unsolvedsudoku = unsolvedsudoku +"0";
                    }
                }

                if(output!=0) {
                    Intent opensudoku = new Intent(getApplicationContext(), com.example.sudokusolver.S5.class);
                    opensudoku.putExtra("solved", solvedsudoku);
                    opensudoku.putExtra("unsolved", unsolvedsudoku);
                    startActivity(opensudoku);
                }

                //SHOULD MOVE TO NEXT SCREEN AFTER THIS.
                // YOU CAN ADD THE INTENT PART HERE AND REMOVE THE TOASTS.

                //CURRENTLY, IF NONE OF EASY MEDIUM HARD IS SELECTED, BUT GO BUTTON IS PRESSED, THEN UNSOLVED IS JUST A STRING OF 81 ZEROES.
                //USER WOULD HAVE TO COME BACK AND RESELECT ONE OF THE 3.



            }
        });
        shuffleNumbers();
        shuffleRows();
        shuffleCols();
        shuffle3X3Rows();
        shuffle3X3Cols();


    }

    private void shuffleNumbers() {
        for (int i = 0; i < 9; i++) {
            int ranNum = random.nextInt(9);
            swapNumbers(i, ranNum);
        }
    }

    private void swapNumbers(int n1, int n2) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[x][y] == n1)
                    board[x][y] = 0;
                if (board[x][y] == n2)
                    board[x][y] = n1;
            }
        }

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[x][y] == 0)
                    board[x][y] = n2;
            }
        }
    }

    private void shuffleRows() {
        int blockNumber;

        for (int i = 0; i < 9; i++) {
            int ranNum = random.nextInt(3);
            blockNumber = i / 3;
            swapRows(i, blockNumber * 3 + ranNum);
        }
    }

    private void swapRows(int r1, int r2) {
        int[] row = board[r1];
        board[r1] = board[r2];
        board[r2] = row;
    }

    private void shuffleCols() {
        int blockNumber;

        for (int i = 0; i < 9; i++) {
            int ranNum = random.nextInt(3);
            blockNumber = i / 3;
            swapCols(i, blockNumber * 3 + ranNum);
        }
    }

    private void swapCols(int c1, int c2) {
        int colVal;
        for (int i = 0; i < 9; i++) {
            colVal = board[i][c1];
            board[i][c1] = board[i][c2];
            board[i][c2] = colVal;
        }
    }

    private void shuffle3X3Rows() {

        for (int i = 0; i < 3; i++) {
            int ranNum = random.nextInt(3);
            swap3X3Rows(i, ranNum);
        }
    }

    private void swap3X3Rows(int r1, int r2) {
        for (int i = 0; i < 3; i++) {
            swapRows(r1 * 3 + i, r2 * 3 + i);
        }
    }


    void shuffle3X3Cols() {

        for (int i = 0; i < 3; i++) {
            int ranNum = random.nextInt(3);
            swap3X3Cols(i, ranNum);
        }
    }
    private void swap3X3Cols(int c1, int c2) {
        for (int i = 0; i < 3; i++) {
            swapCols(c1 * 3 + i, c2 * 3 + i);
        }
    }


    public Double getlevel(){

        int radioId = radioGroup.getCheckedRadioButtonId();
        Double output=0.0;
        radioButton = findViewById(radioId);

        if (radioButton.getText().toString().equals("Easy")) {
            output = 0.5;
        } else if (radioButton.getText().toString().equals("Medium")) {
            output = 0.375;
        } else if (radioButton.getText().toString().equals("Hard")) {
            output = 0.25;
        }
        return output;
    }
}