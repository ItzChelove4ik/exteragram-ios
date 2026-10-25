package okhttp3.internal.http1;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Response$Builder$$ExternalSyntheticBUOutline1;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.BufferedSocket;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.Buffer$$ExternalSyntheticBUOutline3;
import okio.BufferedSink;
import okio.ForwardingTimeout;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Sink;
import okio.Socket;
import okio.Source;
import okio.Timeout;
import org.telegram.ui.WearAuthSheet$$ExternalSyntheticBUOutline0;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 D2\u00020\u0001:\u0007>?@ABCDB!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u0016H\u0016J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020\u0014H\u0016J\u0010\u0010&\u001a\u00020'2\u0006\u0010%\u001a\u00020\u0014H\u0016J\n\u0010(\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010)\u001a\u00020\"H\u0016J\b\u0010*\u001a\u00020\"H\u0016J\u0016\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020.J\u0012\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u00020\u0013H\u0016J\b\u00102\u001a\u00020\u001dH\u0002J\b\u00103\u001a\u00020\u001dH\u0002J\u0018\u00104\u001a\u00020'2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020 H\u0002J\u0010\u00108\u001a\u00020'2\u0006\u00105\u001a\u000206H\u0002J\u0010\u00109\u001a\u00020'2\u0006\u00105\u001a\u000206H\u0002J\u0010\u0010:\u001a\u00020\"2\u0006\u0010;\u001a\u00020<H\u0002J\u000e\u0010=\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0014R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0015R\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006E"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "carrier", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "socket", "Lokhttp3/internal/connection/BufferedSocket;", "<init>", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/http/ExchangeCodec$Carrier;Lokhttp3/internal/connection/BufferedSocket;)V", "getCarrier", "()Lokhttp3/internal/http/ExchangeCodec$Carrier;", "getSocket", "()Lokhttp3/internal/connection/BufferedSocket;", "state", _UrlKt.FRAGMENT_ENCODE_SET, "headersReader", "Lokhttp3/internal/http1/HeadersReader;", "isChunked", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Response;", "(Lokhttp3/Response;)Z", "Lokhttp3/Request;", "(Lokhttp3/Request;)Z", "trailers", "Lokhttp3/Headers;", "isResponseComplete", "()Z", "createRequestBody", "Lokio/Sink;", PluginsConstants.REQUEST, "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "writeRequestHeaders", "reportedContentLength", PluginsConstants.RESPONSE, "openResponseBodySource", "Lokio/Source;", "peekTrailers", "flushRequest", "finishRequest", "writeRequest", "headers", "requestLine", _UrlKt.FRAGMENT_ENCODE_SET, "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "newChunkedSink", "newKnownLengthSink", "newFixedLengthSource", "url", "Lokhttp3/HttpUrl;", "length", "newChunkedSource", "newUnknownLengthSource", "detachTimeout", "timeout", "Lokio/ForwardingTimeout;", "skipConnectBody", "KnownLengthSink", "ChunkedSink", "AbstractSource", "FixedLengthSource", "ChunkedSource", "UnknownLengthSource", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,562:1\n1#2:563\n*E\n"})
/* loaded from: classes5.dex */
public final class Http1ExchangeCodec implements ExchangeCodec {
    public static final Companion Companion = null;
    private static final long NO_CHUNK_YET = -1;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private static final Headers TRAILERS_RESPONSE_BODY_TRUNCATED = null;
    private final ExchangeCodec.Carrier carrier;
    private final OkHttpClient client;
    private final HeadersReader headersReader;
    private final BufferedSocket socket;
    private int state;
    private Headers trailers;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b¢\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokio/Source;", "url", "Lokhttp3/HttpUrl;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "getUrl", "()Lokhttp3/HttpUrl;", "timeout", "Lokio/ForwardingTimeout;", "getTimeout", "()Lokio/ForwardingTimeout;", "closed", _UrlKt.FRAGMENT_ENCODE_SET, "getClosed", "()Z", "setClosed", "(Z)V", "Lokio/Timeout;", "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "responseBodyComplete", _UrlKt.FRAGMENT_ENCODE_SET, "trailers", "Lokhttp3/Headers;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public abstract class AbstractSource implements Source {
        private boolean closed;
        final /* synthetic */ Http1ExchangeCodec this$0;
        private final ForwardingTimeout timeout;
        private final HttpUrl url;

        public AbstractSource(Http1ExchangeCodec r1, HttpUrl r2) {
            this.this$0 = r1;
            this.url = r2;
            this.timeout = new ForwardingTimeout(r1.getSocket().getSource().timeout());
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public abstract /* synthetic */ void close();

        public final boolean getClosed() {
            return this.closed;
        }

        public final ForwardingTimeout getTimeout() {
            return this.timeout;
        }

        public final HttpUrl getUrl() {
            return this.url;
        }

        @Override // okio.Source
        public long read(Buffer r2, long r3) {
            return this.this$0.getSocket().getSource().read(r2, r3);
        L4:
            e = move-exception;
            this.this$0.getCarrier().noNewExchanges();
            this.responseBodyComplete(Http1ExchangeCodec.access$getTRAILERS_RESPONSE_BODY_TRUNCATED$cp());
            throw e;
        }

        public final void responseBodyComplete(Headers r5) {
            if (Http1ExchangeCodec.access$getState$p(this.this$0) == 6) goto L20;
            int r0 = Http1ExchangeCodec.access$getState$p(this.this$0);
            Http1ExchangeCodec r2 = this.this$0;
            if (r0 != 5) goto L15;
            Http1ExchangeCodec.access$detachTimeout(r2, this.timeout);
            Http1ExchangeCodec.access$setTrailers$p(this.this$0, r5);
            Http1ExchangeCodec.access$setState$p(this.this$0, 6);
            if (r5.size() <= 0) goto L17;
            OkHttpClient r02 = Http1ExchangeCodec.access$getClient$p(this.this$0);
            if (r02 == null) goto L18;
            CookieJar r03 = r02.cookieJar();
            if (r03 == null) goto L19;
            HttpHeaders.receiveHeaders(r03, this.url, r5);
            return;
        L19:
            return;
        L18:
            return;
        L17:
            return;
        L15:
            WearAuthSheet$$ExternalSyntheticBUOutline0.m("state: ", Http1ExchangeCodec.access$getState$p(r2));
            return;
        }

        public final void setClosed(boolean r1) {
            this.closed = r1;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink;", "Lokio/Sink;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "timeout", "Lokio/ForwardingTimeout;", "closed", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/Timeout;", "write", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/Buffer;", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "flush", "close", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,562:1\n1#2:563\n*E\n"})
    public final class ChunkedSink implements Sink {
        private boolean closed;
        final /* synthetic */ Http1ExchangeCodec this$0;
        private final ForwardingTimeout timeout;

        public ChunkedSink(Http1ExchangeCodec r2) {
            this.this$0 = r2;
            this.timeout = new ForwardingTimeout(r2.getSocket().getSink().timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            monitor-enter(this);
        L11:
            th = move-exception;
            throw th;
        L4:
            if (this.closed == false) goto L8;
            monitor-exit(this);
            return;
        L8:
            this.closed = true;     // Catch: Throwable -> L11
            this.this$0.getSocket().getSink().writeUtf8("0\r\n\r\n");     // Catch: Throwable -> L11
            Http1ExchangeCodec.access$detachTimeout(this.this$0, this.timeout);     // Catch: Throwable -> L11
            Http1ExchangeCodec.access$setState$p(this.this$0, 3);     // Catch: Throwable -> L11
            monitor-exit(this);
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() {
            monitor-enter(this);
        L10:
            th = move-exception;
            throw th;
        L4:
            if (this.closed == false) goto L7;
            monitor-exit(this);
            return;
        L7:
            this.this$0.getSocket().getSink().flush();     // Catch: Throwable -> L10
            monitor-exit(this);
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(Buffer r3, long r4) {
            if (this.closed == false) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("closed");
            return;
        L5:
            if (r4 != 0) goto L7;
            return;
        L7:
            BufferedSink r2 = this.this$0.getSocket().getSink();
            r2.writeHexadecimalUnsignedLong(r4);
            r2.writeUtf8("\r\n");
            r2.write(r3, r4);
            r2.writeUtf8("\r\n");
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "url", "Lokhttp3/HttpUrl;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "bytesRemainingInChunk", _UrlKt.FRAGMENT_ENCODE_SET, "hasMoreChunks", _UrlKt.FRAGMENT_ENCODE_SET, "read", "sink", "Lokio/Buffer;", "byteCount", "readChunkSize", _UrlKt.FRAGMENT_ENCODE_SET, "close", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,562:1\n1#2:563\n*E\n"})
    public final class ChunkedSource extends AbstractSource {
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;
        final /* synthetic */ Http1ExchangeCodec this$0;

        public ChunkedSource(Http1ExchangeCodec r1, HttpUrl r2) {
            this.this$0 = r1;
            super(r1, r2);
            this.bytesRemainingInChunk = -1;
            this.hasMoreChunks = true;
        }

        private final void readChunkSize() {
            if (this.bytesRemainingInChunk == (-1)) goto L20;
            this.this$0.getSocket().getSource().readUtf8LineStrict();
        L20:
            this.bytesRemainingInChunk = this.this$0.getSocket().getSource().readHexadecimalUnsignedLong();     // Catch: NumberFormatException -> L17
            String r1 = StringsKt.trim(this.this$0.getSocket().getSource().readUtf8LineStrict()).toString();     // Catch: NumberFormatException -> L17
            if (this.bytesRemainingInChunk < 0) goto L16;
            if (r1.length() <= 0) goto L12;
            if (StringsKt.startsWith$default(r1, ";", false, 2, null) == false) goto L16;
        L12:
            if (this.bytesRemainingInChunk != 0) goto L22;
            this.hasMoreChunks = false;
            responseBodyComplete(Http1ExchangeCodec.access$getHeadersReader$p(this.this$0).readHeaders());
            return;
        L22:
            return;
        L16:
            throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + r1 + Typography.quote);     // Catch: NumberFormatException -> L17
        L17:
            e = move-exception;
            throw new ProtocolException(e.getMessage());
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (getClosed() == false) goto L6;
            return;
        L6:
            if (this.hasMoreChunks == true) goto L8;
        L10:
            setClosed(true);
            return;
        L8:
            if (_UtilJvmKt.discard(this, 100, TimeUnit.MILLISECONDS) == true) goto L10;
            this.this$0.getCarrier().noNewExchanges();
            responseBodyComplete(Http1ExchangeCodec.access$getTRAILERS_RESPONSE_BODY_TRUNCATED$cp());
            goto L10
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source
        public long read(Buffer r8, long r9) {
            if (r9 >= 0) goto L5;
            Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r9);
            return 0;
        L5:
            if (getClosed() == false) goto L7;
            Segment$$ExternalSyntheticBUOutline1.m("closed");
            return 0;
        L7:
            if (this.hasMoreChunks == true) goto L9;
            return -1;
        L9:
            long r5 = this.bytesRemainingInChunk;
            if (r5 != 0) goto L12;
        L13:
            readChunkSize();
            if (this.hasMoreChunks == true) goto L16;
            return -1;
        L16:
            long r82 = super.read(r8, Math.min(r9, this.bytesRemainingInChunk));
            if (r82 == (-1)) goto L20;
            this.bytesRemainingInChunk -= r82;
            return r82;
        L20:
            this.this$0.getCarrier().noNewExchanges();
            ProtocolException r83 = new ProtocolException("unexpected end of stream");
            responseBodyComplete(Http1ExchangeCodec.access$getTRAILERS_RESPONSE_BODY_TRUNCATED$cp());
            throw r83;
        L12:
            if (r5 != (-1)) goto L16;
            goto L13
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NO_CHUNK_YET", _UrlKt.FRAGMENT_ENCODE_SET, "STATE_IDLE", _UrlKt.FRAGMENT_ENCODE_SET, "STATE_OPEN_REQUEST_BODY", "STATE_WRITING_REQUEST_BODY", "STATE_READ_RESPONSE_HEADERS", "STATE_OPEN_RESPONSE_BODY", "STATE_READING_RESPONSE_BODY", "STATE_CLOSED", "TRAILERS_RESPONSE_BODY_TRUNCATED", "Lokhttp3/Headers;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "url", "Lokhttp3/HttpUrl;", "bytesRemaining", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;J)V", "read", "sink", "Lokio/Buffer;", "byteCount", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,562:1\n1#2:563\n*E\n"})
    public final class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;
        final /* synthetic */ Http1ExchangeCodec this$0;

        public FixedLengthSource(Http1ExchangeCodec r1, HttpUrl r2, long r3) {
            this.this$0 = r1;
            super(r1, r2);
            this.bytesRemaining = r3;
            if (r3 != 0) goto L6;
            responseBodyComplete(Headers.EMPTY);
            return;
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (getClosed() == false) goto L6;
            return;
        L6:
            if (this.bytesRemaining != 0) goto L8;
        L10:
            setClosed(true);
            return;
        L8:
            if (_UtilJvmKt.discard(this, 100, TimeUnit.MILLISECONDS) == true) goto L10;
            this.this$0.getCarrier().noNewExchanges();
            responseBodyComplete(Http1ExchangeCodec.access$getTRAILERS_RESPONSE_BODY_TRUNCATED$cp());
            goto L10
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source
        public long read(Buffer r8, long r9) {
            if (r9 >= 0) goto L5;
            Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r9);
            return 0;
        L5:
            if (getClosed() == true) goto L17;
            long r2 = this.bytesRemaining;
            if (r2 != 0) goto L9;
            return -1;
        L9:
            long r82 = super.read(r8, Math.min(r2, r9));
            if (r82 == (-1)) goto L15;
            long r22 = this.bytesRemaining - r82;
            this.bytesRemaining = r22;
            if (r22 != 0) goto L14;
            responseBodyComplete(Headers.EMPTY);
        L14:
            return r82;
        L15:
            this.this$0.getCarrier().noNewExchanges();
            ProtocolException r83 = new ProtocolException("unexpected end of stream");
            responseBodyComplete(Http1ExchangeCodec.access$getTRAILERS_RESPONSE_BODY_TRUNCATED$cp());
            throw r83;
        L17:
            Segment$$ExternalSyntheticBUOutline1.m("closed");
            return 0;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink;", "Lokio/Sink;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "timeout", "Lokio/ForwardingTimeout;", "closed", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/Timeout;", "write", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/Buffer;", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "flush", "close", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,562:1\n1#2:563\n*E\n"})
    public final class KnownLengthSink implements Sink {
        private boolean closed;
        final /* synthetic */ Http1ExchangeCodec this$0;
        private final ForwardingTimeout timeout;

        public KnownLengthSink(Http1ExchangeCodec r2) {
            this.this$0 = r2;
            this.timeout = new ForwardingTimeout(r2.getSocket().getSink().timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed == false) goto L5;
            return;
        L5:
            this.closed = true;
            Http1ExchangeCodec.access$detachTimeout(this.this$0, this.timeout);
            Http1ExchangeCodec.access$setState$p(this.this$0, 3);
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            if (this.closed == false) goto L5;
            return;
        L5:
            this.this$0.getSocket().getSink().flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(Buffer r8, long r9) {
            if (this.closed == true) goto L6;
            _UtilCommonKt.checkOffsetAndCount(r8.size(), 0, r9);
            this.this$0.getSocket().getSink().write(r8, r9);
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("closed");
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "url", "Lokhttp3/HttpUrl;", "<init>", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "inputExhausted", _UrlKt.FRAGMENT_ENCODE_SET, "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttp1ExchangeCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http1ExchangeCodec.kt\nokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,562:1\n1#2:563\n*E\n"})
    public final class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;
        final /* synthetic */ Http1ExchangeCodec this$0;

        public UnknownLengthSource(Http1ExchangeCodec r1, HttpUrl r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (getClosed() == false) goto L6;
            return;
        L6:
            if (this.inputExhausted == true) goto L8;
            responseBodyComplete(Http1ExchangeCodec.access$getTRAILERS_RESPONSE_BODY_TRUNCATED$cp());
        L8:
            setClosed(true);
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, okio.Source
        public long read(Buffer r4, long r5) {
            if (r5 >= 0) goto L5;
            Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r5);
            return 0;
        L5:
            if (getClosed() == false) goto L7;
            Segment$$ExternalSyntheticBUOutline1.m("closed");
            return 0;
        L7:
            if (this.inputExhausted == false) goto L9;
            return -1;
        L9:
            long r42 = super.read(r4, r5);
            if (r42 != (-1)) goto L13;
            this.inputExhausted = true;
            responseBodyComplete(Headers.EMPTY);
            return -1;
        L13:
            return r42;
        }
    }

    static {
        Companion = new Companion(null);
        TRAILERS_RESPONSE_BODY_TRUNCATED = Headers.Companion.of(new String[]{"OkHttp-Response-Body", "Truncated"});
    }

    public Http1ExchangeCodec(OkHttpClient r1, ExchangeCodec.Carrier r2, BufferedSocket r3) {
        this.client = r1;
        this.carrier = r2;
        this.socket = r3;
        this.headersReader = new HeadersReader(getSocket().getSource());
    }

    public static final /* synthetic */ void access$detachTimeout(Http1ExchangeCodec r0, ForwardingTimeout r1) {
        r0.detachTimeout(r1);
    }

    public static final /* synthetic */ OkHttpClient access$getClient$p(Http1ExchangeCodec r0) {
        return r0.client;
    }

    public static final /* synthetic */ HeadersReader access$getHeadersReader$p(Http1ExchangeCodec r0) {
        return r0.headersReader;
    }

    public static final /* synthetic */ int access$getState$p(Http1ExchangeCodec r0) {
        return r0.state;
    }

    public static final /* synthetic */ Headers access$getTRAILERS_RESPONSE_BODY_TRUNCATED$cp() {
        return TRAILERS_RESPONSE_BODY_TRUNCATED;
    }

    public static final /* synthetic */ void access$setState$p(Http1ExchangeCodec r0, int r1) {
        r0.state = r1;
    }

    public static final /* synthetic */ void access$setTrailers$p(Http1ExchangeCodec r0, Headers r1) {
        r0.trailers = r1;
    }

    private final void detachTimeout(ForwardingTimeout r2) {
        Timeout r1 = r2.delegate();
        r2.setDelegate(Timeout.NONE);
        r1.clearDeadline();
        r1.clearTimeout();
    }

    private final boolean isChunked(Request r2) {
        return StringsKt.equals("chunked", r2.header("Transfer-Encoding"), true);
    }

    private final boolean isChunked(Response r3) {
        return StringsKt.equals("chunked", Response.header$default(r3, "Transfer-Encoding", null, 2, null), true);
    }

    private final Sink newChunkedSink() {
        if (this.state != 1) goto L6;
        this.state = 2;
        return new ChunkedSink(this);
    L6:
        Response$Builder$$ExternalSyntheticBUOutline1.m("state: ", this.state);
        return null;
    }

    private final Source newChunkedSource(HttpUrl r3) {
        if (this.state != 4) goto L6;
        this.state = 5;
        return new ChunkedSource(this, r3);
    L6:
        Response$Builder$$ExternalSyntheticBUOutline1.m("state: ", this.state);
        return null;
    }

    private final Source newFixedLengthSource(HttpUrl r3, long r4) {
        if (this.state != 4) goto L6;
        this.state = 5;
        return new FixedLengthSource(this, r3, r4);
    L6:
        Response$Builder$$ExternalSyntheticBUOutline1.m("state: ", this.state);
        return null;
    }

    private final Sink newKnownLengthSink() {
        if (this.state != 1) goto L6;
        this.state = 2;
        return new KnownLengthSink(this);
    L6:
        Response$Builder$$ExternalSyntheticBUOutline1.m("state: ", this.state);
        return null;
    }

    private final Source newUnknownLengthSource(HttpUrl r3) {
        if (this.state != 4) goto L6;
        this.state = 5;
        getCarrier().noNewExchanges();
        return new UnknownLengthSource(this, r3);
    L6:
        Response$Builder$$ExternalSyntheticBUOutline1.m("state: ", this.state);
        return null;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void cancel() {
        getCarrier().mo3746cancel();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Sink createRequestBody(Request r3, long r4) {
        RequestBody r0 = r3.body();
        if (r0 == null) goto L10;
        if (r0.isDuplex() != true) goto L10;
        throw new ProtocolException("Duplex connections are not supported for HTTP/1");
    L10:
        if (isChunked(r3) == false) goto L14;
        return newChunkedSink();
    L14:
        if (r4 != (-1)) goto L16;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot stream a request body without chunked encoding or a known content length!");
        return null;
    L16:
        return newKnownLengthSink();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void finishRequest() {
        getSocket().getSink().flush();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void flushRequest() {
        getSocket().getSink().flush();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public ExchangeCodec.Carrier getCarrier() {
        return this.carrier;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public BufferedSocket getSocket() {
        return this.socket;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public /* bridge */ /* synthetic */ Socket getSocket() {
        return getSocket();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public boolean isResponseComplete() {
        if (this.state != 6) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Source openResponseBodySource(Response r5) {
        if (HttpHeaders.promisesBody(r5) == true) goto L7;
        return newFixedLengthSource(r5.request().url(), 0);
    L7:
        if (isChunked(r5) == true) goto L9;
        long r0 = _UtilJvmKt.headersContentLength(r5);
        if (r0 == (-1)) goto L15;
        return newFixedLengthSource(r5.request().url(), r0);
    L15:
        return newUnknownLengthSource(r5.request().url());
    L9:
        return newChunkedSource(r5.request().url());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Headers peekTrailers() {
        Headers r0 = this.trailers;
        if (r0 == TRAILERS_RESPONSE_BODY_TRUNCATED) goto L13;
        int r1 = this.state;
        if (r1 != 5) goto L7;
    L12:
        return r0;
    L7:
        if (r1 == 6) goto L12;
        Response$Builder$$ExternalSyntheticBUOutline1.m("Trailers cannot be read because the state is ", this.state);
        return null;
    L13:
        Model$$ExternalSyntheticBUOutline0.m("Trailers cannot be read because the response body was truncated");
        return null;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public Response.Builder readResponseHeaders(boolean r6) {
        int r0 = this.state;
        if (r0 != 0) goto L5;
    L33:
        StatusLine r02 = StatusLine.Companion.parse(this.headersReader.readLine());     // Catch: EOFException -> L17
        Response.Builder r3 = new Response.Builder().protocol(r02.protocol).code(r02.code).message(r02.message).headers(this.headersReader.readHeaders());     // Catch: EOFException -> L17
        if (r6 == true) goto L15;
    L19:
        int r62 = r02.code;     // Catch: EOFException -> L17
        if (r62 != 100) goto L24;
        this.state = 3;     // Catch: EOFException -> L17
        return r3;
    L24:
        if (102 <= r62) goto L26;
    L29:
        this.state = 4;     // Catch: EOFException -> L17
        return r3;
    L26:
        if (r62 >= 200) goto L29;
        this.state = 3;     // Catch: EOFException -> L17
        return r3;
    L15:
        if (r02.code != 100) goto L19;
        return null;
    L17:
        e = move-exception;
        throw new IOException("unexpected end of stream on " + getCarrier().getRoute().address().url().redact(), e);
    L5:
        if (r0 == 1) goto L33;
        if (r0 == 2) goto L33;
        if (r0 == 3) goto L33;
        Response$Builder$$ExternalSyntheticBUOutline1.m("state: ", this.state);
        return null;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public long reportedContentLength(Response r2) {
        if (HttpHeaders.promisesBody(r2) == true) goto L7;
        return 0;
    L7:
        if (isChunked(r2) == false) goto L11;
        return -1;
    L11:
        return _UtilJvmKt.headersContentLength(r2);
    }

    public final void skipConnectBody(Response r5) {
        long r0 = _UtilJvmKt.headersContentLength(r5);
        if (r0 != (-1)) goto L5;
        return;
    L5:
        Source r4 = newFixedLengthSource(r5.request().url(), r0);
        _UtilJvmKt.skipAll(r4, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        r4.close();
    }

    public final void writeRequest(Headers r5, String r6) {
        if (this.state != 0) goto L9;
        getSocket().getSink().writeUtf8(r6).writeUtf8("\r\n");
        int r62 = r5.size();
        int r1 = 0;
    L5:
        if (r1 >= r62) goto L7;
        getSocket().getSink().writeUtf8(r5.name(r1)).writeUtf8(": ").writeUtf8(r5.value(r1)).writeUtf8("\r\n");
        r1 = r1 + 1;
        goto L5
    L7:
        getSocket().getSink().writeUtf8("\r\n");
        this.state = 1;
        return;
    L9:
        Response$Builder$$ExternalSyntheticBUOutline1.m("state: ", this.state);
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public void writeRequestHeaders(Request r3) {
        String r0 = RequestLine.INSTANCE.get(r3, getCarrier().getRoute().proxy().type());
        writeRequest(r3.headers(), r0);
    }
}
