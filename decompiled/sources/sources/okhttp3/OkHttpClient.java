package okhttp3;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import j$.time.Duration;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.WebSocket;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.RealWebSocket;
import okio.Buffer$$ExternalSyntheticBUOutline3;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@Metadata(d1 = {"\u0000ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0016\u0018\u0000 \u0083\u00012\u00020\u00012\u00020\u0002:\u0004\u0082\u0001\u0083\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007J\u000e\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\J\b\u0010]\u001a\u00020^H\u0002J\u0010\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020bH\u0016J\u0018\u0010c\u001a\u00020d2\u0006\u0010a\u001a\u00020b2\u0006\u0010e\u001a\u00020fH\u0016J\b\u0010g\u001a\u00020\u0004H\u0016J\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\bhJ\r\u0010V\u001a\u00020WH\u0007¢\u0006\u0002\biJ\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007¢\u0006\u0002\bjJ\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007¢\u0006\u0002\bkJ\r\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0002\blJ\r\u0010\u0013\u001a\u00020\u0014H\u0007¢\u0006\u0002\bmJ\r\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\bnJ\r\u0010\u001a\u001a\u00020\u0014H\u0007¢\u0006\u0002\boJ\r\u0010\u001b\u001a\u00020\u0014H\u0007¢\u0006\u0002\bpJ\r\u0010\u001c\u001a\u00020\u001dH\u0007¢\u0006\u0002\bqJ\u000f\u0010\u001f\u001a\u0004\u0018\u00010 H\u0007¢\u0006\u0002\brJ\r\u0010\"\u001a\u00020#H\u0007¢\u0006\u0002\bsJ\u000f\u0010%\u001a\u0004\u0018\u00010&H\u0007¢\u0006\u0002\btJ\r\u0010(\u001a\u00020)H\u0007¢\u0006\u0002\buJ\r\u0010+\u001a\u00020\u0018H\u0007¢\u0006\u0002\bvJ\r\u0010,\u001a\u00020-H\u0007¢\u0006\u0002\bwJ\r\u00101\u001a\u000200H\u0007¢\u0006\u0002\bxJ\u0013\u00106\u001a\b\u0012\u0004\u0012\u0002070\fH\u0007¢\u0006\u0002\byJ\u0013\u00108\u001a\b\u0012\u0004\u0012\u0002090\fH\u0007¢\u0006\u0002\bzJ\r\u0010:\u001a\u00020;H\u0007¢\u0006\u0002\b{J\r\u0010=\u001a\u00020>H\u0007¢\u0006\u0002\b|J\r\u0010C\u001a\u00020DH\u0007¢\u0006\u0002\b}J\r\u0010F\u001a\u00020DH\u0007¢\u0006\u0002\b~J\r\u0010G\u001a\u00020DH\u0007¢\u0006\u0002\b\u007fJ\u000e\u0010H\u001a\u00020DH\u0007¢\u0006\u0003\b\u0080\u0001J\u000e\u0010I\u001a\u00020DH\u0007¢\u0006\u0003\b\u0081\u0001R\u0013\u0010\b\u001a\u00020\t8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u0019\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u000eR\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0010\u001a\u00020\u00118\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0012R\u0013\u0010\u0013\u001a\u00020\u00148\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0015R\u0013\u0010\u0016\u001a\u00020\u00148\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0017\u001a\u00020\u00188\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0019R\u0013\u0010\u001a\u001a\u00020\u00148\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0013\u0010\u001b\u001a\u00020\u00148\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0013\u0010\u001c\u001a\u00020\u001d8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001eR\u0015\u0010\u001f\u001a\u0004\u0018\u00010 8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010!R\u0013\u0010\"\u001a\u00020#8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010$R\u0015\u0010%\u001a\u0004\u0018\u00010&8\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010'R\u0013\u0010(\u001a\u00020)8\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010*R\u0013\u0010+\u001a\u00020\u00188\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0019R\u0013\u0010,\u001a\u00020-8\u0007¢\u0006\b\n\u0000\u001a\u0004\b,\u0010.R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u00101\u001a\u0002008G¢\u0006\u0006\u001a\u0004\b1\u00102R\u0015\u00103\u001a\u0004\u0018\u0001048\u0007¢\u0006\b\n\u0000\u001a\u0004\b3\u00105R\u0019\u00106\u001a\b\u0012\u0004\u0012\u0002070\f8\u0007¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u000eR\u0019\u00108\u001a\b\u0012\u0004\u0012\u0002090\f8\u0007¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u000eR\u0013\u0010:\u001a\u00020;8\u0007¢\u0006\b\n\u0000\u001a\u0004\b:\u0010<R\u0013\u0010=\u001a\u00020>8\u0007¢\u0006\b\n\u0000\u001a\u0004\b=\u0010?R\u0015\u0010@\u001a\u0004\u0018\u00010A8\u0007¢\u0006\b\n\u0000\u001a\u0004\b@\u0010BR\u0013\u0010C\u001a\u00020D8\u0007¢\u0006\b\n\u0000\u001a\u0004\bC\u0010ER\u0013\u0010F\u001a\u00020D8\u0007¢\u0006\b\n\u0000\u001a\u0004\bF\u0010ER\u0013\u0010G\u001a\u00020D8\u0007¢\u0006\b\n\u0000\u001a\u0004\bG\u0010ER\u0013\u0010H\u001a\u00020D8\u0007¢\u0006\b\n\u0000\u001a\u0004\bH\u0010ER\u0013\u0010I\u001a\u00020D8\u0007¢\u0006\b\n\u0000\u001a\u0004\bI\u0010ER\u0013\u0010J\u001a\u00020D8\u0007¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010ER\u0013\u0010K\u001a\u00020L8\u0007¢\u0006\b\n\u0000\u001a\u0004\bK\u0010MR\u0014\u0010N\u001a\u00020OX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u0014\u0010R\u001a\u00020SX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u0013\u0010V\u001a\u00020W8\u0007¢\u0006\b\n\u0000\u001a\u0004\bV\u0010X¨\u0006\u0084\u0001"}, d2 = {"Lokhttp3/OkHttpClient;", "Lokhttp3/Call$Factory;", "Lokhttp3/WebSocket$Factory;", "builder", "Lokhttp3/OkHttpClient$Builder;", "<init>", "(Lokhttp3/OkHttpClient$Builder;)V", "()V", "dispatcher", "Lokhttp3/Dispatcher;", "()Lokhttp3/Dispatcher;", "interceptors", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Interceptor;", "()Ljava/util/List;", "networkInterceptors", "eventListenerFactory", "Lokhttp3/EventListener$Factory;", "()Lokhttp3/EventListener$Factory;", "retryOnConnectionFailure", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "fastFallback", "authenticator", "Lokhttp3/Authenticator;", "()Lokhttp3/Authenticator;", "followRedirects", "followSslRedirects", "cookieJar", "Lokhttp3/CookieJar;", "()Lokhttp3/CookieJar;", "cache", "Lokhttp3/Cache;", "()Lokhttp3/Cache;", "dns", "Lokhttp3/Dns;", "()Lokhttp3/Dns;", "proxy", "Ljava/net/Proxy;", "()Ljava/net/Proxy;", "proxySelector", "Ljava/net/ProxySelector;", "()Ljava/net/ProxySelector;", "proxyAuthenticator", "socketFactory", "Ljavax/net/SocketFactory;", "()Ljavax/net/SocketFactory;", "sslSocketFactoryOrNull", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "x509TrustManager", "Ljavax/net/ssl/X509TrustManager;", "()Ljavax/net/ssl/X509TrustManager;", "connectionSpecs", "Lokhttp3/ConnectionSpec;", "protocols", "Lokhttp3/Protocol;", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "()Ljavax/net/ssl/HostnameVerifier;", "certificatePinner", "Lokhttp3/CertificatePinner;", "()Lokhttp3/CertificatePinner;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "callTimeoutMillis", _UrlKt.FRAGMENT_ENCODE_SET, "()I", "connectTimeoutMillis", "readTimeoutMillis", "writeTimeoutMillis", "pingIntervalMillis", "webSocketCloseTimeout", "minWebSocketMessageToCompress", _UrlKt.FRAGMENT_ENCODE_SET, "()J", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase$okhttp", "()Lokhttp3/internal/connection/RouteDatabase;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "connectionPool", "Lokhttp3/ConnectionPool;", "()Lokhttp3/ConnectionPool;", "address", "Lokhttp3/Address;", "url", "Lokhttp3/HttpUrl;", "verifyClientState", _UrlKt.FRAGMENT_ENCODE_SET, "newCall", "Lokhttp3/Call;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "newWebSocket", "Lokhttp3/WebSocket;", "listener", "Lokhttp3/WebSocketListener;", "newBuilder", "-deprecated_dispatcher", "-deprecated_connectionPool", "-deprecated_interceptors", "-deprecated_networkInterceptors", "-deprecated_eventListenerFactory", "-deprecated_retryOnConnectionFailure", "-deprecated_authenticator", "-deprecated_followRedirects", "-deprecated_followSslRedirects", "-deprecated_cookieJar", "-deprecated_cache", "-deprecated_dns", "-deprecated_proxy", "-deprecated_proxySelector", "-deprecated_proxyAuthenticator", "-deprecated_socketFactory", "-deprecated_sslSocketFactory", "-deprecated_connectionSpecs", "-deprecated_protocols", "-deprecated_hostnameVerifier", "-deprecated_certificatePinner", "-deprecated_callTimeoutMillis", "-deprecated_connectTimeoutMillis", "-deprecated_readTimeoutMillis", "-deprecated_writeTimeoutMillis", "-deprecated_pingIntervalMillis", "Builder", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOkHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1388:1\n2746#2,3:1389\n2746#2,3:1392\n1#3:1395\n*S KotlinDebug\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient\n*L\n278#1:1389,3\n340#1:1392,3\n*E\n"})
/* loaded from: classes.dex */
public class OkHttpClient implements Call.Factory, WebSocket.Factory {
    public static final Companion Companion = null;
    private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS = null;
    private static final List<Protocol> DEFAULT_PROTOCOLS = null;
    private final Authenticator authenticator;
    private final Cache cache;
    private final int callTimeoutMillis;
    private final CertificateChainCleaner certificateChainCleaner;
    private final CertificatePinner certificatePinner;
    private final int connectTimeoutMillis;
    private final ConnectionPool connectionPool;
    private final List<ConnectionSpec> connectionSpecs;
    private final CookieJar cookieJar;
    private final Dispatcher dispatcher;
    private final Dns dns;
    private final EventListener.Factory eventListenerFactory;
    private final boolean fastFallback;
    private final boolean followRedirects;
    private final boolean followSslRedirects;
    private final HostnameVerifier hostnameVerifier;
    private final List<Interceptor> interceptors;
    private final long minWebSocketMessageToCompress;
    private final List<Interceptor> networkInterceptors;
    private final int pingIntervalMillis;
    private final List<Protocol> protocols;
    private final Proxy proxy;
    private final Authenticator proxyAuthenticator;
    private final ProxySelector proxySelector;
    private final int readTimeoutMillis;
    private final boolean retryOnConnectionFailure;
    private final RouteDatabase routeDatabase;
    private final SocketFactory socketFactory;
    private final SSLSocketFactory sslSocketFactoryOrNull;
    private final TaskRunner taskRunner;
    private final int webSocketCloseTimeout;
    private final int writeTimeoutMillis;
    private final X509TrustManager x509TrustManager;

