package okhttp3.internal.connection;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0005¨\u0006\n"}, d2 = {"okhttp3/internal/connection/RealConnection$Companion$newTestConnection$bufferedSocket$1", "Lokhttp3/internal/connection/BufferedSocket;", "sink", "Lokio/Buffer;", "getSink", "()Lokio/Buffer;", "source", "getSource", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class RealConnection$Companion$newTestConnection$bufferedSocket$1 implements BufferedSocket {
    private final Buffer sink;
    private final Buffer source;

    public RealConnection$Companion$newTestConnection$bufferedSocket$1() {
        this.sink = new Buffer();
        this.source = new Buffer();
    }

    @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
    public void cancel() {
    }

    @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
    public Buffer getSink() {
        return this.sink;
    }

    @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
    public /* bridge */ /* synthetic */ BufferedSink getSink() {
        return getSink();
    }

    @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
    public /* bridge */ /* synthetic */ Sink getSink() {
        return getSink();
    }

    @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
    public Buffer getSource() {
        return this.source;
    }

    @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
    public /* bridge */ /* synthetic */ BufferedSource getSource() {
        return getSource();
    }

    @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
    public /* bridge */ /* synthetic */ Source getSource() {
        return getSource();
    }
}
