package com.coderbd.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText message;
    SeekBar seekBar;
    float fontSize;
    String fontColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String test_info;

        message = findViewById(R.id.message);
        seekBar = findViewById(R.id.seek_bar);

        //each time user open the apps we check that there a previous setup is available or not if available lode the related data
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        test_info = sharedPreferences.getString(getString(R.string.app_name), "");
        message.setText(test_info);
        fontSize = sharedPreferences.getFloat(getString(R.string.app_name), 30);
        fontColor = sharedPreferences.getString(getString(R.string.app_name), "");
        message.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);

        //checked font size and set value
        if (fontSize == 30){
            seekBar.setProgress(0);
        }else{

            seekBar.setProgress((int) fontSize);
        }

        //check font color and set value
        if(fontColor.equals("RED")){

            message.setTextColor(Color.parseColor("#FF0000"));

        }else if(fontColor.equals("GREEN")){

            message.setTextColor(Color.parseColor("#00FF00"));

        }else {
            message.setTextColor(Color.parseColor("#0000FF"));
        }


        //change front size based on seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //invoke when progress is change
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                message.setTextSize(TypedValue.COMPLEX_UNIT_PX, progress);
            }

            //invoke when user dragging the seekBar
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //invoke when user finished the dragging
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                fontSize = message.getTextSize();
            }
        });
    }

    //color change
    public void changeColor(View view) {
        switch (view.getId()){
            case R.id.id_read_color:
                message.setTextColor(Color.parseColor("#FF0000"));
                fontColor = "RED";
                break;
            case R.id.id_green_color:
                message.setTextColor(Color.parseColor("#00FF00"));
                fontColor = "GREEN";
                break;

            case R.id.id_blue_color:
                message.setTextColor(Color.parseColor("#0000FF"));
                fontColor = "BLUE";
                break;
        }
    }

    //saveSetting
    public void saveSetting(View view) {
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(getString(R.string.app_name), fontSize);
        editor.putString(getString(R.string.app_name), fontColor);
        editor.putString(getString(R.string.app_name), message.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved!!!", Toast.LENGTH_SHORT).show();
    }

    //clear setting (clear all the value saved in SharedPreferences)
    public void clearSetting(View view) {
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "Saved!!!", Toast.LENGTH_SHORT).show();
    }