package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes5.dex */
public final class CircularProgressIndicatorSpec extends BaseProgressIndicatorSpec {
    public int indeterminateAnimationType;
    public boolean indeterminateTrackVisible;
    public int indicatorDirection;
    public int indicatorInset;
    public int indicatorSize;

    public CircularProgressIndicatorSpec(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicatorSpec(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, CircularProgressIndicator.DEF_STYLE_RES);
    }

    public CircularProgressIndicatorSpec(Context r10, AttributeSet r11, int r12, int r13) {
        super(r10, r11, r12, r13);
        int r0 = r10.getResources().getDimensionPixelSize(R.dimen.mtrl_progress_circular_size_medium);
        int r1 = r10.getResources().getDimensionPixelSize(R.dimen.mtrl_progress_circular_inset_medium);
        TypedArray r102 = ThemeEnforcement.obtainStyledAttributes(r10, r11, R.styleable.CircularProgressIndicator, r12, r13, new int[0]);
        this.indeterminateAnimationType = r102.getInt(R.styleable.CircularProgressIndicator_indeterminateAnimationTypeCircular, 0);
        this.indicatorSize = Math.max(MaterialResources.getDimensionPixelSize(r10, r102, R.styleable.CircularProgressIndicator_indicatorSize, r0), this.trackThickness * 2);
        this.indicatorInset = MaterialResources.getDimensionPixelSize(r10, r102, R.styleable.CircularProgressIndicator_indicatorInset, r1);
        this.indicatorDirection = r102.getInt(R.styleable.CircularProgressIndicator_indicatorDirectionCircular, 0);
        this.indeterminateTrackVisible = r102.getBoolean(R.styleable.CircularProgressIndicator_indeterminateTrackVisible, true);
        r102.recycle();
        validateSpec();
    }
}
