package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import com.google.common.base.Supplier;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
final class AsynchronousMediaCodecAdapter implements MediaCodecAdapter {
    private final AsynchronousMediaCodecCallback asynchronousMediaCodecCallback;
    private final MediaCodecBufferEnqueuer bufferEnqueuer;
    private final MediaCodec codec;
    private boolean codecReleased;
    private final LoudnessCodecController loudnessCodecController;
    private int state;

    /* renamed from: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Factory implements MediaCodecAdapter.Factory {
        private final Supplier<HandlerThread> callbackThreadSupplier;
        private boolean enableSynchronousBufferQueueingWithAsyncCryptoFlag;
        private final Supplier<HandlerThread> queueingThreadSupplier;

        public static /* synthetic */ HandlerThread $r8$lambda$dxogLQ4In6z2OlvcYCXGKljyjA8(int r1) {
            return new HandlerThread(AsynchronousMediaCodecAdapter.access$200(r1));
        }

        public static /* synthetic */ HandlerThread $r8$lambda$ywPSKqV55WpaaeBkvRWSdXOPhIk(int r1) {
            return new HandlerThread(AsynchronousMediaCodecAdapter.access$300(r1));
        }

        public Factory(int r3) {
            this(new AsynchronousMediaCodecAdapter$Factory$$ExternalSyntheticLambda0(r3), new AsynchronousMediaCodecAdapter$Factory$$ExternalSyntheticLambda1(r3));
        }

        public Factory(Supplier<HandlerThread> r1, Supplier<HandlerThread> r2) {
            this.callbackThreadSupplier = r1;
            this.queueingThreadSupplier = r2;
            this.enableSynchronousBufferQueueingWithAsyncCryptoFlag = false;
        }

        private static boolean useSynchronousBufferQueueingWithAsyncCryptoFlag(Format r3) {
            int r0 = Build.VERSION.SDK_INT;
            if (r0 >= 34) goto L6;
            return false;
        L6:
            if (r0 < 35) goto L8;
            return true;
        L8:
            if (MimeTypes.isVideo(r3.sampleMimeType) == true) goto L13;
            return false;
        L13:
            return true;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Factory
        public AsynchronousMediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration r10) {
            String r1 = r10.codecInfo.name;
            AsynchronousMediaCodecAdapter r2 = null;
            TraceUtil.beginSection("createCodec:" + r1);     // Catch: Exception -> L25
            MediaCodec r4 = MediaCodec.createByCodecName(r1);     // Catch: Exception -> L25
        L10:
            e = move-exception;
            Exception r9 = e;
        L27:
            if (r2 != null) goto L30;
            if (r4 == null) goto L38;
            r4.release();
            throw r9;
        L38:
            throw r9;
        L30:
            r2.release();
            throw r9;
        L5:
            if (this.enableSynchronousBufferQueueingWithAsyncCryptoFlag == true) goto L7;
        L12:
            MediaCodecBufferEnqueuer r0 = new AsynchronousMediaCodecBufferEnqueuer(r4, this.queueingThreadSupplier.get());     // Catch: Exception -> L10
            int r12 = 0;
        L13:
            AsynchronousMediaCodecAdapter r3 = new AsynchronousMediaCodecAdapter(r4, this.callbackThreadSupplier.get(), r0, r10.loudnessCodecController, null);     // Catch: Exception -> L10
            TraceUtil.endSection();     // Catch: Exception -> L21
            Surface r92 = r10.surface;     // Catch: Exception -> L21
            if (r92 == null) goto L17;
        L23:
            AsynchronousMediaCodecAdapter.access$100(r3, r10.mediaFormat, r92, r10.crypto, r12);     // Catch: Exception -> L21
            return r3;
        L17:
            if (r10.codecInfo.detachedSurfaceSupported == false) goto L23;
            if (Build.VERSION.SDK_INT < 35) goto L23;
            r12 = r12 | 8;     // Catch: Exception -> L21
        L21:
            e = move-exception;
            r9 = e;
            r2 = r3;
            goto L27
        L7:
            if (useSynchronousBufferQueueingWithAsyncCryptoFlag(r10.format) == false) goto L12;
            r0 = new SynchronousMediaCodecBufferEnqueuer(r4);     // Catch: Exception -> L10
            r12 = 4;
        L25:
            e = move-exception;
            r9 = e;
            r4 = null;
            goto L27
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Factory
        public /* bridge */ /* synthetic */ MediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration r1) {
            return createAdapter(r1);
        }

