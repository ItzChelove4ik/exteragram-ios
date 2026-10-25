package org.telegram.utils.camera.roundvideo;

import android.media.AudioRecord;
import android.media.AudioTimestamp;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Process;
import android.os.SystemClock;
import android.view.Surface;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.MediaController;
import org.telegram.utils.camera.roundvideo.RoundVideoGlProcessor;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
final class RoundVideoCodecRecorder implements RoundVideoGlProcessor.FrameTimingListener {
    private long alignedAudioStartDeltaUs;
    private int audioBuffers;
    private long audioBytes;
    private MediaCodec audioCodec;
    private int audioReadErrors;
    private Runnable audioReadyListener;
    private AudioRecord audioRecord;
    private boolean audioStartAligned;
    private Thread audioThread;
    private final AudioTimestamp audioTimestamp;
    private final RoundVideoDiagnostics diagnostics;
    private int droppedAudioAfterStop;
    private int droppedAudioBuffers;
    private int droppedVideoAfterStop;
    private int emptyAudioReads;
    private final ErrorListener errorListener;
    private final AtomicBoolean errorReported;
    private long firstAudioPtsUs;
    private volatile long firstVideoPtsUs;
    private long lastAudioPtsUs;
    private long lastKeyframePtsUs;
    private long lastVideoPtsUs;
    private long latencyMaxNs;
    private int latencySamples;
    private long latencyTotalNs;
    private long maximumKeyframeIntervalUs;
    private int nonMonotonicAudioPts;
    private int nonMonotonicVideoPts;
    private final int outputSize;
    private final ArrayList<PendingAudioSample> pendingAudioSamples;
    private boolean prepared;
    private int prependHeaderSize;
    private boolean started;
    private boolean stopFinalizationStarted;
    private volatile long stopPresentationTimeUs;
    private volatile boolean stopping;
    private final long[] submittedPtsUs;
    private volatile long submittedSequence;
    private final long[] submittedTimeNs;
    private long timeOriginNs;
    private final long timelineOffsetUs;
    private final int videoBitrate;
    private int videoBuffers;
    private long videoBytes;
    private MediaCodec videoCodec;
    private Thread videoDrainThread;
    private final int videoFrameRate;
    private Surface videoInputSurface;
    private int videoKeyframes;
    private Runnable videoReadyListener;
    private final RoundVideoMp4Writer writer;

    public interface ErrorListener {
        void onError(Exception r1);
    }

    public static final class PendingAudioSample {
        final ByteBuffer data;
        final MediaCodec.BufferInfo info;

