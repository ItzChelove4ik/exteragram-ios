package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ts.TsPayloadReader;

/* loaded from: classes4.dex */
public final class Ac4Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private final Ac4Reader reader;
    private final ParsableByteArray sampleData;
    private boolean startedPacket;

    public static /* synthetic */ Extractor[] $r8$lambda$pIYORmUQfpRIHxmB4WmTODgFbng() {
        return new Extractor[]{new Ac4Extractor()};
    }

    static {
        FACTORY = new Ac4Extractor$$ExternalSyntheticLambda0();
    }

    public Ac4Extractor() {
        this.reader = new Ac4Reader("audio/ac4");
        this.sampleData = new ParsableByteArray(16384);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r4) {
        this.reader.createTracks(r4, new TsPayloadReader.TrackIdGenerator(0, 1));
        r4.endTracks();
        r4.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r5, PositionHolder r6) {
        int r52 = r5.read(this.sampleData.getData(), 0, 16384);
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
    public boolean sniff(ExtractorInput r8) {
        ParsableByteArray r7 = new ParsableByteArray(10);
        int r2 = 0;
    L3:
        r8.peekFully(r7.getData(), 0, 10);
        r7.setPosition(0);
        if (r7.readUnsignedInt24() != 4801587) goto L5;
        r7.skipBytes(3);
        int r3 = r7.readSynchSafeInt();
        r2 = r2 + (r3 + 10);
        r8.advancePeekPosition(r3);
        goto L3
    L5:
        r8.resetPeekPosition();
        r8.advancePeekPosition(r2);
        int r0 = 0;
        int r32 = r2;
    L6:
        r8.peekFully(r7.getData(), 0, 7);
        r7.setPosition(0);
        int r4 = r7.readUnsignedShort();
        if (r4 == 44096) goto L14;
        if (r4 == 44097) goto L14;
        r8.resetPeekPosition();
        r32 = r32 + 1;
        if ((r32 - r2) >= 8192) goto L12;
        r8.advancePeekPosition(r32);
        r0 = 0;
        goto L6
    L12:
        return false;
    L14:
        r0 = r0 + 1;
        if (r0 >= 4) goto L16;
        int r42 = Ac4Util.parseAc4SyncframeSize(r7.getData(), r4);
        if (r42 == (-1)) goto L19;
        r8.advancePeekPosition(r42 - 7);
        goto L6
    L19:
        return false;
    L16:
        return true;
    }
}
