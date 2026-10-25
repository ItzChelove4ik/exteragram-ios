package okhttp3.internal.connection;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.IOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RoutePlanner;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.Hpack$Reader$$ExternalSyntheticBUOutline0;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Timeout;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 R2\u00020\u00012\u00020\u0002:\u0001RB\u0093\u0001\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0016\u0012\u0006\u0010\u0017\u001a\u00020\b\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\b\u0012\u0006\u0010\u001b\u001a\u00020\u000e¢\u0006\u0004\b\u001c\u0010\u001dJ2\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0017\u001a\u00020\b2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\u000eH\u0002J\b\u00108\u001a\u000209H\u0016J\b\u0010:\u001a\u000209H\u0016J\b\u0010;\u001a\u00020<H\u0002J\r\u0010=\u001a\u000209H\u0000¢\u0006\u0002\b>J\u0018\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0002J\n\u0010D\u001a\u0004\u0018\u00010\u0019H\u0002J#\u0010E\u001a\u00020\u00002\f\u0010F\u001a\b\u0012\u0004\u0012\u00020C0\u00162\u0006\u0010@\u001a\u00020AH\u0000¢\u0006\u0002\bGJ%\u0010H\u001a\u0004\u0018\u00010\u00002\f\u0010F\u001a\b\u0012\u0004\u0012\u00020C0\u00162\u0006\u0010@\u001a\u00020AH\u0000¢\u0006\u0002\bIJ\b\u0010J\u001a\u000205H\u0016J\u001a\u0010K\u001a\u00020<2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\b\u0010N\u001a\u00020<H\u0016J\b\u0010O\u001a\u00020<H\u0016J\b\u0010P\u001a\u00020\u0001H\u0016J\u0006\u0010Q\u001a\u00020<R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0016X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\u001b\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0004\u0018\u00010(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082.¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u0010%¨\u0006S"}, d2 = {"Lokhttp3/internal/connection/ConnectPlan;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "readTimeoutMillis", _UrlKt.FRAGMENT_ENCODE_SET, "writeTimeoutMillis", "socketConnectTimeoutMillis", "socketReadTimeoutMillis", "pingIntervalMillis", "retryOnConnectionFailure", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/internal/connection/RealCall;", "routePlanner", "Lokhttp3/internal/connection/RealRoutePlanner;", "route", "Lokhttp3/Route;", "routes", _UrlKt.FRAGMENT_ENCODE_SET, "attempt", "tunnelRequest", "Lokhttp3/Request;", "connectionSpecIndex", "isTlsFallback", "<init>", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/internal/connection/RealConnectionPool;IIIIIZLokhttp3/internal/connection/RealCall;Lokhttp3/internal/connection/RealRoutePlanner;Lokhttp3/Route;Ljava/util/List;ILokhttp3/Request;IZ)V", "getRoute", "()Lokhttp3/Route;", "getRoutes$okhttp", "()Ljava/util/List;", "getConnectionSpecIndex$okhttp", "()I", "isTlsFallback$okhttp", "()Z", "canceled", "rawSocket", "Ljava/net/Socket;", "javaNetSocket", "getJavaNetSocket$okhttp", "()Ljava/net/Socket;", "setJavaNetSocket$okhttp", "(Ljava/net/Socket;)V", "handshake", "Lokhttp3/Handshake;", "protocol", "Lokhttp3/Protocol;", "socket", "Lokhttp3/internal/connection/BufferedSocket;", "connection", "Lokhttp3/internal/connection/RealConnection;", "isReady", "copy", "connectTcp", "Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "connectTlsEtc", "connectSocket", _UrlKt.FRAGMENT_ENCODE_SET, "connectTunnel", "connectTunnel$okhttp", "connectTls", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "connectionSpec", "Lokhttp3/ConnectionSpec;", "createTunnel", "planWithCurrentOrInitialConnectionSpec", "connectionSpecs", "planWithCurrentOrInitialConnectionSpec$okhttp", "nextConnectionSpec", "nextConnectionSpec$okhttp", "handleSuccess", "trackFailure", "e", "Ljava/io/IOException;", "noNewExchanges", "cancel", "retry", "closeQuietly", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConnectPlan.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectPlan.kt\nokhttp3/internal/connection/ConnectPlan\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,564:1\n1#2:565\n63#3:566\n1563#4:567\n1634#4,3:568\n*S KotlinDebug\n*F\n+ 1 ConnectPlan.kt\nokhttp3/internal/connection/ConnectPlan\n*L\n508#1:566\n393#1:567\n393#1:568,3\n*E\n"})
/* loaded from: classes.dex */
public final class ConnectPlan implements RoutePlanner.Plan, ExchangeCodec.Carrier {
    public static final Companion Companion = null;
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private final int attempt;
    private final RealCall call;
    private volatile boolean canceled;
    private RealConnection connection;
    private final RealConnectionPool connectionPool;
    private final int connectionSpecIndex;
    private Handshake handshake;
    private final boolean isTlsFallback;
    private Socket javaNetSocket;
    private final int pingIntervalMillis;
    private Protocol protocol;
    private Socket rawSocket;
    private final int readTimeoutMillis;
    private final boolean retryOnConnectionFailure;
    private final Route route;
    private final RealRoutePlanner routePlanner;
    private final List<Route> routes;
    private BufferedSocket socket;
    private final int socketConnectTimeoutMillis;
    private final int socketReadTimeoutMillis;
    private final TaskRunner taskRunner;
    private final Request tunnelRequest;
    private final int writeTimeoutMillis;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lokhttp3/internal/connection/ConnectPlan$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NPE_THROW_WITH_NULL", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_TUNNEL_ATTEMPTS", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[Proxy.Type.values().length];
            r0[Proxy.Type.DIRECT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L9:
            r0[Proxy.Type.HTTP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L5:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static List $r8$lambda$806SqObLv8d6l4DhU5N2BQVd1eM(CertificatePinner r0, Handshake r1, Address r2) {
        return r0.getCertificateChainCleaner$okhttp().clean(r1.peerCertificates(), r2.url().host());
    }

    public static List $r8$lambda$ZCg05bGI6M6ecME2HgWklhiULrk(Handshake r2) {
        List<Certificate> r22 = r2.peerCertificates();
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r22, 10));
        Iterator<T> r23 = r22.iterator();
    L4:
        if (r23.hasNext() == false) goto L6;
        r0.add((X509Certificate) ((Certificate) r23.next()));
        goto L4
    L6:
        return r0;
    }

