package com.z.blast.workbox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

/**
 * Created by Random on 16.6.15.
 */
public class CalendarActivity extends Activity {
    TextView textView,textView2;
    CalendarView calendarView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        textView=(TextView)findViewById(R.id.dayText_calendar);
        textView2=(TextView)findViewById(R.id.chooseDay_calendar);
        calendarView=(CalendarView)findViewById(R.id.calendar);
        long nowTime=calendarView.getDate();
        SimpleDateFormat f=new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String time=f.format(nowTime);
        textView.setText(time);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat f=new SimpleDateFormat("yyyy年MM月dd日");
                String chooseTime=f.format(calendarView.getDate());
                textView2.setText(chooseTime);
            }
        });
    }
}
