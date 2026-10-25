package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzaed;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzni extends zzadu implements zzafd {
    private static final zzaec zzl = null;
    private static final zzni zzq = null;
    private static volatile zzafj zzr;
    private int zzb;
    private zzacr zze;
    private boolean zzf;
    private String zzg;
    private long zzh;
    private zzaef zzi;
    private zzaef zzj;
    private zzaeb zzk;
    private zznm zzm;
    private boolean zzn;
    private boolean zzo;
    private zznf zzp;

    static {
        zzl = new zzng();
        zzni r0 = new zzni();
        zzq = r0;
        zzadu.zzcs(zzni.class, r0);
    }

    private zzni() {
        this.zze = zzacr.zza;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = zzadu.zzcy();
        this.zzj = zzadu.zzcy();
        this.zzk = zzadu.zzcv();
    }

    public static zznh zzo() {
        return (zznh) zzq.zzcn();
    }

    public static zzni zzp() {
        return zzq;
    }

    public static /* synthetic */ zzni zzr() {
        return zzq;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final zzacr zzb() {
        return this.zze;
    }

    public final boolean zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final long zze() {
        return this.zzh;
    }

    public final List zzf() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r14, Object r15, Object r16) {
        int r142 = r14 - 1;
        if (r142 == 0) goto L38;
        if (r142 == 2) goto L36;
        if (r142 == 3) goto L34;
        if (r142 == 4) goto L32;
        if (r142 == 5) goto L30;
        if (r142 != 6) goto L28;
        zzafj r13 = zzr;
        if (r13 == null) goto L17;
        return r13;
    L17:
        monitor-enter(zzni.class);
        zzafj r132 = zzr;     // Catch: Throwable -> L21
        if (r132 != null) goto L23;
        r132 = new zzadq(zzq);     // Catch: Throwable -> L21
        zzr = r132;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzni.class);     // Catch: Throwable -> L21
        return r132;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzq;
    L32:
        return new zznh(null);
    L34:
        return new zzni();
    L36:
        return zzadu.zzct(zzq, "\u0004\u000b\u0000\u0001\u0001\f\u000b\u0000\u0003\u0000\u0001ည\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005\u001a\u0006\u001a\u0007ࠬ\bဉ\u0004\nဇ\u0005\u000bဇ\u0006\fဉ\u0007", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzabz.zzc(), "zzm", "zzn", "zzo", "zzp"});
    L38:
        return (byte) 1;
    }

    public final List zzh() {
        return this.zzj;
    }

    public final List zzi() {
        return new zzaed.zza(this.zzk, zzl);
    }

    public final boolean zzj() {
        if ((this.zzb & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zznm zzk() {
        zznm r0 = this.zzm;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zznm.zzc();
    }

    public final boolean zzl() {
        return this.zzn;
    }

    public final boolean zzm() {
        return this.zzo;
    }

    public final zznf zzn() {
        zznf r0 = this.zzp;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zznf.zzb();
    }

    public final /* synthetic */ void zzq(long r2) {
        this.zzb |= 8;
        this.zzh = r2;
    }
}
