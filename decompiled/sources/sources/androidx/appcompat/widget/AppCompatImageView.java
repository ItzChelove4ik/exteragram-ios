package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes3.dex */
public class AppCompatImageView extends ImageView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private boolean mHasLevel;
    private final AppCompatImageHelper mImageHelper;

    public AppCompatImageView(Context r2) {
        this(r2, null);
    }

    public AppCompatImageView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public AppCompatImageView(Context r1, AttributeSet r2, int r3) {
        super(TintContextWrapper.wrap(r1), r2, r3);
        this.mHasLevel = false;
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper r12 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = r12;
        r12.loadFromAttributes(r2, r3);
        AppCompatImageHelper r13 = new AppCompatImageHelper(this);
        this.mImageHelper = r13;
        r13.loadFromAttributes(r2, r3);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L5;
        r0.applySupportBackgroundTint();
    L5:
        AppCompatImageHelper r1 = this.mImageHelper;
        if (r1 == null) goto L9;
        r1.applySupportImageTint();
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

    public ColorStateList getSupportImageTintList() {
        AppCompatImageHelper r0 = this.mImageHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportImageTintList();
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        AppCompatImageHelper r0 = this.mImageHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportImageTintMode();
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        if (this.mImageHelper.hasOverlappingRendering() == true) goto L5;
        return false;
    L5:
        if (super.hasOverlappingRendering() == false) goto L10;
        return true;
    L10:
        return false;
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

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap r1) {
        super.setImageBitmap(r1);
        AppCompatImageHelper r0 = this.mImageHelper;
        if (r0 == null) goto L6;
        r0.applySupportImageTint();
        return;
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable r3) {
        AppCompatImageHelper r0 = this.mImageHelper;
        if (r0 == null) goto L8;
        if (r3 == null) goto L8;
        if (this.mHasLevel == true) goto L8;
        r0.obtainLevelFromDrawable(r3);
    L8:
        super.setImageDrawable(r3);
        AppCompatImageHelper r32 = this.mImageHelper;
        if (r32 == null) goto L14;
        r32.applySupportImageTint();
        if (this.mHasLevel == true) goto L15;
        this.mImageHelper.applyImageLevel();
        return;
    L15:
        return;
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int r1) {
        super.setImageLevel(r1);
        this.mHasLevel = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int r1) {
        AppCompatImageHelper r0 = this.mImageHelper;
        if (r0 == null) goto L6;
        r0.setImageResource(r1);
        return;
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri r1) {
        super.setImageURI(r1);
        AppCompatImageHelper r0 = this.mImageHelper;
        if (r0 == null) goto L6;
        r0.applySupportImageTint();
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

    public void setSupportImageTintList(ColorStateList r1) {
        AppCompatImageHelper r0 = this.mImageHelper;
        if (r0 == null) goto L6;
        r0.setSupportImageTintList(r1);
        return;
    }

    public void setSupportImageTintMode(PorterDuff.Mode r1) {
        AppCompatImageHelper r0 = this.mImageHelper;
        if (r0 == null) goto L6;
        r0.setSupportImageTintMode(r1);
        return;
    }
}
