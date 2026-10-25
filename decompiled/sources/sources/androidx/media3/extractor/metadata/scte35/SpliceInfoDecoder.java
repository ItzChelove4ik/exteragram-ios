package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class SpliceInfoDecoder extends SimpleMetadataDecoder {
    private final ParsableByteArray sectionData;
    private final ParsableBitArray sectionHeader;
    private TimestampAdjuster timestampAdjuster;

    public SpliceInfoDecoder() {
        this.sectionData = new ParsableByteArray();
        this.sectionHeader = new ParsableBitArray();
    }

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    public Metadata decode(MetadataInputBuffer r6, ByteBuffer r7) {
        TimestampAdjuster r0 = this.timestampAdjuster;
        if (r0 != null) goto L5;
    L6:
        TimestampAdjuster r02 = new TimestampAdjuster(r6.timeUs);
        this.timestampAdjuster = r02;
        r02.adjustSampleTimestamp(r6.timeUs - r6.subsampleOffsetUs);
    L7:
        byte[] r62 = r7.array();
        int r72 = r7.limit();
        this.sectionData.reset(r62, r72);
        this.sectionHeader.reset(r62, r72);
        this.sectionHeader.skipBits(39);
        long r03 = (this.sectionHeader.readBits(1) << 32) | this.sectionHeader.readBits(32);
        this.sectionHeader.skipBits(20);
        int r63 = this.sectionHeader.readBits(12);
        int r2 = this.sectionHeader.readBits(8);
        this.sectionData.skipBytes(14);
        if (r2 != 0) goto L10;
        Metadata.Entry r5 = new SpliceNullCommand();
    L24:
        if (r5 != null) goto L28;
        return new Metadata(new Metadata.Entry[0]);
    L28:
        return new Metadata(new Metadata.Entry[]{r5});
    L10:
        if (r2 != 255) goto L12;
        r5 = PrivateCommand.parseFromSection(this.sectionData, r63, r03);
        goto L24
    L12:
        if (r2 != 4) goto L14;
        r5 = SpliceScheduleCommand.parseFromSection(this.sectionData);
        goto L24
    L14:
        if (r2 != 5) goto L16;
        r5 = SpliceInsertCommand.parseFromSection(this.sectionData, r03, this.timestampAdjuster);
        goto L24
    L16:
        if (r2 == 6) goto L18;
        r5 = null;
        goto L24
    L18:
        r5 = TimeSignalCommand.parseFromSection(this.sectionData, r03, this.timestampAdjuster);
        goto L24
    L5:
        if (r6.subsampleOffsetUs == r0.getTimestampOffsetUs()) goto L7;
        goto L6
    }
}
