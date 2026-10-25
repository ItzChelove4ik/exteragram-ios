package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzaed implements zzahe {
    private static final zzafh zza = null;

    static {
        zzafh r0 = zzafh.zza;
        int r02 = zzaef.$r8$clinit;
        zza = zzafh.zza;
    }

    public zzaed() {
    }

    public abstract zzagx zza(byte[] r1, int r2, int r3, zzafh r4);

    @Override // com.google.android.recaptcha.internal.zzahe
    public final /* synthetic */ Object zzb(byte[] r4) {
        zzagx r3 = zza(r4, 0, r4.length, zza);
        if (r3 != null) goto L5;
    L9:
        return r3;
    L5:
        if (r3.zzab() == true) goto L9;
        throw new zzahy((zzaec) r3).zza();
    }
}
