
package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class S5 extends AppCompatActivity implements View.OnClickListener{

    static boolean[][][] markings = new boolean[9][9][10] ;

    static String GenerateMarkings(int i, int j) {
        String result="" ;
        for(int k=1;k<=9;k++) {
            if(k==7) result+=" " ;
            if(markings[i][j][k]) {
                result += k ;
            }
            else {
                result += "  " ;
            }
            if(k%3==0 && k!=9) {
                result += "\n" ;
            }
            else result +=" " ;
        }
        return result ;
    }

    int activei = -1;
    int activej = -1;
    Button[][] sudokugrid= new Button[9][9];
    ImageButton[] digitButton = new ImageButton[10];
    ImageButton[] imageButton = new ImageButton[10];
    static boolean markingState = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s5);

        digitButton[1] = (ImageButton) findViewById(R.id.digitButton1) ;
        digitButton[2] = (ImageButton) findViewById(R.id.digitButton2) ;
        digitButton[3] = (ImageButton) findViewById(R.id.digitButton3) ;
        digitButton[4] = (ImageButton) findViewById(R.id.digitButton4) ;
        digitButton[5] = (ImageButton) findViewById(R.id.digitButton5) ;
        digitButton[6] = (ImageButton) findViewById(R.id.digitButton6) ;
        digitButton[7] = (ImageButton) findViewById(R.id.digitButton7) ;
        digitButton[8] = (ImageButton) findViewById(R.id.digitButton8) ;
        digitButton[9] = (ImageButton) findViewById(R.id.digitButton9) ;

        imageButton[1] = (ImageButton) findViewById(R.id.imageButton1);
        imageButton[2] = (ImageButton) findViewById(R.id.imageButton2);
        imageButton[3] = (ImageButton) findViewById(R.id.imageButton3);
        imageButton[4] = (ImageButton) findViewById(R.id.imageButton4);
        imageButton[5] = (ImageButton) findViewById(R.id.imageButton5);
        imageButton[6] = (ImageButton) findViewById(R.id.imageButton6);



        sudokugrid[0][0]=(Button) findViewById(R.id.button00);
        sudokugrid[0][1]=(Button) findViewById(R.id.button01);
        sudokugrid[0][2]=(Button) findViewById(R.id.button02);
        sudokugrid[0][3]=(Button) findViewById(R.id.button03);
        sudokugrid[0][4]=(Button) findViewById(R.id.button04);
        sudokugrid[0][5]=(Button) findViewById(R.id.button05);
        sudokugrid[0][6]=(Button) findViewById(R.id.button06);
        sudokugrid[0][7]=(Button) findViewById(R.id.button07);
        sudokugrid[0][8]=(Button) findViewById(R.id.button08);
        sudokugrid[1][0]=(Button) findViewById(R.id.button10);
        sudokugrid[1][1]=(Button) findViewById(R.id.button11);
        sudokugrid[1][2]=(Button) findViewById(R.id.button12);
        sudokugrid[1][3]=(Button) findViewById(R.id.button13);
        sudokugrid[1][4]=(Button) findViewById(R.id.button14);
        sudokugrid[1][5]=(Button) findViewById(R.id.button15);
        sudokugrid[1][6]=(Button) findViewById(R.id.button16);
        sudokugrid[1][7]=(Button) findViewById(R.id.button17);
        sudokugrid[1][8]=(Button) findViewById(R.id.button18);
        sudokugrid[2][0]=(Button) findViewById(R.id.button20);
        sudokugrid[2][1]=(Button) findViewById(R.id.button21);
        sudokugrid[2][2]=(Button) findViewById(R.id.button22);
        sudokugrid[2][3]=(Button) findViewById(R.id.button23);
        sudokugrid[2][4]=(Button) findViewById(R.id.button24);
        sudokugrid[2][5]=(Button) findViewById(R.id.button25);
        sudokugrid[2][6]=(Button) findViewById(R.id.button26);
        sudokugrid[2][7]=(Button) findViewById(R.id.button27);
        sudokugrid[2][8]=(Button) findViewById(R.id.button28);
        sudokugrid[3][0]=(Button) findViewById(R.id.button30);
        sudokugrid[3][1]=(Button) findViewById(R.id.button31);
        sudokugrid[3][2]=(Button) findViewById(R.id.button32);
        sudokugrid[3][3]=(Button) findViewById(R.id.button33);
        sudokugrid[3][4]=(Button) findViewById(R.id.button34);
        sudokugrid[3][5]=(Button) findViewById(R.id.button35);
        sudokugrid[3][6]=(Button) findViewById(R.id.button36);
        sudokugrid[3][7]=(Button) findViewById(R.id.button37);
        sudokugrid[3][8]=(Button) findViewById(R.id.button38);
        sudokugrid[4][0]=(Button) findViewById(R.id.button40);
        sudokugrid[4][1]=(Button) findViewById(R.id.button41);
        sudokugrid[4][2]=(Button) findViewById(R.id.button42);
        sudokugrid[4][3]=(Button) findViewById(R.id.button43);
        sudokugrid[4][4]=(Button) findViewById(R.id.button44);
        sudokugrid[4][5]=(Button) findViewById(R.id.button45);
        sudokugrid[4][6]=(Button) findViewById(R.id.button46);
        sudokugrid[4][7]=(Button) findViewById(R.id.button47);
        sudokugrid[4][8]=(Button) findViewById(R.id.button48);
        sudokugrid[5][0]=(Button) findViewById(R.id.button50);
        sudokugrid[5][1]=(Button) findViewById(R.id.button51);
        sudokugrid[5][2]=(Button) findViewById(R.id.button52);
        sudokugrid[5][3]=(Button) findViewById(R.id.button53);
        sudokugrid[5][4]=(Button) findViewById(R.id.button54);
        sudokugrid[5][5]=(Button) findViewById(R.id.button55);
        sudokugrid[5][6]=(Button) findViewById(R.id.button56);
        sudokugrid[5][7]=(Button) findViewById(R.id.button57);
        sudokugrid[5][8]=(Button) findViewById(R.id.button58);
        sudokugrid[6][0]=(Button) findViewById(R.id.button60);
        sudokugrid[6][1]=(Button) findViewById(R.id.button61);
        sudokugrid[6][2]=(Button) findViewById(R.id.button62);
        sudokugrid[6][3]=(Button) findViewById(R.id.button63);
        sudokugrid[6][4]=(Button) findViewById(R.id.button64);
        sudokugrid[6][5]=(Button) findViewById(R.id.button65);
        sudokugrid[6][6]=(Button) findViewById(R.id.button66);
        sudokugrid[6][7]=(Button) findViewById(R.id.button67);
        sudokugrid[6][8]=(Button) findViewById(R.id.button68);
        sudokugrid[7][0]=(Button) findViewById(R.id.button70);
        sudokugrid[7][1]=(Button) findViewById(R.id.button71);
        sudokugrid[7][2]=(Button) findViewById(R.id.button72);
        sudokugrid[7][3]=(Button) findViewById(R.id.button73);
        sudokugrid[7][4]=(Button) findViewById(R.id.button74);
        sudokugrid[7][5]=(Button) findViewById(R.id.button75);
        sudokugrid[7][6]=(Button) findViewById(R.id.button76);
        sudokugrid[7][7]=(Button) findViewById(R.id.button77);
        sudokugrid[7][8]=(Button) findViewById(R.id.button78);
        sudokugrid[8][0]=(Button) findViewById(R.id.button80);
        sudokugrid[8][1]=(Button) findViewById(R.id.button81);
        sudokugrid[8][2]=(Button) findViewById(R.id.button82);
        sudokugrid[8][3]=(Button) findViewById(R.id.button83);
        sudokugrid[8][4]=(Button) findViewById(R.id.button84);
        sudokugrid[8][5]=(Button) findViewById(R.id.button85);
        sudokugrid[8][6]=(Button) findViewById(R.id.button86);
        sudokugrid[8][7]=(Button) findViewById(R.id.button87);
        sudokugrid[8][8]=(Button) findViewById(R.id.button88);




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

        imageButton[1].setOnClickListener(this);
        imageButton[2].setOnClickListener(this);
        imageButton[3].setOnClickListener(this);
        imageButton[4].setOnClickListener(this);
        imageButton[5].setOnClickListener(this);
        imageButton[6].setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        TextView sample = (TextView) findViewById(R.id.textView2);


        switch(v.getId()){

            case R.id.imageButton1:
                Intent openFirstScreen = new Intent(getApplicationContext(), S3.class);
                startActivity(openFirstScreen);
                break;
            case R.id.imageButton6:
                markingState= !markingState;
                break;
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
                sample.setText("02");
                break;
            case R.id.button03:
                activei=0;
                activej=3;
                sample.setText("03");
                break;
            case R.id.button04:
                activei=0;
                activej=4;
                sample.setText("04");
                break;
            case R.id.button05:
                activei=0;
                activej=5;
                sample.setText("05");
                break;
            case R.id.button06:
                activei=0;
                activej=6;
                sample.setText("06");
                break;
            case R.id.button07:
                activei=0;
                activej=7;
                sample.setText("07");
                break;
            case R.id.button08:
                activei=0;
                activej=8;
                sample.setText("08");
                break;
            case R.id.button10:
                activei=1;
                activej=0;
                sample.setText("10");
                break;
            case R.id.button11:
                activei=1;
                activej=1;
                sample.setText("11");
                break;
            case R.id.button12:
                activei=1;
                activej=2;
                sample.setText("12");
                break;
            case R.id.button13:
                activei=1;
                activej=3;
                sample.setText("13");
                break;
            case R.id.button14:
                activei=1;
                activej=4;
                sample.setText("14");
                break;
            case R.id.button15:
                activei=1;
                activej=5;
                sample.setText("15");
                break;
            case R.id.button16:
                activei=1;
                activej=6;
                sample.setText("16");
                break;
            case R.id.button17:
                activei=1;
                activej=7;
                sample.setText("17");
                break;
            case R.id.button18:
                activei=1;
                activej=8;
                sample.setText("18");
                break;
            case R.id.button20:
                activei=2;
                activej=0;
                sample.setText("20");
                break;
            case R.id.button21:
                activei=2;
                activej=1;
                sample.setText("21");
                break;
            case R.id.button22:
                activei=2;
                activej=2;
                sample.setText("22");
                break;
            case R.id.button23:
                activei=2;
                activej=3;
                sample.setText("23");
                break;
            case R.id.button24:
                activei=2;
                activej=4;
                sample.setText("24");
                break;
            case R.id.button25:
                activei=2;
                activej=5;
                sample.setText("25");
                break;
            case R.id.button26:
                activei=2;
                activej=6;
                sample.setText("26");
                break;
            case R.id.button27:
                activei=2;
                activej=7;
                sample.setText("27");
                break;
            case R.id.button28:
                activei=2;
                activej=8;
                sample.setText("28");
                break;
            case R.id.button30:
                activei=3;
                activej=0;
                sample.setText("30");
                break;
            case R.id.button31:
                activei=3;
                activej=1;
                sample.setText("31");
                break;
            case R.id.button32:
                activei=3;
                activej=2;
                sample.setText("32");
                break;
            case R.id.button33:
                activei=3;
                activej=3;
                sample.setText("33");
                break;
            case R.id.button34:
                activei=3;
                activej=4;
                sample.setText("34");
                break;
            case R.id.button35:
                activei=3;
                activej=5;
                sample.setText("35");
                break;
            case R.id.button36:
                activei=3;
                activej=6;
                sample.setText("36");
                break;
            case R.id.button37:
                activei=3;
                activej=7;
                sample.setText("37");
                break;
            case R.id.button38:
                activei=3;
                activej=8;
                sample.setText("38");
                break;
            case R.id.button40:
                activei=4;
                activej=0;
                sample.setText("40");
                break;
            case R.id.button41:
                activei=4;
                activej=1;
                sample.setText("41");
                break;
            case R.id.button42:
                activei=4;
                activej=2;
                sample.setText("42");
                break;
            case R.id.button43:
                activei=4;
                activej=3;
                sample.setText("43");
                break;
            case R.id.button44:
                activei=4;
                activej=4;
                sample.setText("44");
                break;
            case R.id.button45:
                activei=4;
                activej=5;
                sample.setText("45");
                break;
            case R.id.button46:
                activei=4;
                activej=6;
                sample.setText("46");
                break;
            case R.id.button47:
                activei=4;
                activej=7;
                sample.setText("47");
                break;
            case R.id.button48:
                activei=4;
                activej=8;
                sample.setText("48");
                break;
            case R.id.button50:
                activei=5;
                activej=0;
                sample.setText("50");
                break;
            case R.id.button51:
                activei=5;
                activej=1;
                sample.setText("51");
                break;
            case R.id.button52:
                activei=5;
                activej=2;
                sample.setText("52");
                break;
            case R.id.button53:
                activei=5;
                activej=3;
                sample.setText("53");
                break;
            case R.id.button54:
                activei=5;
                activej=4;
                sample.setText("54");
                break;
            case R.id.button55:
                activei=5;
                activej=5;
                sample.setText("55");
                break;
            case R.id.button56:
                activei=5;
                activej=6;
                sample.setText("56");
                break;
            case R.id.button57:
                activei=5;
                activej=7;
                sample.setText("57");
                break;
            case R.id.button58:
                activei=5;
                activej=8;
                sample.setText("58");
                break;
            case R.id.button60:
                activei=6;
                activej=0;
                sample.setText("60");
                break;
            case R.id.button61:
                activei=6;
                activej=1;
                sample.setText("61");
                break;
            case R.id.button62:
                activei=6;
                activej=2;
                sample.setText("62");
                break;
            case R.id.button63:
                activei=6;
                activej=3;
                sample.setText("63");
                break;
            case R.id.button64:
                activei=6;
                activej=4;
                sample.setText("64");
                break;
            case R.id.button65:
                activei=6;
                activej=5;
                sample.setText("65");
                break;
            case R.id.button66:
                activei=6;
                activej=6;
                sample.setText("66");
                break;
            case R.id.button67:
                activei=6;
                activej=7;
                sample.setText("67");
                break;
            case R.id.button68:
                activei=6;
                activej=8;
                sample.setText("68");
                break;
            case R.id.button70:
                activei=7;
                activej=0;
                sample.setText("70");
                break;
            case R.id.button71:
                activei=7;
                activej=1;
                sample.setText("71");
                break;
            case R.id.button72:
                activei=7;
                activej=2;
                sample.setText("72");
                break;
            case R.id.button73:
                activei=7;
                activej=3;
                sample.setText("73");
                break;
            case R.id.button74:
                activei=7;
                activej=4;
                sample.setText("74");
                break;
            case R.id.button75:
                activei=7;
                activej=5;
                sample.setText("75");
                break;
            case R.id.button76:
                activei=7;
                activej=6;
                sample.setText("76");
                break;
            case R.id.button77:
                activei=7;
                activej=7;
                sample.setText("77");
                break;
            case R.id.button78:
                activei=7;
                activej=8;
                sample.setText("78");
                break;
            case R.id.button80:
                activei=8;
                activej=0;
                sample.setText("80");
                break;
            case R.id.button81:
                activei=8;
                activej=1;
                sample.setText("81");
                break;
            case R.id.button82:
                activei=8;
                activej=2;
                sample.setText("82");
                break;
            case R.id.button83:
                activei=8;
                activej=3;
                sample.setText("83");
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
                if(markingState == true){
                    if(markings[activei][activej][1]==true) markings[activei][activej][1] = false;
                    else markings[activei][activej][1] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("1");
                }
                break;
            case R.id.digitButton2:
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][2]==true) markings[activei][activej][2] = false;
                    else markings[activei][activej][2] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("2");
                }
                break;
            case R.id.digitButton3:
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][3]==true) markings[activei][activej][3] = false;
                    else markings[activei][activej][3] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("3");
                }
                break;
            case R.id.digitButton4:
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][4]==true) markings[activei][activej][4] = false;
                    else markings[activei][activej][4] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("4");
                }
                break;
            case R.id.digitButton5:
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][5]==true) markings[activei][activej][5] = false;
                    else markings[activei][activej][5] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("5");
                }
                break;
            case R.id.digitButton6:
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][6]==true) markings[activei][activej][6] = false;
                    else markings[activei][activej][6] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("6");
                }
                break;
            case R.id.digitButton7:
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][7]==true) markings[activei][activej][7] = false;
                    else markings[activei][activej][7] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("7");
                }
                break;
            case R.id.digitButton8:
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][8]==true) markings[activei][activej][8] = false;
                    else markings[activei][activej][8] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("8");
                }
                break;
            case R.id.digitButton9:
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][9]==true) markings[activei][activej][9] = false;
                    else markings[activei][activej][9] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText("9");
                }
                break;
        }
    }
}