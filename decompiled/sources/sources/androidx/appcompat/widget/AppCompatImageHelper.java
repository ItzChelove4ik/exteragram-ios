package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;

/* loaded from: classes3.dex */
public class AppCompatImageHelper {
    private TintInfo mImageTint;
    private TintInfo mInternalImageTint;
    private int mLevel;
    private TintInfo mTmpInfo;
    private final ImageView mView;

    public AppCompatImageHelper(ImageView r2) {
        this.mLevel = 0;
        this.mView = r2;
    }

    private boolean applyFrameworkTintUsingColorFilter(Drawable r4) {
        if (this.mTmpInfo != null) goto L5;
        this.mTmpInfo = new TintInfo();
    L5:
        TintInfo r0 = this.mTmpInfo;
        r0.clear();
        ColorStateList r1 = ImageViewCompat.getImageTintList(this.mView);
        if (r1 == null) goto L8;
        r0.mHasTintList = true;
        r0.mTintList = r1;
    L8:
        PorterDuff.Mode r12 = ImageViewCompat.getImageTintMode(this.mView);
        if (r12 == null) goto L12;
        r0.mHasTintMode = true;
        r0.mTintMode = r12;
    L12:
        if (r0.mHasTintList == false) goto L14;
    L18:
        AppCompatDrawableManager.tintDrawable(r4, r0, this.mView.getDrawableState());
        return true;
    L14:
        if (r0.mHasTintMode == true) goto L18;
        return false;
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        if (this.mInternalImageTint == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void applyImageLevel() {
        if (this.mView.getDrawable() == null) goto L6;
        this.mView.getDrawable().setLevel(this.mLevel);
        return;
    }

    public void applySupportImageTint() {
        Drawable r0 = this.mView.getDrawable();
        if (r0 == null) goto L5;
        DrawableUtils.fixDrawable(r0);
    L5:
        if (r0 != null) goto L7;
        return;
    L7:
        if (shouldApplyFrameworkTintUsingColorFilter() == true) goto L9;
    L11:
        TintInfo r1 = this.mImageTint;
        if (r1 == null) goto L15;
        AppCompatDrawableManager.tintDrawable(r0, r1, this.mView.getDrawableState());
        return;
    L15:
        TintInfo r12 = this.mInternalImageTint;
        if (r12 == null) goto L20;
        AppCompatDrawableManager.tintDrawable(r0, r12, this.mView.getDrawableState());
        return;
    L20:
        return;
    L9:
        if (applyFrameworkTintUsingColorFilter(r0) == false) goto L11;
    }

    public ColorStateList getSupportImageTintList() {
        TintInfo r0 = this.mImageTint;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.mTintList;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        TintInfo r0 = this.mImageTint;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.mTintMode;
    }

    public boolean hasOverlappingRendering() {
        if ((this.mView.getBackground() instanceof RippleDrawable) == false) goto L6;
        return false;
    L6:
        return true;
    }

    public void loadFromAttributes(AttributeSet r10, int r11) {
        TintTypedArray r1 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), r10, R$styleable.AppCompatImageView, r11, 0);
        ImageView r2 = this.mView;
        ViewCompat.saveAttributeDataForStyleable(r2, r2.getContext(), R$styleable.AppCompatImageView, r10, r1.getWrappedTypeArray(), r11, 0);
        Drawable r102 = this.mView.getDrawable();     // Catch: Throwable -> L10
        if (r102 != null) goto L12;
        int r0 = r1.getResourceId(R$styleable.AppCompatImageView_srcCompat, -1);     // Catch: Throwable -> L10
        if (r0 == (-1)) goto L12;
        r102 = AppCompatResources.getDrawable(this.mView.getContext(), r0);     // Catch: Throwable -> L10
        if (r102 == null) goto L12;
        this.mView.setImageDrawable(r102);     // Catch: Throwable -> L10
    L12:
        if (r102 == null) goto L15;
        DrawableUtils.fixDrawable(r102);     // Catch: Throwable -> L10
    L15:
        if (r1.hasValue(R$styleable.AppCompatImageView_tint) == false) goto L18;
        ImageViewCompat.setImageTintList(this.mView, r1.getColorStateList(R$styleable.AppCompatImageView_tint));     // Catch: Throwable -> L10
    L18:
        if (r1.hasValue(R$styleable.AppCompatImageView_tintMode) == false) goto L20;
        ImageViewCompat.setImageTintMode(this.mView, DrawableUtils.parseTintMode(r1.getInt(R$styleable.AppCompatImageView_tintMode, -1), null));     // Catch: Throwable -> L10
    L20:
        r1.recycle();
        return;
    L10:
        th = move-exception;
        r1.recycle();
        throw th;
    }

    public void obtainLevelFromDrawable(Drawable r1) {
        this.mLevel = r1.getLevel();
    }

    public void setImageResource(int r2) {
        ImageView r0 = this.mView;
        if (r2 == 0) goto L8;
        Drawable r22 = AppCompatResources.getDrawable(r0.getContext(), r2);
        if (r22 == null) goto L7;
        DrawableUtils.fixDrawable(r22);
    L7:
        this.mView.setImageDrawable(r22);
    L9:
        applySupportImageTint();
        return;
    L8:
        r0.setImageDrawable(null);
        goto L9
    }

    public void setSupportImageTintList(ColorStateList r2) {
        if (this.mImageTint != null) goto L5;
        this.mImageTint = new TintInfo();
    L5:
        TintInfo r0 = this.mImageTint;
        r0.mTintList = r2;
        r0.mHasTintList = true;
        applySupportImageTint();
    }

    public void setSupportImageTintMode(PorterDuff.Mode r2) {
        if (this.mImageTint != null) goto L5;
        this.mImageTint = new TintInfo();
    L5:
        TintInfo r0 = this.mImageTint;
        r0.mTintMode = r2;
        r0.mHasTintMode = true;
        applySupportImageTint();
    }
}
