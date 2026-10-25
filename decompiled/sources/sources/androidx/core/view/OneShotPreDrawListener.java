package androidx.core.view;

import android.view.View;
import android.view.ViewTreeObserver;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
public final class OneShotPreDrawListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    private final Runnable mRunnable;
    private final View mView;
    private ViewTreeObserver mViewTreeObserver;

    private OneShotPreDrawListener(View r1, Runnable r2) {
        this.mView = r1;
        this.mViewTreeObserver = r1.getViewTreeObserver();
        this.mRunnable = r2;
    }

    public static OneShotPreDrawListener add(View r1, Runnable r2) {
        if (r1 == null) goto L9;
        if (r2 == null) goto L7;
        OneShotPreDrawListener r0 = new OneShotPreDrawListener(r1, r2);
        r1.getViewTreeObserver().addOnPreDrawListener(r0);
        r1.addOnAttachStateChangeListener(r0);
        return r0;
    L7:
        f$$ExternalSyntheticBUOutline2.m("runnable == null");
        return null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("view == null");
        return null;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        removeListener();
        this.mRunnable.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View r1) {
        this.mViewTreeObserver = r1.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View r1) {
        removeListener();
    }

    public void removeListener() {
        if (this.mViewTreeObserver.isAlive() == false) goto L5;
        this.mViewTreeObserver.removeOnPreDrawListener(this);
    L6:
        this.mView.removeOnAttachStateChangeListener(this);
        return;
    L5:
        this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
        goto L6
    }
}
