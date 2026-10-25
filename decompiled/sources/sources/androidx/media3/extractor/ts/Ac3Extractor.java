package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ts.TsPayloadReader;

/* loaded from: classes4.dex */
public final class Ac3Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private final Ac3Reader reader;
    private final ParsableByteArray sampleData;
    private boolean startedPacket;

    /* renamed from: $r8$lambda$UBU3oZ0gNDecgBYBKoAx-aebyw0, reason: not valid java name */
    public static /* synthetic */ Extractor[] m861$r8$lambda$UBU3oZ0gNDecgBYBKoAxaebyw0() {
        return new Extractor[]{new Ac3Extractor()};
    }

    static {
        FACTORY = new Ac3Extractor$$ExternalSyntheticLambda0();
    }

    public Ac3Extractor() {
        this.reader = new Ac3Reader("audio/ac3");
        this.sampleData = new ParsableByteArray(2786);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r4) {
        this.reader.createTracks(r4, new TsPayloadReader.TrackIdGenerator(0, 1));
        r4.endTracks();
        r4.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r5, PositionHolder r6) {
        int r52 = r5.read(this.sampleData.getData(), 0, 2786);
        if (r52 != (-1)) goto L5;
        return -1;
    L5:
        this.sampleData.setPosition(0);
        this.sampleData.setLimit(r52);
        if (this.startedPacket == true) goto L8;
        this.reader.packetStarted(0, 4);
        this.startedPacket = true;
    L8:
        this.reader.consume(this.sampleData);
        return 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        this.startedPacket = false;
        this.reader.seek();
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r7) {
        ParsableByteArray r6 = new ParsableByteArray(10);
        int r2 = 0;
    L3:
        r7.peekFully(r6.getData(), 0, 10);
        r6.setPosition(0);
        if (r6.readUnsignedInt24() != 4801587) goto L5;
        r6.skipBytes(3);
        int r3 = r6.readSynchSafeInt();
        r2 = r2 + (r3 + 10);
        r7.advancePeekPosition(r3);
        goto L3
    L5:
        r7.resetPeekPosition();
        r7.advancePeekPosition(r2);
        int r0 = 0;
        int r32 = r2;
    L6:
        r7.peekFully(r6.getData(), 0, 6);
        r6.setPosition(0);
        if (r6.readUnsignedShort() != 2935) goto L8;
        r0 = r0 + 1;
        if (r0 >= 4) goto L14;
        int r4 = Ac3Util.parseAc3SyncframeSize(r6.getData());
        if (r4 == (-1)) goto L17;
        r7.advancePeekPosition(r4 - 6);
        goto L6
    L17:
        return false;
    L14:
        return true;
    L8:
        r7.resetPeekPosition();
        r32 = r32 + 1;
        if ((r32 - r2) >= 8192) goto L10;
        r7.advancePeekPosition(r32);
        r0 = 0;
        goto L6
    L10:
        return false;
    }
}
