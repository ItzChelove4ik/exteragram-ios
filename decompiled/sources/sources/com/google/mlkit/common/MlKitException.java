package com.google.mlkit.common;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
public class MlKitException extends Exception {
    private final int zza;

    public MlKitException(String r2, int r3) {
        super(Preconditions.checkNotEmpty(r2, "Provided message must not be empty."));
        this.zza = r3;
    }

    public MlKitException(String r2, int r3, Throwable r4) {
        super(Preconditions.checkNotEmpty(r2, "Provided message must not be empty."), r4);
        this.zza = r3;
    }

    public int getErrorCode() {
        return this.zza;
    }
}
