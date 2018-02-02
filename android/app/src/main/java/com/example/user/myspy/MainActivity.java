package com.example.user.myspy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.situm.sdk.SitumSdk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SitumSdk.init(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                MainActivity.this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                MainActivity.this.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            }
        }

        findViewById(R.id.draw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawBuildingActivity.class));
                finish();
            }
        });

    }
}
