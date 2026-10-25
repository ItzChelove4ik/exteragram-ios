package okhttp3.internal.connection;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.IOException;
import java.net.ProtocolException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Sink;
import okio.Socket;
import okio.Source;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002BCB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u0016\u0010#\u001a\u00020$2\u0006\u0010!\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0013J\u0006\u0010&\u001a\u00020 J\u0006\u0010'\u001a\u00020 J\u0006\u0010(\u001a\u00020 J\u0010\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u0013J\u000e\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020.J\u000e\u0010/\u001a\u0002002\u0006\u0010-\u001a\u00020.J\b\u00101\u001a\u0004\u0018\u000102J\u0006\u00103\u001a\u000204J\u0006\u00105\u001a\u00020 J\u0006\u00106\u001a\u00020 J\u0006\u00107\u001a\u00020 J\u0010\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020:H\u0002J8\u0010;\u001a\u0004\u0018\u00010:2\b\b\u0002\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00132\b\b\u0002\u0010?\u001a\u00020\u00132\b\b\u0002\u0010@\u001a\u00020\u00132\b\u00109\u001a\u0004\u0018\u00010:J\u0006\u0010A\u001a\u00020 R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u001a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0016¨\u0006D"}, d2 = {"Lokhttp3/internal/connection/Exchange;", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/internal/connection/RealCall;", "eventListener", "Lokhttp3/EventListener;", "finder", "Lokhttp3/internal/connection/ExchangeFinder;", "codec", "Lokhttp3/internal/http/ExchangeCodec;", "<init>", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/EventListener;Lokhttp3/internal/connection/ExchangeFinder;Lokhttp3/internal/http/ExchangeCodec;)V", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "getFinder$okhttp", "()Lokhttp3/internal/connection/ExchangeFinder;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "isDuplex", "isDuplex$okhttp", "()Z", "hasFailure", "getHasFailure$okhttp", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection$okhttp", "()Lokhttp3/internal/connection/RealConnection;", "isCoalescedConnection", "isCoalescedConnection$okhttp", "writeRequestHeaders", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Lokhttp3/Request;", "createRequestBody", "Lokio/Sink;", "duplex", "flushRequest", "finishRequest", "responseHeadersStart", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "responseHeadersEnd", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "openResponseBody", "Lokhttp3/ResponseBody;", "peekTrailers", "Lokhttp3/Headers;", "upgradeToSocket", "Lokio/Socket;", "noNewExchangesOnConnection", "cancel", "detachWithViolence", "trackFailure", "e", "Ljava/io/IOException;", "bodyComplete", "bytesRead", _UrlKt.FRAGMENT_ENCODE_SET, "isSocket", "responseDone", "requestDone", "noRequestBody", "RequestBodySink", "ResponseBodySource", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Exchange {
    private final RealCall call;
    private final ExchangeCodec codec;
    private final EventListener eventListener;
    private final ExchangeFinder finder;
    private boolean hasFailure;
    private boolean isDuplex;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/internal/connection/Exchange$RequestBodySink;", "Lokio/ForwardingSink;", "delegate", "Lokio/Sink;", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "isSocket", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/connection/Exchange;Lokio/Sink;JZ)V", "completed", "bytesReceived", "invokeStartEvent", "closed", "write", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/Buffer;", "byteCount", "flush", "close", "complete", "Ljava/io/IOException;", "e", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nExchange.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Exchange.kt\nokhttp3/internal/connection/Exchange$RequestBodySink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,404:1\n1#2:405\n*E\n"})
    /* loaded from: classes5.dex */
    public final class RequestBodySink extends ForwardingSink {
        private long bytesReceived;
        private boolean closed;
        private boolean completed;
        private final long contentLength;
        private boolean invokeStartEvent;
        private final boolean isSocket;
        final /* synthetic */ Exchange this$0;

        public RequestBodySink(Exchange r1, Sink r2, long r3, boolean r5) {
            this.this$0 = r1;
            super(r2);
            this.contentLength = r3;
            this.isSocket = r5;
            this.invokeStartEvent = r5;
        }

        private final IOException complete(IOException r11) {
            if (this.completed == false) goto L5;
            return r11;
        L5:
            this.completed = true;
            return Exchange.bodyComplete$default(this.this$0, this.bytesReceived, this.isSocket, false, true, r11, 4, null);
        }

        @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed == false) goto L5;
            return;
        L5:
            this.closed = true;
            long r0 = this.contentLength;
            if (r0 != (-1)) goto L8;
        L17:
            super.close();     // Catch: IOException -> L14
            complete(null);     // Catch: IOException -> L14
            return;
        L14:
            e = move-exception;
            throw complete(e);
        L8:
            if (this.bytesReceived == r0) goto L17;
            throw new ProtocolException("unexpected end of stream");
        }

        @Override // okio.ForwardingSink, okio.Sink, java.io.Flushable
        public void flush() {
            super.flush();     // Catch: IOException -> L4
            return;
        L4:
            e = move-exception;
            throw complete(e);
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void write(Buffer r5, long r6) {
            if (this.closed == true) goto L20;
            long r0 = this.contentLength;
            if (r0 != (-1)) goto L7;
        L22:
        L14:
            e = move-exception;
            throw complete(e);
        L12:
            if (this.invokeStartEvent == false) goto L16;
            this.invokeStartEvent = false;     // Catch: IOException -> L14
            this.this$0.getEventListener$okhttp().requestBodyStart(this.this$0.getCall$okhttp());     // Catch: IOException -> L14
        L16:
            super.write(r5, r6);     // Catch: IOException -> L14
            this.bytesReceived += r6;
            return;
        L7:
            if ((this.bytesReceived + r6) <= r0) goto L22;
            throw new ProtocolException("expected " + this.contentLength + " bytes but received " + (this.bytesReceived + r6));
        L20:
            Segment$$ExternalSyntheticBUOutline1.m("closed");
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/connection/Exchange$ResponseBodySource;", "Lokio/ForwardingSource;", "delegate", "Lokio/Source;", "contentLength", _UrlKt.FRAGMENT_ENCODE_SET, "isSocket", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/connection/Exchange;Lokio/Source;JZ)V", "bytesReceived", "invokeStartEvent", "completed", "closed", "read", "sink", "Lokio/Buffer;", "byteCount", "close", _UrlKt.FRAGMENT_ENCODE_SET, "complete", "Ljava/io/IOException;", "e", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nExchange.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Exchange.kt\nokhttp3/internal/connection/Exchange$ResponseBodySource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,404:1\n1#2:405\n*E\n"})
    public final class ResponseBodySource extends ForwardingSource {
        private long bytesReceived;
        private boolean closed;
        private boolean completed;
        private final long contentLength;
        private boolean invokeStartEvent;
        private final boolean isSocket;
        final /* synthetic */ Exchange this$0;

        public ResponseBodySource(Exchange r1, Source r2, long r3, boolean r5) {
            this.this$0 = r1;
            super(r2);
            this.contentLength = r3;
            this.isSocket = r5;
            this.invokeStartEvent = true;
            if (r3 != 0) goto L6;
            complete(null);
            return;
        }

        @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed == false) goto L5;
            return;
        L5:
            this.closed = true;
            super.close();     // Catch: IOException -> L8
            complete(null);     // Catch: IOException -> L8
            return;
        L8:
            e = move-exception;
            throw complete(e);
        }

        public final IOException complete(IOException r12) {
            if (this.completed == false) goto L5;
            return r12;
        L5:
            this.completed = true;
            if (r12 != null) goto L11;
            if (this.invokeStartEvent == false) goto L11;
            this.invokeStartEvent = false;
            this.this$0.getEventListener$okhttp().responseBodyStart(this.this$0.getCall$okhttp());
        L11:
            return Exchange.bodyComplete$default(this.this$0, this.bytesReceived, this.isSocket, true, false, r12, 8, null);
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(Buffer r9, long r10) {
            if (this.closed == false) goto L28;
            Segment$$ExternalSyntheticBUOutline1.m("closed");
            return 0;
        L28:
            long r92 = delegate().read(r9, r10);     // Catch: IOException -> L7
            if (this.invokeStartEvent == false) goto L10;
            this.invokeStartEvent = false;     // Catch: IOException -> L7
            this.this$0.getEventListener$okhttp().responseBodyStart(this.this$0.getCall$okhttp());     // Catch: IOException -> L7
        L10:
            if (r92 != (-1)) goto L13;
            complete(null);     // Catch: IOException -> L7
            return -1;
        L13:
            long r4 = this.bytesReceived + r92;     // Catch: IOException -> L7
            long r6 = this.contentLength;     // Catch: IOException -> L7
            if (r6 != (-1)) goto L16;
        L20:
            this.bytesReceived = r4;     // Catch: IOException -> L7
            if (Exchange.access$getCodec$p(this.this$0).isResponseComplete() == false) goto L23;
            complete(null);     // Catch: IOException -> L7
        L23:
            return r92;
        L16:
            if (r4 <= r6) goto L20;
            throw new ProtocolException("expected " + this.contentLength + " bytes but received " + r4);     // Catch: IOException -> L7
        L7:
            e = move-exception;
            throw complete(e);
        }
    }

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016R\u0018\u0010\u0004\u001a\u00060\u0005R\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\u00060\nR\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"okhttp3/internal/connection/Exchange$upgradeToSocket$1", "Lokio/Socket;", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokhttp3/internal/connection/Exchange$RequestBodySink;", "Lokhttp3/internal/connection/Exchange;", "getSink", "()Lokhttp3/internal/connection/Exchange$RequestBodySink;", "source", "Lokhttp3/internal/connection/Exchange$ResponseBodySource;", "getSource", "()Lokhttp3/internal/connection/Exchange$ResponseBodySource;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okhttp3.internal.connection.Exchange$upgradeToSocket$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 implements Socket {
        private final RequestBodySink sink;
        private final ResponseBodySource source;
        final /* synthetic */ Exchange this$0;

        public AnonymousClass1(Exchange r13) {
            this.this$0 = r13;
            this.sink = new RequestBodySink(r13, Exchange.access$getCodec$p(r13).getSocket().getSink(), -1, true);
            this.source = new ResponseBodySource(r13, Exchange.access$getCodec$p(r13).getSocket().getSource(), -1, true);
        }

        @Override // okio.Socket
        public void cancel() {
            this.this$0.cancel();
        }

        @Override // okio.Socket
        public RequestBodySink getSink() {
            return this.sink;
        }

        @Override // okio.Socket
        public /* bridge */ /* synthetic */ Sink getSink() {
            return getSink();
        }

        @Override // okio.Socket
        public ResponseBodySource getSource() {
            return this.source;
        }

        @Override // okio.Socket
        public /* bridge */ /* synthetic */ Source getSource() {
            return getSource();
        }
    }

    public Exchange(RealCall r1, EventListener r2, ExchangeFinder r3, ExchangeCodec r4) {
        this.call = r1;
        this.eventListener = r2;
        this.finder = r3;
        this.codec = r4;
    }

    public static final /* synthetic */ ExchangeCodec access$getCodec$p(Exchange r0) {
        return r0.codec;
    }

    public static /* synthetic */ IOException bodyComplete$default(Exchange r7, long r8, boolean r10, boolean r11, boolean r12, IOException r13, int r14, Object r15) {
        if ((r14 & 1) == 0) goto L5;
        r8 = -1;
    L5:
        long r1 = r8;
        if ((r14 & 4) == 0) goto L8;
        boolean r4 = false;
    L10:
        if ((r14 & 8) == 0) goto L13;
        boolean r5 = false;
    L15:
        return r7.bodyComplete(r1, r10, r4, r5, r13);
    L13:
        r5 = r12;
        goto L15
    L8:
        r4 = r11;
        goto L10
    }

    private final void trackFailure(IOException r2) {
        this.hasFailure = true;
        this.codec.getCarrier().trackFailure(this.call, r2);
    }

    public final IOException bodyComplete(long r4, boolean r6, boolean r7, boolean r8, IOException r9) {
        if (r9 == null) goto L4;
        trackFailure(r9);
    L4:
        if (r8 == false) goto L9;
        EventListener r0 = this.eventListener;
        if (r9 == null) goto L8;
        r0.requestFailed(this.call, r9);
        goto L9
    L8:
        r0.requestBodyEnd(this.call, r4);
    L9:
        if (r7 == false) goto L15;
        EventListener r02 = this.eventListener;
        if (r9 == null) goto L14;
        r02.responseFailed(this.call, r9);
        goto L15
    L14:
        r02.responseBodyEnd(this.call, r4);
    L15:
        RealCall r3 = this.call;
        boolean r5 = false;
        if (r8 == false) goto L19;
        if (r6 == true) goto L19;
        boolean r1 = false;
        r5 = true;
    L20:
        if (r7 == false) goto L23;
        if (r6 == true) goto L23;
        boolean r2 = r6;
        boolean r62 = true;
    L24:
        if (r8 == false) goto L27;
        if (r2 == false) goto L27;
        boolean r82 = true;
    L28:
        if (r7 == false) goto L31;
        if (r2 == false) goto L31;
        boolean r72 = true;
    L33:
        return r3.messageDone$okhttp(this, r5, r62, r72, r82, r9);
    L31:
        r72 = r1;
    L27:
        r82 = r1;
    L23:
        r2 = r6;
        r62 = r1;
    L19:
        r1 = false;
        goto L20
    }

    public final void cancel() {
        this.codec.cancel();
    }

    public final Sink createRequestBody(Request r7, boolean r8) {
        this.isDuplex = r8;
        long r3 = r7.body().contentLength();
        this.eventListener.requestBodyStart(this.call);
        return new RequestBodySink(this, this.codec.createRequestBody(r7, r3), r3, false);
    }

    public final void detachWithViolence() {
        this.codec.cancel();
        this.call.messageDone$okhttp(this, true, true, true, true, null);
    }

    public final void finishRequest() {
        this.codec.finishRequest();     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        this.eventListener.requestFailed(this.call, e);
        trackFailure(e);
        throw e;
    }

    public final void flushRequest() {
        this.codec.flushRequest();     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        this.eventListener.requestFailed(this.call, e);
        trackFailure(e);
        throw e;
    }

    public final RealCall getCall$okhttp() {
        return this.call;
    }

    public final RealConnection getConnection$okhttp() {
        ExchangeCodec.Carrier r2 = this.codec.getCarrier();
        if ((r2 instanceof RealConnection) == false) goto L5;
        RealConnection r22 = (RealConnection) r2;
    L6:
        if (r22 == null) goto L8;
        return r22;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("no connection for CONNECT tunnels");
        return null;
    L5:
        r22 = null;
        goto L6
    }

    public final EventListener getEventListener$okhttp() {
        return this.eventListener;
    }

    public final ExchangeFinder getFinder$okhttp() {
        return this.finder;
    }

    public final boolean getHasFailure$okhttp() {
        return this.hasFailure;
    }

    public final boolean isCoalescedConnection$okhttp() {
        return !Intrinsics.areEqual(this.finder.getRoutePlanner().getAddress().url().host(), this.codec.getCarrier().getRoute().address().url().host());
    }

    public final boolean isDuplex$okhttp() {
        return this.isDuplex;
    }

    public final void noNewExchangesOnConnection() {
        this.codec.getCarrier().noNewExchanges();
    }

    public final void noRequestBody() {
        RealCall.messageDone$okhttp$default(this.call, this, true, false, false, false, null, 28, null);
    }

    public final ResponseBody openResponseBody(Response r9) {
        String r0 = Response.header$default(r9, "Content-Type", null, 2, null);     // Catch: IOException -> L8
        long r5 = this.codec.reportedContentLength(r9);     // Catch: IOException -> L8
        Exchange r3 = this;
        return new RealResponseBody(r0, r5, Okio.buffer(new ResponseBodySource(r3, this.codec.openResponseBodySource(r9), r5, false)));
    L6:
        e = e;
    L7:
        IOException r8 = e;
        r3.eventListener.responseFailed(r3.call, r8);
        r3.trackFailure(r8);
        throw r8;
    L8:
        e = e;
        r3 = this;
        goto L7
    }

    public final Headers peekTrailers() {
        return this.codec.peekTrailers();
    }

    public final Response.Builder readResponseHeaders(boolean r3) {
        Response.Builder r32 = this.codec.readResponseHeaders(r3);     // Catch: IOException -> L6
        if (r32 == null) goto L8;
        r32.initExchange$okhttp(this);     // Catch: IOException -> L6
        return r32;
    L8:
        return r32;
    L6:
        e = move-exception;
        this.eventListener.responseFailed(this.call, e);
        trackFailure(e);
        throw e;
    }

    public final void responseHeadersEnd(Response r2) {
        this.eventListener.responseHeadersEnd(this.call, r2);
    }

    public final void responseHeadersStart() {
        this.eventListener.responseHeadersStart(this.call);
    }

    public final Socket upgradeToSocket() {
        this.call.upgradeToSocket();
        ((RealConnection) this.codec.getCarrier()).useAsSocket$okhttp();
        return new AnonymousClass1(this);
    }

    public final void writeRequestHeaders(Request r3) {
        this.eventListener.requestHeadersStart(this.call);     // Catch: IOException -> L4
        this.codec.writeRequestHeaders(r3);     // Catch: IOException -> L4
        this.eventListener.requestHeadersEnd(this.call, r3);     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        this.eventListener.requestFailed(this.call, e);
        trackFailure(e);
        throw e;
    }
}
