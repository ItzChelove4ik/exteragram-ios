package com.google.android.gms.internal.measurement;

import java.util.logging.Level;

/* loaded from: classes4.dex */
public final class zzxs extends zzxi {
    private static final zzxr zza = null;

    static {
        zza = new zzxr(null);
    }

    public zzxs(zzzf r1) {
        super(r1);
    }

    @Deprecated
    public static zzxs zzd(String r1) {
        return new zzxs(zzaad.zzd("Phlogger"));
    }

    public final zzxp zze(Level r3) {
        boolean r0 = zzb(r3);
        zzaad.zzh(zza(), r3, r0);
        if (r0 == true) goto L7;
        return zza;
    L7:
        return new zzxq(this, r3, false);
    }
}
