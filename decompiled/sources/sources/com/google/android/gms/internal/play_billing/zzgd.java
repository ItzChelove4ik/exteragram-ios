package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
final class zzgd implements zzgw {
    private static final zzgj zza = null;
    private final zzgj zzb;

    static {
        zza = new zzgb();
    }

    public zzgd() {
        zzfd r1 = zzfd.zza();
        int r2 = zzgs.$r8$clinit;
        zzgc r0 = new zzgc(new zzgj[]{r1, zza});
        byte[] r12 = zzfo.zzb;
        this.zzb = r0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgw
    public final zzgv zza(Class r8) {
        int r0 = zzgx.$r8$clinit;
        if (zzfi.class.isAssignableFrom(r8) == true) goto L5;
        int r02 = zzgs.$r8$clinit;
    L5:
        zzgi r1 = this.zzb.zzb(r8);
        if (r1.zzb() == true) goto L14;
        int r7 = zzgs.$r8$clinit;
        zzgq r2 = zzgr.zza();
        zzfy r3 = zzfz.zza();
        zzhh r4 = zzgx.zzm();
        if ((r1.zzc() - 1) == 1) goto L11;
        zzev r72 = zzex.zza();
    L13:
        return zzgo.zzl(r8, r1, r2, r3, r4, r72, zzgh.zza());
    L11:
        r72 = null;
        goto L13
    L14:
        int r73 = zzgs.$r8$clinit;
        return zzgp.zzc(zzgx.zzm(), zzex.zza(), r1.zza());
    }
}
