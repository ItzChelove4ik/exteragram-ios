package androidx.media3.exoplayer.mediacodec;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Surface;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class SynchronousMediaCodecAdapter implements MediaCodecAdapter {
    private final MediaCodec codec;
    private final LoudnessCodecController loudnessCodecController;

    /* renamed from: androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecAdapter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Factory implements MediaCodecAdapter.Factory {
        public Factory() {
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Factory
        @SuppressLint({"WrongConstant"})
        public MediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration r6) {
            MediaCodec r0 = null;
            MediaCodec r5 = createCodec(r6);     // Catch: RuntimeException -> L18 IOException -> L20
            TraceUtil.beginSection("configureCodec");     // Catch: RuntimeException -> L11 IOException -> L13
            Surface r1 = r6.surface;     // Catch: RuntimeException -> L11 IOException -> L13
            if (r1 == null) goto L7;
        L15:
            int r2 = 0;
        L16:
            r5.configure(r6.mediaFormat, r1, r6.crypto, r2);     // Catch: RuntimeException -> L11 IOException -> L13
            TraceUtil.endSection();     // Catch: RuntimeException -> L11 IOException -> L13
            TraceUtil.beginSection("startCodec");     // Catch: RuntimeException -> L11 IOException -> L13
            r5.start();     // Catch: RuntimeException -> L11 IOException -> L13
            TraceUtil.endSection();     // Catch: RuntimeException -> L11 IOException -> L13
            return new SynchronousMediaCodecAdapter(r5, r6.loudnessCodecController, null);
        L7:
            if (r6.codecInfo.detachedSurfaceSupported == false) goto L15;
            if (Build.VERSION.SDK_INT < 35) goto L15;
            r2 = 8;
        L13:
            e = e;
        L12:
            r0 = r5;
        L21:
            if (r0 == null) goto L23;
            r0.release();
        L23:
            throw e;
        L11:
            e = e;
            goto L12
        L20:
            e = e;
        L18:
            e = e;
            goto L21
        }

        public MediaCodec createCodec(MediaCodecAdapter.Configuration r2) {
            Assertions.checkNotNull(r2.codecInfo);
            String r1 = r2.codecInfo.name;
            TraceUtil.beginSection("createCodec:" + r1);
            MediaCodec r12 = MediaCodec.createByCodecName(r1);
            TraceUtil.endSection();
            return r12;
        }
    }

    public static /* synthetic */ void $r8$lambda$YcXm01yWwhzQB5uuYAW5ZUYz48Q(SynchronousMediaCodecAdapter r0, MediaCodecAdapter.OnFrameRenderedListener r1, MediaCodec r2, long r3, long r5) {
        r0.getClass();
        r1.onFrameRendered(r0, r3, r5);
    }

    private SynchronousMediaCodecAdapter(MediaCodec r2, LoudnessCodecController r3) {
        this.codec = r2;
        this.loudnessCodecController = r3;
        if (Build.VERSION.SDK_INT < 35) goto L7;
        if (r3 == null) goto L8;
        r3.addMediaCodec(r2);
        return;
    L8:
        return;
    }

    public /* synthetic */ SynchronousMediaCodecAdapter(MediaCodec r1, LoudnessCodecController r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public int dequeueInputBufferIndex() {
        return this.codec.dequeueInputBuffer(0);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo r4) {
    L2:
        int r0 = this.codec.dequeueOutputBuffer(r4, 0);
        if (r0 == (-3)) goto L2;
        return r0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void detachOutputSurface() {
        this.codec.detachOutputSurface();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void flush() {
        this.codec.flush();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public ByteBuffer getInputBuffer(int r1) {
        return this.codec.getInputBuffer(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public ByteBuffer getOutputBuffer(int r1) {
        return this.codec.getOutputBuffer(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public MediaFormat getOutputFormat() {
        return this.codec.getOutputFormat();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public boolean needsReconfiguration() {
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void queueInputBuffer(int r1, int r2, int r3, long r4, int r6) {
        this.codec.queueInputBuffer(r1, r2, r3, r4, r6);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void queueSecureInputBuffer(int r1, int r2, CryptoInfo r3, long r4, int r6) {
        this.codec.queueSecureInputBuffer(r1, r2, r3.getFrameworkCryptoInfo(), r4, r6);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void release() {
        int r1 = Build.VERSION.SDK_INT;     // Catch: Throwable -> L9
        if (r1 >= 30) goto L6;
    L11:
        if (r1 < 35) goto L15;
        LoudnessCodecController r0 = this.loudnessCodecController;
        if (r0 == null) goto L15;
        r0.removeMediaCodec(this.codec);
    L15:
        this.codec.release();
        return;
    L6:
        if (r1 >= 33) goto L11;
        this.codec.stop();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        if (Build.VERSION.SDK_INT < 35) goto L22;
        LoudnessCodecController r02 = this.loudnessCodecController;
        if (r02 == null) goto L22;
        r02.removeMediaCodec(this.codec);
    L22:
        this.codec.release();
        throw th;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void releaseOutputBuffer(int r1, long r2) {
        this.codec.releaseOutputBuffer(r1, r2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void releaseOutputBuffer(int r1, boolean r2) {
        this.codec.releaseOutputBuffer(r1, r2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setOnFrameRenderedListener(MediaCodecAdapter.OnFrameRenderedListener r3, Handler r4) {
        this.codec.setOnFrameRenderedListener(new SynchronousMediaCodecAdapter$$ExternalSyntheticLambda0(this, r3), r4);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setOutputSurface(Surface r1) {
        this.codec.setOutputSurface(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setParameters(Bundle r1) {
        this.codec.setParameters(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setVideoScalingMode(int r1) {
        this.codec.setVideoScalingMode(r1);
    }
}
