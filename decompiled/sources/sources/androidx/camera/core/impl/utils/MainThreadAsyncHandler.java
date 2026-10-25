package androidx.camera.core.impl.utils;

import android.os.Handler;
import android.os.Looper;
import androidx.core.os.HandlerCompat;

/* loaded from: classes3.dex */
public abstract class MainThreadAsyncHandler {
    private static volatile Handler sHandler;

    public static Handler getInstance() {
        if (sHandler == null) goto L7;
        return sHandler;
    L7:
        monitor-enter(MainThreadAsyncHandler.class);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (sHandler != null) goto L13;
        sHandler = HandlerCompat.createAsync(Looper.getMainLooper());     // Catch: Throwable -> L11
    L13:
        monitor-exit(MainThreadAsyncHandler.class);     // Catch: Throwable -> L11
        return sHandler;
    }
}
