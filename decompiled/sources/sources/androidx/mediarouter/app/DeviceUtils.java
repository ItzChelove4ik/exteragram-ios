package androidx.mediarouter.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import androidx.mediarouter.R;

/* loaded from: classes4.dex */
final class DeviceUtils {
    private static final String FEATURE_AUTO = "android.hardware.type.automotive";
    private static final String FEATURE_TV_1 = "com.google.android.tv";
    private static final String FEATURE_TV_2 = "android.hardware.type.television";
    private static final String FEATURE_TV_3 = "android.software.leanback";
    private static final int SEVEN_INCH_TABLET_MINIMUM_SCREEN_WIDTH_DP = 600;
    private static Boolean sIsAuto;
    private static Boolean sIsFoldable;
    private static Boolean sIsPhone;
    private static Boolean sIsSevenInchTablet;
    private static Boolean sIsTablet;
    private static Boolean sIsTv;
    private static Boolean sIsWearable;

    private DeviceUtils() {
    }

    public static String getDialogChooserWifiWarningDescription(Context r1) {
        if (isPhone(r1) == true) goto L29;
        if (isFoldable(r1) == true) goto L29;
        if (isTablet(r1) == true) goto L27;
        if (isSevenInchTablet(r1) == true) goto L27;
        if (isTv(r1) == false) goto L17;
        return r1.getString(R.string.mr_chooser_wifi_warning_description_tv);
    L17:
        if (isWearable(r1) == false) goto L21;
        return r1.getString(R.string.mr_chooser_wifi_warning_description_watch);
    L21:
        if (isAuto(r1) == false) goto L25;
        return r1.getString(R.string.mr_chooser_wifi_warning_description_car);
    L25:
        return r1.getString(R.string.mr_chooser_wifi_warning_description_unknown);
    L27:
        return r1.getString(R.string.mr_chooser_wifi_warning_description_tablet);
    L29:
        return r1.getString(R.string.mr_chooser_wifi_warning_description_phone);
    }

    private static boolean isAuto(Context r0) {
        return isAuto(r0.getPackageManager());
    }

    private static boolean isAuto(PackageManager r2) {
        if (sIsAuto != null) goto L12;
        if (Build.VERSION.SDK_INT >= 26) goto L7;
    L9:
        boolean r22 = false;
    L10:
        sIsAuto = Boolean.valueOf(r22);
        goto L12
    L7:
        if (r2.hasSystemFeature(FEATURE_AUTO) == false) goto L9;
        r22 = true;
    L12:
        return sIsAuto.booleanValue();
    }

    private static boolean isFoldable(Context r2) {
        if (sIsFoldable != null) goto L13;
        SensorManager r22 = (SensorManager) r2.getSystemService("sensor");
        if (Build.VERSION.SDK_INT < 30) goto L10;
        if (r22 == null) goto L10;
        if (r22.getDefaultSensor(36) == null) goto L10;
        boolean r23 = true;
    L11:
        sIsFoldable = Boolean.valueOf(r23);
    L10:
        r23 = false;
    L13:
        return sIsFoldable.booleanValue();
    }

    private static boolean isPhone(Context r1) {
        if (sIsPhone != null) goto L16;
        if (isTablet(r1) == false) goto L7;
    L13:
        boolean r12 = false;
    L14:
        sIsPhone = Boolean.valueOf(r12);
        goto L16
    L7:
        if (isWearable(r1) == true) goto L13;
        if (isAuto(r1) == true) goto L13;
        if (isTv(r1) == true) goto L13;
        r12 = true;
    L16:
        return sIsPhone.booleanValue();
    }

    private static boolean isSevenInchTablet(Context r0) {
        return isSevenInchTablet(r0.getResources());
    }

    private static boolean isSevenInchTablet(Resources r3) {
        boolean r0 = false;
        if (r3 != null) goto L6;
        return false;
    L6:
        if (sIsSevenInchTablet != null) goto L14;
        Configuration r32 = r3.getConfiguration();
        if ((r32.screenLayout & 15) <= 3) goto L10;
    L12:
        sIsSevenInchTablet = Boolean.valueOf(r0);
        goto L14
    L10:
        if (r32.smallestScreenWidthDp < 600) goto L12;
        r0 = true;
    L14:
        return sIsSevenInchTablet.booleanValue();
    }

    private static boolean isTablet(Context r0) {
        return isTablet(r0.getResources());
    }

    private static boolean isTablet(Resources r3) {
        boolean r0 = false;
        if (r3 != null) goto L6;
        return false;
    L6:
        if (sIsTablet != null) goto L15;
        if ((r3.getConfiguration().screenLayout & 15) <= 3) goto L11;
    L12:
        r0 = true;
    L13:
        sIsTablet = Boolean.valueOf(r0);
        goto L15
    L11:
        if (isSevenInchTablet(r3) == false) goto L13;
    L15:
        return sIsTablet.booleanValue();
    }

    private static boolean isTv(Context r0) {
        return isTv(r0.getPackageManager());
    }

    private static boolean isTv(PackageManager r1) {
        if (sIsTv != null) goto L15;
        if (r1.hasSystemFeature(FEATURE_TV_1) == false) goto L7;
    L12:
        boolean r12 = true;
    L13:
        sIsTv = Boolean.valueOf(r12);
        goto L15
    L7:
        if (r1.hasSystemFeature(FEATURE_TV_2) == true) goto L12;
        if (r1.hasSystemFeature(FEATURE_TV_3) == true) goto L12;
        r12 = false;
    L15:
        return sIsTv.booleanValue();
    }

    private static boolean isWearable(Context r0) {
        return isWearable(r0.getPackageManager());
    }

    private static boolean isWearable(PackageManager r1) {
        if (sIsWearable != null) goto L6;
        sIsWearable = Boolean.valueOf(r1.hasSystemFeature("android.hardware.type.watch"));
    L6:
        return sIsWearable.booleanValue();
    }
}