        public void experimentalSetAsyncCryptoFlagEnabled(boolean r1) {
            this.enableSynchronousBufferQueueingWithAsyncCryptoFlag = r1;
        }
    }

    /* renamed from: $r8$lambda$t8iiVXXtPJTyXCy-9oagKLCt2ec, reason: not valid java name */
    public static /* synthetic */ void m830$r8$lambda$t8iiVXXtPJTyXCy9oagKLCt2ec(AsynchronousMediaCodecAdapter r0, MediaCodecAdapter.OnFrameRenderedListener r1, MediaCodec r2, long r3, long r5) {
        r0.getClass();
        r1.onFrameRendered(r0, r3, r5);
    }

    private AsynchronousMediaCodecAdapter(MediaCodec r1, HandlerThread r2, MediaCodecBufferEnqueuer r3, LoudnessCodecController r4) {
        this.codec = r1;
        this.asynchronousMediaCodecCallback = new AsynchronousMediaCodecCallback(r2);
        this.bufferEnqueuer = r3;
        this.loudnessCodecController = r4;
        this.state = 0;
    }

    public /* synthetic */ AsynchronousMediaCodecAdapter(MediaCodec r1, HandlerThread r2, MediaCodecBufferEnqueuer r3, LoudnessCodecController r4, AnonymousClass1 r5) {
        this(r1, r2, r3, r4);
    }

    public static /* synthetic */ void access$100(AsynchronousMediaCodecAdapter r0, MediaFormat r1, Surface r2, MediaCrypto r3, int r4) {
        r0.initialize(r1, r2, r3, r4);
    }

    public static /* synthetic */ String access$200(int r0) {
        return createQueueingThreadLabel(r0);
    }

    public static /* synthetic */ String access$300(int r0) {
        return createCallbackThreadLabel(r0);
    }

    private static String createCallbackThreadLabel(int r1) {
        return createThreadLabel(r1, "ExoPlayer:MediaCodecAsyncAdapter:");
    }

    private static String createQueueingThreadLabel(int r1) {
        return createThreadLabel(r1, "ExoPlayer:MediaCodecQueueingThread:");
    }

    private static String createThreadLabel(int r1, String r2) {
        StringBuilder r0 = new StringBuilder(r2);
        if (r1 != 1) goto L6;
        r0.append("Audio");
    L10:
        return r0.toString();
    L6:
        if (r1 != 2) goto L8;
        r0.append("Video");
        goto L10
    L8:
        r0.append("Unknown(");
        r0.append(r1);
        r0.append(")");
        goto L10
    }

    private void initialize(MediaFormat r3, Surface r4, MediaCrypto r5, int r6) {
        this.asynchronousMediaCodecCallback.initialize(this.codec);
        TraceUtil.beginSection("configureCodec");
        this.codec.configure(r3, r4, r5, r6);
        TraceUtil.endSection();
        this.bufferEnqueuer.start();
        TraceUtil.beginSection("startCodec");
        this.codec.start();
        TraceUtil.endSection();
        if (Build.VERSION.SDK_INT < 35) goto L7;
        LoudnessCodecController r32 = this.loudnessCodecController;
        if (r32 == null) goto L7;
        r32.addMediaCodec(this.codec);
    L7:
        this.state = 1;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public int dequeueInputBufferIndex() {
        this.bufferEnqueuer.maybeThrowException();
        return this.asynchronousMediaCodecCallback.dequeueInputBufferIndex();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo r2) {
        this.bufferEnqueuer.maybeThrowException();
        return this.asynchronousMediaCodecCallback.dequeueOutputBufferIndex(r2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void detachOutputSurface() {
        this.codec.detachOutputSurface();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void flush() {
        this.bufferEnqueuer.flush();
        this.codec.flush();
        this.asynchronousMediaCodecCallback.flush();
        this.codec.start();
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
        return this.asynchronousMediaCodecCallback.getOutputFormat();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public boolean needsReconfiguration() {
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void queueInputBuffer(int r1, int r2, int r3, long r4, int r6) {
        this.bufferEnqueuer.queueInputBuffer(r1, r2, r3, r4, r6);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void queueSecureInputBuffer(int r1, int r2, CryptoInfo r3, long r4, int r6) {
        this.bufferEnqueuer.queueSecureInputBuffer(r1, r2, r3, r4, r6);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public boolean registerOnBufferAvailableListener(MediaCodecAdapter.OnBufferAvailableListener r1) {
        this.asynchronousMediaCodecCallback.setOnBufferAvailableListener(r1);
        return true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void release() {
    L6:
        th = move-exception;
        if (this.codecReleased == false) goto L54;
    L53:
        throw th;
    L54:
        int r5 = Build.VERSION.SDK_INT;     // Catch: Throwable -> L39
        if (r5 < 30) goto L41;
        if (r5 >= 33) goto L41;
        this.codec.stop();     // Catch: Throwable -> L39
    L41:
        if (r5 < 35) goto L45;
        LoudnessCodecController r0 = this.loudnessCodecController;
        if (r0 == null) goto L45;
        r0.removeMediaCodec(this.codec);
    L45:
        this.codec.release();
        this.codecReleased = true;
    L39:
        th = move-exception;
        if (Build.VERSION.SDK_INT < 35) goto L51;
        LoudnessCodecController r1 = this.loudnessCodecController;
        if (r1 == null) goto L51;
        r1.removeMediaCodec(this.codec);
    L51:
        this.codec.release();
        this.codecReleased = true;
        throw th;
    L4:
        if (this.state != 1) goto L8;
        this.bufferEnqueuer.shutdown();     // Catch: Throwable -> L6
        this.asynchronousMediaCodecCallback.shutdown();     // Catch: Throwable -> L6
    L8:
        this.state = 2;     // Catch: Throwable -> L6
        if (this.codecReleased == false) goto L58;
        return;
    L58:
        int r4 = Build.VERSION.SDK_INT;     // Catch: Throwable -> L16
        if (r4 < 30) goto L18;
        if (r4 >= 33) goto L18;
        this.codec.stop();     // Catch: Throwable -> L16
    L18:
        if (r4 < 35) goto L22;
        LoudnessCodecController r02 = this.loudnessCodecController;
        if (r02 == null) goto L22;
        r02.removeMediaCodec(this.codec);
    L22:
        this.codec.release();
        this.codecReleased = true;
        return;
    L16:
        th = move-exception;
        if (Build.VERSION.SDK_INT < 35) goto L29;
        LoudnessCodecController r12 = this.loudnessCodecController;
        if (r12 == null) goto L29;
        r12.removeMediaCodec(this.codec);
    L29:
        this.codec.release();
        this.codecReleased = true;
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
        this.codec.setOnFrameRenderedListener(new AsynchronousMediaCodecAdapter$$ExternalSyntheticLambda0(this, r3), r4);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setOutputSurface(Surface r1) {
        this.codec.setOutputSurface(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setParameters(Bundle r1) {
        this.bufferEnqueuer.setParameters(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter
    public void setVideoScalingMode(int r1) {
        this.codec.setVideoScalingMode(r1);
    }
}
