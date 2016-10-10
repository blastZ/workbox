package com.z.blast.workbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.baidu.mapapi.map.Stroke;

/**
 * Created by Random on 16.6.12.
 */
public class RulerText extends TextView {
    public RulerText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public RulerText(Context context) {
        super(context);
        init();
    }
    private void init(){
        setGravity(Gravity.BOTTOM);
    }
    public void draw(Canvas canvas){
        super.draw(canvas);
        float mmWidth=((float)getWidth())/10;
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setDither(true);
        for(int i=0;i<10;i++){
            if(i==0){
                paint.setColor(Color.RED);
                canvas.drawLine(i * mmWidth, 0, i * mmWidth, 3 * mmWidth, paint);
            }else{
                paint.setColor(Color.WHITE);
                canvas.drawLine(i*mmWidth,0,i*mmWidth,2*mmWidth,paint);
            }
        }
    }
}
