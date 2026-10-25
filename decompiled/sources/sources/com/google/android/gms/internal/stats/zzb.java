package com.google.android.gms.internal.stats;

import java.io.Closeable;

/* loaded from: classes.dex */
public final class zzb implements Closeable {
    private static final zzb zza = null;

    static {
        zza = new zzb(false, null);
    }

    private zzb(boolean r1, zzd r2) {
    }

    public static zzb zza(boolean r0, zzc r1) {
        return zza;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
