package com.example.user.myspy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 03-02-2018.
 */
public class RemoteActivity extends AppCompatActivity {
    Button request,permit, track;
    public String tuser="";
    TextView tv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        tuser=getIntent().getStringExtra("building");
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        tv=(TextView)findViewById(R.id.t_user);
        request = (Button)findViewById(R.id.request_btn);
        permit = (Button)findViewById(R.id.permit_btn);
        track =(Button)findViewById(R.id.track_btn);
        tv.setText(tuser);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RemoteActivity.this, ScanQR.class));
                finish();
            }
        });
        permit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RemoteActivity.this, GenerateQR.class));
                finish();
            }
        });

            track.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RemoteActivity.this, DrawBuildingActivity.class));
                    finish();
                }
            });

    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(RemoteActivity.this, HomeActivity.class);
        startActivity(intent);
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