    @Metadata(d1 = {"\u0000\u0084\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\bn\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0006J\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000e¢\u0006\u0004\b\u0012\u0010\u0013J8\u0010\u0012\u001a\u00020\u00002#\b\u0004\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u001d\u0010\u0010J\u0015\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000e¢\u0006\u0004\b\u001e\u0010\u0013J8\u0010\u001e\u001a\u00020\u00002#\b\u0004\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001cJ\u0015\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\u00020\u00002\u0006\u0010$\u001a\u00020#¢\u0006\u0004\b$\u0010%J\u0015\u0010'\u001a\u00020\u00002\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b'\u0010(J\u0015\u0010)\u001a\u00020\u00002\u0006\u0010)\u001a\u00020&¢\u0006\u0004\b)\u0010(J\u0015\u0010+\u001a\u00020\u00002\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\u00002\u0006\u0010-\u001a\u00020&¢\u0006\u0004\b-\u0010(J\u0015\u0010/\u001a\u00020\u00002\u0006\u0010.\u001a\u00020&¢\u0006\u0004\b/\u0010(J\u0015\u00101\u001a\u00020\u00002\u0006\u00101\u001a\u000200¢\u0006\u0004\b1\u00102J\u0017\u00104\u001a\u00020\u00002\b\u00104\u001a\u0004\u0018\u000103¢\u0006\u0004\b4\u00105J\u0017\u00107\u001a\u00020\u00002\u0006\u00107\u001a\u000206H\u0000¢\u0006\u0004\b8\u00109J\u0015\u0010;\u001a\u00020\u00002\u0006\u0010;\u001a\u00020:¢\u0006\u0004\b;\u0010<J\u0017\u0010>\u001a\u00020\u00002\b\u0010>\u001a\u0004\u0018\u00010=¢\u0006\u0004\b>\u0010?J\u0015\u0010A\u001a\u00020\u00002\u0006\u0010A\u001a\u00020@¢\u0006\u0004\bA\u0010BJ\u0015\u0010C\u001a\u00020\u00002\u0006\u0010C\u001a\u00020*¢\u0006\u0004\bC\u0010,J\u0015\u0010E\u001a\u00020\u00002\u0006\u0010E\u001a\u00020D¢\u0006\u0004\bE\u0010FJ\u0017\u0010H\u001a\u00020\u00002\u0006\u0010H\u001a\u00020GH\u0007¢\u0006\u0004\bH\u0010IJ\u001d\u0010H\u001a\u00020\u00002\u0006\u0010H\u001a\u00020G2\u0006\u0010K\u001a\u00020J¢\u0006\u0004\bH\u0010LJ\u001b\u0010O\u001a\u00020\u00002\f\u0010O\u001a\b\u0012\u0004\u0012\u00020N0M¢\u0006\u0004\bO\u0010PJ\u001b\u0010R\u001a\u00020\u00002\f\u0010R\u001a\b\u0012\u0004\u0012\u00020Q0M¢\u0006\u0004\bR\u0010PJ\u0015\u0010T\u001a\u00020\u00002\u0006\u0010T\u001a\u00020S¢\u0006\u0004\bT\u0010UJ\u0015\u0010W\u001a\u00020\u00002\u0006\u0010W\u001a\u00020V¢\u0006\u0004\bW\u0010XJ\u001d\u0010]\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\b]\u0010^J\u0017\u0010]\u001a\u00020\u00002\u0006\u0010`\u001a\u00020_H\u0007¢\u0006\u0004\b]\u0010aJ\u0015\u0010]\u001a\u00020\u00002\u0006\u0010`\u001a\u00020b¢\u0006\u0004\bc\u0010dJ\u001d\u0010e\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\be\u0010^J\u0017\u0010e\u001a\u00020\u00002\u0006\u0010`\u001a\u00020_H\u0007¢\u0006\u0004\be\u0010aJ\u0015\u0010e\u001a\u00020\u00002\u0006\u0010`\u001a\u00020b¢\u0006\u0004\bf\u0010dJ\u001d\u0010g\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\bg\u0010^J\u0017\u0010g\u001a\u00020\u00002\u0006\u0010`\u001a\u00020_H\u0007¢\u0006\u0004\bg\u0010aJ\u0015\u0010g\u001a\u00020\u00002\u0006\u0010`\u001a\u00020b¢\u0006\u0004\bh\u0010dJ\u001d\u0010i\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\bi\u0010^J\u0017\u0010i\u001a\u00020\u00002\u0006\u0010`\u001a\u00020_H\u0007¢\u0006\u0004\bi\u0010aJ\u0015\u0010i\u001a\u00020\u00002\u0006\u0010`\u001a\u00020b¢\u0006\u0004\bj\u0010dJ\u001d\u0010l\u001a\u00020\u00002\u0006\u0010k\u001a\u00020Y2\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\bl\u0010^J\u0017\u0010l\u001a\u00020\u00002\u0006\u0010`\u001a\u00020_H\u0007¢\u0006\u0004\bl\u0010aJ\u0015\u0010l\u001a\u00020\u00002\u0006\u0010`\u001a\u00020b¢\u0006\u0004\bm\u0010dJ\u001d\u0010n\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\bn\u0010^J\u0017\u0010n\u001a\u00020\u00002\u0006\u0010`\u001a\u00020_H\u0007¢\u0006\u0004\bn\u0010aJ\u0015\u0010n\u001a\u00020\u00002\u0006\u0010`\u001a\u00020b¢\u0006\u0004\bo\u0010dJ\u0015\u0010q\u001a\u00020\u00002\u0006\u0010p\u001a\u00020Y¢\u0006\u0004\bq\u0010dJ\r\u0010r\u001a\u00020\u0004¢\u0006\u0004\br\u0010sR\"\u0010\b\u001a\u00020\u00078\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\b\u0010t\u001a\u0004\bu\u0010v\"\u0004\bw\u0010xR$\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010y\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000f\u0010~\u001a\u0004\b\u007f\u0010\u0010R!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0000X\u0080\u0004¢\u0006\r\n\u0004\b\u001d\u0010~\u001a\u0005\b\u0080\u0001\u0010\u0010R'\u0010$\u001a\u00020#8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b$\u0010\u0081\u0001\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R'\u0010'\u001a\u00020&8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b'\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001\"\u0006\b\u0089\u0001\u0010\u008a\u0001R'\u0010)\u001a\u00020&8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b)\u0010\u0086\u0001\u001a\u0006\b\u008b\u0001\u0010\u0088\u0001\"\u0006\b\u008c\u0001\u0010\u008a\u0001R'\u0010+\u001a\u00020*8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b+\u0010\u008d\u0001\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001\"\u0006\b\u0090\u0001\u0010\u0091\u0001R'\u0010-\u001a\u00020&8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b-\u0010\u0086\u0001\u001a\u0006\b\u0092\u0001\u0010\u0088\u0001\"\u0006\b\u0093\u0001\u0010\u008a\u0001R'\u0010/\u001a\u00020&8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b/\u0010\u0086\u0001\u001a\u0006\b\u0094\u0001\u0010\u0088\u0001\"\u0006\b\u0095\u0001\u0010\u008a\u0001R'\u00101\u001a\u0002008\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b1\u0010\u0096\u0001\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001\"\u0006\b\u0099\u0001\u0010\u009a\u0001R)\u00104\u001a\u0004\u0018\u0001038\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b4\u0010\u009b\u0001\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001R'\u0010;\u001a\u00020:8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b;\u0010 \u0001\u001a\u0006\b¡\u0001\u0010¢\u0001\"\u0006\b£\u0001\u0010¤\u0001R)\u0010>\u001a\u0004\u0018\u00010=8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b>\u0010¥\u0001\u001a\u0006\b¦\u0001\u0010§\u0001\"\u0006\b¨\u0001\u0010©\u0001R)\u0010A\u001a\u0004\u0018\u00010@8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bA\u0010ª\u0001\u001a\u0006\b«\u0001\u0010¬\u0001\"\u0006\b\u00ad\u0001\u0010®\u0001R'\u0010C\u001a\u00020*8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bC\u0010\u008d\u0001\u001a\u0006\b¯\u0001\u0010\u008f\u0001\"\u0006\b°\u0001\u0010\u0091\u0001R'\u0010E\u001a\u00020D8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bE\u0010±\u0001\u001a\u0006\b²\u0001\u0010³\u0001\"\u0006\b´\u0001\u0010µ\u0001R+\u0010¶\u0001\u001a\u0004\u0018\u00010G8\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b¶\u0001\u0010·\u0001\u001a\u0006\b¸\u0001\u0010¹\u0001\"\u0006\bº\u0001\u0010»\u0001R+\u0010¼\u0001\u001a\u0004\u0018\u00010J8\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\b¼\u0001\u0010½\u0001\u001a\u0006\b¾\u0001\u0010¿\u0001\"\u0006\bÀ\u0001\u0010Á\u0001R+\u0010O\u001a\b\u0012\u0004\u0012\u00020N0M8\u0000@\u0000X\u0080\u000e¢\u0006\u0015\n\u0004\bO\u0010~\u001a\u0005\bÂ\u0001\u0010\u0010\"\u0006\bÃ\u0001\u0010Ä\u0001R+\u0010R\u001a\b\u0012\u0004\u0012\u00020Q0M8\u0000@\u0000X\u0080\u000e¢\u0006\u0015\n\u0004\bR\u0010~\u001a\u0005\bÅ\u0001\u0010\u0010\"\u0006\bÆ\u0001\u0010Ä\u0001R'\u0010T\u001a\u00020S8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bT\u0010Ç\u0001\u001a\u0006\bÈ\u0001\u0010É\u0001\"\u0006\bÊ\u0001\u0010Ë\u0001R'\u0010W\u001a\u00020V8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bW\u0010Ì\u0001\u001a\u0006\bÍ\u0001\u0010Î\u0001\"\u0006\bÏ\u0001\u0010Ð\u0001R,\u0010Ò\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bÒ\u0001\u0010Ó\u0001\u001a\u0006\bÔ\u0001\u0010Õ\u0001\"\u0006\bÖ\u0001\u0010×\u0001R(\u0010]\u001a\u00030Ø\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b]\u0010Ù\u0001\u001a\u0006\bÚ\u0001\u0010Û\u0001\"\u0006\bÜ\u0001\u0010Ý\u0001R(\u0010e\u001a\u00030Ø\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\be\u0010Ù\u0001\u001a\u0006\bÞ\u0001\u0010Û\u0001\"\u0006\bß\u0001\u0010Ý\u0001R(\u0010g\u001a\u00030Ø\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bg\u0010Ù\u0001\u001a\u0006\bà\u0001\u0010Û\u0001\"\u0006\bá\u0001\u0010Ý\u0001R(\u0010i\u001a\u00030Ø\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bi\u0010Ù\u0001\u001a\u0006\bâ\u0001\u0010Û\u0001\"\u0006\bã\u0001\u0010Ý\u0001R(\u0010l\u001a\u00030Ø\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bl\u0010Ù\u0001\u001a\u0006\bä\u0001\u0010Û\u0001\"\u0006\bå\u0001\u0010Ý\u0001R(\u0010n\u001a\u00030Ø\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bn\u0010Ù\u0001\u001a\u0006\bæ\u0001\u0010Û\u0001\"\u0006\bç\u0001\u0010Ý\u0001R'\u0010q\u001a\u00020Y8\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\bq\u0010è\u0001\u001a\u0006\bé\u0001\u0010ê\u0001\"\u0006\bë\u0001\u0010ì\u0001R,\u0010î\u0001\u001a\u0005\u0018\u00010í\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bî\u0001\u0010ï\u0001\u001a\u0006\bð\u0001\u0010ñ\u0001\"\u0006\bò\u0001\u0010ó\u0001R)\u00107\u001a\u0004\u0018\u0001068\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b7\u0010ô\u0001\u001a\u0006\bõ\u0001\u0010ö\u0001\"\u0006\b÷\u0001\u0010ø\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006ù\u0001"}, d2 = {"Lokhttp3/OkHttpClient$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lokhttp3/OkHttpClient;", "okHttpClient", "(Lokhttp3/OkHttpClient;)V", "Lokhttp3/Dispatcher;", "dispatcher", "(Lokhttp3/Dispatcher;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/ConnectionPool;", "connectionPool", "(Lokhttp3/ConnectionPool;)Lokhttp3/OkHttpClient$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Interceptor;", "interceptors", "()Ljava/util/List;", "interceptor", "addInterceptor", "(Lokhttp3/Interceptor;)Lokhttp3/OkHttpClient$Builder;", "Lkotlin/Function1;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/ParameterName;", "name", "chain", "Lokhttp3/Response;", "block", "-addInterceptor", "(Lkotlin/jvm/functions/Function1;)Lokhttp3/OkHttpClient$Builder;", "networkInterceptors", "addNetworkInterceptor", "-addNetworkInterceptor", "Lokhttp3/EventListener;", "eventListener", "(Lokhttp3/EventListener;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/EventListener$Factory;", "eventListenerFactory", "(Lokhttp3/EventListener$Factory;)Lokhttp3/OkHttpClient$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "retryOnConnectionFailure", "(Z)Lokhttp3/OkHttpClient$Builder;", "fastFallback", "Lokhttp3/Authenticator;", "authenticator", "(Lokhttp3/Authenticator;)Lokhttp3/OkHttpClient$Builder;", "followRedirects", "followProtocolRedirects", "followSslRedirects", "Lokhttp3/CookieJar;", "cookieJar", "(Lokhttp3/CookieJar;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/Cache;", "cache", "(Lokhttp3/Cache;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "taskRunner$okhttp", "(Lokhttp3/internal/concurrent/TaskRunner;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/Dns;", "dns", "(Lokhttp3/Dns;)Lokhttp3/OkHttpClient$Builder;", "Ljava/net/Proxy;", "proxy", "(Ljava/net/Proxy;)Lokhttp3/OkHttpClient$Builder;", "Ljava/net/ProxySelector;", "proxySelector", "(Ljava/net/ProxySelector;)Lokhttp3/OkHttpClient$Builder;", "proxyAuthenticator", "Ljavax/net/SocketFactory;", "socketFactory", "(Ljavax/net/SocketFactory;)Lokhttp3/OkHttpClient$Builder;", "Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)Lokhttp3/OkHttpClient$Builder;", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "(Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/X509TrustManager;)Lokhttp3/OkHttpClient$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/ConnectionSpec;", "connectionSpecs", "(Ljava/util/List;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/Protocol;", "protocols", "Ljavax/net/ssl/HostnameVerifier;", "hostnameVerifier", "(Ljavax/net/ssl/HostnameVerifier;)Lokhttp3/OkHttpClient$Builder;", "Lokhttp3/CertificatePinner;", "certificatePinner", "(Lokhttp3/CertificatePinner;)Lokhttp3/OkHttpClient$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "timeout", "Ljava/util/concurrent/TimeUnit;", "unit", "callTimeout", "(JLjava/util/concurrent/TimeUnit;)Lokhttp3/OkHttpClient$Builder;", "j$/time/Duration", "duration", "(Lj$/time/Duration;)Lokhttp3/OkHttpClient$Builder;", "Lkotlin/time/Duration;", "callTimeout-LRDsOJo", "(J)Lokhttp3/OkHttpClient$Builder;", "connectTimeout", "connectTimeout-LRDsOJo", "readTimeout", "readTimeout-LRDsOJo", "writeTimeout", "writeTimeout-LRDsOJo", "interval", "pingInterval", "pingInterval-LRDsOJo", "webSocketCloseTimeout", "webSocketCloseTimeout-LRDsOJo", "bytes", "minWebSocketMessageToCompress", "build", "()Lokhttp3/OkHttpClient;", "Lokhttp3/Dispatcher;", "getDispatcher$okhttp", "()Lokhttp3/Dispatcher;", "setDispatcher$okhttp", "(Lokhttp3/Dispatcher;)V", "Lokhttp3/ConnectionPool;", "getConnectionPool$okhttp", "()Lokhttp3/ConnectionPool;", "setConnectionPool$okhttp", "(Lokhttp3/ConnectionPool;)V", "Ljava/util/List;", "getInterceptors$okhttp", "getNetworkInterceptors$okhttp", "Lokhttp3/EventListener$Factory;", "getEventListenerFactory$okhttp", "()Lokhttp3/EventListener$Factory;", "setEventListenerFactory$okhttp", "(Lokhttp3/EventListener$Factory;)V", "Z", "getRetryOnConnectionFailure$okhttp", "()Z", "setRetryOnConnectionFailure$okhttp", "(Z)V", "getFastFallback$okhttp", "setFastFallback$okhttp", "Lokhttp3/Authenticator;", "getAuthenticator$okhttp", "()Lokhttp3/Authenticator;", "setAuthenticator$okhttp", "(Lokhttp3/Authenticator;)V", "getFollowRedirects$okhttp", "setFollowRedirects$okhttp", "getFollowSslRedirects$okhttp", "setFollowSslRedirects$okhttp", "Lokhttp3/CookieJar;", "getCookieJar$okhttp", "()Lokhttp3/CookieJar;", "setCookieJar$okhttp", "(Lokhttp3/CookieJar;)V", "Lokhttp3/Cache;", "getCache$okhttp", "()Lokhttp3/Cache;", "setCache$okhttp", "(Lokhttp3/Cache;)V", "Lokhttp3/Dns;", "getDns$okhttp", "()Lokhttp3/Dns;", "setDns$okhttp", "(Lokhttp3/Dns;)V", "Ljava/net/Proxy;", "getProxy$okhttp", "()Ljava/net/Proxy;", "setProxy$okhttp", "(Ljava/net/Proxy;)V", "Ljava/net/ProxySelector;", "getProxySelector$okhttp", "()Ljava/net/ProxySelector;", "setProxySelector$okhttp", "(Ljava/net/ProxySelector;)V", "getProxyAuthenticator$okhttp", "setProxyAuthenticator$okhttp", "Ljavax/net/SocketFactory;", "getSocketFactory$okhttp", "()Ljavax/net/SocketFactory;", "setSocketFactory$okhttp", "(Ljavax/net/SocketFactory;)V", "sslSocketFactoryOrNull", "Ljavax/net/ssl/SSLSocketFactory;", "getSslSocketFactoryOrNull$okhttp", "()Ljavax/net/ssl/SSLSocketFactory;", "setSslSocketFactoryOrNull$okhttp", "(Ljavax/net/ssl/SSLSocketFactory;)V", "x509TrustManagerOrNull", "Ljavax/net/ssl/X509TrustManager;", "getX509TrustManagerOrNull$okhttp", "()Ljavax/net/ssl/X509TrustManager;", "setX509TrustManagerOrNull$okhttp", "(Ljavax/net/ssl/X509TrustManager;)V", "getConnectionSpecs$okhttp", "setConnectionSpecs$okhttp", "(Ljava/util/List;)V", "getProtocols$okhttp", "setProtocols$okhttp", "Ljavax/net/ssl/HostnameVerifier;", "getHostnameVerifier$okhttp", "()Ljavax/net/ssl/HostnameVerifier;", "setHostnameVerifier$okhttp", "(Ljavax/net/ssl/HostnameVerifier;)V", "Lokhttp3/CertificatePinner;", "getCertificatePinner$okhttp", "()Lokhttp3/CertificatePinner;", "setCertificatePinner$okhttp", "(Lokhttp3/CertificatePinner;)V", "Lokhttp3/internal/tls/CertificateChainCleaner;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "setCertificateChainCleaner$okhttp", "(Lokhttp3/internal/tls/CertificateChainCleaner;)V", _UrlKt.FRAGMENT_ENCODE_SET, "I", "getCallTimeout$okhttp", "()I", "setCallTimeout$okhttp", "(I)V", "getConnectTimeout$okhttp", "setConnectTimeout$okhttp", "getReadTimeout$okhttp", "setReadTimeout$okhttp", "getWriteTimeout$okhttp", "setWriteTimeout$okhttp", "getPingInterval$okhttp", "setPingInterval$okhttp", "getWebSocketCloseTimeout$okhttp", "setWebSocketCloseTimeout$okhttp", "J", "getMinWebSocketMessageToCompress$okhttp", "()J", "setMinWebSocketMessageToCompress$okhttp", "(J)V", "Lokhttp3/internal/connection/RouteDatabase;", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase$okhttp", "()Lokhttp3/internal/connection/RouteDatabase;", "setRouteDatabase$okhttp", "(Lokhttp3/internal/connection/RouteDatabase;)V", "Lokhttp3/internal/concurrent/TaskRunner;", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "setTaskRunner$okhttp", "(Lokhttp3/internal/concurrent/TaskRunner;)V", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nOkHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,1388:1\n1#2:1389\n242#3:1390\n*S KotlinDebug\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder\n*L\n1048#1:1390\n*E\n"})
    public static final class Builder {
        private Authenticator authenticator;
        private Cache cache;
        private int callTimeout;
        private CertificateChainCleaner certificateChainCleaner;
        private CertificatePinner certificatePinner;
        private int connectTimeout;
        private ConnectionPool connectionPool;
        private List<ConnectionSpec> connectionSpecs;
        private CookieJar cookieJar;
        private Dispatcher dispatcher;
        private Dns dns;
        private EventListener.Factory eventListenerFactory;
        private boolean fastFallback;
        private boolean followRedirects;
        private boolean followSslRedirects;
        private HostnameVerifier hostnameVerifier;
        private final List<Interceptor> interceptors;
        private long minWebSocketMessageToCompress;
        private final List<Interceptor> networkInterceptors;
        private int pingInterval;
        private List<? extends Protocol> protocols;
        private Proxy proxy;
        private Authenticator proxyAuthenticator;
        private ProxySelector proxySelector;
        private int readTimeout;
        private boolean retryOnConnectionFailure;
        private RouteDatabase routeDatabase;
        private SocketFactory socketFactory;
        private SSLSocketFactory sslSocketFactoryOrNull;
        private TaskRunner taskRunner;
        private int webSocketCloseTimeout;
        private int writeTimeout;
        private X509TrustManager x509TrustManagerOrNull;

        public Builder() {
            this.dispatcher = new Dispatcher();
            this.interceptors = new ArrayList();
            this.networkInterceptors = new ArrayList();
            this.eventListenerFactory = _UtilJvmKt.asFactory(EventListener.NONE);
            this.retryOnConnectionFailure = true;
            this.fastFallback = true;
            Authenticator r1 = Authenticator.NONE;
            this.authenticator = r1;
            this.followRedirects = true;
            this.followSslRedirects = true;
            this.cookieJar = CookieJar.NO_COOKIES;
            this.dns = Dns.SYSTEM;
            this.proxyAuthenticator = r1;
            this.socketFactory = SocketFactory.getDefault();
            Companion r0 = OkHttpClient.Companion;
            this.connectionSpecs = r0.getDEFAULT_CONNECTION_SPECS$okhttp();
            this.protocols = r0.getDEFAULT_PROTOCOLS$okhttp();
            this.hostnameVerifier = OkHostnameVerifier.INSTANCE;
            this.certificatePinner = CertificatePinner.DEFAULT;
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
            this.webSocketCloseTimeout = 60000;
            this.minWebSocketMessageToCompress = 1024;
        }

        public Builder(OkHttpClient r3) {
            this();
            this.dispatcher = r3.dispatcher();
            this.connectionPool = r3.connectionPool();
            CollectionsKt.addAll(this.interceptors, r3.interceptors());
            CollectionsKt.addAll(this.networkInterceptors, r3.networkInterceptors());
            this.eventListenerFactory = r3.eventListenerFactory();
            this.retryOnConnectionFailure = r3.retryOnConnectionFailure();
            this.fastFallback = r3.fastFallback();
            this.authenticator = r3.authenticator();
            this.followRedirects = r3.followRedirects();
            this.followSslRedirects = r3.followSslRedirects();
            this.cookieJar = r3.cookieJar();
            this.cache = r3.cache();
            this.dns = r3.dns();
            this.proxy = r3.proxy();
            this.proxySelector = r3.proxySelector();
            this.proxyAuthenticator = r3.proxyAuthenticator();
            this.socketFactory = r3.socketFactory();
            this.sslSocketFactoryOrNull = OkHttpClient.access$getSslSocketFactoryOrNull$p(r3);
            this.x509TrustManagerOrNull = r3.x509TrustManager();
            this.connectionSpecs = r3.connectionSpecs();
            this.protocols = r3.protocols();
            this.hostnameVerifier = r3.hostnameVerifier();
            this.certificatePinner = r3.certificatePinner();
            this.certificateChainCleaner = r3.certificateChainCleaner();
            this.callTimeout = r3.callTimeoutMillis();
            this.connectTimeout = r3.connectTimeoutMillis();
            this.readTimeout = r3.readTimeoutMillis();
            this.writeTimeout = r3.writeTimeoutMillis();
            this.pingInterval = r3.pingIntervalMillis();
            this.webSocketCloseTimeout = r3.webSocketCloseTimeout();
            this.minWebSocketMessageToCompress = r3.minWebSocketMessageToCompress();
            this.routeDatabase = r3.getRouteDatabase$okhttp();
            this.taskRunner = r3.getTaskRunner$okhttp();
        }

        @JvmName(name = "-addInterceptor")
        /* renamed from: -addInterceptor, reason: not valid java name */
        public final Builder m3713addInterceptor(Function1<? super Interceptor.Chain, Response> r2) {
            return addInterceptor(new OkHttpClient$Builder$addInterceptor$2(r2));
        }

        @JvmName(name = "-addNetworkInterceptor")
        /* renamed from: -addNetworkInterceptor, reason: not valid java name */
        public final Builder m3714addNetworkInterceptor(Function1<? super Interceptor.Chain, Response> r2) {
            return addNetworkInterceptor(new OkHttpClient$Builder$addNetworkInterceptor$2(r2));
        }

        public final Builder addInterceptor(Interceptor r2) {
            this.interceptors.add(r2);
            return this;
        }

        public final Builder addNetworkInterceptor(Interceptor r2) {
            this.networkInterceptors.add(r2);
            return this;
        }

        public final Builder authenticator(Authenticator r1) {
            this.authenticator = r1;
            return this;
        }

        public final OkHttpClient build() {
            return new OkHttpClient(this);
        }

        public final Builder cache(Cache r1) {
            this.cache = r1;
            return this;
        }

        public final Builder callTimeout(long r2, TimeUnit r4) {
            this.callTimeout = _UtilJvmKt.checkDuration("timeout", r2, r4);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder callTimeout(Duration r3) {
            callTimeout(r3.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        /* renamed from: callTimeout-LRDsOJo, reason: not valid java name */
        public final Builder m3715callTimeoutLRDsOJo(long r2) {
            this.callTimeout = _UtilJvmKt.m3745checkDurationHG0u8IE("duration", r2);
            return this;
        }

        public final Builder certificatePinner(CertificatePinner r2) {
            if (Intrinsics.areEqual(r2, this.certificatePinner) == true) goto L5;
            this.routeDatabase = null;
        L5:
            this.certificatePinner = r2;
            return this;
        }

        public final Builder connectTimeout(long r2, TimeUnit r4) {
            this.connectTimeout = _UtilJvmKt.checkDuration("timeout", r2, r4);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder connectTimeout(Duration r3) {
            connectTimeout(r3.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        /* renamed from: connectTimeout-LRDsOJo, reason: not valid java name */
        public final Builder m3716connectTimeoutLRDsOJo(long r2) {
            this.connectTimeout = _UtilJvmKt.m3745checkDurationHG0u8IE("duration", r2);
            return this;
        }

        public final Builder connectionPool(ConnectionPool r1) {
            this.connectionPool = r1;
            return this;
        }

        public final Builder connectionSpecs(List<ConnectionSpec> r2) {
            if (Intrinsics.areEqual(r2, this.connectionSpecs) == true) goto L5;
            this.routeDatabase = null;
        L5:
            this.connectionSpecs = _UtilJvmKt.toImmutableList(r2);
            return this;
        }

        public final Builder cookieJar(CookieJar r1) {
            this.cookieJar = r1;
            return this;
        }

        public final Builder dispatcher(Dispatcher r1) {
            this.dispatcher = r1;
            return this;
        }

        public final Builder dns(Dns r2) {
            if (Intrinsics.areEqual(r2, this.dns) == true) goto L5;
            this.routeDatabase = null;
        L5:
            this.dns = r2;
            return this;
        }

        public final Builder eventListener(EventListener r1) {
            this.eventListenerFactory = _UtilJvmKt.asFactory(r1);
            return this;
        }

        public final Builder eventListenerFactory(EventListener.Factory r1) {
            this.eventListenerFactory = r1;
            return this;
        }

        public final Builder fastFallback(boolean r1) {
            this.fastFallback = r1;
            return this;
        }

        public final Builder followRedirects(boolean r1) {
            this.followRedirects = r1;
            return this;
        }

        public final Builder followSslRedirects(boolean r1) {
            this.followSslRedirects = r1;
            return this;
        }

        public final Authenticator getAuthenticator$okhttp() {
            return this.authenticator;
        }

        public final Cache getCache$okhttp() {
            return this.cache;
        }

        public final int getCallTimeout$okhttp() {
            return this.callTimeout;
        }

        public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
            return this.certificateChainCleaner;
        }

        public final CertificatePinner getCertificatePinner$okhttp() {
            return this.certificatePinner;
        }

        public final int getConnectTimeout$okhttp() {
            return this.connectTimeout;
        }

        public final ConnectionPool getConnectionPool$okhttp() {
            return this.connectionPool;
        }

        public final List<ConnectionSpec> getConnectionSpecs$okhttp() {
            return this.connectionSpecs;
        }

        public final CookieJar getCookieJar$okhttp() {
            return this.cookieJar;
        }

        public final Dispatcher getDispatcher$okhttp() {
            return this.dispatcher;
        }

        public final Dns getDns$okhttp() {
            return this.dns;
        }

        public final EventListener.Factory getEventListenerFactory$okhttp() {
            return this.eventListenerFactory;
        }

        public final boolean getFastFallback$okhttp() {
            return this.fastFallback;
        }

        public final boolean getFollowRedirects$okhttp() {
            return this.followRedirects;
        }

        public final boolean getFollowSslRedirects$okhttp() {
            return this.followSslRedirects;
        }

        public final HostnameVerifier getHostnameVerifier$okhttp() {
            return this.hostnameVerifier;
        }

        public final List<Interceptor> getInterceptors$okhttp() {
            return this.interceptors;
        }

        public final long getMinWebSocketMessageToCompress$okhttp() {
            return this.minWebSocketMessageToCompress;
        }

        public final List<Interceptor> getNetworkInterceptors$okhttp() {
            return this.networkInterceptors;
        }

        public final int getPingInterval$okhttp() {
            return this.pingInterval;
        }

        public final List<Protocol> getProtocols$okhttp() {
            return this.protocols;
        }

        public final Proxy getProxy$okhttp() {
            return this.proxy;
        }

        public final Authenticator getProxyAuthenticator$okhttp() {
            return this.proxyAuthenticator;
        }

        public final ProxySelector getProxySelector$okhttp() {
            return this.proxySelector;
        }

        public final int getReadTimeout$okhttp() {
            return this.readTimeout;
        }

        public final boolean getRetryOnConnectionFailure$okhttp() {
            return this.retryOnConnectionFailure;
        }

        public final RouteDatabase getRouteDatabase$okhttp() {
            return this.routeDatabase;
        }

        public final SocketFactory getSocketFactory$okhttp() {
            return this.socketFactory;
        }

        public final SSLSocketFactory getSslSocketFactoryOrNull$okhttp() {
            return this.sslSocketFactoryOrNull;
        }

        public final TaskRunner getTaskRunner$okhttp() {
            return this.taskRunner;
        }

        public final int getWebSocketCloseTimeout$okhttp() {
            return this.webSocketCloseTimeout;
        }

        public final int getWriteTimeout$okhttp() {
            return this.writeTimeout;
        }

        public final X509TrustManager getX509TrustManagerOrNull$okhttp() {
            return this.x509TrustManagerOrNull;
        }

        public final Builder hostnameVerifier(HostnameVerifier r2) {
            if (Intrinsics.areEqual(r2, this.hostnameVerifier) == true) goto L5;
            this.routeDatabase = null;
        L5:
            this.hostnameVerifier = r2;
            return this;
        }

        public final List<Interceptor> interceptors() {
            return this.interceptors;
        }

        public final Builder minWebSocketMessageToCompress(long r3) {
            if (r3 < 0) goto L6;
            this.minWebSocketMessageToCompress = r3;
            return this;
        L6:
            Buffer$$ExternalSyntheticBUOutline3.m("minWebSocketMessageToCompress must be positive: ", r3);
            return null;
        }

        public final List<Interceptor> networkInterceptors() {
            return this.networkInterceptors;
        }

        public final Builder pingInterval(long r2, TimeUnit r4) {
            this.pingInterval = _UtilJvmKt.checkDuration("interval", r2, r4);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder pingInterval(Duration r3) {
            pingInterval(r3.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        /* renamed from: pingInterval-LRDsOJo, reason: not valid java name */
        public final Builder m3717pingIntervalLRDsOJo(long r2) {
            this.pingInterval = _UtilJvmKt.m3745checkDurationHG0u8IE("duration", r2);
            return this;
        }

        public final Builder protocols(List<? extends Protocol> r4) {
            List r42 = CollectionsKt.toMutableList(r4);
            Protocol r0 = Protocol.H2_PRIOR_KNOWLEDGE;
            if (r42.contains(r0) == true) goto L10;
            if (r42.contains(Protocol.HTTP_1_1) == true) goto L10;
            Options$Companion$$ExternalSyntheticBUOutline0.m("protocols must contain h2_prior_knowledge or http/1.1: ", r42);
            return null;
        L10:
            if (r42.contains(r0) == false) goto L17;
            if (r42.size() <= 1) goto L17;
            Options$Companion$$ExternalSyntheticBUOutline0.m("protocols containing h2_prior_knowledge cannot use other protocols: ", r42);
            return null;
        L17:
            if (r42.contains(Protocol.HTTP_1_0) == false) goto L19;
            Options$Companion$$ExternalSyntheticBUOutline0.m("protocols must not contain http/1.0: ", r42);
            return null;
        L19:
            if (r42.contains(null) == true) goto L25;
            r42.remove(Protocol.SPDY_3);
            if (Intrinsics.areEqual(r42, this.protocols) == true) goto L23;
            this.routeDatabase = null;
        L23:
            this.protocols = Collections.unmodifiableList(r42);
            return this;
        L25:
            f$$ExternalSyntheticBUOutline1.m("protocols must not contain null");
            return null;
        }

        public final Builder proxy(Proxy r2) {
            if (Intrinsics.areEqual(r2, this.proxy) == true) goto L5;
            this.routeDatabase = null;
        L5:
            this.proxy = r2;
            return this;
        }

        public final Builder proxyAuthenticator(Authenticator r2) {
            if (Intrinsics.areEqual(r2, this.proxyAuthenticator) == true) goto L5;
            this.routeDatabase = null;
        L5:
            this.proxyAuthenticator = r2;
            return this;
        }

        public final Builder proxySelector(ProxySelector r2) {
            if (Intrinsics.areEqual(r2, this.proxySelector) == true) goto L5;
            this.routeDatabase = null;
        L5:
            this.proxySelector = r2;
            return this;
        }

        public final Builder readTimeout(long r2, TimeUnit r4) {
            this.readTimeout = _UtilJvmKt.checkDuration("timeout", r2, r4);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder readTimeout(Duration r3) {
            readTimeout(r3.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        /* renamed from: readTimeout-LRDsOJo, reason: not valid java name */
        public final Builder m3718readTimeoutLRDsOJo(long r2) {
            this.readTimeout = _UtilJvmKt.m3745checkDurationHG0u8IE("duration", r2);
            return this;
        }

        public final Builder retryOnConnectionFailure(boolean r1) {
            this.retryOnConnectionFailure = r1;
            return this;
        }

        public final void setAuthenticator$okhttp(Authenticator r1) {
            this.authenticator = r1;
        }

        public final void setCache$okhttp(Cache r1) {
            this.cache = r1;
        }

        public final void setCallTimeout$okhttp(int r1) {
            this.callTimeout = r1;
        }

        public final void setCertificateChainCleaner$okhttp(CertificateChainCleaner r1) {
            this.certificateChainCleaner = r1;
        }

        public final void setCertificatePinner$okhttp(CertificatePinner r1) {
            this.certificatePinner = r1;
        }

        public final void setConnectTimeout$okhttp(int r1) {
            this.connectTimeout = r1;
        }

        public final void setConnectionPool$okhttp(ConnectionPool r1) {
            this.connectionPool = r1;
        }

        public final void setConnectionSpecs$okhttp(List<ConnectionSpec> r1) {
            this.connectionSpecs = r1;
        }

        public final void setCookieJar$okhttp(CookieJar r1) {
            this.cookieJar = r1;
        }

        public final void setDispatcher$okhttp(Dispatcher r1) {
            this.dispatcher = r1;
        }

        public final void setDns$okhttp(Dns r1) {
            this.dns = r1;
        }

        public final void setEventListenerFactory$okhttp(EventListener.Factory r1) {
            this.eventListenerFactory = r1;
        }

        public final void setFastFallback$okhttp(boolean r1) {
            this.fastFallback = r1;
        }

        public final void setFollowRedirects$okhttp(boolean r1) {
            this.followRedirects = r1;
        }

        public final void setFollowSslRedirects$okhttp(boolean r1) {
            this.followSslRedirects = r1;
        }

        public final void setHostnameVerifier$okhttp(HostnameVerifier r1) {
            this.hostnameVerifier = r1;
        }

        public final void setMinWebSocketMessageToCompress$okhttp(long r1) {
            this.minWebSocketMessageToCompress = r1;
        }

        public final void setPingInterval$okhttp(int r1) {
            this.pingInterval = r1;
        }

        public final void setProtocols$okhttp(List<? extends Protocol> r1) {
            this.protocols = r1;
        }

        public final void setProxy$okhttp(Proxy r1) {
            this.proxy = r1;
        }

        public final void setProxyAuthenticator$okhttp(Authenticator r1) {
            this.proxyAuthenticator = r1;
        }

        public final void setProxySelector$okhttp(ProxySelector r1) {
            this.proxySelector = r1;
        }

        public final void setReadTimeout$okhttp(int r1) {
            this.readTimeout = r1;
        }

        public final void setRetryOnConnectionFailure$okhttp(boolean r1) {
            this.retryOnConnectionFailure = r1;
        }

        public final void setRouteDatabase$okhttp(RouteDatabase r1) {
            this.routeDatabase = r1;
        }

        public final void setSocketFactory$okhttp(SocketFactory r1) {
            this.socketFactory = r1;
        }

        public final void setSslSocketFactoryOrNull$okhttp(SSLSocketFactory r1) {
            this.sslSocketFactoryOrNull = r1;
        }

        public final void setTaskRunner$okhttp(TaskRunner r1) {
            this.taskRunner = r1;
        }

        public final void setWebSocketCloseTimeout$okhttp(int r1) {
            this.webSocketCloseTimeout = r1;
        }

        public final void setWriteTimeout$okhttp(int r1) {
            this.writeTimeout = r1;
        }

        public final void setX509TrustManagerOrNull$okhttp(X509TrustManager r1) {
            this.x509TrustManagerOrNull = r1;
        }

        public final Builder socketFactory(SocketFactory r2) {
            if ((r2 instanceof SSLSocketFactory) == false) goto L5;
            f$$ExternalSyntheticBUOutline1.m("socketFactory instanceof SSLSocketFactory");
            return null;
        L5:
            if (Intrinsics.areEqual(r2, this.socketFactory) == true) goto L7;
            this.routeDatabase = null;
        L7:
            this.socketFactory = r2;
            return this;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use the sslSocketFactory overload that accepts a X509TrustManager.")
        public final Builder sslSocketFactory(SSLSocketFactory r4) {
            if (Intrinsics.areEqual(r4, this.sslSocketFactoryOrNull) == true) goto L5;
            this.routeDatabase = null;
        L5:
            this.sslSocketFactoryOrNull = r4;
            Platform.Companion r0 = Platform.Companion;
            X509TrustManager r2 = r0.get().trustManager(r4);
            if (r2 == null) goto L9;
            this.x509TrustManagerOrNull = r2;
            this.certificateChainCleaner = r0.get().buildCertificateChainCleaner(this.x509TrustManagerOrNull);
            return this;
        L9:
            StringBuilder r3 = new StringBuilder("Unable to extract the trust manager on ");
            r3.append(r0.get());
            OkHttpClient$Builder$$ExternalSyntheticBUOutline0.m(r3, ", sslSocketFactory is ", r4.getClass());
            return null;
        }

        public final Builder sslSocketFactory(SSLSocketFactory r2, X509TrustManager r3) {
            if (Intrinsics.areEqual(r2, this.sslSocketFactoryOrNull) == true) goto L5;
        L6:
            this.routeDatabase = null;
        L7:
            this.sslSocketFactoryOrNull = r2;
            this.certificateChainCleaner = CertificateChainCleaner.Companion.get(r3);
            this.x509TrustManagerOrNull = r3;
            return this;
        L5:
            if (Intrinsics.areEqual(r3, this.x509TrustManagerOrNull) == true) goto L7;
            goto L6
        }

        public final Builder taskRunner$okhttp(TaskRunner r1) {
            this.taskRunner = r1;
            return this;
        }

        public final Builder webSocketCloseTimeout(long r2, TimeUnit r4) {
            this.webSocketCloseTimeout = _UtilJvmKt.checkDuration("webSocketCloseTimeout", r2, r4);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder webSocketCloseTimeout(Duration r3) {
            webSocketCloseTimeout(r3.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        /* renamed from: webSocketCloseTimeout-LRDsOJo, reason: not valid java name */
        public final Builder m3719webSocketCloseTimeoutLRDsOJo(long r2) {
            this.webSocketCloseTimeout = _UtilJvmKt.m3745checkDurationHG0u8IE("duration", r2);
            return this;
        }

        public final Builder writeTimeout(long r2, TimeUnit r4) {
            this.writeTimeout = _UtilJvmKt.checkDuration("timeout", r2, r4);
            return this;
        }

        @IgnoreJRERequirement
        public final Builder writeTimeout(Duration r3) {
            writeTimeout(r3.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        /* renamed from: writeTimeout-LRDsOJo, reason: not valid java name */
        public final Builder m3720writeTimeoutLRDsOJo(long r2) {
            this.writeTimeout = _UtilJvmKt.m3745checkDurationHG0u8IE("duration", r2);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lokhttp3/OkHttpClient$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEFAULT_PROTOCOLS", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Protocol;", "getDEFAULT_PROTOCOLS$okhttp", "()Ljava/util/List;", "DEFAULT_CONNECTION_SPECS", "Lokhttp3/ConnectionSpec;", "getDEFAULT_CONNECTION_SPECS$okhttp", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final List<ConnectionSpec> getDEFAULT_CONNECTION_SPECS$okhttp() {
            return OkHttpClient.access$getDEFAULT_CONNECTION_SPECS$cp();
        }

        public final List<Protocol> getDEFAULT_PROTOCOLS$okhttp() {
            return OkHttpClient.access$getDEFAULT_PROTOCOLS$cp();
        }
    }

    static {
        Companion = new Companion(null);
        DEFAULT_PROTOCOLS = _UtilJvmKt.immutableListOf(new Protocol[]{Protocol.HTTP_2, Protocol.HTTP_1_1});
        DEFAULT_CONNECTION_SPECS = _UtilJvmKt.immutableListOf(new ConnectionSpec[]{ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT});
    }

    public OkHttpClient() {
        this(new Builder());
    }

    public OkHttpClient(Builder r4) {
        this.dispatcher = r4.getDispatcher$okhttp();
        this.interceptors = _UtilJvmKt.toImmutableList(r4.getInterceptors$okhttp());
        this.networkInterceptors = _UtilJvmKt.toImmutableList(r4.getNetworkInterceptors$okhttp());
        this.eventListenerFactory = r4.getEventListenerFactory$okhttp();
        this.retryOnConnectionFailure = r4.getRetryOnConnectionFailure$okhttp();
        this.fastFallback = r4.getFastFallback$okhttp();
        this.authenticator = r4.getAuthenticator$okhttp();
        this.followRedirects = r4.getFollowRedirects$okhttp();
        this.followSslRedirects = r4.getFollowSslRedirects$okhttp();
        this.cookieJar = r4.getCookieJar$okhttp();
        this.cache = r4.getCache$okhttp();
        this.dns = r4.getDns$okhttp();
        this.proxy = r4.getProxy$okhttp();
        if (r4.getProxy$okhttp() == null) goto L5;
        ProxySelector r0 = NullProxySelector.INSTANCE;
    L10:
        this.proxySelector = r0;
        this.proxyAuthenticator = r4.getProxyAuthenticator$okhttp();
        this.socketFactory = r4.getSocketFactory$okhttp();
        List<ConnectionSpec> r02 = r4.getConnectionSpecs$okhttp();
        this.connectionSpecs = r02;
        this.protocols = r4.getProtocols$okhttp();
        this.hostnameVerifier = r4.getHostnameVerifier$okhttp();
        this.callTimeoutMillis = r4.getCallTimeout$okhttp();
        this.connectTimeoutMillis = r4.getConnectTimeout$okhttp();
        this.readTimeoutMillis = r4.getReadTimeout$okhttp();
        this.writeTimeoutMillis = r4.getWriteTimeout$okhttp();
        this.pingIntervalMillis = r4.getPingInterval$okhttp();
        this.webSocketCloseTimeout = r4.getWebSocketCloseTimeout$okhttp();
        this.minWebSocketMessageToCompress = r4.getMinWebSocketMessageToCompress$okhttp();
        RouteDatabase r1 = r4.getRouteDatabase$okhttp();
        if (r1 != null) goto L13;
        r1 = new RouteDatabase();
    L13:
        this.routeDatabase = r1;
        TaskRunner r12 = r4.getTaskRunner$okhttp();
        if (r12 != null) goto L16;
        r12 = TaskRunner.INSTANCE;
    L16:
        this.taskRunner = r12;
        ConnectionPool r13 = r4.getConnectionPool$okhttp();
        if (r13 != null) goto L19;
        r13 = new ConnectionPool();
        r4.setConnectionPool$okhttp(r13);
    L19:
        this.connectionPool = r13;
        List<ConnectionSpec> r03 = r02;
        if ((r03 instanceof Collection) == true) goto L22;
    L24:
        Iterator<T> r04 = r03.iterator();
    L26:
        if (r04.hasNext() == false) goto L33;
        if (((ConnectionSpec) r04.next()).isTls() == false) goto L26;
        if (r4.getSslSocketFactoryOrNull$okhttp() == null) goto L32;
        this.sslSocketFactoryOrNull = r4.getSslSocketFactoryOrNull$okhttp();
        CertificateChainCleaner r05 = r4.getCertificateChainCleaner$okhttp();
        this.certificateChainCleaner = r05;
        this.x509TrustManager = r4.getX509TrustManagerOrNull$okhttp();
        this.certificatePinner = r4.getCertificatePinner$okhttp().withCertificateChainCleaner$okhttp(r05);
    L34:
        verifyClientState();
        return;
    L32:
        Platform.Companion r06 = Platform.Companion;
        X509TrustManager r14 = r06.get().platformTrustManager();
        this.x509TrustManager = r14;
        this.sslSocketFactoryOrNull = r06.get().newSslSocketFactory(r14);
        CertificateChainCleaner r07 = CertificateChainCleaner.Companion.get(r14);
        this.certificateChainCleaner = r07;
        this.certificatePinner = r4.getCertificatePinner$okhttp().withCertificateChainCleaner$okhttp(r07);
    L33:
        this.sslSocketFactoryOrNull = null;
        this.certificateChainCleaner = null;
        this.x509TrustManager = null;
        this.certificatePinner = CertificatePinner.DEFAULT;
        goto L34
    L22:
        if (r03.isEmpty() == false) goto L24;
    L5:
        r0 = r4.getProxySelector$okhttp();
        if (r0 != null) goto L10;
        r0 = ProxySelector.getDefault();
        if (r0 != null) goto L10;
        r0 = NullProxySelector.INSTANCE;
        goto L10
    }

    public static final /* synthetic */ List access$getDEFAULT_CONNECTION_SPECS$cp() {
        return DEFAULT_CONNECTION_SPECS;
    }

    public static final /* synthetic */ List access$getDEFAULT_PROTOCOLS$cp() {
        return DEFAULT_PROTOCOLS;
    }

    public static final /* synthetic */ SSLSocketFactory access$getSslSocketFactoryOrNull$p(OkHttpClient r0) {
        return r0.sslSocketFactoryOrNull;
    }

    private final void verifyClientState() {
        if (this.interceptors.contains(null) == false) goto L5;
        OkHttpClient$$ExternalSyntheticBUOutline0.m("Null interceptor: ", this.interceptors);
        return;
    L5:
        if (this.networkInterceptors.contains(null) == true) goto L47;
        List<ConnectionSpec> r0 = this.connectionSpecs;
        if ((r0 instanceof Collection) == true) goto L9;
    L11:
        Iterator<T> r02 = r0.iterator();
    L13:
        if (r02.hasNext() == false) goto L30;
        if (((ConnectionSpec) r02.next()).isTls() == false) goto L13;
        if (this.sslSocketFactoryOrNull != null) goto L19;
        Segment$$ExternalSyntheticBUOutline1.m("sslSocketFactory == null");
        return;
    L19:
        if (this.certificateChainCleaner != null) goto L21;
        Segment$$ExternalSyntheticBUOutline1.m("certificateChainCleaner == null");
        return;
    L21:
        if (this.x509TrustManager == null) goto L23;
        return;
    L23:
        Segment$$ExternalSyntheticBUOutline1.m("x509TrustManager == null");
        return;
    L30:
        if (this.sslSocketFactoryOrNull == null) goto L32;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L32:
        if (this.certificateChainCleaner == null) goto L34;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L34:
        if (this.x509TrustManager == null) goto L36;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L36:
        if (Intrinsics.areEqual(this.certificatePinner, CertificatePinner.DEFAULT) == false) goto L39;
        Unit r2 = Unit.INSTANCE;
        return;
    L39:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L9:
        if (r0.isEmpty() == false) goto L11;
    L47:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("Null network interceptor: ", this.networkInterceptors);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "authenticator", imports = {}))
    @JvmName(name = "-deprecated_authenticator")
    /* renamed from: -deprecated_authenticator, reason: not valid java name */
    public final Authenticator m3687deprecated_authenticator() {
        return this.authenticator;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cache", imports = {}))
    @JvmName(name = "-deprecated_cache")
    /* renamed from: -deprecated_cache, reason: not valid java name */
    public final Cache m3688deprecated_cache() {
        return this.cache;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "callTimeoutMillis", imports = {}))
    @JvmName(name = "-deprecated_callTimeoutMillis")
    /* renamed from: -deprecated_callTimeoutMillis, reason: not valid java name */
    public final int m3689deprecated_callTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "certificatePinner", imports = {}))
    @JvmName(name = "-deprecated_certificatePinner")
    /* renamed from: -deprecated_certificatePinner, reason: not valid java name */
    public final CertificatePinner m3690deprecated_certificatePinner() {
        return this.certificatePinner;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectTimeoutMillis", imports = {}))
    @JvmName(name = "-deprecated_connectTimeoutMillis")
    /* renamed from: -deprecated_connectTimeoutMillis, reason: not valid java name */
    public final int m3691deprecated_connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionPool", imports = {}))
    @JvmName(name = "-deprecated_connectionPool")
    /* renamed from: -deprecated_connectionPool, reason: not valid java name */
    public final ConnectionPool m3692deprecated_connectionPool() {
        return this.connectionPool;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionSpecs", imports = {}))
    @JvmName(name = "-deprecated_connectionSpecs")
    /* renamed from: -deprecated_connectionSpecs, reason: not valid java name */
    public final List<ConnectionSpec> m3693deprecated_connectionSpecs() {
        return this.connectionSpecs;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cookieJar", imports = {}))
    @JvmName(name = "-deprecated_cookieJar")
    /* renamed from: -deprecated_cookieJar, reason: not valid java name */
    public final CookieJar m3694deprecated_cookieJar() {
        return this.cookieJar;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "dispatcher", imports = {}))
    @JvmName(name = "-deprecated_dispatcher")
    /* renamed from: -deprecated_dispatcher, reason: not valid java name */
    public final Dispatcher m3695deprecated_dispatcher() {
        return this.dispatcher;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "dns", imports = {}))
    @JvmName(name = "-deprecated_dns")
    /* renamed from: -deprecated_dns, reason: not valid java name */
    public final Dns m3696deprecated_dns() {
        return this.dns;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "eventListenerFactory", imports = {}))
    @JvmName(name = "-deprecated_eventListenerFactory")
    /* renamed from: -deprecated_eventListenerFactory, reason: not valid java name */
    public final EventListener.Factory m3697deprecated_eventListenerFactory() {
        return this.eventListenerFactory;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "followRedirects", imports = {}))
    @JvmName(name = "-deprecated_followRedirects")
    /* renamed from: -deprecated_followRedirects, reason: not valid java name */
    public final boolean m3698deprecated_followRedirects() {
        return this.followRedirects;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "followSslRedirects", imports = {}))
    @JvmName(name = "-deprecated_followSslRedirects")
    /* renamed from: -deprecated_followSslRedirects, reason: not valid java name */
    public final boolean m3699deprecated_followSslRedirects() {
        return this.followSslRedirects;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hostnameVerifier", imports = {}))
    @JvmName(name = "-deprecated_hostnameVerifier")
    /* renamed from: -deprecated_hostnameVerifier, reason: not valid java name */
    public final HostnameVerifier m3700deprecated_hostnameVerifier() {
        return this.hostnameVerifier;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "interceptors", imports = {}))
    @JvmName(name = "-deprecated_interceptors")
    /* renamed from: -deprecated_interceptors, reason: not valid java name */
    public final List<Interceptor> m3701deprecated_interceptors() {
        return this.interceptors;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "networkInterceptors", imports = {}))
    @JvmName(name = "-deprecated_networkInterceptors")
    /* renamed from: -deprecated_networkInterceptors, reason: not valid java name */
    public final List<Interceptor> m3702deprecated_networkInterceptors() {
        return this.networkInterceptors;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pingIntervalMillis", imports = {}))
    @JvmName(name = "-deprecated_pingIntervalMillis")
    /* renamed from: -deprecated_pingIntervalMillis, reason: not valid java name */
    public final int m3703deprecated_pingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "protocols", imports = {}))
    @JvmName(name = "-deprecated_protocols")
    /* renamed from: -deprecated_protocols, reason: not valid java name */
    public final List<Protocol> m3704deprecated_protocols() {
        return this.protocols;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}))
    @JvmName(name = "-deprecated_proxy")
    /* renamed from: -deprecated_proxy, reason: not valid java name */
    public final Proxy m3705deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxyAuthenticator", imports = {}))
    @JvmName(name = "-deprecated_proxyAuthenticator")
    /* renamed from: -deprecated_proxyAuthenticator, reason: not valid java name */
    public final Authenticator m3706deprecated_proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxySelector", imports = {}))
    @JvmName(name = "-deprecated_proxySelector")
    /* renamed from: -deprecated_proxySelector, reason: not valid java name */
    public final ProxySelector m3707deprecated_proxySelector() {
        return this.proxySelector;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "readTimeoutMillis", imports = {}))
    @JvmName(name = "-deprecated_readTimeoutMillis")
    /* renamed from: -deprecated_readTimeoutMillis, reason: not valid java name */
    public final int m3708deprecated_readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "retryOnConnectionFailure", imports = {}))
    @JvmName(name = "-deprecated_retryOnConnectionFailure")
    /* renamed from: -deprecated_retryOnConnectionFailure, reason: not valid java name */
    public final boolean m3709deprecated_retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "socketFactory", imports = {}))
    @JvmName(name = "-deprecated_socketFactory")
    /* renamed from: -deprecated_socketFactory, reason: not valid java name */
    public final SocketFactory m3710deprecated_socketFactory() {
        return this.socketFactory;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sslSocketFactory", imports = {}))
    @JvmName(name = "-deprecated_sslSocketFactory")
    /* renamed from: -deprecated_sslSocketFactory, reason: not valid java name */
    public final SSLSocketFactory m3711deprecated_sslSocketFactory() {
        return sslSocketFactory();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "writeTimeoutMillis", imports = {}))
    @JvmName(name = "-deprecated_writeTimeoutMillis")
    /* renamed from: -deprecated_writeTimeoutMillis, reason: not valid java name */
    public final int m3712deprecated_writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    public final Address address(HttpUrl r15) {
        if (r15.isHttps() == false) goto L5;
        SSLSocketFactory r0 = sslSocketFactory();
        SSLSocketFactory r6 = r0;
        HostnameVerifier r7 = this.hostnameVerifier;
        CertificatePinner r8 = this.certificatePinner;
    L7:
        return new Address(r15.host(), r15.port(), this.dns, this.socketFactory, r6, r7, r8, this.proxyAuthenticator, this.proxy, this.protocols, this.connectionSpecs, this.proxySelector);
    L5:
        r6 = null;
        r7 = null;
        r8 = null;
        goto L7
    }

    @JvmName(name = "authenticator")
    public final Authenticator authenticator() {
        return this.authenticator;
    }

    @JvmName(name = "cache")
    public final Cache cache() {
        return this.cache;
    }

    @JvmName(name = "callTimeoutMillis")
    public final int callTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    @JvmName(name = "certificateChainCleaner")
    public final CertificateChainCleaner certificateChainCleaner() {
        return this.certificateChainCleaner;
    }

    @JvmName(name = "certificatePinner")
    public final CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    @JvmName(name = "connectTimeoutMillis")
    public final int connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @JvmName(name = "connectionPool")
    public final ConnectionPool connectionPool() {
        return this.connectionPool;
    }

    @JvmName(name = "connectionSpecs")
    public final List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    @JvmName(name = "cookieJar")
    public final CookieJar cookieJar() {
        return this.cookieJar;
    }

    @JvmName(name = "dispatcher")
    public final Dispatcher dispatcher() {
        return this.dispatcher;
    }

    @JvmName(name = "dns")
    public final Dns dns() {
        return this.dns;
    }

    @JvmName(name = "eventListenerFactory")
    public final EventListener.Factory eventListenerFactory() {
        return this.eventListenerFactory;
    }

    @JvmName(name = "fastFallback")
    public final boolean fastFallback() {
        return this.fastFallback;
    }

    @JvmName(name = "followRedirects")
    public final boolean followRedirects() {
        return this.followRedirects;
    }

    @JvmName(name = "followSslRedirects")
    public final boolean followSslRedirects() {
        return this.followSslRedirects;
    }

    public final RouteDatabase getRouteDatabase$okhttp() {
        return this.routeDatabase;
    }

    public final TaskRunner getTaskRunner$okhttp() {
        return this.taskRunner;
    }

    @JvmName(name = "hostnameVerifier")
    public final HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    @JvmName(name = "interceptors")
    public final List<Interceptor> interceptors() {
        return this.interceptors;
    }

    @JvmName(name = "minWebSocketMessageToCompress")
    public final long minWebSocketMessageToCompress() {
        return this.minWebSocketMessageToCompress;
    }

    @JvmName(name = "networkInterceptors")
    public final List<Interceptor> networkInterceptors() {
        return this.networkInterceptors;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    @Override // okhttp3.Call.Factory
    public Call newCall(Request r3) {
        return new RealCall(this, r3, false);
    }

    @Override // okhttp3.WebSocket.Factory
    public WebSocket newWebSocket(Request r13, WebSocketListener r14) {
        RealWebSocket r0 = new RealWebSocket(this.taskRunner, r13, r14, new Random(), this.pingIntervalMillis, null, this.minWebSocketMessageToCompress, this.webSocketCloseTimeout);
        r0.connect(this);
        return r0;
    }

    @JvmName(name = "pingIntervalMillis")
    public final int pingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    @JvmName(name = "protocols")
    public final List<Protocol> protocols() {
        return this.protocols;
    }

    @JvmName(name = "proxy")
    public final Proxy proxy() {
        return this.proxy;
    }

    @JvmName(name = "proxyAuthenticator")
    public final Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    @JvmName(name = "proxySelector")
    public final ProxySelector proxySelector() {
        return this.proxySelector;
    }

    @JvmName(name = "readTimeoutMillis")
    public final int readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    @JvmName(name = "retryOnConnectionFailure")
    public final boolean retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    @JvmName(name = "socketFactory")
    public final SocketFactory socketFactory() {
        return this.socketFactory;
    }

    @JvmName(name = "sslSocketFactory")
    public final SSLSocketFactory sslSocketFactory() {
        SSLSocketFactory r0 = this.sslSocketFactoryOrNull;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("CLEARTEXT-only client");
        return null;
    }

    @JvmName(name = "webSocketCloseTimeout")
    public final int webSocketCloseTimeout() {
        return this.webSocketCloseTimeout;
    }

    @JvmName(name = "writeTimeoutMillis")
    public final int writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    @JvmName(name = "x509TrustManager")
    public final X509TrustManager x509TrustManager() {
        return this.x509TrustManager;
    }
}
