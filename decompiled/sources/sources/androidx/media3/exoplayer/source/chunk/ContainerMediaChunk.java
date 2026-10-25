package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.TrackOutput;

/* loaded from: classes4.dex */
public class ContainerMediaChunk extends BaseMediaChunk {
    private final int chunkCount;
    private final ChunkExtractor chunkExtractor;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private long nextLoadPosition;
    private final long sampleOffsetUs;

    public ContainerMediaChunk(DataSource r1, DataSpec r2, Format r3, int r4, Object r5, long r6, long r8, long r10, long r12, long r14, int r16, long r17, ChunkExtractor r19) {
        super(r1, r2, r3, r4, r5, r6, r8, r10, r12, r14);
        this.chunkCount = r16;
        this.sampleOffsetUs = r17;
        this.chunkExtractor = r19;
    }

    private void maybeWriteEmptySamples(BaseMediaChunkOutput r11) {
        if (MimeTypes.isImage(this.trackFormat.containerMimeType) == false) goto L20;
        Format r0 = this.trackFormat;
        int r1 = r0.tileCountHorizontal;
        int r2 = 1;
        if (r1 > 1) goto L10;
        if (r0.tileCountVertical > 1) goto L10;
        return;
    L10:
        if (r1 != (-1)) goto L12;
        return;
    L12:
        if (r0.tileCountVertical == (-1)) goto L21;
        TrackOutput r3 = r11.track(0, 4);
        Format r112 = this.trackFormat;
        int r02 = r112.tileCountHorizontal * r112.tileCountVertical;
        long r10 = (this.endTimeUs - this.startTimeUs) / r02;
    L15:
        if (r2 >= r02) goto L22;
        r3.sampleData(new ParsableByteArray(), 0);
        r3.sampleMetadata(r2 * r10, 0, 0, 0, null);
        r2 = r2 + 1;
        goto L15
    L22:
        return;
    L21:
        return;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // androidx.media3.exoplayer.source.chunk.MediaChunk
    public long getNextChunkIndex() {
        return this.chunkIndex + this.chunkCount;
    }

    public ChunkExtractor.TrackOutputProvider getTrackOutputProvider(BaseMediaChunkOutput r1) {
        return r1;
    }

    @Override // androidx.media3.exoplayer.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void load() {
        BaseMediaChunkOutput r0 = getOutput();
        if (this.nextLoadPosition != 0) goto L31;
        r0.setSampleOffsetUs(this.sampleOffsetUs);
        ChunkExtractor r3 = this.chunkExtractor;
        ChunkExtractor.TrackOutputProvider r4 = getTrackOutputProvider(r0);
        long r1 = this.clippedStartTimeUs;
        long r5 = -9223372036854775807L;
        if (r1 != (-9223372036854775807L)) goto L7;
        long r12 = -9223372036854775807L;
    L8:
        long r7 = this.clippedEndTimeUs;
        if (r7 == (-9223372036854775807L)) goto L12;
        r5 = r7 - this.sampleOffsetUs;
    L12:
        r3.init(r4, r12, r5);
        goto L31
    L7:
        r12 = r1 - this.sampleOffsetUs;
    L31:
        DataSpec r13 = this.dataSpec.subrange(this.nextLoadPosition);     // Catch: Throwable -> L25
        StatsDataSource r32 = this.dataSource;     // Catch: Throwable -> L25
        DefaultExtractorInput r2 = new DefaultExtractorInput(r32, r13.position, r32.open(r13));     // Catch: Throwable -> L25
    L32:
    L19:
        th = move-exception;
        this.nextLoadPosition = r2.getPosition() - this.dataSpec.position;     // Catch: Throwable -> L25
        throw th;     // Catch: Throwable -> L25
    L15:
        if (this.loadCanceled == true) goto L21;
        if (this.chunkExtractor.read(r2) == true) goto L32;
    L21:
        maybeWriteEmptySamples(r0);     // Catch: Throwable -> L19
        this.nextLoadPosition = r2.getPosition() - this.dataSpec.position;     // Catch: Throwable -> L25
        onLoadEnded();
        DataSourceUtil.closeQuietly(this.dataSource);
        this.loadCompleted = !this.loadCanceled;
        return;
    L25:
        th = move-exception;
        onLoadEnded();
        DataSourceUtil.closeQuietly(this.dataSource);
        throw th;
    }

    public void onLoadEnded() {
    }
}
