package okhttp3.internal.platform;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.ExtendedSSLSession;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient$$ExternalSyntheticBUOutline0;
import okhttp3.Protocol;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 12\u00020\u0001:\u00011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J-\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0011\u0010\u0012\u001a\r\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u00150\u0013H\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0017J \u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J&\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u0012\u0010'\u001a\u0004\u0018\u00010\u00012\u0006\u0010(\u001a\u00020\u0005H\u0016J\u001a\u0010)\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010-\u001a\u00020.2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010/\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u00100\u001a\u00020\u0005H\u0016¨\u00062"}, d2 = {"Lokhttp3/internal/platform/Platform;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getPrefix", _UrlKt.FRAGMENT_ENCODE_SET, "newSSLContext", "Ljavax/net/ssl/SSLContext;", "platformTrustManager", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "configureTlsExtensions", _UrlKt.FRAGMENT_ENCODE_SET, "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "protocols", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "afterHandshake", "getSelectedProtocol", "getHandshakeServerNames", "connectSocket", "socket", "Ljava/net/Socket;", "address", "Ljava/net/InetSocketAddress;", "connectTimeout", _UrlKt.FRAGMENT_ENCODE_SET, "log", "message", "level", "t", _UrlKt.FRAGMENT_ENCODE_SET, "isCleartextTrafficPermitted", _UrlKt.FRAGMENT_ENCODE_SET, "getStackTraceForCloseable", "closer", "logCloseableLeak", "stackTrace", "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "buildTrustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "newSslSocketFactory", "toString", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPlatform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Platform.kt\nokhttp3/internal/platform/Platform\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,241:1\n1617#2,9:242\n1869#2:251\n1870#2:253\n1626#2:254\n1#3:252\n*S KotlinDebug\n*F\n+ 1 Platform.kt\nokhttp3/internal/platform/Platform\n*L\n134#1:242,9\n134#1:251\n134#1:253\n134#1:254\n134#1:252\n*E\n"})
/* loaded from: classes.dex */
public class Platform {
    public static final Companion Companion = null;
    public static final int INFO = 4;
    public static final int WARN = 5;
    private static final Logger logger = null;
    private static volatile Platform platform;

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\u0005H\u0007J\u0010\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u0005J\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010J\b\u0010\u0017\u001a\u00020\u0005H\u0002J\u0014\u0010\u0018\u001a\u00020\u00192\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016¨\u0006\u001a"}, d2 = {"Lokhttp3/internal/platform/Platform$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "platform", "Lokhttp3/internal/platform/Platform;", "INFO", _UrlKt.FRAGMENT_ENCODE_SET, "WARN", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "get", "resetForTests", _UrlKt.FRAGMENT_ENCODE_SET, "alpnProtocolNames", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "protocols", "Lokhttp3/Protocol;", "isAndroid", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "findPlatform", "concatLengthPrefixed", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPlatform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Platform.kt\nokhttp3/internal/platform/Platform$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,241:1\n774#2:242\n865#2,2:243\n1563#2:245\n1634#2,3:246\n*S KotlinDebug\n*F\n+ 1 Platform.kt\nokhttp3/internal/platform/Platform$Companion\n*L\n219#1:242\n219#1:243,2\n219#1:245\n219#1:246,3\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ Platform access$findPlatform(Companion r0) {
            return r0.findPlatform();
        }

        private final Platform findPlatform() {
            return PlatformRegistry.INSTANCE.findPlatform();
        }

        public static /* synthetic */ void resetForTests$default(Companion r0, Platform r1, int r2, Object r3) {
            if ((r2 & 1) == 0) goto L5;
            r1 = r0.findPlatform();
        L5:
            r0.resetForTests(r1);
        }

        public final List<String> alpnProtocolNames(List<? extends Protocol> r4) {
            ArrayList r3 = new ArrayList();
            Iterator<T> r42 = r4.iterator();
        L4:
            if (r42.hasNext() == false) goto L8;
            Object r0 = r42.next();
            if (((Protocol) r0) == Protocol.HTTP_1_0) goto L4;
            r3.add(r0);
            goto L4
        L8:
            ArrayList r43 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r3, 10));
            int r02 = r3.size();
            int r1 = 0;
        L9:
            if (r1 >= r02) goto L11;
            Object r2 = r3.get(r1);
            r1 = r1 + 1;
            r43.add(((Protocol) r2).toString());
            goto L9
        L11:
            return r43;
        }

        public final byte[] concatLengthPrefixed(List<? extends Protocol> r3) {
            Buffer r0 = new Buffer();
            Iterator<String> r2 = alpnProtocolNames(r3).iterator();
        L4:
            if (r2.hasNext() == false) goto L7;
            String r32 = r2.next();
            r0.writeByte(r32.length());
            r0.writeUtf8(r32);
            goto L4
        L7:
            return r0.readByteArray();
        }

        @JvmStatic
        public final Platform get() {
            return Platform.access$getPlatform$cp();
        }

        public final boolean isAndroid() {
            return PlatformRegistry.INSTANCE.isAndroid();
        }

        public final void resetForTests(Platform r1) {
            Platform.access$setPlatform$cp(r1);
            PublicSuffixDatabase.Companion.resetForTests$okhttp();
        }
    }

    static {
        Companion r0 = new Companion(null);
        Companion = r0;
        platform = Companion.access$findPlatform(r0);
        logger = Logger.getLogger(OkHttpClient.class.getName());
    }

    public Platform() {
    }

    public static final /* synthetic */ Platform access$getPlatform$cp() {
        return platform;
    }

    public static final /* synthetic */ void access$setPlatform$cp(Platform r0) {
        platform = r0;
    }

    @JvmStatic
    public static final Platform get() {
        return Companion.get();
    }

    public static /* synthetic */ void log$default(Platform r1, String r2, int r3, Throwable r4, int r5, Object r6) {
        if (r6 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: log");
        return;
    L4:
        if ((r5 & 2) == 0) goto L7;
        r3 = 4;
    L7:
        if ((r5 & 4) == 0) goto L9;
        r4 = null;
    L9:
        r1.log(r2, r3, r4);
    }

    public void afterHandshake(SSLSocket r1) {
    }

    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager r2) {
        return new BasicCertificateChainCleaner(buildTrustRootIndex(r2));
    }

    public TrustRootIndex buildTrustRootIndex(X509TrustManager r2) {
        X509Certificate[] r22 = r2.getAcceptedIssuers();
        return new BasicTrustRootIndex((X509Certificate[]) Arrays.copyOf(r22, r22.length));
    }

    public void configureTlsExtensions(SSLSocket r1, String r2, List<Protocol> r3) {
    }

    public void connectSocket(Socket r1, InetSocketAddress r2, int r3) {
        r1.connect(r2, r3);
    }

    @IgnoreJRERequirement
    public List<String> getHandshakeServerNames(SSLSocket r4) {
        SSLSession r3 = r4.getSession();
        if ((r3 instanceof ExtendedSSLSession) == false) goto L5;
        ExtendedSSLSession r32 = (ExtendedSSLSession) r3;
    L6:
        if (r32 == null) goto L8;
        List<SNIServerName> r33 = r32.getRequestedServerNames();     // Catch: UnsupportedOperationException -> L23
        ArrayList r42 = new ArrayList();     // Catch: UnsupportedOperationException -> L23
        Iterator<T> r34 = r33.iterator();     // Catch: UnsupportedOperationException -> L23
    L10:
        if (r34.hasNext() == false) goto L22;
        SNIServerName r1 = (SNIServerName) r34.next();     // Catch: UnsupportedOperationException -> L23
        if ((r1 instanceof SNIHostName) == false) goto L15;
        SNIHostName r12 = (SNIHostName) r1;     // Catch: UnsupportedOperationException -> L23
    L16:
        if (r12 == null) goto L18;
        String r13 = r12.getAsciiName();     // Catch: UnsupportedOperationException -> L23
    L19:
        if (r13 == null) goto L10;
        r42.add(r13);     // Catch: UnsupportedOperationException -> L23
        goto L10
    L18:
        r13 = null;
        goto L19
    L15:
        r12 = null;
        goto L16
    L22:
        return r42;
    L24:
        return CollectionsKt.emptyList();
    L8:
        return CollectionsKt.emptyList();
    L5:
        r32 = null;
        goto L6
    }

    public final String getPrefix() {
        return "OkHttp";
    }

    public String getSelectedProtocol(SSLSocket r1) {
        return null;
    }

    public Object getStackTraceForCloseable(String r2) {
        if (logger.isLoggable(Level.FINE) == true) goto L5;
        return null;
    L5:
        return new Throwable(r2);
    }

    public boolean isCleartextTrafficPermitted(String r1) {
        return true;
    }

    public void log(String r1, int r2, Throwable r3) {
        if (r2 != 5) goto L5;
        Level r0 = Level.WARNING;
    L6:
        logger.log(r0, r1, r3);
        return;
    L5:
        r0 = Level.INFO;
        goto L6
    }

    public void logCloseableLeak(String r2, Object r3) {
        if (r3 != null) goto L4;
        r2 = r2 + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
    L4:
        log(r2, 5, (Throwable) r3);
    }

    public SSLContext newSSLContext() {
        return SSLContext.getInstance("TLS");
    }

    public SSLSocketFactory newSslSocketFactory(X509TrustManager r3) {
        SSLContext r2 = newSSLContext();     // Catch: GeneralSecurityException -> L4
        r2.init(null, new TrustManager[]{r3}, null);     // Catch: GeneralSecurityException -> L4
        return r2.getSocketFactory();
    L4:
        e = move-exception;
        throw new AssertionError("No System TLS: " + e, e);
    }

    public X509TrustManager platformTrustManager() {
        TrustManagerFactory r3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        r3.init(null);
        TrustManager[] r32 = r3.getTrustManagers();
        if (r32.length != 1) goto L8;
        TrustManager r1 = r32[0];
        if ((r1 instanceof X509TrustManager) == false) goto L8;
        return (X509TrustManager) r1;
    L8:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("Unexpected default trust managers: ", Arrays.toString(r32));
        return null;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public X509TrustManager trustManager(SSLSocketFactory r3) {
        Object r32 = _UtilJvmKt.readFieldOrNull(r3, Class.forName("sun.security.ssl.SSLContextImpl"), "context");     // Catch: RuntimeException -> L8 ClassNotFoundException -> L14
        if (r32 != null) goto L6;
        return null;
    L6:
        return (X509TrustManager) _UtilJvmKt.readFieldOrNull(r32, X509TrustManager.class, "trustManager");
    L8:
        e = move-exception;
        if (Intrinsics.areEqual(e.getClass().getName(), "java.lang.reflect.InaccessibleObjectException") == true) goto L13;
        throw e;
    L13:
        return null;
    }
}
