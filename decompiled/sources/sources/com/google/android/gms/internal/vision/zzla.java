package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzla implements zzki {
    private final zzkk zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    public zzla(zzkk r4, String r5, Object[] r6) {
        this.zza = r4;
        this.zzb = r5;
        this.zzc = r6;
        char r42 = r5.charAt(0);
        if (r42 >= 55296) goto L6;
        this.zzd = r42;
        return;
    L6:
        int r43 = r42 & 8191;
        int r0 = 13;
        int r1 = 1;
    L7:
        int r2 = r1 + 1;
        char r12 = r5.charAt(r1);
        if (r12 < 55296) goto L10;
        r43 = r43 | ((r12 & 8191) << r0);
        r0 = r0 + 13;
        r1 = r2;
        goto L7
    L10:
        this.zzd = r43 | (r12 << r0);
    }

    @Override // com.google.android.gms.internal.vision.zzki
    public final int zza() {
        if ((this.zzd & 1) != 1) goto L7;
        return zzkz.zza;
    L7:
        return zzkz.zzb;
    }

    @Override // com.google.android.gms.internal.vision.zzki
    public final boolean zzb() {
        if ((this.zzd & 2) != 2) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzki
    public final zzkk zzc() {
        return this.zza;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final Object[] zze() {
        return this.zzc;
    }
}
