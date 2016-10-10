package com.z.blast.workbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.baidu.mapapi.map.Stroke;

/**
 * Created by Random on 16.6.15.
 */
public class MyEditText extends EditText{
    public int lineColor;
    public float lineWidth;
    public MyEditText(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
        lineColor= Color.BLACK;
        lineWidth=0.5f;
    }
    public MyEditText(Context context,int color,float width){
        super(context);
        lineColor=color;
        lineWidth=width;
    }
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineWidth);
        paint.setAntiAlias(true);

        int padL=this.getPaddingLeft();
        int padR=this.getPaddingRight();
        int padT=this.getPaddingTop();
        int lines=this.getLineCount();
        float size=this.getTextSize();
        float baseTop=padT+size/6;//从上向下第一条线的位置
        float gap=this.getLineHeight();

        for(int i=1;i<=lines;i++){
            canvas.drawLine(padL,baseTop+gap*i,this.getWidth()-padR,baseTop+gap*i,paint);
        }
    }

}
