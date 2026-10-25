package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class HevcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int decodedHeight;
    public final int decodedWidth;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderPics;
    public final int maxSubLayers;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int stereoMode;
    public final NalUnitUtil.H265VpsData vpsData;
    public final int width;

    private HevcConfig(List<byte[]> r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13, float r14, int r15, String r16, NalUnitUtil.H265VpsData r17) {
        this.initializationData = r1;
        this.nalUnitLengthFieldLength = r2;
        this.maxSubLayers = r3;
        this.width = r4;
        this.height = r5;
        this.decodedWidth = r6;
        this.decodedHeight = r7;
        this.bitdepthLuma = r8;
        this.bitdepthChroma = r9;
        this.colorSpace = r10;
        this.colorRange = r11;
        this.colorTransfer = r12;
        this.stereoMode = r13;
        this.pixelWidthHeightRatio = r14;
        this.maxNumReorderPics = r15;
        this.codecs = r16;
        this.vpsData = r17;
    }

    public static HevcConfig parse(ParsableByteArray r2) {
        return parseImpl(r2, false, null);
    }

    private static HevcConfig parseImpl(ParsableByteArray r38, boolean r39, NalUnitUtil.H265VpsData r40) {
        if (r39 == false) goto L7;
        r38.skipBytes(4);     // Catch: ArrayIndexOutOfBoundsException -> L5
    L8:
        int r2 = r38.readUnsignedByte() & 3;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r3 = r38.readUnsignedByte();     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r4 = r38.getPosition();     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
    L9:
        boolean r8 = true;
        if (r6 >= r3) goto L15;
        r38.skipBytes(1);     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r82 = r38.readUnsignedShort();     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r9 = 0;
    L12:
        if (r9 >= r82) goto L14;
        int r10 = r38.readUnsignedShort();     // Catch: ArrayIndexOutOfBoundsException -> L5
        r7 = r7 + (r10 + 4);     // Catch: ArrayIndexOutOfBoundsException -> L5
        r38.skipBytes(r10);     // Catch: ArrayIndexOutOfBoundsException -> L5
        r9 = r9 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L5
        goto L12
    L14:
        r6 = r6 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L5
        goto L9
    L15:
        r38.setPosition(r4);     // Catch: ArrayIndexOutOfBoundsException -> L5
        byte[] r42 = new byte[r7];     // Catch: ArrayIndexOutOfBoundsException -> L5
        NalUnitUtil.H265VpsData r28 = r40;
        int r14 = -1;
        int r15 = -1;
        int r16 = -1;
        int r17 = -1;
        int r18 = -1;
        int r19 = -1;
        int r20 = -1;
        int r21 = -1;
        int r22 = -1;
        int r23 = -1;
        int r24 = -1;
        int r26 = -1;
        float r25 = 1.0f;
        String r27 = null;
        int r62 = 0;
        int r92 = 0;
    L16:
        if (r62 >= r3) goto L44;
        int r102 = r38.readUnsignedByte() & 63;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r11 = r38.readUnsignedShort();     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r13 = r5;
        NalUnitUtil.H265VpsData r12 = r28;
    L18:
        if (r13 >= r11) goto L43;
        int r1 = r38.readUnsignedShort();     // Catch: ArrayIndexOutOfBoundsException -> L5
        boolean r29 = r8;
        byte[] r83 = NalUnitUtil.NAL_START_CODE;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r30 = r2;
        System.arraycopy(r83, r5, r42, r92, r83.length);     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r93 = r92 + r83.length;     // Catch: ArrayIndexOutOfBoundsException -> L5
        System.arraycopy(r38.getData(), r38.getPosition(), r42, r93, r1);     // Catch: ArrayIndexOutOfBoundsException -> L5
        if (r102 != 32) goto L24;
        if (r13 != 0) goto L24;
        r12 = NalUnitUtil.parseH265VpsNalUnit(r42, r93, r93 + r1);     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r31 = r3;
    L42:
        r92 = r93 + r1;     // Catch: ArrayIndexOutOfBoundsException -> L5
        r38.skipBytes(r1);     // Catch: ArrayIndexOutOfBoundsException -> L5
        r13 = r13 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L5
        r8 = r29;
        r2 = r30;
        r3 = r31;
    L24:
        if (r102 != 33) goto L32;
        if (r13 != 0) goto L32;
        NalUnitUtil.H265SpsData r210 = NalUnitUtil.parseH265SpsNalUnit(r42, r93, r93 + r1, r12);     // Catch: ArrayIndexOutOfBoundsException -> L5
        r14 = r210.maxSubLayersMinus1 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L5
        r15 = r210.width;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r84 = r210.height;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r52 = r210.decodedWidth;     // Catch: ArrayIndexOutOfBoundsException -> L5
        r31 = r3;
        int r32 = r210.decodedHeight;     // Catch: ArrayIndexOutOfBoundsException -> L5
        r19 = r210.bitDepthLumaMinus8 + 8;     // Catch: ArrayIndexOutOfBoundsException -> L5
        r20 = r210.bitDepthChromaMinus8 + 8;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r33 = r210.colorSpace;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r34 = r210.colorRange;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r35 = r210.colorTransfer;     // Catch: ArrayIndexOutOfBoundsException -> L5
        float r36 = r210.pixelWidthHeightRatio;     // Catch: ArrayIndexOutOfBoundsException -> L5
        int r37 = r210.maxNumReorderPics;     // Catch: ArrayIndexOutOfBoundsException -> L5
        NalUnitUtil.H265ProfileTierLevel r211 = r210.profileTierLevel;     // Catch: ArrayIndexOutOfBoundsException -> L5
        if (r211 == null) goto L29;
        int r232 = r37;
        r27 = CodecSpecificDataUtil.buildHevcCodecString(r211.generalProfileSpace, r211.generalTierFlag, r211.generalProfileIdc, r211.generalProfileCompatibilityFlags, r211.constraintBytes, r211.generalLevelIdc);     // Catch: ArrayIndexOutOfBoundsException -> L5
    L30:
        r25 = r36;
        r26 = r232;
        r22 = r34;
        r23 = r35;
        r18 = r32;
        r21 = r33;
        r17 = r52;
        r16 = r84;
    L31:
        r5 = 0;
        goto L42
    L29:
        r232 = r37;
    L32:
        r31 = r3;
        if (r102 != 39) goto L31;
        if (r13 != 0) goto L31;
        NalUnitUtil.H265Sei3dRefDisplayInfoData r212 = NalUnitUtil.parseH265Sei3dRefDisplayInfo(r42, r93, r93 + r1);     // Catch: ArrayIndexOutOfBoundsException -> L5
        if (r212 == null) goto L31;
        if (r12 == null) goto L31;
        r5 = 0;
        if (r212.leftViewId != r12.layerInfos.get(0).viewId) goto L41;
        r24 = 4;
        goto L42
    L41:
        r24 = 5;
        goto L42
    L43:
        r62 = r62 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L5
        r28 = r12;
        goto L16
    L44:
        int r302 = r2;
        if (r7 != 0) goto L48;
        List r0 = Collections.EMPTY_LIST;     // Catch: ArrayIndexOutOfBoundsException -> L5
    L49:
        return new HevcConfig(r0, r302 + 1, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28);
    L48:
        r0 = Collections.singletonList(r42);     // Catch: ArrayIndexOutOfBoundsException -> L5
        goto L49
    L7:
        r38.skipBytes(21);     // Catch: ArrayIndexOutOfBoundsException -> L5
    L5:
        e = move-exception;
        if (r39 == false) goto L53;
        String r110 = "L-HEVC config";
    L55:
        throw ParserException.createForMalformedContainer("Error parsing".concat(r110), e);
    L53:
        r110 = "HEVC config";
        goto L55
    }

    public static HevcConfig parseLayered(ParsableByteArray r1, NalUnitUtil.H265VpsData r2) {
        return parseImpl(r1, true, r2);
    }
}
