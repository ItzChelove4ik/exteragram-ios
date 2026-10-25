package com.google.android.material.checkbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CompoundButton;
import androidx.appcompat.R$attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.widget.CompoundButtonCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes5.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    private static final int[][] CHECKBOX_STATES = null;
    private static final int DEF_STYLE_RES = 0;
    private static final int[] ERROR_STATE_SET = null;

    @SuppressLint({"DiscouragedApi"})
    private static final int FRAMEWORK_BUTTON_DRAWABLE_RES_ID = 0;
    private static final int[] INDETERMINATE_STATE_SET = null;
    public static final int STATE_CHECKED = 1;
    public static final int STATE_INDETERMINATE = 2;
    public static final int STATE_UNCHECKED = 0;
    private boolean broadcasting;
    private Drawable buttonDrawable;
    private Drawable buttonIconDrawable;
    ColorStateList buttonIconTintList;
    private PorterDuff.Mode buttonIconTintMode;
    ColorStateList buttonTintList;
    private boolean centerIfNoTextEnabled;
    private int checkedState;
    private int[] currentStateChecked;
    private CharSequence customStateDescription;
    private CharSequence errorAccessibilityLabel;
    private boolean errorShown;
    private ColorStateList materialThemeColorsTintList;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private final LinkedHashSet<OnCheckedStateChangedListener> onCheckedStateChangedListeners;
    private final LinkedHashSet<OnErrorChangedListener> onErrorChangedListeners;
    private final AnimatedVectorDrawableCompat transitionToUnchecked;
    private final Animatable2Compat$AnimationCallback transitionToUncheckedCallback;
    private boolean useMaterialThemeColors;
    private boolean usingMaterialButtonDrawable;

    /* renamed from: com.google.android.material.checkbox.MaterialCheckBox$1, reason: invalid class name */
    public class AnonymousClass1 extends Animatable2Compat$AnimationCallback {
        final /* synthetic */ MaterialCheckBox this$0;

        public AnonymousClass1(MaterialCheckBox r1) {
            this.this$0 = r1;
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
        public void onAnimationEnd(Drawable r1) {
            super.onAnimationEnd(r1);
            ColorStateList r0 = this.this$0.buttonTintList;
            if (r0 == null) goto L6;
            r1.setTintList(r0);
            return;
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback
        public void onAnimationStart(Drawable r3) {
            super.onAnimationStart(r3);
            MaterialCheckBox r0 = this.this$0;
            ColorStateList r1 = r0.buttonTintList;
            if (r1 == null) goto L6;
            r3.setTint(r1.getColorForState(MaterialCheckBox.access$000(r0), this.this$0.buttonTintList.getDefaultColor()));
            return;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CheckedState {
    }

    public interface OnCheckedStateChangedListener {
        void onCheckedStateChangedListener(MaterialCheckBox r1, int r2);
    }

    public interface OnErrorChangedListener {
        void onErrorChanged(MaterialCheckBox r1, boolean r2);
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        int checkedState;

        /* renamed from: com.google.android.material.checkbox.MaterialCheckBox$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r2) {
                return new SavedState(r2, null);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        private SavedState(Parcel r2) {
            super(r2);
            this.checkedState = ((Integer) r2.readValue(getClass().getClassLoader())).intValue();
        }

        public /* synthetic */ SavedState(Parcel r1, AnonymousClass1 r2) {
            this(r1);
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        private String getCheckedStateString() {
            int r1 = this.checkedState;
            if (r1 != 1) goto L5;
            return "checked";
        L5:
            if (r1 == 2) goto L8;
            return "unchecked";
        L8:
            return "indeterminate";
        }

        public String toString() {
            return "MaterialCheckBox.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " CheckedState=" + getCheckedStateString() + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeValue(Integer.valueOf(this.checkedState));
        }
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_CompoundButton_CheckBox;
        INDETERMINATE_STATE_SET = new int[]{R.attr.state_indeterminate};
        int r0 = R.attr.state_error;
        ERROR_STATE_SET = new int[]{r0};
        CHECKBOX_STATES = new int[][]{new int[]{android.R.attr.state_enabled, r0}, new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}, new int[]{android.R.attr.state_enabled, -16842912}, new int[]{-16842910, android.R.attr.state_checked}, new int[]{-16842910, -16842912}};
        FRAMEWORK_BUTTON_DRAWABLE_RES_ID = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");
    }

    public MaterialCheckBox(Context r2) {
        this(r2, null);
    }

    public MaterialCheckBox(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.checkboxStyle);
    }

    public MaterialCheckBox(Context r8, AttributeSet r9, int r10) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r8, r9, r10, r4), r9, r10);
        this.onErrorChangedListeners = new LinkedHashSet();
        this.onCheckedStateChangedListeners = new LinkedHashSet();
        this.transitionToUnchecked = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.mtrl_checkbox_button_checked_unchecked);
        this.transitionToUncheckedCallback = new AnonymousClass1(this);
        Context r0 = getContext();
        this.buttonDrawable = CompoundButtonCompat.getButtonDrawable(this);
        this.buttonTintList = getSuperButtonTintList();
        setSupportButtonTintList(null);
        TintTypedArray r92 = ThemeEnforcement.obtainTintedStyledAttributes(r0, r9, R.styleable.MaterialCheckBox, r10, r4, new int[0]);
        this.buttonIconDrawable = r92.getDrawable(R.styleable.MaterialCheckBox_buttonIcon);
        if (this.buttonDrawable != null) goto L5;
    L11:
        this.buttonIconTintList = MaterialResources.getColorStateList(r0, r92, R.styleable.MaterialCheckBox_buttonIconTint);
        this.buttonIconTintMode = ViewUtils.parseTintMode(r92.getInt(R.styleable.MaterialCheckBox_buttonIconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.useMaterialThemeColors = r92.getBoolean(R.styleable.MaterialCheckBox_useMaterialThemeColors, false);
        this.centerIfNoTextEnabled = r92.getBoolean(R.styleable.MaterialCheckBox_centerIfNoTextEnabled, true);
        this.errorShown = r92.getBoolean(R.styleable.MaterialCheckBox_errorShown, false);
        this.errorAccessibilityLabel = r92.getText(R.styleable.MaterialCheckBox_errorAccessibilityLabel);
        if (r92.hasValue(R.styleable.MaterialCheckBox_checkedState) == false) goto L15;
        setCheckedState(r92.getInt(R.styleable.MaterialCheckBox_checkedState, 0));
    L15:
        if (r92.hasValue(R.styleable.MaterialCheckBox_rippleColor) == false) goto L17;
        setRippleColor(MaterialResources.getColorStateList(r0, r92, R.styleable.MaterialCheckBox_rippleColor));
    L17:
        r92.recycle();
        refreshButtonDrawable();
        return;
    L5:
        if (ThemeEnforcement.isMaterial3Theme(r0) == false) goto L11;
        if (isButtonDrawableLegacy(r92) == false) goto L11;
        super.setButtonDrawable(null);
        this.buttonDrawable = AppCompatResources.getDrawable(r0, R.drawable.mtrl_checkbox_button);
        this.usingMaterialButtonDrawable = true;
        if (this.buttonIconDrawable != null) goto L11;
        this.buttonIconDrawable = AppCompatResources.getDrawable(r0, R.drawable.mtrl_checkbox_button_icon);
        goto L11
    }

    public static /* synthetic */ int[] access$000(MaterialCheckBox r0) {
        return r0.currentStateChecked;
    }

    private String getButtonStateDescription() {
        int r0 = this.checkedState;
        if (r0 == 1) goto L5;
        if (r0 != 0) goto L10;
        return getResources().getString(R.string.mtrl_checkbox_state_description_unchecked);
    L10:
        return getResources().getString(R.string.mtrl_checkbox_state_description_indeterminate);
    L5:
        return getResources().getString(R.string.mtrl_checkbox_state_description_checked);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.materialThemeColorsTintList != null) goto L6;
        int[][] r0 = CHECKBOX_STATES;
        int[] r1 = new int[r0.length];
        int r2 = MaterialColors.getColor(this, R$attr.colorControlActivated);
        int r3 = MaterialColors.getColor(this, R$attr.colorError);
        int r4 = MaterialColors.getColor(this, R.attr.colorSurface);
        int r5 = MaterialColors.getColor(this, R.attr.colorOnSurface);
        r1[0] = MaterialColors.layer(r4, r3, 1.0f);
        r1[1] = MaterialColors.layer(r4, r2, 1.0f);
        r1[2] = MaterialColors.layer(r4, r5, 0.54f);
        r1[3] = MaterialColors.layer(r4, r5, 0.38f);
        r1[4] = MaterialColors.layer(r4, r5, 0.38f);
        this.materialThemeColorsTintList = new ColorStateList(r0, r1);
    L6:
        return this.materialThemeColorsTintList;
    }

    private ColorStateList getSuperButtonTintList() {
        ColorStateList r0 = this.buttonTintList;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (super.getButtonTintList() == null) goto L10;
        return super.getButtonTintList();
    L10:
        return getSupportButtonTintList();
    }

    private boolean isButtonDrawableLegacy(TintTypedArray r3) {
        int r2 = r3.getResourceId(R.styleable.MaterialCheckBox_android_button, 0);
        int r32 = r3.getResourceId(R.styleable.MaterialCheckBox_buttonCompat, 0);
        if (r2 != FRAMEWORK_BUTTON_DRAWABLE_RES_ID) goto L7;
        if (r32 != 0) goto L7;
        return true;
    L7:
        return false;
    }

    private void refreshButtonDrawable() {
        this.buttonDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.buttonDrawable, this.buttonTintList, CompoundButtonCompat.getButtonTintMode(this));
        this.buttonIconDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.buttonIconDrawable, this.buttonIconTintList, this.buttonIconTintMode);
        setUpDefaultButtonDrawableAnimationIfNeeded();
        updateButtonTints();
        super.setButtonDrawable(DrawableUtils.compositeTwoLayeredDrawable(this.buttonDrawable, this.buttonIconDrawable));
        refreshDrawableState();
    }

    private void setDefaultStateDescription() {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        return;
    L5:
        if (this.customStateDescription != null) goto L9;
        super.setStateDescription(getButtonStateDescription());
        return;
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

    private void setUpDefaultButtonDrawableAnimationIfNeeded() {
        if (this.usingMaterialButtonDrawable == false) goto L16;
        AnimatedVectorDrawableCompat r0 = this.transitionToUnchecked;
        if (r0 == null) goto L8;
        r0.unregisterAnimationCallback(this.transitionToUncheckedCallback);
        this.transitionToUnchecked.registerAnimationCallback(this.transitionToUncheckedCallback);
    L8:
        Drawable r02 = this.buttonDrawable;
        if ((r02 instanceof AnimatedStateListDrawable) == false) goto L14;
        AnimatedVectorDrawableCompat r1 = this.transitionToUnchecked;
        if (r1 == null) goto L15;
        ((AnimatedStateListDrawable) r02).addTransition(R.id.checked, R.id.unchecked, r1, false);
        ((AnimatedStateListDrawable) this.buttonDrawable).addTransition(R.id.indeterminate, R.id.unchecked, this.transitionToUnchecked, false);
        return;
    L15:
        return;
    L14:
        return;
    }

    private void updateButtonTints() {
        Drawable r0 = this.buttonDrawable;
        if (r0 == null) goto L7;
        ColorStateList r1 = this.buttonTintList;
        if (r1 == null) goto L7;
        r0.setTintList(r1);
    L7:
        Drawable r02 = this.buttonIconDrawable;
        if (r02 == null) goto L13;
        ColorStateList r2 = this.buttonIconTintList;
        if (r2 == null) goto L14;
        r02.setTintList(r2);
        return;
    L14:
        return;
    }

    public void addOnCheckedStateChangedListener(OnCheckedStateChangedListener r1) {
        this.onCheckedStateChangedListeners.add(r1);
    }

    public void addOnErrorChangedListener(OnErrorChangedListener r1) {
        this.onErrorChangedListeners.add(r1);
    }

    public void clearOnCheckedStateChangedListeners() {
        this.onCheckedStateChangedListeners.clear();
    }

    public void clearOnErrorChangedListeners() {
        this.onErrorChangedListeners.clear();
    }

    @Override // android.widget.CompoundButton
    public Drawable getButtonDrawable() {
        return this.buttonDrawable;
    }

    public Drawable getButtonIconDrawable() {
        return this.buttonIconDrawable;
    }

    public ColorStateList getButtonIconTintList() {
        return this.buttonIconTintList;
    }

    public PorterDuff.Mode getButtonIconTintMode() {
        return this.buttonIconTintMode;
    }

    @Override // android.widget.CompoundButton
    public ColorStateList getButtonTintList() {
        return this.buttonTintList;
    }

    public int getCheckedState() {
        return this.checkedState;
    }

    public CharSequence getErrorAccessibilityLabel() {
        return this.errorAccessibilityLabel;
    }

    public boolean isCenterIfNoTextEnabled() {
        return this.centerIfNoTextEnabled;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public boolean isChecked() {
        if (this.checkedState != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isErrorShown() {
        return this.errorShown;
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
        if (this.buttonTintList == null) goto L7;
        return;
    L7:
        if (this.buttonIconTintList != null) goto L12;
        setUseMaterialThemeColors(true);
        return;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int r3) {
        int[] r32 = super.onCreateDrawableState(r3 + 2);
        if (getCheckedState() != 2) goto L6;
        View.mergeDrawableStates(r32, INDETERMINATE_STATE_SET);
    L6:
        if (isErrorShown() == false) goto L8;
        View.mergeDrawableStates(r32, ERROR_STATE_SET);
    L8:
        this.currentStateChecked = DrawableUtils.getCheckedState(r32);
        return r32;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas r6) {
        if (this.centerIfNoTextEnabled == true) goto L5;
    L16:
        super.onDraw(r6);
        return;
    L5:
        if (TextUtils.isEmpty(getText()) == false) goto L16;
        Drawable r0 = CompoundButtonCompat.getButtonDrawable(this);
        if (r0 == null) goto L16;
        if (ViewUtils.isLayoutRtl(this) == false) goto L11;
        int r1 = -1;
    L12:
        int r2 = ((getWidth() - r0.getIntrinsicWidth()) / 2) * r1;
        int r12 = r6.save();
        r6.translate(r2, 0.0f);
        super.onDraw(r6);
        r6.restoreToCount(r12);
        if (getBackground() == null) goto L18;
        Rect r62 = r0.getBounds();
        getBackground().setHotspotBounds(r62.left + r2, r62.top, r62.right + r2, r62.bottom);
        return;
    L18:
        return;
    L11:
        r1 = 1;
        goto L12
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        if (r3 != null) goto L6;
        return;
    L6:
        if (isErrorShown() == false) goto L9;
        r3.setText(r3.getText() + ", " + this.errorAccessibilityLabel);
        return;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        setCheckedState(r22.checkedState);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        r1.checkedState = getCheckedState();
        return r1;
    }

    public void removeOnCheckedStateChangedListener(OnCheckedStateChangedListener r1) {
        this.onCheckedStateChangedListeners.remove(r1);
    }

    public void removeOnErrorChangedListener(OnErrorChangedListener r1) {
        this.onErrorChangedListeners.remove(r1);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(int r2) {
        setButtonDrawable(AppCompatResources.getDrawable(getContext(), r2));
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(Drawable r1) {
        this.buttonDrawable = r1;
        this.usingMaterialButtonDrawable = false;
        refreshButtonDrawable();
    }

    public void setButtonIconDrawable(Drawable r1) {
        this.buttonIconDrawable = r1;
        refreshButtonDrawable();
    }

    public void setButtonIconDrawableResource(int r2) {
        setButtonIconDrawable(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setButtonIconTintList(ColorStateList r2) {
        if (this.buttonIconTintList != r2) goto L5;
        return;
    L5:
        this.buttonIconTintList = r2;
        refreshButtonDrawable();
    }

    public void setButtonIconTintMode(PorterDuff.Mode r2) {
        if (this.buttonIconTintMode != r2) goto L5;
        return;
    L5:
        this.buttonIconTintMode = r2;
        refreshButtonDrawable();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintList(ColorStateList r2) {
        if (this.buttonTintList != r2) goto L5;
        return;
    L5:
        this.buttonTintList = r2;
        refreshButtonDrawable();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintMode(PorterDuff.Mode r1) {
        setSupportButtonTintMode(r1);
        refreshButtonDrawable();
    }

    public void setCenterIfNoTextEnabled(boolean r1) {
        this.centerIfNoTextEnabled = r1;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean r1) {
        setCheckedState(r1 ? 1 : 0);
    }

    public void setCheckedState(int r4) {
        if (this.checkedState == r4) goto L30;
        this.checkedState = r4;
        if (r4 != 1) goto L7;
        boolean r42 = true;
    L8:
        super.setChecked(r42);
        refreshDrawableState();
        setDefaultStateDescription();
        if (this.broadcasting == true) goto L31;
        this.broadcasting = true;
        LinkedHashSet<OnCheckedStateChangedListener> r43 = this.onCheckedStateChangedListeners;
        if (r43 == null) goto L18;
        Iterator<OnCheckedStateChangedListener> r44 = r43.iterator();
    L15:
        if (r44.hasNext() == false) goto L18;
        r44.next().onCheckedStateChangedListener(this, this.checkedState);
    L18:
        if (this.checkedState == 2) goto L23;
        CompoundButton.OnCheckedChangeListener r45 = this.onCheckedChangeListener;
        if (r45 == null) goto L23;
        r45.onCheckedChanged(this, isChecked());
    L23:
        if (Build.VERSION.SDK_INT < 26) goto L27;
        AutofillManager r46 = MaterialCheckBox$$ExternalSyntheticApiModelOutline1.m(getContext().getSystemService(MaterialCheckBox$$ExternalSyntheticApiModelOutline0.m()));
        if (r46 == null) goto L27;
        r46.notifyValueChanged(this);
    L27:
        this.broadcasting = false;
        return;
    L31:
        return;
    L7:
        r42 = false;
        goto L8
    }

    public void setErrorAccessibilityLabel(CharSequence r1) {
        this.errorAccessibilityLabel = r1;
    }

    public void setErrorAccessibilityLabelResource(int r2) {
        if (r2 == 0) goto L4;
        CharSequence r22 = getResources().getText(r2);
    L5:
        setErrorAccessibilityLabel(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setErrorShown(boolean r3) {
        if (this.errorShown == r3) goto L9;
        this.errorShown = r3;
        refreshDrawableState();
        Iterator<OnErrorChangedListener> r32 = this.onErrorChangedListeners.iterator();
    L7:
        if (r32.hasNext() == false) goto L11;
        r32.next().onErrorChanged(this, this.errorShown);
        goto L7
    L11:
        return;
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener r1) {
        this.onCheckedChangeListener = r1;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public void setStateDescription(CharSequence r1) {
        this.customStateDescription = r1;
        if (r1 != null) goto L6;
        setDefaultStateDescription();
        return;
    L6:
        super.setStateDescription(r1);
    }

    public void setUseMaterialThemeColors(boolean r1) {
        this.useMaterialThemeColors = r1;
        if (r1 == false) goto L6;
        CompoundButtonCompat.setButtonTintList(this, getMaterialThemeColorsTintList());
        return;
    L6:
        CompoundButtonCompat.setButtonTintList(this, null);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }
}
