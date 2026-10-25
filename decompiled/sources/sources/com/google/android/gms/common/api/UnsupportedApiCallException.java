package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;

/* loaded from: classes4.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zza;

    public UnsupportedApiCallException(Feature r1) {
        this.zza = r1;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Missing ".concat(String.valueOf(this.zza));
    }
}
