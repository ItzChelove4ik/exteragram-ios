package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.ConnectionListener;
import okhttp3.internal.connection.RealConnectionPool;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B;\b\u0010\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0004\u0010\u0010B1\b\u0010\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0004\u0010\u0011B!\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0004\u0010\u0012B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0013J\u0006\u0010\u0016\u001a\u00020\u0007J\u0006\u0010\u0017\u001a\u00020\u0007J\u0006\u0010\u001a\u001a\u00020\u001bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u000e\u001a\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lokhttp3/ConnectionPool;", _UrlKt.FRAGMENT_ENCODE_SET, "delegate", "Lokhttp3/internal/connection/RealConnectionPool;", "<init>", "(Lokhttp3/internal/connection/RealConnectionPool;)V", "maxIdleConnections", _UrlKt.FRAGMENT_ENCODE_SET, "keepAliveDuration", _UrlKt.FRAGMENT_ENCODE_SET, "timeUnit", "Ljava/util/concurrent/TimeUnit;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "connectionListener", "Lokhttp3/internal/connection/ConnectionListener;", "(IJLjava/util/concurrent/TimeUnit;Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/internal/connection/ConnectionListener;)V", "(IJLjava/util/concurrent/TimeUnit;Lokhttp3/internal/connection/ConnectionListener;)V", "(IJLjava/util/concurrent/TimeUnit;)V", "()V", "getDelegate$okhttp", "()Lokhttp3/internal/connection/RealConnectionPool;", "idleConnectionCount", "connectionCount", "getConnectionListener$okhttp", "()Lokhttp3/internal/connection/ConnectionListener;", "evictAll", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ConnectionPool {
    private final RealConnectionPool delegate;

    public ConnectionPool() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public ConnectionPool(int r8, long r9, TimeUnit r11) {
        this(r8, r9, r11, TaskRunner.INSTANCE, ConnectionListener.Companion.getNONE());
    }

    public ConnectionPool(int r8, long r9, TimeUnit r11, TaskRunner r12, ConnectionListener r13) {
        this(new RealConnectionPool(r12, r8, r9, r11, r13));
    }

    public /* synthetic */ ConnectionPool(int r1, long r2, TimeUnit r4, TaskRunner r5, ConnectionListener r6, int r7, DefaultConstructorMarker r8) {
        if ((r7 & 1) == 0) goto L6;
        r1 = 5;
    L6:
        if ((r7 & 2) == 0) goto L9;
        r2 = 5;
    L9:
        if ((r7 & 4) == 0) goto L12;
        r4 = TimeUnit.MINUTES;
    L12:
        if ((r7 & 8) == 0) goto L15;
        r5 = TaskRunner.INSTANCE;
    L15:
        if ((r7 & 16) == 0) goto L17;
        r6 = ConnectionListener.Companion.getNONE();
    L17:
        ConnectionListener r82 = r6;
        TimeUnit r62 = r4;
        long r42 = r2;
        int r3 = r1;
        this(r3, r42, r62, r5, r82);
    }

    public ConnectionPool(int r8, long r9, TimeUnit r11, ConnectionListener r12) {
        this(r8, r9, r11, TaskRunner.INSTANCE, r12);
    }

    public /* synthetic */ ConnectionPool(int r1, long r2, TimeUnit r4, ConnectionListener r5, int r6, DefaultConstructorMarker r7) {
        if ((r6 & 1) == 0) goto L6;
        r1 = 5;
    L6:
        if ((r6 & 2) == 0) goto L9;
        r2 = 5;
    L9:
        if ((r6 & 4) == 0) goto L12;
        r4 = TimeUnit.MINUTES;
    L12:
        if ((r6 & 8) == 0) goto L14;
        r5 = ConnectionListener.Companion.getNONE();
    L14:
        long r42 = r2;
        int r3 = r1;
        this(r3, r42, r4, r5);
    }

    public ConnectionPool(RealConnectionPool r1) {
        this.delegate = r1;
    }

    public final int connectionCount() {
        return this.delegate.connectionCount();
    }

    public final void evictAll() {
        this.delegate.evictAll();
    }

    public final ConnectionListener getConnectionListener$okhttp() {
        return this.delegate.getConnectionListener$okhttp();
    }

    public final RealConnectionPool getDelegate$okhttp() {
        return this.delegate;
    }

    public final int idleConnectionCount() {
        return this.delegate.idleConnectionCount();
    }
}
