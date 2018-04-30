package com.example.m3.mapapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class bulletinActivity extends AppCompatActivity {

    private Context mContext;
    private ListView mListView;
    private TextView titleTextView;
    
    private ArrayList<Bulletin> bulletinList;
    private ArrayList<Bulletin> searchedbulletins;

    BulletinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_board_layout);

        mContext = this;

        bulletinList = Bulletin.getBulletinsFromFile("posts.json", this);
        adapter = new BulletinAdapter(this, bulletinList);

        mListView = findViewById(R.id.bulletin_list_view);
        mListView.setAdapter(adapter);

    }

}

