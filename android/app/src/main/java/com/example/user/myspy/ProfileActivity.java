package com.example.user.myspy;

/**
 * Created by user on 02-02-2018.
 */

import com.example.user.myspy.SQLiteHandler;
import com.example.user.myspy.SessionManager;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity {

    /*private TextView txtName;*/
    private TextView txtEmail;
/*
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;
*/
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //txtName = (TextView) findViewById(R.id.name);

        txtEmail = (TextView) findViewById(R.id.email);
        str=getIntent().getStringExtra("email");
        txtEmail.setText(str);
        //btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler

    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }

   /* @Override
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