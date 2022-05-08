package com.geekymusketeers.gail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.geekymusketeers.gail.authentication.SignInScreen;
import com.geekymusketeers.gail.authentication.SignUpFinal;
import com.geekymusketeers.gail.authentication.SignUpScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeScreen extends AppCompatActivity {

//    code starts here
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    ImageView clockInOut;
    Button Logout;
    TextView DateDis;
    TextClock clock;
    Calendar calendar;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
//        getSupportActionBar().hide();

        Initialization();

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(HomeScreen.this, SignInScreen.class);
                startActivity(i);
                finish();
            }
        });

        clockInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, clock.getText().toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEEE, MMM d");
        String date = dateFormat.format(calendar.getTime());
        DateDis.setText(date);


//        Fetch();
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reference.child(userID).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String st = snapshot.child("base64").getValue().toString();
//                        Bitmap temp = StringToBitMap(st);
//                        img.setImageBitmap(StringToBitMap(st));
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        });
    }

    private void Initialization() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        Logout = findViewById(R.id.logout);
        DateDis = findViewById(R.id.text_view_date);
        clock = findViewById(R.id.textClock);
        clockInOut = findViewById(R.id.clock_inout);
    }

//    private void Fetch() {
//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Users userprofile = snapshot.getValue(Users.class);
//                Intent i = new Intent(HomeScreen.this, HomeScreen.class);
//                if (userprofile != null){
//                    String fullname = userprofile.name;
//                    String base64 = userprofile.base64;
//                    i.putExtra("imagefetch",base64);
//                    String contact = userprofile.contact;
//                    Toast.makeText(getApplicationContext(), "Welcome "+fullname, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    public Bitmap StringToBitMap(String encodedString) {
//        try {
//            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            return bitmap;
//        } catch (Exception e) {
//            e.getMessage();
//            return null;
//        }
//    }

}
