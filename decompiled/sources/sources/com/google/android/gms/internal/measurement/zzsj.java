package com.google.android.gms.internal.measurement;

import java.io.Closeable;

/* loaded from: classes4.dex */
public final class zzsj implements Closeable {
    private final Closeable zza;

    private zzsj(Closeable r1) {
        this.zza = r1;
    }

    public static zzsj zza(Closeable r1) {
        return new zzsj(r1);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Closeable r0 = this.zza;
        if (r0 == null) goto L6;
        r0.close();
        return;
    }

    public final Closeable zzb() {
        return this.zza;
    }
}
