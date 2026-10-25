package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;

/* loaded from: classes4.dex */
public final class Id3Reader implements ElementaryStreamReader {
    private final String containerMimeType;
    private final ParsableByteArray id3Header;
    private TrackOutput output;
    private int sampleBytesRead;
    private int sampleSize;
    private long sampleTimeUs;
    private boolean writingSample;

    public Id3Reader(String r3) {
        this.containerMimeType = r3;
        this.id3Header = new ParsableByteArray(10);
        this.sampleTimeUs = -9223372036854775807L;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r8) {
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample == true) goto L5;
        return;
    L5:
        int r0 = r8.bytesLeft();
        int r1 = this.sampleBytesRead;
        if (r1 >= 10) goto L19;
        int r12 = Math.min(r0, 10 - r1);
        System.arraycopy(r8.getData(), r8.getPosition(), this.id3Header.getData(), this.sampleBytesRead, r12);
        if ((this.sampleBytesRead + r12) != 10) goto L19;
        this.id3Header.setPosition(0);
        if (73 == this.id3Header.readUnsignedByte()) goto L12;
    L17:
        Log.w("Id3Reader", "Discarding invalid ID3 tag");
        this.writingSample = false;
        return;
    L12:
        if (68 != this.id3Header.readUnsignedByte()) goto L17;
        if (51 != this.id3Header.readUnsignedByte()) goto L17;
        this.id3Header.skipBytes(3);
        this.sampleSize = this.id3Header.readSynchSafeInt() + 10;
    L19:
        int r02 = Math.min(r0, this.sampleSize - this.sampleBytesRead);
        this.output.sampleData(r8, r02);
        this.sampleBytesRead += r02;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r3, TsPayloadReader.TrackIdGenerator r4) {
        r4.generateNewId();
        TrackOutput r32 = r3.track(r4.getTrackId(), 5);
        this.output = r32;
        r32.format(new Format.Builder().setId(r4.getFormatId()).setContainerMimeType(this.containerMimeType).setSampleMimeType("application/id3").build());
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r9) {
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample == false) goto L15;
        int r92 = this.sampleSize;
        if (r92 != 0) goto L7;
        return;
    L7:
        if (this.sampleBytesRead == r92) goto L10;
        return;
    L10:
        if (this.sampleTimeUs == (-9223372036854775807L)) goto L12;
        boolean r93 = true;
    L13:
        Assertions.checkState(r93);
        this.output.sampleMetadata(this.sampleTimeUs, 1, this.sampleSize, 0, null);
        this.writingSample = false;
        return;
    L12:
        r93 = false;
        goto L13
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long r1, int r3) {
        if ((r3 & 4) != 0) goto L5;
        return;
    L5:
        this.writingSample = true;
        this.sampleTimeUs = r1;
        this.sampleSize = 0;
        this.sampleBytesRead = 0;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.writingSample = false;
        this.sampleTimeUs = -9223372036854775807L;
    }
}
