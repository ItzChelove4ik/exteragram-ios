package org.telegram.messenger.video.resample;

import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ShortBuffer;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public class UpsampleAudioResampler implements AudioResampler {
    public UpsampleAudioResampler() {
    }

    private static short fakeSample(ShortBuffer r0, ShortBuffer r1, int r2, int r3) {
        return r0.get(r0.position() - r3);
    }

    private static float ratio(int r0, int r1) {
        return r0 / r1;
    }

    @Override // org.telegram.messenger.video.resample.AudioResampler
    public void resample(ShortBuffer r9, int r10, ShortBuffer r11, int r12, int r13) {
        if (r10 <= r12) goto L4;
        f$$ExternalSyntheticBUOutline1.m("Illegal use of UpsampleAudioResampler");
        return;
    L4:
        if (r13 == 1) goto L9;
        if (r13 == 2) goto L9;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Illegal use of UpsampleAudioResampler. Channels:", r13);
        return;
    L9:
        int r1 = r9.remaining() / r13;
        int r102 = ((int) Math.ceil(r1 * (r12 / r10))) - r1;
        float r122 = ratio(r1, r1);
        float r2 = ratio(r102, r102);
        int r4 = r102;
        int r3 = r1;
    L10:
        if (r3 <= 0) goto L22;
        if (r4 <= 0) goto L32;
        if (r122 >= r2) goto L14;
        r11.put(fakeSample(r11, r9, 1, r13));
        if (r13 != 2) goto L21;
        r11.put(fakeSample(r11, r9, 2, r13));
    L21:
        r4 = r4 - 1;
        r2 = ratio(r4, r102);
        goto L10
    L14:
        r11.put(r9.get());
        if (r13 != 2) goto L17;
        r11.put(r9.get());
    L17:
        r3 = r3 - 1;
        r122 = ratio(r3, r1);
        goto L10
    L32:
        return;
    }
}
