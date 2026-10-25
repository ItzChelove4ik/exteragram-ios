package androidx.media3.exoplayer.upstream;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceInputStream;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.upstream.Loader;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class ParsingLoadable<T> implements Loader.Loadable {
    private final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    public final long loadTaskId;
    private final Parser<? extends T> parser;
    private volatile T result;
    public final int type;

    public interface Parser<T> {
        T parse(Uri r1, InputStream r2);
    }

    public ParsingLoadable(DataSource r2, Uri r3, int r4, Parser<? extends T> r5) {
        this(r2, new DataSpec.Builder().setUri(r3).setFlags(1).build(), r4, r5);
    }

    public ParsingLoadable(DataSource r2, DataSpec r3, int r4, Parser<? extends T> r5) {
        this.dataSource = new StatsDataSource(r2);
        this.dataSpec = r3;
        this.type = r4;
        this.parser = r5;
        this.loadTaskId = LoadEventInfo.getNewId();
    }

    public long bytesLoaded() {
        return this.dataSource.getBytesRead();
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void cancelLoad() {
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.getLastResponseHeaders();
    }

    public final T getResult() {
        return this.result;
    }

    public Uri getUri() {
        return this.dataSource.getLastOpenedUri();
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public final void load() {
        this.dataSource.resetBytesRead();
        DataSourceInputStream r0 = new DataSourceInputStream(this.dataSource, this.dataSpec);
        r0.open();     // Catch: Throwable -> L6
        Uri r1 = (Uri) Assertions.checkNotNull(this.dataSource.getUri());     // Catch: Throwable -> L6
        this.result = this.parser.parse(r1, r0);     // Catch: Throwable -> L6
        Util.closeQuietly(r0);
        return;
    L6:
        th = move-exception;
        Util.closeQuietly(r0);
        throw th;
    }
}
