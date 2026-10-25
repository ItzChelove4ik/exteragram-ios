package org.telegram.ui.Components.chat;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes3.dex */
public final class ViewPositionWatcher implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    private static final int[] tmpCords = null;
    private static RectF tmpRectF2;
    private final View anchorView;
    private boolean listening;
    private final WeakHashMap<View, MultiwindowHook> multiwindowHooks;
    private final RectF tmpRect;
    private final WeakHashMap<View, List<Tracked>> tracked;
    private ViewTreeObserver vto;

    /* loaded from: classes7.dex */
    public static final class MultiwindowHook implements ViewTreeObserver.OnPreDrawListener {
        private boolean detached;
        private ViewTreeObserver registeredOn;
        private final WeakReference<View> viewRef;
        private final WeakReference<ViewPositionWatcher> watcherRef;

        public MultiwindowHook(ViewPositionWatcher r2, View r3) {
            this.watcherRef = new WeakReference(r2);
            this.viewRef = new WeakReference(r3);
        }

        public void detach() {
            this.detached = true;
            ViewTreeObserver r0 = this.registeredOn;
            this.registeredOn = null;
            if (r0 != null) goto L5;
        L7:
            View r1 = this.viewRef.get();
            if (r1 != null) goto L10;
            return;
        L10:
            if (r1.isAttachedToWindow() == false) goto L23;
            ViewTreeObserver r12 = r1.getViewTreeObserver();
            if (r12 == null) goto L20;
            if (r12 != r0) goto L16;
            return;
        L16:
            if (r12.isAlive() == false) goto L22;
            r12.removeOnPreDrawListener(this);
            return;
        L22:
            return;
        L20:
            return;
        L23:
            return;
        L5:
            if (r0.isAlive() == false) goto L7;
            r0.removeOnPreDrawListener(this);
            goto L7
        }

        public void ensureRegistered() {
            View r0 = this.viewRef.get();
            if (r0 == null) goto L20;
            ViewTreeObserver r02 = r0.getViewTreeObserver();
            if (r02 != null) goto L8;
            return;
        L8:
            if (r02.isAlive() == false) goto L19;
            ViewTreeObserver r1 = this.registeredOn;
            if (r02 == r1) goto L21;
            if (r1 != null) goto L14;
        L16:
            r02.removeOnPreDrawListener(this);
            r02.addOnPreDrawListener(this);
            this.registeredOn = r02;
            this.detached = false;
            return;
        L14:
            if (r1.isAlive() == false) goto L16;
            this.registeredOn.removeOnPreDrawListener(this);
            goto L16
        L21:
            return;
        L19:
            return;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (this.detached == false) goto L5;
            ViewPositionWatcher r0 = null;
        L6:
            if (r0 != null) goto L10;
            detach();
            return true;
        L10:
            return r0.onPreDraw();
        L5:
            r0 = this.watcherRef.get();
            goto L6
        }
    }

    public interface OnChangedListener {
        void onPositionChanged(View r1, RectF r2);
    }

    public static final class Tracked {
        boolean everAttached;
        boolean hasLast;
        MultiwindowHook hook;
        final RectF last;
        final OnChangedListener listener;
        boolean multiwindow;
        final ViewGroup parent;
        boolean trackReattach;

        public Tracked(ViewGroup r2, OnChangedListener r3) {
            this.last = new RectF();
            this.parent = r2;
            this.listener = r3;
        }
    }

    static {
        tmpCords = new int[2];
        tmpRectF2 = new RectF();
    }

    public ViewPositionWatcher(View r2) {
        this.tracked = new WeakHashMap();
        this.multiwindowHooks = new WeakHashMap();
        this.tmpRect = new RectF();
        this.anchorView = r2;
        r2.addOnAttachStateChangeListener(this);
        attachIfPossible();
    }

    private void attachIfPossible() {
        if (this.anchorView.isAttachedToWindow() == false) goto L16;
        ViewTreeObserver r0 = this.anchorView.getViewTreeObserver();
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.isAlive() == false) goto L14;
        this.vto = r0;
        if (this.listening == true) goto L15;
        r0.addOnPreDrawListener(this);
        this.listening = true;
        return;
    L15:
        return;
    L14:
        return;
    }

    public static boolean computeCoordinatesInParent(View r1, ViewGroup r2, PointF r3) {
        boolean r12 = computeRectInParent(r1, r2, tmpRectF2);
        if (r12 == false) goto L5;
        RectF r22 = tmpRectF2;
        r3.x = r22.left;
        r3.y = r22.top;
    L5:
        return r12;
    }

    public static boolean computeRectInParent(View r5, View r6, RectF r7) {
        float r0 = 0.0f;
        View r2 = r5;
        float r1 = 0.0f;
    L4:
        if (r2 == null) goto L10;
        if (r2 == r6) goto L10;
        float r02 = r0 + r2.getX();
        float r12 = r1 + r2.getY();
        Object r22 = r2.getParent();
        if ((r22 instanceof View) == false) goto L8;
        r2 = (View) r22;
        r0 = r02 - r2.getScrollX();
        r1 = r12 - r2.getScrollY();
        goto L4
    L8:
        return false;
    L10:
        if (r2 == r6) goto L12;
        return false;
    L12:
        r7.set(r0, r1, r5.getWidth() + r0, r5.getHeight() + r1);
        return true;
    }

    public static float computeXCoordinateInParent(View r1, ViewGroup r2) {
        computeRectInParent(r1, r2, tmpRectF2);
        return tmpRectF2.left;
    }

    public static float computeYCoordinateInParent(View r1, ViewGroup r2) {
        computeRectInParent(r1, r2, tmpRectF2);
        return tmpRectF2.top;
    }

    private void detachAllMultiwindowHooks() {
        if (this.multiwindowHooks.isEmpty() == false) goto L5;
        return;
    L5:
        Iterator<MultiwindowHook> r0 = this.multiwindowHooks.values().iterator();
    L7:
        if (r0.hasNext() == false) goto L9;
        r0.next().detach();
        goto L7
    L9:
        this.multiwindowHooks.clear();
    }

    private void detachIfListening() {
        if (this.listening == false) goto L9;
        ViewTreeObserver r0 = this.vto;
        if (r0 == null) goto L9;
        if (r0.isAlive() == false) goto L9;
        this.vto.removeOnPreDrawListener(this);
    L9:
        this.listening = false;
        this.vto = null;
    }

    private void detachMultiwindowHook(View r1) {
        if (r1 == null) goto L9;
        MultiwindowHook r0 = this.multiwindowHooks.remove(r1);
        if (r0 == null) goto L8;
        r0.detach();
        return;
    L8:
        return;
    }

    private void ensureListening() {
        if (this.listening == true) goto L6;
        attachIfPossible();
        return;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        if (this.anchorView.getViewTreeObserver() == this.vto) goto L6;
        detachIfListening();
        attachIfPossible();
    L6:
        if (this.tracked.isEmpty() == false) goto L9;
        detachIfListening();
        detachAllMultiwindowHooks();
        return true;
    L9:
        Iterator<Map.Entry<View, List<Tracked>>> r0 = this.tracked.entrySet().iterator();
    L11:
        if (r0.hasNext() == false) goto L58;
        Map.Entry<View, List<Tracked>> r2 = r0.next();
        View r3 = r2.getKey();
        List<Tracked> r22 = r2.getValue();
        if (r3 == null) goto L56;
        if (r22 == null) goto L56;
        if (r22.isEmpty() == true) goto L56;
        if (r3.isAttachedToWindow() == false) goto L20;
        int r4 = r22.size() - 1;
    L32:
        if (r4 < 0) goto L54;
        Tracked r5 = r22.get(r4);
        r5.everAttached = true;
        if (r5.multiwindow == false) goto L40;
        MultiwindowHook r6 = r5.hook;
        if (r6 == null) goto L38;
        r6.ensureRegistered();
    L38:
        int[] r62 = tmpCords;
        r3.getLocationOnScreen(r62);
        this.tmpRect.set(r62[0], r62[1], r9 + r3.getWidth(), r62[1] + r3.getHeight());
        r5.parent.getLocationOnScreen(r62);
        this.tmpRect.offset(-r62[0], -r62[1]);
    L45:
        if (r5.hasLast == true) goto L47;
    L48:
        r5.last.set(this.tmpRect);
        r5.hasLast = true;
        r5.listener.onPositionChanged(r3, new RectF(this.tmpRect));     // Catch: Throwable -> L61
    L52:
        r4 = r4 - 1;
        goto L32
    L47:
        if (this.tmpRect.equals(r5.last) == true) goto L52;
    L40:
        if (r5.parent.isAttachedToWindow() == true) goto L42;
    L51:
        r22.remove(r4);
        goto L52
    L42:
        if (computeRectInParent(r3, r5.parent, this.tmpRect) == true) goto L45;
    L54:
        if (r22.isEmpty() == false) goto L11;
        r0.remove();
        detachMultiwindowHook(r3);
        goto L11
    L20:
        int r42 = r22.size() - 1;
    L21:
        if (r42 < 0) goto L29;
        Tracked r52 = r22.get(r42);
        if (r52.everAttached == false) goto L27;
        if (r52.trackReattach == true) goto L27;
        r22.remove(r42);
    L27:
        r42 = r42 - 1;
        goto L21
    L29:
        if (r22.isEmpty() == false) goto L11;
        r0.remove();
        detachMultiwindowHook(r3);
    L56:
        r0.remove();
        detachMultiwindowHook(r3);
        goto L11
    L58:
        if (this.tracked.isEmpty() == false) goto L60;
        detachIfListening();
        detachAllMultiwindowHooks();
    L60:
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View r1) {
        attachIfPossible();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View r2) {
        if (r2 != this.anchorView) goto L6;
        detachIfListening();
        return;
    }

    public void shutdown() {
        detachIfListening();
        this.anchorView.removeOnAttachStateChangeListener(this);
        this.tracked.clear();
        detachAllMultiwindowHooks();
    }

    public void subscribe(View r2, ViewGroup r3, OnChangedListener r4) {
        subscribe(r2, r3, r4, false);
    }

    public void subscribe(View r7, ViewGroup r8, OnChangedListener r9, boolean r10) {
        subscribe(r7, r8, r9, r10, false);
    }

    public void subscribe(View r3, ViewGroup r4, OnChangedListener r5, boolean r6, boolean r7) {
        Tracked r0 = new Tracked(r4, r5);
        r0.multiwindow = r6;
        r0.trackReattach = r7;
        List<Tracked> r52 = this.tracked.get(r3);
        if (r52 != null) goto L5;
        r52 = new ArrayList(1);
        this.tracked.put(r3, r52);
    L5:
        r52.add(r0);
        if (computeRectInParent(r3, r4, this.tmpRect) == false) goto L8;
        r0.last.set(this.tmpRect);
        r0.hasLast = true;
    L8:
        ensureListening();
        if (r6 == false) goto L15;
        MultiwindowHook r42 = this.multiwindowHooks.get(r3);
        if (r42 != null) goto L13;
        r42 = new MultiwindowHook(this, r3);
        this.multiwindowHooks.put(r3, r42);
    L13:
        r0.hook = r42;
        r42.ensureRegistered();
        return;
    }
}
