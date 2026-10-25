package com.google.android.gms.internal.measurement;

import java.io.Closeable;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class zzabt implements Closeable {
    private static final ThreadLocal zza = null;
    private int zzb;

    static {
        zza = new zzabs();
    }

    public zzabt() {
        this.zzb = 0;
    }

    public static int zza() {
        return zzd().zzb;
    }

    public static zzabt zzc() {
        zzabt r0 = zzd();
        int r1 = r0.zzb + 1;
        r0.zzb = r1;
        if (r1 == 0) goto L5;
        return r0;
    L5:
        Buffer$$ExternalSyntheticBUOutline2.m("Overflow of RecursionDepth (possible error in core library)");
        return null;
    }

    private static zzabt zzd() {
        return (zzabt) zza.get();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        int r0 = this.zzb;
        if (r0 <= 0) goto L6;
        this.zzb = r0 - 1;
        return;
    L6:
        Buffer$$ExternalSyntheticBUOutline2.m("Mismatched calls to RecursionDepth (possible error in core library)");
    }

    public final int zzb() {
        return this.zzb;
    }
}
