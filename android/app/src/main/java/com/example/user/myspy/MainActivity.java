package com.example.user.myspy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.situm.sdk.SitumSdk;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPage;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] nDots;
    private Button mNxtBtn, mBackBtn;
    private int mCurrentPage;

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

        mSlideViewPage = (ViewPager) findViewById(R.id.viewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPage.setAdapter(sliderAdapter);

        mBackBtn = (Button)findViewById(R.id.backbutton);
        mNxtBtn = (Button)findViewById(R.id.nxtbutton);

        addDotsIndicator(0);
        mSlideViewPage.addOnPageChangeListener(viewListener);
        mNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPage.setCurrentItem(mCurrentPage + 1);
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPage.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position) {
        nDots = new TextView[3];
        mDotLayout.removeAllViews();
        for (int i = 0; i < nDots.length; i++) {
            nDots[i] = new TextView(this);
            nDots[i].setText(Html.fromHtml("&#8226;"));
            nDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            nDots[i].setTextSize(35);
            mDotLayout.addView(nDots[i]);
        }
        if (nDots.length > 0) {
            nDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage=i;
            if(i==0)
            {
                mBackBtn.setEnabled(false);
                mNxtBtn.setEnabled(true);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNxtBtn.setText("NEXT");
                mBackBtn.setText("");
            }else if(i==nDots.length-1)
            {
                mBackBtn.setEnabled(true);
                mNxtBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNxtBtn.setText("FINISH");
                mBackBtn.setText("BACK");

                mNxtBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, GenerateQR.class));
                        finish();
                    }
                });

            }else{
                mBackBtn.setEnabled(true);
                mNxtBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNxtBtn.setText("NEXT");
                mBackBtn.setText("BACK");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
