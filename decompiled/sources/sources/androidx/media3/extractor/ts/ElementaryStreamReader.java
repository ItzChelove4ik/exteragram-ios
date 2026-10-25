package androidx.media3.extractor.ts;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;

/* loaded from: classes4.dex */
public interface ElementaryStreamReader {
    void consume(ParsableByteArray r1);

    void createTracks(ExtractorOutput r1, TsPayloadReader.TrackIdGenerator r2);

    void packetFinished(boolean r1);

    void packetStarted(long r1, int r3);

    void seek();
}
