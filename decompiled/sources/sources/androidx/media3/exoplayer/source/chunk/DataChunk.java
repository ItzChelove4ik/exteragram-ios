package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import java.util.Arrays;

/* loaded from: classes4.dex */
public abstract class DataChunk extends Chunk {
    private byte[] data;
    private volatile boolean loadCanceled;

    public DataChunk(DataSource r12, DataSpec r13, int r14, Format r15, int r16, Object r17, byte[] r18) {
        super(r12, r13, r14, r15, r16, r17, -9223372036854775807L, -9223372036854775807L);
        if (r18 != null) goto L5;
        byte[] r122 = Util.EMPTY_BYTE_ARRAY;
    L6:
        this.data = r122;
        return;
    L5:
        r122 = r18;
        goto L6
    }

    private void maybeExpandData(int r3) {
        byte[] r0 = this.data;
        if (r0.length >= (r3 + 16384)) goto L6;
        this.data = Arrays.copyOf(r0, r0.length + 16384);
        return;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    public abstract void consume(byte[] r1, int r2);

    public byte[] getDataHolder() {
        return this.data;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void load() {
        this.dataSource.open(this.dataSpec);     // Catch: Throwable -> L10
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 == (-1)) goto L13;
        if (this.loadCanceled == true) goto L13;
        maybeExpandData(r1);     // Catch: Throwable -> L10
        r0 = this.dataSource.read(this.data, r1, 16384);     // Catch: Throwable -> L10
        if (r0 == (-1)) goto L4;
        r1 = r1 + r0;     // Catch: Throwable -> L10
    L13:
        if (this.loadCanceled == true) goto L15;
        consume(this.data, r1);     // Catch: Throwable -> L10
    L15:
        DataSourceUtil.closeQuietly(this.dataSource);
        return;
    L10:
        th = move-exception;
        DataSourceUtil.closeQuietly(this.dataSource);
        throw th;
    }
}
