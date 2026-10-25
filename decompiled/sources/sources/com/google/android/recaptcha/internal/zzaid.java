package com.google.android.recaptcha.internal;

import sun.misc.Unsafe;

/* loaded from: classes5.dex */
final class zzaid extends zzaif {
    public zzaid(Unsafe r1) {
        super(r1);
    }

    @Override // com.google.android.recaptcha.internal.zzaif
    public final boolean zza(Object r1, long r2) {
        if (zzaig.zza == false) goto L7;
        return zzaig.zzp(r1, r2);
    L7:
        return zzaig.zzq(r1, r2);
    }

    @Override // com.google.android.recaptcha.internal.zzaif
    public final void zzb(Object r1, long r2, boolean r4) {
        if (zzaig.zza == false) goto L6;
        zzaig.zzr(r1, r2, r4);
        return;
    L6:
        zzaig.zzs(r1, r2, r4);
    }

    @Override // com.google.android.recaptcha.internal.zzaif
    public final float zzc(Object r1, long r2) {
        return Float.intBitsToFloat(this.zza.getInt(r1, r2));
    }

    @Override // com.google.android.recaptcha.internal.zzaif
    public final void zzd(Object r1, long r2, float r4) {
        int r42 = Float.floatToIntBits(r4);
        this.zza.putInt(r1, r2, r42);
    }

    @Override // com.google.android.recaptcha.internal.zzaif
    public final double zze(Object r1, long r2) {
        return Double.longBitsToDouble(this.zza.getLong(r1, r2));
    }

    @Override // com.google.android.recaptcha.internal.zzaif
    public final void zzf(Object r1, long r2, double r4) {
        long r42 = Double.doubleToLongBits(r4);
        this.zza.putLong(r1, r2, r42);
    }
}
