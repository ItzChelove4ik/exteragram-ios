package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public final class zzwx implements AsyncCallable {
    final /* synthetic */ zzws zza;
    final /* synthetic */ AsyncCallable zzb;

    public zzwx(zzws r1, AsyncCallable r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final ListenableFuture call() {
        zzws r0 = this.zza;
        zzwq r1 = zzvy.zzd();
        zzws r02 = zzvy.zzc(r1, r0);
        ListenableFuture r2 = this.zzb.call();     // Catch: Throwable -> L6
        zzvy.zzc(r1, r02);
        return r2;
    L6:
        th = move-exception;
        zzvu.zza(th);     // Catch: Throwable -> L9
        throw th;     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        zzvy.zzc(r1, r02);
        throw th;
    }

    public final String toString() {
        AsyncCallable r2 = this.zzb;
        StringBuilder r1 = new StringBuilder(r2.toString().length() + 14);
        r1.append("propagating=[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    }
}
