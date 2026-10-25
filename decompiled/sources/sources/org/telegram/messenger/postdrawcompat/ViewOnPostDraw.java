package org.telegram.messenger.postdrawcompat;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Trace;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Iterator;
import me.vkryl.core.reference.ReferenceList;

/* loaded from: classes3.dex */
class ViewOnPostDraw extends View {
    private final ReferenceList<OnPostDrawListener> callbacks;
    private ViewTreeObserver observer;
    private final ViewTreeObserver.OnDrawListener onDrawListener;

    public ViewOnPostDraw(Context r1) {
        super(r1);
        this.callbacks = new ReferenceList();
        this.onDrawListener = new ViewOnPostDraw$$ExternalSyntheticLambda0(this);
    }

    private void dispatchOnPostDrawListeners() {
        Trace.beginSection("OnPostDraw");
        Iterator<OnPostDrawListener> r1 = this.callbacks.iterator();     // Catch: Throwable -> L10
    L4:
        if (r1.hasNext() == false) goto L8;
        r1.next().onPostDraw();     // Catch: Throwable -> L10
        goto L4
    L8:
        Trace.endSection();
        return;
    L10:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public void addOnPostDrawListener(OnPostDrawListener r1) {
        this.callbacks.add(r1);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewTreeObserver r0 = getViewTreeObserver();
        this.observer = r0;
        r0.addOnDrawListener(this.onDrawListener);
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
        this.observer.removeOnDrawListener(this.onDrawListener);
        goto L7
    }

    @Override // android.view.View
    public void onDraw(Canvas r1) {
        dispatchOnPostDrawListeners();
    }

    public void removeOnPostDrawListener(OnPostDrawListener r1) {
        this.callbacks.remove(r1);
    }
}