        public PendingAudioSample(ByteBuffer r1, MediaCodec.BufferInfo r2) {
            this.data = r1;
            this.info = r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$rslDXXFtcnYJZUc3Hibo3G7lRuA(RoundVideoCodecRecorder r0) {
        r0.drainVideo();
    }

    /* renamed from: $r8$lambda$uRkrlwnpT4OKrW6qd3hFLO8-yuA, reason: not valid java name */
    public static /* synthetic */ void m22166$r8$lambda$uRkrlwnpT4OKrW6qd3hFLO8yuA(RoundVideoCodecRecorder r0) {
        r0.captureAndEncodeAudio();
    }

    public RoundVideoCodecRecorder(RoundVideoMp4Writer r3, long r4, int r6, int r7, int r8, RoundVideoDiagnostics r9, ErrorListener r10) {
        this.errorReported = new AtomicBoolean();
        this.pendingAudioSamples = new ArrayList();
        this.audioTimestamp = new AudioTimestamp();
        this.submittedPtsUs = new long[256];
        this.submittedTimeNs = new long[256];
        this.stopPresentationTimeUs = LongCompanionObject.MAX_VALUE;
        this.writer = r3;
        this.timelineOffsetUs = r4;
        this.outputSize = r6;
        this.videoBitrate = r7;
        this.videoFrameRate = r8;
        this.diagnostics = r9;
        this.errorListener = r10;
    }

    private static String bitrate(long r2, long r4) {
        if (r4 > 0) goto L7;
        return "n/a";
    L7:
        return String.valueOf((r2 * 8000000) / r4);
    }

    private void bufferAudioSample(ByteBuffer r11, MediaCodec.BufferInfo r12) {
        ByteBuffer r0 = ByteBuffer.allocateDirect(r12.size);
        int r1 = r11.position();
        int r2 = r11.limit();
        r11.position(r12.offset);     // Catch: Throwable -> L6
        r11.limit(r12.offset + r12.size);     // Catch: Throwable -> L6
        r0.put(r11).flip();     // Catch: Throwable -> L6
        r11.limit(r2);
        r11.position(r1);
        MediaCodec.BufferInfo r4 = new MediaCodec.BufferInfo();
        r4.set(0, r12.size, r12.presentationTimeUs, r12.flags);
        this.pendingAudioSamples.add(new PendingAudioSample(r0, r4));
        return;
    L6:
        th = move-exception;
        r11.limit(r2);
        r11.position(r1);
        throw th;
    }

    private void captureAndEncodeAudio() {
        Process.setThreadPriority(-16);
        MediaCodec.BufferInfo r0 = new MediaCodec.BufferInfo();
        boolean r6 = false;
        long r8 = Long.MIN_VALUE;
        boolean r7 = false;
        long r10 = 0;
    L3:
        if (r6 == true) goto L74;
        if (r7 == false) goto L7;
        long r14 = 10000;
    L52:
        r6 = drainAudioOutput(r0, r14);     // Catch: RuntimeException -> L21
        if (r6 == true) goto L72;
        int r16 = this.audioCodec.dequeueInputBuffer(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: RuntimeException -> L21
        if (r16 < 0) goto L3;
        if (this.stopping == true) goto L17;
        ByteBuffer r142 = this.audioCodec.getInputBuffer(r16);     // Catch: RuntimeException -> L21
        if (r142 == null) goto L25;
        r142.clear();     // Catch: RuntimeException -> L21
        int r2 = this.audioRecord.read(r142, Math.min(r142.remaining(), 2048));     // Catch: RuntimeException -> L21
        if (r2 <= 0) goto L28;
        int r22 = r2 / 2;     // Catch: RuntimeException -> L21
        if (r8 != Long.MIN_VALUE) goto L45;
        r8 = resolveAudioBasePtsUs(r22);     // Catch: RuntimeException -> L21
        this.diagnostics.log("first audio input: basePtsUs=" + r8 + ", frames=" + r22 + ", timestampSource=AudioTimestamp");     // Catch: RuntimeException -> L21
    L45:
        this.audioCodec.queueInputBuffer(r16, 0, r22 * 2, r8 + framesToDurationUs(r10), 0);     // Catch: RuntimeException -> L21
        r10 = r10 + r22;     // Catch: RuntimeException -> L21
        notifyAudioReady();     // Catch: RuntimeException -> L21
        goto L3
    L28:
        if (r2 >= 0) goto L36;
        int r3 = this.audioReadErrors + 1;     // Catch: RuntimeException -> L21
        this.audioReadErrors = r3;     // Catch: RuntimeException -> L21
        if (r2 == (-6)) goto L35;
        if (r3 >= 3) goto L35;
    L38:
        if (r8 != Long.MIN_VALUE) goto L40;
        long r19 = 0;
    L41:
        this.audioCodec.queueInputBuffer(r16, 0, 0, r19, 0);     // Catch: RuntimeException -> L21
        goto L3
    L40:
        r19 = framesToDurationUs(r10) + r8;     // Catch: RuntimeException -> L21
    L35:
        throw new IllegalStateException("AudioRecord read failed: " + r2);     // Catch: RuntimeException -> L21
    L36:
        this.emptyAudioReads++;
        goto L38
    L25:
        this.audioCodec.queueInputBuffer(r16, 0, 0, 0, 0);     // Catch: RuntimeException -> L21
        goto L3
    L17:
        if (r8 != Long.MIN_VALUE) goto L19;
        long r192 = 0;
    L20:
        this.audioCodec.queueInputBuffer(r16, 0, 0, r192, 4);     // Catch: RuntimeException -> L21
        r7 = true;
        goto L3
    L19:
        r192 = framesToDurationUs(r10) + r8;     // Catch: RuntimeException -> L21
        goto L20
    L72:
        return;
    L21:
        e = move-exception;
        if (this.stopping == true) goto L50;
        reportError(e);
        return;
    L50:
        this.diagnostics.error("audio capture failed while stopping", e);
        return;
    L7:
        r14 = 0;
        goto L52
    }

    private static String codecDiagnostic(Exception r2) {
        if ((r2 instanceof MediaCodec.CodecException) == false) goto L6;
        MediaCodec.CodecException r22 = (MediaCodec.CodecException) r2;
        return ": diagnostic=" + r22.getDiagnosticInfo() + ", recoverable=" + r22.isRecoverable() + ", transient=" + r22.isTransient();
    L6:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private void createAudioEncoder() {
        int r1 = AudioRecord.getMinBufferSize(48000, 16, 2);
        if (r1 <= 0) goto L9;
        int r10 = Math.max(r1 * 4, 16384);
        MediaFormat r0 = MediaFormat.createAudioFormat(MediaController.AUDIO_MIME_TYPE, 48000, 1);
        r0.setInteger("aac-profile", 2);
        r0.setInteger("bitrate", 64000);
        r0.setInteger("max-input-size", r10);
        this.audioCodec = MediaCodec.createEncoderByType(MediaController.AUDIO_MIME_TYPE);
        this.diagnostics.log("audio encoder configure: codec=" + this.audioCodec.getName() + ", format=" + r0 + ", minBufferSize=" + r1 + ", audioBufferSize=" + r10);
        this.audioCodec.configure(r0, null, null, 1);
        AudioRecord r5 = new AudioRecord(5, 48000, 16, 2, r10);
        this.audioRecord = r5;
        if (r5.getState() != 1) goto L7;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("Unable to initialize AudioRecord");
        return;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("Unsupported audio recording configuration");
    }

    private void createVideoEncoder() {
        int r0 = this.outputSize;
        MediaFormat r02 = MediaFormat.createVideoFormat(MediaController.VIDEO_MIME_TYPE, r0, r0);
        r02.setInteger("color-format", 2130708361);
        r02.setInteger("bitrate", this.videoBitrate);
        r02.setInteger("frame-rate", this.videoFrameRate);
        r02.setInteger("i-frame-interval", 1);
        MediaCodec r2 = MediaCodec.createEncoderByType(MediaController.VIDEO_MIME_TYPE);
        this.videoCodec = r2;
        MediaCodecInfo.VideoCapabilities r1 = r2.getCodecInfo().getCapabilitiesForType(MediaController.VIDEO_MIME_TYPE).getVideoCapabilities();
        int r22 = this.outputSize;
        if (r1.isSizeSupported(r22, r22) == false) goto L9;
        int r23 = this.outputSize;
        if (r1.areSizeAndRateSupported(r23, r23, this.videoFrameRate) == false) goto L9;
        this.diagnostics.log("video encoder configure: codec=" + this.videoCodec.getName() + ", format=" + r02);
        this.videoCodec.configure(r02, null, null, 1);
        this.videoInputSurface = this.videoCodec.createInputSurface();
        return;
    L9:
        throw new IOException("Video encoder does not support " + this.outputSize + "x" + this.outputSize + " at " + this.videoFrameRate + " fps");
    }

    private boolean drainAudioOutput(MediaCodec.BufferInfo r5, long r6) {
        int r62 = this.audioCodec.dequeueOutputBuffer(r5, r6);
        boolean r0 = false;
    L3:
        boolean r1 = true;
        if (r62 < 0) goto L14;
        if (isMediaBuffer(r5) == false) goto L9;
        writeOrBufferAudioSample(this.audioCodec.getOutputBuffer(r62), r5);
    L9:
        if ((r5.flags & 4) != 0) goto L12;
        r1 = false;
    L12:
        r0 = r0 | r1;
        this.audioCodec.releaseOutputBuffer(r62, false);
        r62 = this.audioCodec.dequeueOutputBuffer(r5, 0);
        goto L3
    L14:
        if (r62 != (-2)) goto L16;
        setTrackFormat(false, this.audioCodec.getOutputFormat());
    L16:
        if (r0 == true) goto L18;
    L22:
        return r0;
    L18:
        if (this.audioStartAligned == true) goto L22;
        if (this.firstVideoPtsUs == Long.MIN_VALUE) goto L22;
        flushPendingAudioSamples(this.firstVideoPtsUs, true);
        goto L22
    }

    private void drainVideo() {
        MediaCodec.BufferInfo r0 = new MediaCodec.BufferInfo();
        boolean r2 = false;
    L3:
        if (r2 == true) goto L38;
        int r3 = this.videoCodec.dequeueOutputBuffer(r0, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: RuntimeException -> L7
        if (r3 == (-2)) goto L6;
        if (r3 < 0) goto L3;
        if (isMediaBuffer(r0) == false) goto L17;
        if (r0.presentationTimeUs >= this.stopPresentationTimeUs) goto L15;
        this.videoBuffers++;
        this.videoBytes += r0.size;
        recordSampleStats(true, r0);     // Catch: RuntimeException -> L7
        recordCodecLatency(r0.presentationTimeUs);     // Catch: RuntimeException -> L7
        writeSample(true, this.videoCodec.getOutputBuffer(r3), r0);     // Catch: RuntimeException -> L7
        goto L17
    L15:
        this.droppedVideoAfterStop++;
    L17:
        if ((r0.flags & 4) == 0) goto L19;
        r2 = true;
    L20:
        this.videoCodec.releaseOutputBuffer(r3, false);     // Catch: RuntimeException -> L7
        goto L3
    L19:
        r2 = false;
        goto L20
    L6:
        setTrackFormat(true, this.videoCodec.getOutputFormat());     // Catch: RuntimeException -> L7
    L7:
        e = move-exception;
        if (this.stopping == true) goto L25;
        reportError(e);
        return;
    L25:
        this.diagnostics.error("video drain failed while stopping", e);
        return;
    }

    private static long durationUs(long r2, long r4) {
        if (r2 != Long.MIN_VALUE) goto L5;
        return 0;
    L5:
        if (r4 > r2) goto L8;
        return 0;
    L8:
        return r4 - r2;
    }

    private static long elapsedMs(long r2) {
        return (System.nanoTime() - r2) / 1000000;
    }

    private void flushPendingAudioSamples(long r8, boolean r10) {
        if (this.pendingAudioSamples.isEmpty() == true) goto L35;
        int r0 = 0;
        int r1 = 0;
    L7:
        if (r1 >= this.pendingAudioSamples.size()) goto L12;
        if (this.pendingAudioSamples.get(r1).info.presentationTimeUs >= r8) goto L14;
        r1 = r1 + 1;
    L14:
        if (r1 >= 0) goto L18;
        if (r10 == true) goto L17;
        return;
    L17:
        r1 = this.pendingAudioSamples.size() - 1;
    L22:
        if (r0 >= r1) goto L24;
        this.droppedAudioBuffers++;
        r0 = r0 + 1;
        goto L22
    L24:
        PendingAudioSample r102 = this.pendingAudioSamples.get(r1);
        this.alignedAudioStartDeltaUs = r102.info.presentationTimeUs - r8;
    L25:
        int r02 = this.pendingAudioSamples.size();
        ArrayList<PendingAudioSample> r3 = this.pendingAudioSamples;
        if (r1 >= r02) goto L28;
        PendingAudioSample r03 = r3.get(r1);
        writeAudioSample(r03.data, r03.info);
        r1 = r1 + 1;
        goto L25
    L28:
        r3.clear();
        this.audioStartAligned = true;
        this.diagnostics.log("A/V start aligned: videoPtsUs=" + r8 + ", audioPtsUs=" + r102.info.presentationTimeUs + ", deltaUs=" + this.alignedAudioStartDeltaUs);
        return;
    L18:
        if (r1 <= 0) goto L22;
        if ((r8 - this.pendingAudioSamples.get(r1 - 1).info.presentationTimeUs) > (this.pendingAudioSamples.get(r1).info.presentationTimeUs - r8)) goto L22;
        r1 = r1 - 1;
        goto L22
    L12:
        r1 = -1;
        goto L14
    }

    private static long framesToDurationUs(long r2) {
        return (r2 * 1000000) / 48000;
    }

    private boolean hasAudioVideoPts() {
        if (this.firstVideoPtsUs != Long.MIN_VALUE) goto L5;
        return false;
    L5:
        if (this.lastVideoPtsUs != Long.MIN_VALUE) goto L7;
        return false;
    L7:
        if (this.firstAudioPtsUs != Long.MIN_VALUE) goto L9;
        return false;
    L9:
        if (this.lastAudioPtsUs == Long.MIN_VALUE) goto L16;
        return true;
    L16:
        return false;
    }

    private static boolean isMediaBuffer(MediaCodec.BufferInfo r1) {
        if (r1.size > 0) goto L5;
        return false;
    L5:
        if ((r1.flags & 2) != 0) goto L10;
        return true;
    L10:
        return false;
    }

    private void join(Thread r4) {
        if (r4 != null) goto L12;
        return;
    L12:
        r4.join(5000);     // Catch: InterruptedException -> L10
        if (r4.isAlive() == false) goto L14;
        this.diagnostics.log("encoder thread did not stop: " + r4.getName());     // Catch: InterruptedException -> L10
        return;
    L14:
        return;
    L10:
        Thread.currentThread().interrupt();
    }

    private void logSummary() {
        long r1 = Long.MIN_VALUE;
        if (hasAudioVideoPts() == false) goto L5;
        long r3 = this.firstAudioPtsUs - this.firstVideoPtsUs;
    L7:
        if (hasAudioVideoPts() == false) goto L9;
        r1 = this.lastAudioPtsUs - this.lastVideoPtsUs;
    L9:
        long r5 = durationUs(this.firstVideoPtsUs, this.lastVideoPtsUs);
        long r7 = durationUs(this.firstAudioPtsUs, this.lastAudioPtsUs);
        RoundVideoDiagnostics r0 = this.diagnostics;
        StringBuilder r9 = new StringBuilder("codec summary: videoBuffers=");
        r9.append(this.videoBuffers);
        r9.append(", videoBytes=");
        r9.append(this.videoBytes);
        r9.append(", videoPts=");
        r9.append(ptsRange(this.firstVideoPtsUs, this.lastVideoPtsUs));
        r9.append(", actualVideoBitrate=");
        r9.append(bitrate(this.videoBytes, r5));
        r9.append(", keyframes=");
        r9.append(this.videoKeyframes);
        r9.append(", maxKeyframeIntervalUs=");
        r9.append(this.maximumKeyframeIntervalUs);
        r9.append(", nonMonotonicVideoPts=");
        r9.append(this.nonMonotonicVideoPts);
        r9.append(", audioBuffers=");
        r9.append(this.audioBuffers);
        r9.append(", audioBytes=");
        r9.append(this.audioBytes);
        r9.append(", audioPts=");
        r9.append(ptsRange(this.firstAudioPtsUs, this.lastAudioPtsUs));
        r9.append(", actualAudioBitrate=");
        r9.append(bitrate(this.audioBytes, r7));
        r9.append(", nonMonotonicAudioPts=");
        r9.append(this.nonMonotonicAudioPts);
        r9.append(", avStartDeltaUs=");
        r9.append(optionalValue(r3));
        r9.append(", avEndDeltaUs=");
        r9.append(optionalValue(r1));
        r9.append(", audioReadErrors=");
        r9.append(this.audioReadErrors);
        r9.append(", emptyAudioReads=");
        r9.append(this.emptyAudioReads);
        r9.append(", droppedAudioBuffers=");
        r9.append(this.droppedAudioBuffers);
        r9.append(", droppedAudioAfterStop=");
        r9.append(this.droppedAudioAfterStop);
        r9.append(", droppedVideoAfterStop=");
        r9.append(this.droppedVideoAfterStop);
        r9.append(", stopPresentationTimeUs=");
        Object r2 = "n/a";
        if (this.stopPresentationTimeUs != LongCompanionObject.MAX_VALUE) goto L12;
        Object r12 = "n/a";
    L13:
        r9.append(r12);
        r9.append(", alignedAudioStartDeltaUs=");
        r9.append(optionalValue(this.alignedAudioStartDeltaUs));
        r9.append(", codecLatencyAvgMs=");
        int r13 = this.latencySamples;
        if (r13 == 0) goto L17;
        r2 = Float.valueOf((this.latencyTotalNs / r13) / 1000000.0f);
    L17:
        r9.append(r2);
        r9.append(", codecLatencyMaxMs=");
        r9.append(this.latencyMaxNs / 1000000.0f);
        r0.log(r9.toString());
        return;
    L12:
        r12 = Long.valueOf(this.stopPresentationTimeUs);
        goto L13
    L5:
        r3 = Long.MIN_VALUE;
        goto L7
    }

    private void notifyAudioReady() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.audioReadyListener != null) goto L6;
    L14:
        monitor-exit(this);     // Catch: Throwable -> L12
        return;
    L6:
        if (this.stopping == true) goto L14;
        Runnable r0 = this.audioReadyListener;     // Catch: Throwable -> L12
        this.audioReadyListener = null;     // Catch: Throwable -> L12
        monitor-exit(this);     // Catch: Throwable -> L12
        this.diagnostics.log("audio capture ready; waiting for common A/V start frame");
        r0.run();
    }

    private void notifyVideoReady() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.videoReadyListener != null) goto L6;
    L14:
        monitor-exit(this);     // Catch: Throwable -> L12
        return;
    L6:
        if (this.stopping == true) goto L14;
        Runnable r0 = this.videoReadyListener;     // Catch: Throwable -> L12
        this.videoReadyListener = null;     // Catch: Throwable -> L12
        monitor-exit(this);     // Catch: Throwable -> L12
        this.diagnostics.log("first synchronized video frame submitted");
        r0.run();
    }

