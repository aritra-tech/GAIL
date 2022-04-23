package com.geekymusketeers.gail.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.geekymusketeers.gail.R;

public class SignInScreen extends AppCompatActivity {

    TextView noAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        initialization();

        noAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInScreen.this, SignUpScreen.class));
            }
        });

    }

    private void initialization() {
        noAcc = findViewById(R.id.no_acc);
    }
}