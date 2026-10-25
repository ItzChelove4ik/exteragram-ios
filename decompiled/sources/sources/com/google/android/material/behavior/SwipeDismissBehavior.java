package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;

/* loaded from: classes5.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    float alphaEndSwipeDistance;
    float alphaStartSwipeDistance;
    private final ViewDragHelper.Callback dragCallback;
    float dragDismissThreshold;
    private boolean interceptingEvents;
    OnDismissListener listener;
    private boolean requestingDisallowInterceptTouchEvent;
    private float sensitivity;
    private boolean sensitivitySet;
    int swipeDirection;
    ViewDragHelper viewDragHelper;

    /* renamed from: com.google.android.material.behavior.SwipeDismissBehavior$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewDragHelper.Callback {
        private static final int INVALID_POINTER_ID = -1;
        private int activePointerId;
        private int originalCapturedViewLeft;
        final /* synthetic */ SwipeDismissBehavior this$0;

        public AnonymousClass1(SwipeDismissBehavior r1) {
            this.this$0 = r1;
            this.activePointerId = -1;
        }

        private boolean shouldDismiss(View r6, float r7) {
            if (r7 != SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) goto L5;
            int r72 = r6.getLeft() - this.originalCapturedViewLeft;
            if (Math.abs(r72) < Math.round(r6.getWidth() * this.this$0.dragDismissThreshold)) goto L32;
            return true;
        L32:
            return false;
        L5:
            if (r6.getLayoutDirection() != 1) goto L7;
            boolean r62 = true;
        L8:
            int r5 = this.this$0.swipeDirection;
            if (r5 != 2) goto L11;
            return true;
        L11:
            if (r5 != 0) goto L20;
            if (r62 == true) goto L14;
            if (r7 <= SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) goto L19;
            return true;
        L19:
            return false;
        L14:
            if (r7 >= SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) goto L16;
            return true;
        L16:
            return false;
        L20:
            if (r5 != 1) goto L28;
            if (r62 == false) goto L26;
            if (r7 <= SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) goto L24;
            return true;
        L24:
            return false;
        L26:
            if (r7 >= SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) goto L28;
            return true;
        L28:
            return false;
        L7:
            r62 = false;
            goto L8
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View r3, int r4, int r5) {
            if (r3.getLayoutDirection() != 1) goto L5;
            boolean r52 = true;
        L6:
            int r1 = this.this$0.swipeDirection;
            if (r1 != 0) goto L13;
            int r0 = this.originalCapturedViewLeft;
            if (r52 == false) goto L11;
            r0 = r0 - r3.getWidth();
            int r2 = this.originalCapturedViewLeft;
        L20:
            return SwipeDismissBehavior.clamp(r0, r4, r2);
        L11:
            int r22 = r3.getWidth();
        L12:
            r2 = r22 + r0;
            goto L20
        L13:
            if (r1 != 1) goto L18;
            r0 = this.originalCapturedViewLeft;
            if (r52 == false) goto L17;
            r22 = r3.getWidth();
            goto L12
        L17:
            r0 = r0 - r3.getWidth();
            r2 = this.originalCapturedViewLeft;
            goto L20
        L18:
            r0 = this.originalCapturedViewLeft - r3.getWidth();
            r2 = this.originalCapturedViewLeft + r3.getWidth();
            goto L20
        L5:
            r52 = false;
            goto L6
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View r1, int r2, int r3) {
            return r1.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View r1) {
            return r1.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewCaptured(View r2, int r3) {
            this.activePointerId = r3;
            this.originalCapturedViewLeft = r2.getLeft();
            ViewParent r22 = r2.getParent();
            if (r22 == null) goto L6;
            SwipeDismissBehavior.access$002(this.this$0, true);
            r22.requestDisallowInterceptTouchEvent(true);
            SwipeDismissBehavior.access$002(this.this$0, false);
            return;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int r1) {
            OnDismissListener r0 = this.this$0.listener;
            if (r0 == null) goto L6;
            r0.onDragStateChanged(r1);
            return;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View r2, int r3, int r4, int r5, int r6) {
            float r42 = r2.getWidth() * this.this$0.alphaStartSwipeDistance;
            float r52 = r2.getWidth() * this.this$0.alphaEndSwipeDistance;
            float r1 = Math.abs(r3 - this.originalCapturedViewLeft);
            if (r1 > r42) goto L7;
            r2.setAlpha(1.0f);
            return;
        L7:
            if (r1 < r52) goto L10;
            r2.setAlpha(SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE);
            return;
        L10:
            r2.setAlpha(SwipeDismissBehavior.clamp(SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE, 1.0f - SwipeDismissBehavior.fraction(r42, r52, r1), 1.0f));
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View r3, float r4, float r5) {
            this.activePointerId = -1;
            int r52 = r3.getWidth();
            if (shouldDismiss(r3, r4) == true) goto L5;
            int r0 = this.originalCapturedViewLeft;
            boolean r42 = false;
        L14:
            if (this.this$0.viewDragHelper.settleCapturedViewAt(r0, r3.getTop()) == false) goto L17;
            r3.postOnAnimation(new SettleRunnable(this.this$0, r3, r42));
            return;
        L17:
            if (r42 == false) goto L22;
            OnDismissListener r2 = this.this$0.listener;
            if (r2 == null) goto L23;
            r2.onDismiss(r3);
            return;
        L23:
            return;
        L22:
            return;
        L5:
            if (r4 < SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) goto L10;
            int r43 = r3.getLeft();
            int r02 = this.originalCapturedViewLeft;
            if (r43 < r02) goto L10;
            r0 = r02 + r52;
        L11:
            r42 = true;
        L10:
            r0 = this.originalCapturedViewLeft - r52;
            goto L11
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View r3, int r4) {
            int r0 = this.activePointerId;
            if (r0 == (-1)) goto L6;
            if (r0 == r4) goto L6;
            return false;
        L6:
            if (this.this$0.canSwipeDismissView(r3) == false) goto L11;
            return true;
        L11:
            return false;
        }
    }

    /* renamed from: com.google.android.material.behavior.SwipeDismissBehavior$2, reason: invalid class name */
    public class AnonymousClass2 implements AccessibilityViewCommand {
        final /* synthetic */ SwipeDismissBehavior this$0;

        public AnonymousClass2(SwipeDismissBehavior r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(View r3, AccessibilityViewCommand.CommandArguments r4) {
            boolean r0 = false;
            if (this.this$0.canSwipeDismissView(r3) == true) goto L5;
            return false;
        L5:
            if (r3.getLayoutDirection() != 1) goto L7;
            r0 = true;
        L7:
            int r42 = this.this$0.swipeDirection;
            if (r42 != 0) goto L10;
            if (r0 == false) goto L10;
        L12:
            int r43 = -r3.getWidth();
        L14:
            ViewCompat.offsetLeftAndRight(r3, r43);
            r3.setAlpha(SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE);
            OnDismissListener r2 = this.this$0.listener;
            if (r2 == null) goto L17;
            r2.onDismiss(r3);
        L17:
            return true;
        L10:
            if (r42 != 1) goto L13;
            if (r0 == false) goto L12;
        L13:
            r43 = r3.getWidth();
            goto L14
        }
    }

    public interface OnDismissListener {
        void onDismiss(View r1);

        void onDragStateChanged(int r1);
    }

    public class SettleRunnable implements Runnable {
        private final boolean dismiss;
        final /* synthetic */ SwipeDismissBehavior this$0;
        private final View view;

        public SettleRunnable(SwipeDismissBehavior r1, View r2, boolean r3) {
            this.this$0 = r1;
            this.view = r2;
            this.dismiss = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper r0 = this.this$0.viewDragHelper;
            if (r0 == null) goto L9;
            if (r0.continueSettling(true) == false) goto L9;
            this.view.postOnAnimation(this);
            return;
        L9:
            if (this.dismiss == false) goto L14;
            OnDismissListener r02 = this.this$0.listener;
            if (r02 == null) goto L15;
            r02.onDismiss(this.view);
            return;
        L15:
            return;
        }
    }

    public SwipeDismissBehavior() {
        this.sensitivity = DEFAULT_ALPHA_START_DISTANCE;
        this.swipeDirection = 2;
        this.dragDismissThreshold = 0.5f;
        this.alphaStartSwipeDistance = DEFAULT_ALPHA_START_DISTANCE;
        this.alphaEndSwipeDistance = 0.5f;
        this.dragCallback = new AnonymousClass1(this);
    }

    public static /* synthetic */ boolean access$002(SwipeDismissBehavior r0, boolean r1) {
        r0.requestingDisallowInterceptTouchEvent = r1;
        return r1;
    }

    public static float clamp(float r0, float r1, float r2) {
        return Math.min(Math.max(r0, r1), r2);
    }

    public static int clamp(int r0, int r1, int r2) {
        return Math.min(Math.max(r0, r1), r2);
    }

    private void ensureViewDragHelper(ViewGroup r3) {
        if (this.viewDragHelper == null) goto L5;
        return;
    L5:
        if (this.sensitivitySet == false) goto L7;
        ViewDragHelper r32 = ViewDragHelper.create(r3, this.sensitivity, this.dragCallback);
    L8:
        this.viewDragHelper = r32;
        return;
    L7:
        r32 = ViewDragHelper.create(r3, this.dragCallback);
        goto L8
    }

    public static float fraction(float r0, float r1, float r2) {
        return (r2 - r0) / (r1 - r0);
    }

    private void updateAccessibilityActions(View r3) {
        ViewCompat.removeAccessibilityAction(r3, 1048576);
        if (canSwipeDismissView(r3) == false) goto L6;
        ViewCompat.replaceAccessibilityAction(r3, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, null, new AnonymousClass2(this));
        return;
    }

    public boolean canSwipeDismissView(View r1) {
        return true;
    }

    public int getDragState() {
        ViewDragHelper r0 = this.viewDragHelper;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getViewDragState();
    }

    public OnDismissListener getListener() {
        return this.listener;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout r5, V r6, MotionEvent r7) {
        boolean r0 = this.interceptingEvents;
        int r1 = r7.getActionMasked();
        if (r1 == 0) goto L9;
        if (r1 != 1) goto L6;
    L8:
        this.interceptingEvents = false;
    L10:
        if (r0 == false) goto L16;
        ensureViewDragHelper(r5);
        if (this.requestingDisallowInterceptTouchEvent == true) goto L16;
        if (this.viewDragHelper.shouldInterceptTouchEvent(r7) == false) goto L16;
        return true;
    L16:
        return false;
    L6:
        if (r1 == 3) goto L8;
    L9:
        r0 = r5.isPointInChildBounds(r6, (int) r7.getX(), (int) r7.getY());
        this.interceptingEvents = r0;
        goto L10
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout r1, V r2, int r3) {
        boolean r12 = super.onLayoutChild(r1, r2, r3);
        if (r2.getImportantForAccessibility() != 0) goto L5;
        r2.setImportantForAccessibility(1);
        updateAccessibilityActions(r2);
    L5:
        return r12;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout r1, V r2, MotionEvent r3) {
        if (this.viewDragHelper != null) goto L5;
        return false;
    L5:
        if (this.requestingDisallowInterceptTouchEvent == true) goto L7;
    L8:
        this.viewDragHelper.processTouchEvent(r3);
        return true;
    L7:
        if (r3.getActionMasked() != 3) goto L8;
        return true;
    }

    public void setDragDismissDistance(float r3) {
        this.dragDismissThreshold = clamp(DEFAULT_ALPHA_START_DISTANCE, r3, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float r3) {
        this.alphaEndSwipeDistance = clamp(DEFAULT_ALPHA_START_DISTANCE, r3, 1.0f);
    }

    public void setListener(OnDismissListener r1) {
        this.listener = r1;
    }

    public void setSensitivity(float r1) {
        this.sensitivity = r1;
        this.sensitivitySet = true;
    }

    public void setStartAlphaSwipeDistance(float r3) {
        this.alphaStartSwipeDistance = clamp(DEFAULT_ALPHA_START_DISTANCE, r3, 1.0f);
    }

    public void setSwipeDirection(int r1) {
        this.swipeDirection = r1;
    }
}
