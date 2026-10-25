package org.telegram.messenger.video.remix;

import java.nio.ShortBuffer;

/* loaded from: classes6.dex */
public class DefaultAudioRemixer implements AudioRemixer {
    public DefaultAudioRemixer() {
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public int getRemixedSize(int r1, int r2, int r3) {
        if (r2 != 6) goto L5;
        AudioRemixer r0 = AudioRemixer.SURROUND;
    L11:
        return r0.getRemixedSize(r1, r2, r3);
    L5:
        if (r2 <= r3) goto L7;
        r0 = AudioRemixer.DOWNMIX;
        goto L11
    L7:
        if (r2 >= r3) goto L9;
        r0 = AudioRemixer.UPMIX;
        goto L11
    L9:
        r0 = AudioRemixer.PASSTHROUGH;
        goto L11
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public void remix(ShortBuffer r1, int r2, ShortBuffer r3, int r4) {
        if (r2 != 6) goto L5;
        AudioRemixer r0 = AudioRemixer.SURROUND;
    L10:
        r0.remix(r1, r2, r3, r4);
        return;
    L5:
        if (r2 <= r4) goto L7;
        r0 = AudioRemixer.DOWNMIX;
        goto L10
    L7:
        if (r2 >= r4) goto L9;
        r0 = AudioRemixer.UPMIX;
        goto L10
    L9:
        r0 = AudioRemixer.PASSTHROUGH;
        goto L10
    }
}
