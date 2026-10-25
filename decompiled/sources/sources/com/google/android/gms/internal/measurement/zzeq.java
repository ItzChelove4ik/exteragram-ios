package com.google.android.gms.internal.measurement;

import android.os.Bundle;

/* loaded from: classes.dex */
final class zzeq extends zzcx {
    private final com.google.android.gms.measurement.internal.zzjq zza;

    public zzeq(com.google.android.gms.measurement.internal.zzjq r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzcy
    public final void zze(String r1, String r2, Bundle r3, long r4) {
        this.zza.onEvent(r1, r2, r3, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzcy
    public final int zzf() {
        return System.identityHashCode(this.zza);
    }
}
