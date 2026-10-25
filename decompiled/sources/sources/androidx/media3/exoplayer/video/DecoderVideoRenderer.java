package androidx.media3.exoplayer.video;

import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class DecoderVideoRenderer extends BaseRenderer {
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private int consecutiveDroppedFrameCount;
    private Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder;
    protected DecoderCounters decoderCounters;
    private DrmSession decoderDrmSession;
    private boolean decoderReceivedBuffers;
    private int decoderReinitializationState;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private int firstFrameState;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private final TimedValueQueue<Format> formatQueue;
    private VideoFrameMetadataListener frameMetadataListener;
    private long initialPositionUs;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private long joiningDeadlineMs;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private Object output;
    private VideoDecoderOutputBuffer outputBuffer;
    private Format outputFormat;
    private int outputMode;
    private boolean outputStreamEnded;
    private Surface outputSurface;
    private VideoSize reportedVideoSize;
    private DrmSession sourceDrmSession;
    private boolean waitingForFirstSampleInFormat;

    public DecoderVideoRenderer(long r2, Handler r4, VideoRendererEventListener r5, int r6) {
        super(2);
        this.allowedJoiningTimeMs = r2;
        this.maxDroppedFramesToNotify = r6;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.formatQueue = new TimedValueQueue();
        this.flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(r4, r5);
        this.decoderReinitializationState = 0;
        this.outputMode = -1;
        this.firstFrameState = 0;
        this.decoderCounters = new DecoderCounters();
    }

    private boolean drainOutputBuffer(long r5, long r7) {
        if (this.outputBuffer != null) goto L9;
        VideoDecoderOutputBuffer r0 = (VideoDecoderOutputBuffer) ((Decoder) Assertions.checkNotNull(this.decoder)).dequeueOutputBuffer();
        this.outputBuffer = r0;
        if (r0 != null) goto L7;
        return false;
    L7:
        DecoderCounters r2 = this.decoderCounters;
        int r3 = r2.skippedOutputBufferCount;
        int r02 = r0.skippedOutputBufferCount;
        r2.skippedOutputBufferCount = r3 + r02;
        this.buffersInCodecCount -= r02;
    L9:
        if (this.outputBuffer.isEndOfStream() == true) goto L11;
        boolean r52 = processOutputBuffer(r5, r7);
        if (r52 == false) goto L18;
        onProcessedOutputBuffer(((VideoDecoderOutputBuffer) Assertions.checkNotNull(this.outputBuffer)).timeUs);
        this.outputBuffer = null;
    L18:
        return r52;
    L11:
        if (this.decoderReinitializationState != 2) goto L13;
        releaseDecoder();
        maybeInitDecoder();
    L14:
        return false;
    L13:
        this.outputBuffer.release();
        this.outputBuffer = null;
        this.outputStreamEnded = true;
        goto L14
    }

    private boolean feedInputBuffer() {
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> r0 = this.decoder;
        if (r0 != null) goto L5;
    L38:
        return false;
    L5:
        if (this.decoderReinitializationState == 2) goto L38;
        if (this.inputStreamEnded == true) goto L38;
        if (this.inputBuffer != null) goto L14;
        DecoderInputBuffer r02 = r0.dequeueInputBuffer();
        this.inputBuffer = r02;
        if (r02 != null) goto L14;
        return false;
    L14:
        DecoderInputBuffer r03 = (DecoderInputBuffer) Assertions.checkNotNull(this.inputBuffer);
        if (this.decoderReinitializationState != 1) goto L18;
        r03.setFlags(4);
        ((Decoder) Assertions.checkNotNull(this.decoder)).queueInputBuffer(r03);
        this.inputBuffer = null;
        this.decoderReinitializationState = 2;
        return false;
    L18:
        FormatHolder r2 = getFormatHolder();
        int r3 = readSource(r2, r03, 0);
        if (r3 != (-5)) goto L21;
        onInputFormatChanged(r2);
        return true;
    L21:
        if (r3 == (-4)) goto L28;
        if (r3 != (-3)) goto L25;
        return false;
    L25:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    L28:
        if (r03.isEndOfStream() == false) goto L32;
        this.inputStreamEnded = true;
        ((Decoder) Assertions.checkNotNull(this.decoder)).queueInputBuffer(r03);
        this.inputBuffer = null;
        return false;
    L32:
        if (this.waitingForFirstSampleInFormat == false) goto L34;
        this.formatQueue.add(r03.timeUs, (Format) Assertions.checkNotNull(this.inputFormat));
        this.waitingForFirstSampleInFormat = false;
    L34:
        r03.flip();
        r03.format = this.inputFormat;
        onQueueInputBuffer(r03);
        ((Decoder) Assertions.checkNotNull(this.decoder)).queueInputBuffer(r03);
        this.buffersInCodecCount++;
        this.decoderReceivedBuffers = true;
        this.decoderCounters.queuedInputBufferCount++;
        this.inputBuffer = null;
        return true;
    }

    private boolean hasOutput() {
        if (this.outputMode == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isBufferLate(long r2) {
        if (r2 >= (-30000)) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isBufferVeryLate(long r2) {
        if (r2 >= (-500000)) goto L6;
        return true;
    L6:
        return false;
    }

    private void lowerFirstFrameState(int r2) {
        this.firstFrameState = Math.min(this.firstFrameState, r2);
    }

    private void maybeInitDecoder() {
        if (this.decoder != null) goto L27;
        setDecoderDrmSession(this.sourceDrmSession);
        DrmSession r0 = this.decoderDrmSession;
        if (r0 == null) goto L13;
        CryptoConfig r02 = r0.getCryptoConfig();
        if (r02 != null) goto L25;
        if (this.decoderDrmSession.getError() != null) goto L25;
        return;
    L25:
        long r2 = SystemClock.elapsedRealtime();     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> r03 = createDecoder((Format) Assertions.checkNotNull(this.inputFormat), r02);     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        this.decoder = r03;     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        r03.setOutputStartTimeUs(getLastResetPositionUs());     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        setDecoderOutputMode(this.outputMode);     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        long r6 = SystemClock.elapsedRealtime();     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        this.eventDispatcher.decoderInitialized(((Decoder) Assertions.checkNotNull(this.decoder)).getName(), r6, r6 - r2);     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        this.decoderCounters.decoderInitCount++;
        return;
    L19:
        e = move-exception;
        Log.e("DecoderVideoRenderer", "Video codec error", e);
        this.eventDispatcher.videoCodecError(e);
        throw createRendererException(e, this.inputFormat, 4001);
    L17:
        e = move-exception;
        throw createRendererException(e, this.inputFormat, 4001);
    L13:
        r02 = null;
        goto L25
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames <= 0) goto L6;
        long r0 = SystemClock.elapsedRealtime();
        long r2 = r0 - this.droppedFrameAccumulationStartTimeMs;
        this.eventDispatcher.droppedFrames(this.droppedFrames, r2);
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = r0;
        return;
    }

    private void maybeNotifyRenderedFirstFrame() {
        if (this.firstFrameState == 3) goto L8;
        this.firstFrameState = 3;
        Object r0 = this.output;
        if (r0 == null) goto L9;
        this.eventDispatcher.renderedFirstFrame(r0);
        return;
    L9:
        return;
    }

    private void maybeNotifyVideoSizeChanged(int r3, int r4) {
        VideoSize r0 = this.reportedVideoSize;
        if (r0 != null) goto L5;
    L10:
        VideoSize r02 = new VideoSize(r3, r4);
        this.reportedVideoSize = r02;
        this.eventDispatcher.videoSizeChanged(r02);
        return;
    L5:
        if (r0.width != r3) goto L10;
        if (r0.height != r4) goto L10;
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.firstFrameState != 3) goto L8;
        Object r0 = this.output;
        if (r0 == null) goto L9;
        this.eventDispatcher.renderedFirstFrame(r0);
        return;
    L9:
        return;
    }

    private void maybeRenotifyVideoSizeChanged() {
        VideoSize r0 = this.reportedVideoSize;
        if (r0 == null) goto L6;
        this.eventDispatcher.videoSizeChanged(r0);
        return;
    }

    private void onOutputChanged() {
        maybeRenotifyVideoSizeChanged();
        lowerFirstFrameState(1);
        if (getState() != 2) goto L6;
        setJoiningDeadlineMs();
        return;
    }

    private void onOutputRemoved() {
        this.reportedVideoSize = null;
        lowerFirstFrameState(1);
    }

    private void onOutputReset() {
        maybeRenotifyVideoSizeChanged();
        maybeRenotifyRenderedFirstFrame();
    }

    private boolean processOutputBuffer(long r11, long r13) {
        if (this.initialPositionUs != (-9223372036854775807L)) goto L5;
        this.initialPositionUs = r11;
    L5:
        VideoDecoderOutputBuffer r0 = (VideoDecoderOutputBuffer) Assertions.checkNotNull(this.outputBuffer);
        long r1 = r0.timeUs;
        long r3 = r1 - r11;
        if (hasOutput() == false) goto L8;
        Format r5 = this.formatQueue.pollFloor(r1);
        if (r5 == null) goto L16;
        this.outputFormat = r5;
    L18:
        long r12 = r1 - getStreamOffsetUs();
        if (shouldForceRender(r3) == false) goto L23;
        renderOutputBuffer(r0, r12, (Format) Assertions.checkNotNull(this.outputFormat));
        return true;
    L23:
        if (getState() == 2) goto L25;
    L40:
        return false;
    L25:
        if (r11 == this.initialPositionUs) goto L40;
        if (shouldDropBuffersToKeyframe(r3, r13) == false) goto L33;
        if (maybeDropBuffersToKeyframe(r11) == false) goto L33;
        return false;
    L33:
        if (shouldDropOutputBuffer(r3, r13) == false) goto L37;
        dropOutputBuffer(r0);
        return true;
    L37:
        if (r3 >= NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L40;
        renderOutputBuffer(r0, r12, (Format) Assertions.checkNotNull(this.outputFormat));
        return true;
    L16:
        if (this.outputFormat != null) goto L18;
        this.outputFormat = this.formatQueue.pollFirst();
        goto L18
    L8:
        if (isBufferLate(r3) == false) goto L11;
        skipOutputBuffer(r0);
        return true;
    L11:
        return false;
    }

    private void setDecoderDrmSession(DrmSession r2) {
        DrmSession.replaceSession(this.decoderDrmSession, r2);
        this.decoderDrmSession = r2;
    }

    private void setJoiningDeadlineMs() {
        if (this.allowedJoiningTimeMs <= 0) goto L5;
        long r0 = SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs;
    L6:
        this.joiningDeadlineMs = r0;
        return;
    L5:
        r0 = -9223372036854775807L;
        goto L6
    }

    private void setSourceDrmSession(DrmSession r2) {
        DrmSession.replaceSession(this.sourceDrmSession, r2);
        this.sourceDrmSession = r2;
    }

    private boolean shouldForceRender(long r9) {
        if (getState() != 2) goto L5;
        boolean r0 = true;
    L6:
        int r1 = this.firstFrameState;
        if (r1 == 0) goto L20;
        if (r1 != 1) goto L10;
        return true;
    L10:
        if (r1 != 3) goto L17;
        long r4 = Util.msToUs(SystemClock.elapsedRealtime()) - this.lastRenderTimeUs;
        if (r0 == true) goto L14;
    L16:
        return false;
    L14:
        if (shouldForceRenderOutputBuffer(r9, r4) == false) goto L16;
        return true;
    L17:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    L20:
        return r0;
    L5:
        r0 = false;
        goto L6
    }

    public abstract DecoderReuseEvaluation canReuseDecoder(String r1, Format r2, Format r3);

    public abstract Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> createDecoder(Format r1, CryptoConfig r2);

    public void dropOutputBuffer(VideoDecoderOutputBuffer r3) {
        updateDroppedBufferCounters(0, 1);
        r3.release();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void enableMayRenderStartOfStream() {
        if (this.firstFrameState != 0) goto L6;
        this.firstFrameState = 1;
        return;
    }

    public void flushDecoder() {
        this.buffersInCodecCount = 0;
        if (this.decoderReinitializationState == 0) goto L6;
        releaseDecoder();
        maybeInitDecoder();
        return;
    L6:
        this.inputBuffer = null;
        VideoDecoderOutputBuffer r2 = this.outputBuffer;
        if (r2 == null) goto L9;
        r2.release();
        this.outputBuffer = null;
    L9:
        Decoder r1 = (Decoder) Assertions.checkNotNull(this.decoder);
        r1.flush();
        r1.setOutputStartTimeUs(getLastResetPositionUs());
        this.decoderReceivedBuffers = false;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int r2, Object r3) {
        if (r2 != 1) goto L7;
        setOutput(r3);
        return;
    L7:
        if (r2 != 7) goto L10;
        this.frameMetadataListener = (VideoFrameMetadataListener) r3;
        return;
    L10:
        super.handleMessage(r2, r3);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        if (this.inputFormat == null) goto L15;
        if (isSourceReady() == true) goto L9;
        if (this.outputBuffer == null) goto L15;
    L9:
        if (this.firstFrameState != 3) goto L11;
    L12:
        this.joiningDeadlineMs = -9223372036854775807L;
        return true;
    L11:
        if (hasOutput() == false) goto L12;
    L15:
        if (this.joiningDeadlineMs != (-9223372036854775807L)) goto L18;
        return false;
    L18:
        if (SystemClock.elapsedRealtime() >= this.joiningDeadlineMs) goto L20;
        return true;
    L20:
        this.joiningDeadlineMs = -9223372036854775807L;
        return false;
    }

    public boolean maybeDropBuffersToKeyframe(long r3) {
        int r32 = skipSource(r3);
        if (r32 != 0) goto L6;
        return false;
    L6:
        this.decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(r32, this.buffersInCodecCount);
        flushDecoder();
        return true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.reportedVideoSize = null;
        lowerFirstFrameState(0);
        setSourceDrmSession(null);     // Catch: Throwable -> L6
        releaseDecoder();     // Catch: Throwable -> L6
        this.eventDispatcher.disabled(this.decoderCounters);
        return;
    L6:
        th = move-exception;
        this.eventDispatcher.disabled(this.decoderCounters);
        throw th;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean r2, boolean r3) {
        DecoderCounters r22 = new DecoderCounters();
        this.decoderCounters = r22;
        this.eventDispatcher.enabled(r22);
        this.firstFrameState = r3 ? 1 : 0;
    }

    public void onInputFormatChanged(FormatHolder r9) {
        this.waitingForFirstSampleInFormat = true;
        Format r5 = (Format) Assertions.checkNotNull(r9.format);
        setSourceDrmSession(r9.drmSession);
        Format r92 = this.inputFormat;
        this.inputFormat = r5;
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> r1 = this.decoder;
        if (r1 != null) goto L7;
        maybeInitDecoder();
        this.eventDispatcher.inputFormatChanged((Format) Assertions.checkNotNull(this.inputFormat), null);
        return;
    L7:
        if (this.sourceDrmSession == this.decoderDrmSession) goto L9;
        DecoderReuseEvaluation r2 = new DecoderReuseEvaluation(r1.getName(), (Format) Assertions.checkNotNull(r92), r5, 0, 128);
    L11:
        if (r2.result == 0) goto L13;
    L16:
        this.eventDispatcher.inputFormatChanged((Format) Assertions.checkNotNull(this.inputFormat), r2);
        return;
    L13:
        if (this.decoderReceivedBuffers == false) goto L15;
        this.decoderReinitializationState = 1;
        goto L16
    L15:
        releaseDecoder();
        maybeInitDecoder();
        goto L16
    L9:
        r2 = canReuseDecoder(r1.getName(), (Format) Assertions.checkNotNull(r92), r5);
        goto L11
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r3, boolean r5) {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        lowerFirstFrameState(1);
        this.initialPositionUs = -9223372036854775807L;
        this.consecutiveDroppedFrameCount = 0;
        if (this.decoder == null) goto L5;
        flushDecoder();
    L5:
        if (r5 == false) goto L7;
        setJoiningDeadlineMs();
    L9:
        if (this.formatQueue.size() <= 0) goto L11;
        this.waitingForFirstSampleInFormat = true;
    L11:
        this.formatQueue.clear();
        return;
    L7:
        this.joiningDeadlineMs = -9223372036854775807L;
        goto L9
    }

    public void onProcessedOutputBuffer(long r1) {
        this.buffersInCodecCount--;
    }

    public void onQueueInputBuffer(DecoderInputBuffer r1) {
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = Util.msToUs(SystemClock.elapsedRealtime());
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
        this.joiningDeadlineMs = -9223372036854775807L;
        maybeNotifyDroppedFrames();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] r1, long r2, long r4, MediaSource.MediaPeriodId r6) {
        super.onStreamChanged(r1, r2, r4, r6);
    }

    public void releaseDecoder() {
        this.inputBuffer = null;
        this.outputBuffer = null;
        this.decoderReinitializationState = 0;
        this.decoderReceivedBuffers = false;
        this.buffersInCodecCount = 0;
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> r1 = this.decoder;
        if (r1 == null) goto L5;
        this.decoderCounters.decoderReleaseCount++;
        r1.release();
        this.eventDispatcher.decoderReleased(this.decoder.getName());
        this.decoder = null;
    L5:
        setDecoderDrmSession(null);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long r4, long r6) {
        if (this.outputStreamEnded == false) goto L6;
        return;
    L6:
        if (this.inputFormat != null) goto L14;
        FormatHolder r0 = getFormatHolder();
        this.flagsOnlyBuffer.clear();
        int r1 = readSource(r0, this.flagsOnlyBuffer, 2);
        if (r1 != (-5)) goto L11;
        onInputFormatChanged(r0);
        goto L14
    L11:
        if (r1 != (-4)) goto L29;
        Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
        this.inputStreamEnded = true;
        this.outputStreamEnded = true;
        return;
    L29:
        return;
    L14:
        maybeInitDecoder();
        if (this.decoder == null) goto L34;
        TraceUtil.beginSection("drainAndFeed");     // Catch: DecoderException -> L26
    L18:
        if (drainOutputBuffer(r4, r6) == true) goto L18;
    L21:
        if (feedInputBuffer() == true) goto L21;
        TraceUtil.endSection();     // Catch: DecoderException -> L26
        this.decoderCounters.ensureUpdated();
        return;
    L26:
        e = move-exception;
        Log.e("DecoderVideoRenderer", "Video codec error", e);
        this.eventDispatcher.videoCodecError(e);
        throw createRendererException(e, this.inputFormat, 4003);
    }

    public void renderOutputBuffer(VideoDecoderOutputBuffer r8, long r9, Format r11) {
        VideoFrameMetadataListener r0 = this.frameMetadataListener;
        if (r0 == null) goto L5;
        r0.onVideoFrameAboutToBeRendered(r9, getClock().nanoTime(), r11, null);
    L5:
        this.lastRenderTimeUs = Util.msToUs(SystemClock.elapsedRealtime());
        if (r8.mode == 1) goto L8;
    L10:
        boolean r92 = false;
    L11:
        if (r92 == true) goto L14;
        dropOutputBuffer(r8);
        return;
    L14:
        maybeNotifyVideoSizeChanged(r8.width, r8.height);
        renderOutputBufferToSurface(r8, (Surface) Assertions.checkNotNull(this.outputSurface));
        this.consecutiveDroppedFrameCount = 0;
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
        return;
    L8:
        if (this.outputSurface == null) goto L10;
        r92 = true;
        goto L11
    }

    public abstract void renderOutputBufferToSurface(VideoDecoderOutputBuffer r1, Surface r2);

    public abstract void setDecoderOutputMode(int r1);

    public final void setOutput(Object r2) {
        if ((r2 instanceof Surface) == false) goto L5;
        this.outputSurface = (Surface) r2;
        this.outputMode = 1;
    L7:
        if (this.output == r2) goto L17;
        this.output = r2;
        if (r2 != null) goto L11;
        onOutputRemoved();
        return;
    L11:
        if (this.decoder == null) goto L13;
        setDecoderOutputMode(this.outputMode);
    L13:
        onOutputChanged();
        return;
    L17:
        if (r2 == null) goto L20;
        onOutputReset();
        return;
    L20:
        return;
    L5:
        r2 = null;
        this.outputSurface = null;
        this.outputMode = -1;
        goto L7
    }

    public boolean shouldDropBuffersToKeyframe(long r1, long r3) {
        return isBufferVeryLate(r1);
    }

    public boolean shouldDropOutputBuffer(long r1, long r3) {
        return isBufferLate(r1);
    }

    public boolean shouldForceRenderOutputBuffer(long r1, long r3) {
        if (isBufferLate(r1) == true) goto L5;
        return false;
    L5:
        if (r3 <= 100000) goto L10;
        return true;
    L10:
        return false;
    }

    public void skipOutputBuffer(VideoDecoderOutputBuffer r2) {
        this.decoderCounters.skippedOutputBufferCount++;
        r2.release();
    }

    public void updateDroppedBufferCounters(int r3, int r4) {
        DecoderCounters r0 = this.decoderCounters;
        r0.droppedInputBufferCount += r3;
        int r32 = r3 + r4;
        r0.droppedBufferCount += r32;
        this.droppedFrames += r32;
        int r42 = this.consecutiveDroppedFrameCount + r32;
        this.consecutiveDroppedFrameCount = r42;
        r0.maxConsecutiveDroppedBufferCount = Math.max(r42, r0.maxConsecutiveDroppedBufferCount);
        int r33 = this.maxDroppedFramesToNotify;
        if (r33 > 0) goto L5;
        return;
    L5:
        if (this.droppedFrames < r33) goto L9;
        maybeNotifyDroppedFrames();
        return;
    }
}
