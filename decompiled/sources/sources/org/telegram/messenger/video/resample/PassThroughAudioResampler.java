package org.telegram.messenger.video.resample;

import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ShortBuffer;

/* loaded from: classes6.dex */
public class PassThroughAudioResampler implements AudioResampler {
    public PassThroughAudioResampler() {
    }

    @Override // org.telegram.messenger.video.resample.AudioResampler
    public void resample(ShortBuffer r1, int r2, ShortBuffer r3, int r4, int r5) {
        if (r2 != r4) goto L5;
        r3.put(r1);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Illegal use of PassThroughAudioResampler");
    }
}
