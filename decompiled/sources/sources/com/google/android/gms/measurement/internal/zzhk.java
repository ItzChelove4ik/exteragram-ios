package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;

/* loaded from: classes5.dex */
public final class zzhk {
    final zzic zza;

    public zzhk(zzpg r1) {
        this.zza = r1.zzah();
    }

    public final boolean zza() {
        zzic r1 = this.zza;     // Catch: Exception -> L7
        PackageManagerWrapper r2 = Wrappers.packageManager(r1.zzaZ());     // Catch: Exception -> L7
        if (r2 != null) goto L11;
        r1.zzaW().zzk().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");     // Catch: Exception -> L7
        return false;
    L11:
        if (r2.getPackageInfo("com.android.vending", 128).versionCode < 80837300) goto L14;
        return true;
    L14:
        return false;
    L7:
        e = move-exception;
        this.zza.zzaW().zzk().zzb("Failed to retrieve Play Store version for Install Referrer", e);
        return false;
    }
}
