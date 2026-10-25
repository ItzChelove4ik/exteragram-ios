package okhttp3.internal.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.UnreadableResponseBodyKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001dH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "client", "Lokhttp3/OkHttpClient;", "<init>", "(Lokhttp3/OkHttpClient;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "recover", _UrlKt.FRAGMENT_ENCODE_SET, "e", "Ljava/io/IOException;", "call", "Lokhttp3/internal/connection/RealCall;", "userRequest", "Lokhttp3/Request;", "requestIsOneShot", "isRecoverable", "requestSendStarted", "followUpRequest", "userResponse", "exchange", "Lokhttp3/internal/connection/Exchange;", "buildRedirectRequest", "method", _UrlKt.FRAGMENT_ENCODE_SET, "retryAfter", _UrlKt.FRAGMENT_ENCODE_SET, "defaultDelay", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    public static final Companion Companion = null;
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MAX_FOLLOW_UPS", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public RetryAndFollowUpInterceptor(OkHttpClient r1) {
        this.client = r1;
    }

    private final Request buildRedirectRequest(Response r8, String r9) {
        RequestBody r1 = null;
        if (this.client.followRedirects() == true) goto L5;
        return null;
    L5:
        String r0 = Response.header$default(r8, "Location", null, 2, null);
        if (r0 != null) goto L8;
        return null;
    L8:
        HttpUrl r02 = r8.request().url().resolve(r0);
        if (r02 != null) goto L12;
        return null;
    L12:
        if (Intrinsics.areEqual(r02.scheme(), r8.request().url().scheme()) == false) goto L14;
    L16:
        Request.Builder r7 = r8.request().newBuilder();
        if (HttpMethod.permitsRequestBody(r9) == false) goto L36;
        int r2 = r8.code();
        HttpMethod r3 = HttpMethod.INSTANCE;
        if (r3.redirectsWithBody(r9) == true) goto L24;
        if (r2 == 308) goto L24;
        if (r2 == 307) goto L24;
        boolean r4 = false;
    L26:
        if (r3.redirectsToGet(r9) == false) goto L30;
        if (r2 == 308) goto L30;
        if (r2 == 307) goto L30;
        r7.method("GET", null);
    L33:
        if (r4 == true) goto L36;
        r7.removeHeader("Transfer-Encoding");
        r7.removeHeader("Content-Length");
        r7.removeHeader("Content-Type");
    L30:
        if (r4 == false) goto L32;
        r1 = r8.request().body();
    L32:
        r7.method(r9, r1);
    L24:
        r4 = true;
    L36:
        if (_UtilJvmKt.canReuseConnectionFor(r8.request().url(), r02) == true) goto L39;
        r7.removeHeader("Authorization");
    L39:
        return r7.url(r02).build();
    L14:
        if (this.client.followSslRedirects() == true) goto L16;
        return null;
    }

    private final Request followUpRequest(Response r6, Exchange r7) {
        if (r7 == null) goto L7;
        RealConnection r1 = r7.getConnection$okhttp();
        if (r1 == null) goto L7;
        Route r12 = r1.route();
    L8:
        int r2 = r6.code();
        String r3 = r6.request().method();
        if (r2 == 307) goto L73;
        if (r2 == 308) goto L73;
        if (r2 == 401) goto L71;
        if (r2 != 421) goto L17;
        RequestBody r5 = r6.request().body();
        if (r5 != null) goto L61;
    L63:
        if (r7 != null) goto L65;
    L69:
        return null;
    L65:
        if (r7.isCoalescedConnection$okhttp() == false) goto L69;
        r7.getConnection$okhttp().noCoalescedConnections$okhttp();
        return r6.request();
    L61:
        if (r5.isOneShot() == false) goto L63;
        return null;
    L17:
        if (r2 != 503) goto L19;
        Response r13 = r6.priorResponse();
        if (r13 == null) goto L54;
        if (r13.code() != 503) goto L54;
        return null;
    L54:
        if (retryAfter(r6, Integer.MAX_VALUE) == 0) goto L56;
        return null;
    L56:
        return r6.request();
    L19:
        if (r2 == 407) goto L43;
        if (r2 == 408) goto L25;
        switch(r2) {
            case 300: goto L73;
            case 301: goto L73;
            case 302: goto L73;
            case 303: goto L73;
            default: goto L23;
        };
    L23:
        return null;
    L25:
        if (this.client.retryOnConnectionFailure() == true) goto L27;
        return null;
    L27:
        RequestBody r14 = r6.request().body();
        if (r14 != null) goto L30;
    L32:
        Response r15 = r6.priorResponse();
        if (r15 == null) goto L38;
        if (r15.code() != 408) goto L38;
        return null;
    L38:
        if (retryAfter(r6, 0) <= 0) goto L41;
        return null;
    L41:
        return r6.request();
    L30:
        if (r14.isOneShot() == false) goto L32;
        return null;
    L43:
        if (r12.proxy().type() != Proxy.Type.HTTP) goto L47;
        return this.client.proxyAuthenticator().authenticate(r12, r6);
    L47:
        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
    L71:
        return this.client.authenticator().authenticate(r12, r6);
    L73:
        return buildRedirectRequest(r6, r3);
    L7:
        r12 = null;
        goto L8
    }

    private final boolean isRecoverable(IOException r3, boolean r4) {
        if ((r3 instanceof ProtocolException) == false) goto L6;
        return false;
    L6:
        if ((r3 instanceof InterruptedIOException) == false) goto L13;
        if ((r3 instanceof SocketTimeoutException) == false) goto L11;
        if (r4 == true) goto L11;
        return true;
    L11:
        return false;
    L13:
        if ((r3 instanceof SSLHandshakeException) == false) goto L18;
        if ((r3.getCause() instanceof CertificateException) == false) goto L18;
        return false;
    L18:
        if ((r3 instanceof SSLPeerUnverifiedException) == false) goto L20;
        return false;
    L20:
        return true;
    }

    private final boolean recover(IOException r5, RealCall r6, Request r7) {
        boolean r0 = r5 instanceof ConnectionShutdownException;
        boolean r1 = !r0;
        if (this.client.retryOnConnectionFailure() == true) goto L5;
        return false;
    L5:
        if (r0 == true) goto L10;
        if (requestIsOneShot(r5, r7) == false) goto L10;
        return false;
    L10:
        if (isRecoverable(r5, r1) == true) goto L13;
        return false;
    L13:
        if (r6.retryAfterFailure() == true) goto L15;
        return false;
    L15:
        return true;
    }

    private final boolean requestIsOneShot(IOException r1, Request r2) {
        RequestBody r0 = r2.body();
        if (r0 == null) goto L7;
        if (r0.isOneShot() == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof FileNotFoundException) == true) goto L12;
        return false;
    L12:
        return true;
    }

    private final int retryAfter(Response r3, int r4) {
        String r2 = Response.header$default(r3, "Retry-After", null, 2, null);
        if (r2 != null) goto L6;
        return r4;
    L6:
        if (new Regex("\\d+").matches(r2) == true) goto L8;
        return Integer.MAX_VALUE;
    L8:
        return Integer.valueOf(r2).intValue();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain r12) {
        RealInterceptorChain r122 = (RealInterceptorChain) r12;
        Request r0 = r122.getRequest$okhttp();
        RealCall r1 = r122.getCall$okhttp();
        List r2 = CollectionsKt.emptyList();
        boolean r3 = false;
        int r8 = 0;
        Response r7 = null;
    L3:
        boolean r6 = true;
    L4:
        r1.enterNetworkInterceptorExchange(r0, r6, r122);
    L11:
        th = th;
        r3 = true;
    L47:
        r1.exitNetworkInterceptorExchange$okhttp(r3);
        throw th;
    L6:
        if (r1.isCanceled() == true) goto L46;
    L8:
        Response.Builder r02 = r122.proceed(r0).newBuilder().request(r0);     // Catch: Throwable -> L11
        if (r7 == null) goto L13;
        Response r62 = UnreadableResponseBodyKt.stripBody(r7);     // Catch: Throwable -> L11
    L14:
        r7 = r02.priorResponse(r62).build();     // Catch: Throwable -> L11
        Exchange r03 = r1.getInterceptorScopedExchange$okhttp();     // Catch: Throwable -> L11
        Request r63 = followUpRequest(r7, r03);     // Catch: Throwable -> L11
        if (r63 == null) goto L16;
        RequestBody r04 = r63.body();     // Catch: Throwable -> L11
        if (r04 != null) goto L28;
    L32:
        _UtilCommonKt.closeQuietly(r7.body());     // Catch: Throwable -> L11
        r8 = r8 + 1;     // Catch: Throwable -> L11
        if (r8 > 20) goto L36;
        r1.getEventListener$okhttp().followUpDecision(r1, r7, r63);     // Catch: Throwable -> L11
        r1.exitNetworkInterceptorExchange$okhttp(true);
        r0 = r63;
        goto L3
    L36:
        r1.getEventListener$okhttp().followUpDecision(r1, r7, null);     // Catch: Throwable -> L11
        throw new ProtocolException("Too many follow-up requests: " + r8);     // Catch: Throwable -> L11
    L28:
        if (r04.isOneShot() == false) goto L32;
        r1.getEventListener$okhttp().followUpDecision(r1, r7, null);     // Catch: Throwable -> L23
        r1.exitNetworkInterceptorExchange$okhttp(false);
        return r7;
    L16:
        if (r03 != null) goto L18;
    L20:
        r1.getEventListener$okhttp().followUpDecision(r1, r7, null);     // Catch: Throwable -> L23
        r1.exitNetworkInterceptorExchange$okhttp(false);
        return r7;
    L18:
        if (r03.isDuplex$okhttp() == false) goto L20;
        r1.timeoutEarlyExit();     // Catch: Throwable -> L11
    L23:
        th = th;
        goto L47
    L13:
        r62 = null;
    L38:
        e = move-exception;
        boolean r9 = recover(e, r1, r0);     // Catch: Throwable -> L11
        r1.getEventListener$okhttp().retryDecision(r1, e, r9);     // Catch: Throwable -> L11
        if (r9 == false) goto L44;
        r2 = CollectionsKt.plus(r2, e);     // Catch: Throwable -> L11
        r1.exitNetworkInterceptorExchange$okhttp(true);
        r6 = false;
        goto L4
    L44:
        throw _UtilCommonKt.withSuppressed(e, r2);     // Catch: Throwable -> L11
    L46:
        throw new IOException("Canceled");     // Catch: Throwable -> L11
    }
}
