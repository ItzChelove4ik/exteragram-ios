package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes5.dex */
public final class LinearProgressIndicatorSpec extends BaseProgressIndicatorSpec {
    boolean drawHorizontallyInverse;
    public boolean hasInnerCornerRadius;
    public int indeterminateAnimationType;
    public int indicatorDirection;
    public int trackInnerCornerRadius;
    public float trackInnerCornerRadiusFraction;
    public Integer trackStopIndicatorPadding;
    public int trackStopIndicatorSize;
    public boolean useRelativeTrackInnerCornerRadius;

    public LinearProgressIndicatorSpec(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.linearProgressIndicatorStyle);
    }

    public LinearProgressIndicatorSpec(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, LinearProgressIndicator.DEF_STYLE_RES);
    }

    public LinearProgressIndicatorSpec(Context r7, AttributeSet r8, int r9, int r10) {
        super(r7, r8, r9, r10);
        boolean r92 = false;
        TypedArray r72 = ThemeEnforcement.obtainStyledAttributes(r7, r8, R.styleable.LinearProgressIndicator, R.attr.linearProgressIndicatorStyle, LinearProgressIndicator.DEF_STYLE_RES, new int[0]);
        this.indeterminateAnimationType = r72.getInt(R.styleable.LinearProgressIndicator_indeterminateAnimationType, 1);
        this.indicatorDirection = r72.getInt(R.styleable.LinearProgressIndicator_indicatorDirectionLinear, 0);
        this.trackStopIndicatorSize = r72.getDimensionPixelSize(R.styleable.LinearProgressIndicator_trackStopIndicatorSize, 0);
        if (r72.hasValue(R.styleable.LinearProgressIndicator_trackStopIndicatorPadding) == false) goto L5;
        this.trackStopIndicatorPadding = Integer.valueOf(r72.getDimensionPixelSize(R.styleable.LinearProgressIndicator_trackStopIndicatorPadding, 0));
    L5:
        TypedValue r82 = r72.peekValue(R.styleable.LinearProgressIndicator_trackInnerCornerRadius);
        if (r82 == null) goto L13;
        int r0 = r82.type;
        if (r0 != 5) goto L11;
        this.trackInnerCornerRadius = Math.min(TypedValue.complexToDimensionPixelSize(r82.data, r72.getResources().getDisplayMetrics()), this.trackThickness / 2);
        this.useRelativeTrackInnerCornerRadius = false;
        this.hasInnerCornerRadius = true;
        goto L13
    L11:
        if (r0 != 6) goto L13;
        this.trackInnerCornerRadiusFraction = Math.min(r82.getFraction(1.0f, 1.0f), 0.5f);
        this.useRelativeTrackInnerCornerRadius = true;
        this.hasInnerCornerRadius = true;
    L13:
        r72.recycle();
        validateSpec();
        if (this.indicatorDirection != 1) goto L16;
        r92 = true;
    L16:
        this.drawHorizontallyInverse = r92;
    }

    public int getActualTrackStopIndicatorSize() {
        return Math.min(this.trackStopIndicatorSize, this.trackThickness);
    }

    public int getTrackInnerCornerRadiusInPx() {
        if (this.hasInnerCornerRadius == true) goto L7;
        return getTrackCornerRadiusInPx();
    L7:
        if (this.useRelativeTrackInnerCornerRadius == false) goto L11;
        return (int) (this.trackThickness * this.trackInnerCornerRadiusFraction);
    L11:
        return this.trackInnerCornerRadius;
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicatorSpec
    public boolean useStrokeCap() {
        if (super.useStrokeCap() == true) goto L5;
        return false;
    L5:
        if (getTrackInnerCornerRadiusInPx() != getTrackCornerRadiusInPx()) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicatorSpec
    public void validateSpec() {
        super.validateSpec();
        if (this.trackStopIndicatorSize >= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Stop indicator size must be >= 0.");
        return;
    L5:
        if (this.indeterminateAnimationType == 0) goto L7;
        return;
    L7:
        if (getTrackCornerRadiusInPx() > 0) goto L13;
        if (this.hasInnerCornerRadius == false) goto L15;
        if (getTrackInnerCornerRadiusInPx() > 0) goto L13;
    L15:
        if (this.indicatorColors.length >= 3) goto L24;
        f$$ExternalSyntheticBUOutline1.m("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
        return;
    L24:
        return;
    L13:
        if (this.indicatorTrackGapSize != 0) goto L15;
        f$$ExternalSyntheticBUOutline1.m("Rounded corners without gap are not supported in contiguous indeterminate animation.");
    }
}
