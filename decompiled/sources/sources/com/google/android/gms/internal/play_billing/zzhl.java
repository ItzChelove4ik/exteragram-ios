package com.google.android.gms.internal.play_billing;

import sun.misc.Unsafe;

/* loaded from: classes5.dex */
final class zzhl extends zzhn {
    public zzhl(Unsafe r1) {
        super(r1);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhn
    public final double zza(Object r1, long r2) {
        return Double.longBitsToDouble(this.zza.getLong(r1, r2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzhn
    public final float zzb(Object r1, long r2) {
        return Float.intBitsToFloat(this.zza.getInt(r1, r2));
    }

    @Override // com.google.android.gms.internal.play_billing.zzhn
    public final void zzc(Object r1, long r2, boolean r4) {
        if (zzho.zzb == false) goto L6;
        zzho.zzi(r1, r2, r4);
        return;
    L6:
        zzho.zzj(r1, r2, r4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhn
    public final void zzd(Object r1, long r2, byte r4) {
        if (zzho.zzb == false) goto L6;
        zzho.zzk(r1, r2, r4);
        return;
    L6:
        zzho.zzl(r1, r2, r4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhn
    public final void zze(Object r1, long r2, double r4) {
        long r42 = Double.doubleToLongBits(r4);
        this.zza.putLong(r1, r2, r42);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhn
    public final void zzf(Object r1, long r2, float r4) {
        int r42 = Float.floatToIntBits(r4);
        this.zza.putInt(r1, r2, r42);
    }

    @Override // com.google.android.gms.internal.play_billing.zzhn
    public final boolean zzg(Object r1, long r2) {
        if (zzho.zzb == false) goto L7;
        return zzho.zzt(r1, r2);
    L7:
        return zzho.zzu(r1, r2);
    }
}
