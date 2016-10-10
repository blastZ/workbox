package com.z.blast.workbox;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Random on 16.6.14.
 */
public class CompassActivity extends Activity implements SensorEventListener {
    ImageView imageView;
    float currentDegree=0f;
    TextView text;
    SensorManager sensorManager;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass_main);
        imageView=(ImageView)findViewById(R.id.image_compass);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        text=(TextView)findViewById(R.id.text_compass);
    }
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }
    protected void onPause(){
        sensorManager.unregisterListener(this);
        super.onPause();
    }
    protected void onStop(){
        sensorManager.unregisterListener(this);
        super.onStop();
    }
    public void onSensorChanged(SensorEvent event){
        int sensorType=event.sensor.getType();
        switch(sensorType){
            case Sensor.TYPE_ORIENTATION:
                float degree=event.values[0];
                RotateAnimation ra=new RotateAnimation(currentDegree,
                        -degree, Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f);
                ra.setDuration(200);
                imageView.startAnimation(ra);
                currentDegree=-degree;
                text.setText(String.valueOf(degree));
                break;
        }
    }
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }
}