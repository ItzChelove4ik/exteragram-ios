package com.google.android.material.textfield;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes5.dex */
class StartCompoundLayout extends LinearLayout {
    private boolean hintExpanded;
    private CharSequence prefixText;
    private final TextView prefixTextView;
    private int startIconMinSize;
    private View.OnLongClickListener startIconOnLongClickListener;
    private ImageView.ScaleType startIconScaleType;
    private ColorStateList startIconTintList;
    private PorterDuff.Mode startIconTintMode;
    private final CheckableImageButton startIconView;
    private final TextInputLayout textInputLayout;

    /* renamed from: $r8$lambda$m6fvy4iYBWdE6Sx2PzcPVwZ-0IU, reason: not valid java name */
    public static /* synthetic */ void m1908$r8$lambda$m6fvy4iYBWdE6Sx2PzcPVwZ0IU(StartCompoundLayout r0, View r1, boolean r2) {
        IconHelper.updateIconTooltip(r0.startIconView, r0.startIconOnLongClickListener, r0.getStartIconContentDescription());
    }

    public StartCompoundLayout(TextInputLayout r5, TintTypedArray r6) {
        super(r5.getContext());
        this.textInputLayout = r5;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES));
        CheckableImageButton r52 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_start_icon, this, false);
        this.startIconView = r52;
        IconHelper.setCompatRippleBackgroundIfNeeded(r52);
        AppCompatTextView r0 = new AppCompatTextView(getContext());
        this.prefixTextView = r0;
        initStartIconView(r6);
        initPrefixTextView(r6);
        addView(r52);
        addView(r0);
        r52.setOnFocusableChangedListener(new StartCompoundLayout$$ExternalSyntheticLambda0(this));
    }

    private void initPrefixTextView(TintTypedArray r4) {
        this.prefixTextView.setVisibility(8);
        this.prefixTextView.setId(R.id.textinput_prefix_text);
        this.prefixTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.prefixTextView.setAccessibilityLiveRegion(1);
        setPrefixTextAppearance(r4.getResourceId(R.styleable.TextInputLayout_prefixTextAppearance, 0));
        if (r4.hasValue(R.styleable.TextInputLayout_prefixTextColor) == false) goto L5;
        setPrefixTextColor(r4.getColorStateList(R.styleable.TextInputLayout_prefixTextColor));
    L5:
        setPrefixText(r4.getText(R.styleable.TextInputLayout_prefixText));
    }

    private void initStartIconView(TintTypedArray r5) {
        if (MaterialResources.isFontScaleAtLeast1_3(getContext()) == false) goto L5;
        ((ViewGroup.MarginLayoutParams) this.startIconView.getLayoutParams()).setMarginEnd(0);
    L5:
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        if (r5.hasValue(R.styleable.TextInputLayout_startIconTint) == false) goto L9;
        this.startIconTintList = MaterialResources.getColorStateList(getContext(), r5, R.styleable.TextInputLayout_startIconTint);
    L9:
        if (r5.hasValue(R.styleable.TextInputLayout_startIconTintMode) == false) goto L12;
        this.startIconTintMode = ViewUtils.parseTintMode(r5.getInt(R.styleable.TextInputLayout_startIconTintMode, -1), null);
    L12:
        if (r5.hasValue(R.styleable.TextInputLayout_startIconDrawable) == false) goto L17;
        setStartIconDrawable(r5.getDrawable(R.styleable.TextInputLayout_startIconDrawable));
        if (r5.hasValue(R.styleable.TextInputLayout_startIconContentDescription) == false) goto L16;
        setStartIconContentDescription(r5.getText(R.styleable.TextInputLayout_startIconContentDescription));
    L16:
        setStartIconCheckable(r5.getBoolean(R.styleable.TextInputLayout_startIconCheckable, true));
    L17:
        setStartIconMinSize(r5.getDimensionPixelSize(R.styleable.TextInputLayout_startIconMinSize, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size)));
        if (r5.hasValue(R.styleable.TextInputLayout_startIconScaleType) == false) goto L21;
        setStartIconScaleType(IconHelper.convertScaleType(r5.getInt(R.styleable.TextInputLayout_startIconScaleType, -1)));
        return;
    }

    private void updateVisibility() {
        int r1 = 8;
        if (this.prefixText != null) goto L5;
    L7:
        int r0 = 8;
    L9:
        if (this.startIconView.getVisibility() == 0) goto L11;
        if (r0 == 0) goto L11;
    L12:
        setVisibility(r1);
        this.prefixTextView.setVisibility(r0);
        this.textInputLayout.updateDummyDrawables();
        return;
    L11:
        r1 = 0;
        goto L12
    L5:
        if (this.hintExpanded == true) goto L7;
        r0 = 0;
        goto L9
    }

    public CharSequence getPrefixText() {
        return this.prefixText;
    }

    public ColorStateList getPrefixTextColor() {
        return this.prefixTextView.getTextColors();
    }

    public int getPrefixTextStartOffset() {
        if (isStartIconVisible() == false) goto L5;
        int r0 = this.startIconView.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) this.startIconView.getLayoutParams()).getMarginEnd();
    L7:
        return (getPaddingStart() + this.prefixTextView.getPaddingStart()) + r0;
    L5:
        r0 = 0;
        goto L7
    }

    public TextView getPrefixTextView() {
        return this.prefixTextView;
    }

    public CharSequence getStartIconContentDescription() {
        return this.startIconView.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.startIconView.getDrawable();
    }

    public int getStartIconMinSize() {
        return this.startIconMinSize;
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.startIconScaleType;
    }

    public boolean isStartIconCheckable() {
        return this.startIconView.isCheckable();
    }

    public boolean isStartIconVisible() {
        if (this.startIconView.getVisibility() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void onHintStateChanged(boolean r1) {
        this.hintExpanded = r1;
        updateVisibility();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        updatePrefixTextViewPadding();
    }

    public void refreshStartIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.startIconView, this.startIconTintList);
    }

    public void setPrefixText(CharSequence r2) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        CharSequence r0 = null;
    L6:
        this.prefixText = r0;
        this.prefixTextView.setText(r2);
        updateVisibility();
        return;
    L5:
        r0 = r2;
        goto L6
    }

    public void setPrefixTextAppearance(int r1) {
        TextViewCompat.setTextAppearance(this.prefixTextView, r1);
    }

    public void setPrefixTextColor(ColorStateList r1) {
        this.prefixTextView.setTextColor(r1);
    }

    public void setStartIconCheckable(boolean r1) {
        this.startIconView.setCheckable(r1);
    }

    public void setStartIconContentDescription(CharSequence r2) {
        if (getStartIconContentDescription() == r2) goto L6;
        this.startIconView.setContentDescription(r2);
        IconHelper.updateIconTooltip(this.startIconView, this.startIconOnLongClickListener, r2);
        return;
    }

    public void setStartIconDrawable(Drawable r4) {
        this.startIconView.setImageDrawable(r4);
        if (r4 == null) goto L6;
        IconHelper.applyIconTint(this.textInputLayout, this.startIconView, this.startIconTintList, this.startIconTintMode);
        setStartIconVisible(true);
        refreshStartIconDrawableState();
        return;
    L6:
        setStartIconVisible(false);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        setStartIconContentDescription(null);
    }

    public void setStartIconMinSize(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("startIconSize cannot be less than 0");
        return;
    L4:
        if (r2 == this.startIconMinSize) goto L9;
        this.startIconMinSize = r2;
        IconHelper.setIconMinSize(this.startIconView, r2);
        return;
    }

    public void setStartIconOnClickListener(View.OnClickListener r2) {
        IconHelper.setIconOnClickListener(this.startIconView, r2, this.startIconOnLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener r1) {
        this.startIconOnLongClickListener = r1;
        IconHelper.setIconOnLongClickListener(this.startIconView, r1);
    }

    public void setStartIconScaleType(ImageView.ScaleType r1) {
        this.startIconScaleType = r1;
        IconHelper.setIconScaleType(this.startIconView, r1);
    }

    public void setStartIconTintList(ColorStateList r3) {
        if (this.startIconTintList == r3) goto L6;
        this.startIconTintList = r3;
        IconHelper.applyIconTint(this.textInputLayout, this.startIconView, r3, this.startIconTintMode);
        return;
    }

    public void setStartIconTintMode(PorterDuff.Mode r3) {
        if (this.startIconTintMode == r3) goto L6;
        this.startIconTintMode = r3;
        IconHelper.applyIconTint(this.textInputLayout, this.startIconView, this.startIconTintList, r3);
        return;
    }

    public void setStartIconVisible(boolean r2) {
        if (isStartIconVisible() == r2) goto L16;
        if (r2 == false) goto L6;
    L10:
        CheckableImageButton r0 = this.startIconView;
        if (r2 == false) goto L13;
        int r22 = 0;
    L14:
        r0.setVisibility(r22);
        updatePrefixTextViewPadding();
        updateVisibility();
        return;
    L13:
        r22 = 8;
        goto L14
    L6:
        if (this.startIconView.hasFocus() == false) goto L10;
        EditText r02 = this.textInputLayout.getEditText();
        if (r02 == null) goto L10;
        r02.requestFocus();
        goto L10
    }

    public void setupAccessibilityNodeInfo(AccessibilityNodeInfoCompat r2) {
        if (this.prefixTextView.getVisibility() != 0) goto L6;
        r2.setLabelFor(this.prefixTextView);
        r2.setTraversalAfter(this.prefixTextView);
        return;
    L6:
        r2.setTraversalAfter(this.startIconView);
    }

    public void updatePrefixTextViewPadding() {
        EditText r0 = this.textInputLayout.editText;
        if (r0 != null) goto L6;
        return;
    L6:
        if (isStartIconVisible() == false) goto L8;
        int r1 = 0;
    L9:
        this.prefixTextView.setPaddingRelative(r1, r0.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), r0.getCompoundPaddingBottom());
        return;
    L8:
        r1 = r0.getPaddingStart();
        goto L9
    }
}
