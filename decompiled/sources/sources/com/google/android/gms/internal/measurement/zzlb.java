package com.google.android.gms.internal.measurement;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

/* loaded from: classes4.dex */
public abstract class zzlb {
    public static ListenableFuture zza(Task r2, CancellationTokenSource r3) {
        zzkz r32 = new zzkz(r2, null);
        r2.addOnCompleteListener(MoreExecutors.directExecutor(), new zzla(r32));
        return r32;
    }
}
