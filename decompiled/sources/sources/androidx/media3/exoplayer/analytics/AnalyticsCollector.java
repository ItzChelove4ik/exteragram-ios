package androidx.media3.exoplayer.analytics;

import android.os.Looper;
import androidx.media3.common.Format;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import java.util.List;

/* loaded from: classes4.dex */
public interface AnalyticsCollector extends Player.Listener, MediaSourceEventListener, BandwidthMeter.EventListener, DrmSessionEventListener {
    void addListener(AnalyticsListener r1);

    void notifySeekStarted();

    void onAudioCodecError(Exception r1);

    void onAudioDecoderInitialized(String r1, long r2, long r4);

    void onAudioDecoderReleased(String r1);

    void onAudioDisabled(DecoderCounters r1);

    void onAudioEnabled(DecoderCounters r1);

    void onAudioInputFormatChanged(Format r1, DecoderReuseEvaluation r2);

    void onAudioPositionAdvancing(long r1);

    void onAudioSinkError(Exception r1);

    void onAudioTrackInitialized(AudioSink.AudioTrackConfig r1);

    void onAudioTrackReleased(AudioSink.AudioTrackConfig r1);

    void onAudioUnderrun(int r1, long r2, long r4);

    void onDroppedFrames(int r1, long r2);

    void onRenderedFirstFrame(Object r1, long r2);

    void onRendererReadyChanged(int r1, int r2, boolean r3);

    void onVideoCodecError(Exception r1);

    void onVideoDecoderInitialized(String r1, long r2, long r4);

    void onVideoDecoderReleased(String r1);

    void onVideoDisabled(DecoderCounters r1);

    void onVideoEnabled(DecoderCounters r1);

    void onVideoFrameProcessingOffset(long r1, int r3);

    void onVideoInputFormatChanged(Format r1, DecoderReuseEvaluation r2);

    void release();

    void removeListener(AnalyticsListener r1);

    void setPlayer(Player r1, Looper r2);

    void updateMediaPeriodQueueInfo(List<MediaSource.MediaPeriodId> r1, MediaSource.MediaPeriodId r2);
}
