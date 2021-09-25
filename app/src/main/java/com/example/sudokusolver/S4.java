package com.example.sudokusolver;
import android.content.Intent;
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

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class S4 extends AppCompatActivity {

    private int REQUEST_CODE_PERMISSIONS = 101;
    private String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    TextureView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s4);


        tv = (TextureView) findViewById(R.id.textureView);
        Button gotomain=(Button)findViewById(R.id.gotomainbutton) ;
        gotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomain=new Intent(getApplicationContext(), com.example.sudokusolver.S5.class);
                startActivity(gotomain);
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
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/" +System.currentTimeMillis()+".png");
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
                        String msg = "Pic cap at" + file.getAbsolutePath();
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull @org.jetbrains.annotations.NotNull ImageCapture.UseCaseError useCaseError, @NonNull @org.jetbrains.annotations.NotNull String message, @Nullable @org.jetbrains.annotations.Nullable Throwable cause) {
                        String msg = " Pic cap failed: " + message;
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