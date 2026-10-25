package androidx.media3.extractor.mp4;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.metadata.mp4.SmtaMetadataEntry;

/* loaded from: classes4.dex */
public abstract class SmtaAtomUtil {
    private static int getCaptureFrameRate(int r3, ParsableByteArray r4, int r5) {
        if (r3 != 12) goto L7;
        return 240;
    L7:
        if (r3 != 13) goto L11;
        return 120;
    L11:
        if (r3 == 21) goto L14;
        return -2147483647;
    L14:
        if (r4.bytesLeft() >= 8) goto L16;
    L25:
        return -2147483647;
    L16:
        if ((r4.getPosition() + 8) > r5) goto L25;
        int r32 = r4.readInt();
        int r52 = r4.readInt();
        if (r32 < 12) goto L25;
        if (r52 != 1936877170) goto L25;
        return r4.readUnsignedFixedPoint1616();
    }

    public static Metadata parseSmta(ParsableByteArray r8, int r9) {
        r8.skipBytes(12);
    L4:
        if (r8.getPosition() >= r9) goto L25;
        int r0 = r8.getPosition();
        int r2 = r8.readInt();
        if (r8.readInt() == 1935766900) goto L8;
        r8.setPosition(r0 + r2);
        goto L4
    L8:
        if (r2 >= 16) goto L10;
        return null;
    L10:
        r8.skipBytes(4);
        int r22 = -1;
        int r3 = 0;
        int r4 = 0;
    L12:
        if (r3 >= 2) goto L19;
        int r5 = r8.readUnsignedByte();
        int r7 = r8.readUnsignedByte();
        if (r5 != 0) goto L16;
        r22 = r7;
    L18:
        r3 = r3 + 1;
        goto L12
    L16:
        if (r5 != 1) goto L18;
        r4 = r7;
        goto L18
    L19:
        int r82 = getCaptureFrameRate(r22, r8, r9);
        if (r82 != (-2147483647)) goto L23;
        return null;
    L23:
        return new Metadata(new Metadata.Entry[]{new SmtaMetadataEntry(r82, r4)});
    L25:
        return null;
    }
}
