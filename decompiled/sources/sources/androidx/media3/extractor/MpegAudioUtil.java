package androidx.media3.extractor;

import androidx.car.app.media.CarAudioRecord;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.OneUIUtilities;

/* loaded from: classes4.dex */
public abstract class MpegAudioUtil {
    private static final int[] BITRATE_V1_L1 = null;
    private static final int[] BITRATE_V1_L2 = null;
    private static final int[] BITRATE_V1_L3 = null;
    private static final int[] BITRATE_V2 = null;
    private static final int[] BITRATE_V2_L1 = null;
    private static final String[] MIME_TYPE_BY_LAYER = null;
    private static final int[] SAMPLING_RATE_V1 = null;

    public static final class Header {
        public int bitrate;
        public int channels;
        public int frameSize;
        public String mimeType;
        public int sampleRate;
        public int samplesPerFrame;
        public int version;

        public Header() {
        }

        public Header(Header r2) {
            this.version = r2.version;
            this.mimeType = r2.mimeType;
            this.frameSize = r2.frameSize;
            this.sampleRate = r2.sampleRate;
            this.channels = r2.channels;
            this.bitrate = r2.bitrate;
            this.samplesPerFrame = r2.samplesPerFrame;
        }

        public boolean setForHeaderData(int r9) {
            if (MpegAudioUtil.access$000(r9) == true) goto L5;
            return false;
        L5:
            int r0 = (r9 >>> 19) & 3;
            if (r0 != 1) goto L8;
            return false;
        L8:
            int r4 = (r9 >>> 17) & 3;
            if (r4 != 0) goto L11;
            return false;
        L11:
            int r5 = (r9 >>> 12) & 15;
            if (r5 == 0) goto L44;
            if (r5 == 15) goto L44;
            int r6 = (r9 >>> 10) & 3;
            if (r6 != 3) goto L18;
            return false;
        L18:
            this.version = r0;
            this.mimeType = MpegAudioUtil.access$100()[3 - r4];
            int r1 = MpegAudioUtil.access$200()[r6];
            this.sampleRate = r1;
            int r62 = 2;
            if (r0 != 2) goto L21;
            this.sampleRate = r1 / 2;
        L23:
            int r12 = (r9 >>> 9) & 1;
            this.samplesPerFrame = MpegAudioUtil.access$300(r0, r4);
            if (r4 != 3) goto L29;
            if (r0 != 3) goto L27;
            int r02 = MpegAudioUtil.access$400()[r5 - 1];
        L28:
            this.bitrate = r02;
            this.frameSize = (((r02 * 12) / this.sampleRate) + r12) * 4;
        L40:
            if (((r9 >> 6) & 3) != 3) goto L42;
            r62 = 1;
        L42:
            this.channels = r62;
            return true;
        L27:
            r02 = MpegAudioUtil.access$500()[r5 - 1];
            goto L28
        L29:
            int r7 = 144;
            if (r0 != 3) goto L35;
            if (r4 != 2) goto L33;
            int r03 = MpegAudioUtil.access$600()[r5 - 1];
        L34:
            this.bitrate = r03;
            this.frameSize = ((r03 * 144) / this.sampleRate) + r12;
            goto L40
        L33:
            r03 = MpegAudioUtil.access$700()[r5 - 1];
            goto L34
        L35:
            int r04 = MpegAudioUtil.access$800()[r5 - 1];
            this.bitrate = r04;
            if (r4 != 1) goto L38;
            r7 = 72;
        L38:
            this.frameSize = ((r7 * r04) / this.sampleRate) + r12;
            goto L40
        L21:
            if (r0 != 0) goto L23;
            this.sampleRate = r1 / 4;
        L44:
            return false;
        }
    }

