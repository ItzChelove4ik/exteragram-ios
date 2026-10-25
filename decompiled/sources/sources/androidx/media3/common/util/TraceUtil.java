package androidx.media3.common.util;

import android.os.Trace;

/* loaded from: classes3.dex */
public abstract class TraceUtil {
    public static void beginSection(String r0) {
        Trace.beginSection(r0);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
