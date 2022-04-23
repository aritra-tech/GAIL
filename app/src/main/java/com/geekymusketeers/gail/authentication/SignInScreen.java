package com.geekymusketeers.gail.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geekymusketeers.gail.HomeScreen;
import com.geekymusketeers.gail.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInScreen extends AppCompatActivity {

    TextView noAcc,frgtpass;
    Button login;
    EditText email, pass;
    CheckBox rememberpass;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;


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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginacc();
            }
        });
        frgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
            }
        });

    }

    private void loginacc() {
        String emailtext = email.getText().toString().trim();
        String passtext = pass.getText().toString().trim();

        if (emailtext.isEmpty()){
            email.setError("Field can't be empty");
            email.requestFocus();
            return;
        }
        else if (passtext.isEmpty()){
            pass.setError("Field can't be empty");
            pass.requestFocus();
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()){
            email.setError("Please enter a valid email address");
            email.requestFocus();
            return;
        }
        else if (passtext.length()<6){
            pass.setError("Password must be at least 6 characters");
            pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(emailtext,passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        progressBar.setVisibility(View.GONE);
                        Intent intent2 = new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(intent2);
                        finishAffinity();
                    }
                    else{
                        progressBar.setVisibility(View.GONE);
                        user.sendEmailVerification();
                        Toast.makeText(getApplicationContext(), "Check your email to verify your account and Login again", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Failed to Login! Please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initialization() {
        mAuth = FirebaseAuth.getInstance();
        noAcc = findViewById(R.id.no_acc);
        login = findViewById(R.id.login);
        frgtpass = findViewById(R.id.forgetpass);
        email = findViewById(R.id.email_login);
        pass = findViewById(R.id.password_login);
        rememberpass = findViewById(R.id.checkBox);
        progressBar = findViewById(R.id.progressBar);
    }
}