package com.google.android.material.switchmaterial;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class SwitchMaterial extends SwitchCompat {
    private static final int DEF_STYLE_RES = 0;
    private static final int[][] ENABLED_CHECKED_STATES = null;
    private final ElevationOverlayProvider elevationOverlayProvider;
    private ColorStateList materialThemeColorsThumbTintList;
    private ColorStateList materialThemeColorsTrackTintList;
    private boolean useMaterialThemeColors;

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_CompoundButton_Switch;
        ENABLED_CHECKED_STATES = new int[][]{new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}, new int[]{android.R.attr.state_enabled, -16842912}, new int[]{-16842910, android.R.attr.state_checked}, new int[]{-16842910, -16842912}};
    }

    public SwitchMaterial(Context r2) {
        this(r2, null);
    }

    public SwitchMaterial(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.switchStyle);
    }

    public SwitchMaterial(Context r7, AttributeSet r8, int r9) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r4), r8, r9);
        Context r0 = getContext();
        this.elevationOverlayProvider = new ElevationOverlayProvider(r0);
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r0, r8, R.styleable.SwitchMaterial, r9, r4, new int[0]);
        this.useMaterialThemeColors = r82.getBoolean(R.styleable.SwitchMaterial_useMaterialThemeColors, false);
        r82.recycle();
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.materialThemeColorsThumbTintList != null) goto L9;
        int r0 = MaterialColors.getColor(this, R.attr.colorSurface);
        int r1 = MaterialColors.getColor(this, R$attr.colorControlActivated);
        float r2 = getResources().getDimension(R.dimen.mtrl_switch_thumb_elevation);
        if (this.elevationOverlayProvider.isThemeElevationOverlayEnabled() == false) goto L7;
        r2 = r2 + ViewUtils.getParentAbsoluteElevation(this);
    L7:
        int r22 = this.elevationOverlayProvider.compositeOverlayIfNeeded(r0, r2);
        int[][] r3 = ENABLED_CHECKED_STATES;
        int[] r4 = new int[r3.length];
        r4[0] = MaterialColors.layer(r0, r1, 1.0f);
        r4[1] = r22;
        r4[2] = MaterialColors.layer(r0, r1, 0.38f);
        r4[3] = r22;
        this.materialThemeColorsThumbTintList = new ColorStateList(r3, r4);
    L9:
        return this.materialThemeColorsThumbTintList;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.materialThemeColorsTrackTintList != null) goto L6;
        int[][] r0 = ENABLED_CHECKED_STATES;
        int[] r1 = new int[r0.length];
        int r2 = MaterialColors.getColor(this, R.attr.colorSurface);
        int r3 = MaterialColors.getColor(this, R$attr.colorControlActivated);
        int r4 = MaterialColors.getColor(this, R.attr.colorOnSurface);
        r1[0] = MaterialColors.layer(r2, r3, 0.54f);
        r1[1] = MaterialColors.layer(r2, r4, 0.32f);
        r1[2] = MaterialColors.layer(r2, r3, 0.12f);
        r1[3] = MaterialColors.layer(r2, r4, 0.12f);
        this.materialThemeColorsTrackTintList = new ColorStateList(r0, r1);
    L6:
        return this.materialThemeColorsTrackTintList;
    }

    public boolean isUseMaterialThemeColors() {
        return this.useMaterialThemeColors;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors == false) goto L8;
        if (getThumbTintList() != null) goto L8;
        setThumbTintList(getMaterialThemeColorsThumbTintList());
    L8:
        if (this.useMaterialThemeColors == true) goto L10;
        return;
    L10:
        if (getTrackTintList() != null) goto L14;
        setTrackTintList(getMaterialThemeColorsTrackTintList());
        return;
    }

    public void setUseMaterialThemeColors(boolean r1) {
        this.useMaterialThemeColors = r1;
        if (r1 == false) goto L6;
        setThumbTintList(getMaterialThemeColorsThumbTintList());
        setTrackTintList(getMaterialThemeColorsTrackTintList());
        return;
    L6:
        setThumbTintList(null);
        setTrackTintList(null);
    }
}
