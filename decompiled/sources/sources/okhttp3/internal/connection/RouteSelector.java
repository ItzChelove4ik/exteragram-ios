package okhttp3.internal.connection;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.Address;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0002!\"B)\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\tH\u0086\u0002J\t\u0010\u0017\u001a\u00020\u0018H\u0086\u0002J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010\u001e\u001a\u00020\tH\u0002J\b\u0010\u001f\u001a\u00020\u000eH\u0002J\u0010\u0010 \u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lokhttp3/internal/connection/RouteSelector;", _UrlKt.FRAGMENT_ENCODE_SET, "address", "Lokhttp3/Address;", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "call", "Lokhttp3/internal/connection/RealCall;", "fastFallback", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/Address;Lokhttp3/internal/connection/RouteDatabase;Lokhttp3/internal/connection/RealCall;Z)V", "proxies", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/net/Proxy;", "nextProxyIndex", _UrlKt.FRAGMENT_ENCODE_SET, "inetSocketAddresses", "Ljava/net/InetSocketAddress;", "postponedRoutes", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Route;", "hasNext", "next", "Lokhttp3/internal/connection/RouteSelector$Selection;", "resetNextProxy", _UrlKt.FRAGMENT_ENCODE_SET, "url", "Lokhttp3/HttpUrl;", "proxy", "hasNextProxy", "nextProxy", "resetNextInetSocketAddress", "Selection", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RouteSelector {
    public static final Companion Companion = null;
    private final Address address;
    private final RealCall call;
    private final boolean fastFallback;
    private List<? extends InetSocketAddress> inetSocketAddresses;
    private int nextProxyIndex;
    private final List<Route> postponedRoutes;
    private List<? extends Proxy> proxies;
    private final RouteDatabase routeDatabase;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/connection/RouteSelector$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "socketHost", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/net/InetSocketAddress;", "getSocketHost", "(Ljava/net/InetSocketAddress;)Ljava/lang/String;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final String getSocketHost(InetSocketAddress r1) {
            InetAddress r0 = r1.getAddress();
            if (r0 != null) goto L7;
            return r1.getHostName();
        L7:
            return r0.getHostAddress();
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000b\u001a\u00020\fH\u0086\u0002J\t\u0010\r\u001a\u00020\u0004H\u0086\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/connection/RouteSelector$Selection;", _UrlKt.FRAGMENT_ENCODE_SET, "routes", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Route;", "<init>", "(Ljava/util/List;)V", "getRoutes", "()Ljava/util/List;", "nextRouteIndex", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Selection {
        private int nextRouteIndex;
        private final List<Route> routes;

        public Selection(List<Route> r1) {
            this.routes = r1;
        }

        public final List<Route> getRoutes() {
            return this.routes;
        }

        public final boolean hasNext() {
            if (this.nextRouteIndex >= this.routes.size()) goto L6;
            return true;
        L6:
            return false;
        }

        public final Route next() {
            if (hasNext() == false) goto L6;
            List<Route> r0 = this.routes;
            int r1 = this.nextRouteIndex;
            this.nextRouteIndex = r1 + 1;
            return r0.get(r1);
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public RouteSelector(Address r1, RouteDatabase r2, RealCall r3, boolean r4) {
        this.address = r1;
        this.routeDatabase = r2;
        this.call = r3;
        this.fastFallback = r4;
        this.proxies = CollectionsKt.emptyList();
        this.inetSocketAddresses = CollectionsKt.emptyList();
        this.postponedRoutes = new ArrayList();
        resetNextProxy(r1.url(), r1.proxy());
    }

    private final boolean hasNextProxy() {
        if (this.nextProxyIndex >= this.proxies.size()) goto L6;
        return true;
    L6:
        return false;
    }

    private final Proxy nextProxy() {
        if (hasNextProxy() == false) goto L7;
        List<? extends Proxy> r0 = this.proxies;
        int r1 = this.nextProxyIndex;
        this.nextProxyIndex = r1 + 1;
        Proxy r02 = r0.get(r1);
        resetNextInetSocketAddress(r02);
        return r02;
    L7:
        throw new SocketException("No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies);
    }

    private final void resetNextInetSocketAddress(Proxy r6) {
        ArrayList r0 = new ArrayList();
        this.inetSocketAddresses = r0;
        if (r6.type() != Proxy.Type.DIRECT) goto L5;
    L12:
        String r2 = this.address.url().host();
        int r1 = this.address.url().port();
    L14:
        if (1 > r1) goto L38;
        if (r1 >= 65536) goto L38;
        if (r6.type() != Proxy.Type.SOCKS) goto L22;
        r0.add(InetSocketAddress.createUnresolved(r2, r1));
        return;
    L22:
        if (_HostnamesCommonKt.canParseAsIpAddress(r2) == false) goto L24;
        List<InetAddress> r62 = CollectionsKt.listOf(InetAddress.getByName(r2));
    L28:
        if (this.fastFallback == false) goto L30;
        r62 = InetAddressOrderKt.reorderForHappyEyeballs(r62);
    L30:
        Iterator<InetAddress> r5 = r62.iterator();
    L32:
        if (r5.hasNext() == false) goto L34;
        r0.add(new InetSocketAddress(r5.next(), r1));
        goto L32
    L34:
        return;
    L24:
        this.call.getEventListener$okhttp().dnsStart(this.call, r2);
        r62 = this.address.dns().lookup(r2);
        if (r62.isEmpty() == true) goto L36;
        this.call.getEventListener$okhttp().dnsEnd(this.call, r2, r62);
        goto L28
    L36:
        throw new UnknownHostException(this.address.dns() + " returned no addresses for " + r2);
    L38:
        throw new SocketException("No route to " + r2 + ':' + r1 + "; port is out of range");
    L5:
        if (r6.type() == Proxy.Type.SOCKS) goto L12;
        SocketAddress r12 = r6.address();
        if ((r12 instanceof InetSocketAddress) == false) goto L10;
        InetSocketAddress r13 = (InetSocketAddress) r12;
        r2 = Companion.getSocketHost(r13);
        r1 = r13.getPort();
        goto L14
    L10:
        Options$Companion$$ExternalSyntheticBUOutline0.m("Proxy.address() is not an InetSocketAddress: ", r12.getClass());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void resetNextProxy(HttpUrl r3, Proxy r4) {
        this.call.getEventListener$okhttp().proxySelectStart(this.call, r3);
        this.proxies = resetNextProxy$selectProxies(r4, r3, this);
        this.nextProxyIndex = 0;
        this.call.getEventListener$okhttp().proxySelectEnd(this.call, r3, this.proxies);
    }

    private static final List<Proxy> resetNextProxy$selectProxies(Proxy r0, HttpUrl r1, RouteSelector r2) {
        if (r0 != null) goto L4;
        URI r02 = r1.uri();
        if (r02.getHost() == null) goto L8;
        List<Proxy> r03 = r2.address.proxySelector().select(r02);
        List<Proxy> r12 = r03;
        if (r12 == null) goto L17;
        if (r12.isEmpty() == true) goto L17;
        return _UtilJvmKt.toImmutableList(r03);
    L17:
        return _UtilJvmKt.immutableListOf(new Proxy[]{Proxy.NO_PROXY});
    L8:
        return _UtilJvmKt.immutableListOf(new Proxy[]{Proxy.NO_PROXY});
    L4:
        return CollectionsKt.listOf(r0);
    }

    public final boolean hasNext() {
        if (hasNextProxy() == false) goto L5;
        return true;
    L5:
        if (this.postponedRoutes.isEmpty() == false) goto L11;
        return false;
    L11:
        return true;
    }

    public final Selection next() {
        if (hasNext() == false) goto L21;
        ArrayList r0 = new ArrayList();
    L6:
        if (hasNextProxy() == false) goto L17;
        Proxy r1 = nextProxy();
        Iterator<? extends InetSocketAddress> r2 = this.inetSocketAddresses.iterator();
    L9:
        if (r2.hasNext() == false) goto L15;
        InetSocketAddress r3 = r2.next();
        Route r4 = new Route(this.address, r1, r3);
        if (this.routeDatabase.shouldPostpone(r4) == true) goto L12;
        r0.add(r4);
        goto L9
    L12:
        this.postponedRoutes.add(r4);
        goto L9
    L15:
        if (r0.isEmpty() == true) goto L6;
    L17:
        if (r0.isEmpty() == false) goto L20;
        CollectionsKt.addAll(r0, this.postponedRoutes);
        this.postponedRoutes.clear();
    L20:
        return new Selection(r0);
    L21:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
