package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzajn extends zzafv implements zzagy {
    private static final zzajn zzj = null;
    private static volatile zzahe zzk;
    private int zza;
    private int zzd;
    private Object zze;
    private zzajp zzf;
    private int zzg;
    private zzaja zzh;
    private zzajg zzi;

    static {
        zzajn r0 = new zzajn();
        zzj = r0;
        zzafv.zzL(zzajn.class, r0);
    }

    private zzajn() {
        this.zzd = 0;
    }

    public static /* synthetic */ zzajn zza() {
        return zzj;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzahe r7 = zzk;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zzajn.class);
        zzahe r72 = zzk;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzafr(zzj);     // Catch: Throwable -> L21
        zzk = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajn.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzj;
    L32:
        return new zzajm(null);
    L34:
        return new zzajn();
    L36:
        return zzafv.zzM(zzj, "\u0000.\u0001\u0001\u0001..\u0000\u0000\u0000\u0001ဉ\u0000\u0002\f\u0003ဉ\u0001\u0004?\u0000\u0005?\u0000\u0006?\u0000\u0007?\u0000\b?\u0000\t?\u0000\n?\u0000\u000b?\u0000\f?\u0000\r?\u0000\u000e?\u0000\u000f?\u0000\u0010?\u0000\u0011?\u0000\u0012?\u0000\u0013?\u0000\u0014?\u0000\u0015?\u0000\u0016?\u0000\u0017?\u0000\u0018?\u0000\u0019ဉ\u0002\u001a?\u0000\u001b?\u0000\u001c?\u0000\u001d?\u0000\u001e?\u0000\u001f?\u0000 ?\u0000!?\u0000\"?\u0000#?\u0000$?\u0000%?\u0000&?\u0000'?\u0000(?\u0000)?\u0000*?\u0000+?\u0000,?\u0000-?\u0000.?\u0000", new Object[]{"zze", "zzd", "zza", "zzf", "zzg", "zzh", "zzi"});
    L38:
        return (byte) 1;
    }
}
