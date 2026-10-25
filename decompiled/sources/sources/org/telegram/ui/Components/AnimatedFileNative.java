package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.os.Trace;
import java.util.concurrent.atomic.AtomicBoolean;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.AnimatedFileDrawableStream;
import org.telegram.messenger.BuildVars;

/* loaded from: classes3.dex */
public class AnimatedFileNative {
    private final int[] mMetaData;
    private long mNativePtr;
    private final AtomicBoolean mRecycled;

    private AnimatedFileNative(long r3, int[] r5) {
        this.mRecycled = new AtomicBoolean(false);
        this.mNativePtr = r3;
        this.mMetaData = r5;
    }

    private void checkNotDestroyed() {
        if (this.mRecycled.get() == true) goto L5;
        return;
    L5:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L10;
        Segment$$ExternalSyntheticBUOutline1.m("Called method on a destroyed AnimatedFileNative instance");
        return;
    }

    private static long createDecoder(String r1, int[] r2, int r3, long r4, AnimatedFileDrawableStream r6, boolean r7) {
        Trace.beginSection("AnimatedFileNative#createDecoder");
        long r12 = nCreateDecoder(r1, r2, r3, r4, r6, r7);     // Catch: Throwable -> L6
        Trace.endSection();
        return r12;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static AnimatedFileNative createDecoderFrom(String r0, int[] r1, int r2, long r3, AnimatedFileDrawableStream r5, boolean r6) {
        long r22 = createDecoder(r0, r1, r2, r3, r5, r6);
        if (r22 != 0) goto L7;
        return null;
    L7:
        return new AnimatedFileNative(r22, r1);
    }

    private static void destroyDecoder(long r1) {
        Trace.beginSection("AnimatedFileNative#destroyDecoder");
        nDestroyDecoder(r1);     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    private static int getFrameAtTime(long r1, long r3, Bitmap r5, int[] r6) {
        Trace.beginSection("AnimatedFileNative#getFrameAtTime");
        int r12 = nGetFrameAtTime(r1, r3, r5, r6);     // Catch: Throwable -> L6
        Trace.endSection();
        return r12;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    private static int getVideoFrame(long r1, Bitmap r3, int[] r4, boolean r5, float r6, float r7, boolean r8) {
        Trace.beginSection("AnimatedFileNative#getVideoFrame");
        int r12 = nGetVideoFrame(r1, r3, r4, r5, r6, r7, r8);     // Catch: Throwable -> L6
        Trace.endSection();
        return r12;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static void getVideoInfo(String r1, int[] r2, long r3) {
        Trace.beginSection("AnimatedFileNative#getVideoInfo");
        nGetVideoInfo(r1, r2, r3);     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    private static native long nCreateDecoder(String r0, int[] r1, int r2, long r3, Object r5, boolean r6);

    private static native void nDestroyDecoder(long r0);

    private static native int nGetFrameAtTime(long r0, long r2, Bitmap r4, int[] r5);

    private static native int nGetVideoFrame(long r0, Bitmap r2, int[] r3, boolean r4, float r5, float r6, boolean r7);

    private static native void nGetVideoInfo(String r0, int[] r1, long r2);

    private static native void nPrepareToSeek(long r0);

    private static native void nSeekToMs(long r0, long r2, int[] r4, boolean r5);

    private static native void nStopDecoder(long r0);

    private static void prepareToSeek(long r1) {
        Trace.beginSection("AnimatedFileNative#prepareToSeek");
        nPrepareToSeek(r1);     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    private static void seekToMs(long r1, long r3, int[] r5, boolean r6) {
        Trace.beginSection("AnimatedFileNative#seekToMs");
        nSeekToMs(r1, r3, r5, r6);     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    private static void stopDecoder(long r1) {
        Trace.beginSection("AnimatedFileNative#stopDecoder");
        nStopDecoder(r1);     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

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

    public int getFrameAtTime(long r7, Bitmap r9) {
        checkNotDestroyed();
        return getFrameAtTime(this.mNativePtr, r7, r9, this.mMetaData);
    }

    public int getVideoFrame(Bitmap r9, boolean r10, float r11, float r12, boolean r13) {
        checkNotDestroyed();
        return getVideoFrame(this.mNativePtr, r9, this.mMetaData, r10, r11, r12, r13);
    }

    public boolean isLastFrameOpaque() {
        if (this.mMetaData[6] != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isStaticVideoDetected() {
        if (this.mMetaData[7] != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public void prepareToSeek() {
        checkNotDestroyed();
        prepareToSeek(this.mNativePtr);
    }

    public void recycle() {
        if (this.mRecycled.compareAndSet(false, true) == false) goto L8;
        long r0 = this.mNativePtr;
        this.mNativePtr = 0;
        if (r0 == 0) goto L9;
        destroyDecoder(r0);
        return;
    L9:
        return;
    }

    public void seekToMs(long r7, boolean r9) {
        checkNotDestroyed();
        seekToMs(this.mNativePtr, r7, this.mMetaData, r9);
    }

    public void stopDecoder() {
        checkNotDestroyed();
        stopDecoder(this.mNativePtr);
    }
}
