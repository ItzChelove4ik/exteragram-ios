package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncCallable;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final class zzvg implements Runnable {
    private AsyncCallable zza;
    private Executor zzb;

    public zzvg(AsyncCallable r1, Executor r2) {
        this.zza = (AsyncCallable) Preconditions.checkNotNull(r1);
        this.zzb = (Executor) Preconditions.checkNotNull(r2);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza = null;
        this.zzb = null;
    }

    public final /* synthetic */ AsyncCallable zza() {
        return this.zza;
    }

    public final /* synthetic */ Executor zzb() {
        return this.zzb;
    }
}
