package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzamh extends zzafv implements zzagy {
    private static final zzamh zzf = null;
    private static volatile zzahe zzg;
    private int zza;
    private int zzd;
    private int zze;

    static {
        zzamh r0 = new zzamh();
        zzf = r0;
        zzafv.zzL(zzamh.class, r0);
    }

    private zzamh() {
    }

    public static zzamh zzb(byte[] r1) {
        return (zzamh) zzafv.zzV(zzf, r1);
    }

    public static /* synthetic */ zzamh zzc() {
        return zzf;
    }

    public final zzami zza() {
        switch(this.zze) {
            case 0: goto L14;
            case 1: goto L13;
            case 2: goto L12;
            case 3: goto L11;
            case 4: goto L10;
            case 5: goto L9;
            case 6: goto L8;
            case 7: goto L7;
            case 8: goto L6;
            case 9: goto L5;
            default: goto L4;
        };
    L4:
        zzami r0 = null;
    L15:
        if (r0 == null) goto L17;
        return r0;
    L17:
        return zzami.zzk;
    L5:
        r0 = zzami.zzj;
        goto L15
    L6:
        r0 = zzami.zzi;
        goto L15
    L7:
        r0 = zzami.zzh;
        goto L15
    L8:
        r0 = zzami.zzg;
        goto L15
    L9:
        r0 = zzami.zzf;
        goto L15
    L10:
        r0 = zzami.zze;
        goto L15
    L11:
        r0 = zzami.zzd;
        goto L15
    L12:
        r0 = zzami.zzc;
        goto L15
    L13:
        r0 = zzami.zzb;
        goto L15
    L14:
        r0 = zzami.zza;
        goto L15
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L38;
        if (r12 == 2) goto L36;
        if (r12 == 3) goto L34;
        if (r12 == 4) goto L32;
        if (r12 == 5) goto L30;
        if (r12 != 6) goto L28;
        zzahe r0 = zzg;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzamh.class);
        zzahe r02 = zzg;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzf);     // Catch: Throwable -> L21
        zzg = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzamh.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzf;
    L32:
        return new zzamg(null);
    L34:
        return new zzamh();
    L36:
        return zzafv.zzM(zzf, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001", new Object[]{"zza", "zzd", "zze"});
    L38:
        return (byte) 1;
    }
}
