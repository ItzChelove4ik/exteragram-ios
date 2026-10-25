package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import android.os.Handler;
import android.os.SystemClock;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.SimpleDecoderOutputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class DecoderAudioRenderer<T extends Decoder<DecoderInputBuffer, ? extends SimpleDecoderOutputBuffer, ? extends DecoderException>> extends BaseRenderer implements MediaClock {
    private boolean allowPositionDiscontinuity;
    private final AudioSink audioSink;
    private boolean audioTrackNeedsConfigure;
    private long currentPositionUs;
    private T decoder;
    private DecoderCounters decoderCounters;
    private DrmSession decoderDrmSession;
    private boolean decoderReceivedBuffers;
    private int decoderReinitializationState;
    private int encoderDelay;
    private int encoderPadding;
    private final AudioRendererEventListener.EventDispatcher eventDispatcher;
    private boolean firstStreamSampleRead;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private boolean hasPendingReportedSkippedSilence;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private boolean isStarted;
    private long largestQueuedPresentationTimeUs;
    private long lastBufferInStreamPresentationTimeUs;
    private long nextBufferToWritePresentationTimeUs;
    private SimpleDecoderOutputBuffer outputBuffer;
    private boolean outputStreamEnded;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private DrmSession sourceDrmSession;

    /* renamed from: androidx.media3.exoplayer.audio.DecoderAudioRenderer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api23 {
        public static void setAudioSinkPreferredDevice(AudioSink r0, Object r1) {
            r0.setPreferredDevice((AudioDeviceInfo) r1);
        }
    }

    public final class AudioSinkListener implements AudioSink.Listener {
        final /* synthetic */ DecoderAudioRenderer this$0;

        private AudioSinkListener(DecoderAudioRenderer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ AudioSinkListener(DecoderAudioRenderer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioCapabilitiesChanged() {
            DecoderAudioRenderer.access$300(this.this$0);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioSessionIdChanged(int r1) {
            DecoderAudioRenderer.access$200(this.this$0).audioSessionIdChanged(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioSinkError(Exception r3) {
            Log.e("DecoderAudioRenderer", "Audio sink error", r3);
            DecoderAudioRenderer.access$200(this.this$0).audioSinkError(r3);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioTrackInitialized(AudioSink.AudioTrackConfig r1) {
            DecoderAudioRenderer.access$200(this.this$0).audioTrackInitialized(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioTrackReleased(AudioSink.AudioTrackConfig r1) {
            DecoderAudioRenderer.access$200(this.this$0).audioTrackReleased(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onPositionAdvancing(long r1) {
            DecoderAudioRenderer.access$200(this.this$0).positionAdvancing(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onPositionDiscontinuity() {
            this.this$0.onPositionDiscontinuity();
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onSilenceSkipped() {
            DecoderAudioRenderer.access$102(this.this$0, true);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onSkipSilenceEnabledChanged(boolean r1) {
            DecoderAudioRenderer.access$200(this.this$0).skipSilenceEnabledChanged(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onUnderrun(int r7, long r8, long r10) {
            DecoderAudioRenderer.access$200(this.this$0).underrun(r7, r8, r10);
        }
    }

    public DecoderAudioRenderer(Handler r3, AudioRendererEventListener r4, AudioSink r5) {
        super(1);
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(r3, r4);
        this.audioSink = r5;
        r5.setListener(new AudioSinkListener(this, null));
        this.flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
        this.decoderReinitializationState = 0;
        this.audioTrackNeedsConfigure = true;
        setOutputStreamOffsetUs(-9223372036854775807L);
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        this.lastBufferInStreamPresentationTimeUs = -9223372036854775807L;
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
    }

    public static /* synthetic */ boolean access$102(DecoderAudioRenderer r0, boolean r1) {
        r0.hasPendingReportedSkippedSilence = r1;
        return r1;
    }

    public static /* synthetic */ AudioRendererEventListener.EventDispatcher access$200(DecoderAudioRenderer r0) {
        return r0.eventDispatcher;
    }

    public static /* synthetic */ void access$300(DecoderAudioRenderer r0) {
        r0.onRendererCapabilitiesChanged();
    }

    private boolean drainOutputBuffer() {
        if (this.outputBuffer != null) goto L14;
        SimpleDecoderOutputBuffer r0 = (SimpleDecoderOutputBuffer) this.decoder.dequeueOutputBuffer();
        this.outputBuffer = r0;
        if (r0 != null) goto L7;
        return false;
    L7:
        int r02 = r0.skippedOutputBufferCount;
        if (r02 <= 0) goto L11;
        this.decoderCounters.skippedOutputBufferCount += r02;
        this.audioSink.handleDiscontinuity();
    L11:
        if (this.outputBuffer.isFirstSample() == false) goto L14;
        processFirstSampleOfStream();
    L14:
        if (this.outputBuffer.isEndOfStream() == true) goto L16;
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
        if (this.audioTrackNeedsConfigure == false) goto L27;
        this.audioSink.configure(getOutputFormat(this.decoder).buildUpon().setEncoderDelay(this.encoderDelay).setEncoderPadding(this.encoderPadding).setMetadata(this.inputFormat.metadata).setCustomData(this.inputFormat.customData).setId(this.inputFormat.id).setLabel(this.inputFormat.label).setLabels(this.inputFormat.labels).setLanguage(this.inputFormat.language).setSelectionFlags(this.inputFormat.selectionFlags).setRoleFlags(this.inputFormat.roleFlags).build(), 0, getChannelMapping(this.decoder));
        this.audioTrackNeedsConfigure = false;
    L27:
        AudioSink r03 = this.audioSink;
        SimpleDecoderOutputBuffer r4 = this.outputBuffer;
        if (r03.handleBuffer(r4.data, r4.timeUs, 1) == false) goto L31;
        this.decoderCounters.renderedOutputBufferCount++;
        this.outputBuffer.release();
        this.outputBuffer = null;
        return true;
    L31:
        this.nextBufferToWritePresentationTimeUs = this.outputBuffer.timeUs;
        return false;
    L16:
        if (this.decoderReinitializationState != 2) goto L18;
        releaseDecoder();
        maybeInitDecoder();
        this.audioTrackNeedsConfigure = true;
    L20:
        return false;
    L18:
        this.outputBuffer.release();
        this.outputBuffer = null;
        processEndOfStream();     // Catch: AudioSink.WriteException -> L21
    L21:
        e = move-exception;
        throw createRendererException(e, e.format, e.isRecoverable, 5002);
    }

    private boolean feedInputBuffer() {
        T r0 = this.decoder;
        if (r0 != null) goto L5;
    L46:
        return false;
    L5:
        if (this.decoderReinitializationState == 2) goto L46;
        if (this.inputStreamEnded == true) goto L46;
        if (this.inputBuffer != null) goto L15;
        DecoderInputBuffer r02 = (DecoderInputBuffer) r0.dequeueInputBuffer();
        this.inputBuffer = r02;
        if (r02 != null) goto L15;
        return false;
    L15:
        if (this.decoderReinitializationState != 1) goto L18;
        this.inputBuffer.setFlags(4);
        this.decoder.queueInputBuffer(this.inputBuffer);
        this.inputBuffer = null;
        this.decoderReinitializationState = 2;
        return false;
    L18:
        FormatHolder r03 = getFormatHolder();
        int r3 = readSource(r03, this.inputBuffer, 0);
        if (r3 != (-5)) goto L21;
        onInputFormatChanged(r03);
        return true;
    L21:
        if (r3 == (-4)) goto L31;
        if (r3 == (-3)) goto L25;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    L25:
        if (hasReadStreamToEnd() == false) goto L27;
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
    L27:
        return false;
    L31:
        if (this.inputBuffer.isEndOfStream() == false) goto L35;
        this.inputStreamEnded = true;
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
        this.decoder.queueInputBuffer(this.inputBuffer);
        this.inputBuffer = null;
        return false;
    L35:
        if (this.firstStreamSampleRead == true) goto L37;
        this.firstStreamSampleRead = true;
        this.inputBuffer.addFlag(134217728);
    L37:
        this.largestQueuedPresentationTimeUs = this.inputBuffer.timeUs;
        if (hasReadStreamToEnd() == false) goto L40;
    L41:
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
    L42:
        this.inputBuffer.flip();
        DecoderInputBuffer r04 = this.inputBuffer;
        r04.format = this.inputFormat;
        this.decoder.queueInputBuffer(r04);
        this.decoderReceivedBuffers = true;
        this.decoderCounters.queuedInputBufferCount++;
        this.inputBuffer = null;
        return true;
    L40:
        if (this.inputBuffer.isLastSample() == false) goto L42;
        goto L41
    }

    private void flushDecoder() {
        if (this.decoderReinitializationState == 0) goto L6;
        releaseDecoder();
        maybeInitDecoder();
        return;
    L6:
        this.inputBuffer = null;
        SimpleDecoderOutputBuffer r1 = this.outputBuffer;
        if (r1 == null) goto L9;
        r1.release();
        this.outputBuffer = null;
    L9:
        Decoder r0 = (Decoder) Assertions.checkNotNull(this.decoder);
        r0.flush();
        r0.setOutputStartTimeUs(getLastResetPositionUs());
        this.decoderReceivedBuffers = false;
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
        TraceUtil.beginSection("createAudioDecoder");     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        T r03 = (T) createDecoder(this.inputFormat, r02);
        this.decoder = r03;     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        r03.setOutputStartTimeUs(getLastResetPositionUs());     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        TraceUtil.endSection();     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        long r8 = SystemClock.elapsedRealtime();     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        this.eventDispatcher.decoderInitialized(this.decoder.getName(), r8, r8 - r2);     // Catch: OutOfMemoryError -> L17 DecoderException -> L19
        this.decoderCounters.decoderInitCount++;
        return;
    L19:
        e = move-exception;
        Log.e("DecoderAudioRenderer", "Audio codec error", e);
        this.eventDispatcher.audioCodecError(e);
        throw createRendererException(e, this.inputFormat, 4001);
    L17:
        e = move-exception;
        throw createRendererException(e, this.inputFormat, 4001);
    L13:
        r02 = null;
        goto L25
    }

    private void onInputFormatChanged(FormatHolder r8) {
        Format r4 = (Format) Assertions.checkNotNull(r8.format);
        setSourceDrmSession(r8.drmSession);
        Format r3 = this.inputFormat;
        this.inputFormat = r4;
        this.encoderDelay = r4.encoderDelay;
        this.encoderPadding = r4.encoderPadding;
        T r82 = this.decoder;
        if (r82 != null) goto L7;
        maybeInitDecoder();
        this.eventDispatcher.inputFormatChanged(this.inputFormat, null);
        return;
    L7:
        if (this.sourceDrmSession == this.decoderDrmSession) goto L9;
        DecoderReuseEvaluation r1 = new DecoderReuseEvaluation(r82.getName(), r3, r4, 0, 128);
    L11:
        if (r1.result == 0) goto L13;
    L16:
        this.eventDispatcher.inputFormatChanged(this.inputFormat, r1);
        return;
    L13:
        if (this.decoderReceivedBuffers == false) goto L15;
        this.decoderReinitializationState = 1;
        goto L16
    L15:
        releaseDecoder();
        maybeInitDecoder();
        this.audioTrackNeedsConfigure = true;
        goto L16
    L9:
        r1 = canReuseDecoder(r82.getName(), r3, r4);
        goto L11
    }

    private void processEndOfStream() {
        this.outputStreamEnded = true;
        this.audioSink.playToEndOfStream();
        this.nextBufferToWritePresentationTimeUs = this.lastBufferInStreamPresentationTimeUs;
    }

    private void processFirstSampleOfStream() {
        this.audioSink.handleDiscontinuity();
        if (this.pendingOutputStreamOffsetCount == 0) goto L6;
        setOutputStreamOffsetUs(this.pendingOutputStreamOffsetsUs[0]);
        int r0 = this.pendingOutputStreamOffsetCount - 1;
        this.pendingOutputStreamOffsetCount = r0;
        long[] r4 = this.pendingOutputStreamOffsetsUs;
        System.arraycopy(r4, 1, r4, 0, r0);
        return;
    }

    private void releaseDecoder() {
        this.inputBuffer = null;
        this.outputBuffer = null;
        this.decoderReinitializationState = 0;
        this.decoderReceivedBuffers = false;
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        this.lastBufferInStreamPresentationTimeUs = -9223372036854775807L;
        T r1 = this.decoder;
        if (r1 == null) goto L5;
        this.decoderCounters.decoderReleaseCount++;
        r1.release();
        this.eventDispatcher.decoderReleased(this.decoder.getName());
        this.decoder = null;
    L5:
        setDecoderDrmSession(null);
    }

    private void setDecoderDrmSession(DrmSession r2) {
        DrmSession.replaceSession(this.decoderDrmSession, r2);
        this.decoderDrmSession = r2;
    }

    private void setOutputStreamOffsetUs(long r3) {
        this.outputStreamOffsetUs = r3;
        if (r3 == (-9223372036854775807L)) goto L6;
        this.audioSink.setOutputStreamOffsetUs(r3);
        return;
    }

    private void setSourceDrmSession(DrmSession r2) {
        DrmSession.replaceSession(this.sourceDrmSession, r2);
        this.sourceDrmSession = r2;
    }

    private void updateCurrentPosition() {
        long r0 = this.audioSink.getCurrentPositionUs(isEnded());
        if (r0 != Long.MIN_VALUE) goto L5;
        return;
    L5:
        if (this.allowPositionDiscontinuity == true) goto L8;
        r0 = Math.max(this.currentPositionUs, r0);
    L8:
        this.currentPositionUs = r0;
        this.allowPositionDiscontinuity = false;
    }

    public DecoderReuseEvaluation canReuseDecoder(String r7, Format r8, Format r9) {
        return new DecoderReuseEvaluation(r7, r8, r9, 0, 1);
    }

    public abstract T createDecoder(Format r1, CryptoConfig r2);

    public int[] getChannelMapping(T r1) {
        return null;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public long getDurationToProgressUs(long r9, long r11) {
        if (this.nextBufferToWritePresentationTimeUs == (-9223372036854775807L)) goto L5;
        boolean r0 = true;
    L7:
        if (this.isStarted == true) goto L15;
        if (r0 == false) goto L10;
        return 1000000;
    L10:
        if (this.outputStreamEnded == true) goto L27;
        return NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L27:
        return 1000000;
    L15:
        long r6 = this.audioSink.getAudioTrackBufferSizeUs();
        if (r0 == true) goto L18;
    L26:
        return NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L18:
        if (r6 == (-9223372036854775807L)) goto L26;
        float r92 = Math.min(r6, this.nextBufferToWritePresentationTimeUs - r9);
        if (getPlaybackParameters() == null) goto L23;
        float r10 = getPlaybackParameters().speed;
    L25:
        return Math.max(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, ((long) ((r92 / r10) / 2.0f)) - (Util.msToUs(getClock().elapsedRealtime()) - r11));
    L23:
        r10 = 1.0f;
        goto L25
    L5:
        r0 = false;
        goto L7
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    public MediaClock getMediaClock() {
        return this;
    }

    public abstract Format getOutputFormat(T r1);

    @Override // androidx.media3.exoplayer.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.audioSink.getPlaybackParameters();
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public long getPositionUs() {
        if (getState() != 2) goto L6;
        updateCurrentPosition();
    L6:
        return this.currentPositionUs;
    }

    public final int getSinkFormatSupport(Format r1) {
        return this.audioSink.getFormatSupport(r1);
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int r2, Object r3) {
        if (r2 != 2) goto L5;
        this.audioSink.setVolume(((Float) r3).floatValue());
        return;
    L5:
        if (r2 != 3) goto L7;
        AudioSink r1 = this.audioSink;
        r1.setAudioAttributes((AudioAttributes) r3);
        return;
    L7:
        if (r2 != 6) goto L9;
        AudioSink r12 = this.audioSink;
        r12.setAuxEffectInfo((AuxEffectInfo) r3);
        return;
    L9:
        if (r2 != 12) goto L11;
        Api23.setAudioSinkPreferredDevice(this.audioSink, r3);
        return;
    L11:
        if (r2 != 9) goto L13;
        this.audioSink.setSkipSilenceEnabled(((Boolean) r3).booleanValue());
        return;
    L13:
        if (r2 == 10) goto L16;
        super.handleMessage(r2, r3);
        return;
    L16:
        this.audioSink.setAudioSessionId(((Integer) r3).intValue());
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public boolean hasSkippedSilenceSinceLastCall() {
        boolean r0 = this.hasPendingReportedSkippedSilence;
        this.hasPendingReportedSkippedSilence = false;
        return r0;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        if (this.outputStreamEnded == true) goto L5;
        return false;
    L5:
        if (this.audioSink.isEnded() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        if (this.audioSink.hasPendingData() == false) goto L5;
        return true;
    L5:
        if (this.inputFormat != null) goto L7;
        return false;
    L7:
        if (isSourceReady() == false) goto L9;
        return true;
    L9:
        if (this.outputBuffer != null) goto L17;
        return false;
    L17:
        return true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.audioTrackNeedsConfigure = true;
        setOutputStreamOffsetUs(-9223372036854775807L);
        this.hasPendingReportedSkippedSilence = false;
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
        setSourceDrmSession(null);     // Catch: Throwable -> L6
        releaseDecoder();     // Catch: Throwable -> L6
        this.audioSink.reset();     // Catch: Throwable -> L6
        this.eventDispatcher.disabled(this.decoderCounters);
        return;
    L6:
        th = move-exception;
        this.eventDispatcher.disabled(this.decoderCounters);
        throw th;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean r1, boolean r2) {
        DecoderCounters r12 = new DecoderCounters();
        this.decoderCounters = r12;
        this.eventDispatcher.enabled(r12);
        boolean r13 = getConfiguration().tunneling;
        AudioSink r22 = this.audioSink;
        if (r13 == false) goto L5;
        r22.enableTunnelingV21();
    L6:
        this.audioSink.setPlayerId(getPlayerId());
        this.audioSink.setClock(getClock());
        return;
    L5:
        r22.disableTunneling();
        goto L6
    }

    public void onPositionDiscontinuity() {
        this.allowPositionDiscontinuity = true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r1, boolean r3) {
        this.audioSink.flush();
        this.currentPositionUs = r1;
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
        this.hasPendingReportedSkippedSilence = false;
        this.allowPositionDiscontinuity = true;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.decoder == null) goto L6;
        flushDecoder();
        return;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
        this.audioSink.play();
        this.isStarted = true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
        updateCurrentPosition();
        this.audioSink.pause();
        this.isStarted = false;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] r3, long r4, long r6, MediaSource.MediaPeriodId r8) {
        super.onStreamChanged(r3, r4, r6, r8);
        this.firstStreamSampleRead = false;
        if (this.outputStreamOffsetUs != (-9223372036854775807L)) goto L6;
        setOutputStreamOffsetUs(r6);
        return;
    L6:
        int r32 = this.pendingOutputStreamOffsetCount;
        if (r32 != this.pendingOutputStreamOffsetsUs.length) goto L9;
        Log.w("DecoderAudioRenderer", "Too many stream changes, so dropping offset: " + this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
    L10:
        this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1] = r6;
        return;
    L9:
        this.pendingOutputStreamOffsetCount = r32 + 1;
        goto L10
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long r1, long r3) {
        if (this.outputStreamEnded == false) goto L10;
        this.audioSink.playToEndOfStream();     // Catch: AudioSink.WriteException -> L6
        this.nextBufferToWritePresentationTimeUs = this.lastBufferInStreamPresentationTimeUs;     // Catch: AudioSink.WriteException -> L6
        return;
    L6:
        e = move-exception;
        throw createRendererException(e, e.format, e.isRecoverable, 5002);
    L10:
        if (this.inputFormat != null) goto L22;
        FormatHolder r12 = getFormatHolder();
        this.flagsOnlyBuffer.clear();
        int r32 = readSource(r12, this.flagsOnlyBuffer, 2);
        if (r32 != (-5)) goto L15;
        onInputFormatChanged(r12);
        goto L22
    L15:
        if (r32 != (-4)) goto L51;
        Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
        this.inputStreamEnded = true;
        processEndOfStream();     // Catch: AudioSink.WriteException -> L19
        return;
    L19:
        e = move-exception;
        throw createRendererException(e, null, 5002);
    L51:
        return;
    L22:
        maybeInitDecoder();
        if (this.decoder != null) goto L56;
        return;
    L56:
        TraceUtil.beginSection("drainAndFeed");     // Catch: AudioSink.WriteException -> L35 AudioSink.InitializationException -> L37 AudioSink.ConfigurationException -> L39 DecoderException -> L41
    L27:
        if (drainOutputBuffer() == true) goto L27;
    L30:
        if (feedInputBuffer() == true) goto L30;
        TraceUtil.endSection();     // Catch: AudioSink.WriteException -> L35 AudioSink.InitializationException -> L37 AudioSink.ConfigurationException -> L39 DecoderException -> L41
        this.decoderCounters.ensureUpdated();
        return;
    L41:
        e = move-exception;
        Log.e("DecoderAudioRenderer", "Audio codec error", e);
        this.eventDispatcher.audioCodecError(e);
        throw createRendererException(e, this.inputFormat, 4003);
    L39:
        e = move-exception;
        throw createRendererException(e, e.format, 5001);
    L37:
        e = move-exception;
        throw createRendererException(e, e.format, e.isRecoverable, 5001);
    L35:
        e = move-exception;
        throw createRendererException(e, e.format, e.isRecoverable, 5002);
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public void setPlaybackParameters(PlaybackParameters r1) {
        this.audioSink.setPlaybackParameters(r1);
    }

    public final boolean sinkSupportsFormat(Format r1) {
        return this.audioSink.supportsFormat(r1);
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final int supportsFormat(Format r2) {
        if (MimeTypes.isAudio(r2.sampleMimeType) == false) goto L5;
        int r1 = supportsFormatInternal(r2);
        if (r1 > 2) goto L11;
        return RendererCapabilities.create(r1);
    L11:
        return RendererCapabilities.create(r1, 8, 32);
    L5:
        return RendererCapabilities.create(0);
    }

    public abstract int supportsFormatInternal(Format r1);
}
