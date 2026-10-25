package androidx.media3.exoplayer.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Build;
import android.os.Bundle;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.analytics.PlayerId$LogSessionIdApi31$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.audio.OggOpusAudioPacketizer;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.FrameworkCryptoConfig;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.extractor.OpusUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = null;
    private final float assumedMinimumCodecOperatingRate;
    private ArrayDeque<MediaCodecInfo> availableCodecInfos;
    private final DecoderInputBuffer buffer;
    private final BatchBuffer bypassBatchBuffer;
    private boolean bypassDrainAndReinitialize;
    private boolean bypassEnabled;
    private final DecoderInputBuffer bypassSampleBuffer;
    private boolean bypassSampleBufferPending;
    private MediaCodecAdapter codec;
    private int codecAdaptationWorkaroundMode;
    private final MediaCodecAdapter.Factory codecAdapterFactory;
    private int codecDrainAction;
    private int codecDrainState;
    private DrmSession codecDrmSession;
    private boolean codecHasOutputMediaFormat;
    private long codecHotswapDeadlineMs;
    private MediaCodecInfo codecInfo;
    private Format codecInputFormat;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagation;
    private boolean codecNeedsSosFlushWorkaround;
    private float codecOperatingRate;
    private MediaFormat codecOutputMediaFormat;
    private boolean codecOutputMediaFormatChanged;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private boolean codecRegisteredOnBufferAvailableListener;
    private float currentPlaybackSpeed;
    protected DecoderCounters decoderCounters;
    private final boolean enableDecoderFallback;
    private boolean experimentalEnableProcessedStreamChangedAtStart;
    private Format inputFormat;
    private int inputIndex;
    private boolean inputStreamEnded;
    private boolean isDecodeOnlyOutputBuffer;
    private boolean isLastOutputBuffer;
    private long largestQueuedPresentationTimeOfExpectedOutputBufferUs;
    private long largestQueuedPresentationTimeUs;
    private long lastBufferInStreamPresentationTimeUs;
    private long lastOutputBufferProcessedRealtimeMs;
    private long lastProcessedOutputBufferTimeUs;
    private final MediaCodecSelector mediaCodecSelector;
    private MediaCrypto mediaCrypto;
    private boolean needToNotifyOutputFormatChangeAfterStreamChange;
    private final DecoderInputBuffer noDataBuffer;
    private final OggOpusAudioPacketizer oggOpusAudioPacketizer;
    private ByteBuffer outputBuffer;
    private final MediaCodec.BufferInfo outputBufferInfo;
    private Format outputFormat;
    private int outputIndex;
    private boolean outputStreamEnded;
    private OutputStreamInfo outputStreamInfo;
    private boolean pendingOutputEndOfStream;
    private final ArrayDeque<OutputStreamInfo> pendingOutputStreamChanges;
    private ExoPlaybackException pendingPlaybackException;
    private DecoderInitializationException preferredDecoderInitializationException;
    private long renderTimeLimitMs;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private boolean skippedFlushAndWaitingForEarlierFrame;
    private long skippedFlushLastOutputBufferPresentationTimeUs;
    private DrmSession sourceDrmSession;
    private float targetPlaybackSpeed;
    private boolean waitingForFirstSampleInFormat;
    private Renderer.WakeupListener wakeupListener;

    /* renamed from: androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api31 {
        public static void setLogSessionIdToMediaCodecFormat(MediaCodecAdapter.Configuration r1, PlayerId r2) {
            LogSessionId r22 = r2.getLogSessionId();
            if (r22.equals(PlayerId$LogSessionIdApi31$$ExternalSyntheticApiModelOutline0.m()) == true) goto L6;
            r1.mediaFormat.setString("log-session-id", r22.getStringId());
            return;
        }
    }

    public static class DecoderInitializationException extends Exception {
        public final MediaCodecInfo codecInfo;
        public final String diagnosticInfo;
        public final DecoderInitializationException fallbackDecoderInitializationException;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format r11, Throwable r12, boolean r13, int r14) {
            this("Decoder init failed: [" + r14 + "], " + r11, r12, r11.sampleMimeType, r13, null, buildCustomDiagnosticInfo(r14), null);
        }

        public DecoderInitializationException(Format r11, Throwable r12, boolean r13, MediaCodecInfo r14) {
            String r3 = "Decoder init failed: " + r14.name + ", " + r11;
            String r5 = r11.sampleMimeType;
            if ((r12 instanceof MediaCodec.CodecException) == false) goto L6;
            String r112 = ((MediaCodec.CodecException) r12).getDiagnosticInfo();
        L7:
            this(r3, r12, r5, r13, r14, r112, null);
            return;
        L6:
            r112 = null;
            goto L7
        }

        private DecoderInitializationException(String r1, Throwable r2, String r3, boolean r4, MediaCodecInfo r5, String r6, DecoderInitializationException r7) {
            super(r1, r2);
            this.mimeType = r3;
            this.secureDecoderRequired = r4;
            this.codecInfo = r5;
            this.diagnosticInfo = r6;
            this.fallbackDecoderInitializationException = r7;
        }

        public static /* synthetic */ DecoderInitializationException access$000(DecoderInitializationException r0, DecoderInitializationException r1) {
            return r0.copyWithFallbackException(r1);
        }

        private static String buildCustomDiagnosticInfo(int r3) {
            if (r3 >= 0) goto L4;
            String r0 = "neg_";
        L6:
            return "androidx.media3.exoplayer.mediacodec.MediaCodecRenderer_" + r0 + Math.abs(r3);
        L4:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }

        private DecoderInitializationException copyWithFallbackException(DecoderInitializationException r9) {
            return new DecoderInitializationException(getMessage(), getCause(), this.mimeType, this.secureDecoderRequired, this.codecInfo, this.diagnosticInfo, r9);
        }
    }

    public final class MediaCodecRendererCodecAdapterListener implements MediaCodecAdapter.OnBufferAvailableListener {
        final /* synthetic */ MediaCodecRenderer this$0;

        private MediaCodecRendererCodecAdapterListener(MediaCodecRenderer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ MediaCodecRendererCodecAdapterListener(MediaCodecRenderer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.OnBufferAvailableListener
        public void onInputBufferAvailable() {
            if (MediaCodecRenderer.access$200(this.this$0) == null) goto L6;
            MediaCodecRenderer.access$200(this.this$0).onWakeup();
            return;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.OnBufferAvailableListener
        public void onOutputBufferAvailable() {
            if (MediaCodecRenderer.access$200(this.this$0) == null) goto L6;
            MediaCodecRenderer.access$200(this.this$0).onWakeup();
            return;
        }
    }

    public static final class OutputStreamInfo {
        public static final OutputStreamInfo UNSET = null;
        public final TimedValueQueue<Format> formatQueue;
        public final long previousStreamLastBufferTimeUs;
        public final long startPositionUs;
        public final long streamOffsetUs;

        static {
            UNSET = new OutputStreamInfo(-9223372036854775807L, -9223372036854775807L, -9223372036854775807L);
        }

        public OutputStreamInfo(long r1, long r3, long r5) {
            this.previousStreamLastBufferTimeUs = r1;
            this.startPositionUs = r3;
            this.streamOffsetUs = r5;
            this.formatQueue = new TimedValueQueue();
        }
    }

    static {
        ADAPTATION_WORKAROUND_BUFFER = new byte[]{0, 0, 1, 103, 66, -64, 11, -38, 37, -112, 0, 0, 1, 104, -50, 15, 19, 32, 0, 0, 1, 101, -120, -124, 13, -50, 113, 24, -96, 0, 47, -65, 28, 49, -61, 39, 93, 120};
    }

    public MediaCodecRenderer(int r1, MediaCodecAdapter.Factory r2, MediaCodecSelector r3, boolean r4, float r5) {
        super(r1);
        this.codecAdapterFactory = r2;
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(r3);
        this.enableDecoderFallback = r4;
        this.assumedMinimumCodecOperatingRate = r5;
        this.noDataBuffer = DecoderInputBuffer.newNoDataInstance();
        this.buffer = new DecoderInputBuffer(0);
        this.bypassSampleBuffer = new DecoderInputBuffer(2);
        BatchBuffer r12 = new BatchBuffer();
        this.bypassBatchBuffer = r12;
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.currentPlaybackSpeed = 1.0f;
        this.targetPlaybackSpeed = 1.0f;
        this.renderTimeLimitMs = -9223372036854775807L;
        this.pendingOutputStreamChanges = new ArrayDeque();
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        r12.ensureSpaceForWrite(0);
        r12.data.order(ByteOrder.nativeOrder());
        this.oggOpusAudioPacketizer = new OggOpusAudioPacketizer();
        this.codecOperatingRate = -1.0f;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecReconfigurationState = 0;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        this.lastBufferInStreamPresentationTimeUs = -9223372036854775807L;
        this.lastProcessedOutputBufferTimeUs = -9223372036854775807L;
        this.lastOutputBufferProcessedRealtimeMs = -9223372036854775807L;
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
        this.decoderCounters = new DecoderCounters();
        this.skippedFlushLastOutputBufferPresentationTimeUs = -9223372036854775807L;
        this.largestQueuedPresentationTimeOfExpectedOutputBufferUs = -9223372036854775807L;
    }

    public static /* synthetic */ Renderer.WakeupListener access$200(MediaCodecRenderer r0) {
        return r0.wakeupListener;
    }

    private void bypassRead() {
        Assertions.checkState(!this.inputStreamEnded);
        FormatHolder r0 = getFormatHolder();
        this.bypassSampleBuffer.clear();
    L3:
        this.bypassSampleBuffer.clear();
        int r2 = readSource(r0, this.bypassSampleBuffer, 0);
        if (r2 == (-5)) goto L48;
        if (r2 != (-4)) goto L8;
        if (this.bypassSampleBuffer.isEndOfStream() == true) goto L17;
        this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, this.bypassSampleBuffer.timeUs);
        if (hasReadStreamToEnd() == false) goto L22;
    L23:
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
    L25:
        if (this.waitingForFirstSampleInFormat == false) goto L32;
        Format r22 = (Format) Assertions.checkNotNull(this.inputFormat);
        this.outputFormat = r22;
        if (Objects.equals(r22.sampleMimeType, "audio/opus") == true) goto L29;
    L31:
        onOutputFormatChanged(this.outputFormat, null);
        this.waitingForFirstSampleInFormat = false;
        goto L32
    L29:
        if (this.outputFormat.initializationData.isEmpty() == true) goto L31;
        this.outputFormat = this.outputFormat.buildUpon().setEncoderDelay(OpusUtil.getPreSkipSamples(this.outputFormat.initializationData.get(0))).build();
    L32:
        this.bypassSampleBuffer.flip();
        Format r23 = this.outputFormat;
        if (r23 == null) goto L43;
        if (Objects.equals(r23.sampleMimeType, "audio/opus") == false) goto L43;
        if (this.bypassSampleBuffer.hasSupplementalData() == false) goto L40;
        DecoderInputBuffer r24 = this.bypassSampleBuffer;
        r24.format = this.outputFormat;
        handleInputBufferSupplementalData(r24);
    L40:
        if (OpusUtil.needToDecodeOpusFrame(getLastResetPositionUs(), this.bypassSampleBuffer.timeUs) == false) goto L43;
        this.oggOpusAudioPacketizer.packetize(this.bypassSampleBuffer, this.outputFormat.initializationData);
    L43:
        if (haveBypassBatchBufferAndNewSampleSameDecodeOnlyState() == false) goto L46;
        if (this.bypassBatchBuffer.append(this.bypassSampleBuffer) == true) goto L3;
    L46:
        this.bypassSampleBufferPending = true;
        return;
    L22:
        if (this.buffer.isLastSample() == false) goto L25;
    L17:
        this.inputStreamEnded = true;
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
        return;
    L8:
        if (r2 == (-3)) goto L10;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L10:
        if (hasReadStreamToEnd() == false) goto L56;
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
        return;
    L56:
        return;
    L48:
        onInputFormatChanged(r0);
    }

    private boolean bypassRender(long r18, long r20) {
        Assertions.checkState(!this.outputStreamEnded);
        if (this.bypassBatchBuffer.hasSamples() == false) goto L8;
        BatchBuffer r1 = this.bypassBatchBuffer;
        boolean r15 = false;
        if (processOutputBuffer(r18, r20, null, r1.data, this.outputIndex, 0, r1.getSampleCount(), this.bypassBatchBuffer.getFirstSampleTimeUs(), isDecodeOnly(getLastResetPositionUs(), this.bypassBatchBuffer.getLastSampleTimeUs()), this.bypassBatchBuffer.isEndOfStream(), (Format) Assertions.checkNotNull(this.outputFormat)) == false) goto L7;
        onProcessedOutputBuffer(this.bypassBatchBuffer.getLastSampleTimeUs());
        this.bypassBatchBuffer.clear();
    L10:
        if (this.inputStreamEnded == false) goto L14;
        this.outputStreamEnded = true;
        return r15;
    L14:
        if (this.bypassSampleBufferPending == false) goto L17;
        Assertions.checkState(this.bypassBatchBuffer.append(this.bypassSampleBuffer));
        this.bypassSampleBufferPending = r15;
    L17:
        if (this.bypassDrainAndReinitialize == true) goto L19;
    L25:
        bypassRead();
        if (this.bypassBatchBuffer.hasSamples() == false) goto L29;
        this.bypassBatchBuffer.flip();
    L29:
        if (this.bypassBatchBuffer.hasSamples() == false) goto L31;
        return true;
    L31:
        if (this.inputStreamEnded == false) goto L33;
        return true;
    L33:
        if (this.bypassDrainAndReinitialize == true) goto L38;
        return r15;
    L38:
        return true;
    L19:
        if (this.bypassBatchBuffer.hasSamples() == false) goto L22;
        return true;
    L22:
        disableBypass();
        this.bypassDrainAndReinitialize = r15;
        maybeInitCodecOrBypass();
        if (this.bypassEnabled == true) goto L25;
        return r15;
    L7:
        return false;
    L8:
        r15 = false;
        goto L10
    }

    private int codecAdaptationWorkaroundMode(String r2) {
        if (Build.VERSION.SDK_INT <= 25) goto L5;
        return 0;
    L5:
        if ("OMX.Exynos.avc.dec.secure".equals(r2) == false) goto L21;
        String r1 = Build.MODEL;
        if (r1.startsWith("SM-T585") == false) goto L9;
        return 2;
    L9:
        if (r1.startsWith("SM-A510") == false) goto L11;
        return 2;
    L11:
        if (r1.startsWith("SM-A520") == false) goto L13;
        return 2;
    L13:
        if (r1.startsWith("SM-J700") == false) goto L22;
        return 2;
    L22:
        return 0;
    L21:
        return 0;
    }

    private static boolean codecNeedsEosFlushWorkaround(String r0) {
        return false;
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String r0) {
        return false;
    }

    private static boolean codecNeedsEosPropagationWorkaround(MediaCodecInfo r3) {
        String r0 = r3.name;
        int r1 = Build.VERSION.SDK_INT;
        if (r1 > 25) goto L7;
        if ("OMX.rk.video_decoder.avc".equals(r0) == false) goto L7;
        return true;
    L7:
        if (r1 > 29) goto L21;
        if ("OMX.broadcom.video_decoder.tunnel".equals(r0) == false) goto L11;
        return true;
    L11:
        if ("OMX.broadcom.video_decoder.tunnel.secure".equals(r0) == false) goto L13;
        return true;
    L13:
        if ("OMX.bcm.vdec.avc.tunnel".equals(r0) == false) goto L15;
        return true;
    L15:
        if ("OMX.bcm.vdec.avc.tunnel.secure".equals(r0) == false) goto L17;
        return true;
    L17:
        if ("OMX.bcm.vdec.hevc.tunnel".equals(r0) == false) goto L19;
        return true;
    L19:
        if ("OMX.bcm.vdec.hevc.tunnel.secure".equals(r0) == false) goto L21;
        return true;
    L21:
        if ("Amazon".equals(Build.MANUFACTURER) == true) goto L23;
        return false;
    L23:
        if ("AFTS".equals(Build.MODEL) == true) goto L25;
        return false;
    L25:
        if (r3.secure == false) goto L38;
        return true;
    L38:
        return false;
    }

    private static boolean codecNeedsSosFlushWorkaround(String r2) {
        if (Build.VERSION.SDK_INT == 29) goto L5;
        return false;
    L5:
        if ("c2.android.aac.decoder".equals(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private void disableBypass() {
        this.bypassEnabled = false;
        resetBypassState();
    }

    private boolean drainAndFlushCodec() {
        if (this.codecReceivedBuffers == false) goto L9;
        this.codecDrainState = 1;
        if (this.codecNeedsEosFlushWorkaround == false) goto L8;
        this.codecDrainAction = 3;
        return false;
    L8:
        this.codecDrainAction = 1;
    L9:
        return true;
    }

    private void drainAndReinitializeCodec() {
        if (this.codecReceivedBuffers == false) goto L6;
        this.codecDrainState = 1;
        this.codecDrainAction = 3;
        return;
    L6:
        reinitializeCodec();
    }

    @TargetApi(23)
    private boolean drainAndUpdateCodecDrmSessionV23() {
        if (this.codecReceivedBuffers == false) goto L9;
        this.codecDrainState = 1;
        if (this.codecNeedsEosFlushWorkaround == false) goto L8;
        this.codecDrainAction = 3;
        return false;
    L8:
        this.codecDrainAction = 2;
    L10:
        return true;
    L9:
        updateDrmSessionV23();
        goto L10
    }

    private boolean drainOutputBuffer(long r18, long r20) {
        MediaCodecAdapter r5 = (MediaCodecAdapter) Assertions.checkNotNull(this.codec);
        if (hasOutputBuffer() == true) goto L48;
        if (this.codecNeedsEosOutputExceptionWorkaround == true) goto L7;
    L14:
        int r1 = r5.dequeueOutputBufferIndex(this.outputBufferInfo);
    L15:
        if (r1 >= 0) goto L34;
        if (r1 != (-2)) goto L21;
        processOutputMediaFormatChanged();
        return true;
    L21:
        if (this.codecNeedsEosPropagation == true) goto L23;
    L27:
        long r52 = this.lastOutputBufferProcessedRealtimeMs;
        if (r52 != (-9223372036854775807L)) goto L30;
    L32:
        return false;
    L30:
        if ((r52 + 100) >= getClock().currentTimeMillis()) goto L32;
        processEndOfStream();
        goto L32
    L23:
        if (this.inputStreamEnded == false) goto L25;
    L26:
        processEndOfStream();
        goto L27
    L25:
        if (this.codecDrainState != 2) goto L27;
    L34:
        if (this.shouldSkipAdaptationWorkaroundOutputBuffer == false) goto L37;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        r5.releaseOutputBuffer(r1, false);
        return true;
    L37:
        MediaCodec.BufferInfo r6 = this.outputBufferInfo;
        if (r6.size == 0) goto L40;
    L43:
        this.outputIndex = r1;
        ByteBuffer r12 = r5.getOutputBuffer(r1);
        this.outputBuffer = r12;
        if (r12 == null) goto L46;
        r12.position(this.outputBufferInfo.offset);
        ByteBuffer r13 = this.outputBuffer;
        MediaCodec.BufferInfo r62 = this.outputBufferInfo;
        r13.limit(r62.offset + r62.size);
    L46:
        updateOutputFormatForTime(this.outputBufferInfo.presentationTimeUs);
        goto L48
    L40:
        if ((r6.flags & 4) == 0) goto L43;
        processEndOfStream();
        return false;
    L7:
        if (this.codecReceivedEos == false) goto L14;
        r1 = r5.dequeueOutputBufferIndex(this.outputBufferInfo);     // Catch: IllegalStateException -> L10
    L10:
        processEndOfStream();
        if (this.outputStreamEnded == false) goto L13;
        releaseCodec();
    L13:
        return false;
    L48:
        if (this.outputBufferInfo.presentationTimeUs >= getLastResetPositionUs()) goto L50;
        boolean r14 = true;
    L51:
        this.isDecodeOnlyOutputBuffer = r14;
        long r63 = this.lastBufferInStreamPresentationTimeUs;
        if (r63 != (-9223372036854775807L)) goto L54;
    L56:
        boolean r15 = false;
    L57:
        this.isLastOutputBuffer = r15;
        if (this.skippedFlushAndWaitingForEarlierFrame == false) goto L66;
        long r64 = this.skippedFlushLastOutputBufferPresentationTimeUs;
        if (r64 != (-9223372036854775807L)) goto L62;
    L64:
        this.skippedFlushLastOutputBufferPresentationTimeUs = this.outputBufferInfo.presentationTimeUs;
        this.isDecodeOnlyOutputBuffer = true;
        this.isLastOutputBuffer = false;
        goto L66
    L62:
        if (this.outputBufferInfo.presentationTimeUs > r64) goto L64;
        this.skippedFlushAndWaitingForEarlierFrame = false;
        this.skippedFlushLastOutputBufferPresentationTimeUs = -9223372036854775807L;
    L66:
        if (this.codecNeedsEosOutputExceptionWorkaround == true) goto L68;
    L78:
        boolean r16 = false;
        ByteBuffer r65 = this.outputBuffer;
        int r7 = this.outputIndex;
        MediaCodec.BufferInfo r17 = this.outputBufferInfo;
        boolean r19 = processOutputBuffer(r18, r20, r5, r65, r7, r17.flags, 1, r17.presentationTimeUs, this.isDecodeOnlyOutputBuffer, this.isLastOutputBuffer, (Format) Assertions.checkNotNull(this.outputFormat));
    L79:
        if (r19 == false) goto L94;
        onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
        if ((this.outputBufferInfo.flags & 4) == 0) goto L83;
        boolean r4 = true;
    L84:
        if (r4 == false) goto L86;
    L90:
        resetOutputBuffer();
        if (r4 == true) goto L93;
        return true;
    L93:
        processEndOfStream();
        goto L94
    L86:
        if (this.codecReceivedEos == false) goto L90;
        if (this.isLastOutputBuffer == false) goto L90;
        this.lastOutputBufferProcessedRealtimeMs = getClock().currentTimeMillis();
        goto L90
    L83:
        r4 = r16;
    L94:
        return r16;
    L68:
        if (this.codecReceivedEos == false) goto L78;
        ByteBuffer r66 = this.outputBuffer;     // Catch: IllegalStateException -> L73
        int r72 = this.outputIndex;     // Catch: IllegalStateException -> L73
        MediaCodec.BufferInfo r110 = this.outputBufferInfo;     // Catch: IllegalStateException -> L73
        r16 = false;
        r19 = processOutputBuffer(r18, r20, r5, r66, r72, r110.flags, 1, r110.presentationTimeUs, this.isDecodeOnlyOutputBuffer, this.isLastOutputBuffer, (Format) Assertions.checkNotNull(this.outputFormat));     // Catch: IllegalStateException -> L95
    L74:
        processEndOfStream();
        if (this.outputStreamEnded == false) goto L77;
        releaseCodec();
    L77:
        return r16;
    L73:
        r16 = false;
        goto L74
    L54:
        if (r63 > this.outputBufferInfo.presentationTimeUs) goto L56;
        r15 = true;
        goto L57
    L50:
        r14 = false;
        goto L51
    }

    private boolean drmNeedsCodecReinitialization(MediaCodecInfo r5, Format r6, DrmSession r7, DrmSession r8) {
        if (r7 != r8) goto L6;
        return false;
    L6:
        if (r8 == null) goto L40;
        if (r7 == null) goto L40;
        CryptoConfig r1 = r8.getCryptoConfig();
        if (r1 != null) goto L12;
        return true;
    L12:
        CryptoConfig r2 = r7.getCryptoConfig();
        if (r2 == null) goto L40;
        if (r1.getClass().equals(r2.getClass()) == false) goto L40;
        if ((r1 instanceof FrameworkCryptoConfig) == true) goto L21;
        return false;
    L21:
        if (r8.getSchemeUuid().equals(r7.getSchemeUuid()) == true) goto L23;
        return true;
    L23:
        UUID r12 = C.PLAYREADY_UUID;
        if (r12.equals(r7.getSchemeUuid()) == true) goto L40;
        if (r12.equals(r8.getSchemeUuid()) == true) goto L40;
        if (r5.secure == false) goto L31;
    L39:
        return false;
    L31:
        if (r8.getState() != 2) goto L33;
    L38:
        return true;
    L33:
        if (r8.getState() == 3) goto L37;
        if (r8.getState() != 4) goto L39;
    L37:
        if (r8.requiresSecureDecoder((String) Assertions.checkNotNull(r6.sampleMimeType)) == false) goto L39;
    L40:
        return true;
    }

    private boolean feedInputBuffer() {
        if (this.codec == null) goto L111;
        int r0 = this.codecDrainState;
        if (r0 == 2) goto L111;
        if (this.inputStreamEnded == true) goto L111;
        if (r0 == 0) goto L11;
    L13:
        MediaCodecAdapter r4 = (MediaCodecAdapter) Assertions.checkNotNull(this.codec);
        if (this.inputIndex >= 0) goto L20;
        int r02 = r4.dequeueInputBufferIndex();
        this.inputIndex = r02;
        if (r02 >= 0) goto L18;
        return false;
    L18:
        this.buffer.data = r4.getInputBuffer(r02);
        this.buffer.clear();
    L20:
        if (this.codecDrainState != 1) goto L28;
        if (this.codecNeedsEosPropagation == true) goto L25;
        this.codecReceivedEos = true;
        r4.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
        resetInputBuffer();
    L25:
        this.codecDrainState = 2;
        return false;
    L28:
        if (this.codecNeedsAdaptationWorkaroundBuffer == false) goto L32;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        ByteBuffer r03 = (ByteBuffer) Assertions.checkNotNull(this.buffer.data);
        byte[] r2 = ADAPTATION_WORKAROUND_BUFFER;
        r03.put(r2);
        r4.queueInputBuffer(this.inputIndex, 0, r2.length, 0, 0);
        resetInputBuffer();
        this.codecReceivedBuffers = true;
        return true;
    L32:
        if (this.codecReconfigurationState != 1) goto L38;
        int r04 = 0;
    L35:
        if (r04 >= ((Format) Assertions.checkNotNull(this.codecInputFormat)).initializationData.size()) goto L37;
        ((ByteBuffer) Assertions.checkNotNull(this.buffer.data)).put(this.codecInputFormat.initializationData.get(r04));
        r04 = r04 + 1;
        goto L35
    L37:
        this.codecReconfigurationState = 2;
    L38:
        int r05 = ((ByteBuffer) Assertions.checkNotNull(this.buffer.data)).position();
        FormatHolder r5 = getFormatHolder();
        int r6 = readSource(r5, this.buffer, 0);     // Catch: DecoderInputBuffer.InsufficientCapacityException -> L108
        if (r6 != (-3)) goto L47;
        if (hasReadStreamToEnd() == false) goto L45;
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
    L45:
        return false;
    L47:
        if (r6 != (-5)) goto L54;
        if (this.codecReconfigurationState != 2) goto L51;
        this.buffer.clear();
        this.codecReconfigurationState = 1;
    L51:
        onInputFormatChanged(r5);
        return true;
    L54:
        if (this.buffer.isEndOfStream() == false) goto L68;
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
        if (this.codecReconfigurationState != 2) goto L58;
        this.buffer.clear();
        this.codecReconfigurationState = 1;
    L58:
        this.inputStreamEnded = true;
        if (this.codecReceivedBuffers == true) goto L63;
        processEndOfStream();
        return false;
    L63:
        if (this.codecNeedsEosPropagation == true) goto L66;
        this.codecReceivedEos = true;
        r4.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
        resetInputBuffer();
    L66:
        return false;
    L68:
        if (this.codecReceivedBuffers == true) goto L76;
        if (this.buffer.isKeyFrame() == true) goto L76;
        this.buffer.clear();
        if (this.codecReconfigurationState != 2) goto L74;
        this.codecReconfigurationState = 1;
    L74:
        return true;
    L76:
        if (shouldDiscardDecoderInputBuffer(this.buffer) == false) goto L78;
        return true;
    L78:
        boolean r3 = this.buffer.isEncrypted();
        if (r3 == false) goto L81;
        this.buffer.cryptoInfo.increaseClearDataFirstSubSampleBy(r05);
    L81:
        long r52 = this.buffer.timeUs;
        if (this.waitingForFirstSampleInFormat == true) goto L84;
    L88:
        this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, r52);
        if (hasReadStreamToEnd() == false) goto L91;
    L92:
        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
    L93:
        this.buffer.flip();
        if (this.buffer.hasSupplementalData() == false) goto L96;
        handleInputBufferSupplementalData(this.buffer);
    L96:
        onQueueInputBuffer(this.buffer);
        int r18 = getCodecBufferFlags(this.buffer);
        if (Build.VERSION.SDK_INT < 34) goto L101;
        if ((r18 & 32) == 0) goto L101;
    L103:
        if (r3 == false) goto L105;
        ((MediaCodecAdapter) Assertions.checkNotNull(r4)).queueSecureInputBuffer(this.inputIndex, 0, this.buffer.cryptoInfo, r52, r18);
    L106:
        resetInputBuffer();
        this.codecReceivedBuffers = true;
        this.codecReconfigurationState = 0;
        this.decoderCounters.queuedInputBufferCount++;
        return true;
    L105:
        ((MediaCodecAdapter) Assertions.checkNotNull(r4)).queueInputBuffer(this.inputIndex, 0, ((ByteBuffer) Assertions.checkNotNull(this.buffer.data)).limit(), r52, r18);
    L101:
        if (getConfiguration().tunneling == true) goto L103;
        this.largestQueuedPresentationTimeOfExpectedOutputBufferUs = Math.max(this.largestQueuedPresentationTimeOfExpectedOutputBufferUs, this.buffer.timeUs);
        goto L103
    L91:
        if (this.buffer.isLastSample() == false) goto L93;
    L84:
        if (this.pendingOutputStreamChanges.isEmpty() == true) goto L86;
        this.pendingOutputStreamChanges.peekLast().formatQueue.add(r52, (Format) Assertions.checkNotNull(this.inputFormat));
    L87:
        this.waitingForFirstSampleInFormat = false;
        goto L88
    L86:
        this.outputStreamInfo.formatQueue.add(r52, (Format) Assertions.checkNotNull(this.inputFormat));
    L108:
        e = move-exception;
        onCodecError(e);
        readSourceOmittingSampleData(0);
        flushCodec();
        return true;
    L11:
        if (shouldReinitCodec() == false) goto L13;
        drainAndReinitializeCodec();
    L111:
        return false;
    }

    private void flushCodec() {
        ((MediaCodecAdapter) Assertions.checkStateNotNull(this.codec)).flush();     // Catch: Throwable -> L5
        resetCodecStateForFlush();
        return;
    L5:
        th = move-exception;
        resetCodecStateForFlush();
        throw th;
    }

    private boolean flushOrReleaseCodec() {
        if (this.codec != null) goto L6;
        return false;
    L6:
        if (shouldReleaseCodecInsteadOfFlushing() == false) goto L10;
        releaseCodec();
        return true;
    L10:
        if (shouldFlushCodec() == false) goto L12;
        flushCodec();
    L13:
        return false;
    L12:
        onSkippedFlushCodec();
        goto L13
    }

    private List<MediaCodecInfo> getAvailableCodecInfos(boolean r4) {
        Format r0 = (Format) Assertions.checkNotNull(this.inputFormat);
        List<MediaCodecInfo> r1 = getDecoderInfos(this.mediaCodecSelector, r0, r4);
        if (r1.isEmpty() == false) goto L9;
        if (r4 == false) goto L9;
        List<MediaCodecInfo> r3 = getDecoderInfos(this.mediaCodecSelector, r0, false);
        if (r3.isEmpty() == true) goto L8;
        Log.w("MediaCodecRenderer", "Drm session requires secure decoder for " + r0.sampleMimeType + ", but no secure decoder available. Trying to proceed with " + r3 + ".");
    L8:
        return r3;
    L9:
        return r1;
    }

    private boolean hasOutputBuffer() {
        if (this.outputIndex < 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean haveBypassBatchBufferAndNewSampleSameDecodeOnlyState() {
        if (this.bypassBatchBuffer.hasSamples() == true) goto L5;
        return true;
    L5:
        long r2 = getLastResetPositionUs();
        if (isDecodeOnly(r2, this.bypassBatchBuffer.getLastSampleTimeUs()) != isDecodeOnly(r2, this.bypassSampleBuffer.timeUs)) goto L8;
        return true;
    L8:
        return false;
    }

    private void initBypass(Format r3) {
        disableBypass();
        String r32 = r3.sampleMimeType;
        if (MediaController.AUDIO_MIME_TYPE.equals(r32) == false) goto L5;
    L9:
        this.bypassBatchBuffer.setMaxSampleCount(32);
    L10:
        this.bypassEnabled = true;
        return;
    L5:
        if ("audio/mpeg".equals(r32) == true) goto L9;
        if ("audio/opus".equals(r32) == true) goto L9;
        this.bypassBatchBuffer.setMaxSampleCount(1);
        goto L10
    }

    private void initCodec(MediaCodecInfo r13, MediaCrypto r14) {
        this.codecInfo = r13;
        Format r1 = (Format) Assertions.checkNotNull(this.inputFormat);
        String r3 = r13.name;
        float r2 = getCodecOperatingRateV23(this.targetPlaybackSpeed, r1, getStreamFormats());
        if (r2 > this.assumedMinimumCodecOperatingRate) goto L5;
        r2 = -1.0f;
    L5:
        long r4 = getClock().elapsedRealtime();
        MediaCodecAdapter.Configuration r142 = getMediaCodecConfiguration(r13, r1, r14, r2);
        if (Build.VERSION.SDK_INT < 31) goto L33;
        Api31.setLogSessionIdToMediaCodecFormat(r142, getPlayerId());
    L33:
        TraceUtil.beginSection("createCodec:" + r3);     // Catch: Throwable -> L30
        MediaCodecAdapter r0 = this.codecAdapterFactory.createAdapter(r142);     // Catch: Throwable -> L30
        this.codec = r0;     // Catch: Throwable -> L30
        this.codecRegisteredOnBufferAvailableListener = r0.registerOnBufferAvailableListener(new MediaCodecRendererCodecAdapterListener(this, null));     // Catch: Throwable -> L30
        TraceUtil.endSection();
        long r6 = getClock().elapsedRealtime();
        if (r13.isFormatSupported(r1) == true) goto L12;
        Log.w("MediaCodecRenderer", Util.formatInvariant("Format exceeds selected codec's capabilities [%s, %s]", new Object[]{Format.toLogString(r1), r3}));
    L12:
        this.codecOperatingRate = r2;
        this.codecInputFormat = r1;
        this.codecAdaptationWorkaroundMode = codecAdaptationWorkaroundMode(r3);
        this.codecNeedsSosFlushWorkaround = codecNeedsSosFlushWorkaround(r3);
        this.codecNeedsEosFlushWorkaround = codecNeedsEosFlushWorkaround(r3);
        this.codecNeedsEosOutputExceptionWorkaround = codecNeedsEosOutputExceptionWorkaround(r3);
        boolean r02 = false;
        if (codecNeedsEosPropagationWorkaround(r13) == false) goto L15;
    L18:
        boolean r132 = true;
    L19:
        this.codecNeedsEosPropagation = r132;
        if (((MediaCodecAdapter) Assertions.checkNotNull(this.codec)).needsReconfiguration() == false) goto L26;
        this.codecReconfigured = true;
        this.codecReconfigurationState = 1;
        if (this.codecAdaptationWorkaroundMode == 0) goto L24;
        r02 = true;
    L24:
        this.codecNeedsAdaptationWorkaroundBuffer = r02;
    L26:
        if (getState() != 2) goto L28;
        this.codecHotswapDeadlineMs = getClock().elapsedRealtime() + 1000;
    L28:
        this.decoderCounters.decoderInitCount++;
        onCodecInitialized(r3, r142, r6, r6 - r4);
        return;
    L15:
        if (getCodecNeedsEosPropagation() == true) goto L18;
        r132 = false;
    L30:
        th = move-exception;
        TraceUtil.endSection();
        throw th;
    }

    @RequiresNonNull({"this.codecDrmSession"})
    private boolean initMediaCryptoIfDrmSessionReady() {
        if (this.mediaCrypto != null) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        DrmSession r02 = this.codecDrmSession;
        CryptoConfig r3 = r02.getCryptoConfig();
        if (FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC == true) goto L9;
    L17:
        if (r3 != null) goto L23;
        if (r02.getError() == null) goto L21;
        return true;
    L21:
        return false;
    L23:
        if ((r3 instanceof FrameworkCryptoConfig) == false) goto L30;
        FrameworkCryptoConfig r32 = (FrameworkCryptoConfig) r3;
        this.mediaCrypto = new MediaCrypto(r32.uuid, r32.sessionId);     // Catch: MediaCryptoException -> L27
    L27:
        e = move-exception;
        throw createRendererException(e, this.inputFormat, 6006);
    L30:
        return true;
    L9:
        if ((r3 instanceof FrameworkCryptoConfig) == false) goto L17;
        int r4 = r02.getState();
        if (r4 != 1) goto L13;
        DrmSession.DrmSessionException r03 = (DrmSession.DrmSessionException) Assertions.checkNotNull(r02.getError());
        throw createRendererException(r03, this.inputFormat, r03.errorCode);
    L13:
        if (r4 == 4) goto L17;
        return false;
    L5:
        r0 = false;
        goto L6
    }

    private boolean isDecodeOnly(long r2, long r4) {
        if (r4 >= r2) goto L12;
        Format r1 = this.outputFormat;
        if (r1 != null) goto L7;
        return true;
    L7:
        if (Objects.equals(r1.sampleMimeType, "audio/opus") == true) goto L9;
        return true;
    L9:
        if (OpusUtil.needToDecodeOpusFrame(r2, r4) == true) goto L16;
        return true;
    L16:
        return false;
    L12:
        return false;
    }

    private static boolean isMediaCodecException(IllegalStateException r3) {
        if ((r3 instanceof MediaCodec.CodecException) == false) goto L5;
        return true;
    L5:
        StackTraceElement[] r32 = r3.getStackTrace();
        if (r32.length > 0) goto L8;
    L10:
        return false;
    L8:
        if (r32[0].getClassName().equals("android.media.MediaCodec") == false) goto L10;
        return true;
    }

    private void maybeInitCodecWithFallback(MediaCrypto r8, boolean r9) {
        Format r0 = (Format) Assertions.checkNotNull(this.inputFormat);
        if (this.availableCodecInfos != null) goto L17;
        List<MediaCodecInfo> r1 = getAvailableCodecInfos(r9);     // Catch: MediaCodecUtil.DecoderQueryException -> L7
        ArrayDeque<MediaCodecInfo> r3 = new ArrayDeque();     // Catch: MediaCodecUtil.DecoderQueryException -> L7
        this.availableCodecInfos = r3;     // Catch: MediaCodecUtil.DecoderQueryException -> L7
        if (this.enableDecoderFallback == false) goto L10;
        r3.addAll(r1);     // Catch: MediaCodecUtil.DecoderQueryException -> L7
    L12:
        this.preferredDecoderInitializationException = null;     // Catch: MediaCodecUtil.DecoderQueryException -> L7
        goto L17
    L10:
        if (r1.isEmpty() == true) goto L12;
        this.availableCodecInfos.add(r1.get(0));     // Catch: MediaCodecUtil.DecoderQueryException -> L7
    L7:
        e = move-exception;
        throw new DecoderInitializationException(r0, e, r9, -49998);
    L17:
        if (this.availableCodecInfos.isEmpty() == true) goto L42;
        ArrayDeque r12 = (ArrayDeque) Assertions.checkNotNull(this.availableCodecInfos);
    L20:
        if (this.codec != null) goto L39;
        MediaCodecInfo r32 = (MediaCodecInfo) Assertions.checkNotNull((MediaCodecInfo) r12.peekFirst());
        if (maybeInitializeProcessingPipeline(r0) == false) goto L26;
        if (shouldInitCodec(r32) == false) goto L54;
        initCodec(r32, r8);     // Catch: Exception -> L29
    L29:
        e = move-exception;
        Log.w("MediaCodecRenderer", "Failed to initialize decoder: " + r32, e);
        r12.removeFirst();
        DecoderInitializationException r5 = new DecoderInitializationException(r0, e, r9, r32);
        onCodecError(r5);
        if (this.preferredDecoderInitializationException != null) goto L33;
        this.preferredDecoderInitializationException = r5;
    L35:
        if (r12.isEmpty() == false) goto L20;
        throw this.preferredDecoderInitializationException;
    L33:
        this.preferredDecoderInitializationException = DecoderInitializationException.access$000(this.preferredDecoderInitializationException, r5);
        goto L35
    L54:
        return;
    L26:
        return;
    L39:
        this.availableCodecInfos = null;
        return;
    L42:
        throw new DecoderInitializationException(r0, null, r9, -49999);
    }

    private void onSkippedFlushCodec() {
        if (this.largestQueuedPresentationTimeOfExpectedOutputBufferUs == (-9223372036854775807L)) goto L10;
        long r0 = getLastResetPositionUs();
        long r4 = this.largestQueuedPresentationTimeOfExpectedOutputBufferUs;
        if (r0 <= r4) goto L7;
        return;
    L7:
        if (this.lastProcessedOutputBufferTimeUs >= r4) goto L12;
        this.skippedFlushAndWaitingForEarlierFrame = true;
        this.largestQueuedPresentationTimeOfExpectedOutputBufferUs = -9223372036854775807L;
        return;
    L12:
        return;
    }

    @TargetApi(23)
    private void processEndOfStream() {
        int r0 = this.codecDrainAction;
        if (r0 != 1) goto L5;
        flushCodec();
        return;
    L5:
        if (r0 != 2) goto L7;
        flushCodec();
        updateDrmSessionV23();
        return;
    L7:
        if (r0 == 3) goto L10;
        this.outputStreamEnded = true;
        renderToEndOfStream();
        return;
    L10:
        reinitializeCodec();
    }

    private void processOutputMediaFormatChanged() {
        this.codecHasOutputMediaFormat = true;
        MediaFormat r1 = ((MediaCodecAdapter) Assertions.checkNotNull(this.codec)).getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0) goto L5;
    L10:
        this.codecOutputMediaFormat = r1;
        this.codecOutputMediaFormatChanged = true;
        return;
    L5:
        if (r1.getInteger("width") != 32) goto L10;
        if (r1.getInteger("height") != 32) goto L10;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
    }

    private boolean readSourceOmittingSampleData(int r4) {
        FormatHolder r0 = getFormatHolder();
        this.noDataBuffer.clear();
        int r42 = readSource(r0, this.noDataBuffer, r4 | 4);
        if (r42 != (-5)) goto L7;
        onInputFormatChanged(r0);
        return true;
    L7:
        if (r42 == (-4)) goto L9;
        return false;
    L9:
        if (this.noDataBuffer.isEndOfStream() == false) goto L14;
        this.inputStreamEnded = true;
        processEndOfStream();
        return false;
    L14:
        return false;
    }

    private void reinitializeCodec() {
        releaseCodec();
        maybeInitCodecOrBypass();
    }

    private void resetBypassState() {
        resetCommonStateForFlush();
        this.bypassDrainAndReinitialize = false;
        this.bypassBatchBuffer.clear();
        this.bypassSampleBuffer.clear();
        this.bypassSampleBufferPending = false;
        this.oggOpusAudioPacketizer.reset();
    }

    private void resetCommonStateForFlush() {
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        this.lastBufferInStreamPresentationTimeUs = -9223372036854775807L;
        this.lastProcessedOutputBufferTimeUs = -9223372036854775807L;
    }

    private void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    private void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    private void setCodecDrmSession(DrmSession r2) {
        DrmSession.replaceSession(this.codecDrmSession, r2);
        this.codecDrmSession = r2;
    }

    private void setOutputStreamInfo(OutputStreamInfo r5) {
        this.outputStreamInfo = r5;
        long r0 = r5.streamOffsetUs;
        if (r0 == (-9223372036854775807L)) goto L6;
        this.needToNotifyOutputFormatChangeAfterStreamChange = true;
        onOutputStreamOffsetUsChanged(r0);
        return;
    }

    private void setSourceDrmSession(DrmSession r2) {
        DrmSession.replaceSession(this.sourceDrmSession, r2);
        this.sourceDrmSession = r2;
    }

    private boolean shouldContinueRendering(long r5) {
        if (this.renderTimeLimitMs != (-9223372036854775807L)) goto L5;
        return true;
    L5:
        if ((getClock().elapsedRealtime() - r5) < this.renderTimeLimitMs) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean supportsFormatDrm(Format r1) {
        int r12 = r1.cryptoType;
        if (r12 != 0) goto L5;
        return true;
    L5:
        if (r12 == 2) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean updateCodecOperatingRate(Format r5) {
        if (this.codec != null) goto L5;
    L23:
        return true;
    L5:
        if (this.codecDrainAction == 3) goto L23;
        if (getState() == 0) goto L23;
        float r52 = getCodecOperatingRateV23(this.targetPlaybackSpeed, (Format) Assertions.checkNotNull(r5), getStreamFormats());
        float r0 = this.codecOperatingRate;
        if (r0 != r52) goto L13;
        return true;
    L13:
        if (r52 != (-1.0f)) goto L17;
        drainAndReinitializeCodec();
        return false;
    L17:
        if (r0 == (-1.0f)) goto L19;
    L22:
        Bundle r02 = new Bundle();
        r02.putFloat("operating-rate", r52);
        ((MediaCodecAdapter) Assertions.checkNotNull(this.codec)).setParameters(r02);
        this.codecOperatingRate = r52;
        goto L23
    L19:
        if (r52 > this.assumedMinimumCodecOperatingRate) goto L22;
        return true;
    }

    private void updateDrmSessionV23() {
        CryptoConfig r0 = ((DrmSession) Assertions.checkNotNull(this.sourceDrmSession)).getCryptoConfig();
        if ((r0 instanceof FrameworkCryptoConfig) == true) goto L11;
    L9:
        setCodecDrmSession(this.sourceDrmSession);
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
        return;
    L11:
        ((MediaCrypto) Assertions.checkNotNull(this.mediaCrypto)).setMediaDrmSession(((FrameworkCryptoConfig) r0).sessionId);     // Catch: MediaCryptoException -> L6
    L6:
        e = move-exception;
        throw createRendererException(e, this.inputFormat, 6006);
    }

    public abstract DecoderReuseEvaluation canReuseCodec(MediaCodecInfo r1, Format r2, Format r3);

    public MediaCodecDecoderException createDecoderException(Throwable r1, MediaCodecInfo r2) {
        return new MediaCodecDecoderException(r1, r2);
    }

    public void experimentalEnableProcessedStreamChangedAtStart() {
        this.experimentalEnableProcessedStreamChangedAtStart = true;
    }

    public final boolean flushOrReinitializeCodec() {
        boolean r0 = flushOrReleaseCodec();
        if (r0 == false) goto L5;
        maybeInitCodecOrBypass();
    L5:
        return r0;
    }

    public final MediaCodecAdapter getCodec() {
        return this.codec;
    }

    public int getCodecBufferFlags(DecoderInputBuffer r1) {
        return 0;
    }

    public final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    public final Format getCodecInputFormat() {
        return this.codecInputFormat;
    }

    public boolean getCodecNeedsEosPropagation() {
        return false;
    }

    public abstract float getCodecOperatingRateV23(float r1, Format r2, Format[] r3);

    public final MediaFormat getCodecOutputMediaFormat() {
        return this.codecOutputMediaFormat;
    }

    public abstract List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector r1, Format r2, boolean r3);

    @Override // androidx.media3.exoplayer.Renderer
    public final long getDurationToProgressUs(long r7, long r9) {
        return getDurationToProgressUs(r7, r9, this.codecRegisteredOnBufferAvailableListener);
    }

    public long getDurationToProgressUs(long r1, long r3, boolean r5) {
        return super.getDurationToProgressUs(r1, r3);
    }

    public long getLastBufferInStreamPresentationTimeUs() {
        return this.lastBufferInStreamPresentationTimeUs;
    }

    public abstract MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo r1, Format r2, MediaCrypto r3, float r4);

    public final long getOutputStreamOffsetUs() {
        return this.outputStreamInfo.streamOffsetUs;
    }

    public final long getOutputStreamStartPositionUs() {
        return this.outputStreamInfo.startPositionUs;
    }

    public float getPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    public final Renderer.WakeupListener getWakeupListener() {
        return this.wakeupListener;
    }

    public abstract void handleInputBufferSupplementalData(DecoderInputBuffer r1);

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int r2, Object r3) {
        if (r2 != 11) goto L6;
        this.wakeupListener = (Renderer.WakeupListener) Assertions.checkNotNull((Renderer.WakeupListener) r3);
        return;
    L6:
        super.handleMessage(r2, r3);
    }

    public boolean hasSkippedFlushAndWaitingForEarlierFrame() {
        return this.skippedFlushAndWaitingForEarlierFrame;
    }

    public final boolean isBypassEnabled() {
        return this.bypassEnabled;
    }

    public final boolean isBypassPossible(Format r2) {
        if (this.sourceDrmSession == null) goto L5;
        return false;
    L5:
        if (shouldUseBypass(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        if (this.inputFormat != null) goto L5;
        return false;
    L5:
        if (isSourceReady() == false) goto L7;
        return true;
    L7:
        if (hasOutputBuffer() == false) goto L9;
        return true;
    L9:
        if (this.codecHotswapDeadlineMs != (-9223372036854775807L)) goto L11;
        return false;
    L11:
        if (getClock().elapsedRealtime() >= this.codecHotswapDeadlineMs) goto L19;
        return true;
    L19:
        return false;
    }

    public final void maybeInitCodecOrBypass() {
        if (this.codec == null) goto L5;
        return;
    L5:
        if (this.bypassEnabled == true) goto L42;
        Format r0 = this.inputFormat;
        if (r0 != null) goto L10;
        return;
    L10:
        if (isBypassPossible(r0) == false) goto L13;
        initBypass(r0);
        return;
    L13:
        setCodecDrmSession(this.sourceDrmSession);
        if (this.codecDrmSession != null) goto L16;
    L40:
        DrmSession r1 = this.codecDrmSession;     // Catch: DecoderInitializationException -> L24
        if (r1 != null) goto L20;
    L29:
        boolean r12 = false;
    L30:
        maybeInitCodecWithFallback(this.mediaCrypto, r12);     // Catch: DecoderInitializationException -> L24
    L31:
        MediaCrypto r02 = this.mediaCrypto;
        if (r02 != null) goto L34;
        return;
    L34:
        if (this.codec != null) goto L44;
        r02.release();
        this.mediaCrypto = null;
        return;
    L44:
        return;
    L20:
        if (r1.getState() == 3) goto L27;
        if (this.codecDrmSession.getState() != 4) goto L29;
    L27:
        if (this.codecDrmSession.requiresSecureDecoder((String) Assertions.checkStateNotNull(r0.sampleMimeType)) == false) goto L29;
        r12 = true;
    L24:
        e = move-exception;
        throw createRendererException(e, r0, 4001);
    L16:
        if (initMediaCryptoIfDrmSessionReady() == false) goto L31;
    }

    public boolean maybeInitializeProcessingPipeline(Format r1) {
        return true;
    }

    public abstract void onCodecError(Exception r1);

    public abstract void onCodecInitialized(String r1, MediaCodecAdapter.Configuration r2, long r3, long r5);

    public abstract void onCodecReleased(String r1);

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        setOutputStreamInfo(OutputStreamInfo.UNSET);
        this.pendingOutputStreamChanges.clear();
        if (this.bypassEnabled == false) goto L6;
        disableBypass();
        return;
    L6:
        flushOrReleaseCodec();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean r1, boolean r2) {
        this.decoderCounters = new DecoderCounters();
    }

    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder r13) {
        boolean r0 = true;
        this.waitingForFirstSampleInFormat = true;
        Format r1 = (Format) Assertions.checkNotNull(r13.format);
        String r2 = r1.sampleMimeType;
        if (r2 == null) goto L81;
        if (Objects.equals(r2, "video/av01") == true) goto L9;
        if (Objects.equals(r1.sampleMimeType, "video/x-vnd.on2.vp9") == true) goto L9;
    L11:
        Format r7 = r1;
        setSourceDrmSession(r13.drmSession);
        this.inputFormat = r7;
        if (this.bypassEnabled == false) goto L15;
        this.bypassDrainAndReinitialize = true;
        return null;
    L15:
        MediaCodecAdapter r132 = this.codec;
        if (r132 != null) goto L19;
        this.availableCodecInfos = null;
        maybeInitCodecOrBypass();
        return null;
    L19:
        MediaCodecInfo r12 = (MediaCodecInfo) Assertions.checkNotNull(this.codecInfo);
        Format r6 = (Format) Assertions.checkNotNull(this.codecInputFormat);
        if (drmNeedsCodecReinitialization(r12, r7, this.codecDrmSession, this.sourceDrmSession) == false) goto L23;
        drainAndReinitializeCodec();
        return new DecoderReuseEvaluation(r12.name, r6, r7, 0, 128);
    L23:
        int r5 = 0;
        if (this.sourceDrmSession == this.codecDrmSession) goto L26;
        boolean r22 = true;
    L27:
        Assertions.checkState(true);
        DecoderReuseEvaluation r4 = canReuseCodec(r12, r6, r7);
        int r8 = r4.result;
        if (r8 != 0) goto L30;
        drainAndReinitializeCodec();
    L73:
        if (r4.result != 0) goto L75;
        return r4;
    L75:
        if (this.codec != r132) goto L79;
        if (this.codecDrainAction == 3) goto L79;
        return r4;
    L79:
        return new DecoderReuseEvaluation(r12.name, r6, r7, 0, r5);
    L30:
        if (r8 == 1) goto L61;
        if (r8 == 2) goto L44;
        if (r8 == 3) goto L34;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L34:
        if (updateCodecOperatingRate(r7) == true) goto L36;
    L35:
        r5 = 16;
        goto L73
    L36:
        this.codecInputFormat = r7;
        if (r22 == false) goto L73;
        if (drainAndUpdateCodecDrmSessionV23() == true) goto L73;
    L40:
        r5 = 2;
        goto L73
    L44:
        if (updateCodecOperatingRate(r7) == false) goto L35;
        this.codecReconfigured = true;
        this.codecReconfigurationState = 1;
        int r3 = this.codecAdaptationWorkaroundMode;
        if (r3 == 2) goto L55;
        if (r3 == 1) goto L50;
    L54:
        r0 = false;
        goto L55
    L50:
        if (r7.width != r6.width) goto L54;
        if (r7.height != r6.height) goto L54;
    L55:
        this.codecNeedsAdaptationWorkaroundBuffer = r0;
        this.codecInputFormat = r7;
        if (r22 == false) goto L73;
        if (drainAndUpdateCodecDrmSessionV23() == true) goto L73;
    L61:
        if (updateCodecOperatingRate(r7) == false) goto L35;
        this.codecInputFormat = r7;
        if (r22 == false) goto L69;
        if (drainAndUpdateCodecDrmSessionV23() == true) goto L73;
    L69:
        if (drainAndFlushCodec() == true) goto L73;
    L26:
        r22 = false;
    L9:
        if (r1.initializationData.isEmpty() == true) goto L11;
        r1 = r1.buildUpon().setInitializationData(null).build();
        goto L11
    L81:
        throw createRendererException(new IllegalArgumentException("Sample MIME type is null."), r1, 4005);
    }

    public abstract void onOutputFormatChanged(Format r1, MediaFormat r2);

    public void onOutputStreamOffsetUsChanged(long r1) {
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r1, boolean r3) {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.pendingOutputEndOfStream = false;
        if (this.bypassEnabled == false) goto L5;
        resetBypassState();
    L7:
        if (this.outputStreamInfo.formatQueue.size() <= 0) goto L9;
        this.waitingForFirstSampleInFormat = true;
    L9:
        this.outputStreamInfo.formatQueue.clear();
        this.pendingOutputStreamChanges.clear();
        return;
    L5:
        flushOrReinitializeCodec();
        goto L7
    }

    public void onProcessedOutputBuffer(long r3) {
        this.lastProcessedOutputBufferTimeUs = r3;
    L4:
        if (this.pendingOutputStreamChanges.isEmpty() == true) goto L8;
        if (r3 < this.pendingOutputStreamChanges.peek().previousStreamLastBufferTimeUs) goto L11;
        setOutputStreamInfo((OutputStreamInfo) Assertions.checkNotNull(this.pendingOutputStreamChanges.poll()));
        onProcessedStreamChange();
        goto L4
    L11:
        return;
    }

    public void onProcessedStreamChange() {
    }

    public void onQueueInputBuffer(DecoderInputBuffer r1) {
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onReset() {
        disableBypass();     // Catch: Throwable -> L6
        releaseCodec();     // Catch: Throwable -> L6
        setSourceDrmSession(null);
        return;
    L6:
        th = move-exception;
        setSourceDrmSession(null);
        throw th;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] r12, long r13, long r15, MediaSource.MediaPeriodId r17) {
        if (this.outputStreamInfo.streamOffsetUs != (-9223372036854775807L)) goto L9;
        setOutputStreamInfo(new OutputStreamInfo(-9223372036854775807L, r13, r15));
        if (this.experimentalEnableProcessedStreamChangedAtStart == false) goto L22;
        onProcessedStreamChange();
        return;
    L22:
        return;
    L9:
        if (this.pendingOutputStreamChanges.isEmpty() == false) goto L20;
        long r0 = this.largestQueuedPresentationTimeUs;
        if (r0 == (-9223372036854775807L)) goto L16;
        long r4 = this.lastProcessedOutputBufferTimeUs;
        if (r4 == (-9223372036854775807L)) goto L20;
        if (r4 < r0) goto L20;
    L16:
        setOutputStreamInfo(new OutputStreamInfo(-9223372036854775807L, r13, r15));
        if (this.outputStreamInfo.streamOffsetUs == (-9223372036854775807L)) goto L23;
        onProcessedStreamChange();
        return;
    L23:
        return;
    L20:
        this.pendingOutputStreamChanges.add(new OutputStreamInfo(this.largestQueuedPresentationTimeUs, r13, r15));
    }

    public abstract boolean processOutputBuffer(long r1, long r3, MediaCodecAdapter r5, ByteBuffer r6, int r7, int r8, int r9, long r10, boolean r12, boolean r13, Format r14);

    public void releaseCodec() {
        MediaCodecAdapter r1 = this.codec;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.release();     // Catch: Throwable -> L7
        this.decoderCounters.decoderReleaseCount++;
        onCodecReleased(((MediaCodecInfo) Assertions.checkNotNull(this.codecInfo)).name);     // Catch: Throwable -> L7
    L9:
        this.codec = null;
        MediaCrypto r12 = this.mediaCrypto;     // Catch: Throwable -> L14
        if (r12 == null) goto L16;
        r12.release();     // Catch: Throwable -> L14
    L16:
        this.mediaCrypto = null;
        setCodecDrmSession(null);
        resetCodecStateForRelease();
        return;
    L14:
        th = move-exception;
        this.mediaCrypto = null;
        setCodecDrmSession(null);
        resetCodecStateForRelease();
        throw th;
    L7:
        th = move-exception;
        this.codec = null;
        MediaCrypto r2 = this.mediaCrypto;     // Catch: Throwable -> L25
        if (r2 == null) goto L27;
        r2.release();     // Catch: Throwable -> L25
    L27:
        this.mediaCrypto = null;
        setCodecDrmSession(null);
        resetCodecStateForRelease();
        throw th;
    L25:
        th = move-exception;
        this.mediaCrypto = null;
        setCodecDrmSession(null);
        resetCodecStateForRelease();
        throw th;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long r6, long r8) {
        boolean r1 = false;
        if (this.pendingOutputEndOfStream == false) goto L5;
        this.pendingOutputEndOfStream = false;
        processEndOfStream();
    L5:
        ExoPlaybackException r0 = this.pendingPlaybackException;
        if (r0 == null) goto L65;
        this.pendingPlaybackException = null;
        throw r0;
    L65:
    L14:
        e = move-exception;
        throw createRendererException(e, this.inputFormat, Util.getErrorCodeForMediaDrmErrorCode(e.getErrorCode()));
    L12:
        e = move-exception;
        if (isMediaCodecException(e) == false) goto L60;
        onCodecError(e);
        if ((e instanceof MediaCodec.CodecException) == true) goto L50;
    L52:
        if (r1 == false) goto L54;
        releaseCodec();
    L54:
        MediaCodecDecoderException r62 = createDecoderException(e, getCodecInfo());
        if (r62.errorCode != 1101) goto L57;
        int r7 = 4006;
    L59:
        throw createRendererException(r62, this.inputFormat, r1, r7);
    L57:
        r7 = 4003;
        goto L59
    L50:
        if (((MediaCodec.CodecException) e).isRecoverable() == false) goto L52;
        r1 = true;
        goto L52
    L60:
        throw e;
    L9:
        if (this.outputStreamEnded == false) goto L17;
        renderToEndOfStream();     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
        return;
    L17:
        if (this.inputFormat == null) goto L19;
    L21:
        maybeInitCodecOrBypass();     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
        if (this.bypassEnabled == false) goto L29;
        TraceUtil.beginSection("bypassRender");     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
    L25:
        if (bypassRender(r6, r8) == true) goto L25;
        TraceUtil.endSection();     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
    L43:
        this.decoderCounters.ensureUpdated();     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
        return;
    L29:
        if (this.codec == null) goto L42;
        long r2 = getClock().elapsedRealtime();     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
        TraceUtil.beginSection("drainAndFeed");     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
    L32:
        if (drainOutputBuffer(r6, r8) == false) goto L37;
        if (shouldContinueRendering(r2) == true) goto L32;
    L37:
        if (feedInputBuffer() == false) goto L41;
        if (shouldContinueRendering(r2) == true) goto L37;
    L41:
        TraceUtil.endSection();     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
        goto L43
    L42:
        this.decoderCounters.skippedInputBufferCount += skipSource(r6);
        readSourceOmittingSampleData(1);     // Catch: IllegalStateException -> L12 MediaCodec.CryptoException -> L14
        goto L43
    L19:
        if (readSourceOmittingSampleData(2) == true) goto L21;
    }

    public abstract void renderToEndOfStream();

    public void resetCodecStateForFlush() {
        resetInputBuffer();
        resetOutputBuffer();
        resetCommonStateForFlush();
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        this.codecReceivedEos = false;
        this.lastOutputBufferProcessedRealtimeMs = -9223372036854775807L;
        this.codecReceivedBuffers = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.isDecodeOnlyOutputBuffer = false;
        this.isLastOutputBuffer = false;
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
        this.codecReconfigurationState = this.codecReconfigured ? 1 : 0;
        this.skippedFlushAndWaitingForEarlierFrame = false;
        this.skippedFlushLastOutputBufferPresentationTimeUs = -9223372036854775807L;
        this.largestQueuedPresentationTimeOfExpectedOutputBufferUs = -9223372036854775807L;
    }

    public void resetCodecStateForRelease() {
        resetCodecStateForFlush();
        this.pendingPlaybackException = null;
        this.availableCodecInfos = null;
        this.codecInfo = null;
        this.codecInputFormat = null;
        this.codecOutputMediaFormat = null;
        this.codecOutputMediaFormatChanged = false;
        this.codecHasOutputMediaFormat = false;
        this.codecOperatingRate = -1.0f;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsSosFlushWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsEosOutputExceptionWorkaround = false;
        this.codecNeedsEosPropagation = false;
        this.codecRegisteredOnBufferAvailableListener = false;
        this.codecReconfigured = false;
        this.codecReconfigurationState = 0;
    }

    public final void setPendingOutputEndOfStream() {
        this.pendingOutputEndOfStream = true;
    }

    public final void setPendingPlaybackException(ExoPlaybackException r1) {
        this.pendingPlaybackException = r1;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void setPlaybackSpeed(float r1, float r2) {
        this.currentPlaybackSpeed = r1;
        this.targetPlaybackSpeed = r2;
        updateCodecOperatingRate(this.codecInputFormat);
    }

    public boolean shouldDiscardDecoderInputBuffer(DecoderInputBuffer r2) {
        if (shouldSkipDecoderInputBuffer(r2) == false) goto L6;
        r2.clear();
        this.decoderCounters.skippedInputBufferCount++;
        return true;
    L6:
        return false;
    }

    public boolean shouldFlushCodec() {
        return true;
    }

    public boolean shouldInitCodec(MediaCodecInfo r1) {
        return true;
    }

    public boolean shouldReinitCodec() {
        return false;
    }

    public boolean shouldReleaseCodecInsteadOfFlushing() {
        int r0 = this.codecDrainAction;
        if (r0 != 3) goto L5;
    L23:
        return true;
    L5:
        if (this.codecNeedsSosFlushWorkaround == false) goto L9;
        if (this.codecHasOutputMediaFormat == false) goto L23;
    L9:
        if (this.codecNeedsEosFlushWorkaround == false) goto L14;
        if (this.codecReceivedEos == true) goto L23;
    L14:
        if (r0 != 2) goto L21;
        Assertions.checkState(true);
        updateDrmSessionV23();     // Catch: ExoPlaybackException -> L18
        return false;
    L18:
        e = move-exception;
        Log.w("MediaCodecRenderer", "Failed to update the DRM session, releasing the codec instead.", e);
        return true;
    L21:
        return false;
    }

    public boolean shouldSkipDecoderInputBuffer(DecoderInputBuffer r1) {
        return false;
    }

    public boolean shouldUseBypass(Format r1) {
        return false;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final int supportsFormat(Format r3) {
        return supportsFormat(this.mediaCodecSelector, r3);
    L4:
        e = move-exception;
        throw this.createRendererException(e, r3, 4002);
    }

    public abstract int supportsFormat(MediaCodecSelector r1, Format r2);

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.RendererCapabilities
    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    public final boolean updateCodecOperatingRate() {
        return updateCodecOperatingRate(this.codecInputFormat);
    }

    public final void updateOutputFormatForTime(long r2) {
        Format r22 = this.outputStreamInfo.formatQueue.pollFloor(r2);
        if (r22 == null) goto L5;
    L9:
        if (r22 == null) goto L12;
        this.outputFormat = r22;
    L15:
        onOutputFormatChanged((Format) Assertions.checkNotNull(this.outputFormat), this.codecOutputMediaFormat);
        this.codecOutputMediaFormatChanged = false;
        this.needToNotifyOutputFormatChangeAfterStreamChange = false;
        return;
    L12:
        if (this.codecOutputMediaFormatChanged == true) goto L14;
        return;
    L14:
        if (this.outputFormat != null) goto L15;
        return;
    L5:
        if (this.needToNotifyOutputFormatChangeAfterStreamChange == false) goto L9;
        if (this.codecOutputMediaFormat == null) goto L9;
        r22 = this.outputStreamInfo.formatQueue.pollFirst();
        goto L9
    }
}
