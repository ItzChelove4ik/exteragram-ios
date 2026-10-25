package com.google.android.gms.internal.play_billing;

/* loaded from: classes.dex */
public final class zzjo extends zzfi implements zzgm {
    private static final zzjo zzb = null;
    private int zzd;
    private zzig zze;
    private long zzf;

    static {
        zzjo r0 = new zzjo();
        zzb = r0;
        zzfi.zzw(zzjo.class, r0);
    }

    private zzjo() {
    }

    public static /* synthetic */ void zzA(zzjo r0, zzig r1) {
        r1.getClass();
        r0.zze = r1;
        r0.zzd |= 1;
    }

    public static /* synthetic */ void zzB(zzjo r1, long r2) {
        r1.zzd |= 2;
        r1.zzf = r2;
    }

    public static zzjm zzc() {
        return (zzjm) zzb.zzl();
    }

    public static /* bridge */ /* synthetic */ zzjo zzd() {
        return zzb;
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
        return new zzjm(null);
    L18:
        return new zzjo();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဂ\u0001", new Object[]{"zzd", "zze", "zzf"});
    L22:
        return (byte) 1;
    }
}
