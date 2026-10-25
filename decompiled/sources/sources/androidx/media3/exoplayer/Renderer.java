package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import com.google.android.gms.cast.framework.media.NotificationOptions;

/* loaded from: classes4.dex */
public interface Renderer extends PlayerMessage.Target {

    public interface WakeupListener {
        void onSleep();

        void onWakeup();
    }

    void disable();

    void enable(RendererConfiguration r1, Format[] r2, SampleStream r3, long r4, boolean r6, boolean r7, long r8, long r10, MediaSource.MediaPeriodId r12);

    default void enableMayRenderStartOfStream() {
    }

    RendererCapabilities getCapabilities();

    default long getDurationToProgressUs(long r1, long r3) {
        if (getState() == 1) goto L5;
        return NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L5:
        if (isReady() == false) goto L7;
        return 1000000;
    L7:
        if (isEnded() == false) goto L13;
        return 1000000;
    L13:
        return NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    }

    MediaClock getMediaClock();

    String getName();

    long getReadingPositionUs();

    int getState();

    SampleStream getStream();

    int getTrackType();

    boolean hasReadStreamToEnd();

    void init(int r1, PlayerId r2, Clock r3);

    boolean isCurrentStreamFinal();

    boolean isEnded();

    boolean isReady();

    void maybeThrowStreamError();

    void release();

    void render(long r1, long r3);

    void replaceStream(Format[] r1, SampleStream r2, long r3, long r5, MediaSource.MediaPeriodId r7);

    void reset();

    void resetPosition(long r1);

    void setCurrentStreamFinal();

    default void setPlaybackSpeed(float r1, float r2) {
    }

    void setTimeline(Timeline r1);

    void start();

    void stop();
}
