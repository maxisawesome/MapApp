package com.example.m3.mapapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class bulletinActivity extends AppCompatActivity {

    private Context mContext;
    private ListView mListView;
    private TextView titleTextView;
    private FloatingActionButton newPost;
    private String location;
    private String username;

    DatabaseHelper myDb;

    private ArrayList<Bulletin> bulletinList;
    //private ArrayList<Bulletin> curLocBulletins;

    BulletinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_board_layout);

        mContext = this;
        myDb = new DatabaseHelper(this);


        location = this.getIntent().getExtras().getString("location");
        username = this.getIntent().getExtras().getString("username");

        bulletinList = Bulletin.getBulletinsFromDatabase(location, myDb, this);
        ArrayList<Bulletin> curLocBulletins = new ArrayList<>();
        //There's no data, so use the dummy data
        if(bulletinList.size() == 0){
            System.out.println("loading from file");
            bulletinList = Bulletin.getBulletinsFromFile("posts.json", this);
            System.out.println(bulletinList.size());
            for(int i = 0; i < bulletinList.size();  i++){
                boolean isInserted = myDb.insertData(bulletinList.get(i).username, bulletinList.get(i).title,
                        bulletinList.get(i).location, bulletinList.get(i).content,
                        bulletinList.get(i).time, bulletinList.get(i).type);
                if(!isInserted){
                    Toast.makeText(bulletinActivity.this, "Bulletins couldn't load - " +
                            "something went wrong.", Toast.LENGTH_LONG).show();
                }

            }
            for(int i = 0; i < bulletinList.size();  i++) {
                if(bulletinList.get(i).location.equals(location)){
                    curLocBulletins.add(bulletinList.get(i));
                }
            }
            bulletinList = curLocBulletins;
        }

        adapter = new BulletinAdapter(this, bulletinList);

        titleTextView = findViewById(R.id.bulletinBoardLocationTextView);
        titleTextView.setText(location);
        titleTextView.setTextSize(24);


        mListView = findViewById(R.id.bulletin_list_view);
        mListView.setAdapter(adapter);

        newPost = findViewById(R.id.newPostFAB);

        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, newPostActivity.class);
                i.putExtra("location", location);
                i.putExtra("username", username);
                startActivityForResult(i,1);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(mContext, bulletinPostActivity.class);
                Bulletin tappedBulletin = bulletinList.get(position);
                i.putExtra("content", tappedBulletin.content);
                i.putExtra("username", tappedBulletin.username);
                i.putExtra("location", tappedBulletin.location);
                i.putExtra("time", tappedBulletin.time);
                i.putExtra("title", tappedBulletin.title);
                i.putExtra("type", tappedBulletin.type);
                startActivity(i);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

                System.out.println(bulletinList.size());
                bulletinList = Bulletin.getBulletinsFromDatabase(location, myDb, this);
                mListView.setAdapter( new BulletinAdapter(this, bulletinList));

                System.out.println(bulletinList.size());
            }
        }
    }

}

