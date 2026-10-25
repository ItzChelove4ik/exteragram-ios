package okhttp3.internal.platform.android;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.url._UrlKt;
import org.conscrypt.Conscrypt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J(\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016¨\u0006\u0012"}, d2 = {"Lokhttp3/internal/platform/android/ConscryptSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "<init>", "()V", "matchesSocket", _UrlKt.FRAGMENT_ENCODE_SET, "sslSocket", "Ljavax/net/ssl/SSLSocket;", "isSupported", "getSelectedProtocol", _UrlKt.FRAGMENT_ENCODE_SET, "configureTlsExtensions", _UrlKt.FRAGMENT_ENCODE_SET, "hostname", "protocols", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Protocol;", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConscryptSocketAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConscryptSocketAdapter.kt\nokhttp3/internal/platform/android/ConscryptSocketAdapter\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,97:1\n37#2,2:98\n*S KotlinDebug\n*F\n+ 1 ConscryptSocketAdapter.kt\nokhttp3/internal/platform/android/ConscryptSocketAdapter\n*L\n50#1:98,2\n*E\n"})
/* loaded from: classes.dex */
public final class ConscryptSocketAdapter implements SocketAdapter {
    public static final Companion Companion = null;
    private static final DeferredSocketAdapter.Factory factory = null;
    private static final boolean isSupported = false;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\n¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/platform/android/ConscryptSocketAdapter$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", PluginsConstants.Settings.FACTORY, "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "isSupported", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "atLeastVersion", "major", _UrlKt.FRAGMENT_ENCODE_SET, "minor", "patch", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ boolean atLeastVersion$default(Companion r1, int r2, int r3, int r4, int r5, Object r6) {
            if ((r5 & 2) == 0) goto L6;
            r3 = 0;
        L6:
            if ((r5 & 4) == 0) goto L9;
            r4 = 0;
        L9:
            return r1.atLeastVersion(r2, r3, r4);
        }

        public final boolean atLeastVersion(int r4, int r5, int r6) {
            Conscrypt.Version r3 = Conscrypt.version();
            if (r3 != null) goto L6;
            return false;
        L6:
            if (r3.major() == r4) goto L12;
            if (r3.major() <= r4) goto L10;
            return true;
        L10:
            return false;
        L12:
            if (r3.minor() == r5) goto L18;
            if (r3.minor() <= r5) goto L16;
            return true;
        L16:
            return false;
        L18:
            if (r3.patch() < r6) goto L20;
            return true;
        L20:
            return false;
        }

        public final DeferredSocketAdapter.Factory getFactory() {
            return ConscryptSocketAdapter.access$getFactory$cp();
        }

        public final boolean isSupported() {
            return ConscryptSocketAdapter.access$isSupported$cp();
        }
    }

    static {
        Companion r0 = new Companion(null);
        Companion = r0;
        factory = new ConscryptSocketAdapter$Companion$factory$1();
        boolean r1 = false;
        Class.forName("org.conscrypt.Conscrypt$Version", false, r0.getClass().getClassLoader());     // Catch: Throwable -> L10
        if (Conscrypt.isAvailable() == false) goto L8;
        if (r0.atLeastVersion(2, 1, 0) == false) goto L8;
        r1 = true;
    L8:
        isSupported = r1;
    }

    public ConscryptSocketAdapter() {
    }

    public static final /* synthetic */ DeferredSocketAdapter.Factory access$getFactory$cp() {
        return factory;
    }

    public static final /* synthetic */ boolean access$isSupported$cp() {
        return isSupported;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(SSLSocket r1, String r2, List<? extends Protocol> r3) {
        if (matchesSocket(r1) == false) goto L6;
        Conscrypt.setUseSessionTickets(r1, true);
        Conscrypt.setApplicationProtocols(r1, (String[]) Platform.Companion.alpnProtocolNames(r3).toArray(new String[0]));
        return;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public String getSelectedProtocol(SSLSocket r1) {
        if (matchesSocket(r1) == true) goto L5;
        return null;
    L5:
        return Conscrypt.getApplicationProtocol(r1);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return isSupported;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(SSLSocket r1) {
        return Conscrypt.isConscrypt(r1);
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
