package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes5.dex */
public class TouchObserverFrameLayout extends FrameLayout {
    private View.OnTouchListener onTouchListener;

    public TouchObserverFrameLayout(Context r1) {
        super(r1);
    }

    public TouchObserverFrameLayout(Context r1, AttributeSet r2) {
        super(r1, r2);
    }

    public TouchObserverFrameLayout(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        View.OnTouchListener r0 = this.onTouchListener;
        if (r0 == null) goto L6;
        r0.onTouch(this, r2);
    L6:
        return super.onInterceptTouchEvent(r2);
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener r1) {
        this.onTouchListener = r1;
    }
}
