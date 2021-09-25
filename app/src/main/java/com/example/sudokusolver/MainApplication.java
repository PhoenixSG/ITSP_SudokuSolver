package com.example.sudokusolver;

import android.app.Application;
import android.media.MediaPlayer;

public class MainApplication extends Application {

    MediaPlayer ring;

    @Override
    public void onCreate() {
        super.onCreate();
        ring = MediaPlayer.create(MainApplication.this,R.raw.ring);
    }
}
