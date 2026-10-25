package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;

/* loaded from: classes4.dex */
public abstract class CeaUtil {
    public static void consume(long r10, ParsableByteArray r12, TrackOutput[] r13) {
    L2:
        boolean r1 = true;
        if (r12.bytesLeft() <= 1) goto L38;
        int r0 = readNon255TerminatedValue(r12);
        int r2 = readNon255TerminatedValue(r12);
        int r3 = r12.getPosition() + r2;
        if (r2 != (-1)) goto L7;
    L36:
        Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
        r3 = r12.limit();
    L37:
        r12.setPosition(r3);
        goto L2
    L7:
        if (r2 > r12.bytesLeft()) goto L36;
        if (r0 != 4) goto L37;
        if (r2 < 8) goto L37;
        int r02 = r12.readUnsignedByte();
        int r22 = r12.readUnsignedShort();
        if (r22 != 49) goto L16;
        int r6 = r12.readInt();
    L17:
        int r7 = r12.readUnsignedByte();
        if (r22 != 47) goto L21;
        r12.skipBytes(1);
    L21:
        if (r02 != 181) goto L27;
        if (r22 == 49) goto L25;
        if (r22 != 47) goto L27;
    L25:
        if (r7 != 3) goto L27;
        boolean r03 = true;
    L28:
        if (r22 == 49) goto L30;
    L34:
        if (r03 == false) goto L37;
        consumeCcData(r10, r12, r13);
        goto L37
    L30:
        if (r6 == 1195456820) goto L33;
        r1 = false;
    L33:
        r03 = r03 & r1;
    L27:
        r03 = false;
        goto L28
    L16:
        r6 = 0;
        goto L17
    }

    public static void consumeCcData(long r12, ParsableByteArray r14, TrackOutput[] r15) {
        int r0 = r14.readUnsignedByte();
        if ((r0 & 64) == 0) goto L11;
        r14.skipBytes(1);
        int r6 = (r0 & 31) * 3;
        int r02 = r14.getPosition();
        int r9 = r15.length;
        int r11 = 0;
    L5:
        if (r11 >= r9) goto L15;
        TrackOutput r2 = r15[r11];
        r14.setPosition(r02);
        r2.sampleData(r14, r6);
        if (r12 == (-9223372036854775807L)) goto L9;
        boolean r3 = true;
    L10:
        Assertions.checkState(r3);
        r2.sampleMetadata(r12, 1, r6, 0, null);
        r11 = r11 + 1;
        goto L5
    L9:
        r3 = false;
        goto L10
    L15:
        return;
    }

    private static int readNon255TerminatedValue(ParsableByteArray r3) {
        int r0 = 0;
    L4:
        if (r3.bytesLeft() == 0) goto L5;
        int r1 = r3.readUnsignedByte();
        r0 = r0 + r1;
        if (r1 == 255) goto L4;
        return r0;
    L5:
        return -1;
    }
}
