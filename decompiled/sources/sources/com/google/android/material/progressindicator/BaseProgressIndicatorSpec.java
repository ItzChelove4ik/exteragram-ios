package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.R$attr;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes5.dex */
public abstract class BaseProgressIndicatorSpec {
    public int hideAnimationBehavior;
    public float indeterminateAnimatorDurationScale;
    public int[] indicatorColors;
    public int indicatorTrackGapSize;
    public int showAnimationBehavior;
    public int trackColor;
    public int trackCornerRadius;
    public float trackCornerRadiusFraction;
    public int trackThickness;
    public boolean useRelativeTrackCornerRadius;
    public int waveAmplitude;
    public float waveAmplitudeRampProgressMax;
    public float waveAmplitudeRampProgressMin;
    public int waveSpeed;
    public int wavelengthDeterminate;
    public int wavelengthIndeterminate;

    public BaseProgressIndicatorSpec(Context r9, AttributeSet r10, int r11, int r12) {
        this.indicatorColors = new int[0];
        int r1 = r9.getResources().getDimensionPixelSize(R.dimen.mtrl_progress_track_thickness);
        TypedArray r92 = ThemeEnforcement.obtainStyledAttributes(r9, r10, R.styleable.BaseProgressIndicator, r11, r12, new int[0]);
        this.trackThickness = MaterialResources.getDimensionPixelSize(r9, r92, R.styleable.BaseProgressIndicator_trackThickness, r1);
        TypedValue r102 = r92.peekValue(R.styleable.BaseProgressIndicator_trackCornerRadius);
        if (r102 == null) goto L10;
        int r122 = r102.type;
        if (r122 != 5) goto L8;
        this.trackCornerRadius = Math.min(TypedValue.complexToDimensionPixelSize(r102.data, r92.getResources().getDisplayMetrics()), this.trackThickness / 2);
        this.useRelativeTrackCornerRadius = false;
        goto L10
    L8:
        if (r122 != 6) goto L10;
        this.trackCornerRadiusFraction = Math.min(r102.getFraction(1.0f, 1.0f), 0.5f);
        this.useRelativeTrackCornerRadius = true;
    L10:
        this.showAnimationBehavior = r92.getInt(R.styleable.BaseProgressIndicator_showAnimationBehavior, 0);
        this.hideAnimationBehavior = r92.getInt(R.styleable.BaseProgressIndicator_hideAnimationBehavior, 0);
        this.indicatorTrackGapSize = r92.getDimensionPixelSize(R.styleable.BaseProgressIndicator_indicatorTrackGapSize, 0);
        int r103 = Math.abs(r92.getDimensionPixelSize(R.styleable.BaseProgressIndicator_wavelength, 0));
        this.wavelengthDeterminate = Math.abs(r92.getDimensionPixelSize(R.styleable.BaseProgressIndicator_wavelengthDeterminate, r103));
        this.wavelengthIndeterminate = Math.abs(r92.getDimensionPixelSize(R.styleable.BaseProgressIndicator_wavelengthIndeterminate, r103));
        this.waveAmplitude = Math.abs(r92.getDimensionPixelSize(R.styleable.BaseProgressIndicator_waveAmplitude, 0));
        this.waveSpeed = r92.getDimensionPixelSize(R.styleable.BaseProgressIndicator_waveSpeed, 0);
        this.indeterminateAnimatorDurationScale = r92.getFloat(R.styleable.BaseProgressIndicator_indeterminateAnimatorDurationScale, 1.0f);
        this.waveAmplitudeRampProgressMin = r92.getFloat(R.styleable.BaseProgressIndicator_waveAmplitudeRampProgressMin, 0.1f);
        this.waveAmplitudeRampProgressMax = r92.getFloat(R.styleable.BaseProgressIndicator_waveAmplitudeRampProgressMax, 0.9f);
        loadIndicatorColors(r9, r92);
        loadTrackColor(r9, r92);
        r92.recycle();
    }

    private void loadIndicatorColors(Context r4, TypedArray r5) {
        if (r5.hasValue(R.styleable.BaseProgressIndicator_indicatorColor) == true) goto L7;
        this.indicatorColors = new int[]{MaterialColors.getColor(r4, R$attr.colorPrimary, -1)};
        return;
    L7:
        if (r5.peekValue(R.styleable.BaseProgressIndicator_indicatorColor).type == 1) goto L10;
        this.indicatorColors = new int[]{r5.getColor(R.styleable.BaseProgressIndicator_indicatorColor, -1)};
        return;
    L10:
        int[] r42 = r4.getResources().getIntArray(r5.getResourceId(R.styleable.BaseProgressIndicator_indicatorColor, -1));
        this.indicatorColors = r42;
        if (r42.length == 0) goto L13;
        return;
    L13:
        f$$ExternalSyntheticBUOutline1.m("indicatorColors cannot be empty when indicatorColor is not used.");
    }

    private void loadTrackColor(Context r2, TypedArray r3) {
        if (r3.hasValue(R.styleable.BaseProgressIndicator_trackColor) == false) goto L6;
        this.trackColor = r3.getColor(R.styleable.BaseProgressIndicator_trackColor, -1);
        return;
    L6:
        this.trackColor = this.indicatorColors[0];
        TypedArray r22 = r2.getTheme().obtainStyledAttributes(new int[]{android.R.attr.disabledAlpha});
        float r32 = r22.getFloat(0, 0.2f);
        r22.recycle();
        int r23 = (int) (r32 * 255.0f);
        this.trackColor = MaterialColors.compositeARGBWithAlpha(this.trackColor, r23);
    }

    public int getTrackCornerRadiusInPx() {
        if (this.useRelativeTrackCornerRadius == false) goto L7;
        return (int) (this.trackThickness * this.trackCornerRadiusFraction);
    L7:
        return this.trackCornerRadius;
    }

    public boolean hasWavyEffect(boolean r2) {
        if (this.waveAmplitude <= 0) goto L12;
        if (r2 == false) goto L6;
    L7:
        if (r2 == true) goto L9;
        return false;
    L9:
        if (this.wavelengthDeterminate <= 0) goto L16;
        return true;
    L16:
        return false;
    L6:
        if (this.wavelengthIndeterminate <= 0) goto L7;
        return true;
    L12:
        return false;
    }

    public boolean isHideAnimationEnabled() {
        if (this.hideAnimationBehavior == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isShowAnimationEnabled() {
        if (this.showAnimationBehavior == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean useStrokeCap() {
        if (this.useRelativeTrackCornerRadius == true) goto L5;
        return false;
    L5:
        if (this.trackCornerRadiusFraction != 0.5f) goto L10;
        return true;
    L10:
        return false;
    }

    public void validateSpec() {
        if (this.indicatorTrackGapSize < 0) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("indicatorTrackGapSize must be >= 0.");
    }
}
