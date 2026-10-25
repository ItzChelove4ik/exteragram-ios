package androidx.media3.extractor.ts;

import androidx.car.app.media.CarAudioRecord;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import org.telegram.messenger.OneUIUtilities;

/* loaded from: classes4.dex */
abstract class MpeghUtil {

    /* renamed from: androidx.media3.extractor.ts.MpeghUtil$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class MhasPacketHeader {
        public long packetLabel;
        public int packetLength;
        public int packetType;

        public MhasPacketHeader() {
        }
    }

    public static class Mpegh3daConfig {
        public final byte[] compatibleProfileLevelSet;
        public final int profileLevelIndication;
        public final int samplingFrequency;
        public final int standardFrameLength;

        private Mpegh3daConfig(int r1, int r2, int r3, byte[] r4) {
            this.profileLevelIndication = r1;
            this.samplingFrequency = r2;
            this.standardFrameLength = r3;
            this.compatibleProfileLevelSet = r4;
        }

        public /* synthetic */ Mpegh3daConfig(int r1, int r2, int r3, byte[] r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }
    }

    private static int getOutputFrameLength(int r2) {
        if (r2 != 0) goto L4;
        return 768;
    L4:
        if (r2 != 1) goto L6;
        return 1024;
    L6:
        if (r2 != 2) goto L8;
        return 2048;
    L8:
        if (r2 != 3) goto L10;
        return 2048;
    L10:
        if (r2 != 4) goto L14;
        return 4096;
    L14:
        throw ParserException.createForUnsupportedContainerFeature("Unsupported coreSbrFrameLengthIndex " + r2);
    }

    private static double getResamplingRatio(int r2) {
        switch(r2) {
            case 14700: goto L11;
            case 16000: goto L11;
            case 22050: goto L9;
            case 24000: goto L9;
            case 29400: goto L7;
            case 32000: goto L7;
            case 44100: goto L5;
            case 48000: goto L5;
            case 58800: goto L7;
            case 64000: goto L7;
            case 88200: goto L5;
            case 96000: goto L5;
            default: goto L4;
        };
    L5:
        return 1.0d;
    L7:
        return 1.5d;
    L9:
        return 2.0d;
    L11:
        return 3.0d;
    L4:
        throw ParserException.createForUnsupportedContainerFeature("Unsupported sampling rate " + r2);
    }

    private static int getSamplingFrequency(int r2) {
        switch(r2) {
            case 0: goto L55;
            case 1: goto L53;
            case 2: goto L51;
            case 3: goto L49;
            case 4: goto L47;
            case 5: goto L45;
            case 6: goto L43;
            case 7: goto L41;
            case 8: goto L39;
            case 9: goto L37;
            case 10: goto L35;
            case 11: goto L33;
            case 12: goto L31;
            case 13: goto L4;
            case 14: goto L4;
            case 15: goto L29;
            case 16: goto L27;
            case 17: goto L25;
            case 18: goto L23;
            case 19: goto L21;
            case 20: goto L19;
            case 21: goto L17;
            case 22: goto L15;
            case 23: goto L13;
            case 24: goto L11;
            case 25: goto L9;
            case 26: goto L7;
            case 27: goto L5;
            default: goto L4;
        };
    L5:
        return 9600;
    L7:
        return 12800;
    L9:
        return 14400;
    L11:
        return 17075;
    L13:
        return 19200;
    L15:
        return 20000;
    L17:
        return 25600;
    L19:
        return 28800;
    L21:
        return 34150;
    L23:
        return 38400;
    L25:
        return OneUIUtilities.ONE_UI_4_0;
    L27:
        return 51200;
    L29:
        return 57600;
    L31:
        return 7350;
    L33:
        return 8000;
    L35:
        return 11025;
    L37:
        return 12000;
    L39:
        return CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE;
    L41:
        return 22050;
    L43:
        return 24000;
    L45:
        return 32000;
    L47:
        return 44100;
    L49:
        return 48000;
    L51:
        return 64000;
    L53:
        return 88200;
    L55:
        return 96000;
    L4:
        throw ParserException.createForUnsupportedContainerFeature("Unsupported sampling rate index " + r2);
    }

    private static int getSbrRatioIndex(int r2) {
        if (r2 != 0) goto L4;
        return 0;
    L4:
        if (r2 == 1) goto L17;
        int r1 = 2;
        if (r2 == 2) goto L14;
        r1 = 3;
        if (r2 == 3) goto L14;
        if (r2 != 4) goto L13;
        return 1;
    L13:
        throw ParserException.createForUnsupportedContainerFeature("Unsupported coreSbrFrameLengthIndex " + r2);
    L14:
        return r1;
    L17:
        return 0;
    }

    public static boolean isSyncWord(int r1) {
        if ((r1 & 16777215) != 12583333) goto L6;
        return true;
    L6:
        return false;
    }

    public static int parseAudioTruncationInfo(ParsableBitArray r1) {
        if (r1.readBit() == false) goto L6;
        r1.skipBits(2);
        return r1.readBits(13);
    L6:
        return 0;
    }

    public static boolean parseMhasPacketHeader(ParsableBitArray r7, MhasPacketHeader r8) {
        r7.getBytePosition();
        int r0 = readEscapedIntValue(r7, 3, 8, 8);
        r8.packetType = r0;
        if (r0 != (-1)) goto L5;
        return false;
    L5:
        long r02 = readEscapedLongValue(r7, 2, 8, 32);
        r8.packetLabel = r02;
        if (r02 != (-1)) goto L9;
        return false;
    L9:
        if (r02 > 16) goto L29;
        if (r02 != 0) goto L24;
        int r03 = r8.packetType;
        if (r03 == 1) goto L23;
        if (r03 == 2) goto L21;
        if (r03 != 17) goto L24;
        throw ParserException.createForMalformedContainer("AudioTruncation packet with invalid packet label 0", null);
    L21:
        throw ParserException.createForMalformedContainer("Mpegh3daFrame packet with invalid packet label 0", null);
    L23:
        throw ParserException.createForMalformedContainer("Mpegh3daConfig packet with invalid packet label 0", null);
    L24:
        int r72 = readEscapedIntValue(r7, 11, 24, 24);
        r8.packetLength = r72;
        if (r72 == (-1)) goto L27;
        return true;
    L27:
        return false;
    L29:
        throw ParserException.createForUnsupportedContainerFeature("Contains sub-stream with an invalid packet label " + r8.packetLabel);
    }

    public static Mpegh3daConfig parseMpegh3daConfig(ParsableBitArray r12) {
        int r2 = r12.readBits(8);
        int r1 = r12.readBits(5);
        if (r1 != 31) goto L5;
        int r13 = r12.readBits(24);
    L6:
        int r3 = r12.readBits(3);
        int r4 = getOutputFrameLength(r3);
        int r32 = getSbrRatioIndex(r3);
        r12.skipBits(2);
        skipSpeakerConfig3d(r12);
        skipMpegh3daDecoderConfig(r12, parseSignals3d(r12), r32);
        byte[] r6 = null;
        if (r12.readBit() == false) goto L18;
        int r5 = readEscapedIntValue(r12, 2, 4, 8) + 1;
        int r8 = 0;
    L9:
        if (r8 >= r5) goto L18;
        int r10 = readEscapedIntValue(r12, 4, 8, 16);
        int r9 = readEscapedIntValue(r12, 4, 8, 16);
        if (r10 != 7) goto L16;
        int r62 = r12.readBits(4) + 1;
        r12.skipBits(4);
        byte[] r92 = new byte[r62];
        int r102 = 0;
    L13:
        if (r102 >= r62) goto L15;
        r92[r102] = (byte) r12.readBits(8);
        r102 = r102 + 1;
        goto L13
    L15:
        r6 = r92;
    L17:
        r8 = r8 + 1;
        goto L9
    L16:
        r12.skipBits(r9 * 8);
    L18:
        byte[] r52 = r6;
        double r63 = getResamplingRatio(r13);
        return new Mpegh3daConfig(r2, (int) (r13 * r63), (int) (r4 * r63), r52, null);
    L5:
        r13 = getSamplingFrequency(r1);
        goto L6
    }

    private static boolean parseMpegh3daCoreConfig(ParsableBitArray r2) {
        r2.skipBits(3);
        boolean r0 = r2.readBit();
        if (r0 == false) goto L5;
        r2.skipBits(13);
    L5:
        return r0;
    }

    private static int parseSignals3d(ParsableBitArray r7) {
        int r1 = r7.readBits(5);
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r2 >= (r1 + 1)) goto L13;
        int r4 = r7.readBits(3);
        r3 = r3 + (readEscapedIntValue(r7, 5, 8, 16) + 1);
        if (r4 == 0) goto L10;
        if (r4 == 2) goto L10;
    L12:
        r2 = r2 + 1;
    L10:
        if (r7.readBit() == false) goto L12;
        skipSpeakerConfig3d(r7);
        goto L12
    L13:
        return r3;
    }

    private static int readEscapedIntValue(ParsableBitArray r4, int r5, int r6, int r7) {
        if (Math.max(Math.max(r5, r6), r7) > 31) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        int r02 = (1 << r5) - 1;
        int r1 = (1 << r6) - 1;
        IntMath.checkedAdd(IntMath.checkedAdd(r02, r1), 1 << r7);
        if (r4.bitsLeft() >= r5) goto L9;
        return -1;
    L9:
        int r52 = r4.readBits(r5);
        if (r52 == r02) goto L12;
        return r52;
    L12:
        if (r4.bitsLeft() >= r6) goto L14;
        return -1;
    L14:
        int r62 = r4.readBits(r6);
        int r53 = r52 + r62;
        if (r62 == r1) goto L17;
        return r53;
    L17:
        if (r4.bitsLeft() >= r7) goto L20;
        return -1;
    L20:
        return r53 + r4.readBits(r7);
    L5:
        r0 = false;
        goto L6
    }

    private static long readEscapedLongValue(ParsableBitArray r8, int r9, int r10, int r11) {
        if (Math.max(Math.max(r9, r10), r11) > 63) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        long r2 = (1 << r9) - 1;
        long r4 = (1 << r10) - 1;
        LongMath.checkedAdd(LongMath.checkedAdd(r2, r4), 1 << r11);
        if (r8.bitsLeft() >= r9) goto L9;
        return -1;
    L9:
        long r02 = r8.readBitsToLong(r9);
        if (r02 == r2) goto L12;
        return r02;
    L12:
        if (r8.bitsLeft() >= r10) goto L14;
        return -1;
    L14:
        long r92 = r8.readBitsToLong(r10);
        long r03 = r02 + r92;
        if (r92 == r4) goto L17;
        return r03;
    L17:
        if (r8.bitsLeft() >= r11) goto L20;
        return -1;
    L20:
        return r03 + r8.readBitsToLong(r11);
    L5:
        r0 = false;
        goto L6
    }

    private static void skipMpegh3daDecoderConfig(ParsableBitArray r13, int r14, int r15) {
        int r3 = readEscapedIntValue(r13, 4, 8, 16) + 1;
        r13.skipBit();
        int r6 = 0;
    L3:
        if (r6 >= r3) goto L46;
        int r8 = r13.readBits(2);
        if (r8 != 0) goto L7;
        parseMpegh3daCoreConfig(r13);
        if (r15 <= 0) goto L45;
        skipSbrConfig(r13);
    L45:
        r6 = r6 + 1;
        goto L3
    L7:
        if (r8 == 1) goto L17;
        if (r8 != 3) goto L45;
        readEscapedIntValue(r13, 4, 8, 16);
        int r7 = readEscapedIntValue(r13, 4, 8, 16);
        if (r13.readBit() == false) goto L13;
        readEscapedIntValue(r13, 8, 16, 0);
    L13:
        r13.skipBit();
        if (r7 <= 0) goto L45;
        r13.skipBits(r7 * 8);
        goto L45
    L17:
        if (parseMpegh3daCoreConfig(r13) == false) goto L19;
        r13.skipBit();
    L19:
        if (r15 <= 0) goto L21;
        skipSbrConfig(r13);
        int r82 = r13.readBits(2);
    L22:
        if (r82 <= 0) goto L31;
        r13.skipBits(6);
        int r11 = r13.readBits(2);
        r13.skipBits(4);
        if (r13.readBit() == false) goto L26;
        r13.skipBits(5);
    L26:
        if (r82 == 2) goto L28;
        if (r82 == 3) goto L28;
    L29:
        if (r11 != 2) goto L31;
        r13.skipBit();
    L28:
        r13.skipBits(6);
    L31:
        int r83 = ((int) Math.floor(Math.log(r14 - 1) / Math.log(2.0d))) + 1;
        int r72 = r13.readBits(2);
        if (r72 <= 0) goto L37;
        if (r13.readBit() == false) goto L37;
        r13.skipBits(r83);
    L37:
        if (r13.readBit() == false) goto L39;
        r13.skipBits(r83);
    L39:
        if (r15 != 0) goto L45;
        if (r72 != 0) goto L45;
        r13.skipBit();
        goto L45
    L21:
        r82 = 0;
        goto L22
    }

    private static void skipMpegh3daFlexibleSpeakerConfig(ParsableBitArray r10, int r11) {
        boolean r0 = r10.readBit();
        int r1 = 5;
        if (r0 == false) goto L5;
        int r3 = 1;
    L7:
        if (r0 == false) goto L9;
        r1 = 7;
    L9:
        if (r0 == false) goto L11;
        int r02 = 8;
    L12:
        int r6 = 0;
    L13:
        if (r6 >= r11) goto L33;
        if (r10.readBit() == false) goto L18;
        r10.skipBits(7);
        int r7 = 0;
    L27:
        if (r7 == 0) goto L32;
        if (r7 == 180) goto L32;
        if (r10.readBit() == false) goto L32;
        r6 = r6 + 1;
    L32:
        r6 = r6 + 1;
        goto L13
    L18:
        if (r10.readBits(2) == 3) goto L20;
    L22:
        r7 = r10.readBits(r02) * r3;
        if (r7 == 0) goto L26;
        if (r7 == 180) goto L26;
        r10.skipBit();
    L26:
        r10.skipBit();
        goto L27
    L20:
        if ((r10.readBits(r1) * r3) == 0) goto L22;
        r10.skipBit();
        goto L22
    L33:
        return;
    L11:
        r02 = 6;
        goto L12
    L5:
        r3 = 5;
        goto L7
    }

    private static void skipSbrConfig(ParsableBitArray r2) {
        r2.skipBits(3);
        r2.skipBits(8);
        boolean r0 = r2.readBit();
        boolean r1 = r2.readBit();
        if (r0 == false) goto L5;
        r2.skipBits(5);
    L5:
        if (r1 == false) goto L8;
        r2.skipBits(6);
        return;
    }

    private static void skipSpeakerConfig3d(ParsableBitArray r5) {
        int r1 = r5.readBits(2);
        if (r1 != 0) goto L6;
        r5.skipBits(6);
        return;
    L6:
        int r2 = readEscapedIntValue(r5, 5, 8, 16) + 1;
        if (r1 != 1) goto L10;
        r5.skipBits(r2 * 7);
        return;
    L10:
        if (r1 != 2) goto L13;
        skipMpegh3daFlexibleSpeakerConfig(r5, r2);
        return;
    }
}
