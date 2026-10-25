package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzaet {
    private static final zzafa zzb = null;
    private final zzafa zza;

    static {
        zzb = new zzaer();
    }

    public zzaet() {
        zzado r1 = zzado.zza();
        int r2 = zzacf.$r8$clinit;
        this.zza = new zzaes(new zzafa[]{r1, zzb});
    }

    public final zzafp zza(Class r8) {
        int r0 = zzafq.$r8$clinit;
        if (zzadu.class.isAssignableFrom(r8) == true) goto L5;
        int r02 = zzacf.$r8$clinit;
    L5:
        zzaez r1 = this.zza.zzc(r8);
        if (r1.zza() == true) goto L14;
        int r7 = zzacf.$r8$clinit;
        zzafh r2 = zzafi.zza();
        zzaeo r3 = zzaep.zza();
        zzafz r4 = zzafq.zzB();
        if ((r1.zzc() - 1) == 1) goto L11;
        zzadg r72 = zzadi.zza();
    L13:
        return zzaff.zzm(r8, r1, r2, r3, r4, r72, zzaey.zza());
    L11:
        r72 = null;
        goto L13
    L14:
        int r73 = zzacf.$r8$clinit;
        return zzafg.zzh(zzafq.zzB(), zzadi.zza(), r1.zzb());
    }
}
