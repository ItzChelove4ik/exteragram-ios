package okhttp3.internal.platform;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Protocol;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
import okhttp3.internal.platform.android.AndroidSocketAdapter;
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
import okhttp3.internal.platform.android.ConscryptSocketAdapter;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import okhttp3.internal.platform.android.StandardAndroidSocketAdapter;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okhttp3.internal.url._UrlKt;

@SuppressSignatureCheck
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0007\u0018\u0000 22\u00020\u00012\u00020\u0002:\u000212B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J-\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0011\u0010!\u001a\r\u0012\t\u0012\u00070\"¢\u0006\u0002\b#0\fH\u0016J\u0012\u0010$\u001a\u0004\u0018\u00010 2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020 0\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\"\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020\u00152\b\u0010/\u001a\u0004\u0018\u000100H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform;", "Lokhttp3/internal/platform/Platform;", "Lokhttp3/internal/platform/ContextAwarePlatform;", "<init>", "()V", "applicationContext", "Landroid/content/Context;", "getApplicationContext", "()Landroid/content/Context;", "setApplicationContext", "(Landroid/content/Context;)V", "socketAdapters", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/platform/android/SocketAdapter;", "connectSocket", _UrlKt.FRAGMENT_ENCODE_SET, "socket", "Ljava/net/Socket;", "address", "Ljava/net/InetSocketAddress;", "connectTimeout", _UrlKt.FRAGMENT_ENCODE_SET, "newSSLContext", "Ljavax/net/ssl/SSLContext;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "configureTlsExtensions", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", _UrlKt.FRAGMENT_ENCODE_SET, "protocols", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "isCleartextTrafficPermitted", _UrlKt.FRAGMENT_ENCODE_SET, "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "buildTrustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "getHandshakeServerNames", "log", "message", "level", "t", _UrlKt.FRAGMENT_ENCODE_SET, "CustomTrustRootIndex", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAndroidPlatform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidPlatform.kt\nokhttp3/internal/platform/AndroidPlatform\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,186:1\n774#2:187\n865#2,2:188\n1#3:190\n*S KotlinDebug\n*F\n+ 1 AndroidPlatform.kt\nokhttp3/internal/platform/AndroidPlatform\n*L\n60#1:187\n60#1:188,2\n*E\n"})
/* loaded from: classes5.dex */
public final class AndroidPlatform extends Platform implements ContextAwarePlatform {
    public static final Companion Companion = null;
    private static final String Tag = null;
    private static final boolean isSupported = false;
    private Context applicationContext;
    private final List<SocketAdapter> socketAdapters;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\n¨\u0006\r"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Tag", _UrlKt.FRAGMENT_ENCODE_SET, "getTag", "()Ljava/lang/String;", "isSupported", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Platform buildIfSupported() {
            if (isSupported() == true) goto L5;
            return null;
        L5:
            return new AndroidPlatform();
        }

        public final String getTag() {
            return AndroidPlatform.access$getTag$cp();
        }

