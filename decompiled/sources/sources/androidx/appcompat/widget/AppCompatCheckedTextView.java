package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.widget.TextViewCompat;

/* loaded from: classes3.dex */
public class AppCompatCheckedTextView extends CheckedTextView {
    private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatCheckedTextViewHelper mCheckedHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatCheckedTextView(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.checkedTextViewStyle);
    }

    public AppCompatCheckedTextView(Context r1, AttributeSet r2, int r3) {
        super(TintContextWrapper.wrap(r1), r2, r3);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatTextHelper r12 = new AppCompatTextHelper(this);
        this.mTextHelper = r12;
        r12.loadFromAttributes(r2, r3);
        r12.applyCompoundDrawablesTints();
        AppCompatBackgroundHelper r13 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = r13;
        r13.loadFromAttributes(r2, r3);
        AppCompatCheckedTextViewHelper r14 = new AppCompatCheckedTextViewHelper(this);
        this.mCheckedHelper = r14;
        r14.loadFromAttributes(r2, r3);
        getEmojiTextViewHelper().loadFromAttributes(r2, r3);
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.mAppCompatEmojiTextHelper != null) goto L6;
        this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
    L6:
        return this.mAppCompatEmojiTextHelper;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L5;
        r0.applyCompoundDrawablesTints();
    L5:
        AppCompatBackgroundHelper r02 = this.mBackgroundTintHelper;
        if (r02 == null) goto L8;
        r02.applySupportBackgroundTint();
    L8:
        AppCompatCheckedTextViewHelper r1 = this.mCheckedHelper;
        if (r1 == null) goto L12;
        r1.applyCheckMarkTint();
        return;
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

    public ColorStateList getSupportCheckMarkTintList() {
        AppCompatCheckedTextViewHelper r0 = this.mCheckedHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportCheckMarkTintList();
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        AppCompatCheckedTextViewHelper r0 = this.mCheckedHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportCheckMarkTintMode();
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.getCompoundDrawableTintList();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.getCompoundDrawableTintMode();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo r2) {
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(r2), r2, this);
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean r1) {
        super.setAllCaps(r1);
        getEmojiTextViewHelper().setAllCaps(r1);
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

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int r2) {
        setCheckMarkDrawable(AppCompatResources.getDrawable(getContext(), r2));
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable r1) {
        super.setCheckMarkDrawable(r1);
        AppCompatCheckedTextViewHelper r0 = this.mCheckedHelper;
        if (r0 == null) goto L6;
        r0.onSetCheckMarkDrawable();
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

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback r1) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, r1));
    }

    public void setEmojiCompatEnabled(boolean r1) {
        getEmojiTextViewHelper().setEnabled(r1);
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

    public void setSupportCheckMarkTintList(ColorStateList r1) {
        AppCompatCheckedTextViewHelper r0 = this.mCheckedHelper;
        if (r0 == null) goto L6;
        r0.setSupportCheckMarkTintList(r1);
        return;
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode r1) {
        AppCompatCheckedTextViewHelper r0 = this.mCheckedHelper;
        if (r0 == null) goto L6;
        r0.setSupportCheckMarkTintMode(r1);
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
}
