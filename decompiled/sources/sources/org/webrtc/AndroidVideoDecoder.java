package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import androidx.camera.core.UseCase$$ExternalSyntheticBUOutline0;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.FileLog;
import org.webrtc.EglBase;
import org.webrtc.EncodedImage;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoDecoder;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
class AndroidVideoDecoder implements VideoDecoder, VideoSink {
    private static final int DEQUEUE_INPUT_TIMEOUT_US = 500000;
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "AndroidVideoDecoder";
    private VideoDecoder.Callback callback;
    private MediaCodecWrapper codec;
    private final String codecName;
    private final VideoCodecMimeType codecType;
    private int colorFormat;
    private ThreadUtils.ThreadChecker decoderThreadChecker;
    private final Object dimensionLock;
    private final BlockingDeque<FrameInfo> frameInfos;
    private boolean hasDecodedFirstFrame;
    private int height;
    private boolean keyFrameRequired;
    private final MediaCodecWrapperFactory mediaCodecWrapperFactory;
    private Thread outputThread;
    private ThreadUtils.ThreadChecker outputThreadChecker;
    private DecodedTextureMetadata renderedTextureMetadata;
    private final Object renderedTextureMetadataLock;
    private volatile boolean running;
    private final EglBase.Context sharedContext;
    private volatile Exception shutdownException;
    private int sliceHeight;
    private int stride;
    private Surface surface;
    private SurfaceTextureHelper surfaceTextureHelper;
    private int width;

    /* renamed from: org.webrtc.AndroidVideoDecoder$1, reason: invalid class name */
    public class AnonymousClass1 extends Thread {
        final /* synthetic */ AndroidVideoDecoder this$0;

        public AnonymousClass1(AndroidVideoDecoder r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AndroidVideoDecoder.m22267$$Nest$fputoutputThreadChecker(this.this$0, new ThreadUtils.ThreadChecker());
        L3:
            boolean r0 = AndroidVideoDecoder.m22266$$Nest$fgetrunning(this.this$0);
            AndroidVideoDecoder r1 = this.this$0;
            if (r0 == false) goto L6;
            r1.deliverDecodedFrame();
            goto L3
        L6:
            AndroidVideoDecoder.m22268$$Nest$mreleaseCodecOnOutputThread(r1);
        }
    }

    public static class DecodedTextureMetadata {
        final Integer decodeTimeMs;
        final long presentationTimestampUs;

        public DecodedTextureMetadata(long r1, Integer r3) {
            this.presentationTimestampUs = r1;
            this.decodeTimeMs = r3;
        }
    }

    public static class FrameInfo {
        final long decodeStartTimeMs;
        final int rotation;

        public FrameInfo(long r1, int r3) {
            this.decodeStartTimeMs = r1;
            this.rotation = r3;
        }
    }

