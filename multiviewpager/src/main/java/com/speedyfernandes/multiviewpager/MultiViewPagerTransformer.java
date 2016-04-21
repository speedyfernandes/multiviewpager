package com.speedyfernandes.multiviewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Jerry on 21/04/16.
 */
public class MultiViewPagerTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        if (position >= -1 && position <= 1) { // [-1,1]
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // [(-Infinity,-1),(1,+Infinity)]
            // This page is way off-screen.
            view.setAlpha(MIN_ALPHA);
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
        }
    }
}