package com.google.android.material.listitem;

import android.R;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ViewDragHelper;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ListItemLayout extends FrameLayout {
    private static final TimeInterpolator CUBIC_BEZIER_INTERPOLATOR = null;
    private static final int DEFAULT_SIGNIFICANT_VEL_THRESHOLD = 500;
    private static final int[] FIRST_STATE_SET = null;
    private static final int[] LAST_STATE_SET = null;
    private static final int[] MIDDLE_STATE_SET = null;
    public static final int POSITION_FIRST = 0;
    public static final int POSITION_LAST = 2;
    public static final int POSITION_MIDDLE = 1;
    public static final int POSITION_SINGLE = 3;
    private static final int SETTLING_DURATION = 350;
    private static final int[] SINGLE_STATE_SET = null;
    private RevealableListItem activeSwipeToRevealLayout;
    private View contentView;
    private GestureDetector gestureDetector;
    private int lastStableSwipeState;
    private boolean originalClipToPadding;
    private int originalContentViewLeft;
    private int[] positionState;
    private int revealViewOffset;
    private final StateSettlingTracker stateSettlingTracker;
    private View.AccessibilityDelegate swipeAccessibilityDelegate;
    private int swipeState;
    private View swipeToRevealLayoutLeft;
    private View swipeToRevealLayoutRight;
    private ViewDragHelper viewDragHelper;

    /* renamed from: com.google.android.material.listitem.ListItemLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewDragHelper.Callback {
        final /* synthetic */ ListItemLayout this$0;

        public AnonymousClass1(ListItemLayout r1) {
            this.this$0 = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private int calculateMaxSwipeDistance(RevealableListItem r3) {
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) ((View) r3).getLayoutParams();
            if (r3.getPrimaryActionSwipeMode() == 0) goto L11;
            ViewGroup.MarginLayoutParams r02 = (ViewGroup.MarginLayoutParams) ListItemLayout.access$400(this.this$0).getLayoutParams();
            if (ListItemUtils.isRightAligned((View) r3) == false) goto L7;
            int r32 = r02.leftMargin;
        L8:
            int r2 = ListItemLayout.access$400(this.this$0).getMeasuredWidth();
        L10:
            return r2 + r32;
        L7:
            r32 = r02.rightMargin;
            goto L8
        L11:
            r2 = r3.getIntrinsicWidth() + r0.getMarginStart();
            r32 = r0.getMarginEnd();
            goto L10
        }

        private int calculateTargetSwipeState(int r7, RevealableListItem r8, float r9, int r10) {
            if (ListItemLayout.access$1300(this.this$0, r7) == true) goto L5;
            return 3;
        L5:
            if (r7 != 3) goto L7;
        L6:
            float r2 = r9;
            return calculateTargetSwipeStateForRevealLayout(r10, r2, r8, ListItemLayout.access$1400(this.this$0, r7), ListItemLayout.access$1500(this.this$0, r7));
        L7:
            r9 = -r9;
            goto L6
        }

        private int calculateTargetSwipeStateForRevealLayout(int r6, float r7, RevealableListItem r8, int r9, int r10) {
            boolean r1 = false;
            if (r8.getPrimaryActionSwipeMode() == 0) goto L5;
            boolean r0 = true;
        L7:
            if (r8.getPrimaryActionSwipeMode() != 2) goto L10;
            r1 = true;
        L10:
            if (r7 <= 500.0f) goto L19;
            if (r0 == true) goto L13;
        L17:
            return 4;
        L13:
            if (ListItemLayout.access$1600(this.this$0) != 3) goto L16;
            if (r1 == false) goto L17;
        L16:
            return 5;
        L19:
            if (r7 >= (-500.0f)) goto L25;
            if (r1 == false) goto L22;
        L24:
            return 3;
        L22:
            if (ListItemLayout.access$1600(this.this$0) != 5) goto L24;
            return 4;
        L25:
            if (r0 == true) goto L27;
        L29:
            if (r0 == false) goto L33;
            if (r1 == false) goto L33;
            r9 = r10;
        L33:
            if (Math.abs(r6 - r9) >= Math.abs(r6 - ListItemLayout.access$1700(this.this$0))) goto L38;
            if (r0 == false) goto L37;
            if (r1 == false) goto L37;
            return 5;
        L37:
            return 4;
        L38:
            return 3;
        L27:
            if (Math.abs(r6 - r10) >= Math.abs(r6 - r9)) goto L29;
            return 5;
        L5:
            r0 = false;
            goto L7
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View r4, int r5, int r6) {
            if ((ListItemLayout.access$400(this.this$0) instanceof SwipeableListItem) == true) goto L5;
            return 0;
        L5:
            if (ListItemLayout.access$500(this.this$0) == false) goto L17;
            SwipeableListItem r42 = (SwipeableListItem) ListItemLayout.access$400(this.this$0);
            int r62 = ListItemLayout.access$600(this.this$0);
            int r0 = ListItemLayout.access$600(this.this$0);
            if ((ListItemLayout.access$700(this.this$0) instanceof RevealableListItem) == false) goto L11;
            int r63 = calculateMaxSwipeDistance((RevealableListItem) ListItemLayout.access$700(this.this$0));
            r62 = ListItemLayout.access$600(this.this$0) - (r63 + r42.getSwipeMaxOvershoot());
        L11:
            if ((ListItemLayout.access$800(this.this$0) instanceof RevealableListItem) == false) goto L14;
            int r02 = calculateMaxSwipeDistance((RevealableListItem) ListItemLayout.access$800(this.this$0));
            int r3 = ListItemLayout.access$600(this.this$0);
            r0 = (r02 + r42.getSwipeMaxOvershoot()) + r3;
        L14:
            return Math.max(r62, Math.min(r5, r0));
        L17:
            return 0;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View r3) {
            int r0 = 0;
            if ((ListItemLayout.access$400(this.this$0) instanceof SwipeableListItem) == false) goto L11;
            SwipeableListItem r32 = (SwipeableListItem) ListItemLayout.access$400(this.this$0);
            if ((ListItemLayout.access$800(this.this$0) instanceof RevealableListItem) == false) goto L8;
            r0 = ((RevealableListItem) ListItemLayout.access$800(this.this$0)).getIntrinsicWidth() + r32.getSwipeMaxOvershoot();
        L8:
            if ((ListItemLayout.access$700(this.this$0) instanceof RevealableListItem) == true) goto L10;
            return r0;
        L10:
            return r0 + (((RevealableListItem) ListItemLayout.access$700(this.this$0)).getIntrinsicWidth() + r32.getSwipeMaxOvershoot());
        L11:
            return 0;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View r2, int r3, int r4, int r5, int r6) {
            if (ListItemLayout.access$100(this.this$0) != null) goto L5;
            return;
        L5:
            if ((ListItemLayout.access$400(this.this$0) instanceof SwipeableListItem) == true) goto L7;
            return;
        L7:
            if (ListItemLayout.access$500(this.this$0) == false) goto L19;
            super.onViewPositionChanged(r2, r3, r4, r5, r6);
            ListItemLayout.access$900(this.this$0, r3);
            if (ListItemLayout.access$100(this.this$0).getViewDragState() == 1) goto L12;
            return;
        L12:
            if (ListItemLayout.access$1000(this.this$0) == null) goto L18;
            ListItemLayout r1 = this.this$0;
            ListItemLayout.access$300(r1, 1, ListItemLayout.access$1100(r1, (View) ListItemLayout.access$1000(r1)));
            return;
        L18:
            return;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View r3, float r4, float r5) {
            if ((ListItemLayout.access$400(this.this$0) instanceof SwipeableListItem) == true) goto L5;
            return;
        L5:
            if (ListItemLayout.access$500(this.this$0) == false) goto L22;
            int r32 = r3.getLeft();
            if (r32 != ListItemLayout.access$600(this.this$0)) goto L10;
            return;
        L10:
            if (r32 <= ListItemLayout.access$600(this.this$0)) goto L12;
            int r52 = 3;
        L13:
            ListItemLayout r1 = this.this$0;
            if (r52 != 3) goto L16;
            RevealableListItem r0 = (RevealableListItem) ListItemLayout.access$800(r1);
        L17:
            if (r0 == null) goto L24;
            int r33 = calculateTargetSwipeState(r52, r0, r4, r32);
            ListItemLayout r2 = this.this$0;
            ListItemLayout.access$1200(r2, ListItemLayout.access$400(r2), r33, r52);
            return;
        L24:
            return;
        L16:
            r0 = (RevealableListItem) ListItemLayout.access$700(r1);
            goto L17
        L12:
            r52 = 5;
            goto L13
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View r2, int r3) {
            if ((ListItemLayout.access$400(this.this$0) instanceof SwipeableListItem) == false) goto L8;
            if (((SwipeableListItem) ListItemLayout.access$400(this.this$0)).isSwipeEnabled() == true) goto L8;
            return false;
        L8:
            if (ListItemLayout.access$500(this.this$0) == true) goto L10;
        L12:
            return false;
        L10:
            if (ListItemLayout.access$400(this.this$0) == null) goto L12;
            ListItemLayout.access$100(this.this$0).captureChildView(ListItemLayout.access$400(this.this$0), r3);
            goto L12
        }
    }

    /* renamed from: com.google.android.material.listitem.ListItemLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ ListItemLayout this$0;

        public AnonymousClass2(ListItemLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            if (this.this$0.getParent() == null) goto L7;
            this.this$0.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        L7:
            return false;
        }
    }

    /* renamed from: com.google.android.material.listitem.ListItemLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends View.AccessibilityDelegate {
        final /* synthetic */ ListItemLayout this$0;

        public AnonymousClass3(ListItemLayout r1) {
            this.this$0 = r1;
        }

        private void addSwipeAccessibilityActions(View r5, AccessibilityNodeInfoCompat r6) {
            if ((r5 instanceof ViewGroup) == false) goto L11;
            ViewGroup r52 = (ViewGroup) r5;
            int r0 = 0;
        L6:
            if (r0 >= r52.getChildCount()) goto L15;
            View r1 = r52.getChildAt(r0);
            if (shouldAddAccessibilityAction(r1) == false) goto L10;
            r6.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(getAccessibilityActionId(r1), r1.getContentDescription()));
        L10:
            r0 = r0 + 1;
            goto L6
        L15:
            return;
        }

        private int getAccessibilityActionId(View r1) {
            return r1.getId();
        }

        private boolean performRevealViewAction(View r5, int r6) {
            if ((r5 instanceof ViewGroup) == false) goto L12;
            ViewGroup r52 = (ViewGroup) r5;
            int r0 = 0;
        L6:
            if (r0 >= r52.getChildCount()) goto L12;
            View r2 = r52.getChildAt(r0);
            if (getAccessibilityActionId(r2) == r6) goto L10;
            r0 = r0 + 1;
            goto L6
        L10:
            return r2.performClick();
        L12:
            return false;
        }

        private boolean shouldAddAccessibilityAction(View r1) {
            if (r1.isClickable() == true) goto L5;
            return false;
        L5:
            if (r1.getContentDescription() != null) goto L7;
            return false;
        L7:
            if (r1.isEnabled() == true) goto L9;
            return false;
        L9:
            if (r1.getVisibility() != 0) goto L16;
            return true;
        L16:
            return false;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            AccessibilityNodeInfoCompat r12 = AccessibilityNodeInfoCompat.wrap(r2);
            addSwipeAccessibilityActions(ListItemLayout.access$800(this.this$0), r12);
            addSwipeAccessibilityActions(ListItemLayout.access$700(this.this$0), r12);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View r3, int r4, Bundle r5) {
            if (performRevealViewAction(ListItemLayout.access$800(this.this$0), r4) == false) goto L6;
            return true;
        L6:
            if (performRevealViewAction(ListItemLayout.access$700(this.this$0), r4) == false) goto L9;
            return true;
        L9:
            return super.performAccessibilityAction(r3, r4, r5);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {
    }

    public class StateSettlingTracker {
        private final Runnable continueSettlingRunnable;
        private boolean isContinueSettlingRunnablePosted;
        private int targetRevealGravity;
        private int targetSwipeState;
        final /* synthetic */ ListItemLayout this$0;

        public static /* synthetic */ void $r8$lambda$AzXj3h68wFeaKiCTbCOdmaiCIaI(StateSettlingTracker r2) {
            r2.isContinueSettlingRunnablePosted = false;
            if (ListItemLayout.access$100(r2.this$0) == null) goto L9;
            if (ListItemLayout.access$100(r2.this$0).continueSettling(true) == false) goto L9;
            r2.continueSettlingToState(r2.targetSwipeState, r2.targetRevealGravity);
            return;
        L9:
            if (ListItemLayout.access$200(r2.this$0) != 2) goto L12;
            ListItemLayout.access$300(r2.this$0, r2.targetSwipeState, r2.targetRevealGravity);
            return;
        }

        private StateSettlingTracker(ListItemLayout r1) {
            this.this$0 = r1;
            this.continueSettlingRunnable = new ListItemLayout$StateSettlingTracker$$ExternalSyntheticLambda0(this);
        }

        public /* synthetic */ StateSettlingTracker(ListItemLayout r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ void access$1800(StateSettlingTracker r0, int r1, int r2) {
            r0.continueSettlingToState(r1, r2);
        }

        private void continueSettlingToState(int r1, int r2) {
            this.targetSwipeState = r1;
            this.targetRevealGravity = r2;
            if (this.isContinueSettlingRunnablePosted == true) goto L6;
            this.this$0.post(this.continueSettlingRunnable);
            this.isContinueSettlingRunnablePosted = true;
            return;
        }
    }

    /* renamed from: $r8$lambda$GgC1597LH4x8zQheq-iQrVdVDOY, reason: not valid java name */
    public static /* synthetic */ void m1883$r8$lambda$GgC1597LH4x8zQheqiQrVdVDOY(ListItemLayout r2, boolean r3, int r4, int r5) {
        if (r3 == true) goto L8;
        ViewDragHelper r32 = r2.viewDragHelper;
        if (r32 == null) goto L6;
        r32.abort();
    L6:
        int r33 = r2.getOffsetForSwipeState(r4, r5);
        View r0 = r2.contentView;
        r0.offsetLeftAndRight(r33 - r0.getLeft());
        r2.updateSwipeProgress(r33);
        r2.setSwipeStateInternal(r4, r5);
        return;
    L8:
        r2.startSettling(r2.contentView, r4, r5);
    }

    static {
        FIRST_STATE_SET = new int[]{R.attr.state_first};
        MIDDLE_STATE_SET = new int[]{R.attr.state_middle};
        LAST_STATE_SET = new int[]{R.attr.state_last};
        SINGLE_STATE_SET = new int[]{R.attr.state_single};
        CUBIC_BEZIER_INTERPOLATOR = new PathInterpolator(0.42f, 1.67f, 0.21f, 0.9f);
    }

    public ListItemLayout(Context r2) {
        this(r2, null);
    }

    public ListItemLayout(Context r2, AttributeSet r3) {
        this(r2, r3, com.google.android.material.R.attr.listItemLayoutStyle);
    }

    public ListItemLayout(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, com.google.android.material.R.style.Widget_Material3_ListItemLayout);
    }

    public ListItemLayout(Context r1, AttributeSet r2, int r3, int r4) {
        super(MaterialThemeOverlay.wrap(r1, r2, r3, r4), r2, r3);
        this.swipeState = 3;
        this.lastStableSwipeState = 3;
        this.stateSettlingTracker = new StateSettlingTracker(this, null);
        getContext();
    }

    public static /* synthetic */ ViewDragHelper access$100(ListItemLayout r0) {
        return r0.viewDragHelper;
    }

    public static /* synthetic */ RevealableListItem access$1000(ListItemLayout r0) {
        return r0.activeSwipeToRevealLayout;
    }

    public static /* synthetic */ int access$1100(ListItemLayout r0, View r1) {
        return r0.getAbsoluteRevealGravity(r1);
    }

    public static /* synthetic */ void access$1200(ListItemLayout r0, View r1, int r2, int r3) {
        r0.startSettling(r1, r2, r3);
    }

    public static /* synthetic */ boolean access$1300(ListItemLayout r0, int r1) {
        return r0.swipeToRevealLayoutExistsForGravity(r1);
    }

    public static /* synthetic */ int access$1400(ListItemLayout r0, int r1) {
        return r0.getSwipeRevealViewRevealedOffset(r1);
    }

    public static /* synthetic */ int access$1500(ListItemLayout r0, int r1) {
        return r0.getSwipeToActionOffset(r1);
    }

    public static /* synthetic */ int access$1600(ListItemLayout r0) {
        return r0.lastStableSwipeState;
    }

    public static /* synthetic */ int access$1700(ListItemLayout r0) {
        return r0.getSwipeViewClosedOffset();
    }

    public static /* synthetic */ int access$200(ListItemLayout r0) {
        return r0.swipeState;
    }

    public static /* synthetic */ void access$300(ListItemLayout r0, int r1, int r2) {
        r0.setSwipeStateInternal(r1, r2);
    }

    public static /* synthetic */ View access$400(ListItemLayout r0) {
        return r0.contentView;
    }

    public static /* synthetic */ boolean access$500(ListItemLayout r0) {
        return r0.swipeToRevealLayoutExists();
    }

    public static /* synthetic */ int access$600(ListItemLayout r0) {
        return r0.originalContentViewLeft;
    }

    public static /* synthetic */ View access$700(ListItemLayout r0) {
        return r0.swipeToRevealLayoutRight;
    }

    public static /* synthetic */ View access$800(ListItemLayout r0) {
        return r0.swipeToRevealLayoutLeft;
    }

    public static /* synthetic */ void access$900(ListItemLayout r0, int r1) {
        r0.updateSwipeProgress(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends View & RevealableListItem> T castToView(RevealableListItem r1) {
        return (T) ((View) r1);
    }

    private GestureDetector createGestureDetector() {
        return new GestureDetector(getContext(), new AnonymousClass2(this));
    }

    private View.AccessibilityDelegate createSwipeAccessibilityDelegate() {
        return new AnonymousClass3(this);
    }

    private ViewDragHelper createViewDragHelper() {
        return ViewDragHelper.create(this, new AnonymousClass1(this));
    }

    private boolean ensureSwipeToRevealSetupIfNeeded() {
        if (swipeToRevealLayoutExists() == true) goto L5;
    L21:
        return false;
    L5:
        if (this.contentView == null) goto L21;
        if (this.viewDragHelper != null) goto L11;
        this.viewDragHelper = createViewDragHelper();
    L11:
        if (this.gestureDetector != null) goto L14;
        this.gestureDetector = createGestureDetector();
    L14:
        if (this.swipeAccessibilityDelegate != null) goto L17;
        View.AccessibilityDelegate r0 = createSwipeAccessibilityDelegate();
        this.swipeAccessibilityDelegate = r0;
        this.contentView.setAccessibilityDelegate(r0);
    L17:
        if (getClipToPadding() == false) goto L22;
        this.originalClipToPadding = getClipToPadding();
        setClipToPadding(false);
        return true;
    L22:
        return true;
    }

    private int getAbsoluteHorizontalGravity(int r3) {
        int r32 = GravityCompat.getAbsoluteGravity(r3, getLayoutDirection()) & 7;
        if (r32 != 3) goto L6;
        return 3;
    L6:
        if (r32 != 5) goto L9;
        return 5;
    L9:
        if (getLayoutDirection() != 1) goto L11;
        return 3;
    L11:
        return 5;
    }

    private int getAbsoluteRevealGravity(View r1) {
        if (ListItemUtils.isRightAligned(r1) == false) goto L6;
        return 5;
    L6:
        return 3;
    }

    private int getOffsetForSwipeState(int r2, int r3) {
        if (swipeToRevealLayoutExistsForGravity(r3) == true) goto L5;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("No RevealableListItem with gravity ", r3);
        return 0;
    L5:
        if (r2 == 3) goto L18;
        if (r2 == 4) goto L16;
        if (r2 == 5) goto L11;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid state to get swipe offset: ", r2);
        return 0;
    L11:
        return getSwipeToActionOffset(r3);
    L16:
        return getSwipeRevealViewRevealedOffset(r3);
    L18:
        return getSwipeViewClosedOffset();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int getSwipeRevealViewRevealedOffset(int r4) {
        if (isRevealGravityLeft(r4) == false) goto L5;
        View r0 = this.swipeToRevealLayoutLeft;
    L6:
        if (r0 != 0) goto L9;
        return 0;
    L9:
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        int r02 = (((RevealableListItem) r0).getIntrinsicWidth() + r1.leftMargin) + r1.rightMargin;
        if (isRevealGravityLeft(r4) == false) goto L12;
        int r42 = 1;
    L14:
        return this.originalContentViewLeft + (r42 * r02);
    L12:
        r42 = -1;
        goto L14
    L5:
        r0 = this.swipeToRevealLayoutRight;
        goto L6
    }

    private int getSwipeToActionOffset(int r4) {
        View r0 = this.contentView;
        if (r0 != null) goto L6;
        return 0;
    L6:
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        int r1 = (this.contentView.getMeasuredWidth() + r02.leftMargin) + r02.rightMargin;
        if (isRevealGravityLeft(r4) == false) goto L9;
        int r42 = 1;
    L11:
        return this.originalContentViewLeft + (r42 * r1);
    L9:
        r42 = -1;
        goto L11
    }

    private int getSwipeViewClosedOffset() {
        return this.originalContentViewLeft;
    }

    private boolean isRevealGravityLeft(int r1) {
        if (getAbsoluteHorizontalGravity(r1) != 3) goto L6;
        return true;
    L6:
        return false;
    }

    private void layoutRevealView(View r2, int r3, int r4) {
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        if (ListItemUtils.isRightAligned(r2) == false) goto L5;
        int r42 = r4 - r1.rightMargin;
        int r12 = r42 - r2.getMeasuredWidth();
    L6:
        r2.layout(r12, r2.getTop(), r42, r2.getBottom());
        return;
    L5:
        r12 = r1.leftMargin + r3;
        r42 = r12 + r2.getMeasuredWidth();
        goto L6
    }

    private void maybeSwapRevealLayoutsForGravity() {
        View r0 = this.swipeToRevealLayoutLeft;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        View r3 = this.swipeToRevealLayoutRight;
        if (r3 != null) goto L11;
    L13:
        if (r02 == false) goto L18;
        if (r1 == false) goto L18;
        View r03 = this.swipeToRevealLayoutLeft;
        this.swipeToRevealLayoutLeft = this.swipeToRevealLayoutRight;
        this.swipeToRevealLayoutRight = r03;
        this.revealViewOffset *= -1;
        return;
    L18:
        if (r02 == true) goto L20;
        if (r1 == true) goto L27;
        return;
    L27:
        if (this.swipeToRevealLayoutLeft != null) goto L30;
        this.swipeToRevealLayoutLeft = this.swipeToRevealLayoutRight;
        this.swipeToRevealLayoutRight = null;
        this.revealViewOffset *= -1;
        return;
    L30:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot have more than one RevealableListItem with the same absolute gravity.");
        return;
    L20:
        if (this.swipeToRevealLayoutRight != null) goto L23;
        this.swipeToRevealLayoutRight = this.swipeToRevealLayoutLeft;
        this.swipeToRevealLayoutLeft = null;
        this.revealViewOffset *= -1;
        return;
    L23:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot have more than one RevealableListItem with the same absolute gravity.");
        return;
    L11:
        if (ListItemUtils.isRightAligned(r3) == true) goto L13;
        r1 = true;
        goto L13
    L5:
        if (ListItemUtils.isRightAligned(r0) == false) goto L7;
        r02 = true;
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setSwipeStateInternal(int r3, int r4) {
        int r42 = getAbsoluteHorizontalGravity(r4);
        if (r3 != this.swipeState) goto L10;
        Object r0 = this.activeSwipeToRevealLayout;
        if (r0 != null) goto L7;
        return;
    L7:
        if (getAbsoluteRevealGravity((View) r0) != r42) goto L10;
        return;
    L10:
        if (r3 == 3) goto L15;
        if (swipeToRevealLayoutExistsForGravity(r42) == true) goto L15;
        return;
    L15:
        if (r3 != 5) goto L22;
        RevealableListItem r02 = this.activeSwipeToRevealLayout;
        if (r02 != null) goto L19;
        return;
    L19:
        if (r02.getPrimaryActionSwipeMode() != 0) goto L22;
        return;
    L22:
        if (isRevealGravityLeft(r42) == false) goto L24;
        RevealableListItem r03 = (RevealableListItem) this.swipeToRevealLayoutLeft;
    L25:
        this.activeSwipeToRevealLayout = r03;
        this.swipeState = r3;
        if (r3 != 1) goto L28;
    L30:
        if (r03 == 0) goto L32;
        r42 = ((FrameLayout.LayoutParams) ((View) r03).getLayoutParams()).gravity;
    L32:
        SwipeableListItem r04 = (SwipeableListItem) this.contentView;
        View r2 = castToView(this.activeSwipeToRevealLayout);
        if (r42 != (-1)) goto L35;
        r42 = 8388613;
    L35:
        r04.onSwipeStateChanged(r3, r2, r42);
        return;
    L28:
        if (r3 == 2) goto L30;
        this.lastStableSwipeState = r3;
        goto L30
    L24:
        r03 = (RevealableListItem) this.swipeToRevealLayoutRight;
        goto L25
    }

    private void startSettling(View r8, int r9, int r10) {
        if (this.viewDragHelper != null) goto L5;
        return;
    L5:
        int r3 = getOffsetForSwipeState(r9, r10);
        ViewDragHelper r1 = this.viewDragHelper;
        if (r9 != 4) goto L8;
        boolean r82 = r1.smoothSlideViewTo(r8, r3, r8.getTop(), SETTLING_DURATION, (Interpolator) CUBIC_BEZIER_INTERPOLATOR);
    L9:
        if (r82 == false) goto L12;
        setSwipeStateInternal(2, r10);
        StateSettlingTracker.access$1800(this.stateSettlingTracker, r9, r10);
        return;
    L12:
        setSwipeStateInternal(r9, r10);
        return;
    L8:
        r82 = r1.smoothSlideViewTo(r8, r3, r8.getTop());
        goto L9
    }

    private boolean swipeToRevealLayoutExists() {
        if ((this.swipeToRevealLayoutLeft instanceof RevealableListItem) == false) goto L5;
        return true;
    L5:
        if ((this.swipeToRevealLayoutRight instanceof RevealableListItem) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean swipeToRevealLayoutExistsForGravity(int r1) {
        maybeSwapRevealLayoutsForGravity();
        if (isRevealGravityLeft(r1) == false) goto L7;
        return this.swipeToRevealLayoutLeft instanceof RevealableListItem;
    L7:
        return this.swipeToRevealLayoutRight instanceof RevealableListItem;
    }

    private void updateAlphaFade(int r2, int r3) {
        if (r3 != r2) goto L4;
        int r32 = (getSwipeViewClosedOffset() + r2) / 2;
    L5:
        this.contentView.setAlpha(AnimationUtils.lerp(1.0f, 0.0f, (this.revealViewOffset - r32) / (r2 - r32)));
        return;
    L4:
        r32 = (r3 + r2) / 2;
        goto L5
    }

    private void updateSwipeProgress(int r7) {
        if ((this.contentView instanceof SwipeableListItem) == true) goto L5;
        return;
    L5:
        if (swipeToRevealLayoutExists() == false) goto L49;
        int r72 = r7 - this.originalContentViewLeft;
        this.revealViewOffset = r72;
        boolean r0 = true;
        int r1 = 0;
        if (r72 <= 0) goto L10;
        boolean r2 = true;
    L11:
        if (r72 < 0) goto L14;
        r0 = false;
    L14:
        if (r2 == false) goto L18;
        KeyEvent.Callback r73 = this.swipeToRevealLayoutLeft;
        if ((r73 instanceof RevealableListItem) == false) goto L18;
        this.activeSwipeToRevealLayout = (RevealableListItem) r73;
    L22:
        FrameLayout.LayoutParams r74 = (FrameLayout.LayoutParams) this.contentView.getLayoutParams();
        View r3 = this.swipeToRevealLayoutLeft;
        if ((r3 instanceof RevealableListItem) == false) goto L29;
        FrameLayout.LayoutParams r32 = (FrameLayout.LayoutParams) r3.getLayoutParams();
        int r33 = Math.max(0, ((Math.abs(this.originalContentViewLeft - this.contentView.getLeft()) - r74.leftMargin) - r32.getMarginStart()) - r32.getMarginEnd());
        if (r2 == true) goto L28;
        r33 = 0;
    L28:
        ((RevealableListItem) this.swipeToRevealLayoutLeft).setRevealedWidth(r33);
    L29:
        View r34 = this.swipeToRevealLayoutRight;
        if ((r34 instanceof RevealableListItem) == false) goto L35;
        FrameLayout.LayoutParams r35 = (FrameLayout.LayoutParams) r34.getLayoutParams();
        int r75 = Math.max(0, ((Math.abs(this.originalContentViewLeft - this.contentView.getLeft()) - r74.rightMargin) - r35.getMarginStart()) - r35.getMarginEnd());
        if (r0 == false) goto L34;
        r1 = r75;
    L34:
        ((RevealableListItem) this.swipeToRevealLayoutRight).setRevealedWidth(r1);
    L35:
        ((SwipeableListItem) this.contentView).onSwipe(this.revealViewOffset);
        if (r0 == true) goto L38;
    L41:
        if (r2 == true) goto L43;
    L46:
        this.contentView.setAlpha(1.0f);
        return;
    L43:
        if ((this.swipeToRevealLayoutLeft instanceof RevealableListItem) == false) goto L46;
        updateAlphaFade(getSwipeToActionOffset(3), getSwipeRevealViewRevealedOffset(3));
        return;
    L38:
        if ((this.swipeToRevealLayoutRight instanceof RevealableListItem) == false) goto L41;
        updateAlphaFade(getSwipeToActionOffset(5), getSwipeRevealViewRevealedOffset(5));
        return;
    L18:
        if (r0 == false) goto L22;
        KeyEvent.Callback r76 = this.swipeToRevealLayoutRight;
        if ((r76 instanceof RevealableListItem) == false) goto L22;
        this.activeSwipeToRevealLayout = (RevealableListItem) r76;
        goto L22
    L10:
        r2 = false;
        goto L11
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public void addView(View r1, int r2, ViewGroup.LayoutParams r3) {
        super.addView(r1, r2, r3);
        if ((r1 instanceof RevealableListItem) == false) goto L19;
        if (ListItemUtils.isRightAligned(r1) == false) goto L12;
        if (this.swipeToRevealLayoutRight != null) goto L9;
        this.swipeToRevealLayoutRight = r1;
    L14:
        ((RevealableListItem) r1).setRevealedWidth(0);
        r1.setElevation(getElevation() - 1.0f);
        return;
    L9:
        ByteString$$ExternalSyntheticBUOutline0.m("Only one RevealableListItem with end gravity is supported.");
        return;
    L12:
        if (this.swipeToRevealLayoutLeft != null) goto L16;
        this.swipeToRevealLayoutLeft = r1;
        goto L14
    L16:
        ByteString$$ExternalSyntheticBUOutline0.m("Only one RevealableListItem with start gravity is supported.");
        return;
    L19:
        if (this.contentView == null) goto L26;
        if ((r1 instanceof SwipeableListItem) == false) goto L26;
        ByteString$$ExternalSyntheticBUOutline0.m("Only one SwipeableListItem view is allowed in a ListItemLayout.");
        return;
    L26:
        if ((r1 instanceof SwipeableListItem) == false) goto L29;
        this.contentView = r1;
        return;
    }

    public int getSwipeState() {
        return this.swipeState;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int r2) {
        if (this.positionState != null) goto L7;
        return super.onCreateDrawableState(r2);
    L7:
        return View.mergeDrawableStates(super.onCreateDrawableState(r2 + 1), this.positionState);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (ensureSwipeToRevealSetupIfNeeded() == false) goto L14;
        int r0 = r3.getActionMasked();
        if (r0 != 3) goto L7;
    L11:
        this.viewDragHelper.cancel();
        return false;
    L7:
        if (r0 == 1) goto L11;
        this.gestureDetector.onTouchEvent(r3);
        return this.viewDragHelper.shouldInterceptTouchEvent(r3);
    L14:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        maybeSwapRevealLayoutsForGravity();
        if (this.contentView != null) goto L5;
        return;
    L5:
        if (swipeToRevealLayoutExists() == true) goto L7;
        return;
    L7:
        if (ensureSwipeToRevealSetupIfNeeded() == false) goto L17;
        this.originalContentViewLeft = this.contentView.getLeft();
        int r12 = this.contentView.getRight();
        this.contentView.offsetLeftAndRight(this.revealViewOffset);
        View r22 = this.swipeToRevealLayoutLeft;
        if (r22 == null) goto L11;
        layoutRevealView(r22, this.originalContentViewLeft, r12);
    L11:
        View r23 = this.swipeToRevealLayoutRight;
        if (r23 == null) goto L18;
        layoutRevealView(r23, this.originalContentViewLeft, r12);
        return;
    L18:
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (ensureSwipeToRevealSetupIfNeeded() == false) goto L8;
        this.viewDragHelper.processTouchEvent(r3);
        this.gestureDetector.onTouchEvent(r3);
        if (this.viewDragHelper.getViewDragState() != 1) goto L8;
        return true;
    L8:
        return super.onTouchEvent(r3);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View r3) {
        super.onViewRemoved(r3);
        if (r3 != this.swipeToRevealLayoutLeft) goto L6;
        this.swipeToRevealLayoutLeft = null;
    L12:
        if (swipeToRevealLayoutExists() == true) goto L14;
    L17:
        this.viewDragHelper = null;
        this.gestureDetector = null;
        this.swipeAccessibilityDelegate = null;
        setClipToPadding(this.originalClipToPadding);
        return;
    L14:
        if (this.contentView == null) goto L17;
        return;
    L6:
        if (r3 != this.swipeToRevealLayoutRight) goto L9;
        this.swipeToRevealLayoutRight = null;
        goto L12
    L9:
        if (this.contentView != r3) goto L12;
        this.contentView = null;
        goto L12
    }

    public void setSwipeState(int r2, int r3) {
        setSwipeState(r2, r3, true);
    }

    public void setSwipeState(int r3, int r4, boolean r5) {
        if (r3 == 3) goto L12;
        if (r3 == 4) goto L12;
        if (r3 == 5) goto L12;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid swipe state: ", r3);
        return;
    L12:
        if ((this.contentView instanceof SwipeableListItem) == true) goto L14;
    L27:
        f$$ExternalSyntheticBUOutline1.m("ListItemLayout must have a SwipeableListItem child and a RevealableListItem child to be swiped.");
        return;
    L14:
        if (swipeToRevealLayoutExists() == false) goto L27;
        if (r3 != 3) goto L17;
    L21:
        ListItemLayout$$ExternalSyntheticLambda0 r0 = new ListItemLayout$$ExternalSyntheticLambda0(this, r5, r3, r4);
        if (isLaidOut() == false) goto L25;
        r0.run();
        return;
    L25:
        post(r0);
        return;
    L17:
        if (swipeToRevealLayoutExistsForGravity(r4) == true) goto L21;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("No RevealableListItem is defined for the given gravity: ", r4);
    }

    public <T extends View & RevealableListItem> void setSwipeState(int r2, T r3) {
        setSwipeState(r2, r3, true);
    }

    public <T extends View & RevealableListItem> void setSwipeState(int r2, T r3, boolean r4) {
        if (r3 != this.swipeToRevealLayoutLeft) goto L5;
    L9:
        setSwipeState(r2, ((FrameLayout.LayoutParams) r3.getLayoutParams()).gravity, r4);
        return;
    L5:
        if (r3 == this.swipeToRevealLayoutRight) goto L9;
        f$$ExternalSyntheticBUOutline1.m("revealView must be a child of ListItemLayout.");
    }

    public void updateAppearance(int r2) {
        if (r2 != 0) goto L4;
        this.positionState = FIRST_STATE_SET;
    L14:
        refreshDrawableState();
        return;
    L4:
        if (r2 != 1) goto L6;
        this.positionState = MIDDLE_STATE_SET;
        goto L14
    L6:
        if (r2 != 2) goto L8;
        this.positionState = LAST_STATE_SET;
        goto L14
    L8:
        if (r2 != 3) goto L14;
        this.positionState = SINGLE_STATE_SET;
        goto L14
    }

    public void updateAppearance(int r2, int r3) {
        if (r2 < 0) goto L14;
        if (r3 < 0) goto L14;
        if (r3 != 1) goto L8;
        this.positionState = SINGLE_STATE_SET;
    L15:
        refreshDrawableState();
        return;
    L8:
        if (r2 != 0) goto L11;
        this.positionState = FIRST_STATE_SET;
        goto L15
    L11:
        if (r2 != (r3 - 1)) goto L13;
        this.positionState = LAST_STATE_SET;
        goto L15
    L13:
        this.positionState = MIDDLE_STATE_SET;
    L14:
        this.positionState = null;
        goto L15
    }
}
