package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* loaded from: classes4.dex */
public abstract class Response<T extends Result> {
    private Result zza;

    public Response(T r1) {
        this.zza = r1;
    }

    public T getResult() {
        return (T) this.zza;
    }
}
