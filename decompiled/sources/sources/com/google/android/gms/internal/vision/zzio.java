package com.google.android.gms.internal.vision;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.gms.internal.vision.zzjb;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes5.dex */
public class zzio {
    private static volatile zzio zzc;
    private static volatile zzio zzd;
    private static final zzio zze = null;
    private final Map<zza, zzjb.zze<?, ?>> zzf;

    public static final class zza {
        private final Object zza;
        private final int zzb;

        public zza(Object r1, int r2) {
            this.zza = r1;
            this.zzb = r2;
        }

        public final boolean equals(Object r4) {
            if ((r4 instanceof zza) == true) goto L5;
            return false;
        L5:
            zza r42 = (zza) r4;
            if (this.zza == r42.zza) goto L8;
        L11:
            return false;
        L8:
            if (this.zzb != r42.zzb) goto L11;
            return true;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * 65535) + this.zzb;
        }
    }

    static {
        zze = new zzio(true);
    }

    private zzio(boolean r1) {
        this.zzf = Collections.EMPTY_MAP;
    }

    public static zzio zzb() {
        zzio r0 = zzc;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(zzio.class);
        zzio r02 = zzc;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = zze;     // Catch: Throwable -> L9
        zzc = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(zzio.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static zzio zzc() {
        zzio r0 = zzd;
        if (r0 == null) goto L6;
        return r0;
    L6:
        monitor-enter(zzio.class);
        zzio r1 = zzd;     // Catch: Throwable -> L11
        if (r1 == null) goto L13;
        monitor-exit(zzio.class);     // Catch: Throwable -> L11
        return r1;
    L13:
        zzio r12 = zziz.zza(zzio.class);     // Catch: Throwable -> L11
        zzd = r12;     // Catch: Throwable -> L11
        monitor-exit(zzio.class);     // Catch: Throwable -> L11
        return r12;
    L11:
        th = move-exception;
        throw th;
    }

    public final <ContainingType extends zzkk> zzjb.zze<ContainingType, ?> zza(ContainingType r2, int r3) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzf.get(new zza(r2, r3)));
        return null;
    }
}
