package androidx.media3.extractor;

import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class FlacMetadataReader {

    public static final class FlacStreamMetadataHolder {
        public FlacStreamMetadata flacStreamMetadata;

        public FlacStreamMetadataHolder(FlacStreamMetadata r1) {
            this.flacStreamMetadata = r1;
        }
    }

    public static boolean checkAndPeekStreamMarker(ExtractorInput r6) {
        ParsableByteArray r0 = new ParsableByteArray(4);
        r6.peekFully(r0.getData(), 0, 4);
        if (r0.readUnsignedInt() != 1716281667) goto L6;
        return true;
    L6:
        return false;
    }

    public static int getFrameStartMarker(ExtractorInput r4) {
        r4.resetPeekPosition();
        ParsableByteArray r0 = new ParsableByteArray(2);
        r4.peekFully(r0.getData(), 0, 2);
        int r02 = r0.readUnsignedShort();
        if ((r02 >> 2) != 16382) goto L6;
        r4.resetPeekPosition();
        return r02;
    L6:
        r4.resetPeekPosition();
        throw ParserException.createForMalformedContainer("First frame does not start with sync code.", null);
    }

    public static Metadata peekId3Metadata(ExtractorInput r2, boolean r3) {
        if (r3 == false) goto L5;
        Id3Decoder.FramePredicate r32 = null;
    L6:
        Metadata r22 = new Id3Peeker().peekId3Data(r2, r32);
        if (r22 != null) goto L9;
    L12:
        return null;
    L9:
        if (r22.length() == 0) goto L12;
        return r22;
    L5:
        r32 = Id3Decoder.NO_FRAMES_PREDICATE;
        goto L6
    }

    public static Metadata readId3Metadata(ExtractorInput r4, boolean r5) {
        r4.resetPeekPosition();
        long r0 = r4.getPeekPosition();
        Metadata r52 = peekId3Metadata(r4, r5);
        r4.skipFully((int) (r4.getPeekPosition() - r0));
        return r52;
    }

    public static boolean readMetadataBlock(ExtractorInput r7, FlacStreamMetadataHolder r8) {
        r7.resetPeekPosition();
        ParsableBitArray r0 = new ParsableBitArray(new byte[4]);
        r7.peekFully(r0.data, 0, 4);
        boolean r2 = r0.readBit();
        int r4 = r0.readBits(7);
        int r02 = r0.readBits(24) + 4;
        if (r4 != 0) goto L6;
        r8.flacStreamMetadata = readStreamInfoBlock(r7);
        return r2;
    L6:
        FlacStreamMetadata r5 = r8.flacStreamMetadata;
        if (r5 != null) goto L9;
        Segment$$ExternalSyntheticBUOutline0.m();
        return false;
    L9:
        if (r4 != 3) goto L12;
        r8.flacStreamMetadata = r5.copyWithSeekTable(readSeekTableMetadataBlock(r7, r02));
        return r2;
    L12:
        if (r4 != 4) goto L16;
        r8.flacStreamMetadata = r5.copyWithVorbisComments(readVorbisCommentMetadataBlock(r7, r02));
        return r2;
    L16:
        if (r4 != 6) goto L19;
        ParsableByteArray r42 = new ParsableByteArray(r02);
        r7.readFully(r42.getData(), 0, r02);
        r42.skipBytes(4);
        r8.flacStreamMetadata = r5.copyWithPictureFrames(ImmutableList.of(PictureFrame.fromPictureBlock(r42)));
        return r2;
    L19:
        r7.skipFully(r02);
        return r2;
    }

    public static FlacStreamMetadata.SeekTable readSeekTableMetadataBlock(ParsableByteArray r10) {
        r10.skipBytes(1);
        int r0 = r10.readUnsignedInt24();
        long r1 = r10.getPosition() + r0;
        int r02 = r0 / 18;
        long[] r3 = new long[r02];
        long[] r4 = new long[r02];
        int r5 = 0;
    L3:
        if (r5 >= r02) goto L8;
        long r6 = r10.readLong();
        if (r6 == (-1)) goto L6;
        r3[r5] = r6;
        r4[r5] = r10.readLong();
        r10.skipBytes(2);
        r5 = r5 + 1;
        goto L3
    L6:
        r3 = Arrays.copyOf(r3, r5);
        r4 = Arrays.copyOf(r4, r5);
    L8:
        r10.skipBytes((int) (r1 - r10.getPosition()));
        return new FlacStreamMetadata.SeekTable(r3, r4);
    }

    private static FlacStreamMetadata.SeekTable readSeekTableMetadataBlock(ExtractorInput r3, int r4) {
        ParsableByteArray r0 = new ParsableByteArray(r4);
        r3.readFully(r0.getData(), 0, r4);
        return readSeekTableMetadataBlock(r0);
    }

    private static FlacStreamMetadata readStreamInfoBlock(ExtractorInput r3) {
        byte[] r1 = new byte[38];
        r3.readFully(r1, 0, 38);
        return new FlacStreamMetadata(r1, 4);
    }

    public static void readStreamMarker(ExtractorInput r4) {
        ParsableByteArray r0 = new ParsableByteArray(4);
        r4.readFully(r0.getData(), 0, 4);
        if (r0.readUnsignedInt() != 1716281667) goto L6;
        return;
    L6:
        throw ParserException.createForMalformedContainer("Failed to read FLAC stream marker.", null);
    }

    private static List<String> readVorbisCommentMetadataBlock(ExtractorInput r3, int r4) {
        ParsableByteArray r0 = new ParsableByteArray(r4);
        r3.readFully(r0.getData(), 0, r4);
        r0.skipBytes(4);
        return Arrays.asList(VorbisUtil.readVorbisCommentHeader(r0, false, false).comments);
    }
}
