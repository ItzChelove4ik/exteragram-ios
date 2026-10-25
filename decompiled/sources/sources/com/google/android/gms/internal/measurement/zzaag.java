package com.google.android.gms.internal.measurement;

import android.util.Log;

/* loaded from: classes4.dex */
public abstract class zzaag extends zzzf {
    private final String zza;

    public zzaag(String r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzzf
    public String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzzf
    public void zzd(RuntimeException r1, zzzd r2) {
        Log.e("AbstractAndroidBackend", "Internal logging error", r1);
    }
}
