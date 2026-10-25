package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.analytics.PlayerId;
import java.nio.ByteBuffer;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public interface AudioSink {

    public static final class AudioTrackConfig {
        public final int bufferSize;
        public final int channelConfig;
        public final int encoding;
        public final boolean offload;
        public final int sampleRate;
        public final boolean tunneling;

        public AudioTrackConfig(int r1, int r2, int r3, boolean r4, boolean r5, int r6) {
            this.encoding = r1;
            this.sampleRate = r2;
            this.channelConfig = r3;
            this.tunneling = r4;
            this.offload = r5;
            this.bufferSize = r6;
        }
    }

    public static final class ConfigurationException extends Exception {
        public final Format format;

        public ConfigurationException(String r1, Format r2) {
            super(r1);
            this.format = r2;
        }

        public ConfigurationException(Throwable r1, Format r2) {
            super(r1);
            this.format = r2;
        }
    }

    public static final class InitializationException extends Exception {
        public final int audioTrackState;
        public final Format format;
        public final boolean isRecoverable;

        public InitializationException(int r3, int r4, int r5, int r6, int r7, Format r8, boolean r9, Exception r10) {
            StringBuilder r0 = new StringBuilder("AudioTrack init failed ");
            r0.append(r3);
            r0.append(" Config(");
            r0.append(r4);
            r0.append(", ");
            r0.append(r5);
            r0.append(", ");
            r0.append(r6);
            r0.append(", ");
            r0.append(r7);
            r0.append(") ");
            r0.append(r8);
            if (r9 == false) goto L5;
            String r42 = " (recoverable)";
        L6:
            r0.append(r42);
            this(r0.toString(), r3, r8, r9, r10);
            return;
        L5:
            r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }

        public InitializationException(String r1, int r2, Format r3, boolean r4, Throwable r5) {
            super(r1, r5);
            this.audioTrackState = r2;
            this.isRecoverable = r4;
            this.format = r3;
        }
    }

    public interface Listener {
        void onAudioCapabilitiesChanged();

        void onAudioSessionIdChanged(int r1);

        void onAudioSinkError(Exception r1);

        void onAudioTrackInitialized(AudioTrackConfig r1);

        void onAudioTrackReleased(AudioTrackConfig r1);

        default void onOffloadBufferEmptying() {
        }

        default void onOffloadBufferFull() {
        }

        void onPositionAdvancing(long r1);

        void onPositionDiscontinuity();

        void onSilenceSkipped();

        void onSkipSilenceEnabledChanged(boolean r1);

        void onUnderrun(int r1, long r2, long r4);
    }

    public static final class UnexpectedDiscontinuityException extends Exception {
        public final long actualPresentationTimeUs;
        public final long expectedPresentationTimeUs;

        public UnexpectedDiscontinuityException(long r3, long r5) {
            super("Unexpected audio track timestamp discontinuity: expected " + r5 + ", got " + r3);
            this.actualPresentationTimeUs = r3;
            this.expectedPresentationTimeUs = r5;
        }
    }

    public static final class WriteException extends Exception {
        public final int errorCode;
        public final Format format;
        public final boolean isRecoverable;

        public WriteException(int r3, Format r4, boolean r5) {
            super("AudioTrack write failed: " + r3);
            this.isRecoverable = r5;
            this.errorCode = r3;
            this.format = r4;
        }
    }

    void configure(Format r1, int r2, int[] r3);

    void disableTunneling();

    void enableTunnelingV21();

    void flush();

    long getAudioTrackBufferSizeUs();

    long getCurrentPositionUs(boolean r1);

    AudioOffloadSupport getFormatOffloadSupport(Format r1);

    int getFormatSupport(Format r1);

    PlaybackParameters getPlaybackParameters();

    boolean handleBuffer(ByteBuffer r1, long r2, int r4);

    void handleDiscontinuity();

    boolean hasPendingData();

    boolean isEnded();

    void pause();

    void play();

    void playToEndOfStream();

    void release();

    void reset();

    void setAudioAttributes(AudioAttributes r1);

    void setAudioSessionId(int r1);

    void setAuxEffectInfo(AuxEffectInfo r1);

    void setClock(Clock r1);

    void setListener(Listener r1);

    void setOffloadDelayPadding(int r1, int r2);

    void setOffloadMode(int r1);

    default void setOutputStreamOffsetUs(long r1) {
    }

    void setPlaybackParameters(PlaybackParameters r1);

    void setPlayerId(PlayerId r1);

    void setPreferredDevice(AudioDeviceInfo r1);

    void setSkipSilenceEnabled(boolean r1);

    void setVolume(float r1);

    boolean supportsFormat(Format r1);
}
