package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.internal._ZlibJvmKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\fH\u0016J\r\u0010\u0014\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\fH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lokio/DeflaterSink;", "Lokio/Sink;", "sink", "Lokio/BufferedSink;", "deflater", "Ljava/util/zip/Deflater;", "<init>", "(Lokio/BufferedSink;Ljava/util/zip/Deflater;)V", "(Lokio/Sink;Ljava/util/zip/Deflater;)V", "closed", _UrlKt.FRAGMENT_ENCODE_SET, "write", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/Buffer;", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "deflate", "syncFlush", "flush", "finishDeflate", "finishDeflate$okio", "close", "timeout", "Lokio/Timeout;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDeflaterSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeflaterSink.kt\nokio/DeflaterSink\n+ 2 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,140:1\n85#2:141\n*S KotlinDebug\n*F\n+ 1 DeflaterSink.kt\nokio/DeflaterSink\n*L\n39#1:141\n*E\n"})
/* loaded from: classes5.dex */
public final class DeflaterSink implements Sink {
    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;

    public DeflaterSink(BufferedSink r1, Deflater r2) {
        this.sink = r1;
        this.deflater = r2;
    }

    public DeflaterSink(Sink r1, Deflater r2) {
        this(Okio.buffer(r1), r2);
    }

    private final void deflate(boolean r8) {
        Buffer r0 = this.sink.getBuffer();
    L3:
        Segment r1 = r0.writableSegment$okio(1);
        Deflater r2 = this.deflater;
        if (r8 == true) goto L18;
        byte[] r3 = r1.data;     // Catch: NullPointerException -> L15
        int r4 = r1.limit;     // Catch: NullPointerException -> L15
        int r22 = r2.deflate(r3, r4, 8192 - r4);     // Catch: NullPointerException -> L15
    L7:
        if (r22 > 0) goto L8;
        if (this.deflater.needsInput() == false) goto L3;
        if (r1.pos != r1.limit) goto L26;
        r0.head = r1.pop();
        SegmentPool.recycle(r1);
        return;
    L26:
        return;
    L8:
        r1.limit += r22;
        r0.setSize$okio(r0.size() + r22);
        this.sink.emitCompleteSegments();
        goto L3
    L18:
        byte[] r32 = r1.data;     // Catch: NullPointerException -> L15
        int r42 = r1.limit;     // Catch: NullPointerException -> L15
        r22 = r2.deflate(r32, r42, 8192 - r42, 2);     // Catch: NullPointerException -> L15
    L15:
        e = move-exception;
        throw new IOException("Deflater already closed", e);
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed == true) goto L28;
        finishDeflate$okio();     // Catch: Throwable -> L7
        th = null;
    L22:
        this.deflater.end();     // Catch: Throwable -> L10
    L26:
        this.sink.close();     // Catch: Throwable -> L15
    L18:
        this.closed = true;
        if (th != null) goto L21;
        return;
    L21:
        throw th;
    L15:
        th = move-exception;
        if (th != null) goto L18;
        th = th;
    L10:
        th = move-exception;
        if (th != null) goto L26;
        th = th;
    L7:
        th = th;
        goto L22
    }

    public final void finishDeflate$okio() {
        this.deflater.finish();
        deflate(false);
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        deflate(true);
        this.sink.flush();
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "DeflaterSink(" + this.sink + ')';
    }

    @Override // okio.Sink
    public void write(Buffer r8, long r9) {
        SegmentedByteString.checkOffsetAndCount(r8.size(), 0, r9);
    L4:
        if (r9 <= 0) goto L9;
        Segment r0 = r8.head;
        int r2 = (int) Math.min(r9, r0.limit - r0.pos);
        this.deflater.setInput(r0.data, r0.pos, r2);
        deflate(false);
        long r5 = r2;
        r8.setSize$okio(r8.size() - r5);
        int r1 = r0.pos + r2;
        r0.pos = r1;
        if (r1 != r0.limit) goto L8;
        r8.head = r0.pop();
        SegmentPool.recycle(r0);
    L8:
        r9 = r9 - r5;
        goto L4
    L9:
        this.deflater.setInput(_ZlibJvmKt.getEMPTY_BYTE_ARRAY(), 0, 0);
    }
}
