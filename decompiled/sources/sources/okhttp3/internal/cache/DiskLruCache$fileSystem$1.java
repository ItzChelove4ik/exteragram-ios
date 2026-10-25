package okhttp3.internal.cache;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.FileSystem;
import okio.ForwardingFileSystem;
import okio.Path;
import okio.Sink;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"okhttp3/internal/cache/DiskLruCache$fileSystem$1", "Lokio/ForwardingFileSystem;", "sink", "Lokio/Sink;", "file", "Lokio/Path;", "mustCreate", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DiskLruCache$fileSystem$1 extends ForwardingFileSystem {
    public DiskLruCache$fileSystem$1(FileSystem r1) {
        super(r1);
    }

    @Override // okio.ForwardingFileSystem, okio.FileSystem
    public Sink sink(Path r2, boolean r3) {
        Path r0 = r2.parent();
        if (r0 == null) goto L6;
        createDirectories(r0);
    L6:
        return super.sink(r2, r3);
    }
}
