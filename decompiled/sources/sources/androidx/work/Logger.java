package androidx.work;

import android.util.Log;

/* loaded from: classes.dex */
public abstract class Logger {
    private static final int MAX_PREFIXED_TAG_LENGTH = 20;
    private static final Object sLock = null;
    private static volatile Logger sLogger;

    public static class LogcatLogger extends Logger {
        private final int mLoggingLevel;

        public LogcatLogger(int r1) {
            super(r1);
            this.mLoggingLevel = r1;
        }

        @Override // androidx.work.Logger
        public void debug(String r2, String r3) {
            if (this.mLoggingLevel > 3) goto L6;
            Log.d(r2, r3);
            return;
        }

        @Override // androidx.work.Logger
        public void debug(String r2, String r3, Throwable r4) {
            if (this.mLoggingLevel > 3) goto L6;
            Log.d(r2, r3, r4);
            return;
        }

        @Override // androidx.work.Logger
        public void error(String r2, String r3) {
            if (this.mLoggingLevel > 6) goto L6;
            Log.e(r2, r3);
            return;
        }

        @Override // androidx.work.Logger
        public void error(String r2, String r3, Throwable r4) {
            if (this.mLoggingLevel > 6) goto L6;
            Log.e(r2, r3, r4);
            return;
        }

        @Override // androidx.work.Logger
        public void info(String r2, String r3) {
            if (this.mLoggingLevel > 4) goto L6;
            Log.i(r2, r3);
            return;
        }

        @Override // androidx.work.Logger
        public void info(String r2, String r3, Throwable r4) {
            if (this.mLoggingLevel > 4) goto L6;
            Log.i(r2, r3, r4);
            return;
        }

        @Override // androidx.work.Logger
        public void verbose(String r2, String r3) {
            if (this.mLoggingLevel > 2) goto L6;
            Log.v(r2, r3);
            return;
        }

        @Override // androidx.work.Logger
        public void warning(String r2, String r3) {
            if (this.mLoggingLevel > 5) goto L6;
            Log.w(r2, r3);
            return;
        }

        @Override // androidx.work.Logger
        public void warning(String r2, String r3, Throwable r4) {
            if (this.mLoggingLevel > 5) goto L6;
            Log.w(r2, r3, r4);
            return;
        }
    }

    static {
        sLock = new Object();
    }

    public Logger(int r1) {
    }

    public static Logger get() {
        Object r0 = sLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (sLogger != null) goto L9;
        sLogger = new LogcatLogger(3);     // Catch: Throwable -> L7
    L9:
        Logger r1 = sLogger;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    }

    public static void setLogger(Logger r2) {
        Object r0 = sLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (sLogger != null) goto L9;
        sLogger = r2;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    public static String tagWithPrefix(String r3) {
        int r0 = r3.length();
        StringBuilder r1 = new StringBuilder(23);
        r1.append("WM-");
        int r2 = MAX_PREFIXED_TAG_LENGTH;
        if (r0 < r2) goto L5;
        r1.append(r3.substring(0, r2));
    L7:
        return r1.toString();
    L5:
        r1.append(r3);
        goto L7
    }

    public abstract void debug(String r1, String r2);

    public abstract void debug(String r1, String r2, Throwable r3);

    public abstract void error(String r1, String r2);

    public abstract void error(String r1, String r2, Throwable r3);

    public abstract void info(String r1, String r2);

    public abstract void info(String r1, String r2, Throwable r3);

    public abstract void verbose(String r1, String r2);

    public abstract void warning(String r1, String r2);

    public abstract void warning(String r1, String r2, Throwable r3);
}
