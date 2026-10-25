package androidx.media3.extractor.ogg;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorUtil;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class OggPacket {
    private int currentSegmentIndex;
    private final ParsableByteArray packetArray;
    private final OggPageHeader pageHeader;
    private boolean populated;
    private int segmentCount;

    public OggPacket() {
        this.pageHeader = new OggPageHeader();
        this.packetArray = new ParsableByteArray(new byte[65025], 0);
        this.currentSegmentIndex = -1;
    }

    private int calculatePacketSize(int r6) {
        int r0 = 0;
        this.segmentCount = 0;
    L3:
        int r1 = this.segmentCount;
        int r2 = r6 + r1;
        OggPageHeader r3 = this.pageHeader;
        if (r2 >= r3.pageSegmentCount) goto L7;
        int[] r22 = r3.laces;
        this.segmentCount = r1 + 1;
        int r12 = r22[r1 + r6];
        r0 = r0 + r12;
        if (r12 == 255) goto L3;
    L7:
        return r0;
    }

    public OggPageHeader getPageHeader() {
        return this.pageHeader;
    }

    public ParsableByteArray getPayload() {
        return this.packetArray;
    }

    public boolean populate(ExtractorInput r7) {
        if (r7 == null) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkState(r2);
        if (this.populated == false) goto L10;
        this.populated = false;
        this.packetArray.reset(0);
    L10:
        if (this.populated == true) goto L43;
        if (this.currentSegmentIndex < 0) goto L14;
    L29:
        int r22 = calculatePacketSize(this.currentSegmentIndex);
        int r3 = this.currentSegmentIndex + this.segmentCount;
        if (r22 <= 0) goto L40;
        ParsableByteArray r4 = this.packetArray;
        r4.ensureCapacity(r4.limit() + r22);
        if (ExtractorUtil.readFullyQuietly(r7, this.packetArray.getData(), this.packetArray.limit(), r22) == false) goto L33;
        ParsableByteArray r42 = this.packetArray;
        r42.setLimit(r42.limit() + r22);
        if (this.pageHeader.laces[r3 - 1] == 255) goto L37;
        boolean r23 = true;
    L38:
        this.populated = r23;
        goto L40
    L37:
        r23 = false;
        goto L38
    L33:
        return false;
    L40:
        if (r3 != this.pageHeader.pageSegmentCount) goto L42;
        r3 = -1;
    L42:
        this.currentSegmentIndex = r3;
        goto L10
    L14:
        if (this.pageHeader.skipToNextPage(r7) == false) goto L28;
        if (this.pageHeader.populate(r7, true) == false) goto L28;
        OggPageHeader r24 = this.pageHeader;
        int r32 = r24.headerSize;
        if ((r24.type & 1) == 1) goto L21;
    L23:
        int r25 = 0;
    L25:
        if (ExtractorUtil.skipFullyQuietly(r7, r32) == false) goto L26;
        this.currentSegmentIndex = r25;
        goto L29
    L26:
        return false;
    L21:
        if (this.packetArray.limit() != 0) goto L23;
        r32 = r32 + calculatePacketSize(0);
        r25 = this.segmentCount;
    L28:
        return false;
    L43:
        return true;
    L5:
        r2 = false;
        goto L6
    }

    public void reset() {
        this.pageHeader.reset();
        this.packetArray.reset(0);
        this.currentSegmentIndex = -1;
        this.populated = false;
    }

    public void trimPayload() {
        if (this.packetArray.getData().length != 65025) goto L5;
        return;
    L5:
        ParsableByteArray r0 = this.packetArray;
        r0.reset(Arrays.copyOf(r0.getData(), Math.max(65025, this.packetArray.limit())), this.packetArray.limit());
    }
}
