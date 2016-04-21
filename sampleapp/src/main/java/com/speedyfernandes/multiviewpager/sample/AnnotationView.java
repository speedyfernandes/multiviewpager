package com.speedyfernandes.multiviewpager.sample;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jerry on 22/04/16.
 */
public class AnnotationView extends RelativeLayout {

    private TextView mTitle;
    private ImageView mImage;

    public AnnotationView(Context context) {
        this(context, null);
    }

    public AnnotationView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.page_annotation, this, true);

        mImage = (ImageView) findViewById(R.id.annotation_category_image);
        mTitle = (TextView) findViewById(R.id.annotation_category_title);
    }


    public void setImage(int image) {
        mImage.setImageResource(image);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setTint(float amount) {
        int mixColor = mixTwoColors(ContextCompat.getColor(getContext(), R.color.colorAccent),
                ContextCompat.getColor(getContext(), R.color.colorPrimary), amount);
        Drawable background = ContextCompat.getDrawable(getContext(),
                R.drawable.category_picker_circle_grey);
        background.setColorFilter(mixColor, PorterDuff.Mode.SRC_IN);
        mImage.setBackgroundDrawable(background);
        mTitle.setTextColor(mixColor);
    }

    private int mixTwoColors(int color1, int color2, float amount) {
        final byte ALPHA_CHANNEL = 24;
        final byte RED_CHANNEL = 16;
        final byte GREEN_CHANNEL = 8;
        final byte BLUE_CHANNEL = 0;

        final float inverseAmount = 1.0f - amount;

        int a = ((int) (((float) (color1 >> ALPHA_CHANNEL & 0xff) * amount) +
                ((float) (color2 >> ALPHA_CHANNEL & 0xff) * inverseAmount))) & 0xff;
        int r = ((int) (((float) (color1 >> RED_CHANNEL & 0xff) * amount) +
                ((float) (color2 >> RED_CHANNEL & 0xff) * inverseAmount))) & 0xff;
        int g = ((int) (((float) (color1 >> GREEN_CHANNEL & 0xff) * amount) +
                ((float) (color2 >> GREEN_CHANNEL & 0xff) * inverseAmount))) & 0xff;
        int b = ((int) (((float) (color1 & 0xff) * amount) +
                ((float) (color2 & 0xff) * inverseAmount))) & 0xff;

        return a << ALPHA_CHANNEL | r << RED_CHANNEL | g << GREEN_CHANNEL | b << BLUE_CHANNEL;
    }
}
