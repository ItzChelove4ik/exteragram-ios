package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSink;
import okio.Okio;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DiskLruCache$cleanupTask$1 extends Task {
    final /* synthetic */ DiskLruCache this$0;

    public DiskLruCache$cleanupTask$1(DiskLruCache r3, String r4) {
        this.this$0 = r3;
        super(r4, false, 2, null);
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        DiskLruCache r4 = this.this$0;
        monitor-enter(r4);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (DiskLruCache.access$getInitialized$p(r4) == true) goto L7;
    L25:
        monitor-exit(r4);
        return -1;
    L7:
        if (r4.getClosed$okhttp() == true) goto L25;
        r4.trimToSize();     // Catch: Throwable -> L12 IOException -> L14
    L31:
    L19:
        DiskLruCache.access$setMostRecentRebuildFailed$p(r4, true);     // Catch: Throwable -> L12
        BufferedSink r0 = DiskLruCache.access$getJournalWriter$p(r4);     // Catch: Throwable -> L12
        if (r0 == null) goto L22;
        _UtilCommonKt.closeQuietly(r0);     // Catch: Throwable -> L12
    L22:
        DiskLruCache.access$setJournalWriter$p(r4, Okio.buffer(Okio.blackhole()));     // Catch: Throwable -> L12
    L23:
        monitor-exit(r4);
        return -1;
    L16:
        if (DiskLruCache.access$journalRebuildRequired(r4) == false) goto L23;
        r4.rebuildJournal$okhttp();     // Catch: Throwable -> L12 IOException -> L19
        DiskLruCache.access$setRedundantOpCount$p(r4, 0);     // Catch: Throwable -> L12 IOException -> L19
    L14:
        DiskLruCache.access$setMostRecentTrimFailed$p(r4, true);     // Catch: Throwable -> L12
        goto L31
    }
}
