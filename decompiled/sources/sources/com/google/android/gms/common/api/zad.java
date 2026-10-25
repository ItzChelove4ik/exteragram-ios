package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* loaded from: classes4.dex */
final class zad<R extends Result> extends BasePendingResult<R> {
    private final Result zae;

    public zad(GoogleApiClient r1, Result r2) {
        super(r1);
        this.zae = r2;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final R createFailedResult(Status r1) {
        return (R) this.zae;
    }
}
