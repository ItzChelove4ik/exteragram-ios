package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;

/* loaded from: classes4.dex */
final /* synthetic */ class zzva implements AsyncFunction {
    private final /* synthetic */ IOException zza;

    public /* synthetic */ zzva(IOException r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r1) {
        IOException r0 = this.zza;
        r0.addSuppressed((IOException) r1);
        throw r0;
    }
}
