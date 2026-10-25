package com.google.android.gms.internal.measurement;

import com.google.common.base.Functions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

/* loaded from: classes4.dex */
public abstract class zzuy {
    public static ListenableFuture zza(ListenableFuture r2) {
        return Futures.transform(r2, Functions.constant(null), MoreExecutors.directExecutor());
    }
}
