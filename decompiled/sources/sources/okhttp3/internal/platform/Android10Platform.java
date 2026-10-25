package okhttp3.internal.platform;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.security.NetworkSecurityPolicy;
import android.util.CloseGuard;
import android.util.Log;
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
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Protocol;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.platform.android.Android10SocketAdapter;
import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
import okhttp3.internal.platform.android.AndroidSocketAdapter;
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
import okhttp3.internal.platform.android.ConscryptSocketAdapter;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okhttp3.internal.url._UrlKt;

@SuppressSignatureCheck
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u0000 .2\u00020\u00012\u00020\u0002:\u0001.B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\fH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u001bH\u0016J\u001a\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u001b2\b\u0010$\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020\u001bH\u0017J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\"\u0010)\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lokhttp3/internal/platform/Android10Platform;", "Lokhttp3/internal/platform/Platform;", "Lokhttp3/internal/platform/ContextAwarePlatform;", "<init>", "()V", "applicationContext", "Landroid/content/Context;", "getApplicationContext", "()Landroid/content/Context;", "setApplicationContext", "(Landroid/content/Context;)V", "socketAdapters", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/platform/android/SocketAdapter;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "buildTrustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "configureTlsExtensions", _UrlKt.FRAGMENT_ENCODE_SET, "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", _UrlKt.FRAGMENT_ENCODE_SET, "protocols", "Lokhttp3/Protocol;", "getSelectedProtocol", "getStackTraceForCloseable", _UrlKt.FRAGMENT_ENCODE_SET, "closer", "logCloseableLeak", "message", "stackTrace", "isCleartextTrafficPermitted", _UrlKt.FRAGMENT_ENCODE_SET, "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "log", "level", _UrlKt.FRAGMENT_ENCODE_SET, "t", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAndroid10Platform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Android10Platform.kt\nokhttp3/internal/platform/Android10Platform\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,133:1\n774#2:134\n865#2,2:135\n1#3:137\n*S KotlinDebug\n*F\n+ 1 Android10Platform.kt\nokhttp3/internal/platform/Android10Platform\n*L\n55#1:134\n55#1:135,2\n*E\n"})
/* loaded from: classes.dex */
public final class Android10Platform extends Platform implements ContextAwarePlatform {
    public static final Companion Companion = null;
    private static final boolean isSupported = false;
    private Context applicationContext;
    private final List<SocketAdapter> socketAdapters;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006¨\u0006\t"}, d2 = {"Lokhttp3/internal/platform/Android10Platform$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isSupported", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
            return new Android10Platform();
        }

        public final boolean isSupported() {
            return Android10Platform.access$isSupported$cp();
        }
    }

    static {
        Companion = new Companion(null);
        if (Platform.Companion.isAndroid() == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        isSupported = r0;
        return;
    L5:
        if (Build.VERSION.SDK_INT < 29) goto L7;
        r0 = true;
        goto L8
    }

    public Android10Platform() {
        List r0 = CollectionsKt.listOfNotNull(new SocketAdapter[]{Android10SocketAdapter.Companion.buildIfSupported(), new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory()), new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory()), new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory())});
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
    public TrustRootIndex buildTrustRootIndex(X509TrustManager r2) {
        StrictMode.noteSlowCall("buildTrustRootIndex");
        return super.buildTrustRootIndex(r2);
    }

    @Override // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket r3, String r4, List<? extends Protocol> r5) {
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

    @Override // okhttp3.internal.platform.ContextAwarePlatform
    public Context getApplicationContext() {
        return this.applicationContext;
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
    public Object getStackTraceForCloseable(String r3) {
        if (Build.VERSION.SDK_INT < 30) goto L7;
        CloseGuard r2 = Android10Platform$$ExternalSyntheticApiModelOutline0.m();
        r2.open(r3);
        return r2;
    L7:
        return super.getStackTraceForCloseable(r3);
    }

    @Override // okhttp3.internal.platform.Platform
    @SuppressLint({"NewApi"})
    public boolean isCleartextTrafficPermitted(String r1) {
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(r1);
    }

    @Override // okhttp3.internal.platform.Platform
    public void log(String r1, int r2, Throwable r3) {
        if (r2 != 5) goto L6;
        Log.w(AndroidPlatform.Companion.getTag(), r1, r3);
        return;
    L6:
        Log.i(AndroidPlatform.Companion.getTag(), r1, r3);
    }

    @Override // okhttp3.internal.platform.Platform
    public void logCloseableLeak(String r3, Object r4) {
        if (Build.VERSION.SDK_INT < 30) goto L6;
        Android10Platform$$ExternalSyntheticApiModelOutline1.m(r4).warnIfOpen();
        return;
    L6:
        super.logCloseableLeak(r3, r4);
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
