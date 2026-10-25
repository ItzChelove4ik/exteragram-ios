package org.chromium.net.telemetry;

import android.os.SystemClock;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class RateLimiter {
    private long mLastPermitMillis;
    private final Object mLock;
    private int mSamplesLoggedDuringSecond;
    private final int mSamplesPerSeconds;

    public RateLimiter(int r3) {
        this.mLock = new Object();
        this.mLastPermitMillis = Long.MIN_VALUE;
        if (r3 <= 0) goto L6;
        this.mSamplesPerSeconds = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Expect sample rate to be > 0 sample(s) per second");
        throw null;
    }

    public boolean tryAcquire() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        long r1 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L9
        if ((this.mLastPermitMillis + 1000) > r1) goto L11;
        this.mSamplesLoggedDuringSecond = 1;     // Catch: Throwable -> L9
        this.mLastPermitMillis = r1;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return true;
    L11:
        int r12 = this.mSamplesLoggedDuringSecond;     // Catch: Throwable -> L9
        if (r12 >= this.mSamplesPerSeconds) goto L17;
        this.mSamplesLoggedDuringSecond = r12 + 1;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return true;
    L17:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L9:
        th = move-exception;
        throw th;
    }
}
