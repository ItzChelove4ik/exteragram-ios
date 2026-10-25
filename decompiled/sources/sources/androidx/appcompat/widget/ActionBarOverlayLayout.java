package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.graphics.Insets;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import okio.Segment$$ExternalSyntheticBUOutline1;

@SuppressLint({"UnknownNullness"})
/* loaded from: classes3.dex */
public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent, NestedScrollingParent2, NestedScrollingParent3 {
    static final int[] ATTRS = null;
    private static final WindowInsetsCompat NON_EMPTY_SYSTEM_WINDOW_INSETS = null;
    private static final Rect ZERO_INSETS = null;
    private int mActionBarHeight;
    ActionBarContainer mActionBarTop;
    private ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private WindowInsetsCompat mBaseInnerInsets;
    private final Rect mBaseInnerInsetsRect;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    ViewPropertyAnimator mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private OverScroller mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private WindowInsetsCompat mInnerInsets;
    private final Rect mInnerInsetsRect;
    private final Rect mLastBaseContentInsets;
    private WindowInsetsCompat mLastBaseInnerInsets;
    private final Rect mLastBaseInnerInsetsRect;
    private WindowInsetsCompat mLastInnerInsets;
    private final Rect mLastInnerInsetsRect;
    private int mLastSystemUiVisibility;
    private final NoSystemUiLayoutFlagView mNoSystemUiLayoutFlagView;
    private boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    private final Rect mTmpRect;
    final AnimatorListenerAdapter mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility;

    /* renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarOverlayLayout this$0;

        public AnonymousClass1(ActionBarOverlayLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            ActionBarOverlayLayout r0 = this.this$0;
            r0.mCurrentActionBarTopAnimator = null;
            r0.mAnimatingForFling = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ActionBarOverlayLayout r0 = this.this$0;
            r0.mCurrentActionBarTopAnimator = null;
            r0.mAnimatingForFling = false;
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ ActionBarOverlayLayout this$0;

        public AnonymousClass2(ActionBarOverlayLayout r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.haltActionBarHideOffsetAnimations();
            ActionBarOverlayLayout r0 = this.this$0;
            r0.mCurrentActionBarTopAnimator = r0.mActionBarTop.animate().translationY(0.0f).setListener(this.this$0.mTopAnimatorListener);
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ ActionBarOverlayLayout this$0;

        public AnonymousClass3(ActionBarOverlayLayout r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.haltActionBarHideOffsetAnimations();
            ActionBarOverlayLayout r0 = this.this$0;
            r0.mCurrentActionBarTopAnimator = r0.mActionBarTop.animate().translationY(-this.this$0.mActionBarTop.getHeight()).setListener(this.this$0.mTopAnimatorListener);
        }
    }

    public interface ActionBarVisibilityCallback {
        void enableContentAnimations(boolean r1);

        void hideForSystem();

        void onContentScrollStarted();

        void onContentScrollStopped();

        void onWindowVisibilityChanged(int r1);

        void showForSystem();
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(int r1, int r2) {
            super(r1, r2);
        }

        public LayoutParams(Context r1, AttributeSet r2) {
            super(r1, r2);
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
        }
    }

    public static final class NoSystemUiLayoutFlagView extends View {
        public NoSystemUiLayoutFlagView(Context r1) {
            super(r1);
            setWillNotDraw(true);
        }

        @Override // android.view.View
        public int getWindowSystemUiVisibility() {
            return 0;
        }
    }

    static {
        ATTRS = new int[]{R$attr.actionBarSize, R.attr.windowContentOverlay};
        NON_EMPTY_SYSTEM_WINDOW_INSETS = new WindowInsetsCompat.Builder().setSystemWindowInsets(Insets.of(0, 1, 0, 1)).build();
        ZERO_INSETS = new Rect();
    }

    public ActionBarOverlayLayout(Context r2) {
        this(r2, null);
    }

    public ActionBarOverlayLayout(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mTmpRect = new Rect();
        this.mBaseInnerInsetsRect = new Rect();
        this.mLastBaseInnerInsetsRect = new Rect();
        this.mInnerInsetsRect = new Rect();
        this.mLastInnerInsetsRect = new Rect();
        WindowInsetsCompat r22 = WindowInsetsCompat.CONSUMED;
        this.mBaseInnerInsets = r22;
        this.mLastBaseInnerInsets = r22;
        this.mInnerInsets = r22;
        this.mLastInnerInsets = r22;
        this.mTopAnimatorListener = new AnonymousClass1(this);
        this.mRemoveActionBarHideOffset = new AnonymousClass2(this);
        this.mAddActionBarHideOffset = new AnonymousClass3(this);
        init(r1);
        this.mParentHelper = new NestedScrollingParentHelper(this);
        NoSystemUiLayoutFlagView r23 = new NoSystemUiLayoutFlagView(r1);
        this.mNoSystemUiLayoutFlagView = r23;
        addView(r23);
    }

    private void addActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }

    private boolean applyInsets(View r2, Rect r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        LayoutParams r1 = (LayoutParams) r2.getLayoutParams();
        if (r4 == false) goto L7;
        int r42 = ((ViewGroup.MarginLayoutParams) r1).leftMargin;
        int r0 = r3.left;
        if (r42 == r0) goto L7;
        ((ViewGroup.MarginLayoutParams) r1).leftMargin = r0;
        boolean r43 = true;
    L8:
        if (r5 == false) goto L12;
        int r52 = ((ViewGroup.MarginLayoutParams) r1).topMargin;
        int r02 = r3.top;
        if (r52 == r02) goto L12;
        ((ViewGroup.MarginLayoutParams) r1).topMargin = r02;
        r43 = true;
    L12:
        if (r7 == false) goto L16;
        int r53 = ((ViewGroup.MarginLayoutParams) r1).rightMargin;
        int r72 = r3.right;
        if (r53 == r72) goto L16;
        ((ViewGroup.MarginLayoutParams) r1).rightMargin = r72;
        r43 = true;
    L16:
        if (r6 == false) goto L21;
        int r54 = ((ViewGroup.MarginLayoutParams) r1).bottomMargin;
        int r32 = r3.bottom;
        if (r54 == r32) goto L21;
        ((ViewGroup.MarginLayoutParams) r1).bottomMargin = r32;
        return true;
    L21:
        return r43;
    L7:
        r43 = false;
        goto L8
    }

    private boolean decorFitsSystemWindows() {
        ViewCompat.computeSystemWindowInsets(this.mNoSystemUiLayoutFlagView, NON_EMPTY_SYSTEM_WINDOW_INSETS, this.mTmpRect);
        return !this.mTmpRect.equals(ZERO_INSETS);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DecorToolbar getDecorToolbar(View r1) {
        if ((r1 instanceof DecorToolbar) == false) goto L7;
        return (DecorToolbar) r1;
    L7:
        if ((r1 instanceof Toolbar) == true) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("Can't make a decor toolbar out of ".concat(r1.getClass().getSimpleName()));
        return null;
    L9:
        return ((Toolbar) r1).getWrapper();
    }

    private void init(Context r5) {
        TypedArray r0 = getContext().getTheme().obtainStyledAttributes(ATTRS);
        boolean r1 = false;
        this.mActionBarHeight = r0.getDimensionPixelSize(0, 0);
        Drawable r3 = r0.getDrawable(1);
        this.mWindowContentOverlay = r3;
        if (r3 != null) goto L5;
        r1 = true;
    L5:
        setWillNotDraw(r1);
        r0.recycle();
        this.mFlingEstimator = new OverScroller(r5);
    }

    private void postAddActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mAddActionBarHideOffset, 600);
    }

    private void postRemoveActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mRemoveActionBarHideOffset, 600);
    }

    private void removeActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }

    private boolean shouldHideActionBarOnFling(float r10) {
        this.mFlingEstimator.fling(0, 0, 0, (int) r10, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.mFlingEstimator.getFinalY() <= this.mActionBarTop.getHeight()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public boolean canShowOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public void dismissPopups() {
        pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }

    @Override // android.view.View
    public void draw(Canvas r6) {
        super.draw(r6);
        if (this.mWindowContentOverlay != null) goto L5;
        return;
    L5:
        if (this.mActionBarTop.getVisibility() != 0) goto L7;
        int r0 = (int) ((this.mActionBarTop.getBottom() + this.mActionBarTop.getTranslationY()) + 0.5f);
    L8:
        this.mWindowContentOverlay.setBounds(0, r0, getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + r0);
        this.mWindowContentOverlay.draw(r6);
        return;
    L7:
        r0 = 0;
        goto L8
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect r1) {
        return super.fitSystemWindows(r1);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return new LayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer r0 = this.mActionBarTop;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return -((int) r0.getTranslationY());
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public CharSequence getTitle() {
        pullChildren();
        return this.mDecorToolbar.getTitle();
    }

    public void haltActionBarHideOffsetAnimations() {
        removeCallbacks(this.mRemoveActionBarHideOffset);
        removeCallbacks(this.mAddActionBarHideOffset);
        ViewPropertyAnimator r1 = this.mCurrentActionBarTopAnimator;
        if (r1 == null) goto L6;
        r1.cancel();
        return;
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public boolean hideOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public void initFeature(int r2) {
        pullChildren();
        if (r2 != 2) goto L5;
        this.mDecorToolbar.initProgress();
        return;
    L5:
        if (r2 != 5) goto L7;
        this.mDecorToolbar.initIndeterminateProgress();
        return;
    L7:
        if (r2 == 109) goto L9;
        return;
    L9:
        setOverlayMode(true);
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public boolean isOverflowMenuShowPending() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public boolean isOverflowMenuShowing() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets r8) {
        pullChildren();
        WindowInsetsCompat r82 = WindowInsetsCompat.toWindowInsetsCompat(r8, this);
        boolean r7 = applyInsets(this.mActionBarTop, new Rect(r82.getSystemWindowInsetLeft(), r82.getSystemWindowInsetTop(), r82.getSystemWindowInsetRight(), r82.getSystemWindowInsetBottom()), true, true, false, true);
        ViewCompat.computeSystemWindowInsets(this, r82, this.mBaseContentInsets);
        Rect r1 = this.mBaseContentInsets;
        WindowInsetsCompat r12 = r82.inset(r1.left, r1.top, r1.right, r1.bottom);
        this.mBaseInnerInsets = r12;
        boolean r2 = true;
        if (this.mLastBaseInnerInsets.equals(r12) == true) goto L6;
        this.mLastBaseInnerInsets = this.mBaseInnerInsets;
        r7 = true;
    L6:
        if (this.mLastBaseContentInsets.equals(this.mBaseContentInsets) == true) goto L8;
        this.mLastBaseContentInsets.set(this.mBaseContentInsets);
    L9:
        if (r2 == false) goto L12;
        requestLayout();
    L12:
        return r82.consumeDisplayCutout().consumeSystemWindowInsets().consumeStableInsets().toWindowInsets();
    L8:
        r2 = r7;
        goto L9
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        init(getContext());
        ViewCompat.requestApplyInsets(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        haltActionBarHideOffsetAnimations();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        int r52 = getChildCount();
        int r62 = getPaddingLeft();
        int r72 = getPaddingTop();
        int r82 = 0;
    L3:
        if (r82 >= r52) goto L8;
        View r92 = getChildAt(r82);
        if (r92.getVisibility() == 8) goto L7;
        LayoutParams r0 = (LayoutParams) r92.getLayoutParams();
        int r1 = r92.getMeasuredWidth();
        int r2 = r92.getMeasuredHeight();
        int r3 = ((ViewGroup.MarginLayoutParams) r0).leftMargin + r62;
        int r02 = ((ViewGroup.MarginLayoutParams) r0).topMargin + r72;
        r92.layout(r3, r02, r1 + r3, r2 + r02);
    L7:
        r82 = r82 + 1;
        goto L3
    }

    @Override // android.view.View
    public void onMeasure(int r14, int r15) {
        pullChildren();
        measureChildWithMargins(this.mActionBarTop, r14, 0, r15, 0);
        LayoutParams r13 = (LayoutParams) this.mActionBarTop.getLayoutParams();
        int r142 = Math.max(0, (this.mActionBarTop.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) r13).leftMargin) + ((ViewGroup.MarginLayoutParams) r13).rightMargin);
        int r132 = Math.max(0, (this.mActionBarTop.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) r13).topMargin) + ((ViewGroup.MarginLayoutParams) r13).bottomMargin);
        int r0 = View.combineMeasuredStates(0, this.mActionBarTop.getMeasuredState());
        if ((ViewCompat.getWindowSystemUiVisibility(this) & 256) == 0) goto L5;
        boolean r1 = true;
    L6:
        if (r1 == false) goto L13;
        int r3 = this.mActionBarHeight;
        if (this.mHasNonEmbeddedTabs == true) goto L10;
    L16:
        this.mContentInsets.set(this.mBaseContentInsets);
        this.mInnerInsets = this.mBaseInnerInsets;
        if (this.mOverlayMode == true) goto L22;
        if (r1 == true) goto L22;
        if (decorFitsSystemWindows() == false) goto L22;
        Rect r12 = this.mContentInsets;
        r12.top += r3;
        r12.bottom = r12.bottom;
        this.mInnerInsets = this.mInnerInsets.inset(0, r3, 0, 0);
    L23:
        applyInsets(this.mContent, this.mContentInsets, true, true, true, true);
        if (this.mLastInnerInsets.equals(this.mInnerInsets) == true) goto L26;
        WindowInsetsCompat r152 = this.mInnerInsets;
        this.mLastInnerInsets = r152;
        ViewCompat.dispatchApplyWindowInsets(this.mContent, r152);
    L26:
        measureChildWithMargins(this.mContent, r14, 0, r15, 0);
        LayoutParams r153 = (LayoutParams) this.mContent.getLayoutParams();
        int r143 = Math.max(r142, (this.mContent.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) r153).leftMargin) + ((ViewGroup.MarginLayoutParams) r153).rightMargin);
        int r133 = Math.max(r132, (this.mContent.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) r153).topMargin) + ((ViewGroup.MarginLayoutParams) r153).bottomMargin);
        int r154 = View.combineMeasuredStates(r0, this.mContent.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(r143 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), r14, r154), View.resolveSizeAndState(Math.max(r133 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), r15, r154 << 16));
        return;
    L22:
        this.mInnerInsets = new WindowInsetsCompat.Builder(this.mInnerInsets).setSystemWindowInsets(Insets.of(this.mInnerInsets.getSystemWindowInsetLeft(), this.mInnerInsets.getSystemWindowInsetTop() + r3, this.mInnerInsets.getSystemWindowInsetRight(), this.mInnerInsets.getSystemWindowInsetBottom())).build();
        goto L23
    L10:
        if (this.mActionBarTop.getTabContainer() == null) goto L16;
        r3 = r3 + this.mActionBarHeight;
        goto L16
    L13:
        if (this.mActionBarTop.getVisibility() == 8) goto L15;
        r3 = this.mActionBarTop.getMeasuredHeight();
        goto L16
    L15:
        r3 = 0;
        goto L16
    L5:
        r1 = false;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View r1, float r2, float r3, boolean r4) {
        if (this.mHideOnContentScroll == false) goto L12;
        if (r4 == true) goto L7;
        return false;
    L7:
        if (shouldHideActionBarOnFling(r3) == false) goto L9;
        addActionBarHideOffset();
    L10:
        this.mAnimatingForFling = true;
        return true;
    L9:
        removeActionBarHideOffset();
        goto L10
    L12:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View r1, float r2, float r3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View r1, int r2, int r3, int[] r4) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View r1, int r2, int r3, int[] r4, int r5) {
        if (r5 != 0) goto L5;
        onNestedPreScroll(r1, r2, r3, r4);
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5) {
        int r12 = this.mHideOnContentScrollReference + r3;
        this.mHideOnContentScrollReference = r12;
        setActionBarHideOffset(r12);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6) {
        if (r6 != 0) goto L5;
        onNestedScroll(r1, r2, r3, r4, r5);
        return;
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6, int[] r7) {
        onNestedScroll(r1, r2, r3, r4, r5, r6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View r2, View r3, int r4) {
        this.mParentHelper.onNestedScrollAccepted(r2, r3, r4);
        this.mHideOnContentScrollReference = getActionBarHideOffset();
        haltActionBarHideOffsetAnimations();
        ActionBarVisibilityCallback r1 = this.mActionBarVisibilityCallback;
        if (r1 == null) goto L6;
        r1.onContentScrollStarted();
        return;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View r1, View r2, int r3, int r4) {
        if (r4 != 0) goto L5;
        onNestedScrollAccepted(r1, r2, r3);
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View r1, View r2, int r3) {
        if ((r3 & 2) != 0) goto L5;
        return false;
    L5:
        if (this.mActionBarTop.getVisibility() == 0) goto L8;
        return false;
    L8:
        return this.mHideOnContentScroll;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View r1, View r2, int r3, int r4) {
        if (r4 == 0) goto L4;
        return false;
    L4:
        if (onStartNestedScroll(r1, r2, r3) == false) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View r2) {
        if (this.mHideOnContentScroll == true) goto L5;
    L10:
        ActionBarVisibilityCallback r1 = this.mActionBarVisibilityCallback;
        if (r1 == null) goto L14;
        r1.onContentScrollStopped();
        return;
    L14:
        return;
    L5:
        if (this.mAnimatingForFling == true) goto L10;
        if (this.mHideOnContentScrollReference > this.mActionBarTop.getHeight()) goto L9;
        postRemoveActionBarHideOffset();
        goto L10
    L9:
        postAddActionBarHideOffset();
        goto L10
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View r1, int r2) {
        if (r2 != 0) goto L5;
        onStopNestedScroll(r1);
        return;
    }

    @Override // android.view.View
    @Deprecated
    public void onWindowSystemUiVisibilityChanged(int r5) {
        super.onWindowSystemUiVisibilityChanged(r5);
        pullChildren();
        int r0 = this.mLastSystemUiVisibility ^ r5;
        this.mLastSystemUiVisibility = r5;
        boolean r2 = false;
        if ((r5 & 4) != 0) goto L5;
        boolean r1 = true;
    L7:
        if ((r5 & 256) == 0) goto L9;
        r2 = true;
    L9:
        ActionBarVisibilityCallback r52 = this.mActionBarVisibilityCallback;
        if (r52 == null) goto L18;
        r52.enableContentAnimations(!r2);
        if (r1 == true) goto L16;
        if (r2 == false) goto L16;
        this.mActionBarVisibilityCallback.hideForSystem();
    L16:
        this.mActionBarVisibilityCallback.showForSystem();
    L18:
        if ((r0 & 256) != 0) goto L20;
        return;
    L20:
        if (this.mActionBarVisibilityCallback == null) goto L24;
        ViewCompat.requestApplyInsets(this);
        return;
    L24:
        return;
    L5:
        r1 = false;
        goto L7
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int r1) {
        super.onWindowVisibilityChanged(r1);
        this.mWindowVisibility = r1;
        ActionBarVisibilityCallback r0 = this.mActionBarVisibilityCallback;
        if (r0 == null) goto L6;
        r0.onWindowVisibilityChanged(r1);
        return;
    }

    public void pullChildren() {
        if (this.mContent != null) goto L6;
        this.mContent = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
        this.mActionBarTop = (ActionBarContainer) findViewById(R$id.action_bar_container);
        this.mDecorToolbar = getDecorToolbar(findViewById(R$id.action_bar));
        return;
    }

    public void setActionBarHideOffset(int r3) {
        haltActionBarHideOffsetAnimations();
        int r32 = Math.max(0, Math.min(r3, this.mActionBarTop.getHeight()));
        this.mActionBarTop.setTranslationY(-r32);
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback r2) {
        this.mActionBarVisibilityCallback = r2;
        if (getWindowToken() == null) goto L8;
        this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
        int r22 = this.mLastSystemUiVisibility;
        if (r22 == 0) goto L9;
        onWindowSystemUiVisibilityChanged(r22);
        ViewCompat.requestApplyInsets(this);
        return;
    L9:
        return;
    }

    public void setHasNonEmbeddedTabs(boolean r1) {
        this.mHasNonEmbeddedTabs = r1;
    }

    public void setHideOnContentScrollEnabled(boolean r2) {
        if (r2 == this.mHideOnContentScroll) goto L8;
        this.mHideOnContentScroll = r2;
        if (r2 == true) goto L9;
        haltActionBarHideOffsetAnimations();
        setActionBarHideOffset(0);
        return;
    L9:
        return;
    }

    public void setIcon(int r1) {
        pullChildren();
        this.mDecorToolbar.setIcon(r1);
    }

    public void setIcon(Drawable r1) {
        pullChildren();
        this.mDecorToolbar.setIcon(r1);
    }

    public void setLogo(int r1) {
        pullChildren();
        this.mDecorToolbar.setLogo(r1);
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public void setMenu(Menu r1, MenuPresenter.Callback r2) {
        pullChildren();
        this.mDecorToolbar.setMenu(r1, r2);
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public void setMenuPrepared() {
        pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }

    public void setOverlayMode(boolean r1) {
        this.mOverlayMode = r1;
    }

    public void setShowingForActionMode(boolean r1) {
    }

    public void setUiOptions(int r1) {
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public void setWindowCallback(Window.Callback r1) {
        pullChildren();
        this.mDecorToolbar.setWindowCallback(r1);
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public void setWindowTitle(CharSequence r1) {
        pullChildren();
        this.mDecorToolbar.setWindowTitle(r1);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public boolean showOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }
}
