package androidx.media3.exoplayer;

import android.os.SystemClock;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* loaded from: classes4.dex */
final class PlaybackInfo {
    private static final MediaSource.MediaPeriodId PLACEHOLDER_MEDIA_PERIOD_ID = null;
    public volatile long bufferedPositionUs;
    public final long discontinuityStartPositionUs;
    public final boolean isLoading;
    public final MediaSource.MediaPeriodId loadingMediaPeriodId;
    public final MediaSource.MediaPeriodId periodId;
    public final boolean playWhenReady;
    public final int playWhenReadyChangeReason;
    public final ExoPlaybackException playbackError;
    public final PlaybackParameters playbackParameters;
    public final int playbackState;
    public final int playbackSuppressionReason;
    public volatile long positionUpdateTimeMs;
    public volatile long positionUs;
    public final long requestedContentPositionUs;
    public final boolean sleepingForOffload;
    public final List<Metadata> staticMetadata;
    public final Timeline timeline;
    public volatile long totalBufferedDurationUs;
    public final TrackGroupArray trackGroups;
    public final TrackSelectorResult trackSelectorResult;

    static {
        PLACEHOLDER_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());
    }

    public PlaybackInfo(Timeline r1, MediaSource.MediaPeriodId r2, long r3, long r5, int r7, ExoPlaybackException r8, boolean r9, TrackGroupArray r10, TrackSelectorResult r11, List<Metadata> r12, MediaSource.MediaPeriodId r13, boolean r14, int r15, int r16, PlaybackParameters r17, long r18, long r20, long r22, long r24, boolean r26) {
        this.timeline = r1;
        this.periodId = r2;
        this.requestedContentPositionUs = r3;
        this.discontinuityStartPositionUs = r5;
        this.playbackState = r7;
        this.playbackError = r8;
        this.isLoading = r9;
        this.trackGroups = r10;
        this.trackSelectorResult = r11;
        this.staticMetadata = r12;
        this.loadingMediaPeriodId = r13;
        this.playWhenReady = r14;
        this.playWhenReadyChangeReason = r15;
        this.playbackSuppressionReason = r16;
        this.playbackParameters = r17;
        this.bufferedPositionUs = r18;
        this.totalBufferedDurationUs = r20;
        this.positionUs = r22;
        this.positionUpdateTimeMs = r24;
        this.sleepingForOffload = r26;
    }

    public static PlaybackInfo createDummy(TrackSelectorResult r27) {
        Timeline r1 = Timeline.EMPTY;
        MediaSource.MediaPeriodId r2 = PLACEHOLDER_MEDIA_PERIOD_ID;
        return new PlaybackInfo(r1, r2, -9223372036854775807L, 0, 1, null, false, TrackGroupArray.EMPTY, r27, ImmutableList.of(), r2, false, 1, 0, PlaybackParameters.DEFAULT, 0, 0, 0, 0, false);
    }

    public static MediaSource.MediaPeriodId getDummyPeriodForEmptyTimeline() {
        return PLACEHOLDER_MEDIA_PERIOD_ID;
    }

    public PlaybackInfo copyWithEstimatedPosition() {
        return new PlaybackInfo(this.timeline, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.staticMetadata, this.loadingMediaPeriodId, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, getEstimatedPositionUs(), SystemClock.elapsedRealtime(), this.sleepingForOffload);
    }

    public PlaybackInfo copyWithIsLoading(boolean r30) {
        return new PlaybackInfo(this.timeline, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, this.playbackState, this.playbackError, r30, this.trackGroups, this.trackSelectorResult, this.staticMetadata, this.loadingMediaPeriodId, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithLoadingMediaPeriodId(MediaSource.MediaPeriodId r30) {
        return new PlaybackInfo(this.timeline, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.staticMetadata, r30, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithNewPosition(MediaSource.MediaPeriodId r28, long r29, long r31, long r33, long r35, TrackGroupArray r37, TrackSelectorResult r38, List<Metadata> r39) {
        return new PlaybackInfo(this.timeline, r28, r31, r33, this.playbackState, this.playbackError, this.isLoading, r37, r38, r39, this.loadingMediaPeriodId, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackParameters, this.bufferedPositionUs, r35, r29, SystemClock.elapsedRealtime(), this.sleepingForOffload);
    }

    public PlaybackInfo copyWithPlayWhenReady(boolean r28, int r29, int r30) {
        return new PlaybackInfo(this.timeline, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.staticMetadata, this.loadingMediaPeriodId, r28, r29, r30, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithPlaybackError(ExoPlaybackException r30) {
        return new PlaybackInfo(this.timeline, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, this.playbackState, r30, this.isLoading, this.trackGroups, this.trackSelectorResult, this.staticMetadata, this.loadingMediaPeriodId, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithPlaybackParameters(PlaybackParameters r31) {
        return new PlaybackInfo(this.timeline, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.staticMetadata, this.loadingMediaPeriodId, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, r31, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithPlaybackState(int r30) {
        return new PlaybackInfo(this.timeline, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, r30, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.staticMetadata, this.loadingMediaPeriodId, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithSleepingForOffload(boolean r32) {
        return new PlaybackInfo(this.timeline, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.staticMetadata, this.loadingMediaPeriodId, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, r32);
    }

    public PlaybackInfo copyWithTimeline(Timeline r30) {
        return new PlaybackInfo(r30, this.periodId, this.requestedContentPositionUs, this.discontinuityStartPositionUs, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.staticMetadata, this.loadingMediaPeriodId, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, this.sleepingForOffload);
    }

    public long getEstimatedPositionUs() {
        if (isPlaying() == false) goto L5;
    L6:
        long r0 = this.positionUpdateTimeMs;
        long r2 = this.positionUs;
        if (r0 != this.positionUpdateTimeMs) goto L6;
        return Util.msToUs(Util.usToMs(r2) + ((long) ((SystemClock.elapsedRealtime() - r0) * this.playbackParameters.speed)));
    L5:
        return this.positionUs;
    }

    public boolean isPlaying() {
        if (this.playbackState == 3) goto L5;
        return false;
    L5:
        if (this.playWhenReady == true) goto L7;
        return false;
    L7:
        if (this.playbackSuppressionReason != 0) goto L13;
        return true;
    L13:
        return false;
    }

    public void updatePositionUs(long r1) {
        this.positionUs = r1;
        this.positionUpdateTimeMs = SystemClock.elapsedRealtime();
    }
}
