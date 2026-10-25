package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

/* loaded from: classes4.dex */
abstract class zzpx {
    public static void zza(ListenableFuture r2) {
        r2.addListener(new zzpw(r2), MoreExecutors.directExecutor());
    }
}
