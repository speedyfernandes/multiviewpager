package com.speedyfernandes.multiviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Jerry on 21/04/16.
 */
public class MultiViewPagerContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    private static final int LEFT = 0;
    private static final int CENTER = 1;
    private static final int RIGHT = 2;
    private static final float ONE_THIRD = 0.333f;

    private ViewPager mPager;
    private Point mCenter = new Point();
    private Point mInitialTouch = new Point();
    private boolean mNeedsRedraw = false;
    private float mPageWidth = ONE_THIRD;
    private int mPagerPosition = CENTER;

    public MultiViewPagerContainer(Context context) {
        super(context);
        init(context, null);
    }

    public MultiViewPagerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MultiViewPagerContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //Disable clipping of children so non-selected pages are visible
        setClipChildren(false);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MultiViewPagerContainer,
                0, 0);

        try {
            mPagerPosition = a.getInt(R.styleable.MultiViewPagerContainer_pagerPosition, CENTER);
            mPageWidth = a.getFloat(R.styleable.MultiViewPagerContainer_pageWidth, ONE_THIRD);
        } finally {
            a.recycle();
        }
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
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int pagerWidth = (int) ((float) viewWidth * mPageWidth);
        int pagerHeight = viewHeight;

        mPager.setLayoutParams(new LayoutParams(pagerWidth, pagerHeight));

        if (mPagerPosition == LEFT) {
            mPager.setX(0);
        } else if (mPagerPosition == CENTER) {
            mPager.setX(viewWidth / 2 - pagerWidth / 2);
        } else if (mPagerPosition == RIGHT) {
            mPager.setX(viewWidth - pagerWidth);
        }

        mCenter.x = (int) mPager.getPivotX();
        mCenter.y = (int) mPager.getPivotY();
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
