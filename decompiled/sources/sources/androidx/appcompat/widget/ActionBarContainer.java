package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import org.mvel2.asm.Opcodes;

/* loaded from: classes3.dex */
public class ActionBarContainer extends FrameLayout {
    private View mActionBarView;
    Drawable mBackground;
    private View mContextView;
    private int mHeight;
    boolean mIsSplit;
    boolean mIsStacked;
    private boolean mIsTransitioning;
    Drawable mSplitBackground;
    Drawable mStackedBackground;
    private View mTabContainer;

    public static class Api21Impl {
        public static void invalidateOutline(ActionBarContainer r0) {
            r0.invalidateOutline();
        }
    }

    public ActionBarContainer(Context r2) {
        this(r2, null);
    }

    public ActionBarContainer(Context r3, AttributeSet r4) {
        super(r3, r4);
        setBackground(new ActionBarBackgroundDrawable(this));
        TypedArray r32 = r3.obtainStyledAttributes(r4, R$styleable.ActionBar);
        this.mBackground = r32.getDrawable(R$styleable.ActionBar_background);
        this.mStackedBackground = r32.getDrawable(R$styleable.ActionBar_backgroundStacked);
        this.mHeight = r32.getDimensionPixelSize(R$styleable.ActionBar_height, -1);
        boolean r1 = true;
        if (getId() != R$id.split_action_bar) goto L5;
        this.mIsSplit = true;
        this.mSplitBackground = r32.getDrawable(R$styleable.ActionBar_backgroundSplit);
    L5:
        r32.recycle();
        if (this.mIsSplit == false) goto L12;
        if (this.mSplitBackground == null) goto L15;
    L10:
        r1 = false;
    L15:
        setWillNotDraw(r1);
        return;
    L12:
        if (this.mBackground != null) goto L10;
        if (this.mStackedBackground != null) goto L10;
        goto L10
    }

    private int getMeasuredHeightWithMargins(View r2) {
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        return (r2.getMeasuredHeight() + r1.topMargin) + r1.bottomMargin;
    }

