package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzqt extends zzyd implements zzzj {
    private static final zzqt zzi = null;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;

    static {
        zzqt r0 = new zzqt();
        zzi = r0;
        zzyd.zzG(zzqt.class, r0);
    }

    private zzqt() {
    }

    public static zzqs zza() {
        return (zzqs) zzi.zzB();
    }

    public static /* synthetic */ zzqt zzg() {
        return zzi;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L22;
        if (r82 == 2) goto L20;
        if (r82 == 3) goto L18;
        if (r82 == 4) goto L16;
        if (r82 == 5) goto L13;
        throw null;
    L13:
        return zzi;
    L16:
        return new zzqs(null);
    L18:
        return new zzqt();
    L20:
        return zzyd.zzH(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004", new Object[]{"zzb", "zzd", zzoo.zza(), "zze", "zzf", "zzg", "zzh"});
    L22:
        return (byte) 1;
    }

    public final /* synthetic */ void zzc(int r2) {
        this.zzb |= 2;
        this.zze = r2;
    }

    public final /* synthetic */ void zzd(int r2) {
        this.zzb |= 4;
        this.zzf = r2;
    }

    public final /* synthetic */ void zze(int r2) {
        this.zzb |= 8;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzf(int r2) {
        this.zzb |= 16;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzh(int r1) {
        this.zzd = r1 - 1;
        this.zzb |= 1;
    }
}
