package okio.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.Buffer$$ExternalSyntheticBUOutline3;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Socket;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lokio/internal/DefaultSocket;", "Lokio/Socket;", "socket", "Ljava/net/Socket;", "<init>", "(Ljava/net/Socket;)V", "getSocket", "()Ljava/net/Socket;", "closeBits", "Ljava/util/concurrent/atomic/AtomicInteger;", "source", "Lokio/Source;", "getSource", "()Lokio/Source;", "sink", "Lokio/Sink;", "getSink", "()Lokio/Sink;", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "SocketSink", "SocketSource", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultSocket implements Socket {
    private AtomicInteger closeBits;
    private final Sink sink;
    private final java.net.Socket socket;
    private final Source source;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lokio/internal/DefaultSocket$SocketSink;", "Lokio/Sink;", "<init>", "(Lokio/internal/DefaultSocket;)V", "outputStream", "Ljava/io/OutputStream;", "kotlin.jvm.PlatformType", "timeout", "Lokio/internal/SocketAsyncTimeout;", "write", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/Buffer;", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "flush", "close", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDefaultSocket.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultSocket.kt\nokio/internal/DefaultSocket$SocketSink\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 AsyncTimeout.kt\nokio/AsyncTimeout\n*L\n1#1,176:1\n85#2:177\n195#3,11:178\n195#3,11:189\n195#3,11:200\n*S KotlinDebug\n*F\n+ 1 DefaultSocket.kt\nokio/internal/DefaultSocket$SocketSink\n*L\n60#1:177\n61#1:178,11\n77#1:189,11\n83#1:200,11\n*E\n"})
    public final class SocketSink implements Sink {
        private final OutputStream outputStream;
        final /* synthetic */ DefaultSocket this$0;
        private final SocketAsyncTimeout timeout;

        public SocketSink(DefaultSocket r2) {
            this.this$0 = r2;
            this.outputStream = r2.getSocket().getOutputStream();
            this.timeout = new SocketAsyncTimeout(r2.getSocket());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            SocketAsyncTimeout r0 = this.timeout;
            DefaultSocket r1 = this.this$0;
            r0.enter();
            int r2 = _AtomicKt.setBitsOrZero(DefaultSocket.access$getCloseBits$p(r1), 1);     // Catch: Throwable -> L15 IOException -> L17
            if (r2 != 0) goto L6;
            r0.exit();
            return;
        L6:
            if (r2 != 3) goto L8;
            r1.getSocket().close();     // Catch: Throwable -> L15 IOException -> L17
        L24:
            Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L15 IOException -> L17
        L26:
            if (r0.exit() == true) goto L29;
            return;
        L29:
            throw r0.access$newTimeoutException(null);
        L8:
            if (r1.getSocket().isClosed() == false) goto L10;
        L21:
            r0.exit();
            return;
        L10:
            if (r1.getSocket().isOutputShutdown() == true) goto L21;
            this.outputStream.flush();     // Catch: Throwable -> L15 IOException -> L17
            r1.getSocket().shutdownOutput();     // Catch: Throwable -> L15 IOException -> L17 UnsupportedOperationException -> L19
        L19:
            this.outputStream.close();     // Catch: Throwable -> L15 IOException -> L17
        L17:
            e = move-exception;
            if (r0.exit() == true) goto L36;
            throw e;     // Catch: Throwable -> L15
        L36:
            throw r0.access$newTimeoutException(e);     // Catch: Throwable -> L15
        L15:
            th = move-exception;
            r0.exit();
            throw th;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            SocketAsyncTimeout r0 = this.timeout;
            r0.enter();
            this.outputStream.flush();     // Catch: Throwable -> L9 IOException -> L11
            Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9 IOException -> L11
        L5:
            if (r0.exit() == true) goto L8;
            return;
        L8:
            throw r0.access$newTimeoutException(null);
        L9:
            th = move-exception;
            r0.exit();
            throw th;
        L11:
            e = move-exception;
            if (r0.exit() == true) goto L16;
            throw e;     // Catch: Throwable -> L9
        L16:
            throw r0.access$newTimeoutException(e);     // Catch: Throwable -> L9
        }

        @Override // okio.Sink
        public /* bridge */ /* synthetic */ Timeout timeout() {
            return timeout();
        }

        @Override // okio.Sink
        public SocketAsyncTimeout timeout() {
            return this.timeout;
        }

        public String toString() {
            return "sink(" + this.this$0.getSocket() + ')';
        }

        @Override // okio.Sink
        public void write(Buffer r7, long r8) {
            SegmentedByteString.checkOffsetAndCount(r7.size(), 0, r8);
        L4:
            if (r8 <= 0) goto L25;
            this.timeout.throwIfReached();
            Segment r0 = r7.head;
            int r1 = (int) Math.min(r8, r0.limit - r0.pos);
            SocketAsyncTimeout r2 = this.timeout;
            r2.enter();
            this.outputStream.write(r0.data, r0.pos, r1);     // Catch: Throwable -> L14 IOException -> L16
            Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L14 IOException -> L16
        L8:
            if (r2.exit() == true) goto L13;
            r0.pos += r1;
            long r12 = r1;
            r8 = r8 - r12;
            r7.setSize$okio(r7.size() - r12);
            if (r0.pos != r0.limit) goto L4;
            r7.head = r0.pop();
            SegmentPool.recycle(r0);
            goto L4
        L13:
            throw r2.access$newTimeoutException(null);
        L16:
            e = move-exception;
            if (r2.exit() == true) goto L22;
            throw e;     // Catch: Throwable -> L14
        L22:
            throw r2.access$newTimeoutException(e);     // Catch: Throwable -> L14
        L14:
            th = move-exception;
            r2.exit();
            throw th;
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokio/internal/DefaultSocket$SocketSource;", "Lokio/Source;", "<init>", "(Lokio/internal/DefaultSocket;)V", "inputStream", "Ljava/io/InputStream;", "kotlin.jvm.PlatformType", "timeout", "Lokio/internal/SocketAsyncTimeout;", "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDefaultSocket.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultSocket.kt\nokio/internal/DefaultSocket$SocketSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n+ 4 AsyncTimeout.kt\nokio/AsyncTimeout\n*L\n1#1,176:1\n1#2:177\n85#3:178\n195#4,11:179\n195#4,11:190\n*S KotlinDebug\n*F\n+ 1 DefaultSocket.kt\nokio/internal/DefaultSocket$SocketSource\n*L\n121#1:178\n123#1:179,11\n144#1:190,11\n*E\n"})
    public final class SocketSource implements Source {
        private final InputStream inputStream;
        final /* synthetic */ DefaultSocket this$0;
        private final SocketAsyncTimeout timeout;

        public SocketSource(DefaultSocket r2) {
            this.this$0 = r2;
            this.inputStream = r2.getSocket().getInputStream();
            this.timeout = new SocketAsyncTimeout(r2.getSocket());
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            SocketAsyncTimeout r0 = this.timeout;
            DefaultSocket r1 = this.this$0;
            r0.enter();
            int r2 = _AtomicKt.setBitsOrZero(DefaultSocket.access$getCloseBits$p(r1), 2);     // Catch: Throwable -> L14 IOException -> L16
            if (r2 != 0) goto L6;
            r0.exit();
            return;
        L6:
            if (r2 != 3) goto L8;
            r1.getSocket().close();     // Catch: Throwable -> L14 IOException -> L16
        L23:
            Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L14 IOException -> L16
        L25:
            if (r0.exit() == true) goto L28;
            return;
        L28:
            throw r0.access$newTimeoutException(null);
        L8:
            if (r1.getSocket().isClosed() == false) goto L10;
        L20:
            r0.exit();
            return;
        L10:
            if (r1.getSocket().isInputShutdown() == true) goto L20;
            r1.getSocket().shutdownInput();     // Catch: Throwable -> L14 IOException -> L16 UnsupportedOperationException -> L18
        L18:
            this.inputStream.close();     // Catch: Throwable -> L14 IOException -> L16
        L14:
            th = move-exception;
            r0.exit();
            throw th;
        L16:
            e = move-exception;
            if (r0.exit() == true) goto L35;
            throw e;     // Catch: Throwable -> L14
        L35:
            throw r0.access$newTimeoutException(e);     // Catch: Throwable -> L14
        }

        @Override // okio.Source
        public long read(Buffer r4, long r5) {
            if (r5 != 0) goto L5;
            return 0;
        L5:
            if (r5 < 0) goto L39;
            this.timeout.throwIfReached();
            Segment r0 = r4.writableSegment$okio(1);
            int r52 = (int) Math.min(r5, 8192 - r0.limit);
            SocketAsyncTimeout r6 = this.timeout;     // Catch: AssertionError -> L33
            r6.enter();     // Catch: AssertionError -> L33
            int r3 = this.inputStream.read(r0.data, r0.limit, r52);     // Catch: Throwable -> L23 IOException -> L25
        L10:
            if (r6.exit() == true) goto L22;
            if (r3 == (-1)) goto L14;
            r0.limit += r3;
            long r02 = r3;
            r4.setSize$okio(r4.size() + r02);
            return r02;
        L14:
            if (r0.pos != r0.limit) goto L45;
            r4.head = r0.pop();
            SegmentPool.recycle(r0);
            return -1;
        L45:
            return -1;
        L22:
            throw r6.access$newTimeoutException(null);     // Catch: AssertionError -> L33
        L23:
            th = move-exception;
            r6.exit();     // Catch: AssertionError -> L33
            throw th;     // Catch: AssertionError -> L33
        L25:
            e = move-exception;
            if (r6.exit() == true) goto L30;
            throw e;     // Catch: Throwable -> L23
        L30:
            throw r6.access$newTimeoutException(e);     // Catch: Throwable -> L23
        L33:
            e = move-exception;
            if (_JavaIoKt.isAndroidGetsocknameError(e) == true) goto L37;
            throw e;
        L37:
            throw new IOException(e);
        L39:
            Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r5);
            return 0;
        }

        @Override // okio.Source
        public /* bridge */ /* synthetic */ Timeout timeout() {
            return timeout();
        }

        @Override // okio.Source
        public SocketAsyncTimeout timeout() {
            return this.timeout;
        }

        public String toString() {
            return "source(" + this.this$0.getSocket() + ')';
        }
    }

    public DefaultSocket(java.net.Socket r1) {
        this.socket = r1;
        this.closeBits = new AtomicInteger();
        this.source = new SocketSource(this);
        this.sink = new SocketSink(this);
    }

    public static final /* synthetic */ AtomicInteger access$getCloseBits$p(DefaultSocket r0) {
        return r0.closeBits;
    }

    @Override // okio.Socket
    public void cancel() {
        this.socket.close();
    }

    @Override // okio.Socket
    public Sink getSink() {
        return this.sink;
    }

    public final java.net.Socket getSocket() {
        return this.socket;
    }

    @Override // okio.Socket
    public Source getSource() {
        return this.source;
    }

    public String toString() {
        return this.socket.toString();
    }
}
