package org.telegram.messenger.video.remix;

import java.nio.ShortBuffer;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes6.dex */
public class DownMixAudioRemixer implements AudioRemixer {
    public DownMixAudioRemixer() {
    }

    public static short mix(short r3, short r4) {
        int r32 = r3 + ShortCompanionObject.MIN_VALUE;
        int r42 = r4 + ShortCompanionObject.MIN_VALUE;
        int r1 = 65535;
        if (r32 < 32768) goto L7;
        if (r42 < 32768) goto L7;
        int r2 = (((r32 + r42) * 2) - ((r32 * r42) / 32768)) - 65535;
    L9:
        if (r2 == 65536) goto L13;
        r1 = r2;
    L13:
        return (short) (r1 - 32768);
    L7:
        r2 = (r32 * r42) / 32768;
        goto L9
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public int getRemixedSize(int r1, int r2, int r3) {
        return r1 / 2;
    }

    @Override // org.telegram.messenger.video.remix.AudioRemixer
    public void remix(ShortBuffer r2, int r3, ShortBuffer r4, int r5) {
        int r1 = Math.min(r2.remaining() / 2, r4.remaining());
        int r32 = 0;
    L3:
        if (r32 >= r1) goto L5;
        r4.put(mix(r2.get(), r2.get()));
        r32 = r32 + 1;
        goto L3
    }
}
