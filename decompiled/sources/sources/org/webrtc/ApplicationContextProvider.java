package org.webrtc;

import android.content.Context;

/* loaded from: classes6.dex */
public class ApplicationContextProvider {
    public ApplicationContextProvider() {
    }

    @CalledByNative
    public static Context getApplicationContext() {
        return ContextUtils.getApplicationContext();
    }
}
