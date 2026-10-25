package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class AvcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderFrames;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    private AvcConfig(List<byte[]> r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10, float r11, String r12) {
        this.initializationData = r1;
        this.nalUnitLengthFieldLength = r2;
        this.width = r3;
        this.height = r4;
        this.bitdepthLuma = r5;
        this.bitdepthChroma = r6;
        this.colorSpace = r7;
        this.colorRange = r8;
        this.colorTransfer = r9;
        this.maxNumReorderFrames = r10;
        this.pixelWidthHeightRatio = r11;
        this.codecs = r12;
    }

    private static byte[] buildNalUnitForChild(ParsableByteArray r2) {
        int r0 = r2.readUnsignedShort();
        int r1 = r2.getPosition();
        r2.skipBytes(r0);
        return CodecSpecificDataUtil.buildNalUnit(r2.getData(), r1, r0);
    }

    public static AvcConfig parse(ParsableByteArray r15) {
        r15.skipBytes(4);     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r4 = (r15.readUnsignedByte() & 3) + 1;     // Catch: ArrayIndexOutOfBoundsException -> L19
        if (r4 == 3) goto L18;
        ArrayList r3 = new ArrayList();     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r0 = r15.readUnsignedByte() & 31;     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r3.add(buildNalUnitForChild(r15));     // Catch: ArrayIndexOutOfBoundsException -> L19
        r2 = r2 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L19
        goto L6
    L8:
        int r22 = r15.readUnsignedByte();     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r5 = 0;
    L9:
        if (r5 >= r22) goto L11;
        r3.add(buildNalUnitForChild(r15));     // Catch: ArrayIndexOutOfBoundsException -> L19
        r5 = r5 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L19
        goto L9
    L11:
        if (r0 <= 0) goto L14;
        NalUnitUtil.SpsData r152 = NalUnitUtil.parseSpsNalUnit((byte[]) r3.get(0), NalUnitUtil.NAL_START_CODE.length, ((byte[]) r3.get(0)).length);     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r02 = r152.width;     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r1 = r152.height;     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r23 = r152.bitDepthLumaMinus8 + 8;     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r52 = r152.bitDepthChromaMinus8 + 8;     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r6 = r152.colorSpace;     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r7 = r152.colorRange;     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r8 = r152.colorTransfer;     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r9 = r152.maxNumReorderFrames;     // Catch: ArrayIndexOutOfBoundsException -> L19
        float r10 = r152.pixelWidthHeightRatio;     // Catch: ArrayIndexOutOfBoundsException -> L19
        String r153 = CodecSpecificDataUtil.buildAvcCodecString(r152.profileIdc, r152.constraintsFlagsAndReservedZero2Bits, r152.levelIdc);     // Catch: ArrayIndexOutOfBoundsException -> L19
        int r11 = r8;
        int r12 = r9;
        float r13 = r10;
        int r82 = r52;
        int r92 = r6;
        int r102 = r7;
        int r53 = r02;
        int r62 = r1;
        int r72 = r23;
    L16:
        return new AvcConfig(r3, r4, r53, r62, r72, r82, r92, r102, r11, r12, r13, r153);
    L14:
        r153 = null;
        r53 = -1;
        r62 = -1;
        r72 = -1;
        r82 = -1;
        r11 = -1;
        r12 = 16;
        r13 = 1.0f;
        r92 = -1;
        r102 = -1;
        goto L16
    L18:
        throw new IllegalStateException();     // Catch: ArrayIndexOutOfBoundsException -> L19
    L19:
        e = move-exception;
        throw ParserException.createForMalformedContainer("Error parsing AVC config", e);
    }
}
