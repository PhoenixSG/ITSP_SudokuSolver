package com.example.sudokusolver;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class buffer2 extends AppCompatActivity implements View.OnClickListener{
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


    int activei = -1;
    int activej = -1;
    Button[][] sudokugrid = new Button[9][9];
    ImageView[][] sdg_block = new ImageView[9][9];
    ImageButton[] digitButton = new ImageButton[10];
    int[][] unsolved = new int[9][9];
    Button gotobutton;

    static String example_solved;
    static String unsolved_string;
    String unsolved_final;
    static String current;
    static String[] cell_digit;


    void GridColorChange(int i, int j){
        for(int k=0;k<9;k++) {
            for(int l=0;l<9;l++) {
                if(unsolved[k][l] != 0) continue ;
                if((k/3+l/3)%2==0) sdg_block[k][l].setImageResource(R.drawable.block1);
                else sdg_block[k][l].setImageResource(R.drawable.block2);
            }
        }
        for(int k=0;k<9;k++) {
            if(unsolved[k][j] != 0) continue ;
            sdg_block[k][j].setImageResource(R.drawable.block5);
        }
        for(int l=0;l<9;l++) {
            if(unsolved[i][l] != 0) continue ;
            sdg_block[i][l].setImageResource(R.drawable.block5);
        }
        sdg_block[i][j].setImageResource(R.drawable.block7);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer2);


        gotobutton = (Button) findViewById(R.id.gotobutton);

        digitButton[1] = (ImageButton) findViewById(R.id.digitButton1);
        digitButton[2] = (ImageButton) findViewById(R.id.digitButton2);
        digitButton[3] = (ImageButton) findViewById(R.id.digitButton3);
        digitButton[4] = (ImageButton) findViewById(R.id.digitButton4);
        digitButton[5] = (ImageButton) findViewById(R.id.digitButton5);
        digitButton[6] = (ImageButton) findViewById(R.id.digitButton6);
        digitButton[7] = (ImageButton) findViewById(R.id.digitButton7);
        digitButton[8] = (ImageButton) findViewById(R.id.digitButton8);
        digitButton[9] = (ImageButton) findViewById(R.id.digitButton9);


        sudokugrid[0][0] = (Button) findViewById(R.id.button00);
        sudokugrid[0][1] = (Button) findViewById(R.id.button01);
        sudokugrid[0][2] = (Button) findViewById(R.id.button02);
        sudokugrid[0][3] = (Button) findViewById(R.id.button03);
        sudokugrid[0][4] = (Button) findViewById(R.id.button04);
        sudokugrid[0][5] = (Button) findViewById(R.id.button05);
        sudokugrid[0][6] = (Button) findViewById(R.id.button06);
        sudokugrid[0][7] = (Button) findViewById(R.id.button07);
        sudokugrid[0][8] = (Button) findViewById(R.id.button08);
        sudokugrid[1][0] = (Button) findViewById(R.id.button10);
        sudokugrid[1][1] = (Button) findViewById(R.id.button11);
        sudokugrid[1][2] = (Button) findViewById(R.id.button12);
        sudokugrid[1][3] = (Button) findViewById(R.id.button13);
        sudokugrid[1][4] = (Button) findViewById(R.id.button14);
        sudokugrid[1][5] = (Button) findViewById(R.id.button15);
        sudokugrid[1][6] = (Button) findViewById(R.id.button16);
        sudokugrid[1][7] = (Button) findViewById(R.id.button17);
        sudokugrid[1][8] = (Button) findViewById(R.id.button18);
        sudokugrid[2][0] = (Button) findViewById(R.id.button20);
        sudokugrid[2][1] = (Button) findViewById(R.id.button21);
        sudokugrid[2][2] = (Button) findViewById(R.id.button22);
        sudokugrid[2][3] = (Button) findViewById(R.id.button23);
        sudokugrid[2][4] = (Button) findViewById(R.id.button24);
        sudokugrid[2][5] = (Button) findViewById(R.id.button25);
        sudokugrid[2][6] = (Button) findViewById(R.id.button26);
        sudokugrid[2][7] = (Button) findViewById(R.id.button27);
        sudokugrid[2][8] = (Button) findViewById(R.id.button28);
        sudokugrid[3][0] = (Button) findViewById(R.id.button30);
        sudokugrid[3][1] = (Button) findViewById(R.id.button31);
        sudokugrid[3][2] = (Button) findViewById(R.id.button32);
        sudokugrid[3][3] = (Button) findViewById(R.id.button33);
        sudokugrid[3][4] = (Button) findViewById(R.id.button34);
        sudokugrid[3][5] = (Button) findViewById(R.id.button35);
        sudokugrid[3][6] = (Button) findViewById(R.id.button36);
        sudokugrid[3][7] = (Button) findViewById(R.id.button37);
        sudokugrid[3][8] = (Button) findViewById(R.id.button38);
        sudokugrid[4][0] = (Button) findViewById(R.id.button40);
        sudokugrid[4][1] = (Button) findViewById(R.id.button41);
        sudokugrid[4][2] = (Button) findViewById(R.id.button42);
        sudokugrid[4][3] = (Button) findViewById(R.id.button43);
        sudokugrid[4][4] = (Button) findViewById(R.id.button44);
        sudokugrid[4][5] = (Button) findViewById(R.id.button45);
        sudokugrid[4][6] = (Button) findViewById(R.id.button46);
        sudokugrid[4][7] = (Button) findViewById(R.id.button47);
        sudokugrid[4][8] = (Button) findViewById(R.id.button48);
        sudokugrid[5][0] = (Button) findViewById(R.id.button50);
        sudokugrid[5][1] = (Button) findViewById(R.id.button51);
        sudokugrid[5][2] = (Button) findViewById(R.id.button52);
        sudokugrid[5][3] = (Button) findViewById(R.id.button53);
        sudokugrid[5][4] = (Button) findViewById(R.id.button54);
        sudokugrid[5][5] = (Button) findViewById(R.id.button55);
        sudokugrid[5][6] = (Button) findViewById(R.id.button56);
        sudokugrid[5][7] = (Button) findViewById(R.id.button57);
        sudokugrid[5][8] = (Button) findViewById(R.id.button58);
        sudokugrid[6][0] = (Button) findViewById(R.id.button60);
        sudokugrid[6][1] = (Button) findViewById(R.id.button61);
        sudokugrid[6][2] = (Button) findViewById(R.id.button62);
        sudokugrid[6][3] = (Button) findViewById(R.id.button63);
        sudokugrid[6][4] = (Button) findViewById(R.id.button64);
        sudokugrid[6][5] = (Button) findViewById(R.id.button65);
        sudokugrid[6][6] = (Button) findViewById(R.id.button66);
        sudokugrid[6][7] = (Button) findViewById(R.id.button67);
        sudokugrid[6][8] = (Button) findViewById(R.id.button68);
        sudokugrid[7][0] = (Button) findViewById(R.id.button70);
        sudokugrid[7][1] = (Button) findViewById(R.id.button71);
        sudokugrid[7][2] = (Button) findViewById(R.id.button72);
        sudokugrid[7][3] = (Button) findViewById(R.id.button73);
        sudokugrid[7][4] = (Button) findViewById(R.id.button74);
        sudokugrid[7][5] = (Button) findViewById(R.id.button75);
        sudokugrid[7][6] = (Button) findViewById(R.id.button76);
        sudokugrid[7][7] = (Button) findViewById(R.id.button77);
        sudokugrid[7][8] = (Button) findViewById(R.id.button78);
        sudokugrid[8][0] = (Button) findViewById(R.id.button80);
        sudokugrid[8][1] = (Button) findViewById(R.id.button81);
        sudokugrid[8][2] = (Button) findViewById(R.id.button82);
        sudokugrid[8][3] = (Button) findViewById(R.id.button83);
        sudokugrid[8][4] = (Button) findViewById(R.id.button84);
        sudokugrid[8][5] = (Button) findViewById(R.id.button85);
        sudokugrid[8][6] = (Button) findViewById(R.id.button86);
        sudokugrid[8][7] = (Button) findViewById(R.id.button87);
        sudokugrid[8][8] = (Button) findViewById(R.id.button88);

        sudokugrid[0][0].setOnClickListener(this);
        sudokugrid[0][1].setOnClickListener(this);
        sudokugrid[0][2].setOnClickListener(this);
        sudokugrid[0][3].setOnClickListener(this);
        sudokugrid[0][4].setOnClickListener(this);
        sudokugrid[0][5].setOnClickListener(this);
        sudokugrid[0][6].setOnClickListener(this);
        sudokugrid[0][7].setOnClickListener(this);
        sudokugrid[0][8].setOnClickListener(this);
        sudokugrid[1][0].setOnClickListener(this);
        sudokugrid[1][1].setOnClickListener(this);
        sudokugrid[1][2].setOnClickListener(this);
        sudokugrid[1][3].setOnClickListener(this);
        sudokugrid[1][4].setOnClickListener(this);
        sudokugrid[1][5].setOnClickListener(this);
        sudokugrid[1][6].setOnClickListener(this);
        sudokugrid[1][7].setOnClickListener(this);
        sudokugrid[1][8].setOnClickListener(this);
        sudokugrid[2][0].setOnClickListener(this);
        sudokugrid[2][1].setOnClickListener(this);
        sudokugrid[2][2].setOnClickListener(this);
        sudokugrid[2][3].setOnClickListener(this);
        sudokugrid[2][4].setOnClickListener(this);
        sudokugrid[2][5].setOnClickListener(this);
        sudokugrid[2][6].setOnClickListener(this);
        sudokugrid[2][7].setOnClickListener(this);
        sudokugrid[2][8].setOnClickListener(this);
        sudokugrid[3][0].setOnClickListener(this);
        sudokugrid[3][1].setOnClickListener(this);
        sudokugrid[3][2].setOnClickListener(this);
        sudokugrid[3][3].setOnClickListener(this);
        sudokugrid[3][4].setOnClickListener(this);
        sudokugrid[3][5].setOnClickListener(this);
        sudokugrid[3][6].setOnClickListener(this);
        sudokugrid[3][7].setOnClickListener(this);
        sudokugrid[3][8].setOnClickListener(this);
        sudokugrid[4][0].setOnClickListener(this);
        sudokugrid[4][1].setOnClickListener(this);
        sudokugrid[4][2].setOnClickListener(this);
        sudokugrid[4][3].setOnClickListener(this);
        sudokugrid[4][4].setOnClickListener(this);
        sudokugrid[4][5].setOnClickListener(this);
        sudokugrid[4][6].setOnClickListener(this);
        sudokugrid[4][7].setOnClickListener(this);
        sudokugrid[4][8].setOnClickListener(this);
        sudokugrid[5][0].setOnClickListener(this);
        sudokugrid[5][1].setOnClickListener(this);
        sudokugrid[5][2].setOnClickListener(this);
        sudokugrid[5][3].setOnClickListener(this);
        sudokugrid[5][4].setOnClickListener(this);
        sudokugrid[5][5].setOnClickListener(this);
        sudokugrid[5][6].setOnClickListener(this);
        sudokugrid[5][7].setOnClickListener(this);
        sudokugrid[5][8].setOnClickListener(this);
        sudokugrid[6][0].setOnClickListener(this);
        sudokugrid[6][1].setOnClickListener(this);
        sudokugrid[6][2].setOnClickListener(this);
        sudokugrid[6][3].setOnClickListener(this);
        sudokugrid[6][4].setOnClickListener(this);
        sudokugrid[6][5].setOnClickListener(this);
        sudokugrid[6][6].setOnClickListener(this);
        sudokugrid[6][7].setOnClickListener(this);
        sudokugrid[6][8].setOnClickListener(this);
        sudokugrid[7][0].setOnClickListener(this);
        sudokugrid[7][1].setOnClickListener(this);
        sudokugrid[7][2].setOnClickListener(this);
        sudokugrid[7][3].setOnClickListener(this);
        sudokugrid[7][4].setOnClickListener(this);
        sudokugrid[7][5].setOnClickListener(this);
        sudokugrid[7][6].setOnClickListener(this);
        sudokugrid[7][7].setOnClickListener(this);
        sudokugrid[7][8].setOnClickListener(this);
        sudokugrid[8][0].setOnClickListener(this);
        sudokugrid[8][1].setOnClickListener(this);
        sudokugrid[8][2].setOnClickListener(this);
        sudokugrid[8][3].setOnClickListener(this);
        sudokugrid[8][4].setOnClickListener(this);
        sudokugrid[8][5].setOnClickListener(this);
        sudokugrid[8][6].setOnClickListener(this);
        sudokugrid[8][7].setOnClickListener(this);
        sudokugrid[8][8].setOnClickListener(this);

        digitButton[1].setOnClickListener(this);
        digitButton[2].setOnClickListener(this);
        digitButton[3].setOnClickListener(this);
        digitButton[4].setOnClickListener(this);
        digitButton[5].setOnClickListener(this);
        digitButton[6].setOnClickListener(this);
        digitButton[7].setOnClickListener(this);
        digitButton[8].setOnClickListener(this);
        digitButton[9].setOnClickListener(this);


        unsolved_string = getIntent().getStringExtra("predictions");

       // unsolved_string = "284375190739816254651042378476120539312594687598637012143760825965203741827451963";


        for (int i = 0; i < 81; i++) {
            int a = unsolved_string.charAt(i) - '0';
            unsolved[i / 9][i % 9] = a;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (unsolved[i][j] != 0) {
                    sudokugrid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[i][j].setText("" + unsolved[i][j]);
                } else {
                    sudokugrid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[i][j].setText(" ");
                }

            }

        }


        gotobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String unsolved_final ="";

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        String x = (String) sudokugrid[i][j].getText();
                        if(x==" ") {
                            unsolved_final += "0";
                        }
                        else{
                            unsolved_final += x;
                        }
                    }
                }


                RequestQueue queue = Volley.newRequestQueue(buffer2.this);
                String url = "https://sudoku-solving-algorithm.herokuapp.com/";
               // TextView textView = (TextView) findViewById(R.id.textView3);

               // textView.setText(unsolved_final);

                Map<String, String> postParam = new HashMap<String, String>();
                postParam.put("unsolved_sudoku", unsolved_final);


                String finalUnsolved_final = unsolved_final;
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, new JSONObject(postParam),
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                               // textView.setText(response.toString());

                                Intent gotoS5 = new Intent(getApplicationContext(),com.example.sudokusolver.S5.class) ;
                                try {
                                    gotoS5.putExtra("solved", (String) response.get("solved_sudoku"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                gotoS5.putExtra("unsolved", finalUnsolved_final);

                                startActivity(gotoS5);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(buffer2.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {

                };

                queue.add(jsonObjReq);

            }
        });


    }




    @Override
    public void onClick(View v) {





        switch(v.getId()){


            case R.id.button00:
                activei=0;
                activej=0;

                break;
            case R.id.button01:
                activei=0;
                activej=1;

                break;
            case R.id.button02:
                activei=0;
                activej=2;

                break;
            case R.id.button03:
                activei=0;
                activej=3;
                break;
            case R.id.button04:
                activei=0;
                activej=4;
                break;
            case R.id.button05:
                activei=0;
                activej=5;
                break;
            case R.id.button06:
                activei=0;
                activej=6;
                break;
            case R.id.button07:
                activei=0;
                activej=7;
                break;
            case R.id.button08:
                activei=0;
                activej=8;
                break;
            case R.id.button10:
                activei=1;
                activej=0;
                break;
            case R.id.button11:
                activei=1;
                activej=1;
                break;
            case R.id.button12:
                activei=1;
                activej=2;
                break;
            case R.id.button13:
                activei=1;
                activej=3;
                break;
            case R.id.button14:
                activei=1;
                activej=4;
                break;
            case R.id.button15:
                activei=1;
                activej=5;
                break;
            case R.id.button16:
                activei=1;
                activej=6;
                break;
            case R.id.button17:
                activei=1;
                activej=7;
                break;
            case R.id.button18:
                activei=1;
                activej=8;
                break;
            case R.id.button20:
                activei=2;
                activej=0;
                break;
            case R.id.button21:
                activei=2;
                activej=1;
                break;
            case R.id.button22:
                activei=2;
                activej=2;
                break;
            case R.id.button23:
                activei=2;
                activej=3;
                break;
            case R.id.button24:
                activei=2;
                activej=4;
                break;
            case R.id.button25:
                activei=2;
                activej=5;
                break;
            case R.id.button26:
                activei=2;
                activej=6;
                break;
            case R.id.button27:
                activei=2;
                activej=7;
                break;
            case R.id.button28:
                activei=2;
                activej=8;
                break;
            case R.id.button30:
                activei=3;
                activej=0;
                break;
            case R.id.button31:
                activei=3;
                activej=1;
                break;
            case R.id.button32:
                activei=3;
                activej=2;
                break;
            case R.id.button33:
                activei=3;
                activej=3;
                break;
            case R.id.button34:
                activei=3;
                activej=4;
                break;
            case R.id.button35:
                activei=3;
                activej=5;
                break;
            case R.id.button36:
                activei=3;
                activej=6;
                break;
            case R.id.button37:
                activei=3;
                activej=7;
                break;
            case R.id.button38:
                activei=3;
                activej=8;
                break;
            case R.id.button40:
                activei=4;
                activej=0;
                break;
            case R.id.button41:
                activei=4;
                activej=1;
                break;
            case R.id.button42:
                activei=4;
                activej=2;
                break;
            case R.id.button43:
                activei=4;
                activej=3;
                break;
            case R.id.button44:
                activei=4;
                activej=4;
                break;
            case R.id.button45:
                activei=4;
                activej=5;
                break;
            case R.id.button46:
                activei=4;
                activej=6;
                break;
            case R.id.button47:
                activei=4;
                activej=7;
                break;
            case R.id.button48:
                activei=4;
                activej=8;
                break;
            case R.id.button50:
                activei=5;
                activej=0;
                break;
            case R.id.button51:
                activei=5;
                activej=1;
                break;
            case R.id.button52:
                activei=5;
                activej=2;
                break;
            case R.id.button53:
                activei=5;
                activej=3;
                break;
            case R.id.button54:
                activei=5;
                activej=4;
                break;
            case R.id.button55:
                activei=5;
                activej=5;
                break;
            case R.id.button56:
                activei=5;
                activej=6;
                break;
            case R.id.button57:
                activei=5;
                activej=7;
                break;
            case R.id.button58:
                activei=5;
                activej=8;
                break;
            case R.id.button60:
                activei=6;
                activej=0;
                break;
            case R.id.button61:
                activei=6;
                activej=1;
                break;
            case R.id.button62:
                activei=6;
                activej=2;
                break;
            case R.id.button63:
                activei=6;
                activej=3;
                break;
            case R.id.button64:
                activei=6;
                activej=4;
                break;
            case R.id.button65:
                activei=6;
                activej=5;
                break;
            case R.id.button66:
                activei=6;
                activej=6;
                break;
            case R.id.button67:
                activei=6;
                activej=7;
                break;
            case R.id.button68:
                activei=6;
                activej=8;
                break;
            case R.id.button70:
                activei=7;
                activej=0;
                break;
            case R.id.button71:
                activei=7;
                activej=1;
                break;
            case R.id.button72:
                activei=7;
                activej=2;
                break;
            case R.id.button73:
                activei=7;
                activej=3;
                break;
            case R.id.button74:
                activei=7;
                activej=4;
                break;
            case R.id.button75:
                activei=7;
                activej=5;
                break;
            case R.id.button76:
                activei=7;
                activej=6;
                break;
            case R.id.button77:
                activei=7;
                activej=7;
                break;
            case R.id.button78:
                activei=7;
                activej=8;
                break;
            case R.id.button80:
                activei=8;
                activej=0;
                break;
            case R.id.button81:
                activei=8;
                activej=1;
                break;
            case R.id.button82:
                activei=8;
                activej=2;
                break;
            case R.id.button83:
                activei=8;
                activej=3;
                break;
            case R.id.button84:
                activei=8;
                activej=4;
                break;
            case R.id.button85:
                activei=8;
                activej=5;
                break;
            case R.id.button86:
                activei=8;
                activej=6;
                break;
            case R.id.button87:
                activei=8;
                activej=7;
                break;
            case R.id.button88:
                activei=8;
                activej=8;
                break;

            case R.id.digitButton1:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("1");

                unsolved[activei][activej]=1;

                break;
            case R.id.digitButton2:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("2");

                unsolved[activei][activej]=2;

                break;
            case R.id.digitButton3:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("3");

                unsolved[activei][activej]=3;

                break;
            case R.id.digitButton4:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("4");

                unsolved[activei][activej]=4;

                break;
            case R.id.digitButton5:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("5");

                unsolved[activei][activej]=5;

                break;
            case R.id.digitButton6:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("6");

                unsolved[activei][activej]=6;

                break;
            case R.id.digitButton7:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("7");

                unsolved[activei][activej]=7;

                break;
            case R.id.digitButton8:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("8");

                unsolved[activei][activej]=8;

                break;
            case R.id.digitButton9:
                if(activej==-1) break ;

                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("9");

                unsolved[activei][activej]=9;

                break;
        }

    }
}