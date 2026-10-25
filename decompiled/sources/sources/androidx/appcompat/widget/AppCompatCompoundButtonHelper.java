package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.CompoundButtonCompat;

/* loaded from: classes3.dex */
class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList;
    private PorterDuff.Mode mButtonTintMode;
    private boolean mHasButtonTint;
    private boolean mHasButtonTintMode;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    public AppCompatCompoundButtonHelper(CompoundButton r2) {
        this.mButtonTintList = null;
        this.mButtonTintMode = null;
        this.mHasButtonTint = false;
        this.mHasButtonTintMode = false;
        this.mView = r2;
    }

    public void applyButtonTint() {
        Drawable r0 = CompoundButtonCompat.getButtonDrawable(this.mView);
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mHasButtonTint == false) goto L7;
    L8:
        Drawable r02 = DrawableCompat.wrap(r0).mutate();
        if (this.mHasButtonTint == false) goto L12;
        DrawableCompat.setTintList(r02, this.mButtonTintList);
    L12:
        if (this.mHasButtonTintMode == false) goto L15;
        DrawableCompat.setTintMode(r02, this.mButtonTintMode);
    L15:
        if (r02.isStateful() == false) goto L17;
        r02.setState(this.mView.getDrawableState());
    L17:
        this.mView.setButtonDrawable(r02);
        return;
    L7:
        if (this.mHasButtonTintMode == true) goto L8;
    }

    public ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    public void loadFromAttributes(AttributeSet r11, int r12) {
        TintTypedArray r1 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), r11, R$styleable.CompoundButton, r12, 0);
        CompoundButton r3 = this.mView;
        ViewCompat.saveAttributeDataForStyleable(r3, r3.getContext(), R$styleable.CompoundButton, r11, r1.getWrappedTypeArray(), r12, 0);
    L9:
        th = move-exception;
        r1.recycle();
        throw th;
    L4:
        if (r1.hasValue(R$styleable.CompoundButton_buttonCompat) == false) goto L12;
        int r112 = r1.getResourceId(R$styleable.CompoundButton_buttonCompat, 0);     // Catch: Throwable -> L9
        if (r112 == 0) goto L12;
        CompoundButton r122 = this.mView;     // Catch: Throwable -> L9 Resources.NotFoundException -> L26
        r122.setButtonDrawable(AppCompatResources.getDrawable(r122.getContext(), r112));     // Catch: Throwable -> L9 Resources.NotFoundException -> L26
    L17:
        if (r1.hasValue(R$styleable.CompoundButton_buttonTint) == false) goto L20;
        CompoundButtonCompat.setButtonTintList(this.mView, r1.getColorStateList(R$styleable.CompoundButton_buttonTint));     // Catch: Throwable -> L9
    L20:
        if (r1.hasValue(R$styleable.CompoundButton_buttonTintMode) == false) goto L22;
        CompoundButtonCompat.setButtonTintMode(this.mView, DrawableUtils.parseTintMode(r1.getInt(R$styleable.CompoundButton_buttonTintMode, -1), null));     // Catch: Throwable -> L9
    L22:
        r1.recycle();
        return;
    L12:
        if (r1.hasValue(R$styleable.CompoundButton_android_button) == false) goto L17;
        int r113 = r1.getResourceId(R$styleable.CompoundButton_android_button, 0);     // Catch: Throwable -> L9
        if (r113 == 0) goto L17;
        CompoundButton r123 = this.mView;     // Catch: Throwable -> L9
        r123.setButtonDrawable(AppCompatResources.getDrawable(r123.getContext(), r113));     // Catch: Throwable -> L9
        goto L17
    }

    public void onSetButtonDrawable() {
        if (this.mSkipNextApply == false) goto L6;
        this.mSkipNextApply = false;
        return;
    L6:
        this.mSkipNextApply = true;
        applyButtonTint();
    }

    public void setSupportButtonTintList(ColorStateList r1) {
        this.mButtonTintList = r1;
        this.mHasButtonTint = true;
        applyButtonTint();
    }

    public void setSupportButtonTintMode(PorterDuff.Mode r1) {
        this.mButtonTintMode = r1;
        this.mHasButtonTintMode = true;
        applyButtonTint();
    }
}
