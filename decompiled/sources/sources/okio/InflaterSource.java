package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0013\u001a\u00020\fJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lokio/InflaterSource;", "Lokio/Source;", "source", "Lokio/BufferedSource;", "inflater", "Ljava/util/zip/Inflater;", "<init>", "(Lokio/BufferedSource;Ljava/util/zip/Inflater;)V", "(Lokio/Source;Ljava/util/zip/Inflater;)V", "bufferBytesHeldByInflater", _UrlKt.FRAGMENT_ENCODE_SET, "closed", _UrlKt.FRAGMENT_ENCODE_SET, "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "readOrInflate", "refill", "releaseBytesAfterInflate", _UrlKt.FRAGMENT_ENCODE_SET, "timeout", "Lokio/Timeout;", "close", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInflaterSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InflaterSource.kt\nokio/InflaterSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,132:1\n1#2:133\n85#3:134\n*S KotlinDebug\n*F\n+ 1 InflaterSource.kt\nokio/InflaterSource\n*L\n66#1:134\n*E\n"})
/* loaded from: classes.dex */
public final class InflaterSource implements Source {
    private int bufferBytesHeldByInflater;
    private boolean closed;
    private final Inflater inflater;
    private final BufferedSource source;

    public InflaterSource(BufferedSource r1, Inflater r2) {
        this.source = r1;
        this.inflater = r2;
    }

    public InflaterSource(Source r1, Inflater r2) {
        this(Okio.buffer(r1), r2);
    }

    private final void releaseBytesAfterInflate() {
        int r0 = this.bufferBytesHeldByInflater;
        if (r0 != 0) goto L5;
        return;
    L5:
        int r02 = r0 - this.inflater.getRemaining();
        this.bufferBytesHeldByInflater -= r02;
        this.source.skip(r02);
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed == false) goto L5;
        return;
    L5:
        this.inflater.end();
        this.closed = true;
        this.source.close();
    }

    @Override // okio.Source
    public long read(Buffer r5, long r6) {
    L2:
        long r0 = readOrInflate(r5, r6);
        if (r0 > 0) goto L4;
        if (this.inflater.finished() == true) goto L15;
        if (this.inflater.needsDictionary() == true) goto L22;
        if (this.source.exhausted() == false) goto L2;
        throw new EOFException("source exhausted prematurely");
    L22:
        return -1;
    L15:
        return -1;
    L4:
        return r0;
    }

    public final long readOrInflate(Buffer r6, long r7) {
        if (r7 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r7);
        return 0;
    L5:
        if (this.closed == true) goto L20;
        if (r7 != 0) goto L24;
        return 0;
    L24:
        Segment r2 = r6.writableSegment$okio(1);     // Catch: DataFormatException -> L17
        int r72 = (int) Math.min(r7, 8192 - r2.limit);     // Catch: DataFormatException -> L17
        refill();     // Catch: DataFormatException -> L17
        int r73 = this.inflater.inflate(r2.data, r2.limit, r72);     // Catch: DataFormatException -> L17
        releaseBytesAfterInflate();     // Catch: DataFormatException -> L17
        if (r73 <= 0) goto L14;
        r2.limit += r73;
        long r74 = r73;
        r6.setSize$okio(r6.size() + r74);     // Catch: DataFormatException -> L17
        return r74;
    L14:
        if (r2.pos != r2.limit) goto L16;
        r6.head = r2.pop();     // Catch: DataFormatException -> L17
        SegmentPool.recycle(r2);     // Catch: DataFormatException -> L17
    L16:
        return 0;
    L17:
        e = move-exception;
        throw new IOException(e);
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return 0;
    }

    public final boolean refill() {
        if (this.inflater.needsInput() == true) goto L6;
        return false;
    L6:
        if (this.source.exhausted() == false) goto L9;
        return true;
    L9:
        Segment r0 = this.source.getBuffer().head;
        int r2 = r0.limit;
        int r3 = r0.pos;
        int r22 = r2 - r3;
        this.bufferBytesHeldByInflater = r22;
        this.inflater.setInput(r0.data, r3, r22);
        return false;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }
}
