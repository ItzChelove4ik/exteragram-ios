package okhttp3.internal.platform.android;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.Protocol;
import okhttp3.internal.platform.AndroidPlatform;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline2;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J(\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0010\u001a\u00020\u0004H\u0016R\u0016\u0010\u0002\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \n*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \n*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "<init>", "(Ljava/lang/Class;)V", "setUseSessionTickets", "Ljava/lang/reflect/Method;", "setHostname", "kotlin.jvm.PlatformType", "getAlpnSelectedProtocol", "setAlpnProtocols", "isSupported", _UrlKt.FRAGMENT_ENCODE_SET, "matchesSocket", "sslSocket", "configureTlsExtensions", _UrlKt.FRAGMENT_ENCODE_SET, "hostname", _UrlKt.FRAGMENT_ENCODE_SET, "protocols", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Protocol;", "getSelectedProtocol", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public class AndroidSocketAdapter implements SocketAdapter {
    public static final Companion Companion = null;
    private static final DeferredSocketAdapter.Factory playProviderFactory = null;
    private final Method getAlpnSelectedProtocol;
    private final Method setAlpnProtocols;
    private final Method setHostname;
    private final Method setUseSessionTickets;
    private final Class<? super SSLSocket> sslSocketClass;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\b\u0000\u0012\u00020\f0\u000bH\u0002J\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "playProviderFactory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getPlayProviderFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "build", "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "actualSSLSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", PluginsConstants.Settings.FACTORY, "packageName", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ AndroidSocketAdapter access$build(Companion r0, Class r1) {
            return r0.build(r1);
        }

        private final AndroidSocketAdapter build(Class<? super SSLSocket> r3) {
            Class<? super SSLSocket> r2 = r3;
        L3:
            if (r2 == null) goto L12;
            if (Intrinsics.areEqual(r2.getSimpleName(), "OpenSSLSocketImpl") == true) goto L12;
            r2 = r2.getSuperclass();
            if (r2 != null) goto L3;
            throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + r3);
        L12:
            return new AndroidSocketAdapter(r2);
        }

        public final DeferredSocketAdapter.Factory factory(String r1) {
            return new AndroidSocketAdapter$Companion$factory$1(r1);
        }

        public final DeferredSocketAdapter.Factory getPlayProviderFactory() {
            return AndroidSocketAdapter.access$getPlayProviderFactory$cp();
        }
    }

    static {
        Companion r0 = new Companion(null);
        Companion = r0;
        playProviderFactory = r0.factory("com.google.android.gms.org.conscrypt");
    }

    public AndroidSocketAdapter(Class<? super SSLSocket> r3) {
        this.sslSocketClass = r3;
        this.setUseSessionTickets = r3.getDeclaredMethod("setUseSessionTickets", new Class[]{Boolean.TYPE});
        this.setHostname = r3.getMethod("setHostname", new Class[]{String.class});
        this.getAlpnSelectedProtocol = r3.getMethod("getAlpnSelectedProtocol", null);
        this.setAlpnProtocols = r3.getMethod("setAlpnProtocols", new Class[]{byte[].class});
    }

    public static final /* synthetic */ DeferredSocketAdapter.Factory access$getPlayProviderFactory$cp() {
        return playProviderFactory;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(SSLSocket r2, String r3, List<? extends Protocol> r4) {
        if (matchesSocket(r2) == false) goto L14;
        this.setUseSessionTickets.invoke(r2, new Object[]{Boolean.TRUE});     // Catch: InvocationTargetException -> L6 IllegalAccessException -> L9
        this.setAlpnProtocols.invoke(r2, new Object[]{Platform.Companion.concatLengthPrefixed(r4)});     // Catch: InvocationTargetException -> L6 IllegalAccessException -> L9
        return;
    L9:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return;
    L6:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public String getSelectedProtocol(SSLSocket r3) {
        if (matchesSocket(r3) == true) goto L23;
        return null;
    L23:
        byte[] r2 = (byte[]) this.getAlpnSelectedProtocol.invoke(r3, null);     // Catch: InvocationTargetException -> L9 IllegalAccessException -> L11
        if (r2 == null) goto L13;
        return new String(r2, Charsets.UTF_8);
    L13:
        return null;
    L11:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    L9:
        e = move-exception;
        Throwable r32 = e.getCause();
        if ((r32 instanceof NullPointerException) == true) goto L17;
    L19:
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    L17:
        if (Intrinsics.areEqual(((NullPointerException) r32).getMessage(), "ssl == null") == false) goto L19;
        return null;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return AndroidPlatform.Companion.isSupported();
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(SSLSocket r1) {
        return this.sslSocketClass.isInstance(r1);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public /* bridge */ boolean matchesSocketFactory(SSLSocketFactory r1) {
        return super.matchesSocketFactory(r1);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public /* bridge */ X509TrustManager trustManager(SSLSocketFactory r1) {
        return super.trustManager(r1);
    }
}
