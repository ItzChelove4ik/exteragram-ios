package com.google.android.material.materialswitch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class MaterialSwitch extends SwitchCompat {
    private static final int DEF_STYLE_RES = 0;
    private static final int[] STATE_SET_WITH_ICON = null;
    private int[] currentStateChecked;
    private int[] currentStateUnchecked;
    private Drawable thumbDrawable;
    private Drawable thumbIconDrawable;
    private int thumbIconSize;
    private ColorStateList thumbIconTintList;
    private PorterDuff.Mode thumbIconTintMode;
    private ColorStateList thumbTintList;
    private Drawable trackDecorationDrawable;
    private ColorStateList trackDecorationTintList;
    private PorterDuff.Mode trackDecorationTintMode;
    private Drawable trackDrawable;
    private ColorStateList trackTintList;

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_CompoundButton_MaterialSwitch;
        STATE_SET_WITH_ICON = new int[]{R.attr.state_with_icon};
    }

    public MaterialSwitch(Context r2) {
        this(r2, null);
    }

    public MaterialSwitch(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialSwitchStyle);
    }

    public MaterialSwitch(Context r8, AttributeSet r9, int r10) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r8, r9, r10, r4), r9, r10);
        this.thumbIconSize = -1;
        Context r0 = getContext();
        this.thumbDrawable = super.getThumbDrawable();
        this.thumbTintList = super.getThumbTintList();
        super.setThumbTintList(null);
        this.trackDrawable = super.getTrackDrawable();
        this.trackTintList = super.getTrackTintList();
        super.setTrackTintList(null);
        TintTypedArray r92 = ThemeEnforcement.obtainTintedStyledAttributes(r0, r9, R.styleable.MaterialSwitch, r10, r4, new int[0]);
        this.thumbIconDrawable = r92.getDrawable(R.styleable.MaterialSwitch_thumbIcon);
        this.thumbIconSize = r92.getDimensionPixelSize(R.styleable.MaterialSwitch_thumbIconSize, -1);
        this.thumbIconTintList = r92.getColorStateList(R.styleable.MaterialSwitch_thumbIconTint);
        int r102 = r92.getInt(R.styleable.MaterialSwitch_thumbIconTintMode, -1);
        PorterDuff.Mode r02 = PorterDuff.Mode.SRC_IN;
        this.thumbIconTintMode = ViewUtils.parseTintMode(r102, r02);
        this.trackDecorationDrawable = r92.getDrawable(R.styleable.MaterialSwitch_trackDecoration);
        this.trackDecorationTintList = r92.getColorStateList(R.styleable.MaterialSwitch_trackDecorationTint);
        this.trackDecorationTintMode = ViewUtils.parseTintMode(r92.getInt(R.styleable.MaterialSwitch_trackDecorationTintMode, -1), r02);
        r92.recycle();
        setEnforceSwitchWidth(false);
        refreshThumbDrawable();
        refreshTrackDrawable();
    }

    private void refreshThumbDrawable() {
        this.thumbDrawable = DrawableUtils.createTintableDrawableIfNeeded(this.thumbDrawable, this.thumbTintList, getThumbTintMode());
        this.thumbIconDrawable = DrawableUtils.createTintableDrawableIfNeeded(this.thumbIconDrawable, this.thumbIconTintList, this.thumbIconTintMode);
        updateDrawableTints();
        Drawable r0 = this.thumbDrawable;
        Drawable r1 = this.thumbIconDrawable;
        int r2 = this.thumbIconSize;
        super.setThumbDrawable(DrawableUtils.compositeTwoLayeredDrawable(r0, r1, r2, r2));
        refreshDrawableState();
    }

    private void refreshTrackDrawable() {
        this.trackDrawable = DrawableUtils.createTintableDrawableIfNeeded(this.trackDrawable, this.trackTintList, getTrackTintMode());
        this.trackDecorationDrawable = DrawableUtils.createTintableDrawableIfNeeded(this.trackDecorationDrawable, this.trackDecorationTintList, this.trackDecorationTintMode);
        updateDrawableTints();
        Drawable r0 = this.trackDrawable;
        if (r0 != null) goto L5;
    L7:
        if (r0 != null) goto L10;
        r0 = this.trackDecorationDrawable;
    L10:
        if (r0 == null) goto L12;
        setSwitchMinWidth(r0.getIntrinsicWidth());
    L12:
        super.setTrackDrawable(r0);
        return;
    L5:
        if (this.trackDecorationDrawable == null) goto L7;
        r0 = new LayerDrawable(new Drawable[]{this.trackDrawable, this.trackDecorationDrawable});
        goto L10
    }

    private static void setInterpolatedDrawableTintIfPossible(Drawable r1, ColorStateList r2, int[] r3, int[] r4, float r5) {
        if (r1 == null) goto L7;
        if (r2 == null) goto L8;
        r1.setTint(ColorUtils.blendARGB(r2.getColorForState(r3, 0), r2.getColorForState(r4, 0), r5));
        return;
    L8:
        return;
    }

    private void updateDrawableTints() {
        if (this.thumbTintList == null) goto L5;
    L11:
        float r0 = getThumbPosition();
        ColorStateList r1 = this.thumbTintList;
        if (r1 == null) goto L14;
        setInterpolatedDrawableTintIfPossible(this.thumbDrawable, r1, this.currentStateUnchecked, this.currentStateChecked, r0);
    L14:
        ColorStateList r12 = this.thumbIconTintList;
        if (r12 == null) goto L17;
        setInterpolatedDrawableTintIfPossible(this.thumbIconDrawable, r12, this.currentStateUnchecked, this.currentStateChecked, r0);
    L17:
        ColorStateList r13 = this.trackTintList;
        if (r13 == null) goto L20;
        setInterpolatedDrawableTintIfPossible(this.trackDrawable, r13, this.currentStateUnchecked, this.currentStateChecked, r0);
    L20:
        ColorStateList r14 = this.trackDecorationTintList;
        if (r14 == null) goto L24;
        setInterpolatedDrawableTintIfPossible(this.trackDecorationDrawable, r14, this.currentStateUnchecked, this.currentStateChecked, r0);
        return;
    L24:
        return;
    L5:
        if (this.thumbIconTintList != null) goto L11;
        if (this.trackTintList != null) goto L11;
        if (this.trackDecorationTintList != null) goto L11;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getThumbDrawable() {
        return this.thumbDrawable;
    }

    public Drawable getThumbIconDrawable() {
        return this.thumbIconDrawable;
    }

    public int getThumbIconSize() {
        return this.thumbIconSize;
    }

    public ColorStateList getThumbIconTintList() {
        return this.thumbIconTintList;
    }

    public PorterDuff.Mode getThumbIconTintMode() {
        return this.thumbIconTintMode;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getThumbTintList() {
        return this.thumbTintList;
    }

    public Drawable getTrackDecorationDrawable() {
        return this.trackDecorationDrawable;
    }

    public ColorStateList getTrackDecorationTintList() {
        return this.trackDecorationTintList;
    }

    public PorterDuff.Mode getTrackDecorationTintMode() {
        return this.trackDecorationTintMode;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getTrackDrawable() {
        return this.trackDrawable;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getTrackTintList() {
        return this.trackTintList;
    }

    @Override // android.view.View
    public void invalidate() {
        updateDrawableTints();
        super.invalidate();
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int r2) {
        int[] r22 = super.onCreateDrawableState(r2 + 1);
        if (this.thumbIconDrawable == null) goto L5;
        View.mergeDrawableStates(r22, STATE_SET_WITH_ICON);
    L5:
        this.currentStateUnchecked = DrawableUtils.getUncheckedState(r22);
        this.currentStateChecked = DrawableUtils.getCheckedState(r22);
        return r22;
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (this.trackDrawable == null) goto L8;
        FocusRingDrawable r12 = FocusRingDrawable.find(getBackground());
        if (r12 == null) goto L9;
        r12.mutate();
        r12.setFocusRingBounds(this.trackDrawable.getBounds());
        return;
    L9:
        return;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbDrawable(Drawable r1) {
        this.thumbDrawable = r1;
        refreshThumbDrawable();
    }

    public void setThumbIconDrawable(Drawable r1) {
        this.thumbIconDrawable = r1;
        refreshThumbDrawable();
    }

    public void setThumbIconResource(int r2) {
        setThumbIconDrawable(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setThumbIconSize(int r2) {
        if (this.thumbIconSize == r2) goto L6;
        this.thumbIconSize = r2;
        refreshThumbDrawable();
        return;
    }

    public void setThumbIconTintList(ColorStateList r1) {
        this.thumbIconTintList = r1;
        refreshThumbDrawable();
    }

    public void setThumbIconTintMode(PorterDuff.Mode r1) {
        this.thumbIconTintMode = r1;
        refreshThumbDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintList(ColorStateList r1) {
        this.thumbTintList = r1;
        refreshThumbDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintMode(PorterDuff.Mode r1) {
        super.setThumbTintMode(r1);
        refreshThumbDrawable();
    }

    public void setTrackDecorationDrawable(Drawable r1) {
        this.trackDecorationDrawable = r1;
        refreshTrackDrawable();
    }

    public void setTrackDecorationResource(int r2) {
        setTrackDecorationDrawable(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setTrackDecorationTintList(ColorStateList r1) {
        this.trackDecorationTintList = r1;
        refreshTrackDrawable();
    }

    public void setTrackDecorationTintMode(PorterDuff.Mode r1) {
        this.trackDecorationTintMode = r1;
        refreshTrackDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackDrawable(Drawable r1) {
        this.trackDrawable = r1;
        refreshTrackDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintList(ColorStateList r1) {
        this.trackTintList = r1;
        refreshTrackDrawable();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintMode(PorterDuff.Mode r1) {
        super.setTrackTintMode(r1);
        refreshTrackDrawable();
    }
}
