package okhttp3.internal.connection;

import kotlin.Metadata;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Sink;
import okio.Socket;
import okio.Source;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lokhttp3/internal/connection/BufferedSocket;", "Lokio/Socket;", "source", "Lokio/BufferedSource;", "getSource", "()Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "getSink", "()Lokio/BufferedSink;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public interface BufferedSocket extends Socket {
    @Override // okio.Socket
    /* synthetic */ void cancel();

    @Override // okio.Socket
    BufferedSink getSink();

    @Override // okio.Socket
    /* synthetic */ Sink getSink();

    @Override // okio.Socket
    BufferedSource getSource();

    @Override // okio.Socket
    /* synthetic */ Source getSource();
}
