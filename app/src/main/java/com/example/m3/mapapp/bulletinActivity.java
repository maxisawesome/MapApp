package com.example.m3.mapapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class bulletinActivity extends AppCompatActivity {

    private Context mContext;
    private ListView mListView;
    private TextView titleTextView;
    private FloatingActionButton newPost;
    private String location;

    DatabaseHelper myDb;

    private ArrayList<Bulletin> bulletinList;
    private ArrayList<Bulletin> searchedbulletins;

    BulletinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_board_layout);

        mContext = this;

        location = this.getIntent().getExtras().getString("location");

        bulletinList = Bulletin.getBulletinsFromFile("posts.json", this);
        adapter = new BulletinAdapter(this, bulletinList);

        titleTextView = findViewById(R.id.bulletinBoardLocationTextView);
        titleTextView.setText("Bulletins at " + location);


        mListView = findViewById(R.id.bulletin_list_view);
        mListView.setAdapter(adapter);

        newPost = findViewById(R.id.newPostFAB);

        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, newPostActivity.class);
                i.putExtra("location", location);
                startActivityForResult(i,1);
            }
        });


    }

}

