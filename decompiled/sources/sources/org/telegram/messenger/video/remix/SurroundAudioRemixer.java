package org.telegram.messenger.video.remix;

import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ShortBuffer;

/* loaded from: classes6.dex */
public class SurroundAudioRemixer implements AudioRemixer {
    public SurroundAudioRemixer() {
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public int getRemixedSize(int r1, int r2, int r3) {
        return (r1 / r2) * r3;
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public void remix(ShortBuffer r6, int r7, ShortBuffer r8, int r9) {
        if (r9 == 1) goto L8;
        if (r9 == 2) goto L8;
        f$$ExternalSyntheticBUOutline1.m("Output must be 2 or 1 channels");
        return;
    L8:
        int r72 = Math.min(r6.remaining() / r7, r8.remaining() / r9);
        int r1 = 0;
    L9:
        if (r1 >= r72) goto L16;
        short r2 = r6.get();
        short r3 = r6.get();
        r6.position(r6.position() + 4);
        if (r9 != 2) goto L13;
        r8.put(r2);
        r8.put(r3);
    L15:
        r1 = r1 + 1;
        goto L9
    L13:
        if (r9 != 1) goto L15;
        r8.put(DownMixAudioRemixer.mix(r2, r3));
        goto L15
    }
}
