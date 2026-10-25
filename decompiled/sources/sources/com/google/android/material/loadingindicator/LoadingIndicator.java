package com.google.android.material.loadingindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.R$attr;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.progressindicator.AnimatorDurationScaleProvider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Arrays;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final class LoadingIndicator extends View implements Drawable.Callback {
    static final int DEF_STYLE_RES = 0;
    static final int MAX_HIDE_DELAY = 1000;
    private final Runnable delayedHide;
    private final Runnable delayedShow;
    private final LoadingIndicatorDrawable drawable;
    private long lastShowStartTime;
    private final int minHideDelay;
    private final int showDelay;
    private final LoadingIndicatorSpec specs;

    /* renamed from: com.google.android.material.loadingindicator.LoadingIndicator$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ LoadingIndicator this$0;

        public AnonymousClass1(LoadingIndicator r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            LoadingIndicator.access$000(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.loadingindicator.LoadingIndicator$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ LoadingIndicator this$0;

        public AnonymousClass2(LoadingIndicator r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            LoadingIndicator.access$100(this.this$0);
            LoadingIndicator.access$202(this.this$0, -1);
        }
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_LoadingIndicator;
    }

    public LoadingIndicator(Context r2) {
        this(r2, null);
    }

    public LoadingIndicator(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.loadingIndicatorStyle);
    }

    public LoadingIndicator(Context r7, AttributeSet r8, int r9) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r4), r8, r9);
        this.lastShowStartTime = -1;
        this.delayedShow = new AnonymousClass1(this);
        this.delayedHide = new AnonymousClass2(this);
        Context r0 = getContext();
        LoadingIndicatorDrawable r72 = LoadingIndicatorDrawable.create(r0, new LoadingIndicatorSpec(r0, r8, r9));
        this.drawable = r72;
        r72.setCallback(this);
        this.specs = r72.getDrawingDelegate().specs;
        TypedArray r73 = ThemeEnforcement.obtainStyledAttributes(r0, r8, R.styleable.LoadingIndicator, r9, r4, new int[0]);
        this.showDelay = r73.getInt(R.styleable.LoadingIndicator_showDelay, -1);
        this.minHideDelay = Math.min(r73.getInt(R.styleable.LoadingIndicator_minHideDelay, -1), 1000);
        r73.recycle();
        setAnimatorDurationScaleProvider(new AnimatorDurationScaleProvider());
    }

    public static /* synthetic */ void access$000(LoadingIndicator r0) {
        r0.internalShow();
    }

    public static /* synthetic */ void access$100(LoadingIndicator r0) {
        r0.internalHide();
    }

    public static /* synthetic */ long access$202(LoadingIndicator r0, long r1) {
        r0.lastShowStartTime = r1;
        return r1;
    }

    private void internalHide() {
        getDrawable().setVisible(false, false, true);
        if (getDrawable().isVisible() == true) goto L6;
        setVisibility(4);
        return;
    }

    private void internalShow() {
        if (this.minHideDelay <= 0) goto L5;
        this.lastShowStartTime = SystemClock.uptimeMillis();
    L5:
        setVisibility(0);
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return ProgressBar.class.getName();
    }

    public int getContainerColor() {
        return this.specs.containerColor;
    }

    public int getContainerHeight() {
        return this.specs.containerHeight;
    }

    public int getContainerWidth() {
        return this.specs.containerWidth;
    }

    public LoadingIndicatorDrawable getDrawable() {
        return this.drawable;
    }

    public int[] getIndicatorColor() {
        return this.specs.indicatorColors;
    }

    public int getIndicatorSize() {
        return this.specs.indicatorSize;
    }

    public void hide() {
        if (getVisibility() == 0) goto L6;
        removeCallbacks(this.delayedShow);
        return;
    L6:
        removeCallbacks(this.delayedHide);
        long r0 = SystemClock.uptimeMillis() - this.lastShowStartTime;
        int r2 = this.minHideDelay;
        long r3 = r2;
        Runnable r4 = this.delayedHide;
        if (r0 < r3) goto L10;
        r4.run();
        return;
    L10:
        postDelayed(r4, r2 - r0);
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        invalidate();
    }

    public boolean isEffectivelyVisible() {
        View r0 = this;
    L4:
        if (r0.getVisibility() != 0) goto L5;
        Object r02 = r0.getParent();
        if (r02 == null) goto L9;
        if ((r02 instanceof View) == false) goto L14;
        r0 = (View) r02;
        goto L4
    L14:
        return true;
    L9:
        if (getWindowVisibility() != 0) goto L11;
        return true;
    L11:
        return false;
    L5:
        return false;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (visibleToUser() == false) goto L6;
        internalShow();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        super.onDraw(r6);
        int r0 = r6.save();
        if (getPaddingLeft() == 0) goto L5;
    L6:
        r6.translate(getPaddingLeft(), getPaddingTop());
    L8:
        if (getPaddingRight() == 0) goto L10;
    L11:
        r6.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
    L12:
        this.drawable.draw(r6);
        r6.restoreToCount(r0);
        return;
    L10:
        if (getPaddingBottom() == 0) goto L12;
    L5:
        if (getPaddingTop() == 0) goto L8;
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r9, int r10) {
        int r0 = View.MeasureSpec.getMode(r9);
        int r1 = View.MeasureSpec.getMode(r10);
        int r2 = View.MeasureSpec.getSize(r9);
        int r3 = View.MeasureSpec.getSize(r10);
        LoadingIndicatorDrawingDelegate r4 = this.drawable.getDrawingDelegate();
        int r5 = (r4.getPreferredWidth() + getPaddingLeft()) + getPaddingRight();
        int r42 = (r4.getPreferredHeight() + getPaddingTop()) + getPaddingBottom();
        if (r0 != Integer.MIN_VALUE) goto L5;
        r9 = View.MeasureSpec.makeMeasureSpec(Math.min(r2, r5), TLObject.FLAG_30);
    L7:
        if (r1 != Integer.MIN_VALUE) goto L9;
        r10 = View.MeasureSpec.makeMeasureSpec(Math.min(r3, r42), TLObject.FLAG_30);
    L11:
        super.onMeasure(r9, r10);
        return;
    L9:
        if (r1 != 0) goto L11;
        r10 = View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30);
        goto L11
    L5:
        if (r0 != 0) goto L7;
        r9 = View.MeasureSpec.makeMeasureSpec(r5, TLObject.FLAG_30);
        goto L7
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        this.drawable.setBounds(0, 0, r1, r2);
    }

    @Override // android.view.View
    public void onVisibilityChanged(View r2, int r3) {
        super.onVisibilityChanged(r2, r3);
        LoadingIndicatorDrawable r22 = this.drawable;
        boolean r1 = visibleToUser();
        if (r3 != 0) goto L5;
        boolean r32 = true;
    L6:
        r22.setVisible(r1, false, r32);
        return;
    L5:
        r32 = false;
        goto L6
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int r3) {
        super.onWindowVisibilityChanged(r3);
        LoadingIndicatorDrawable r0 = this.drawable;
        boolean r2 = visibleToUser();
        if (r3 != 0) goto L5;
        boolean r32 = true;
    L6:
        r0.setVisible(r2, false, r32);
        return;
    L5:
        r32 = false;
        goto L6
    }

    public void setAnimatorDurationScaleProvider(AnimatorDurationScaleProvider r1) {
        this.drawable.animatorDurationScaleProvider = r1;
    }

    public void setContainerColor(int r3) {
        LoadingIndicatorSpec r0 = this.specs;
        if (r0.containerColor == r3) goto L6;
        r0.containerColor = r3;
        invalidate();
        return;
    }

    public void setContainerHeight(int r3) {
        LoadingIndicatorSpec r0 = this.specs;
        if (r0.containerHeight == r3) goto L6;
        r0.containerHeight = r3;
        requestLayout();
        invalidate();
        return;
    }

    public void setContainerWidth(int r3) {
        LoadingIndicatorSpec r0 = this.specs;
        if (r0.containerWidth == r3) goto L6;
        r0.containerWidth = r3;
        requestLayout();
        invalidate();
        return;
    }

    public void setIndicatorColor(int... r4) {
        if (r4.length != 0) goto L6;
        r4 = new int[]{MaterialColors.getColor(getContext(), R$attr.colorPrimary, -1)};
    L6:
        if (Arrays.equals(getIndicatorColor(), r4) == true) goto L9;
        this.specs.indicatorColors = r4;
        this.drawable.getAnimatorDelegate().invalidateSpecValues();
        invalidate();
        return;
    }

    public void setIndicatorSize(int r3) {
        LoadingIndicatorSpec r0 = this.specs;
        if (r0.indicatorSize == r3) goto L6;
        r0.indicatorSize = r3;
        requestLayout();
        invalidate();
        return;
    }

    public void show() {
        int r0 = this.showDelay;
        Runnable r1 = this.delayedShow;
        if (r0 <= 0) goto L6;
        removeCallbacks(r1);
        postDelayed(this.delayedShow, this.showDelay);
        return;
    L6:
        r1.run();
    }

    public boolean visibleToUser() {
        if (isAttachedToWindow() == true) goto L5;
        return false;
    L5:
        if (getWindowVisibility() == 0) goto L7;
        return false;
    L7:
        if (isEffectivelyVisible() == false) goto L13;
        return true;
    L13:
        return false;
    }
}