    private static String optionalValue(long r2) {
        if (r2 != Long.MIN_VALUE) goto L7;
        return "n/a";
    L7:
        return String.valueOf(r2);
    }

    private static String ptsRange(long r2, long r4) {
        if (r2 != Long.MIN_VALUE) goto L7;
        return "n/a";
    L7:
        return r2 + ".." + r4;
    }

    private void recordCodecLatency(long r13) {
        long r0 = this.submittedSequence;
        long r4 = Math.max(0, r0 - 256);
    L3:
        r0 = r0 - 1;
        if (r0 < r4) goto L16;
        int r8 = (int) (r0 % 256);
        if (this.submittedPtsUs[r8] != r13) goto L3;
        long r132 = System.nanoTime() - this.submittedTimeNs[r8];
        this.latencySamples++;
        this.latencyTotalNs += r132;
        this.latencyMaxNs = Math.max(this.latencyMaxNs, r132);
        if ((this.latencySamples % 30) != 0) goto L15;
        this.diagnostics.log("codec latency: average=" + ((this.latencyTotalNs / this.latencySamples) / 1000000.0f) + " ms, max=" + (this.latencyMaxNs / 1000000.0f) + " ms");
        return;
    L15:
        return;
    }

    private void recordSampleStats(boolean r7, MediaCodec.BufferInfo r8) {
        long r0 = r8.presentationTimeUs;
        if (r7 == false) goto L20;
        if (this.firstVideoPtsUs != Long.MIN_VALUE) goto L7;
        this.firstVideoPtsUs = r0;
    L7:
        long r4 = this.lastVideoPtsUs;
        if (r4 != Long.MIN_VALUE) goto L10;
    L12:
        this.lastVideoPtsUs = Math.max(r4, r0);
        if ((r8.flags & 1) == 0) goto L29;
        this.videoKeyframes++;
        long r72 = this.lastKeyframePtsUs;
        if (r72 == Long.MIN_VALUE) goto L17;
        this.maximumKeyframeIntervalUs = Math.max(this.maximumKeyframeIntervalUs, r0 - r72);
    L17:
        this.lastKeyframePtsUs = r0;
        return;
    L29:
        return;
    L10:
        if (r0 >= r4) goto L12;
        this.nonMonotonicVideoPts++;
        goto L12
    L20:
        if (this.firstAudioPtsUs != Long.MIN_VALUE) goto L22;
        this.firstAudioPtsUs = r0;
    L22:
        long r73 = this.lastAudioPtsUs;
        if (r73 != Long.MIN_VALUE) goto L25;
    L27:
        this.lastAudioPtsUs = Math.max(r73, r0);
        return;
    L25:
        if (r0 >= r73) goto L27;
        this.nonMonotonicAudioPts++;
        goto L27
    }

