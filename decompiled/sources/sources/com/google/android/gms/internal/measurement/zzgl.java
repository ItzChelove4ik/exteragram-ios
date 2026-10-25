package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzgl extends zzadu implements zzafd {
    private static final zzgl zzw = null;
    private static volatile zzafj zzx;
    private int zzb;
    private long zze;
    private String zzf;
    private int zzg;
    private zzaef zzh;
    private zzaef zzi;
    private zzaef zzj;
    private String zzk;
    private boolean zzl;
    private zzaef zzm;
    private zzaef zzn;
    private String zzo;
    private String zzp;
    private zzgf zzq;
    private zzgp zzr;
    private zzgv zzs;
    private zzgr zzt;
    private zzgn zzu;
    private zzaeb zzv;

    static {
        zzgl r0 = new zzgl();
        zzw = r0;
        zzadu.zzcs(zzgl.class, r0);
    }

    private zzgl() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = zzadu.zzcy();
        this.zzi = zzadu.zzcy();
        this.zzj = zzadu.zzcy();
        this.zzk = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzm = zzadu.zzcy();
        this.zzn = zzadu.zzcy();
        this.zzo = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzp = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzv = zzadu.zzcv();
    }

    public static zzgk zzs() {
        return (zzgk) zzw.zzcn();
    }

    public static zzgl zzt() {
        return zzw;
    }

    public static /* synthetic */ zzgl zzx() {
        return zzw;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final long zzb() {
        return this.zze;
    }

    public final boolean zzc() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final List zze() {
        return this.zzh;
    }

    public final int zzf() {
        return this.zzi.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r27, Object r28, Object r29) {
        int r0 = r27 - 1;
        if (r0 == 0) goto L38;
        if (r0 == 2) goto L36;
        if (r0 == 3) goto L34;
        if (r0 == 4) goto L32;
        if (r0 == 5) goto L30;
        if (r0 != 6) goto L28;
        zzafj r02 = zzx;
        if (r02 == null) goto L17;
        return r02;
    L17:
        monitor-enter(zzgl.class);
        zzafj r03 = zzx;     // Catch: Throwable -> L21
        if (r03 != null) goto L23;
        r03 = new zzadq(zzw);     // Catch: Throwable -> L21
        zzx = r03;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzgl.class);     // Catch: Throwable -> L21
        return r03;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzw;
    L32:
        return new zzgk(null);
    L34:
        return new zzgl();
    L36:
        return zzadu.zzct(zzw, "\u0004\u0012\u0000\u0001\u0001\u0014\u0012\u0000\u0006\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b\n\u001b\u000bဈ\u0005\u000eဈ\u0006\u000fဉ\u0007\u0010ဉ\b\u0011ဉ\t\u0012ဉ\n\u0013ဉ\u000b\u0014+", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", zzgt.class, "zzi", zzgj.class, "zzj", zzfd.class, "zzk", "zzl", "zzm", zzja.class, "zzn", zzgh.class, "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv"});
    L38:
        return (byte) 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzgj zzh(int r1) {
        return (zzgj) this.zzi.get(r1);
    }

    public final List zzi() {
        return this.zzj;
    }

    public final List zzj() {
        return this.zzm;
    }

    public final int zzk() {
        return this.zzm.size();
    }

    public final List zzl() {
        return this.zzn;
    }

    public final String zzm() {
        return this.zzo;
    }

    public final boolean zzn() {
        if ((this.zzb & 128) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzgf zzo() {
        zzgf r0 = this.zzq;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzgf.zzh();
    }

    public final boolean zzp() {
        if ((this.zzb & 512) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzgv zzq() {
        zzgv r0 = this.zzs;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzgv.zze();
    }

    public final List zzr() {
        return this.zzv;
    }

    public final /* synthetic */ void zzu(int r3, zzgj r4) {
        r4.getClass();
        zzaef r0 = this.zzi;
        if (r0.zza() == true) goto L5;
        this.zzi = zzadu.zzcz(r0);
    L5:
        this.zzi.set(r3, r4);
    }

    public final /* synthetic */ void zzv() {
        this.zzj = zzadu.zzcy();
    }

    public final /* synthetic */ void zzw() {
        this.zzm = zzadu.zzcy();
    }
}
