package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zznk extends zzadu implements zzafd {
    private static final zznk zzo = null;
    private static volatile zzafj zzp;
    private int zzb;
    private zzacr zze;
    private boolean zzf;
    private String zzg;
    private zzaef zzh;
    private zzaef zzi;
    private zzaeb zzj;
    private zznm zzk;
    private boolean zzl;
    private boolean zzm;
    private zznf zzn;

    static {
        zznk r0 = new zznk();
        zzo = r0;
        zzadu.zzcs(zznk.class, r0);
    }

    private zznk() {
        this.zze = zzacr.zza;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = zzadu.zzcy();
        this.zzi = zzadu.zzcy();
        this.zzj = zzadu.zzcv();
    }

    public static zznk zza() {
        return zzo;
    }

    public static /* synthetic */ zznk zzb() {
        return zzo;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r13, Object r14, Object r15) {
        int r132 = r13 - 1;
        if (r132 == 0) goto L38;
        if (r132 == 2) goto L36;
        if (r132 == 3) goto L34;
        if (r132 == 4) goto L32;
        if (r132 == 5) goto L30;
        if (r132 != 6) goto L28;
        zzafj r12 = zzp;
        if (r12 == null) goto L17;
        return r12;
    L17:
        monitor-enter(zznk.class);
        zzafj r122 = zzp;     // Catch: Throwable -> L21
        if (r122 != null) goto L23;
        r122 = new zzadq(zzo);     // Catch: Throwable -> L21
        zzp = r122;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zznk.class);     // Catch: Throwable -> L21
        return r122;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzo;
    L32:
        return new zznj(null);
    L34:
        return new zznk();
    L36:
        return zzadu.zzct(zzo, "\u0004\n\u0000\u0001\u0001\f\n\u0000\u0003\u0000\u0001ည\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004\u001a\u0005\u001a\u0007ࠬ\bဉ\u0003\nဇ\u0004\u000bဇ\u0005\fဉ\u0006", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzabz.zzc(), "zzk", "zzl", "zzm", "zzn"});
    L38:
        return (byte) 1;
    }
}
