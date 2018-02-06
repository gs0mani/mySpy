package com.example.user.myspy;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by user on 28-01-2018.
 */
public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context)
    {
        this.context=context;
    }

    public int[] slide_images = {
            R.drawable.fire2,
            R.drawable.car3,
            R.drawable.monitor1 };

    public String[] slide_headings={
            "RESCUE",
            "RIDE A CAB",
            "REMOTE MONITOR" };

    public String[] slide_descriptions={
            "Fire Emergency? Worry not!  I'll locate you precisely and show you the shortest way out!! ",
            "I'll pick you from your exact location without any hassel! \n -Yours own Chauffer",
            "Allow me to keep a track of your productivity on a day-to-day basis, soyou won't slack off " };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout)o ;
    }
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater)context.getSystemService((context.LAYOUT_INFLATER_SERVICE));
        View view =  layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView slideImageView = (ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView)view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView)view.findViewById(R.id.slide_description);
        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descriptions[position]);

        container.addView(view);
        return view;

    }
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((RelativeLayout)object);
    }
}
