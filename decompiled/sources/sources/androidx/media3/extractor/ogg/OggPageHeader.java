package androidx.media3.extractor.ogg;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorUtil;

/* loaded from: classes4.dex */
final class OggPageHeader {
    public int bodySize;
    public long granulePosition;
    public int headerSize;
    public final int[] laces;
    public long pageChecksum;
    public int pageSegmentCount;
    public long pageSequenceNumber;
    public int revision;
    private final ParsableByteArray scratch;
    public long streamSerialNumber;
    public int type;

    public OggPageHeader() {
        this.laces = new int[255];
        this.scratch = new ParsableByteArray(255);
    }

    public boolean populate(ExtractorInput r6, boolean r7) {
        reset();
        this.scratch.reset(27);
        int r2 = 0;
        if (ExtractorUtil.peekFullyQuietly(r6, this.scratch.getData(), 0, 27, r7) == true) goto L5;
    L21:
        return false;
    L5:
        if (this.scratch.readUnsignedInt() != 1332176723) goto L21;
        int r0 = this.scratch.readUnsignedByte();
        this.revision = r0;
        if (r0 == 0) goto L13;
        if (r7 == false) goto L12;
        return false;
    L12:
        throw ParserException.createForUnsupportedContainerFeature("unsupported bit stream revision");
    L13:
        this.type = this.scratch.readUnsignedByte();
        this.granulePosition = this.scratch.readLittleEndianLong();
        this.streamSerialNumber = this.scratch.readLittleEndianUnsignedInt();
        this.pageSequenceNumber = this.scratch.readLittleEndianUnsignedInt();
        this.pageChecksum = this.scratch.readLittleEndianUnsignedInt();
        int r02 = this.scratch.readUnsignedByte();
        this.pageSegmentCount = r02;
        this.headerSize = r02 + 27;
        this.scratch.reset(r02);
        if (ExtractorUtil.peekFullyQuietly(r6, this.scratch.getData(), 0, this.pageSegmentCount, r7) == true) goto L17;
        return false;
    L17:
        if (r2 >= this.pageSegmentCount) goto L19;
        this.laces[r2] = this.scratch.readUnsignedByte();
        this.bodySize += this.laces[r2];
        r2 = r2 + 1;
        goto L17
    L19:
        return true;
    }

    public void reset() {
        this.revision = 0;
        this.type = 0;
        this.granulePosition = 0;
        this.streamSerialNumber = 0;
        this.pageSequenceNumber = 0;
        this.pageChecksum = 0;
        this.pageSegmentCount = 0;
        this.headerSize = 0;
        this.bodySize = 0;
    }

    public boolean skipToNextPage(ExtractorInput r3) {
        return skipToNextPage(r3, -1);
    }

    public boolean skipToNextPage(ExtractorInput r9, long r10) {
        if (r9.getPosition() != r9.getPeekPosition()) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        this.scratch.reset(4);
    L8:
        if (r10 == (-1)) goto L12;
        if ((r9.getPosition() + 4) < r10) goto L12;
    L18:
        if (r10 == (-1)) goto L22;
        if (r9.getPosition() < r10) goto L22;
    L24:
        return false;
    L22:
        if (r9.skip(1) == (-1)) goto L24;
    L12:
        if (ExtractorUtil.peekFullyQuietly(r9, this.scratch.getData(), 0, 4, true) == false) goto L18;
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt() == 1332176723) goto L15;
        r9.skipFully(1);
        goto L8
    L15:
        r9.resetPeekPosition();
        return true;
    L5:
        r0 = false;
        goto L6
    }
}
