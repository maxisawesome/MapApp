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

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_post_layout);

        mContext = this;

        location = this.getIntent().getExtras().getString("location");

        submitButton = findViewById(R.id.submitButton);
        titleEdit = findViewById(R.id.newBulletinTitleEditText);
        postEdit = findViewById(R.id.newBulletinContentEditText);
        rGroup = findViewById(R.id.newBulletinRadioGroup);

        myDb = new DatabaseHelper(this);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Date currentTime = Calendar.getInstance().getTime();
                boolean isInserted = myDb.insertData("username placeholder", titleEdit.getText().toString(),
                        location, postEdit.getText().toString(),
                        currentTime.toString(), rGroup.getCheckedRadioButtonId());
                if(isInserted == true){
                    // I inserted correctly
                    Toast.makeText(newPostActivity.this, "Bulletin Added", Toast.LENGTH_LONG).show();
                    Intent movieDetailReturnIntent = new Intent();
                    //movieDetailReturnIntent.putExtra("position", position);
                    //movieDetailReturnIntent.putExtra("checkedRadioButton", checkedButton);
                    setResult(Activity.RESULT_OK, movieDetailReturnIntent);
                    finish();
                } else {
                    Toast.makeText(newPostActivity.this, "Bulletin Not Added - " +
                            "something went wrong :(", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
