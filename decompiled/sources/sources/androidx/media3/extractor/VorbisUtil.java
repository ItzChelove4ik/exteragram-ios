package androidx.media3.extractor;

import android.util.Base64;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import androidx.media3.extractor.metadata.vorbis.VorbisComment;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class VorbisUtil {

    public static final class CommentHeader {
        public final String[] comments;
        public final int length;
        public final String vendor;

        public CommentHeader(String r1, String[] r2, int r3) {
            this.vendor = r1;
            this.comments = r2;
            this.length = r3;
        }
    }

    public static final class Mode {
        public final boolean blockFlag;
        public final int mapping;
        public final int transformType;
        public final int windowType;

        public Mode(boolean r1, int r2, int r3, int r4) {
            this.blockFlag = r1;
            this.windowType = r2;
            this.transformType = r3;
            this.mapping = r4;
        }
    }

    public static final class VorbisIdHeader {
        public final int bitrateMaximum;
        public final int bitrateMinimum;
        public final int bitrateNominal;
        public final int blockSize0;
        public final int blockSize1;
        public final int channels;
        public final byte[] data;
        public final boolean framingFlag;
        public final int sampleRate;
        public final int version;

        public VorbisIdHeader(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, boolean r9, byte[] r10) {
            this.version = r1;
            this.channels = r2;
            this.sampleRate = r3;
            this.bitrateMaximum = r4;
            this.bitrateNominal = r5;
            this.bitrateMinimum = r6;
            this.blockSize0 = r7;
            this.blockSize1 = r8;
            this.framingFlag = r9;
            this.data = r10;
        }
    }

    public static int[] getVorbisToAndroidChannelLayoutMapping(int r5) {
        if (r5 == 3) goto L23;
        if (r5 == 5) goto L21;
        if (r5 == 6) goto L19;
        if (r5 == 7) goto L17;
        if (r5 == 8) goto L15;
        return null;
    L15:
        return new int[]{0, 2, 1, 7, 5, 6, 3, 4};
    L17:
        return new int[]{0, 2, 1, 6, 5, 3, 4};
    L19:
        return new int[]{0, 2, 1, 5, 3, 4};
    L21:
        return new int[]{0, 2, 1, 3, 4};
    L23:
        return new int[]{0, 2, 1};
    }

    public static int iLog(int r1) {
        int r0 = 0;
    L3:
        if (r1 <= 0) goto L5;
        r0 = r0 + 1;
        r1 = r1 >>> 1;
        goto L3
    L5:
        return r0;
    }

    private static long mapType1QuantValues(long r2, long r4) {
        return (long) Math.floor(Math.pow(r2, 1.0d / r4));
    }

    public static Metadata parseVorbisComments(List<String> r8) {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= r8.size()) goto L17;
        String r3 = r8.get(r2);
        String[] r4 = Util.splitAtFirst(r3, "=");
        if (r4.length == 2) goto L9;
        Log.w("VorbisUtil", "Failed to parse Vorbis comment: " + r3);
    L15:
        r2 = r2 + 1;
        goto L4
    L9:
        if (r4[0].equals("METADATA_BLOCK_PICTURE") == true) goto L21;
        r0.add(new VorbisComment(r4[0], r4[1]));
        goto L15
    L21:
        r0.add(PictureFrame.fromPictureBlock(new ParsableByteArray(Base64.decode(r4[1], 0))));     // Catch: RuntimeException -> L12
    L12:
        e = move-exception;
        Log.w("VorbisUtil", "Failed to parse vorbis picture", e);
        goto L15
    L17:
        if (r0.isEmpty() == false) goto L20;
        return null;
    L20:
        return new Metadata(r0);
    }

    public static ImmutableList<byte[]> parseVorbisCsdFromEsdsInitializationData(byte[] r7) {
        ParsableByteArray r0 = new ParsableByteArray(r7);
        r0.skipBytes(1);
        int r3 = 0;
    L4:
        if (r0.bytesLeft() <= 0) goto L8;
        if (r0.peekUnsignedByte() != 255) goto L8;
        r3 = r3 + 255;
        r0.skipBytes(1);
    L8:
        int r32 = r3 + r0.readUnsignedByte();
        int r4 = 0;
    L10:
        if (r0.bytesLeft() <= 0) goto L14;
        if (r0.peekUnsignedByte() != 255) goto L14;
        r4 = r4 + 255;
        r0.skipBytes(1);
    L14:
        int r42 = r4 + r0.readUnsignedByte();
        byte[] r1 = new byte[r32];
        int r02 = r0.getPosition();
        System.arraycopy(r7, r02, r1, 0, r32);
        int r03 = (r02 + r32) + r42;
        int r33 = r7.length - r03;
        byte[] r43 = new byte[r33];
        System.arraycopy(r7, r03, r43, 0, r33);
        return ImmutableList.of(r1, r43);
    }

    private static void readFloors(VorbisBitArray r15) {
        int r1 = r15.readBits(6) + 1;
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L36;
        int r6 = r15.readBits(16);
        if (r6 == 0) goto L32;
        if (r6 != 1) goto L31;
        int r5 = r15.readBits(5);
        int[] r62 = new int[r5];
        int r9 = -1;
        int r10 = 0;
    L8:
        if (r10 >= r5) goto L13;
        int r11 = r15.readBits(4);
        r62[r10] = r11;
        if (r11 <= r9) goto L12;
        r9 = r11;
    L12:
        r10 = r10 + 1;
        goto L8
    L13:
        int r92 = r9 + 1;
        int[] r102 = new int[r92];
        int r112 = 0;
    L15:
        if (r112 >= r92) goto L24;
        r102[r112] = r15.readBits(3) + 1;
        int r12 = r15.readBits(2);
        if (r12 <= 0) goto L19;
        r15.skipBits(8);
    L19:
        int r13 = 0;
    L21:
        if (r13 >= (1 << r12)) goto L23;
        r15.skipBits(8);
        r13 = r13 + 1;
        goto L21
    L23:
        r112 = r112 + 1;
        goto L15
    L24:
        r15.skipBits(2);
        int r7 = r15.readBits(4);
        int r8 = 0;
        int r93 = 0;
        int r113 = 0;
    L25:
        if (r8 >= r5) goto L35;
        r93 = r93 + r102[r62[r8]];
    L27:
        if (r113 >= r93) goto L29;
        r15.skipBits(r7);
        r113 = r113 + 1;
        goto L27
    L29:
        r8 = r8 + 1;
    L35:
        r4 = r4 + 1;
        goto L3
    L31:
        throw ParserException.createForMalformedContainer("floor type greater than 1 not decodable: " + r6, null);
    L32:
        r15.skipBits(8);
        r15.skipBits(16);
        r15.skipBits(16);
        r15.skipBits(6);
        r15.skipBits(8);
        int r52 = r15.readBits(4) + 1;
        int r63 = 0;
    L33:
        if (r63 >= r52) goto L35;
        r15.skipBits(8);
        r63 = r63 + 1;
        goto L33
    }

    private static void readMappings(int r11, VorbisBitArray r12) {
        int r0 = r12.readBits(6) + 1;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L28;
        int r4 = r12.readBits(16);
        if (r4 == 0) goto L8;
        Log.e("VorbisUtil", "mapping type other than 0 not supported: " + r4);
    L25:
        r3 = r3 + 1;
        goto L3
    L8:
        if (r12.readBit() == false) goto L10;
        int r42 = r12.readBits(4) + 1;
    L12:
        if (r12.readBit() == false) goto L17;
        int r6 = r12.readBits(8) + 1;
        int r8 = 0;
    L14:
        if (r8 >= r6) goto L17;
        int r9 = r11 - 1;
        r12.skipBits(iLog(r9));
        r12.skipBits(iLog(r9));
        r8 = r8 + 1;
    L17:
        if (r12.readBits(2) != 0) goto L27;
        if (r42 <= 1) goto L22;
        int r62 = 0;
    L20:
        if (r62 >= r11) goto L22;
        r12.skipBits(4);
        r62 = r62 + 1;
    L22:
        int r5 = 0;
    L23:
        if (r5 >= r42) goto L25;
        r12.skipBits(8);
        r12.skipBits(8);
        r12.skipBits(8);
        r5 = r5 + 1;
        goto L23
    L27:
        throw ParserException.createForMalformedContainer("to reserved bits must be zero after mapping coupling steps", null);
    L10:
        r42 = 1;
        goto L12
    }

    private static Mode[] readModes(VorbisBitArray r8) {
        int r0 = r8.readBits(6) + 1;
        Mode[] r1 = new Mode[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = new Mode(r8.readBit(), r8.readBits(16), r8.readBits(16), r8.readBits(8));
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private static void readResidues(VorbisBitArray r12) {
        int r1 = r12.readBits(6) + 1;
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L25;
        if (r12.readBits(16) > 2) goto L24;
        r12.skipBits(24);
        r12.skipBits(24);
        r12.skipBits(24);
        int r5 = r12.readBits(6) + 1;
        r12.skipBits(8);
        int[] r7 = new int[r5];
        int r8 = 0;
    L7:
        if (r8 >= r5) goto L13;
        int r9 = r12.readBits(3);
        if (r12.readBit() == false) goto L11;
        int r10 = r12.readBits(5);
    L12:
        r7[r8] = (r10 * 8) + r9;
        r8 = r8 + 1;
        goto L7
    L11:
        r10 = 0;
        goto L12
    L13:
        int r82 = 0;
    L14:
        if (r82 >= r5) goto L22;
        int r92 = 0;
    L16:
        if (r92 >= 8) goto L21;
        if ((r7[r82] & (1 << r92)) == 0) goto L20;
        r12.skipBits(8);
    L20:
        r92 = r92 + 1;
        goto L16
    L21:
        r82 = r82 + 1;
        goto L14
    L22:
        r4 = r4 + 1;
        goto L3
    L24:
        throw ParserException.createForMalformedContainer("residueType greater than 2 is not decodable", null);
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray r1) {
        return readVorbisCommentHeader(r1, true, true);
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray r7, boolean r8, boolean r9) {
        int r0 = 0;
        if (r8 == false) goto L5;
        verifyVorbisHeaderCapturePattern(3, r7, false);
    L5:
        String r82 = r7.readString((int) r7.readLittleEndianUnsignedInt());
        int r1 = r82.length();
        long r2 = r7.readLittleEndianUnsignedInt();
        String[] r4 = new String[(int) r2];
        int r12 = r1 + 15;
    L7:
        if (r0 >= r2) goto L9;
        String r5 = r7.readString((int) r7.readLittleEndianUnsignedInt());
        r4[r0] = r5;
        r12 = (r12 + 4) + r5.length();
        r0 = r0 + 1;
        goto L7
    L9:
        if (r9 == false) goto L16;
        if ((r7.readUnsignedByte() & 1) != 0) goto L16;
        throw ParserException.createForMalformedContainer("framing bit expected to be set", null);
    L16:
        return new CommentHeader(r82, r4, r12 + 1);
    }

    public static VorbisIdHeader readVorbisIdentificationHeader(ParsableByteArray r15) {
        verifyVorbisHeaderCapturePattern(1, r15, false);
        int r3 = r15.readLittleEndianUnsignedIntToInt();
        int r4 = r15.readUnsignedByte();
        int r5 = r15.readLittleEndianUnsignedIntToInt();
        int r2 = r15.readLittleEndianInt();
        if (r2 > 0) goto L5;
        r2 = -1;
    L5:
        int r7 = r15.readLittleEndianInt();
        if (r7 > 0) goto L8;
        r7 = -1;
    L8:
        int r8 = r15.readLittleEndianInt();
        if (r8 > 0) goto L11;
        r8 = -1;
    L11:
        int r6 = r15.readUnsignedByte();
        int r9 = (int) Math.pow(2.0d, r6 & 15);
        int r10 = (int) Math.pow(2.0d, (r6 & 240) >> 4);
        if ((r15.readUnsignedByte() & 1) <= 0) goto L14;
        boolean r11 = true;
    L16:
        return new VorbisIdHeader(r3, r4, r5, r2, r7, r8, r9, r10, r11, Arrays.copyOf(r15.getData(), r15.limit()));
    L14:
        r11 = false;
        goto L16
    }

    public static Mode[] readVorbisModes(ParsableByteArray r4, int r5) {
        int r1 = 0;
        verifyVorbisHeaderCapturePattern(5, r4, false);
        int r0 = r4.readUnsignedByte() + 1;
        VorbisBitArray r2 = new VorbisBitArray(r4.getData());
        r2.skipBits(r4.getPosition() * 8);
        int r42 = 0;
    L3:
        if (r42 >= r0) goto L5;
        skipBook(r2);
        r42 = r42 + 1;
        goto L3
    L5:
        int r43 = r2.readBits(6) + 1;
    L7:
        if (r1 >= r43) goto L13;
        if (r2.readBits(16) != 0) goto L12;
        r1 = r1 + 1;
        goto L7
    L12:
        throw ParserException.createForMalformedContainer("placeholder of time domain transforms not zeroed out", null);
    L13:
        readFloors(r2);
        readResidues(r2);
        readMappings(r5, r2);
        Mode[] r44 = readModes(r2);
        if (r2.readBit() == false) goto L17;
        return r44;
    L17:
        throw ParserException.createForMalformedContainer("framing bit after modes not set as expected", null);
    }

    private static void skipBook(VorbisBitArray r7) {
        if (r7.readBits(24) != 5653314) goto L35;
        int r1 = r7.readBits(16);
        int r0 = r7.readBits(24);
        int r4 = 0;
        if (r7.readBit() == true) goto L14;
        boolean r2 = r7.readBit();
    L7:
        if (r4 >= r0) goto L17;
        if (r2 == true) goto L10;
        r7.skipBits(5);
    L13:
        r4 = r4 + 1;
        goto L7
    L10:
        if (r7.readBit() == false) goto L13;
        r7.skipBits(5);
    L17:
        int r42 = r7.readBits(4);
        if (r42 > 2) goto L33;
        if (r42 == 1) goto L24;
        if (r42 == 2) goto L24;
        return;
    L24:
        r7.skipBits(32);
        r7.skipBits(32);
        int r22 = r7.readBits(4) + 1;
        r7.skipBits(1);
        if (r42 != 1) goto L29;
        if (r1 == 0) goto L28;
        long r02 = mapType1QuantValues(r0, r1);
    L30:
        r7.skipBits((int) (r02 * r22));
        return;
    L28:
        r02 = 0;
        goto L30
    L29:
        r02 = r1 * r0;
        goto L30
    L33:
        throw ParserException.createForMalformedContainer("lookup type greater than 2 not decodable: " + r42, null);
    L14:
        r7.skipBits(5);
    L15:
        if (r4 >= r0) goto L17;
        r4 = r4 + r7.readBits(iLog(r0 - r4));
        goto L15
    L35:
        throw ParserException.createForMalformedContainer("expected code book to start with [0x56, 0x43, 0x42] at " + r7.getPosition(), null);
    }

    public static boolean verifyVorbisHeaderCapturePattern(int r4, ParsableByteArray r5, boolean r6) {
        if (r5.bytesLeft() >= 7) goto L9;
        if (r6 == false) goto L7;
        return false;
    L7:
        throw ParserException.createForMalformedContainer("too short header: " + r5.bytesLeft(), null);
    L9:
        if (r5.readUnsignedByte() == r4) goto L15;
        if (r6 == false) goto L13;
        return false;
    L13:
        throw ParserException.createForMalformedContainer("expected header type " + Integer.toHexString(r4), null);
    L15:
        if (r5.readUnsignedByte() == 118) goto L17;
    L29:
        if (r6 == false) goto L32;
        return false;
    L32:
        throw ParserException.createForMalformedContainer("expected characters 'vorbis'", null);
    L17:
        if (r5.readUnsignedByte() != 111) goto L29;
        if (r5.readUnsignedByte() != 114) goto L29;
        if (r5.readUnsignedByte() != 98) goto L29;
        if (r5.readUnsignedByte() != 105) goto L29;
        if (r5.readUnsignedByte() != 115) goto L29;
        return true;
    }
}
