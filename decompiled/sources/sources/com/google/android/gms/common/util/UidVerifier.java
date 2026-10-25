package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.wrappers.Wrappers;

/* loaded from: classes4.dex */
public abstract class UidVerifier {
    public static boolean isGooglePlayServicesUid(Context r2, int r3) {
        if (uidHasPackageName(r2, r3, "com.google.android.gms") == true) goto L14;
        return false;
    L14:
        PackageInfo r32 = r2.getPackageManager().getPackageInfo("com.google.android.gms", 64);     // Catch: PackageManager.NameNotFoundException -> L9
        return GoogleSignatureVerifier.getInstance(r2).isGooglePublicSignedPackage(r32);
    L10:
        if (Log.isLoggable("UidVerifier", 3) == false) goto L16;
        Log.d("UidVerifier", "Package manager can't find google play services package, defaulting to false");
        return false;
    L16:
        return false;
    }

    public static boolean uidHasPackageName(Context r0, int r1, String r2) {
        return Wrappers.packageManager(r0).zza(r1, r2);
    }
}
