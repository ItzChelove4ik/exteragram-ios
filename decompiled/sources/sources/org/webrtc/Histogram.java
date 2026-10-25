package org.webrtc;

/* loaded from: classes6.dex */
class Histogram {
    private final long handle;

    private Histogram(long r1) {
        this.handle = r1;
    }

    public static Histogram createCounts(String r1, int r2, int r3, int r4) {
        return new Histogram(nativeCreateCounts(r1, r2, r3, r4));
    }

    public static Histogram createEnumeration(String r1, int r2) {
        return new Histogram(nativeCreateEnumeration(r1, r2));
    }

    private static native void nativeAddSample(long r0, int r2);

    private static native long nativeCreateCounts(String r0, int r1, int r2, int r3);

    private static native long nativeCreateEnumeration(String r0, int r1);

    public void addSample(int r3) {
        nativeAddSample(this.handle, r3);
    }
}
