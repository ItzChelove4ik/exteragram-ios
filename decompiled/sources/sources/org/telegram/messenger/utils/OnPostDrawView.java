package org.telegram.messenger.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class OnPostDrawView extends View implements ViewTreeObserver.OnPreDrawListener {
    private final InvalidateCallback callback;
    private int invalidateFlags;
    private ViewTreeObserver observer;
    private final boolean onPreDrawMode;

    public interface InvalidateCallback {
        void onPostDraw(int r1);
    }

    public OnPostDrawView(Context r1, boolean r2, InvalidateCallback r3) {
        super(r1);
        this.invalidateFlags = 0;
        this.callback = r3;
        this.onPreDrawMode = r2;
    }

    public void bringToFrontIfNeeded() {
        ViewParent r0 = getParent();
        if ((r0 instanceof ViewGroup) == false) goto L10;
        ViewGroup r02 = (ViewGroup) r0;
        int r1 = r02.indexOfChild(this);
        if (r1 >= 0) goto L7;
        return;
    L7:
        if (r1 == (r02.getChildCount() - 1)) goto L12;
        r02.bringChildToFront(this);
        return;
    L12:
        return;
    }

    public void invalidate(int r2) {
        if (this.invalidateFlags != 0) goto L5;
        invalidate();
    L5:
        this.invalidateFlags = r2 | this.invalidateFlags;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.onPreDrawMode == false) goto L6;
        ViewTreeObserver r0 = getViewTreeObserver();
        this.observer = r0;
        r0.addOnPreDrawListener(this);
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewTreeObserver r0 = this.observer;
        if (r0 != null) goto L5;
    L7:
        this.observer = null;
        return;
    L5:
        if (r0.isAlive() == false) goto L7;
        this.observer.removeOnPreDrawListener(this);
        goto L7
    }

    @Override // android.view.View
    public void onDraw(Canvas r2) {
        super.onDraw(r2);
        if (this.onPreDrawMode == true) goto L6;
        this.callback.onPostDraw(this.invalidateFlags);
        this.invalidateFlags = 0;
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(LayoutHelper.measureSpecExactly(1), LayoutHelper.measureSpecExactly(1));
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        if (this.onPreDrawMode == false) goto L9;
        int r0 = this.invalidateFlags;
        if (r0 == 0) goto L10;
        this.callback.onPostDraw(r0);
        this.invalidateFlags = 0;
        return true;
    L10:
        return true;
    L9:
        return true;
    }
}
