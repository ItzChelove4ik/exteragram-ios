package okio;

import java.util.zip.CRC32;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.internal._ZlibJvmKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0010\u0010\u0007J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0014\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0016R\u001b\u0010\u0019\u001a\u00060\u0017j\u0002`\u00188\u0007¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0019\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010$\u001a\u00060\"j\u0002`#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lokio/GzipSink;", "Lokio/Sink;", "sink", "<init>", "(Lokio/Sink;)V", _UrlKt.FRAGMENT_ENCODE_SET, "writeFooter", "()V", "Lokio/Buffer;", "buffer", _UrlKt.FRAGMENT_ENCODE_SET, "byteCount", "updateCrc", "(Lokio/Buffer;J)V", "source", "write", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "close", "Lokio/RealBufferedSink;", "Lokio/RealBufferedSink;", "Ljava/util/zip/Deflater;", "Lokio/Deflater;", "deflater", "Ljava/util/zip/Deflater;", "()Ljava/util/zip/Deflater;", "Lokio/DeflaterSink;", "deflaterSink", "Lokio/DeflaterSink;", _UrlKt.FRAGMENT_ENCODE_SET, "closed", "Z", "Ljava/util/zip/CRC32;", "Lokio/internal/CRC32;", "crc", "Ljava/util/zip/CRC32;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGzipSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GzipSink.kt\nokio/GzipSink\n+ 2 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,152:1\n51#2:153\n1#3:154\n85#4:155\n*S KotlinDebug\n*F\n+ 1 GzipSink.kt\nokio/GzipSink\n*L\n62#1:153\n130#1:155\n*E\n"})
/* loaded from: classes5.dex */
public final class GzipSink implements Sink {
    private boolean closed;
    private final CRC32 crc;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final RealBufferedSink sink;

    public GzipSink(Sink r4) {
        RealBufferedSink r0 = new RealBufferedSink(r4);
        this.sink = r0;
        Deflater r42 = new Deflater(_ZlibJvmKt.getDEFAULT_COMPRESSION(), true);
        this.deflater = r42;
        this.deflaterSink = new DeflaterSink(r0, r42);
        this.crc = new CRC32();
        Buffer r3 = r0.bufferField;
        r3.writeShort(8075);
        r3.writeByte(8);
        r3.writeByte(0);
        r3.writeInt(0);
        r3.writeByte(0);
        r3.writeByte(0);
    }

    private final void updateCrc(Buffer r5, long r6) {
        Segment r52 = r5.head;
    L4:
        if (r6 <= 0) goto L6;
        int r0 = (int) Math.min(r6, r52.limit - r52.pos);
        this.crc.update(r52.data, r52.pos, r0);
        r6 = r6 - r0;
        r52 = r52.next;
        goto L4
    }

    private final void writeFooter() {
        this.sink.writeIntLe((int) this.crc.getValue());
        this.sink.writeIntLe((int) this.deflater.getBytesRead());
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed == true) goto L28;
        this.deflaterSink.finishDeflate$okio();     // Catch: Throwable -> L7
        writeFooter();     // Catch: Throwable -> L7
        th = null;
    L24:
        this.deflater.end();     // Catch: Throwable -> L10
    L22:
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
        if (th != null) goto L22;
        th = th;
    L7:
        th = th;
        goto L24
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.deflaterSink.flush();
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @Override // okio.Sink
    public void write(Buffer r3, long r4) {
        if (r4 < 0) goto L8;
        if (r4 != 0) goto L6;
        return;
    L6:
        updateCrc(r3, r4);
        this.deflaterSink.write(r3, r4);
        return;
    L8:
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r4);
    }
}
