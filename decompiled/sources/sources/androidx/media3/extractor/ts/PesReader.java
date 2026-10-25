package androidx.media3.extractor.ts;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class PesReader implements TsPayloadReader {
    private int bytesRead;
    private boolean dataAlignmentIndicator;
    private boolean dtsFlag;
    private int extendedHeaderLength;
    private int payloadSize;
    private final ParsableBitArray pesScratch;
    private boolean ptsFlag;
    private final ElementaryStreamReader reader;
    private boolean seenFirstDts;
    private int state;
    private long timeUs;
    private TimestampAdjuster timestampAdjuster;

    public PesReader(ElementaryStreamReader r2) {
        this.reader = r2;
        this.pesScratch = new ParsableBitArray(new byte[10]);
        this.state = 0;
    }

    private boolean continueRead(ParsableByteArray r4, byte[] r5, int r6) {
        int r0 = Math.min(r4.bytesLeft(), r6 - this.bytesRead);
        if (r0 > 0) goto L5;
        return true;
    L5:
        if (r5 != null) goto L7;
        r4.skipBytes(r0);
    L8:
        int r42 = this.bytesRead + r0;
        this.bytesRead = r42;
        if (r42 != r6) goto L11;
        return true;
    L11:
        return false;
    L7:
        r4.readBytes(r5, this.bytesRead, r0);
        goto L8
    }

    private boolean parseHeader() {
        this.pesScratch.setPosition(0);
        int r0 = this.pesScratch.readBits(24);
        if (r0 == 1) goto L6;
        Log.w("PesReader", "Unexpected start code prefix: " + r0);
        this.payloadSize = -1;
        return false;
    L6:
        this.pesScratch.skipBits(8);
        int r02 = this.pesScratch.readBits(16);
        this.pesScratch.skipBits(5);
        this.dataAlignmentIndicator = this.pesScratch.readBit();
        this.pesScratch.skipBits(2);
        this.ptsFlag = this.pesScratch.readBit();
        this.dtsFlag = this.pesScratch.readBit();
        this.pesScratch.skipBits(6);
        int r1 = this.pesScratch.readBits(8);
        this.extendedHeaderLength = r1;
        if (r02 != 0) goto L9;
        this.payloadSize = -1;
    L12:
        return true;
    L9:
        int r03 = (r02 - 3) - r1;
        this.payloadSize = r03;
        if (r03 >= 0) goto L12;
        Log.w("PesReader", "Found negative packet payload size: " + this.payloadSize);
        this.payloadSize = -1;
        goto L12
    }

    @RequiresNonNull({"timestampAdjuster"})
    private void parseHeaderExtension() {
        this.pesScratch.setPosition(0);
        this.timeUs = -9223372036854775807L;
        if (this.ptsFlag == false) goto L11;
        this.pesScratch.skipBits(4);
        this.pesScratch.skipBits(1);
        this.pesScratch.skipBits(1);
        long r3 = ((this.pesScratch.readBits(3) << 30) | (this.pesScratch.readBits(15) << 15)) | this.pesScratch.readBits(15);
        this.pesScratch.skipBits(1);
        if (this.seenFirstDts == false) goto L7;
    L9:
        this.timeUs = this.timestampAdjuster.adjustTsTimestamp(r3);
        return;
    L7:
        if (this.dtsFlag == false) goto L9;
        this.pesScratch.skipBits(4);
        this.pesScratch.skipBits(1);
        this.pesScratch.skipBits(1);
        this.pesScratch.skipBits(1);
        this.timestampAdjuster.adjustTsTimestamp(((this.pesScratch.readBits(3) << 30) | (this.pesScratch.readBits(15) << 15)) | this.pesScratch.readBits(15));
        this.seenFirstDts = true;
        goto L9
    }

    private void setState(int r1) {
        this.state = r1;
        this.bytesRead = 0;
    }

    public boolean canConsumeSynthesizedEmptyPusi(boolean r6) {
        if (r6 == true) goto L5;
    L8:
        boolean r2 = true;
    L10:
        if (this.state == 3) goto L12;
    L18:
        return false;
    L12:
        if (this.payloadSize != (-1)) goto L18;
        if (r6 == true) goto L15;
    L16:
        if (r2 == false) goto L18;
        return true;
    L15:
        if ((this.reader instanceof H262Reader) == true) goto L18;
    L5:
        if (parseHeader() == true) goto L8;
        r2 = false;
        goto L10
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void consume(ParsableByteArray r9, int r10) {
        Assertions.checkStateNotNull(this.timestampAdjuster);
        if ((r10 & 1) == 0) goto L23;
        int r0 = this.state;
        if (r0 == 0) goto L21;
        if (r0 == 1) goto L21;
        if (r0 == 2) goto L20;
        if (r0 == 3) goto L11;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L11:
        if (this.payloadSize == (-1)) goto L14;
        Log.w("PesReader", "Unexpected start indicator: expected " + this.payloadSize + " more bytes");
    L14:
        if (r9.limit() != 0) goto L16;
        boolean r02 = true;
    L17:
        this.reader.packetFinished(r02);
        goto L21
    L16:
        r02 = false;
        goto L17
    L20:
        Log.w("PesReader", "Unexpected start indicator reading extended header");
    L21:
        setState(1);
    L23:
        if (r9.bytesLeft() <= 0) goto L59;
        int r03 = this.state;
        if (r03 != 0) goto L26;
        r9.skipBytes(r9.bytesLeft());
        goto L23
    L26:
        if (r03 != 1) goto L27;
        if (continueRead(r9, this.pesScratch.data, 9) == false) goto L23;
        if (parseHeader() == false) goto L56;
        int r04 = 2;
    L57:
        setState(r04);
        goto L23
    L56:
        r04 = 0;
        goto L57
    L27:
        if (r03 != 2) goto L28;
        if (continueRead(r9, this.pesScratch.data, Math.min(10, this.extendedHeaderLength)) == false) goto L23;
        if (continueRead(r9, null, this.extendedHeaderLength) == false) goto L23;
        parseHeaderExtension();
        if (this.dataAlignmentIndicator == false) goto L49;
        int r05 = 4;
    L50:
        r10 = r10 | r05;
        this.reader.packetStarted(this.timeUs, r10);
        setState(3);
        goto L23
    L49:
        r05 = 0;
        goto L50
    L28:
        if (r03 != 3) goto L40;
        int r06 = r9.bytesLeft();
        int r6 = this.payloadSize;
        if (r6 != (-1)) goto L32;
        int r62 = 0;
    L33:
        if (r62 <= 0) goto L35;
        r06 = r06 - r62;
        r9.setLimit(r9.getPosition() + r06);
    L35:
        this.reader.consume(r9);
        int r63 = this.payloadSize;
        if (r63 == (-1)) goto L23;
        int r64 = r63 - r06;
        this.payloadSize = r64;
        if (r64 != 0) goto L23;
        this.reader.packetFinished(false);
        setState(1);
        goto L23
    L32:
        r62 = r06 - r6;
        goto L33
    L40:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void init(TimestampAdjuster r1, ExtractorOutput r2, TsPayloadReader.TrackIdGenerator r3) {
        this.timestampAdjuster = r1;
        this.reader.createTracks(r2, r3);
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.seenFirstDts = false;
        this.reader.seek();
    }
}
