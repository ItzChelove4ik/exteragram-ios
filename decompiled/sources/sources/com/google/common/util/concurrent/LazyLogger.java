package com.google.common.util.concurrent;

import java.util.logging.Logger;

/* loaded from: classes5.dex */
final class LazyLogger {
    private final Object lock;
    private volatile Logger logger;
    private final String loggerName;

    public LazyLogger(Class<?> r2) {
        this.lock = new Object();
        this.loggerName = r2.getName();
    }

    public Logger get() {
        Logger r0 = this.logger;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Object r02 = this.lock;
        monitor-enter(r02);
        Logger r1 = this.logger;     // Catch: Throwable -> L11
        if (r1 == null) goto L13;
        monitor-exit(r02);     // Catch: Throwable -> L11
        return r1;
    L13:
        this.logger = Logger.getLogger(this.loggerName);     // Catch: Throwable -> L11
        Logger r2 = this.logger;     // Catch: Throwable -> L11
        monitor-exit(r02);     // Catch: Throwable -> L11
        return r2;
    L11:
        th = move-exception;
        throw th;
    }
}