    static {
        MIME_TYPE_BY_LAYER = new String[]{"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};
        SAMPLING_RATE_V1 = new int[]{44100, 48000, 32000};
        BITRATE_V1_L1 = new int[]{32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000};
        BITRATE_V2_L1 = new int[]{32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000};
        BITRATE_V1_L2 = new int[]{32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000};
        BITRATE_V1_L3 = new int[]{32000, OneUIUtilities.ONE_UI_4_0, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000};
        BITRATE_V2 = new int[]{8000, CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE, 24000, 32000, OneUIUtilities.ONE_UI_4_0, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000};
    }

    public static /* synthetic */ boolean access$000(int r0) {
        return isMagicPresent(r0);
    }

    public static /* synthetic */ String[] access$100() {
        return MIME_TYPE_BY_LAYER;
    }

    public static /* synthetic */ int[] access$200() {
        return SAMPLING_RATE_V1;
    }

    public static /* synthetic */ int access$300(int r0, int r1) {
        return getFrameSizeInSamples(r0, r1);
    }

    public static /* synthetic */ int[] access$400() {
        return BITRATE_V1_L1;
    }

    public static /* synthetic */ int[] access$500() {
        return BITRATE_V2_L1;
    }

    public static /* synthetic */ int[] access$600() {
        return BITRATE_V1_L2;
    }

    public static /* synthetic */ int[] access$700() {
        return BITRATE_V1_L3;
    }

    public static /* synthetic */ int[] access$800() {
        return BITRATE_V2;
    }

    public static int getFrameSize(int r7) {
        if (isMagicPresent(r7) == true) goto L5;
        return -1;
    L5:
        int r0 = (r7 >>> 19) & 3;
        if (r0 != 1) goto L8;
        return -1;
    L8:
        int r4 = (r7 >>> 17) & 3;
        if (r4 != 0) goto L11;
        return -1;
    L11:
        int r5 = (r7 >>> 12) & 15;
        if (r5 == 0) goto L43;
        if (r5 == 15) goto L43;
        int r6 = (r7 >>> 10) & 3;
        if (r6 != 3) goto L18;
        return -1;
    L18:
        int r1 = SAMPLING_RATE_V1[r6];
        if (r0 != 2) goto L21;
        r1 = r1 / 2;
    L23:
        int r72 = (r7 >>> 9) & 1;
        if (r4 != 3) goto L30;
        if (r0 != 3) goto L27;
        int r02 = BITRATE_V1_L1[r5 - 1];
    L29:
        return (((r02 * 12) / r1) + r72) * 4;
    L27:
        r02 = BITRATE_V2_L1[r5 - 1];
        goto L29
    L30:
        if (r0 != 3) goto L34;
        if (r4 != 2) goto L33;
        int r52 = BITRATE_V1_L2[r5 - 1];
    L35:
        int r62 = 144;
        if (r0 == 3) goto L38;
        if (r4 != 1) goto L42;
        r62 = 72;
    L42:
        return ((r62 * r52) / r1) + r72;
    L38:
        return ((r52 * 144) / r1) + r72;
    L33:
        r52 = BITRATE_V1_L3[r5 - 1];
        goto L35
    L34:
        r52 = BITRATE_V2[r5 - 1];
        goto L35
    L21:
        if (r0 != 0) goto L23;
        r1 = r1 / 4;
    L43:
        return -1;
    }

    private static int getFrameSizeInSamples(int r3, int r4) {
        if (r4 != 1) goto L5;
        if (r3 != 3) goto L14;
        return 1152;
    L14:
        return 576;
    L5:
        if (r4 == 2) goto L11;
        if (r4 != 3) goto L9;
        return 384;
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        return 1152;
    }

    private static boolean isMagicPresent(int r1) {
        if ((r1 & (-2097152)) != (-2097152)) goto L6;
        return true;
    L6:
        return false;
    }

    public static int parseMpegAudioFrameSampleCount(int r6) {
        if (isMagicPresent(r6) == true) goto L5;
        return -1;
    L5:
        int r0 = (r6 >>> 19) & 3;
        if (r0 != 1) goto L8;
        return -1;
    L8:
        int r3 = (r6 >>> 17) & 3;
        if (r3 != 0) goto L11;
        return -1;
    L11:
        int r4 = (r6 >>> 12) & 15;
        int r62 = (r6 >>> 10) & 3;
        if (r4 == 0) goto L18;
        if (r4 == 15) goto L18;
        if (r62 == 3) goto L18;
        return getFrameSizeInSamples(r0, r3);
    L18:
        return -1;
    }
}
