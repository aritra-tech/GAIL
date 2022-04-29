package com.geekymusketeers.gail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Profile_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        getSupportActionBar().hide();
    }
}