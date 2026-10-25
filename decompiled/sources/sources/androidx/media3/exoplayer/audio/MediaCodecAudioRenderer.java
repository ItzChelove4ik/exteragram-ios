package androidx.media3.exoplayer.audio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.mediacodec.LoudnessCodecController;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.extractor.VorbisUtil;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public class MediaCodecAudioRenderer extends MediaCodecRenderer implements MediaClock {
    private boolean allowPositionDiscontinuity;
    private final AudioSink audioSink;
    private boolean audioSinkNeedsReset;
    private int codecMaxInputSize;
    private boolean codecNeedsDiscardChannelsWorkaround;
    private boolean codecNeedsVorbisToAndroidChannelMappingWorkaround;
    private final Context context;
    private long currentPositionUs;
    private Format decryptOnlyCodecFormat;
    private final AudioRendererEventListener.EventDispatcher eventDispatcher;
    private boolean hasPendingReportedSkippedSilence;
    private Format inputFormat;
    private boolean isStarted;
    private final LoudnessCodecController loudnessCodecController;
    private long nextBufferToWritePresentationTimeUs;
    private int rendererPriority;

    /* renamed from: androidx.media3.exoplayer.audio.MediaCodecAudioRenderer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api23 {
        public static void setAudioSinkPreferredDevice(AudioSink r0, Object r1) {
            r0.setPreferredDevice((AudioDeviceInfo) r1);
        }
    }

    public final class AudioSinkListener implements AudioSink.Listener {
        final /* synthetic */ MediaCodecAudioRenderer this$0;

        private AudioSinkListener(MediaCodecAudioRenderer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ AudioSinkListener(MediaCodecAudioRenderer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioCapabilitiesChanged() {
            MediaCodecAudioRenderer.access$500(this.this$0);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioSessionIdChanged(int r3) {
            if (Build.VERSION.SDK_INT >= 35) goto L5;
        L7:
            MediaCodecAudioRenderer.access$200(this.this$0).audioSessionIdChanged(r3);
            return;
        L5:
            if (MediaCodecAudioRenderer.access$600(this.this$0) == null) goto L7;
            MediaCodecAudioRenderer.access$600(this.this$0).setAudioSessionId(r3);
            goto L7
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioSinkError(Exception r3) {
            Log.e("MediaCodecAudioRenderer", "Audio sink error", r3);
            MediaCodecAudioRenderer.access$200(this.this$0).audioSinkError(r3);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioTrackInitialized(AudioSink.AudioTrackConfig r1) {
            MediaCodecAudioRenderer.access$200(this.this$0).audioTrackInitialized(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioTrackReleased(AudioSink.AudioTrackConfig r1) {
            MediaCodecAudioRenderer.access$200(this.this$0).audioTrackReleased(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onOffloadBufferEmptying() {
            Renderer.WakeupListener r0 = MediaCodecAudioRenderer.access$300(this.this$0);
            if (r0 == null) goto L6;
            r0.onWakeup();
            return;
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onOffloadBufferFull() {
            Renderer.WakeupListener r0 = MediaCodecAudioRenderer.access$400(this.this$0);
            if (r0 == null) goto L6;
            r0.onSleep();
            return;
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onPositionAdvancing(long r1) {
            MediaCodecAudioRenderer.access$200(this.this$0).positionAdvancing(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onPositionDiscontinuity() {
            this.this$0.onPositionDiscontinuity();
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onSilenceSkipped() {
            MediaCodecAudioRenderer.access$102(this.this$0, true);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onSkipSilenceEnabledChanged(boolean r1) {
            MediaCodecAudioRenderer.access$200(this.this$0).skipSilenceEnabledChanged(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onUnderrun(int r7, long r8, long r10) {
            MediaCodecAudioRenderer.access$200(this.this$0).underrun(r7, r8, r10);
        }
    }

    public MediaCodecAudioRenderer(Context r11, MediaCodecAdapter.Factory r12, MediaCodecSelector r13, boolean r14, Handler r15, AudioRendererEventListener r16, AudioSink r17) {
        if (Build.VERSION.SDK_INT < 35) goto L6;
        LoudnessCodecController r0 = new LoudnessCodecController();
    L7:
        this(r11, r12, r13, r14, r15, r16, r17, r0);
        return;
    L6:
        r0 = null;
        goto L7
    }

    public MediaCodecAudioRenderer(Context r7, MediaCodecAdapter.Factory r8, MediaCodecSelector r9, boolean r10, Handler r11, AudioRendererEventListener r12, AudioSink r13, LoudnessCodecController r14) {
        super(1, r8, r9, r10, 44100.0f);
        this.context = r7.getApplicationContext();
        this.audioSink = r13;
        this.loudnessCodecController = r14;
        this.rendererPriority = -1000;
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(r11, r12);
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
        r13.setListener(new AudioSinkListener(this, null));
    }

    public static /* synthetic */ boolean access$102(MediaCodecAudioRenderer r0, boolean r1) {
        r0.hasPendingReportedSkippedSilence = r1;
        return r1;
    }

    public static /* synthetic */ AudioRendererEventListener.EventDispatcher access$200(MediaCodecAudioRenderer r0) {
        return r0.eventDispatcher;
    }

    public static /* synthetic */ Renderer.WakeupListener access$300(MediaCodecAudioRenderer r0) {
        return r0.getWakeupListener();
    }

    public static /* synthetic */ Renderer.WakeupListener access$400(MediaCodecAudioRenderer r0) {
        return r0.getWakeupListener();
    }

    public static /* synthetic */ void access$500(MediaCodecAudioRenderer r0) {
        r0.onRendererCapabilitiesChanged();
    }

    public static /* synthetic */ LoudnessCodecController access$600(MediaCodecAudioRenderer r0) {
        return r0.loudnessCodecController;
    }

    private static boolean codecNeedsDiscardChannelsWorkaround(String r0) {
        return false;
    }

    private static boolean codecNeedsVorbisToAndroidChannelMappingWorkaround(String r1) {
        if (r1.equals("OMX.google.opus.decoder") == false) goto L5;
        return true;
    L5:
        if (r1.equals("c2.android.opus.decoder") == false) goto L7;
        return true;
    L7:
        if (r1.equals("OMX.google.vorbis.decoder") == false) goto L9;
        return true;
    L9:
        if (r1.equals("c2.android.vorbis.decoder") == true) goto L17;
        return false;
    L17:
        return true;
    }

    private static boolean deviceDoesntSupportOperatingRate() {
        return false;
    }

    private int getAudioOffloadSupport(Format r1) {
        AudioOffloadSupport r0 = this.audioSink.getFormatOffloadSupport(r1);
        if (r0.isFormatSupported == true) goto L7;
        return 0;
    L7:
        if (r0.isGaplessSupported == false) goto L9;
        int r12 = 1536;
    L11:
        if (r0.isSpeedChangeSupported == true) goto L13;
        return r12;
    L13:
        return r12 | 2048;
    L9:
        r12 = 512;
        goto L11
    }

    private int getCodecMaxInputSize(MediaCodecInfo r1, Format r2) {
        "OMX.google.raw.decoder".equals(r1.name);
        return r2.maxInputSize;
    }

    private static List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector r1, Format r2, boolean r3, AudioSink r4) {
        if (r2.sampleMimeType != null) goto L7;
        return ImmutableList.of();
    L7:
        if (r4.supportsFormat(r2) == false) goto L13;
        MediaCodecInfo r42 = MediaCodecUtil.getDecryptOnlyDecoderInfo();
        if (r42 == null) goto L13;
        return ImmutableList.of(r42);
    L13:
        return MediaCodecUtil.getDecoderInfosSoftMatch(r1, r2, r3, false);
    }

    private void setAudioSessionId(int r3) {
        this.audioSink.setAudioSessionId(r3);
        if (Build.VERSION.SDK_INT < 35) goto L8;
        LoudnessCodecController r2 = this.loudnessCodecController;
        if (r2 == null) goto L9;
        r2.setAudioSessionId(r3);
        return;
    L9:
        return;
    }

    private void updateCodecImportance() {
        MediaCodecAdapter r0 = getCodec();
        if (r0 != null) goto L6;
        return;
    L6:
        if (Build.VERSION.SDK_INT < 35) goto L9;
        Bundle r1 = new Bundle();
        r1.putInt("importance", Math.max(0, -this.rendererPriority));
        r0.setParameters(r1);
        return;
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

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo r9, Format r10, Format r11) {
        DecoderReuseEvaluation r0 = r9.canReuseCodec(r10, r11);
        int r1 = r0.discardReasons;
        if (isBypassPossible(r11) == false) goto L6;
        r1 = r1 | 32768;
    L6:
        if (getCodecMaxInputSize(r9, r11) <= this.codecMaxInputSize) goto L8;
        r1 = r1 | 64;
    L8:
        int r7 = r1;
        String r3 = r9.name;
        if (r7 == 0) goto L12;
        int r8 = 0;
    L14:
        return new DecoderReuseEvaluation(r3, r10, r11, r8, r7);
    L12:
        r8 = r0.result;
        goto L14
    }

    public int getCodecMaxInputSize(MediaCodecInfo r6, Format r7, Format[] r8) {
        int r0 = getCodecMaxInputSize(r6, r7);
        if (r8.length != 1) goto L5;
        return r0;
    L5:
        int r1 = r8.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        Format r3 = r8[r2];
        if (r6.canReuseCodec(r7, r3).result == 0) goto L10;
        r0 = Math.max(r0, getCodecMaxInputSize(r6, r3));
    L10:
        r2 = r2 + 1;
        goto L6
    L11:
        return r0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public float getCodecOperatingRateV23(float r4, Format r5, Format[] r6) {
        int r3 = r6.length;
        int r0 = 0;
        int r1 = -1;
    L3:
        if (r0 >= r3) goto L8;
        int r2 = r6[r0].sampleRate;
        if (r2 == (-1)) goto L7;
        r1 = Math.max(r1, r2);
    L7:
        r0 = r0 + 1;
        goto L3
    L8:
        if (r1 != (-1)) goto L12;
        return -1.0f;
    L12:
        return r1 * r4;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector r1, Format r2, boolean r3) {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(r1, r2, r3, this.audioSink), r2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public long getDurationToProgressUs(long r7, long r9, boolean r11) {
        if (this.nextBufferToWritePresentationTimeUs == (-9223372036854775807L)) goto L5;
        boolean r112 = true;
    L7:
        if (this.isStarted == true) goto L15;
        if (r112 == false) goto L10;
        return 1000000;
    L10:
        if (super.isEnded() == true) goto L27;
        return NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L27:
        return 1000000;
    L15:
        long r0 = this.audioSink.getAudioTrackBufferSizeUs();
        if (r112 == true) goto L18;
    L26:
        return NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L18:
        if (r0 == (-9223372036854775807L)) goto L26;
        float r72 = Math.min(r0, this.nextBufferToWritePresentationTimeUs - r7);
        if (getPlaybackParameters() == null) goto L23;
        float r8 = getPlaybackParameters().speed;
    L25:
        return Math.max(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, ((long) ((r72 / r8) / 2.0f)) - (Util.msToUs(getClock().elapsedRealtime()) - r9));
    L23:
        r8 = 1.0f;
        goto L25
    L5:
        r112 = false;
        goto L7
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    public MediaClock getMediaClock() {
        return this;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo r3, Format r4, MediaCrypto r5, float r6) {
        this.codecMaxInputSize = getCodecMaxInputSize(r3, r4, getStreamFormats());
        this.codecNeedsDiscardChannelsWorkaround = codecNeedsDiscardChannelsWorkaround(r3.name);
        this.codecNeedsVorbisToAndroidChannelMappingWorkaround = codecNeedsVorbisToAndroidChannelMappingWorkaround(r3.name);
        MediaFormat r62 = getMediaFormat(r4, r3.codecMimeType, this.codecMaxInputSize, r6);
        if ("audio/raw".equals(r3.mimeType) == true) goto L5;
    L7:
        Format r0 = null;
    L8:
        this.decryptOnlyCodecFormat = r0;
        return MediaCodecAdapter.Configuration.createForAudioDecoding(r3, r62, r4, r5, this.loudnessCodecController);
    L5:
        if ("audio/raw".equals(r4.sampleMimeType) == true) goto L7;
        r0 = r4;
        goto L8
    }

    @SuppressLint({"InlinedApi"})
    public MediaFormat getMediaFormat(Format r3, String r4, int r5, float r6) {
        MediaFormat r0 = new MediaFormat();
        r0.setString("mime", r4);
        r0.setInteger("channel-count", r3.channelCount);
        r0.setInteger("sample-rate", r3.sampleRate);
        MediaFormatUtil.setCsdBuffers(r0, r3.initializationData);
        MediaFormatUtil.maybeSetInteger(r0, "max-input-size", r5);
        r0.setInteger(PluginsConstants.MenuItemProperties.PRIORITY, 0);
        if (r6 == (-1.0f)) goto L8;
        if (deviceDoesntSupportOperatingRate() == true) goto L8;
        r0.setFloat("operating-rate", r6);
    L8:
        if ("audio/ac4".equals(r3.sampleMimeType) == false) goto L16;
        Pair<Integer, Integer> r42 = CodecSpecificDataUtil.getCodecProfileAndLevel(r3);
        if (r42 == null) goto L13;
        MediaFormatUtil.maybeSetInteger(r0, "profile", ((Integer) r42.first).intValue());
        MediaFormatUtil.maybeSetInteger(r0, "level", ((Integer) r42.second).intValue());
    L13:
        if (Build.VERSION.SDK_INT > 28) goto L16;
        r0.setInteger("ac4-is-sync", 1);
    L16:
        if (this.audioSink.getFormatSupport(Util.getPcmFormat(4, r3.channelCount, r3.sampleRate)) != 2) goto L18;
        r0.setInteger("pcm-encoding", 4);
    L18:
        int r32 = Build.VERSION.SDK_INT;
        if (r32 < 32) goto L22;
        r0.setInteger("max-output-channel-count", 99);
    L22:
        if (r32 < 35) goto L24;
        r0.setInteger("importance", Math.max(0, -this.rendererPriority));
    L24:
        return r0;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "MediaCodecAudioRenderer";
    }

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

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void handleInputBufferSupplementalData(DecoderInputBuffer r5) {
        if (Build.VERSION.SDK_INT < 29) goto L14;
        Format r0 = r5.format;
        if (r0 != null) goto L7;
        return;
    L7:
        if (Objects.equals(r0.sampleMimeType, "audio/opus") == true) goto L9;
        return;
    L9:
        if (isBypassEnabled() == false) goto L17;
        ByteBuffer r02 = (ByteBuffer) Assertions.checkNotNull(r5.supplementalData);
        int r52 = ((Format) Assertions.checkNotNull(r5.format)).encoderDelay;
        if (r02.remaining() != 8) goto L18;
        int r03 = (int) ((r02.order(ByteOrder.LITTLE_ENDIAN).getLong() * 48000) / 1000000000);
        this.audioSink.setOffloadDelayPadding(r52, r03);
        return;
    L18:
        return;
    L17:
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int r2, Object r3) {
        if (r2 != 2) goto L5;
        this.audioSink.setVolume(((Float) Assertions.checkNotNull(r3)).floatValue());
        return;
    L5:
        if (r2 != 3) goto L7;
        this.audioSink.setAudioAttributes((AudioAttributes) Assertions.checkNotNull((AudioAttributes) r3));
        return;
    L7:
        if (r2 != 6) goto L9;
        this.audioSink.setAuxEffectInfo((AuxEffectInfo) Assertions.checkNotNull((AuxEffectInfo) r3));
        return;
    L9:
        if (r2 != 12) goto L11;
        Api23.setAudioSinkPreferredDevice(this.audioSink, r3);
        return;
    L11:
        if (r2 != 16) goto L13;
        this.rendererPriority = ((Integer) Assertions.checkNotNull(r3)).intValue();
        updateCodecImportance();
        return;
    L13:
        if (r2 != 9) goto L15;
        this.audioSink.setSkipSilenceEnabled(((Boolean) Assertions.checkNotNull(r3)).booleanValue());
        return;
    L15:
        if (r2 == 10) goto L18;
        super.handleMessage(r2, r3);
        return;
    L18:
        setAudioSessionId(((Integer) Assertions.checkNotNull(r3)).intValue());
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public boolean hasSkippedSilenceSinceLastCall() {
        boolean r0 = this.hasPendingReportedSkippedSilence;
        this.hasPendingReportedSkippedSilence = false;
        return r0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        if (super.isEnded() == true) goto L5;
        return false;
    L5:
        if (this.audioSink.isEnded() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        if (this.audioSink.hasPendingData() == false) goto L5;
        return true;
    L5:
        if (super.isReady() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecError(Exception r3) {
        Log.e("MediaCodecAudioRenderer", "Audio codec error", r3);
        this.eventDispatcher.audioCodecError(r3);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String r1, MediaCodecAdapter.Configuration r2, long r3, long r5) {
        this.eventDispatcher.decoderInitialized(r1, r3, r5);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecReleased(String r1) {
        this.eventDispatcher.decoderReleased(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.audioSinkNeedsReset = true;
        this.inputFormat = null;
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
        this.audioSink.flush();     // Catch: Throwable -> L10
        super.onDisabled();     // Catch: Throwable -> L7
        this.eventDispatcher.disabled(this.decoderCounters);
        return;
    L7:
        th = move-exception;
        this.eventDispatcher.disabled(this.decoderCounters);
        throw th;
    L10:
        th = move-exception;
        super.onDisabled();     // Catch: Throwable -> L14
        this.eventDispatcher.disabled(this.decoderCounters);
        throw th;
    L14:
        th = move-exception;
        this.eventDispatcher.disabled(this.decoderCounters);
        throw th;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean r1, boolean r2) {
        super.onEnabled(r1, r2);
        this.eventDispatcher.enabled(this.decoderCounters);
        boolean r12 = getConfiguration().tunneling;
        AudioSink r22 = this.audioSink;
        if (r12 == false) goto L5;
        r22.enableTunnelingV21();
    L6:
        this.audioSink.setPlayerId(getPlayerId());
        this.audioSink.setClock(getClock());
        return;
    L5:
        r22.disableTunneling();
        goto L6
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder r2) {
        Format r0 = (Format) Assertions.checkNotNull(r2.format);
        this.inputFormat = r0;
        DecoderReuseEvaluation r22 = super.onInputFormatChanged(r2);
        this.eventDispatcher.inputFormatChanged(r0, r22);
        return r22;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onOutputFormatChanged(Format r6, MediaFormat r7) {
        Format r0 = this.decryptOnlyCodecFormat;
        int[] r2 = null;
        if (r0 == null) goto L6;
        r6 = r0;
    L46:
    L39:
        e = move-exception;
        throw createRendererException(e, e.format, 5001);
    L33:
        if (Build.VERSION.SDK_INT >= 29) goto L35;
    L42:
        this.audioSink.configure(r6, 0, r2);     // Catch: AudioSink.ConfigurationException -> L39
        return;
    L35:
        if (isBypassEnabled() == true) goto L37;
    L41:
        this.audioSink.setOffloadMode(0);     // Catch: AudioSink.ConfigurationException -> L39
        goto L42
    L37:
        if (getConfiguration().offloadModePreferred == 0) goto L41;
        this.audioSink.setOffloadMode(getConfiguration().offloadModePreferred);     // Catch: AudioSink.ConfigurationException -> L39
        goto L42
    L6:
        if (getCodec() == null) goto L46;
        Assertions.checkNotNull(r7);
        if ("audio/raw".equals(r6.sampleMimeType) == false) goto L12;
        int r02 = r6.pcmEncoding;
    L18:
        Format r72 = new Format.Builder().setSampleMimeType("audio/raw").setPcmEncoding(r02).setEncoderDelay(r6.encoderDelay).setEncoderPadding(r6.encoderPadding).setMetadata(r6.metadata).setCustomData(r6.customData).setId(r6.id).setLabel(r6.label).setLabels(r6.labels).setLanguage(r6.language).setSelectionFlags(r6.selectionFlags).setRoleFlags(r6.roleFlags).setChannelCount(r7.getInteger("channel-count")).setSampleRate(r7.getInteger("sample-rate")).build();
        if (this.codecNeedsDiscardChannelsWorkaround == false) goto L30;
        if (r72.channelCount != 6) goto L30;
        int r03 = r6.channelCount;
        if (r03 >= 6) goto L30;
        r2 = new int[r03];
        int r04 = 0;
    L26:
        if (r04 >= r6.channelCount) goto L28;
        r2[r04] = r04;
        r04 = r04 + 1;
    L28:
        r6 = r72;
    L30:
        if (this.codecNeedsVorbisToAndroidChannelMappingWorkaround == false) goto L28;
        r2 = VorbisUtil.getVorbisToAndroidChannelLayoutMapping(r72.channelCount);
        goto L28
    L12:
        if (r7.containsKey("pcm-encoding") == false) goto L15;
        r02 = r7.getInteger("pcm-encoding");
        goto L18
    L15:
        if (r7.containsKey("v-bits-per-sample") == false) goto L17;
        r02 = Util.getPcmEncoding(r7.getInteger("v-bits-per-sample"));
        goto L18
    L17:
        r02 = 2;
        goto L18
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onOutputStreamOffsetUsChanged(long r1) {
        this.audioSink.setOutputStreamOffsetUs(r1);
    }

    public void onPositionDiscontinuity() {
        this.allowPositionDiscontinuity = true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r1, boolean r3) {
        super.onPositionReset(r1, r3);
        this.audioSink.flush();
        this.currentPositionUs = r1;
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
        this.hasPendingReportedSkippedSilence = false;
        this.allowPositionDiscontinuity = true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onProcessedStreamChange() {
        super.onProcessedStreamChange();
        this.audioSink.handleDiscontinuity();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        this.audioSink.release();
        if (Build.VERSION.SDK_INT < 35) goto L8;
        LoudnessCodecController r2 = this.loudnessCodecController;
        if (r2 == null) goto L9;
        r2.release();
        return;
    L9:
        return;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onReset() {
        this.hasPendingReportedSkippedSilence = false;
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
        super.onReset();     // Catch: Throwable -> L8
        if (this.audioSinkNeedsReset == false) goto L15;
        this.audioSinkNeedsReset = false;
        this.audioSink.reset();
        return;
    L15:
        return;
    L8:
        th = move-exception;
        if (this.audioSinkNeedsReset == false) goto L12;
        this.audioSinkNeedsReset = false;
        this.audioSink.reset();
    L12:
        throw th;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
        super.onStarted();
        this.audioSink.play();
        this.isStarted = true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
        updateCurrentPosition();
        this.isStarted = false;
        this.audioSink.pause();
        super.onStopped();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean processOutputBuffer(long r1, long r3, MediaCodecAdapter r5, ByteBuffer r6, int r7, int r8, int r9, long r10, boolean r12, boolean r13, Format r14) {
        Assertions.checkNotNull(r6);
        this.nextBufferToWritePresentationTimeUs = -9223372036854775807L;
        if (this.decryptOnlyCodecFormat != null) goto L5;
    L8:
        if (r12 == false) goto L41;
        if (r5 == null) goto L11;
        r5.releaseOutputBuffer(r7, false);
    L11:
        this.decoderCounters.skippedOutputBufferCount += r9;
        this.audioSink.handleDiscontinuity();
        return true;
    L41:
        if (this.audioSink.handleBuffer(r6, r10, r9) == false) goto L19;
        if (r5 == null) goto L17;
        r5.releaseOutputBuffer(r7, false);
    L17:
        this.decoderCounters.renderedOutputBufferCount += r9;
        return true;
    L19:
        this.nextBufferToWritePresentationTimeUs = r10;
        return false;
    L23:
        e = move-exception;
        Format r2 = this.inputFormat;
        boolean r32 = e.isRecoverable;
        if (isBypassEnabled() == true) goto L36;
    L38:
        int r4 = 5001;
    L40:
        throw createRendererException(e, r2, r32, r4);
    L36:
        if (getConfiguration().offloadModePreferred == 0) goto L38;
        r4 = 5004;
    L21:
        e = move-exception;
        boolean r22 = e.isRecoverable;
        if (isBypassEnabled() == true) goto L28;
    L30:
        int r33 = 5002;
    L32:
        throw createRendererException(e, r14, r22, r33);
    L28:
        if (getConfiguration().offloadModePreferred == 0) goto L30;
        r33 = 5003;
        goto L32
    L5:
        if ((r8 & 2) == 0) goto L8;
        ((MediaCodecAdapter) Assertions.checkNotNull(r5)).releaseOutputBuffer(r7, false);
        return true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void renderToEndOfStream() {
        this.audioSink.playToEndOfStream();     // Catch: AudioSink.WriteException -> L6
        if (getLastBufferInStreamPresentationTimeUs() == (-9223372036854775807L)) goto L8;
        this.nextBufferToWritePresentationTimeUs = getLastBufferInStreamPresentationTimeUs();     // Catch: AudioSink.WriteException -> L6
        return;
    L8:
        return;
    L6:
        e = move-exception;
        Format r1 = e.format;
        boolean r2 = e.isRecoverable;
        if (isBypassEnabled() == false) goto L12;
        int r3 = 5003;
    L14:
        throw createRendererException(e, r1, r2, r3);
    L12:
        r3 = 5002;
        goto L14
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public void setPlaybackParameters(PlaybackParameters r1) {
        this.audioSink.setPlaybackParameters(r1);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean shouldUseBypass(Format r4) {
        if (getConfiguration().offloadModePreferred == 0) goto L17;
        int r0 = getAudioOffloadSupport(r4);
        if ((r0 & 512) == 0) goto L17;
        if (getConfiguration().offloadModePreferred != 2) goto L9;
        return true;
    L9:
        if ((r0 & 1024) == 0) goto L11;
        return true;
    L11:
        if (r4.encoderDelay != 0) goto L17;
        if (r4.encoderPadding != 0) goto L17;
        return true;
    L17:
        return this.audioSink.supportsFormat(r4);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public int supportsFormat(MediaCodecSelector r12, Format r13) {
        int r1 = 0;
        if (MimeTypes.isAudio(r13.sampleMimeType) == false) goto L5;
        boolean r2 = true;
        if (r13.cryptoType == 0) goto L9;
        boolean r0 = true;
    L10:
        boolean r3 = MediaCodecRenderer.supportsFormatDrm(r13);
        int r4 = 8;
        int r5 = 4;
        if (r3 == false) goto L19;
        if (r0 == true) goto L14;
    L15:
        int r02 = getAudioOffloadSupport(r13);
        if (this.audioSink.supportsFormat(r13) == false) goto L21;
        return RendererCapabilities.create(4, 8, 32, r02);
    L21:
        if ("audio/raw".equals(r13.sampleMimeType) == false) goto L27;
        if (this.audioSink.supportsFormat(r13) == true) goto L27;
        return RendererCapabilities.create(1);
    L27:
        if (this.audioSink.supportsFormat(Util.getPcmFormat(2, r13.channelCount, r13.sampleRate)) == false) goto L29;
        List<MediaCodecInfo> r11 = getDecoderInfos(r12, r13, false, this.audioSink);
        if (r11.isEmpty() == true) goto L33;
        if (r3 == false) goto L36;
        MediaCodecInfo r122 = r11.get(0);
        boolean r32 = r122.isFormatSupported(r13);
        if (r32 == true) goto L46;
        int r6 = 1;
    L41:
        if (r6 >= r11.size()) goto L46;
        MediaCodecInfo r7 = r11.get(r6);
        if (r7.isFormatSupported(r13) == true) goto L44;
        r6 = r6 + 1;
        goto L41
    L44:
        boolean r112 = false;
        r122 = r7;
    L47:
        if (r2 == true) goto L50;
        r5 = 3;
    L50:
        if (r2 == false) goto L55;
        if (r122.isSeamlessAdaptationSupported(r13) == false) goto L55;
        r4 = 16;
    L55:
        if (r122.hardwareAccelerated == false) goto L57;
        int r33 = 64;
    L58:
        if (r112 == false) goto L61;
        r1 = 128;
    L61:
        return RendererCapabilities.create(r5, r4, 32, r33, r1, r02);
    L57:
        r33 = 0;
    L46:
        r112 = true;
        r2 = r32;
        goto L47
    L36:
        return RendererCapabilities.create(2);
    L33:
        return RendererCapabilities.create(1);
    L29:
        return RendererCapabilities.create(1);
    L14:
        if (MediaCodecUtil.getDecryptOnlyDecoderInfo() != null) goto L15;
    L19:
        r02 = 0;
        goto L21
    L9:
        r0 = false;
        goto L10
    L5:
        return RendererCapabilities.create(0);
    }
}
