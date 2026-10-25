package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* loaded from: classes4.dex */
public final class zzpw extends zzyd implements zzzj {
    private static final zzpw zzh = null;
    private int zzb;
    private zzqc zzd;
    private zzsx zze;
    private zzyl zzf;
    private zzyj zzg;

    static {
        zzpw r0 = new zzpw();
        zzh = r0;
        zzyd.zzG(zzpw.class, r0);
    }

    private zzpw() {
        this.zzf = zzyd.zzM();
        this.zzg = zzyd.zzJ();
    }

    public static zzpv zza() {
        return (zzpv) zzh.zzB();
    }

    public static /* synthetic */ zzpw zze() {
        return zzh;
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
        return zzh;
    L16:
        return new zzpv(null);
    L18:
        return new zzpw();
    L20:
        return zzyd.zzH(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b\u0004ࠞ", new Object[]{"zzb", "zzd", "zze", "zzf", zzsr.class, "zzg", zzpm.zzb()});
    L22:
        return (byte) 1;
    }

    public final /* synthetic */ void zzc(zzqc r1) {
        r1.getClass();
        this.zzd = r1;
        this.zzb |= 1;
    }

    public final /* synthetic */ void zzd(Iterable r3) {
        zzyj r0 = this.zzg;
        if (r0.zza() == true) goto L5;
        int r1 = r0.size();
        this.zzg = r0.zze(r1 + r1);
    L5:
        Iterator r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L9;
        zzpm r02 = (zzpm) r32.next();
        this.zzg.zzh(r02.zza());
        goto L7
    }
}
