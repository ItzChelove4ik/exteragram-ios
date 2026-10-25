package org.telegram.messenger.video.resample;

import java.nio.ShortBuffer;

/* loaded from: classes6.dex */
public class DefaultAudioResampler implements AudioResampler {
    public DefaultAudioResampler() {
    }

    @Override // org.telegram.messenger.video.resample.AudioResampler
    public void resample(ShortBuffer r7, int r8, ShortBuffer r9, int r10, int r11) {
        if (r8 >= r10) goto L6;
        AudioResampler.UPSAMPLE.resample(r7, r8, r9, r10, r11);
        return;
    L6:
        if (r8 <= r10) goto L9;
        AudioResampler.DOWNSAMPLE.resample(r7, r8, r9, r10, r11);
        return;
    L9:
        AudioResampler.PASSTHROUGH.resample(r7, r8, r9, r10, r11);
    }
}
