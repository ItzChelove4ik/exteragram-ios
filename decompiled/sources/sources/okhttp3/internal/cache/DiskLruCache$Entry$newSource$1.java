package okhttp3.internal.cache;

import kotlin.Metadata;
import kotlin.Unit;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.url._UrlKt;
import okio.ForwardingSource;
import okio.Source;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"okhttp3/internal/cache/DiskLruCache$Entry$newSource$1", "Lokio/ForwardingSource;", "closed", _UrlKt.FRAGMENT_ENCODE_SET, "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DiskLruCache$Entry$newSource$1 extends ForwardingSource {
    private boolean closed;
    final /* synthetic */ DiskLruCache this$0;
    final /* synthetic */ DiskLruCache.Entry this$1;

    public DiskLruCache$Entry$newSource$1(Source r1, DiskLruCache r2, DiskLruCache.Entry r3) {
        this.this$0 = r2;
        this.this$1 = r3;
        super(r1);
    }

    @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        if (this.closed == true) goto L18;
        this.closed = true;
        DiskLruCache r0 = this.this$0;
        DiskLruCache.Entry r2 = this.this$1;
        monitor-enter(r0);
        r2.setLockingSourceCount$okhttp(r2.getLockingSourceCount$okhttp() - 1);     // Catch: Throwable -> L11
        if (r2.getLockingSourceCount$okhttp() == 0) goto L9;
    L13:
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r0);
        return;
    L9:
        if (r2.getZombie$okhttp() == false) goto L13;
        r0.removeEntry$okhttp(r2);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }
}
