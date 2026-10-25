package androidx.media3.common.util;

import android.text.TextUtils;
import java.net.UnknownHostException;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes3.dex */
public abstract class Log {
    private static final Object lock = null;
    private static int logLevel = 0;
    private static boolean logStackTraces = true;
    private static Logger logger;

    public interface Logger {
        public static final Logger DEFAULT = null;

        /* renamed from: androidx.media3.common.util.Log$Logger$1, reason: invalid class name */
        public class AnonymousClass1 implements Logger {
            public AnonymousClass1() {
            }

            @Override // androidx.media3.common.util.Log.Logger
            public void d(String r1, String r2, Throwable r3) {
                android.util.Log.d(r1, Log.appendThrowableString(r2, r3));
            }

            @Override // androidx.media3.common.util.Log.Logger
            public void e(String r1, String r2, Throwable r3) {
                android.util.Log.e(r1, Log.appendThrowableString(r2, r3));
            }

            @Override // androidx.media3.common.util.Log.Logger
            public void i(String r1, String r2, Throwable r3) {
                android.util.Log.i(r1, Log.appendThrowableString(r2, r3));
            }

            @Override // androidx.media3.common.util.Log.Logger
            public void w(String r1, String r2, Throwable r3) {
                android.util.Log.w(r1, Log.appendThrowableString(r2, r3));
            }
        }

        static {
            DEFAULT = new AnonymousClass1();
        }

        void d(String r1, String r2, Throwable r3);

        void e(String r1, String r2, Throwable r3);

        void i(String r1, String r2, Throwable r3);

        void w(String r1, String r2, Throwable r3);
    }

    static {
        lock = new Object();
        logger = Logger.DEFAULT;
    }

    @Pure
    public static String appendThrowableString(String r2, Throwable r3) {
        String r32 = getThrowableString(r3);
        if (TextUtils.isEmpty(r32) == false) goto L5;
        return r2;
    L5:
        return r2 + "\n  " + r32.replace("\n", "\n  ") + '\n';
    }

    @Pure
    public static void d(String r3, String r4) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (logLevel != 0) goto L9;
        logger.d(r3, r4, null);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    @Pure
    public static void d(String r2, String r3, Throwable r4) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (logLevel != 0) goto L9;
        logger.d(r2, r3, r4);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    @Pure
    public static void e(String r3, String r4) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (logLevel > 3) goto L9;
        logger.e(r3, r4, null);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    @Pure
    public static void e(String r3, String r4, Throwable r5) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (logLevel > 3) goto L9;
        logger.e(r3, r4, r5);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    @Pure
    public static String getThrowableString(Throwable r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        Object r0 = lock;
        monitor-enter(r0);
    L12:
        th = move-exception;
        throw th;
    L8:
        if (isCausedByUnknownHostException(r3) == false) goto L15;
        monitor-exit(r0);     // Catch: Throwable -> L12
        return "UnknownHostException (no network)";
    L15:
        if (logStackTraces == true) goto L19;
        String r32 = r3.getMessage();     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        return r32;
    L19:
        String r33 = android.util.Log.getStackTraceString(r3).trim().replace("\t", "    ");     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        return r33;
    }

    @Pure
    public static void i(String r3, String r4) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (logLevel > 1) goto L9;
        logger.i(r3, r4, null);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    @Pure
    private static boolean isCausedByUnknownHostException(Throwable r1) {
    L2:
        if (r1 == null) goto L8;
        if ((r1 instanceof UnknownHostException) == true) goto L5;
        r1 = r1.getCause();
        goto L2
    L5:
        return true;
    L8:
        return false;
    }

    @Pure
    public static void w(String r3, String r4) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (logLevel > 2) goto L9;
        logger.w(r3, r4, null);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    @Pure
    public static void w(String r3, String r4, Throwable r5) {
        Object r0 = lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (logLevel > 2) goto L9;
        logger.w(r3, r4, r5);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }
}
