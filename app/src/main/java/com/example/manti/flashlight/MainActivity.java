package com.example.manti.flashlight;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    Camera camera;
    Camera.Parameters params;
    boolean isb;
    ToggleButton buton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buton = (ToggleButton) findViewById(R.id.buton);
        isb = false;

        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            camera = Camera.open();
            params =  camera.getParameters();
        }

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isb)
                {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(params);
                    camera.startPreview();
                    isb = true;
                }
                else
                {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(params);
                    camera.stopPreview();
                    isb = false;
                }
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isb)
        {
            camera.release();
            camera = null;
        }
    }
}
