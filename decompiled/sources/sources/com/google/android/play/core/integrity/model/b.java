package com.google.android.play.core.integrity.model;

import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f89a = null;

    /* renamed from: b, reason: collision with root package name */
    private static final Map f90b = null;

    static {
        HashMap r0 = new HashMap();
        f89a = r0;
        HashMap r1 = new HashMap();
        f90b = r1;
        r0.put(-1, "Standard Integrity API is not available.\nStandard Integrity API is not enabled, or the Play Store version might be old.\nRecommended actions:\n1) Make sure to be allowlisted to use Standard Integrity API.\n2) Make sure that Integrity API is enabled in Google Play Console.\n3) Ask the user to update Play Store.\n");
        r0.put(-2, "The Play Store app is either not installed or not the official version.\nAsk the user to install an official and recent version of Play Store.\n");
        r0.put(-3, "Network error: unable to obtain integrity details.\nAsk the user to check for a connection.\n");
        r0.put(-5, "PackageManager could not find this app.\nSomething is wrong (possibly an attack). Non-actionable.\n");
        r0.put(-6, "Google Play Services is not available or version is too old.\nAsk the user to Install or Update Play Services.\n");
        r0.put(-7, "The calling app UID (user id) does not match the one from Package Manager.\nSomething is wrong (possibly an attack). Non-actionable.\n");
        r0.put(-8, "The calling app is making too many requests to the API and hence is throttled.\nRetry with an exponential backoff.\n");
        r0.put(-9, "Binding to the service in the Play Store has failed. This can be due to having an old Play Store version installed on the device.\nAsk the user to update Play Store.\n");
        r0.put(-12, "Unknown internal Google server error.\nRetry with an exponential backoff. Consider filing a bug if fails consistently.\n");
        r0.put(-14, "The Play Store needs to be updated.\nAsk the user to update the Google Play Store.\n");
        r0.put(-15, "Play Services needs to be updated.\nAsk the user to update Google Play Services.\n");
        r0.put(-16, "The provided cloud project number is invalid.\nUse the cloud project number which can be found in Project info in your Google Cloud Console for the cloud project where Play Integrity API is enabled.\n");
        r0.put(-17, "The provided request hash is too long. The request hash length must be less than 500 bytes.\nRetry with a shorter request hash.");
        r0.put(-18, "There is a transient error on the calling device.\nRetry with an exponential backoff.\n");
        r0.put(-19, "The StandardIntegrityTokenProvider is invalid (e.g. it is outdated).\nRequest a new integrity token provider by calling StandardIntegrityManager#prepareIntegrityToken.");
        r0.put(-100, "Unknown error processing integrity request.\nRetry with an exponential backoff. Consider filing a bug if fails consistently.\n");
        r1.put(-1, "API_NOT_AVAILABLE");
        r1.put(-3, "NETWORK_ERROR");
        r1.put(-2, "PLAY_STORE_NOT_FOUND");
        r1.put(-14, "PLAY_STORE_VERSION_OUTDATED");
        r1.put(-5, "APP_NOT_INSTALLED");
        r1.put(-6, "PLAY_SERVICES_NOT_FOUND");
        r1.put(-15, "PLAY_SERVICES_VERSION_OUTDATED");
        r1.put(-7, "APP_UID_MISMATCH");
        r1.put(-8, "TOO_MANY_REQUESTS");
        r1.put(-9, "CANNOT_BIND_TO_SERVICE");
        r1.put(-16, "CLOUD_PROJECT_NUMBER_IS_INVALID");
        r1.put(-17, "REQUEST_HASH_TOO_LONG");
        r1.put(-12, "GOOGLE_SERVER_UNAVAILABLE");
        r1.put(-18, "CLIENT_TRANSIENT_ERROR");
        r1.put(-19, "INTEGRITY_TOKEN_PROVIDER_INVALID");
        r1.put(-100, "INTERNAL_ERROR");
    }

    public static String a(int r3) {
        Map r0 = f89a;
        Integer r32 = Integer.valueOf(r3);
        if (r0.containsKey(r32) == false) goto L9;
        Map r1 = f90b;
        if (r1.containsKey(r32) == true) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return ((String) r0.get(r32)) + " (https://developer.android.com/google/play/integrity/reference/com/google/android/play/core/integrity/model/StandardIntegrityErrorCode.html#" + ((String) r1.get(r32)) + ")";
    L9:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
