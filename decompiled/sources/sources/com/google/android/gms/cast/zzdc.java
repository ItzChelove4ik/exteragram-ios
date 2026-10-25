package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* loaded from: classes4.dex */
abstract class zzdc extends com.google.android.gms.cast.internal.zzc {
    private com.google.android.gms.cast.internal.zzat zza;
    protected final WeakReference zzg;
    final /* synthetic */ RemoteMediaPlayer zzh;

    public zzdc(RemoteMediaPlayer r1, GoogleApiClient r2) {
        Objects.requireNonNull(r1);
        this.zzh = r1;
        super(r2);
        this.zzg = new WeakReference(r2);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status r2) {
        return new zzdb(this, r2);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r5) {
        com.google.android.gms.cast.internal.zzx r52 = (com.google.android.gms.cast.internal.zzx) r5;
        RemoteMediaPlayer r0 = this.zzh;
        Object r1 = r0.zzf();
        monitor-enter(r1);
        GoogleApiClient r2 = (GoogleApiClient) this.zzg.get();     // Catch: Throwable -> L9
        if (r2 != null) goto L11;
        setResult(new zzdb(this, new Status(2100)));     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L11:
        r0.zzh().zza(r2);     // Catch: Throwable -> L9
        zza(r52);     // Catch: Throwable -> L14 IllegalArgumentException -> L18
    L15:
        this.zzh.zzh().zza(null);     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L18:
        e = move-exception;
        throw e;     // Catch: Throwable -> L9
    L14:
        setResult(new zzdb(this, new Status(2100)));     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public abstract void zza(com.google.android.gms.cast.internal.zzx r1);

    public final com.google.android.gms.cast.internal.zzat zzb() {
        if (this.zza != null) goto L6;
        this.zza = new zzda(this);
    L6:
        return this.zza;
    }
}
