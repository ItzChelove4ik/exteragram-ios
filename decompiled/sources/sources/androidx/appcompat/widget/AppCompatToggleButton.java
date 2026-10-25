package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* loaded from: classes3.dex */
public class AppCompatToggleButton extends ToggleButton {
    private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatToggleButton(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.buttonStyleToggle);
    }

    public AppCompatToggleButton(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper r12 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = r12;
        r12.loadFromAttributes(r2, r3);
        AppCompatTextHelper r13 = new AppCompatTextHelper(this);
        this.mTextHelper = r13;
        r13.loadFromAttributes(r2, r3);
        getEmojiTextViewHelper().loadFromAttributes(r2, r3);
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.mAppCompatEmojiTextHelper != null) goto L6;
        this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
    L6:
        return this.mAppCompatEmojiTextHelper;
    }

    @Override // android.widget.ToggleButton, android.widget.CompoundButton, android.widget.TextView, android.view.View
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

    @Override // android.widget.TextView
    public void setAllCaps(boolean r1) {
        super.setAllCaps(r1);
        getEmojiTextViewHelper().setAllCaps(r1);
    }

    @Override // android.widget.ToggleButton, android.view.View
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
    public void setCompoundDrawables(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        super.setCompoundDrawables(r1, r2, r3, r4);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetCompoundDrawables();
        return;
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        super.setCompoundDrawablesRelative(r1, r2, r3, r4);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetCompoundDrawables();
        return;
    }

    public void setEmojiCompatEnabled(boolean r1) {
        getEmojiTextViewHelper().setEnabled(r1);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] r2) {
        super.setFilters(getEmojiTextViewHelper().getFilters(r2));
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
}
