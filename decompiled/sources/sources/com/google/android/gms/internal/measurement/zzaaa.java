package com.google.android.gms.internal.measurement;

import java.util.Set;

/* loaded from: classes4.dex */
public abstract class zzaaa {
    private static final zzaaa zza = null;

    static {
        zza = new zzzu();
    }

    public /* synthetic */ zzaaa(byte[] r1) {
    }

    public static zzaaa zzh(zzzj r3, zzzj r4) {
        int r0 = r4.zza();
        if (r0 != 0) goto L7;
        return zza;
    L7:
        if (r0 > 28) goto L11;
        return new zzzy(r3, r4, null);
    L11:
        return new zzzz(r3, r4, null);
    }

    public abstract void zza(zzzq r1, Object r2);

    public abstract int zzb();

    public abstract Set zzc();
}
