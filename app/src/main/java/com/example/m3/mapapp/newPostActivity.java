package com.example.m3.mapapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class newPostActivity extends AppCompatActivity {


    private Context mContext;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private RadioGroup rGroup;
    private Button submitButton;
    private EditText titleEdit;
    private EditText postEdit;
    private String location;
    private String username;
    private int type = -1;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_post_layout);

        mContext = this;

        location = this.getIntent().getExtras().getString("location");
        username = this.getIntent().getExtras().getString("username");
        submitButton = findViewById(R.id.submitButton);
        titleEdit = findViewById(R.id.newBulletinTitleEditText);
        postEdit = findViewById(R.id.newBulletinContentEditText);
        rGroup = findViewById(R.id.newBulletinRadioGroup);

        myDb = new DatabaseHelper(this);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("Debug here, poop");
                System.out.println(rGroup.getCheckedRadioButtonId());

                switch(rGroup.getCheckedRadioButtonId()) {
                    case R.id.radio_button_1:
                        type = 0;
                        break;
                    case R.id.radio_button_2:
                        type = 1;
                        break;
                    case R.id.radio_button_3:
                        type = 2;
                        break;
                    case R.id.radio_button_4:
                        type = 3;
                        break;
                    case R.id.radio_button_5:
                        type = 4;
                        break;

                }


                if(type == -1 || titleEdit.getText().toString().equals("") || postEdit.getText().toString().equals("")) {
                    Toast.makeText(newPostActivity.this, "Please fill in all information. ", Toast.LENGTH_LONG).show();
                } else {
                    Date currentTime = Calendar.getInstance().getTime();
                    boolean isInserted = myDb.insertData(username, titleEdit.getText().toString(),
                            location, postEdit.getText().toString(),
                            currentTime.toString(), type);
                    if (isInserted == true) {
                        // I inserted correctly
                        Toast.makeText(newPostActivity.this, "Bulletin Added", Toast.LENGTH_LONG).show();
                        Intent movieDetailReturnIntent = new Intent();
                        setResult(Activity.RESULT_OK, movieDetailReturnIntent);
                        finish();
                    } else {
                        Toast.makeText(newPostActivity.this, "Bulletin Not Added - " +
                                "something went wrong :(", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

}
