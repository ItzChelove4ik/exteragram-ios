package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;

/* loaded from: classes4.dex */
public abstract class BaseMediaChunk extends MediaChunk {
    public final long clippedEndTimeUs;
    public final long clippedStartTimeUs;
    private int[] firstSampleIndices;
    private BaseMediaChunkOutput output;

    public BaseMediaChunk(DataSource r13, DataSpec r14, Format r15, int r16, Object r17, long r18, long r20, long r22, long r24, long r26) {
        super(r13, r14, r15, r16, r17, r18, r20, r26);
        this.clippedStartTimeUs = r22;
        this.clippedEndTimeUs = r24;
    }

    public final int getFirstSampleIndex(int r1) {
        return ((int[]) Assertions.checkStateNotNull(this.firstSampleIndices))[r1];
    }

    public final BaseMediaChunkOutput getOutput() {
        return (BaseMediaChunkOutput) Assertions.checkStateNotNull(this.output);
    }

    public void init(BaseMediaChunkOutput r1) {
        this.output = r1;
        this.firstSampleIndices = r1.getWriteIndices();
    }
}