    /* renamed from: -$$Nest$fgetrunning, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22266$$Nest$fgetrunning(AndroidVideoDecoder r0) {
        return r0.running;
    }

    /* renamed from: -$$Nest$fputoutputThreadChecker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22267$$Nest$fputoutputThreadChecker(AndroidVideoDecoder r0, ThreadUtils.ThreadChecker r1) {
        r0.outputThreadChecker = r1;
    }

    /* renamed from: -$$Nest$mreleaseCodecOnOutputThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22268$$Nest$mreleaseCodecOnOutputThread(AndroidVideoDecoder r0) {
        r0.releaseCodecOnOutputThread();
    }

    public AndroidVideoDecoder(MediaCodecWrapperFactory r3, String r4, VideoCodecMimeType r5, int r6, EglBase.Context r7) {
        this.dimensionLock = new Object();
        this.renderedTextureMetadataLock = new Object();
        if (isSupportedColorFormat(r6) == false) goto L6;
        Logging.d(TAG, "ctor name: " + r4 + " type: " + r5 + " color format: " + r6 + " context: " + r7);
        this.mediaCodecWrapperFactory = r3;
        this.codecName = r4;
        this.codecType = r5;
        this.colorFormat = r6;
        this.sharedContext = r7;
        this.frameInfos = new LinkedBlockingDeque();
        return;
    L6:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unsupported color format: ", r6);
        throw null;
    }

    private VideoFrame.Buffer copyI420Buffer(ByteBuffer r17, int r18, int r19, int r20, int r21) {
        if ((r18 % 2) != 0) goto L21;
        int r8 = (r20 + 1) / 2;
        if ((r19 % 2) != 0) goto L8;
        int r1 = (r21 + 1) / 2;
    L7:
        int r9 = r1;
        int r10 = r18 / 2;
        int r12 = r18 * r21;
        int r11 = r18 * r19;
        int r2 = r10 * r9;
        int r122 = r11 + r2;
        int r13 = r11 + ((r10 * r19) / 2);
        int r14 = r13 + r2;
        VideoFrame.I420Buffer r15 = allocateI420Buffer(r20, r21);
        r17.limit(r12);     // Catch: Throwable -> L13
        r17.position(0);     // Catch: Throwable -> L13
        copyPlane(r17.slice(), r18, r15.getDataY(), r15.getStrideY(), r20, r21);     // Catch: Throwable -> L13
        r17.limit(r122);     // Catch: Throwable -> L13
        r17.position(r11);     // Catch: Throwable -> L13
        copyPlane(r17.slice(), r10, r15.getDataU(), r15.getStrideU(), r8, r9);     // Catch: Throwable -> L13
        if ((r19 % 2) != 1) goto L15;
        r17.position(r11 + (r10 * (r9 - 1)));     // Catch: Throwable -> L13
        ByteBuffer r16 = r15.getDataU();     // Catch: Throwable -> L13
        r16.position(r15.getStrideU() * r9);     // Catch: Throwable -> L13
        r16.put(r17);     // Catch: Throwable -> L13
    L15:
        r17.limit(r14);     // Catch: Throwable -> L13
        r17.position(r13);     // Catch: Throwable -> L13
        copyPlane(r17.slice(), r10, r15.getDataV(), r15.getStrideV(), r8, r9);     // Catch: Throwable -> L13
        if ((r19 % 2) != 1) goto L18;
        r17.position(r13 + (r10 * (r9 - 1)));     // Catch: Throwable -> L13
        ByteBuffer r110 = r15.getDataV();     // Catch: Throwable -> L13
        r110.position(r15.getStrideV() * r9);     // Catch: Throwable -> L13
        r110.put(r17);     // Catch: Throwable -> L13
    L18:
        return r15;
    L13:
        th = move-exception;
        FileLog.e(th);
        return r15;
    L8:
        r1 = r21 / 2;
        goto L7
    L21:
        UseCase$$ExternalSyntheticBUOutline0.m("Stride is not divisible by two: ", r18);
        return null;
    }

    private VideoFrame.Buffer copyNV12ToI420Buffer(ByteBuffer r8, int r9, int r10, int r11, int r12) {
        return new NV12Buffer(r11, r12, r9, r10, r8, null).toI420();
    }

    private Thread createOutputThread() {
        return new AnonymousClass1(this, "AndroidVideoDecoder.outputThread");
    }

    private void deliverByteFrame(int r9, MediaCodec.BufferInfo r10, int r11, Integer r12) {
        Object r1 = this.dimensionLock;
        monitor-enter(r1);
        int r6 = this.width;     // Catch: Throwable -> L21
        int r7 = this.height;     // Catch: Throwable -> L21
        int r0 = this.stride;     // Catch: Throwable -> L21
        int r5 = this.sliceHeight;     // Catch: Throwable -> L21
        monitor-exit(r1);     // Catch: Throwable -> L21
        int r13 = r10.size;
        if (r13 >= (((r6 * r7) * 3) / 2)) goto L11;
        Logging.e(TAG, "Insufficient output buffer size: " + r10.size);
        return;
    L11:
        if (r13 >= (((r0 * r7) * 3) / 2)) goto L15;
        if (r5 != r7) goto L15;
        if (r0 <= r6) goto L15;
        r0 = (r13 * 2) / (r7 * 3);
    L15:
        int r4 = r0;
        ByteBuffer r02 = this.codec.getOutputBuffer(r9);
        r02.position(r10.offset);
        r02.limit(r10.offset + r10.size);
        ByteBuffer r3 = r02.slice();
        if (this.colorFormat != 19) goto L18;
        AndroidVideoDecoder r2 = this;
        VideoFrame.Buffer r8 = r2.copyI420Buffer(r3, r4, r5, r6, r7);
    L19:
        r2.codec.releaseOutputBuffer(r9, false);
        VideoFrame r03 = new VideoFrame(r8, r11, r10.presentationTimeUs * 1000);
        r2.callback.onDecodedFrame(r03, r12, null);
        r03.release();
        return;
    L18:
        r2 = this;
        r8 = r2.copyNV12ToI420Buffer(r3, r4, r5, r6, r7);
    L21:
        th = move-exception;
        throw th;
    }

    private void deliverTextureFrame(int r5, MediaCodec.BufferInfo r6, int r7, Integer r8) {
        Object r0 = this.dimensionLock;
        monitor-enter(r0);
        int r1 = this.width;     // Catch: Throwable -> L20
        int r2 = this.height;     // Catch: Throwable -> L20
        monitor-exit(r0);     // Catch: Throwable -> L20
        Object r3 = this.renderedTextureMetadataLock;
        monitor-enter(r3);
    L13:
        th = move-exception;
        throw th;
    L9:
        if (this.renderedTextureMetadata == null) goto L15;
        this.codec.releaseOutputBuffer(r5, false);     // Catch: Throwable -> L13
        monitor-exit(r3);     // Catch: Throwable -> L13
        return;
    L15:
        this.surfaceTextureHelper.setTextureSize(r1, r2);     // Catch: Throwable -> L13
        this.surfaceTextureHelper.setFrameRotation(r7);     // Catch: Throwable -> L13
        this.renderedTextureMetadata = new DecodedTextureMetadata(r6.presentationTimeUs, r8);     // Catch: Throwable -> L13
        this.codec.releaseOutputBuffer(r5, true);     // Catch: Throwable -> L13
        monitor-exit(r3);     // Catch: Throwable -> L13
        return;
    L20:
        th = move-exception;
        throw th;
    }

    private VideoCodecStatus initDecodeInternal(int r6, int r7) {
        this.decoderThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initDecodeInternal name: " + this.codecName + " type: " + this.codecType + " width: " + r6 + " height: " + r7 + " color format: " + this.colorFormat);
        if (this.outputThread == null) goto L6;
        Logging.e(TAG, "initDecodeInternal called while the codec is already running");
        return VideoCodecStatus.FALLBACK_SOFTWARE;
    L6:
        this.width = r6;
        this.height = r7;
        this.stride = r6;
        this.sliceHeight = r7;
        this.hasDecodedFirstFrame = false;
        this.keyFrameRequired = true;
        this.codec = this.mediaCodecWrapperFactory.createByCodecName(this.codecName);     // Catch: Throwable -> L20
        MediaFormat r62 = MediaFormat.createVideoFormat(this.codecType.mimeType(), r6, r7);     // Catch: IllegalArgumentException -> L11 IllegalStateException -> L13
        if (this.sharedContext != null) goto L15;
        r62.setInteger("color-format", this.colorFormat);     // Catch: IllegalArgumentException -> L11 IllegalStateException -> L13
    L15:
        this.codec.configure(r62, this.surface, null, 0);     // Catch: IllegalArgumentException -> L11 IllegalStateException -> L13
        this.codec.start();     // Catch: IllegalArgumentException -> L11 IllegalStateException -> L13
        this.running = true;
        Thread r63 = createOutputThread();
        this.outputThread = r63;
        r63.start();
        Logging.d(TAG, "initDecodeInternal done");
        return VideoCodecStatus.OK;
    L11:
        e = e;
    L18:
        Logging.e(TAG, "initDecode failed", e);
        release();
        return VideoCodecStatus.FALLBACK_SOFTWARE;
    L13:
        e = e;
    L20:
        Logging.e(TAG, "Cannot create media decoder " + this.codecName);
        return VideoCodecStatus.FALLBACK_SOFTWARE;
    }

    private boolean isSupportedColorFormat(int r5) {
        int[] r4 = MediaCodecUtils.DECODER_COLOR_FORMATS;
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private void reformat(MediaFormat r8) {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Decoder format changed: " + r8);
        if (r8.containsKey("crop-left") == true) goto L5;
    L11:
        int r3 = r8.getInteger("width");
        int r4 = r8.getInteger("height");
    L12:
        Object r5 = this.dimensionLock;
        monitor-enter(r5);
    L19:
        th = move-exception;
        throw th;
    L15:
        if (r3 != this.width) goto L22;
        if (r4 != this.height) goto L22;
    L30:
        monitor-exit(r5);     // Catch: Throwable -> L19
        if (this.surfaceTextureHelper == null) goto L34;
    L39:
        Object r0 = this.dimensionLock;
        monitor-enter(r0);
    L44:
        th = move-exception;
        throw th;
    L42:
        if (r8.containsKey("stride") == false) goto L47;
        this.stride = r8.getInteger("stride");     // Catch: Throwable -> L44
    L47:
        if (r8.containsKey("slice-height") == false) goto L49;
        this.sliceHeight = r8.getInteger("slice-height");     // Catch: Throwable -> L44
    L49:
        Logging.d(TAG, "Frame stride and slice height: " + this.stride + " x " + this.sliceHeight);     // Catch: Throwable -> L44
        this.stride = Math.max(this.width, this.stride);     // Catch: Throwable -> L44
        this.sliceHeight = Math.max(this.height, this.sliceHeight);     // Catch: Throwable -> L44
        monitor-exit(r0);     // Catch: Throwable -> L44
        return;
    L34:
        if (r8.containsKey("color-format") == false) goto L39;
        this.colorFormat = r8.getInteger("color-format");
        Logging.d(TAG, "Color: 0x" + Integer.toHexString(this.colorFormat));
        if (isSupportedColorFormat(this.colorFormat) == true) goto L39;
        stopOnOutputThread(new IllegalStateException("Unsupported color format: " + this.colorFormat));
        return;
    L22:
        if (this.hasDecodedFirstFrame == false) goto L26;
        stopOnOutputThread(new RuntimeException("Unexpected size change. Configured " + this.width + "*" + this.height + ". New " + r3 + "*" + r4));     // Catch: Throwable -> L19
        monitor-exit(r5);     // Catch: Throwable -> L19
        return;
    L26:
        if (r3 <= 0) goto L54;
        if (r4 <= 0) goto L54;
        this.width = r3;     // Catch: Throwable -> L19
        this.height = r4;     // Catch: Throwable -> L19
    L54:
        Logging.w(TAG, "Unexpected format dimensions. Configured " + this.width + "*" + this.height + ". New " + r3 + "*" + r4 + ". Skip it");     // Catch: Throwable -> L19
        monitor-exit(r5);     // Catch: Throwable -> L19
        return;
    L5:
        if (r8.containsKey("crop-right") == false) goto L11;
        if (r8.containsKey("crop-bottom") == false) goto L11;
        if (r8.containsKey("crop-top") == false) goto L11;
        r3 = (r8.getInteger("crop-right") + 1) - r8.getInteger("crop-left");
        r4 = (r8.getInteger("crop-bottom") + 1) - r8.getInteger("crop-top");
        goto L12
    }

    private VideoCodecStatus reinitDecode(int r3, int r4) {
        this.decoderThreadChecker.checkIsOnValidThread();
        VideoCodecStatus r0 = releaseInternal();
        if (r0 == VideoCodecStatus.OK) goto L6;
        return r0;
    L6:
        return initDecodeInternal(r3, r4);
    }

    private void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        this.codec.stop();     // Catch: Exception -> L5
    L13:
        this.codec.release();     // Catch: Exception -> L9
    L11:
        Logging.d(TAG, "Release on output thread done");
        return;
    L9:
        e = move-exception;
        Logging.e(TAG, "Media decoder release failed", e);
        this.shutdownException = e;
    L5:
        e = move-exception;
        Logging.e(TAG, "Media decoder stop failed", e);
        goto L13
    }

    private VideoCodecStatus releaseInternal() {
        if (this.running == true) goto L22;
        Logging.d(TAG, "release: Decoder is not running.");
        return VideoCodecStatus.OK;
    L22:
        this.running = false;     // Catch: Throwable -> L12
        if (ThreadUtils.joinUninterruptibly(this.outputThread, 5000) == true) goto L15;
        Logging.e(TAG, "Media decoder release timeout", new RuntimeException());     // Catch: Throwable -> L12
        VideoCodecStatus r0 = VideoCodecStatus.TIMEOUT;     // Catch: Throwable -> L12
    L10:
        this.codec = null;
        this.outputThread = null;
        return r0;
    L15:
        if (this.shutdownException == null) goto L18;
        Logging.e(TAG, "Media decoder release error", new RuntimeException(this.shutdownException));     // Catch: Throwable -> L12
        this.shutdownException = null;     // Catch: Throwable -> L12
        r0 = VideoCodecStatus.ERROR;     // Catch: Throwable -> L12
        goto L10
    L18:
        this.codec = null;
        this.outputThread = null;
        return VideoCodecStatus.OK;
    L12:
        th = move-exception;
        this.codec = null;
        this.outputThread = null;
        throw th;
    }

    private void stopOnOutputThread(Exception r2) {
        this.outputThreadChecker.checkIsOnValidThread();
        this.running = false;
        this.shutdownException = r2;
    }

    public VideoFrame.I420Buffer allocateI420Buffer(int r1, int r2) {
        return JavaI420Buffer.allocate(r1, r2);
    }

    public void copyPlane(ByteBuffer r1, int r2, ByteBuffer r3, int r4, int r5, int r6) {
        YuvHelper.copyPlane(r1, r2, r3, r4, r5, r6);
    }

    public SurfaceTextureHelper createSurfaceTextureHelper() {
        return SurfaceTextureHelper.create("decoder-texture-thread", this.sharedContext);
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus decode(EncodedImage r9, VideoDecoder.DecodeInfo r10) {
        this.decoderThreadChecker.checkIsOnValidThread();
        boolean r0 = false;
        if (this.codec != null) goto L5;
    L61:
        StringBuilder r102 = new StringBuilder("decode uninitalized, codec: ");
        if (this.codec == null) goto L64;
        r0 = true;
    L64:
        r102.append(r0);
        r102.append(", callback: ");
        r102.append(this.callback);
        Logging.d(TAG, r102.toString());
        return VideoCodecStatus.UNINITIALIZED;
    L5:
        if (this.callback == null) goto L61;
        ByteBuffer r103 = r9.buffer;
        if (r103 != null) goto L11;
        Logging.e(TAG, "decode() - no input data");
        return VideoCodecStatus.ERR_PARAMETER;
    L11:
        int r4 = r103.remaining();
        if (r4 != 0) goto L15;
        Logging.e(TAG, "decode() - input buffer empty");
        return VideoCodecStatus.ERR_PARAMETER;
    L15:
        Object r104 = this.dimensionLock;
        monitor-enter(r104);
        int r1 = this.width;     // Catch: Throwable -> L57
        int r2 = this.height;     // Catch: Throwable -> L57
        monitor-exit(r104);     // Catch: Throwable -> L57
        int r105 = r9.encodedWidth;
        int r3 = r9.encodedHeight;
        if ((r105 * r3) <= 0) goto L27;
        if (r105 != r1) goto L23;
        if (r3 == r2) goto L27;
    L23:
        VideoCodecStatus r106 = reinitDecode(r105, r3);
        if (r106 == VideoCodecStatus.OK) goto L27;
        return r106;
    L27:
        if (this.keyFrameRequired == true) goto L29;
    L66:
        int r22 = this.codec.dequeueInputBuffer(500000);     // Catch: IllegalStateException -> L54
        if (r22 >= 0) goto L70;
        Logging.e(TAG, "decode() - no HW buffers available; decoder falling behind");
        return VideoCodecStatus.ERROR;
    L70:
        ByteBuffer r107 = this.codec.getInputBuffer(r22);     // Catch: IllegalStateException -> L51
        if (r107.capacity() >= r4) goto L41;
        Logging.e(TAG, "decode() - HW buffer too small");
        return VideoCodecStatus.ERROR;
    L41:
        r107.put(r9.buffer);
        this.frameInfos.offer(new FrameInfo(SystemClock.elapsedRealtime(), r9.rotation));
        this.codec.queueInputBuffer(r22, 0, r4, r9.captureTimeNs / 1000, 0);     // Catch: IllegalStateException -> L48
        if (this.keyFrameRequired == false) goto L47;
        this.keyFrameRequired = false;
    L47:
        return VideoCodecStatus.OK;
    L48:
        e = move-exception;
        Logging.e(TAG, "queueInputBuffer failed", e);
        this.frameInfos.pollLast();
        return VideoCodecStatus.ERROR;
    L51:
        e = move-exception;
        Logging.e(TAG, "getInputBuffer with index=" + r22 + " failed", e);
        return VideoCodecStatus.ERROR;
    L54:
        e = move-exception;
        Logging.e(TAG, "dequeueInputBuffer failed", e);
        return VideoCodecStatus.ERROR;
    L29:
        if (r9.frameType == EncodedImage.FrameType.VideoFrameKey) goto L66;
        Logging.e(TAG, "decode() - key frame required first");
        return VideoCodecStatus.NO_OUTPUT;
    L57:
        th = move-exception;
        throw th;
    }

    public void deliverDecodedFrame() {
        this.outputThreadChecker.checkIsOnValidThread();
        MediaCodec.BufferInfo r2 = new MediaCodec.BufferInfo();     // Catch: IllegalStateException -> L7
        int r3 = this.codec.dequeueOutputBuffer(r2, 100000);     // Catch: IllegalStateException -> L7
        if (r3 != (-2)) goto L9;
        reformat(this.codec.getOutputFormat());     // Catch: IllegalStateException -> L7
        return;
    L9:
        if (r3 >= 0) goto L12;
        Logging.v(TAG, "dequeueOutputBuffer returned " + r3);     // Catch: IllegalStateException -> L7
        return;
    L12:
        FrameInfo r1 = this.frameInfos.poll();     // Catch: IllegalStateException -> L7
        if (r1 == null) goto L15;
        Integer r4 = Integer.valueOf((int) (SystemClock.elapsedRealtime() - r1.decodeStartTimeMs));     // Catch: IllegalStateException -> L7
        int r12 = r1.rotation;     // Catch: IllegalStateException -> L7
    L16:
        this.hasDecodedFirstFrame = true;     // Catch: IllegalStateException -> L7
        if (this.surfaceTextureHelper == null) goto L20;
        deliverTextureFrame(r3, r2, r12, r4);     // Catch: IllegalStateException -> L7
        return;
    L20:
        deliverByteFrame(r3, r2, r12, r4);     // Catch: IllegalStateException -> L7
        return;
    L15:
        r4 = null;
        r12 = 0;
    L7:
        e = move-exception;
        Logging.e(TAG, "deliverDecodedFrame failed", e);
    }

    @Override // org.webrtc.VideoDecoder
    public String getImplementationName() {
        return this.codecName;
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus initDecode(VideoDecoder.Settings r2, VideoDecoder.Callback r3) {
        this.decoderThreadChecker = new ThreadUtils.ThreadChecker();
        this.callback = r3;
        if (this.sharedContext == null) goto L6;
        this.surfaceTextureHelper = createSurfaceTextureHelper();
        this.surface = new Surface(this.surfaceTextureHelper.getSurfaceTexture());
        this.surfaceTextureHelper.startListening(this);
    L6:
        return initDecodeInternal(r2.width, r2.height);
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame r7) {
        Object r0 = this.renderedTextureMetadataLock;
        monitor-enter(r0);
        DecodedTextureMetadata r1 = this.renderedTextureMetadata;     // Catch: Throwable -> L10
        if (r1 == null) goto L13;
        long r2 = r1.presentationTimestampUs * 1000;     // Catch: Throwable -> L10
        Integer r12 = r1.decodeTimeMs;     // Catch: Throwable -> L10
        this.renderedTextureMetadata = null;     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        VideoFrame r02 = new VideoFrame(r7.getBuffer(), r7.getRotation(), r2);
        this.callback.onDecodedFrame(r02, r12, null);
        return;
    L13:
        throw new IllegalStateException("Rendered texture metadata was null in onTextureFrameAvailable.");     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus release() {
        Logging.d(TAG, BuildConfig.BUILD_TYPE);
        VideoCodecStatus r0 = releaseInternal();
        if (this.surface == null) goto L5;
        releaseSurface();
        this.surface = null;
        this.surfaceTextureHelper.stopListening();
        this.surfaceTextureHelper.dispose();
        this.surfaceTextureHelper = null;
    L5:
        Object r1 = this.renderedTextureMetadataLock;
        monitor-enter(r1);
        this.renderedTextureMetadata = null;     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: Throwable -> L11
        this.callback = null;
        this.frameInfos.clear();
        return r0;
    L11:
        th = move-exception;
        throw th;
    }

    public void releaseSurface() {
        this.surface.release();
    }
}
