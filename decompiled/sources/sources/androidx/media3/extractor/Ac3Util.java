package androidx.media3.extractor;

import androidx.car.app.media.CarAudioRecord;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.android.dx.io.Opcodes;
import java.nio.ByteBuffer;
import kotlin.UByte;

/* loaded from: classes4.dex */
public abstract class Ac3Util {
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = null;
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = null;
    private static final int[] CHANNEL_COUNT_BY_ACMOD = null;
    private static final int[] SAMPLE_RATE_BY_FSCOD = null;
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = null;
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = null;

    /* renamed from: androidx.media3.extractor.Ac3Util$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class SyncFrameInfo {
        public final int bitrate;
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        private SyncFrameInfo(String r1, int r2, int r3, int r4, int r5, int r6, int r7) {
            this.mimeType = r1;
            this.streamType = r2;
            this.channelCount = r3;
            this.sampleRate = r4;
            this.frameSize = r5;
            this.sampleCount = r6;
            this.bitrate = r7;
        }

        public /* synthetic */ SyncFrameInfo(String r1, int r2, int r3, int r4, int r5, int r6, int r7, AnonymousClass1 r8) {
            this(r1, r2, r3, r4, r5, r6, r7);
        }
    }

    static {
        BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = new int[]{1, 2, 3, 6};
        SAMPLE_RATE_BY_FSCOD = new int[]{48000, 44100, 32000};
        SAMPLE_RATE_BY_FSCOD2 = new int[]{24000, 22050, CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE};
        CHANNEL_COUNT_BY_ACMOD = new int[]{2, 1, 2, 3, 3, 4, 4, 5};
        BITRATE_BY_HALF_FRMSIZECOD = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, Opcodes.SHL_INT_LIT8, 256, 320, 384, 448, 512, 576, 640};
        SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = new int[]{69, 87, 104, 121, 139, 174, Opcodes.ADD_INT_LIT16, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};
    }

    private static int calculateEac3Bitrate(int r0, int r1, int r2) {
        return (r0 * r1) / (r2 * 32);
    }

    public static int findTrueHdSyncframeOffset(ByteBuffer r5) {
        int r0 = r5.position();
        int r1 = r5.limit() - 10;
        int r2 = r0;
    L3:
        if (r2 > r1) goto L9;
        if ((Util.getBigEndianInt(r5, r2 + 4) & (-2)) == (-126718022)) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        return r2 - r0;
    L9:
        return -1;
    }

    private static int getAc3SyncframeSize(int r4, int r5) {
        int r0 = r5 / 2;
        if (r4 < 0) goto L20;
        int[] r1 = SAMPLE_RATE_BY_FSCOD;
        if (r4 >= r1.length) goto L22;
        if (r5 < 0) goto L23;
        int[] r2 = SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1;
        if (r0 >= r2.length) goto L24;
        int r42 = r1[r4];
        if (r42 == 44100) goto L13;
        int r52 = BITRATE_BY_HALF_FRMSIZECOD[r0];
        if (r42 != 32000) goto L19;
        return r52 * 6;
    L19:
        return r52 * 4;
    L13:
        return (r2[r0] + (r5 % 2)) * 2;
    L24:
        return -1;
    L23:
        return -1;
    L22:
        return -1;
    L20:
        return -1;
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray r5, String r6, String r7, DrmInitData r8) {
        ParsableBitArray r0 = new ParsableBitArray();
        r0.reset(r5);
        int r1 = r0.readBits(2);
        int r12 = SAMPLE_RATE_BY_FSCOD[r1];
        r0.skipBits(8);
        int r2 = CHANNEL_COUNT_BY_ACMOD[r0.readBits(3)];
        if (r0.readBits(1) == 0) goto L5;
        r2 = r2 + 1;
    L5:
        int r3 = r0.readBits(5);
        int r32 = BITRATE_BY_HALF_FRMSIZECOD[r3] * 1000;
        r0.byteAlign();
        r5.setPosition(r0.getBytePosition());
        return new Format.Builder().setId(r6).setSampleMimeType("audio/ac3").setChannelCount(r2).setSampleRate(r12).setDrmInitData(r8).setLanguage(r7).setAverageBitrate(r32).setPeakBitrate(r32).build();
    }

    public static int parseAc3SyncframeAudioSampleCount(ByteBuffer r3) {
        int r1 = 3;
        if (((r3.get(r3.position() + 5) & 248) >> 3) > 10) goto L5;
        return 1536;
    L5:
        if (((r3.get(r3.position() + 4) & 192) >> 6) == 3) goto L9;
        r1 = (r3.get(r3.position() + 4) & 48) >> 4;
    L9:
        return BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[r1] * 256;
    }

    public static SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray r30) {
        int r1 = r30.getPosition();
        r30.skipBits(40);
        if (r30.readBits(5) <= 10) goto L5;
        boolean r3 = true;
    L6:
        r30.setPosition(r1);
        int r12 = -1;
        if (r3 == false) goto L159;
        r30.skipBits(16);
        int r11 = r30.readBits(2);
        if (r11 == 0) goto L15;
        if (r11 == 1) goto L14;
        if (r11 != 2) goto L16;
        r12 = 2;
    L16:
        r30.skipBits(3);
        int r112 = (r30.readBits(11) + 1) * 2;
        int r122 = r30.readBits(2);
        if (r122 != 3) goto L19;
        int r13 = SAMPLE_RATE_BY_FSCOD2[r30.readBits(2)];
        int r14 = 3;
        int r15 = 6;
    L20:
        int r4 = r15 * 256;
        int r16 = calculateEac3Bitrate(r112, r13, r15);
        int r8 = r30.readBits(3);
        boolean r18 = r30.readBit();
        int r19 = CHANNEL_COUNT_BY_ACMOD[r8] + (r18 ? 1 : 0);
        r30.skipBits(10);
        if (r30.readBit() == false) goto L23;
        r30.skipBits(8);
    L23:
        if (r8 != 0) goto L27;
        r30.skipBits(5);
        if (r30.readBit() == false) goto L27;
        r30.skipBits(8);
    L27:
        if (r12 != 1) goto L32;
        if (r30.readBit() == false) goto L32;
        r30.skipBits(16);
    L32:
        if (r30.readBit() == false) goto L123;
        if (r8 <= 2) goto L36;
        r30.skipBits(2);
    L36:
        if ((r8 & 1) == 0) goto L39;
        if (r8 <= 2) goto L39;
        int r32 = 6;
        r30.skipBits(6);
    L41:
        if ((r8 & 4) == 0) goto L43;
        r30.skipBits(r32);
    L43:
        if (r18 == true) goto L45;
    L47:
        if (r12 != 0) goto L123;
        if (r30.readBit() == false) goto L51;
        int r33 = 6;
        r30.skipBits(6);
    L52:
        if (r8 != 0) goto L57;
        if (r30.readBit() == false) goto L57;
        r30.skipBits(r33);
    L57:
        if (r30.readBit() == false) goto L59;
        r30.skipBits(r33);
    L59:
        int r34 = r30.readBits(2);
        if (r34 != 1) goto L62;
        r30.skipBits(5);
    L104:
        if (r8 >= 2) goto L113;
        if (r30.readBit() == false) goto L108;
        r30.skipBits(14);
    L108:
        if (r8 != 0) goto L113;
        if (r30.readBit() == false) goto L113;
        r30.skipBits(14);
    L113:
        if (r30.readBit() == false) goto L123;
        if (r14 != 0) goto L116;
        r30.skipBits(5);
        goto L123
    L116:
        int r35 = 0;
    L117:
        if (r35 >= r15) goto L123;
        if (r30.readBit() == false) goto L121;
        r30.skipBits(5);
    L121:
        r35 = r35 + 1;
        goto L117
    L62:
        if (r34 != 2) goto L64;
        r30.skipBits(12);
        goto L104
    L64:
        if (r34 != 3) goto L104;
        int r36 = r30.readBits(5);
        if (r30.readBit() == false) goto L97;
        r30.skipBits(5);
        if (r30.readBit() == false) goto L71;
        r30.skipBits(4);
    L71:
        if (r30.readBit() == false) goto L74;
        r30.skipBits(4);
    L74:
        if (r30.readBit() == false) goto L77;
        r30.skipBits(4);
    L77:
        if (r30.readBit() == false) goto L80;
        r30.skipBits(4);
    L80:
        if (r30.readBit() == false) goto L83;
        r30.skipBits(4);
    L83:
        if (r30.readBit() == false) goto L86;
        r30.skipBits(4);
    L86:
        if (r30.readBit() == false) goto L89;
        r30.skipBits(4);
    L89:
        if (r30.readBit() == false) goto L97;
        if (r30.readBit() == false) goto L94;
        r30.skipBits(4);
    L94:
        if (r30.readBit() == false) goto L97;
        r30.skipBits(4);
    L97:
        if (r30.readBit() == false) goto L103;
        r30.skipBits(5);
        if (r30.readBit() == false) goto L103;
        r30.skipBits(7);
        if (r30.readBit() == false) goto L103;
        r30.skipBits(8);
    L103:
        r30.skipBits((r36 + 2) * 8);
        r30.byteAlign();
        goto L104
    L51:
        r33 = 6;
        goto L52
    L45:
        if (r30.readBit() == false) goto L47;
        r30.skipBits(5);
    L39:
        r32 = 6;
    L123:
        if (r30.readBit() == false) goto L139;
        r30.skipBits(5);
        if (r8 != 2) goto L128;
        r30.skipBits(4);
    L128:
        if (r8 < 6) goto L131;
        r30.skipBits(2);
    L131:
        if (r30.readBit() == false) goto L133;
        r30.skipBits(8);
    L133:
        if (r8 == 0) goto L135;
    L137:
        if (r122 >= 3) goto L139;
        r30.skipBit();
        goto L139
    L135:
        if (r30.readBit() == false) goto L137;
        r30.skipBits(8);
    L139:
        if (r12 != 0) goto L142;
        if (r14 == 3) goto L142;
        r30.skipBit();
    L142:
        if (r12 != 2) goto L147;
        if (r14 != 3) goto L145;
    L146:
        int r37 = 6;
        r30.skipBits(6);
    L150:
        if (r30.readBit() == true) goto L152;
    L156:
        String r0 = "audio/eac3";
    L157:
        String r21 = r0;
        int r27 = r16;
    L180:
        return new SyncFrameInfo(r21, r12, r19, r13, r112, r4, r27, null);
    L152:
        if (r30.readBits(r37) != 1) goto L156;
        if (r30.readBits(8) != 1) goto L156;
        r0 = "audio/eac3-joc";
        goto L157
    L145:
        if (r30.readBit() == true) goto L146;
    L147:
        r37 = 6;
        goto L150
    L19:
        int r132 = r30.readBits(2);
        int r142 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[r132];
        r14 = r132;
        r13 = SAMPLE_RATE_BY_FSCOD[r122];
        r15 = r142;
        goto L20
    L14:
        r12 = 1;
        goto L16
    L15:
        r12 = 0;
        goto L16
    L159:
        r30.skipBits(32);
        int r2 = r30.readBits(2);
        if (r2 != 3) goto L163;
        String r38 = null;
    L164:
        int r42 = r30.readBits(6);
        int r5 = BITRATE_BY_HALF_FRMSIZECOD[r42 / 2] * 1000;
        r112 = getAc3SyncframeSize(r2, r42);
        r30.skipBits(8);
        int r43 = r30.readBits(3);
        if ((r43 & 1) == 0) goto L170;
        if (r43 == 1) goto L170;
        r30.skipBits(2);
    L170:
        if ((r43 & 4) == 0) goto L172;
        r30.skipBits(2);
    L172:
        if (r43 != 2) goto L174;
        r30.skipBits(2);
    L174:
        int[] r6 = SAMPLE_RATE_BY_FSCOD;
        if (r2 >= r6.length) goto L177;
        r13 = r6[r2];
    L178:
        r19 = CHANNEL_COUNT_BY_ACMOD[r43] + (r30.readBit() ? 1 : 0);
        r4 = 1536;
        r21 = r38;
        r27 = r5;
        goto L180
    L177:
        r13 = -1;
        goto L178
    L163:
        r38 = "audio/ac3";
        goto L164
    L5:
        r3 = false;
        goto L6
    }

    public static int parseAc3SyncframeSize(byte[] r4) {
        if (r4.length >= 6) goto L7;
        return -1;
    L7:
        if (((r4[5] & 248) >> 3) <= 10) goto L10;
        int r1 = (r4[2] & 7) << 8;
        return (((r4[3] & UByte.MAX_VALUE) | r1) + 1) * 2;
    L10:
        byte r42 = r4[4];
        return getAc3SyncframeSize((r42 & 192) >> 6, r42 & 63);
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray r7, String r8, String r9, DrmInitData r10) {
        ParsableBitArray r0 = new ParsableBitArray();
        r0.reset(r7);
        int r1 = r0.readBits(13) * 1000;
        r0.skipBits(3);
        int r3 = r0.readBits(2);
        int r32 = SAMPLE_RATE_BY_FSCOD[r3];
        r0.skipBits(10);
        int r4 = CHANNEL_COUNT_BY_ACMOD[r0.readBits(3)];
        if (r0.readBits(1) == 0) goto L5;
        r4 = r4 + 1;
    L5:
        r0.skipBits(3);
        int r2 = r0.readBits(4);
        r0.skipBits(1);
        if (r2 <= 0) goto L12;
        r0.skipBits(6);
        if (r0.readBits(1) == 0) goto L10;
        r4 = r4 + 2;
    L10:
        r0.skipBits(1);
    L12:
        if (r0.bitsLeft() <= 7) goto L16;
        r0.skipBits(7);
        if (r0.readBits(1) == 0) goto L16;
        String r22 = "audio/eac3-joc";
    L17:
        r0.byteAlign();
        r7.setPosition(r0.getBytePosition());
        return new Format.Builder().setId(r8).setSampleMimeType(r22).setChannelCount(r4).setSampleRate(r32).setDrmInitData(r10).setLanguage(r9).setPeakBitrate(r1).build();
    L16:
        r22 = "audio/eac3";
        goto L17
    }

    public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer r2, int r3) {
        if ((r2.get((r2.position() + r3) + 7) & UByte.MAX_VALUE) != 187) goto L5;
        boolean r0 = true;
    L6:
        int r1 = r2.position() + r3;
        if (r0 == false) goto L9;
        int r32 = 9;
    L11:
        return 40 << ((r2.get(r1 + r32) >> 4) & 7);
    L9:
        r32 = 8;
        goto L11
    L5:
        r0 = false;
        goto L6
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] r6) {
        boolean r3 = false;
        if (r6[4] == (-8)) goto L5;
    L19:
        return 0;
    L5:
        if (r6[5] != 114) goto L19;
        if (r6[6] != 111) goto L19;
        byte r2 = r6[7];
        if ((r2 & 254) != 186) goto L19;
        if ((r2 & UByte.MAX_VALUE) != 187) goto L14;
        r3 = true;
    L14:
        if (r3 == false) goto L16;
        char r22 = '\t';
    L18:
        return 40 << ((r6[r22] >> 4) & 7);
    L16:
        r22 = '\b';
        goto L18
    }
}
