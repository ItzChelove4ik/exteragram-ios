package org.webrtc;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class Metrics {
    private static final String TAG = "Metrics";
    public final Map<String, HistogramInfo> map;

    public static class HistogramInfo {
        public final int bucketCount;
        public final int max;
        public final int min;
        public final Map<Integer, Integer> samples;

        @CalledByNative("HistogramInfo")
        public HistogramInfo(int r2, int r3, int r4) {
            this.samples = new HashMap();
            this.min = r2;
            this.max = r3;
            this.bucketCount = r4;
        }

        @CalledByNative("HistogramInfo")
        public void addSample(int r1, int r2) {
            this.samples.put(Integer.valueOf(r1), Integer.valueOf(r2));
        }
    }

    @CalledByNative
    public Metrics() {
        this.map = new HashMap();
    }

    @CalledByNative
    private void add(String r1, HistogramInfo r2) {
        this.map.put(r1, r2);
    }

    public static void enable() {
        nativeEnable();
    }

    public static Metrics getAndReset() {
        return nativeGetAndReset();
    }

    private static native void nativeEnable();

    private static native Metrics nativeGetAndReset();
}
