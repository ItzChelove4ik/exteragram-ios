package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;

/* loaded from: classes4.dex */
public final class zzvb extends zztf {
    private final zzafc zza;

    public zzvb(zzafc r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zztf
    public final ListenableFuture zza(IOException r2, zztg r3) {
        if ((r2.getCause() instanceof zzaeh) == true) goto L7;
        return Futures.immediateFailedFuture(r2);
    L7:
        return Futures.catchingAsync(r3.zza(Futures.immediateFuture(this.zza)), IOException.class, new zzva(r2), MoreExecutors.directExecutor());
    }
}
