package com.silentsquad.sudokusolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class S4 extends AppCompatActivity {

    String path_of_latest_image="";
    //String solvedsudoku = "284375196739816254651942378476128539312594687598637412143769825965283741827451963";
    //String unsolvedsudoku = "020070107009800004051902000070008039000094080500030402043000800960080000007000903";

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

    private int REQUEST_CODE_PERMISSIONS = 101;
    private String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    TextureView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s4);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        tv = (TextureView) findViewById(R.id.textureView);
        Button gotomain=(Button)findViewById(R.id.gotomainbutton) ;
        gotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!path_of_latest_image.equals("")) {
                    Intent bufferactivity = new Intent(getApplicationContext(), com.silentsquad.sudokusolver.buffer.class);
                    bufferactivity.putExtra("imagepath", path_of_latest_image);
                    startActivity(bufferactivity);
                    finish();
                }
            }
        });
//        getSupportActionBar().hide();

        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }


    private void startCamera() {
        CameraX.unbindAll();

        Rational aspectRatio = new Rational(tv.getWidth(), tv.getHeight());
        Size screen = new Size(tv.getWidth(), tv.getHeight());

        PreviewConfig pConfig = new PreviewConfig.Builder().setTargetAspectRatio(aspectRatio).setTargetResolution(screen).build();
        Preview preview = new Preview(pConfig);

        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
            @Override
            public void onUpdated(Preview.PreviewOutput output) {
                ViewGroup parent = (ViewGroup) tv.getParent();
                parent.removeView(tv);
                parent.addView(tv);

                tv.setSurfaceTexture(output.getSurfaceTexture());
                updateTransform();

            }
        });
        /**
         Unable to capture image:
         Reasons: The onImageSaved.. is not default. (Most Prolly)(Nonnull problems)
         File location(Prolly Not)
         **/
        ImageCaptureConfig imageCaptureConfig = new ImageCaptureConfig.Builder().setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY).setTargetRotation(getWindowManager().getDefaultDisplay().getRotation()).build();
        final ImageCapture imgCap = new ImageCapture(imageCaptureConfig);
        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                path_of_latest_image = ""+getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/" +System.currentTimeMillis()+".png";

                File file = new File(path_of_latest_image);






//                imgCap.takePicture(file, new ImageCapture.OnImageSavedListener() {
//                    @Override
//                    public void onImageSaved(@NonNull  File file) {
//                        String msg= "Pic cap at" + file.getAbsolutePath() ;
//                        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull  ImageCapture.UseCaseError useCaseError, @NonNull  String message, @Nullable  Throwable cause) {
//                    String msg =" Pic cap failed: "+ message;
//                    Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
//
//                    if (cause!= null){
//                          cause.printStackTrace();
//                      }
//                    }
//                });

                imgCap.takePicture(file, new ImageCapture.OnImageSavedListener() {
                    @Override
                    public void onImageSaved(@NonNull @org.jetbrains.annotations.NotNull File file) {
                        String msg = "Image Stored as" + file.getAbsolutePath();
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull @org.jetbrains.annotations.NotNull ImageCapture.UseCaseError useCaseError, @NonNull @org.jetbrains.annotations.NotNull String message, @Nullable @org.jetbrains.annotations.Nullable Throwable cause) {
                        String msg = "Capture failed " + message;
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

                        if (cause != null) {
                            cause.printStackTrace();
                        }
                    }
                });


            }
        });
        CameraX.bindToLifecycle(this, preview, imgCap);
    }

    private void updateTransform() {
        Matrix mx = new Matrix();
        float w = tv.getMeasuredWidth();
        float h = tv.getMeasuredHeight();

        float cx = w / 2f;
        float cy = h / 2f;

        int rotationDgr = 0;
        int rotation = (int) tv.getRotation();

        switch (rotation) {
            case Surface.ROTATION_0:
                rotationDgr = 0;
                break;
            case Surface.ROTATION_90:
                rotationDgr = 90;
                break;
            case Surface.ROTATION_180:
                rotationDgr = 180;
                break;
            case Surface.ROTATION_270:
                rotationDgr = 270;
                break;
            default:
                break;
        }
        mx.postRotate((float) rotationDgr, cx, cy);
        tv.setTransform(mx);


    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;


    }
}