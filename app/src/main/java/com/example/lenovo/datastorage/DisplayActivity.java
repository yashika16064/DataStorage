package com.example.lenovo.datastorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        SharedPreferences sharedPrefers = getSharedPreferences("prefers",MODE_PRIVATE);
        String roll=sharedPrefers.getString("myroll","no value");
        String name=sharedPrefers.getString("myname","no value");
        String cgpa=sharedPrefers.getString("mycgpa","no value");

        TextView textView4=(TextView) findViewById(R.id.textView4);
        TextView textView5=(TextView) findViewById(R.id.textView5);
        TextView textView6=(TextView) findViewById(R.id.textView6);

        textView4.setText(roll);
        textView5.setText(name);
        textView6.setText(cgpa);
    }
}
