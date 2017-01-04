package com.maidouvr.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.maidouvr.R;
import com.maidouvr.utils.LogUtil;

/**
 * Created by stone on 2016/12/26.
 * Project:maidouvr_android
 */

public class DrawableView extends View {
    private final static int DRAG = 1;
    private final static int ZOOM = 2;

    private Bitmap backgroundImg;
    private int width;
    private int height;
    private Matrix matrix;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public DrawableView(Context context) {
        super(context);
        initView();
    }

    public DrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    void initView() {
        backgroundImg = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        matrix = new Matrix();
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        LogUtil.d("SIZE", "[" + width + ":" + height + "]");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);


        canvas.drawBitmap(backgroundImg, matrix, null);

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 2.0f));
            LogUtil.d("SCALE", "[" + mScaleFactor + "]");
            LogUtil.d("SPAN-->X", "[" + detector.getPreviousSpanX() + "-" + detector.getCurrentSpanX() + "]");
            LogUtil.d("SPAN-->Y", "[" + detector.getPreviousSpanY() + "-" + detector.getCurrentSpanY() + "]");
            Matrix newMatrix = new Matrix();
            newMatrix.postScale(mScaleFactor, mScaleFactor);
//            newMatrix.postTranslate(detector.getCurrentSpanX() / 2, detector.getCurrentSpanY() / 2);
            matrix.set(newMatrix);

            invalidate();
            return true;
        }
    }

}
