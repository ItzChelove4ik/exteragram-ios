package androidx.media3.extractor.ogg;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacMetadataReader;
import androidx.media3.extractor.FlacSeekTableSeekMap;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ogg.StreamReader;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes4.dex */
final class FlacReader extends StreamReader {
    private FlacOggSeeker flacOggSeeker;
    private FlacStreamMetadata streamMetadata;

    public static final class FlacOggSeeker implements OggSeeker {
        private long firstFrameOffset;
        private long pendingSeekGranule;
        private FlacStreamMetadata.SeekTable seekTable;
        private FlacStreamMetadata streamMetadata;

        public FlacOggSeeker(FlacStreamMetadata r1, FlacStreamMetadata.SeekTable r2) {
            this.streamMetadata = r1;
            this.seekTable = r2;
            this.firstFrameOffset = -1;
            this.pendingSeekGranule = -1;
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public SeekMap createSeekMap() {
            if (this.firstFrameOffset == (-1)) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkState(r0);
            return new FlacSeekTableSeekMap(this.streamMetadata, this.firstFrameOffset);
        L5:
            r0 = false;
            goto L6
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public long read(ExtractorInput r7) {
            long r0 = this.pendingSeekGranule;
            if (r0 < 0) goto L6;
            long r02 = -(r0 + 2);
            this.pendingSeekGranule = -1;
            return r02;
        L6:
            return -1;
        }

        public void setFirstFrameOffset(long r1) {
            this.firstFrameOffset = r1;
        }

        @Override // androidx.media3.extractor.ogg.OggSeeker
        public void startSeek(long r3) {
            long[] r0 = this.seekTable.pointSampleNumbers;
            this.pendingSeekGranule = r0[Util.binarySearchFloor(r0, r3, true, true)];
        }
    }

    public FlacReader() {
    }

    private int getFlacFrameBlockSize(ParsableByteArray r3) {
        int r2 = (r3.getData()[2] & UByte.MAX_VALUE) >> 4;
        if (r2 != 6) goto L5;
    L6:
        r3.skipBytes(4);
        r3.readUtf8EncodedLong();
    L7:
        int r22 = FlacFrameReader.readFrameBlockSizeSamplesFromKey(r3, r2);
        r3.setPosition(0);
        return r22;
    L5:
        if (r2 != 7) goto L7;
        goto L6
    }

    private static boolean isAudioPacket(byte[] r2) {
        if (r2[0] != (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean verifyBitstreamType(ParsableByteArray r4) {
        if (r4.bytesLeft() >= 5) goto L5;
        return false;
    L5:
        if (r4.readUnsignedByte() == 127) goto L7;
        return false;
    L7:
        if (r4.readUnsignedInt() != 1179402563) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray r2) {
        if (isAudioPacket(r2.getData()) == true) goto L7;
        return -1;
    L7:
        return getFlacFrameBlockSize(r2);
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray r7, long r8, StreamReader.SetupData r10) {
        byte[] r0 = r7.getData();
        FlacStreamMetadata r1 = this.streamMetadata;
        if (r1 != null) goto L7;
        FlacStreamMetadata r82 = new FlacStreamMetadata(r0, 17);
        this.streamMetadata = r82;
        r10.format = r82.getFormat(Arrays.copyOfRange(r0, 9, r7.limit()), null).buildUpon().setContainerMimeType("audio/ogg").build();
        return true;
    L7:
        if ((r0[0] & ByteCompanionObject.MAX_VALUE) != 3) goto L11;
        FlacStreamMetadata.SeekTable r72 = FlacMetadataReader.readSeekTableMetadataBlock(r7);
        FlacStreamMetadata r83 = r1.copyWithSeekTable(r72);
        this.streamMetadata = r83;
        this.flacOggSeeker = new FlacOggSeeker(r83, r72);
        return true;
    L11:
        if (isAudioPacket(r0) == false) goto L17;
        FlacOggSeeker r73 = this.flacOggSeeker;
        if (r73 == null) goto L15;
        r73.setFirstFrameOffset(r8);
        r10.oggSeeker = this.flacOggSeeker;
    L15:
        Assertions.checkNotNull(r10.format);
        return false;
    L17:
        return true;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public void reset(boolean r1) {
        super.reset(r1);
        if (r1 == false) goto L6;
        this.streamMetadata = null;
        this.flacOggSeeker = null;
        return;
    }
}
