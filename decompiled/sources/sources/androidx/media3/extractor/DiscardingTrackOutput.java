package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;
import okio.Buffer$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class DiscardingTrackOutput implements TrackOutput {
    private final byte[] readBuffer;

    public DiscardingTrackOutput() {
        this.readBuffer = new byte[4096];
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void format(Format r1) {
    }

    @Override // androidx.media3.extractor.TrackOutput
    public int sampleData(DataReader r1, int r2, boolean r3, int r4) {
        int r22 = Math.min(this.readBuffer.length, r2);
        int r0 = r1.read(this.readBuffer, 0, r22);
        if (r0 != (-1)) goto L8;
        if (r3 == false) goto L6;
        return -1;
    L6:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    L8:
        return r0;
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleData(ParsableByteArray r1, int r2, int r3) {
        r1.skipBytes(r2);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleMetadata(long r1, int r3, int r4, int r5, TrackOutput.CryptoData r6) {
    }
}
