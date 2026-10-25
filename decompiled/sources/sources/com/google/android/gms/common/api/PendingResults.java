package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public abstract class PendingResults {
    public static <R extends Result> PendingResult<R> immediateFailedResult(R r2, GoogleApiClient r3) {
        Preconditions.checkNotNull(r2, "Result must not be null");
        Preconditions.checkArgument(!r2.getStatus().isSuccess(), "Status code must not be SUCCESS");
        zad r0 = new zad(r3, r2);
        r0.setResult(r2);
        return r0;
    }

    public static PendingResult<Status> immediatePendingResult(Status r2) {
        Preconditions.checkNotNull(r2, "Result must not be null");
        StatusPendingResult r0 = new StatusPendingResult(Looper.getMainLooper());
        r0.setResult(r2);
        return r0;
    }

    public static PendingResult<Status> immediatePendingResult(Status r1, GoogleApiClient r2) {
        Preconditions.checkNotNull(r1, "Result must not be null");
        StatusPendingResult r0 = new StatusPendingResult(r2);
        r0.setResult(r1);
        return r0;
    }
}
