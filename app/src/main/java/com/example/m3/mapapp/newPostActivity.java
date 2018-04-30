package com.example.m3.mapapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class newPostActivity extends AppCompatActivity {


    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_post_layout);

    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_button_1:
                if (checked)
                    checkedButton = radioButton1.getText().toString();
                break;
            case R.id.radio_button_2:
                if (checked)
                    checkedButton = radioButton2.getText().toString();
                break;
            case R.id.radio_button_3:
                if (checked)
                    checkedButton = radioButton3.getText().toString();
                break;

        }
    }
}
