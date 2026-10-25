package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final class zze implements Callable<SharedPreferences> {
    final /* synthetic */ Context zza;

    public zze(Context r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ SharedPreferences call() {
        return this.zza.getSharedPreferences("google_sdk_flags", 0);
    }
}
