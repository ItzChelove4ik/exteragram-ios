package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* loaded from: classes4.dex */
final class zzaai extends zzaaj {
    public zzaai(Unsafe r1) {
        super(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzaaj
    public final void zza(Object r1, long r2, byte r4) {
        if (zzaak.zzb == false) goto L6;
        zzaak.zzs(r1, r2, r4);
        return;
    L6:
        zzaak.zzt(r1, r2, r4);
    }

    @Override // com.google.android.gms.internal.cast.zzaaj
    public final boolean zzb(Object r1, long r2) {
        if (zzaak.zzb == false) goto L7;
        return zzaak.zzu(r1, r2);
    L7:
        return zzaak.zzv(r1, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzaaj
    public final void zzc(Object r1, long r2, boolean r4) {
        if (zzaak.zzb == false) goto L6;
        zzaak.zzw(r1, r2, r4);
        return;
    L6:
        zzaak.zzx(r1, r2, r4);
    }

    @Override // com.google.android.gms.internal.cast.zzaaj
    public final float zzd(Object r1, long r2) {
        return Float.intBitsToFloat(this.zza.getInt(r1, r2));
    }

    @Override // com.google.android.gms.internal.cast.zzaaj
    public final void zze(Object r1, long r2, float r4) {
        int r42 = Float.floatToIntBits(r4);
        this.zza.putInt(r1, r2, r42);
    }

    @Override // com.google.android.gms.internal.cast.zzaaj
    public final double zzf(Object r1, long r2) {
        return Double.longBitsToDouble(this.zza.getLong(r1, r2));
    }

    @Override // com.google.android.gms.internal.cast.zzaaj
    public final void zzg(Object r1, long r2, double r4) {
        long r42 = Double.doubleToLongBits(r4);
        this.zza.putLong(r1, r2, r42);
    }
}
