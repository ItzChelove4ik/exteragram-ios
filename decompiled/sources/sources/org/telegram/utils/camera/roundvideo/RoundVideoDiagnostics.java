package org.telegram.utils.camera.roundvideo;

import android.os.SystemClock;
import java.util.concurrent.atomic.AtomicLong;
import org.telegram.messenger.FileLog;

/* loaded from: classes6.dex */
final class RoundVideoDiagnostics {
    private static final AtomicLong NEXT_SESSION_ID = null;
    private final long sessionId;
    private final long startedAtMs;

    static {
        NEXT_SESSION_ID = new AtomicLong(1);
    }

    public RoundVideoDiagnostics() {
        this.sessionId = NEXT_SESSION_ID.getAndIncrement();
        this.startedAtMs = SystemClock.elapsedRealtime();
    }

    private String prefix() {
        return "RoundVideo[" + this.sessionId + "] t+" + (SystemClock.elapsedRealtime() - this.startedAtMs) + "ms [" + Thread.currentThread().getName() + "] ";
    }

    public void error(String r2, Throwable r3) {
        FileLog.e(prefix() + r2 + ": " + r3);
        FileLog.e(r3);
    }

    public void log(String r2) {
        FileLog.d(prefix() + r2);
    }
}
