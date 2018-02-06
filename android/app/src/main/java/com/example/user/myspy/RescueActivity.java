package com.example.user.myspy;

/**
 * Created by user on 03-02-2018.
 */
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class RescueActivity extends AppCompatActivity {

    public   ImageView image;
    Button button;
    public String str1,str2,str3,str4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //image = (ImageView) findViewById(R.id.imageView1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue);
        image =  findViewById(R.id.imageView1);

      //  getActionBar().setDisplayHomeAsUpEnabled(true);
        //button = findViewById(R.id.exit);


        str1=getIntent().getStringExtra("building");
        str2=getIntent().getStringExtra("buildingNo");
        str3=getIntent().getStringExtra("room");
        str4=getIntent().getStringExtra("roomNo");


/**        int i[]={R.drawable.groundfloorcon,R.drawable.groundfloorhod,R.drawable.groundfloorlab1,
 R.drawable.groundfloorlab2,R.drawable.groundflooroff,
 R.drawable.groundfloorstair,R.drawable.groundfloorstair2,R.drawable.groundfloorwash};**/
        String s=str1+str3+str4;
        if(s.equals("New Lecture Theater ComplexLab01")) {

            //image.setBackground(getResources().getDrawable(i[2]));
            //Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.groundfloor);
        }
        else if(s.equals("New Lecture Theater ComplexStair-well01")){
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.groundfloorstair);
        }

        else if(s.equals("New Lecture Theater ComplexStair-well02"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.groundfloorstair2);
        }
        else if(s.equals("New Lecture Theater ComplexWashrooms01"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.groundfloorwash);
        }
        else if(s.equals("New Lecture Theater ComplexLab02"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.groundfloorlab2);
        }

        else if(s.equals("Department of Computer Science and EngineeringLab01"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cselab1);
        }
        else if(s.equals("Department of Computer Science and EngineeringLecture Theater01"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cselt1);
        }
        else if(s.equals("Department of Computer Science and EngineeringLecture Theater02"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cselt2);
        }
        else if(s.equals("Department of Computer Science and EngineeringLab02"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cselab2);
        }
        else if(s.equals("Department of Computer Science and EngineeringOffice01"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cseoffice);
        }

        else if(s.equals("Department of Mathematical SciencesLab01"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cseoffice);
        }
        else if(s.equals("Department of Mathematical SciencesLab02"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cseoffice);
        }
        else if(s.equals("Department of Mathematical SciencesStaff01"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cseoffice);
        }
        else if(s.equals("Department of Mathematical SciencesStaff02"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cseoffice);
        }
        else if(s.equals("Department of Mathematical SciencesStaff03"))
        {
            Toast.makeText(RescueActivity.this,s+ "Hua toh",Toast.LENGTH_SHORT);
            image.setImageResource(R.drawable.cseoffice);
        }
        else{
            image.setImageResource(R.drawable.mnc2);
            Toast.makeText(RescueActivity.this,
                    "You are currently in : " +
                            "\nBuilding: New Lecture Theater"+
                            "\nFloor: 02"+
                            "\nRoom : Lecture Hall" +
                            "\nNumber : 02"+
                            "s: "+s+ "Which has not been entered!\nSORRY!",
                    Toast.LENGTH_LONG).show();
        }

        //image.setImageResource(R.drawable.groundfloor);

    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(RescueActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    /*@Override
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
