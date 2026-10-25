package org.telegram.messenger.audioinfo.mp3;

import androidx.car.app.media.CarAudioRecord;
import kotlin.UByte;
import org.telegram.messenger.OneUIUtilities;

/* loaded from: classes5.dex */
public class MP3Frame {
    private final byte[] bytes;
    private final Header header;

    public static final class CRC16 {
        private short crc;

        public CRC16() {
            this.crc = -1;
        }

        public short getValue() {
            return this.crc;
        }

        public void update(byte r2) {
            update(r2, 8);
        }

        public void update(int r6, int r7) {
            int r72 = 1 << (r7 - 1);
        L3:
            short r1 = this.crc;
            boolean r3 = false;
            if ((32768 & r1) != 0) goto L6;
            boolean r2 = true;
        L8:
            if ((r6 & r72) != 0) goto L11;
            r3 = true;
        L11:
            if ((r2 ^ r3) == false) goto L13;
            this.crc = (short) (((short) (r1 << 1)) ^ 32773);
        L14:
            r72 = r72 >>> 1;
            if (r72 != 0) goto L3;
            return;
        L13:
            this.crc = (short) (r1 << 1);
            goto L14
        L6:
            r2 = false;
            goto L8
        }
    }

    public static class Header {
        private static final int[][] BITRATES = null;
        private static final int[][] BITRATES_COLUMN = null;
        private static final int[][] FREQUENCIES = null;
        private static final int[][] SIDE_INFO_SIZES = null;
        private static final int[][] SIZE_COEFFICIENTS = null;
        private static final int[] SLOT_SIZES = null;
        private final int bitrate;
        private final int channelMode;
        private final int frequency;
        private final int layer;
        private final int padding;
        private final int protection;
        private final int version;

        static {
            FREQUENCIES = new int[][]{new int[]{11025, -1, 22050, 44100}, new int[]{12000, -1, 24000, 48000}, new int[]{8000, -1, CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE, 32000}, new int[]{-1, -1, -1, -1}};
            BITRATES = new int[][]{new int[]{0, 0, 0, 0, 0}, new int[]{32000, 32000, 32000, 32000, 8000}, new int[]{64000, 48000, OneUIUtilities.ONE_UI_4_0, 48000, CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE}, new int[]{96000, 56000, 48000, 56000, 24000}, new int[]{128000, 64000, 56000, 64000, 32000}, new int[]{160000, 80000, 64000, 80000, OneUIUtilities.ONE_UI_4_0}, new int[]{192000, 96000, 80000, 96000, 48000}, new int[]{224000, 112000, 96000, 112000, 56000}, new int[]{256000, 128000, 112000, 128000, 64000}, new int[]{288000, 160000, 128000, 144000, 80000}, new int[]{320000, 192000, 160000, 160000, 96000}, new int[]{352000, 224000, 192000, 176000, 112000}, new int[]{384000, 256000, 224000, 192000, 128000}, new int[]{416000, 320000, 256000, 224000, 144000}, new int[]{448000, 384000, 320000, 256000, 160000}, new int[]{-1, -1, -1, -1, -1}};
            BITRATES_COLUMN = new int[][]{new int[]{-1, 4, 4, 3}, new int[]{-1, -1, -1, -1}, new int[]{-1, 4, 4, 3}, new int[]{-1, 2, 1, 0}};
            SIZE_COEFFICIENTS = new int[][]{new int[]{-1, 72, 144, 12}, new int[]{-1, -1, -1, -1}, new int[]{-1, 72, 144, 12}, new int[]{-1, 144, 144, 12}};
            SLOT_SIZES = new int[]{-1, 1, 1, 4};
            SIDE_INFO_SIZES = new int[][]{new int[]{17, -1, 17, 32}, new int[]{17, -1, 17, 32}, new int[]{17, -1, 17, 32}, new int[]{9, -1, 9, 17}};
        }

        public Header(int r6, int r7, int r8) {
            int r0 = (r6 >> 3) & 3;
            this.version = r0;
            if (r0 == 1) goto L31;
            int r02 = (r6 >> 1) & 3;
            this.layer = r02;
            if (r02 == 0) goto L29;
            int r3 = (r7 >> 4) & 15;
            this.bitrate = r3;
            if (r3 == 15) goto L27;
            if (r3 == 0) goto L25;
            int r32 = (r7 >> 2) & 3;
            this.frequency = r32;
            if (r32 == 3) goto L23;
            int r33 = 6;
            this.channelMode = (r8 >> 6) & 3;
            this.padding = (r7 >> 1) & 1;
            int r62 = r6 & 1;
            this.protection = r62;
            if (r62 == 0) goto L15;
            r33 = 4;
        L15:
            if (r02 != 1) goto L18;
            r33 = r33 + getSideInfoSize();
        L18:
            if (getFrameSize() < r33) goto L21;
            return;
        L21:
            throw new MP3Exception("Frame size must be at least " + r33);
        L23:
            throw new MP3Exception("Reserved frequency");
        L25:
            throw new MP3Exception("Free bitrate");
        L27:
            throw new MP3Exception("Reserved bitrate");
        L29:
            throw new MP3Exception("Reserved layer");
        L31:
            throw new MP3Exception("Reserved version");
        }

