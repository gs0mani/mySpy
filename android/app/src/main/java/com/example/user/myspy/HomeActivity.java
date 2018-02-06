package com.example.user.myspy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by user on 03-02-2018.
 */
public class HomeActivity extends AppCompatActivity {
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    public String str1="", str2="", str3="", str4="",email="";
    private ImageButton rescue, ride,remote, learn, profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        str1=getIntent().getStringExtra("building");
        str2=getIntent().getStringExtra("buildingNo");
        str3=getIntent().getStringExtra("room");
        str4=getIntent().getStringExtra("roomNo");
        email=getIntent().getStringExtra("email");


        rescue = (ImageButton)findViewById(R.id.ib_rescue);
        ride = (ImageButton)findViewById(R.id.ib_ride);
        remote = (ImageButton)findViewById(R.id.ib_remote);
        learn = (ImageButton)findViewById(R.id.ib_learn);
        profile = (ImageButton)findViewById(R.id.ib_profile);

        rescue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RescueActivity.class);
                intent.putExtra("building",str1);
                intent.putExtra("buildingNo",str2);
                intent.putExtra("room",str3);
                intent.putExtra("roomNo", str4);

                startActivity(intent);
                finish();

            }
        });

        ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(HomeActivity.this, RideActivity.class));

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });

        remote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RemoteActivity.class));
                finish();
            }
        });

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LearnActivity.class));
                finish();
            }
        });

    }
}
