package org.telegram.messenger.video.remix;

import java.nio.ShortBuffer;

/* loaded from: classes6.dex */
public interface AudioRemixer {
    public static final AudioRemixer DOWNMIX = null;
    public static final AudioRemixer PASSTHROUGH = null;
    public static final AudioRemixer SURROUND = null;
    public static final AudioRemixer UPMIX = null;

    static {
        DOWNMIX = new DownMixAudioRemixer();
        UPMIX = new UpMixAudioRemixer();
        PASSTHROUGH = new PassThroughAudioRemixer();
        SURROUND = new SurroundAudioRemixer();
    }

    int getRemixedSize(int r1, int r2, int r3);

    void remix(ShortBuffer r1, int r2, ShortBuffer r3, int r4);
}
