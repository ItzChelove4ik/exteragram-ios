package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.MediaSource;

/* loaded from: classes4.dex */
public interface PlaybackSessionManager {

    public interface Listener {
        void onAdPlaybackStarted(AnalyticsListener.EventTime r1, String r2, String r3);

        void onSessionActive(AnalyticsListener.EventTime r1, String r2);

        void onSessionCreated(AnalyticsListener.EventTime r1, String r2);

        void onSessionFinished(AnalyticsListener.EventTime r1, String r2, boolean r3);
    }

    void finishAllSessions(AnalyticsListener.EventTime r1);

    String getActiveSessionId();

    String getSessionForMediaPeriodId(Timeline r1, MediaSource.MediaPeriodId r2);

    void setListener(Listener r1);

    void updateSessions(AnalyticsListener.EventTime r1);

    void updateSessionsWithDiscontinuity(AnalyticsListener.EventTime r1, int r2);

    void updateSessionsWithTimelineChange(AnalyticsListener.EventTime r1);
}
