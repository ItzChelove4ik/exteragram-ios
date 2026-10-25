package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpw implements Runnable {
    private final /* synthetic */ ListenableFuture zza;

    public /* synthetic */ zzpw(ListenableFuture r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        Futures.getDone(this.zza);     // Catch: ExecutionException -> L5
        return;
    L5:
        e = move-exception;
        zzpv r0 = new zzpv(e);
        zzrn.zzb().post(r0);
    }
}
