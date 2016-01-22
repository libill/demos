package com.libill.demos.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.libill.demos.view.MyAnimView.java
 * @author: liqiongwei
 * @date: 2016-01-22 11:06
 */
public class MyAnimView extends View {

    public static final float RADIUS = 50f;

    private Point currentPoint;

    protected Paint mPaint;

    /**
     * ObjectAnimator内部的工作机制是通过寻找特定属性的get和set方法，
     * 然后通过方法不断地对值进行改变，从而实现动画效果的。
     * 因此我们就需要在MyAnimView中定义一个color属性，并提供它的get和set方法
     */
    private String color;

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(RADIUS, RADIUS);
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
//        anim.setInterpolator(new AccelerateInterpolator(2f));
        anim.setInterpolator(new BounceInterpolator());
//        anim.setDuration(5000);
//        anim.start();
        ObjectAnimator anim2 = ObjectAnimator
                .ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f, 1f, 0.3f);
        final AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim).with(anim2).with(anim3);
        animSet.setDuration(5000);
        animSet.start();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                animSet.start();
            }
        });
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

}