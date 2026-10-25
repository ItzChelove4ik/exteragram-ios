package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes3.dex */
public class SystemClock implements Clock {
    public SystemClock() {
    }

    @Override // androidx.media3.common.util.Clock
    public HandlerWrapper createHandler(Looper r2, Handler.Callback r3) {
        return new SystemHandlerWrapper(new Handler(r2, r3));
    }

    @Override // androidx.media3.common.util.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // androidx.media3.common.util.Clock
    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    @Override // androidx.media3.common.util.Clock
    public long nanoTime() {
        return System.nanoTime();
    }

    @Override // androidx.media3.common.util.Clock
    public void onThreadBlocked() {
    }

    @Override // androidx.media3.common.util.Clock
    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }
}
