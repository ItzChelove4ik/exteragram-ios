package androidx.mediarouter.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaRouter2;
import android.os.Build;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class SystemOutputSwitcherDialogController {
    private static final String OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_R = "com.android.settings.panel.action.MEDIA_OUTPUT";
    private static final String OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_S = "com.android.systemui.action.LAUNCH_MEDIA_OUTPUT_DIALOG";
    private static final String OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_R = "com.android.settings.panel.extra.PACKAGE_NAME";
    private static final String OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_S = "package_name";
    private static final String PACKAGE_NAME_SYSTEM_UI = "com.android.systemui";

    public static final class Api23Impl {
        private Api23Impl() {
        }

        public static boolean isSuitableDeviceAlreadyConnectedAsAudioOutput(Context r5) {
            AudioDeviceInfo[] r52 = ((AudioManager) r5.getSystemService(AudioManager.class)).getDevices(2);
            int r0 = r52.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L29;
            int r3 = r52[r2].getType();
            if (r3 == 3) goto L27;
            if (r3 == 4) goto L42;
            if (r3 == 5) goto L43;
            if (r3 == 6) goto L44;
            if (r3 == 8) goto L45;
            if (r3 == 11) goto L46;
            if (r3 == 30) goto L47;
            if (r3 == 22) goto L48;
            if (r3 == 23) goto L49;
            if (r3 == 26) goto L50;
            if (r3 == 27) goto L51;
            r2 = r2 + 1;
            goto L3
        L51:
            return true;
        L50:
            return true;
        L49:
            return true;
        L48:
            return true;
        L47:
            return true;
        L46:
            return true;
        L45:
            return true;
        L44:
            return true;
        L43:
            return true;
        L42:
            return true;
        L27:
            return true;
        L29:
            return false;
        }
    }

    public static class Api30Impl {
        private Api30Impl() {
        }

        public static MediaRouter2 getInstance(Context r0) {
            return MediaRouter2.getInstance(r0);
        }
    }

    public static class Api34Impl {
        private Api34Impl() {
        }

        public static boolean showSystemOutputSwitcher(MediaRouter2 r0) {
            return r0.showSystemOutputSwitcher();
        }
    }

    private SystemOutputSwitcherDialogController() {
    }

    private static boolean isRunningOnWear(Context r1) {
        return r1.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    private static boolean isSuitableDeviceAlreadyConnectedAsAudioOutput(Context r0) {
        return Api23Impl.isSuitableDeviceAlreadyConnectedAsAudioOutput(r0);
    }

    private static boolean showBluetoothSettingsFragment(Context r6) {
        Intent r0 = new Intent("android.settings.BLUETOOTH_SETTINGS").addFlags(268468224).putExtra("EXTRA_CONNECTION_ONLY", true).putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 1).putExtra("EXTRA_CLOSE_ON_CONNECT", !isSuitableDeviceAlreadyConnectedAsAudioOutput(r6));
        Iterator<ResolveInfo> r1 = r6.getPackageManager().queryIntentActivities(r0, 0).iterator();
    L4:
        if (r1.hasNext() == false) goto L14;
        ActivityInfo r4 = r1.next().activityInfo;
        if (r4 == null) goto L4;
        ApplicationInfo r42 = r4.applicationInfo;
        if (r42 == null) goto L4;
        if ((r42.flags & 129) == 0) goto L4;
        r0.setPackage(r42.packageName);
        r6.startActivity(r0);
        return true;
    L14:
        return false;
    }

    public static boolean showDialog(Context r4) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 34) goto L6;
        boolean r02 = showDialogForAndroidUAndAbove(r4);
    L16:
        if (r02 == false) goto L19;
        return true;
    L19:
        if (isRunningOnWear(r4) == true) goto L21;
    L23:
        return false;
    L21:
        if (showBluetoothSettingsFragment(r4) == false) goto L23;
        return true;
    L6:
        if (r0 < 31) goto L13;
        if (showDialogForAndroidSAndT(r4) == false) goto L10;
    L11:
        r02 = true;
        goto L16
    L10:
        if (showDialogForAndroidR(r4) == true) goto L11;
    L15:
        r02 = false;
        goto L16
    L13:
        if (r0 != 30) goto L15;
        r02 = showDialogForAndroidR(r4);
        goto L16
    }

    private static boolean showDialogForAndroidR(Context r5) {
        Intent r0 = new Intent().addFlags(268435456).setAction(OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_R).putExtra(OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_R, r5.getPackageName());
        Iterator<ResolveInfo> r1 = r5.getPackageManager().queryIntentActivities(r0, 0).iterator();
    L4:
        if (r1.hasNext() == false) goto L14;
        ActivityInfo r3 = r1.next().activityInfo;
        if (r3 == null) goto L4;
        ApplicationInfo r32 = r3.applicationInfo;
        if (r32 == null) goto L4;
        if ((r32.flags & 129) == 0) goto L4;
        r0.setPackage(r32.packageName);
        r5.startActivity(r0);
        return true;
    L14:
        return false;
    }

    private static boolean showDialogForAndroidSAndT(Context r4) {
        Intent r0 = new Intent().setAction(OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_S).setPackage(PACKAGE_NAME_SYSTEM_UI).putExtra(OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_S, r4.getPackageName());
        Iterator<ResolveInfo> r1 = r4.getPackageManager().queryBroadcastReceivers(r0, 0).iterator();
    L4:
        if (r1.hasNext() == false) goto L14;
        ActivityInfo r3 = r1.next().activityInfo;
        if (r3 == null) goto L4;
        ApplicationInfo r32 = r3.applicationInfo;
        if (r32 == null) goto L4;
        if ((r32.flags & 129) == 0) goto L4;
        r4.sendBroadcast(r0);
        return true;
    L14:
        return false;
    }

    private static boolean showDialogForAndroidUAndAbove(Context r2) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 30) goto L8;
        MediaRouter2 r22 = Api30Impl.getInstance(r2);
        if (r0 >= 34) goto L7;
        return false;
    L7:
        return Api34Impl.showSystemOutputSwitcher(r22);
    L8:
        return false;
    }
}
