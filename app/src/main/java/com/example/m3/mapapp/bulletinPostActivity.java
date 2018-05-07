package com.example.m3.mapapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class bulletinPostActivity  extends AppCompatActivity {

    private TextView userTextView;
    private TextView titleTextView;
    private TextView timeTextView;
    private TextView locationTextView;
    private TextView contentTextView;
    private ImageView iconImageView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_full_view);

        userTextView = findViewById(R.id.post_detail_username);
        titleTextView = findViewById(R.id.post_detail_title);
        timeTextView = findViewById(R.id.post_detail_time);
        locationTextView = findViewById(R.id.post_detail_location);
        contentTextView = findViewById(R.id.post_detail_content);
        iconImageView = findViewById(R.id.postPicture);
        
        userTextView.setText(this.getIntent().getExtras().getString("username"));
        titleTextView.setText(this.getIntent().getExtras().getString("title"));
        timeTextView.setText(this.getIntent().getExtras().getString("time"));
        locationTextView.setText(this.getIntent().getExtras().getString("location"));
        contentTextView.setText(this.getIntent().getExtras().getString("content"));
        int type = this.getIntent().getExtras().getInt("type");

        switch (type){
            case 0:
                iconImageView.setImageResource(R.drawable.have_icon);
                break;
            case 1:
                iconImageView.setImageResource(R.drawable.want_icon);
                break;
            case 2:
                iconImageView.setImageResource(R.drawable.announcement_icon);
                break;
            case 3:
                iconImageView.setImageResource(R.drawable.statement_icon);
                break;
            case 4:
                iconImageView.setImageResource(R.drawable.other_icon);
                break;
        }
    }
}

