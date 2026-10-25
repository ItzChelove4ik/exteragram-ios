package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TrailersSource;
import okhttp3.internal.UnreadableResponseBody;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "<init>", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "shouldIgnoreAndWaitForRealResponse", _UrlKt.FRAGMENT_ENCODE_SET, "code", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CallServerInterceptor implements Interceptor {
    public static final CallServerInterceptor INSTANCE = null;

    @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"okhttp3/internal/http/CallServerInterceptor$intercept$1", "Lokhttp3/TrailersSource;", "peek", "Lokhttp3/Headers;", "get", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okhttp3.internal.http.CallServerInterceptor$intercept$1, reason: invalid class name */
    public static final class AnonymousClass1 implements TrailersSource {
        final /* synthetic */ Exchange $exchange;
        final /* synthetic */ ResponseBody $responseBody;

        public AnonymousClass1(Exchange r1, ResponseBody r2) {
            this.$exchange = r1;
            this.$responseBody = r2;
        }

        @Override // okhttp3.TrailersSource
        public Headers get() {
            BufferedSource r0 = this.$responseBody.source();
            if (r0.isOpen() == false) goto L5;
            _UtilJvmKt.skipAll(r0);
        L5:
            Headers r2 = peek();
            if (r2 == null) goto L8;
            return r2;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("null trailers after exhausting response body?!");
            return null;
        }

        @Override // okhttp3.TrailersSource
        public Headers peek() {
            return this.$exchange.peekTrailers();
        }
    }

    static {
        INSTANCE = new CallServerInterceptor();
    }

    private CallServerInterceptor() {
    }

    private final boolean shouldIgnoreAndWaitForRealResponse(int r2) {
        if (r2 != 100) goto L6;
        return true;
    L6:
        if (102 <= r2) goto L8;
        return false;
    L8:
        if (r2 >= 200) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain r19) {
        RealInterceptorChain r0 = (RealInterceptorChain) r19;
        Exchange r2 = r0.getExchange$okhttp();
        Request r3 = r0.getRequest$okhttp();
        RequestBody r02 = r3.body();
        long r4 = System.currentTimeMillis();
        boolean r7 = false;
        if (HttpMethod.permitsRequestBody(r3.method()) == false) goto L6;
        if (r02 == null) goto L6;
        Response.Builder r6 = 1;
    L7:
        boolean r10 = StringsKt.equals("upgrade", r3.header("Connection"), true);
        r2.writeRequestHeaders(r3);     // Catch: IOException -> L17
        if (r6 != null) goto L11;
        r2.noRequestBody();     // Catch: IOException -> L17
        boolean r13 = true;
        r6 = null;
    L33:
        if (r02 != null) goto L35;
    L36:
        r2.finishRequest();     // Catch: IOException -> L24
    L37:
        Response.Builder r03 = r6;
        IOException r62 = null;
    L43:
        if (r03 == null) goto L97;
    L49:
        Response r04 = r03.request(r3).handshake(r2.getConnection$okhttp().handshake()).sentRequestAtMillis(r4).receivedResponseAtMillis(System.currentTimeMillis()).build();     // Catch: IOException -> L47
        int r14 = r04.code();     // Catch: IOException -> L47
    L51:
        if (shouldIgnoreAndWaitForRealResponse(r14) == false) goto L56;
        Response.Builder r05 = r2.readResponseHeaders(r7);     // Catch: IOException -> L47
        if (r13 == false) goto L55;
        r2.responseHeadersStart();     // Catch: IOException -> L47
    L55:
        r04 = r05.request(r3).handshake(r2.getConnection$okhttp().handshake()).sentRequestAtMillis(r4).receivedResponseAtMillis(System.currentTimeMillis()).build();     // Catch: IOException -> L47
        r14 = r04.code();     // Catch: IOException -> L47
        r7 = false;
        goto L51
    L56:
        r2.responseHeadersEnd(r04);     // Catch: IOException -> L47
        if (r14 != 101) goto L59;
        boolean r32 = true;
    L60:
        if (r32 == false) goto L67;
        if (r2.getConnection$okhttp().isMultiplexed$okhttp() == false) goto L67;
        throw new ProtocolException("Unexpected 101 code on HTTP/2 connection");     // Catch: IOException -> L47
    L67:
        if (r32 == true) goto L69;
    L71:
        boolean r72 = false;
    L72:
        if (r10 == false) goto L75;
        if (r72 == false) goto L75;
        Response r06 = r04.newBuilder().body(new UnreadableResponseBody(r04.body().contentType(), r04.body().contentLength())).socket(r2.upgradeToSocket()).build();     // Catch: IOException -> L47
    L77:
        if (StringsKt.equals("close", r06.request().header("Connection"), true) == false) goto L79;
    L80:
        r2.noNewExchangesOnConnection();     // Catch: IOException -> L47
    L82:
        if (r14 == 204) goto L86;
        if (r14 == 205) goto L86;
    L87:
        return r06;
    L86:
        if (r06.body().contentLength() <= 0) goto L87;
        throw new ProtocolException("HTTP " + r14 + " had non-zero Content-Length: " + r06.body().contentLength());     // Catch: IOException -> L47
    L79:
        if (StringsKt.equals("close", Response.header$default(r06, "Connection", null, 2, null), true) == false) goto L82;
    L75:
        ResponseBody r33 = r2.openResponseBody(r04);     // Catch: IOException -> L47
        r06 = r04.newBuilder().body(r33).trailers(new AnonymousClass1(r2, r33)).build();     // Catch: IOException -> L47
        goto L77
    L69:
        if (StringsKt.equals("upgrade", Response.header$default(r04, "Connection", null, 2, null), true) == false) goto L71;
        r72 = true;
        goto L72
    L59:
        r32 = false;
        goto L60
    L97:
        r03 = r2.readResponseHeaders(false);     // Catch: IOException -> L47
        if (r13 == false) goto L49;
        r2.responseHeadersStart();     // Catch: IOException -> L47
        r13 = false;
    L47:
        e = move-exception;
        if (r62 == null) goto L93;
        ExceptionsKt.addSuppressed(r62, e);
        throw r62;
    L93:
        throw e;
    L35:
        if (r02.isDuplex() == true) goto L37;
    L11:
        if (StringsKt.equals("100-continue", r3.header("Expect"), true) == false) goto L19;
        r2.flushRequest();     // Catch: IOException -> L17
        r6 = r2.readResponseHeaders(true);     // Catch: IOException -> L17
        r2.responseHeadersStart();     // Catch: IOException -> L15
        r13 = false;
    L20:
        if (r6 == null) goto L22;
        r2.noRequestBody();     // Catch: IOException -> L24
        if (r2.getConnection$okhttp().isMultiplexed$okhttp() == true) goto L33;
        r2.noNewExchangesOnConnection();     // Catch: IOException -> L24
        goto L33
    L22:
        if (r02.isDuplex() == false) goto L26;
        r2.flushRequest();     // Catch: IOException -> L24
        r02.writeTo(Okio.buffer(r2.createRequestBody(r3, true)));     // Catch: IOException -> L24
        goto L33
    L26:
        BufferedSink r142 = Okio.buffer(r2.createRequestBody(r3, false));     // Catch: IOException -> L24
        r02.writeTo(r142);     // Catch: IOException -> L24
        r142.close();     // Catch: IOException -> L24
    L15:
        e = e;
        r13 = true;
    L39:
        if ((e instanceof ConnectionShutdownException) == false) goto L41;
        throw e;
    L41:
        if (r2.getHasFailure$okhttp() == false) goto L94;
        Response.Builder r17 = r6;
        r62 = e;
        r03 = r17;
        goto L43
    L94:
        throw e;
    L19:
        r13 = true;
        r6 = null;
    L24:
        e = e;
    L17:
        e = e;
        r13 = true;
        r6 = null;
    L6:
        r6 = null;
        goto L7
    }
}
