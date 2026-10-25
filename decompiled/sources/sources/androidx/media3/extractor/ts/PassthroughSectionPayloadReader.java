package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes4.dex */
public final class PassthroughSectionPayloadReader implements SectionPayloadReader {
    private Format format;
    private TrackOutput output;
    private TimestampAdjuster timestampAdjuster;

    public PassthroughSectionPayloadReader(String r2, String r3) {
        this.format = new Format.Builder().setContainerMimeType(r3).setSampleMimeType(r2).build();
    }

    @EnsuresNonNull({"timestampAdjuster", "output"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.timestampAdjuster);
        Util.castNonNull(this.output);
    }

    @Override // androidx.media3.extractor.ts.SectionPayloadReader
    public void consume(ParsableByteArray r9) {
        assertInitialized();
        long r2 = this.timestampAdjuster.getLastAdjustedTimestampUs();
        long r0 = this.timestampAdjuster.getTimestampOffsetUs();
        if (r2 != (-9223372036854775807L)) goto L5;
        return;
    L5:
        if (r0 == (-9223372036854775807L)) goto L13;
        Format r4 = this.format;
        if (r0 == r4.subsampleOffsetUs) goto L10;
        Format r02 = r4.buildUpon().setSubsampleOffsetUs(r0).build();
        this.format = r02;
        this.output.format(r02);
    L10:
        int r5 = r9.bytesLeft();
        this.output.sampleData(r9, r5);
        this.output.sampleMetadata(r2, 1, r5, 0, null);
        return;
    }

    @Override // androidx.media3.extractor.ts.SectionPayloadReader
    public void init(TimestampAdjuster r1, ExtractorOutput r2, TsPayloadReader.TrackIdGenerator r3) {
        this.timestampAdjuster = r1;
        r3.generateNewId();
        TrackOutput r12 = r2.track(r3.getTrackId(), 5);
        this.output = r12;
        r12.format(this.format);
    }
}
