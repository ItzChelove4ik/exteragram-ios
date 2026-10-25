package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzhf {
    final String zza;
    final /* synthetic */ zzhh zzb;
    private final String zzc;
    private final String zzd;
    private final long zze;

    public /* synthetic */ zzhf(zzhh r1, String r2, long r3, byte[] r5) {
        Objects.requireNonNull(r1);
        this.zzb = r1;
        Preconditions.checkNotEmpty("health_monitor");
        if (r3 <= 0) goto L5;
        boolean r12 = true;
    L6:
        Preconditions.checkArgument(r12);
        this.zza = "health_monitor:start";
        this.zzc = "health_monitor:count";
        this.zzd = "health_monitor:value";
        this.zze = r3;
        return;
    L5:
        r12 = false;
        goto L6
    }

    private final void zzc() {
        zzhh r0 = this.zzb;
        r0.zzg();
        long r1 = r0.zzu.zzba().currentTimeMillis();
        SharedPreferences.Editor r02 = r0.zzd().edit();
        r02.remove(this.zzc);
        r02.remove(this.zzd);
        r02.putLong(this.zza, r1);
        r02.apply();
    }

    private final long zzd() {
        return this.zzb.zzd().getLong(this.zza, 0);
    }

    public final void zza(String r11, long r12) {
        zzhh r122 = this.zzb;
        r122.zzg();
        if (zzd() != 0) goto L5;
        zzc();
    L5:
        if (r11 != null) goto L7;
        r11 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        SharedPreferences r13 = r122.zzd();
        String r0 = this.zzc;
        long r4 = r13.getLong(r0, 0);
        if (r4 > 0) goto L11;
        SharedPreferences.Editor r123 = r122.zzd().edit();
        r123.putString(this.zzd, r11);
        r123.putLong(r0, 1);
        r123.apply();
        return;
    L11:
        long r6 = r122.zzu.zzk().zzf().nextLong() & LongCompanionObject.MAX_VALUE;
        long r42 = r4 + 1;
        long r8 = LongCompanionObject.MAX_VALUE / r42;
        SharedPreferences.Editor r124 = r122.zzd().edit();
        if (r6 >= r8) goto L14;
        r124.putString(this.zzd, r11);
    L14:
        r124.putLong(r0, r42);
        r124.apply();
    }

    public final Pair zzb() {
        zzhh r0 = this.zzb;
        r0.zzg();
        r0.zzg();
        long r1 = zzd();
        if (r1 != 0) goto L5;
        zzc();
        long r12 = 0;
    L6:
        long r5 = this.zze;
        if (r12 >= r5) goto L10;
        return null;
    L10:
        if (r12 <= (r5 + r5)) goto L13;
        zzc();
        return null;
    L13:
        String r13 = r0.zzd().getString(this.zzd, null);
        long r52 = r0.zzd().getLong(this.zzc, 0);
        zzc();
        if (r13 == null) goto L21;
        if (r52 <= 0) goto L21;
        return new Pair(r13, Long.valueOf(r52));
    L21:
        return zzhh.zza;
    L5:
        r12 = Math.abs(r1 - r0.zzu.zzba().currentTimeMillis());
        goto L6
    }
}
