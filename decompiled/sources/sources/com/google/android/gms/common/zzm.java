package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
abstract class zzm extends zzj {
    private static final WeakReference zzb = null;
    private WeakReference zza;

    static {
        zzb = new WeakReference(null);
    }

    public zzm(byte[] r1) {
        super(r1);
        this.zza = zzb;
    }

    public abstract byte[] zzb();

    @Override // com.google.android.gms.common.zzj
    public final byte[] zzc() {
        monitor-enter(this);
        byte[] r0 = (byte[]) this.zza.get();     // Catch: Throwable -> L6
        if (r0 != null) goto L8;
        r0 = zzb();     // Catch: Throwable -> L6
        this.zza = new WeakReference(r0);     // Catch: Throwable -> L6
    L8:
        monitor-exit(this);     // Catch: Throwable -> L6
        return r0;
    L6:
        th = move-exception;
        throw th;
    }
}
