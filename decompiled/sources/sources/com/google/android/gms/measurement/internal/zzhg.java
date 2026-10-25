package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzhg {
    final /* synthetic */ zzhh zza;
    private final String zzb;
    private boolean zzc;
    private String zzd;

    public zzhg(zzhh r1, String r2, String r3) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        Preconditions.checkNotEmpty(r2);
        this.zzb = r2;
    }

    public final String zza() {
        if (this.zzc == true) goto L6;
        this.zzc = true;
        zzhh r0 = this.zza;
        String r1 = this.zzb;
        this.zzd = r0.zzd().getString(r1, null);
    L6:
        return this.zzd;
    }

    public final void zzb(String r3) {
        SharedPreferences.Editor r0 = this.zza.zzd().edit();
        r0.putString(this.zzb, r3);
        r0.apply();
        this.zzd = r3;
    }
}
