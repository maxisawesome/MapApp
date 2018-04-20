package com.example.m3.mapapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class bulletinActivity extends AppCompatActivity {

    private Context mContext;
    private ListView mListView;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_board_layout);
    }

}

