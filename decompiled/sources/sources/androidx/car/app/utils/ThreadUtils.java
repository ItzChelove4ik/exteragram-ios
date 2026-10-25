package androidx.car.app.utils;

import android.os.Handler;
import android.os.Looper;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class ThreadUtils {
    private static final Handler HANDLER = null;

    static {
        HANDLER = new Handler(Looper.getMainLooper());
    }

    public static void checkMainThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Not running on main thread when it is required to");
    }

    public static void runOnMain(Runnable r2) {
        if (Looper.getMainLooper() != Looper.myLooper()) goto L6;
        r2.run();
        return;
    L6:
        HANDLER.post(r2);
    }
}
