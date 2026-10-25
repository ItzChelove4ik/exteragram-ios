package okhttp3.internal.connection;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.Route;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline3;

@Metadata(d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t*\u0001\u0016\u0018\u0000 22\u00020\u0001:\u00012B1\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u001b\u001a\u00020\u0005J\u0006\u0010\u001c\u001a\u00020\u0005J?\u0010\u001d\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%2\u0006\u0010'\u001a\u00020\u001fH\u0000¢\u0006\u0002\b(J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u001aJ\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u001aJ\u0006\u0010-\u001a\u00020*J\u000e\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0007J\u0018\u00100\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u0007H\u0002J\u0006\u00101\u001a\u00020*R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool;", _UrlKt.FRAGMENT_ENCODE_SET, "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "maxIdleConnections", _UrlKt.FRAGMENT_ENCODE_SET, "keepAliveDuration", _UrlKt.FRAGMENT_ENCODE_SET, "timeUnit", "Ljava/util/concurrent/TimeUnit;", "connectionListener", "Lokhttp3/internal/connection/ConnectionListener;", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;IJLjava/util/concurrent/TimeUnit;Lokhttp3/internal/connection/ConnectionListener;)V", "getConnectionListener$okhttp", "()Lokhttp3/internal/connection/ConnectionListener;", "keepAliveDurationNs", "getKeepAliveDurationNs$okhttp", "()J", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/connection/RealConnectionPool$cleanupTask$1", "Lokhttp3/internal/connection/RealConnectionPool$cleanupTask$1;", "connections", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lokhttp3/internal/connection/RealConnection;", "idleConnectionCount", "connectionCount", "callAcquirePooledConnection", "doExtensiveHealthChecks", _UrlKt.FRAGMENT_ENCODE_SET, "address", "Lokhttp3/Address;", "call", "Lokhttp3/internal/connection/RealCall;", "routes", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Route;", "requireMultiplexed", "callAcquirePooledConnection$okhttp", "put", _UrlKt.FRAGMENT_ENCODE_SET, "connection", "connectionBecameIdle", "evictAll", "closeConnections", "now", "pruneAndGetAllocationCount", "scheduleCloser", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRealConnectionPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealConnectionPool.kt\nokhttp3/internal/connection/RealConnectionPool\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,326:1\n1#2:327\n1788#3,3:328\n1791#3:332\n63#4:331\n63#4:333\n63#4:334\n55#4,4:335\n55#4,4:339\n63#4:343\n63#4:344\n63#4:345\n55#4,4:346\n*S KotlinDebug\n*F\n+ 1 RealConnectionPool.kt\nokhttp3/internal/connection/RealConnectionPool\n*L\n64#1:328,3\n64#1:332\n65#1:331\n92#1:333\n111#1:334\n127#1:335,4\n139#1:339,4\n157#1:343\n201#1:344\n247#1:345\n287#1:346,4\n*E\n"})
/* loaded from: classes.dex */
public final class RealConnectionPool {
    public static final Companion Companion = null;
    private final TaskQueue cleanupQueue;
    private final RealConnectionPool$cleanupTask$1 cleanupTask;
    private final ConnectionListener connectionListener;
    private final ConcurrentLinkedQueue<RealConnection> connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "get", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/ConnectionPool;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final RealConnectionPool get(ConnectionPool r1) {
            return r1.getDelegate$okhttp();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public RealConnectionPool(TaskRunner r1, int r2, long r3, TimeUnit r5, ConnectionListener r6) {
        this.maxIdleConnections = r2;
        this.connectionListener = r6;
        this.keepAliveDurationNs = r5.toNanos(r3);
        this.cleanupQueue = r1.newQueue();
        this.cleanupTask = new RealConnectionPool$cleanupTask$1(this, _UtilJvmKt.okHttpName + " ConnectionPool connection closer");
        this.connections = new ConcurrentLinkedQueue();
        if (r3 <= 0) goto L5;
        return;
    L5:
        Buffer$$ExternalSyntheticBUOutline3.m("keepAliveDuration <= 0: ", r3);
        throw null;
    }

    private final int pruneAndGetAllocationCount(RealConnection r7, long r8) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        List<Reference<RealCall>> r0 = r7.getCalls();
        int r2 = 0;
    L11:
        if (r2 >= r0.size()) goto L20;
        Reference<RealCall> r3 = r0.get(r2);
        if (r3.get() != null) goto L14;
        Platform.Companion.get().logCloseableLeak("A connection to " + r7.route().address().url() + " was leaked. Did you forget to close a response body?", ((RealCall.CallReference) r3).getCallStackTrace());
        r0.remove(r2);
        if (r0.isEmpty() == false) goto L11;
        r7.setIdleAtNs(r8 - this.keepAliveDurationNs);
        return 0;
    L14:
        r2 = r2 + 1;
        goto L11
    L20:
        return r0.size();
    L5:
        if (Thread.holdsLock(r7) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r7);
        return 0;
    }

    public final RealConnection callAcquirePooledConnection$okhttp(boolean r6, Address r7, RealCall r8, List<Route> r9, boolean r10) {
        Iterator<RealConnection> r0 = this.connections.iterator();
    L4:
        if (r0.hasNext() == false) goto L36;
        RealConnection r1 = r0.next();
        monitor-enter(r1);
        boolean r3 = false;
        if (r10 == false) goto L15;
        if (r1.isMultiplexed$okhttp() == true) goto L15;
    L19:
        monitor-exit(r1);
        if (r3 == false) goto L4;
        if (r1.isHealthy(r6) == true) goto L23;
        monitor-enter(r1);
        boolean r32 = r1.getNoNewExchanges();     // Catch: Throwable -> L31
        r1.setNoNewExchanges(true);     // Catch: Throwable -> L31
        Socket r2 = r8.releaseConnectionNoEvents$okhttp();     // Catch: Throwable -> L31
        monitor-exit(r1);
        if (r2 != null) goto L28;
        if (r32 == true) goto L4;
        this.connectionListener.noNewExchanges(r1);
        goto L4
    L28:
        _UtilJvmKt.closeQuietly(r2);
        this.connectionListener.connectionClosed(r1);
    L31:
        th = move-exception;
        throw th;
    L23:
        return r1;
    L12:
        th = move-exception;
        throw th;
    L15:
        if (r1.isEligible$okhttp(r7, r9) == false) goto L19;
        r8.acquireConnectionNoEvents(r1);     // Catch: Throwable -> L12
        r3 = true;
        goto L19
    L36:
        return null;
    }

    public final long closeConnections(long r18) {
        long r3 = (r18 - this.keepAliveDurationNs) + 1;
        Iterator<RealConnection> r5 = this.connections.iterator();
        RealConnection r6 = null;
        long r7 = LongCompanionObject.MAX_VALUE;
        int r9 = 0;
        RealConnection r11 = null;
        RealConnection r12 = null;
        int r10 = 0;
    L4:
        if (r5.hasNext() == false) goto L23;
        RealConnection r13 = r5.next();
        monitor-enter(r13);
        if (pruneAndGetAllocationCount(r13, r18) <= 0) goto L10;
        r10 = r10 + 1;     // Catch: Throwable -> L19
    L16:
        Unit r14 = Unit.INSTANCE;     // Catch: Throwable -> L19
        monitor-exit(r13);
        goto L4
    L10:
        long r142 = r13.getIdleAtNs();     // Catch: Throwable -> L19
        if (r142 >= r3) goto L13;
        r11 = r13;
        r3 = r142;
    L13:
        r9 = r9 + 1;     // Catch: Throwable -> L19
        if (r142 >= r7) goto L16;
        r12 = r13;
        r7 = r142;
    L19:
        th = move-exception;
        throw th;
    L23:
        if (r11 == null) goto L26;
        r6 = r11;
    L29:
        if (r6 == null) goto L51;
        monitor-enter(r6);
    L48:
        th = move-exception;
        throw th;
    L33:
        if (r6.getCalls().isEmpty() == true) goto L38;
        monitor-exit(r6);
        return 0;
    L38:
        if (r6.getIdleAtNs() == r3) goto L42;
        monitor-exit(r6);
        return 0;
    L42:
        r6.setNoNewExchanges(true);     // Catch: Throwable -> L48
        this.connections.remove(r6);     // Catch: Throwable -> L48
        monitor-exit(r6);
        _UtilJvmKt.closeQuietly(r6.socket());
        this.connectionListener.connectionClosed(r6);
        if (this.connections.isEmpty() == false) goto L47;
        this.cleanupQueue.cancelAll();
    L47:
        return 0;
    L51:
        if (r12 != null) goto L53;
        if (r10 > 0) goto L56;
        return -1;
    L56:
        return this.keepAliveDurationNs;
    L53:
        return (r7 + this.keepAliveDurationNs) - r18;
    L26:
        if (r9 <= this.maxIdleConnections) goto L28;
        r3 = r7;
        r6 = r12;
        goto L29
    L28:
        r3 = -1;
        goto L29
    }

    public final boolean connectionBecameIdle(RealConnection r3) {
        if (_UtilJvmKt.assertionsEnabled == false) goto L10;
        if (Thread.holdsLock(r3) == true) goto L10;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r3);
        return false;
    L10:
        if (r3.getNoNewExchanges() == false) goto L12;
    L16:
        r3.setNoNewExchanges(true);
        this.connections.remove(r3);
        if (this.connections.isEmpty() == false) goto L19;
        this.cleanupQueue.cancelAll();
    L19:
        return true;
    L12:
        if (this.maxIdleConnections == 0) goto L16;
        scheduleCloser();
        return false;
    }

    public final int connectionCount() {
        return this.connections.size();
    }

    public final void evictAll() {
        Iterator<RealConnection> r0 = this.connections.iterator();
    L4:
        if (r0.hasNext() == false) goto L20;
        RealConnection r1 = r0.next();
        monitor-enter(r1);
        if (r1.getCalls().isEmpty() == false) goto L13;
        r0.remove();     // Catch: Throwable -> L11
        r1.setNoNewExchanges(true);     // Catch: Throwable -> L11
        Socket r2 = r1.socket();     // Catch: Throwable -> L11
    L14:
        monitor-exit(r1);
        if (r2 == null) goto L4;
        _UtilJvmKt.closeQuietly(r2);
        this.connectionListener.connectionClosed(r1);
        goto L4
    L13:
        r2 = null;
    L11:
        th = move-exception;
        throw th;
    L20:
        if (this.connections.isEmpty() == false) goto L30;
        this.cleanupQueue.cancelAll();
        return;
    }

    public final ConnectionListener getConnectionListener$okhttp() {
        return this.connectionListener;
    }

    public final long getKeepAliveDurationNs$okhttp() {
        return this.keepAliveDurationNs;
    }

    public final int idleConnectionCount() {
        ConcurrentLinkedQueue<RealConnection> r3 = this.connections;
        int r0 = 0;
        if (r3 != null) goto L5;
    L7:
        Iterator<T> r32 = r3.iterator();
    L9:
        if (r32.hasNext() == false) goto L21;
        RealConnection r1 = (RealConnection) r32.next();
        monitor-enter(r1);
        boolean r2 = r1.getCalls().isEmpty();     // Catch: Throwable -> L18
        monitor-exit(r1);
        if (r2 == false) goto L9;
        r0 = r0 + 1;
        if (r0 >= 0) goto L9;
        CollectionsKt.throwCountOverflow();
    L18:
        th = move-exception;
        throw th;
    L21:
        return r0;
    L5:
        if (r3.isEmpty() == false) goto L7;
        return 0;
    }

    public final void put(RealConnection r2) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        this.connections.add(r2);
        scheduleCloser();
        return;
    L5:
        if (Thread.holdsLock(r2) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r2);
    }

    public final void scheduleCloser() {
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, null);
    }
}
