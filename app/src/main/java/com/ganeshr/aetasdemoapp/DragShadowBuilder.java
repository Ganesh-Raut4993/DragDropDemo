package com.ganeshr.aetasdemoapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

public class DragShadowBuilder extends View.DragShadowBuilder {

    Context mContext;
    private View dragView = null;
    private Point touchPoints;

    public DragShadowBuilder(View v, Context mContext, Point touchPoints) {
        super(v);
        this.dragView = v;
        this.mContext = mContext;
        this.touchPoints = touchPoints;
    }


    @Override
    public void onDrawShadow(Canvas canvas) {

        dragView.draw(canvas);
        super.onDrawShadow(canvas);

        final View view = dragView;
//     give drag shadow a GRAY background, this also changes the drag-start view
//    attached to the screen
        view.setBackground(mContext.getResources().getDrawable(R.drawable.dotted_border));
//        view.setBackgroundColor(mContext.getResources().getColor(R.color.darkGray));
//        view.draw(canvas);
//         bring the drag-start view back to its original state (does not affect drag-shadow)
        view.setBackgroundColor(mContext.getResources().getColor(R.color.white));

    }


    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
//        View v = getView();
//        int height = v.getHeight();
//        int width = v.getWidth();
//        shadowSize.set(width, height);
//        shadowTouchPoint.set((width / 2), (height / 2));



        View v = getView();
        int height = v.getHeight();
        int width = v.getWidth();
        shadowSize.set(width, height);
        shadowTouchPoint.set(touchPoints.x,touchPoints.y);
        // super.onProvideShadowMetrics(shadowSize,shadowTouchPoint);




    }

}