    private boolean isCollapsed(View r2) {
        if (r2 != null) goto L4;
        return true;
    L4:
        if (r2.getVisibility() != 8) goto L6;
        return true;
    L6:
        if (r2.getMeasuredHeight() == 0) goto L13;
        return false;
    L13:
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable r0 = this.mBackground;
        if (r0 != null) goto L5;
    L7:
        Drawable r02 = this.mStackedBackground;
        if (r02 != null) goto L10;
    L12:
        Drawable r03 = this.mSplitBackground;
        if (r03 != null) goto L15;
        return;
    L15:
        if (r03.isStateful() == false) goto L19;
        this.mSplitBackground.setState(getDrawableState());
        return;
    L19:
        return;
    L10:
        if (r02.isStateful() == false) goto L12;
        this.mStackedBackground.setState(getDrawableState());
        goto L12
    L5:
        if (r0.isStateful() == false) goto L7;
        this.mBackground.setState(getDrawableState());
        goto L7
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.mBackground;
        if (r0 == null) goto L5;
        r0.jumpToCurrentState();
    L5:
        Drawable r02 = this.mStackedBackground;
        if (r02 == null) goto L8;
        r02.jumpToCurrentState();
    L8:
        Drawable r1 = this.mSplitBackground;
        if (r1 == null) goto L12;
        r1.jumpToCurrentState();
        return;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = findViewById(R$id.action_bar);
        this.mContextView = findViewById(R$id.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent r1) {
        super.onHoverEvent(r1);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (this.mIsTransitioning == false) goto L5;
        return true;
    L5:
        if (super.onInterceptTouchEvent(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        super.onLayout(r5, r6, r7, r8, r9);
        View r52 = this.mTabContainer;
        boolean r92 = true;
        boolean r0 = false;
        if (r52 != null) goto L5;
    L7:
        boolean r1 = false;
    L8:
        if (r52 == null) goto L13;
        if (r52.getVisibility() == 8) goto L13;
        int r72 = getMeasuredHeight();
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) r52.getLayoutParams();
        int r3 = r72 - r52.getMeasuredHeight();
        int r22 = r2.bottomMargin;
        r52.layout(r6, r3 - r22, r8, r72 - r22);
    L13:
        if (this.mIsSplit == false) goto L19;
        Drawable r53 = this.mSplitBackground;
        if (r53 == null) goto L17;
        r53.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    L35:
        if (r92 == false) goto L38;
        invalidate();
        return;
    L38:
        return;
    L17:
        r92 = r0;
        goto L35
    L19:
        if (this.mBackground != null) goto L21;
    L30:
        this.mIsStacked = r1;
        if (r1 == false) goto L17;
        Drawable r62 = this.mStackedBackground;
        if (r62 == null) goto L17;
        r62.setBounds(r52.getLeft(), r52.getTop(), r52.getRight(), r52.getBottom());
        goto L35
    L21:
        if (this.mActionBarView.getVisibility() != 0) goto L23;
        this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
    L29:
        r0 = true;
        goto L30
    L23:
        View r63 = this.mContextView;
        if (r63 != null) goto L26;
    L28:
        this.mBackground.setBounds(0, 0, 0, 0);
        goto L29
    L26:
        if (r63.getVisibility() != 0) goto L28;
        this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
        goto L29
    L5:
        if (r52.getVisibility() == 8) goto L7;
        r1 = true;
        goto L8
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        if (this.mActionBarView == null) goto L5;
    L9:
        super.onMeasure(r4, r5);
        if (this.mActionBarView == null) goto L33;
        int r42 = View.MeasureSpec.getMode(r5);
        View r0 = this.mTabContainer;
        if (r0 != null) goto L15;
        return;
    L15:
        if (r0.getVisibility() != 8) goto L17;
        return;
    L17:
        if (r42 != 1073741824) goto L19;
        return;
    L19:
        if (isCollapsed(this.mActionBarView) == true) goto L22;
        int r02 = getMeasuredHeightWithMargins(this.mActionBarView);
    L25:
        if (r42 != Integer.MIN_VALUE) goto L27;
        int r43 = View.MeasureSpec.getSize(r5);
    L28:
        setMeasuredDimension(getMeasuredWidth(), Math.min(r02 + getMeasuredHeightWithMargins(this.mTabContainer), r43));
        return;
    L27:
        r43 = Integer.MAX_VALUE;
        goto L28
    L22:
        if (isCollapsed(this.mContextView) == true) goto L24;
        r02 = getMeasuredHeightWithMargins(this.mContextView);
        goto L25
    L24:
        r02 = 0;
        goto L25
    L33:
        return;
    L5:
        if (View.MeasureSpec.getMode(r5) != Integer.MIN_VALUE) goto L9;
        int r03 = this.mHeight;
        if (r03 < 0) goto L9;
        r5 = View.MeasureSpec.makeMeasureSpec(Math.min(r03, View.MeasureSpec.getSize(r5)), Integer.MIN_VALUE);
        goto L9
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        super.onTouchEvent(r1);
        return true;
    }

    public void setPrimaryBackground(Drawable r5) {
        Drawable r0 = this.mBackground;
        if (r0 == null) goto L5;
        r0.setCallback(null);
        unscheduleDrawable(this.mBackground);
    L5:
        this.mBackground = r5;
        if (r5 == null) goto L10;
        r5.setCallback(this);
        View r52 = this.mActionBarView;
        if (r52 == null) goto L10;
        this.mBackground.setBounds(r52.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
    L10:
        boolean r02 = false;
        if (this.mIsSplit == false) goto L16;
        if (this.mSplitBackground != null) goto L20;
    L14:
        r02 = true;
    L20:
        setWillNotDraw(r02);
        invalidate();
        Api21Impl.invalidateOutline(this);
        return;
    L16:
        if (this.mBackground != null) goto L20;
        if (this.mStackedBackground != null) goto L20;
        goto L20
    }

    public void setSplitBackground(Drawable r4) {
        Drawable r0 = this.mSplitBackground;
        if (r0 == null) goto L5;
        r0.setCallback(null);
        unscheduleDrawable(this.mSplitBackground);
    L5:
        this.mSplitBackground = r4;
        boolean r02 = false;
        if (r4 == null) goto L13;
        r4.setCallback(this);
        if (this.mIsSplit == false) goto L13;
        Drawable r42 = this.mSplitBackground;
        if (r42 == null) goto L13;
        r42.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    L13:
        if (this.mIsSplit == false) goto L18;
        if (this.mSplitBackground != null) goto L22;
    L16:
        r02 = true;
    L22:
        setWillNotDraw(r02);
        invalidate();
        Api21Impl.invalidateOutline(this);
        return;
    L18:
        if (this.mBackground != null) goto L22;
        if (this.mStackedBackground != null) goto L22;
        goto L22
    }

    public void setStackedBackground(Drawable r5) {
        Drawable r0 = this.mStackedBackground;
        if (r0 == null) goto L5;
        r0.setCallback(null);
        unscheduleDrawable(this.mStackedBackground);
    L5:
        this.mStackedBackground = r5;
        if (r5 == null) goto L12;
        r5.setCallback(this);
        if (this.mIsStacked == false) goto L12;
        Drawable r52 = this.mStackedBackground;
        if (r52 == null) goto L12;
        r52.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
    L12:
        boolean r02 = false;
        if (this.mIsSplit == false) goto L18;
        if (this.mSplitBackground != null) goto L22;
    L16:
        r02 = true;
    L22:
        setWillNotDraw(r02);
        invalidate();
        Api21Impl.invalidateOutline(this);
        return;
    L18:
        if (this.mBackground != null) goto L22;
        if (this.mStackedBackground != null) goto L22;
        goto L22
    }

    public void setTabContainer(ScrollingTabContainerView r2) {
        View r0 = this.mTabContainer;
        if (r0 == null) goto L5;
        removeView(r0);
    L5:
        this.mTabContainer = r2;
    }

    public void setTransitioning(boolean r1) {
        this.mIsTransitioning = r1;
        if (r1 == false) goto L5;
        int r12 = Opcodes.ASM6;
    L6:
        setDescendantFocusability(r12);
        return;
    L5:
        r12 = 262144;
        goto L6
    }

    @Override // android.view.View
    public void setVisibility(int r3) {
        super.setVisibility(r3);
        if (r3 != 0) goto L5;
        boolean r32 = true;
    L6:
        Drawable r1 = this.mBackground;
        if (r1 == null) goto L9;
        r1.setVisible(r32, false);
    L9:
        Drawable r12 = this.mStackedBackground;
        if (r12 == null) goto L12;
        r12.setVisible(r32, false);
    L12:
        Drawable r2 = this.mSplitBackground;
        if (r2 == null) goto L16;
        r2.setVisible(r32, false);
        return;
    L16:
        return;
    L5:
        r32 = false;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View r1, ActionMode.Callback r2) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View r1, ActionMode.Callback r2, int r3) {
        if (r3 != 0) goto L4;
        return null;
    L4:
        return super.startActionModeForChild(r1, r2, r3);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.mBackground) goto L7;
        if (this.mIsSplit == true) goto L7;
        return true;
    L7:
        if (r2 != this.mStackedBackground) goto L11;
        if (this.mIsStacked == false) goto L11;
        return true;
    L11:
        if (r2 != this.mSplitBackground) goto L15;
        if (this.mIsSplit == false) goto L15;
        return true;
    L15:
        if (super.verifyDrawable(r2) == true) goto L22;
        return false;
    L22:
        return true;
    }
}
