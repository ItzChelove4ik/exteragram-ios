package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import com.google.android.gms.common.util.PlatformVersion;

/* loaded from: classes.dex */
abstract class ProxyNotificationPreferences {
    public static /* synthetic */ void $r8$lambda$ixnUbg_8yLG2a9GdSQrcP1s4cZg(Context r0, boolean r1, Void r2) {
        setProxyRetentionPreferences(r0, r1);
    }

    private static SharedPreferences getPreference(Context r2) {
        Context r0 = r2.getApplicationContext();
        if (r0 == null) goto L7;
        r2 = r0;
    L7:
        return r2.getSharedPreferences("com.google.firebase.messaging", 0);
    }

    public static boolean isProxyNotificationInitialized(Context r2) {
        return getPreference(r2).getBoolean("proxy_notification_initialized", false);
    }

    public static boolean isProxyNotificationRetentionSet(SharedPreferences r3, boolean r4) {
        if (r3.contains("proxy_retention") == true) goto L5;
    L8:
        return false;
    L5:
        if (r3.getBoolean("proxy_retention", false) != r4) goto L8;
        return true;
    }

    public static void setProxyNotificationsInitialized(Context r1, boolean r2) {
        SharedPreferences.Editor r12 = getPreference(r1).edit();
        r12.putBoolean("proxy_notification_initialized", r2);
        r12.apply();
    }

    public static void setProxyRetention(Context r2, GmsRpc r3, boolean r4) {
        if (PlatformVersion.isAtLeastQ() == true) goto L6;
        return;
    L6:
        if (isProxyNotificationRetentionSet(getPreference(r2), r4) == true) goto L9;
        r3.setRetainProxiedNotifications(r4).addOnSuccessListener(new CredentialManager$$ExternalSyntheticLambda0(), new ProxyNotificationPreferences$$ExternalSyntheticLambda0(r2, r4));
        return;
    }

    public static void setProxyRetentionPreferences(Context r1, boolean r2) {
        SharedPreferences.Editor r12 = getPreference(r1).edit();
        r12.putBoolean("proxy_retention", r2);
        r12.apply();
    }
}
