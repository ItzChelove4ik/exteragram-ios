package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
final class zzza implements zzzt {
    private static final zzzg zzb = null;
    private final zzzg zza;

    static {
        zzb = new zzyy();
    }

    public zzza() {
        zzxz r1 = zzxz.zza();
        int r2 = zzxb.$r8$clinit;
        zzyz r0 = new zzyz(new zzzg[]{r1, zzb});
        byte[] r12 = zzym.zzb;
        this.zza = r0;
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final zzzs zza(Class r8) {
        int r0 = zzzu.$r8$clinit;
        if (zzyd.class.isAssignableFrom(r8) == true) goto L5;
        int r02 = zzxb.$r8$clinit;
    L5:
        zzzf r1 = this.zza.zzc(r8);
        if (r1.zza() == true) goto L14;
        int r7 = zzxb.$r8$clinit;
        zzzn r2 = zzzo.zza();
        zzyv r3 = zzyw.zza();
        zzaad r4 = zzzu.zzB();
        if ((r1.zzc() - 1) == 1) goto L11;
        zzxs r72 = zzxu.zza();
    L13:
        return zzzl.zzi(r8, r1, r2, r3, r4, r72, zzze.zza());
    L11:
        r72 = null;
        goto L13
    L14:
        int r73 = zzxb.$r8$clinit;
        return zzzm.zzi(zzzu.zzB(), zzxu.zza(), r1.zzb());
    }
}
