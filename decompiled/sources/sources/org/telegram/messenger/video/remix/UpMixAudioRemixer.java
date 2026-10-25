package org.telegram.messenger.video.remix;

import java.nio.ShortBuffer;

/* loaded from: classes6.dex */
public class UpMixAudioRemixer implements AudioRemixer {
    public UpMixAudioRemixer() {
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public int getRemixedSize(int r1, int r2, int r3) {
        return r1 * 2;
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public void remix(ShortBuffer r1, int r2, ShortBuffer r3, int r4) {
        int r0 = Math.min(r1.remaining(), r3.remaining() / 2);
        int r22 = 0;
    L3:
        if (r22 >= r0) goto L5;
        short r42 = r1.get();
        r3.put(r42);
        r3.put(r42);
        r22 = r22 + 1;
        goto L3
    }
}
