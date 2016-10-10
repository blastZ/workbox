package com.z.blast.workbox;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

/**
 * Created by Random on 16.6.14.
 */
public class GradienterActivity extends Activity implements SensorEventListener {
    GradienterView show;
    int MAX_ANGLE=30;
    SensorManager mSensorManager;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gradienter_main);
        show=(GradienterView)findViewById(R.id.show);
        mSensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);
    }
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    protected void onStop(){
        mSensorManager.unregisterListener(this);
        super.onStop();
    }
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }
    public void onSensorChanged(SensorEvent event){
        float[] values=event.values;
        int sensorType=event.sensor.getType();
        switch(sensorType){
            case Sensor.TYPE_ORIENTATION:
                float yAngle=values[1];
                float zAngle=values[2];
                int x=(show.back.getWidth()-show.bubble.getWidth())/2;
                int y=(show.back.getHeight()-show.bubble.getHeight())/2;
                if(Math.abs(zAngle)<=MAX_ANGLE){
                    int deltaX=(int)((show.back.getWidth()-show.bubble.getWidth())/2*zAngle/MAX_ANGLE);
                    x+=deltaX;
                }else if(zAngle>MAX_ANGLE){
                    x=0;
                }else{
                    x=show.back.getWidth()-show.bubble.getWidth();
                }
                if(Math.abs(yAngle)<=MAX_ANGLE){
                    int deltaY=(int)((show.back.getHeight()-show.bubble.getHeight())/2*yAngle/MAX_ANGLE);
                    y+=deltaY;
                }else if(yAngle>MAX_ANGLE){
                    y=show.back.getHeight()-show.bubble.getHeight();
                }else{
                    y=0;
                }
                if(isContain(x,y)){
                    show.bubbleX=x;
                    show.bubbleY=y;
                }
                show.postInvalidate();
                break;
        }
    }
    private boolean isContain(int x,int y){
        int bubbleCx=x+show.bubble.getWidth()/2;
        int bubbleCy=y+show.bubble.getHeight()/2;
        int backCx=show.back.getWidth()/2;
        int backCy=show.back.getHeight()/2;
        double distance=Math.sqrt((bubbleCx-backCx)*(bubbleCx-backCx)+(bubbleCy-backCy)*(bubbleCy-backCy));
        if(distance<(show.back.getWidth()-show.bubble.getWidth())/2){
            return true;
        }else{
            return false;
        }
    }
}
