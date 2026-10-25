package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.ObjectsCompat;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.File;
import java.util.concurrent.Executor;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline0;

@SuppressLint({"PrivateConstructorForUtilityClass"})
/* loaded from: classes.dex */
public abstract class ContextCompat {

    public static class Api24Impl {
        public static Context createDeviceProtectedStorageContext(Context r0) {
            return r0.createDeviceProtectedStorageContext();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api26Impl {
        public static Intent registerReceiver(Context r6, BroadcastReceiver r7, IntentFilter r8, String r9, Handler r10, int r11) {
            if ((r11 & 4) == 0) goto L8;
            if (r9 != null) goto L8;
            return r6.registerReceiver(r7, r8, ContextCompat.obtainAndCheckReceiverPermission(r6), r10);
        L8:
            return r6.registerReceiver(r7, r8, r9, r10, r11 & 1);
        }

        public static ComponentName startForegroundService(Context r0, Intent r1) {
            return r0.startForegroundService(r1);
        }
    }

    /* loaded from: classes3.dex */
    public static class Api28Impl {
        public static Executor getMainExecutor(Context r0) {
            return r0.getMainExecutor();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api30Impl {
        public static String getAttributionTag(Context r0) {
            return r0.getAttributionTag();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api33Impl {
        public static Intent registerReceiver(Context r0, BroadcastReceiver r1, IntentFilter r2, String r3, Handler r4, int r5) {
            return r0.registerReceiver(r1, r2, r3, r4, r5);
        }
    }

    public static int checkSelfPermission(Context r2, String r3) {
        ObjectsCompat.requireNonNull(r3, "permission must be non-null");
        if (Build.VERSION.SDK_INT >= 33) goto L13;
        if (TextUtils.equals("android.permission.POST_NOTIFICATIONS", r3) == false) goto L13;
        if (NotificationManagerCompat.from(r2).areNotificationsEnabled() == false) goto L10;
        return 0;
    L10:
        return -1;
    L13:
        return r2.checkPermission(r3, Process.myPid(), Process.myUid());
    }

    public static Context createDeviceProtectedStorageContext(Context r0) {
        return Api24Impl.createDeviceProtectedStorageContext(r0);
    }

    public static String getAttributionTag(Context r2) {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        return null;
    L5:
        return Api30Impl.getAttributionTag(r2);
    }

    public static int getColor(Context r0, int r1) {
        return r0.getColor(r1);
    }

    public static ColorStateList getColorStateList(Context r1, int r2) {
        return ResourcesCompat.getColorStateList(r1.getResources(), r2, r1.getTheme());
    }

    public static Drawable getDrawable(Context r0, int r1) {
        return r0.getDrawable(r1);
    }

    @Deprecated
    public static File[] getExternalCacheDirs(Context r0) {
        return r0.getExternalCacheDirs();
    }

    @Deprecated
    public static File[] getExternalFilesDirs(Context r0, String r1) {
        return r0.getExternalFilesDirs(r1);
    }

    public static Executor getMainExecutor(Context r2) {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return Api28Impl.getMainExecutor(r2);
    L7:
        return ExecutorCompat.create(new Handler(r2.getMainLooper()));
    }

    public static File getNoBackupFilesDir(Context r0) {
        return r0.getNoBackupFilesDir();
    }

    public static <T> T getSystemService(Context r0, Class<T> r1) {
        return (T) r0.getSystemService(r1);
    }

    public static String obtainAndCheckReceiverPermission(Context r4) {
        String r0 = r4.getApplicationContext().getPackageName() + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
        if (PermissionChecker.checkSelfPermission(r4, r0) != 0) goto L5;
        return r0;
    L5:
        if (Build.VERSION.SDK_INT < 29) goto L9;
        r0 = r4.getOpPackageName() + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
        if (PermissionChecker.checkSelfPermission(r4, r0) != 0) goto L9;
        return r0;
    L9:
        GlShader$$ExternalSyntheticBUOutline0.m("Permission ", r0, " is required by your application to receive broadcasts, please add it to your manifest");
        return null;
    }

    public static Intent registerReceiver(Context r6, BroadcastReceiver r7, IntentFilter r8, int r9) {
        return registerReceiver(r6, r7, r8, null, null, r9);
    }

    public static Intent registerReceiver(Context r6, BroadcastReceiver r7, IntentFilter r8, String r9, Handler r10, int r11) {
        int r0 = r11 & 1;
        if (r0 != 0) goto L5;
    L9:
        if (r0 == 0) goto L11;
        r11 = r11 | 2;
    L11:
        int r5 = r11;
        int r112 = r5 & 2;
        if (r112 == 0) goto L14;
    L18:
        if (r112 != 0) goto L20;
    L24:
        int r113 = Build.VERSION.SDK_INT;
        if (r113 < 33) goto L29;
        return Api33Impl.registerReceiver(r6, r7, r8, r9, r10, r5);
    L29:
        if (r113 < 26) goto L33;
        return Api26Impl.registerReceiver(r6, r7, r8, r9, r10, r5);
    L33:
        if ((r5 & 4) == 0) goto L38;
        if (r9 != null) goto L38;
        return r6.registerReceiver(r7, r8, obtainAndCheckReceiverPermission(r6), r10);
    L38:
        return r6.registerReceiver(r7, r8, r9, r10);
    L20:
        if ((r5 & 4) == 0) goto L24;
        f$$ExternalSyntheticBUOutline1.m("Cannot specify both RECEIVER_EXPORTED and RECEIVER_NOT_EXPORTED");
        return null;
    L14:
        if ((r5 & 4) != 0) goto L18;
        f$$ExternalSyntheticBUOutline1.m("One of either RECEIVER_EXPORTED or RECEIVER_NOT_EXPORTED is required");
        return null;
    L5:
        if ((r11 & 4) == 0) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Cannot specify both RECEIVER_VISIBLE_TO_INSTANT_APPS and RECEIVER_NOT_EXPORTED");
        return null;
    }

    public static boolean startActivities(Context r0, Intent[] r1, Bundle r2) {
        r0.startActivities(r1, r2);
        return true;
    }

    @Deprecated
    public static void startActivity(Context r0, Intent r1, Bundle r2) {
        r0.startActivity(r1, r2);
    }

    public static void startForegroundService(Context r2, Intent r3) {
        if (Build.VERSION.SDK_INT < 26) goto L6;
        Api26Impl.startForegroundService(r2, r3);
        return;
    L6:
        r2.startService(r3);
    }
}
