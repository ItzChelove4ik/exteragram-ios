package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public final class zzwy implements AsyncFunction {
    final /* synthetic */ zzws zza;
    final /* synthetic */ AsyncFunction zzb;

    public zzwy(zzws r1, AsyncFunction r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object r3) {
        zzws r0 = this.zza;
        zzwq r1 = zzvy.zzd();
        zzws r02 = zzvy.zzc(r1, r0);
        ListenableFuture r2 = this.zzb.apply(r3);     // Catch: Throwable -> L9
        if (r2 == null) goto L8;
        zzvy.zzc(r1, r02);
        return r2;
    L8:
        throw new IllegalStateException("AsyncFunction should return a ListenableFuture instead of null.");     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        zzvu.zza(th);     // Catch: Throwable -> L12
        throw th;     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        zzvy.zzc(r1, r02);
        throw th;
    }

    public final String toString() {
        AsyncFunction r2 = this.zzb;
        StringBuilder r1 = new StringBuilder(r2.toString().length() + 14);
        r1.append("propagating=[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    }
}
