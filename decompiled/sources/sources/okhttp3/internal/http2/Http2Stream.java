package okhttp3.internal.http2;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Headers;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.concurrent.Lockable;
import okhttp3.internal.http2.flowcontrol.WindowCounter;
import okhttp3.internal.url._UrlKt;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import okio.Buffer$$ExternalSyntheticBUOutline3;
import okio.BufferedSource;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Sink;
import okio.Socket;
import okio.Source;
import okio.Timeout;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 b2\u00020\u00012\u00020\u0002:\u0004`abcB3\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010A\u001a\u00020\u000b2\b\b\u0002\u0010B\u001a\u00020\bJ\b\u0010C\u001a\u0004\u0018\u00010\u000bJ$\u0010D\u001a\u00020E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020H0G2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010I\u001a\u00020\bJ\u000e\u0010J\u001a\u00020E2\u0006\u0010K\u001a\u00020\u000bJ\u0006\u0010+\u001a\u00020LJ\u0006\u0010/\u001a\u00020LJ\u0018\u0010M\u001a\u00020E2\u0006\u0010N\u001a\u0002022\b\u00107\u001a\u0004\u0018\u000108J\b\u0010O\u001a\u00020EH\u0016J\u000e\u0010P\u001a\u00020E2\u0006\u00101\u001a\u000202J\u001a\u0010Q\u001a\u00020\b2\u0006\u00101\u001a\u0002022\b\u00107\u001a\u0004\u0018\u000108H\u0002J\u0016\u0010R\u001a\u00020E2\u0006\u0010#\u001a\u00020S2\u0006\u0010T\u001a\u00020\u0004J\u0016\u0010U\u001a\u00020E2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010V\u001a\u00020E2\u0006\u00101\u001a\u000202J\b\u0010W\u001a\u00020\bH\u0002J\r\u0010X\u001a\u00020EH\u0000¢\u0006\u0002\bYJ\u000e\u0010Z\u001a\u00020E2\u0006\u0010[\u001a\u00020\u0017J\r\u0010\\\u001a\u00020EH\u0000¢\u0006\u0002\b]J\r\u0010^\u001a\u00020EH\u0000¢\u0006\u0002\b_R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0017@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0017@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010#\u001a\u00060$R\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0018\u0010'\u001a\u00060(R\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0018\u0010+\u001a\u00060,R\u00020\u0000X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0018\u0010/\u001a\u00060,R\u00020\u0000X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010.R\u001e\u00101\u001a\u0004\u0018\u0001028@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001c\u00107\u001a\u0004\u0018\u000108X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0011\u0010=\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0011\u0010?\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b?\u0010>R\u0011\u0010@\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b@\u0010>¨\u0006d"}, d2 = {"Lokhttp3/internal/http2/Http2Stream;", "Lokhttp3/internal/concurrent/Lockable;", "Lokio/Socket;", "id", _UrlKt.FRAGMENT_ENCODE_SET, "connection", "Lokhttp3/internal/http2/Http2Connection;", "outFinished", _UrlKt.FRAGMENT_ENCODE_SET, "inFinished", "headers", "Lokhttp3/Headers;", "<init>", "(ILokhttp3/internal/http2/Http2Connection;ZZLokhttp3/Headers;)V", "getId", "()I", "getConnection", "()Lokhttp3/internal/http2/Http2Connection;", "readBytes", "Lokhttp3/internal/http2/flowcontrol/WindowCounter;", "getReadBytes", "()Lokhttp3/internal/http2/flowcontrol/WindowCounter;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "writeBytesTotal", "getWriteBytesTotal", "()J", "setWriteBytesTotal$okhttp", "(J)V", "writeBytesMaximum", "getWriteBytesMaximum", "setWriteBytesMaximum$okhttp", "headersQueue", "Ljava/util/ArrayDeque;", "hasResponseHeaders", "source", "Lokhttp3/internal/http2/Http2Stream$FramingSource;", "getSource", "()Lokhttp3/internal/http2/Http2Stream$FramingSource;", "sink", "Lokhttp3/internal/http2/Http2Stream$FramingSink;", "getSink", "()Lokhttp3/internal/http2/Http2Stream$FramingSink;", "readTimeout", "Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "getReadTimeout$okhttp", "()Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "writeTimeout", "getWriteTimeout$okhttp", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "getErrorCode$okhttp", "()Lokhttp3/internal/http2/ErrorCode;", "setErrorCode$okhttp", "(Lokhttp3/internal/http2/ErrorCode;)V", "errorException", "Ljava/io/IOException;", "getErrorException$okhttp", "()Ljava/io/IOException;", "setErrorException$okhttp", "(Ljava/io/IOException;)V", "isOpen", "()Z", "isLocallyInitiated", "isSourceComplete", "takeHeaders", "callerIsIdle", "peekTrailers", "writeHeaders", _UrlKt.FRAGMENT_ENCODE_SET, "responseHeaders", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "flushHeaders", "enqueueTrailers", "trailers", "Lokio/Timeout;", "close", "rstStatusCode", "cancel", "closeLater", "closeInternal", "receiveData", "Lokio/BufferedSource;", "length", "receiveHeaders", "receiveRstStream", "doReadTimeout", "cancelStreamIfNecessary", "cancelStreamIfNecessary$okhttp", "addBytesToWriteWindow", "delta", "checkOutNotClosed", "checkOutNotClosed$okhttp", "waitForIo", "waitForIo$okhttp", "FramingSource", "FramingSink", "Companion", "StreamTimeout", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHttp2Stream.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,743:1\n1#2:744\n63#3:745\n63#3:746\n63#3:747\n63#3:748\n63#3:749\n49#3,4:750\n63#3:754\n38#3:755\n63#3:756\n63#3:757\n49#3,4:758\n63#3:762\n38#3:763\n49#3,4:764\n49#3,4:768\n63#3:772\n38#3:773\n63#3:774\n38#3:775\n49#3,4:776\n63#3:780\n38#3:781\n34#3:782\n*S KotlinDebug\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream\n*L\n89#1:745\n114#1:746\n136#1:747\n149#1:748\n175#1:749\n200#1:750,4\n203#1:754\n207#1:755\n214#1:756\n227#1:757\n272#1:758,4\n274#1:762\n280#1:763\n294#1:764,4\n304#1:768,4\n307#1:772\n321#1:773\n329#1:774\n332#1:775\n543#1:776,4\n547#1:780\n693#1:781\n713#1:782\n*E\n"})
/* loaded from: classes.dex */
public final class Http2Stream implements Lockable, Socket {
    public static final Companion Companion = null;
    public static final long EMIT_BUFFER_SIZE = 16384;
    private final Http2Connection connection;
    private ErrorCode errorCode;
    private IOException errorException;
    private boolean hasResponseHeaders;
    private final ArrayDeque<Headers> headersQueue;
    private final int id;
    private final WindowCounter readBytes;
    private final StreamTimeout readTimeout;
    private final FramingSink sink;
    private final FramingSource source;
    private long writeBytesMaximum;
    private long writeBytesTotal;
    private final StreamTimeout writeTimeout;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EMIT_BUFFER_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0003H\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0007\"\u0004\b\u0014\u0010\t¨\u0006 "}, d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSink;", "Lokio/Sink;", "finished", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/http2/Http2Stream;Z)V", "getFinished", "()Z", "setFinished", "(Z)V", "sendBuffer", "Lokio/Buffer;", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "closed", "getClosed", "setClosed", "write", _UrlKt.FRAGMENT_ENCODE_SET, "source", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "emitFrame", "outFinishedOnLastFrame", "flush", "timeout", "Lokio/Timeout;", "close", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttp2Stream.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream$FramingSink\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,743:1\n49#2,4:744\n63#2:748\n49#2,4:749\n63#2:753\n49#2,4:754\n63#2:758\n63#2:759\n38#2:760\n*S KotlinDebug\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream$FramingSink\n*L\n582#1:744,4\n598#1:748\n628#1:749,4\n630#1:753\n644#1:754,4\n647#1:758\n676#1:759\n678#1:760\n*E\n"})
    public final class FramingSink implements Sink {
        private boolean closed;
        private boolean finished;
        private final Buffer sendBuffer;
        final /* synthetic */ Http2Stream this$0;
        private Headers trailers;

        public FramingSink(Http2Stream r1, boolean r2) {
            this.this$0 = r1;
            this.finished = r2;
            this.sendBuffer = new Buffer();
        }

        public /* synthetic */ FramingSink(Http2Stream r1, boolean r2, int r3, DefaultConstructorMarker r4) {
            if ((r3 & 1) == 0) goto L5;
            r2 = false;
        L5:
            this(r1, r2);
        }

        private final void emitFrame(boolean r13) {
            Http2Stream r1 = this.this$0;
            monitor-enter(r1);
            r1.getWriteTimeout$okhttp().enter();     // Catch: Throwable -> L23
        L39:
            if (r1.getWriteBytesTotal() < r1.getWriteBytesMaximum()) goto L17;
            if (this.finished == true) goto L17;
            if (this.closed == true) goto L17;
            if (r1.getErrorCode$okhttp() != null) goto L17;
            r1.waitForIo$okhttp();     // Catch: Throwable -> L15
        L17:
            r1.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();     // Catch: Throwable -> L23
            r1.checkOutNotClosed$okhttp();     // Catch: Throwable -> L23
            long r10 = Math.min(r1.getWriteBytesMaximum() - r1.getWriteBytesTotal(), this.sendBuffer.size());     // Catch: Throwable -> L23
            r1.setWriteBytesTotal$okhttp(r1.getWriteBytesTotal() + r10);     // Catch: Throwable -> L23
            if (r13 == true) goto L20;
        L25:
            boolean r132 = false;
        L22:
            boolean r8 = r132;
            Unit r133 = Unit.INSTANCE;     // Catch: Throwable -> L23
            monitor-exit(r1);
            this.this$0.getWriteTimeout$okhttp().enter();
            this.this$0.getConnection().writeData(this.this$0.getId(), r8, this.sendBuffer, r10);     // Catch: Throwable -> L32
            this.this$0.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
            return;
        L32:
            th = move-exception;
            this.this$0.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
            throw th;
        L20:
            if (r10 != this.sendBuffer.size()) goto L25;
            r132 = true;
            goto L22
        L15:
            th = move-exception;
            r1.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();     // Catch: Throwable -> L23
            throw th;     // Catch: Throwable -> L23
        L23:
            th = move-exception;
            throw th;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Http2Stream r0 = this.this$0;
            if (_UtilJvmKt.assertionsEnabled == true) goto L5;
        L9:
            Http2Stream r1 = this.this$0;
            monitor-enter(r1);
        L49:
            th = move-exception;
            throw th;
        L12:
            if (this.closed == false) goto L16;
            monitor-exit(r1);
            return;
        L16:
            if (r1.getErrorCode$okhttp() != null) goto L18;
            boolean r02 = true;
        L19:
            Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L49
            monitor-exit(r1);
            if (this.this$0.getSink().finished == false) goto L24;
        L39:
            Http2Stream r12 = this.this$0;
            monitor-enter(r12);
            this.closed = true;     // Catch: Throwable -> L45
            r12.notifyAll();     // Catch: Throwable -> L45
            Unit r03 = Unit.INSTANCE;     // Catch: Throwable -> L45
            monitor-exit(r12);
            this.this$0.getConnection().flush();
            this.this$0.cancelStreamIfNecessary$okhttp();
            return;
        L45:
            th = move-exception;
            throw th;
        L24:
            if (this.sendBuffer.size() <= 0) goto L26;
            boolean r13 = true;
        L28:
            if (this.trailers != null) goto L30;
            if (r13 == true) goto L35;
            if (r02 == false) goto L39;
            this.this$0.getConnection().writeData(this.this$0.getId(), true, null, 0);
        L35:
            if (this.sendBuffer.size() <= 0) goto L39;
            emitFrame(true);
        L30:
            if (this.sendBuffer.size() <= 0) goto L32;
            emitFrame(false);
            goto L30
        L32:
            this.this$0.getConnection().writeHeaders$okhttp(this.this$0.getId(), r02, _UtilJvmKt.toHeaderList(this.trailers));
            goto L39
        L26:
            r13 = false;
            goto L28
        L18:
            r02 = false;
            goto L19
        L5:
            if (Thread.holdsLock(r0) == false) goto L9;
            _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r0);
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            Http2Stream r0 = this.this$0;
            if (_UtilJvmKt.assertionsEnabled == true) goto L5;
        L9:
            Http2Stream r02 = this.this$0;
            monitor-enter(r02);
            r02.checkOutNotClosed$okhttp();     // Catch: Throwable -> L17
            Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L17
            monitor-exit(r02);
        L14:
            if (this.sendBuffer.size() <= 0) goto L16;
            emitFrame(false);
            this.this$0.getConnection().flush();
            goto L14
        L16:
            return;
        L17:
            th = move-exception;
            throw th;
        L5:
            if (Thread.holdsLock(r0) == false) goto L9;
            _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r0);
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final boolean getFinished() {
            return this.finished;
        }

        public final Headers getTrailers() {
            return this.trailers;
        }

        public final void setClosed(boolean r1) {
            this.closed = r1;
        }

        public final void setFinished(boolean r1) {
            this.finished = r1;
        }

        public final void setTrailers(Headers r1) {
            this.trailers = r1;
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.this$0.getWriteTimeout$okhttp();
        }

        @Override // okio.Sink
        public void write(Buffer r3, long r4) {
            Http2Stream r0 = this.this$0;
            if (_UtilJvmKt.assertionsEnabled == true) goto L5;
        L9:
            this.sendBuffer.write(r3, r4);
        L11:
            if (this.sendBuffer.size() < 16384) goto L13;
            emitFrame(false);
            goto L11
        L13:
            return;
        L5:
            if (Thread.holdsLock(r0) == false) goto L9;
            _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r0);
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0003H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u0003H\u0002J\u001d\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u0003H\u0000¢\u0006\u0002\b#J\b\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u001fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000b¨\u0006'"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSource;", "Lokio/Source;", "maxByteCount", _UrlKt.FRAGMENT_ENCODE_SET, "finished", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/http2/Http2Stream;JZ)V", "getFinished$okhttp", "()Z", "setFinished$okhttp", "(Z)V", "receiveBuffer", "Lokio/Buffer;", "getReceiveBuffer", "()Lokio/Buffer;", "readBuffer", "getReadBuffer", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "closed", "getClosed$okhttp", "setClosed$okhttp", "read", "sink", "byteCount", "updateConnectionFlowControl", _UrlKt.FRAGMENT_ENCODE_SET, "receive", "source", "Lokio/BufferedSource;", "receive$okhttp", "timeout", "Lokio/Timeout;", "close", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHttp2Stream.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream$FramingSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,743:1\n1#2:744\n63#3:745\n49#3,4:746\n49#3,4:750\n63#3:754\n63#3:755\n38#3:756\n63#3:757\n38#3:758\n*S KotlinDebug\n*F\n+ 1 Http2Stream.kt\nokhttp3/internal/http2/Http2Stream$FramingSource\n*L\n390#1:745\n453#1:746,4\n467#1:750,4\n474#1:754\n500#1:755\n507#1:756\n528#1:757\n532#1:758\n*E\n"})
    public final class FramingSource implements Source {
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer;
        private final Buffer receiveBuffer;
        final /* synthetic */ Http2Stream this$0;
        private Headers trailers;

        public FramingSource(Http2Stream r1, long r2, boolean r4) {
            this.this$0 = r1;
            this.maxByteCount = r2;
            this.finished = r4;
            this.receiveBuffer = new Buffer();
            this.readBuffer = new Buffer();
        }

        private final void updateConnectionFlowControl(long r3) {
            Http2Stream r0 = this.this$0;
            if (_UtilJvmKt.assertionsEnabled == true) goto L5;
        L9:
            this.this$0.getConnection().updateConnectionFlowControl$okhttp(r3);
            return;
        L5:
            if (Thread.holdsLock(r0) == false) goto L9;
            _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r0);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Http2Stream r0 = this.this$0;
            monitor-enter(r0);
            this.closed = true;     // Catch: Throwable -> L12
            long r1 = this.readBuffer.size();     // Catch: Throwable -> L12
            this.readBuffer.clear();     // Catch: Throwable -> L12
            r0.notifyAll();     // Catch: Throwable -> L12
            Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L12
            monitor-exit(r0);
            if (r1 <= 0) goto L10;
            updateConnectionFlowControl(r1);
        L10:
            this.this$0.cancelStreamIfNecessary$okhttp();
            return;
        L12:
            th = move-exception;
            throw th;
        }

        public final boolean getClosed$okhttp() {
            return this.closed;
        }

        public final boolean getFinished$okhttp() {
            return this.finished;
        }

        public final Buffer getReadBuffer() {
            return this.readBuffer;
        }

        public final Buffer getReceiveBuffer() {
            return this.receiveBuffer;
        }

        public final Headers getTrailers() {
            return this.trailers;
        }

        @Override // okio.Source
        public long read(Buffer r28, long r29) {
            long r3 = 0;
            if (r29 < 0) goto L58;
        L4:
            Http2Stream r5 = this.this$0;
            monitor-enter(r5);
            boolean r6 = Http2Stream.access$doReadTimeout(r5);     // Catch: Throwable -> L10
            if (r6 == false) goto L60;
            r5.getReadTimeout$okhttp().enter();     // Catch: Throwable -> L10
        L60:
        L19:
            th = move-exception;
            if (r6 == false) goto L55;
            r5.getReadTimeout$okhttp().exitAndThrowIfTimedOut();     // Catch: Throwable -> L10
        L55:
            throw th;     // Catch: Throwable -> L10
        L13:
            if (r5.getErrorCode$okhttp() != null) goto L15;
        L21:
            IOException r7 = null;
        L23:
            if (this.closed == true) goto L52;
            boolean r11 = false;
            if (this.readBuffer.size() <= r3) goto L32;
            Buffer r8 = this.readBuffer;     // Catch: Throwable -> L19
            long r16 = r8.read(r28, Math.min(r29, r8.size()));     // Catch: Throwable -> L19
            WindowCounter.update$default(r5.getReadBytes(), r16, 0, 2, null);     // Catch: Throwable -> L19
            long r12 = r5.getReadBytes().getUnacknowledged();     // Catch: Throwable -> L19
            if (r7 != null) goto L31;
            long r25 = r3;
            if (r12 < (r5.getConnection().getOkHttpSettings().getInitialWindowSize() / 2)) goto L38;
            r5.getConnection().writeWindowUpdateLater$okhttp(r5.getId(), r12);     // Catch: Throwable -> L19
            WindowCounter.update$default(r5.getReadBytes(), 0, r12, 1, null);     // Catch: Throwable -> L19
        L38:
            if (r6 == false) goto L40;
            r5.getReadTimeout$okhttp().exitAndThrowIfTimedOut();     // Catch: Throwable -> L10
        L40:
            Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L10
            monitor-exit(r5);
            this.this$0.getConnection().getFlowControlListener$okhttp().receivingStreamWindowChanged(this.this$0.getId(), this.this$0.getReadBytes(), this.readBuffer.size());
            if (r11 == false) goto L46;
            r3 = r25;
            goto L4
        L46:
            if (r16 == (-1)) goto L48;
            return r16;
        L48:
            if (r7 != null) goto L50;
            return -1;
        L50:
            throw r7;
        L31:
            r25 = r3;
            goto L38
        L32:
            r25 = r3;
            if (this.finished == true) goto L37;
            if (r7 != null) goto L37;
            r5.waitForIo$okhttp();     // Catch: Throwable -> L19
            r11 = true;
        L37:
            r16 = -1;
            goto L38
        L52:
            throw new IOException("stream closed");     // Catch: Throwable -> L19
        L15:
            if (this.finished == true) goto L21;
            r7 = r5.getErrorException$okhttp();     // Catch: Throwable -> L19
            if (r7 != null) goto L23;
            r7 = new StreamResetException(r5.getErrorCode$okhttp());     // Catch: Throwable -> L19
        L10:
            th = move-exception;
            throw th;
        L58:
            Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r29);
            return 0;
        }

        public final void receive$okhttp(BufferedSource r12, long r13) {
            Http2Stream r0 = this.this$0;
            if (_UtilJvmKt.assertionsEnabled == true) goto L5;
        L9:
            long r02 = r13;
        L11:
            if (r02 <= 0) goto L50;
            Http2Stream r4 = this.this$0;
            monitor-enter(r4);
            boolean r5 = this.finished;     // Catch: Throwable -> L47
            boolean r7 = false;
            if ((this.readBuffer.size() + r02) <= this.maxByteCount) goto L17;
            boolean r6 = true;
        L18:
            Unit r9 = Unit.INSTANCE;     // Catch: Throwable -> L47
            monitor-exit(r4);
            if (r6 == true) goto L21;
            if (r5 == true) goto L24;
            long r42 = r12.read(this.receiveBuffer, r02);
            if (r42 == (-1)) goto L45;
            r02 = r02 - r42;
            Http2Stream r43 = this.this$0;
            monitor-enter(r43);
            if (this.closed == false) goto L36;
            this.receiveBuffer.clear();     // Catch: Throwable -> L33
        L41:
            monitor-exit(r43);
            goto L11
        L36:
            if (this.readBuffer.size() != 0) goto L38;
            r7 = true;
        L38:
            this.readBuffer.writeAll(this.receiveBuffer);     // Catch: Throwable -> L33
            if (r7 == false) goto L41;
            r43.notifyAll();     // Catch: Throwable -> L33
        L33:
            th = move-exception;
            throw th;
        L45:
            Buffer$$ExternalSyntheticBUOutline1.m();
            return;
        L24:
            r12.skip(r02);
            return;
        L21:
            r12.skip(r02);
            this.this$0.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
            return;
        L17:
            r6 = false;
        L47:
            th = move-exception;
            throw th;
        L50:
            updateConnectionFlowControl(r13);
            this.this$0.getConnection().getFlowControlListener$okhttp().receivingStreamWindowChanged(this.this$0.getId(), this.this$0.getReadBytes(), this.readBuffer.size());
            return;
        L5:
            if (Thread.holdsLock(r0) == false) goto L9;
            _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r0);
        }

        public final void setClosed$okhttp(boolean r1) {
            this.closed = r1;
        }

        public final void setFinished$okhttp(boolean r1) {
            this.finished = r1;
        }

        public final void setTrailers(Headers r1) {
            this.trailers = r1;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.this$0.getReadTimeout$okhttp();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0014J\u0006\u0010\t\u001a\u00020\u0005¨\u0006\n"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "Lokio/AsyncTimeout;", "<init>", "(Lokhttp3/internal/http2/Http2Stream;)V", "timedOut", _UrlKt.FRAGMENT_ENCODE_SET, "newTimeoutException", "Ljava/io/IOException;", "cause", "exitAndThrowIfTimedOut", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class StreamTimeout extends AsyncTimeout {
        final /* synthetic */ Http2Stream this$0;

        public StreamTimeout(Http2Stream r1) {
            this.this$0 = r1;
        }

        public final void exitAndThrowIfTimedOut() {
            if (exit() == true) goto L6;
            return;
        L6:
            throw newTimeoutException(null);
        }

        @Override // okio.AsyncTimeout
        public IOException newTimeoutException(IOException r2) {
            SocketTimeoutException r1 = new SocketTimeoutException("timeout");
            if (r2 == null) goto L5;
            r1.initCause(r2);
        L5:
            return r1;
        }

        @Override // okio.AsyncTimeout
        public void timedOut() {
            this.this$0.closeLater(ErrorCode.CANCEL);
            this.this$0.getConnection().sendDegradedPingLater$okhttp();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public Http2Stream(int r4, Http2Connection r5, boolean r6, boolean r7, Headers r8) {
        this.id = r4;
        this.connection = r5;
        this.readBytes = new WindowCounter(r4);
        this.writeBytesMaximum = r5.getPeerSettings().getInitialWindowSize();
        ArrayDeque<Headers> r42 = new ArrayDeque();
        this.headersQueue = r42;
        this.source = new FramingSource(this, r5.getOkHttpSettings().getInitialWindowSize(), r7);
        this.sink = new FramingSink(this, r6);
        this.readTimeout = new StreamTimeout(this);
        this.writeTimeout = new StreamTimeout(this);
        if (r8 == null) goto L11;
        if (isLocallyInitiated() == true) goto L8;
        r42.add(r8);
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("locally-initiated streams shouldn't have headers yet");
        throw null;
    L11:
        if (isLocallyInitiated() == false) goto L13;
        return;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("remotely-initiated streams should have headers");
        throw null;
    }

    public static final /* synthetic */ boolean access$doReadTimeout(Http2Stream r0) {
        return r0.doReadTimeout();
    }

    private final boolean closeInternal(ErrorCode r3, IOException r4) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        monitor-enter(this);
    L20:
        th = move-exception;
        throw th;
    L11:
        if (getErrorCode$okhttp() == null) goto L14;
        monitor-exit(this);
        return false;
    L14:
        this.errorCode = r3;     // Catch: Throwable -> L20
        this.errorException = r4;     // Catch: Throwable -> L20
        notifyAll();     // Catch: Throwable -> L20
        if (getSource().getFinished$okhttp() == true) goto L17;
    L22:
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L20
        monitor-exit(this);
        this.connection.removeStream$okhttp(this.id);
        return true;
    L17:
        if (getSink().getFinished() == false) goto L22;
        monitor-exit(this);
        return false;
    L5:
        if (Thread.holdsLock(this) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", this);
        return false;
    }

    private final boolean doReadTimeout() {
        if (this.connection.getClient$okhttp() == true) goto L5;
        return true;
    L5:
        if (getSink().getClosed() == false) goto L7;
        return true;
    L7:
        if (getSink().getFinished() == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static /* synthetic */ Headers takeHeaders$default(Http2Stream r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = false;
    L6:
        return r0.takeHeaders(r1);
    }

    public final void addBytesToWriteWindow(long r3) {
        this.writeBytesMaximum += r3;
        if (r3 <= 0) goto L6;
        notifyAll();
        return;
    }

    @Override // okio.Socket
    public void cancel() {
        closeLater(ErrorCode.CANCEL);
    }

    public final void cancelStreamIfNecessary$okhttp() {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        monitor-enter(this);
    L19:
        th = move-exception;
        throw th;
    L11:
        if (getSource().getFinished$okhttp() == false) goto L13;
    L22:
        boolean r0 = false;
    L23:
        boolean r1 = isOpen();     // Catch: Throwable -> L19
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L19
        monitor-exit(this);
        if (r0 == false) goto L28;
        close(ErrorCode.CANCEL, null);
        return;
    L28:
        if (r1 == true) goto L35;
        this.connection.removeStream$okhttp(this.id);
        return;
    L35:
        return;
    L13:
        if (getSource().getClosed$okhttp() == false) goto L22;
        if (getSink().getFinished() == false) goto L17;
    L21:
        r0 = true;
        goto L23
    L17:
        if (getSink().getClosed() == false) goto L22;
    L5:
        if (Thread.holdsLock(this) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", this);
    }

    public final void checkOutNotClosed$okhttp() {
        if (getSink().getClosed() == false) goto L5;
        Model$$ExternalSyntheticBUOutline0.m("stream closed");
        return;
    L5:
        if (getSink().getFinished() == false) goto L7;
        Model$$ExternalSyntheticBUOutline0.m("stream finished");
        return;
    L7:
        if (getErrorCode$okhttp() == null) goto L13;
        IOException r0 = this.errorException;
        if (r0 == null) goto L12;
        throw r0;
    L12:
        throw new StreamResetException(getErrorCode$okhttp());
    }

    public final void close(ErrorCode r1, IOException r2) {
        if (closeInternal(r1, r2) == true) goto L5;
        return;
    L5:
        this.connection.writeSynReset$okhttp(this.id, r1);
    }

    public final void closeLater(ErrorCode r2) {
        if (closeInternal(r2, null) == true) goto L5;
        return;
    L5:
        this.connection.writeSynResetLater$okhttp(this.id, r2);
    }

    public final void enqueueTrailers(Headers r2) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (getSink().getFinished() == true) goto L15;
        if (r2.size() == 0) goto L13;
        getSink().setTrailers(r2);     // Catch: Throwable -> L10
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L13:
        throw new IllegalArgumentException("trailers.size() == 0");     // Catch: Throwable -> L10
    L15:
        throw new IllegalStateException("already finished");     // Catch: Throwable -> L10
    }

    public final Http2Connection getConnection() {
        return this.connection;
    }

    public final ErrorCode getErrorCode$okhttp() {
        monitor-enter(this);
        ErrorCode r0 = this.errorCode;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final IOException getErrorException$okhttp() {
        return this.errorException;
    }

    public final int getId() {
        return this.id;
    }

    public final WindowCounter getReadBytes() {
        return this.readBytes;
    }

    public final StreamTimeout getReadTimeout$okhttp() {
        return this.readTimeout;
    }

    @Override // okio.Socket
    public FramingSink getSink() {
        return this.sink;
    }

    @Override // okio.Socket
    public /* bridge */ /* synthetic */ Sink getSink() {
        return getSink();
    }

    @Override // okio.Socket
    public FramingSource getSource() {
        return this.source;
    }

    @Override // okio.Socket
    public /* bridge */ /* synthetic */ Source getSource() {
        return getSource();
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final StreamTimeout getWriteTimeout$okhttp() {
        return this.writeTimeout;
    }

    public final boolean isLocallyInitiated() {
        if ((this.id & 1) != 1) goto L5;
        boolean r0 = true;
    L7:
        if (this.connection.getClient$okhttp() != r0) goto L9;
        return true;
    L9:
        return false;
    L5:
        r0 = false;
        goto L7
    }

    public final boolean isOpen() {
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (getErrorCode$okhttp() == null) goto L9;
        monitor-exit(this);
        return false;
    L9:
        if (getSource().getFinished$okhttp() == true) goto L16;
        if (getSource().getClosed$okhttp() == true) goto L16;
    L23:
        monitor-exit(this);
        return true;
    L16:
        if (getSink().getFinished() == false) goto L18;
    L19:
        if (this.hasResponseHeaders == false) goto L23;
        monitor-exit(this);
        return false;
    L18:
        if (getSink().getClosed() == false) goto L23;
        goto L19
    }

    public final boolean isSourceComplete() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (getSource().getFinished$okhttp() == false) goto L10;
        if (getSource().getReadBuffer().exhausted() == false) goto L10;
        boolean r0 = true;
    L11:
        monitor-exit(this);
        return r0;
    L10:
        r0 = false;
        goto L11
    }

    public final Headers peekTrailers() {
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L4:
        if (getSource().getFinished$okhttp() == false) goto L18;
        if (getSource().getReceiveBuffer().exhausted() == false) goto L18;
        if (getSource().getReadBuffer().exhausted() == false) goto L18;
        Headers r0 = getSource().getTrailers();     // Catch: Throwable -> L13
        if (r0 != null) goto L15;
        r0 = Headers.EMPTY;     // Catch: Throwable -> L13
    L15:
        monitor-exit(this);
        return r0;
    L18:
        if (getErrorCode$okhttp() == null) goto L24;
        IOException r02 = this.errorException;     // Catch: Throwable -> L13
        if (r02 == null) goto L23;
        throw r02;     // Catch: Throwable -> L13
    L23:
        throw new StreamResetException(getErrorCode$okhttp());     // Catch: Throwable -> L13
    L24:
        monitor-exit(this);
        return null;
    }

    public final Timeout readTimeout() {
        return this.readTimeout;
    }

    public final void receiveData(BufferedSource r3, int r4) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        getSource().receive$okhttp(r3, r4);
        return;
    L5:
        if (Thread.holdsLock(this) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", this);
    }

    public final void receiveHeaders(Headers r3, boolean r4) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        monitor-enter(this);
    L18:
        th = move-exception;
        throw th;
    L11:
        if (this.hasResponseHeaders == true) goto L13;
    L20:
        this.hasResponseHeaders = true;     // Catch: Throwable -> L18
        this.headersQueue.add(r3);     // Catch: Throwable -> L18
    L21:
        if (r4 == false) goto L23;
        getSource().setFinished$okhttp(true);     // Catch: Throwable -> L18
    L23:
        boolean r32 = isOpen();     // Catch: Throwable -> L18
        notifyAll();     // Catch: Throwable -> L18
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L18
        monitor-exit(this);
        if (r32 == true) goto L32;
        this.connection.removeStream$okhttp(this.id);
        return;
    L32:
        return;
    L13:
        if (r3.get(Header.RESPONSE_STATUS_UTF8) != null) goto L20;
        if (r3.get(Header.TARGET_METHOD_UTF8) != null) goto L20;
        getSource().setTrailers(r3);     // Catch: Throwable -> L18
        goto L21
    L5:
        if (Thread.holdsLock(this) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", this);
    }

    public final void receiveRstStream(ErrorCode r2) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (getErrorCode$okhttp() != null) goto L8;
        this.errorCode = r2;     // Catch: Throwable -> L6
        notifyAll();     // Catch: Throwable -> L6
    L8:
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L6
        monitor-exit(this);
    }

    public final void setErrorCode$okhttp(ErrorCode r1) {
        this.errorCode = r1;
    }

    public final void setErrorException$okhttp(IOException r1) {
        this.errorException = r1;
    }

    public final void setWriteBytesMaximum$okhttp(long r1) {
        this.writeBytesMaximum = r1;
    }

    public final void setWriteBytesTotal$okhttp(long r1) {
        this.writeBytesTotal = r1;
    }

    public final Headers takeHeaders(boolean r3) {
        monitor-enter(this);
    L36:
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.headersQueue.isEmpty() == false) goto L25;
        if (getErrorCode$okhttp() != null) goto L25;
        if (r3 == false) goto L9;
    L14:
        boolean r0 = true;
    L15:
        if (r0 == false) goto L37;
        this.readTimeout.enter();     // Catch: Throwable -> L12
    L37:
        waitForIo$okhttp();     // Catch: Throwable -> L20
        if (r0 == false) goto L36;
        this.readTimeout.exitAndThrowIfTimedOut();     // Catch: Throwable -> L12
        goto L36
    L20:
        th = move-exception;
        if (r0 == false) goto L23;
        this.readTimeout.exitAndThrowIfTimedOut();     // Catch: Throwable -> L12
    L23:
        throw th;     // Catch: Throwable -> L12
    L9:
        if (doReadTimeout() == true) goto L14;
        r0 = false;
    L25:
        if (this.headersQueue.isEmpty() == true) goto L29;
        Headers r32 = this.headersQueue.removeFirst();     // Catch: Throwable -> L12
        monitor-exit(this);
        return r32;
    L29:
        IOException r33 = this.errorException;     // Catch: Throwable -> L12
        if (r33 == null) goto L33;
        throw r33;     // Catch: Throwable -> L12
    L33:
        throw new StreamResetException(getErrorCode$okhttp());     // Catch: Throwable -> L12
    }

    public final void waitForIo$okhttp() {
        wait();     // Catch: InterruptedException -> L4
        return;
    L4:
        Thread.currentThread().interrupt();
        throw new InterruptedIOException();
    }

    public final void writeHeaders(List<Header> r6, boolean r7, boolean r8) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        monitor-enter(this);
        this.hasResponseHeaders = true;     // Catch: Throwable -> L14
        if (r7 == false) goto L16;
        getSink().setFinished(true);     // Catch: Throwable -> L14
        notifyAll();     // Catch: Throwable -> L14
    L16:
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(this);
        if (r8 == true) goto L30;
        monitor-enter(this);
        if (this.connection.getWriteBytesTotal() < this.connection.getWriteBytesMaximum()) goto L24;
        r8 = true;
    L25:
        monitor-exit(this);
        goto L30
    L24:
        r8 = false;
    L27:
        th = move-exception;
        throw th;
    L30:
        this.connection.writeHeaders$okhttp(this.id, r7, r6);
        if (r8 == false) goto L40;
        this.connection.flush();
        return;
    L40:
        return;
    L14:
        th = move-exception;
        throw th;
    L5:
        if (Thread.holdsLock(this) == false) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", this);
    }

    public final Timeout writeTimeout() {
        return this.writeTimeout;
    }
}
