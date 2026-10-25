package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzhe {
    final /* synthetic */ zzhh zza;
    private final String zzb;
    private final long zzc;
    private boolean zzd;
    private long zze;

    public zzhe(zzhh r1, String r2, long r3) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        Preconditions.checkNotEmpty(r2);
        this.zzb = r2;
        this.zzc = r3;
    }

    public final long zza() {
        if (this.zzd == true) goto L6;
        this.zzd = true;
        zzhh r0 = this.zza;
        String r1 = this.zzb;
        long r2 = this.zzc;
        this.zze = r0.zzd().getLong(r1, r2);
    L6:
        return this.zze;
    }

    public final void zzb(long r3) {
        SharedPreferences.Editor r0 = this.zza.zzd().edit();
        r0.putLong(this.zzb, r3);
        r0.apply();
        this.zze = r3;
    }
}
