package okhttp3.internal.http2;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.url._UrlKt;
import okio.Sink;
import okio.Socket;
import okio.Source;
import okio.Timeout;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 /2\u00020\u0001:\u0001/B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\"\u001a\u00020!H\u0016J\b\u0010#\u001a\u00020!H\u0016J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u0013H\u0016J\u0010\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020+2\u0006\u0010(\u001a\u00020)H\u0016J\n\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020!H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u00060"}, d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "carrier", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "<init>", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/http/ExchangeCodec$Carrier;Lokhttp3/internal/http/RealInterceptorChain;Lokhttp3/internal/http2/Http2Connection;)V", "getCarrier", "()Lokhttp3/internal/http/ExchangeCodec$Carrier;", "stream", "Lokhttp3/internal/http2/Http2Stream;", "protocol", "Lokhttp3/Protocol;", "canceled", _UrlKt.FRAGMENT_ENCODE_SET, "isResponseComplete", "()Z", "socket", "Lokio/Socket;", "getSocket", "()Lokio/Socket;", "createRequestBody", "Lokio/Sink;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "writeRequestHeaders", _UrlKt.FRAGMENT_ENCODE_SET, "flushRequest", "finishRequest", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "openResponseBodySource", "Lokio/Source;", "peekTrailers", "Lokhttp3/Headers;", "cancel", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Http2ExchangeCodec implements ExchangeCodec {
    private static final String CONNECTION = "connection";
    public static final Companion Companion = null;
    private static final String ENCODING = "encoding";
    private static final String HOST = "host";
    private static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS = null;
    private static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS = null;
    private static final String KEEP_ALIVE = "keep-alive";
    private static final String PROXY_CONNECTION = "proxy-connection";
    private static final String TE = "te";
    private static final String TRANSFER_ENCODING = "transfer-encoding";
    private static final String UPGRADE = "upgrade";
    private volatile boolean canceled;
    private final ExchangeCodec.Carrier carrier;
    private final RealInterceptorChain chain;
    private final Http2Connection http2Connection;
    private final Protocol protocol;
    private volatile Http2Stream stream;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CONNECTION", _UrlKt.FRAGMENT_ENCODE_SET, "HOST", "KEEP_ALIVE", "PROXY_CONNECTION", "TRANSFER_ENCODING", "TE", "ENCODING", "UPGRADE", "HTTP_2_SKIPPED_REQUEST_HEADERS", _UrlKt.FRAGMENT_ENCODE_SET, "HTTP_2_SKIPPED_RESPONSE_HEADERS", "http2HeadersList", "Lokhttp3/internal/http2/Header;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "readHttp2HeadersList", "Lokhttp3/Response$Builder;", "headerBlock", "Lokhttp3/Headers;", "protocol", "Lokhttp3/Protocol;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final List<Header> http2HeadersList(Request r6) {
            Headers r5 = r6.headers();
            ArrayList r0 = new ArrayList(r5.size() + 4);
            r0.add(new Header(Header.TARGET_METHOD, r6.method()));
            r0.add(new Header(Header.TARGET_PATH, RequestLine.INSTANCE.requestPath(r6.url())));
            String r1 = r6.header("Host");
            if (r1 == null) goto L5;
            r0.add(new Header(Header.TARGET_AUTHORITY, r1));
        L5:
            r0.add(new Header(Header.TARGET_SCHEME, r6.url().scheme()));
            int r62 = r5.size();
            int r12 = 0;
        L6:
            if (r12 >= r62) goto L15;
            String r2 = r5.name(r12).toLowerCase(Locale.US);
            if (Http2ExchangeCodec.access$getHTTP_2_SKIPPED_REQUEST_HEADERS$cp().contains(r2) == true) goto L10;
        L13:
            r0.add(new Header(r2, r5.value(r12)));
        L14:
            r12 = r12 + 1;
            goto L6
        L10:
            if (Intrinsics.areEqual(r2, Http2ExchangeCodec.TE) == false) goto L14;
            if (Intrinsics.areEqual(r5.value(r12), "trailers") == false) goto L14;
        L15:
            return r0;
        }

        public final Response.Builder readHttp2HeadersList(Headers r7, Protocol r8) {
            Headers.Builder r6 = new Headers.Builder();
            int r0 = r7.size();
            StatusLine r1 = null;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L11;
            String r3 = r7.name(r2);
            String r4 = r7.value(r2);
            if (Intrinsics.areEqual(r3, Header.RESPONSE_STATUS_UTF8) == false) goto L8;
            r1 = StatusLine.Companion.parse("HTTP/1.1 " + r4);
        L10:
            r2 = r2 + 1;
            goto L3
        L8:
            if (Http2ExchangeCodec.access$getHTTP_2_SKIPPED_RESPONSE_HEADERS$cp().contains(r3) == true) goto L10;
            r6.addLenient$okhttp(r3, r4);
            goto L10
        L11:
            if (r1 == null) goto L15;
            return new Response.Builder().protocol(r8).code(r1.code).message(r1.message).headers(r6.build());
        L15:
            throw new ProtocolException("Expected ':status' header not present");
        }
    }

    static {
        Companion = new Companion(null);
        HTTP_2_SKIPPED_REQUEST_HEADERS = _UtilJvmKt.immutableListOf(new String[]{CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD_UTF8, Header.TARGET_PATH_UTF8, Header.TARGET_SCHEME_UTF8, Header.TARGET_AUTHORITY_UTF8});
        HTTP_2_SKIPPED_RESPONSE_HEADERS = _UtilJvmKt.immutableListOf(new String[]{CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE});
    }

    public Http2ExchangeCodec(OkHttpClient r1, ExchangeCodec.Carrier r2, RealInterceptorChain r3, Http2Connection r4) {
        this.carrier = r2;
        this.chain = r3;
        this.http2Connection = r4;
        List<Protocol> r12 = r1.protocols();
        Protocol r22 = Protocol.H2_PRIOR_KNOWLEDGE;
        if (r12.contains(r22) == true) goto L6;
        r22 = Protocol.HTTP_2;
    L6:
        this.protocol = r22;
    }

    public static final /* synthetic */ List access$getHTTP_2_SKIPPED_REQUEST_HEADERS$cp() {
        return HTTP_2_SKIPPED_REQUEST_HEADERS;
    }

    public static final /* synthetic */ List access$getHTTP_2_SKIPPED_RESPONSE_HEADERS$cp() {
        return HTTP_2_SKIPPED_RESPONSE_HEADERS;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void cancel() {
        this.canceled = true;
        Http2Stream r1 = this.stream;
        if (r1 == null) goto L6;
        r1.closeLater(ErrorCode.CANCEL);
        return;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Sink createRequestBody(Request r1, long r2) {
        return this.stream.getSink();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void finishRequest() {
        this.stream.getSink().close();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void flushRequest() {
        this.http2Connection.flush();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public ExchangeCodec.Carrier getCarrier() {
        return this.carrier;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Socket getSocket() {
        return this.stream;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public boolean isResponseComplete() {
        Http2Stream r2 = this.stream;
        if (r2 != null) goto L5;
    L7:
        return false;
    L5:
        if (r2.isSourceComplete() != true) goto L7;
        return true;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Source openResponseBodySource(Response r1) {
        return this.stream.getSource();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Headers peekTrailers() {
        return this.stream.peekTrailers();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Response.Builder readResponseHeaders(boolean r4) {
        Http2Stream r0 = this.stream;
        if (r0 == null) goto L10;
        Headers r02 = r0.takeHeaders(r4);
        Response.Builder r3 = Companion.readHttp2HeadersList(r02, this.protocol);
        if (r4 == true) goto L7;
    L9:
        return r3;
    L7:
        if (r3.getCode$okhttp() != 100) goto L9;
        return null;
    L10:
        Model$$ExternalSyntheticBUOutline0.m("stream wasn't created");
        return null;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public long reportedContentLength(Response r1) {
        if (HttpHeaders.promisesBody(r1) == true) goto L7;
        return 0;
    L7:
        return _UtilJvmKt.headersContentLength(r1);
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void writeRequestHeaders(Request r4) {
        if (this.stream == null) goto L6;
        return;
    L6:
        if (r4.body() == null) goto L8;
        boolean r0 = true;
    L9:
        this.stream = this.http2Connection.newStream(Companion.http2HeadersList(r4), r0);
        boolean r42 = this.canceled;
        Http2Stream r02 = this.stream;
        if (r42 == true) goto L13;
        Timeout r43 = r02.readTimeout();
        long r03 = this.chain.getReadTimeoutMillis$okhttp();
        TimeUnit r2 = TimeUnit.MILLISECONDS;
        r43.timeout(r03, r2);
        this.stream.writeTimeout().timeout(this.chain.getWriteTimeoutMillis$okhttp(), r2);
        return;
    L13:
        r02.closeLater(ErrorCode.CANCEL);
        Model$$ExternalSyntheticBUOutline0.m("Canceled");
        return;
    L8:
        r0 = false;
        goto L9
    }
}
