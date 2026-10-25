package androidx.media3.exoplayer;

import androidx.media3.common.MediaItem;

/* loaded from: classes4.dex */
public interface LivePlaybackSpeedControl {
    float getAdjustedPlaybackSpeed(long r1, long r3);

    long getTargetLiveOffsetUs();

    void notifyRebuffer();

    void setLiveConfiguration(MediaItem.LiveConfiguration r1);

    void setTargetLiveOffsetOverrideUs(long r1);
}
