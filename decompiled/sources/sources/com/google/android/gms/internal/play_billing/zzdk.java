package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
public final class zzdk extends zzfi implements zzgm {
    private static final zzdk zzb = null;
    private zzfn zzd;

    static {
        zzdk r0 = new zzdk();
        zzb = r0;
        zzfi.zzw(zzdk.class, r0);
    }

    private zzdk() {
        this.zzd = zzfi.zzr();
    }

    public static zzdj zza() {
        return (zzdj) zzb.zzl();
    }

    public static /* bridge */ /* synthetic */ zzdk zzc() {
        return zzb;
    }

    public static /* synthetic */ void zzd(zzdk r2, Iterable r3) {
        zzfn r0 = r2.zzd;
        if (r0.zzc() == true) goto L5;
        int r1 = r0.size();
        r2.zzd = r0.zzd(r1 + r1);
    L5:
        zzds.zzg(r3, r2.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L22;
        if (r12 == 2) goto L20;
        if (r12 == 3) goto L18;
        if (r12 == 4) goto L16;
        if (r12 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzdj(null);
    L18:
        return new zzdk();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzdi.class});
    L22:
        return (byte) 1;
    }
}