    private void release() {
        this.prepared = false;
        this.started = false;
        this.audioReadyListener = null;
        this.videoReadyListener = null;
        AudioRecord r1 = this.audioRecord;
        if (r1 == null) goto L5;
        r1.release();
        this.audioRecord = null;
    L5:
        Surface r12 = this.videoInputSurface;
        if (r12 == null) goto L8;
        r12.release();
        this.videoInputSurface = null;
    L8:
        releaseCodec(this.videoCodec);
        releaseCodec(this.audioCodec);
        this.audioCodec = null;
        this.videoCodec = null;
        this.audioThread = null;
        this.videoDrainThread = null;
    }

    private static void releaseCodec(MediaCodec r0) {
        if (r0 != null) goto L8;
        return;
    L8:
        r0.stop();     // Catch: IllegalStateException -> L7
    L5:
        r0.release();
    }

    private void reportError(Exception r4) {
        this.diagnostics.error("codec error" + codecDiagnostic(r4), r4);
        if (this.errorReported.compareAndSet(false, true) == false) goto L6;
        this.errorListener.onError(r4);
        return;
    }

    private void resetStats() {
        this.stopping = false;
        this.stopFinalizationStarted = false;
        this.stopPresentationTimeUs = LongCompanionObject.MAX_VALUE;
        this.timeOriginNs = 0;
        this.audioBytes = 0;
        this.videoBytes = 0;
        this.audioBuffers = 0;
        this.videoBuffers = 0;
        this.submittedSequence = 0;
        this.latencySamples = 0;
        this.latencyMaxNs = 0;
        this.latencyTotalNs = 0;
        this.lastVideoPtsUs = Long.MIN_VALUE;
        this.firstVideoPtsUs = Long.MIN_VALUE;
        this.lastAudioPtsUs = Long.MIN_VALUE;
        this.firstAudioPtsUs = Long.MIN_VALUE;
        this.lastKeyframePtsUs = Long.MIN_VALUE;
        this.maximumKeyframeIntervalUs = 0;
        this.videoKeyframes = 0;
        this.nonMonotonicAudioPts = 0;
        this.nonMonotonicVideoPts = 0;
        this.emptyAudioReads = 0;
        this.audioReadErrors = 0;
        this.droppedAudioBuffers = 0;
        this.droppedAudioAfterStop = 0;
        this.droppedVideoAfterStop = 0;
        this.audioStartAligned = false;
        this.alignedAudioStartDeltaUs = Long.MIN_VALUE;
        this.audioReadyListener = null;
        this.videoReadyListener = null;
        this.pendingAudioSamples.clear();
    }

