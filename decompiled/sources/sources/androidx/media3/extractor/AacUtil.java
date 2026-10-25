package androidx.media3.extractor;

import androidx.car.app.media.CarAudioRecord;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class AacUtil {
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = null;
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = null;

    /* renamed from: androidx.media3.extractor.AacUtil$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Config {
        public final int channelCount;
        public final String codecs;
        public final int sampleRateHz;

        private Config(int r1, int r2, String r3) {
            this.sampleRateHz = r1;
            this.channelCount = r2;
            this.codecs = r3;
        }

        public /* synthetic */ Config(int r1, int r2, String r3, AnonymousClass1 r4) {
            this(r1, r2, r3);
        }
    }

    static {
        AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = new int[]{96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE, 12000, 11025, 8000, 7350};
        AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = new int[]{0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};
    }

    public static byte[] buildAudioSpecificConfig(int r1, int r2, int r3) {
        return new byte[]{(byte) (((r1 << 3) & 248) | ((r2 >> 1) & 7)), (byte) (((r2 << 7) & 128) | ((r3 << 3) & 120))};
    }

    private static int getAudioObjectType(ParsableBitArray r2) {
        int r0 = r2.readBits(5);
        if (r0 == 31) goto L5;
        return r0;
    L5:
        return r2.readBits(6) + 32;
    }

    private static int getSamplingFrequency(ParsableBitArray r3) {
        int r0 = r3.readBits(4);
        if (r0 != 15) goto L11;
        if (r3.bitsLeft() < 24) goto L9;
        return r3.readBits(24);
    L9:
        throw ParserException.createForMalformedContainer("AAC header insufficient data", null);
    L11:
        if (r0 >= 13) goto L15;
        return AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[r0];
    L15:
        throw ParserException.createForMalformedContainer("AAC header wrong Sampling Frequency Index", null);
    }

    public static Config parseAudioSpecificConfig(ParsableBitArray r7, boolean r8) {
        int r0 = getAudioObjectType(r7);
        int r1 = getSamplingFrequency(r7);
        int r3 = r7.readBits(4);
        String r4 = "mp4a.40." + r0;
        if (r0 != 5) goto L5;
    L6:
        r1 = getSamplingFrequency(r7);
        r0 = getAudioObjectType(r7);
        if (r0 != 22) goto L9;
        r3 = r7.readBits(4);
    L9:
        if (r8 == true) goto L11;
    L33:
        int r72 = AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[r3];
        if (r72 == (-1)) goto L38;
        return new Config(r1, r72, r4, null);
    L38:
        throw ParserException.createForMalformedContainer(null, null);
    L11:
        if (r0 == 1) goto L24;
        if (r0 == 2) goto L24;
        if (r0 == 3) goto L24;
        if (r0 == 4) goto L24;
        if (r0 == 6) goto L24;
        if (r0 == 7) goto L24;
        if (r0 == 17) goto L24;
        switch(r0) {
            case 19: goto L24;
            case 20: goto L24;
            case 21: goto L24;
            case 22: goto L24;
            case 23: goto L24;
            default: goto L23;
        };
    L23:
        throw ParserException.createForUnsupportedContainerFeature("Unsupported audio object type: " + r0);
    L24:
        parseGaSpecificConfig(r7, r0, r3);
        switch(r0) {
            case 17: goto L27;
            case 18: goto L33;
            case 19: goto L27;
            case 20: goto L27;
            case 21: goto L27;
            case 22: goto L27;
            case 23: goto L27;
            default: goto L33;
        };
    L27:
        int r73 = r7.readBits(2);
        if (r73 == 2) goto L32;
        if (r73 != 3) goto L33;
    L32:
        throw ParserException.createForUnsupportedContainerFeature("Unsupported epConfig: " + r73);
    L5:
        if (r0 != 29) goto L9;
        goto L6
    }

    public static Config parseAudioSpecificConfig(byte[] r1) {
        return parseAudioSpecificConfig(new ParsableBitArray(r1), false);
    }

    private static void parseGaSpecificConfig(ParsableBitArray r3, int r4, int r5) {
        if (r3.readBit() == false) goto L6;
        Log.w("AacUtil", "Unexpected frameLengthFlag = 1");
    L6:
        if (r3.readBit() == false) goto L8;
        r3.skipBits(14);
    L8:
        boolean r0 = r3.readBit();
        if (r5 != 0) goto L11;
        Type$$ExternalSyntheticBUOutline0.m();
        return;
    L11:
        if (r4 == 6) goto L13;
        if (r4 == 20) goto L13;
    L14:
        if (r0 == true) goto L16;
        return;
    L16:
        if (r4 != 22) goto L19;
        r3.skipBits(16);
    L19:
        if (r4 != 17) goto L21;
    L25:
        r3.skipBits(3);
    L26:
        r3.skipBits(1);
        return;
    L21:
        if (r4 == 19) goto L25;
        if (r4 == 20) goto L25;
        if (r4 != 23) goto L26;
    L13:
        r3.skipBits(3);
        goto L14
    }
}
