package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzaev {
    private final zzaeu zza;

    private zzaev(zzagm r2, Object r3, zzagm r4, Object r5) {
        this.zza = new zzaeu(r2, _UrlKt.FRAGMENT_ENCODE_SET, r4, r5);
    }

    public static zzaev zza(zzagm r1, Object r2, zzagm r3, Object r4) {
        return new zzaev(r1, _UrlKt.FRAGMENT_ENCODE_SET, r3, r4);
    }

    public static void zzb(zzada r2, zzaeu r3, Object r4, Object r5) {
        zzadk.zzf(r2, r3.zza, 1, r4);
        zzadk.zzf(r2, r3.zzc, 2, r5);
    }

    public static int zzc(zzaeu r2, Object r3, Object r4) {
        zzagm r0 = r2.zza;
        zzagm r22 = r2.zzc;
        return zzadk.zzh(r0, 1, r3) + zzadk.zzh(r22, 2, r4);
    }

    public final int zzd(int r1, Object r2, Object r3) {
        zzaeu r0 = this.zza;
        int r12 = zzada.zzE(r1 << 3);
        int r02 = zzc(r0, r2, r3);
        return r12 + (zzada.zzE(r02) + r02);
    }

    public final zzaeu zze() {
        return this.zza;
    }
}
