package androidx.media3.extractor;

import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import java.nio.ByteBuffer;
import kotlin.UByte;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes4.dex */
public abstract class Ac4Util {
    private static final int[] SAMPLE_COUNT = null;

    /* renamed from: androidx.media3.extractor.Ac4Util$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Ac4Presentation {
        public int channelMode;
        public boolean hasBackChannels;
        public boolean isChannelCoded;
        public int level;
        public int numOfUmxObjects;
        public int topChannelPairs;
        public int version;

        private Ac4Presentation() {
            this.isChannelCoded = true;
            this.channelMode = -1;
            this.numOfUmxObjects = -1;
            this.hasBackChannels = true;
            this.topChannelPairs = 2;
            this.version = 1;
            this.level = 0;
        }

        public /* synthetic */ Ac4Presentation(AnonymousClass1 r1) {
            this();
        }
    }

    public static final class SyncFrameInfo {
        public final int bitstreamVersion;
        public final int channelCount;
        public final int frameSize;
        public final int sampleCount;
        public final int sampleRate;

        private SyncFrameInfo(int r1, int r2, int r3, int r4, int r5) {
            this.bitstreamVersion = r1;
            this.channelCount = r2;
            this.sampleRate = r3;
            this.frameSize = r4;
            this.sampleCount = r5;
        }

        public /* synthetic */ SyncFrameInfo(int r1, int r2, int r3, int r4, int r5, AnonymousClass1 r6) {
            this(r1, r2, r3, r4, r5);
        }
    }

    static {
        SAMPLE_COUNT = new int[]{CastStatusCodes.CANCELED, CastStatusCodes.AUTHENTICATION_FAILED, 1920, 1601, 1600, WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY, 1000, 960, 800, 800, 480, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, 2048};
    }

    private static String createCodecsString(int r0, int r1, int r2) {
        return Util.formatInvariant("ac-4.%02d.%02d.%02d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r1), Integer.valueOf(r2)});
    }

    public static void getAc4SampleHeader(int r2, ParsableByteArray r3) {
        r3.reset(7);
        byte[] r32 = r3.getData();
        r32[0] = -84;
        r32[1] = 64;
        r32[2] = -1;
        r32[3] = -1;
        r32[4] = (byte) ((r2 >> 16) & 255);
        r32[5] = (byte) ((r2 >> 8) & 255);
        r32[6] = (byte) (r2 & 255);
    }

    private static int getAdjustedChannelCount(int r2, boolean r3, int r4) {
        int r0 = getChannelCountFromChannelMode(r2);
        if (r2 != 11) goto L5;
    L12:
        if (r3 == true) goto L14;
        r0 = r0 - 2;
    L14:
        if (r4 == 0) goto L21;
        if (r4 == 1) goto L19;
        return r0;
    L19:
        return r0 - 2;
    L21:
        return r0 - 4;
    L5:
        if (r2 == 12) goto L12;
        if (r2 == 13) goto L12;
        if (r2 == 14) goto L12;
        return r0;
    }

    private static int getChannelCountFromChannelMode(int r0) {
        switch(r0) {
            case 0: goto L27;
            case 1: goto L25;
            case 2: goto L23;
            case 3: goto L21;
            case 4: goto L19;
            case 5: goto L17;
            case 6: goto L15;
            case 7: goto L17;
            case 8: goto L15;
            case 9: goto L17;
            case 10: goto L15;
            case 11: goto L13;
            case 12: goto L11;
            case 13: goto L9;
            case 14: goto L7;
            case 15: goto L5;
            default: goto L3;
        };
    L3:
        return -1;
    L5:
        return 24;
    L7:
        return 14;
    L9:
        return 13;
    L11:
        return 12;
    L13:
        return 11;
    L15:
        return 8;
    L17:
        return 7;
    L19:
        return 6;
    L21:
        return 5;
    L23:
        return 3;
    L25:
        return 2;
    L27:
        return 1;
    }

    public static Format parseAc4AnnexEFormat(ParsableByteArray r20, String r21, String r22, DrmInitData r23) {
        ParsableBitArray r0 = new ParsableBitArray();
        r0.reset(r20);
        int r1 = r0.bitsLeft();
        int r3 = r0.readBits(3);
        if (r3 > 1) goto L197;
        int r6 = r0.readBits(7);
        if (r0.readBit() == false) goto L7;
        int r7 = 48000;
    L8:
        r0.skipBits(4);
        int r9 = r0.readBits(9);
        if (r6 <= 1) goto L18;
        if (r3 == 0) goto L17;
        if (r0.readBit() == false) goto L18;
        r0.skipBits(16);
        if (r0.readBit() == false) goto L18;
        r0.skipBits(128);
        goto L18
    L17:
        throw ParserException.createForUnsupportedContainerFeature("Invalid AC-4 DSI version: " + r3);
    L18:
        if (r3 == 1) goto L20;
    L24:
        Ac4Presentation r11 = new Ac4Presentation(null);
        int r13 = 0;
    L26:
        if (r13 >= r9) goto L163;
        if (r3 == 0) goto L29;
        int r14 = r0.readBits(8);
        int r4 = r0.readBits(8);
        int r18 = 8;
        if (r4 != 255) goto L33;
        r4 = r4 + r0.readBits(16);
    L33:
        if (r14 <= 2) goto L35;
        r0.skipBits(r4 * 8);
        r13 = r13 + 1;
        goto L26
    L35:
        int r92 = (r1 - r0.bitsLeft()) / 8;
        int r12 = r0.readBits(5);
        if (r12 != 31) goto L38;
        boolean r10 = true;
    L39:
        int r5 = r14;
        int r142 = r12;
        boolean r122 = r10;
        int r102 = r92;
        boolean r93 = false;
    L40:
        r11.version = r5;
        if (r93 == true) goto L45;
        if (r122 == true) goto L45;
        if (r142 != 6) goto L45;
        boolean r2 = true;
    L132:
        if (r2 == false) goto L136;
        int r24 = r0.readBits(7);
        int r8 = 0;
    L134:
        if (r8 >= r24) goto L136;
        r0.skipBits(15);
        r8 = r8 + 1;
    L136:
        if (r5 > 0) goto L138;
    L149:
        int r94 = 8;
        r0.byteAlign();
        if (r3 != 1) goto L157;
        int r15 = ((r1 - r0.bitsLeft()) / 8) - r102;
        if (r4 < r15) goto L155;
        r0.skipBytes(r4 - r15);
        goto L157
    L155:
        throw ParserException.createForUnsupportedContainerFeature("pres_bytes is smaller than presentation bytes read.");
    L157:
        if (r11.isChannelCoded == false) goto L165;
        if (r11.channelMode != (-1)) goto L165;
        throw ParserException.createForUnsupportedContainerFeature("Can't determine channel mode of presentation " + r13);
    L165:
        if (r11.isChannelCoded == false) goto L167;
        int r143 = getAdjustedChannelCount(r11.channelMode, r11.hasBackChannels, r11.topChannelPairs);
    L191:
        if (r143 <= 0) goto L195;
        return new Format.Builder().setId(r21).setSampleMimeType("audio/ac4").setChannelCount(r143).setSampleRate(r7).setDrmInitData(r23).setLanguage(r22).setCodecs(createCodecsString(r6, r11.version, r11.level)).build();
    L195:
        throw ParserException.createForUnsupportedContainerFeature("Cannot determine channel count of presentation.");
    L167:
        int r02 = r11.numOfUmxObjects;
        int r16 = r11.level;
        if (r02 <= 0) goto L176;
        int r03 = r02 + 1;
        if (r16 == 4) goto L172;
    L174:
        r143 = r03;
        goto L191
    L172:
        if (r03 != 17) goto L174;
        r03 = 21;
        goto L174
    L176:
        if (r16 == 0) goto L190;
        if (r16 == 1) goto L189;
        int r152 = 2;
        if (r16 != 2) goto L181;
        r143 = r94;
        goto L191
    L181:
        if (r16 != 3) goto L183;
        r143 = 10;
        goto L191
    L183:
        if (r16 == 4) goto L186;
        Log.w("Ac4Util", "AC-4 level " + r11.level + " has not been defined.");
    L185:
        r143 = r152;
        goto L191
    L186:
        r143 = 12;
        goto L191
    L189:
        r143 = 6;
        goto L191
    L190:
        r152 = 2;
        goto L185
    L138:
        if (r0.readBit() == false) goto L145;
        if (skipDsiBitrate(r0) == true) goto L145;
        throw ParserException.createForUnsupportedContainerFeature("Can't parse bitrate DSI.");
    L145:
        if (r0.readBit() == false) goto L149;
        r0.byteAlign();
        r0.skipBytes(r0.readBits(16));
        int r25 = r0.readBits(5);
        int r123 = 0;
    L147:
        if (r123 >= r25) goto L149;
        r0.skipBits(3);
        r0.skipBits(8);
        r123 = r123 + 1;
    L45:
        r11.level = r0.readBits(3);
        if (r0.readBit() == false) goto L48;
        r0.skipBits(5);
    L48:
        r0.skipBits(2);
        int r26 = 1;
        if (r3 != 1) goto L55;
        if (r5 == 1) goto L54;
        if (r5 != 2) goto L55;
    L54:
        r0.skipBits(2);
    L55:
        r0.skipBits(5);
        r0.skipBits(10);
        if (r3 != 1) goto L88;
        if (r5 <= 0) goto L60;
        r11.isChannelCoded = r0.readBit();
    L60:
        if (r11.isChannelCoded == false) goto L75;
        if (r5 == 1) goto L64;
        int r153 = 2;
        if (r5 == 2) goto L64;
    L74:
        r0.skipBits(24);
        r26 = 1;
    L76:
        if (r5 == r26) goto L79;
        if (r5 != r153) goto L88;
    L79:
        if (r0.readBit() == false) goto L84;
        if (r0.readBit() == false) goto L84;
        r0.skipBits(r153);
    L84:
        if (r0.readBit() == false) goto L88;
        r0.skipBit();
        int r27 = r18;
        int r154 = r0.readBits(r27);
        int r82 = 0;
    L86:
        if (r82 >= r154) goto L88;
        r0.skipBits(r27);
        r82 = r82 + 1;
        r27 = 8;
    L64:
        int r28 = r0.readBits(5);
        if (r28 < 0) goto L69;
        if (r28 > 15) goto L69;
        r11.channelMode = r28;
    L69:
        if (r28 >= 11) goto L71;
    L73:
        r153 = 2;
        goto L74
    L71:
        if (r28 > 14) goto L73;
        r11.hasBackChannels = r0.readBit();
        r153 = 2;
        r11.topChannelPairs = r0.readBits(2);
        goto L74
    L75:
        r153 = 2;
    L88:
        if (r93 == true) goto L128;
        if (r122 == true) goto L128;
        r0.skipBit();
        if (r142 != 0) goto L94;
    L120:
        if (r5 != 0) goto L124;
        int r29 = 0;
    L122:
        if (r29 >= 2) goto L131;
        parseDsiSubstream(r0, r11);
        r29 = r29 + 1;
    L131:
        r0.skipBit();
        r2 = r0.readBit();
        goto L132
    L124:
        int r210 = 0;
    L126:
        if (r210 >= 2) goto L131;
        parseDsiSubstreamGroup(r0, r11);
        r210 = r210 + 1;
        goto L126
    L94:
        if (r142 == 1) goto L120;
        if (r142 == 2) goto L120;
        if (r142 != 3) goto L100;
    L112:
        if (r5 != 0) goto L116;
        int r211 = 0;
    L114:
        if (r211 >= 3) goto L131;
        parseDsiSubstream(r0, r11);
        r211 = r211 + 1;
        goto L114
    L116:
        int r212 = 0;
    L118:
        if (r212 >= 3) goto L131;
        parseDsiSubstreamGroup(r0, r11);
        r212 = r212 + 1;
        goto L118
    L100:
        if (r142 == 4) goto L112;
        if (r142 == 5) goto L106;
        int r83 = r0.readBits(7);
        int r213 = 0;
    L104:
        if (r213 >= r83) goto L131;
        r0.skipBits(8);
        r213 = r213 + 1;
        goto L104
    L106:
        if (r5 != 0) goto L108;
        parseDsiSubstream(r0, r11);
        goto L131
    L108:
        int r84 = r0.readBits(3);
        int r214 = 0;
    L110:
        if (r214 >= (r84 + 2)) goto L131;
        parseDsiSubstreamGroup(r0, r11);
        r214 = r214 + 1;
    L128:
        if (r5 != 0) goto L130;
        parseDsiSubstream(r0, r11);
        goto L131
    L130:
        parseDsiSubstreamGroup(r0, r11);
        goto L131
    L38:
        r10 = false;
        goto L39
    L29:
        r93 = r0.readBit();
        r18 = 8;
        r142 = r0.readBits(5);
        r5 = r0.readBits(5);
        r4 = 0;
        r102 = 0;
        r122 = false;
        goto L40
    L163:
        r94 = 8;
        goto L165
    L20:
        if (skipDsiBitrate(r0) == false) goto L23;
        r0.byteAlign();
        goto L24
    L23:
        throw ParserException.createForUnsupportedContainerFeature("Invalid AC-4 DSI bitrate.");
    L7:
        r7 = 44100;
        goto L8
    L197:
        throw ParserException.createForUnsupportedContainerFeature("Unsupported AC-4 DSI version: " + r3);
    }

    public static int parseAc4SyncframeAudioSampleCount(ByteBuffer r2) {
        byte[] r0 = new byte[16];
        int r1 = r2.position();
        r2.get(r0);
        r2.position(r1);
        return parseAc4SyncframeInfo(new ParsableBitArray(r0)).sampleCount;
    }

    public static SyncFrameInfo parseAc4SyncframeInfo(ParsableBitArray r11) {
        int r1 = r11.readBits(16);
        int r0 = r11.readBits(16);
        if (r0 != 65535) goto L5;
        r0 = r11.readBits(24);
        int r2 = 7;
    L6:
        int r02 = r0 + r2;
        if (r1 != 44097) goto L9;
        r02 = r02 + 2;
    L9:
        int r8 = r02;
        int r12 = r11.readBits(2);
        if (r12 != 3) goto L12;
        r12 = r12 + readVariableBits(r11, 2);
    L12:
        int r5 = r12;
        int r13 = r11.readBits(10);
        if (r11.readBit() == true) goto L15;
    L17:
        int r7 = 48000;
        if (r11.readBit() == false) goto L20;
        int r4 = 48000;
    L21:
        int r112 = r11.readBits(4);
        if (r7 == 44100) goto L24;
    L27:
        if (r7 != r4) goto L48;
        int[] r42 = SAMPLE_COUNT;
        if (r112 >= r42.length) goto L48;
        int r43 = r42[r112];
        int r14 = r13 % 5;
        if (r14 != 1) goto L33;
    L44:
        if (r112 != 3) goto L45;
    L40:
        int r113 = r43 + 1;
    L26:
        int r9 = r113;
    L50:
        return new SyncFrameInfo(r5, 2, r7, r8, r9, null);
    L45:
        if (r112 == 8) goto L40;
    L47:
        r9 = r43;
        goto L50
    L33:
        if (r14 == 2) goto L41;
        if (r14 == 3) goto L44;
        if (r14 != 4) goto L47;
        if (r112 == 3) goto L40;
        if (r112 == 8) goto L40;
        if (r112 != 11) goto L47;
    L41:
        if (r112 == 8) goto L40;
        if (r112 != 11) goto L47;
    L48:
        r113 = 0;
        goto L26
    L24:
        if (r112 != 13) goto L27;
        r113 = SAMPLE_COUNT[r112];
        goto L26
    L20:
        r4 = 48000;
        r7 = 44100;
        goto L21
    L15:
        if (r11.readBits(3) <= 0) goto L17;
        r11.skipBits(2);
        goto L17
    L5:
        r2 = 4;
        goto L6
    }

    public static int parseAc4SyncframeSize(byte[] r4, int r5) {
        int r1 = 7;
        if (r4.length >= 7) goto L6;
        return -1;
    L6:
        int r0 = ((r4[2] & UByte.MAX_VALUE) << 8) | (r4[3] & UByte.MAX_VALUE);
        if (r0 != 65535) goto L9;
        r0 = (((r4[4] & UByte.MAX_VALUE) << 16) | ((r4[5] & UByte.MAX_VALUE) << 8)) | (r4[6] & UByte.MAX_VALUE);
    L11:
        if (r5 != 44097) goto L14;
        r1 = r1 + 2;
    L14:
        return r0 + r1;
    L9:
        r1 = 4;
        goto L11
    }

    private static void parseDsiSubstream(ParsableBitArray r4, Ac4Presentation r5) {
        int r1 = r4.readBits(5);
        r4.skipBits(2);
        if (r4.readBit() == false) goto L6;
        r4.skipBits(5);
    L6:
        if (r1 < 7) goto L11;
        if (r1 > 10) goto L11;
        r4.skipBit();
    L11:
        if (r4.readBit() == false) goto L25;
        int r0 = r4.readBits(3);
        if (r5.channelMode != (-1)) goto L22;
        if (r1 < 0) goto L22;
        if (r1 > 15) goto L22;
        if (r0 != 0) goto L19;
    L20:
        r5.channelMode = r1;
        goto L22
    L19:
        if (r0 == 1) goto L20;
    L22:
        if (r4.readBit() == false) goto L26;
        skipDsiLanguage(r4);
        return;
    L26:
        return;
    }

    private static void parseDsiSubstreamGroup(ParsableBitArray r6, Ac4Presentation r7) {
        r6.skipBits(2);
        boolean r1 = r6.readBit();
        int r2 = r6.readBits(8);
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L18;
        r6.skipBits(2);
        if (r6.readBit() == false) goto L7;
        r6.skipBits(5);
    L7:
        if (r1 == false) goto L10;
        r6.skipBits(24);
    L16:
        r3 = r3 + 1;
        goto L3
    L10:
        if (r6.readBit() == true) goto L12;
    L15:
        r6.skipBits(4);
        goto L16
    L12:
        if (r6.readBit() == true) goto L14;
        r6.skipBits(4);
    L14:
        r7.numOfUmxObjects = r6.readBits(6) + 1;
        goto L15
    L18:
        if (r6.readBit() == false) goto L26;
        r6.skipBits(3);
        if (r6.readBit() == false) goto L27;
        skipDsiLanguage(r6);
        return;
    L27:
        return;
    }

    private static int readVariableBits(ParsableBitArray r2, int r3) {
        int r0 = 0;
    L3:
        int r02 = r0 + r2.readBits(r3);
        if (r2.readBit() == false) goto L5;
        r0 = (r02 + 1) << r3;
        goto L3
    L5:
        return r02;
    }

    private static boolean skipDsiBitrate(ParsableBitArray r2) {
        if (r2.bitsLeft() >= 66) goto L6;
        return false;
    L6:
        r2.skipBits(66);
        return true;
    }

    private static void skipDsiLanguage(ParsableBitArray r2) {
        int r0 = r2.readBits(6);
        if (r0 < 2) goto L9;
        if (r0 > 42) goto L9;
        r2.skipBits(r0 * 8);
        return;
    L9:
        throw ParserException.createForUnsupportedContainerFeature(String.format("Invalid language tag bytes number: %d. Must be between 2 and 42.", new Object[]{Integer.valueOf(r0)}));
    }
}
