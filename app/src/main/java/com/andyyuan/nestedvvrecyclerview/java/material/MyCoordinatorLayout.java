package com.andyyuan.nestedvvrecyclerview.java.material;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * author : AndyYuan
 * e-mail : 018...@smg.cn
 * date   : 2021/11/15 001511:00
 * desc   : 外层协调Layout
 * version: 2.0
 */
public class MyCoordinatorLayout extends CoordinatorLayout implements NestedScrollingParent2 {

    //滑动
    private boolean isNeedScroll = true;
    private float xDistance, yDistance, xLast, yLast;
    private int scaledTouchSlop;
    private boolean mNeedsPreDrawListener;
    private WindowInsetsCompat mLastInsets;
    private boolean mDrawStatusBarBackground;
    private Drawable mStatusBarBackground;
    OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;

    public MyCoordinatorLayout(@NonNull Context context) {
        super(context);
    }

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setNeedScroll(boolean isNeedScroll) {
        this.isNeedScroll = isNeedScroll;
        if (isNeedScroll) {
            //Log.e("SiberiaDante------->>>>","父控件拦截事件");
            getParent().requestDisallowInterceptTouchEvent(false);
        }else {
            //Log.e("SiberiaDante------->>>>","父控件not拦截事件");
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                return isNeedScroll;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }


    public void setStatusBarBackground(@Nullable Drawable bg) {
        if (this.mStatusBarBackground != bg) {
            if (this.mStatusBarBackground != null) {
                this.mStatusBarBackground.setCallback((Drawable.Callback)null);
            }

            this.mStatusBarBackground = bg != null ? bg.mutate() : null;
            if (this.mStatusBarBackground != null) {
                if (this.mStatusBarBackground.isStateful()) {
                    this.mStatusBarBackground.setState(this.getDrawableState());
                }

                DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection(this));
                this.mStatusBarBackground.setVisible(this.getVisibility() == View.VISIBLE, false);
                this.mStatusBarBackground.setCallback(this);
            }

            ViewCompat.postInvalidateOnAnimation(this);
        }

    }

    @Nullable
    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] state = this.getDrawableState();
        boolean changed = false;
        Drawable d = this.mStatusBarBackground;
        if (d != null && d.isStateful()) {
            changed |= d.setState(state);
        }

        if (changed) {
            this.invalidate();
        }

    }

    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.mStatusBarBackground;
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        boolean visible = visibility == 0;
        if (this.mStatusBarBackground != null && this.mStatusBarBackground.isVisible() != visible) {
            this.mStatusBarBackground.setVisible(visible, false);
        }

    }

    public void setStatusBarBackgroundResource(@DrawableRes int resId) {
        this.setStatusBarBackground(resId != 0 ? ContextCompat.getDrawable(this.getContext(), resId) : null);
    }

    public void setStatusBarBackgroundColor(@ColorInt int color) {
        this.setStatusBarBackground(new ColorDrawable(color));
    }



}
