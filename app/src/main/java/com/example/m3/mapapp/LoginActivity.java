package com.example.m3.mapapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Context mContext;

    private Button mLoginButton;
    private Button mSignUpButton;

    private EditText usernameEdit;

    private TextView titleTextView;
    private TextView sloganTextView;
    private TextView usernameTextView;
    private TextView passwordTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // get this context
        mContext = this;

        //find the edit text view from the layout
        //save it to the variable username
        usernameEdit = findViewById(R.id.loginUsernameEditText);

        mLoginButton = findViewById(R.id.loginButton);
        mSignUpButton = findViewById(R.id.loginSignUpButton);

        //how to start 2nd activity when the login button is clicked?
        mLoginButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchActivity();
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does nothign right now
            }
        });

    }

    private void launchActivity(){
        // 1. intent with from and to
        // 2. add data to the intent
        // 3. start activity with the intent
        Intent detailIntent = new Intent(mContext, MapsActivity.class);
        detailIntent.putExtra("username", usernameEdit.getText());
        startActivityForResult(detailIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){ //SECOND ACTIVITY IS SENDING DATA

                //This stuff had info from the last app, but now it's empty
            }
        }

    }
}
