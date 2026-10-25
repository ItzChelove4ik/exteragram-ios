package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;

/* loaded from: classes4.dex */
public final class SectionReader implements TsPayloadReader {
    private int bytesRead;
    private final SectionPayloadReader reader;
    private final ParsableByteArray sectionData;
    private boolean sectionSyntaxIndicator;
    private int totalSectionLength;
    private boolean waitingForPayloadStart;

    public SectionReader(SectionPayloadReader r2) {
        this.reader = r2;
        this.sectionData = new ParsableByteArray(32);
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void consume(ParsableByteArray r7, int r8) {
        if ((r8 & 1) == 0) goto L5;
        boolean r82 = true;
    L7:
        if (r82 == false) goto L9;
        int r4 = r7.getPosition() + r7.readUnsignedByte();
    L11:
        if (this.waitingForPayloadStart == false) goto L16;
        if (r82 == false) goto L44;
        this.waitingForPayloadStart = false;
        r7.setPosition(r4);
        this.bytesRead = 0;
        goto L16
    L44:
        return;
    L16:
        if (r7.bytesLeft() <= 0) goto L60;
        int r83 = this.bytesRead;
        if (r83 < 3) goto L19;
        int r84 = Math.min(r7.bytesLeft(), this.totalSectionLength - this.bytesRead);
        r7.readBytes(this.sectionData.getData(), this.bytesRead, r84);
        int r3 = this.bytesRead + r84;
        this.bytesRead = r3;
        int r85 = this.totalSectionLength;
        if (r3 != r85) goto L16;
        boolean r32 = this.sectionSyntaxIndicator;
        ParsableByteArray r42 = this.sectionData;
        if (r32 == true) goto L38;
        r42.setLimit(r85);
    L43:
        this.sectionData.setPosition(0);
        this.reader.consume(this.sectionData);
        this.bytesRead = 0;
        goto L16
    L38:
        if (Util.crc32(r42.getData(), 0, this.totalSectionLength, -1) != 0) goto L39;
        this.sectionData.setLimit(this.totalSectionLength - 4);
        goto L43
    L39:
        this.waitingForPayloadStart = true;
        return;
    L19:
        if (r83 != 0) goto L24;
        int r86 = r7.readUnsignedByte();
        r7.setPosition(r7.getPosition() - 1);
        if (r86 != 255) goto L24;
        this.waitingForPayloadStart = true;
        return;
    L24:
        int r87 = Math.min(r7.bytesLeft(), 3 - this.bytesRead);
        r7.readBytes(this.sectionData.getData(), this.bytesRead, r87);
        int r43 = this.bytesRead + r87;
        this.bytesRead = r43;
        if (r43 != 3) goto L16;
        this.sectionData.setPosition(0);
        this.sectionData.setLimit(3);
        this.sectionData.skipBytes(1);
        int r88 = this.sectionData.readUnsignedByte();
        int r44 = this.sectionData.readUnsignedByte();
        if ((r88 & 128) == 0) goto L29;
        boolean r5 = true;
    L30:
        this.sectionSyntaxIndicator = r5;
        this.totalSectionLength = (((r88 & 15) << 8) | r44) + 3;
        int r89 = this.sectionData.capacity();
        int r33 = this.totalSectionLength;
        if (r89 >= r33) goto L16;
        this.sectionData.ensureCapacity(Math.min(4098, Math.max(r33, this.sectionData.capacity() * 2)));
        goto L16
    L29:
        r5 = false;
        goto L30
    L60:
        return;
    L9:
        r4 = -1;
        goto L11
    L5:
        r82 = false;
        goto L7
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void init(TimestampAdjuster r2, ExtractorOutput r3, TsPayloadReader.TrackIdGenerator r4) {
        this.reader.init(r2, r3, r4);
        this.waitingForPayloadStart = true;
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void seek() {
        this.waitingForPayloadStart = true;
    }
}
