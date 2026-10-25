package org.webrtc;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class TimestampAligner {
    private volatile long nativeTimestampAligner;

    public TimestampAligner() {
        this.nativeTimestampAligner = nativeCreateTimestampAligner();
    }

    private void checkNativeAlignerExists() {
        if (this.nativeTimestampAligner == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("TimestampAligner has been disposed.");
    }

    public static long getRtcTimeNanos() {
        return nativeRtcTimeNanos();
    }

    private static native long nativeCreateTimestampAligner();

    private static native void nativeReleaseTimestampAligner(long r0);

    private static native long nativeRtcTimeNanos();

    private static native long nativeTranslateTimestamp(long r0, long r2);

    public void dispose() {
        checkNativeAlignerExists();
        nativeReleaseTimestampAligner(this.nativeTimestampAligner);
        this.nativeTimestampAligner = 0;
    }

    public long translateTimestamp(long r3) {
        checkNativeAlignerExists();
        return nativeTranslateTimestamp(this.nativeTimestampAligner, r3);
    }
}
