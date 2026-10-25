package com.google.android.gms.cast.framework.internal.featurehighlight;

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
import androidx.annotation.Keep;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.internal.cast.zzgy;

/* loaded from: classes4.dex */
class InnerZoneDrawable extends Drawable {
    private final Paint zza;
    private final Paint zzb;
    private final Rect zzc;
    private final int zzd;
    private final int zze;
    private float zzf;
    private float zzg;
    private float zzh;
    private float zzi;
    private float zzj;
    private float zzk;

    public InnerZoneDrawable(Context r4) {
        Paint r0 = new Paint();
        this.zza = r0;
        Paint r1 = new Paint();
        this.zzb = r1;
        this.zzc = new Rect();
        this.zzg = 1.0f;
        Resources r42 = r4.getResources();
        this.zzd = r42.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zze = r42.getInteger(R.integer.cast_libraries_material_featurehighlight_pulse_base_alpha);
        r0.setAntiAlias(true);
        Paint.Style r43 = Paint.Style.FILL;
        r0.setStyle(r43);
        r0.setColor(-1);
        r1.setAntiAlias(true);
        r1.setStyle(r43);
        r1.setColor(-1);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas r5) {
        float r0 = this.zzk;
        if (r0 <= 0.0f) goto L5;
        float r1 = this.zzf * this.zzj;
        Paint r2 = this.zzb;
        r2.setAlpha((int) (this.zze * r0));
        r5.drawCircle(this.zzh, this.zzi, r1, r2);
    L5:
        r5.drawCircle(this.zzh, this.zzi, this.zzf * this.zzg, this.zza);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int r2) {
        this.zza.setAlpha(r2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter r2) {
        this.zza.setColorFilter(r2);
        invalidateSelf();
    }

    @Keep
    public void setPulseAlpha(float r1) {
        this.zzk = r1;
        invalidateSelf();
    }

    @Keep
    public void setPulseScale(float r1) {
        this.zzj = r1;
        invalidateSelf();
    }

    @Keep
    public void setScale(float r1) {
        this.zzg = r1;
        invalidateSelf();
    }

    public final void zza(Rect r3) {
        Rect r0 = this.zzc;
        r0.set(r3);
        this.zzh = r0.exactCenterX();
        this.zzi = r0.exactCenterY();
        float r32 = r0.width();
        this.zzf = Math.max(this.zzd, Math.max(r32 / 2.0f, r0.height() / 2.0f));
        invalidateSelf();
    }

    public final Animator zzb() {
        ObjectAnimator r7 = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{0.0f}), PropertyValuesHolder.ofInt("alpha", new int[]{0}), PropertyValuesHolder.ofFloat("pulseScale", new float[]{0.0f}), PropertyValuesHolder.ofFloat("pulseAlpha", new float[]{0.0f})});
        r7.setInterpolator(zzgy.zzb());
        return r7.setDuration(200);
    }
}
