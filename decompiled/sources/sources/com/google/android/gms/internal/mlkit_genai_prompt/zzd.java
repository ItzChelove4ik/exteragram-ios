package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public final class zzd extends zzawy implements zzaxx {
    private static final zzd zzq = null;
    private int zzb;
    private long zzd;
    private long zze;
    private int zzf;
    private long zzg;
    private int zzh;
    private long zzi;
    private int zzj;
    private long zzk;
    private int zzl;
    private long zzm;
    private int zzn;
    private long zzo;
    private int zzp;

    static {
        zzd r0 = new zzd();
        zzq = r0;
        zzawy.zzN(zzd.class, r0);
    }

    private zzd() {
    }

    public static /* synthetic */ zzd zzC() {
        return zzq;
    }

    public static zzd zzo(byte[] r1, zzawl r2) {
        return (zzd) zzawy.zzR(zzq, r1, r2);
    }

    public final /* synthetic */ void zzA(long r2) {
        this.zzb |= 2048;
        this.zzo = r2;
    }

    public final /* synthetic */ void zzB(int r2) {
        this.zzb |= 4096;
        this.zzp = r2;
    }

    public final long zza() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawy
    public final Object zzb(int r15, Object r16, Object r17) {
        int r14 = r15 - 1;
        if (r14 == 0) goto L22;
        if (r14 == 2) goto L20;
        if (r14 == 3) goto L18;
        if (r14 == 4) goto L16;
        if (r14 == 5) goto L13;
        throw null;
    L13:
        return zzq;
    L16:
        return new zzc(null);
    L18:
        return new zzd();
    L20:
        return zzawy.zzO(zzq, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003င\u0002\u0004ဂ\u0003\u0005င\u0004\u0006ဂ\u0005\u0007င\u0006\bဂ\u0007\tင\b\nဂ\t\u000bင\n\fဂ\u000b\rင\f", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
    L22:
        return (byte) 1;
    }

    public final long zzc() {
        return this.zze;
    }

    public final int zzd() {
        return this.zzf;
    }

    public final long zze() {
        return this.zzg;
    }

    public final int zzf() {
        return this.zzh;
    }

    public final long zzg() {
        return this.zzi;
    }

    public final int zzh() {
        return this.zzj;
    }

    public final long zzi() {
        return this.zzk;
    }

    public final int zzj() {
        return this.zzl;
    }

    public final long zzk() {
        return this.zzm;
    }

    public final int zzl() {
        return this.zzn;
    }

    public final long zzm() {
        return this.zzo;
    }

    public final int zzn() {
        return this.zzp;
    }

    public final /* synthetic */ void zzp(long r2) {
        this.zzb |= 1;
        this.zzd = r2;
    }

    public final /* synthetic */ void zzq(long r2) {
        this.zzb |= 2;
        this.zze = r2;
    }

    public final /* synthetic */ void zzr(int r2) {
        this.zzb |= 4;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzs(long r2) {
        this.zzb |= 8;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzt(int r2) {
        this.zzb |= 16;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzu(long r2) {
        this.zzb |= 32;
        this.zzi = r2;
    }

    public final /* synthetic */ void zzv(int r2) {
        this.zzb |= 64;
        this.zzj = r2;
    }

    public final /* synthetic */ void zzw(long r2) {
        this.zzb |= 128;
        this.zzk = r2;
    }

    public final /* synthetic */ void zzx(int r2) {
        this.zzb |= 256;
        this.zzl = r2;
    }

    public final /* synthetic */ void zzy(long r2) {
        this.zzb |= 512;
        this.zzm = r2;
    }

    public final /* synthetic */ void zzz(int r2) {
        this.zzb |= 1024;
        this.zzn = r2;
    }
}
