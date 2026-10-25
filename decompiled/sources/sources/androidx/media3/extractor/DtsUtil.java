package androidx.media3.extractor;

import androidx.car.app.media.CarAudioRecord;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.Util;
import com.android.dx.io.Opcodes;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.UByte;

/* loaded from: classes4.dex */
public abstract class DtsUtil {
    private static final int[] CHANNELS_BY_AMODE = null;
    private static final int[] SAMPLE_RATE_BY_INDEX = null;
    private static final int[] SAMPLE_RATE_BY_SFREQ = null;
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE = null;
    private static final int[] UHD_AUDIO_CHUNK_ID_LENGTH_TABLE = null;
    private static final int[] UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE = null;
    private static final int[] UHD_FTOC_PAYLOAD_LENGTH_TABLE = null;
    private static final int[] UHD_HEADER_SIZE_LENGTH_TABLE = null;
    private static final int[] UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE = null;

    /* renamed from: androidx.media3.extractor.DtsUtil$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class DtsHeader {
        public final int bitrate;
        public final int channelCount;
        public final long frameDurationUs;
        public final int frameSize;
        public final String mimeType;
        public final int sampleRate;

        private DtsHeader(String r1, int r2, int r3, int r4, long r5, int r7) {
            this.mimeType = r1;
            this.channelCount = r2;
            this.sampleRate = r3;
            this.frameSize = r4;
            this.frameDurationUs = r5;
            this.bitrate = r7;
        }

        public /* synthetic */ DtsHeader(String r1, int r2, int r3, int r4, long r5, int r7, AnonymousClass1 r8) {
            this(r1, r2, r3, r4, r5, r7);
        }
    }

    static {
        CHANNELS_BY_AMODE = new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
        SAMPLE_RATE_BY_SFREQ = new int[]{-1, 8000, CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
        TWICE_BITRATE_KBPS_BY_RATE = new int[]{64, 112, 128, 192, Opcodes.SHL_INT_LIT8, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};
        SAMPLE_RATE_BY_INDEX = new int[]{8000, CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE, 32000, 64000, 128000, 22050, 44100, 88200, 176400, 352800, 12000, 24000, 48000, 96000, 192000, 384000};
        UHD_FTOC_PAYLOAD_LENGTH_TABLE = new int[]{5, 8, 10, 12};
        UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE = new int[]{6, 9, 12, 15};
        UHD_AUDIO_CHUNK_ID_LENGTH_TABLE = new int[]{2, 4, 6, 8};
        UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE = new int[]{9, 11, 13, 16};
        UHD_HEADER_SIZE_LENGTH_TABLE = new int[]{5, 8, 10, 12};
    }

    private static void checkCrc(byte[] r3, int r4) {
        int r0 = r4 - 2;
        if (((r3[r4 - 1] & UByte.MAX_VALUE) | ((r3[r0] << 8) & 65535)) != Util.crc16(r3, 0, r0, 65535)) goto L6;
        return;
    L6:
        throw ParserException.createForMalformedContainer("CRC check failed", null);
    }

    public static int getDtsFrameSize(byte[] r7) {
        boolean r0 = false;
        byte r1 = r7[0];
        if (r1 != (-2)) goto L5;
        int r12 = ((r7[4] & 3) << 12) | ((r7[7] & UByte.MAX_VALUE) << 4);
        byte r72 = r7[6];
    L9:
        int r73 = (((r72 & 240) >> 4) | r12) + 1;
    L14:
        if (r0 == true) goto L16;
        return r73;
    L16:
        return (r73 * 16) / 14;
    L5:
        if (r1 != (-1)) goto L7;
        int r02 = ((r7[7] & 3) << 12) | ((r7[6] & UByte.MAX_VALUE) << 4);
        byte r74 = r7[9];
    L11:
        r73 = (((r74 & 60) >> 2) | r02) + 1;
        r0 = true;
        goto L14
    L7:
        if (r1 == 31) goto L10;
        r12 = ((r7[5] & 3) << 12) | ((r7[6] & UByte.MAX_VALUE) << 4);
        r72 = r7[7];
        goto L9
    L10:
        r02 = ((r7[6] & 3) << 12) | ((r7[7] & UByte.MAX_VALUE) << 4);
        r74 = r7[8];
        goto L11
    }

    public static int getFrameType(int r1) {
        if (r1 != 2147385345) goto L5;
        return 1;
    L5:
        if (r1 != (-25230976)) goto L7;
        return 1;
    L7:
        if (r1 != 536864768) goto L9;
        return 1;
    L9:
        if (r1 != (-14745368)) goto L12;
        return 1;
    L12:
        if (r1 != 1683496997) goto L14;
        return 2;
    L14:
        if (r1 != 622876772) goto L17;
        return 2;
    L17:
        if (r1 != 1078008818) goto L19;
        return 3;
    L19:
        if (r1 != (-233094848)) goto L22;
        return 3;
    L22:
        if (r1 != 1908687592) goto L24;
        return 4;
    L24:
        if (r1 == (-398277519)) goto L36;
        return 0;
    L36:
        return 4;
    }

    private static ParsableBitArray getNormalizedFrame(byte[] r5) {
        byte r1 = r5[0];
        if (r1 == Byte.MAX_VALUE) goto L26;
        if (r1 == 100) goto L26;
        if (r1 == 64) goto L26;
        if (r1 == 113) goto L26;
        byte[] r52 = Arrays.copyOf(r5, r5.length);
        if (isLittleEndianFrameHeader(r52) == false) goto L17;
        int r12 = 0;
    L15:
        if (r12 >= (r52.length - 1)) goto L17;
        byte r2 = r52[r12];
        int r3 = r12 + 1;
        r52[r12] = r52[r3];
        r52[r3] = r2;
        r12 = r12 + 2;
    L17:
        ParsableBitArray r13 = new ParsableBitArray(r52);
        if (r52[0] != 31) goto L23;
        ParsableBitArray r0 = new ParsableBitArray(r52);
    L21:
        if (r0.bitsLeft() < 16) goto L23;
        r0.skipBits(2);
        r13.putInt(r0.readBits(14), 14);
    L23:
        r13.reset(r52);
        return r13;
    L26:
        return new ParsableBitArray(r5);
    }

    private static boolean isLittleEndianFrameHeader(byte[] r2) {
        byte r22 = r2[0];
        if (r22 != (-2)) goto L5;
        return true;
    L5:
        if (r22 != (-1)) goto L7;
        return true;
    L7:
        if (r22 != 37) goto L9;
        return true;
    L9:
        if (r22 != (-14)) goto L11;
        return true;
    L11:
        if (r22 == (-24)) goto L19;
        return false;
    L19:
        return true;
    }

    public static int parseDtsAudioSampleCount(ByteBuffer r3) {
        if (r3.getInt(0) != (-233094848)) goto L5;
        return 1024;
    L5:
        if (r3.getInt(0) != (-398277519)) goto L8;
        return 1024;
    L8:
        if (r3.getInt(0) != 622876772) goto L11;
        return 4096;
    L11:
        int r0 = r3.position();
        byte r1 = r3.get(r0);
        if (r1 != (-2)) goto L14;
        int r12 = (r3.get(r0 + 5) & 1) << 6;
        byte r32 = r3.get(r0 + 4);
    L18:
        int r33 = r32 & 252;
    L25:
        return (((r33 >> 2) | r12) + 1) * 32;
    L14:
        if (r1 != (-1)) goto L16;
        r12 = (r3.get(r0 + 4) & 7) << 4;
        byte r34 = r3.get(r0 + 7);
    L21:
        r33 = r34 & 60;
        goto L25
    L16:
        if (r1 == 31) goto L20;
        r12 = (r3.get(r0 + 4) & 1) << 6;
        r32 = r3.get(r0 + 5);
        goto L18
    L20:
        r12 = (r3.get(r0 + 5) & 7) << 4;
        r34 = r3.get(r0 + 6);
        goto L21
    }

    public static int parseDtsAudioSampleCount(byte[] r6) {
        byte r0 = r6[0];
        if (r0 != (-2)) goto L5;
        int r02 = (r6[5] & 1) << 6;
        byte r62 = r6[4];
    L9:
        int r63 = r62 & 252;
    L16:
        return (((r63 >> 2) | r02) + 1) * 32;
    L5:
        if (r0 != (-1)) goto L7;
        r02 = (r6[4] & 7) << 4;
        byte r64 = r6[7];
    L12:
        r63 = r64 & 60;
        goto L16
    L7:
        if (r0 == 31) goto L11;
        r02 = (r6[4] & 1) << 6;
        r62 = r6[5];
        goto L9
    L11:
        r02 = (r6[5] & 7) << 4;
        r64 = r6[6];
        goto L12
    }

    public static Format parseDtsFormat(byte[] r6, String r7, String r8, int r9, String r10, DrmInitData r11) {
        ParsableBitArray r62 = getNormalizedFrame(r6);
        r62.skipBits(60);
        int r0 = r62.readBits(6);
        int r02 = CHANNELS_BY_AMODE[r0];
        int r1 = r62.readBits(4);
        int r12 = SAMPLE_RATE_BY_SFREQ[r1];
        int r2 = r62.readBits(5);
        int[] r3 = TWICE_BITRATE_KBPS_BY_RATE;
        if (r2 < r3.length) goto L5;
        int r22 = -1;
    L6:
        r62.skipBits(10);
        if (r62.readBits(2) <= 0) goto L9;
        int r63 = 1;
    L10:
        int r03 = r02 + r63;
        return new Format.Builder().setId(r7).setContainerMimeType(r10).setSampleMimeType("audio/vnd.dts").setAverageBitrate(r22).setChannelCount(r03).setSampleRate(r12).setDrmInitData(r11).setLanguage(r8).setRoleFlags(r9).build();
    L9:
        r63 = 0;
        goto L10
    L5:
        r22 = (r3[r2] * 1000) / 2;
        goto L6
    }

    public static DtsHeader parseDtsHdHeader(byte[] r17) {
        ParsableBitArray r0 = getNormalizedFrame(r17);
        r0.skipBits(40);
        int r2 = r0.readBits(2);
        if (r0.readBit() == true) goto L5;
        int r3 = 16;
        int r6 = 8;
    L6:
        r0.skipBits(r6);
        int r12 = r0.readBits(r3) + 1;
        boolean r62 = r0.readBit();
        int r8 = -1;
        int r9 = 0;
        if (r62 == false) goto L28;
        int r10 = r0.readBits(2);
        int r13 = (r0.readBits(3) + 1) * 512;
        if (r0.readBit() == false) goto L11;
        r0.skipBits(36);
    L11:
        int r14 = r0.readBits(3) + 1;
        int r11 = r0.readBits(3) + 1;
        if (r14 != 1) goto L27;
        if (r11 != 1) goto L27;
        int r22 = r2 + 1;
        int r112 = r0.readBits(r22);
        int r142 = 0;
    L15:
        if (r142 >= r22) goto L21;
        if (((r112 >> r142) & 1) != 1) goto L19;
        r0.skipBits(8);
    L19:
        r142 = r142 + 1;
        goto L15
    L21:
        if (r0.readBit() == false) goto L25;
        r0.skipBits(2);
        int r23 = (r0.readBits(2) + 1) << 2;
        int r113 = r0.readBits(2) + 1;
    L23:
        if (r9 >= r113) goto L25;
        r0.skipBits(r23);
        r9 = r9 + 1;
    L25:
        r9 = r13;
    L29:
        r0.skipBits(r3);
        r0.skipBits(12);
        if (r62 == true) goto L32;
        int r24 = -2147483647;
    L41:
        int r114 = r24;
        if (r62 == false) goto L54;
        if (r10 == 0) goto L51;
        if (r10 == 1) goto L50;
        if (r10 != 2) goto L49;
        int r02 = 48000;
    L52:
        long r03 = Util.scaleLargeTimestamp(r9, 1000000, r02);
    L56:
        return new DtsHeader("audio/vnd.dts.hd;profile=lbr", r8, r114, r12, r03, 0, null);
    L49:
        throw ParserException.createForMalformedContainer("Unsupported reference clock code in DTS HD header: " + r10, null);
    L50:
        r02 = 44100;
        goto L52
    L51:
        r02 = 32000;
        goto L52
    L54:
        r03 = -9223372036854775807L;
        goto L56
    L32:
        if (r0.readBit() == false) goto L35;
        r0.skipBits(4);
    L35:
        if (r0.readBit() == false) goto L38;
        r0.skipBits(24);
    L38:
        if (r0.readBit() == false) goto L40;
        r0.skipBytes(r0.readBits(10) + 1);
    L40:
        r0.skipBits(5);
        r24 = SAMPLE_RATE_BY_INDEX[r0.readBits(4)];
        r8 = r0.readBits(8) + 1;
    L27:
        throw ParserException.createForUnsupportedContainerFeature("Multiple audio presentations or assets not supported");
    L28:
        r10 = -1;
        goto L29
    L5:
        r3 = 20;
        r6 = 12;
        goto L6
    }

    public static int parseDtsHdHeaderSize(byte[] r1) {
        ParsableBitArray r12 = getNormalizedFrame(r1);
        r12.skipBits(42);
        if (r12.readBit() == false) goto L5;
        int r0 = 12;
    L7:
        return r12.readBits(r0) + 1;
    L5:
        r0 = 8;
        goto L7
    }

    public static DtsHeader parseDtsUhdHeader(byte[] r17, AtomicInteger r18) {
        ParsableBitArray r0 = getNormalizedFrame(r17);
        int r3 = 0;
        if (r0.readBits(32) != 1078008818) goto L5;
        int r1 = 1;
    L6:
        int r2 = parseUnsignedVarInt(r0, UHD_FTOC_PAYLOAD_LENGTH_TABLE, true) + 1;
        if (r1 != 0) goto L9;
        int r5 = -2147483647;
        long r6 = -9223372036854775807L;
    L32:
        int r11 = r5;
        long r13 = r6;
        int r52 = 0;
        int r62 = 0;
    L37:
        if (r52 >= r1) goto L39;
        r62 = r62 + parseUnsignedVarInt(r0, UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE, true);
        r52 = r52 + 1;
        goto L37
    L39:
        if (r1 == 0) goto L41;
        AtomicInteger r53 = r18;
        r53.set(parseUnsignedVarInt(r0, UHD_AUDIO_CHUNK_ID_LENGTH_TABLE, true));
    L43:
        if (r53.get() == 0) goto L46;
        r3 = parseUnsignedVarInt(r0, UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE, true);
    L46:
        return new DtsHeader("audio/vnd.dts.uhd;profile=p2", 2, r11, r2 + (r62 + r3), r13, 0, null);
    L41:
        r53 = r18;
        goto L43
    L9:
        if (r0.readBit() == false) goto L34;
        checkCrc(r17, r2);
        int r63 = r0.readBits(2);
        if (r63 == 0) goto L18;
        if (r63 == 1) goto L17;
        if (r63 != 2) goto L16;
        int r64 = 384;
    L19:
        int r65 = r64 * (r0.readBits(3) + 1);
        int r8 = r0.readBits(2);
        if (r8 == 0) goto L27;
        if (r8 == 1) goto L26;
        if (r8 != 2) goto L25;
        int r7 = 48000;
    L29:
        if (r0.readBit() == false) goto L31;
        r0.skipBits(36);
    L31:
        r5 = (1 << r0.readBits(2)) * r7;
        r6 = Util.scaleLargeTimestamp(r65, 1000000, r7);
        goto L32
    L25:
        throw ParserException.createForMalformedContainer("Unsupported clock rate index in DTS UHD header: " + r8, null);
    L26:
        r7 = 44100;
        goto L29
    L27:
        r7 = 32000;
        goto L29
    L16:
        throw ParserException.createForMalformedContainer("Unsupported base duration index in DTS UHD header: " + r63, null);
    L17:
        r64 = 480;
        goto L19
    L18:
        r64 = 512;
        goto L19
    L34:
        throw ParserException.createForUnsupportedContainerFeature("Only supports full channel mask-based audio presentation");
    L5:
        r1 = 0;
        goto L6
    }

    public static int parseDtsUhdHeaderSize(byte[] r2) {
        ParsableBitArray r22 = getNormalizedFrame(r2);
        r22.skipBits(32);
        return parseUnsignedVarInt(r22, UHD_HEADER_SIZE_LENGTH_TABLE, true) + 1;
    }

    private static int parseUnsignedVarInt(ParsableBitArray r4, int[] r5, boolean r6) {
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= 3) goto L8;
        if (r4.readBit() == false) goto L8;
        r2 = r2 + 1;
        r1 = r1 + 1;
    L8:
        if (r6 == false) goto L14;
        int r62 = 0;
    L10:
        if (r0 >= r2) goto L12;
        r62 = r62 + (1 << r5[r0]);
        r0 = r0 + 1;
        goto L10
    L12:
        r0 = r62;
    L14:
        return r0 + r4.readBits(r5[r2]);
    }
}
