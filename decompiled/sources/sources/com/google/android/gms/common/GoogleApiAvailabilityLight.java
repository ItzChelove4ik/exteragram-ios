package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.app.PendingIntentCompat;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;

/* loaded from: classes.dex */
public class GoogleApiAvailabilityLight {
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    private static final GoogleApiAvailabilityLight zza = null;

    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        zza = new GoogleApiAvailabilityLight();
    }

    public GoogleApiAvailabilityLight() {
    }

    public static GoogleApiAvailabilityLight getInstance() {
        return zza;
    }

    public void cancelAvailabilityErrorNotifications(Context r1) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(r1);
    }

    public int getApkVersion(Context r1) {
        return GooglePlayServicesUtilLight.getApkVersion(r1);
    }

    @Deprecated
    public Intent getErrorResolutionIntent(int r2) {
        return getErrorResolutionIntent(null, r2, null);
    }

    public Intent getErrorResolutionIntent(Context r3, int r4, String r5) {
        if (r4 != 1) goto L5;
    L11:
        if (r3 != null) goto L13;
    L17:
        StringBuilder r2 = new StringBuilder("gcore_");
        r2.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        r2.append("-");
        if (TextUtils.isEmpty(r5) == true) goto L20;
        r2.append(r5);
    L20:
        r2.append("-");
        if (r3 == null) goto L23;
        r2.append(r3.getPackageName());
    L23:
        r2.append("-");
        if (r3 != null) goto L32;
    L26:
        String r22 = r2.toString();
        Intent r32 = new Intent("android.intent.action.VIEW");
        Uri.Builder r42 = Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.gms");
        if (TextUtils.isEmpty(r22) == true) goto L29;
        r42.appendQueryParameter("pcampaignid", r22);
    L29:
        r32.setData(r42.build());
        r32.setPackage("com.android.vending");
        r32.addFlags(524288);
        return r32;
    L32:
        r2.append(Wrappers.packageManager(r3).getPackageInfo(r3.getPackageName(), 0).versionCode);     // Catch: PackageManager.NameNotFoundException -> L31
        goto L26
    L13:
        if (DeviceProperties.isWearableWithoutPlayStore(r3) == false) goto L17;
        Intent r23 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        r23.setPackage("com.google.android.wearable.app");
        return r23;
    L5:
        if (r4 == 2) goto L11;
        if (r4 == 3) goto L9;
        return null;
    L9:
        Uri r24 = Uri.fromParts("package", "com.google.android.gms", null);
        Intent r33 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        r33.setData(r24);
        return r33;
    }

    public PendingIntent getErrorResolutionPendingIntent(Context r2, int r3, int r4) {
        return getErrorResolutionPendingIntent(r2, r3, r4, null);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context r1, int r2, int r3, String r4) {
        Intent r0 = getErrorResolutionIntent(r1, r2, r4);
        if (r0 != null) goto L7;
        return null;
    L7:
        return PendingIntentCompat.getActivity(r1, r3, r0, 134217728, false);
    }

    public String getErrorString(int r1) {
        return GooglePlayServicesUtilLight.getErrorString(r1);
    }

    public int isGooglePlayServicesAvailable(Context r2) {
        return isGooglePlayServicesAvailable(r2, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public int isGooglePlayServicesAvailable(Context r1, int r2) {
        int r0 = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(r1, r2);
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(r1, r0) == false) goto L6;
        return 18;
    L6:
        return r0;
    }

    public boolean isPlayServicesPossiblyUpdating(Context r1, int r2) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(r1, r2);
    }

    public boolean isUninstalledAppPossiblyUpdating(Context r1, String r2) {
        return GooglePlayServicesUtilLight.zza(r1, r2);
    }

    public boolean isUserResolvableError(int r1) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(r1);
    }
}
