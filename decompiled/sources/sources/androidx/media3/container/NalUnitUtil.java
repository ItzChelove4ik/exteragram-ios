package androidx.media3.container;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import com.google.common.collect.ImmutableList;
import com.google.common.math.DoubleMath;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.telegram.messenger.MediaController;

/* loaded from: classes3.dex */
public abstract class NalUnitUtil {
    public static final float[] ASPECT_RATIO_IDC_VALUES = null;
    public static final byte[] NAL_START_CODE = null;
    private static int[] scratchEscapePositions;
    private static final Object scratchEscapePositionsLock = null;

    public static final class H265LayerInfo {
        public final int layerIdInVps;
        public final int viewId;

        public H265LayerInfo(int r1, int r2) {
            this.layerIdInVps = r1;
            this.viewId = r2;
        }
    }

    public static final class H265NalHeader {
        public final int layerId;
        public final int nalUnitType;
        public final int temporalId;

        public H265NalHeader(int r1, int r2, int r3) {
            this.nalUnitType = r1;
            this.layerId = r2;
            this.temporalId = r3;
        }
    }

    public static final class H265ProfileTierLevel {
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;

        public H265ProfileTierLevel(int r1, boolean r2, int r3, int r4, int[] r5, int r6) {
            this.generalProfileSpace = r1;
            this.generalTierFlag = r2;
            this.generalProfileIdc = r3;
            this.generalProfileCompatibilityFlags = r4;
            this.constraintBytes = r5;
            this.generalLevelIdc = r6;
        }
    }

    public static final class H265ProfileTierLevelsAndIndices {
        public final int[] indices;
        public final ImmutableList<H265ProfileTierLevel> profileTierLevels;

        public H265ProfileTierLevelsAndIndices(List<H265ProfileTierLevel> r1, int[] r2) {
            this.profileTierLevels = ImmutableList.copyOf(r1);
            this.indices = r2;
        }
    }

    public static final class H265RepFormat {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int height;
        public final int width;

        public H265RepFormat(int r1, int r2, int r3, int r4, int r5) {
            this.chromaFormatIdc = r1;
            this.bitDepthLumaMinus8 = r2;
            this.bitDepthChromaMinus8 = r3;
            this.width = r4;
            this.height = r5;
        }
    }

    public static final class H265RepFormatsAndIndices {
        public final int[] indices;
        public final ImmutableList<H265RepFormat> repFormats;

        public H265RepFormatsAndIndices(List<H265RepFormat> r1, int[] r2) {
            this.repFormats = ImmutableList.copyOf(r1);
            this.indices = r2;
        }
    }

    public static final class H265Sei3dRefDisplayInfoData {
        public final int exponentRefDisplayWidth;
        public final int exponentRefViewingDist;
        public final int leftViewId;
        public final int mantissaRefDisplayWidth;
        public final int mantissaRefViewingDist;
        public final int numRefDisplays;
        public final int precRefDisplayWidth;
        public final int precRefViewingDist;
        public final int rightViewId;