    private long resolveAudioBasePtsUs(int r12) {
        long r0 = Math.max(0, ((SystemClock.elapsedRealtimeNanos() - this.timeOriginNs) / 1000) - framesToDurationUs(r12));
        if (this.audioRecord.getTimestamp(this.audioTimestamp, 1) != 0) goto L6;
        AudioTimestamp r122 = this.audioTimestamp;
        long r4 = Math.max(0, ((r122.nanoTime - this.timeOriginNs) / 1000) - framesToDurationUs(r122.framePosition));
        this.diagnostics.log("audio timestamp alignment: framePosition=" + this.audioTimestamp.framePosition + ", timestampDeltaUs=" + ((this.audioTimestamp.nanoTime - this.timeOriginNs) / 1000) + ", timestampBaseUs=" + r4 + ", readCompletionBaseUs=" + r0);
        return r4;
    L6:
        this.diagnostics.log("audio timestamp unavailable: readCompletionBaseUs=" + r0);
        return r0;
    }

    private void setTrackFormat(boolean r4, MediaFormat r5) {
        if (r4 == true) goto L25;
    L16:
        this.writer.setTrackFormat(r4, r5);     // Catch: IOException -> L22
        RoundVideoDiagnostics r3 = this.diagnostics;     // Catch: IOException -> L22
        StringBuilder r0 = new StringBuilder();     // Catch: IOException -> L22
        if (r4 == false) goto L19;
        String r42 = MediaStreamTrack.VIDEO_TRACK_KIND;
    L20:
        r0.append(r42);     // Catch: IOException -> L22
        r0.append(" output format: ");     // Catch: IOException -> L22
        r0.append(r5);     // Catch: IOException -> L22
        r3.log(r0.toString());     // Catch: IOException -> L22
        return;
    L22:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return;
    L19:
        r42 = MediaStreamTrack.AUDIO_TRACK_KIND;
        goto L20
    L25:
        if (r5.containsKey("prepend-sps-pps-to-idr-frames") == false) goto L16;
        if (r5.getInteger("prepend-sps-pps-to-idr-frames") != 1) goto L16;
        ByteBuffer r02 = r5.getByteBuffer("csd-0");     // Catch: IOException -> L22
        ByteBuffer r1 = r5.getByteBuffer("csd-1");     // Catch: IOException -> L22
        int r2 = 0;
        if (r02 != null) goto L11;
        int r03 = 0;
    L12:
        if (r1 == null) goto L15;
        r2 = r1.remaining();     // Catch: IOException -> L22
    L15:
        this.prependHeaderSize = r03 + r2;     // Catch: IOException -> L22
        goto L16
    L11:
        r03 = r02.remaining();     // Catch: IOException -> L22
        goto L12
    }

