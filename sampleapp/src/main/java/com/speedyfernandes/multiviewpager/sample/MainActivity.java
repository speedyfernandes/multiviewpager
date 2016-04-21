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

        final MultiViewPager viewPager1 = (MultiViewPager) findViewById(R.id.multiViewPager1);
        viewPager1.setAdapter(new MultiViewPagerAdapter(this));
        viewPager1.setOffscreenPageLimit(9);
        viewPager1.setPageTransformer(false, new MultiViewPagerTransformer());
        viewPager1.setClipChildren(false);

        final MultiViewPager viewPager2 = (MultiViewPager) findViewById(R.id.multiViewPager2);
        viewPager2.setAdapter(new AnnotationCategoryAdapter(this));
        viewPager2.setOffscreenPageLimit(9);
        viewPager2.setPageTransformer(false, new AnnotationPagerTransformer());
        viewPager2.setClipChildren(false);


        final MultiViewPager viewPager3 = (MultiViewPager) findViewById(R.id.multiViewPager3);
        viewPager3.setAdapter(new MultiViewPagerAdapter(this));
        viewPager3.setOffscreenPageLimit(9);
        viewPager3.setPageTransformer(false, new MultiViewPagerTransformer());
        viewPager3.setClipChildren(false);
    }
}
