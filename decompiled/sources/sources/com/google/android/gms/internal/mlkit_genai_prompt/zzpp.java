package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public final class zzpp implements zzqc {
    private static final Object zza = null;
    private volatile Object zzb;
    private volatile zzqc zzc;

    static {
        zza = new Object();
    }

    public zzpp(zzqc r2) {
        this.zzb = zza;
        this.zzc = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzqc
    public final Object zza() {
        Object r0 = this.zzb;
        Object r1 = zza;
        if (r0 != r1) goto L14;
        monitor-enter(this);
        Object r02 = this.zzb;     // Catch: Throwable -> L8
        if (r02 != r1) goto L10;
        r02 = this.zzc.zza();     // Catch: Throwable -> L8
        this.zzb = r02;     // Catch: Throwable -> L8
        this.zzc = null;     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
        return r02;
    L8:
        th = move-exception;
        throw th;
    L14:
        return r0;
    }
}