    static {
        Companion = new Companion(null);
    }

    public ConnectPlan(TaskRunner r1, RealConnectionPool r2, int r3, int r4, int r5, int r6, int r7, boolean r8, RealCall r9, RealRoutePlanner r10, Route r11, List<Route> r12, int r13, Request r14, int r15, boolean r16) {
        this.taskRunner = r1;
        this.connectionPool = r2;
        this.readTimeoutMillis = r3;
        this.writeTimeoutMillis = r4;
        this.socketConnectTimeoutMillis = r5;
        this.socketReadTimeoutMillis = r6;
        this.pingIntervalMillis = r7;
        this.retryOnConnectionFailure = r8;
        this.call = r9;
        this.routePlanner = r10;
        this.route = r11;
        this.routes = r12;
        this.attempt = r13;
        this.tunnelRequest = r14;
        this.connectionSpecIndex = r15;
        this.isTlsFallback = r16;
    }

    private final void connectSocket() {
        Proxy.Type r0 = getRoute().proxy().type();
        if (r0 != null) goto L5;
        int r02 = -1;
    L7:
        if (r02 != 1) goto L9;
    L11:
        Socket r03 = getRoute().address().socketFactory().createSocket();
    L12:
        this.rawSocket = r03;
        if (this.canceled == true) goto L27;
        r03.setSoTimeout(this.socketReadTimeoutMillis);
        Platform.Companion.get().connectSocket(r03, getRoute().socketAddress(), this.socketConnectTimeoutMillis);     // Catch: ConnectException -> L24
        this.socket = BufferedSocketKt.asBufferedSocket(r03);     // Catch: NullPointerException -> L18
        return;
    L18:
        e = move-exception;
        if (Intrinsics.areEqual(e.getMessage(), NPE_THROW_WITH_NULL) == true) goto L23;
        return;
    L23:
        throw new IOException(e);
    L24:
        e = move-exception;
        ConnectException r1 = new ConnectException("Failed to connect to " + getRoute().socketAddress());
        r1.initCause(e);
        throw r1;
    L27:
        Model$$ExternalSyntheticBUOutline0.m("canceled");
        return;
    L9:
        if (r02 == 2) goto L11;
        r03 = new Socket(getRoute().proxy());
        goto L12
    L5:
        r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
        goto L7
    }

