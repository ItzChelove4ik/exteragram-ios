package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.apps.common.proguard.SideEffectFree;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

/* loaded from: classes.dex */
public abstract class DeviceProperties {
    private static Boolean zzf;
    private static Boolean zzg;
    private static Boolean zzj;
    private static Boolean zzl;

    @SideEffectFree
    public static boolean isAuto(Context r0) {
        return zze(r0.getPackageManager());
    }

    public static boolean isUserBuild() {
        int r0 = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        return "user".equals(Build.TYPE);
    }

    @SideEffectFree
    public static boolean isWearable(Context r0) {
        return zzb(r0.getPackageManager());
    }

    @TargetApi(26)
    public static boolean isWearableWithoutPlayStore(Context r1) {
        if (isWearable(r1) == false) goto L7;
        if (PlatformVersion.isAtLeastN() == true) goto L7;
        return true;
    L7:
        if (zzc(r1) == true) goto L9;
        return false;
    L9:
        if (PlatformVersion.isAtLeastO() == true) goto L11;
        return true;
    L11:
        if (PlatformVersion.isAtLeastR() == false) goto L18;
        return true;
    L18:
        return false;
    }

    @SideEffectFree
    public static boolean zzb(PackageManager r1) {
        if (zzf != null) goto L6;
        zzf = Boolean.valueOf(r1.hasSystemFeature("android.hardware.type.watch"));
    L6:
        return zzf.booleanValue();
    }

    public static boolean zzc(Context r1) {
        if (zzg != null) goto L6;
        zzg = Boolean.valueOf(r1.getPackageManager().hasSystemFeature("cn.google"));
    L6:
        return zzg.booleanValue();
    }

    public static boolean zzd(Context r1) {
        if (zzj != null) goto L10;
        if (PlatformVersion.isAtLeastO() == false) goto L7;
        boolean r12 = r1.getPackageManager().hasSystemFeature("android.hardware.type.embedded");
    L8:
        zzj = Boolean.valueOf(r12);
        goto L10
    L7:
        r12 = r1.getPackageManager().hasSystemFeature("android.hardware.type.iot");
    L10:
        return zzj.booleanValue();
    }

    @SideEffectFree
    public static boolean zze(PackageManager r2) {
        if (zzl != null) goto L11;
        boolean r1 = false;
        if (PlatformVersion.isAtLeastO() == true) goto L7;
    L9:
        zzl = Boolean.valueOf(r1);
        goto L11
    L7:
        if (r2.hasSystemFeature("android.hardware.type.automotive") == false) goto L9;
        r1 = true;
    L11:
        return zzl.booleanValue();
    }
}
