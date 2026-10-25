package org.webrtc;

import android.content.Context;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class ContextUtils {
    private static final String TAG = "ContextUtils";
    private static Context applicationContext;

    public ContextUtils() {
    }

    @Deprecated
    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void initialize(Context r0) {
        if (r0 == null) goto L5;
        applicationContext = r0;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Application context cannot be null for ContextUtils.initialize.");
    }
}
