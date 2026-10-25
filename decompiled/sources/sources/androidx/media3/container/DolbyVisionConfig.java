package androidx.media3.container;

import androidx.media3.common.util.ParsableByteArray;

/* loaded from: classes3.dex */
public final class DolbyVisionConfig {
    public final String codecs;
    public final int level;
    public final int profile;

    private DolbyVisionConfig(int r1, int r2, String r3) {
        this.profile = r1;
        this.level = r2;
        this.codecs = r3;
    }

    public static DolbyVisionConfig parse(ParsableByteArray r6) {
        r6.skipBytes(2);
        int r0 = r6.readUnsignedByte();
        int r1 = r0 >> 1;
        int r62 = (r6.readUnsignedByte() >> 3) & 31;
        int r63 = r62 | ((r0 & 1) << 5);
        if (r1 == 4) goto L17;
        if (r1 == 5) goto L17;
        if (r1 == 7) goto L17;
        if (r1 == 8) goto L17;
        if (r1 != 9) goto L13;
        String r02 = "dvav";
    L18:
        StringBuilder r2 = new StringBuilder();
        r2.append(r02);
        String r03 = ".";
        if (r1 >= 10) goto L21;
        String r5 = ".0";
    L22:
        r2.append(r5);
        r2.append(r1);
        if (r63 >= 10) goto L25;
        r03 = ".0";
    L25:
        r2.append(r03);
        r2.append(r63);
        return new DolbyVisionConfig(r1, r63, r2.toString());
    L21:
        r5 = ".";
        goto L22
    L13:
        if (r1 != 10) goto L15;
        r02 = "dav1";
        goto L18
    L15:
        return null;
    L17:
        r02 = "dvhe";
        goto L18
    }
}
