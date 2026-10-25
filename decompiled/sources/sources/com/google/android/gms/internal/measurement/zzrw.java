package com.google.android.gms.internal.measurement;

import android.content.Context;

/* loaded from: classes4.dex */
public final class zzrw {
    private final Context zza;
    private final zzsh zzb;

    public /* synthetic */ zzrw(Context r3, byte[] r4) {
        this.zzb = new zzsh();
        if (r3 == null) goto L5;
        boolean r0 = true;
    L6:
        zzsq.zza(r0, "Context cannot be null", new Object[0]);
        this.zza = r3.getApplicationContext();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public final zzrx zza() {
        return new zzrx(this, null);
    }

    public final /* synthetic */ Context zzb() {
        return this.zza;
    }

    public final /* synthetic */ zzsh zzc() {
        return this.zzb;
    }
}
