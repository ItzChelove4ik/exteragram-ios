package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;

/* loaded from: classes4.dex */
public abstract class MediaChunk extends Chunk {
    public final long chunkIndex;

    public MediaChunk(DataSource r12, DataSpec r13, Format r14, int r15, Object r16, long r17, long r19, long r21) {
        super(r12, r13, 1, r14, r15, r16, r17, r19);
        Assertions.checkNotNull(r14);
        this.chunkIndex = r21;
    }

    public long getNextChunkIndex() {
        long r0 = this.chunkIndex;
        if (r0 != (-1)) goto L5;
        return -1;
    L5:
        return r0 + 1;
    }

    public abstract boolean isLoadCompleted();
}
