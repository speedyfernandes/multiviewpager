package com.speedyfernandes.multiviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Jerry on 21/04/16.
 */
public class MultiViewPager extends ViewPager {

    private static final String TAG = MultiViewPager.class.getSimpleName();
    private int lastWidth;
    private int lastHeight;

    public MultiViewPager(Context context) {
        super(context);
    }

    public MultiViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(final boolean changed, final int left, final int top, final int right,
                            final int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final int width = right - left;
        final int height = bottom - top;

        // Check if it actually has changed...
        if (changed && (width != lastWidth || height != lastHeight)) {
            lastWidth = width;
            lastHeight = height;
        }
    }
}
