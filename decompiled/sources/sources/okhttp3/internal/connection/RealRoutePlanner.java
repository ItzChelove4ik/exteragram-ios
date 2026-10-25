package okhttp3.internal.connection;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Address;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RoutePlanner;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bq\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010'\u001a\u00020\rH\u0016J\b\u0010(\u001a\u00020$H\u0016J\n\u0010)\u001a\u0004\u0018\u00010*H\u0002J\r\u0010+\u001a\u00020,H\u0000¢\u0006\u0002\b-J-\u0010.\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010,2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020!\u0018\u000101H\u0000¢\u0006\u0002\b2J'\u00103\u001a\u00020,2\u0006\u00104\u001a\u00020!2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020!\u0018\u000101H\u0000¢\u0006\u0002\b5J\u0010\u00106\u001a\u00020\u00162\u0006\u00104\u001a\u00020!H\u0002J\u0012\u00107\u001a\u00020\r2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u0012\u0010:\u001a\u0004\u0018\u00010!2\u0006\u0010;\u001a\u000209H\u0002J\u0010\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020>H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006?"}, d2 = {"Lokhttp3/internal/connection/RealRoutePlanner;", "Lokhttp3/internal/connection/RoutePlanner;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "readTimeoutMillis", _UrlKt.FRAGMENT_ENCODE_SET, "writeTimeoutMillis", "socketConnectTimeoutMillis", "socketReadTimeoutMillis", "pingIntervalMillis", "retryOnConnectionFailure", _UrlKt.FRAGMENT_ENCODE_SET, "fastFallback", "address", "Lokhttp3/Address;", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "call", "Lokhttp3/internal/connection/RealCall;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/internal/connection/RealConnectionPool;IIIIIZZLokhttp3/Address;Lokhttp3/internal/connection/RouteDatabase;Lokhttp3/internal/connection/RealCall;Lokhttp3/Request;)V", "getAddress", "()Lokhttp3/Address;", "doExtensiveHealthChecks", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "nextRouteToTry", "Lokhttp3/Route;", "deferredPlans", "Lkotlin/collections/ArrayDeque;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "getDeferredPlans", "()Lkotlin/collections/ArrayDeque;", "isCanceled", "plan", "planReuseCallConnection", "Lokhttp3/internal/connection/ReusePlan;", "planConnect", "Lokhttp3/internal/connection/ConnectPlan;", "planConnect$okhttp", "planReusePooledConnection", "planToReplace", "routes", _UrlKt.FRAGMENT_ENCODE_SET, "planReusePooledConnection$okhttp", "planConnectToRoute", "route", "planConnectToRoute$okhttp", "createTunnelRequest", "hasNext", "failedConnection", "Lokhttp3/internal/connection/RealConnection;", "retryRoute", "connection", "sameHostAndPort", "url", "Lokhttp3/HttpUrl;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRealRoutePlanner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealRoutePlanner.kt\nokhttp3/internal/connection/RealRoutePlanner\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,348:1\n63#2:349\n63#2:350\n*S KotlinDebug\n*F\n+ 1 RealRoutePlanner.kt\nokhttp3/internal/connection/RealRoutePlanner\n*L\n100#1:349\n327#1:350\n*E\n"})
/* loaded from: classes.dex */
public final class RealRoutePlanner implements RoutePlanner {
    private final Address address;
    private final RealCall call;
    private final RealConnectionPool connectionPool;
    private final ArrayDeque<RoutePlanner.Plan> deferredPlans;
    private final boolean doExtensiveHealthChecks;
    private final boolean fastFallback;
    private Route nextRouteToTry;
    private final int pingIntervalMillis;
    private final int readTimeoutMillis;
    private final boolean retryOnConnectionFailure;
    private final RouteDatabase routeDatabase;
    private RouteSelector.Selection routeSelection;
    private RouteSelector routeSelector;
    private final int socketConnectTimeoutMillis;
    private final int socketReadTimeoutMillis;
    private final TaskRunner taskRunner;
    private final int writeTimeoutMillis;

    public RealRoutePlanner(TaskRunner r1, RealConnectionPool r2, int r3, int r4, int r5, int r6, int r7, boolean r8, boolean r9, Address r10, RouteDatabase r11, RealCall r12, Request r13) {
        this.taskRunner = r1;
        this.connectionPool = r2;
        this.readTimeoutMillis = r3;
        this.writeTimeoutMillis = r4;
        this.socketConnectTimeoutMillis = r5;
        this.socketReadTimeoutMillis = r6;
        this.pingIntervalMillis = r7;
        this.retryOnConnectionFailure = r8;
        this.fastFallback = r9;
        this.address = r10;
        this.routeDatabase = r11;
        this.call = r12;
        this.doExtensiveHealthChecks = !Intrinsics.areEqual(r13.method(), "GET");
        this.deferredPlans = new ArrayDeque();
    }

