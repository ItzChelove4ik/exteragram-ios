package com.google.android.material.radiobutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.widget.CompoundButtonCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class MaterialRadioButton extends AppCompatRadioButton {
    private static final int DEF_STYLE_RES = 0;
    private static final int[][] ENABLED_CHECKED_STATES = null;
    private ColorStateList materialThemeColorsTintList;
    private boolean useMaterialThemeColors;

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_CompoundButton_RadioButton;
        ENABLED_CHECKED_STATES = new int[][]{new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}, new int[]{android.R.attr.state_enabled, -16842912}, new int[]{-16842910, android.R.attr.state_checked}, new int[]{-16842910, -16842912}};
    }

    public MaterialRadioButton(Context r2) {
        this(r2, null);
    }

    public MaterialRadioButton(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.radioButtonStyle);
    }

    public MaterialRadioButton(Context r7, AttributeSet r8, int r9) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r4), r8, r9);
        Context r0 = getContext();
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r0, r8, R.styleable.MaterialRadioButton, r9, r4, new int[0]);
        if (r82.hasValue(R.styleable.MaterialRadioButton_buttonTint) == false) goto L6;
        CompoundButtonCompat.setButtonTintList(this, MaterialResources.getColorStateList(r0, r82, R.styleable.MaterialRadioButton_buttonTint));
    L6:
        if (r82.hasValue(R.styleable.MaterialRadioButton_rippleColor) == false) goto L8;
        setRippleColor(MaterialResources.getColorStateList(r0, r82, R.styleable.MaterialRadioButton_rippleColor));
    L8:
        this.useMaterialThemeColors = r82.getBoolean(R.styleable.MaterialRadioButton_useMaterialThemeColors, false);
        r82.recycle();
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.materialThemeColorsTintList != null) goto L6;
        int r0 = MaterialColors.getColor(this, R$attr.colorControlActivated);
        int r1 = MaterialColors.getColor(this, R.attr.colorOnSurface);
        int r2 = MaterialColors.getColor(this, R.attr.colorSurface);
        int[][] r3 = ENABLED_CHECKED_STATES;
        int[] r4 = new int[r3.length];
        r4[0] = MaterialColors.layer(r2, r0, 1.0f);
        r4[1] = MaterialColors.layer(r2, r1, 0.54f);
        r4[2] = MaterialColors.layer(r2, r1, 0.38f);
        r4[3] = MaterialColors.layer(r2, r1, 0.38f);
        this.materialThemeColorsTintList = new ColorStateList(r3, r4);
    L6:
        return this.materialThemeColorsTintList;
    }

    private void setRippleColor(ColorStateList r2) {
        if (r2 == null) goto L12;
        Drawable r1 = getBackground();
        if ((r1 instanceof DrawableWrapper) == false) goto L8;
        r1 = ((DrawableWrapper) r1).getDrawable();
    L8:
        if ((r1 instanceof RippleDrawable) == false) goto L11;
        ((RippleDrawable) r1).setColor(r2);
        return;
    L11:
        return;
    }

    public boolean isUseMaterialThemeColors() {
        return this.useMaterialThemeColors;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors == true) goto L5;
        return;
    L5:
        if (CompoundButtonCompat.getButtonTintList(this) != null) goto L9;
        setUseMaterialThemeColors(true);
        return;
    }

    public void setUseMaterialThemeColors(boolean r1) {
        this.useMaterialThemeColors = r1;
        if (r1 == false) goto L6;
        CompoundButtonCompat.setButtonTintList(this, getMaterialThemeColorsTintList());
        return;
    L6:
        CompoundButtonCompat.setButtonTintList(this, null);
    }
}
