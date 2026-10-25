package okhttp3.internal.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"okhttp3/internal/cache/CacheInterceptor$cacheWritingResponse$cacheWritingSource$1", "Lokio/Source;", "cacheRequestClosed", _UrlKt.FRAGMENT_ENCODE_SET, "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CacheInterceptor$cacheWritingResponse$cacheWritingSource$1 implements Source {
    final /* synthetic */ BufferedSink $cacheBody;
    final /* synthetic */ CacheRequest $cacheRequest;
    final /* synthetic */ BufferedSource $source;
    private boolean cacheRequestClosed;

    public CacheInterceptor$cacheWritingResponse$cacheWritingSource$1(BufferedSource r1, CacheRequest r2, BufferedSink r3) {
        this.$source = r1;
        this.$cacheRequest = r2;
        this.$cacheBody = r3;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.cacheRequestClosed == false) goto L5;
    L7:
        this.$source.close();
        return;
    L5:
        if (_UtilJvmKt.discard(this, 100, TimeUnit.MILLISECONDS) == true) goto L7;
        this.cacheRequestClosed = true;
        this.$cacheRequest.abort();
        goto L7
    }

    @Override // okio.Source
    public long read(Buffer r9, long r10) {
        long r6 = this.$source.read(r9, r10);     // Catch: IOException -> L12
        if (r6 == (-1)) goto L7;
        r9.copyTo(this.$cacheBody.getBuffer(), r9.size() - r6, r6);
        this.$cacheBody.emitCompleteSegments();
        return r6;
    L7:
        if (this.cacheRequestClosed == true) goto L9;
        this.cacheRequestClosed = true;
        this.$cacheBody.close();
    L9:
        return -1;
    L12:
        e = move-exception;
        if (this.cacheRequestClosed == true) goto L19;
        this.cacheRequestClosed = true;
        this.$cacheRequest.abort();
        throw e;
    L19:
        throw e;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.$source.timeout();
    }
}
