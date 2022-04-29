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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class HomeScreen extends AppCompatActivity {
    //code starts here
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    ImageView img;
    Button btn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
//        getSupportActionBar().hide();

        Thread t = new Thread(){
            @Override
            public void run(){
                try

                {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.date);
                                TextView tdate1 = (TextView) findViewById(R.id.date1);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy");
                                String dateString = sdf.format(date);
                                String dateString1 = sdf1.format(date);
                                tdate.setText(dateString);
                                tdate1.setText(dateString1);
                            }
                        });
                    }
                }catch (InterruptedException e){
                }
            }
        };
        t.start();
//        Initialization();
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
//    }
//
//    private void Initialization() {
//        //Firebase authentication
//        //Firebase get username
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        userID = user.getUid();
//        img = findViewById(R.id.imageView);
//        btn = findViewById(R.id.button);
//        text = findViewById(R.id.textView5);
//    }
//    private void Fetch() {
//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Users userprofile = snapshot.getValue(Users.class);
//                Intent i = new Intent(HomeScreen.this, HomeScreen.class);
//                if (userprofile != null){
//                    String fullname = userprofile.name;
//                    text.setText(fullname);
//                    String base64 = userprofile.base64;
//                    i.putExtra("imagefetch",base64);
//                    String contact = userprofile.contact;
//                    Toast.makeText(getApplicationContext(), "Welcome "+fullname, Toast.LENGTH_SHORT).show();
//                }
////                Toast.makeText(getApplicationContext(), "Welcome"+fullname, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public Bitmap StringToBitMap(String encodedString){
//        try {
//            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
//            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            return bitmap;
//        } catch(Exception e) {
//            e.getMessage();
//            return null;
//        }

    }
}
