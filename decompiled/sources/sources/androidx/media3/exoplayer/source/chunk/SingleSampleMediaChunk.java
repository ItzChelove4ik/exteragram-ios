package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.TrackOutput;

@Deprecated
/* loaded from: classes4.dex */
public final class SingleSampleMediaChunk extends BaseMediaChunk {
    private boolean loadCompleted;
    private long nextLoadPosition;
    private final Format sampleFormat;
    private final int trackType;

    public SingleSampleMediaChunk(DataSource r17, DataSpec r18, Format r19, int r20, Object r21, long r22, long r24, long r26, int r28, Format r29) {
        super(r17, r18, r19, r20, r21, r22, r24, -9223372036854775807L, -9223372036854775807L, r26);
        this.trackType = r28;
        this.sampleFormat = r29;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public void cancelLoad() {
    }

    @Override // androidx.media3.exoplayer.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public void load() {
        BaseMediaChunkOutput r0 = getOutput();
        r0.setSampleOffsetUs(0);
        int r2 = 0;
        TrackOutput r3 = r0.track(0, this.trackType);
        r3.format(this.sampleFormat);
        long r02 = this.dataSource.open(this.dataSpec.subrange(this.nextLoadPosition));     // Catch: Throwable -> L7
        if (r02 == (-1)) goto L9;
        r02 = r02 + this.nextLoadPosition;     // Catch: Throwable -> L7
    L9:
        DefaultExtractorInput r4 = new DefaultExtractorInput(this.dataSource, this.nextLoadPosition, r02);     // Catch: Throwable -> L7
    L10:
        long r03 = this.nextLoadPosition;
        if (r2 == (-1)) goto L14;
        this.nextLoadPosition = r03 + r2;     // Catch: Throwable -> L7
        r2 = r3.sampleData(r4, Integer.MAX_VALUE, true);     // Catch: Throwable -> L7
        goto L10
    L14:
        r3.sampleMetadata(this.startTimeUs, 1, (int) r03, 0, null);     // Catch: Throwable -> L7
        DataSourceUtil.closeQuietly(this.dataSource);
        this.loadCompleted = true;
        return;
    L7:
        th = move-exception;
        DataSourceUtil.closeQuietly(this.dataSource);
        throw th;
    }
}
