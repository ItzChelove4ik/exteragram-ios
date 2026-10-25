package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.logging.Logging;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class SafeLoggingExecutor implements Executor {
    private final Executor delegate;

    public static class SafeLoggingRunnable implements Runnable {
        private final Runnable delegate;

        public SafeLoggingRunnable(Runnable r1) {
            this.delegate = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.delegate.run();     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            Logging.e("Executor", "Background execution failure.", e);
        }
    }

    public SafeLoggingExecutor(Executor r1) {
        this.delegate = r1;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r2) {
        this.delegate.execute(new SafeLoggingRunnable(r2));
    }
}
