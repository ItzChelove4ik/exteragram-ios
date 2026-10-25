package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.DefaultExtractorInput;

/* loaded from: classes4.dex */
public final class InitializationChunk extends Chunk {
    private final ChunkExtractor chunkExtractor;
    private ChunkIndex chunkIndex;
    private volatile boolean loadCanceled;
    private long nextLoadPosition;
    private ChunkExtractor.TrackOutputProvider trackOutputProvider;

    public InitializationChunk(DataSource r12, DataSpec r13, Format r14, int r15, Object r16, ChunkExtractor r17) {
        super(r12, r13, 2, r14, r15, r16, -9223372036854775807L, -9223372036854775807L);
        this.chunkExtractor = r17;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void init(ChunkExtractor.TrackOutputProvider r1) {
        this.trackOutputProvider = r1;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public void load() {
        if (this.nextLoadPosition != 0) goto L22;
        this.chunkExtractor.init(this.trackOutputProvider, -9223372036854775807L, -9223372036854775807L);
    L22:
        DataSpec r0 = this.dataSpec.subrange(this.nextLoadPosition);     // Catch: Throwable -> L16
        StatsDataSource r2 = this.dataSource;     // Catch: Throwable -> L16
        DefaultExtractorInput r1 = new DefaultExtractorInput(r2, r0.position, r2.open(r0));     // Catch: Throwable -> L16
    L23:
        if (this.loadCanceled == true) goto L13;
        if (this.chunkExtractor.read(r1) == true) goto L23;
    L13:
        this.nextLoadPosition = r1.getPosition() - this.dataSpec.position;     // Catch: Throwable -> L16
        this.chunkIndex = this.chunkExtractor.getChunkIndex();     // Catch: Throwable -> L16
        DataSourceUtil.closeQuietly(this.dataSource);
        return;
    L11:
        th = move-exception;
        this.nextLoadPosition = r1.getPosition() - this.dataSpec.position;     // Catch: Throwable -> L16
        this.chunkIndex = this.chunkExtractor.getChunkIndex();     // Catch: Throwable -> L16
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        DataSourceUtil.closeQuietly(this.dataSource);
        throw th;
    }
}
