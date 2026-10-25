package com.google.android.recaptcha.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public final class zzbi {
    private final AtomicReference zza;

    public zzbi() {
        this.zza = new AtomicReference(null);
    }

    public final void zzb() {
        this.zza.set(null);
    }

    public final boolean zzc() {
        if (this.zza.get() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzd() {
        zzbq r0 = (zzbq) this.zza.get();
        if (r0 == null) goto L6;
        r0.zzg();
        return;
    }
}
