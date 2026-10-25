package androidx.core.app;

import android.app.ActivityManager;

/* loaded from: classes3.dex */
public abstract class ActivityManagerCompat {
    @Deprecated
    public static boolean isLowRamDevice(ActivityManager r0) {
        return r0.isLowRamDevice();
    }
}
