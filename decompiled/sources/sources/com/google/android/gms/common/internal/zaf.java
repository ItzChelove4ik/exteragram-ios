package com.google.android.gms.common.internal;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.ConfigurationCompat;
import com.google.android.gms.base.R$string;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;

/* loaded from: classes4.dex */
public abstract class zaf {
    private static final SimpleArrayMap zaa = null;
    private static Locale zab;

    static {
        zaa = new SimpleArrayMap();
    }

    public static String zaa(Context r3, int r4) {
        Resources r0 = r3.getResources();
        switch(r4) {
            case 1: goto L30;
            case 2: goto L28;
            case 3: goto L26;
            case 4: goto L24;
            case 5: goto L22;
            case 6: goto L24;
            case 7: goto L20;
            case 8: goto L18;
            case 9: goto L16;
            case 10: goto L14;
            case 11: goto L12;
            case 12: goto L4;
            case 13: goto L4;
            case 14: goto L4;
            case 15: goto L4;
            case 16: goto L10;
            case 17: goto L8;
            case 18: goto L24;
            case 19: goto L4;
            case 20: goto L6;
            default: goto L4;
        };
    L4:
        StringBuilder r02 = new StringBuilder(String.valueOf(r4).length() + 22);
        r02.append("Unexpected error code ");
        r02.append(r4);
        Log.e("GoogleApiAvailability", r02.toString());
        return null;
    L6:
        Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
        return zah(r3, "common_google_play_services_restricted_profile_title");
    L8:
        Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
        return zah(r3, "common_google_play_services_sign_in_failed_title");
    L10:
        Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
        return null;
    L12:
        Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
        return null;
    L14:
        Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
        return null;
    L16:
        Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
        return null;
    L18:
        Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
        return null;
    L20:
        Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
        return zah(r3, "common_google_play_services_network_error_title");
    L22:
        Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
        return zah(r3, "common_google_play_services_invalid_account_title");
    L24:
        return null;
    L26:
        return r0.getString(R$string.common_google_play_services_enable_title);
    L28:
        return r0.getString(R$string.common_google_play_services_update_title);
    L30:
        return r0.getString(R$string.common_google_play_services_install_title);
    }

    public static String zab(Context r1, int r2) {
        if (r2 != 6) goto L5;
        String r22 = zah(r1, "common_google_play_services_resolution_required_title");
    L6:
        if (r22 == null) goto L8;
        return r22;
    L8:
        return r1.getResources().getString(R$string.common_google_play_services_notification_ticker);
    L5:
        r22 = zaa(r1, r2);
        goto L6
    }

    public static String zac(Context r3, int r4) {
        Resources r0 = r3.getResources();
        String r1 = zaf(r3);
        if (r4 == 1) goto L42;
        if (r4 == 2) goto L36;
        if (r4 == 3) goto L34;
        if (r4 == 5) goto L32;
        if (r4 == 7) goto L30;
        if (r4 == 9) goto L28;
        if (r4 == 20) goto L26;
        switch(r4) {
            case 16: goto L24;
            case 17: goto L22;
            case 18: goto L20;
            default: goto L18;
        };
    L18:
        return r0.getString(com.google.android.gms.common.R$string.common_google_play_services_unknown_issue, new Object[]{r1});
    L20:
        return r0.getString(R$string.common_google_play_services_updating_text, new Object[]{r1});
    L22:
        return zag(r3, "common_google_play_services_sign_in_failed_text", r1);
    L24:
        return zag(r3, "common_google_play_services_api_unavailable_text", r1);
    L26:
        return zag(r3, "common_google_play_services_restricted_profile_text", r1);
    L28:
        return r0.getString(R$string.common_google_play_services_unsupported_text, new Object[]{r1});
    L30:
        return zag(r3, "common_google_play_services_network_error_text", r1);
    L32:
        return zag(r3, "common_google_play_services_invalid_account_text", r1);
    L34:
        return r0.getString(R$string.common_google_play_services_enable_text, new Object[]{r1});
    L36:
        if (DeviceProperties.isWearableWithoutPlayStore(r3) == false) goto L40;
        return r0.getString(R$string.common_google_play_services_wear_update_text);
    L40:
        return r0.getString(R$string.common_google_play_services_update_text, new Object[]{r1});
    L42:
        return r0.getString(R$string.common_google_play_services_install_text, new Object[]{r1});
    }

    public static String zad(Context r1, int r2) {
        if (r2 == 6) goto L10;
        if (r2 == 19) goto L10;
        return zac(r1, r2);
    L10:
        return zag(r1, "common_google_play_services_resolution_required_text", zaf(r1));
    }

    public static String zae(Context r1, int r2) {
        Resources r12 = r1.getResources();
        if (r2 == 1) goto L15;
        if (r2 == 2) goto L13;
        if (r2 == 3) goto L11;
        return r12.getString(R.string.ok);
    L11:
        return r12.getString(R$string.common_google_play_services_enable_button);
    L13:
        return r12.getString(R$string.common_google_play_services_update_button);
    L15:
        return r12.getString(R$string.common_google_play_services_install_button);
    }

    public static String zaf(Context r2) {
        String r0 = r2.getPackageName();
        return Wrappers.packageManager(r2).getApplicationLabel(r0).toString();
    L5:
        String r22 = r2.getApplicationInfo().name;
        if (TextUtils.isEmpty(r22) == false) goto L8;
        return r0;
    L8:
        return r22;
    }

    private static String zag(Context r1, String r2, String r3) {
        Resources r0 = r1.getResources();
        String r12 = zah(r1, r2);
        if (r12 != null) goto L6;
        r12 = r0.getString(com.google.android.gms.common.R$string.common_google_play_services_unknown_issue);
    L6:
        return String.format(r0.getConfiguration().locale, r12, new Object[]{r3});
    }

    private static String zah(Context r6, String r7) {
        SimpleArrayMap r0 = zaa;
        monitor-enter(r0);
        Locale r3 = ConfigurationCompat.getLocales(r6.getResources().getConfiguration()).get(0);     // Catch: Throwable -> L7
        if (r3.equals(zab) == true) goto L9;
        r0.clear();     // Catch: Throwable -> L7
        zab = r3;     // Catch: Throwable -> L7
    L9:
        String r32 = (String) r0.get(r7);     // Catch: Throwable -> L7
        if (r32 == null) goto L13;
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r32;
    L13:
        Resources r62 = GooglePlayServicesUtil.getRemoteResource(r6);     // Catch: Throwable -> L7
        if (r62 != null) goto L17;
        monitor-exit(r0);     // Catch: Throwable -> L7
        return null;
    L17:
        int r4 = r62.getIdentifier(r7, "string", "com.google.android.gms");     // Catch: Throwable -> L7
        if (r4 != 0) goto L22;
        StringBuilder r42 = new StringBuilder(r7.length() + 18);     // Catch: Throwable -> L7
        r42.append("Missing resource: ");     // Catch: Throwable -> L7
        r42.append(r7);     // Catch: Throwable -> L7
        Log.w("GoogleApiAvailability", r42.toString());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return null;
    L22:
        String r63 = r62.getString(r4);     // Catch: Throwable -> L7
        if (TextUtils.isEmpty(r63) == false) goto L27;
        StringBuilder r43 = new StringBuilder(r7.length() + 20);     // Catch: Throwable -> L7
        r43.append("Got empty resource: ");     // Catch: Throwable -> L7
        r43.append(r7);     // Catch: Throwable -> L7
        Log.w("GoogleApiAvailability", r43.toString());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return null;
    L27:
        r0.put(r7, r63);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r63;
    L7:
        th = move-exception;
        throw th;
    }
}
