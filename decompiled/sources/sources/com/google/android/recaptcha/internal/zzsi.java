package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzsi {
    private static final zzsh zzf = null;
    private final zzse zza;
    private final zzsg zzb;
    private final int zzc;
    private final boolean zzd;
    private final boolean zze;
    private final int zzg;

    static {
        zzf = zzsh.zza;
    }

    public /* synthetic */ zzsi(zzse r1, int r2, int r3, boolean r4, boolean r5, zzsh r6, byte[] r7) {
        this.zza = r1;
        this.zzg = r2;
        int r22 = r2 - 2;
        if (r22 != 1) goto L5;
        zzsg r12 = zzsg.zza;
    L9:
        this.zzb = r12;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        return;
    L5:
        if (r22 == 3) goto L7;
        r12 = zzsg.zzb;
        goto L9
    L7:
        r12 = zzsg.zzc;
        goto L9
    }

    public static /* synthetic */ zzsh zzg() {
        return zzf;
    }

    public final zzse zza() {
        return this.zza;
    }

    public final zzsg zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzd;
    }

    public final /* synthetic */ zzse zze() {
        return this.zza;
    }

    public final /* synthetic */ boolean zzf() {
        return this.zze;
    }

    public final /* synthetic */ int zzh() {
        return this.zzg;
    }
}
