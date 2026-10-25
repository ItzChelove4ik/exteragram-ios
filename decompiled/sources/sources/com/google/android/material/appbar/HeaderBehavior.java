package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;

/* loaded from: classes5.dex */
abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private int activePointerId;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    OverScroller scroller;
    private int touchSlop;
    private VelocityTracker velocityTracker;

    public class FlingRunnable implements Runnable {
        private final V layout;
        private final CoordinatorLayout parent;
        final /* synthetic */ HeaderBehavior this$0;

        public FlingRunnable(HeaderBehavior r1, CoordinatorLayout r2, V r3) {
            this.this$0 = r1;
            this.parent = r2;
            this.layout = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.layout == null) goto L12;
            OverScroller r0 = this.this$0.scroller;
            if (r0 == null) goto L13;
            boolean r02 = r0.computeScrollOffset();
            HeaderBehavior r1 = this.this$0;
            if (r02 == false) goto L10;
            r1.setHeaderTopBottomOffset(this.parent, this.layout, r1.scroller.getCurrY());
            this.layout.postOnAnimation(this);
            return;
        L10:
            r1.onFlingFinished(this.parent, this.layout);
            return;
        L13:
            return;
        }
    }

    public HeaderBehavior() {
        this.activePointerId = -1;
        this.touchSlop = -1;
    }

    public HeaderBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.activePointerId = -1;
        this.touchSlop = -1;
    }

    private void ensureVelocityTracker() {
        if (this.velocityTracker != null) goto L6;
        this.velocityTracker = VelocityTracker.obtain();
        return;
    }

    public boolean canDragView(V r1) {
        return false;
    }

    public final boolean fling(CoordinatorLayout r12, V r13, int r14, int r15, float r16) {
        Runnable r0 = this.flingRunnable;
        if (r0 == null) goto L6;
        r13.removeCallbacks(r0);
        this.flingRunnable = null;
    L6:
        if (this.scroller != null) goto L8;
        this.scroller = new OverScroller(r13.getContext());
    L8:
        this.scroller.fling(0, getTopAndBottomOffset(), 0, Math.round(r16), 0, 0, r14, r15);
        if (this.scroller.computeScrollOffset() == false) goto L12;
        FlingRunnable r142 = new FlingRunnable(this, r12, r13);
        this.flingRunnable = r142;
        r13.postOnAnimation(r142);
        return true;
    L12:
        onFlingFinished(r12, r13);
        return false;
    }

    public int getMaxDragOffset(V r1) {
        return -r1.getHeight();
    }

    public int getScrollRangeForDragFling(V r1) {
        return r1.getHeight();
    }

    public int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    public void onFlingFinished(CoordinatorLayout r1, V r2) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout r7, V r8, MotionEvent r9) {
        if (this.touchSlop >= 0) goto L6;
        this.touchSlop = ViewConfiguration.get(r7.getContext()).getScaledTouchSlop();
    L6:
        if (r9.getActionMasked() != 2) goto L20;
        if (this.isBeingDragged == false) goto L20;
        int r0 = this.activePointerId;
        if (r0 != (-1)) goto L12;
        return false;
    L12:
        int r02 = r9.findPointerIndex(r0);
        if (r02 != (-1)) goto L15;
        return false;
    L15:
        int r03 = (int) r9.getY(r02);
        if (Math.abs(r03 - this.lastMotionY) <= this.touchSlop) goto L20;
        this.lastMotionY = r03;
        return true;
    L20:
        if (r9.getActionMasked() != 0) goto L35;
        this.activePointerId = -1;
        int r04 = (int) r9.getX();
        int r1 = (int) r9.getY();
        if (canDragView(r8) == true) goto L24;
    L26:
        boolean r72 = false;
    L27:
        this.isBeingDragged = r72;
        if (r72 == false) goto L35;
        this.lastMotionY = r1;
        this.activePointerId = r9.getPointerId(0);
        ensureVelocityTracker();
        OverScroller r73 = this.scroller;
        if (r73 == null) goto L35;
        if (r73.isFinished() == true) goto L35;
        this.scroller.abortAnimation();
        return true;
    L24:
        if (r7.isPointInChildBounds(r8, r04, r1) == false) goto L26;
        r72 = true;
    L35:
        VelocityTracker r6 = this.velocityTracker;
        if (r6 == null) goto L38;
        r6.addMovement(r9);
    L38:
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout r10, V r11, MotionEvent r12) {
        int r1 = r12.getActionMasked();
        if (r1 != 1) goto L5;
        VelocityTracker r13 = this.velocityTracker;
        if (r13 == null) goto L24;
        r13.addMovement(r12);
        this.velocityTracker.computeCurrentVelocity(1000);
        fling(r10, r11, -getScrollRangeForDragFling(r11), 0, this.velocityTracker.getYVelocity(this.activePointerId));
        boolean r14 = true;
    L25:
        this.isBeingDragged = false;
        this.activePointerId = -1;
        VelocityTracker r2 = this.velocityTracker;
        if (r2 == null) goto L28;
        r2.recycle();
        this.velocityTracker = null;
    L28:
        VelocityTracker r22 = this.velocityTracker;
        if (r22 == null) goto L32;
        r22.addMovement(r12);
    L32:
        if (this.isBeingDragged == true) goto L36;
        if (r14 == true) goto L36;
        return false;
    L36:
        return true;
    L24:
        r14 = false;
        goto L25
    L5:
        if (r1 != 2) goto L7;
        int r15 = r12.findPointerIndex(this.activePointerId);
        if (r15 != (-1)) goto L19;
        return false;
    L19:
        int r16 = (int) r12.getY(r15);
        int r3 = this.lastMotionY - r16;
        this.lastMotionY = r16;
        scroll(r10, r11, r3, getMaxDragOffset(r11), 0);
    L20:
        r14 = false;
        goto L28
    L7:
        if (r1 == 3) goto L24;
        if (r1 != 6) goto L20;
        if (r12.getActionIndex() != 0) goto L14;
        int r17 = 1;
    L15:
        this.activePointerId = r12.getPointerId(r17);
        this.lastMotionY = (int) (r12.getY(r17) + 0.5f);
        goto L20
    L14:
        r17 = 0;
        goto L15
    }

    public final int scroll(CoordinatorLayout r2, V r3, int r4, int r5, int r6) {
        return setHeaderTopBottomOffset(r2, r3, getTopBottomOffsetForScrollingSibling() - r4, r5, r6);
    }

    public int setHeaderTopBottomOffset(CoordinatorLayout r7, V r8, int r9) {
        return setHeaderTopBottomOffset(r7, r8, r9, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int setHeaderTopBottomOffset(CoordinatorLayout r1, V r2, int r3, int r4, int r5) {
        int r12 = getTopAndBottomOffset();
        if (r4 == 0) goto L10;
        if (r12 < r4) goto L12;
        if (r12 > r5) goto L13;
        int r22 = MathUtils.clamp(r3, r4, r5);
        if (r12 == r22) goto L14;
        setTopAndBottomOffset(r22);
        return r12 - r22;
    L14:
        return 0;
    L13:
        return 0;
    L12:
        return 0;
    L10:
        return 0;
    }
}