        public H265Sei3dRefDisplayInfoData(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            this.precRefDisplayWidth = r1;
            this.precRefViewingDist = r2;
            this.numRefDisplays = r3;
            this.leftViewId = r4;
            this.rightViewId = r5;
            this.exponentRefDisplayWidth = r6;
            this.mantissaRefDisplayWidth = r7;
            this.exponentRefViewingDist = r8;
            this.mantissaRefViewingDist = r9;
        }
    }

    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int decodedHeight;
        public final int decodedWidth;
        public final int height;
        public final int maxNumReorderPics;
        public final int maxSubLayersMinus1;
        public final H265NalHeader nalHeader;
        public final float pixelWidthHeightRatio;
        public final H265ProfileTierLevel profileTierLevel;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(H265NalHeader r1, int r2, H265ProfileTierLevel r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11, float r12, int r13, int r14, int r15, int r16) {
            this.nalHeader = r1;
            this.maxSubLayersMinus1 = r2;
            this.profileTierLevel = r3;
            this.chromaFormatIdc = r4;
            this.bitDepthLumaMinus8 = r5;
            this.bitDepthChromaMinus8 = r6;
            this.seqParameterSetId = r7;
            this.width = r8;
            this.height = r9;
            this.pixelWidthHeightRatio = r12;
            this.maxNumReorderPics = r13;
            this.colorSpace = r14;
            this.colorRange = r15;
            this.colorTransfer = r16;
            this.decodedWidth = r10;
            this.decodedHeight = r11;
        }
    }

    public static final class H265VideoSignalInfo {
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;

        public H265VideoSignalInfo(int r1, int r2, int r3) {
            this.colorSpace = r1;
            this.colorRange = r2;
            this.colorTransfer = r3;
        }
    }

    public static final class H265VideoSignalInfosAndIndices {
        public final int[] indices;
        public final ImmutableList<H265VideoSignalInfo> videoSignalInfos;

        public H265VideoSignalInfosAndIndices(List<H265VideoSignalInfo> r1, int[] r2) {
            this.videoSignalInfos = ImmutableList.copyOf(r1);
            this.indices = r2;
        }
    }

    public static final class H265VpsData {
        public final ImmutableList<H265LayerInfo> layerInfos;
        public final H265NalHeader nalHeader;
        public final H265ProfileTierLevelsAndIndices profileTierLevelsAndIndices;
        public final H265RepFormatsAndIndices repFormatsAndIndices;
        public final H265VideoSignalInfosAndIndices videoSignalInfosAndIndices;

        public H265VpsData(H265NalHeader r1, List<H265LayerInfo> r2, H265ProfileTierLevelsAndIndices r3, H265RepFormatsAndIndices r4, H265VideoSignalInfosAndIndices r5) {
            this.nalHeader = r1;
            if (r2 == null) goto L5;
            ImmutableList<H265LayerInfo> r12 = ImmutableList.copyOf(r2);
        L6:
            this.layerInfos = r12;
            this.profileTierLevelsAndIndices = r3;
            this.repFormatsAndIndices = r4;
            this.videoSignalInfosAndIndices = r5;
            return;
        L5:
            r12 = ImmutableList.of();
            goto L6
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int r1, int r2, boolean r3) {
            this.picParameterSetId = r1;
            this.seqParameterSetId = r2;
            this.bottomFieldPicOrderInFramePresentFlag = r3;
        }
    }

    public static final class SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int maxNumReorderFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int r1, int r2, int r3, int r4, int r5, int r6, int r7, float r8, int r9, int r10, boolean r11, boolean r12, int r13, int r14, int r15, boolean r16, int r17, int r18, int r19, int r20) {
            this.profileIdc = r1;
            this.constraintsFlagsAndReservedZero2Bits = r2;
            this.levelIdc = r3;
            this.seqParameterSetId = r4;
            this.maxNumRefFrames = r5;
            this.width = r6;
            this.height = r7;
            this.pixelWidthHeightRatio = r8;
            this.bitDepthLumaMinus8 = r9;
            this.bitDepthChromaMinus8 = r10;
            this.separateColorPlaneFlag = r11;
            this.frameMbsOnlyFlag = r12;
            this.frameNumLength = r13;
            this.picOrderCountType = r14;
            this.picOrderCntLsbLength = r15;
            this.deltaPicOrderAlwaysZeroFlag = r16;
            this.colorSpace = r17;
            this.colorRange = r18;
            this.colorTransfer = r19;
            this.maxNumReorderFrames = r20;
        }
    }

    static {
        NAL_START_CODE = new byte[]{0, 0, 0, 1};
        ASPECT_RATIO_IDC_VALUES = new float[]{1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
        scratchEscapePositionsLock = new Object();
        scratchEscapePositions = new int[10];
    }

    private static int applyConformanceWindowToHeight(int r1, int r2, int r3, int r4) {
        int r0 = 1;
        if (r2 != 1) goto L6;
        r0 = 2;
    L6:
        return r1 - (r0 * (r3 + r4));
    }

    private static int applyConformanceWindowToWidth(int r2, int r3, int r4, int r5) {
        int r0 = 2;
        if (r3 == 1) goto L8;
        if (r3 == 2) goto L8;
        r0 = 1;
    L8:
        return r2 - (r0 * (r4 + r5));
    }

    public static void clearPrefixFlags(boolean[] r2) {
        r2[0] = false;
        r2[1] = false;
        r2[2] = false;
    }

    private static String createCodecStringFromH265SpsPalyoad(ParsableNalUnitBitArray r6) {
        r6.skipBits(4);
        int r0 = r6.readBits(3);
        r6.skipBit();
        H265ProfileTierLevel r62 = parseH265ProfileTierLevel(r6, true, r0, null);
        return CodecSpecificDataUtil.buildHevcCodecString(r62.generalProfileSpace, r62.generalTierFlag, r62.generalProfileIdc, r62.generalProfileCompatibilityFlags, r62.constraintBytes, r62.generalLevelIdc);
    }

    public static int findNalUnit(byte[] r8, int r9, int r10, boolean[] r11) {
        int r0 = r10 - r9;
        boolean r1 = false;
        if (r0 < 0) goto L5;
        boolean r3 = true;
    L6:
        Assertions.checkState(r3);
        if (r0 != 0) goto L10;
        return r10;
    L10:
        if (r11[0] == false) goto L14;
        clearPrefixFlags(r11);
        return r9 - 3;
    L14:
        if (r0 > 1) goto L16;
    L21:
        if (r0 > 2) goto L23;
    L30:
        int r4 = r10 - 1;
        int r92 = r9 + 2;
    L31:
        if (r92 >= r4) goto L44;
        byte r5 = r8[r92];
        if ((r5 & 254) != 0) goto L43;
        int r6 = r92 - 2;
        if (r8[r6] == 0) goto L38;
    L42:
        r92 = r92 - 2;
        goto L43
    L38:
        if (r8[r92 - 1] != 0) goto L42;
        if (r5 != 1) goto L42;
        clearPrefixFlags(r11);
        return r6;
    L43:
        r92 = r92 + 3;
        goto L31
    L44:
        if (r0 > 2) goto L46;
        if (r0 != 2) goto L62;
        if (r11[2] == true) goto L57;
    L52:
        boolean r93 = false;
    L66:
        r11[0] = r93;
        if (r0 <= 1) goto L75;
        if (r8[r10 - 2] == 0) goto L71;
    L73:
        boolean r94 = false;
    L79:
        r11[1] = r94;
        if (r8[r4] != 0) goto L82;
        r1 = true;
    L82:
        r11[2] = r1;
        return r10;
    L71:
        if (r8[r4] != 0) goto L73;
    L72:
        r94 = true;
        goto L79
    L75:
        if (r11[2] == false) goto L73;
        if (r8[r4] != 0) goto L73;
    L57:
        if (r8[r10 - 2] != 0) goto L52;
        if (r8[r4] != 1) goto L52;
    L51:
        r93 = true;
        goto L66
    L62:
        if (r11[1] == false) goto L52;
        if (r8[r4] != 1) goto L52;
    L46:
        if (r8[r10 - 3] != 0) goto L52;
        if (r8[r10 - 2] != 0) goto L52;
        if (r8[r4] != 1) goto L52;
    L23:
        if (r11[2] == false) goto L30;
        if (r8[r9] != 0) goto L30;
        if (r8[r9 + 1] != 1) goto L30;
        clearPrefixFlags(r11);
        return r9 - 1;
    L16:
        if (r11[1] == false) goto L21;
        if (r8[r9] != 1) goto L21;
        clearPrefixFlags(r11);
        return r9 - 2;
    L5:
        r3 = false;
        goto L6
    }

    private static ImmutableList<Integer> findNalUnitPositions(byte[] r4) {
        boolean[] r0 = new boolean[3];
        ImmutableList.Builder r1 = ImmutableList.builder();
        int r2 = 0;
    L4:
        if (r2 >= r4.length) goto L10;
        int r22 = findNalUnit(r4, r2, r4.length, r0);
        if (r22 == r4.length) goto L8;
        r1.add(Integer.valueOf(r22));
    L8:
        r2 = r22 + 3;
        goto L4
    L10:
        return r1.build();
    }

    private static int findNextUnescapeIndex(byte[] r2, int r3, int r4) {
    L3:
        if (r3 >= (r4 - 2)) goto L12;
        if (r2[r3] != 0) goto L11;
        if (r2[r3 + 1] != 0) goto L11;
        if (r2[r3 + 2] != 3) goto L11;
        return r3;
    L11:
        r3 = r3 + 1;
        goto L3
    L12:
        return r4;
    }

    public static String getH265BaseLayerCodecsString(List<byte[]> r11) {
        int r1 = 0;
    L4:
        if (r1 >= r11.size()) goto L20;
        byte[] r2 = r11.get(r1);
        int r3 = r2.length;
        if (r3 <= 3) goto L19;
        ImmutableList<Integer> r5 = findNalUnitPositions(r2);
        int r6 = 0;
    L9:
        if (r6 >= r5.size()) goto L19;
        if ((r5.get(r6).intValue() + 3) >= r3) goto L18;
        ParsableNalUnitBitArray r7 = new ParsableNalUnitBitArray(r2, r5.get(r6).intValue() + 3, r3);
        H265NalHeader r8 = parseH265NalHeader(r7);
        if (r8.nalUnitType != 33) goto L18;
        if (r8.layerId != 0) goto L18;
        return createCodecStringFromH265SpsPalyoad(r7);
    L18:
        r6 = r6 + 1;
    L19:
        r1 = r1 + 1;
        goto L4
    L20:
        return null;
    }

    public static int getH265NalUnitType(byte[] r0, int r1) {
        return (r0[r1 + 3] & 126) >> 1;
    }

    public static int getNalUnitType(byte[] r0, int r1) {
        return r0[r1 + 3] & 31;
    }

    public static boolean isDependedOn(byte[] r2, int r3, int r4, Format r5) {
        if (Objects.equals(r5.sampleMimeType, MediaController.VIDEO_MIME_TYPE) == false) goto L7;
        return isH264NalUnitDependedOn(r2[r3]);
    L7:
        if (Objects.equals(r5.sampleMimeType, "video/hevc") == true) goto L9;
        return true;
    L9:
        return isH265NalUnitDependedOn(r2, r3, r4, r5);
    }

    public static boolean isH264NalUnitDependedOn(byte r3) {
        if (((r3 & 96) >> 5) == 0) goto L5;
        return true;
    L5:
        int r32 = r3 & 31;
        if (r32 != 1) goto L9;
        return false;
    L9:
        if (r32 != 9) goto L12;
        return false;
    L12:
        if (r32 != 14) goto L14;
        return false;
    L14:
        return true;
    }

    private static boolean isH265NalUnitDependedOn(byte[] r2, int r3, int r4, Format r5) {
        H265NalHeader r22 = parseH265NalHeader(new ParsableNalUnitBitArray(r2, r3, r4 + r3));
        int r32 = r22.nalUnitType;
        if (r32 != 35) goto L6;
        return false;
    L6:
        if (r32 <= 14) goto L8;
    L12:
        return true;
    L8:
        if ((r32 % 2) != 0) goto L12;
        if (r22.temporalId != (r5.maxSubLayers - 1)) goto L12;
        return false;
    }

    public static boolean isNalUnitSei(Format r3, byte r4) {
        if (Objects.equals(r3.sampleMimeType, MediaController.VIDEO_MIME_TYPE) == true) goto L7;
        if (MimeTypes.containsCodecsCorrespondingToMimeType(r3.codecs, MediaController.VIDEO_MIME_TYPE) == true) goto L7;
    L9:
        if (Objects.equals(r3.sampleMimeType, "video/hevc") == true) goto L13;
        if (MimeTypes.containsCodecsCorrespondingToMimeType(r3.codecs, "video/hevc") == true) goto L13;
        return false;
    L13:
        if (((r4 & 126) >> 1) != 39) goto L18;
    L17:
        return true;
    L18:
        return false;
    L7:
        if ((r4 & 31) == 6) goto L17;
        goto L17
    }

    public static int numberOfBytesInNalUnitHeader(Format r2) {
        if (Objects.equals(r2.sampleMimeType, MediaController.VIDEO_MIME_TYPE) == false) goto L7;
        return 1;
    L7:
        if (Objects.equals(r2.sampleMimeType, "video/hevc") == false) goto L9;
        return 2;
    L9:
        if (MimeTypes.containsCodecsCorrespondingToMimeType(r2.codecs, "video/hevc") == true) goto L15;
        return 0;
    L15:
        return 2;
    }

    private static H265NalHeader parseH265NalHeader(ParsableNalUnitBitArray r3) {
        r3.skipBit();
        return new H265NalHeader(r3.readBits(6), r3.readBits(6), r3.readBits(3) - 1);
    }

    private static H265ProfileTierLevel parseH265ProfileTierLevel(ParsableNalUnitBitArray r19, boolean r20, int r21, H265ProfileTierLevel r22) {
        int[] r4 = new int[6];
        int r7 = 0;
        if (r20 == false) goto L16;
        int r2 = r19.readBits(2);
        boolean r8 = r19.readBit();
        int r9 = r19.readBits(5);
        int r10 = 0;
        int r11 = 0;
    L6:
        if (r10 >= 32) goto L11;
        if (r19.readBit() == false) goto L10;
        r11 = r11 | (1 << r10);
    L10:
        r10 = r10 + 1;
        goto L6
    L11:
        int r102 = 0;
    L12:
        if (r102 >= 6) goto L14;
        r4[r102] = r19.readBits(8);
        r102 = r102 + 1;
        goto L12
    L14:
        int r13 = r2;
    L15:
        int[] r17 = r4;
        boolean r14 = r8;
        int r15 = r9;
        int r16 = r11;
    L19:
        int r18 = r19.readBits(8);
        int r23 = 0;
    L20:
        if (r7 >= r21) goto L28;
        if (r19.readBit() == false) goto L25;
        r23 = r23 + 88;
    L25:
        if (r19.readBit() == false) goto L27;
        r23 = r23 + 8;
    L27:
        r7 = r7 + 1;
        goto L20
    L28:
        r19.skipBits(r23);
        if (r21 <= 0) goto L32;
        r19.skipBits((8 - r21) * 2);
    L32:
        return new H265ProfileTierLevel(r13, r14, r15, r16, r17, r18);
    L16:
        if (r22 == null) goto L18;
        int r3 = r22.generalProfileSpace;
        r8 = r22.generalTierFlag;
        r9 = r22.generalProfileIdc;
        r11 = r22.generalProfileCompatibilityFlags;
        r4 = r22.constraintBytes;
        r13 = r3;
        goto L15
    L18:
        r17 = r4;
        r13 = 0;
        r14 = false;
        r15 = 0;
        r16 = 0;
        goto L19
    }

    private static H265RepFormat parseH265RepFormat(ParsableNalUnitBitArray r9) {
        int r1 = r9.readBits(16);
        int r0 = r9.readBits(16);
        if (r9.readBit() == false) goto L8;
        int r2 = r9.readBits(2);
        if (r2 != 3) goto L7;
        r9.skipBit();
    L7:
        int r4 = r9.readBits(4);
        int r6 = r9.readBits(4);
        int r5 = r4;
        int r42 = r2;
    L10:
        if (r9.readBit() == false) goto L13;
        int r22 = r9.readUnsignedExpGolombCodedInt();
        int r3 = r9.readUnsignedExpGolombCodedInt();
        int r7 = r9.readUnsignedExpGolombCodedInt();
        int r92 = r9.readUnsignedExpGolombCodedInt();
        r1 = applyConformanceWindowToWidth(r1, r42, r22, r3);
        r0 = applyConformanceWindowToHeight(r0, r42, r7, r92);
    L13:
        return new H265RepFormat(r42, r5, r6, r1, r0);
    L8:
        r42 = 0;
        r5 = 0;
        r6 = 0;
        goto L10
    }

    private static H265RepFormatsAndIndices parseH265RepFormatsAndIndices(ParsableNalUnitBitArray r6, int r7) {
        int r0 = r6.readUnsignedExpGolombCodedInt();
        int r1 = r0 + 1;
        ImmutableList.Builder r2 = ImmutableList.builderWithExpectedSize(r1);
        int[] r3 = new int[r7];
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L5;
        r2.add(parseH265RepFormat(r6));
        r4 = r4 + 1;
        goto L3
    L5:
        int r42 = 1;
        if (r1 <= 1) goto L12;
        if (r6.readBit() == false) goto L12;
        int r02 = DoubleMath.log2(r1, RoundingMode.CEILING);
    L10:
        if (r42 >= r7) goto L15;
        r3[r42] = r6.readBits(r02);
        r42 = r42 + 1;
    L15:
        return new H265RepFormatsAndIndices(r2.build(), r3);
    L12:
        if (r42 >= r7) goto L15;
        r3[r42] = Math.min(r42, r0);
        r42 = r42 + 1;
        goto L12
    }

    public static H265Sei3dRefDisplayInfoData parseH265Sei3dRefDisplayInfo(byte[] r18, int r19, int r20) {
        int r1 = r19 + 2;
        int r2 = r20 - 1;
    L3:
        byte r3 = r18[r2];
        if (r3 != 0) goto L8;
        if (r2 <= r1) goto L8;
        r2 = r2 - 1;
    L8:
        if (r3 == 0) goto L56;
        if (r2 <= r1) goto L56;
        ParsableNalUnitBitArray r32 = new ParsableNalUnitBitArray(r18, r1, r2 + 1);
    L13:
        if (r32.canReadBits(16) == false) goto L56;
        int r12 = r32.readBits(8);
        int r5 = 0;
    L16:
        if (r12 != 255) goto L18;
        r5 = r5 + 255;
        r12 = r32.readBits(8);
        goto L16
    L18:
        int r52 = r5 + r12;
        int r13 = r32.readBits(8);
        int r7 = 0;
    L19:
        if (r13 != 255) goto L21;
        r7 = r7 + 255;
        r13 = r32.readBits(8);
        goto L19
    L21:
        int r72 = r7 + r13;
        if (r72 == 0) goto L56;
        if (r32.canReadBits(r72) == false) goto L56;
        if (r52 == 176) goto L28;
        r32.skipBits(r72 * 8);
        goto L13
    L28:
        int r9 = r32.readUnsignedExpGolombCodedInt();
        boolean r0 = r32.readBit();
        if (r0 == false) goto L31;
        int r10 = r32.readUnsignedExpGolombCodedInt();
    L32:
        int r14 = r32.readUnsignedExpGolombCodedInt();
        int r122 = -1;
        int r132 = -1;
        int r142 = -1;
        int r15 = -1;
        int r16 = -1;
        int r17 = -1;
        int r53 = 0;
    L33:
        if (r53 > r14) goto L54;
        r122 = r32.readUnsignedExpGolombCodedInt();
        r132 = r32.readUnsignedExpGolombCodedInt();
        r142 = r32.readBits(6);
        if (r142 == 63) goto L36;
        if (r142 != 0) goto L39;
        int r8 = Math.max(0, r9 - 30);
    L40:
        r15 = r32.readBits(r8);
        if (r0 == false) goto L50;
        int r6 = r32.readBits(6);
        if (r6 == 63) goto L44;
        if (r6 != 0) goto L47;
        int r73 = Math.max(0, r10 - 30);
    L48:
        r16 = r6;
        r17 = r32.readBits(r73);
        goto L50
    L47:
        r73 = Math.max(0, (r6 + r10) - 31);
        goto L48
    L44:
        return null;
    L50:
        if (r32.readBit() == false) goto L52;
        r32.skipBits(10);
    L52:
        r53 = r53 + 1;
        goto L33
    L39:
        r8 = Math.max(0, (r142 + r9) - 31);
        goto L40
    L36:
        return null;
    L54:
        return new H265Sei3dRefDisplayInfoData(r9, r10, r14 + 1, r122, r132, r142, r15, r16, r17);
    L31:
        r10 = 0;
    L56:
        return null;
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] r1, int r2, int r3, H265VpsData r4) {
        H265NalHeader r0 = parseH265NalHeader(new ParsableNalUnitBitArray(r1, r2, r3));
        return parseH265SpsNalUnitPayload(r1, r2 + 2, r3, r0, r4);
    }

    public static H265SpsData parseH265SpsNalUnitPayload(byte[] r20, int r21, int r22, H265NalHeader r23, H265VpsData r24) {
        ParsableNalUnitBitArray r2 = new ParsableNalUnitBitArray(r20, r21, r22);
        r2.skipBits(4);
        int r4 = r2.readBits(3);
        if (r23.layerId != 0) goto L5;
    L7:
        boolean r5 = false;
    L8:
        if (r24 != null) goto L10;
    L12:
        int r8 = 0;
    L13:
        H265ProfileTierLevel r9 = null;
        if (r5 == true) goto L17;
        r2.skipBit();
        r9 = parseH265ProfileTierLevel(r2, true, r4, null);
    L21:
        int r7 = r2.readUnsignedExpGolombCodedInt();
        if (r5 == true) goto L24;
        int r14 = r2.readUnsignedExpGolombCodedInt();
        if (r14 != 3) goto L40;
        r2.skipBit();
    L40:
        int r15 = r2.readUnsignedExpGolombCodedInt();
        int r6 = r2.readUnsignedExpGolombCodedInt();
        if (r2.readBit() == false) goto L43;
        int r10 = r2.readUnsignedExpGolombCodedInt();
        int r13 = r2.readUnsignedExpGolombCodedInt();
        int r12 = r2.readUnsignedExpGolombCodedInt();
        int r3 = r2.readUnsignedExpGolombCodedInt();
        int r102 = applyConformanceWindowToWidth(r15, r14, r10, r13);
        int r32 = applyConformanceWindowToHeight(r6, r14, r12, r3);
    L44:
        int r122 = r2.readUnsignedExpGolombCodedInt();
        int r132 = r2.readUnsignedExpGolombCodedInt();
        int r62 = r32;
        int r33 = r6;
        int r152 = r102;
        int r103 = r122;
        int r123 = r15;
    L45:
        int r17 = r2.readUnsignedExpGolombCodedInt();
        if (r5 == false) goto L48;
        int r1 = -1;
    L54:
        int r18 = r33;
        r2.readUnsignedExpGolombCodedInt();
        r2.readUnsignedExpGolombCodedInt();
        r2.readUnsignedExpGolombCodedInt();
        r2.readUnsignedExpGolombCodedInt();
        r2.readUnsignedExpGolombCodedInt();
        r2.readUnsignedExpGolombCodedInt();
        if (r2.readBit() == false) goto L66;
        if (r5 == false) goto L60;
        boolean r34 = r2.readBit();
    L61:
        if (r34 == false) goto L64;
        r2.skipBits(6);
        goto L66
    L64:
        if (r2.readBit() == false) goto L66;
        skipH265ScalingList(r2);
        goto L66
    L60:
        r34 = false;
    L66:
        int r35 = 2;
        r2.skipBits(2);
        if (r2.readBit() == false) goto L69;
        r2.skipBits(8);
        r2.readUnsignedExpGolombCodedInt();
        r2.readUnsignedExpGolombCodedInt();
        r2.skipBit();
    L69:
        skipH265ShortTermReferencePictureSets(r2);
        if (r2.readBit() == false) goto L74;
        int r52 = r2.readUnsignedExpGolombCodedInt();
        int r11 = 0;
    L72:
        if (r11 >= r52) goto L74;
        r2.skipBits(r17 + 5);
        r11 = r11 + 1;
        r35 = 2;
    L74:
        r2.skipBits(r35);
        if (r2.readBit() == true) goto L77;
        int r25 = r4;
        int r42 = r14;
        int r82 = r152;
        float r112 = 1.0f;
        int r142 = -1;
        int r153 = -1;
        int r16 = -1;
    L117:
        int r92 = r62;
        return new H265SpsData(r23, r25, r9, r42, r103, r132, r7, r82, r92, r123, r18, r112, r1, r142, r153, r16);
    L77:
        if (r2.readBit() == false) goto L88;
        int r36 = r2.readBits(8);
        if (r36 != 255) goto L84;
        int r53 = r2.readBits(16);
        int r37 = r2.readBits(16);
        if (r53 == 0) goto L88;
        if (r37 == 0) goto L88;
        r112 = r53 / r37;
    L90:
        if (r2.readBit() == false) goto L93;
        r2.skipBit();
    L93:
        if (r2.readBit() == false) goto L102;
        r2.skipBits(3);
        if (r2.readBit() == false) goto L97;
        int r0 = 1;
    L99:
        if (r2.readBit() == false) goto L101;
        int r38 = r2.readBits(8);
        int r83 = r2.readBits(8);
        r2.skipBits(8);
        int r39 = ColorInfo.isoColorPrimariesToColorSpace(r38);
        int r54 = ColorInfo.isoTransferCharacteristicsToColorTransfer(r83);
    L110:
        if (r2.readBit() == false) goto L112;
        r2.readUnsignedExpGolombCodedInt();
        r2.readUnsignedExpGolombCodedInt();
    L112:
        r2.skipBit();
        if (r2.readBit() == false) goto L115;
        r62 = r62 * 2;
    L115:
        r25 = r4;
        r16 = r54;
        r42 = r14;
        r82 = r152;
        r153 = r0;
        r142 = r39;
    L101:
        r39 = -1;
        r54 = -1;
        goto L110
    L97:
        r0 = 2;
        goto L99
    L102:
        if (r24 == null) goto L108;
        H265VideoSignalInfosAndIndices r310 = r24.videoSignalInfosAndIndices;
        if (r310 == null) goto L108;
        int r55 = r310.indices[r8];
        if (r310.videoSignalInfos.size() <= r55) goto L108;
        H265VideoSignalInfo r02 = r24.videoSignalInfosAndIndices.videoSignalInfos.get(r55);
        r39 = r02.colorSpace;
        int r56 = r02.colorRange;
        r54 = r02.colorTransfer;
        r0 = r56;
    L108:
        r0 = -1;
        goto L101
    L84:
        float[] r57 = ASPECT_RATIO_IDC_VALUES;
        if (r36 >= r57.length) goto L87;
        r112 = r57[r36];
        goto L90
    L87:
        Log.w("NalUnitUtil", "Unexpected aspect_ratio_idc value: " + r36);
    L88:
        r112 = 1.0f;
        goto L90
    L48:
        if (r2.readBit() == false) goto L50;
        int r182 = 0;
    L51:
        int r113 = r182;
        r1 = -1;
    L52:
        if (r113 > r4) goto L54;
        r2.readUnsignedExpGolombCodedInt();
        r1 = Math.max(r2.readUnsignedExpGolombCodedInt(), r1);
        r2.readUnsignedExpGolombCodedInt();
        r113 = r113 + 1;
        r33 = r33;
        goto L52
    L50:
        r182 = r4;
        goto L51
    L43:
        r32 = r6;
        r102 = r15;
        goto L44
    L24:
        if (r2.readBit() == false) goto L26;
        int r133 = r2.readBits(8);
    L27:
        if (r24 == null) goto L36;
        H265RepFormatsAndIndices r143 = r24.repFormatsAndIndices;
        if (r143 == null) goto L36;
        if (r133 != (-1)) goto L32;
        r133 = r143.indices[r8];
    L32:
        if (r133 == (-1)) goto L36;
        if (r143.repFormats.size() <= r133) goto L36;
        H265RepFormat r134 = r24.repFormatsAndIndices.repFormats.get(r133);
        r14 = r134.chromaFormatIdc;
        r152 = r134.width;
        r62 = r134.height;
        r103 = r134.bitDepthLumaMinus8;
        r132 = r134.bitDepthChromaMinus8;
        r33 = r62;
        r123 = r152;
    L36:
        r33 = 0;
        r62 = 0;
        r103 = 0;
        r123 = 0;
        r132 = 0;
        r14 = 0;
        r152 = 0;
        goto L45
    L26:
        r133 = -1;
        goto L27
    L17:
        if (r24 == null) goto L21;
        H265ProfileTierLevelsAndIndices r104 = r24.profileTierLevelsAndIndices;
        int r114 = r104.indices[r8];
        if (r104.profileTierLevels.size() <= r114) goto L21;
        r9 = r24.profileTierLevelsAndIndices.profileTierLevels.get(r114);
        goto L21
    L10:
        if (r24.layerInfos.isEmpty() == true) goto L12;
        r8 = r24.layerInfos.get(Math.min(r23.layerId, r24.layerInfos.size() - 1)).layerIdInVps;
        goto L13
    L5:
        if (r4 != 7) goto L7;
        r5 = true;
        goto L8
    }

    private static H265VideoSignalInfo parseH265VideoSignalInfo(ParsableNalUnitBitArray r4) {
        r4.skipBits(3);
        if (r4.readBit() == false) goto L5;
        int r0 = 1;
    L6:
        int r2 = ColorInfo.isoColorPrimariesToColorSpace(r4.readBits(8));
        int r3 = ColorInfo.isoTransferCharacteristicsToColorTransfer(r4.readBits(8));
        r4.skipBits(8);
        return new H265VideoSignalInfo(r2, r0, r3);
    L5:
        r0 = 2;
        goto L6
    }

    private static H265VideoSignalInfosAndIndices parseH265VideoSignalInfosAndIndices(ParsableNalUnitBitArray r8, int r9, int r10, int[] r11) {
        if (r8.readBit() == true) goto L5;
        boolean r0 = r8.readBit();
    L6:
        if (r0 == false) goto L8;
        r8.skipBit();
    L8:
        boolean r02 = r8.readBit();
        boolean r2 = r8.readBit();
        int r3 = 0;
        if (r02 == true) goto L11;
        if (r2 == true) goto L11;
    L28:
        boolean r102 = r8.readBit();
        if (r102 == false) goto L31;
        int r03 = r8.readBits(4) + 1;
    L32:
        ImmutableList.Builder r22 = ImmutableList.builderWithExpectedSize(r03);
        int[] r4 = new int[r9];
        int r5 = 0;
    L33:
        if (r5 >= r03) goto L35;
        r22.add(parseH265VideoSignalInfo(r8));
        r5 = r5 + 1;
        goto L33
    L35:
        if (r102 == false) goto L40;
        if (r03 <= 1) goto L40;
    L37:
        if (r3 >= r9) goto L40;
        r4[r3] = r8.readBits(4);
        r3 = r3 + 1;
    L40:
        return new H265VideoSignalInfosAndIndices(r22.build(), r4);
    L31:
        r03 = r9;
    L11:
        int r42 = 0;
    L12:
        if (r42 >= r10) goto L28;
        int r52 = 0;
    L15:
        if (r52 >= r11[r42]) goto L27;
        if (r02 == false) goto L18;
        boolean r6 = r8.readBit();
    L19:
        if (r2 == false) goto L21;
        boolean r7 = r8.readBit();
    L22:
        if (r6 == false) goto L24;
        r8.skipBits(32);
    L24:
        if (r7 == false) goto L26;
        r8.skipBits(18);
    L26:
        r52 = r52 + 1;
        goto L15
    L21:
        r7 = false;
        goto L22
    L18:
        r6 = false;
        goto L19
    L27:
        r42 = r42 + 1;
        goto L12
    L5:
        r0 = true;
        goto L6
    }

    public static H265VpsData parseH265VpsNalUnit(byte[] r1, int r2, int r3) {
        ParsableNalUnitBitArray r0 = new ParsableNalUnitBitArray(r1, r2, r3);
        return parseH265VpsNalUnitPayload(r0, parseH265NalHeader(r0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static H265VpsData parseH265VpsNalUnitPayload(ParsableNalUnitBitArray r35, H265NalHeader r36) {
        r35.skipBits(4);
        boolean r2 = r35.readBit();
        boolean r3 = r35.readBit();
        int r5 = r35.readBits(6);
        int r6 = r5 + 1;
        int r8 = r35.readBits(3);
        r35.skipBits(17);
        H265ProfileTierLevel r11 = parseH265ProfileTierLevel(r35, true, r8, null);
        boolean r13 = false;
        if (r35.readBit() == false) goto L5;
        int r12 = 0;
    L6:
        if (r12 > r8) goto L8;
        r35.readUnsignedExpGolombCodedInt();
        r35.readUnsignedExpGolombCodedInt();
        r35.readUnsignedExpGolombCodedInt();
        r12 = r12 + 1;
        goto L6
    L8:
        int r122 = r35.readBits(6);
        int r14 = r35.readUnsignedExpGolombCodedInt() + 1;
        H265ProfileTierLevelsAndIndices r10 = new H265ProfileTierLevelsAndIndices(ImmutableList.of(r11), new int[1]);
        if (r6 < 2) goto L12;
        if (r14 < 2) goto L12;
        Object[] r15 = true;
    L13:
        if (r2 == false) goto L16;
        if (r3 == false) goto L16;
        Object[] r22 = true;
    L17:
        int r32 = r122 + 1;
        if (r32 < r6) goto L20;
        Object[] r18 = true;
    L21:
        if (r15 == false) goto L304;
        if (r22 == false) goto L304;
        if (r18 == false) goto L304;
        Class r152 = Integer.TYPE;
        int[][] r23 = (int[][]) Array.newInstance(r152, new int[]{r14, r32});
        int r182 = 1;
        int[] r9 = new int[r14];
        int[] r4 = new int[r14];
        r23[0][0] = 0;
        r9[0] = 1;
        r4[0] = 0;
        int r1 = 1;
    L26:
        if (r1 >= r14) goto L35;
        int r7 = 0;
        int r222 = 0;
    L28:
        if (r7 > r122) goto L33;
        if (r35.readBit() == false) goto L32;
        r23[r1][r222] = r7;
        r4[r1] = r7;
        r222 = r222 + 1;
    L32:
        r9[r1] = r222;
        r7 = r7 + 1;
        goto L28
    L33:
        r1 = r1 + 1;
        goto L26
    L35:
        if (r35.readBit() == false) goto L48;
        r35.skipBits(64);
        if (r35.readBit() == false) goto L39;
        r35.readUnsignedExpGolombCodedInt();
    L39:
        int r16 = r35.readUnsignedExpGolombCodedInt();
        int r72 = 0;
    L40:
        if (r72 >= r16) goto L48;
        r35.readUnsignedExpGolombCodedInt();
        if (r72 != 0) goto L44;
    L45:
        r13 = true;
    L46:
        skipH265HrdParameters(r35, r13, r8);
        r72 = r72 + 1;
        r13 = false;
        goto L40
    L44:
        if (r35.readBit() == false) goto L46;
    L48:
        if (r35.readBit() == false) goto L50;
        r35.byteAlign();
        H265ProfileTierLevel r102 = parseH265ProfileTierLevel(r35, false, r8, r11);
        boolean r73 = r35.readBit();
        int r232 = 6;
        boolean[] r17 = new boolean[16];
        int r19 = 0;
        int r24 = 0;
    L52:
        if (r19 >= 16) goto L57;
        boolean r26 = r35.readBit();
        r17[r19] = r26;
        if (r26 == false) goto L56;
        r24 = r24 + 1;
    L56:
        r19 = r19 + 1;
        goto L52
    L57:
        if (r24 == 0) goto L302;
        if (r17[1] == false) goto L302;
        int[] r110 = new int[r24];
        int r132 = 0;
    L63:
        if (r132 >= (r24 - (r73 ? 1 : 0))) goto L65;
        r110[r132] = r35.readBits(3);
        r132 = r132 + 1;
        goto L63
    L65:
        int[] r111 = new int[r24 + 1];
        if (r73 == false) goto L75;
        int r133 = 1;
    L68:
        if (r133 >= r24) goto L73;
        int[] r28 = r111;
        int r112 = 0;
    L70:
        if (r112 >= r133) goto L72;
        r28[r133] = r28[r133] + (r110[r112] + 1);
        r112 = r112 + 1;
        goto L70
    L72:
        r133 = r133 + 1;
        r111 = r28;
        goto L68
    L73:
        int[] r282 = r111;
        r282[r24] = 6;
    L76:
        int[][] r113 = (int[][]) Array.newInstance(r152, new int[]{r6, r24});
        int[] r134 = new int[r6];
        r134[0] = 0;
        boolean r153 = r35.readBit();
        int r114 = 1;
    L77:
        if (r114 >= r6) goto L89;
        if (r153 == false) goto L80;
        int r30 = r114;
        r134[r30] = r35.readBits(r232);
    L81:
        if (r73 == true) goto L85;
        int r115 = 0;
    L83:
        if (r115 >= r24) goto L88;
        int r33 = r115;
        r113[r30][r33] = r35.readBits(r110[r115] + 1);
        r115 = r33 + 1;
    L88:
        r114 = r30 + 1;
        r232 = 6;
        goto L77
    L85:
        int r116 = 0;
    L86:
        if (r116 >= r24) goto L88;
        r113[r30][r116] = (r134[r30] & ((1 << r282[r33]) - 1)) >> r282[r116];
        r116 = r116 + 1;
        goto L86
    L80:
        r30 = r114;
        r134[r30] = r30;
        goto L81
    L89:
        int[] r117 = new int[r32];
        int r25 = 1;
        int r74 = 0;
    L91:
        if (r74 >= r6) goto L111;
        r117[r134[r74]] = -1;
        int[] r262 = r117;
        int r154 = 0;
        int r233 = 0;
    L94:
        if (r154 >= 16) goto L102;
        if (r17[r154] == false) goto L101;
        if (r154 != r182) goto L100;
        r262[r134[r74]] = r113[r74][r233];
    L100:
        r233 = r233 + 1;
    L101:
        r154 = r154 + 1;
        r182 = 1;
        goto L94
    L102:
        if (r74 <= 0) goto L110;
        int r118 = 0;
    L104:
        if (r118 >= r74) goto L109;
        int r283 = r118;
        if (r262[r134[r74]] == r262[r134[r118]]) goto L110;
        r118 = r283 + 1;
        goto L104
    L109:
        r25 = r25 + 1;
    L110:
        r74 = r74 + 1;
        r117 = r262;
        r182 = 1;
        goto L91
    L111:
        int[] r263 = r117;
        int r119 = r35.readBits(4);
        if (r25 < 2) goto L300;
        if (r119 == 0) goto L300;
        int[] r75 = new int[r25];
        int r155 = 0;
    L116:
        if (r155 >= r25) goto L118;
        r75[r155] = r35.readBits(r119);
        r155 = r155 + 1;
        goto L116
    L118:
        int[] r120 = new int[r32];
        int r156 = 0;
    L119:
        if (r156 >= r6) goto L121;
        int[] r234 = r120;
        r234[Math.min(r134[r156], r122)] = r156;
        r156 = r156 + 1;
        r120 = r234;
        goto L119
    L121:
        int[] r235 = r120;
        ImmutableList.Builder r121 = ImmutableList.builder();
        int r157 = 0;
    L122:
        if (r157 > r122) goto L128;
        int r242 = r25;
        int[] r27 = r4;
        int r29 = Math.min(r263[r157], r242 - 1);
        if (r29 < 0) goto L126;
        int r210 = r75[r29];
    L127:
        r121.add(new H265LayerInfo(r235[r157], r210));
        r157 = r157 + 1;
        r25 = r242;
        r4 = r27;
        r75 = r75;
        goto L122
    L126:
        r210 = -1;
        goto L127
    L128:
        int[] r272 = r4;
        ImmutableList r211 = r121.build();
        if (((H265LayerInfo) r211.get(0)).viewId == (-1)) goto L131;
        int r123 = 1;
    L133:
        if (r123 > r122) goto L138;
        int r76 = -1;
        if (((H265LayerInfo) r211.get(r123)).viewId != (-1)) goto L136;
        r123 = r123 + 1;
        goto L133
    L136:
        int r42 = r123;
    L139:
        if (r42 == r76) goto L141;
        Class r124 = Boolean.TYPE;
        boolean[][] r77 = (boolean[][]) Array.newInstance(r124, new int[]{r6, r6});
        boolean[][] r125 = (boolean[][]) Array.newInstance(r124, new int[]{r6, r6});
        int r158 = 1;
    L143:
        if (r158 >= r6) goto L148;
        boolean[][] r172 = r125;
        int r126 = 0;
    L145:
        if (r126 >= r158) goto L147;
        boolean[] r236 = r77[r158];
        boolean[] r243 = r172[r158];
        boolean r264 = r35.readBit();
        r243[r126] = r264;
        r236[r126] = r264;
        r126 = r126 + 1;
        goto L145
    L147:
        r158 = r158 + 1;
        r125 = r172;
        goto L143
    L148:
        boolean[][] r173 = r125;
        int r127 = 1;
    L149:
        if (r127 >= r6) goto L162;
        int r159 = 0;
    L151:
        if (r159 >= r5) goto L161;
        int[] r237 = r134;
        int r135 = 0;
    L153:
        if (r135 >= r127) goto L160;
        boolean[] r244 = r173[r127];
        if (r244[r135] == false) goto L159;
        if (r173[r135][r159] == false) goto L159;
        r244[r159] = true;
    L159:
        r135 = r135 + 1;
    L160:
        r159 = r159 + 1;
        r134 = r237;
        goto L151
    L161:
        r127 = r127 + 1;
        goto L149
    L162:
        int[] r238 = r134;
        int[] r128 = new int[r32];
        int r136 = 0;
    L163:
        if (r136 >= r6) goto L168;
        int r1510 = 0;
        int r245 = 0;
    L165:
        if (r1510 >= r136) goto L167;
        r245 = r245 + (r77[r136][r1510] ? 1 : 0);
        r1510 = r1510 + 1;
        goto L165
    L167:
        r128[r238[r136]] = r245;
        r136 = r136 + 1;
        goto L163
    L168:
        int r137 = 0;
        int r1511 = 0;
    L169:
        if (r137 >= r6) goto L175;
        if (r128[r238[r137]] != 0) goto L173;
        r1511 = r1511 + 1;
    L173:
        r137 = r137 + 1;
        goto L169
    L175:
        if (r1511 > 1) goto L177;
        int[] r138 = new int[r6];
        int[] r1512 = new int[r14];
        if (r35.readBit() == false) goto L183;
        int[] r246 = r128;
        int r129 = 0;
    L181:
        if (r129 >= r6) goto L184;
        int r265 = r129;
        r138[r265] = r35.readBits(3);
        r129 = r265 + 1;
    L184:
        int r130 = 0;
    L185:
        if (r130 >= r14) goto L191;
        int r266 = r130;
        boolean[][] r292 = r77;
        int[] r284 = r138;
        int r131 = 0;
        int r139 = 0;
    L188:
        if (r131 >= r9[r266]) goto L190;
        r139 = Math.max(r139, r284[((H265LayerInfo) r211.get(r23[r266][r131])).layerIdInVps]);
        r131 = r131 + 1;
        goto L188
    L190:
        r1512[r266] = r139 + 1;
        r130 = r266 + 1;
        r138 = r284;
        r77 = r292;
        goto L185
    L191:
        boolean[][] r293 = r77;
        if (r35.readBit() == false) goto L203;
        int r140 = 0;
    L194:
        if (r140 >= r5) goto L203;
        int r78 = r140 + 1;
        int r1310 = r78;
    L196:
        if (r1310 >= r6) goto L202;
        if (r293[r1310][r140] == false) goto L200;
        int r267 = r140;
        r35.skipBits(3);
    L201:
        r1310 = r1310 + 1;
        r140 = r267;
        goto L196
    L200:
        r267 = r140;
        goto L201
    L202:
        r140 = r78;
    L203:
        r35.skipBit();
        int r141 = r35.readUnsignedExpGolombCodedInt() + 1;
        ImmutableList.Builder r52 = ImmutableList.builder();
        r52.add(r11);
        if (r141 <= 1) goto L208;
        r52.add(r102);
        int r79 = 2;
    L206:
        if (r79 >= r141) goto L208;
        r102 = parseH265ProfileTierLevel(r35, r35.readBit(), r8, r102);
        r52.add(r102);
        r79 = r79 + 1;
    L208:
        ImmutableList r53 = r52.build();
        int r710 = r35.readUnsignedExpGolombCodedInt() + r14;
        if (r710 > r14) goto L211;
        int r103 = r35.readBits(2);
        boolean[][] r1110 = (boolean[][]) Array.newInstance(r124, new int[]{r710, r32});
        int[] r1311 = new int[r710];
        int r223 = 0;
        int[] r82 = new int[r710];
        int r83 = 0;
    L213:
        if (r83 >= r14) goto L231;
        r1311[r83] = r223;
        r82[r83] = r272[r83];
        if (r103 != 0) goto L218;
        int r268 = r83;
        boolean[][] r285 = r1110;
        ImmutableList r31 = r53;
        int[] r302 = r1311;
        Arrays.fill(r1110[r268], r223, r9[r268], true);
        r302[r268] = r9[r268];
    L217:
        r223 = 0;
    L230:
        r83 = r268 + 1;
        r1110 = r285;
        r1311 = r302;
        r53 = r31;
        goto L213
    L218:
        r31 = r53;
        r268 = r83;
        r285 = r1110;
        r302 = r1311;
        if (r103 != 1) goto L229;
        int r54 = r272[r268];
        int r84 = 0;
    L222:
        if (r84 >= r9[r268]) goto L228;
        boolean[] r1111 = r285[r268];
        if (r23[r268][r84] != r54) goto L226;
        boolean r1312 = true;
    L227:
        r1111[r84] = r1312;
        r84 = r84 + 1;
        goto L222
    L226:
        r1312 = false;
        goto L227
    L228:
        r302[r268] = 1;
        goto L217
    L229:
        r223 = 0;
        r285[0][0] = true;
        r302[0] = 1;
        goto L230
    L231:
        ImmutableList r312 = r53;
        boolean[][] r286 = r1110;
        int[] r303 = r1311;
        int[] r55 = new int[r32];
        int r85 = 2;
        int[] r1112 = new int[2];
        r1112[1] = r32;
        r1112[r223] = r710;
        boolean[][] r34 = (boolean[][]) Array.newInstance(r124, r1112);
        int r1113 = 1;
        int r1210 = 0;
    L232:
        if (r1113 >= r710) goto L283;
        if (r103 != r85) goto L241;
        int r86 = 0;
    L236:
        if (r86 >= r9[r1113]) goto L241;
        r286[r1113][r86] = r35.readBit();
        int r1313 = r303[r1113];
        boolean r269 = r286[r1113][r86];
        r303[r1113] = r1313 + (r269 ? 1 : 0);
        if (r269 == false) goto L240;
        r82[r1113] = r23[r1113][r86];
    L240:
        r86 = r86 + 1;
    L241:
        if (r1210 != 0) goto L255;
        int r224 = 0;
        if (r23[r1113][0] == 0) goto L245;
    L256:
        int r87 = r224;
    L258:
        if (r87 >= r9[r1113]) goto L277;
        if (r141 <= 1) goto L275;
        r34[r1113][r87] = r286[r1113][r87];
        int r1314 = r42;
        int[] r2610 = r55;
        int r273 = r141;
        int r142 = DoubleMath.log2(r141, RoundingMode.CEILING);
        if (r34[r1113][r87] == true) goto L270;
        int r43 = ((H265LayerInfo) r211.get(r23[r1113][r87])).layerIdInVps;
        int r56 = r224;
    L264:
        if (r56 >= r87) goto L270;
        int r332 = r43;
        if (r173[r332][((H265LayerInfo) r211.get(r23[r1113][r56])).layerIdInVps] == true) goto L267;
        r56 = r56 + 1;
        r43 = r332;
        goto L264
    L267:
        r34[r1113][r87] = true;
    L270:
        if (r34[r1113][r87] == false) goto L276;
        if (r1210 <= 0) goto L274;
        if (r1113 != r1210) goto L274;
        r2610[r87] = r35.readBits(r142);
    L274:
        r35.skipBits(r142);
    L276:
        r87 = r87 + 1;
        r42 = r1314;
        r55 = r2610;
        r141 = r273;
        goto L258
    L275:
        r273 = r141;
        r1314 = r42;
        r2610 = r55;
        goto L276
    L277:
        int r274 = r141;
        int r1315 = r42;
        int[] r2611 = r55;
        if (r303[r1113] != 1) goto L282;
        if (r246[r82[r1113]] <= 0) goto L282;
        r35.skipBit();
    L282:
        r1113 = r1113 + 1;
        r42 = r1315;
        r55 = r2611;
        r141 = r274;
        r85 = 2;
        goto L232
    L245:
        if (r286[r1113][0] == false) goto L256;
        int r88 = 1;
    L248:
        if (r88 >= r9[r1113]) goto L256;
        if (r23[r1113][r88] != r42) goto L254;
        if (r286[r1113][r42] == false) goto L254;
        r1210 = r1113;
    L254:
        r88 = r88 + 1;
        goto L248
    L255:
        r224 = 0;
        goto L256
    L283:
        int[] r2612 = r55;
        int r57 = 1;
        if (r1210 == 0) goto L286;
        H265RepFormatsAndIndices r44 = parseH265RepFormatsAndIndices(r35, r6);
        r35.skipBits(2);
    L288:
        if (r57 >= r6) goto L293;
        if (r246[r238[r57]] != 0) goto L292;
        r35.skipBit();
    L292:
        r57 = r57 + 1;
        goto L288
    L293:
        skipH265DpbSize(r35, r710, r1512, r9, r34);
        skipToH265VuiPresentFlagAfterDpbSize(r35, r6, r293);
        if (r35.readBit() == false) goto L296;
        r35.byteAlign();
        H265VideoSignalInfosAndIndices r58 = parseH265VideoSignalInfosAndIndices(r35, r6, r14, r1512);
    L298:
        return new H265VpsData(r36, r211, new H265ProfileTierLevelsAndIndices(r312, r2612), r44, r58);
    L296:
        r58 = null;
        goto L298
    L286:
        return new H265VpsData(r36, null, r10, null, null);
    L211:
        return new H265VpsData(r36, null, r10, null, null);
    L183:
        r246 = r128;
        Arrays.fill(r138, 0, r6, r8);
        goto L184
    L177:
        return new H265VpsData(r36, null, r10, null, null);
    L141:
        return new H265VpsData(r36, null, r10, null, null);
    L138:
        r76 = -1;
        r42 = -1;
        goto L139
    L131:
        return new H265VpsData(r36, null, r10, null, null);
    L300:
        return new H265VpsData(r36, null, r10, null, null);
    L75:
        r282 = r111;
    L302:
        return new H265VpsData(r36, null, r10, null, null);
    L50:
        return new H265VpsData(r36, null, r10, null, null);
    L304:
        return new H265VpsData(r36, null, r10, null, null);
    L20:
        r18 = false;
    L16:
        r22 = false;
    L12:
        r15 = false;
        goto L13
    L5:
        r12 = r8;
        goto L6
    }

    public static PpsData parsePpsNalUnit(byte[] r0, int r1, int r2) {
        return parsePpsNalUnitPayload(r0, r1 + 1, r2);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] r1, int r2, int r3) {
        ParsableNalUnitBitArray r0 = new ParsableNalUnitBitArray(r1, r2, r3);
        int r12 = r0.readUnsignedExpGolombCodedInt();
        int r22 = r0.readUnsignedExpGolombCodedInt();
        r0.skipBit();
        return new PpsData(r12, r22, r0.readBit());
    }

    public static SpsData parseSpsNalUnit(byte[] r0, int r1, int r2) {
        return parseSpsNalUnitPayload(r0, r1 + 1, r2);
    }

    public static SpsData parseSpsNalUnitPayload(byte[] r32, int r33, int r34) {
        ParsableNalUnitBitArray r0 = new ParsableNalUnitBitArray(r32, r33, r34);
        int r3 = r0.readBits(8);
        int r4 = r0.readBits(8);
        int r5 = r0.readBits(8);
        int r6 = r0.readUnsignedExpGolombCodedInt();
        if (r3 == 100) goto L19;
        if (r3 == 110) goto L19;
        if (r3 == 122) goto L19;
        if (r3 == 244) goto L19;
        if (r3 == 44) goto L19;
        if (r3 == 83) goto L19;
        if (r3 == 86) goto L19;
        if (r3 == 118) goto L19;
        if (r3 == 128) goto L19;
        if (r3 == 138) goto L19;
        int r14 = 1;
        int r332 = 16;
        int r12 = 0;
        boolean r16 = false;
        int r17 = 0;
    L41:
        int r1 = r0.readUnsignedExpGolombCodedInt() + 4;
        int r8 = r0.readUnsignedExpGolombCodedInt();
        if (r8 != 0) goto L44;
        int r18 = r0.readUnsignedExpGolombCodedInt() + 4;
        int r21 = r3;
        int r23 = r8;
        boolean r2 = false;
    L52:
        int r7 = r0.readUnsignedExpGolombCodedInt();
        r0.skipBit();
        int r35 = r0.readUnsignedExpGolombCodedInt() + 1;
        int r82 = r0.readUnsignedExpGolombCodedInt() + 1;
        boolean r9 = r0.readBit();
        int r24 = (2 - (r9 ? 1 : 0)) * r82;
        if (r9 == true) goto L55;
        r0.skipBit();
    L55:
        r0.skipBit();
        int r36 = r35 * 16;
        int r242 = r24 * 16;
        if (r0.readBit() == false) goto L68;
        int r83 = r0.readUnsignedExpGolombCodedInt();
        int r26 = r0.readUnsignedExpGolombCodedInt();
        int r27 = r0.readUnsignedExpGolombCodedInt();
        int r28 = r0.readUnsignedExpGolombCodedInt();
        if (r14 != 0) goto L60;
        int r142 = 2 - (r9 ? 1 : 0);
        int r29 = 1;
    L67:
        r36 = r36 - ((r83 + r26) * r29);
        r242 = r242 - ((r27 + r28) * r142);
        goto L68
    L60:
        if (r14 != 3) goto L62;
        r29 = 1;
    L63:
        if (r14 != 1) goto L65;
        int r143 = 2;
    L66:
        r142 = r143 * (2 - (r9 ? 1 : 0));
        goto L67
    L65:
        r143 = 1;
        goto L66
    L62:
        r29 = 2;
    L68:
        int r84 = r36;
        int r37 = r21;
        if (r37 == 44) goto L79;
        if (r37 == 86) goto L79;
        if (r37 == 100) goto L79;
        if (r37 == 110) goto L79;
        if (r37 == 122) goto L79;
        if (r37 == 244) goto L79;
    L81:
        int r144 = r332;
    L82:
        float r19 = 1.0f;
        if (r0.readBit() == true) goto L85;
        int r22 = r144;
        int r11 = r17;
        int r172 = r18;
        float r10 = 1.0f;
        int r192 = -1;
        int r20 = -1;
        int r212 = -1;
        boolean r182 = r2;
    L132:
        return new SpsData(r37, r4, r5, r6, r7, r84, r242, r10, r11, r12, r16, r9, r1, r23, r172, r182, r192, r20, r212, r22);
    L85:
        if (r0.readBit() == false) goto L97;
        int r13 = r0.readBits(8);
        if (r13 != 255) goto L92;
        int r102 = r332;
        int r132 = r0.readBits(r102);
        int r103 = r0.readBits(r102);
        if (r132 == 0) goto L97;
        if (r103 == 0) goto L97;
        r19 = r132 / r103;
        goto L97
    L92:
        float[] r104 = ASPECT_RATIO_IDC_VALUES;
        if (r13 >= r104.length) goto L95;
        r19 = r104[r13];
        goto L97
    L95:
        Log.w("NalUnitUtil", "Unexpected aspect_ratio_idc value: " + r13);
    L97:
        if (r0.readBit() == false) goto L100;
        r0.skipBit();
    L100:
        if (r0.readBit() == false) goto L110;
        r0.skipBits(3);
        if (r0.readBit() == false) goto L104;
        int r133 = 1;
    L106:
        if (r0.readBit() == false) goto L108;
        int r112 = r0.readBits(8);
        int r15 = r0.readBits(8);
        r0.skipBits(8);
        int r105 = ColorInfo.isoColorPrimariesToColorSpace(r112);
        int r152 = ColorInfo.isoTransferCharacteristicsToColorTransfer(r15);
        int r153 = r105;
        int r106 = r152;
    L112:
        if (r0.readBit() == false) goto L115;
        r0.readUnsignedExpGolombCodedInt();
        r0.readUnsignedExpGolombCodedInt();
    L115:
        if (r0.readBit() == false) goto L117;
        r0.skipBits(65);
    L117:
        boolean r113 = r0.readBit();
        if (r113 == false) goto L120;
        skipHrdParameters(r0);
    L120:
        boolean r202 = r0.readBit();
        if (r202 == false) goto L123;
        skipHrdParameters(r0);
    L123:
        if (r113 == true) goto L125;
        if (r202 == true) goto L125;
    L126:
        r0.skipBit();
        if (r0.readBit() == false) goto L129;
        r0.skipBit();
        r0.readUnsignedExpGolombCodedInt();
        r0.readUnsignedExpGolombCodedInt();
        r0.readUnsignedExpGolombCodedInt();
        r0.readUnsignedExpGolombCodedInt();
        r144 = r0.readUnsignedExpGolombCodedInt();
        r0.readUnsignedExpGolombCodedInt();
    L129:
        r212 = r106;
        r20 = r133;
        r22 = r144;
        r11 = r17;
        r172 = r18;
        r10 = r19;
        r182 = r2;
        r192 = r153;
    L125:
        r0.skipBit();
        goto L126
    L108:
        r106 = -1;
    L109:
        r153 = -1;
        goto L112
    L104:
        r133 = 2;
        goto L106
    L110:
        r106 = -1;
        r133 = -1;
    L79:
        if ((r4 & 16) == 0) goto L81;
        r144 = 0;
        goto L82
    L44:
        if (r8 != 1) goto L51;
        boolean r183 = r0.readBit();
        r0.readSignedExpGolombCodedInt();
        r0.readSignedExpGolombCodedInt();
        r21 = r3;
        long r25 = r0.readUnsignedExpGolombCodedInt();
        r23 = r8;
        int r92 = 0;
    L47:
        if (r92 >= r25) goto L49;
        r0.readUnsignedExpGolombCodedInt();
        r92 = r92 + 1;
        goto L47
    L49:
        r2 = r183;
    L50:
        r18 = 0;
        goto L52
    L51:
        r21 = r3;
        r23 = r8;
        r2 = false;
    L19:
        r14 = r0.readUnsignedExpGolombCodedInt();
        if (r14 != 3) goto L22;
        r16 = r0.readBit();
    L23:
        r17 = r0.readUnsignedExpGolombCodedInt();
        int r184 = r0.readUnsignedExpGolombCodedInt();
        r0.skipBit();
        if (r0.readBit() == false) goto L39;
        if (r14 == 3) goto L28;
        int r122 = 8;
    L27:
        r332 = 16;
        int r110 = 0;
    L30:
        if (r110 >= r122) goto L40;
        if (r0.readBit() == false) goto L38;
        if (r110 >= 6) goto L36;
        int r85 = 16;
    L37:
        skipScalingList(r0, r85);
        goto L38
    L36:
        r85 = 64;
    L38:
        r110 = r110 + 1;
    L40:
        r12 = r184;
        goto L41
    L28:
        r122 = 12;
        goto L27
    L39:
        r332 = 16;
        goto L40
    L22:
        r16 = false;
        goto L23
    }

    private static void skipH265DpbSize(ParsableNalUnitBitArray r7, int r8, int[] r9, int[] r10, boolean[][] r11) {
        int r1 = 1;
    L3:
        if (r1 >= r8) goto L24;
        boolean r2 = r7.readBit();
        int r4 = 0;
    L6:
        if (r4 >= r9[r1]) goto L23;
        if (r4 <= 0) goto L10;
        if (r2 == false) goto L10;
        boolean r5 = r7.readBit();
    L13:
        if (r5 == false) goto L22;
        int r52 = 0;
    L16:
        if (r52 >= r10[r1]) goto L21;
        if (r11[r1][r52] == false) goto L20;
        r7.readUnsignedExpGolombCodedInt();
    L20:
        r52 = r52 + 1;
        goto L16
    L21:
        r7.readUnsignedExpGolombCodedInt();
        r7.readUnsignedExpGolombCodedInt();
    L22:
        r4 = r4 + 1;
    L10:
        if (r4 != 0) goto L12;
        r5 = true;
        goto L13
    L12:
        r5 = false;
        goto L13
    L23:
        r1 = r1 + 1;
        goto L3
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    private static void skipH265HrdParameters(ParsableNalUnitBitArray r8, boolean r9, int r10) {
        if (r9 == false) goto L16;
        boolean r92 = r8.readBit();
        boolean r1 = r8.readBit();
        if (r92 == true) goto L9;
        if (r1 == true) goto L9;
        boolean r2 = false;
        ?? r12 = r1;
        ?? r93 = r92;
    L17:
        int r3 = 0;
    L18:
        if (r3 > r10) goto L38;
        boolean r4 = r8.readBit();
        if (r4 == true) goto L22;
        r4 = r8.readBit();
    L22:
        if (r4 == false) goto L24;
        r8.readUnsignedExpGolombCodedInt();
        boolean r42 = false;
    L25:
        if (r42 == true) goto L27;
        int r43 = r8.readUnsignedExpGolombCodedInt();
    L28:
        int r5 = r93 + r12;
        int r6 = 0;
    L29:
        if (r6 >= r5) goto L37;
        int r7 = 0;
    L31:
        if (r7 > r43) goto L36;
        r8.readUnsignedExpGolombCodedInt();
        r8.readUnsignedExpGolombCodedInt();
        if (r2 == false) goto L35;
        r8.readUnsignedExpGolombCodedInt();
        r8.readUnsignedExpGolombCodedInt();
    L35:
        r8.skipBit();
        r7 = r7 + 1;
        goto L31
    L36:
        r6 = r6 + 1;
        goto L29
    L37:
        r3 = r3 + 1;
        goto L18
    L27:
        r43 = 0;
        goto L28
    L24:
        r42 = r8.readBit();
        goto L25
    L38:
        return;
    L9:
        r2 = r8.readBit();
        if (r2 == false) goto L12;
        r8.skipBits(19);
    L12:
        r8.skipBits(8);
        if (r2 == false) goto L15;
        r8.skipBits(4);
    L15:
        r8.skipBits(15);
        r12 = r1;
        r93 = r92;
        goto L17
    L16:
        r93 = 0;
        r12 = 0;
        r2 = false;
        goto L17
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray r7) {
        int r1 = 0;
    L4:
        if (r1 >= 4) goto L22;
        int r3 = 0;
    L7:
        if (r3 >= 6) goto L21;
        int r5 = 1;
        if (r7.readBit() == true) goto L11;
        r7.readUnsignedExpGolombCodedInt();
    L18:
        if (r1 != 3) goto L20;
        r5 = 3;
    L20:
        r3 = r3 + r5;
        goto L7
    L11:
        int r4 = Math.min(64, 1 << ((r1 << 1) + 4));
        if (r1 <= 1) goto L14;
        r7.readSignedExpGolombCodedInt();
    L14:
        int r6 = 0;
    L15:
        if (r6 >= r4) goto L18;
        r7.readSignedExpGolombCodedInt();
        r6 = r6 + 1;
        goto L15
    L21:
        r1 = r1 + 1;
        goto L4
    }

    private static void skipH265ShortTermReferencePictureSets(ParsableNalUnitBitArray r18) {
        int r0 = r18.readUnsignedExpGolombCodedInt();
        int[] r2 = new int[0];
        int[] r3 = new int[0];
        int r4 = -1;
        int r6 = 0;
        int r5 = -1;
    L3:
        if (r6 >= r0) goto L70;
        if (r6 != 0) goto L7;
    L56:
        int r22 = r18.readUnsignedExpGolombCodedInt();
        int r32 = r18.readUnsignedExpGolombCodedInt();
        int[] r42 = new int[r22];
        int r52 = 0;
    L57:
        if (r52 >= r22) goto L62;
        if (r52 <= 0) goto L60;
        int r8 = r42[r52 - 1];
    L61:
        r42[r52] = r8 - (r18.readUnsignedExpGolombCodedInt() + 1);
        r18.skipBit();
        r52 = r52 + 1;
        goto L57
    L60:
        r8 = 0;
        goto L61
    L62:
        int[] r53 = new int[r32];
        int r82 = 0;
    L63:
        if (r82 >= r32) goto L68;
        if (r82 <= 0) goto L66;
        int r9 = r53[r82 - 1];
    L67:
        r53[r82] = r9 + (r18.readUnsignedExpGolombCodedInt() + 1);
        r18.skipBit();
        r82 = r82 + 1;
        goto L63
    L66:
        r9 = 0;
        goto L67
    L68:
        r4 = r22;
        r2 = r42;
        r5 = r32;
        r3 = r53;
    L69:
        r6 = r6 + 1;
        goto L3
    L7:
        if (r18.readBit() == false) goto L56;
        int r83 = r4 + r5;
        boolean r92 = r18.readBit();
        int r93 = (1 - ((r92 ? 1 : 0) * 2)) * (r18.readUnsignedExpGolombCodedInt() + 1);
        int r10 = r83 + 1;
        boolean[] r11 = new boolean[r10];
        int r12 = 0;
    L9:
        if (r12 > r83) goto L15;
        if (r18.readBit() == true) goto L13;
        r11[r12] = r18.readBit();
    L14:
        r12 = r12 + 1;
        goto L9
    L13:
        r11[r12] = true;
        goto L14
    L15:
        int[] r7 = new int[r10];
        int[] r102 = new int[r10];
        int r122 = r5 - 1;
        int r13 = 0;
    L16:
        if (r122 < 0) goto L23;
        int r14 = r3[r122] + r93;
        if (r14 >= 0) goto L22;
        if (r11[r4 + r122] == false) goto L22;
        r7[r13] = r14;
        r13 = r13 + 1;
    L22:
        r122 = r122 - 1;
        goto L16
    L23:
        if (r93 < 0) goto L25;
    L27:
        int r123 = 0;
    L28:
        if (r123 >= r4) goto L35;
        int r142 = r2[r123] + r93;
        if (r142 >= 0) goto L34;
        if (r11[r123] == false) goto L34;
        r7[r13] = r142;
        r13 = r13 + 1;
    L34:
        r123 = r123 + 1;
        goto L28
    L35:
        int[] r72 = Arrays.copyOf(r7, r13);
        int r124 = r4 - 1;
        int r143 = 0;
    L36:
        if (r124 < 0) goto L43;
        int r15 = r2[r124] + r93;
        if (r15 <= 0) goto L42;
        if (r11[r124] == false) goto L42;
        r102[r143] = r15;
        r143 = r143 + 1;
    L42:
        r124 = r124 - 1;
        goto L36
    L43:
        if (r93 > 0) goto L45;
    L47:
        int r23 = 0;
    L48:
        if (r23 >= r5) goto L55;
        int r84 = r3[r23] + r93;
        if (r84 <= 0) goto L54;
        if (r11[r4 + r23] == false) goto L54;
        r102[r143] = r84;
        r143 = r143 + 1;
    L54:
        r23 = r23 + 1;
        goto L48
    L55:
        r3 = Arrays.copyOf(r102, r143);
        r2 = r72;
        r4 = r13;
        r5 = r143;
        goto L69
    L45:
        if (r11[r83] == false) goto L47;
        r102[r143] = r93;
        r143 = r143 + 1;
        goto L47
    L25:
        if (r11[r83] == false) goto L27;
        r7[r13] = r93;
        r13 = r13 + 1;
        goto L27
    }

    private static void skipHrdParameters(ParsableNalUnitBitArray r2) {
        int r0 = r2.readUnsignedExpGolombCodedInt() + 1;
        r2.skipBits(8);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r2.readUnsignedExpGolombCodedInt();
        r2.readUnsignedExpGolombCodedInt();
        r2.skipBit();
        r1 = r1 + 1;
        goto L3
    L5:
        r2.skipBits(20);
    }

    private static void skipScalingList(ParsableNalUnitBitArray r3, int r4) {
        int r0 = 8;
        int r2 = 0;
        int r1 = 8;
    L3:
        if (r2 >= r4) goto L10;
        if (r0 == 0) goto L6;
        r0 = ((r3.readSignedExpGolombCodedInt() + r1) + 256) % 256;
    L6:
        if (r0 == 0) goto L9;
        r1 = r0;
    L9:
        r2 = r2 + 1;
        goto L3
    }

    private static void skipToH265VuiPresentFlagAfterDpbSize(ParsableNalUnitBitArray r5, int r6, boolean[][] r7) {
        int r0 = r5.readUnsignedExpGolombCodedInt() + 2;
        int r2 = 1;
        if (r5.readBit() == false) goto L5;
        r5.skipBits(r0);
    L14:
        int r62 = r5.readUnsignedExpGolombCodedInt();
    L15:
        if (r2 > r62) goto L17;
        r5.skipBits(8);
        r2 = r2 + 1;
        goto L15
    L17:
        return;
    L5:
        int r1 = 1;
    L6:
        if (r1 >= r6) goto L14;
        int r3 = 0;
    L8:
        if (r3 >= r1) goto L13;
        if (r7[r1][r3] == false) goto L12;
        r5.skipBits(r0);
    L12:
        r3 = r3 + 1;
        goto L8
    L13:
        r1 = r1 + 1;
        goto L6
    }

    public static int unescapeStream(byte[] r8, int r9) {
        Object r0 = scratchEscapePositionsLock;
        monitor-enter(r0);
        int r2 = 0;
        int r3 = 0;
    L5:
        if (r2 >= r9) goto L14;
        r2 = findNextUnescapeIndex(r8, r2, r9);     // Catch: Throwable -> L11
        if (r2 >= r9) goto L5;
        int[] r4 = scratchEscapePositions;     // Catch: Throwable -> L11
        if (r4.length > r3) goto L13;
        scratchEscapePositions = Arrays.copyOf(r4, r4.length * 2);     // Catch: Throwable -> L11
    L13:
        scratchEscapePositions[r3] = r2;     // Catch: Throwable -> L11
        r2 = r2 + 3;     // Catch: Throwable -> L11
        r3 = r3 + 1;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    L14:
        int r92 = r9 - r3;     // Catch: Throwable -> L11
        int r22 = 0;
        int r42 = 0;
        int r5 = 0;
    L15:
        if (r22 >= r3) goto L17;
        int r6 = scratchEscapePositions[r22] - r5;     // Catch: Throwable -> L11
        System.arraycopy(r8, r5, r8, r42, r6);     // Catch: Throwable -> L11
        int r43 = r42 + r6;     // Catch: Throwable -> L11
        int r7 = r43 + 1;     // Catch: Throwable -> L11
        r8[r43] = 0;     // Catch: Throwable -> L11
        r42 = r43 + 2;     // Catch: Throwable -> L11
        r8[r7] = 0;     // Catch: Throwable -> L11
        r5 = r5 + (r6 + 3);     // Catch: Throwable -> L11
        r22 = r22 + 1;     // Catch: Throwable -> L11
        goto L15
    L17:
        System.arraycopy(r8, r5, r8, r42, r92 - r42);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r92;
    }
}
