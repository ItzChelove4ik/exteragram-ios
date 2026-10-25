package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.TrackOutput;

/* loaded from: classes4.dex */
public final class BaseMediaChunkOutput implements ChunkExtractor.TrackOutputProvider {
    private final SampleQueue[] sampleQueues;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] r1, SampleQueue[] r2) {
        this.trackTypes = r1;
        this.sampleQueues = r2;
    }

    public int[] getWriteIndices() {
        int[] r0 = new int[this.sampleQueues.length];
        int r1 = 0;
    L3:
        SampleQueue[] r2 = this.sampleQueues;
        if (r1 >= r2.length) goto L6;
        r0[r1] = r2[r1].getWriteIndex();
        r1 = r1 + 1;
        goto L3
    L6:
        return r0;
    }

    public void setSampleOffsetUs(long r4) {
        SampleQueue[] r3 = this.sampleQueues;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].setSampleOffsetUs(r4);
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.TrackOutputProvider
    public TrackOutput track(int r3, int r4) {
        int r32 = 0;
    L3:
        int[] r0 = this.trackTypes;
        if (r32 >= r0.length) goto L10;
        if (r4 == r0[r32]) goto L8;
        r32 = r32 + 1;
        goto L3
    L8:
        return this.sampleQueues[r32];
    L10:
        Log.e("BaseMediaChunkOutput", "Unmatched track of type: " + r4);
        return new DiscardingTrackOutput();
    }
}
