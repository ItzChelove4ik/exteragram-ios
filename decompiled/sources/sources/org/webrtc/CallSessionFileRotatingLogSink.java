package org.webrtc;

import c.f$$ExternalSyntheticBUOutline1;
import org.webrtc.Logging;

/* loaded from: classes6.dex */
public class CallSessionFileRotatingLogSink {
    private long nativeSink;

    public CallSessionFileRotatingLogSink(String r1, int r2, Logging.Severity r3) {
        if (r1 == null) goto L6;
        this.nativeSink = nativeAddSink(r1, r2, r3.ordinal());
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("dirPath may not be null.");
        throw null;
    }

    public static byte[] getLogData(String r0) {
        if (r0 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("dirPath may not be null.");
        return null;
    L4:
        return nativeGetLogData(r0);
    }

    private static native long nativeAddSink(String r0, int r1, int r2);

    private static native void nativeDeleteSink(long r0);

    private static native byte[] nativeGetLogData(String r0);

    public void dispose() {
        long r0 = this.nativeSink;
        if (r0 == 0) goto L6;
        nativeDeleteSink(r0);
        this.nativeSink = 0;
        return;
    }
}
