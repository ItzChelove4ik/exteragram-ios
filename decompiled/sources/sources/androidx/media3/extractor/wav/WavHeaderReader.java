package androidx.media3.extractor.wav;

import android.util.Pair;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes4.dex */
abstract class WavHeaderReader {
    private static final byte[] AMBISONIC_SUBFORMAT = null;
    private static final byte[] WAVEEXT_SUBFORMAT = null;

    public static final class ChunkHeader {
        public final int id;
        public final long size;

        private ChunkHeader(int r1, long r2) {
            this.id = r1;
            this.size = r2;
        }

        public static ChunkHeader peek(ExtractorInput r3, ParsableByteArray r4) {
            r3.peekFully(r4.getData(), 0, 8);
            r4.setPosition(0);
            return new ChunkHeader(r4.readInt(), r4.readLittleEndianUnsignedInt());
        }
    }

    static {
        WAVEEXT_SUBFORMAT = new byte[]{0, 0, 0, 0, 16, 0, ByteCompanionObject.MIN_VALUE, 0, 0, -86, 0, 56, -101, 113};
        AMBISONIC_SUBFORMAT = new byte[]{0, 0, 33, 7, -45, 17, -122, 68, -56, -63, -54, 0, 0, 0};
    }

    public static boolean checkFileType(ExtractorInput r4) {
        ParsableByteArray r0 = new ParsableByteArray(8);
        int r1 = ChunkHeader.peek(r4, r0).id;
        if (r1 != 1380533830) goto L5;
    L7:
        r4.peekFully(r0.getData(), 0, 4);
        r0.setPosition(0);
        int r42 = r0.readInt();
        if (r42 == 1463899717) goto L11;
        Log.e("WavHeaderReader", "Unsupported form type: " + r42);
        return false;
    L11:
        return true;
    L5:
        if (r1 == 1380333108) goto L7;
        return false;
    }

    public static WavFormat readFormat(ExtractorInput r13) {
        ParsableByteArray r0 = new ParsableByteArray(16);
        ChunkHeader r2 = skipToChunk(1718449184, r13, r0);
        if (r2.size < 16) goto L5;
        boolean r3 = true;
    L6:
        Assertions.checkState(r3);
        r13.peekFully(r0.getData(), 0, 16);
        r0.setPosition(0);
        int r32 = r0.readLittleEndianUnsignedShort();
        int r7 = r0.readLittleEndianUnsignedShort();
        int r8 = r0.readLittleEndianUnsignedIntToInt();
        int r9 = r0.readLittleEndianUnsignedIntToInt();
        int r10 = r0.readLittleEndianUnsignedShort();
        int r11 = r0.readLittleEndianUnsignedShort();
        int r02 = ((int) r2.size) - 16;
        if (r02 <= 0) goto L36;
        byte[] r1 = new byte[r02];
        r13.peekFully(r1, 0, r02);
        if (r32 == 65534) goto L11;
    L33:
        byte[] r12 = r1;
        int r6 = r32;
        r13.skipFully((int) (r13.getPeekPosition() - r13.getPosition()));
        return new WavFormat(r6, r7, r8, r9, r10, r11, r12);
    L11:
        if (r02 != 24) goto L33;
        ParsableByteArray r03 = new ParsableByteArray(r1);
        r03.readLittleEndianUnsignedShort();
        int r22 = r03.readLittleEndianUnsignedShort();
        if (r22 == 0) goto L18;
        if (r22 == r11) goto L18;
        throw ParserException.createForUnsupportedContainerFeature("validBits ( " + r22 + ")  != bitsPerSample( " + r11 + ") are not supported");
    L18:
        int r23 = r03.readLittleEndianUnsignedIntToInt();
        if ((r23 >> 18) != 0) goto L35;
        if (r23 != 0) goto L22;
    L26:
        r32 = r03.readLittleEndianUnsignedShort();
        byte[] r5 = new byte[14];
        r03.readBytes(r5, 0, 14);
        if (Arrays.equals(r5, WAVEEXT_SUBFORMAT) == true) goto L33;
        if (Arrays.equals(r5, AMBISONIC_SUBFORMAT) == true) goto L33;
        throw ParserException.createForUnsupportedContainerFeature("invalid wav format extension guid");
    L22:
        if (Integer.bitCount(r23) == r7) goto L26;
        throw ParserException.createForUnsupportedContainerFeature("invalid number of channels (" + Integer.bitCount(r23) + ") in channel mask " + r23);
    L35:
        throw ParserException.createForUnsupportedContainerFeature("invalid channel mask " + r23);
    L36:
        r1 = Util.EMPTY_BYTE_ARRAY;
        goto L33
    L5:
        r3 = false;
        goto L6
    }

    public static long readRf64SampleDataSize(ExtractorInput r7) {
        ParsableByteArray r0 = new ParsableByteArray(8);
        ChunkHeader r2 = ChunkHeader.peek(r7, r0);
        if (r2.id == 1685272116) goto L6;
        r7.resetPeekPosition();
        return -1;
    L6:
        r7.advancePeekPosition(8);
        r0.setPosition(0);
        r7.peekFully(r0.getData(), 0, 8);
        long r3 = r0.readLittleEndianLong();
        r7.skipFully(((int) r2.size) + 8);
        return r3;
    }

    private static ChunkHeader skipToChunk(int r9, ExtractorInput r10, ParsableByteArray r11) {
        ChunkHeader r0 = ChunkHeader.peek(r10, r11);
    L4:
        if (r0.id == r9) goto L13;
        Log.w("WavHeaderReader", "Ignoring unknown WAV chunk: " + r0.id);
        long r1 = r0.size;
        long r3 = 8 + r1;
        if ((r1 % 2) == 0) goto L9;
        r3 = 9 + r1;
    L9:
        if (r3 > 2147483647L) goto L12;
        r10.skipFully((int) r3);
        r0 = ChunkHeader.peek(r10, r11);
        goto L4
    L12:
        throw ParserException.createForUnsupportedContainerFeature("Chunk is too large (~2GB+) to skip; id: " + r0.id);
    L13:
        return r0;
    }

    public static Pair<Long, Long> skipToSampleData(ExtractorInput r3) {
        r3.resetPeekPosition();
        ChunkHeader r0 = skipToChunk(1684108385, r3, new ParsableByteArray(8));
        r3.skipFully(8);
        return Pair.create(Long.valueOf(r3.getPosition()), Long.valueOf(r0.size));
    }
}
