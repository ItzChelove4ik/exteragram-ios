package com.google.android.gms.internal.measurement;

import java.util.concurrent.ExecutionException;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpv implements Runnable {
    private final /* synthetic */ ExecutionException zza;

    public /* synthetic */ zzpv(ExecutionException r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        throw new RuntimeException(this.zza.getCause());
    }
}
