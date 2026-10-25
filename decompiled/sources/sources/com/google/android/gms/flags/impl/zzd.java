package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final class zzd implements Callable<String> {
    final /* synthetic */ SharedPreferences zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;

    public zzd(SharedPreferences r1, String r2, String r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ String call() {
        return this.zza.getString(this.zzb, this.zzc);
    }
}
