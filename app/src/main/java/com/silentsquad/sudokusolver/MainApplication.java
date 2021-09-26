package com.silentsquad.sudokusolver;

import android.app.Application;
import android.media.MediaPlayer;

public class MainApplication extends Application {


    MediaPlayer ring;
    public boolean ring_start;

    @Override
    public void onCreate() {
        super.onCreate();
        ring = MediaPlayer.create(MainApplication.this,R.raw.ring);
        ring_start = true;
    }

}
