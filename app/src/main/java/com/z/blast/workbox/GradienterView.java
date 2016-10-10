package com.z.blast.workbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Random on 16.6.14.
 */
public class GradienterView extends View {
    Bitmap back;
    Bitmap bubble;
    int bubbleX,bubbleY;
    public GradienterView(Context context) {
        super(context);
    }

    public GradienterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display=wm.getDefaultDisplay();
        DisplayMetrics metrics=new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth=metrics.widthPixels;
        int screenHeight=metrics.heightPixels;
        back=Bitmap.createBitmap(screenWidth,screenHeight,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(back);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Shader shader=new LinearGradient(0,screenWidth,
                screenWidth*0.8f,screenWidth*0.2f,
                Color.argb(255, 170, 98, 39),Color.argb(255,223,165,92),Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(screenWidth / 2, screenHeight/2, screenWidth / 2, paint);
        Paint paint2=new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5);
        paint2.setColor(Color.argb(255,252,200,59));
        canvas.drawCircle(screenWidth / 2, screenHeight / 2, screenWidth / 2, paint2);
        canvas.drawLine(0, screenHeight / 2, screenWidth, screenHeight / 2, paint2);
        canvas.drawLine(screenWidth / 2, screenHeight/2-screenWidth/2, screenWidth / 2, screenHeight/2+screenWidth/2, paint2);
        paint2.setStrokeWidth(10);
        paint2.setColor(Color.RED);
        canvas.drawLine(screenWidth / 2 - 30, screenHeight / 2, screenWidth / 2 + 30, screenHeight / 2, paint2);
        canvas.drawLine(screenWidth / 2, screenHeight / 2 - 30, screenWidth / 2, screenHeight / 2 + 30, paint2);
        bubble= BitmapFactory.decodeResource(getResources(), R.drawable.bubble);
    }
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(back,0,0,null);
        canvas.drawBitmap(bubble,bubbleX,bubbleY,null);
    }
}
