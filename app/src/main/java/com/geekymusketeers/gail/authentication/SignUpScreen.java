package com.geekymusketeers.gail.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geekymusketeers.gail.R;

public class SignUpScreen extends AppCompatActivity {

    private Button next;
    private EditText name, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        initialization();

        validate();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpScreen.this, SignUpFinal.class);
                intent.putExtra("NAME", name.getText().toString());
                intent.putExtra("ID", id.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void validate() {

        if (name.getText().toString().isEmpty()) {
            name.setError("Field can't be empty");
            name.requestFocus();
            return;
        }

        if (id.getText().toString().isEmpty()) {
            id.setError("Field can't be empty");
            id.requestFocus();
            return;
        }
    }

    private void initialization() {
        next = findViewById(R.id.next);
        name = findViewById(R.id.name);
        id = findViewById(R.id.emp_id);
    }
}