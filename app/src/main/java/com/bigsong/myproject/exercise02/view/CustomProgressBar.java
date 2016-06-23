package com.bigsong.myproject.exercise02.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.bigsong.myproject.R;

/**
 * Created by Administrator on 2016/6/21.
 */
public class CustomProgressBar extends View {

    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;
    private int mSpeed;

    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mProgress;

    /**
     * 是否开始下一个
     */
    private boolean isNext = false;

    public CustomProgressBar(Context context) {
        this(context, null);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomProgressBar_csFirstColor:
                    mFirstColor = typedArray.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomProgressBar_csSecondColor:
                    mSecondColor = typedArray.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomProgressBar_csCircleWidth:
                    mCircleWidth = (int) typedArray.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_csSpeed:
                    mSpeed = typedArray.getInt(attr, 20);
                    break;
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!isNext) {
                            isNext = true;
                        } else {
                            isNext = false;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;
        /**
         * 设置圆环的宽度
         */
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置圆环为空心
        mPaint.setAntiAlias(true);
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        if (!isNext) {
            mPaint.setColor(mFirstColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mSecondColor);
            canvas.drawArc(oval, -90, mProgress, false, mPaint);
        } else {
            mPaint.setColor(mSecondColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mFirstColor);
            canvas.drawArc(oval, -90, mProgress, false, mPaint);
        }
    }
}
