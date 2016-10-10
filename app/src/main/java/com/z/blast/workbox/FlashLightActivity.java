package com.z.blast.workbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Random on 16.6.12.
 */
public class FlashLightActivity extends Activity {
    ImageView openFlash;
    boolean isOpen;
    android.hardware.Camera camera;
    protected void onCreate(Bundle savedInstanceState) {
        isOpen=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashlight_main);
        openFlash = (ImageView) findViewById(R.id.openFlash);
        OpenListener openListener=new OpenListener();
        openFlash.setOnClickListener(openListener);
    }
    class OpenListener implements View.OnClickListener{
        public void onClick(View v){
            if(isOpen==false){
                camera= android.hardware.Camera.open();
                android.hardware.Camera.Parameters mParameters=camera.getParameters();
                mParameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                openFlash.setImageResource(R.drawable.openflash);
                camera.setParameters(mParameters);
                camera.startPreview();
                openFlash.setImageResource(R.drawable.openflash);
                isOpen=true;
            }else {
                camera.stopPreview();
                camera.release();
                openFlash.setImageResource(R.drawable.closeflash);
                isOpen=false;
            }
        }
    }

}
