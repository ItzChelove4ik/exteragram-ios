package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.R;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.internal.cast.zzgy;
import com.google.android.gms.internal.cast.zzgz;

/* loaded from: classes4.dex */
class OuterHighlightDrawable extends Drawable {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final Rect zzd;
    private final Rect zze;
    private final Paint zzf;
    private float zzg;
    private float zzh;
    private float zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private int zzm;

    public OuterHighlightDrawable(Context r7) {
        this.zzd = new Rect();
        this.zze = new Rect();
        Paint r0 = new Paint();
        this.zzf = r0;
        this.zzh = 1.0f;
        this.zzk = 0.0f;
        this.zzl = 0.0f;
        this.zzm = 244;
        TypedValue r2 = new TypedValue();
        r7.getTheme().resolveAttribute(R.attr.colorPrimary, r2, true);
        r0.setColor(ColorUtils.setAlphaComponent(r2.data, 244));
        this.zzm = r0.getAlpha();
        r0.setAntiAlias(true);
        r0.setStyle(Paint.Style.FILL);
        Resources r72 = r7.getResources();
        this.zza = r72.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_center_threshold);
        this.zzb = r72.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_center_horizontal_offset);
        this.zzc = r72.getDimensionPixelSize(com.google.android.gms.cast.framework.R.dimen.cast_libraries_material_featurehighlight_outer_padding);
    }

    private static final float zzh(float r5, float r6, Rect r7) {
        float r0 = r7.left;
        float r1 = r7.top;
        float r2 = r7.right;
        float r72 = r7.bottom;
        float r3 = zzgz.zza(r5, r6, r0, r1);
        float r12 = zzgz.zza(r5, r6, r2, r1);
        float r22 = zzgz.zza(r5, r6, r2, r72);
        float r52 = zzgz.zza(r5, r6, r0, r72);
        if (r3 <= r12) goto L10;
        if (r3 <= r22) goto L10;
        if (r3 <= r52) goto L10;
    L19:
        return (float) Math.ceil(r3);
    L10:
        if (r12 <= r22) goto L15;
        if (r12 <= r52) goto L15;
        r3 = r12;
    L15:
        if (r22 > r52) goto L17;
        r3 = r52;
        goto L19
    L17:
        r3 = r22;
        goto L19
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas r5) {
        r5.drawCircle(this.zzi + this.zzk, this.zzj + this.zzl, this.zzg * this.zzh, this.zzf);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.zzf.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int r2) {
        this.zzf.setAlpha(r2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter r2) {
        this.zzf.setColorFilter(r2);
        invalidateSelf();
    }

    @Keep
    public void setScale(float r1) {
        this.zzh = r1;
        invalidateSelf();
    }

    @Keep
    public void setTranslationX(float r1) {
        this.zzk = r1;
        invalidateSelf();
    }

    @Keep
    public void setTranslationY(float r1) {
        this.zzl = r1;
        invalidateSelf();
    }

    public final int zza() {
        return this.zzf.getColor();
    }

    public final void zzb(int r2) {
        Paint r0 = this.zzf;
        r0.setColor(r2);
        this.zzm = r0.getAlpha();
        invalidateSelf();
    }

    public final void zzc(Rect r7, Rect r8) {
        this.zzd.set(r7);
        this.zze.set(r8);
        float r0 = r7.exactCenterX();
        float r1 = r7.exactCenterY();
        Rect r2 = getBounds();
        float r3 = r1 - r2.top;
        float r4 = r2.bottom - r1;
        if (Math.min(r3, r4) >= this.zza) goto L6;
        this.zzi = r0;
        this.zzj = r1;
    L10:
        this.zzg = this.zzc + Math.max(zzh(this.zzi, r1, r7), zzh(this.zzi, this.zzj, r8));
        invalidateSelf();
        return;
    L6:
        if (r0 > r2.exactCenterX()) goto L8;
        float r02 = r8.exactCenterX() + this.zzb;
    L9:
        this.zzi = r02;
        r1 = r8.exactCenterY();
        this.zzj = r1;
        goto L10
    L8:
        r02 = r8.exactCenterX() - this.zzb;
        goto L9
    }

    public final float zzd() {
        return this.zzi;
    }

    public final float zze() {
        return this.zzj;
    }

    public final boolean zzf(float r3, float r4) {
        if (zzgz.zza(r3, r4, this.zzi, this.zzj) >= this.zzg) goto L6;
        return true;
    L6:
        return false;
    }

    public final Animator zzg(float r7, float r8) {
        ObjectAnimator r6 = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofFloat("translationX", new float[]{r7, 0.0f}), PropertyValuesHolder.ofFloat("translationY", new float[]{r8, 0.0f}), PropertyValuesHolder.ofInt("alpha", new int[]{0, this.zzm})});
        r6.setInterpolator(zzgy.zza());
        return r6.setDuration(350);
    }
}
