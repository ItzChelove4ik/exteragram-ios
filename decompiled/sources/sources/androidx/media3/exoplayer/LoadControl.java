package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;

/* loaded from: classes4.dex */
public interface LoadControl {

    public static final class Parameters {
        public final long bufferedDurationUs;
        public final long lastRebufferRealtimeMs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final boolean playWhenReady;
        public final long playbackPositionUs;
        public final float playbackSpeed;
        public final PlayerId playerId;
        public final boolean rebuffering;
        public final long targetLiveOffsetUs;
        public final Timeline timeline;

        public Parameters(PlayerId r1, Timeline r2, MediaSource.MediaPeriodId r3, long r4, long r6, float r8, boolean r9, boolean r10, long r11, long r13) {
            this.playerId = r1;
            this.timeline = r2;
            this.mediaPeriodId = r3;
            this.playbackPositionUs = r4;
            this.bufferedDurationUs = r6;
            this.playbackSpeed = r8;
            this.playWhenReady = r9;
            this.rebuffering = r10;
            this.targetLiveOffsetUs = r11;
            this.lastRebufferRealtimeMs = r13;
        }
    }

    Allocator getAllocator();

    long getBackBufferDurationUs(PlayerId r1);

    void onPrepared(PlayerId r1);

    void onReleased(PlayerId r1);

    void onStopped(PlayerId r1);

    void onTracksSelected(Parameters r1, TrackGroupArray r2, ExoTrackSelection[] r3);

    boolean retainBackBufferFromKeyframe(PlayerId r1);

    boolean shouldContinueLoading(Parameters r1);

    boolean shouldContinuePreloading(Timeline r1, MediaSource.MediaPeriodId r2, long r3);

    boolean shouldStartPlayback(Parameters r1);
}
