package com.geekymusketeers.gail.authentication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.geekymusketeers.gail.R;
import com.geekymusketeers.gail.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class SignUpFinal extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button create;
    EditText email, pass, confPass, designation, officeAdd, contact;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_final);

        initialization();


        Intent intent = getIntent();
        String sName = intent.getStringExtra("NAME");
        String sID = intent.getStringExtra("ID");
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sEmail = email.getText().toString();
                String sPass = pass.getText().toString();
                String sCPass = confPass.getText().toString();
                String sDes = designation.getText().toString();
                String sCon = contact.getText().toString();
                String sOAdd = officeAdd.getText().toString();
                String Base64 = BitMapToString(bmp);

                validate(sEmail, sPass, sCPass, sDes, sOAdd);

              //  Toast.makeText(SignUpFinal.this, Base64, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(SignUpFinal.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Users users = new Users(sName, sEmail, sID, sCon, "Male", sDes, sOAdd, Base64);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Log.d(TAG, "createUserWithEmail:success");
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                startActivity(new Intent(SignUpFinal.this, SignInScreen.class));
                                                // updateUI(user);
                                                Toast.makeText(SignUpFinal.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                                Toast.makeText(SignUpFinal.this, "User data failed.", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpFinal.this, "Authentication failed"+task.getException(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                  //  updateUI(null);
                                }
                            }
                        });
            }
        });

    }

    private void validate(String sEmail, String sPass, String sConfPass, String sDes, String sOfficeAdd) {

        if (sEmail.isEmpty()) {
            email.setError("Field can't be empty");
            email.requestFocus();
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            email.setError("Please enter a valid email address");
            email.requestFocus();
            return;
        }
        else if (sPass.isEmpty()) {
            pass.setError("Field can't be empty");
            pass.requestFocus();
            return;
        }
        else if (sPass.length() < 6) {
            pass.setError("Password must be at least 6 characters");
            pass.requestFocus();
            return;
        }
        else if (sConfPass.isEmpty()) {
            confPass.setError("Field can't be empty");
            confPass.requestFocus();
            return;
        }
        else if (!sConfPass.equals(sPass)) {
            confPass.setError("Password did not match");
            confPass.requestFocus();
            return;
        }

        else if (sOfficeAdd.isEmpty()) {
            officeAdd.setError("Field can't be empty");
            officeAdd.requestFocus();
            return;
        }

    }

    private void initialization() {
        mAuth = FirebaseAuth.getInstance();
        create = findViewById(R.id.create);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        confPass = findViewById(R.id.conf_pass);
        designation = findViewById(R.id.designation);
        officeAdd = findViewById(R.id.office_add);
        contact = findViewById(R.id.contact);
        progressBar = findViewById(R.id.progressBar6);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            reload();
//        }
//    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

//    public Bitmap StringToBitMap(String encodedString){
//        try {
//            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
//            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            return bitmap;
//        } catch(Exception e) {
//            e.getMessage();
//            return null;
//        }
//    }
}