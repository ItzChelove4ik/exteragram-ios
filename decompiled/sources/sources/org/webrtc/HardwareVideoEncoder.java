package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.FileLog;
import org.webrtc.EglBase14;
import org.webrtc.EncodedImage;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoEncoder;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
class HardwareVideoEncoder implements VideoEncoder {
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final int MAX_ENCODER_Q_SIZE = 2;
    private static final int MAX_VIDEO_FRAMERATE = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final int REQUIRED_RESOLUTION_ALIGNMENT = 16;
    private static final String TAG = "HardwareVideoEncoder";
    private int adjustedBitrate;
    private boolean automaticResizeOn;
    private final BitrateAdjuster bitrateAdjuster;
    private VideoEncoder.Callback callback;
    private MediaCodecWrapper codec;
    private final String codecName;
    private final VideoCodecMimeType codecType;
    private ByteBuffer configBuffer;
    private final ThreadUtils.ThreadChecker encodeThreadChecker;
    private final long forcedKeyFrameNs;
    private int frameSizeBytes;
    private int height;
    private boolean isEncodingStatisticsEnabled;
    private boolean isSemiPlanar;
    private final int keyFrameIntervalSec;
    private long lastKeyFrameNs;
    private final MediaCodecWrapperFactory mediaCodecWrapperFactory;
    private long nextPresentationTimestampUs;
    private final BusyCount outputBuffersBusyCount;
    private final BlockingDeque<EncodedImage.Builder> outputBuilders;
    private Thread outputThread;
    private final ThreadUtils.ThreadChecker outputThreadChecker;
    private final Map<String, String> params;
    private volatile boolean running;
    private final EglBase14.Context sharedContext;
    private volatile Exception shutdownException;
    private int sliceHeight;
    private int stride;
    private final Integer surfaceColorFormat;
    private final GlRectDrawer textureDrawer;
    private EglBase14 textureEglBase;
    private Surface textureInputSurface;
    private boolean useSurfaceMode;
    private final VideoFrameDrawer videoFrameDrawer;
    private int width;
    private final Integer yuvColorFormat;

    /* renamed from: org.webrtc.HardwareVideoEncoder$1, reason: invalid class name */
    public class AnonymousClass1 extends Thread {
        final /* synthetic */ HardwareVideoEncoder this$0;

        public AnonymousClass1(HardwareVideoEncoder r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
        L2:
            boolean r0 = HardwareVideoEncoder.m22354$$Nest$fgetrunning(this.this$0);
            HardwareVideoEncoder r1 = this.this$0;
            if (r0 == false) goto L5;
            r1.deliverEncodedImage();
            goto L2
        L5:
            HardwareVideoEncoder.m22355$$Nest$mreleaseCodecOnOutputThread(r1);
        }
    }

    public static class BusyCount {
        private int count;
        private final Object countLock;

        private BusyCount() {
            this.countLock = new Object();
        }

        public /* synthetic */ BusyCount(HardwareVideoEncoderIA r1) {
            this();
        }

