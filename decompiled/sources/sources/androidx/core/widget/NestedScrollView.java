package androidx.core.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.R$attr;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.DifferentialMotionFlingController;
import androidx.core.view.DifferentialMotionFlingTarget;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ScrollFeedbackProviderCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import java.util.ArrayList;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class NestedScrollView extends FrameLayout implements NestedScrollingParent3, NestedScrollingChild {
    private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = null;
    private static final float DECELERATION_RATE = 0.0f;
    private static final int[] SCROLLVIEW_STYLEABLE = null;
    private int mActivePointerId;
    private final NestedScrollingChildHelper mChildHelper;
    private View mChildToScrollTo;
    DifferentialMotionFlingController mDifferentialMotionFlingController;
    final DifferentialMotionFlingTargetImpl mDifferentialMotionFlingTarget;
    public EdgeEffect mEdgeGlowBottom;
    public EdgeEffect mEdgeGlowTop;
    private boolean mFillViewport;
    private boolean mIsBeingDragged;
    private boolean mIsLaidOut;
    private boolean mIsLayoutDirty;
    private int mLastMotionY;
    private long mLastScroll;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private OnScrollChangeListener mOnScrollChangeListener;
    private final NestedScrollingParentHelper mParentHelper;
    private final float mPhysicalCoeff;
    private SavedState mSavedState;
    private final int[] mScrollConsumed;
    ScrollFeedbackProviderCompat mScrollFeedbackProvider;
    private final int[] mScrollOffset;
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        public AccessibilityDelegate() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View r1, AccessibilityEvent r2) {
            super.onInitializeAccessibilityEvent(r1, r2);
            NestedScrollView r12 = (NestedScrollView) r1;
            r2.setClassName(ScrollView.class.getName());
            if (r12.getScrollRange() <= 0) goto L5;
            boolean r0 = true;
        L6:
            r2.setScrollable(r0);
            r2.setScrollX(r12.getScrollX());
            r2.setScrollY(r12.getScrollY());
            AccessibilityRecordCompat.setMaxScrollX(r2, r12.getScrollX());
            AccessibilityRecordCompat.setMaxScrollY(r2, r12.getScrollRange());
            return;
        L5:
            r0 = false;
            goto L6
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r2, AccessibilityNodeInfoCompat r3) {
            super.onInitializeAccessibilityNodeInfo(r2, r3);
            NestedScrollView r22 = (NestedScrollView) r2;
            r3.setClassName(ScrollView.class.getName());
            if (r22.isEnabled() == false) goto L13;
            int r1 = r22.getScrollRange();
            if (r1 <= 0) goto L14;
            r3.setScrollable(true);
            if (r22.getScrollY() <= 0) goto L10;
            r3.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
            r3.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
        L10:
            if (r22.getScrollY() >= r1) goto L15;
            r3.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
            r3.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
            return;
        L15:
            return;
        L14:
            return;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View r4, int r5, Bundle r6) {
            if (super.performAccessibilityAction(r4, r5, r6) == false) goto L5;
            return true;
        L5:
            NestedScrollView r42 = (NestedScrollView) r4;
            if (r42.isEnabled() == true) goto L8;
            return false;
        L8:
            int r3 = r42.getHeight();
            Rect r1 = new Rect();
            if (r42.getMatrix().isIdentity() == false) goto L14;
            if (r42.getGlobalVisibleRect(r1) == false) goto L14;
            r3 = r1.height();
        L14:
            if (r5 != 4096) goto L16;
        L27:
            int r32 = (r3 - r42.getPaddingBottom()) - r42.getPaddingTop();
            int r33 = Math.min(r42.getScrollY() + r32, r42.getScrollRange());
            if (r33 == r42.getScrollY()) goto L31;
            r42.smoothScrollTo(0, r33, true);
            return true;
        L31:
            return false;
        L16:
            if (r5 != 8192) goto L18;
        L22:
            int r34 = (r3 - r42.getPaddingBottom()) - r42.getPaddingTop();
            int r35 = Math.max(r42.getScrollY() - r34, 0);
            if (r35 == r42.getScrollY()) goto L26;
            r42.smoothScrollTo(0, r35, true);
            return true;
        L26:
            return false;
        L18:
            if (r5 == 16908344) goto L22;
            if (r5 == 16908346) goto L27;
            return false;
        }
    }

    public static final class Api35Impl {
        public static void setFrameContentVelocity(View r0, float r1) {
            r0.setFrameContentVelocity(r1);     // Catch: LinkageError -> L4
            return;
        }
    }

    public class DifferentialMotionFlingTargetImpl implements DifferentialMotionFlingTarget {
        final /* synthetic */ NestedScrollView this$0;

        public DifferentialMotionFlingTargetImpl(NestedScrollView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.DifferentialMotionFlingTarget
        public float getScaledScrollFactor() {
            return -this.this$0.getVerticalScrollFactorCompat();
        }

        @Override // androidx.core.view.DifferentialMotionFlingTarget
        public boolean startDifferentialMotionFling(float r2) {
            if (r2 != 0.0f) goto L6;
            return false;
        L6:
            stopDifferentialMotionFling();
            this.this$0.fling((int) r2);
            return true;
        }

        @Override // androidx.core.view.DifferentialMotionFlingTarget
        public void stopDifferentialMotionFling() {
            NestedScrollView.access$000(this.this$0).abortAnimation();
        }
    }

    public interface OnScrollChangeListener {
        void onScrollChange(NestedScrollView r1, int r2, int r3, int r4, int r5);
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        public int scrollPosition;

        /* renamed from: androidx.core.widget.NestedScrollView$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r1) {
                return new SavedState(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r1) {
            super(r1);
            this.scrollPosition = r1.readInt();
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeInt(this.scrollPosition);
        }
    }

    static {
        DECELERATION_RATE = (float) (Math.log(0.78d) / Math.log(0.9d));
        ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
        SCROLLVIEW_STYLEABLE = new int[]{R.attr.fillViewport};
    }

    public NestedScrollView(Context r2) {
        this(r2, null);
    }

    public NestedScrollView(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.nestedScrollViewStyle);
    }

    public NestedScrollView(Context r6, AttributeSet r7, int r8) {
        super(r6, r7, r8);
        this.mTempRect = new Rect();
        this.mIsLayoutDirty = true;
        this.mIsLaidOut = false;
        this.mChildToScrollTo = null;
        this.mIsBeingDragged = false;
        this.mSmoothScrollingEnabled = true;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        DifferentialMotionFlingTargetImpl r2 = new DifferentialMotionFlingTargetImpl(this);
        this.mDifferentialMotionFlingTarget = r2;
        this.mDifferentialMotionFlingController = new DifferentialMotionFlingController(getContext(), r2);
        this.mEdgeGlowTop = EdgeEffectCompat.create(r6, r7);
        this.mEdgeGlowBottom = EdgeEffectCompat.create(r6, r7);
        this.mPhysicalCoeff = ((r6.getResources().getDisplayMetrics().density * 160.0f) * 386.0878f) * 0.84f;
        initScrollView();
        TypedArray r62 = r6.obtainStyledAttributes(r7, SCROLLVIEW_STYLEABLE, r8, 0);
        setFillViewport(r62.getBoolean(0, false));
        r62.recycle();
        this.mParentHelper = new NestedScrollingParentHelper(this);
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
    }

    private void abortAnimatedScroll() {
        this.mScroller.abortAnimation();
        stopNestedScroll(1);
    }

    public static /* synthetic */ OverScroller access$000(NestedScrollView r0) {
        return r0.mScroller;
    }

    private boolean canOverScroll() {
        int r0 = getOverScrollMode();
        if (r0 == 0) goto L10;
        if (r0 == 1) goto L6;
        return false;
    L6:
        if (getScrollRange() > 0) goto L10;
        return false;
    L10:
        return true;
    }

    private boolean canScroll() {
        if (getChildCount() <= 0) goto L8;
        View r0 = getChildAt(0);
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        if (((r0.getHeight() + r2.topMargin) + r2.bottomMargin) <= ((getHeight() - getPaddingTop()) - getPaddingBottom())) goto L8;
        return true;
    L8:
        return false;
    }

    private static int clamp(int r1, int r2, int r3) {
        if (r2 >= r3) goto L10;
        if (r1 >= 0) goto L6;
        return 0;
    L6:
        if ((r2 + r1) > r3) goto L8;
        return r1;
    L8:
        return r3 - r2;
    L10:
        return 0;
    }

    private void doScrollY(int r3) {
        if (r3 != 0) goto L4;
        return;
    L4:
        if (this.mSmoothScrollingEnabled == false) goto L7;
        smoothScrollBy(0, r3);
        return;
    L7:
        scrollBy(0, r3);
    }

    private boolean edgeEffectFling(int r4) {
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) == 0.0f) goto L11;
        if (shouldAbsorb(this.mEdgeGlowTop, r4) == false) goto L8;
        this.mEdgeGlowTop.onAbsorb(r4);
        return true;
    L8:
        fling(-r4);
        return true;
    L11:
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0f) goto L18;
        int r42 = -r4;
        if (shouldAbsorb(this.mEdgeGlowBottom, r42) == false) goto L16;
        this.mEdgeGlowBottom.onAbsorb(r42);
        return true;
    L16:
        fling(r42);
        return true;
    L18:
        return false;
    }

    private void endTouchDrag() {
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        recycleVelocityTracker();
        stopNestedScroll(0);
        this.mEdgeGlowTop.onRelease();
        this.mEdgeGlowBottom.onRelease();
    }

    private View findFocusableViewInBounds(boolean r12, int r13, int r14) {
        ArrayList<View> r11 = getFocusables(2);
        int r0 = r11.size();
        View r1 = null;
        int r3 = 0;
        boolean r4 = false;
    L3:
        if (r3 >= r0) goto L31;
        View r5 = r11.get(r3);
        int r6 = r5.getTop();
        int r7 = r5.getBottom();
        if (r13 >= r7) goto L30;
        if (r6 >= r14) goto L30;
        if (r13 >= r6) goto L11;
        if (r7 >= r14) goto L11;
        boolean r9 = true;
    L12:
        if (r1 != null) goto L14;
        r1 = r5;
        r4 = r9;
        goto L30
    L14:
        if (r12 == true) goto L16;
    L17:
        if (r12 == false) goto L19;
    L21:
        boolean r62 = false;
    L22:
        if (r4 == false) goto L26;
        if (r9 == false) goto L30;
        if (r62 == false) goto L30;
    L29:
        r1 = r5;
        goto L30
    L26:
        if (r9 == false) goto L28;
        r1 = r5;
        r4 = true;
        goto L30
    L28:
        if (r62 == false) goto L30;
    L19:
        if (r7 <= r1.getBottom()) goto L21;
    L20:
        r62 = true;
        goto L22
    L16:
        if (r6 < r1.getTop()) goto L20;
    L11:
        r9 = false;
    L30:
        r3 = r3 + 1;
        goto L3
    L31:
        return r1;
    }

    private ScrollFeedbackProviderCompat getScrollFeedbackProvider() {
        if (this.mScrollFeedbackProvider != null) goto L6;
        this.mScrollFeedbackProvider = ScrollFeedbackProviderCompat.createProvider(this);
    L6:
        return this.mScrollFeedbackProvider;
    }

    private float getSplineFlingDistance(int r9) {
        double r2 = Math.log((Math.abs(r9) * 0.35f) / (this.mPhysicalCoeff * 0.015f));
        float r92 = DECELERATION_RATE;
        return (float) ((this.mPhysicalCoeff * 0.015f) * Math.exp((r92 / (r92 - 1.0d)) * r2));
    }

    private boolean inChild(int r4, int r5) {
        if (getChildCount() <= 0) goto L14;
        int r0 = getScrollY();
        View r3 = getChildAt(0);
        if (r5 < (r3.getTop() - r0)) goto L14;
        if (r5 >= (r3.getBottom() - r0)) goto L14;
        if (r4 < r3.getLeft()) goto L14;
        if (r4 >= r3.getRight()) goto L14;
        return true;
    L14:
        return false;
    }

    private void initOrResetVelocityTracker() {
        VelocityTracker r0 = this.mVelocityTracker;
        if (r0 != null) goto L6;
        this.mVelocityTracker = VelocityTracker.obtain();
        return;
    L6:
        r0.clear();
    }

    private void initScrollView() {
        this.mScroller = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration r0 = ViewConfiguration.get(getContext());
        this.mTouchSlop = r0.getScaledTouchSlop();
        this.mMinimumVelocity = r0.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = r0.getScaledMaximumFlingVelocity();
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker != null) goto L6;
        this.mVelocityTracker = VelocityTracker.obtain();
        return;
    }

    private void initializeTouchDrag(int r1, int r2) {
        this.mLastMotionY = r1;
        this.mActivePointerId = r2;
        startNestedScroll(2, 0);
    }

    private boolean isOffScreen(View r3) {
        return !isWithinDeltaOfScreen(r3, 0, getHeight());
    }

    private static boolean isViewDescendantOf(View r2, View r3) {
        if (r2 != r3) goto L5;
        return true;
    L5:
        Object r22 = r2.getParent();
        if ((r22 instanceof ViewGroup) == true) goto L8;
        return false;
    L8:
        if (isViewDescendantOf((View) r22, r3) == false) goto L12;
        return true;
    L12:
        return false;
    }

    private boolean isWithinDeltaOfScreen(View r2, int r3, int r4) {
        r2.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(r2, this.mTempRect);
        if ((this.mTempRect.bottom + r3) >= getScrollY()) goto L5;
        return false;
    L5:
        if ((this.mTempRect.top - r3) > (getScrollY() + r4)) goto L10;
        return true;
    L10:
        return false;
    }

    private void onNestedScrollInternal(int r11, int r12, int[] r13) {
        int r0 = getScrollY();
        scrollBy(0, r11);
        int r4 = getScrollY() - r0;
        if (r13 == null) goto L5;
        r13[1] = r13[1] + r4;
    L5:
        NestedScrollingChildHelper r2 = this.mChildHelper;
        r2.dispatchNestedScroll(0, r4, 0, r11 - r4, null, r12, r13);
    }

    private void onSecondaryPointerUp(MotionEvent r4) {
        int r0 = r4.getActionIndex();
        if (r4.getPointerId(r0) != this.mActivePointerId) goto L11;
        if (r0 != 0) goto L6;
        int r02 = 1;
    L7:
        this.mLastMotionY = (int) r4.getY(r02);
        this.mActivePointerId = r4.getPointerId(r02);
        VelocityTracker r3 = this.mVelocityTracker;
        if (r3 == null) goto L12;
        r3.clear();
        return;
    L12:
        return;
    L6:
        r02 = 0;
        goto L7
    }

    private void recycleVelocityTracker() {
        VelocityTracker r0 = this.mVelocityTracker;
        if (r0 == null) goto L6;
        r0.recycle();
        this.mVelocityTracker = null;
        return;
    }

    private int releaseVerticalGlow(int r4, float r5) {
        float r52 = r5 / getWidth();
        float r42 = r4 / getHeight();
        float r1 = 0.0f;
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) == 0.0f) goto L9;
        float r43 = -EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, -r42, r52);
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) != 0.0f) goto L7;
        this.mEdgeGlowTop.onRelease();
    L7:
        r1 = r43;
    L13:
        int r44 = Math.round(r1 * getHeight());
        if (r44 == 0) goto L16;
        invalidate();
    L16:
        return r44;
    L9:
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0f) goto L13;
        r43 = EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, r42, 1.0f - r52);
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) != 0.0f) goto L7;
        this.mEdgeGlowBottom.onRelease();
        goto L7
    }

    private void runAnimatedScroll(boolean r2) {
        if (r2 == false) goto L5;
        startNestedScroll(2, 1);
    L6:
        this.mLastScrollerY = getScrollY();
        postInvalidateOnAnimation();
        return;
    L5:
        stopNestedScroll(1);
        goto L6
    }

    private boolean scrollAndFocus(int r7, int r8, int r9) {
        int r0 = getHeight();
        int r1 = getScrollY();
        int r02 = r0 + r1;
        boolean r3 = false;
        if (r7 != 33) goto L5;
        boolean r2 = true;
    L6:
        View r5 = findFocusableViewInBounds(r2, r8, r9);
        if (r5 != null) goto L9;
        r5 = this;
    L9:
        if (r8 < r1) goto L12;
        if (r9 > r02) goto L12;
    L17:
        if (r5 == findFocus()) goto L19;
        r5.requestFocus(r7);
    L19:
        return r3;
    L12:
        if (r2 == false) goto L14;
        int r82 = r8 - r1;
    L15:
        scrollBy(r82, 0, 1, true);
        r3 = true;
        goto L17
    L14:
        r82 = r9 - r02;
        goto L15
    L5:
        r2 = false;
        goto L6
    }

    private int scrollBy(int r8, int r9, int r10, boolean r11) {
        return scrollBy(r8, -1, null, r9, r10, r11);
    }

    private void scrollToChild(View r2) {
        r2.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(r2, this.mTempRect);
        int r22 = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
        if (r22 == 0) goto L6;
        scrollBy(0, r22);
        return;
    }

    private boolean scrollToChildRect(Rect r3, boolean r4) {
        int r32 = computeScrollDeltaToGetChildRectOnScreen(r3);
        if (r32 == 0) goto L5;
        boolean r1 = true;
    L6:
        if (r1 == false) goto L11;
        if (r4 == false) goto L10;
        scrollBy(0, r32);
        return r1;
    L10:
        smoothScrollBy(0, r32);
    L11:
        return r1;
    L5:
        r1 = false;
        goto L6
    }

    private boolean shouldAbsorb(EdgeEffect r3, int r4) {
        if (r4 <= 0) goto L6;
        return true;
    L6:
        if (getSplineFlingDistance(-r4) >= (EdgeEffectCompat.getDistance(r3) * getHeight())) goto L8;
        return true;
    L8:
        return false;
    }

    private void smoothScrollBy(int r9, int r10, int r11, boolean r12) {
        if (getChildCount() != 0) goto L6;
        return;
    L6:
        if ((AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll) <= 250) goto L9;
        View r0 = getChildAt(0);
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        int r02 = (r0.getHeight() + r1.topMargin) + r1.bottomMargin;
        int r13 = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int r4 = getScrollY();
        int r102 = r10 + r4;
        int r6 = Math.max(0, Math.min(r102, Math.max(0, r02 - r13))) - r4;
        this.mScroller.startScroll(getScrollX(), r4, 0, r6, r11);
        runAnimatedScroll(r12);
    L12:
        this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
        return;
    L9:
        if (this.mScroller.isFinished() == true) goto L11;
        abortAnimatedScroll();
    L11:
        scrollBy(r9, r10);
        goto L12
    }

    private boolean stopGlowAnimations(MotionEvent r6) {
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) == 0.0f) goto L5;
        EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, 0.0f, r6.getX() / getWidth());
        boolean r0 = true;
    L7:
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0f) goto L10;
        EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, 0.0f, 1.0f - (r6.getX() / getWidth()));
        return true;
    L10:
        return r0;
    L5:
        r0 = false;
        goto L7
    }

    @Override // android.view.ViewGroup
    public void addView(View r2) {
        if (getChildCount() > 0) goto L6;
        super.addView(r2);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View r2, int r3) {
        if (getChildCount() > 0) goto L6;
        super.addView(r2, r3);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View r2, int r3, ViewGroup.LayoutParams r4) {
        if (getChildCount() > 0) goto L6;
        super.addView(r2, r3, r4);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View r2, ViewGroup.LayoutParams r3) {
        if (getChildCount() > 0) goto L6;
        super.addView(r2, r3);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("ScrollView can host only one direct child");
    }

    public boolean arrowScroll(int r9) {
        View r0 = findFocus();
        if (r0 != this) goto L5;
        r0 = null;
    L5:
        View r1 = FocusFinder.getInstance().findNextFocus(this, r0, r9);
        int r2 = getMaxScrollAmount();
        if (r1 == null) goto L11;
        if (isWithinDeltaOfScreen(r1, r2, getHeight()) == false) goto L11;
        r1.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(r1, this.mTempRect);
        scrollBy(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect), 0, 1, true);
        r1.requestFocus(r9);
    L25:
        if (r0 != null) goto L27;
    L31:
        return true;
    L27:
        if (r0.isFocused() == false) goto L31;
        if (isOffScreen(r0) == false) goto L31;
        int r92 = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(r92);
    L11:
        if (r9 == 33) goto L13;
    L15:
        if (r9 == 130) goto L17;
    L19:
        if (r2 != 0) goto L21;
        return false;
    L21:
        if (r9 == 130) goto L24;
        r2 = -r2;
    L24:
        scrollBy(r2, 0, 1, true);
        goto L25
    L17:
        if (getChildCount() <= 0) goto L19;
        View r12 = getChildAt(0);
        FrameLayout.LayoutParams r6 = (FrameLayout.LayoutParams) r12.getLayoutParams();
        r2 = Math.min((r12.getBottom() + r6.bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), r2);
        goto L19
    L13:
        if (getScrollY() >= r2) goto L15;
        r2 = getScrollY();
        goto L19
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.isFinished() == false) goto L5;
        return;
    L5:
        this.mScroller.computeScrollOffset();
        int r1 = this.mScroller.getCurrY();
        int r2 = consumeFlingInVerticalStretch(r1 - this.mLastScrollerY);
        this.mLastScrollerY = r1;
        int[] r3 = this.mScrollConsumed;
        r3[1] = 0;
        dispatchNestedPreScroll(0, r2, r3, null, 1);
        int r22 = r2 - this.mScrollConsumed[1];
        int r6 = getScrollRange();
        if (Build.VERSION.SDK_INT < 35) goto L8;
        Api35Impl.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
    L8:
        if (r22 == 0) goto L10;
        int r4 = getScrollY();
        overScrollByCompat(0, r22, getScrollX(), r4, 0, r6, 0, 0, false);
        int r8 = r6;
        int r12 = getScrollY() - r4;
        int r42 = r22 - r12;
        int[] r7 = this.mScrollConsumed;
        r7[1] = 0;
        dispatchNestedScroll(0, r12, 0, r42, this.mScrollOffset, 1, r7);
        r22 = r42 - this.mScrollConsumed[1];
    L11:
        if (r22 == 0) goto L25;
        int r13 = getOverScrollMode();
        if (r13 == 0) goto L16;
        if (r13 != 1) goto L23;
        if (r8 > 0) goto L16;
    L23:
        abortAnimatedScroll();
    L16:
        if (r22 >= 0) goto L21;
        if (this.mEdgeGlowTop.isFinished() == false) goto L23;
        this.mEdgeGlowTop.onAbsorb((int) this.mScroller.getCurrVelocity());
        goto L23
    L21:
        if (this.mEdgeGlowBottom.isFinished() == false) goto L23;
        this.mEdgeGlowBottom.onAbsorb((int) this.mScroller.getCurrVelocity());
    L25:
        if (this.mScroller.isFinished() == true) goto L28;
        postInvalidateOnAnimation();
        return;
    L28:
        stopNestedScroll(1);
        return;
    L10:
        r8 = r6;
        goto L11
    }

    public int computeScrollDeltaToGetChildRectOnScreen(Rect r11) {
        if (getChildCount() != 0) goto L5;
        return 0;
    L5:
        int r0 = getHeight();
        int r2 = getScrollY();
        int r3 = r2 + r0;
        int r4 = getVerticalFadingEdgeLength();
        if (r11.top <= 0) goto L8;
        r2 = r2 + r4;
    L8:
        View r5 = getChildAt(0);
        FrameLayout.LayoutParams r6 = (FrameLayout.LayoutParams) r5.getLayoutParams();
        if (r11.bottom >= ((r5.getHeight() + r6.topMargin) + r6.bottomMargin)) goto L11;
        int r42 = r3 - r4;
    L12:
        int r7 = r11.bottom;
        if (r7 <= r42) goto L23;
        if (r11.top <= r2) goto L23;
        if (r11.height() <= r0) goto L19;
        int r10 = r11.top - r2;
    L21:
        return Math.min(r10, (r5.getBottom() + r6.bottomMargin) - r3);
    L19:
        r10 = r11.bottom - r42;
    L23:
        if (r11.top >= r2) goto L31;
        if (r7 >= r42) goto L31;
        if (r11.height() <= r0) goto L28;
        int r1 = 0 - (r42 - r11.bottom);
    L30:
        return Math.max(r1, -getScrollY());
    L28:
        r1 = 0 - (r2 - r11.top);
    L31:
        return 0;
    L11:
        r42 = r3;
        goto L12
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        int r0 = getChildCount();
        int r1 = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (r0 != 0) goto L5;
        return r1;
    L5:
        View r2 = getChildAt(0);
        FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        int r22 = r2.getBottom() + r3.bottomMargin;
        int r4 = getScrollY();
        int r02 = Math.max(0, r22 - r1);
        if (r4 < 0) goto L8;
        if (r4 > r02) goto L11;
        return r22;
    L11:
        return r22 + (r4 - r02);
    L8:
        return r22 - r4;
    }

    public int consumeFlingInVerticalStretch(int r6) {
        int r0 = getHeight();
        if (r6 > 0) goto L5;
    L11:
        if (r6 < 0) goto L13;
        return r6;
    L13:
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowBottom) == 0.0f) goto L20;
        float r02 = r0;
        int r03 = Math.round((r02 / 4.0f) * EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, (r6 * 4.0f) / r02, 0.5f));
        if (r03 == r6) goto L18;
        this.mEdgeGlowBottom.finish();
    L18:
        return r6 - r03;
    L20:
        return r6;
    L5:
        if (EdgeEffectCompat.getDistance(this.mEdgeGlowTop) == 0.0f) goto L11;
        int r04 = Math.round(((-r0) / 4.0f) * EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, ((-r6) * 4.0f) / r0, 0.5f));
        if (r04 == r6) goto L10;
        this.mEdgeGlowTop.finish();
    L10:
        return r6 - r04;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent r2) {
        if (super.dispatchKeyEvent(r2) == false) goto L5;
        return true;
    L5:
        if (executeKeyEvent(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float r1, float r2, boolean r3) {
        return this.mChildHelper.dispatchNestedFling(r1, r2, r3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float r1, float r2) {
        return this.mChildHelper.dispatchNestedPreFling(r1, r2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int r7, int r8, int[] r9, int[] r10) {
        return dispatchNestedPreScroll(r7, r8, r9, r10, 0);
    }

    public boolean dispatchNestedPreScroll(int r1, int r2, int[] r3, int[] r4, int r5) {
        return this.mChildHelper.dispatchNestedPreScroll(r1, r2, r3, r4, r5);
    }

    public void dispatchNestedScroll(int r1, int r2, int r3, int r4, int[] r5, int r6, int[] r7) {
        this.mChildHelper.dispatchNestedScroll(r1, r2, r3, r4, r5, r6, r7);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int r1, int r2, int r3, int r4, int[] r5) {
        return this.mChildHelper.dispatchNestedScroll(r1, r2, r3, r4, r5);
    }

    @Override // android.view.View
    public void draw(Canvas r10) {
        super.draw(r10);
        int r0 = getScrollY();
        int r2 = 0;
        if (this.mEdgeGlowTop.isFinished() == true) goto L13;
        int r1 = r10.save();
        int r3 = getWidth();
        int r4 = getHeight();
        int r5 = Math.min(0, r0);
        if (getClipToPadding() == false) goto L7;
        r3 = r3 - (getPaddingLeft() + getPaddingRight());
        int r6 = getPaddingLeft();
        r4 = r4 - (getPaddingTop() + getPaddingBottom());
        r5 = r5 + getPaddingTop();
    L8:
        r10.translate(r6, r5);
        this.mEdgeGlowTop.setSize(r3, r4);
        if (this.mEdgeGlowTop.draw(r10) == false) goto L11;
        postInvalidateOnAnimation();
    L11:
        r10.restoreToCount(r1);
        goto L13
    L7:
        r6 = 0;
    L13:
        if (this.mEdgeGlowBottom.isFinished() == true) goto L25;
        int r12 = r10.save();
        int r32 = getWidth();
        int r42 = getHeight();
        int r02 = Math.max(getScrollRange(), r0) + r42;
        if (getClipToPadding() == false) goto L18;
        r32 = r32 - (getPaddingLeft() + getPaddingRight());
        r2 = getPaddingLeft();
    L18:
        if (getClipToPadding() == false) goto L20;
        r42 = r42 - (getPaddingTop() + getPaddingBottom());
        r02 = r02 - getPaddingBottom();
    L20:
        r10.translate(r2 - r32, r02);
        r10.rotate(180.0f, r32, 0.0f);
        this.mEdgeGlowBottom.setSize(r32, r42);
        if (this.mEdgeGlowBottom.draw(r10) == false) goto L23;
        postInvalidateOnAnimation();
    L23:
        r10.restoreToCount(r12);
        return;
    }

    public boolean executeKeyEvent(KeyEvent r6) {
        this.mTempRect.setEmpty();
        int r2 = 130;
        if (canScroll() == true) goto L20;
        if (isFocused() == true) goto L7;
    L18:
        return false;
    L7:
        if (r6.getKeyCode() == 4) goto L18;
        View r62 = findFocus();
        if (r62 != this) goto L11;
        r62 = null;
    L11:
        View r63 = FocusFinder.getInstance().findNextFocus(this, r62, 130);
        if (r63 == null) goto L18;
        if (r63 == this) goto L18;
        if (r63.requestFocus(130) == false) goto L18;
        return true;
    L20:
        if (r6.getAction() != 0) goto L61;
        int r0 = r6.getKeyCode();
        if (r0 == 19) goto L56;
        if (r0 == 20) goto L50;
        if (r0 == 62) goto L45;
        if (r0 == 92) goto L43;
        if (r0 == 93) goto L41;
        if (r0 != 122) goto L34;
        pageScroll(33);
        return false;
    L34:
        if (r0 != 123) goto L61;
        pageScroll(130);
        return false;
    L41:
        return fullScroll(130);
    L43:
        return fullScroll(33);
    L45:
        if (r6.isShiftPressed() == false) goto L47;
        r2 = 33;
    L47:
        pageScroll(r2);
        return false;
    L50:
        if (r6.isAltPressed() == false) goto L54;
        return fullScroll(130);
    L54:
        return arrowScroll(130);
    L56:
        if (r6.isAltPressed() == false) goto L60;
        return fullScroll(33);
    L60:
        return arrowScroll(33);
    L61:
        return false;
    }

    public void fling(int r13) {
        if (getChildCount() <= 0) goto L8;
        this.mScroller.fling(getScrollX(), getScrollY(), 0, r13, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        runAnimatedScroll(true);
        if (Build.VERSION.SDK_INT < 35) goto L9;
        Api35Impl.setFrameContentVelocity(this, Math.abs(this.mScroller.getCurrVelocity()));
        return;
    L9:
        return;
    }

    public boolean fullScroll(int r6) {
        if (r6 != 130) goto L5;
        boolean r0 = true;
    L6:
        int r3 = getHeight();
        Rect r4 = this.mTempRect;
        r4.top = 0;
        r4.bottom = r3;
        if (r0 == false) goto L11;
        int r02 = getChildCount();
        if (r02 <= 0) goto L11;
        View r03 = getChildAt(r02 - 1);
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r03.getLayoutParams();
        this.mTempRect.bottom = (r03.getBottom() + r1.bottomMargin) + getPaddingBottom();
        Rect r04 = this.mTempRect;
        r04.top = r04.bottom - r3;
    L11:
        Rect r05 = this.mTempRect;
        return scrollAndFocus(r6, r05.top, r05.bottom);
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() != 0) goto L6;
        return 0.0f;
    L6:
        View r0 = getChildAt(0);
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        int r2 = getVerticalFadingEdgeLength();
        int r02 = ((r0.getBottom() + r1.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (r02 < r2) goto L9;
        return 1.0f;
    L9:
        return r02 / r2;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) goto L6;
        View r0 = getChildAt(0);
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        return Math.max(0, ((r0.getHeight() + r2.topMargin) + r2.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    L6:
        return 0;
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() != 0) goto L6;
        return 0.0f;
    L6:
        int r0 = getVerticalFadingEdgeLength();
        int r1 = getScrollY();
        if (r1 < r0) goto L9;
        return 1.0f;
    L9:
        return r1 / r0;
    }

    public float getVerticalScrollFactorCompat() {
        if (this.mVerticalScrollFactor != 0.0f) goto L10;
        TypedValue r0 = new TypedValue();
        Context r2 = getContext();
        if (r2.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, r0, true) == false) goto L7;
        this.mVerticalScrollFactor = r0.getDimension(r2.getResources().getDisplayMetrics());
        goto L10
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("Expected theme to define listPreferredItemHeight.");
        return 0.0f;
    L10:
        return this.mVerticalScrollFactor;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int r1) {
        return this.mChildHelper.hasNestedScrollingParent(r1);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.view.ViewGroup
    public void measureChild(View r2, int r3, int r4) {
        ViewGroup.LayoutParams r42 = r2.getLayoutParams();
        r2.measure(ViewGroup.getChildMeasureSpec(r3, getPaddingLeft() + getPaddingRight(), r42.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View r1, int r2, int r3, int r4, int r5) {
        ViewGroup.MarginLayoutParams r42 = (ViewGroup.MarginLayoutParams) r1.getLayoutParams();
        r1.measure(ViewGroup.getChildMeasureSpec(r2, (((getPaddingLeft() + getPaddingRight()) + r42.leftMargin) + r42.rightMargin) + r3, r42.width), View.MeasureSpec.makeMeasureSpec(r42.topMargin + r42.bottomMargin, 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsLaidOut = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent r13) {
        if (r13.getAction() == 8) goto L5;
    L20:
        return false;
    L5:
        if (this.mIsBeingDragged == true) goto L20;
        if (MotionEventCompat.isFromSource(r13, 2) == false) goto L10;
        float r1 = r13.getAxisValue(9);
        int r7 = 9;
        int r9 = (int) r13.getX();
    L14:
        if (r1 == 0.0f) goto L20;
        scrollBy(-((int) (r1 * getVerticalScrollFactorCompat())), r7, r13, r9, 1, MotionEventCompat.isFromSource(r13, 8194));
        if (r7 == 0) goto L21;
        this.mDifferentialMotionFlingController.onMotionEvent(r13, r7);
        return true;
    L21:
        return true;
    L10:
        if (MotionEventCompat.isFromSource(r13, 4194304) == false) goto L12;
        float r4 = r13.getAxisValue(26);
        r9 = getWidth() / 2;
        r7 = 26;
        r1 = r4;
        goto L14
    L12:
        r7 = 0;
        r9 = 0;
        r1 = 0.0f;
        goto L14
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r12) {
        int r0 = r12.getAction();
        boolean r1 = true;
        if (r0 == 2) goto L5;
    L7:
        int r02 = r0 & 255;
        if (r02 != 0) goto L10;
        int r03 = (int) r12.getY();
        if (inChild((int) r12.getX(), r03) == false) goto L38;
        this.mLastMotionY = r03;
        this.mActivePointerId = r12.getPointerId(0);
        initOrResetVelocityTracker();
        this.mVelocityTracker.addMovement(r12);
        this.mScroller.computeScrollOffset();
        if (stopGlowAnimations(r12) == false) goto L47;
    L50:
        this.mIsBeingDragged = r1;
        startNestedScroll(2, 0);
    L52:
        return this.mIsBeingDragged;
    L47:
        if (this.mScroller.isFinished() == false) goto L50;
        r1 = false;
        goto L50
    L38:
        if (stopGlowAnimations(r12) == false) goto L40;
    L43:
        this.mIsBeingDragged = r1;
        recycleVelocityTracker();
        goto L52
    L40:
        if (this.mScroller.isFinished() == false) goto L43;
        r1 = false;
        goto L43
    L10:
        if (r02 == 1) goto L31;
        if (r02 != 2) goto L13;
        int r04 = this.mActivePointerId;
        if (r04 == (-1)) goto L52;
        int r5 = r12.findPointerIndex(r04);
        if (r5 != (-1)) goto L24;
        Log.e("NestedScrollView", "Invalid pointerId=" + r04 + " in onInterceptTouchEvent");
        goto L52
    L24:
        int r05 = (int) r12.getY(r5);
        if (Math.abs(r05 - this.mLastMotionY) <= this.mTouchSlop) goto L52;
        if ((2 & getNestedScrollAxes()) != 0) goto L52;
        this.mIsBeingDragged = true;
        this.mLastMotionY = r05;
        initVelocityTrackerIfNotExists();
        this.mVelocityTracker.addMovement(r12);
        this.mNestedYOffset = 0;
        ViewParent r122 = getParent();
        if (r122 == null) goto L52;
        r122.requestDisallowInterceptTouchEvent(true);
        goto L52
    L13:
        if (r02 == 3) goto L31;
        if (r02 != 6) goto L52;
        onSecondaryPointerUp(r12);
    L31:
        this.mIsBeingDragged = false;
        this.mActivePointerId = -1;
        recycleVelocityTracker();
        if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()) == false) goto L34;
        postInvalidateOnAnimation();
    L34:
        stopNestedScroll(0);
        goto L52
    L5:
        if (this.mIsBeingDragged == false) goto L7;
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);
        int r22 = 0;
        this.mIsLayoutDirty = false;
        View r32 = this.mChildToScrollTo;
        if (r32 != null) goto L5;
    L7:
        this.mChildToScrollTo = null;
        if (this.mIsLaidOut == false) goto L10;
    L18:
        scrollTo(getScrollX(), getScrollY());
        this.mIsLaidOut = true;
        return;
    L10:
        if (this.mSavedState == null) goto L13;
        scrollTo(getScrollX(), this.mSavedState.scrollPosition);
        this.mSavedState = null;
    L13:
        if (getChildCount() <= 0) goto L15;
        View r23 = getChildAt(0);
        FrameLayout.LayoutParams r33 = (FrameLayout.LayoutParams) r23.getLayoutParams();
        r22 = (r23.getMeasuredHeight() + r33.topMargin) + r33.bottomMargin;
    L15:
        int r62 = ((r6 - r4) - getPaddingTop()) - getPaddingBottom();
        int r34 = getScrollY();
        int r24 = clamp(r34, r62, r22);
        if (r24 == r34) goto L18;
        scrollTo(getScrollX(), r24);
        goto L18
    L5:
        if (isViewDescendantOf(r32, this) == false) goto L7;
        scrollToChild(this.mChildToScrollTo);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        super.onMeasure(r5, r6);
        if (this.mFillViewport == true) goto L6;
        return;
    L6:
        if (View.MeasureSpec.getMode(r6) != 0) goto L9;
        return;
    L9:
        if (getChildCount() <= 0) goto L14;
        View r62 = getChildAt(0);
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) r62.getLayoutParams();
        int r1 = r62.getMeasuredHeight();
        int r2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - r0.topMargin) - r0.bottomMargin;
        if (r1 >= r2) goto L15;
        r62.measure(ViewGroup.getChildMeasureSpec(r5, ((getPaddingLeft() + getPaddingRight()) + r0.leftMargin) + r0.rightMargin, r0.width), View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30));
        return;
    L15:
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View r1, float r2, float r3, boolean r4) {
        if (r4 == true) goto L5;
        dispatchNestedFling(0.0f, r3, true);
        fling((int) r3);
        return true;
    L5:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View r1, float r2, float r3) {
        return dispatchNestedPreFling(r2, r3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View r7, int r8, int r9, int[] r10) {
        onNestedPreScroll(r7, r8, r9, r10, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View r1, int r2, int r3, int[] r4, int r5) {
        dispatchNestedPreScroll(r2, r3, r4, null, r5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5) {
        onNestedScrollInternal(r5, 0, null);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6) {
        onNestedScrollInternal(r5, r6, null);
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6, int[] r7) {
        onNestedScrollInternal(r5, r6, r7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View r2, View r3, int r4) {
        onNestedScrollAccepted(r2, r3, r4, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View r2, View r3, int r4, int r5) {
        this.mParentHelper.onNestedScrollAccepted(r2, r3, r4, r5);
        startNestedScroll(2, r5);
    }

    @Override // android.view.View
    public void onOverScrolled(int r1, int r2, boolean r3, boolean r4) {
        super.scrollTo(r1, r2);
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int r3, Rect r4) {
        if (r3 != 2) goto L6;
        r3 = 130;
    L8:
        if (r4 != null) goto L10;
        View r0 = FocusFinder.getInstance().findNextFocus(this, null, r3);
    L12:
        if (r0 != null) goto L15;
        return false;
    L15:
        if (isOffScreen(r0) == false) goto L18;
        return false;
    L18:
        return r0.requestFocus(r3, r4);
    L10:
        r0 = FocusFinder.getInstance().findNextFocusFromRect(this, r4, r3);
        goto L12
    L6:
        if (r3 != 1) goto L8;
        r3 = 33;
        goto L8
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        this.mSavedState = r22;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        r1.scrollPosition = getScrollY();
        return r1;
    }

    @Override // android.view.View
    public void onScrollChanged(int r7, int r8, int r9, int r10) {
        super.onScrollChanged(r7, r8, r9, r10);
        OnScrollChangeListener r0 = this.mOnScrollChangeListener;
        if (r0 == null) goto L6;
        r0.onScrollChange(this, r7, r8, r9, r10);
        return;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        View r12 = findFocus();
        if (r12 == null) goto L10;
        if (this != r12) goto L7;
        return;
    L7:
        if (isWithinDeltaOfScreen(r12, 0, r4) == false) goto L11;
        r12.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(r12, this.mTempRect);
        doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
        return;
    L11:
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View r2, View r3, int r4) {
        return onStartNestedScroll(r2, r3, r4, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View r1, View r2, int r3, int r4) {
        if ((r3 & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View r2) {
        onStopNestedScroll(r2, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View r2, int r3) {
        this.mParentHelper.onStopNestedScroll(r2, r3);
        stopNestedScroll(r3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r14) {
        initVelocityTrackerIfNotExists();
        int r0 = r14.getActionMasked();
        if (r0 != 0) goto L5;
        this.mNestedYOffset = 0;
    L5:
        MotionEvent r2 = MotionEvent.obtain(r14);
        r2.offsetLocation(0.0f, this.mNestedYOffset);
        if (r0 == 0) goto L56;
        if (r0 != 1) goto L9;
        NestedScrollView r5 = this;
        VelocityTracker r13 = r5.mVelocityTracker;
        r13.computeCurrentVelocity(1000, r5.mMaximumVelocity);
        int r132 = (int) r13.getYVelocity(r5.mActivePointerId);
        if (Math.abs(r132) < r5.mMinimumVelocity) goto L53;
        if (r5.edgeEffectFling(r132) == true) goto L55;
        int r133 = -r132;
        float r142 = r133;
        if (r5.dispatchNestedPreFling(0.0f, r142) == true) goto L55;
        r5.dispatchNestedFling(0.0f, r142, true);
        r5.fling(r133);
    L55:
        r5.endTouchDrag();
    L68:
        VelocityTracker r134 = r5.mVelocityTracker;
        if (r134 == null) goto L71;
        r134.addMovement(r2);
    L71:
        r2.recycle();
        return true;
    L53:
        if (r5.mScroller.springBack(r5.getScrollX(), r5.getScrollY(), 0, 0, 0, r5.getScrollRange()) == false) goto L55;
        r5.postInvalidateOnAnimation();
        goto L55
    L9:
        if (r0 != 2) goto L11;
        int r02 = r14.findPointerIndex(this.mActivePointerId);
        if (r02 != (-1)) goto L30;
        Log.e("NestedScrollView", "Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent");
    L16:
        r5 = this;
        goto L68
    L30:
        int r1 = (int) r14.getY(r02);
        int r4 = this.mLastMotionY - r1;
        int r42 = r4 - releaseVerticalGlow(r4, r14.getX(r02));
        if (this.mIsBeingDragged == false) goto L33;
    L40:
        int r6 = r42;
        if (this.mIsBeingDragged == false) goto L16;
        r5 = this;
        int r135 = r5.scrollBy(r6, 1, r14, (int) r14.getX(r02), 0, false);
        r5.mLastMotionY = r1 - r135;
        r5.mNestedYOffset += r135;
        goto L68
    L33:
        if (Math.abs(r42) <= this.mTouchSlop) goto L40;
        ViewParent r52 = getParent();
        if (r52 == null) goto L37;
        r52.requestDisallowInterceptTouchEvent(true);
    L37:
        this.mIsBeingDragged = true;
        int r53 = this.mTouchSlop;
        if (r42 <= 0) goto L41;
        r42 = r42 - r53;
        goto L40
    L41:
        r42 = r42 + r53;
        goto L40
    L11:
        if (r0 == 3) goto L20;
        if (r0 != 5) goto L15;
        int r03 = r14.getActionIndex();
        this.mLastMotionY = (int) r14.getY(r03);
        this.mActivePointerId = r14.getPointerId(r03);
        goto L16
    L15:
        if (r0 != 6) goto L16;
        onSecondaryPointerUp(r14);
        this.mLastMotionY = (int) r14.getY(r14.findPointerIndex(this.mActivePointerId));
        goto L16
    L20:
        if (this.mIsBeingDragged == true) goto L22;
    L26:
        endTouchDrag();
        goto L16
    L22:
        if (getChildCount() <= 0) goto L26;
        if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()) == false) goto L26;
        postInvalidateOnAnimation();
        goto L26
    L56:
        r5 = this;
        if (r5.getChildCount() != 0) goto L60;
        return false;
    L60:
        if (r5.mIsBeingDragged == false) goto L65;
        ViewParent r136 = r5.getParent();
        if (r136 == null) goto L65;
        r136.requestDisallowInterceptTouchEvent(true);
    L65:
        if (r5.mScroller.isFinished() == true) goto L67;
        r5.abortAnimatedScroll();
    L67:
        r5.initializeTouchDrag((int) r14.getY(), r14.getPointerId(0));
        goto L68
    }

    public boolean overScrollByCompat(int r12, int r13, int r14, int r15, int r16, int r17, int r18, int r19, boolean r20) {
        int r0 = getOverScrollMode();
        if (computeHorizontalScrollRange() <= computeHorizontalScrollExtent()) goto L5;
        boolean r1 = true;
    L7:
        if (computeVerticalScrollRange() <= computeVerticalScrollExtent()) goto L9;
        boolean r2 = true;
    L10:
        if (r0 == 0) goto L15;
        if (r0 != 1) goto L14;
        if (r1 == true) goto L15;
    L14:
        boolean r110 = false;
    L16:
        if (r0 == 0) goto L21;
        if (r0 != 1) goto L20;
        if (r2 == true) goto L21;
    L20:
        boolean r02 = false;
    L22:
        int r22 = r14 + r12;
        if (r110 == true) goto L25;
        int r111 = 0;
    L26:
        int r5 = r15 + r13;
        if (r02 == true) goto L29;
        int r03 = 0;
    L30:
        int r6 = -r111;
        int r112 = r111 + r16;
        int r7 = -r03;
        int r04 = r03 + r17;
        if (r22 <= r112) goto L33;
        r22 = r112;
        boolean r113 = true;
    L36:
        if (r5 <= r04) goto L38;
        r5 = r04;
        boolean r05 = true;
    L41:
        if (r05 == true) goto L43;
    L45:
        int r62 = r22;
    L46:
        onOverScrolled(r62, r5, r113, r05);
        if (r113 == true) goto L51;
        if (r05 == true) goto L51;
        return false;
    L51:
        return true;
    L43:
        if (hasNestedScrollingParent(1) == true) goto L45;
        int r132 = r22;
        this.mScroller.springBack(r132, r5, 0, 0, 0, getScrollRange());
        r62 = r132;
        goto L46
    L38:
        if (r5 >= r7) goto L40;
        r05 = true;
        r5 = r7;
        goto L41
    L40:
        r05 = false;
        goto L41
    L33:
        if (r22 >= r6) goto L35;
        r113 = true;
        r22 = r6;
        goto L36
    L35:
        r113 = false;
        goto L36
    L29:
        r03 = r19;
        goto L30
    L25:
        r111 = r18;
    L21:
        r02 = true;
    L15:
        r110 = true;
        goto L16
    L9:
        r2 = false;
        goto L10
    L5:
        r1 = false;
        goto L7
    }

    public boolean pageScroll(int r6) {
        if (r6 != 130) goto L5;
        boolean r0 = true;
    L6:
        int r3 = getHeight();
        Rect r4 = this.mTempRect;
        if (r0 == false) goto L13;
        r4.top = getScrollY() + r3;
        int r02 = getChildCount();
        if (r02 <= 0) goto L16;
        View r03 = getChildAt(r02 - 1);
        int r04 = (r03.getBottom() + ((FrameLayout.LayoutParams) r03.getLayoutParams()).bottomMargin) + getPaddingBottom();
        Rect r1 = this.mTempRect;
        if ((r1.top + r3) <= r04) goto L16;
        r1.top = r04 - r3;
    L16:
        Rect r05 = this.mTempRect;
        int r12 = r05.top;
        int r32 = r3 + r12;
        r05.bottom = r32;
        return scrollAndFocus(r6, r12, r32);
    L13:
        r4.top = getScrollY() - r3;
        Rect r06 = this.mTempRect;
        if (r06.top >= 0) goto L16;
        r06.top = 0;
        goto L16
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View r2, View r3) {
        if (this.mIsLayoutDirty == true) goto L5;
        scrollToChild(r3);
    L6:
        super.requestChildFocus(r2, r3);
        return;
    L5:
        this.mChildToScrollTo = r3;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View r3, Rect r4, boolean r5) {
        r4.offset(r3.getLeft() - r3.getScrollX(), r3.getTop() - r3.getScrollY());
        return scrollToChildRect(r4, r5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean r1) {
        if (r1 == false) goto L4;
        recycleVelocityTracker();
    L4:
        super.requestDisallowInterceptTouchEvent(r1);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }

    public int scrollBy(int r20, int r21, MotionEvent r22, int r23, int r24, boolean r25) {
        if (r24 != 1) goto L5;
        startNestedScroll(2, r24);
    L5:
        boolean r14 = false;
        if (dispatchNestedPreScroll(0, r20, this.mScrollConsumed, this.mScrollOffset, r24) == false) goto L8;
        int r1 = r20 - this.mScrollConsumed[1];
        int r15 = this.mScrollOffset[1];
        int r2 = r1;
    L9:
        int r4 = getScrollY();
        int r6 = getScrollRange();
        if (canOverScroll() == false) goto L13;
        if (r25 == true) goto L13;
        boolean r16 = true;
    L14:
        int r8 = r2;
        if (overScrollByCompat(0, r2, 0, r4, 0, r6, 0, 0, true) == true) goto L17;
    L19:
        boolean r17 = false;
    L20:
        int r26 = getScrollY() - r4;
        if (r22 == null) goto L24;
        if (r26 == 0) goto L24;
        getScrollFeedbackProvider().onScrollProgress(r22.getDeviceId(), r22.getSource(), r21, r26);
    L24:
        int[] r7 = this.mScrollConsumed;
        r7[1] = 0;
        dispatchNestedScroll(0, r26, 0, r8 - r26, this.mScrollOffset, r24, r7);
        int r152 = r15 + this.mScrollOffset[1];
        int r27 = r8 - this.mScrollConsumed[1];
        int r42 = r4 + r27;
        if (r42 >= 0) goto L33;
        if (r16 == false) goto L42;
        EdgeEffectCompat.onPullDistance(this.mEdgeGlowTop, (-r27) / getHeight(), r23 / getWidth());
        if (r22 == null) goto L31;
        getScrollFeedbackProvider().onScrollLimit(r22.getDeviceId(), r22.getSource(), r21, true);
    L31:
        if (this.mEdgeGlowBottom.isFinished() == true) goto L42;
        this.mEdgeGlowBottom.onRelease();
    L42:
        if (this.mEdgeGlowTop.isFinished() == true) goto L44;
    L47:
        postInvalidateOnAnimation();
    L48:
        if (r14 == false) goto L53;
        if (r24 != 0) goto L53;
        VelocityTracker r12 = this.mVelocityTracker;
        if (r12 == null) goto L53;
        r12.clear();
    L53:
        if (r24 != 1) goto L55;
        stopNestedScroll(r24);
        this.mEdgeGlowTop.onRelease();
        this.mEdgeGlowBottom.onRelease();
    L55:
        return r152;
    L44:
        if (this.mEdgeGlowBottom.isFinished() == false) goto L47;
        r14 = r17;
        goto L48
    L33:
        if (r42 <= r6) goto L42;
        if (r16 == false) goto L42;
        EdgeEffectCompat.onPullDistance(this.mEdgeGlowBottom, r27 / getHeight(), 1.0f - (r23 / getWidth()));
        if (r22 == null) goto L39;
        getScrollFeedbackProvider().onScrollLimit(r22.getDeviceId(), r22.getSource(), r21, false);
    L39:
        if (this.mEdgeGlowTop.isFinished() == true) goto L42;
        this.mEdgeGlowTop.onRelease();
        goto L42
    L17:
        if (hasNestedScrollingParent(r24) == true) goto L19;
        r17 = true;
    L13:
        r16 = false;
        goto L14
    L8:
        r2 = r20;
        r15 = 0;
        goto L9
    }

    @Override // android.view.View
    public void scrollTo(int r7, int r8) {
        if (getChildCount() <= 0) goto L10;
        View r0 = getChildAt(0);
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        int r2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int r3 = (r0.getWidth() + r1.leftMargin) + r1.rightMargin;
        int r4 = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int r02 = (r0.getHeight() + r1.topMargin) + r1.bottomMargin;
        int r72 = clamp(r7, r2, r3);
        int r82 = clamp(r8, r4, r02);
        if (r72 == getScrollX()) goto L7;
    L8:
        super.scrollTo(r72, r82);
        return;
    L7:
        if (r82 != getScrollY()) goto L8;
        return;
    }

    public void setFillViewport(boolean r2) {
        if (r2 == this.mFillViewport) goto L6;
        this.mFillViewport = r2;
        requestLayout();
        return;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean r1) {
        this.mChildHelper.setNestedScrollingEnabled(r1);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener r1) {
        this.mOnScrollChangeListener = r1;
    }

    public void setSmoothScrollingEnabled(boolean r1) {
        this.mSmoothScrollingEnabled = r1;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    public final void smoothScrollBy(int r3, int r4) {
        smoothScrollBy(r3, r4, 250, false);
    }

    public final void smoothScrollTo(int r3, int r4) {
        smoothScrollTo(r3, r4, 250, false);
    }

    public void smoothScrollTo(int r2, int r3, int r4, boolean r5) {
        smoothScrollBy(r2 - getScrollX(), r3 - getScrollY(), r4, r5);
    }

    public void smoothScrollTo(int r2, int r3, boolean r4) {
        smoothScrollTo(r2, r3, 250, r4);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int r2) {
        return startNestedScroll(r2, 0);
    }

    public boolean startNestedScroll(int r1, int r2) {
        return this.mChildHelper.startNestedScroll(r1, r2);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public void stopNestedScroll(int r1) {
        this.mChildHelper.stopNestedScroll(r1);
    }
}