        public int getBitrate() {
            return BITRATES[this.bitrate][BITRATES_COLUMN[this.version][this.layer]];
        }

        public int getChannelMode() {
            return this.channelMode;
        }

        public int getDuration() {
            return (int) getTotalDuration(getFrameSize());
        }

        public int getFrameSize() {
            return (((SIZE_COEFFICIENTS[this.version][this.layer] * getBitrate()) / getFrequency()) + this.padding) * SLOT_SIZES[this.layer];
        }

        public int getFrequency() {
            return FREQUENCIES[this.frequency][this.version];
        }

        public int getLayer() {
            return this.layer;
        }

        public int getProtection() {
            return this.protection;
        }

        public int getSampleCount() {
            if (this.layer != 3) goto L6;
            return 384;
        L6:
            return 1152;
        }

        public int getSideInfoSize() {
            return SIDE_INFO_SIZES[this.channelMode][this.version];
        }

        public long getTotalDuration(long r3) {
            long r0 = ((getSampleCount() * r3) * 1000) / (getFrameSize() * getFrequency());
            if (getVersion() != 3) goto L5;
            return r0;
        L5:
            if (getChannelMode() == 3) goto L7;
            return r0;
        L7:
            return r0 / 2;
        }

        public int getVBRIOffset() {
            return 36;
        }

        public int getVersion() {
            return this.version;
        }

        public int getXingOffset() {
            return getSideInfoSize() + 4;
        }

        public boolean isCompatible(Header r3) {
            if (this.layer == r3.layer) goto L5;
            return false;
        L5:
            if (this.version == r3.version) goto L7;
            return false;
        L7:
            if (this.frequency == r3.frequency) goto L9;
            return false;
        L9:
            if (this.channelMode != r3.channelMode) goto L16;
            return true;
        L16:
            return false;
        }
    }

    public MP3Frame(Header r1, byte[] r2) {
        this.header = r1;
        this.bytes = r2;
    }

    public Header getHeader() {
        return this.header;
    }

    public int getNumberOfFrames() {
        if (isXingFrame() == false) goto L10;
        int r0 = this.header.getXingOffset();
        byte[] r3 = this.bytes;
        if ((r3[r0 + 7] & 1) == 0) goto L12;
        int r1 = (((r3[r0 + 8] & UByte.MAX_VALUE) << 24) | ((r3[r0 + 9] & UByte.MAX_VALUE) << 16)) | ((r3[r0 + 10] & UByte.MAX_VALUE) << 8);
        byte r32 = r3[r0 + 11];
    L8:
        return (r32 & UByte.MAX_VALUE) | r1;
    L12:
        return -1;
    L10:
        if (isVBRIFrame() == false) goto L14;
        int r02 = this.header.getVBRIOffset();
        byte[] r33 = this.bytes;
        r1 = (((r33[r02 + 14] & UByte.MAX_VALUE) << 24) | ((r33[r02 + 15] & UByte.MAX_VALUE) << 16)) | ((r33[r02 + 16] & UByte.MAX_VALUE) << 8);
        r32 = r33[r02 + 17];
        goto L8
    L14:
        return -1;
    }

    public int getSize() {
        return this.bytes.length;
    }

    public boolean isChecksumError() {
        if (this.header.getProtection() == 0) goto L5;
    L13:
        return false;
    L5:
        if (this.header.getLayer() != 1) goto L13;
        CRC16 r0 = new CRC16();
        r0.update(this.bytes[2]);
        r0.update(this.bytes[3]);
        int r3 = this.header.getSideInfoSize();
        int r4 = 0;
    L7:
        byte[] r5 = this.bytes;
        if (r4 >= r3) goto L11;
        r0.update(r5[r4 + 6]);
        r4 = r4 + 1;
        goto L7
    L11:
        if ((((r5[4] & UByte.MAX_VALUE) << 8) | (r5[5] & UByte.MAX_VALUE)) == r0.getValue()) goto L13;
        return true;
    }

    public boolean isVBRIFrame() {
        int r0 = this.header.getVBRIOffset();
        byte[] r4 = this.bytes;
        if (r4.length >= (r0 + 26)) goto L6;
        return false;
    L6:
        if (r4[r0] == 86) goto L8;
    L15:
        return false;
    L8:
        if (r4[r0 + 1] != 66) goto L15;
        if (r4[r0 + 2] != 82) goto L15;
        if (r4[r0 + 3] != 73) goto L15;
        return true;
    }

    public boolean isXingFrame() {
        int r0 = this.header.getXingOffset();
        byte[] r7 = this.bytes;
        if (r7.length >= (r0 + 12)) goto L5;
        return false;
    L5:
        if (r0 >= 0) goto L7;
    L27:
        return false;
    L7:
        if (r7.length < (r0 + 8)) goto L27;
        byte r1 = r7[r0];
        if (r1 != 88) goto L19;
        if (r7[r0 + 1] != 105) goto L19;
        if (r7[r0 + 2] != 110) goto L19;
        if (r7[r0 + 3] != 103) goto L19;
        return true;
    L19:
        if (r1 != 73) goto L27;
        if (r7[r0 + 1] != 110) goto L27;
        if (r7[r0 + 2] != 102) goto L27;
        if (r7[r0 + 3] != 111) goto L27;
        return true;
    }
}
