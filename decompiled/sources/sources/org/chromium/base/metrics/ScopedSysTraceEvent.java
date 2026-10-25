package org.chromium.base.metrics;

import android.os.Trace;

/* loaded from: classes5.dex */
public class ScopedSysTraceEvent implements AutoCloseable {
    private ScopedSysTraceEvent(String r2) {
        if (r2.length() <= 127) goto L5;
        r2 = r2.substring(0, 124).concat("...");
    L5:
        Trace.beginSection(r2);
    }

    public static ScopedSysTraceEvent scoped(String r1) {
        return new ScopedSysTraceEvent(r1);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Trace.endSection();
    }
}
