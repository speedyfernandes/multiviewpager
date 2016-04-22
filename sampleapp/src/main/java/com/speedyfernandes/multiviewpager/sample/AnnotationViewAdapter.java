package com.speedyfernandes.multiviewpager.sample;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.speedyfernandes.multiviewpager.MultiViewPager;

import java.util.Stack;

/**
 * Created by Jerry on 22/04/16.
 */
public class AnnotationViewAdapter extends PagerAdapter {

    private static final String TAG = AnnotationViewAdapter.class.getSimpleName();
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

    private Stack<View> mPages = new Stack<>();


    public AnnotationViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        AnnotationView page;
        if (mPages.isEmpty())
        {
            page = new AnnotationView(mContext);
        }
        else
        {
            page = (AnnotationView)mPages.pop();
            Log.i(TAG,"Restored recycled view from cache "+ page.hashCode());
        }

        page.setTitle(titles[position%6]);
        page.setImage(images[position%6]);
        collection.addView(page);

        return page;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        //collection.removeView((View) view);

        MultiViewPager pager = (MultiViewPager) collection;
        View recycledView = (View) view;
        pager.removeView(recycledView);
        mPages.push(recycledView);
        Log.i(TAG,"Stored view in cache "+ recycledView.hashCode());

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return 6 * 10;
    }
}