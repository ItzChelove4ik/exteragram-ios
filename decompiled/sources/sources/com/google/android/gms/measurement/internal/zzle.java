package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;

/* loaded from: classes5.dex */
final /* synthetic */ class zzle implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final /* synthetic */ zzlj zza;

    public /* synthetic */ zzle(zzlj r1) {
        this.zza = r1;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final /* synthetic */ void onSharedPreferenceChanged(SharedPreferences r1, String r2) {
        this.zza.zzaf(r1, r2);
    }
}
