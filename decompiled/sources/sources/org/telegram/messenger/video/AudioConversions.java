package org.telegram.messenger.video;

/* loaded from: classes6.dex */
public class AudioConversions {
    private static final int BYTES_PER_SAMPLE_PER_CHANNEL = 2;
    private static final int BYTES_PER_SHORT = 2;
    private static final long MICROSECONDS_PER_SECOND = 1000000;

    public AudioConversions() {
    }

    public static long bytesToUs(int r4, int r5, int r6) {
        return (r4 * MICROSECONDS_PER_SECOND) / ((r5 * 2) * r6);
    }

    public static long shortsToUs(int r0, int r1, int r2) {
        return bytesToUs(r0 * 2, r1, r2);
    }

    public static int usToBytes(long r0, int r2, int r3) {
        return (int) Math.ceil((r0 * ((r2 * 2) * r3)) / 1000000.0d);
    }

    public static int usToShorts(long r0, int r2, int r3) {
        return usToBytes(r0, r2, r3) / 2;
    }
}