    private final void connectTls(SSLSocket r10, ConnectionSpec r11) {
        Address r2 = getRoute().address();
    L6:
        th = move-exception;
        Platform.Companion.get().afterHandshake(r10);
        _UtilJvmKt.closeQuietly(r10);
        throw th;
    L4:
        if (r11.supportsTlsExtensions() == false) goto L8;
        Platform.Companion.get().configureTlsExtensions(r10, r2.url().host(), r2.protocols());     // Catch: Throwable -> L6
    L8:
        r10.startHandshake();     // Catch: Throwable -> L6
        SSLSession r3 = r10.getSession();     // Catch: Throwable -> L6
        Handshake r4 = Handshake.Companion.get(r3);     // Catch: Throwable -> L6
        String r5 = null;
        if (r2.hostnameVerifier().verify(r2.url().host(), r3) == true) goto L16;
        List<Certificate> r9 = r4.peerCertificates();     // Catch: Throwable -> L6
        if (r9.isEmpty() == true) goto L15;
        X509Certificate r92 = (X509Certificate) r9.get(0);     // Catch: Throwable -> L6
        throw new SSLPeerUnverifiedException(StringsKt.trimMargin$default("\n            |Hostname " + r2.url().host() + " not verified:\n            |    certificate: " + CertificatePinner.Companion.pin(r92) + "\n            |    DN: " + r92.getSubjectDN().getName() + "\n            |    subjectAltNames: " + OkHostnameVerifier.INSTANCE.allSubjectAltNames(r92) + "\n            ", null, 1, null));     // Catch: Throwable -> L6
    L15:
        throw new SSLPeerUnverifiedException("Hostname " + r2.url().host() + " not verified (no certificates)");     // Catch: Throwable -> L6
    L16:
        CertificatePinner r0 = r2.certificatePinner();     // Catch: Throwable -> L6
        Handshake r1 = new Handshake(r4.tlsVersion(), r4.cipherSuite(), r4.localCertificates(), new ConnectPlan$$ExternalSyntheticLambda0(r0, r4, r2));     // Catch: Throwable -> L6
        this.handshake = r1;     // Catch: Throwable -> L6
        r0.check$okhttp(r2.url().host(), new ConnectPlan$$ExternalSyntheticLambda1(r1));     // Catch: Throwable -> L6
        if (r11.supportsTlsExtensions() == false) goto L19;
        r5 = Platform.Companion.get().getSelectedProtocol(r10);     // Catch: Throwable -> L6
    L19:
        this.javaNetSocket = r10;     // Catch: Throwable -> L6
        this.socket = BufferedSocketKt.asBufferedSocket(r10);     // Catch: Throwable -> L6
        if (r5 == null) goto L22;
        Protocol r112 = Protocol.Companion.get(r5);     // Catch: Throwable -> L6
    L23:
        this.protocol = r112;     // Catch: Throwable -> L6
        Platform.Companion.get().afterHandshake(r10);
        return;
    L22:
        r112 = Protocol.HTTP_1_1;     // Catch: Throwable -> L6
        goto L23
    }

    private final ConnectPlan copy(int r18, Request r19, int r20, boolean r21) {
        return new ConnectPlan(this.taskRunner, this.connectionPool, this.readTimeoutMillis, this.writeTimeoutMillis, this.socketConnectTimeoutMillis, this.socketReadTimeoutMillis, this.pingIntervalMillis, this.retryOnConnectionFailure, this.call, this.routePlanner, getRoute(), this.routes, r18, r19, r20, r21);
    }

