package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes3.dex */
public interface Clock {
    public static final Clock DEFAULT = null;

    static {
        DEFAULT = new SystemClock();
    }

    HandlerWrapper createHandler(Looper r1, Handler.Callback r2);

    long currentTimeMillis();

    long elapsedRealtime();

    long nanoTime();

    void onThreadBlocked();

    long uptimeMillis();
}
