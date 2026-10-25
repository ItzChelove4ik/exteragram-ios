package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import kotlin.UByte;

/* loaded from: classes4.dex */
public abstract class FlacFrameReader {

    public static final class SampleNumberHolder {
        public long sampleNumber;

        public SampleNumberHolder() {
        }
    }

    private static boolean checkAndReadBlockSizeSamples(ParsableByteArray r0, FlacStreamMetadata r1, int r2) {
        int r02 = readFrameBlockSizeSamplesFromKey(r0, r2);
        if (r02 != (-1)) goto L5;
        return false;
    L5:
        if (r02 > r1.maxBlockSizeSamples) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean checkAndReadCrc(ParsableByteArray r4, int r5) {
        int r0 = r4.readUnsignedByte();
        int r1 = r4.getPosition();
        if (r0 != Util.crc8(r4.getData(), r5, r1 - 1, 0)) goto L5;
        return true;
    L5:
        return false;
    }

    private static boolean checkAndReadFirstSampleNumber(ParsableByteArray r2, FlacStreamMetadata r3, boolean r4, SampleNumberHolder r5) {
        long r0 = r2.readUtf8EncodedLong();     // Catch: NumberFormatException -> L8
        if (r4 == true) goto L6;
        r0 = r0 * r3.maxBlockSizeSamples;
    L6:
        r5.sampleNumber = r0;
        return true;
    L8:
        return false;
    }

    public static boolean checkAndReadFrameHeader(ParsableByteArray r17, FlacStreamMetadata r18, int r19, SampleNumberHolder r20) {
        int r2 = r17.getPosition();
        long r3 = r17.readUnsignedInt();
        long r5 = r3 >>> 16;
        if (r5 == r19) goto L6;
        return false;
    L6:
        if ((r5 & 1) != 1) goto L8;
        boolean r52 = true;
    L9:
        int r7 = (int) ((r3 >> 12) & 15);
        int r11 = (int) ((r3 >> 8) & 15);
        int r12 = (int) ((r3 >> 4) & 15);
        int r13 = (int) ((r3 >> 1) & 7);
        if ((r3 & 1) != 1) goto L12;
        boolean r32 = true;
    L14:
        if (checkChannelAssignment(r12, r18) == true) goto L16;
    L27:
        return false;
    L16:
        if (checkBitsPerSample(r13, r18) == false) goto L27;
        if (r32 == true) goto L27;
        if (checkAndReadFirstSampleNumber(r17, r18, r52, r20) == false) goto L27;
        if (checkAndReadBlockSizeSamples(r17, r18, r7) == false) goto L27;
        if (checkAndReadSampleRate(r17, r18, r11) == false) goto L27;
        if (checkAndReadCrc(r17, r2) == false) goto L27;
        return true;
    L12:
        r32 = false;
        goto L14
    L8:
        r52 = false;
        goto L9
    }

    private static boolean checkAndReadSampleRate(ParsableByteArray r4, FlacStreamMetadata r5, int r6) {
        int r0 = r5.sampleRate;
        if (r6 != 0) goto L6;
        return true;
    L6:
        if (r6 > 11) goto L12;
        if (r6 != r5.sampleRateLookupKey) goto L10;
        return true;
    L10:
        return false;
    L12:
        if (r6 != 12) goto L18;
        if ((r4.readUnsignedByte() * 1000) != r0) goto L16;
        return true;
    L16:
        return false;
    L18:
        if (r6 > 14) goto L24;
        int r42 = r4.readUnsignedShort();
        if (r6 != 14) goto L22;
        r42 = r42 * 10;
    L22:
        if (r42 != r0) goto L24;
        return true;
    L24:
        return false;
    }

    private static boolean checkBitsPerSample(int r1, FlacStreamMetadata r2) {
        if (r1 != 0) goto L6;
        return true;
    L6:
        if (r1 != r2.bitsPerSampleLookupKey) goto L8;
        return true;
    L8:
        return false;
    }

    private static boolean checkChannelAssignment(int r3, FlacStreamMetadata r4) {
        if (r3 > 7) goto L9;
        if (r3 != (r4.channels - 1)) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r3 <= 10) goto L11;
    L13:
        return false;
    L11:
        if (r4.channels != 2) goto L13;
        return true;
    }

    public static boolean checkFrameHeaderFromPeek(ExtractorInput r7, FlacStreamMetadata r8, int r9, SampleNumberHolder r10) {
        long r0 = r7.getPeekPosition();
        byte[] r3 = new byte[2];
        r7.peekFully(r3, 0, 2);
        if ((((r3[0] & UByte.MAX_VALUE) << 8) | (r3[1] & UByte.MAX_VALUE)) == r9) goto L6;
        r7.resetPeekPosition();
        r7.advancePeekPosition((int) (r0 - r7.getPosition()));
        return false;
    L6:
        ParsableByteArray r5 = new ParsableByteArray(16);
        System.arraycopy(r3, 0, r5.getData(), 0, 2);
        r5.setLimit(ExtractorUtil.peekToLength(r7, r5.getData(), 2, 14));
        r7.resetPeekPosition();
        r7.advancePeekPosition((int) (r0 - r7.getPosition()));
        return checkAndReadFrameHeader(r5, r8, r9, r10);
    }

    public static long getFirstSampleNumber(ExtractorInput r5, FlacStreamMetadata r6) {
        r5.resetPeekPosition();
        boolean r0 = true;
        r5.advancePeekPosition(1);
        byte[] r1 = new byte[1];
        r5.peekFully(r1, 0, 1);
        if ((r1[0] & 1) == 1) goto L6;
        r0 = false;
    L6:
        r5.advancePeekPosition(2);
        if (r0 == false) goto L9;
        int r12 = 7;
    L10:
        ParsableByteArray r3 = new ParsableByteArray(r12);
        r3.setLimit(ExtractorUtil.peekToLength(r5, r3.getData(), 0, r12));
        r5.resetPeekPosition();
        SampleNumberHolder r52 = new SampleNumberHolder();
        if (checkAndReadFirstSampleNumber(r3, r6, r0, r52) == false) goto L15;
        return r52.sampleNumber;
    L15:
        throw ParserException.createForMalformedContainer(null, null);
    L9:
        r12 = 6;
        goto L10
    }

    public static int readFrameBlockSizeSamplesFromKey(ParsableByteArray r0, int r1) {
        switch(r1) {
            case 1: goto L13;
            case 2: goto L12;
            case 3: goto L12;
            case 4: goto L12;
            case 5: goto L12;
            case 6: goto L10;
            case 7: goto L8;
            case 8: goto L6;
            case 9: goto L6;
            case 10: goto L6;
            case 11: goto L6;
            case 12: goto L6;
            case 13: goto L6;
            case 14: goto L6;
            case 15: goto L6;
            default: goto L3;
        };
    L3:
        return -1;
    L13:
        return 192;
    L6:
        return 256 << (r1 - 8);
    L8:
        return r0.readUnsignedShort() + 1;
    L10:
        return r0.readUnsignedByte() + 1;
    L12:
        return 576 << (r1 - 2);
    }
}
