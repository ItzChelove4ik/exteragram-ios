package okhttp3.internal.connection;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Socket;
import okio.Source;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"asBufferedSocket", "Lokhttp3/internal/connection/BufferedSocket;", "Ljava/net/Socket;", "Lokio/Socket;", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BufferedSocketKt {

    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"okhttp3/internal/connection/BufferedSocketKt$asBufferedSocket$1", "Lokhttp3/internal/connection/BufferedSocket;", "delegate", "Lokio/Socket;", "source", "Lokio/BufferedSource;", "getSource", "()Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "getSink", "()Lokio/BufferedSink;", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okhttp3.internal.connection.BufferedSocketKt$asBufferedSocket$1, reason: invalid class name */
    public static final class AnonymousClass1 implements BufferedSocket {
        private final Socket delegate;
        private final BufferedSink sink;
        private final BufferedSource source;

        public AnonymousClass1(Socket r2) {
            this.delegate = r2;
            this.source = Okio.buffer(r2.getSource());
            this.sink = Okio.buffer(r2.getSink());
        }

        @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
        public void cancel() {
            this.delegate.cancel();
        }

        @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
        public BufferedSink getSink() {
            return this.sink;
        }

        @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
        public /* bridge */ /* synthetic */ Sink getSink() {
            return getSink();
        }

        @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
        public BufferedSource getSource() {
            return this.source;
        }

        @Override // okhttp3.internal.connection.BufferedSocket, okio.Socket
        public /* bridge */ /* synthetic */ Source getSource() {
            return getSource();
        }
    }

    public static final BufferedSocket asBufferedSocket(java.net.Socket r0) {
        return asBufferedSocket(Okio.socket(r0));
    }

    public static final BufferedSocket asBufferedSocket(Socket r1) {
        return new AnonymousClass1(r1);
    }
}
