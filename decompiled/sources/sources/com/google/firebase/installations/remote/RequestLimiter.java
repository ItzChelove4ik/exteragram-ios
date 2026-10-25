package com.google.firebase.installations.remote;

import com.google.firebase.installations.Utils;
import kotlin.time.DurationKt;

/* loaded from: classes.dex */
class RequestLimiter {
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS = 0;
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS = 0;
    private int attemptCount;
    private long nextRequestTime;
    private final Utils utils;

    static {
        MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS = DurationKt.MILLIS_IN_DAY;
        MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS = 1800000;
    }

    public RequestLimiter() {
        this.utils = Utils.getInstance();
    }

    private synchronized long getBackoffDuration(int r5) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (isRetryableError(r5) == true) goto L11;
        long r0 = MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r0;
    L11:
        long r02 = (long) Math.min(Math.pow(2.0d, this.attemptCount) + this.utils.getRandomDelayForSyncPrevention(), MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS);
        monitor-exit(this);
        return r02;
    }

    private static boolean isRetryableError(int r1) {
        if (r1 != 429) goto L5;
        return true;
    L5:
        if (r1 >= 500) goto L7;
        return false;
    L7:
        if (r1 < 600) goto L14;
        return false;
    L14:
        return true;
    }

    private static boolean isSuccessfulOrRequiresNewFidCreation(int r1) {
        if (r1 < 200) goto L7;
        if (r1 >= 300) goto L7;
        return true;
    L7:
        if (r1 != 401) goto L9;
        return true;
    L9:
        if (r1 == 404) goto L16;
        return false;
    L16:
        return true;
    }

    private synchronized void resetBackoffStrategy() {
        monitor-enter(this);
        this.attemptCount = 0;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isRequestAllowed() {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.attemptCount == 0) goto L12;
        if (this.utils.currentTimeInMillis() > this.nextRequestTime) goto L12;
        boolean r0 = false;
    L13:
        monitor-exit(this);
        return r0;
    L12:
        r0 = true;
        goto L13
    }

    public synchronized void setNextRequestTime(int r5) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (isSuccessfulOrRequiresNewFidCreation(r5) == false) goto L10;
        resetBackoffStrategy();     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L10:
        this.attemptCount++;
        this.nextRequestTime = this.utils.currentTimeInMillis() + getBackoffDuration(r5);     // Catch: Throwable -> L8
        monitor-exit(this);
    }
}
