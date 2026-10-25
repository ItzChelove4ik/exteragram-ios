package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzhr {
    private final Context zza;
    private final GoogleApiAvailabilityLight zzb;

    public zzhr(Context r1, GoogleApiAvailabilityLight r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean zza() {
        if (zzd() != 3) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzb() {
        List<PackageInfo> r3 = this.zza.getPackageManager().getInstalledPackages(0);     // Catch: Exception -> L16
        if ((r3 instanceof Collection) == true) goto L6;
    L8:
        Iterator<T> r32 = r3.iterator();     // Catch: Exception -> L16
    L9:
        if (r32.hasNext() == false) goto L15;
        if (Intrinsics.areEqual(((PackageInfo) r32.next()).packageName, "com.android.vending") == false) goto L9;
        return true;
    L6:
        if (r3.isEmpty() == false) goto L8;
        return false;
    L15:
        return false;
    }

    public final String zzc() {
        Context r4 = this.zza;     // Catch: Exception -> L13
        PackageManager r1 = r4.getPackageManager();     // Catch: Exception -> L13
        String r42 = r4.getPackageName();     // Catch: Exception -> L13
        if (Build.VERSION.SDK_INT < 30) goto L9;
        String r43 = r1.getInstallSourceInfo(r42).getInitiatingPackageName();     // Catch: Exception -> L13
        if (r43 != null) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return r43;
    L9:
        String r44 = r1.getInstallerPackageName(r42);     // Catch: Exception -> L13
        if (r44 != null) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return r44;
    L13:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public final int zzd() {
        int r2 = this.zzb.isGooglePlayServicesAvailable(this.zza);
        if (r2 != 1) goto L5;
        return 4;
    L5:
        if (r2 != 3) goto L7;
        return 4;
    L7:
        if (r2 == 9) goto L12;
        return 3;
    L12:
        return 4;
    }
}
