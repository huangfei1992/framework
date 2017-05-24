package com.example.skn.framework.事件冲突;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by skn on 2017/5/24.
 */

public class MyLinearLayout extends LinearLayout {

    final int TOUCH_STATE_REST = 1;
    final int TOUCH_STATE_SCROLLING = 2;
    float mLastMotionX;
    float mLastMotionY;
    int mTouchState;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if (action == MotionEvent.ACTION_MOVE && mTouchState != TOUCH_STATE_REST) {
            return true;
        }

        float x = ev.getX();
        float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                final int xDiff = (int) Math.abs(mLastMotionX - x);
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
