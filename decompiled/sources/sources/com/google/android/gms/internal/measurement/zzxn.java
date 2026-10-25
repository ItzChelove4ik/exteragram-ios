package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
final class zzxn extends zzyq {
    private static final zzyf zza = null;
    private final AtomicLong zzb;

    static {
        zza = new zzxl();
    }

    public zzxn() {
        this.zzb = new AtomicLong(-1);
    }

    public static zzyq zza(zzzj r0, zzyd r1, long r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.zzd(zzxx.zzd));
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzyq
    public final void zzb() {
        AtomicLong r4 = this.zzb;
        r4.set(Math.max(-r4.get(), 0));
    }
}
