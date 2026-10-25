package okhttp3.internal;

import kotlin.Metadata;
import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Response;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.url._UrlKt;
import okio.FileSystem;
import okio.Path;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u0018\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\n\u0010\u001a\u001a\u00060\u001bR\u00020\u001cH\u0000\"(\u0010\t\u001a\u00020\u0005*\u00020\n2\u0006\u0010\b\u001a\u00020\u00058@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\"\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u0018\u0010\u0014\u001a\u00020\n*\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"buildCache", "Lokhttp3/Cache;", "file", "Lokio/Path;", "maxSize", _UrlKt.FRAGMENT_ENCODE_SET, "fileSystem", "Lokio/FileSystem;", "value", "idleAtNsAccessor", "Lokhttp3/internal/connection/RealConnection;", "getIdleAtNsAccessor", "(Lokhttp3/internal/connection/RealConnection;)J", "setIdleAtNsAccessor", "(Lokhttp3/internal/connection/RealConnection;J)V", "exchangeAccessor", "Lokhttp3/internal/connection/Exchange;", "Lokhttp3/Response;", "getExchangeAccessor", "(Lokhttp3/Response;)Lokhttp3/internal/connection/Exchange;", "connectionAccessor", "getConnectionAccessor", "(Lokhttp3/internal/connection/Exchange;)Lokhttp3/internal/connection/RealConnection;", "finishedAccessor", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Dispatcher;", "call", "Lokhttp3/internal/connection/RealCall$AsyncCall;", "Lokhttp3/internal/connection/RealCall;", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class NativeImageTestsAccessorsKt {
    public static final Cache buildCache(Path r1, long r2, FileSystem r4) {
        return new Cache(r4, r1, r2);
    }

    public static final void finishedAccessor(Dispatcher r0, RealCall.AsyncCall r1) {
        r0.finished$okhttp(r1);
    }

    public static final RealConnection getConnectionAccessor(Exchange r0) {
        return r0.getConnection$okhttp();
    }

    public static final Exchange getExchangeAccessor(Response r0) {
        return r0.exchange();
    }

    public static final long getIdleAtNsAccessor(RealConnection r2) {
        return r2.getIdleAtNs();
    }

    public static final void setIdleAtNsAccessor(RealConnection r0, long r1) {
        r0.setIdleAtNs(r1);
    }
}
