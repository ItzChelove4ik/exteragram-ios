package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class LinearProgressIndicator extends BaseProgressIndicator<LinearProgressIndicatorSpec> {
    public static final int DEF_STYLE_RES = 0;
    public static final int INDETERMINATE_ANIMATION_TYPE_CONTIGUOUS = 0;
    public static final int INDETERMINATE_ANIMATION_TYPE_DISJOINT = 1;
    public static final int INDICATOR_DIRECTION_END_TO_START = 3;
    public static final int INDICATOR_DIRECTION_LEFT_TO_RIGHT = 0;
    public static final int INDICATOR_DIRECTION_RIGHT_TO_LEFT = 1;
    public static final int INDICATOR_DIRECTION_START_TO_END = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    public @interface IndeterminateAnimationType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    public @interface IndicatorDirection {
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_LinearProgressIndicator;
    }

    public LinearProgressIndicator(Context r2) {
        this(r2, null);
    }

    public LinearProgressIndicator(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.linearProgressIndicatorStyle);
    }

    public LinearProgressIndicator(Context r2, AttributeSet r3, int r4) {
        super(r2, r3, r4, DEF_STYLE_RES);
        initializeDrawables();
        this.initialized = true;
    }

    private void initializeDrawables() {
        LinearDrawingDelegate r0 = new LinearDrawingDelegate((LinearProgressIndicatorSpec) this.spec);
        setIndeterminateDrawable(IndeterminateDrawable.createLinearDrawable(getContext(), (LinearProgressIndicatorSpec) this.spec, r0));
        setProgressDrawable(DeterminateDrawable.createLinearDrawable(getContext(), (LinearProgressIndicatorSpec) this.spec, r0));
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public /* bridge */ /* synthetic */ BaseProgressIndicatorSpec createSpec(Context r1, AttributeSet r2) {
        return createSpec(r1, r2);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public LinearProgressIndicatorSpec createSpec(Context r1, AttributeSet r2) {
        return new LinearProgressIndicatorSpec(r1, r2);
    }

    public int getIndeterminateAnimationType() {
        return ((LinearProgressIndicatorSpec) this.spec).indeterminateAnimationType;
    }

    public int getIndicatorDirection() {
        return ((LinearProgressIndicatorSpec) this.spec).indicatorDirection;
    }

    public int getTrackInnerCornerRadius() {
        return ((LinearProgressIndicatorSpec) this.spec).trackInnerCornerRadius;
    }

    public Integer getTrackStopIndicatorPadding() {
        return ((LinearProgressIndicatorSpec) this.spec).trackStopIndicatorPadding;
    }

    public int getTrackStopIndicatorSize() {
        return ((LinearProgressIndicatorSpec) this.spec).trackStopIndicatorSize;
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        S r12 = this.spec;
        LinearProgressIndicatorSpec r22 = (LinearProgressIndicatorSpec) r12;
        boolean r32 = true;
        if (((LinearProgressIndicatorSpec) r12).indicatorDirection != 1) goto L5;
    L14:
        r22.drawHorizontallyInverse = r32;
        return;
    L5:
        if (getLayoutDirection() != 1) goto L9;
        if (((LinearProgressIndicatorSpec) this.spec).indicatorDirection == 2) goto L14;
    L9:
        if (getLayoutDirection() == 0) goto L11;
    L13:
        r32 = false;
        goto L14
    L11:
        if (((LinearProgressIndicatorSpec) this.spec).indicatorDirection != 3) goto L13;
        goto L13
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        int r12 = r1 - (getPaddingLeft() + getPaddingRight());
        int r22 = r2 - (getPaddingTop() + getPaddingBottom());
        IndeterminateDrawable<LinearProgressIndicatorSpec> r32 = getIndeterminateDrawable();
        if (r32 == null) goto L5;
        r32.setBounds(0, 0, r12, r22);
    L5:
        DeterminateDrawable<LinearProgressIndicatorSpec> r0 = getProgressDrawable();
        if (r0 == null) goto L9;
        r0.setBounds(0, 0, r12, r22);
        return;
    }

    public void setIndeterminateAnimationType(int r4) {
        if (((LinearProgressIndicatorSpec) this.spec).indeterminateAnimationType != r4) goto L6;
        return;
    L6:
        if (visibleToUser() == true) goto L8;
    L12:
        S r0 = this.spec;
        ((LinearProgressIndicatorSpec) r0).indeterminateAnimationType = r4;
        ((LinearProgressIndicatorSpec) r0).validateSpec();
        if (r4 != 0) goto L15;
        getIndeterminateDrawable().setAnimatorDelegate(new LinearIndeterminateContiguousAnimatorDelegate((LinearProgressIndicatorSpec) this.spec));
    L16:
        registerSwitchIndeterminateModeCallback();
        invalidate();
        return;
    L15:
        getIndeterminateDrawable().setAnimatorDelegate(new LinearIndeterminateDisjointAnimatorDelegate(getContext(), (LinearProgressIndicatorSpec) this.spec));
        goto L16
    L8:
        if (isIndeterminate() == false) goto L12;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setIndicatorColor(int... r1) {
        super.setIndicatorColor(r1);
        ((LinearProgressIndicatorSpec) this.spec).validateSpec();
    }

    public void setIndicatorDirection(int r5) {
        S r0 = this.spec;
        ((LinearProgressIndicatorSpec) r0).indicatorDirection = r5;
        LinearProgressIndicatorSpec r02 = (LinearProgressIndicatorSpec) r0;
        boolean r1 = true;
        if (r5 != 1) goto L5;
    L14:
        r02.drawHorizontallyInverse = r1;
        invalidate();
        return;
    L5:
        if (getLayoutDirection() != 1) goto L9;
        if (((LinearProgressIndicatorSpec) this.spec).indicatorDirection == 2) goto L14;
    L9:
        if (getLayoutDirection() == 0) goto L11;
    L13:
        r1 = false;
        goto L14
    L11:
        if (r5 != 3) goto L13;
        goto L13
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setProgressCompat(int r2, boolean r3) {
        S r0 = this.spec;
        if (r0 != 0) goto L5;
    L9:
        super.setProgressCompat(r2, r3);
        return;
    L5:
        if (((LinearProgressIndicatorSpec) r0).indeterminateAnimationType != 0) goto L9;
        if (isIndeterminate() == false) goto L9;
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackCornerRadius(int r1) {
        super.setTrackCornerRadius(r1);
        ((LinearProgressIndicatorSpec) this.spec).validateSpec();
        invalidate();
    }

    public void setTrackInnerCornerRadius(int r4) {
        S r0 = this.spec;
        if (((LinearProgressIndicatorSpec) r0).trackInnerCornerRadius == r4) goto L6;
        ((LinearProgressIndicatorSpec) r0).trackInnerCornerRadius = Math.round(Math.min(r4, ((LinearProgressIndicatorSpec) r0).trackThickness / 2.0f));
        S r42 = this.spec;
        ((LinearProgressIndicatorSpec) r42).useRelativeTrackInnerCornerRadius = false;
        ((LinearProgressIndicatorSpec) r42).hasInnerCornerRadius = true;
        ((LinearProgressIndicatorSpec) r42).validateSpec();
        invalidate();
        return;
    }

    public void setTrackInnerCornerRadiusFraction(float r3) {
        S r0 = this.spec;
        if (((LinearProgressIndicatorSpec) r0).trackInnerCornerRadiusFraction == r3) goto L6;
        float r32 = Math.min(r3, 0.5f);
        ((LinearProgressIndicatorSpec) r0).trackInnerCornerRadiusFraction = r32;
        S r33 = this.spec;
        ((LinearProgressIndicatorSpec) r33).useRelativeTrackInnerCornerRadius = true;
        ((LinearProgressIndicatorSpec) r33).hasInnerCornerRadius = true;
        ((LinearProgressIndicatorSpec) r33).validateSpec();
        invalidate();
        return;
    }

    public void setTrackStopIndicatorPadding(Integer r2) {
        if (Objects.equals(((LinearProgressIndicatorSpec) this.spec).trackStopIndicatorPadding, r2) == true) goto L6;
        ((LinearProgressIndicatorSpec) this.spec).trackStopIndicatorPadding = r2;
        invalidate();
        return;
    }

    public void setTrackStopIndicatorSize(int r3) {
        S r0 = this.spec;
        if (((LinearProgressIndicatorSpec) r0).trackStopIndicatorSize == r3) goto L6;
        ((LinearProgressIndicatorSpec) r0).trackStopIndicatorSize = r3;
        ((LinearProgressIndicatorSpec) r0).validateSpec();
        invalidate();
        return;
    }
}
