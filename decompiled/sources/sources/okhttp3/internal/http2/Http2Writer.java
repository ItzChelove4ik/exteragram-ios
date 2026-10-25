package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.Lockable;
import okhttp3.internal.http2.Hpack;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.Utf8$$ExternalSyntheticBUOutline0;
import okio.Utf8$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\u0018\u0000 <2\u00020\u00012\u00020\u0002:\u0001<B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J$\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0006\u0010\u001d\u001a\u00020\u0013J\u0016\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\fJ(\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010\n2\u0006\u0010%\u001a\u00020\fJ(\u0010&\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010\n2\u0006\u0010%\u001a\u00020\fJ\u000e\u0010)\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u0016J\u001e\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\fJ\u001e\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u00100\u001a\u000201J\u0016\u00102\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\f2\u0006\u00103\u001a\u000204J&\u00105\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\f2\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\f2\u0006\u0010'\u001a\u00020\fJ\b\u00108\u001a\u00020\u0013H\u0016J\u0018\u00109\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010%\u001a\u000204H\u0002J$\u0010:\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\f2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006="}, d2 = {"Lokhttp3/internal/http2/Http2Writer;", "Ljava/io/Closeable;", "Lokhttp3/internal/concurrent/Lockable;", "sink", "Lokio/BufferedSink;", "client", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokio/BufferedSink;Z)V", "hpackBuffer", "Lokio/Buffer;", "maxFrameSize", _UrlKt.FRAGMENT_ENCODE_SET, "closed", "hpackWriter", "Lokhttp3/internal/http2/Hpack$Writer;", "getHpackWriter", "()Lokhttp3/internal/http2/Hpack$Writer;", "connectionPreface", _UrlKt.FRAGMENT_ENCODE_SET, "applyAndAckSettings", "peerSettings", "Lokhttp3/internal/http2/Settings;", "pushPromise", "streamId", "promisedStreamId", "requestHeaders", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "flush", "rstStream", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "maxDataLength", "data", "outFinished", "source", "byteCount", "dataFrame", "flags", "buffer", "settings", "ping", "ack", "payload1", "payload2", "goAway", "lastGoodStreamId", "debugData", _UrlKt.FRAGMENT_ENCODE_SET, "windowUpdate", "windowSizeIncrement", _UrlKt.FRAGMENT_ENCODE_SET, "frameHeader", "length", "type", "close", "writeContinuationFrames", "headers", "headerBlock", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHttp2Writer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http2Writer.kt\nokhttp3/internal/http2/Http2Writer\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,384:1\n63#2:385\n63#2:386\n63#2:387\n63#2:388\n63#2:389\n63#2:390\n63#2:391\n63#2:392\n63#2:393\n63#2:395\n63#2:396\n63#2:397\n1#3:394\n*S KotlinDebug\n*F\n+ 1 Http2Writer.kt\nokhttp3/internal/http2/Http2Writer\n*L\n60#1:385\n74#1:386\n108#1:387\n129#1:388\n140#1:389\n172#1:390\n201#1:391\n228#1:392\n256#1:393\n283#1:395\n329#1:396\n360#1:397\n*E\n"})
/* loaded from: classes.dex */
public final class Http2Writer implements Closeable, Lockable {
    public static final Companion Companion = null;
    private static final Logger logger = null;
    private final boolean client;
    private boolean closed;
    private final Buffer hpackBuffer;
    private final Hpack.Writer hpackWriter;
    private int maxFrameSize;
    private final BufferedSink sink;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/http2/Http2Writer$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        logger = Logger.getLogger(Http2.class.getName());
    }

    public Http2Writer(BufferedSink r7, boolean r8) {
        this.sink = r7;
        this.client = r8;
        Buffer r3 = new Buffer();
        this.hpackBuffer = r3;
        this.maxFrameSize = 16384;
        this.hpackWriter = new Hpack.Writer(0, false, r3, 3, null);
    }

    private final void writeContinuationFrames(int r6, long r7) {
    L3:
        if (r7 <= 0) goto L9;
        long r2 = Math.min(this.maxFrameSize, r7);
        r7 = r7 - r2;
        int r4 = (int) r2;
        if (r7 != 0) goto L7;
        int r0 = 4;
    L8:
        frameHeader(r6, r4, 9, r0);
        this.sink.write(this.hpackBuffer, r2);
        goto L3
    L7:
        r0 = 0;
        goto L8
    }

    public final void applyAndAckSettings(Settings r3) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L14;
        this.maxFrameSize = r3.getMaxFrameSize(this.maxFrameSize);     // Catch: Throwable -> L8
        if (r3.getHeaderTableSize() == (-1)) goto L10;
        this.hpackWriter.resizeHeaderTable(r3.getHeaderTableSize());     // Catch: Throwable -> L8
    L10:
        frameHeader(0, 0, 4, 1);     // Catch: Throwable -> L8
        this.sink.flush();     // Catch: Throwable -> L8
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L14:
        throw new IOException("closed");     // Catch: Throwable -> L8
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        monitor-enter(this);
        this.closed = true;     // Catch: Throwable -> L7
        this.sink.close();     // Catch: Throwable -> L7
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void connectionPreface() {
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == true) goto L19;
        if (this.client == true) goto L10;
        monitor-exit(this);
        return;
    L10:
        Logger r1 = logger;     // Catch: Throwable -> L13
        if (r1.isLoggable(Level.FINE) == false) goto L15;
        r1.fine(_UtilJvmKt.format(">> CONNECTION " + Http2.CONNECTION_PREFACE.hex(), new Object[0]));     // Catch: Throwable -> L13
    L15:
        this.sink.write(Http2.CONNECTION_PREFACE);     // Catch: Throwable -> L13
        this.sink.flush();     // Catch: Throwable -> L13
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(this);
        return;
    L19:
        throw new IOException("closed");     // Catch: Throwable -> L13
    }

    public final void data(boolean r2, int r3, Buffer r4, int r5) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L11;
        dataFrame(r3, r2 ? 1 : 0, r4, r5);     // Catch: Throwable -> L8
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L11:
        throw new IOException("closed");     // Catch: Throwable -> L8
    }

    public final void dataFrame(int r2, int r3, Buffer r4, int r5) {
        frameHeader(r2, r5, 0, r3);
        if (r5 <= 0) goto L6;
        this.sink.write(r4, r5);
        return;
    }

    public final void flush() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L11;
        this.sink.flush();     // Catch: Throwable -> L8
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L11:
        throw new IOException("closed");     // Catch: Throwable -> L8
    }

    public final void frameHeader(int r9, int r10, int r11, int r12) {
        if (r11 == 8) goto L7;
        Logger r0 = logger;
        if (r0.isLoggable(Level.FINE) == false) goto L7;
        int r4 = r9;
        int r5 = r10;
        int r6 = r11;
        int r7 = r12;
        r0.fine(Http2.INSTANCE.frameLog(false, r4, r5, r6, r7));
    L9:
        if (r5 <= this.maxFrameSize) goto L11;
        Utf8$$ExternalSyntheticBUOutline0.m("FRAME_SIZE_ERROR length > ", this.maxFrameSize, ": ", r5);
        return;
    L11:
        if ((Integer.MIN_VALUE & r4) != 0) goto L14;
        _UtilCommonKt.writeMedium(this.sink, r5);
        this.sink.writeByte(r6 & 255);
        this.sink.writeByte(r7 & 255);
        this.sink.writeInt(Integer.MAX_VALUE & r4);
        return;
    L14:
        Utf8$$ExternalSyntheticBUOutline1.m("reserved bit set: ", r4);
        return;
    L7:
        r4 = r9;
        r5 = r10;
        r6 = r11;
        r7 = r12;
        goto L9
    }

    public final Hpack.Writer getHpackWriter() {
        return this.hpackWriter;
    }

    public final void goAway(int r4, ErrorCode r5, byte[] r6) {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L20;
        if (r5.getHttpCode() == (-1)) goto L18;
        boolean r2 = false;
        frameHeader(0, r6.length + 8, 7, 0);     // Catch: Throwable -> L12
        this.sink.writeInt(r4);     // Catch: Throwable -> L12
        this.sink.writeInt(r5.getHttpCode());     // Catch: Throwable -> L12
        if (r6.length != 0) goto L10;
        r2 = true;
    L10:
        if (r2 == true) goto L14;
        this.sink.write(r6);     // Catch: Throwable -> L12
    L14:
        this.sink.flush();     // Catch: Throwable -> L12
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L18:
        throw new IllegalArgumentException("errorCode.httpCode == -1");     // Catch: Throwable -> L12
    L20:
        throw new IOException("closed");     // Catch: Throwable -> L12
    }

    public final void headers(boolean r7, int r8, List<Header> r9) {
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L20;
        this.hpackWriter.writeHeaders(r9);     // Catch: Throwable -> L14
        long r0 = this.hpackBuffer.size();     // Catch: Throwable -> L14
        long r2 = Math.min(this.maxFrameSize, r0);     // Catch: Throwable -> L14
        if (r0 != r2) goto L8;
        int r4 = 4;
    L9:
        if (r7 == false) goto L11;
        r4 = r4 | 1;     // Catch: Throwable -> L14
    L11:
        frameHeader(r8, (int) r2, 1, r4);     // Catch: Throwable -> L14
        this.sink.write(this.hpackBuffer, r2);     // Catch: Throwable -> L14
        if (r0 <= r2) goto L16;
        writeContinuationFrames(r8, r0 - r2);     // Catch: Throwable -> L14
    L16:
        Unit r72 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(this);
        return;
    L8:
        r4 = 0;
        goto L9
    L20:
        throw new IOException("closed");     // Catch: Throwable -> L14
    }

    public final int maxDataLength() {
        return this.maxFrameSize;
    }

    public final void ping(boolean r4, int r5, int r6) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L11;
        frameHeader(0, 8, 6, r4 ? 1 : 0);     // Catch: Throwable -> L8
        this.sink.writeInt(r5);     // Catch: Throwable -> L8
        this.sink.writeInt(r6);     // Catch: Throwable -> L8
        this.sink.flush();     // Catch: Throwable -> L8
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L11:
        throw new IOException("closed");     // Catch: Throwable -> L8
    }

    public final void pushPromise(int r8, int r9, List<Header> r10) {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L18;
        this.hpackWriter.writeHeaders(r10);     // Catch: Throwable -> L12
        long r0 = this.hpackBuffer.size();     // Catch: Throwable -> L12
        int r102 = (int) Math.min(this.maxFrameSize - 4, r0);     // Catch: Throwable -> L12
        int r2 = r102 + 4;     // Catch: Throwable -> L12
        long r3 = r102;
        if (r0 != r3) goto L8;
        int r5 = 4;
    L9:
        frameHeader(r8, r2, 5, r5);     // Catch: Throwable -> L12
        this.sink.writeInt(r9 & Integer.MAX_VALUE);     // Catch: Throwable -> L12
        this.sink.write(this.hpackBuffer, r3);     // Catch: Throwable -> L12
        if (r0 <= r3) goto L14;
        writeContinuationFrames(r8, r0 - r3);     // Catch: Throwable -> L12
    L14:
        Unit r82 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L8:
        r5 = 0;
        goto L9
    L18:
        throw new IOException("closed");     // Catch: Throwable -> L12
    }

    public final void rstStream(int r4, ErrorCode r5) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L15;
        if (r5.getHttpCode() == (-1)) goto L13;
        frameHeader(r4, 4, 3, 0);     // Catch: Throwable -> L10
        this.sink.writeInt(r5.getHttpCode());     // Catch: Throwable -> L10
        this.sink.flush();     // Catch: Throwable -> L10
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L13:
        throw new IllegalArgumentException("Failed requirement.");     // Catch: Throwable -> L10
    L15:
        throw new IOException("closed");     // Catch: Throwable -> L10
    }

    public final void settings(Settings r4) {
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L18;
        int r2 = 0;
        frameHeader(0, r4.size() * 6, 4, 0);     // Catch: Throwable -> L11
    L7:
        if (r2 >= 10) goto L14;
        if (r4.isSet(r2) == false) goto L13;
        this.sink.writeShort(r2);     // Catch: Throwable -> L11
        this.sink.writeInt(r4.get(r2));     // Catch: Throwable -> L11
    L13:
        r2 = r2 + 1;     // Catch: Throwable -> L11
        goto L7
    L14:
        this.sink.flush();     // Catch: Throwable -> L11
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(this);
        return;
    L18:
        throw new IOException("closed");     // Catch: Throwable -> L11
    }

    public final void windowUpdate(int r9, long r10) {
        monitor-enter(this);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == true) goto L23;
        if (r10 == 0) goto L21;
        if (r10 > 2147483647L) goto L21;
        Logger r0 = logger;     // Catch: Throwable -> L13
        if (r0.isLoggable(Level.FINE) == false) goto L15;
        int r4 = r9;
        long r6 = r10;
        r0.fine(Http2.INSTANCE.frameLogWindowUpdate(false, r4, 4, r6));     // Catch: Throwable -> L13
    L16:
        frameHeader(r4, 4, 8, 0);     // Catch: Throwable -> L13
        this.sink.writeInt((int) r6);     // Catch: Throwable -> L13
        this.sink.flush();     // Catch: Throwable -> L13
        Unit r92 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(this);
        return;
    L15:
        r4 = r9;
        r6 = r10;
    L21:
        throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + r10).toString());     // Catch: Throwable -> L13
    L23:
        throw new IOException("closed");     // Catch: Throwable -> L13
    }
}
