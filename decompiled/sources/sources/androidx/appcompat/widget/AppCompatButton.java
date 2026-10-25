package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R$attr;
import androidx.core.widget.TextViewCompat;

/* loaded from: classes3.dex */
public class AppCompatButton extends Button {
    private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatButton(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.buttonStyle);
    }

    public AppCompatButton(Context r1, AttributeSet r2, int r3) {
        super(TintContextWrapper.wrap(r1), r2, r3);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper r12 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = r12;
        r12.loadFromAttributes(r2, r3);
        AppCompatTextHelper r13 = new AppCompatTextHelper(this);
        this.mTextHelper = r13;
        r13.loadFromAttributes(r2, r3);
        r13.applyCompoundDrawablesTints();
        getEmojiTextViewHelper().loadFromAttributes(r2, r3);
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.mAppCompatEmojiTextHelper != null) goto L6;
        this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
    L6:
        return this.mAppCompatEmojiTextHelper;
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L5;
        r0.applySupportBackgroundTint();
    L5:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L9;
        r1.applyCompoundDrawablesTints();
        return;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 != null) goto L9;
        return -1;
    L9:
        return r1.getAutoSizeMaxTextSize();
    L5:
        return super.getAutoSizeMaxTextSize();
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 != null) goto L9;
        return -1;
    L9:
        return r1.getAutoSizeMinTextSize();
    L5:
        return super.getAutoSizeMinTextSize();
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 != null) goto L9;
        return -1;
    L9:
        return r1.getAutoSizeStepGranularity();
    L5:
        return super.getAutoSizeStepGranularity();
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L11;
        return r1.getAutoSizeTextAvailableSizes();
    L11:
        return new int[0];
    L5:
        return super.getAutoSizeTextAvailableSizes();
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r2 = this.mTextHelper;
        if (r2 != null) goto L11;
        return 0;
    L11:
        return r2.getAutoSizeTextType();
    L5:
        if (super.getAutoSizeTextType() != 1) goto L7;
        return 1;
    L7:
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportBackgroundTintMode();
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.getCompoundDrawableTintList();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.getCompoundDrawableTintMode();
    }

    public boolean isEmojiCompatEnabled() {
        return getEmojiTextViewHelper().isEnabled();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent r1) {
        super.onInitializeAccessibilityEvent(r1);
        r1.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        r1.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onLayout(r1, r2, r3, r4, r5);
        return;
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        super.onTextChanged(r1, r2, r3, r4);
        AppCompatTextHelper r12 = this.mTextHelper;
        if (r12 != null) goto L5;
        return;
    L5:
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L7;
        return;
    L7:
        if (r12.isAutoSizeEnabled() == false) goto L12;
        this.mTextHelper.autoSizeText();
        return;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean r1) {
        super.setAllCaps(r1);
        getEmojiTextViewHelper().setAllCaps(r1);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int r2, int r3, int r4, int r5) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L6;
        super.setAutoSizeTextTypeUniformWithConfiguration(r2, r3, r4, r5);
        return;
    L6:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L10;
        r1.setAutoSizeTextTypeUniformWithConfiguration(r2, r3, r4, r5);
        return;
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] r2, int r3) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L6;
        super.setAutoSizeTextTypeUniformWithPresetSizes(r2, r3);
        return;
    L6:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L10;
        r1.setAutoSizeTextTypeUniformWithPresetSizes(r2, r3);
        return;
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int r2) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L6;
        super.setAutoSizeTextTypeWithDefaults(r2);
        return;
    L6:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L10;
        r1.setAutoSizeTextTypeWithDefaults(r2);
        return;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable r1) {
        super.setBackgroundDrawable(r1);
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.onSetBackgroundDrawable(r1);
        return;
    }

    @Override // android.view.View
    public void setBackgroundResource(int r1) {
        super.setBackgroundResource(r1);
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.onSetBackgroundResource(r1);
        return;
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback r1) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, r1));
    }

    public void setEmojiCompatEnabled(boolean r1) {
        getEmojiTextViewHelper().setEnabled(r1);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] r2) {
        super.setFilters(getEmojiTextViewHelper().getFilters(r2));
    }

    public void setSupportAllCaps(boolean r1) {
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.setAllCaps(r1);
        return;
    }

    public void setSupportBackgroundTintList(ColorStateList r1) {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.setSupportBackgroundTintList(r1);
        return;
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode r1) {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.setSupportBackgroundTintMode(r1);
        return;
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList r2) {
        this.mTextHelper.setCompoundDrawableTintList(r2);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode r2) {
        this.mTextHelper.setCompoundDrawableTintMode(r2);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context r1, int r2) {
        super.setTextAppearance(r1, r2);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetTextAppearance(r1, r2);
        return;
    }

    @Override // android.widget.TextView
    public void setTextSize(int r2, float r3) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L6;
        super.setTextSize(r2, r3);
        return;
    L6:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L10;
        r1.setTextSize(r2, r3);
        return;
    }
}
