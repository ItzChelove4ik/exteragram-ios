package com.stripe.android.util;

import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class StripeJsonUtils {
    public static String getString(JSONObject r0, String r1) {
        return nullIfNullOrEmpty(r0.getString(r1));
    }

    public static String nullIfNullOrEmpty(String r1) {
        if ("null".equals(r1) == false) goto L5;
        return null;
    L5:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r1) == true) goto L10;
        return r1;
    L10:
        return null;
    }

    public static String optString(JSONObject r0, String r1) {
        return nullIfNullOrEmpty(r0.optString(r1));
    }
}