    public static /* synthetic */ ConnectPlan copy$default(ConnectPlan r0, int r1, Request r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 1) == 0) goto L6;
        r1 = r0.attempt;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r2 = r0.tunnelRequest;
    L9:
        if ((r5 & 4) == 0) goto L12;
        r3 = r0.connectionSpecIndex;
    L12:
        if ((r5 & 8) == 0) goto L15;
        r4 = r0.isTlsFallback;
    L15:
        return r0.copy(r1, r2, r3, r4);
    }

    private final Request createTunnel() {
        Request r0 = this.tunnelRequest;
        String r1 = "CONNECT " + _UtilJvmKt.toHostHeader(getRoute().address().url(), true) + " HTTP/1.1";
    L3:
        BufferedSocket r4 = this.socket;
        if (r4 != null) goto L6;
        r4 = null;
    L6:
        Http1ExchangeCodec r2 = new Http1ExchangeCodec(null, this, r4);
        BufferedSocket r42 = this.socket;
        if (r42 != null) goto L9;
        r42 = null;
    L9:
        Timeout r43 = r42.getSource().timeout();
        long r6 = this.readTimeoutMillis;
        TimeUnit r8 = TimeUnit.MILLISECONDS;
        r43.timeout(r6, r8);
        BufferedSocket r44 = this.socket;
        if (r44 != null) goto L12;
        r44 = null;
    L12:
        r44.getSink().timeout().timeout(this.writeTimeoutMillis, r8);
        r2.writeRequest(r0.headers(), r1);
        r2.finishRequest();
        Response r02 = r2.readResponseHeaders(false).request(r0).build();
        r2.skipConnectBody(r02);
        int r22 = r02.code();
        if (r22 == 200) goto L25;
        if (r22 != 407) goto L24;
        Request r23 = getRoute().address().proxyAuthenticator().authenticate(getRoute(), r02);
        if (r23 == null) goto L22;
        if (StringsKt.equals("close", Response.header$default(r02, "Connection", null, 2, null), true) == true) goto L20;
        r0 = r23;
        goto L3
    L20:
        return r23;
    L22:
        Model$$ExternalSyntheticBUOutline0.m("Failed to authenticate with proxy");
        return null;
    L24:
        Hpack$Reader$$ExternalSyntheticBUOutline0.m("Unexpected response code for CONNECT: ", r02.code());
    L25:
        return null;
    }

    @Override // okhttp3.internal.connection.RoutePlanner.Plan, okhttp3.internal.http.ExchangeCodec.Carrier
    /* renamed from: cancel */
    public void mo3746cancel() {
        this.canceled = true;
        Socket r1 = this.rawSocket;
        if (r1 == null) goto L6;
        _UtilJvmKt.closeQuietly(r1);
        return;
    }

    public final void closeQuietly() {
        Socket r0 = this.javaNetSocket;
        if (r0 == null) goto L6;
        _UtilJvmKt.closeQuietly(r0);
        return;
    }

    @Override // okhttp3.internal.connection.RoutePlanner.Plan
    /* renamed from: connectTcp */
    public RoutePlanner.ConnectResult mo3750connectTcp() {
        if (this.rawSocket != null) goto L40;
        this.call.getPlansToCancel$okhttp().add(this);
        boolean r1 = false;
        this.call.getEventListener$okhttp().connectStart(this.call, getRoute().socketAddress(), getRoute().proxy());     // Catch: Throwable -> L14 IOException -> L16
        this.connectionPool.getConnectionListener$okhttp().connectStart(getRoute(), this.call);     // Catch: Throwable -> L14 IOException -> L16
        connectSocket();     // Catch: Throwable -> L14 IOException -> L16
        r1 = true;
        ConnectPlan r3 = this;
        RoutePlanner.ConnectResult r2 = new RoutePlanner.ConnectResult(r3, null, null, 6, null);     // Catch: Throwable -> L10 IOException -> L12
        r3.call.getPlansToCancel$okhttp().remove(r3);
        return r2;
    L12:
        e = e;
    L13:
        IOException r14 = e;
        if (r3.getRoute().address().proxy() == null) goto L21;
    L23:
        r3.call.getEventListener$okhttp().connectFailed(r3.call, r3.getRoute().socketAddress(), r3.getRoute().proxy(), null, r14);     // Catch: Throwable -> L10
        r3.connectionPool.getConnectionListener$okhttp().connectFailed(r3.getRoute(), r3.call, r14);     // Catch: Throwable -> L10
        ConnectPlan r9 = r3;
        RoutePlanner.ConnectResult r8 = new RoutePlanner.ConnectResult(r9, null, r14, 2, null);     // Catch: Throwable -> L32
        r3.call.getPlansToCancel$okhttp().remove(r3);
        if (r1 == true) goto L31;
        Socket r142 = r3.rawSocket;
        if (r142 == null) goto L31;
        _UtilJvmKt.closeQuietly(r142);
    L31:
        return r8;
    L32:
        th = th;
        ConnectPlan r32 = r9;
    L11:
        Throwable r143 = th;
        r32.call.getPlansToCancel$okhttp().remove(r32);
        if (r1 == true) goto L48;
        Socket r0 = r32.rawSocket;
        if (r0 == null) goto L49;
        _UtilJvmKt.closeQuietly(r0);
        throw r143;
    L49:
        throw r143;
    L48:
        throw r143;
    L21:
        if (r3.getRoute().proxy().type() == Proxy.Type.DIRECT) goto L23;
        r3.getRoute().address().proxySelector().connectFailed(r3.getRoute().address().url().uri(), r3.getRoute().proxy().address(), r14);     // Catch: Throwable -> L10
        goto L23
    L10:
        th = th;
    L16:
        e = e;
        r3 = this;
    L14:
        th = th;
        r32 = this;
        goto L11
    L40:
        Segment$$ExternalSyntheticBUOutline1.m("TCP already connected");
        return null;
    }

    @Override // okhttp3.internal.connection.RoutePlanner.Plan
    /* renamed from: connectTlsEtc */
    public RoutePlanner.ConnectResult mo3751connectTlsEtc() {
        Socket r6 = this.rawSocket;
        ConnectPlan r13 = null;
        if (r6 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("TCP not connected");
        return null;
    L5:
        if (isReady() == true) goto L84;
        List<ConnectionSpec> r0 = getRoute().address().connectionSpecs();
        this.call.getPlansToCancel$okhttp().add(this);
        boolean r14 = false;
    L14:
        th = th;
    L77:
        this.call.getPlansToCancel$okhttp().remove(this);
        if (r14 == true) goto L83;
        Socket r1 = this.javaNetSocket;
        if (r1 == null) goto L82;
        _UtilJvmKt.closeQuietly(r1);
    L82:
        _UtilJvmKt.closeQuietly(r6);
    L83:
        throw th;
    L16:
        e = move-exception;
        IOException r12 = e;
        ConnectPlan r5 = null;
    L63:
        this.call.getEventListener$okhttp().connectFailed(this.call, getRoute().socketAddress(), getRoute().proxy(), null, r12);     // Catch: Throwable -> L14
        this.connectionPool.getConnectionListener$okhttp().connectFailed(getRoute(), this.call, r12);     // Catch: Throwable -> L14
        if (this.retryOnConnectionFailure == true) goto L66;
    L69:
        RoutePlanner.ConnectResult r02 = new RoutePlanner.ConnectResult(this, r13, r12);     // Catch: Throwable -> L14
        this.call.getPlansToCancel$okhttp().remove(this);
        if (r14 == true) goto L76;
        Socket r15 = this.javaNetSocket;
        if (r15 == null) goto L75;
        _UtilJvmKt.closeQuietly(r15);
    L75:
        _UtilJvmKt.closeQuietly(r6);
    L76:
        return r02;
    L66:
        if (RetryTlsHandshakeKt.retryTlsHandshake(r12) == false) goto L69;
        r13 = r5;
        goto L69
    L8:
        if (this.tunnelRequest == null) goto L24;
        RoutePlanner.ConnectResult r2 = connectTunnel$okhttp();     // Catch: Throwable -> L14 IOException -> L16
        if (r2.getNextPlan() == null) goto L12;
    L18:
        this.call.getPlansToCancel$okhttp().remove(this);
        Socket r03 = this.javaNetSocket;
        if (r03 == null) goto L21;
        _UtilJvmKt.closeQuietly(r03);
    L21:
        _UtilJvmKt.closeQuietly(r6);
        return r2;
    L12:
        if (r2.getThrowable() != null) goto L18;
    L24:
        if (getRoute().address().sslSocketFactory() == null) goto L42;
        BufferedSocket r22 = this.socket;     // Catch: Throwable -> L14 IOException -> L16
        if (r22 != null) goto L29;
        r22 = null;
    L29:
        if (r22.getSource().getBuffer().exhausted() == false) goto L41;
        BufferedSocket r23 = this.socket;     // Catch: Throwable -> L14 IOException -> L16
        if (r23 != null) goto L34;
        r23 = null;
    L34:
        if (r23.getSink().getBuffer().exhausted() == false) goto L41;
        this.call.getEventListener$okhttp().secureConnectStart(this.call);     // Catch: Throwable -> L14 IOException -> L16
        SSLSocket r24 = (SSLSocket) getRoute().address().sslSocketFactory().createSocket(r6, getRoute().address().url().host(), getRoute().address().url().port(), true);     // Catch: Throwable -> L14 IOException -> L16
        ConnectPlan r3 = planWithCurrentOrInitialConnectionSpec$okhttp(r0, r24);     // Catch: Throwable -> L14 IOException -> L16
        ConnectionSpec r4 = r0.get(r3.connectionSpecIndex);     // Catch: Throwable -> L14 IOException -> L16
        r5 = r3.nextConnectionSpec$okhttp(r0, r24);     // Catch: Throwable -> L14 IOException -> L16
        r4.apply$okhttp(r24, r3.isTlsFallback);     // Catch: Throwable -> L14 IOException -> L38
        connectTls(r24, r4);     // Catch: Throwable -> L14 IOException -> L38
        this.call.getEventListener$okhttp().secureConnectEnd(this.call, this.handshake);     // Catch: Throwable -> L14 IOException -> L38
        ConnectPlan r16 = r5;
    L88:
        TaskRunner r32 = this.taskRunner;     // Catch: Throwable -> L14 IOException -> L61
        RealConnectionPool r42 = this.connectionPool;     // Catch: Throwable -> L14 IOException -> L61
        Route r52 = getRoute();     // Catch: Throwable -> L14 IOException -> L61
        Socket r7 = this.javaNetSocket;     // Catch: Throwable -> L14 IOException -> L61
        Handshake r8 = this.handshake;     // Catch: Throwable -> L14 IOException -> L61
        Protocol r9 = this.protocol;     // Catch: Throwable -> L14 IOException -> L61
        BufferedSocket r04 = this.socket;     // Catch: Throwable -> L14 IOException -> L61
        if (r04 != null) goto L51;
        BufferedSocket r10 = null;
    L52:
        RealConnection r25 = new RealConnection(r32, r42, r52, r6, r7, r8, r9, r10, this.pingIntervalMillis, this.connectionPool.getConnectionListener$okhttp());     // Catch: Throwable -> L14 IOException -> L61
        this.connection = r25;     // Catch: Throwable -> L14 IOException -> L61
        r25.start();     // Catch: Throwable -> L14 IOException -> L61
        this.call.getEventListener$okhttp().connectEnd(this.call, getRoute().socketAddress(), getRoute().proxy(), this.protocol);     // Catch: Throwable -> L14 IOException -> L61
        RoutePlanner.ConnectResult r05 = new RoutePlanner.ConnectResult(this, null, null, 6, null);     // Catch: Throwable -> L56 IOException -> L58
        this.call.getPlansToCancel$okhttp().remove(this);
        return r05;
    L58:
        e = move-exception;
        r12 = e;
        r14 = true;
    L60:
        r5 = r16;
    L56:
        th = th;
        r14 = true;
        goto L77
    L51:
        r10 = r04;
    L61:
        e = move-exception;
        r12 = e;
    L38:
        e = move-exception;
        r12 = e;
    L41:
        throw new IOException("TLS tunnel buffered too many bytes!");     // Catch: Throwable -> L14 IOException -> L16
    L42:
        this.javaNetSocket = r6;     // Catch: Throwable -> L14 IOException -> L16
        List<Protocol> r06 = getRoute().address().protocols();     // Catch: Throwable -> L14 IOException -> L16
        Protocol r26 = Protocol.H2_PRIOR_KNOWLEDGE;     // Catch: Throwable -> L14 IOException -> L16
        if (r06.contains(r26) == true) goto L46;
        r26 = Protocol.HTTP_1_1;     // Catch: Throwable -> L14 IOException -> L16
    L46:
        this.protocol = r26;     // Catch: Throwable -> L14 IOException -> L16
        r16 = null;
        goto L88
    L84:
        Segment$$ExternalSyntheticBUOutline1.m("already connected");
        return null;
    }

    public final RoutePlanner.ConnectResult connectTunnel$okhttp() {
        Request r2 = createTunnel();
        if (r2 == null) goto L5;
        Socket r12 = this.rawSocket;
        if (r12 == null) goto L9;
        _UtilJvmKt.closeQuietly(r12);
    L9:
        int r1 = this.attempt + 1;
        if (r1 >= 21) goto L13;
        this.call.getEventListener$okhttp().connectEnd(this.call, getRoute().socketAddress(), getRoute().proxy(), null);
        return new RoutePlanner.ConnectResult(this, copy$default(this, r1, r2, 0, false, 12, null), null, 4, null);
    L13:
        ProtocolException r7 = new ProtocolException("Too many tunnel connections attempted: 21");
        this.call.getEventListener$okhttp().connectFailed(this.call, getRoute().socketAddress(), getRoute().proxy(), null, r7);
        this.connectionPool.getConnectionListener$okhttp().connectFailed(getRoute(), this.call, r7);
        return new RoutePlanner.ConnectResult(this, null, r7, 2, null);
    L5:
        return new RoutePlanner.ConnectResult(this, null, null, 6, null);
    }

    public final int getConnectionSpecIndex$okhttp() {
        return this.connectionSpecIndex;
    }

    public final Socket getJavaNetSocket$okhttp() {
        return this.javaNetSocket;
    }

    @Override // okhttp3.internal.http.ExchangeCodec.Carrier
    public Route getRoute() {
        return this.route;
    }

    public final List<Route> getRoutes$okhttp() {
        return this.routes;
    }

    @Override // okhttp3.internal.connection.RoutePlanner.Plan
    /* renamed from: handleSuccess */
    public RealConnection mo3747handleSuccess() {
        this.call.getClient().getRouteDatabase$okhttp().connected(getRoute());
        RealConnection r0 = this.connection;
        r0.getConnectionListener$okhttp().connectEnd(r0, getRoute(), this.call);
        ReusePlan r1 = this.routePlanner.planReusePooledConnection$okhttp(this, this.routes);
        if (r1 != null) goto L5;
        monitor-enter(r0);
        this.connectionPool.put(r0);     // Catch: Throwable -> L11
        this.call.acquireConnectionNoEvents(r0);     // Catch: Throwable -> L11
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r0);
        this.call.getEventListener$okhttp().connectionAcquired(this.call, r0);
        r0.getConnectionListener$okhttp().connectionAcquired(r0, this.call);
        return r0;
    L11:
        th = move-exception;
        throw th;
    L5:
        return r1.getConnection();
    }

    @Override // okhttp3.internal.connection.RoutePlanner.Plan
    public boolean isReady() {
        if (this.protocol == null) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean isTlsFallback$okhttp() {
        return this.isTlsFallback;
    }

    public final ConnectPlan nextConnectionSpec$okhttp(List<ConnectionSpec> r11, SSLSocket r12) {
        boolean r1 = true;
        int r0 = this.connectionSpecIndex + 1;
        int r2 = r11.size();
        int r6 = r0;
    L3:
        if (r6 >= r2) goto L13;
        if (r11.get(r6).isCompatible(r12) == true) goto L7;
        r6 = r6 + 1;
        goto L3
    L7:
        if (this.connectionSpecIndex != (-1)) goto L11;
        r1 = false;
    L11:
        return copy$default(this, 0, null, r6, r1, 3, null);
    L13:
        return null;
    }

    @Override // okhttp3.internal.http.ExchangeCodec.Carrier
    public void noNewExchanges() {
    }

    public final ConnectPlan planWithCurrentOrInitialConnectionSpec$okhttp(List<ConnectionSpec> r4, SSLSocket r5) {
        if (this.connectionSpecIndex == (-1)) goto L5;
        return this;
    L5:
        ConnectPlan r0 = nextConnectionSpec$okhttp(r4, r5);
        if (r0 == null) goto L8;
        return r0;
    L8:
        StringBuilder r1 = new StringBuilder("Unable to find acceptable protocols. isFallback=");
        r1.append(this.isTlsFallback);
        r1.append(", modes=");
        r1.append(r4);
        String r3 = Arrays.toString(r5.getEnabledProtocols());
        r1.append(", supported protocols=");
        r1.append(r3);
        throw new UnknownServiceException(r1.toString());
    }

    @Override // okhttp3.internal.connection.RoutePlanner.Plan
    /* renamed from: retry */
    public RoutePlanner.Plan mo3748retry() {
        return new ConnectPlan(this.taskRunner, this.connectionPool, this.readTimeoutMillis, this.writeTimeoutMillis, this.socketConnectTimeoutMillis, this.socketReadTimeoutMillis, this.pingIntervalMillis, this.retryOnConnectionFailure, this.call, this.routePlanner, getRoute(), this.routes, this.attempt, this.tunnelRequest, this.connectionSpecIndex, this.isTlsFallback);
    }

    public final void setJavaNetSocket$okhttp(Socket r1) {
        this.javaNetSocket = r1;
    }

    @Override // okhttp3.internal.http.ExchangeCodec.Carrier
    public void trackFailure(RealCall r1, IOException r2) {
    }
}
