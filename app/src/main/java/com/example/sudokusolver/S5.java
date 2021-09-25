
package com.example.sudokusolver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class S5 extends AppCompatActivity implements View.OnClickListener{

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text",RESU = "resu";
    public static boolean resume ;
    private String text;

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

        text = example_solved + "-" + example_unsolved + "-" ;
        for(int i=0 ; i<81 ; i++){
            if (sudokugrid[i / 9][i % 9].getText().toString() != " ") {
                text = text + sudokugrid[i / 9][i % 9].getText().toString() + ";";
            }
            else    text = text +"0;";
        }
        text = text +"-" + counter;
        savedata();
    }

    static boolean[][][] markings = new boolean[9][9][10] ;
    int activei = -1;
    int activej = -1;
    Button[][] sudokugrid = new Button[9][9];
    ImageView[][] sdg_block = new ImageView[9][9];
    ImageButton[] digitButton = new ImageButton[10];
    int[][] unsolved = new int[9][9];
    int[][] solving= new int[9][9];
    int[][] solved = new int[9][9];
    static boolean[][] predefined_numbers = new boolean[9][9];
    static boolean markingState = false ;
    ImageButton menuBtn ;
    ImageButton solveBtn ;
    ImageButton hintBtn ;
    ImageButton resetBtn ;
    ImageButton eraseBtn ;
    ImageButton notesBtn ;
    static String example_solved;
    static String example_unsolved;
    static String current;
    static String[] cell_digit;
    static String string_timer;
    static boolean solved_state = false;
    CountDownTimer sudokutimer;


    TextView Timer;
    public int counter;
    boolean started = false ;

    void after_solve(){
        if(solved_state = true){
            Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
        }
    }

    static String FormatTime(int seconds) {
        int minutes = seconds/60 ;
        seconds = seconds%60 ;
        if(seconds<10 && minutes<10) {
            return "0"+ minutes + ":0" + seconds ;
        }
        if(seconds<10) {
            return minutes + ":0" + seconds ;
        }
        if(minutes<10) {
            return "0"+ minutes + ":" + seconds ;
        }
        return minutes + ":" + seconds ;
    }

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

    void check_solved(){
        for(int i=0;i<81;i++){
            if(solving[i/9][i%9]==solved[i/9][i%9]){
                if(i==80){
                    resume=false;
                    sudokutimer.cancel();
                    Toast.makeText(getApplicationContext(),"You have solved",Toast.LENGTH_SHORT).show();
                    Intent Back2menu = new Intent(getApplicationContext(),com.example.sudokusolver.FinishSplash.class) ;
                    startActivity(Back2menu);
                }
            }
            else{
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s5);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        if(getIntent().getBooleanExtra("fromS4beta",true)) {
            resume = true;
            example_unsolved = getIntent().getStringExtra("unsolved");
            example_solved = getIntent().getStringExtra("solved");
        }
        else {
            loaddata();
            String[] part = text.split("-");
            example_solved = part[0];
            example_unsolved = part[1];
            current = part[2];
            string_timer = part[3];
            cell_digit = current.split(";");
        }

        for(int i=0 ; i<81 ; i++){
            int a = example_unsolved.charAt(i) - '0';
            unsolved[i/9][i%9] =  a;
            solving[i/9][i%9]=a;
        }
        for(int i=0 ; i<81 ; i++){
            int a = example_solved.charAt(i) - '0';
            solved[i/9][i%9] =  a;
        }

        digitButton[1] = (ImageButton) findViewById(R.id.digitButton1) ;
        digitButton[2] = (ImageButton) findViewById(R.id.digitButton2) ;
        digitButton[3] = (ImageButton) findViewById(R.id.digitButton3) ;
        digitButton[4] = (ImageButton) findViewById(R.id.digitButton4) ;
        digitButton[5] = (ImageButton) findViewById(R.id.digitButton5) ;
        digitButton[6] = (ImageButton) findViewById(R.id.digitButton6) ;
        digitButton[7] = (ImageButton) findViewById(R.id.digitButton7) ;
        digitButton[8] = (ImageButton) findViewById(R.id.digitButton8) ;
        digitButton[9] = (ImageButton) findViewById(R.id.digitButton9) ;

        menuBtn = (ImageButton) findViewById(R.id.menuBtn);
        solveBtn = (ImageButton) findViewById(R.id.solveBtn);
        hintBtn = (ImageButton) findViewById(R.id.hintBtn);
        resetBtn = (ImageButton) findViewById(R.id.resetBtn);
        eraseBtn = (ImageButton) findViewById(R.id.eraseBtn);
        notesBtn = (ImageButton) findViewById(R.id.notesBtn);

        sdg_block[0][0] = (ImageView) findViewById(R.id.sdg_block00) ;
        sdg_block[0][1] = (ImageView) findViewById(R.id.sdg_block01) ;
        sdg_block[0][2] = (ImageView) findViewById(R.id.sdg_block02) ;
        sdg_block[0][3] = (ImageView) findViewById(R.id.sdg_block03) ;
        sdg_block[0][4] = (ImageView) findViewById(R.id.sdg_block04) ;
        sdg_block[0][5] = (ImageView) findViewById(R.id.sdg_block05) ;
        sdg_block[0][6] = (ImageView) findViewById(R.id.sdg_block06) ;
        sdg_block[0][7] = (ImageView) findViewById(R.id.sdg_block07) ;
        sdg_block[0][8] = (ImageView) findViewById(R.id.sdg_block08) ;
        sdg_block[1][0] = (ImageView) findViewById(R.id.sdg_block10) ;
        sdg_block[1][1] = (ImageView) findViewById(R.id.sdg_block11) ;
        sdg_block[1][2] = (ImageView) findViewById(R.id.sdg_block12) ;
        sdg_block[1][3] = (ImageView) findViewById(R.id.sdg_block13) ;
        sdg_block[1][4] = (ImageView) findViewById(R.id.sdg_block14) ;
        sdg_block[1][5] = (ImageView) findViewById(R.id.sdg_block15) ;
        sdg_block[1][6] = (ImageView) findViewById(R.id.sdg_block16) ;
        sdg_block[1][7] = (ImageView) findViewById(R.id.sdg_block17) ;
        sdg_block[1][8] = (ImageView) findViewById(R.id.sdg_block18) ;
        sdg_block[2][0] = (ImageView) findViewById(R.id.sdg_block20) ;
        sdg_block[2][1] = (ImageView) findViewById(R.id.sdg_block21) ;
        sdg_block[2][2] = (ImageView) findViewById(R.id.sdg_block22) ;
        sdg_block[2][3] = (ImageView) findViewById(R.id.sdg_block23) ;
        sdg_block[2][4] = (ImageView) findViewById(R.id.sdg_block24) ;
        sdg_block[2][5] = (ImageView) findViewById(R.id.sdg_block25) ;
        sdg_block[2][6] = (ImageView) findViewById(R.id.sdg_block26) ;
        sdg_block[2][7] = (ImageView) findViewById(R.id.sdg_block27) ;
        sdg_block[2][8] = (ImageView) findViewById(R.id.sdg_block28) ;
        sdg_block[3][0] = (ImageView) findViewById(R.id.sdg_block30) ;
        sdg_block[3][1] = (ImageView) findViewById(R.id.sdg_block31) ;
        sdg_block[3][2] = (ImageView) findViewById(R.id.sdg_block32) ;
        sdg_block[3][3] = (ImageView) findViewById(R.id.sdg_block33) ;
        sdg_block[3][4] = (ImageView) findViewById(R.id.sdg_block34) ;
        sdg_block[3][5] = (ImageView) findViewById(R.id.sdg_block35) ;
        sdg_block[3][6] = (ImageView) findViewById(R.id.sdg_block36) ;
        sdg_block[3][7] = (ImageView) findViewById(R.id.sdg_block37) ;
        sdg_block[3][8] = (ImageView) findViewById(R.id.sdg_block38) ;
        sdg_block[4][0] = (ImageView) findViewById(R.id.sdg_block40) ;
        sdg_block[4][1] = (ImageView) findViewById(R.id.sdg_block41) ;
        sdg_block[4][2] = (ImageView) findViewById(R.id.sdg_block42) ;
        sdg_block[4][3] = (ImageView) findViewById(R.id.sdg_block43) ;
        sdg_block[4][4] = (ImageView) findViewById(R.id.sdg_block44) ;
        sdg_block[4][5] = (ImageView) findViewById(R.id.sdg_block45) ;
        sdg_block[4][6] = (ImageView) findViewById(R.id.sdg_block46) ;
        sdg_block[4][7] = (ImageView) findViewById(R.id.sdg_block47) ;
        sdg_block[4][8] = (ImageView) findViewById(R.id.sdg_block48) ;
        sdg_block[5][0] = (ImageView) findViewById(R.id.sdg_block50) ;
        sdg_block[5][1] = (ImageView) findViewById(R.id.sdg_block51) ;
        sdg_block[5][2] = (ImageView) findViewById(R.id.sdg_block52) ;
        sdg_block[5][3] = (ImageView) findViewById(R.id.sdg_block53) ;
        sdg_block[5][4] = (ImageView) findViewById(R.id.sdg_block54) ;
        sdg_block[5][5] = (ImageView) findViewById(R.id.sdg_block55) ;
        sdg_block[5][6] = (ImageView) findViewById(R.id.sdg_block56) ;
        sdg_block[5][7] = (ImageView) findViewById(R.id.sdg_block57) ;
        sdg_block[5][8] = (ImageView) findViewById(R.id.sdg_block58) ;
        sdg_block[6][0] = (ImageView) findViewById(R.id.sdg_block60) ;
        sdg_block[6][1] = (ImageView) findViewById(R.id.sdg_block61) ;
        sdg_block[6][2] = (ImageView) findViewById(R.id.sdg_block62) ;
        sdg_block[6][3] = (ImageView) findViewById(R.id.sdg_block63) ;
        sdg_block[6][4] = (ImageView) findViewById(R.id.sdg_block64) ;
        sdg_block[6][5] = (ImageView) findViewById(R.id.sdg_block65) ;
        sdg_block[6][6] = (ImageView) findViewById(R.id.sdg_block66) ;
        sdg_block[6][7] = (ImageView) findViewById(R.id.sdg_block67) ;
        sdg_block[6][8] = (ImageView) findViewById(R.id.sdg_block68) ;
        sdg_block[7][0] = (ImageView) findViewById(R.id.sdg_block70) ;
        sdg_block[7][1] = (ImageView) findViewById(R.id.sdg_block71) ;
        sdg_block[7][2] = (ImageView) findViewById(R.id.sdg_block72) ;
        sdg_block[7][3] = (ImageView) findViewById(R.id.sdg_block73) ;
        sdg_block[7][4] = (ImageView) findViewById(R.id.sdg_block74) ;
        sdg_block[7][5] = (ImageView) findViewById(R.id.sdg_block75) ;
        sdg_block[7][6] = (ImageView) findViewById(R.id.sdg_block76) ;
        sdg_block[7][7] = (ImageView) findViewById(R.id.sdg_block77) ;
        sdg_block[7][8] = (ImageView) findViewById(R.id.sdg_block78) ;
        sdg_block[8][0] = (ImageView) findViewById(R.id.sdg_block80) ;
        sdg_block[8][1] = (ImageView) findViewById(R.id.sdg_block81) ;
        sdg_block[8][2] = (ImageView) findViewById(R.id.sdg_block82) ;
        sdg_block[8][3] = (ImageView) findViewById(R.id.sdg_block83) ;
        sdg_block[8][4] = (ImageView) findViewById(R.id.sdg_block84) ;
        sdg_block[8][5] = (ImageView) findViewById(R.id.sdg_block85) ;
        sdg_block[8][6] = (ImageView) findViewById(R.id.sdg_block86) ;
        sdg_block[8][7] = (ImageView) findViewById(R.id.sdg_block87) ;
        sdg_block[8][8] = (ImageView) findViewById(R.id.sdg_block88) ;


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

        menuBtn.setOnClickListener(this);
        solveBtn.setOnClickListener(this);
        hintBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        eraseBtn.setOnClickListener(this);
        notesBtn.setOnClickListener(this);

        if(!getIntent().getBooleanExtra("fromS4beta",true)){
            counter = Integer.valueOf(string_timer);
        }

        Timer = (TextView) findViewById(R.id.textView2) ;
        if(!started) {
            started=true ;
            sudokutimer = new CountDownTimer(300000000, 1000){
                public void onTick(long millisUntilFinished){
                    Timer.setText(FormatTime(counter));
                    counter++;
                }
                public void onFinish(){
                    Timer.setText("FINISH!!");
                }
            };
            sudokutimer.start();
        }

        for(int i=0 ; i<9 ; i++){
            for(int j=0 ; j<9 ; j++){
                if(unsolved[i][j] != 0){
                    predefined_numbers[i][j] = true;
                    sudokugrid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[i][j].setText("" + unsolved[i][j]);
                    sdg_block[i][j].setImageResource(R.drawable.block3);
                }
                else {
                    if(getIntent().getBooleanExtra("fromS4beta",true)) {
                        sudokugrid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                        sudokugrid[i][j].setText(" ");
                        if((i/3+j/3)%2==1) sdg_block[i][j].setImageResource(R.drawable.block2);
                        else sdg_block[i][j].setImageResource(R.drawable.block1);

                    }
                    else{
                        if(cell_digit[i*9+j].length() != 1){
                            sudokugrid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                            sudokugrid[i][j].setText(cell_digit[i*9+j]+"");
                            if((i/3+j/3)%2==1) sdg_block[i][j].setImageResource(R.drawable.block2);
                            else sdg_block[i][j].setImageResource(R.drawable.block1);
                        }
                        else if (!cell_digit[i*9 +j].contains("0")){
                            sudokugrid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                            sudokugrid[i][j].setText(cell_digit[i*9+j]+"");
                            if((i/3+j/3)%2==1) sdg_block[i][j].setImageResource(R.drawable.block2);
                            else sdg_block[i][j].setImageResource(R.drawable.block1);
                        }
                        else {
                            sudokugrid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                            sudokugrid[i][j].setText(" ");
                            if((i/3+j/3)%2==1) sdg_block[i][j].setImageResource(R.drawable.block2);
                            else sdg_block[i][j].setImageResource(R.drawable.block1);
                        }
                    }
                }
            }
        }


    }



    @Override
    public void onClick(View v) {




        switch(v.getId()){

            case R.id.solveBtn:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }

                AlertDialog.Builder confirmation_box_builder = new AlertDialog.Builder(S5.this);
                confirmation_box_builder.setCancelable(true);
                confirmation_box_builder.setTitle("Solve Sudoku");
                confirmation_box_builder.setMessage("Are you sure you want to view the solution?");
                confirmation_box_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                confirmation_box_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0 ; i<9 ; i++){
                            for(int j=0 ; j<9 ; j++){
                                sudokugrid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                                sudokugrid[i][j].setText(""+solved[i][j]);
                            }
                        }
                        solved_state = true;
                    }
                });
                AlertDialog confirmation_box = confirmation_box_builder.create();
                confirmation_box.show();
                sudokutimer.cancel();
                break;
            case R.id.hintBtn:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej == -1) break;
                sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                sudokugrid[activei][activej].setText(""+solved[activei][activej]);
                solving[activei][activej]=solved[activei][activej];
                check_solved();
                break;
            case R.id.menuBtn:
                Intent openFirstScreen = new Intent(getApplicationContext(), com.example.sudokusolver.MainActivity.class);
                startActivity(openFirstScreen);
                finish();
                break;
            case R.id.notesBtn:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                markingState= !markingState;
                if(markingState == true) {
                    notesBtn.setImageResource(R.drawable.notes_btn2);
                }
                else {
                    notesBtn.setImageResource(R.drawable.notes_btn1);
                }
                break;
            case R.id.eraseBtn:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activei!=-1 && activej!=-1) {
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                    sudokugrid[activei][activej].setText(" ");
                }
                break ;
            case  R.id.resetBtn:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                AlertDialog.Builder reset_box_builder = new AlertDialog.Builder(S5.this);
                reset_box_builder.setCancelable(true);
                reset_box_builder.setTitle("Reset Sudoku");
                reset_box_builder.setMessage("Are you sure you want reset the Sudoku?");
                reset_box_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                reset_box_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       for (int i=0 ; i<81 ; i++){
                           if(unsolved[i/9][i%9] == 0){
                               sudokugrid[i/9][i%9].setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                               sudokugrid[i/9][i%9].setText(" ");
                           }
                       }
                    }
                });
                AlertDialog reset_box = reset_box_builder.create();
                reset_box.show();
                break;
            case R.id.button00:
                if(unsolved[0][0] != 0) break ;
                activei=0;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button01:
                if(unsolved[0][1] != 0) break ;
                activei=0;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button02:
                if(unsolved[0][2] != 0) break ;
                activei=0;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button03:
                if(unsolved[0][3] != 0) break ;
                activei=0;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button04:
                if(unsolved[0][4] != 0) break ;
                activei=0;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button05:
                if(unsolved[0][5] != 0) break ;
                activei=0;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button06:
                if(unsolved[0][6] != 0) break ;
                activei=0;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button07:
                if(unsolved[0][7] != 0) break ;
                activei=0;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button08:
                if(unsolved[0][8] != 0) break ;
                activei=0;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.button10:
                if(unsolved[1][0] != 0) break ;
                activei=1;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button11:
                if(unsolved[1][1] != 0) break ;
                activei=1;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button12:
                if(unsolved[1][2] != 0) break ;
                activei=1;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button13:
                if(unsolved[1][3] != 0) break ;
                activei=1;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button14:
                if(unsolved[1][4] != 0) break ;
                activei=1;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button15:
                if(unsolved[1][5] != 0) break ;
                activei=1;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button16:
                if(unsolved[1][6] != 0) break ;
                activei=1;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button17:
                if(unsolved[1][7] != 0) break ;
                activei=1;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button18:
                if(unsolved[1][8] != 0) break ;
                activei=1;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.button20:
                if(unsolved[2][0] != 0) break ;
                activei=2;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button21:
                if(unsolved[2][1] != 0) break ;
                activei=2;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button22:
                if(unsolved[2][2] != 0) break ;
                activei=2;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button23:
                if(unsolved[2][3] != 0) break ;
                activei=2;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button24:
                if(unsolved[2][4] != 0) break ;
                activei=2;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button25:
                if(unsolved[2][5] != 0) break ;
                activei=2;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button26:
                if(unsolved[2][6] != 0) break ;
                activei=2;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button27:
                if(unsolved[2][7] != 0) break ;
                activei=2;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button28:
                if(unsolved[2][8] != 0) break ;
                activei=2;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.button30:
                if(unsolved[3][0] != 0) break ;
                activei=3;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button31:
                if(unsolved[3][1] != 0) break ;
                activei=3;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button32:
                if(unsolved[3][2] != 0) break ;
                activei=3;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button33:
                if(unsolved[3][3] != 0) break ;
                activei=3;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button34:
                if(unsolved[3][4] != 0) break ;
                activei=3;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button35:
                if(unsolved[3][5] != 0) break ;
                activei=3;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button36:
                if(unsolved[3][6] != 0) break ;
                activei=3;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button37:
                if(unsolved[3][7] != 0) break ;
                activei=3;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button38:
                if(unsolved[3][8] != 0) break ;
                activei=3;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.button40:
                if(unsolved[4][0] != 0) break ;
                activei=4;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button41:
                if(unsolved[4][1] != 0) break ;
                activei=4;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button42:
                if(unsolved[4][2] != 0) break ;
                activei=4;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button43:
                if(unsolved[4][3] != 0) break ;
                activei=4;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button44:
                if(unsolved[4][4] != 0) break ;
                activei=4;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button45:
                if(unsolved[4][5] != 0) break ;
                activei=4;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button46:
                if(unsolved[4][6] != 0) break ;
                activei=4;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button47:
                if(unsolved[4][7] != 0) break ;
                activei=4;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button48:
                if(unsolved[4][8] != 0) break ;
                activei=4;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.button50:
                if(unsolved[5][0] != 0) break ;
                activei=5;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button51:
                if(unsolved[5][1] != 0) break ;
                activei=5;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button52:
                if(unsolved[5][2] != 0) break ;
                activei=5;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button53:
                if(unsolved[5][3] != 0) break ;
                activei=5;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button54:
                if(unsolved[5][4] != 0) break ;
                activei=5;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button55:
                if(unsolved[5][5] != 0) break ;
                activei=5;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button56:
                if(unsolved[5][6] != 0) break ;
                activei=5;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button57:
                if(unsolved[5][7] != 0) break ;
                activei=5;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button58:
                if(unsolved[5][8] != 0) break ;
                activei=5;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.button60:
                if(unsolved[6][0] != 0) break ;
                activei=6;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button61:
                if(unsolved[6][1] != 0) break ;
                activei=6;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button62:
                if(unsolved[6][2] != 0) break ;
                activei=6;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button63:
                if(unsolved[6][3] != 0) break ;
                activei=6;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button64:
                if(unsolved[6][4] != 0) break ;
                activei=6;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button65:
                if(unsolved[6][5] != 0) break ;
                activei=6;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button66:
                if(unsolved[6][6] != 0) break ;
                activei=6;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button67:
                if(unsolved[6][7] != 0) break ;
                activei=6;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button68:
                if(unsolved[6][8] != 0) break ;
                activei=6;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.button70:
                if(unsolved[7][0] != 0) break ;
                activei=7;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button71:
                if(unsolved[7][1] != 0) break ;
                activei=7;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button72:
                if(unsolved[7][2] != 0) break ;
                activei=7;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button73:
                if(unsolved[7][3] != 0) break ;
                activei=7;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button74:
                if(unsolved[7][4] != 0) break ;
                activei=7;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button75:
                if(unsolved[7][5] != 0) break ;
                activei=7;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button76:
                if(unsolved[7][6] != 0) break ;
                activei=7;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button77:
                if(unsolved[7][7] != 0) break ;
                activei=7;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button78:
                if(unsolved[7][8] != 0) break ;
                activei=7;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.button80:
                if(unsolved[8][0] != 0) break ;
                activei=8;
                activej=0;
                GridColorChange(activei,activej);
                break;
            case R.id.button81:
                if(unsolved[8][1] != 0) break ;
                activei=8;
                activej=1;
                GridColorChange(activei,activej);
                break;
            case R.id.button82:
                if(unsolved[8][2] != 0) break ;
                activei=8;
                activej=2;
                GridColorChange(activei,activej);
                break;
            case R.id.button83:
                if(unsolved[8][3] != 0) break ;
                activei=8;
                activej=3;
                GridColorChange(activei,activej);
                break;
            case R.id.button84:
                if(unsolved[8][4] != 0) break ;
                activei=8;
                activej=4;
                GridColorChange(activei,activej);
                break;
            case R.id.button85:
                if(unsolved[8][5] != 0) break ;
                activei=8;
                activej=5;
                GridColorChange(activei,activej);
                break;
            case R.id.button86:
                if(unsolved[8][6] != 0) break ;
                activei=8;
                activej=6;
                GridColorChange(activei,activej);
                break;
            case R.id.button87:
                if(unsolved[8][7] != 0) break ;
                activei=8;
                activej=7;
                GridColorChange(activei,activej);
                break;
            case R.id.button88:
                if(unsolved[8][8] != 0) break ;
                activei=8;
                activej=8;
                GridColorChange(activei,activej);
                break;
            case R.id.digitButton1:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][1]==true) markings[activei][activej][1] = false;
                    else markings[activei][activej][1] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("1");
                }
                solving[activei][activej]=1;
                check_solved();
                break;
            case R.id.digitButton2:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][2]==true) markings[activei][activej][2] = false;
                    else markings[activei][activej][2] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("2");
                }
                solving[activei][activej]=2;
                check_solved();
                break;
            case R.id.digitButton3:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][3]==true) markings[activei][activej][3] = false;
                    else markings[activei][activej][3] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("3");
                }
                solving[activei][activej]=3;
                check_solved();
                break;
            case R.id.digitButton4:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][4]==true) markings[activei][activej][4] = false;
                    else markings[activei][activej][4] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("4");
                }
                solving[activei][activej]=4;
                check_solved();
                break;
            case R.id.digitButton5:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][5]==true) markings[activei][activej][5] = false;
                    else markings[activei][activej][5] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("5");
                }
                solving[activei][activej]=5;
                check_solved();
                break;
            case R.id.digitButton6:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][6]==true) markings[activei][activej][6] = false;
                    else markings[activei][activej][6] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("6");
                }
                solving[activei][activej]=6;
                check_solved();
                break;
            case R.id.digitButton7:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][7]==true) markings[activei][activej][7] = false;
                    else markings[activei][activej][7] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("7");
                }
                solving[activei][activej]=7;
                check_solved();
                break;
            case R.id.digitButton8:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][8]==true) markings[activei][activej][8] = false;
                    else markings[activei][activej][8] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("8");
                }
                solving[activei][activej]=8;
                check_solved();
                break;
            case R.id.digitButton9:
                if(solved_state){
                    Toast.makeText(getApplicationContext(),"Sudoku already solved",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(activej==-1) break ;
                if(markingState == true){
                    if(markings[activei][activej][9]==true) markings[activei][activej][9] = false;
                    else markings[activei][activej][9] = true ;
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); ;
                    sudokugrid[activei][activej].setText(GenerateMarkings(activei, activej)) ;
                }
                else{
                    for(int i=1;i<=9;i++) {
                        markings[activei][activej][i] = false ;
                    }
                    sudokugrid[activei][activej].setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
                    sudokugrid[activei][activej].setText("9");
                }
                solving[activei][activej]=9;
                check_solved();
                break;
        }
    }

    public void savedata(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, text);
        editor.putBoolean(RESU,resume);
        editor.apply();

    }

    public void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        resume = sharedPreferences.getBoolean(RESU,false);
    }
}