package com.google.android.gms.common;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzae;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public abstract class GooglePlayServicesUtilLight {

    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;

    @Deprecated
    static final AtomicBoolean sCanceledAvailabilityNotification = null;
    public static boolean zza = false;
    public static boolean zzb = false;
    private static final AtomicBoolean zzc = null;

    static {
        sCanceledAvailabilityNotification = new AtomicBoolean();
        zzc = new AtomicBoolean();
    }

    @Deprecated
    public static void cancelAvailabilityErrorNotifications(Context r2) {
        if (sCanceledAvailabilityNotification.getAndSet(true) == true) goto L15;
        NotificationManager r22 = (NotificationManager) r2.getSystemService("notification");     // Catch: SecurityException -> L9
        if (r22 == null) goto L14;
        r22.cancel(10436);     // Catch: SecurityException -> L9
        return;
    L14:
        return;
    L9:
        e = move-exception;
        Log.d("GooglePlayServicesUtil", "Suppressing Security Exception %s in cancelAvailabilityErrorNotifications.", e);
        return;
    }

    @Deprecated
    public static int getApkVersion(Context r2) {
        return r2.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
    L6:
        Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
        return 0;
    }

    @Deprecated
    public static String getErrorString(int r0) {
        return ConnectionResult.zza(r0);
    }

    public static Context getRemoteContext(Context r2) {
        return r2.createPackageContext("com.google.android.gms", 3);
    L4:
        return null;
    }

    public static Resources getRemoteResource(Context r1) {
        return r1.getPackageManager().getResourcesForApplication("com.google.android.gms");
    L4:
        return null;
    }

    public static boolean honorsDebugCertificates(Context r5) {
        if (zzb == true) goto L26;
        PackageManagerWrapper r0 = Wrappers.packageManager(r5);     // Catch: Throwable -> L15 PackageManager.NameNotFoundException -> L17
        if (Build.VERSION.SDK_INT < 28) goto L7;
        int r3 = 134217792;
    L8:
        PackageInfo r02 = r0.getPackageInfo("com.google.android.gms", r3);     // Catch: Throwable -> L15 PackageManager.NameNotFoundException -> L17
        GoogleSignatureVerifier.getInstance(r5);     // Catch: Throwable -> L15 PackageManager.NameNotFoundException -> L17
        if (r02 != null) goto L11;
    L19:
        zza = false;     // Catch: Throwable -> L15 PackageManager.NameNotFoundException -> L17
    L20:
        zzb = true;
        goto L26
    L11:
        if (GoogleSignatureVerifier.zza(r02, false) == true) goto L19;
        if (GoogleSignatureVerifier.zza(r02, true) == false) goto L19;
        zza = true;     // Catch: Throwable -> L15 PackageManager.NameNotFoundException -> L17
        goto L20
    L7:
        r3 = 64;
    L17:
        e = move-exception;
        Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);     // Catch: Throwable -> L15
        zzb = true;
    L26:
        if (zza == false) goto L28;
    L31:
        return true;
    L28:
        if (DeviceProperties.isUserBuild() == false) goto L31;
        return false;
    L15:
        th = move-exception;
        zzb = true;
        throw th;
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context r1) {
        return isGooglePlayServicesAvailable(r1, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context r11, int r12) {
        r11.getResources().getString(R$string.common_google_play_services_unknown_issue);     // Catch: Throwable -> L5
    L7:
        if ("com.google.android.gms".equals(r11.getPackageName()) == true) goto L21;
        if (zzc.get() == true) goto L21;
        int r1 = zzae.zzb(r11);
        if (r1 == 0) goto L19;
        if (r1 == GOOGLE_PLAY_SERVICES_VERSION_CODE) goto L21;
        throw new GooglePlayServicesIncorrectManifestValueException(r1);
    L19:
        throw new GooglePlayServicesMissingManifestValueException();
    L21:
        if (DeviceProperties.isWearableWithoutPlayStore(r11) == false) goto L23;
    L25:
        boolean r13 = false;
    L26:
        if (r12 < 0) goto L28;
        boolean r5 = true;
    L29:
        Preconditions.checkArgument(r5);
        String r52 = r11.getPackageName();
        PackageManager r6 = r11.getPackageManager();
        if (r13 == true) goto L74;
        PackageInfo r9 = null;
    L72:
    L70:
        Log.w("GooglePlayServicesUtil", String.valueOf(r52).concat(" requires Google Play services, but they are missing."));
        return 1;
    L41:
        if (Build.VERSION.SDK_INT < 28) goto L43;
        int r7 = 134217792;
    L44:
        PackageInfo r72 = r6.getPackageInfo("com.google.android.gms", r7);     // Catch: PackageManager.NameNotFoundException -> L70
        GoogleSignatureVerifier.getInstance(r11);
        if (GoogleSignatureVerifier.zza(r72, true) == true) goto L48;
        Log.w("GooglePlayServicesUtil", String.valueOf(r52).concat(" requires Google Play services, but their signature is invalid."));
    L80:
        return 9;
    L48:
        if (r13 == false) goto L52;
        Preconditions.checkNotNull(r9);
        if (GoogleSignatureVerifier.zza(r9, true) == true) goto L52;
        Log.w("GooglePlayServicesUtil", String.valueOf(r52).concat(" requires Google Play Store, but its signature is invalid."));
    L52:
        if (r13 == false) goto L58;
        if (r9 == null) goto L58;
        if (r9.signatures[0].equals(r72.signatures[0]) == true) goto L58;
        Log.w("GooglePlayServicesUtil", String.valueOf(r52).concat(" requires Google Play Store, but its signature doesn't match that of Google Play services."));
    L58:
        if (com.google.android.gms.common.util.zzb.zza(r72.versionCode) >= com.google.android.gms.common.util.zzb.zza(r12)) goto L60;
        int r112 = r72.versionCode;
        int r14 = String.valueOf(r52).length();
        StringBuilder r3 = new StringBuilder((((r14 + 49) + String.valueOf(r12).length()) + 11) + String.valueOf(r112).length());
        r3.append("Google Play services out of date for ");
        r3.append(r52);
        r3.append(".  Requires ");
        r3.append(r12);
        r3.append(" but found ");
        r3.append(r112);
        Log.w("GooglePlayServicesUtil", r3.toString());
        return 2;
    L60:
        ApplicationInfo r113 = r72.applicationInfo;
        if (r113 != null) goto L67;
        r113 = r6.getApplicationInfo("com.google.android.gms", 0);     // Catch: PackageManager.NameNotFoundException -> L64
    L64:
        e = move-exception;
        Log.wtf("GooglePlayServicesUtil", String.valueOf(r52).concat(" requires Google Play services, but they're missing when getting application info."), e);
        return 1;
    L67:
        if (r113.enabled == true) goto L69;
        return 3;
    L69:
        return 0;
    L43:
        r7 = 64;
        goto L44
    L74:
    L37:
        Log.w("GooglePlayServicesUtil", String.valueOf(r52).concat(" requires the Google Play Store, but it is missing."));
        goto L80
    L32:
        if (Build.VERSION.SDK_INT < 28) goto L34;
        int r92 = 134225984;
    L35:
        r9 = r6.getPackageInfo("com.android.vending", r92);     // Catch: PackageManager.NameNotFoundException -> L37
        goto L72
    L34:
        r92 = 8256;
        goto L35
    L28:
        r5 = false;
        goto L29
    L23:
        if (DeviceProperties.zzd(r11) == true) goto L25;
        r13 = true;
    L5:
        Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        goto L7
    }

    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context r2, int r3) {
        if (r3 != 18) goto L5;
        return true;
    L5:
        if (r3 == 1) goto L7;
        return false;
    L7:
        return zza(r2, "com.google.android.gms");
    }

    public static boolean isRestrictedUserProfile(Context r2) {
        Object r0 = r2.getSystemService("user");
        Preconditions.checkNotNull(r0);
        Bundle r22 = ((UserManager) r0).getApplicationRestrictions(r2.getPackageName());
        if (r22 != null) goto L5;
        return false;
    L5:
        if ("true".equals(r22.getString("restricted_profile")) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Deprecated
    public static boolean isUserRecoverableError(int r2) {
        if (r2 != 1) goto L5;
    L12:
        return true;
    L5:
        if (r2 == 2) goto L12;
        if (r2 == 3) goto L12;
        if (r2 == 9) goto L12;
        return false;
    }

    @Deprecated
    public static boolean uidHasPackageName(Context r0, int r1, String r2) {
        return UidVerifier.uidHasPackageName(r0, r1, r2);
    }

    public static boolean zza(Context r5, String r6) {
        boolean r0 = r6.equals("com.google.android.gms");
        Iterator<PackageInstaller.SessionInfo> r2 = r5.getPackageManager().getPackageInstaller().getAllSessions().iterator();
    L6:
        if (r2.hasNext() == false) goto L10;
        if (r6.equals(r2.next().getAppPackageName()) == false) goto L6;
        return true;
    L10:
        ApplicationInfo r62 = r5.getPackageManager().getApplicationInfo(r6, 8192);
        if (r0 == false) goto L15;
        return r62.enabled;
    L15:
        if (r62.enabled == false) goto L19;
        if (isRestrictedUserProfile(r5) == true) goto L19;
        return true;
    L19:
        return false;
    }
}
