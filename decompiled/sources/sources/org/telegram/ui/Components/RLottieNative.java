package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.os.Trace;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class RLottieNative {
    private final int[] mMetaData;
    private long mNativePtr;
    private final AtomicBoolean mRecycled;

    private RLottieNative(long r3, int[] r5) {
        this.mRecycled = new AtomicBoolean(false);
        this.mNativePtr = r3;
        this.mMetaData = r5;
    }

    private void checkNotRecycled() {
        if (this.mRecycled.get() == true) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Called method on a recycled RLottie instance");
    }

    private static long create(String r9, String r10, int[] r11, int[] r12, int r13, Map<String, Integer> r14) {
        Trace.beginSection("RLottieNative#create");
        int[] r0 = null;
        if (r14 != null) goto L15;
        String[] r7 = null;
    L6:
        if (r14 == null) goto L9;
        r0 = layerNamesToColors(r7, r14);     // Catch: Throwable -> L12
    L9:
        long r92 = nCreate(r9, r10, r11, r12, r13, r7, r0);     // Catch: Throwable -> L12
        Trace.endSection();
        return r92;
    L12:
        th = move-exception;
        Trace.endSection();
        throw th;
    L15:
        r7 = (String[]) r14.keySet().toArray(new String[0]);     // Catch: Throwable -> L12
        goto L6
    }

    public static RLottieNative createFromFile(String r6) {
        return createFromFile(r6, null, null, null, 0, null);
    }

    public static RLottieNative createFromFile(String r7, String r8, int[] r9, int[] r10, int r11, Map<String, Integer> r12) {
        int[] r3 = new int[3];
        long r72 = create(r7, r8, r3, r10, r11, r12);
        if (r72 != 0) goto L6;
        return null;
    L6:
        if (r9 == null) goto L11;
        if (r9.length != 3) goto L11;
        System.arraycopy(r3, 0, r9, 0, 3);
    L11:
        return new RLottieNative(r72, r3);
    }

    public static RLottieNative createFromRawJson(String r1) {
        return createFromRawJson(r1, null, null, null);
    }

    public static RLottieNative createFromRawJson(String r1, int[] r2) {
        return createFromRawJson(r1, r2, null, null);
    }

    public static RLottieNative createFromRawJson(String r6, int[] r7, int[] r8, Map<String, Integer> r9) {
        if (r6 != null) goto L5;
    L23:
        return null;
    L5:
        if (r6.isEmpty() == true) goto L23;
        int[] r2 = new int[3];
        if (r9 != null) goto L10;
        String[] r4 = null;
    L11:
        if (r9 != null) goto L13;
        int[] r92 = null;
    L14:
        long r82 = createWithJson(r6, r2, r8, r4, r92);
        if (r82 != 0) goto L17;
        return null;
    L17:
        if (r7 == null) goto L22;
        if (r7.length != 3) goto L22;
        System.arraycopy(r2, 0, r7, 0, 3);
    L22:
        return new RLottieNative(r82, r2);
    L13:
        r92 = layerNamesToColors(r4, r9);
        goto L14
    L10:
        r4 = (String[]) r9.keySet().toArray(new String[0]);
        goto L11
    }

    private static long createWithJson(String r1, int[] r2, int[] r3, String[] r4, int[] r5) {
        Trace.beginSection("RLottieNative#createWithJson");
        long r12 = nCreateWithJson(r1, r2, r3, r4, r5);     // Catch: Throwable -> L6
        Trace.endSection();
        return r12;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    private static void destroy(long r1) {
        Trace.beginSection("RLottieNative#destroy");
        nDestroy(r1);     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static double getDuration(String r6, String r7) {
        RLottieNative r62 = createFromFile(r6, r7, null, null, 0, null);
        if (r62 == null) goto L6;
        int r72 = r62.getFrameCount();
        int r0 = r62.getFps();
        r62.recycle();
        return r72 / r0;
    L6:
        return 0.0d;
    }

    private static int getFrame(long r1, int r3, Bitmap r4, boolean r5) {
        Trace.beginSection("RLottieNative#getFrame");
        int r12 = nGetFrame(r1, r3, r4, r5);     // Catch: Throwable -> L6
        Trace.endSection();
        return r12;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static long getFramesCount(String r6, String r7) {
        RLottieNative r62 = createFromFile(r6, r7, null, null, 0, null);
        if (r62 == null) goto L6;
        int r72 = r62.getFrameCount();
        r62.recycle();
        return r72;
    L6:
        return 0;
    }

    private static int[] layerNamesToColors(String[] r3, Map<String, Integer> r4) {
        int[] r0 = new int[r3.length];
        int r1 = 0;
    L4:
        if (r1 >= r3.length) goto L6;
        r0[r1] = r4.get(r3[r1]).intValue();
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private static native long nCreate(String r0, String r1, int[] r2, int[] r3, int r4, String[] r5, int[] r6);

    private static native long nCreateWithJson(String r0, int[] r1, int[] r2, String[] r3, int[] r4);

    private static native void nDestroy(long r0);

    private static native int nGetFrame(long r0, int r2, Bitmap r3, boolean r4);

    public void finalize() {
    L6:
        th = move-exception;
        super.finalize();
        throw th;
    L3:
        if (this.mRecycled.get() == true) goto L8;
        recycle();     // Catch: Throwable -> L6
    L8:
        super.finalize();
    }

    public int getFps() {
        return this.mMetaData[1];
    }

    public int getFrame(int r3, Bitmap r4, boolean r5) {
        checkNotRecycled();
        return getFrame(this.mNativePtr, r3, r4, r5);
    }

    public int getFrameCount() {
        return this.mMetaData[0];
    }

    public void recycle() {
        if (this.mRecycled.compareAndSet(false, true) == false) goto L8;
        long r0 = this.mNativePtr;
        this.mNativePtr = 0;
        if (r0 == 0) goto L9;
        destroy(r0);
        return;
    L9:
        return;
    }
}
