package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Proxy;
import java.net.Socket;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Address;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Route;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.concurrent.Lockable;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.FlowControlListener;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.url._UrlKt;
import okio.Timeout;

@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 r2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001rB[\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010$\u001a\u00020CH\u0016J\r\u0010*\u001a\u00020CH\u0000¢\u0006\u0002\bDJ\r\u0010E\u001a\u00020CH\u0000¢\u0006\u0002\bFJ\u0006\u0010G\u001a\u00020CJ\b\u0010H\u001a\u00020CH\u0002J%\u0010I\u001a\u00020%2\u0006\u0010J\u001a\u00020K2\u000e\u0010L\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010MH\u0000¢\u0006\u0002\bNJ\u0016\u0010O\u001a\u00020%2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\n0MH\u0002J\u0010\u0010Q\u001a\u00020%2\u0006\u0010R\u001a\u00020SH\u0002J\u0018\u0010T\u001a\u00020%2\u0006\u0010R\u001a\u00020S2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001d\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020ZH\u0000¢\u0006\u0002\b[J\r\u0010\\\u001a\u00020CH\u0000¢\u0006\u0002\b]J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010^\u001a\u00020CH\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\u000e\u0010_\u001a\u00020%2\u0006\u0010`\u001a\u00020%J\u0010\u0010a\u001a\u00020C2\u0006\u0010b\u001a\u00020cH\u0016J\u0018\u0010d\u001a\u00020C2\u0006\u0010e\u001a\u00020#2\u0006\u0010f\u001a\u00020gH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J%\u0010h\u001a\u00020C2\u0006\u0010W\u001a\u00020X2\u0006\u0010i\u001a\u00020\n2\u0006\u0010j\u001a\u00020kH\u0000¢\u0006\u0002\blJ\u001a\u0010m\u001a\u00020C2\u0006\u0010n\u001a\u0002082\b\u0010o\u001a\u0004\u0018\u00010kH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010p\u001a\u00020qH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00103\u001a\u00020\u00152\u0006\u00102\u001a\u00020\u0015@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u0010-R\u001d\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080706¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020%8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bB\u0010'¨\u0006s"}, d2 = {"Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "Lokhttp3/Connection;", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "Lokhttp3/internal/concurrent/Lockable;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "rawSocket", "Ljava/net/Socket;", "javaNetSocket", "handshake", "Lokhttp3/Handshake;", "protocol", "Lokhttp3/Protocol;", "socket", "Lokhttp3/internal/connection/BufferedSocket;", "pingIntervalMillis", _UrlKt.FRAGMENT_ENCODE_SET, "connectionListener", "Lokhttp3/internal/connection/ConnectionListener;", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Route;Ljava/net/Socket;Ljava/net/Socket;Lokhttp3/Handshake;Lokhttp3/Protocol;Lokhttp3/internal/connection/BufferedSocket;ILokhttp3/internal/connection/ConnectionListener;)V", "getTaskRunner", "()Lokhttp3/internal/concurrent/TaskRunner;", "getConnectionPool", "()Lokhttp3/internal/connection/RealConnectionPool;", "getRoute", "()Lokhttp3/Route;", "getConnectionListener$okhttp", "()Lokhttp3/internal/connection/ConnectionListener;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "noNewExchanges", _UrlKt.FRAGMENT_ENCODE_SET, "getNoNewExchanges", "()Z", "setNoNewExchanges", "(Z)V", "noCoalescedConnections", "routeFailureCount", "getRouteFailureCount$okhttp", "()I", "setRouteFailureCount$okhttp", "(I)V", "successCount", "refusedStreamCount", "value", "allocationLimit", "getAllocationLimit$okhttp", "calls", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/ref/Reference;", "Lokhttp3/internal/connection/RealCall;", "getCalls", "()Ljava/util/List;", "idleAtNs", _UrlKt.FRAGMENT_ENCODE_SET, "getIdleAtNs", "()J", "setIdleAtNs", "(J)V", "isMultiplexed", "isMultiplexed$okhttp", _UrlKt.FRAGMENT_ENCODE_SET, "noCoalescedConnections$okhttp", "incrementSuccessCount", "incrementSuccessCount$okhttp", "start", "startHttp2", "isEligible", "address", "Lokhttp3/Address;", "routes", _UrlKt.FRAGMENT_ENCODE_SET, "isEligible$okhttp", "routeMatchesAny", "candidates", "supportsUrl", "url", "Lokhttp3/HttpUrl;", "certificateSupportHost", "newCodec", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "newCodec$okhttp", "useAsSocket", "useAsSocket$okhttp", "cancel", "isHealthy", "doExtensiveChecks", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "onSettings", "connection", "settings", "Lokhttp3/internal/http2/Settings;", "connectFailed", "failedRoute", "failure", "Ljava/io/IOException;", "connectFailed$okhttp", "trackFailure", "call", "e", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRealConnection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealConnection.kt\nokhttp3/internal/connection/RealConnection\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,463:1\n63#2:464\n63#2:465\n63#2:466\n55#2,4:467\n55#2,4:474\n49#2,4:478\n63#2:482\n63#2:483\n63#2:484\n1761#3,3:471\n*S KotlinDebug\n*F\n+ 1 RealConnection.kt\nokhttp3/internal/connection/RealConnection\n*L\n133#1:464\n141#1:465\n147#1:466\n185#1:467,4\n241#1:474,4\n301#1:478,4\n318#1:482\n337#1:483\n372#1:484\n234#1:471,3\n*E\n"})
/* loaded from: classes.dex */
public final class RealConnection extends Http2Connection.Listener implements Connection, ExchangeCodec.Carrier, Lockable {
    public static final Companion Companion = null;
    public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;
    private int allocationLimit;
    private final List<Reference<RealCall>> calls;
    private final ConnectionListener connectionListener;
    private final RealConnectionPool connectionPool;
    private final Handshake handshake;
    private Http2Connection http2Connection;
    private long idleAtNs;
    private final Socket javaNetSocket;
    private boolean noCoalescedConnections;
    private boolean noNewExchanges;
    private final int pingIntervalMillis;
    private final Protocol protocol;
    private final Socket rawSocket;
    private int refusedStreamCount;
    private final Route route;
    private int routeFailureCount;
    private final BufferedSocket socket;
    private int successCount;
    private final TaskRunner taskRunner;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/connection/RealConnection$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "IDLE_CONNECTION_HEALTHY_NS", _UrlKt.FRAGMENT_ENCODE_SET, "newTestConnection", "Lokhttp3/internal/connection/RealConnection;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "socket", "Ljava/net/Socket;", "idleAtNs", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final RealConnection newTestConnection(TaskRunner r12, RealConnectionPool r13, Route r14, Socket r15, long r16) {
            RealConnection$Companion$newTestConnection$bufferedSocket$1 r8 = new RealConnection$Companion$newTestConnection$bufferedSocket$1();
            RealConnection r0 = new RealConnection(r12, r13, r14, new Socket(), r15, null, Protocol.HTTP_2, r8, 0, ConnectionListener.Companion.getNONE());
            r0.setIdleAtNs(r16);
            return r0;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public RealConnection(TaskRunner r1, RealConnectionPool r2, Route r3, Socket r4, Socket r5, Handshake r6, Protocol r7, BufferedSocket r8, int r9, ConnectionListener r10) {
        this.taskRunner = r1;
        this.connectionPool = r2;
        this.route = r3;
        this.rawSocket = r4;
        this.javaNetSocket = r5;
        this.handshake = r6;
        this.protocol = r7;
        this.socket = r8;
        this.pingIntervalMillis = r9;
        this.connectionListener = r10;
        this.allocationLimit = 1;
        this.calls = new ArrayList();
        this.idleAtNs = LongCompanionObject.MAX_VALUE;
    }

    private final boolean certificateSupportHost(HttpUrl r2, Handshake r3) {
        List<Certificate> r1 = r3.peerCertificates();
        if (r1.isEmpty() == false) goto L5;
    L8:
        return false;
    L5:
        if (OkHostnameVerifier.INSTANCE.verify(r2.host(), (X509Certificate) r1.get(0)) == false) goto L8;
        return true;
    }

    private final boolean routeMatchesAny(List<Route> r5) {
        List<Route> r52 = r5;
        if ((r52 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r53 = r52.iterator();
    L9:
        if (r53.hasNext() == false) goto L18;
        Route r0 = (Route) r53.next();
        Proxy.Type r2 = r0.proxy().type();
        Proxy.Type r3 = Proxy.Type.DIRECT;
        if (r2 != r3) goto L9;
        if (getRoute().proxy().type() != r3) goto L9;
        if (Intrinsics.areEqual(getRoute().socketAddress(), r0.socketAddress()) == false) goto L9;
        return true;
    L18:
        return false;
    L5:
        if (r52.isEmpty() == false) goto L7;
        return false;
    }

    private final void startHttp2() {
        this.javaNetSocket.setSoTimeout(0);
        Object r0 = this.connectionListener;
        if ((r0 instanceof FlowControlListener) == false) goto L5;
        FlowControlListener r02 = (FlowControlListener) r0;
    L6:
        if (r02 != null) goto L8;
        r02 = FlowControlListener.None.INSTANCE;
    L8:
        Http2Connection r03 = new Http2Connection.Builder(true, this.taskRunner).socket(this.socket, getRoute().address().url().host()).listener(this).pingIntervalMillis(this.pingIntervalMillis).flowControlListener(r02).build();
        this.http2Connection = r03;
        this.allocationLimit = Http2Connection.Companion.getDEFAULT_SETTINGS().getMaxConcurrentStreams();
        Http2Connection.start$default(r03, false, 1, null);
        return;
    L5:
        r02 = null;
        goto L6
    }

    private final boolean supportsUrl(HttpUrl r5) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        HttpUrl r0 = getRoute().address().url();
        if (r5.port() == r0.port()) goto L13;
        return false;
    L13:
        if (Intrinsics.areEqual(r5.host(), r0.host()) == false) goto L16;
        return true;
    L16:
        if (this.noCoalescedConnections == true) goto L22;
        Handshake r02 = this.handshake;
        if (r02 == null) goto L22;
        if (certificateSupportHost(r5, r02) == false) goto L22;
        return true;
    L22:
        return false;
    L5:
        if (Thread.holdsLock(this) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
        return false;
    }

    @Override // okhttp3.internal.http.ExchangeCodec.Carrier
    /* renamed from: cancel */
    public void mo3746cancel() {
        _UtilJvmKt.closeQuietly(this.rawSocket);
    }

    public final void connectFailed$okhttp(OkHttpClient r3, Route r4, IOException r5) {
        if (r4.proxy().type() == Proxy.Type.DIRECT) goto L5;
        Address r2 = r4.address();
        r2.proxySelector().connectFailed(r2.url().uri(), r4.proxy().address(), r5);
    L5:
        r3.getRouteDatabase$okhttp().failed(r4);
    }

    public final int getAllocationLimit$okhttp() {
        return this.allocationLimit;
    }

    public final List<Reference<RealCall>> getCalls() {
        return this.calls;
    }

    public final ConnectionListener getConnectionListener$okhttp() {
        return this.connectionListener;
    }

    public final RealConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    public final long getIdleAtNs() {
        return this.idleAtNs;
    }

    public final boolean getNoNewExchanges() {
        return this.noNewExchanges;
    }

    @Override // okhttp3.internal.http.ExchangeCodec.Carrier
    public Route getRoute() {
        return this.route;
    }

    public final int getRouteFailureCount$okhttp() {
        return this.routeFailureCount;
    }

    public final TaskRunner getTaskRunner() {
        return this.taskRunner;
    }

    @Override // okhttp3.Connection
    public Handshake handshake() {
        return this.handshake;
    }

    public final void incrementSuccessCount$okhttp() {
        monitor-enter(this);
        this.successCount++;
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final boolean isEligible$okhttp(Address r4, List<Route> r5) {
        if (_UtilJvmKt.assertionsEnabled == false) goto L10;
        if (Thread.holdsLock(this) == true) goto L10;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", this);
        return false;
    L10:
        if (this.calls.size() < this.allocationLimit) goto L12;
    L35:
        return false;
    L12:
        if (this.noNewExchanges == true) goto L35;
        if (getRoute().address().equalsNonHost$okhttp(r4) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(r4.url().host(), route().address().url().host()) == false) goto L21;
        return true;
    L21:
        if (this.http2Connection != null) goto L23;
        return false;
    L23:
        if (r5 == null) goto L35;
        if (routeMatchesAny(r5) == false) goto L35;
        if (r4.hostnameVerifier() == OkHostnameVerifier.INSTANCE) goto L31;
        return false;
    L31:
        if (supportsUrl(r4.url()) == true) goto L37;
        return false;
    L37:
        r4.certificatePinner().check(r4.url().host(), handshake().peerCertificates());     // Catch: SSLPeerUnverifiedException -> L36
        return true;
    }

    public final boolean isHealthy(boolean r5) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        long r2 = System.nanoTime();
        if (this.rawSocket.isClosed() == false) goto L12;
    L36:
        return false;
    L12:
        if (this.javaNetSocket.isClosed() == true) goto L36;
        if (this.javaNetSocket.isInputShutdown() == true) goto L36;
        if (this.javaNetSocket.isOutputShutdown() == true) goto L36;
        Http2Connection r0 = this.http2Connection;
        if (r0 != null) goto L21;
        monitor-enter(this);
        long r22 = r2 - this.idleAtNs;
        monitor-exit(this);
        if (r22 < IDLE_CONNECTION_HEALTHY_NS) goto L31;
        if (r5 == true) goto L30;
        return true;
    L30:
        return _UtilJvmKt.isHealthy(this.javaNetSocket, this.socket.getSource());
    L31:
        return true;
    L33:
        th = move-exception;
        throw th;
    L21:
        return r0.isHealthy(r2);
    L5:
        if (Thread.holdsLock(this) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", this);
        return false;
    }

    public final boolean isMultiplexed$okhttp() {
        if (this.http2Connection == null) goto L6;
        return true;
    L6:
        return false;
    }

    public final ExchangeCodec newCodec$okhttp(OkHttpClient r6, RealInterceptorChain r7) {
        BufferedSocket r0 = this.socket;
        Http2Connection r1 = this.http2Connection;
        if (r1 != null) goto L5;
        this.javaNetSocket.setSoTimeout(r7.readTimeoutMillis());
        Timeout r12 = r0.getSource().timeout();
        long r2 = r7.getReadTimeoutMillis$okhttp();
        TimeUnit r4 = TimeUnit.MILLISECONDS;
        r12.timeout(r2, r4);
        r0.getSink().timeout().timeout(r7.getWriteTimeoutMillis$okhttp(), r4);
        return new Http1ExchangeCodec(r6, this, r0);
    L5:
        return new Http2ExchangeCodec(r6, this, r7, r1);
    }

    public final void noCoalescedConnections$okhttp() {
        monitor-enter(this);
        this.noCoalescedConnections = true;     // Catch: Throwable -> L7
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // okhttp3.internal.http.ExchangeCodec.Carrier
    public void noNewExchanges() {
        monitor-enter(this);
        this.noNewExchanges = true;     // Catch: Throwable -> L8
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(this);
        this.connectionListener.noNewExchanges(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onSettings(Http2Connection r1, Settings r2) {
        monitor-enter(this);
        this.allocationLimit = r2.getMaxConcurrentStreams();     // Catch: Throwable -> L6
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onStream(Http2Stream r2) {
        r2.close(ErrorCode.REFUSED_STREAM, null);
    }

    @Override // okhttp3.Connection
    public Protocol protocol() {
        return this.protocol;
    }

    @Override // okhttp3.Connection
    public Route route() {
        return getRoute();
    }

    public final void setIdleAtNs(long r1) {
        this.idleAtNs = r1;
    }

    public final void setNoNewExchanges(boolean r1) {
        this.noNewExchanges = r1;
    }

    public final void setRouteFailureCount$okhttp(int r1) {
        this.routeFailureCount = r1;
    }

    @Override // okhttp3.Connection
    public Socket socket() {
        return this.javaNetSocket;
    }

    public final void start() {
        this.idleAtNs = System.nanoTime();
        Protocol r0 = this.protocol;
        if (r0 != Protocol.HTTP_2) goto L5;
    L8:
        startHttp2();
        return;
    L5:
        if (r0 == Protocol.H2_PRIOR_KNOWLEDGE) goto L8;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("Connection{");
        r0.append(getRoute().address().url().host());
        r0.append(':');
        r0.append(getRoute().address().url().port());
        r0.append(", proxy=");
        r0.append(getRoute().proxy());
        r0.append(" hostAddress=");
        r0.append(getRoute().socketAddress());
        r0.append(" cipherSuite=");
        Handshake r1 = this.handshake;
        if (r1 == null) goto L6;
        Object r12 = r1.cipherSuite();
        if (r12 == null) goto L6;
    L7:
        r0.append(r12);
        r0.append(" protocol=");
        r0.append(this.protocol);
        r0.append('}');
        return r0.toString();
    L6:
        r12 = "none";
        goto L7
    }

    @Override // okhttp3.internal.http.ExchangeCodec.Carrier
    public void trackFailure(RealCall r4, IOException r5) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if ((r5 instanceof StreamResetException) == false) goto L18;
        if (((StreamResetException) r5).errorCode != ErrorCode.REFUSED_STREAM) goto L13;
        int r42 = this.refusedStreamCount + 1;     // Catch: Throwable -> L10
        this.refusedStreamCount = r42;     // Catch: Throwable -> L10
        if (r42 <= 1) goto L22;
        boolean r43 = !this.noNewExchanges;
        this.noNewExchanges = true;     // Catch: Throwable -> L10
        this.routeFailureCount++;
    L29:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(this);
        if (r43 == false) goto L38;
        this.connectionListener.noNewExchanges(this);
        return;
    L38:
        return;
    L22:
        r43 = false;
        goto L29
    L13:
        if (((StreamResetException) r5).errorCode == ErrorCode.CANCEL) goto L15;
    L16:
        r43 = !this.noNewExchanges;
        this.noNewExchanges = true;     // Catch: Throwable -> L10
        this.routeFailureCount++;
        goto L29
    L15:
        if (r4.isCanceled() == true) goto L22;
    L18:
        if (isMultiplexed$okhttp() == true) goto L20;
    L23:
        boolean r0 = !this.noNewExchanges;
        this.noNewExchanges = true;     // Catch: Throwable -> L10
        if (this.successCount != 0) goto L28;
        if (r5 == null) goto L27;
        connectFailed$okhttp(r4.getClient(), getRoute(), r5);     // Catch: Throwable -> L10
    L27:
        this.routeFailureCount++;
    L28:
        r43 = r0;
        goto L29
    L20:
        if ((r5 instanceof ConnectionShutdownException) == false) goto L22;
        goto L22
    }

    public final void useAsSocket$okhttp() {
        this.javaNetSocket.setSoTimeout(0);
        noNewExchanges();
    }
}
