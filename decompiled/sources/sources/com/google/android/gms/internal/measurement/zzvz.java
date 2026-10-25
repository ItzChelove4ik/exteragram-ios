package com.google.android.gms.internal.measurement;

import android.os.StrictMode;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
final class zzvz {
    private static final zzvz zza = null;
    private final UUID zzb;
    private final AtomicLong zzc;

    static {
        StrictMode.ThreadPolicy r0 = StrictMode.allowThreadDiskReads();
        zza = new zzvz(UUID.randomUUID(), new SecureRandom().nextLong());     // Catch: Throwable -> L6
        StrictMode.setThreadPolicy(r0);
        return;
    L6:
        th = move-exception;
        StrictMode.setThreadPolicy(r0);
        throw th;
    }

    public zzvz(UUID r3, long r4) {
        this.zzb = r3;
        this.zzc = new AtomicLong((r4 ^ 25214903917L) & 281474976710655L);
    }

    public static zzvz zza() {
        return zza;
    }

    public final long zzb() {
    L2:
        AtomicLong r0 = this.zzc;
        long r1 = r0.get();
        if (r0.compareAndSet(r1, ((25214903917L * (((r1 * 25214903917L) + 11) & 281474976710655L)) + 11) & 281474976710655L) == false) goto L2;
        return (((int) (r5 >>> 16)) << 32) + ((int) (r3 >>> 16));
    }

    public final UUID zzc() {
        long r0 = zzb() & (-61441);
        long r2 = zzb() >>> 2;
        UUID r7 = this.zzb;
        return new UUID(r0 ^ r7.getMostSignificantBits(), r2 ^ r7.getLeastSignificantBits());
    }
}
