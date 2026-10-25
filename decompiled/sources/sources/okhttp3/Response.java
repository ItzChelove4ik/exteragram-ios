package okhttp3;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Closeable;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Headers;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Socket;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001PB\u008d\u0001\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0000\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0000\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b/J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b0J\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b1J\r\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\b5J\u000f\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\b6J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u0007072\u0006\u00108\u001a\u00020\u0007J\u001e\u00109\u001a\u0004\u0018\u00010\u00072\u0006\u00108\u001a\u00020\u00072\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0007H\u0007J\r\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\b;J\u0006\u0010<\u001a\u00020\rJ\b\u0010=\u001a\u0004\u0018\u00010\rJ\u000e\u0010>\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020\u0016J\r\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\b@J\u0006\u0010A\u001a\u00020BJ\u000f\u0010\u0012\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0002\bDJ\u000f\u0010\u0013\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0002\bEJ\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0002\bFJ\f\u0010G\u001a\b\u0012\u0004\u0012\u00020H07J\r\u0010I\u001a\u00020*H\u0007¢\u0006\u0002\bJJ\r\u0010\u0015\u001a\u00020\u0016H\u0007¢\u0006\u0002\bKJ\r\u0010\u0017\u001a\u00020\u0016H\u0007¢\u0006\u0002\bLJ\b\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u00020\u0007H\u0016R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001eR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u001fR\u0013\u0010\u0006\u001a\u00020\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010 R\u0013\u0010\b\u001a\u00020\t8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010!R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\"R\u0013\u0010\f\u001a\u00020\r8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010#R\u0013\u0010\u000e\u001a\u00020\u000f8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010$R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010%R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u00008\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010&R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u00008\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010&R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u00008\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010&R\u0013\u0010\u0015\u001a\u00020\u00168\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010'R\u0013\u0010\u0017\u001a\u00020\u00168\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010'R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00198\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010(R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0011\u00102\u001a\u000203¢\u0006\b\n\u0000\u001a\u0004\b2\u00104R\u0011\u0010C\u001a\u000203¢\u0006\b\n\u0000\u001a\u0004\bC\u00104R\u0011\u0010I\u001a\u00020*8G¢\u0006\u0006\u001a\u0004\bI\u0010,¨\u0006Q"}, d2 = {"Lokhttp3/Response;", "Ljava/io/Closeable;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "protocol", "Lokhttp3/Protocol;", "message", _UrlKt.FRAGMENT_ENCODE_SET, "code", _UrlKt.FRAGMENT_ENCODE_SET, "handshake", "Lokhttp3/Handshake;", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/ResponseBody;", "socket", "Lokio/Socket;", "networkResponse", "cacheResponse", "priorResponse", "sentRequestAtMillis", _UrlKt.FRAGMENT_ENCODE_SET, "receivedResponseAtMillis", "exchange", "Lokhttp3/internal/connection/Exchange;", "trailersSource", "Lokhttp3/TrailersSource;", "<init>", "(Lokhttp3/Request;Lokhttp3/Protocol;Ljava/lang/String;ILokhttp3/Handshake;Lokhttp3/Headers;Lokhttp3/ResponseBody;Lokio/Socket;Lokhttp3/Response;Lokhttp3/Response;Lokhttp3/Response;JJLokhttp3/internal/connection/Exchange;Lokhttp3/TrailersSource;)V", "()Lokhttp3/Request;", "()Lokhttp3/Protocol;", "()Ljava/lang/String;", "()I", "()Lokhttp3/Handshake;", "()Lokhttp3/Headers;", "()Lokhttp3/ResponseBody;", "()Lokio/Socket;", "()Lokhttp3/Response;", "()J", "()Lokhttp3/internal/connection/Exchange;", "lazyCacheControl", "Lokhttp3/CacheControl;", "getLazyCacheControl$okhttp", "()Lokhttp3/CacheControl;", "setLazyCacheControl$okhttp", "(Lokhttp3/CacheControl;)V", "-deprecated_request", "-deprecated_protocol", "-deprecated_code", "isSuccessful", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "-deprecated_message", "-deprecated_handshake", _UrlKt.FRAGMENT_ENCODE_SET, "name", PluginsConstants.Settings.TYPE_HEADER, "defaultValue", "-deprecated_headers", "trailers", "peekTrailers", "peekBody", "byteCount", "-deprecated_body", "newBuilder", "Lokhttp3/Response$Builder;", "isRedirect", "-deprecated_networkResponse", "-deprecated_cacheResponse", "-deprecated_priorResponse", "challenges", "Lokhttp3/Challenge;", "cacheControl", "-deprecated_cacheControl", "-deprecated_sentRequestAtMillis", "-deprecated_receivedResponseAtMillis", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "Builder", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Response implements Closeable {
    private final ResponseBody body;
    private final Response cacheResponse;
    private final int code;
    private final Exchange exchange;
    private final Handshake handshake;
    private final Headers headers;
    private final boolean isRedirect;
    private final boolean isSuccessful;
    private CacheControl lazyCacheControl;
    private final String message;
    private final Response networkResponse;
    private final Response priorResponse;
    private final Protocol protocol;
    private final long receivedResponseAtMillis;
    private final Request request;
    private final long sentRequestAtMillis;
    private final Socket socket;
    private TrailersSource trailersSource;

    @Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001f\u001a\u00020\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0018\u0010V\u001a\u00020\u00002\u0006\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020\u001aH\u0016J\u0018\u0010Y\u001a\u00020\u00002\u0006\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020\u001aH\u0016J\u0010\u0010Z\u001a\u00020\u00002\u0006\u0010W\u001a\u00020\u001aH\u0016J\u0010\u0010%\u001a\u00020\u00002\u0006\u0010%\u001a\u00020[H\u0016J\u0010\u0010+\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u00101\u001a\u00020\u00002\u0006\u00101\u001a\u000202H\u0016J\u0012\u00107\u001a\u00020\u00002\b\u00107\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010;\u001a\u00020\u00002\b\u0010;\u001a\u0004\u0018\u00010\u0005H\u0016J\u001a\u0010\\\u001a\u00020]2\u0006\u0010W\u001a\u00020\u001a2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\u0012\u0010>\u001a\u00020\u00002\b\u0010>\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010^\u001a\u00020\u00002\u0006\u0010P\u001a\u00020QH\u0016J\u0010\u0010A\u001a\u00020\u00002\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010G\u001a\u00020\u00002\u0006\u0010G\u001a\u00020BH\u0016J\u0015\u0010_\u001a\u00020]2\u0006\u0010J\u001a\u00020KH\u0000¢\u0006\u0002\b`J\b\u0010a\u001a\u00020\u0005H\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u000102X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001c\u00107\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010\u0006R\u001c\u0010;\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u00109\"\u0004\b=\u0010\u0006R\u001c\u0010>\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u00109\"\u0004\b@\u0010\u0006R\u001a\u0010A\u001a\u00020BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010G\u001a\u00020BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010D\"\u0004\bI\u0010FR\u001c\u0010J\u001a\u0004\u0018\u00010KX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010P\u001a\u00020QX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010U¨\u0006b"}, d2 = {"Lokhttp3/Response$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "(Lokhttp3/Response;)V", PluginsConstants.REQUEST, "Lokhttp3/Request;", "getRequest$okhttp", "()Lokhttp3/Request;", "setRequest$okhttp", "(Lokhttp3/Request;)V", "protocol", "Lokhttp3/Protocol;", "getProtocol$okhttp", "()Lokhttp3/Protocol;", "setProtocol$okhttp", "(Lokhttp3/Protocol;)V", "code", _UrlKt.FRAGMENT_ENCODE_SET, "getCode$okhttp", "()I", "setCode$okhttp", "(I)V", "message", _UrlKt.FRAGMENT_ENCODE_SET, "getMessage$okhttp", "()Ljava/lang/String;", "setMessage$okhttp", "(Ljava/lang/String;)V", "handshake", "Lokhttp3/Handshake;", "getHandshake$okhttp", "()Lokhttp3/Handshake;", "setHandshake$okhttp", "(Lokhttp3/Handshake;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "body", "Lokhttp3/ResponseBody;", "getBody$okhttp", "()Lokhttp3/ResponseBody;", "setBody$okhttp", "(Lokhttp3/ResponseBody;)V", "socket", "Lokio/Socket;", "getSocket$okhttp", "()Lokio/Socket;", "setSocket$okhttp", "(Lokio/Socket;)V", "networkResponse", "getNetworkResponse$okhttp", "()Lokhttp3/Response;", "setNetworkResponse$okhttp", "cacheResponse", "getCacheResponse$okhttp", "setCacheResponse$okhttp", "priorResponse", "getPriorResponse$okhttp", "setPriorResponse$okhttp", "sentRequestAtMillis", _UrlKt.FRAGMENT_ENCODE_SET, "getSentRequestAtMillis$okhttp", "()J", "setSentRequestAtMillis$okhttp", "(J)V", "receivedResponseAtMillis", "getReceivedResponseAtMillis$okhttp", "setReceivedResponseAtMillis$okhttp", "exchange", "Lokhttp3/internal/connection/Exchange;", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "setExchange$okhttp", "(Lokhttp3/internal/connection/Exchange;)V", "trailersSource", "Lokhttp3/TrailersSource;", "getTrailersSource$okhttp", "()Lokhttp3/TrailersSource;", "setTrailersSource$okhttp", "(Lokhttp3/TrailersSource;)V", PluginsConstants.Settings.TYPE_HEADER, "name", "value", "addHeader", "removeHeader", "Lokhttp3/Headers;", "checkSupportResponse", _UrlKt.FRAGMENT_ENCODE_SET, "trailers", "initExchange", "initExchange$okhttp", "build", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nResponse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Response.kt\nokhttp3/Response$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,530:1\n1#2:531\n*E\n"})
    public static class Builder {
        private ResponseBody body;
        private Response cacheResponse;
        private int code;
        private Exchange exchange;
        private Handshake handshake;
        private Headers.Builder headers;
        private String message;
        private Response networkResponse;
        private Response priorResponse;
        private Protocol protocol;
        private long receivedResponseAtMillis;
        private Request request;
        private long sentRequestAtMillis;
        private Socket socket;
        private TrailersSource trailersSource;

        public Builder() {
            this.code = -1;
            this.body = ResponseBody.EMPTY;
            this.trailersSource = TrailersSource.EMPTY;
            this.headers = new Headers.Builder();
        }

        public Builder(Response r3) {
            this.code = -1;
            this.body = ResponseBody.EMPTY;
            this.trailersSource = TrailersSource.EMPTY;
            this.request = r3.request();
            this.protocol = r3.protocol();
            this.code = r3.code();
            this.message = r3.message();
            this.handshake = r3.handshake();
            this.headers = r3.headers().newBuilder();
            this.body = r3.body();
            this.socket = r3.socket();
            this.networkResponse = r3.networkResponse();
            this.cacheResponse = r3.cacheResponse();
            this.priorResponse = r3.priorResponse();
            this.sentRequestAtMillis = r3.sentRequestAtMillis();
            this.receivedResponseAtMillis = r3.receivedResponseAtMillis();
            this.exchange = r3.exchange();
            this.trailersSource = Response.access$getTrailersSource$p(r3);
        }

        private final void checkSupportResponse(String r1, Response r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.networkResponse() == null) goto L6;
            Response$Builder$$ExternalSyntheticBUOutline0.m(r1, ".networkResponse != null");
            return;
        L6:
            if (r2.cacheResponse() == null) goto L8;
            Response$Builder$$ExternalSyntheticBUOutline0.m(r1, ".cacheResponse != null");
            return;
        L8:
            if (r2.priorResponse() != null) goto L10;
            return;
        L10:
            Response$Builder$$ExternalSyntheticBUOutline0.m(r1, ".priorResponse != null");
        }

        public Builder addHeader(String r2, String r3) {
            this.headers.add(r2, r3);
            return this;
        }

        public Builder body(ResponseBody r1) {
            this.body = r1;
            return this;
        }

        public Response build() {
            int r4 = this.code;
            if (r4 < 0) goto L18;
            Request r1 = this.request;
            if (r1 == null) goto L16;
            Protocol r2 = this.protocol;
            if (r2 == null) goto L14;
            String r3 = this.message;
            if (r3 != null) goto L11;
            Segment$$ExternalSyntheticBUOutline1.m("message == null");
            return null;
        L11:
            return new Response(r1, r2, r3, r4, this.handshake, this.headers.build(), this.body, this.socket, this.networkResponse, this.cacheResponse, this.priorResponse, this.sentRequestAtMillis, this.receivedResponseAtMillis, this.exchange, this.trailersSource);
        L14:
            Segment$$ExternalSyntheticBUOutline1.m("protocol == null");
            return null;
        L16:
            Segment$$ExternalSyntheticBUOutline1.m("request == null");
            return null;
        L18:
            Response$Builder$$ExternalSyntheticBUOutline1.m("code < 0: ", this.code);
            return null;
        }

        public Builder cacheResponse(Response r2) {
            checkSupportResponse("cacheResponse", r2);
            this.cacheResponse = r2;
            return this;
        }

        public Builder code(int r1) {
            this.code = r1;
            return this;
        }

        public final ResponseBody getBody$okhttp() {
            return this.body;
        }

        public final Response getCacheResponse$okhttp() {
            return this.cacheResponse;
        }

        public final int getCode$okhttp() {
            return this.code;
        }

        public final Exchange getExchange$okhttp() {
            return this.exchange;
        }

        public final Handshake getHandshake$okhttp() {
            return this.handshake;
        }

        public final Headers.Builder getHeaders$okhttp() {
            return this.headers;
        }

        public final String getMessage$okhttp() {
            return this.message;
        }

        public final Response getNetworkResponse$okhttp() {
            return this.networkResponse;
        }

        public final Response getPriorResponse$okhttp() {
            return this.priorResponse;
        }

        public final Protocol getProtocol$okhttp() {
            return this.protocol;
        }

        public final long getReceivedResponseAtMillis$okhttp() {
            return this.receivedResponseAtMillis;
        }

        public final Request getRequest$okhttp() {
            return this.request;
        }

        public final long getSentRequestAtMillis$okhttp() {
            return this.sentRequestAtMillis;
        }

        public final Socket getSocket$okhttp() {
            return this.socket;
        }

        public final TrailersSource getTrailersSource$okhttp() {
            return this.trailersSource;
        }

        public Builder handshake(Handshake r1) {
            this.handshake = r1;
            return this;
        }

        public Builder header(String r2, String r3) {
            this.headers.set(r2, r3);
            return this;
        }

        public Builder headers(Headers r1) {
            this.headers = r1.newBuilder();
            return this;
        }

        public final void initExchange$okhttp(Exchange r1) {
            this.exchange = r1;
        }

        public Builder message(String r1) {
            this.message = r1;
            return this;
        }

        public Builder networkResponse(Response r2) {
            checkSupportResponse("networkResponse", r2);
            this.networkResponse = r2;
            return this;
        }

        public Builder priorResponse(Response r1) {
            this.priorResponse = r1;
            return this;
        }

        public Builder protocol(Protocol r1) {
            this.protocol = r1;
            return this;
        }

        public Builder receivedResponseAtMillis(long r1) {
            this.receivedResponseAtMillis = r1;
            return this;
        }

        public Builder removeHeader(String r2) {
            this.headers.removeAll(r2);
            return this;
        }

        public Builder request(Request r1) {
            this.request = r1;
            return this;
        }

        public Builder sentRequestAtMillis(long r1) {
            this.sentRequestAtMillis = r1;
            return this;
        }

        public final void setBody$okhttp(ResponseBody r1) {
            this.body = r1;
        }

        public final void setCacheResponse$okhttp(Response r1) {
            this.cacheResponse = r1;
        }

        public final void setCode$okhttp(int r1) {
            this.code = r1;
        }

        public final void setExchange$okhttp(Exchange r1) {
            this.exchange = r1;
        }

        public final void setHandshake$okhttp(Handshake r1) {
            this.handshake = r1;
        }

        public final void setHeaders$okhttp(Headers.Builder r1) {
            this.headers = r1;
        }

        public final void setMessage$okhttp(String r1) {
            this.message = r1;
        }

        public final void setNetworkResponse$okhttp(Response r1) {
            this.networkResponse = r1;
        }

        public final void setPriorResponse$okhttp(Response r1) {
            this.priorResponse = r1;
        }

        public final void setProtocol$okhttp(Protocol r1) {
            this.protocol = r1;
        }

        public final void setReceivedResponseAtMillis$okhttp(long r1) {
            this.receivedResponseAtMillis = r1;
        }

        public final void setRequest$okhttp(Request r1) {
            this.request = r1;
        }

        public final void setSentRequestAtMillis$okhttp(long r1) {
            this.sentRequestAtMillis = r1;
        }

        public final void setSocket$okhttp(Socket r1) {
            this.socket = r1;
        }

        public final void setTrailersSource$okhttp(TrailersSource r1) {
            this.trailersSource = r1;
        }

        public Builder socket(Socket r1) {
            this.socket = r1;
            return this;
        }

        public Builder trailers(TrailersSource r1) {
            this.trailersSource = r1;
            return this;
        }
    }

    public Response(Request r1, Protocol r2, String r3, int r4, Handshake r5, Headers r6, ResponseBody r7, Socket r8, Response r9, Response r10, Response r11, long r12, long r14, Exchange r16, TrailersSource r17) {
        this.request = r1;
        this.protocol = r2;
        this.message = r3;
        this.code = r4;
        this.handshake = r5;
        this.headers = r6;
        this.body = r7;
        this.socket = r8;
        this.networkResponse = r9;
        this.cacheResponse = r10;
        this.priorResponse = r11;
        this.sentRequestAtMillis = r12;
        this.receivedResponseAtMillis = r14;
        this.exchange = r16;
        this.trailersSource = r17;
        boolean r22 = true;
        if (200 <= r4) goto L5;
    L7:
        boolean r13 = false;
    L8:
        this.isSuccessful = r13;
        if (r4 != 307) goto L11;
    L14:
        this.isRedirect = r22;
        return;
    L11:
        if (r4 == 308) goto L14;
        switch(r4) {
            case 300: goto L14;
            case 301: goto L14;
            case 302: goto L14;
            case 303: goto L14;
            default: goto L13;
        };
    L13:
        r22 = false;
        goto L14
    L5:
        if (r4 >= 300) goto L7;
        r13 = true;
        goto L8
    }

    public static final /* synthetic */ TrailersSource access$getTrailersSource$p(Response r0) {
        return r0.trailersSource;
    }

    public static /* synthetic */ String header$default(Response r0, String r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = null;
    L6:
        return r0.header(r1, r2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}))
    @JvmName(name = "-deprecated_body")
    /* renamed from: -deprecated_body, reason: not valid java name */
    public final ResponseBody m3726deprecated_body() {
        return this.body;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheControl", imports = {}))
    @JvmName(name = "-deprecated_cacheControl")
    /* renamed from: -deprecated_cacheControl, reason: not valid java name */
    public final CacheControl m3727deprecated_cacheControl() {
        return cacheControl();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheResponse", imports = {}))
    @JvmName(name = "-deprecated_cacheResponse")
    /* renamed from: -deprecated_cacheResponse, reason: not valid java name */
    public final Response m3728deprecated_cacheResponse() {
        return this.cacheResponse;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "code", imports = {}))
    @JvmName(name = "-deprecated_code")
    /* renamed from: -deprecated_code, reason: not valid java name */
    public final int m3729deprecated_code() {
        return this.code;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "handshake", imports = {}))
    @JvmName(name = "-deprecated_handshake")
    /* renamed from: -deprecated_handshake, reason: not valid java name */
    public final Handshake m3730deprecated_handshake() {
        return this.handshake;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    @JvmName(name = "-deprecated_headers")
    /* renamed from: -deprecated_headers, reason: not valid java name */
    public final Headers m3731deprecated_headers() {
        return this.headers;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "message", imports = {}))
    @JvmName(name = "-deprecated_message")
    /* renamed from: -deprecated_message, reason: not valid java name */
    public final String m3732deprecated_message() {
        return this.message;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "networkResponse", imports = {}))
    @JvmName(name = "-deprecated_networkResponse")
    /* renamed from: -deprecated_networkResponse, reason: not valid java name */
    public final Response m3733deprecated_networkResponse() {
        return this.networkResponse;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "priorResponse", imports = {}))
    @JvmName(name = "-deprecated_priorResponse")
    /* renamed from: -deprecated_priorResponse, reason: not valid java name */
    public final Response m3734deprecated_priorResponse() {
        return this.priorResponse;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "protocol", imports = {}))
    @JvmName(name = "-deprecated_protocol")
    /* renamed from: -deprecated_protocol, reason: not valid java name */
    public final Protocol m3735deprecated_protocol() {
        return this.protocol;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "receivedResponseAtMillis", imports = {}))
    @JvmName(name = "-deprecated_receivedResponseAtMillis")
    /* renamed from: -deprecated_receivedResponseAtMillis, reason: not valid java name */
    public final long m3736deprecated_receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = PluginsConstants.REQUEST, imports = {}))
    @JvmName(name = "-deprecated_request")
    /* renamed from: -deprecated_request, reason: not valid java name */
    public final Request m3737deprecated_request() {
        return this.request;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sentRequestAtMillis", imports = {}))
    @JvmName(name = "-deprecated_sentRequestAtMillis")
    /* renamed from: -deprecated_sentRequestAtMillis, reason: not valid java name */
    public final long m3738deprecated_sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    @JvmName(name = "body")
    public final ResponseBody body() {
        return this.body;
    }

    @JvmName(name = "cacheControl")
    public final CacheControl cacheControl() {
        CacheControl r0 = this.lazyCacheControl;
        if (r0 != null) goto L6;
        CacheControl r02 = CacheControl.Companion.parse(this.headers);
        this.lazyCacheControl = r02;
        return r02;
    L6:
        return r0;
    }

    @JvmName(name = "cacheResponse")
    public final Response cacheResponse() {
        return this.cacheResponse;
    }

    public final List<Challenge> challenges() {
        Headers r0 = this.headers;
        int r2 = this.code;
        if (r2 != 401) goto L5;
        String r22 = "WWW-Authenticate";
    L11:
        return HttpHeaders.parseChallenges(r0, r22);
    L5:
        if (r2 != 407) goto L7;
        r22 = "Proxy-Authenticate";
        goto L11
    L7:
        return CollectionsKt.emptyList();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.body.close();
    }

    @JvmName(name = "code")
    public final int code() {
        return this.code;
    }

    @JvmName(name = "exchange")
    public final Exchange exchange() {
        return this.exchange;
    }

    public final CacheControl getLazyCacheControl$okhttp() {
        return this.lazyCacheControl;
    }

    @JvmName(name = "handshake")
    public final Handshake handshake() {
        return this.handshake;
    }

    @JvmOverloads
    public final String header(String r3) {
        return header$default(this, r3, null, 2, null);
    }

    @JvmOverloads
    public final String header(String r1, String r2) {
        String r0 = this.headers.get(r1);
        if (r0 != null) goto L5;
        return r2;
    L5:
        return r0;
    }

    public final List<String> headers(String r1) {
        return this.headers.values(r1);
    }

    @JvmName(name = "headers")
    public final Headers headers() {
        return this.headers;
    }

    public final boolean isRedirect() {
        return this.isRedirect;
    }

    public final boolean isSuccessful() {
        return this.isSuccessful;
    }

    @JvmName(name = "message")
    public final String message() {
        return this.message;
    }

    @JvmName(name = "networkResponse")
    public final Response networkResponse() {
        return this.networkResponse;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final ResponseBody peekBody(long r5) {
        BufferedSource r0 = this.body.source().peek();
        Buffer r1 = new Buffer();
        r0.request(r5);
        r1.write(r0, Math.min(r5, r0.getBuffer().size()));
        return ResponseBody.Companion.create(r1, this.body.contentType(), r1.size());
    }

    public final Headers peekTrailers() {
        return this.trailersSource.peek();
    }

    @JvmName(name = "priorResponse")
    public final Response priorResponse() {
        return this.priorResponse;
    }

    @JvmName(name = "protocol")
    public final Protocol protocol() {
        return this.protocol;
    }

    @JvmName(name = "receivedResponseAtMillis")
    public final long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    @JvmName(name = PluginsConstants.REQUEST)
    public final Request request() {
        return this.request;
    }

    @JvmName(name = "sentRequestAtMillis")
    public final long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    public final void setLazyCacheControl$okhttp(CacheControl r1) {
        this.lazyCacheControl = r1;
    }

    @JvmName(name = "socket")
    public final Socket socket() {
        return this.socket;
    }

    public String toString() {
        return "Response{protocol=" + this.protocol + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}';
    }

    public final Headers trailers() {
        return this.trailersSource.get();
    }
}
