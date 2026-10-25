package com.google.android.gms.cast;

import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Collection;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class CastMediaControlIntent {
    public static final String ACTION_SYNC_STATUS = "com.google.android.gms.cast.ACTION_SYNC_STATUS";
    public static final String DEFAULT_MEDIA_RECEIVER_APPLICATION_ID = "CC1AD845";
    public static final int ERROR_CODE_REQUEST_FAILED = 1;
    public static final int ERROR_CODE_SESSION_START_FAILED = 2;
    public static final int ERROR_CODE_TEMPORARILY_DISCONNECTED = 3;
    public static final String EXTRA_CAST_APPLICATION_ID = "com.google.android.gms.cast.EXTRA_CAST_APPLICATION_ID";
    public static final String EXTRA_CAST_LANGUAGE_CODE = "com.google.android.gms.cast.EXTRA_CAST_LANGUAGE_CODE";
    public static final String EXTRA_CAST_RELAUNCH_APPLICATION = "com.google.android.gms.cast.EXTRA_CAST_RELAUNCH_APPLICATION";
    public static final String EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS = "com.google.android.gms.cast.EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS";
    public static final String EXTRA_CUSTOM_DATA = "com.google.android.gms.cast.EXTRA_CUSTOM_DATA";
    public static final String EXTRA_DEBUG_LOGGING_ENABLED = "com.google.android.gms.cast.EXTRA_DEBUG_LOGGING_ENABLED";
    public static final String EXTRA_ERROR_CODE = "com.google.android.gms.cast.EXTRA_ERROR_CODE";

    private CastMediaControlIntent() {
    }

    public static String categoryForCast(String r2) {
        if (r2 == null) goto L6;
        zzs r1 = new zzs(null);
        r1.zzb(r2);
        return r1.zzd().zza();
    L6:
        f$$ExternalSyntheticBUOutline1.m("applicationId cannot be null");
        return null;
    }

    public static String categoryForCast(String r2, Collection<String> r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        zzs r1 = new zzs(null);
        r1.zzb(r2);
        r1.zzc(r3);
        return r1.zzd().zza();
    L7:
        f$$ExternalSyntheticBUOutline1.m("namespaces cannot be null");
        return null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("applicationId cannot be null");
        return null;
    }

    public static String categoryForCast(Collection<String> r2) {
        if (r2 == null) goto L6;
        zzs r1 = new zzs(null);
        r1.zzc(r2);
        return r1.zzd().zza();
    L6:
        f$$ExternalSyntheticBUOutline1.m("namespaces cannot be null");
        return null;
    }

    public static String categoryForRemotePlayback() {
        zzs r0 = new zzs(null);
        r0.zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK");
        return r0.zzd().zza();
    }

    public static String categoryForRemotePlayback(String r2) {
        if (TextUtils.isEmpty(r2) == true) goto L6;
        zzs r0 = new zzs(null);
        r0.zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK");
        r0.zzb(r2);
        return r0.zzd().zza();
    L6:
        f$$ExternalSyntheticBUOutline1.m("applicationId cannot be null or empty");
        return null;
    }

    public static String languageTagForLocale(Locale r0) {
        return CastUtils.zzd(r0);
    }
}
