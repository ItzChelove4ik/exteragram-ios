package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzyg extends Exception {
    public zzyg(Throwable r1, zzyv r2, StackTraceElement[] r3) {
        super(r2.toString(), r1);
        setStackTrace(r3);
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        return this;
    }
}
