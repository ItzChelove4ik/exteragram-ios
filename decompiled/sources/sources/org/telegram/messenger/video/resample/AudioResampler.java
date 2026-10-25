package org.telegram.messenger.video.resample;

import java.nio.ShortBuffer;

/* loaded from: classes6.dex */
public interface AudioResampler {
    public static final AudioResampler DOWNSAMPLE = null;
    public static final AudioResampler PASSTHROUGH = null;
    public static final AudioResampler UPSAMPLE = null;

    static {
        DOWNSAMPLE = new DownsampleAudioResampler();
        UPSAMPLE = new UpsampleAudioResampler();
        PASSTHROUGH = new PassThroughAudioResampler();
    }

    void resample(ShortBuffer r1, int r2, ShortBuffer r3, int r4, int r5);
}
