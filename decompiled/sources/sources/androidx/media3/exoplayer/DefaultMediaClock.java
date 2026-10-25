package androidx.media3.exoplayer;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;

/* loaded from: classes4.dex */
final class DefaultMediaClock implements MediaClock {
    private boolean isUsingStandaloneClock;
    private final PlaybackParametersListener listener;
    private MediaClock rendererClock;
    private Renderer rendererClockSource;
    private final StandaloneMediaClock standaloneClock;
    private boolean standaloneClockIsStarted;

    public interface PlaybackParametersListener {
        void onPlaybackParametersChanged(PlaybackParameters r1);
    }

    public DefaultMediaClock(PlaybackParametersListener r1, Clock r2) {
        this.listener = r1;
        this.standaloneClock = new StandaloneMediaClock(r2);
        this.isUsingStandaloneClock = true;
    }

    private boolean shouldUseStandaloneClock(boolean r3) {
        Renderer r0 = this.rendererClockSource;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.isEnded() == true) goto L20;
        if (r3 == false) goto L10;
        if (this.rendererClockSource.getState() == 2) goto L10;
        return true;
    L10:
        if (this.rendererClockSource.isReady() == true) goto L15;
        if (r3 == false) goto L13;
        return true;
    L13:
        if (this.rendererClockSource.hasReadStreamToEnd() == true) goto L23;
        return false;
    L23:
        return true;
    L15:
        return false;
    L20:
        return true;
    }

    private void syncClocks(boolean r5) {
        if (shouldUseStandaloneClock(r5) == false) goto L8;
        this.isUsingStandaloneClock = true;
        if (this.standaloneClockIsStarted == false) goto L21;
        this.standaloneClock.start();
        return;
    L21:
        return;
    L8:
        MediaClock r52 = (MediaClock) Assertions.checkNotNull(this.rendererClock);
        long r0 = r52.getPositionUs();
        if (this.isUsingStandaloneClock == true) goto L11;
    L17:
        this.standaloneClock.resetPosition(r0);
        PlaybackParameters r53 = r52.getPlaybackParameters();
        if (r53.equals(this.standaloneClock.getPlaybackParameters()) == true) goto L22;
        this.standaloneClock.setPlaybackParameters(r53);
        this.listener.onPlaybackParametersChanged(r53);
        return;
    L22:
        return;
    L11:
        if (r0 >= this.standaloneClock.getPositionUs()) goto L14;
        this.standaloneClock.stop();
        return;
    L14:
        this.isUsingStandaloneClock = false;
        if (this.standaloneClockIsStarted == false) goto L17;
        this.standaloneClock.start();
        goto L17
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        MediaClock r0 = this.rendererClock;
        if (r0 == null) goto L7;
        return r0.getPlaybackParameters();
    L7:
        return this.standaloneClock.getPlaybackParameters();
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public long getPositionUs() {
        if (this.isUsingStandaloneClock == false) goto L7;
        return this.standaloneClock.getPositionUs();
    L7:
        return ((MediaClock) Assertions.checkNotNull(this.rendererClock)).getPositionUs();
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public boolean hasSkippedSilenceSinceLastCall() {
        if (this.isUsingStandaloneClock == false) goto L7;
        return this.standaloneClock.hasSkippedSilenceSinceLastCall();
    L7:
        return ((MediaClock) Assertions.checkNotNull(this.rendererClock)).hasSkippedSilenceSinceLastCall();
    }

    public void onRendererDisabled(Renderer r2) {
        if (r2 != this.rendererClockSource) goto L6;
        this.rendererClock = null;
        this.rendererClockSource = null;
        this.isUsingStandaloneClock = true;
        return;
    }

    public void onRendererEnabled(Renderer r3) {
        MediaClock r0 = r3.getMediaClock();
        if (r0 == null) goto L11;
        MediaClock r1 = this.rendererClock;
        if (r0 == r1) goto L12;
        if (r1 != null) goto L10;
        this.rendererClock = r0;
        this.rendererClockSource = r3;
        r0.setPlaybackParameters(this.standaloneClock.getPlaybackParameters());
        return;
    L10:
        throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."), 1000);
    L12:
        return;
    }

    public void resetPosition(long r1) {
        this.standaloneClock.resetPosition(r1);
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public void setPlaybackParameters(PlaybackParameters r2) {
        MediaClock r0 = this.rendererClock;
        if (r0 == null) goto L5;
        r0.setPlaybackParameters(r2);
        r2 = this.rendererClock.getPlaybackParameters();
    L5:
        this.standaloneClock.setPlaybackParameters(r2);
    }

    public void start() {
        this.standaloneClockIsStarted = true;
        this.standaloneClock.start();
    }

    public void stop() {
        this.standaloneClockIsStarted = false;
        this.standaloneClock.stop();
    }

    public long syncAndGetPositionUs(boolean r1) {
        syncClocks(r1);
        return getPositionUs();
    }
}