    private void writeAudioSample(ByteBuffer r5, MediaCodec.BufferInfo r6) {
        this.audioBuffers++;
        this.audioBytes += r6.size;
        recordSampleStats(false, r6);
        writeSample(false, r5, r6);
    }

    private void writeOrBufferAudioSample(ByteBuffer r5, MediaCodec.BufferInfo r6) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (r6.presentationTimeUs < this.stopPresentationTimeUs) goto L9;
        this.droppedAudioBuffers++;
        this.droppedAudioAfterStop++;
        return;
    L9:
        if (this.audioStartAligned == false) goto L12;
        writeAudioSample(r5, r6);
        return;
    L12:
        bufferAudioSample(r5, r6);
        long r0 = this.firstVideoPtsUs;
        if (r0 != Long.MIN_VALUE) goto L15;
        return;
    L15:
        if (r6.presentationTimeUs < r0) goto L19;
        flushPendingAudioSamples(r0, false);
        return;
    }

    private void writeSample(boolean r7, ByteBuffer r8, MediaCodec.BufferInfo r9) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (r7 == true) goto L17;
    L12:
        this.writer.writeSample(r7, r8, r9, this.timelineOffsetUs);     // Catch: IOException -> L14
        return;
    L14:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return;
    L17:
        int r0 = this.prependHeaderSize;     // Catch: IOException -> L14
        if (r0 <= 0) goto L12;
        if ((r9.flags & 1) == 0) goto L12;
        int r1 = r9.size;     // Catch: IOException -> L14
        if (r1 <= r0) goto L12;
        r9.offset += r0;
        r9.size = r1 - r0;     // Catch: IOException -> L14
        goto L12
    }

    public long getTimeOriginNs() {
        return this.timeOriginNs;
    }

    public synchronized boolean isStarted() {
        monitor-enter(this);
        boolean r0 = this.started;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // org.telegram.utils.camera.roundvideo.RoundVideoGlProcessor.FrameTimingListener
    public void onFrameSubmitted(long r5, long r7) {
        long r0 = this.submittedSequence;
        int r2 = (int) (r0 % 256);
        this.submittedPtsUs[r2] = r5;
        this.submittedTimeNs[r2] = r7;
        this.submittedSequence = r0 + 1;
        notifyVideoReady();
    }

    public synchronized Surface prepare() {
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.prepared == false) goto L11;
        Surface r0 = this.videoInputSurface;     // Catch: Throwable -> L9
        monitor-exit(this);
        return r0;
    L11:
        resetStats();     // Catch: Throwable -> L9
        long r1 = System.nanoTime();     // Catch: Throwable -> L9
        createVideoEncoder();     // Catch: Throwable -> L9 RuntimeException -> L15 Throwable -> L17
        createAudioEncoder();     // Catch: Throwable -> L9 RuntimeException -> L15 Throwable -> L17
        this.videoCodec.start();     // Catch: Throwable -> L9 RuntimeException -> L15 Throwable -> L17
        this.prepared = true;     // Catch: Throwable -> L9 RuntimeException -> L15 Throwable -> L17
        this.diagnostics.log("codecs prepared: video=" + this.videoCodec.getName() + ", audio=" + this.audioCodec.getName() + ", elapsedMs=" + elapsedMs(r1));     // Catch: Throwable -> L9 RuntimeException -> L15 Throwable -> L17
        Surface r02 = this.videoInputSurface;     // Catch: Throwable -> L9 RuntimeException -> L15 Throwable -> L17
        monitor-exit(this);
        return r02;
    L17:
        e = move-exception;
        release();     // Catch: Throwable -> L9
        throw e;     // Catch: Throwable -> L9
    }

    public long requestStop() {
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L4:
        if (this.prepared == false) goto L27;
        if (this.started == false) goto L27;
        if (this.stopping == false) goto L15;
        long r0 = this.stopPresentationTimeUs;     // Catch: Throwable -> L13
        monitor-exit(this);     // Catch: Throwable -> L13
        return r0;
    L15:
        this.stopping = true;     // Catch: Throwable -> L13
        long r02 = Math.max(0, (SystemClock.elapsedRealtimeNanos() - this.timeOriginNs) / 1000);     // Catch: Throwable -> L13
        this.stopPresentationTimeUs = r02;     // Catch: Throwable -> L13
        AudioRecord r2 = this.audioRecord;     // Catch: Throwable -> L13
        monitor-exit(this);     // Catch: Throwable -> L13
        if (r2 != null) goto L32;
    L24:
        this.diagnostics.log("recording stop boundary: presentationTimeUs=" + r02);
        return r02;
    L32:
    L22:
        e = move-exception;
        this.diagnostics.error("AudioRecord stop failed", e);
        goto L24
    L19:
        if (r2.getRecordingState() != 3) goto L24;
        r2.stop();     // Catch: IllegalStateException -> L22
    L27:
        monitor-exit(this);     // Catch: Throwable -> L13
        return LongCompanionObject.MAX_VALUE;
    }

    public synchronized void startRecording(Runnable r4, Runnable r5) {
        monitor-enter(this);
    L15:
        th = move-exception;
        throw th;
    L5:
        if (this.started == false) goto L9;
        monitor-exit(this);
        return;
    L9:
        if (this.prepared == false) goto L24;
        this.audioCodec.start();     // Catch: Throwable -> L15 RuntimeException -> L17
        this.audioRecord.startRecording();     // Catch: Throwable -> L15 RuntimeException -> L17
        if (this.audioRecord.getRecordingState() != 3) goto L20;
        this.timeOriginNs = SystemClock.elapsedRealtimeNanos();     // Catch: Throwable -> L15 RuntimeException -> L17
        this.audioReadyListener = r4;     // Catch: Throwable -> L15 RuntimeException -> L17
        this.videoReadyListener = r5;     // Catch: Throwable -> L15 RuntimeException -> L17
        this.started = true;     // Catch: Throwable -> L15 RuntimeException -> L17
        this.diagnostics.log("audio and video recording started: timeOriginNs=" + this.timeOriginNs + ", timelineOffsetUs=" + this.timelineOffsetUs + ", audioSessionId=" + this.audioRecord.getAudioSessionId());     // Catch: Throwable -> L15 RuntimeException -> L17
        this.videoDrainThread = new Thread(new RoundVideoCodecRecorder$$ExternalSyntheticLambda0(this), "RoundVideoVideoEncoder");     // Catch: Throwable -> L15 RuntimeException -> L17
        this.audioThread = new Thread(new RoundVideoCodecRecorder$$ExternalSyntheticLambda1(this), "RoundVideoAudioEncoder");     // Catch: Throwable -> L15 RuntimeException -> L17
        this.videoDrainThread.start();     // Catch: Throwable -> L15 RuntimeException -> L17
        this.audioThread.start();     // Catch: Throwable -> L15 RuntimeException -> L17
        monitor-exit(this);
        return;
    L20:
        throw new IllegalStateException("Unable to start AudioRecord");     // Catch: Throwable -> L15 RuntimeException -> L17
    L17:
        e = move-exception;
        release();     // Catch: Throwable -> L15
        throw e;     // Catch: Throwable -> L15
    L24:
        throw new IllegalStateException("Recorder is not prepared");     // Catch: Throwable -> L15
    }

    public void stop() {
        requestStop();
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L5:
        if (this.prepared == true) goto L7;
    L24:
        monitor-exit(this);     // Catch: Throwable -> L14
        return;
    L7:
        if (this.stopFinalizationStarted == true) goto L24;
        if (this.started == true) goto L16;
        release();     // Catch: Throwable -> L14
        monitor-exit(this);     // Catch: Throwable -> L14
        return;
    L16:
        this.stopFinalizationStarted = true;     // Catch: Throwable -> L14
        monitor-exit(this);     // Catch: Throwable -> L14
        this.videoCodec.signalEndOfInputStream();     // Catch: IllegalStateException -> L20
    L22:
        join(this.videoDrainThread);
        join(this.audioThread);
        logSummary();
        release();
        return;
    L20:
        e = move-exception;
        this.diagnostics.error("video encoder EOS failed", e);
        goto L22
    }
}
