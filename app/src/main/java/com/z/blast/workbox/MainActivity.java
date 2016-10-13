package com.z.blast.workbox;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Random on 16.6.12.
 */
public class MainActivity extends Activity{
    private GridView grid;
    private MyAdapter myAdapter;
    private MyClickListener myClickListener;
    int[] imageIds=new int[]{
            R.mipmap.calendar_128, R.mipmap.compass_128,
            R.mipmap.flashlight_128, R.mipmap.note_128,
            R.mipmap.ruler_128,R.mipmap.map_128,
            R.mipmap.calculator_128, R.mipmap.weather_128,
            R.mipmap.gradienter_128, R.mipmap.alarmclock_128
    };
    String[] names=new String[]{
            "Calendar","Compass", "FlashLight",
            "Note", "Ruler", "Map",
            "Calculator", "Weather","Gradienter",
            "AlarmClock",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    class MyClickListener implements GridView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
            Class targetClass=null;
            switch(position){
                case 0:targetClass=CalendarActivity.class;break;
                case 1:targetClass=CompassActivity.class;break;
                case 2:targetClass=FlashLightActivity.class;break;
                case 3:targetClass=noteActivity.class;break;
                case 4:targetClass=RulerActivity.class;break;
                case 5:targetClass=MapActivity.class;break;
                case 6:targetClass=Calculator.class;break;
                case 7:targetClass=WeatherActivity.class;break;
                case 8:targetClass=GradienterActivity.class;break;
                case 9:targetClass=AlarmActivity.class;break;

            }
            intent=new Intent(MainActivity.this,targetClass);
            startActivity(intent);
        }
    }
    class MyAdapter extends BaseAdapter{
        ViewHolder viewHolder;
        LayoutInflater inflater;
        public int getCount(){
            return 10;
        }
        public Object getItem(int position){
            return null;
        }
        public long getItemId(int position){
            return position;
        }
        public View getView(int position,View view,ViewGroup parent){
            inflater=LayoutInflater.from(MainActivity.this);
              if(view==null){
                  view=inflater.inflate(R.layout.griditem,null);
                  viewHolder=new ViewHolder();
                  viewHolder.imageView=(ImageView)view.findViewById(R.id.griditem_imageView);
                  viewHolder.textView=(TextView)view.findViewById(R.id.griditem_textView);
                  view.setTag(viewHolder);
              }else{
                  viewHolder=(ViewHolder)view.getTag();
              }
            viewHolder.imageView.setImageResource(imageIds[position]);
            viewHolder.textView.setText(names[position]);
            return view;
        }
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
    public void init(){
        SDKInitializer.initialize(getApplicationContext());
        grid=(GridView)findViewById(R.id.grid01);
        myAdapter=new MyAdapter();
        grid.setAdapter(myAdapter);
        myClickListener=new MyClickListener();
        grid.setOnItemClickListener(myClickListener);
    }
}
