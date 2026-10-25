package androidx.media3.exoplayer.source.chunk;

import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.util.List;

/* loaded from: classes4.dex */
public interface ChunkSource {
    long getAdjustedSeekPositionUs(long r1, SeekParameters r3);

    void getNextChunk(LoadingInfo r1, long r2, List<? extends MediaChunk> r4, ChunkHolder r5);

    int getPreferredQueueSize(long r1, List<? extends MediaChunk> r3);

    void maybeThrowError();

    void onChunkLoadCompleted(Chunk r1);

    boolean onChunkLoadError(Chunk r1, boolean r2, LoadErrorHandlingPolicy.LoadErrorInfo r3, LoadErrorHandlingPolicy r4);

    void release();

    boolean shouldCancelLoad(long r1, Chunk r3, List<? extends MediaChunk> r4);
}
