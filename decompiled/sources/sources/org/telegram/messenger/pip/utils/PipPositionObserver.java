package org.telegram.messenger.pip.utils;

import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: classes5.dex */
public class PipPositionObserver {
    private final ViewTreeObserver.OnGlobalLayoutListener listener;
    private View mView;
    private ViewTreeObserver mViewTreeObserver;
    private final View.OnAttachStateChangeListener onAttachStateChangeListener;

    /* renamed from: org.telegram.messenger.pip.utils.PipPositionObserver$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ PipPositionObserver this$0;

        public AnonymousClass1(PipPositionObserver r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r2) {
            if (r2 != PipPositionObserver.m5165$$Nest$fgetmView(this.this$0)) goto L6;
            PipPositionObserver.m5166$$Nest$msetViewTreeObserverInternal(this.this$0, r2.getViewTreeObserver());
            return;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            if (r2 != PipPositionObserver.m5165$$Nest$fgetmView(this.this$0)) goto L6;
            PipPositionObserver.m5166$$Nest$msetViewTreeObserverInternal(this.this$0, null);
            return;
        }
    }

    /* renamed from: -$$Nest$fgetmView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5165$$Nest$fgetmView(PipPositionObserver r0) {
        return r0.mView;
    }

    /* renamed from: -$$Nest$msetViewTreeObserverInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5166$$Nest$msetViewTreeObserverInternal(PipPositionObserver r0, ViewTreeObserver r1) {
        r0.setViewTreeObserverInternal(r1);
    }

    public PipPositionObserver(ViewTreeObserver.OnGlobalLayoutListener r2) {
        this.onAttachStateChangeListener = new AnonymousClass1(this);
        this.listener = r2;
    }

    private void setViewInternal(View r3) {
        if (this.mView != r3) goto L5;
        return;
    L5:
        setViewTreeObserverInternal(null);
        View r0 = this.mView;
        if (r0 == null) goto L8;
        r0.removeOnAttachStateChangeListener(this.onAttachStateChangeListener);
    L8:
        if (r3 == null) goto L12;
        r3.addOnAttachStateChangeListener(this.onAttachStateChangeListener);
        if (r3.isAttachedToWindow() == false) goto L12;
        setViewTreeObserverInternal(r3.getViewTreeObserver());
    L12:
        this.mView = r3;
    }

    private void setViewTreeObserverInternal(ViewTreeObserver r3) {
        ViewTreeObserver r0 = this.mViewTreeObserver;
        if (r0 != r3) goto L5;
        return;
    L5:
        if (r0 != null) goto L7;
    L9:
        if (r3 == null) goto L11;
        r3.addOnGlobalLayoutListener(this.listener);
    L11:
        this.mViewTreeObserver = r3;
        return;
    L7:
        if (r0.isAlive() == false) goto L9;
        this.mViewTreeObserver.removeOnGlobalLayoutListener(this.listener);
        goto L9
    }

    public void start(View r1) {
        setViewInternal(r1);
    }

    public void stop() {
        setViewInternal(null);
    }
}
