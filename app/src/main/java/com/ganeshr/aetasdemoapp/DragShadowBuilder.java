package com.ganeshr.aetasdemoapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

public class DragShadowBuilder extends View.DragShadowBuilder {

    View mView;
    Context mContext;

    public DragShadowBuilder(View v, Context mContext) {
        super(v);
        this.mView = v;
        this.mContext = mContext;

    }


    @Override
    public void onDrawShadow(Canvas canvas) {
        super.onDrawShadow(canvas);

        final View view = mView;
    /* give drag shadow a GRAY background, this also changes the drag-start view
    attached to the screen */
        view.setBackground(mContext.getResources().getDrawable(R.drawable.dotted_border));
//        view.setBackgroundColor(mContext.getResources().getColor(R.color.darkGray));
        view.draw(canvas);
        /* bring the drag-start view back to its original state (does not affect drag-shadow) */
        view.setBackgroundColor(mContext.getResources().getColor(R.color.white));

    }


    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
        View v = getView();
        int height = v.getHeight();
        int width = v.getWidth();
        shadowSize.set(width, height);
        shadowTouchPoint.set((width / 2), (height / 2));
    }
}
