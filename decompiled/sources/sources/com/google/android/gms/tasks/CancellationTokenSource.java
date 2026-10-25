package com.google.android.gms.tasks;

/* loaded from: classes5.dex */
public class CancellationTokenSource {
    private final zzb zza;

    public CancellationTokenSource() {
        this.zza = new zzb();
    }

    public void cancel() {
        this.zza.zza();
    }

    public CancellationToken getToken() {
        return this.zza;
    }
}
