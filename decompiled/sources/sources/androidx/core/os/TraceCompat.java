package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

@Deprecated
/* loaded from: classes.dex */
public abstract class TraceCompat {
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    static {
        if (Build.VERSION.SDK_INT >= 29) goto L11;
        sTraceTagApp = Trace.class.getField("TRACE_TAG_APP").getLong(null);     // Catch: Exception -> L6
        Class r3 = Long.TYPE;     // Catch: Exception -> L6
        sIsTagEnabledMethod = Trace.class.getMethod("isTagEnabled", new Class[]{r3});     // Catch: Exception -> L6
        Class r4 = Integer.TYPE;     // Catch: Exception -> L6
        sAsyncTraceBeginMethod = Trace.class.getMethod("asyncTraceBegin", new Class[]{r3, String.class, r4});     // Catch: Exception -> L6
        sAsyncTraceEndMethod = Trace.class.getMethod("asyncTraceEnd", new Class[]{r3, String.class, r4});     // Catch: Exception -> L6
        sTraceCounterMethod = Trace.class.getMethod("traceCounter", new Class[]{r3, String.class, r4});     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        Log.i("TraceCompat", "Unable to initialize via reflection.", e);
        return;
    }

    public static void beginSection(String r0) {
        Trace.beginSection(r0);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
