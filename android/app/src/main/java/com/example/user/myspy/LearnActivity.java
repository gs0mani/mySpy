package com.example.user.myspy;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;

import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;




public class LearnActivity extends AppCompatActivity {
    public  String str1;
    public  String str2;
    public  String str3, str4;

    private Spinner spinner1, spinner2,spinner3, spinner4;
    private Button track,fire,cab,learn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //addItemsOnSpinner2();
        addListenerOnButton(); //learn button functions goes here
        addListenerOnSpinnerItemSelection();


    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/


    /**public void addItemsOnSpinner2() {
     spinner2 = (Spinner) findViewById(R.id.spinner2);
     List<String> list = new ArrayList<String>();
     list.add("list 1");
     list.add("list 2");
     list.add("list 3");
     ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
     android.R.layout.simple_spinner_item, list);
     dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     spinner2.setAdapter(dataAdapter);
     }**/

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);

        learn = (Button) findViewById(R.id.learn);

        learn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                str1=String.valueOf(spinner1.getSelectedItem());
                str2=String.valueOf(spinner2.getSelectedItem());
                str3=String.valueOf(spinner3.getSelectedItem());
                str4=String.valueOf(spinner4.getSelectedItem());
                Toast.makeText(LearnActivity.this,
                        "You are currently in : " +
                                "\nBuilding : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nFloor : "+ String.valueOf(spinner2.getSelectedItem())+
                                "\nRoom : "+ String.valueOf(spinner3.getSelectedItem())+
                                "\nNumbered: "+ String.valueOf(spinner4.getSelectedItem()),
                        Toast.LENGTH_LONG).show();
            }

        });
        /* learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnActivity.this, Wifii.class));
                finish();
            }
        });*/
    }

    /**
     * Created by shreemoyee on 2/2/18.
     */

    public static class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            /**Toast.makeText(parent.getContext(),
             "You selected" + parent.getItemAtPosition(pos).toString(),
             Toast.LENGTH_SHORT).show();**/
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(LearnActivity.this, HomeActivity.class);
        intent.putExtra("building",str1);
        intent.putExtra("buildingNo",str2);
        intent.putExtra("room",str3);
        intent.putExtra("roomNo",str4);

        startActivity(intent);
    }
}