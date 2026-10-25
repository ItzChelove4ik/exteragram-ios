package androidx.media3.common.util;

import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public abstract class BackgroundExecutor {
    private static Executor staticInstance;

    public static synchronized Executor get() {
        monitor-enter(BackgroundExecutor.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (staticInstance != null) goto L9;
        staticInstance = Util.newSingleThreadExecutor("ExoPlayer:BackgroundExecutor");     // Catch: Throwable -> L7
    L9:
        Executor r1 = staticInstance;     // Catch: Throwable -> L7
        monitor-exit(BackgroundExecutor.class);
        return r1;
    }
}
