package okhttp3.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.nio.charset.Charset;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.JvmName;
import kotlin.text.Charsets;
import okhttp3.Cache;
import okhttp3.CipherSuite;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.ConnectionListener;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0007H\u0000\u001a \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0000\u001a\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000\u001a \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000bH\u0000\u001a%\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070 *\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070 H\u0000¢\u0006\u0002\u0010\"\u001a\u001c\u0010#\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0006\u0012\u0004\u0018\u00010&0$*\u0004\u0018\u00010&H\u0000\u001a\u000e\u0010'\u001a\u00020%*\u0004\u0018\u00010&H\u0000\u001a\u0014\u0010,\u001a\u00020-*\u00020-2\u0006\u0010.\u001a\u00020/H\u0000\u001a\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u0010.\u001a\u00020/H\u0000\"\u0018\u0010(\u001a\u00020)*\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u00064"}, d2 = {"parseCookie", "Lokhttp3/Cookie;", "currentTimeMillis", _UrlKt.FRAGMENT_ENCODE_SET, "url", "Lokhttp3/HttpUrl;", "setCookie", _UrlKt.FRAGMENT_ENCODE_SET, "cookieToString", "cookie", "forObsoleteRfc2965", _UrlKt.FRAGMENT_ENCODE_SET, "addHeaderLenient", "Lokhttp3/Headers$Builder;", "builder", "line", "name", "value", "cacheGet", "Lokhttp3/Response;", "cache", "Lokhttp3/Cache;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "applyConnectionSpec", _UrlKt.FRAGMENT_ENCODE_SET, "connectionSpec", "Lokhttp3/ConnectionSpec;", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "isFallback", "effectiveCipherSuites", _UrlKt.FRAGMENT_ENCODE_SET, "socketEnabledCipherSuites", "(Lokhttp3/ConnectionSpec;[Ljava/lang/String;)[Ljava/lang/String;", "chooseCharset", "Lkotlin/Pair;", "Ljava/nio/charset/Charset;", "Lokhttp3/MediaType;", "charsetOrUtf8", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "(Lokhttp3/Response;)Lokhttp3/internal/connection/RealConnection;", "taskRunnerInternal", "Lokhttp3/OkHttpClient$Builder;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "buildConnectionPool", "Lokhttp3/ConnectionPool;", "connectionListener", "Lokhttp3/internal/connection/ConnectionListener;", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "Internal")
/* loaded from: classes.dex */
public final class Internal {
    public static final Headers.Builder addHeaderLenient(Headers.Builder r0, String r1) {
        return r0.addLenient$okhttp(r1);
    }

    public static final Headers.Builder addHeaderLenient(Headers.Builder r0, String r1, String r2) {
        return r0.addLenient$okhttp(r1, r2);
    }

    public static final void applyConnectionSpec(ConnectionSpec r0, SSLSocket r1, boolean r2) {
        r0.apply$okhttp(r1, r2);
    }

    public static final ConnectionPool buildConnectionPool(ConnectionListener r9, TaskRunner r10) {
        return new ConnectionPool(0, 0, null, r10, r9, 7, null);
    }

    public static final Response cacheGet(Cache r0, Request r1) {
        return r0.get$okhttp(r1);
    }

    public static final Charset charsetOrUtf8(MediaType r2) {
        if (r2 == null) goto L8;
        Charset r22 = MediaType.charset$default(r2, null, 1, null);
        if (r22 == null) goto L8;
        return r22;
    L8:
        return Charsets.UTF_8;
    }

    public static final Pair<Charset, MediaType> chooseCharset(MediaType r3) {
        Charset r0 = Charsets.UTF_8;
        if (r3 == null) goto L9;
        Charset r1 = MediaType.charset$default(r3, null, 1, null);
        if (r1 != null) goto L7;
        r3 = MediaType.Companion.parse(r3 + "; charset=utf-8");
        goto L9
    L7:
        r0 = r1;
    L9:
        return TuplesKt.to(r0, r3);
    }

    public static final String cookieToString(Cookie r0, boolean r1) {
        return r0.toString$okhttp(r1);
    }

    public static final String[] effectiveCipherSuites(ConnectionSpec r1, String[] r2) {
        if (r1.getCipherSuitesAsString$okhttp() != null) goto L5;
        return r2;
    L5:
        return _UtilCommonKt.intersect(r1.getCipherSuitesAsString$okhttp(), r2, CipherSuite.Companion.getORDER_BY_NAME$okhttp());
    }

    public static final RealConnection getConnection(Response r0) {
        return r0.exchange().getConnection$okhttp();
    }

    public static final Cookie parseCookie(long r1, HttpUrl r3, String r4) {
        return Cookie.Companion.parse$okhttp(r1, r3, r4);
    }

    public static final OkHttpClient.Builder taskRunnerInternal(OkHttpClient.Builder r0, TaskRunner r1) {
        return r0.taskRunner$okhttp(r1);
    }
}
