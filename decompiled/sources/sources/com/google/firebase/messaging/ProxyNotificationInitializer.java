package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
abstract class ProxyNotificationInitializer {
    public static /* synthetic */ void $r8$lambda$4f3YGOo_VogWWzQiXH4gueD5Xb8(Context r3, boolean r4, TaskCompletionSource r5) {
    L8:
        th = move-exception;
        r5.trySetResult(null);
        throw th;
    L4:
        if (allowedToUse(r3) == true) goto L11;
        Log.e("FirebaseMessaging", "error configuring notification delegate for package " + r3.getPackageName());     // Catch: Throwable -> L8
        r5.trySetResult(null);
        return;
    L11:
        ProxyNotificationPreferences.setProxyNotificationsInitialized(r3, true);     // Catch: Throwable -> L8
        NotificationManager r32 = (NotificationManager) r3.getSystemService(NotificationManager.class);     // Catch: Throwable -> L8
        if (r4 == false) goto L16;
        r32.setNotificationDelegate("com.google.android.gms");     // Catch: Throwable -> L8
    L18:
        r5.trySetResult(null);
        return;
    L16:
        if ("com.google.android.gms".equals(r32.getNotificationDelegate()) == false) goto L18;
        r32.setNotificationDelegate(null);     // Catch: Throwable -> L8
        goto L18
    }

    private static boolean allowedToUse(Context r1) {
        if (Binder.getCallingUid() != r1.getApplicationInfo().uid) goto L6;
        return true;
    L6:
        return false;
    }

    public static void initialize(Context r2) {
        if (ProxyNotificationPreferences.isProxyNotificationInitialized(r2) == false) goto L5;
        return;
    L5:
        setEnableProxyNotification(new CredentialManager$$ExternalSyntheticLambda0(), r2, shouldEnableProxyNotification(r2));
    }

    public static boolean isProxyNotificationEnabled(Context r4) {
        if (PlatformVersion.isAtLeastQ() == true) goto L9;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L7;
        Log.d("FirebaseMessaging", "Platform doesn't support proxying.");
    L7:
        return false;
    L9:
        if (allowedToUse(r4) == true) goto L13;
        Log.e("FirebaseMessaging", "error retrieving notification delegate for package " + r4.getPackageName());
        return false;
    L13:
        if ("com.google.android.gms".equals(((NotificationManager) r4.getSystemService(NotificationManager.class)).getNotificationDelegate()) == true) goto L15;
        return false;
    L15:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L20;
        Log.d("FirebaseMessaging", "GMS core is set for proxying");
        return true;
    L20:
        return true;
    }

    @TargetApi(29)
    public static Task<Void> setEnableProxyNotification(Executor r2, Context r3, boolean r4) {
        if (PlatformVersion.isAtLeastQ() == false) goto L5;
        TaskCompletionSource r0 = new TaskCompletionSource();
        r2.execute(new ProxyNotificationInitializer$$ExternalSyntheticLambda0(r3, r4, r0));
        return r0.getTask();
    L5:
        return Tasks.forResult(null);
    }

    private static boolean shouldEnableProxyNotification(Context r3) {
        Context r32 = r3.getApplicationContext();     // Catch: PackageManager.NameNotFoundException -> L15
        PackageManager r1 = r32.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L15
        if (r1 == null) goto L13;
        ApplicationInfo r33 = r1.getApplicationInfo(r32.getPackageName(), 128);     // Catch: PackageManager.NameNotFoundException -> L15
        if (r33 == null) goto L18;
        Bundle r12 = r33.metaData;     // Catch: PackageManager.NameNotFoundException -> L15
        if (r12 != null) goto L10;
        return true;
    L10:
        if (r12.containsKey("firebase_messaging_notification_delegation_enabled") == false) goto L20;
        return r33.metaData.getBoolean("firebase_messaging_notification_delegation_enabled");
    L20:
        return true;
    L18:
        return true;
    L13:
        return true;
    L21:
        return true;
    }
}
