package org.telegram.messenger.video.remix;

import java.nio.ShortBuffer;

/* loaded from: classes6.dex */
public class PassThroughAudioRemixer implements AudioRemixer {
    public PassThroughAudioRemixer() {
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public int getRemixedSize(int r1, int r2, int r3) {
        return r1;
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public void remix(ShortBuffer r1, int r2, ShortBuffer r3, int r4) {
        r3.put(r1);
    }
}
