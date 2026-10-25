package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class StatsDataSource implements DataSource {
    private long bytesRead;
    private final DataSource dataSource;
    private Uri lastOpenedUri;
    private Map<String, List<String>> lastResponseHeaders;

    public StatsDataSource(DataSource r1) {
        this.dataSource = (DataSource) Assertions.checkNotNull(r1);
        this.lastOpenedUri = Uri.EMPTY;
        this.lastResponseHeaders = Collections.EMPTY_MAP;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener r1) {
        Assertions.checkNotNull(r1);
        this.dataSource.addTransferListener(r1);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        this.dataSource.close();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public Uri getLastOpenedUri() {
        return this.lastOpenedUri;
    }

    public Map<String, List<String>> getLastResponseHeaders() {
        return this.lastResponseHeaders;
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.getResponseHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.dataSource.getUri();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r3) {
        this.lastOpenedUri = r3.uri;
        this.lastResponseHeaders = Collections.EMPTY_MAP;
        long r0 = this.dataSource.open(r3);     // Catch: Throwable -> L9
        Uri r32 = getUri();
        if (r32 == null) goto L7;
        this.lastOpenedUri = r32;
    L7:
        this.lastResponseHeaders = getResponseHeaders();
        return r0;
    L9:
        th = move-exception;
        Uri r02 = getUri();
        if (r02 == null) goto L13;
        this.lastOpenedUri = r02;
    L13:
        this.lastResponseHeaders = getResponseHeaders();
        throw th;
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r3, int r4, int r5) {
        int r32 = this.dataSource.read(r3, r4, r5);
        if (r32 == (-1)) goto L5;
        this.bytesRead += r32;
    L5:
        return r32;
    }

    public void resetBytesRead() {
        this.bytesRead = 0;
    }
}
