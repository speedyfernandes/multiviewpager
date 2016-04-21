package com.speedyfernandes.multiviewpager;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Jerry on 21/04/16.
 */
public class MultiViewPagerContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    boolean mNeedsRedraw = false;
    private ViewPager mPager;
    private Point mCenter = new Point();
    private Point mInitialTouch = new Point();

    public MultiViewPagerContainer(Context context) {
        super(context);
        init();
    }

    public MultiViewPagerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultiViewPagerContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //Disable clipping of children so non-selected pages are visible
        setClipChildren(false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        try {
            mPager = (ViewPager) getChildAt(0);
            mPager.addOnPageChangeListener(this);
        } catch (Exception e) {
            throw new IllegalStateException("The root child of PagerContainer must be a ViewPager");
        }
    }

    public ViewPager getViewPager() {
        return mPager;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenter.x = w * (1 / 5);
        mCenter.y = h / 2;

        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int pagerWidth = viewWidth / 5;
        int pagerHeight = viewHeight;

        mPager.setLayoutParams(new LayoutParams(pagerWidth, pagerHeight));
        mPager.setX(viewWidth / 2 - pagerWidth / 2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //We capture any touches not already handled by the ViewPager
        // to implement scrolling from a touch outside the pager bounds.
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialTouch.x = (int) ev.getX();
                mInitialTouch.y = (int) ev.getY();
            default:
                ev.offsetLocation(mCenter.x - mInitialTouch.x, mCenter.y - mInitialTouch.y);
                break;
        }

        return mPager.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Force the container to redraw on scrolling.
        //Without this the outer pages render initially and then stay static
        if (mNeedsRedraw) invalidate();
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mNeedsRedraw = (state != ViewPager.SCROLL_STATE_IDLE);
    }
}
