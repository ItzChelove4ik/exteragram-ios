package com.google.android.gms.internal.play_billing;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class zzip extends zzfi implements zzgm {
    private static final zzip zzb = null;
    private int zzd;
    private String zze;
    private int zzf;
    private zzfm zzg;
    private zzfn zzh;
    private zzig zzi;
    private boolean zzj;
    private boolean zzk;

    static {
        zzip r0 = new zzip();
        zzb = r0;
        zzfi.zzw(zzip.class, r0);
    }

    private zzip() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = zzfi.zzq();
        this.zzh = zzfi.zzr();
    }

    public static /* bridge */ /* synthetic */ zzip zzc() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r12, Object r13, Object r14) {
        int r122 = r12 - 1;
        if (r122 == 0) goto L22;
        if (r122 == 2) goto L20;
        if (r122 == 3) goto L18;
        if (r122 == 4) goto L16;
        if (r122 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzim(null);
    L18:
        return new zzip();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0002\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003ࠬ\u0004\u001b\u0005ဉ\u0002\u0006ဇ\u0003\u0007ဇ\u0004", new Object[]{"zzd", "zze", "zzf", zzin.zza, "zzg", zzik.zza, "zzh", zzjl.class, "zzi", "zzj", "zzk"});
    L22:
        return (byte) 1;
    }
}
