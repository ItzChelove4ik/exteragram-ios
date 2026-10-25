package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzib extends zzadu implements zzafd {
    private static final zzib zzi = null;
    private static volatile zzafj zzj;
    private int zzb;
    private zzaef zze;
    private String zzf;
    private String zzg;
    private int zzh;

    static {
        zzib r0 = new zzib();
        zzi = r0;
        zzadu.zzcs(zzib.class, r0);
    }

    private zzib() {
        this.zze = zzadu.zzcy();
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzhz zzi() {
        return (zzhz) zzi.zzcn();
    }

    public static zzhz zzj(zzib r1) {
        zzadp r0 = zzi.zzcn();
        r0.zzbe(r1);
        return (zzhz) r0;
    }

    public static /* synthetic */ zzib zzq() {
        return zzi;
    }

    private final void zzr() {
        zzaef r0 = this.zze;
        if (r0.zza() == true) goto L6;
        this.zze = zzadu.zzcz(r0);
        return;
    }

    public final List zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zze.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzid zzc(int r1) {
        return (zzid) this.zze.get(r1);
    }

    public final boolean zzd() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final String zze() {
        return this.zzf;
    }

    public final boolean zzf() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzafj r7 = zzj;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzib.class);
        zzafj r72 = zzj;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzadq(zzi);     // Catch: Throwable -> L21
        zzj = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzib.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzi;
    L32:
        return new zzhz(null);
    L34:
        return new zzib();
    L36:
        return zzadu.zzct(zzi, "\u0004\u0004\u0000\u0001\u0001\t\u0004\u0000\u0001\u0000\u0001\u001b\u0007ဈ\u0000\bဈ\u0001\t᠌\u0002", new Object[]{"zzb", "zze", zzid.class, "zzf", "zzg", "zzh", zzia.zza});
    L38:
        return (byte) 1;
    }

    public final String zzh() {
        return this.zzg;
    }

    public final /* synthetic */ void zzk(int r1, zzid r2) {
        r2.getClass();
        zzr();
        this.zze.set(r1, r2);
    }

    public final /* synthetic */ void zzl(zzid r1) {
        r1.getClass();
        zzr();
        this.zze.add(r1);
    }

    public final /* synthetic */ void zzm(Iterable r1) {
        zzr();
        zzacb.zzcg(r1, this.zze);
    }

    public final /* synthetic */ void zzn() {
        this.zze = zzadu.zzcy();
    }

    public final /* synthetic */ void zzo(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzp(String r2) {
        r2.getClass();
        this.zzb |= 2;
        this.zzg = r2;
    }
}
