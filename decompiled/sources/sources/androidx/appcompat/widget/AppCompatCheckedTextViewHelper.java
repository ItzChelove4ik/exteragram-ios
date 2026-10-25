package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.CheckedTextViewCompat;

/* loaded from: classes3.dex */
class AppCompatCheckedTextViewHelper {
    private ColorStateList mCheckMarkTintList;
    private PorterDuff.Mode mCheckMarkTintMode;
    private boolean mHasCheckMarkTint;
    private boolean mHasCheckMarkTintMode;
    private boolean mSkipNextApply;
    private final CheckedTextView mView;

    public AppCompatCheckedTextViewHelper(CheckedTextView r2) {
        this.mCheckMarkTintList = null;
        this.mCheckMarkTintMode = null;
        this.mHasCheckMarkTint = false;
        this.mHasCheckMarkTintMode = false;
        this.mView = r2;
    }

    public void applyCheckMarkTint() {
        Drawable r0 = CheckedTextViewCompat.getCheckMarkDrawable(this.mView);
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mHasCheckMarkTint == false) goto L7;
    L8:
        Drawable r02 = DrawableCompat.wrap(r0).mutate();
        if (this.mHasCheckMarkTint == false) goto L12;
        DrawableCompat.setTintList(r02, this.mCheckMarkTintList);
    L12:
        if (this.mHasCheckMarkTintMode == false) goto L15;
        DrawableCompat.setTintMode(r02, this.mCheckMarkTintMode);
    L15:
        if (r02.isStateful() == false) goto L17;
        r02.setState(this.mView.getDrawableState());
    L17:
        this.mView.setCheckMarkDrawable(r02);
        return;
    L7:
        if (this.mHasCheckMarkTintMode == true) goto L8;
    }

    public ColorStateList getSupportCheckMarkTintList() {
        return this.mCheckMarkTintList;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        return this.mCheckMarkTintMode;
    }

    public void loadFromAttributes(AttributeSet r11, int r12) {
        TintTypedArray r1 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), r11, R$styleable.CheckedTextView, r12, 0);
        CheckedTextView r3 = this.mView;
        ViewCompat.saveAttributeDataForStyleable(r3, r3.getContext(), R$styleable.CheckedTextView, r11, r1.getWrappedTypeArray(), r12, 0);
    L9:
        th = move-exception;
        r1.recycle();
        throw th;
    L4:
        if (r1.hasValue(R$styleable.CheckedTextView_checkMarkCompat) == false) goto L12;
        int r112 = r1.getResourceId(R$styleable.CheckedTextView_checkMarkCompat, 0);     // Catch: Throwable -> L9
        if (r112 == 0) goto L12;
        CheckedTextView r122 = this.mView;     // Catch: Throwable -> L9 Resources.NotFoundException -> L26
        r122.setCheckMarkDrawable(AppCompatResources.getDrawable(r122.getContext(), r112));     // Catch: Throwable -> L9 Resources.NotFoundException -> L26
    L17:
        if (r1.hasValue(R$styleable.CheckedTextView_checkMarkTint) == false) goto L20;
        CheckedTextViewCompat.setCheckMarkTintList(this.mView, r1.getColorStateList(R$styleable.CheckedTextView_checkMarkTint));     // Catch: Throwable -> L9
    L20:
        if (r1.hasValue(R$styleable.CheckedTextView_checkMarkTintMode) == false) goto L22;
        CheckedTextViewCompat.setCheckMarkTintMode(this.mView, DrawableUtils.parseTintMode(r1.getInt(R$styleable.CheckedTextView_checkMarkTintMode, -1), null));     // Catch: Throwable -> L9
    L22:
        r1.recycle();
        return;
    L12:
        if (r1.hasValue(R$styleable.CheckedTextView_android_checkMark) == false) goto L17;
        int r113 = r1.getResourceId(R$styleable.CheckedTextView_android_checkMark, 0);     // Catch: Throwable -> L9
        if (r113 == 0) goto L17;
        CheckedTextView r123 = this.mView;     // Catch: Throwable -> L9
        r123.setCheckMarkDrawable(AppCompatResources.getDrawable(r123.getContext(), r113));     // Catch: Throwable -> L9
        goto L17
    }

    public void onSetCheckMarkDrawable() {
        if (this.mSkipNextApply == false) goto L6;
        this.mSkipNextApply = false;
        return;
    L6:
        this.mSkipNextApply = true;
        applyCheckMarkTint();
    }

    public void setSupportCheckMarkTintList(ColorStateList r1) {
        this.mCheckMarkTintList = r1;
        this.mHasCheckMarkTint = true;
        applyCheckMarkTint();
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode r1) {
        this.mCheckMarkTintMode = r1;
        this.mHasCheckMarkTintMode = true;
        applyCheckMarkTint();
    }
}