        public final boolean isSupported() {
            return AndroidPlatform.access$isSupported$cp();
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\t\u0010\u000b\u001a\u00020\u0003HÂ\u0003J\t\u0010\f\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform$CustomTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "findByIssuerAndSignatureMethod", "Ljava/lang/reflect/Method;", "<init>", "(Ljavax/net/ssl/X509TrustManager;Ljava/lang/reflect/Method;)V", "findByIssuerAndSignature", "Ljava/security/cert/X509Certificate;", "cert", "component1", "component2", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CustomTrustRootIndex implements TrustRootIndex {
        private final Method findByIssuerAndSignatureMethod;
        private final X509TrustManager trustManager;

        public CustomTrustRootIndex(X509TrustManager r1, Method r2) {
            this.trustManager = r1;
            this.findByIssuerAndSignatureMethod = r2;
        }

        private final X509TrustManager component1() {
            return this.trustManager;
        }

        private final Method component2() {
            return this.findByIssuerAndSignatureMethod;
        }

        public static /* synthetic */ CustomTrustRootIndex copy$default(CustomTrustRootIndex r0, X509TrustManager r1, Method r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r1 = r0.trustManager;
        L6:
            if ((r3 & 2) == 0) goto L9;
            r2 = r0.findByIssuerAndSignatureMethod;
        L9:
            return r0.copy(r1, r2);
        }

        public final CustomTrustRootIndex copy(X509TrustManager r1, Method r2) {
            return new CustomTrustRootIndex(r1, r2);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof CustomTrustRootIndex) == true) goto L8;
            return false;
        L8:
            CustomTrustRootIndex r52 = (CustomTrustRootIndex) r5;
            if (Intrinsics.areEqual(this.trustManager, r52.trustManager) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.findByIssuerAndSignatureMethod, r52.findByIssuerAndSignatureMethod) == true) goto L14;
            return false;
        L14:
            return true;
        }

        @Override // okhttp3.internal.tls.TrustRootIndex
        public X509Certificate findByIssuerAndSignature(X509Certificate r2) {
            return ((TrustAnchor) this.findByIssuerAndSignatureMethod.invoke(this.trustManager, new Object[]{r2})).getTrustedCert();
        L6:
            e = move-exception;
            throw new AssertionError("unable to get issues and signature", e);
        L4:
            return null;
        }

        public int hashCode() {
            return (this.trustManager.hashCode() * 31) + this.findByIssuerAndSignatureMethod.hashCode();
        }

        public String toString() {
            return "CustomTrustRootIndex(trustManager=" + this.trustManager + ", findByIssuerAndSignatureMethod=" + this.findByIssuerAndSignatureMethod + ')';
        }
    }

    static {
        Companion = new Companion(null);
        Tag = "OkHttp";
        if (Platform.Companion.isAndroid() == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        isSupported = r0;
        return;
    L5:
        if (Build.VERSION.SDK_INT >= 29) goto L7;
        r0 = true;
        goto L8
    }

    public AndroidPlatform() {
        List r0 = CollectionsKt.listOfNotNull(new SocketAdapter[]{StandardAndroidSocketAdapter.Companion.buildIfSupported$default(StandardAndroidSocketAdapter.Companion, null, 1, null), new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory()), new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory()), new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory())});
        ArrayList r1 = new ArrayList();
        Iterator r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L8;
        Object r2 = r02.next();
        if (((SocketAdapter) r2).isSupported() == false) goto L4;
        r1.add(r2);
        goto L4
    L8:
        this.socketAdapters = r1;
    }

    public static final /* synthetic */ String access$getTag$cp() {
        return Tag;
    }

    public static final /* synthetic */ boolean access$isSupported$cp() {
        return isSupported;
    }

    @Override // okhttp3.internal.platform.Platform
    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager r2) {
        AndroidCertificateChainCleaner r0 = AndroidCertificateChainCleaner.Companion.buildIfSupported(r2);
        if (r0 == null) goto L6;
        return r0;
    L6:
        return super.buildCertificateChainCleaner(r2);
    }

    @Override // okhttp3.internal.platform.Platform
    public TrustRootIndex buildTrustRootIndex(X509TrustManager r4) {
        StrictMode.noteSlowCall("buildTrustRootIndex");     // Catch: NoSuchMethodException -> L4
        Method r0 = r4.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[]{X509Certificate.class});     // Catch: NoSuchMethodException -> L4
        r0.setAccessible(true);     // Catch: NoSuchMethodException -> L4
        return new CustomTrustRootIndex(r4, r0);
    L5:
        return super.buildTrustRootIndex(r4);
    }

    @Override // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket r3, String r4, List<Protocol> r5) {
        Iterator<T> r2 = this.socketAdapters.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (((SocketAdapter) r0).matchesSocket(r3) == false) goto L4;
    L9:
        SocketAdapter r02 = (SocketAdapter) r0;
        if (r02 == null) goto L16;
        r02.configureTlsExtensions(r3, r4, r5);
        return;
    L16:
        return;
    L8:
        r0 = null;
        goto L9
    }

    @Override // okhttp3.internal.platform.Platform
    public void connectSocket(Socket r1, InetSocketAddress r2, int r3) {
        r1.connect(r2, r3);     // Catch: ClassCastException -> L4
        return;
    L4:
        e = move-exception;
        if (Build.VERSION.SDK_INT == 26) goto L8;
        throw e;
    L8:
        throw new IOException("Exception in connect", e);
    }

    @Override // okhttp3.internal.platform.ContextAwarePlatform
    public Context getApplicationContext() {
        return this.applicationContext;
    }

    @Override // okhttp3.internal.platform.Platform
    public List<String> getHandshakeServerNames(SSLSocket r3) {
        if (Build.VERSION.SDK_INT > 24) goto L7;
        return CollectionsKt.emptyList();
    L7:
        return super.getHandshakeServerNames(r3);
    }

    @Override // okhttp3.internal.platform.Platform
    public String getSelectedProtocol(SSLSocket r4) {
        Iterator<T> r3 = this.socketAdapters.iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        Object r0 = r3.next();
        if (((SocketAdapter) r0).matchesSocket(r4) == false) goto L4;
    L9:
        SocketAdapter r02 = (SocketAdapter) r0;
        if (r02 != null) goto L12;
        return null;
    L12:
        return r02.getSelectedProtocol(r4);
    L8:
        r0 = null;
        goto L9
    }

    @Override // okhttp3.internal.platform.Platform
    public boolean isCleartextTrafficPermitted(String r1) {
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(r1);
    }

    @Override // okhttp3.internal.platform.Platform
    public void log(String r1, int r2, Throwable r3) {
        if (r2 != 5) goto L6;
        Log.w(Tag, r1, r3);
        return;
    L6:
        Log.i(Tag, r1, r3);
    }

    @Override // okhttp3.internal.platform.Platform
    public SSLContext newSSLContext() {
        StrictMode.noteSlowCall("newSSLContext");
        return super.newSSLContext();
    }

    @Override // okhttp3.internal.platform.ContextAwarePlatform
    public void setApplicationContext(Context r1) {
        this.applicationContext = r1;
    }

    @Override // okhttp3.internal.platform.Platform
    public X509TrustManager trustManager(SSLSocketFactory r4) {
        Iterator<T> r3 = this.socketAdapters.iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        Object r0 = r3.next();
        if (((SocketAdapter) r0).matchesSocketFactory(r4) == false) goto L4;
    L9:
        SocketAdapter r02 = (SocketAdapter) r0;
        if (r02 != null) goto L12;
        return null;
    L12:
        return r02.trustManager(r4);
    L8:
        r0 = null;
        goto L9
    }
}
