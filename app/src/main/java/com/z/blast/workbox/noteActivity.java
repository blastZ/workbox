package com.z.blast.workbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Random on 16.6.15.
 */
public class noteActivity extends Activity{
    Button save,edit;
    MyEditText editText;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);
        preferences=getSharedPreferences("noteData", MODE_PRIVATE);
        editor=preferences.edit();
        editText=(MyEditText)findViewById(R.id.editText_edittext);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setText(preferences.getString("textData",null));
        save=(Button)findViewById(R.id.saveButton_edittext);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=editText.getText().toString();
                editor.putString("textData",data);
                editor.commit();
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);

            }
        });
        edit=(Button)findViewById(R.id.editButton_edittext);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
            }
        });
    }
}
