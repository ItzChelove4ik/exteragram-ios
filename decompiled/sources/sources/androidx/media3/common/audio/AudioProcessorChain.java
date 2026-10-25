package androidx.media3.common.audio;

import androidx.media3.common.PlaybackParameters;

/* loaded from: classes3.dex */
public interface AudioProcessorChain {
    PlaybackParameters applyPlaybackParameters(PlaybackParameters r1);

    boolean applySkipSilenceEnabled(boolean r1);

    AudioProcessor[] getAudioProcessors();

    long getMediaDuration(long r1);

    long getSkippedOutputFrameCount();
}
