package com.google.firebase.messaging;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class NotificationParams {
    private final Bundle data;

    public NotificationParams(Bundle r2) {
        if (r2 == null) goto L6;
        this.data = new Bundle(r2);
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("data");
        throw null;
    }

    private static int getLightColor(String r1) {
        int r12 = Color.parseColor(r1);
        if (r12 == (-16777216)) goto L5;
        return r12;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Transparent color is invalid");
        return 0;
    }

    private static boolean isAnalyticsKey(String r1) {
        if (r1.startsWith("google.c.a.") == false) goto L5;
        return true;
    L5:
        if (r1.equals("from") == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isNotification(Bundle r3) {
        if ("1".equals(r3.getString("gcm.n.e")) == false) goto L5;
        return true;
    L5:
        if ("1".equals(r3.getString(keyWithOldPrefix("gcm.n.e"))) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private static boolean isReservedKey(String r1) {
        if (r1.startsWith("google.c.") == false) goto L5;
        return true;
    L5:
        if (r1.startsWith("gcm.n.") == false) goto L7;
        return true;
    L7:
        if (r1.startsWith("gcm.notification.") == true) goto L14;
        return false;
    L14:
        return true;
    }

    private static String keyWithOldPrefix(String r2) {
        if (r2.startsWith("gcm.n.") == true) goto L6;
        return r2;
    L6:
        return r2.replace("gcm.n.", "gcm.notification.");
    }

    private String normalizePrefix(String r2) {
        if (this.data.containsKey(r2) == false) goto L5;
    L9:
        return r2;
    L5:
        if (r2.startsWith("gcm.n.") == false) goto L9;
        String r0 = keyWithOldPrefix(r2);
        if (this.data.containsKey(r0) == false) goto L9;
        return r0;
    }

    private static String userFriendlyKey(String r1) {
        if (r1.startsWith("gcm.n.") == true) goto L5;
        return r1;
    L5:
        return r1.substring(6);
    }

    public boolean getBoolean(String r1) {
        String r0 = getString(r1);
        if ("1".equals(r0) == false) goto L5;
        return true;
    L5:
        if (Boolean.parseBoolean(r0) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public Integer getInteger(String r3) {
        String r2 = getString(r3);
        if (TextUtils.isEmpty(r2) == true) goto L11;
        return Integer.valueOf(Integer.parseInt(r2));
    L6:
        Log.w("NotificationParams", "Couldn't parse value of " + userFriendlyKey(r3) + "(" + r2 + ") into an int");
        return null;
    L11:
        return null;
    }

    public JSONArray getJSONArray(String r3) {
        String r2 = getString(r3);
        if (TextUtils.isEmpty(r2) == true) goto L11;
        return new JSONArray(r2);
    L6:
        Log.w("NotificationParams", "Malformed JSON for key " + userFriendlyKey(r3) + ": " + r2 + ", falling back to default");
        return null;
    L11:
        return null;
    }

    public int[] getLightSettings() {
        JSONArray r7 = getJSONArray("gcm.n.light_settings");
        if (r7 != null) goto L5;
        return null;
    L5:
        int[] r5 = new int[3];
    L10:
        e = move-exception;
        Log.w("NotificationParams", "LightSettings is invalid: " + r7 + ". " + e.getMessage() + ". Skipping setting LightSettings");
    L16:
        return null;
    L15:
        Log.w("NotificationParams", "LightSettings is invalid: " + r7 + ". Skipping setting LightSettings");
        goto L16
    L7:
        if (r7.length() != 3) goto L13;
        r5[0] = getLightColor(r7.optString(0));     // Catch: IllegalArgumentException -> L10 JSONException -> L15
        r5[1] = r7.optInt(1);     // Catch: IllegalArgumentException -> L10 JSONException -> L15
        r5[2] = r7.optInt(2);     // Catch: IllegalArgumentException -> L10 JSONException -> L15
        return r5;
    L13:
        throw new JSONException("lightSettings don't have all three fields");     // Catch: IllegalArgumentException -> L10 JSONException -> L15
    }

    public Uri getLink() {
        String r0 = getString("gcm.n.link_android");
        if (TextUtils.isEmpty(r0) == false) goto L6;
        r0 = getString("gcm.n.link");
    L6:
        if (TextUtils.isEmpty(r0) == false) goto L8;
        return null;
    L8:
        return Uri.parse(r0);
    }

    public Object[] getLocalizationArgsForKey(String r4) {
        JSONArray r3 = getJSONArray(r4 + "_loc_args");
        if (r3 != null) goto L6;
        return null;
    L6:
        int r42 = r3.length();
        String[] r0 = new String[r42];
        int r1 = 0;
    L7:
        if (r1 >= r42) goto L9;
        r0[r1] = r3.optString(r1);
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }

    public String getLocalizationResourceForKey(String r2) {
        return getString(r2 + "_loc_key");
    }

    public String getLocalizedString(Resources r5, String r6, String r7) {
        String r0 = getLocalizationResourceForKey(r7);
        if (TextUtils.isEmpty(r0) == false) goto L5;
        return null;
    L5:
        int r62 = r5.getIdentifier(r0, "string", r6);
        if (r62 != 0) goto L9;
        Log.w("NotificationParams", userFriendlyKey(r7 + "_loc_key") + " resource not found: " + r7 + " Default value will be used.");
        return null;
    L9:
        Object[] r4 = getLocalizationArgsForKey(r7);
        if (r4 == null) goto L12;
        return r5.getString(r62, r4);
    L15:
        e = move-exception;
        Log.w("NotificationParams", "Missing format argument for " + userFriendlyKey(r7) + ": " + Arrays.toString(r4) + " Default value will be used.", e);
        return null;
    L12:
        return r5.getString(r62);
    }

    public Long getLong(String r3) {
        String r2 = getString(r3);
        if (TextUtils.isEmpty(r2) == true) goto L11;
        return Long.valueOf(Long.parseLong(r2));
    L6:
        Log.w("NotificationParams", "Couldn't parse value of " + userFriendlyKey(r3) + "(" + r2 + ") into a long");
        return null;
    L11:
        return null;
    }

    public String getNotificationChannelId() {
        return getString("gcm.n.android_channel_id");
    }

    public Integer getNotificationCount() {
        Integer r3 = getInteger("gcm.n.notification_count");
        if (r3 != null) goto L6;
        return null;
    L6:
        if (r3.intValue() >= 0) goto L9;
        Log.w("FirebaseMessaging", "notificationCount is invalid: " + r3 + ". Skipping setting notificationCount.");
        return null;
    L9:
        return r3;
    }

    public Integer getNotificationPriority() {
        Integer r3 = getInteger("gcm.n.notification_priority");
        if (r3 != null) goto L6;
        return null;
    L6:
        if (r3.intValue() >= (-2)) goto L8;
    L11:
        Log.w("FirebaseMessaging", "notificationPriority is invalid " + r3 + ". Skipping setting notificationPriority.");
        return null;
    L8:
        if (r3.intValue() > 2) goto L11;
        return r3;
    }

    public String getPossiblyLocalizedString(Resources r3, String r4, String r5) {
        String r0 = getString(r5);
        if (TextUtils.isEmpty(r0) == true) goto L6;
        return r0;
    L6:
        return getLocalizedString(r3, r4, r5);
    }

    public String getSoundResourceName() {
        String r0 = getString("gcm.n.sound2");
        if (TextUtils.isEmpty(r0) == true) goto L5;
        return r0;
    L5:
        return getString("gcm.n.sound");
    }

    public String getString(String r2) {
        return this.data.getString(normalizePrefix(r2));
    }

    public long[] getVibrateTimings() {
        JSONArray r6 = getJSONArray("gcm.n.vibrate_timings");
        if (r6 != null) goto L15;
        return null;
    L15:
    L13:
        Log.w("NotificationParams", "User defined vibrateTimings is invalid: " + r6 + ". Skipping setting vibrateTimings.");
        return null;
    L6:
        if (r6.length() <= 1) goto L12;
        int r1 = r6.length();     // Catch: Throwable -> L13
        long[] r2 = new long[r1];     // Catch: Throwable -> L13
        int r3 = 0;
    L8:
        if (r3 >= r1) goto L10;
        r2[r3] = r6.optLong(r3);     // Catch: Throwable -> L13
        r3 = r3 + 1;     // Catch: Throwable -> L13
        goto L8
    L10:
        return r2;
    L12:
        throw new JSONException("vibrateTimings have invalid length");     // Catch: Throwable -> L13
    }

    public Integer getVisibility() {
        Integer r3 = getInteger("gcm.n.visibility");
        if (r3 != null) goto L6;
        return null;
    L6:
        if (r3.intValue() >= (-1)) goto L8;
    L11:
        Log.w("NotificationParams", "visibility is invalid: " + r3 + ". Skipping setting visibility.");
        return null;
    L8:
        if (r3.intValue() > 1) goto L11;
        return r3;
    }

    public Bundle paramsForAnalyticsIntent() {
        Bundle r0 = new Bundle(this.data);
        Iterator<String> r3 = this.data.keySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        String r1 = r3.next();
        if (isAnalyticsKey(r1) == true) goto L4;
        r0.remove(r1);
        goto L4
    L8:
        return r0;
    }

    public Bundle paramsWithReservedKeysRemoved() {
        Bundle r0 = new Bundle(this.data);
        Iterator<String> r3 = this.data.keySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        String r1 = r3.next();
        if (isReservedKey(r1) == false) goto L4;
        r0.remove(r1);
        goto L4
    L8:
        return r0;
    }
}
