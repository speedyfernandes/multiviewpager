package com.speedyfernandes.multiviewpager.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.speedyfernandes.multiviewpager.MultiViewPager;
import com.speedyfernandes.multiviewpager.MultiViewPagerTransformer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiViewPager viewPager = (MultiViewPager) findViewById(R.id.multiViewPager);
        viewPager.setAdapter(new MultiViewPagerAdapter(this));
        viewPager.setOffscreenPageLimit(9);
        viewPager.setPageTransformer(false, new MultiViewPagerTransformer());
        viewPager.setClipChildren(false);
    }
}
