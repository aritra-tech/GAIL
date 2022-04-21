package com.geekymusketeers.gail.authentication;

import static android.content.ContentValues.TAG;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.geekymusketeers.gail.R;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    FirebaseAuth Auth2;
    EditText email;
    Button resetpass;
   // ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Auth2 = FirebaseAuth.getInstance();
        email = findViewById(R.id.reset_email);
        resetpass = findViewById(R.id.reset_pass);

        resetpass.setOnClickListener(view -> { //forgetpass code starts here
            String Email = email.getText().toString().trim();
            if (Email.isEmpty()) {
                email.setError("Field can't be empty");
                email.requestFocus();

            } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                email.setError("Please enter a valid Email id");
                email.requestFocus();

            } else {
                Auth2.sendPasswordResetEmail(Email)
                        .addOnCompleteListener(task -> {
                            try {
                                if (task.isSuccessful())
                                    Toast.makeText(ForgotPassword.this, "Password Reset email sent!", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(ForgotPassword.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d(TAG,"Email sent");
                            }
                        });
            }
        });
    }
}