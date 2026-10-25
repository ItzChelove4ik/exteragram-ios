package com.google.android.material.card;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes5.dex */
public class MaterialCardView extends CardView implements Checkable, Shapeable {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.cardview.widget.CardView";
    private static final int[] CHECKABLE_STATE_SET = null;
    public static final int CHECKED_ICON_GRAVITY_BOTTOM_END = 8388693;
    public static final int CHECKED_ICON_GRAVITY_BOTTOM_START = 8388691;
    public static final int CHECKED_ICON_GRAVITY_TOP_END = 8388661;
    public static final int CHECKED_ICON_GRAVITY_TOP_START = 8388659;
    private static final int[] CHECKED_STATE_SET = null;
    private static final int DEF_STYLE_RES = 0;
    private static final int[] DRAGGED_STATE_SET = null;
    private static final int[] HOVERED_STATE_SET = null;
    private static final String LOG_TAG = "MaterialCardView";
    private final MaterialCardViewHelper cardViewHelper;
    private boolean checked;
    private boolean dragged;
    private boolean isParentCardViewDoneInitializing;
    private OnCheckedChangeListener onCheckedChangeListener;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CheckedIconGravity {
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialCardView r1, boolean r2);
    }

    static {
        CHECKABLE_STATE_SET = new int[]{R.attr.state_checkable};
        CHECKED_STATE_SET = new int[]{R.attr.state_checked};
        DRAGGED_STATE_SET = new int[]{com.google.android.material.R.attr.state_dragged};
        HOVERED_STATE_SET = new int[]{R.attr.state_hovered};
        DEF_STYLE_RES = com.google.android.material.R.style.Widget_MaterialComponents_CardView;
    }

    public MaterialCardView(Context r2) {
        this(r2, null);
    }

    public MaterialCardView(Context r2, AttributeSet r3) {
        this(r2, r3, com.google.android.material.R.attr.materialCardViewStyle);
    }

    public MaterialCardView(Context r7, AttributeSet r8, int r9) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r4), r8, r9);
        this.checked = false;
        this.dragged = false;
        this.isParentCardViewDoneInitializing = true;
        TypedArray r72 = ThemeEnforcement.obtainStyledAttributes(getContext(), r8, com.google.android.material.R.styleable.MaterialCardView, r9, r4, new int[0]);
        MaterialCardViewHelper r82 = new MaterialCardViewHelper(this, r8, r9, r4);
        this.cardViewHelper = r82;
        r82.setCardBackgroundColor(super.getCardBackgroundColor());
        r82.setUserContentPadding(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        r82.loadFromAttributes(r72);
        r72.recycle();
    }

    public static /* synthetic */ float access$001(MaterialCardView r0) {
        return super.getRadius();
    }

    private void forceRippleRedrawIfNeeded() {
        if (Build.VERSION.SDK_INT <= 26) goto L6;
        this.cardViewHelper.forceRippleRedraw();
        return;
    }

    private RectF getBoundsAsRectF() {
        RectF r0 = new RectF();
        r0.set(this.cardViewHelper.getBackground().getBounds());
        return r0;
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.cardViewHelper.getCardBackgroundColor();
    }

    public ColorStateList getCardForegroundColor() {
        return this.cardViewHelper.getCardForegroundColor();
    }

    public float getCardViewRadius() {
        return access$001(this);
    }

    public Drawable getCheckedIcon() {
        return this.cardViewHelper.getCheckedIcon();
    }

    public int getCheckedIconGravity() {
        return this.cardViewHelper.getCheckedIconGravity();
    }

    public int getCheckedIconMargin() {
        return this.cardViewHelper.getCheckedIconMargin();
    }

    public int getCheckedIconSize() {
        return this.cardViewHelper.getCheckedIconSize();
    }

    public ColorStateList getCheckedIconTint() {
        return this.cardViewHelper.getCheckedIconTint();
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.cardViewHelper.getUserContentPadding().bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.cardViewHelper.getUserContentPadding().left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.cardViewHelper.getUserContentPadding().right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.cardViewHelper.getUserContentPadding().top;
    }

    public float getProgress() {
        return this.cardViewHelper.getProgress();
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.cardViewHelper.getCornerRadius();
    }

    public ColorStateList getRippleColor() {
        return this.cardViewHelper.getRippleColor();
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.cardViewHelper.getShapeAppearance().getDefaultShape();
    }

    @Deprecated
    public int getStrokeColor() {
        return this.cardViewHelper.getStrokeColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.cardViewHelper.getStrokeColorStateList();
    }

    public int getStrokeWidth() {
        return this.cardViewHelper.getStrokeWidth();
    }

    public boolean isCheckable() {
        MaterialCardViewHelper r0 = this.cardViewHelper;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isCheckable() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    public boolean isDragged() {
        return this.dragged;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.cardViewHelper.updateClickable();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.cardViewHelper.getBackground());
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int r2) {
        int[] r22 = super.onCreateDrawableState(r2 + 8);
        if (isCheckable() == false) goto L6;
        View.mergeDrawableStates(r22, CHECKABLE_STATE_SET);
    L6:
        if (isChecked() == false) goto L9;
        View.mergeDrawableStates(r22, CHECKED_STATE_SET);
    L9:
        if (isDragged() == false) goto L12;
        View.mergeDrawableStates(r22, DRAGGED_STATE_SET);
    L12:
        if (isDuplicateParentStateEnabled() == true) goto L14;
    L28:
        return r22;
    L14:
        if (isPressed() == false) goto L17;
        View.mergeDrawableStates(r22, FrameLayout.PRESSED_STATE_SET);
    L17:
        if (isHovered() == false) goto L20;
        View.mergeDrawableStates(r22, HOVERED_STATE_SET);
    L20:
        if (isEnabled() == false) goto L23;
        View.mergeDrawableStates(r22, FrameLayout.ENABLED_STATE_SET);
    L23:
        if (isFocused() == false) goto L26;
        View.mergeDrawableStates(r22, FrameLayout.FOCUSED_STATE_SET);
    L26:
        if (isSelected() == false) goto L28;
        View.mergeDrawableStates(r22, FrameLayout.SELECTED_STATE_SET);
        goto L28
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent r2) {
        super.onInitializeAccessibilityEvent(r2);
        r2.setClassName(ACCESSIBILITY_CLASS_NAME);
        r2.setChecked(isChecked());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName(ACCESSIBILITY_CLASS_NAME);
        r2.setCheckable(isCheckable());
        r2.setClickable(isClickable());
        r2.setChecked(isChecked());
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        this.cardViewHelper.recalculateCheckedIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setAncestorContentPadding(int r1, int r2, int r3, int r4) {
        super.setContentPadding(r1, r2, r3, r4);
    }

    @Override // android.view.View
    public void setBackground(Drawable r1) {
        setBackgroundDrawable(r1);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable r3) {
        if (this.isParentCardViewDoneInitializing == true) goto L5;
        return;
    L5:
        if (this.cardViewHelper.isBackgroundOverwritten() == true) goto L7;
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
        this.cardViewHelper.setBackgroundOverwritten(true);
    L7:
        super.setBackgroundDrawable(r3);
    }

    public void setBackgroundInternal(Drawable r1) {
        super.setBackgroundDrawable(r1);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int r1) {
        this.cardViewHelper.setCardBackgroundColor(ColorStateList.valueOf(r1));
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList r1) {
        this.cardViewHelper.setCardBackgroundColor(r1);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float r1) {
        super.setCardElevation(r1);
        this.cardViewHelper.updateElevation();
    }

    public void setCardForegroundColor(ColorStateList r1) {
        this.cardViewHelper.setCardForegroundColor(r1);
    }

    public void setCheckable(boolean r1) {
        this.cardViewHelper.setCheckable(r1);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean r2) {
        if (this.checked == r2) goto L6;
        toggle();
        return;
    }

    public void setCheckedIcon(Drawable r1) {
        this.cardViewHelper.setCheckedIcon(r1);
    }

    public void setCheckedIconGravity(int r2) {
        if (this.cardViewHelper.getCheckedIconGravity() == r2) goto L6;
        this.cardViewHelper.setCheckedIconGravity(r2);
        return;
    }

    public void setCheckedIconMargin(int r1) {
        this.cardViewHelper.setCheckedIconMargin(r1);
    }

    public void setCheckedIconMarginResource(int r2) {
        if (r2 == (-1)) goto L6;
        this.cardViewHelper.setCheckedIconMargin(getResources().getDimensionPixelSize(r2));
        return;
    }

    public void setCheckedIconResource(int r2) {
        this.cardViewHelper.setCheckedIcon(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setCheckedIconSize(int r1) {
        this.cardViewHelper.setCheckedIconSize(r1);
    }

    public void setCheckedIconSizeResource(int r2) {
        if (r2 == 0) goto L5;
        this.cardViewHelper.setCheckedIconSize(getResources().getDimensionPixelSize(r2));
        return;
    }

    public void setCheckedIconTint(ColorStateList r1) {
        this.cardViewHelper.setCheckedIconTint(r1);
    }

    @Override // android.view.View
    public void setClickable(boolean r1) {
        super.setClickable(r1);
        MaterialCardViewHelper r0 = this.cardViewHelper;
        if (r0 == null) goto L6;
        r0.updateClickable();
        return;
    }

    @Override // androidx.cardview.widget.CardView
    public void setContentPadding(int r1, int r2, int r3, int r4) {
        this.cardViewHelper.setUserContentPadding(r1, r2, r3, r4);
    }

    public void setDragged(boolean r2) {
        if (this.dragged == r2) goto L6;
        this.dragged = r2;
        refreshDrawableState();
        forceRippleRedrawIfNeeded();
        invalidate();
        return;
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float r1) {
        super.setMaxCardElevation(r1);
        this.cardViewHelper.updateInsets();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener r1) {
        this.onCheckedChangeListener = r1;
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean r1) {
        super.setPreventCornerOverlap(r1);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    public void setProgress(float r1) {
        this.cardViewHelper.setProgress(r1);
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float r1) {
        super.setRadius(r1);
        this.cardViewHelper.setCornerRadius(r1);
    }

    public void setRippleColor(ColorStateList r1) {
        this.cardViewHelper.setRippleColor(r1);
    }

    public void setRippleColorResource(int r2) {
        this.cardViewHelper.setRippleColor(AppCompatResources.getColorStateList(getContext(), r2));
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel r2) {
        setClipToOutline(r2.isRoundRect(getBoundsAsRectF()));
        this.cardViewHelper.setShapeAppearance(r2);
    }

    public void setStrokeColor(int r1) {
        setStrokeColor(ColorStateList.valueOf(r1));
    }

    public void setStrokeColor(ColorStateList r2) {
        this.cardViewHelper.setStrokeColor(r2);
        invalidate();
    }

    public void setStrokeWidth(int r2) {
        this.cardViewHelper.setStrokeWidth(r2);
        invalidate();
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean r1) {
        super.setUseCompatPadding(r1);
        this.cardViewHelper.updateInsets();
        this.cardViewHelper.updateContentPadding();
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (isCheckable() == true) goto L5;
        return;
    L5:
        if (isEnabled() == false) goto L11;
        this.checked = !this.checked;
        refreshDrawableState();
        forceRippleRedrawIfNeeded();
        this.cardViewHelper.setChecked(this.checked, true);
        OnCheckedChangeListener r0 = this.onCheckedChangeListener;
        if (r0 == null) goto L12;
        r0.onCheckedChanged(this, this.checked);
        return;
    L12:
        return;
    }
}
