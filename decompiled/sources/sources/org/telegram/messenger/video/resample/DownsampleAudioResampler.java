package org.telegram.messenger.video.resample;

import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ShortBuffer;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public class DownsampleAudioResampler implements AudioResampler {
    public DownsampleAudioResampler() {
    }

    private static float ratio(int r0, int r1) {
        return r0 / r1;
    }

    @Override // org.telegram.messenger.video.resample.AudioResampler
    public void resample(ShortBuffer r8, int r9, ShortBuffer r10, int r11, int r12) {
        if (r9 >= r11) goto L4;
        f$$ExternalSyntheticBUOutline1.m("Illegal use of DownsampleAudioResampler");
        return;
    L4:
        if (r12 == 1) goto L9;
        if (r12 == 2) goto L9;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Illegal use of DownsampleAudioResampler. Channels:", r12);
        return;
    L9:
        int r7 = r8.remaining() / r12;
        int r92 = (int) Math.ceil(r7 * (r11 / r9));
        int r72 = r7 - r92;
        float r112 = ratio(r92, r92);
        float r1 = ratio(r72, r72);
        int r3 = r72;
        int r2 = r92;
    L10:
        if (r2 <= 0) goto L19;
        if (r3 <= 0) goto L29;
        if (r112 >= r1) goto L14;
        r8.position(r8.position() + r12);
        r3 = r3 - 1;
        r1 = ratio(r3, r72);
        goto L10
    L14:
        r10.put(r8.get());
        if (r12 != 2) goto L17;
        r10.put(r8.get());
    L17:
        r2 = r2 - 1;
        r112 = ratio(r2, r92);
        goto L10
    L29:
        return;
    }
}
