package com.silentsquad.sudokusolver;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class S4beta extends AppCompatActivity {

    Double output = 0.0;
    ImageButton menuBtn ;
    ImageButton generateSudoku ;
    ImageButton easyDifficulty ;
    ImageButton mediumDifficulty ;
    ImageButton hardDifficulty ;

    int difficulty = 0 ;

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
    String solvedsudoku = "", unsolvedsudoku = "";

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
        setContentView(R.layout.activity_s4beta);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        menuBtn = (ImageButton) findViewById(R.id.menuBtn) ;
        generateSudoku = (ImageButton) findViewById(R.id.generatesudoku);
        easyDifficulty = (ImageButton) findViewById(R.id.easydifficulty);
        mediumDifficulty = (ImageButton) findViewById(R.id.mediumdifficulty);
        hardDifficulty = (ImageButton) findViewById(R.id.harddifficulty);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent openFirstScreen = new Intent(getApplicationContext(), com.silentsquad.sudokusolver.MainActivity.class);
//                startActivity(openFirstScreen);
                finish();
            }
        });

        easyDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty=1 ;
                generateSudoku.setImageResource(R.drawable.generatesudokugreen);
            }
        });

        mediumDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty=2 ;
                generateSudoku.setImageResource(R.drawable.generatesudokuyellow);
            }
        });

        hardDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty=3 ;
                generateSudoku.setImageResource(R.drawable.generatesudokured);
            }
        });


        generateSudoku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                solvedsudoku = "";
                unsolvedsudoku = "";

                output = getlevel() ;

//                try {
//                    output = getlevel();
//                } catch (Exception e) {
//                    Toast.makeText(S4beta.this, "PLEASE SELECT ONE", Toast.LENGTH_SHORT).show();
//                }

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        solvedsudoku = solvedsudoku + board[i][j];
                    }
                }

                for (int i = 0; i < 81; i++) {
                    if (random.nextDouble() < output) {
                        unsolvedsudoku = unsolvedsudoku + solvedsudoku.charAt(i);
                    } else {
                        unsolvedsudoku = unsolvedsudoku + "0";
                    }
                }

                if (output != 0) {
                    Intent opensudoku = new Intent(getApplicationContext(), com.silentsquad.sudokusolver.S5.class);
                    opensudoku.putExtra("solved", solvedsudoku);
                    opensudoku.putExtra("unsolved", unsolvedsudoku);
                    opensudoku.putExtra("fromS4beta",true);
                    startActivity(opensudoku);
                    finish();

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


    public Double getlevel() {

        Double output = 0.0;

        if (difficulty==1) {
            output = 0.5;
        } else if (difficulty==2) {
            output = 0.375;
        } else if (difficulty==3) {
            output = 0.25;
        }
        return output;
    }
}