package com.speedyfernandes.multiviewpager.sample;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jerry on 22/04/16.
 */
public class AnnotationViewAdapter extends PagerAdapter {

    private Context mContext;
    private String[] titles = {"General",
            "Heating/Cooling",
            "Electronics",
            "Laundry",
            "Cleaning",
            "Visitors"};
    private int[] images = {R.drawable.category_carousel_icon_other,
            R.drawable.category_carousel_icon_heatcool,
            R.drawable.category_carousel_icon_business,
            R.drawable.category_carousel_icon_washing,
            R.drawable.category_carousel_icon_cleaning,
            R.drawable.category_carousel_icon_visitor};

    public AnnotationViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        AnnotationView page = new AnnotationView(mContext);
        page.setTitle(titles[position]);
        page.setImage(images[position]);
        collection.addView(page);
        return page;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return 6;
    }
}