    private final Request createTunnelRequest(Route r4) {
        Request r3 = new Request.Builder().url(r4.address().url()).method("CONNECT", null).header("Host", _UtilJvmKt.toHostHeader(r4.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", _UtilCommonKt.USER_AGENT).build();
        Response r0 = new Response.Builder().request(r3).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").sentRequestAtMillis(-1).receivedResponseAtMillis(-1).header("Proxy-Authenticate", "OkHttp-Preemptive").build();
        Request r42 = r4.address().proxyAuthenticator().authenticate(r4, r0);
        if (r42 != null) goto L5;
        return r3;
    L5:
        return r42;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConnectPlan planConnectToRoute$okhttp$default(RealRoutePlanner r0, Route r1, List r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = null;
    L6:
        return r0.planConnectToRoute$okhttp(r1, r2);
    }

    private final ReusePlan planReuseCallConnection() {
        RealConnection r0 = this.call.getConnection();
        if (r0 != null) goto L5;
        return null;
    L5:
        boolean r2 = r0.isHealthy(this.doExtensiveHealthChecks);
        monitor-enter(r0);
        if (r2 == true) goto L12;
        boolean r22 = !r0.getNoNewExchanges();
        r0.setNoNewExchanges(true);     // Catch: Throwable -> L9
        Socket r3 = this.call.releaseConnectionNoEvents$okhttp();     // Catch: Throwable -> L9
    L19:
        monitor-exit(r0);
        if (this.call.getConnection() == null) goto L27;
        if (r3 == null) goto L24;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L24:
        return new ReusePlan(r0);
    L27:
        if (r3 == null) goto L29;
        _UtilJvmKt.closeQuietly(r3);
    L29:
        this.call.getEventListener$okhttp().connectionReleased(this.call, r0);
        r0.getConnectionListener$okhttp().connectionReleased(r0, this.call);
        if (r3 == null) goto L32;
        r0.getConnectionListener$okhttp().connectionClosed(r0);
    L34:
        return null;
    L32:
        if (r22 == false) goto L34;
        r0.getConnectionListener$okhttp().noNewExchanges(r0);
        goto L34
    L12:
        if (r0.getNoNewExchanges() == true) goto L18;
        if (sameHostAndPort(r0.route().address().url()) == false) goto L18;
        r22 = false;
        r3 = null;
    L18:
        r3 = this.call.releaseConnectionNoEvents$okhttp();     // Catch: Throwable -> L9
        r22 = false;
    L9:
        th = move-exception;
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ReusePlan planReusePooledConnection$okhttp$default(RealRoutePlanner r1, ConnectPlan r2, List r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r2 = null;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r3 = null;
    L9:
        return r1.planReusePooledConnection$okhttp(r2, r3);
    }

    private final Route retryRoute(RealConnection r3) {
        monitor-enter(r3);
        Route r1 = null;
        if (r3.getRouteFailureCount$okhttp() == 0) goto L7;
    L13:
        monitor-exit(r3);
        return r1;
    L7:
        if (r3.getNoNewExchanges() == false) goto L13;
        if (_UtilJvmKt.canReuseConnectionFor(r3.route().address().url(), getAddress().url()) == false) goto L13;
        r1 = r3.route();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public Address getAddress() {
        return this.address;
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public ArrayDeque<RoutePlanner.Plan> getDeferredPlans() {
        return this.deferredPlans;
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public boolean hasNext(RealConnection r3) {
        if (getDeferredPlans().isEmpty() == true) goto L6;
        return true;
    L6:
        if (this.nextRouteToTry == null) goto L8;
        return true;
    L8:
        if (r3 == null) goto L13;
        Route r32 = retryRoute(r3);
        if (r32 == null) goto L13;
        this.nextRouteToTry = r32;
        return true;
    L13:
        RouteSelector.Selection r33 = this.routeSelection;
        if (r33 != null) goto L16;
    L18:
        RouteSelector r2 = this.routeSelector;
        if (r2 != null) goto L22;
        return true;
    L22:
        return r2.hasNext();
    L16:
        if (r33.hasNext() != true) goto L18;
        return true;
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public boolean isCanceled() {
        return this.call.isCanceled();
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public RoutePlanner.Plan plan() {
        ReusePlan r0 = planReuseCallConnection();
        if (r0 == null) goto L5;
        return r0;
    L5:
        ReusePlan r02 = planReusePooledConnection$okhttp$default(this, null, null, 3, null);
        if (r02 == null) goto L9;
        return r02;
    L9:
        if (getDeferredPlans().isEmpty() == false) goto L11;
        ConnectPlan r03 = planConnect$okhttp();
        ReusePlan r2 = planReusePooledConnection$okhttp(r03, r03.getRoutes$okhttp());
        if (r2 == null) goto L15;
        return r2;
    L15:
        return r03;
    L11:
        return getDeferredPlans().removeFirst();
    }

    public final ConnectPlan planConnect$okhttp() {
        Route r0 = this.nextRouteToTry;
        if (r0 == null) goto L6;
        this.nextRouteToTry = null;
        return planConnectToRoute$okhttp$default(this, r0, null, 2, null);
    L6:
        RouteSelector.Selection r02 = this.routeSelection;
        if (r02 != null) goto L9;
    L12:
        RouteSelector r03 = this.routeSelector;
        if (r03 != null) goto L16;
        r03 = new RouteSelector(getAddress(), this.routeDatabase, this.call, this.fastFallback);
        this.routeSelector = r03;
    L16:
        if (r03.hasNext() == false) goto L23;
        RouteSelector.Selection r04 = r03.next();
        this.routeSelection = r04;
        if (isCanceled() == false) goto L20;
        Model$$ExternalSyntheticBUOutline0.m("Canceled");
        return null;
    L20:
        return planConnectToRoute$okhttp(r04.next(), r04.getRoutes());
    L23:
        Model$$ExternalSyntheticBUOutline0.m("exhausted all routes");
        return null;
    L9:
        if (r02.hasNext() == false) goto L12;
        return planConnectToRoute$okhttp$default(this, r02.next(), null, 2, null);
    }

    public final ConnectPlan planConnectToRoute$okhttp(Route r18, List<Route> r19) {
        if (r18.address().sslSocketFactory() != null) goto L14;
        if (r18.address().connectionSpecs().contains(ConnectionSpec.CLEARTEXT) == false) goto L12;
        String r0 = r18.address().url().host();
        if (Platform.Companion.get().isCleartextTrafficPermitted(r0) == true) goto L16;
        throw new UnknownServiceException("CLEARTEXT communication to " + r0 + " not permitted by network security policy");
    L16:
        if (r18.requiresTunnel() == false) goto L19;
        Request r02 = createTunnelRequest(r18);
    L21:
        return new ConnectPlan(this.taskRunner, this.connectionPool, this.readTimeoutMillis, this.writeTimeoutMillis, this.socketConnectTimeoutMillis, this.socketReadTimeoutMillis, this.pingIntervalMillis, this.retryOnConnectionFailure, this.call, this, r18, r19, 0, r02, -1, false);
    L19:
        r02 = null;
        goto L21
    L12:
        throw new UnknownServiceException("CLEARTEXT communication not enabled for client");
    L14:
        if (r18.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) == false) goto L16;
        throw new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS");
    }

    public final ReusePlan planReusePooledConnection$okhttp(ConnectPlan r7, List<Route> r8) {
        RealConnectionPool r0 = this.connectionPool;
        boolean r1 = this.doExtensiveHealthChecks;
        Address r2 = getAddress();
        RealCall r3 = this.call;
        if (r7 != null) goto L5;
    L8:
        boolean r4 = false;
    L9:
        RealConnection r82 = r0.callAcquirePooledConnection$okhttp(r1, r2, r3, r8, r4);
        if (r82 != null) goto L13;
        return null;
    L13:
        if (r7 == null) goto L15;
        this.nextRouteToTry = r7.getRoute();
        r7.closeQuietly();
    L15:
        this.call.getEventListener$okhttp().connectionAcquired(this.call, r82);
        r82.getConnectionListener$okhttp().connectionAcquired(r82, this.call);
        return new ReusePlan(r82);
    L5:
        if (r7.isReady() == false) goto L8;
        r4 = true;
        goto L9
    }

    @Override // okhttp3.internal.connection.RoutePlanner
    public boolean sameHostAndPort(HttpUrl r3) {
        HttpUrl r2 = getAddress().url();
        if (r3.port() == r2.port()) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(r3.host(), r2.host()) == false) goto L10;
        return true;
    L10:
        return false;
    }
}
