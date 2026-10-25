package com.google.android.material.loadingindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.R$attr;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes5.dex */
public final class LoadingIndicatorSpec {
    int containerColor;
    int containerHeight;
    int containerWidth;
    int[] indicatorColors;
    int indicatorSize;
    boolean scaleToFit;

    public LoadingIndicatorSpec(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.loadingIndicatorStyle);
    }

    public LoadingIndicatorSpec(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, LoadingIndicator.DEF_STYLE_RES);
    }

    public LoadingIndicatorSpec(Context r10, AttributeSet r11, int r12, int r13) {
        this.scaleToFit = false;
        this.indicatorColors = new int[0];
        int r1 = r10.getResources().getDimensionPixelSize(R.dimen.m3_loading_indicator_shape_size);
        int r2 = r10.getResources().getDimensionPixelSize(R.dimen.m3_loading_indicator_container_size);
        TypedArray r102 = ThemeEnforcement.obtainStyledAttributes(r10, r11, R.styleable.LoadingIndicator, r12, r13, new int[0]);
        this.indicatorSize = r102.getDimensionPixelSize(R.styleable.LoadingIndicator_indicatorSize, r1);
        this.containerWidth = r102.getDimensionPixelSize(R.styleable.LoadingIndicator_containerWidth, r2);
        this.containerHeight = r102.getDimensionPixelSize(R.styleable.LoadingIndicator_containerHeight, r2);
        loadIndicatorColors(r10, r102);
        this.containerColor = r102.getColor(R.styleable.LoadingIndicator_containerColor, 0);
        r102.recycle();
    }

    private void loadIndicatorColors(Context r4, TypedArray r5) {
        if (r5.hasValue(R.styleable.LoadingIndicator_indicatorColor) == true) goto L7;
        this.indicatorColors = new int[]{MaterialColors.getColor(r4, R$attr.colorPrimary, -1)};
        return;
    L7:
        if (r5.peekValue(R.styleable.LoadingIndicator_indicatorColor).type == 1) goto L10;
        this.indicatorColors = new int[]{r5.getColor(R.styleable.LoadingIndicator_indicatorColor, -1)};
        return;
    L10:
        int[] r42 = r4.getResources().getIntArray(r5.getResourceId(R.styleable.LoadingIndicator_indicatorColor, -1));
        this.indicatorColors = r42;
        if (r42.length == 0) goto L13;
        return;
    L13:
        f$$ExternalSyntheticBUOutline1.m("indicatorColors cannot be empty when indicatorColor is not used.");
    }

    public void setScaleToFit(boolean r1) {
        this.scaleToFit = r1;
    }
}