        public void decrement() {
            Object r0 = this.countLock;
            monitor-enter(r0);
            int r1 = this.count - 1;
            this.count = r1;     // Catch: Throwable -> L7
            if (r1 != 0) goto L9;
            this.countLock.notifyAll();     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        public void increment() {
            Object r0 = this.countLock;
            monitor-enter(r0);
            this.count++;
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        public void waitForZero() {
            Object r0 = this.countLock;
            monitor-enter(r0);
            boolean r1 = false;
        L21:
        L9:
            th = move-exception;
            throw th;
        L6:
            if (this.count <= 0) goto L13;
            this.countLock.wait();     // Catch: Throwable -> L9 InterruptedException -> L11
        L11:
            e = move-exception;
            Logging.e(HardwareVideoEncoder.TAG, "Interrupted while waiting on busy count", e);     // Catch: Throwable -> L9
            r1 = true;
            goto L21
        L13:
            monitor-exit(r0);     // Catch: Throwable -> L9
            if (r1 == false) goto L27;
            Thread.currentThread().interrupt();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$y4IJntadnwqegO9qiJSAGAUhlDo(HardwareVideoEncoder r0, int r1) {
        r0.lambda$deliverEncodedImage$0(r1);
    }

    /* renamed from: -$$Nest$fgetrunning, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22354$$Nest$fgetrunning(HardwareVideoEncoder r0) {
        return r0.running;
    }

    /* renamed from: -$$Nest$mreleaseCodecOnOutputThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22355$$Nest$mreleaseCodecOnOutputThread(HardwareVideoEncoder r0) {
        r0.releaseCodecOnOutputThread();
    }

    public HardwareVideoEncoder(MediaCodecWrapperFactory r4, String r5, VideoCodecMimeType r6, Integer r7, Integer r8, Map<String, String> r9, int r10, int r11, BitrateAdjuster r12, EglBase14.Context r13) {
        this.textureDrawer = new GlRectDrawer();
        this.videoFrameDrawer = new VideoFrameDrawer();
        this.outputBuilders = new LinkedBlockingDeque();
        ThreadUtils.ThreadChecker r0 = new ThreadUtils.ThreadChecker();
        this.encodeThreadChecker = r0;
        this.outputThreadChecker = new ThreadUtils.ThreadChecker();
        this.outputBuffersBusyCount = new BusyCount(null);
        this.mediaCodecWrapperFactory = r4;
        this.codecName = r5;
        this.codecType = r6;
        this.surfaceColorFormat = r7;
        this.yuvColorFormat = r8;
        this.params = r9;
        this.keyFrameIntervalSec = r10;
        this.forcedKeyFrameNs = TimeUnit.MILLISECONDS.toNanos(r11);
        this.bitrateAdjuster = r12;
        this.sharedContext = r13;
        r0.detachThread();
    }

    private boolean canUseSurface() {
        if (this.sharedContext != null) goto L5;
        return false;
    L5:
        if (this.surfaceColorFormat == null) goto L10;
        return true;
    L10:
        return false;
    }

    private Thread createOutputThread() {
        return new AnonymousClass1(this);
    }

    private VideoCodecStatus encodeByteBuffer(VideoFrame r12, VideoFrame.Buffer r13, int r14) {
        this.encodeThreadChecker.checkIsOnValidThread();
        long r8 = (r12.getTimestampNs() + 500) / 1000;
        int r5 = this.codec.dequeueInputBuffer(0);     // Catch: IllegalStateException -> L23
        if (r5 != (-1)) goto L26;
        Logging.d(TAG, "Dropped frame, no input buffers available");
        return VideoCodecStatus.NO_OUTPUT;
    L26:
        ByteBuffer r142 = this.codec.getInputBuffer(r5);     // Catch: IllegalStateException -> L20
        if (r142.capacity() >= this.frameSizeBytes) goto L13;
        Logging.e(TAG, "Input buffer size: " + r142.capacity() + " is smaller than frame size: " + this.frameSizeBytes);
        return VideoCodecStatus.ERROR;
    L13:
        fillInputBuffer(r142, r12.getBuffer());
        this.codec.queueInputBuffer(r5, 0, this.frameSizeBytes, r8, 0);     // Catch: IllegalStateException -> L17
        return VideoCodecStatus.OK;
    L17:
        e = move-exception;
        Logging.e(TAG, "queueInputBuffer failed", e);
        return VideoCodecStatus.ERROR;
    L20:
        e = move-exception;
        Logging.e(TAG, "getInputBuffer with index=" + r5 + " failed", e);
        return VideoCodecStatus.ERROR;
    L23:
        e = move-exception;
        Logging.e(TAG, "dequeueInputBuffer failed", e);
        return VideoCodecStatus.ERROR;
    }

    private VideoCodecStatus encodeTextureBuffer(VideoFrame r5, long r6) {
        this.encodeThreadChecker.checkIsOnValidThread();
        this.textureEglBase.makeCurrent();     // Catch: RuntimeException -> L6
        GLES20.glClear(16384);     // Catch: RuntimeException -> L6
        VideoFrame r0 = new VideoFrame(r5.getBuffer(), 0, r5.getTimestampNs());     // Catch: RuntimeException -> L6
        this.videoFrameDrawer.drawFrame(r0, this.textureDrawer, null);     // Catch: RuntimeException -> L6
        this.textureEglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(r6), false);     // Catch: RuntimeException -> L6
        return VideoCodecStatus.OK;
    L6:
        e = move-exception;
        Logging.e(TAG, "encodeTexture failed", e);
        return VideoCodecStatus.ERROR;
    }

    private VideoCodecStatus initEncodeInternal() {
        this.encodeThreadChecker.checkIsOnValidThread();
        this.nextPresentationTimestampUs = 0;
        this.lastKeyFrameNs = -1;
        this.isEncodingStatisticsEnabled = false;
        this.codec = this.mediaCodecWrapperFactory.createByCodecName(this.codecName);     // Catch: Throwable -> L45
        if (this.useSurfaceMode == false) goto L7;
        Integer r3 = this.surfaceColorFormat;
    L8:
        int r32 = r3.intValue();
        MediaFormat r4 = MediaFormat.createVideoFormat(this.codecType.mimeType(), this.width, this.height);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        r4.setInteger("bitrate", this.adjustedBitrate);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        r4.setInteger("bitrate-mode", 2);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        r4.setInteger("color-format", r32);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        r4.setFloat("frame-rate", (float) this.bitrateAdjuster.getAdjustedFramerateFps());     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        r4.setInteger("i-frame-interval", this.keyFrameIntervalSec);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        if (this.codecType != VideoCodecMimeType.H264) goto L32;
        String r33 = this.params.get("profile-level-id");     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        if (r33 != null) goto L15;
        r33 = "42e01f";
    L15:
        int r6 = r33.hashCode();     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        if (r6 == 1537948542) goto L28;
        if (r6 == 1595523974) goto L21;
    L30:
        Logging.w(TAG, "Unknown profile level id: ".concat(r33));     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        goto L32
    L21:
        if (r33.equals("640c1f") == false) goto L30;
        r4.setInteger("profile", 8);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        r4.setInteger("level", 256);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        goto L32
    L28:
        if (r33.equals("42e01f") == false) goto L30;
    L32:
        if (this.codecName.equals("c2.google.av1.encoder") == false) goto L35;
        r4.setInteger("vendor.google-av1enc.encoding-preset.int32.value", 1);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
    L35:
        if (isEncodingStatisticsSupported() == false) goto L37;
        r4.setInteger("video-encoding-statistics-level", 1);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        this.isEncodingStatisticsEnabled = true;     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
    L37:
        Logging.d(TAG, "Format: " + r4);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        this.codec.configure(r4, null, null, 1);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        if (this.useSurfaceMode == false) goto L40;
        this.textureEglBase = EglBase.createEgl14(this.sharedContext, EglBase.CONFIG_RECORDABLE);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        Surface r1 = this.codec.createInputSurface();     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        this.textureInputSurface = r1;     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        this.textureEglBase.createSurface(r1);     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        this.textureEglBase.makeCurrent();     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
    L40:
        updateInputFormat(this.codec.getInputFormat());     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        this.codec.start();     // Catch: IllegalStateException -> L23 IllegalArgumentException -> L25
        this.running = true;
        this.outputThreadChecker.detachThread();
        Thread r0 = createOutputThread();
        this.outputThread = r0;
        r0.start();
        return VideoCodecStatus.OK;
    L25:
        e = e;
    L43:
        Logging.e(TAG, "initEncodeInternal failed", e);
        release();
        return VideoCodecStatus.FALLBACK_SOFTWARE;
    L23:
        e = e;
        goto L43
    L7:
        r3 = this.yuvColorFormat;
    L45:
        Logging.e(TAG, "Cannot create media encoder " + this.codecName);
        return VideoCodecStatus.FALLBACK_SOFTWARE;
    }

    private /* synthetic */ void lambda$deliverEncodedImage$0(int r3) {
        this.codec.releaseOutputBuffer(r3, false);     // Catch: Exception -> L4
    L6:
        this.outputBuffersBusyCount.decrement();
        return;
    L4:
        e = move-exception;
        Logging.e(TAG, "releaseOutputBuffer failed", e);
        goto L6
    }

    private void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        this.outputBuffersBusyCount.waitForZero();
        this.codec.stop();     // Catch: Exception -> L5
    L15:
        this.codec.release();     // Catch: Exception -> L9
    L11:
        this.configBuffer = null;
        Logging.d(TAG, "Release on output thread done");
        return;
    L9:
        e = move-exception;
        Logging.e(TAG, "Media encoder release failed", e);
        this.shutdownException = e;
    L5:
        e = move-exception;
        Logging.e(TAG, "Media encoder stop failed", e);
        goto L15
    }

    private void requestKeyFrame(long r4) {
        this.encodeThreadChecker.checkIsOnValidThread();
        Bundle r0 = new Bundle();     // Catch: IllegalStateException -> L6
        r0.putInt("request-sync", 0);     // Catch: IllegalStateException -> L6
        this.codec.setParameters(r0);     // Catch: IllegalStateException -> L6
        this.lastKeyFrameNs = r4;
        return;
    L6:
        e = move-exception;
        Logging.e(TAG, "requestKeyFrame failed", e);
    }

    private VideoCodecStatus resetCodec(int r3, int r4, boolean r5) {
        FileLog.d("resetCodec " + r3 + "x" + r4);
        this.encodeThreadChecker.checkIsOnValidThread();
        VideoCodecStatus r0 = release();
        if (r0 == VideoCodecStatus.OK) goto L5;
        return r0;
    L5:
        this.width = r3;
        this.height = r4;
        this.useSurfaceMode = r5;
        return initEncodeInternal();
    }

    private boolean shouldForceKeyFrame(long r5) {
        this.encodeThreadChecker.checkIsOnValidThread();
        long r0 = this.forcedKeyFrameNs;
        if (r0 > 0) goto L5;
        return false;
    L5:
        if (r5 <= (this.lastKeyFrameNs + r0)) goto L10;
        return true;
    L10:
        return false;
    }

    private VideoCodecStatus updateBitrate() {
        this.outputThreadChecker.checkIsOnValidThread();
        this.adjustedBitrate = this.bitrateAdjuster.getAdjustedBitrateBps();
        Bundle r0 = new Bundle();     // Catch: IllegalStateException -> L5
        r0.putInt("video-bitrate", this.adjustedBitrate);     // Catch: IllegalStateException -> L5
        this.codec.setParameters(r0);     // Catch: IllegalStateException -> L5
        return VideoCodecStatus.OK;
    L5:
        e = move-exception;
        Logging.e(TAG, "updateBitrate failed", e);
        return VideoCodecStatus.ERROR;
    }

    private void updateInputFormat(MediaFormat r5) {
        this.stride = this.width;
        this.sliceHeight = this.height;
        if (r5 != null) goto L5;
    L10:
        boolean r0 = isSemiPlanar(this.yuvColorFormat.intValue());
        this.isSemiPlanar = r0;
        if (r0 == false) goto L13;
        int r02 = (this.height + 1) / 2;
        int r1 = this.sliceHeight;
        int r2 = this.stride;
        this.frameSizeBytes = (r1 * r2) + (r02 * r2);
    L14:
        Logging.d(TAG, "updateInputFormat format: " + r5 + " stride: " + this.stride + " sliceHeight: " + this.sliceHeight + " isSemiPlanar: " + this.isSemiPlanar + " frameSizeBytes: " + this.frameSizeBytes);
        return;
    L13:
        int r03 = this.stride;
        int r22 = this.sliceHeight;
        this.frameSizeBytes = (r22 * r03) + ((((r22 + 1) / 2) * ((r03 + 1) / 2)) * 2);
        goto L14
    L5:
        if (r5.containsKey("stride") == false) goto L8;
        int r04 = r5.getInteger("stride");
        this.stride = r04;
        this.stride = Math.max(r04, this.width);
    L8:
        if (r5.containsKey("slice-height") == false) goto L10;
        int r05 = r5.getInteger("slice-height");
        this.sliceHeight = r05;
        this.sliceHeight = Math.max(r05, this.height);
        goto L10
    }

    public void deliverEncodedImage() {
        this.outputThreadChecker.checkIsOnValidThread();
        MediaCodec.BufferInfo r4 = new MediaCodec.BufferInfo();     // Catch: IllegalStateException -> L9
        int r5 = this.codec.dequeueOutputBuffer(r4, 100000);     // Catch: IllegalStateException -> L9
        if (r5 < 0) goto L6;
        ByteBuffer r6 = this.codec.getOutputBuffer(r5);     // Catch: IllegalStateException -> L9
        r6.position(r4.offset);     // Catch: IllegalStateException -> L9
        r6.limit(r4.offset + r4.size);     // Catch: IllegalStateException -> L9
        if ((r4.flags & 2) == 0) goto L21;
        Logging.d(TAG, "Config frame generated. Offset: " + r4.offset + ". Size: " + r4.size);     // Catch: IllegalStateException -> L9
        int r0 = r4.size;     // Catch: IllegalStateException -> L9
        if (r0 <= 0) goto L52;
        VideoCodecMimeType r2 = this.codecType;     // Catch: IllegalStateException -> L9
        if (r2 != VideoCodecMimeType.H264) goto L18;
    L19:
        ByteBuffer r02 = ByteBuffer.allocateDirect(r0);     // Catch: IllegalStateException -> L9
        this.configBuffer = r02;     // Catch: IllegalStateException -> L9
        r02.put(r6);     // Catch: IllegalStateException -> L9
        return;
    L18:
        if (r2 == VideoCodecMimeType.H265) goto L19;
        return;
    L52:
        return;
    L21:
        this.bitrateAdjuster.reportEncodedFrame(r4.size);     // Catch: IllegalStateException -> L9
        if (this.adjustedBitrate == this.bitrateAdjuster.getAdjustedBitrateBps()) goto L24;
        updateBitrate();     // Catch: IllegalStateException -> L9
    L24:
        boolean r7 = true;
        if ((r4.flags & 1) != 0) goto L28;
        r7 = false;
    L28:
        if (r7 == false) goto L30;
        Logging.d(TAG, "Sync frame generated");     // Catch: IllegalStateException -> L9
    L30:
        HardwareVideoEncoder$$ExternalSyntheticLambda0 r9 = null;
        if (this.isEncodingStatisticsEnabled == false) goto L37;
        MediaFormat r3 = this.codec.getOutputFormat(r5);     // Catch: IllegalStateException -> L9
        if (r3 == null) goto L37;
        if (r3.containsKey("video-qp-average") == false) goto L37;
        Integer r03 = Integer.valueOf(r3.getInteger("video-qp-average"));     // Catch: IllegalStateException -> L9
    L38:
        if (r7 == true) goto L40;
    L42:
        ByteBuffer r22 = r6.slice();     // Catch: IllegalStateException -> L9
        this.outputBuffersBusyCount.increment();     // Catch: IllegalStateException -> L9
        r9 = new HardwareVideoEncoder$$ExternalSyntheticLambda0(this, r5);     // Catch: IllegalStateException -> L9
    L43:
        if (r7 == false) goto L45;
        EncodedImage.FrameType r32 = EncodedImage.FrameType.VideoFrameKey;     // Catch: IllegalStateException -> L9
    L46:
        EncodedImage.Builder r42 = this.outputBuilders.poll();     // Catch: IllegalStateException -> L9
        r42.setBuffer(r22, r9);     // Catch: IllegalStateException -> L9
        r42.setFrameType(r32);     // Catch: IllegalStateException -> L9
        r42.setQp(r03);     // Catch: IllegalStateException -> L9
        EncodedImage r04 = r42.createEncodedImage();     // Catch: IllegalStateException -> L9
        this.callback.onEncodedFrame(r04, new VideoEncoder.CodecSpecificInfo());     // Catch: IllegalStateException -> L9
        r04.release();     // Catch: IllegalStateException -> L9
        return;
    L45:
        r32 = EncodedImage.FrameType.VideoFrameDelta;     // Catch: IllegalStateException -> L9
        goto L46
    L40:
        if (this.configBuffer == null) goto L42;
        Logging.d(TAG, "Prepending config buffer of size " + this.configBuffer.capacity() + " to output buffer with offset " + r4.offset + ", size " + r4.size);     // Catch: IllegalStateException -> L9
        r22 = ByteBuffer.allocateDirect(r4.size + this.configBuffer.capacity());     // Catch: IllegalStateException -> L9
        this.configBuffer.rewind();     // Catch: IllegalStateException -> L9
        r22.put(this.configBuffer);     // Catch: IllegalStateException -> L9
        r22.put(r6);     // Catch: IllegalStateException -> L9
        r22.rewind();     // Catch: IllegalStateException -> L9
        this.codec.releaseOutputBuffer(r5, false);     // Catch: IllegalStateException -> L9
    L37:
        r03 = null;
        goto L38
    L6:
        if (r5 != (-3)) goto L51;
        this.outputBuffersBusyCount.waitForZero();     // Catch: IllegalStateException -> L9
        return;
    L51:
        return;
    L9:
        e = move-exception;
        Logging.e(TAG, "deliverOutput failed", e);
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus encode(VideoFrame r9, VideoEncoder.EncodeInfo r10) {
        this.encodeThreadChecker.checkIsOnValidThread();
        if (this.codec == null) goto L5;
        VideoFrame.Buffer r0 = r9.getBuffer();
        boolean r1 = r9.getBuffer() instanceof VideoFrame.TextureBuffer;
        int r2 = r9.getBuffer().getWidth();
        int r3 = r9.getBuffer().getHeight();
        int r5 = 0;
        if (canUseSurface() == false) goto L10;
        if (r1 == false) goto L10;
        boolean r12 = true;
    L12:
        if (r2 == this.width) goto L14;
    L17:
        VideoCodecStatus r13 = resetCodec(r2, r3, r12);
        if (r13 == VideoCodecStatus.OK) goto L21;
        return r13;
    L21:
        if (this.outputBuilders.size() <= 2) goto L24;
        Logging.e(TAG, "Dropped frame, encoder queue full");
        return VideoCodecStatus.NO_OUTPUT;
    L24:
        EncodedImage.FrameType[] r102 = r10.frameTypes;
        int r14 = r102.length;
        boolean r32 = false;
    L25:
        if (r5 >= r14) goto L30;
        if (r102[r5] != EncodedImage.FrameType.VideoFrameKey) goto L29;
        r32 = true;
    L29:
        r5 = r5 + 1;
        goto L25
    L30:
        if (r32 == false) goto L32;
    L33:
        requestKeyFrame(r9.getTimestampNs());
    L34:
        int r103 = ((r0.getHeight() * r0.getWidth()) * 3) / 2;
        this.outputBuilders.offer(EncodedImage.builder().setCaptureTimeNs(r9.getTimestampNs()).setEncodedWidth(r9.getBuffer().getWidth()).setEncodedHeight(r9.getBuffer().getHeight()).setRotation(r9.getRotation()));
        long r15 = this.nextPresentationTimestampUs;
        this.nextPresentationTimestampUs += (long) (1000000.0d / this.bitrateAdjuster.getAdjustedFramerateFps());
        if (this.useSurfaceMode == false) goto L37;
        VideoCodecStatus r92 = encodeTextureBuffer(r9, r15);
    L39:
        if (r92 == VideoCodecStatus.OK) goto L41;
        this.outputBuilders.pollLast();
    L41:
        return r92;
    L37:
        r92 = encodeByteBuffer(r9, r0, r103);
        goto L39
    L32:
        if (shouldForceKeyFrame(r9.getTimestampNs()) == false) goto L34;
    L14:
        if (r3 != this.height) goto L17;
        if (r12 == this.useSurfaceMode) goto L21;
    L10:
        r12 = false;
        goto L12
    L5:
        return VideoCodecStatus.UNINITIALIZED;
    }

    public void fillInputBuffer(ByteBuffer r26, VideoFrame.Buffer r27) {
        VideoFrame.I420Buffer r1 = r27.toI420();
        if (this.isSemiPlanar == false) goto L5;
        YuvHelper.I420ToNV12(r1.getDataY(), r1.getStrideY(), r1.getDataU(), r1.getStrideU(), r1.getDataV(), r1.getStrideV(), r26, r1.getWidth(), r1.getHeight(), this.stride, this.sliceHeight);
    L6:
        r1.release();
        return;
    L5:
        YuvHelper.I420Copy(r1.getDataY(), r1.getStrideY(), r1.getDataU(), r1.getStrideU(), r1.getDataV(), r1.getStrideV(), r26, r1.getWidth(), r1.getHeight(), this.stride, this.sliceHeight);
        goto L6
    }

    @Override // org.webrtc.VideoEncoder
    public VideoEncoder.EncoderInfo getEncoderInfo() {
        return new VideoEncoder.EncoderInfo(16, false);
    }

    @Override // org.webrtc.VideoEncoder
    public String getImplementationName() {
        return this.codecName;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoEncoder.ScalingSettings getScalingSettings() {
        if (this.automaticResizeOn == false) goto L13;
        VideoCodecMimeType r2 = this.codecType;
        if (r2 != VideoCodecMimeType.VP8) goto L9;
        return new VideoEncoder.ScalingSettings(29, 95);
    L9:
        if (r2 != VideoCodecMimeType.H264) goto L13;
        return new VideoEncoder.ScalingSettings(24, 37);
    L13:
        return VideoEncoder.ScalingSettings.OFF;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus initEncode(VideoEncoder.Settings r5, VideoEncoder.Callback r6) {
        this.encodeThreadChecker.checkIsOnValidThread();
        this.callback = r6;
        this.automaticResizeOn = r5.automaticResizeOn;
        this.width = r5.width;
        this.height = r5.height;
        this.useSurfaceMode = canUseSurface();
        int r62 = r5.startBitrate;
        if (r62 == 0) goto L7;
        int r0 = r5.maxFramerate;
        if (r0 == 0) goto L7;
        this.bitrateAdjuster.setTargets(r62 * 1000, r0);
    L7:
        this.adjustedBitrate = this.bitrateAdjuster.getAdjustedBitrateBps();
        Logging.d(TAG, "initEncode name: " + this.codecName + " type: " + this.codecType + " width: " + this.width + " height: " + this.height + " framerate_fps: " + r5.maxFramerate + " bitrate_kbps: " + r5.startBitrate + " surface mode: " + this.useSurfaceMode);
        return initEncodeInternal();
    }

    public boolean isEncodingStatisticsSupported() {
        VideoCodecMimeType r0 = this.codecType;
        if (r0 != VideoCodecMimeType.VP8) goto L5;
    L15:
        return false;
    L5:
        if (r0 == VideoCodecMimeType.VP9) goto L15;
        MediaCodecInfo r02 = this.codec.getCodecInfo();
        if (r02 != null) goto L10;
        return false;
    L10:
        MediaCodecInfo.CodecCapabilities r3 = r02.getCapabilitiesForType(this.codecType.mimeType());
        if (r3 != null) goto L14;
        return false;
    L14:
        return r3.isFeatureSupported("encoding-statistics");
    }

    public boolean isSemiPlanar(int r1) {
        if (r1 != 19) goto L5;
        return false;
    L5:
        if (r1 != 21) goto L7;
        return true;
    L7:
        if (r1 != 2141391872) goto L9;
        return true;
    L9:
        if (r1 == 2141391876) goto L18;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unsupported colorFormat: ", r1);
        return false;
    L18:
        return true;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus release() {
        this.encodeThreadChecker.checkIsOnValidThread();
        if (this.outputThread != null) goto L5;
        VideoCodecStatus r0 = VideoCodecStatus.OK;
    L12:
        this.textureDrawer.release();
        this.videoFrameDrawer.release();
        EglBase14 r1 = this.textureEglBase;
        if (r1 == null) goto L15;
        r1.release();
        this.textureEglBase = null;
    L15:
        Surface r12 = this.textureInputSurface;
        if (r12 == null) goto L18;
        r12.release();
        this.textureInputSurface = null;
    L18:
        this.outputBuilders.clear();
        this.codec = null;
        this.outputThread = null;
        this.encodeThreadChecker.detachThread();
        return r0;
    L5:
        this.running = false;
        if (ThreadUtils.joinUninterruptibly(this.outputThread, 5000) == true) goto L9;
        Logging.e(TAG, "Media encoder release timeout");
        r0 = VideoCodecStatus.TIMEOUT;
        goto L12
    L9:
        if (this.shutdownException == null) goto L11;
        Logging.e(TAG, "Media encoder release exception", this.shutdownException);
        r0 = VideoCodecStatus.ERROR;
        goto L12
    L11:
        r0 = VideoCodecStatus.OK;
        goto L12
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation r3, int r4) {
        this.encodeThreadChecker.checkIsOnValidThread();
        if (r4 <= 30) goto L5;
        r4 = 30;
    L5:
        this.bitrateAdjuster.setTargets(r3.getSum(), r4);
        return VideoCodecStatus.OK;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus setRates(VideoEncoder.RateControlParameters r4) {
        this.encodeThreadChecker.checkIsOnValidThread();
        this.bitrateAdjuster.setTargets(r4.bitrate.getSum(), r4.framerateFps);
        return VideoCodecStatus.OK;
    }
}
