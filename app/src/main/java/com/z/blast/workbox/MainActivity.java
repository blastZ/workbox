package com.z.blast.workbox;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;

import java.util.ArrayList;

/**
 * Created by Random on 16.6.12.
 */
public class MainActivity extends Activity{
    GridView grid;
    int[] imageIds=new int[]{
            R.drawable.calendar_128,
            R.drawable.compass_128,
            R.drawable.flashlight_128,
            R.drawable.note_128,
            R.drawable.ruler_128,
            R.drawable.map_128,
            R.drawable.calculator_128,
            R.drawable.weather_128,
            R.drawable.gradienter_128,
            R.drawable.alarmclock_128
    };
    String[] names=new String[]{
            "Calendar","Compass", "FlashLight",
            "Note", "Ruler", "Map",
            "Calculator", "Weather","MyGradienter",
            "MyAlarmClock",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        grid=(GridView)findViewById(R.id.grid01);
        MyAdapter myAdapter=new MyAdapter();
        grid.setAdapter(myAdapter);
        MyClickListener myClickListener=new MyClickListener();
        grid.setOnItemClickListener(myClickListener);
    }
    class MyClickListener implements GridView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
            if(position==0){
                intent=new Intent(MainActivity.this,CalendarActivity.class);
                MainActivity.this.startActivity(intent);
            }else if(position==1){
                intent=new Intent(MainActivity.this,CompassActivity.class);
                MainActivity.this.startActivity(intent);
            }else if(position==2){
                intent=new Intent(MainActivity.this,FlashLightActivity.class);
                MainActivity.this.startActivity(intent);
            }else if(position==3){
                intent=new Intent(MainActivity.this,noteActivity.class);
                MainActivity.this.startActivity(intent);
            }else if(position==4){
                intent=new Intent(MainActivity.this,RulerActivity.class);
                MainActivity.this.startActivity(intent);
            }else if(position==5){
                intent=new Intent(MainActivity.this,MapActivity.class);
                MainActivity.this.startActivity(intent);
            }else if(position==6){
                intent=new Intent(MainActivity.this,Calculator.class);
                MainActivity.this.startActivity(intent);
            }else if(position==7){
                intent=new Intent(MainActivity.this,WeatherActivity.class);
                MainActivity.this.startActivity(intent);
            }else if(position==8){
                intent=new Intent(MainActivity.this,GradienterActivity.class);
                MainActivity.this.startActivity(intent);
            }else if(position==9){
                intent=new Intent(MainActivity.this,AlarmActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }
    }
    class MyAdapter extends BaseAdapter{
        public int getCount(){
            return 10;
        }
        public Object getItem(int position){
            return null;
        }
        public long getItemId(int position){
            return position;
        }
        public View getView(int position,View convertView,ViewGroup parent){
            LinearLayout line=new LinearLayout(MainActivity.this);
            line.setOrientation(LinearLayout.VERTICAL);
            ImageView imageView=new ImageView(MainActivity.this);
            TextView textView=new TextView(MainActivity.this);
            textView.setText(names[position]);
            imageView.setImageResource(imageIds[position]);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(128, 128));
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            textView.setLayoutParams(layoutParams);
            textView.setId(position);
            line.setGravity(0x11);
            line.addView(imageView);
            line.addView(textView);
            return line;
        }
    }
}
