package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {
    private boolean drawBottomInsetForeground;
    private boolean drawLeftInsetForeground;
    private boolean drawRightInsetForeground;
    private boolean drawTopInsetForeground;
    Drawable insetForeground;
    Rect insets;
    private Rect tempRect;

    /* renamed from: com.google.android.material.internal.ScrimInsetsFrameLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ ScrimInsetsFrameLayout this$0;

        public AnonymousClass1(ScrimInsetsFrameLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r5, WindowInsetsCompat r6) {
            ScrimInsetsFrameLayout r52 = this.this$0;
            if (r52.insets != null) goto L5;
            r52.insets = new Rect();
        L5:
            this.this$0.insets.set(r6.getSystemWindowInsetLeft(), r6.getSystemWindowInsetTop(), r6.getSystemWindowInsetRight(), r6.getSystemWindowInsetBottom());
            this.this$0.onInsetsChanged(r6);
            ScrimInsetsFrameLayout r53 = this.this$0;
            if (r6.hasSystemWindowInsets() == true) goto L8;
        L11:
            boolean r0 = true;
        L12:
            r53.setWillNotDraw(r0);
            this.this$0.postInvalidateOnAnimation();
            return r6.consumeSystemWindowInsets();
        L8:
            if (this.this$0.insetForeground == null) goto L11;
            r0 = false;
            goto L12
        }
    }

    public ScrimInsetsFrameLayout(Context r2) {
        this(r2, null);
    }

    public ScrimInsetsFrameLayout(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public ScrimInsetsFrameLayout(Context r8, AttributeSet r9, int r10) {
        super(r8, r9, r10);
        this.tempRect = new Rect();
        this.drawTopInsetForeground = true;
        this.drawBottomInsetForeground = true;
        this.drawLeftInsetForeground = true;
        this.drawRightInsetForeground = true;
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r8, r9, R.styleable.ScrimInsetsFrameLayout, r10, R.style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.insetForeground = r82.getDrawable(R.styleable.ScrimInsetsFrameLayout_insetForeground);
        r82.recycle();
        setWillNotDraw(true);
        ViewCompat.setOnApplyWindowInsetsListener(this, new AnonymousClass1(this));
    }

    @Override // android.view.View
    public void draw(Canvas r9) {
        super.draw(r9);
        int r0 = getWidth();
        int r1 = getHeight();
        if (this.insets != null) goto L5;
        return;
    L5:
        if (this.insetForeground == null) goto L21;
        int r2 = r9.save();
        r9.translate(getScrollX(), getScrollY());
        if (this.drawTopInsetForeground == false) goto L10;
        this.tempRect.set(0, 0, r0, this.insets.top);
        this.insetForeground.setBounds(this.tempRect);
        this.insetForeground.draw(r9);
    L10:
        if (this.drawBottomInsetForeground == false) goto L13;
        this.tempRect.set(0, r1 - this.insets.bottom, r0, r1);
        this.insetForeground.setBounds(this.tempRect);
        this.insetForeground.draw(r9);
    L13:
        if (this.drawLeftInsetForeground == false) goto L16;
        Rect r3 = this.tempRect;
        Rect r5 = this.insets;
        r3.set(0, r5.top, r5.left, r1 - r5.bottom);
        this.insetForeground.setBounds(this.tempRect);
        this.insetForeground.draw(r9);
    L16:
        if (this.drawRightInsetForeground == false) goto L18;
        Rect r32 = this.tempRect;
        Rect r4 = this.insets;
        r32.set(r0 - r4.right, r4.top, r0, r1 - r4.bottom);
        this.insetForeground.setBounds(this.tempRect);
        this.insetForeground.draw(r9);
    L18:
        r9.restoreToCount(r2);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable r0 = this.insetForeground;
        if (r0 == null) goto L6;
        r0.setCallback(this);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable r1 = this.insetForeground;
        if (r1 == null) goto L6;
        r1.setCallback(null);
        return;
    }

    public void onInsetsChanged(WindowInsetsCompat r1) {
    }

    public void setDrawBottomInsetForeground(boolean r1) {
        this.drawBottomInsetForeground = r1;
    }

    public void setDrawLeftInsetForeground(boolean r1) {
        this.drawLeftInsetForeground = r1;
    }

    public void setDrawRightInsetForeground(boolean r1) {
        this.drawRightInsetForeground = r1;
    }

    public void setDrawTopInsetForeground(boolean r1) {
        this.drawTopInsetForeground = r1;
    }

    public void setScrimInsetForeground(Drawable r1) {
        this.insetForeground = r1;
    }
}
