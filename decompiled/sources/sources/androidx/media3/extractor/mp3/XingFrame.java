package androidx.media3.extractor.mp3;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;

/* loaded from: classes4.dex */
final class XingFrame {
    public final long dataSize;
    public final int encoderDelay;
    public final int encoderPadding;
    public final long frameCount;
    public final MpegAudioUtil.Header header;
    public final long[] tableOfContents;

    private XingFrame(MpegAudioUtil.Header r2, long r3, long r5, long[] r7, int r8, int r9) {
        this.header = new MpegAudioUtil.Header(r2);
        this.frameCount = r3;
        this.dataSize = r5;
        this.tableOfContents = r7;
        this.encoderDelay = r8;
        this.encoderPadding = r9;
    }

    public static XingFrame parse(MpegAudioUtil.Header r14, ParsableByteArray r15) {
        int r0 = r15.readInt();
        if ((r0 & 1) == 0) goto L5;
        int r1 = r15.readUnsignedIntToInt();
    L7:
        if ((r0 & 2) == 0) goto L10;
        long r3 = r15.readUnsignedInt();
    L9:
        long r9 = r3;
        if ((r0 & 4) != 4) goto L17;
        long[] r5 = new long[100];
        int r6 = 0;
    L14:
        if (r6 >= 100) goto L16;
        r5[r6] = r15.readUnsignedByte();
        r6 = r6 + 1;
    L16:
        long[] r11 = r5;
        if ((r0 & 8) == 0) goto L22;
        r15.skipBytes(4);
    L22:
        if (r15.bytesLeft() < 24) goto L24;
        r15.skipBytes(21);
        int r152 = r15.readUnsignedInt24();
        int r13 = r152 & 4095;
        int r12 = (16773120 & r152) >> 12;
    L26:
        return new XingFrame(r14, r1, r9, r11, r12, r13);
    L24:
        r12 = -1;
        r13 = -1;
        goto L26
    L17:
        r5 = null;
        goto L16
    L10:
        r3 = -1;
        goto L9
    L5:
        r1 = -1;
        goto L7
    }

    public long computeDurationUs() {
        long r0 = this.frameCount;
        if (r0 != (-1)) goto L5;
        return -9223372036854775807L;
    L5:
        if (r0 != 0) goto L8;
        return -9223372036854775807L;
    L8:
        return Util.sampleCountToDurationUs((r0 * r4.samplesPerFrame) - 1, this.header.sampleRate);
    }
}
