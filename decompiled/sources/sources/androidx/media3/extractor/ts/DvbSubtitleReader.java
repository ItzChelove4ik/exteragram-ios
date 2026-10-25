package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class DvbSubtitleReader implements ElementaryStreamReader {
    private int bytesToCheck;
    private final String containerMimeType;
    private final TrackOutput[] outputs;
    private int sampleBytesWritten;
    private long sampleTimeUs;
    private final List<TsPayloadReader.DvbSubtitleInfo> subtitleInfos;
    private boolean writingSample;

    public DvbSubtitleReader(List<TsPayloadReader.DvbSubtitleInfo> r1, String r2) {
        this.subtitleInfos = r1;
        this.containerMimeType = r2;
        this.outputs = new TrackOutput[r1.size()];
        this.sampleTimeUs = -9223372036854775807L;
    }

    private boolean checkNextByte(ParsableByteArray r3, int r4) {
        if (r3.bytesLeft() != 0) goto L6;
        return false;
    L6:
        if (r3.readUnsignedByte() == r4) goto L8;
        this.writingSample = false;
    L8:
        this.bytesToCheck--;
        return this.writingSample;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r7) {
        if (this.writingSample == true) goto L5;
        return;
    L5:
        if (this.bytesToCheck == 2) goto L7;
    L9:
        int r1 = 0;
        if (this.bytesToCheck == 1) goto L12;
    L14:
        int r0 = r7.getPosition();
        int r2 = r7.bytesLeft();
        TrackOutput[] r3 = this.outputs;
        int r4 = r3.length;
    L15:
        if (r1 >= r4) goto L17;
        TrackOutput r5 = r3[r1];
        r7.setPosition(r0);
        r5.sampleData(r7, r2);
        r1 = r1 + 1;
        goto L15
    L17:
        this.sampleBytesWritten += r2;
        return;
    L12:
        if (checkNextByte(r7, 0) == true) goto L14;
        return;
    L7:
        if (checkNextByte(r7, 32) == true) goto L9;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r6, TsPayloadReader.TrackIdGenerator r7) {
        int r0 = 0;
    L4:
        if (r0 >= this.outputs.length) goto L6;
        TsPayloadReader.DvbSubtitleInfo r1 = this.subtitleInfos.get(r0);
        r7.generateNewId();
        TrackOutput r2 = r6.track(r7.getTrackId(), 3);
        r2.format(new Format.Builder().setId(r7.getFormatId()).setContainerMimeType(this.containerMimeType).setSampleMimeType("application/dvbsubs").setInitializationData(Collections.singletonList(r1.initializationData)).setLanguage(r1.language).build());
        this.outputs[r0] = r2;
        r0 = r0 + 1;
        goto L4
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r11) {
        if (this.writingSample == true) goto L5;
        return;
    L5:
        if (this.sampleTimeUs == (-9223372036854775807L)) goto L7;
        boolean r112 = true;
    L8:
        Assertions.checkState(r112);
        TrackOutput[] r113 = this.outputs;
        int r1 = r113.length;
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L11;
        r113[r2].sampleMetadata(this.sampleTimeUs, 1, this.sampleBytesWritten, 0, null);
        r2 = r2 + 1;
        goto L9
    L11:
        this.writingSample = false;
        return;
    L7:
        r112 = false;
        goto L8
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long r1, int r3) {
        if ((r3 & 4) != 0) goto L5;
        return;
    L5:
        this.writingSample = true;
        this.sampleTimeUs = r1;
        this.sampleBytesWritten = 0;
        this.bytesToCheck = 2;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.writingSample = false;
        this.sampleTimeUs = -9223372036854775807L;
    }
}
