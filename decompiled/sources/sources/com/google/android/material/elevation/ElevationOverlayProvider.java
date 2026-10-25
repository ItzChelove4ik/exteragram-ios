package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;

/* loaded from: classes5.dex */
public class ElevationOverlayProvider {
    private static final float FORMULA_MULTIPLIER = 4.5f;
    private static final float FORMULA_OFFSET = 2.0f;
    private static final int OVERLAY_ACCENT_COLOR_ALPHA = 0;
    private final int colorSurface;
    private final float displayDensity;
    private final int elevationOverlayAccentColor;
    private final int elevationOverlayColor;
    private final boolean elevationOverlayEnabled;

    static {
        OVERLAY_ACCENT_COLOR_ALPHA = (int) Math.round(5.1000000000000005d);
    }

    public ElevationOverlayProvider(Context r9) {
        this(MaterialAttributes.resolveBoolean(r9, R.attr.elevationOverlayEnabled, false), MaterialColors.getColor(r9, R.attr.elevationOverlayColor, 0), MaterialColors.getColor(r9, R.attr.elevationOverlayAccentColor, 0), MaterialColors.getColor(r9, R.attr.colorSurface, 0), r9.getResources().getDisplayMetrics().density);
    }

    public ElevationOverlayProvider(boolean r1, int r2, int r3, int r4, float r5) {
        this.elevationOverlayEnabled = r1;
        this.elevationOverlayColor = r2;
        this.elevationOverlayAccentColor = r3;
        this.colorSurface = r4;
        this.displayDensity = r5;
    }

    private boolean isThemeSurfaceColor(int r2) {
        if (ColorUtils.setAlphaComponent(r2, 255) != this.colorSurface) goto L6;
        return true;
    L6:
        return false;
    }

    public int calculateOverlayAlpha(float r1) {
        return Math.round(calculateOverlayAlphaFraction(r1) * 255.0f);
    }

    public float calculateOverlayAlphaFraction(float r3) {
        if (this.displayDensity > 0.0f) goto L5;
    L9:
        return 0.0f;
    L5:
        if (r3 <= 0.0f) goto L9;
        return Math.min(((((float) Math.log1p(r3 / r2)) * FORMULA_MULTIPLIER) + FORMULA_OFFSET) / 100.0f, 1.0f);
    }

    public int compositeOverlay(int r3, float r4) {
        float r42 = calculateOverlayAlphaFraction(r4);
        int r0 = Color.alpha(r3);
        int r32 = MaterialColors.layer(ColorUtils.setAlphaComponent(r3, 255), this.elevationOverlayColor, r42);
        if (r42 <= 0.0f) goto L8;
        int r2 = this.elevationOverlayAccentColor;
        if (r2 == 0) goto L8;
        r32 = MaterialColors.layer(r32, ColorUtils.setAlphaComponent(r2, OVERLAY_ACCENT_COLOR_ALPHA));
    L8:
        return ColorUtils.setAlphaComponent(r32, r0);
    }

    public int compositeOverlay(int r1, float r2, View r3) {
        return compositeOverlay(r1, r2 + getParentAbsoluteElevation(r3));
    }

    public int compositeOverlayIfNeeded(int r2, float r3) {
        if (this.elevationOverlayEnabled == true) goto L5;
    L8:
        return r2;
    L5:
        if (isThemeSurfaceColor(r2) == false) goto L8;
        return compositeOverlay(r2, r3);
    }

    public int compositeOverlayIfNeeded(int r1, float r2, View r3) {
        return compositeOverlayIfNeeded(r1, r2 + getParentAbsoluteElevation(r3));
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float r2) {
        return compositeOverlayIfNeeded(this.colorSurface, r2);
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float r1, View r2) {
        return compositeOverlayWithThemeSurfaceColorIfNeeded(r1 + getParentAbsoluteElevation(r2));
    }

    public float getParentAbsoluteElevation(View r1) {
        return ViewUtils.getParentAbsoluteElevation(r1);
    }

    public int getThemeElevationOverlayColor() {
        return this.elevationOverlayColor;
    }

    public int getThemeSurfaceColor() {
        return this.colorSurface;
    }

    public boolean isThemeElevationOverlayEnabled() {
        return this.elevationOverlayEnabled;
    }
}
