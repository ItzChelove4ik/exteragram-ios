package okhttp3.internal.platform;

import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0011\u0010\n\u001a\r\u0012\t\u0012\u00070\f¢\u0006\u0002\b\r0\u000bH\u0017J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016¨\u0006\u0016"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform;", "Lokhttp3/internal/platform/Platform;", "<init>", "()V", "configureTlsExtensions", _UrlKt.FRAGMENT_ENCODE_SET, "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", _UrlKt.FRAGMENT_ENCODE_SET, "protocols", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJdk9Platform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Jdk9Platform.kt\nokhttp3/internal/platform/Jdk9Platform\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,109:1\n37#2,2:110\n*S KotlinDebug\n*F\n+ 1 Jdk9Platform.kt\nokhttp3/internal/platform/Jdk9Platform\n*L\n42#1:110,2\n*E\n"})
/* loaded from: classes5.dex */
public class Jdk9Platform extends Platform {
    public static final Companion Companion = null;
    private static final boolean isAvailable = false;
    private static final Integer majorVersion = null;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u0004\u0018\u00010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isAvailable", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "majorVersion", _UrlKt.FRAGMENT_ENCODE_SET, "getMajorVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "buildIfSupported", "Lokhttp3/internal/platform/Jdk9Platform;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Jdk9Platform buildIfSupported() {
            if (isAvailable() == true) goto L5;
            return null;
        L5:
            return new Jdk9Platform();
        }

        public final Integer getMajorVersion() {
            return Jdk9Platform.access$getMajorVersion$cp();
        }

        public final boolean isAvailable() {
            return Jdk9Platform.access$isAvailable$cp();
        }
    }

    static {
        Companion = new Companion(null);
        String r0 = System.getProperty("java.specification.version");
        if (r0 == null) goto L5;
        Integer r02 = StringsKt.toIntOrNull(r0);
    L6:
        majorVersion = r02;
        boolean r2 = false;
        if (r02 != null) goto L9;
        SSLSocket.class.getMethod("getApplicationProtocol", null);     // Catch: NoSuchMethodException -> L15
    L10:
        r2 = true;
    L13:
        isAvailable = r2;
        return;
    L9:
        if (r02.intValue() < 9) goto L13;
    L5:
        r02 = null;
        goto L6
    }

    public Jdk9Platform() {
    }

    public static final /* synthetic */ Integer access$getMajorVersion$cp() {
        return majorVersion;
    }

    public static final /* synthetic */ boolean access$isAvailable$cp() {
        return isAvailable;
    }

    @Override // okhttp3.internal.platform.Platform
    @SuppressSignatureCheck
    public void configureTlsExtensions(SSLSocket r1, String r2, List<Protocol> r3) {
        SSLParameters r0 = r1.getSSLParameters();
        r0.setApplicationProtocols((String[]) Platform.Companion.alpnProtocolNames(r3).toArray(new String[0]));
        r1.setSSLParameters(r0);
    }

    @Override // okhttp3.internal.platform.Platform
    @SuppressSignatureCheck
    public String getSelectedProtocol(SSLSocket r2) {
        String r22 = r2.getApplicationProtocol();     // Catch: UnsupportedOperationException -> L10
        if (r22 == null) goto L9;
        if (Intrinsics.areEqual(r22, _UrlKt.FRAGMENT_ENCODE_SET) == true) goto L9;
        return r22;
    L9:
        return null;
    }

    @Override // okhttp3.internal.platform.Platform
    public SSLContext newSSLContext() {
        Integer r2 = majorVersion;
        if (r2 != null) goto L5;
    L12:
        return SSLContext.getInstance("TLSv1.3");
    L11:
        return SSLContext.getInstance("TLS");
    L5:
        if (r2.intValue() < 9) goto L12;
        return SSLContext.getInstance("TLS");
    }

    @Override // okhttp3.internal.platform.Platform
    public X509TrustManager trustManager(SSLSocketFactory r1) {
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 8 (>= 252) or JDK 9+");
    }
}
