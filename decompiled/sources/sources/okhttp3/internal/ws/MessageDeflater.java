package okhttp3.internal.ws;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.Closeable;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ByteString;
import okio.DeflaterSink;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007J\b\u0010\u000f\u001a\u00020\rH\u0016J\u0014\u0010\u0010\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lokhttp3/internal/ws/MessageDeflater;", "Ljava/io/Closeable;", "noContextTakeover", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Z)V", "deflatedBytes", "Lokio/Buffer;", "deflater", "Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "deflate", _UrlKt.FRAGMENT_ENCODE_SET, "buffer", "close", "endsWith", "suffix", "Lokio/ByteString;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class MessageDeflater implements Closeable {
    private final Buffer deflatedBytes;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final boolean noContextTakeover;

    public MessageDeflater(boolean r4) {
        this.noContextTakeover = r4;
        Buffer r42 = new Buffer();
        this.deflatedBytes = r42;
        Deflater r0 = new Deflater(-1, true);
        this.deflater = r0;
        this.deflaterSink = new DeflaterSink(r42, r0);
    }

    private final boolean endsWith(Buffer r5, ByteString r6) {
        return r5.rangeEquals(r5.size() - r6.size(), r6);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.deflaterSink.close();
    }

    public final void deflate(Buffer r6) {
        if (this.deflatedBytes.size() == 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return;
    L5:
        if (this.noContextTakeover == false) goto L7;
        this.deflater.reset();
    L7:
        this.deflaterSink.write(r6, r6.size());
        this.deflaterSink.flush();
        boolean r0 = endsWith(this.deflatedBytes, MessageDeflaterKt.access$getEMPTY_DEFLATE_BLOCK$p());
        Buffer r1 = this.deflatedBytes;
        if (r0 == false) goto L17;
        long r02 = r1.size() - 4;
        Buffer.UnsafeCursor r2 = Buffer.readAndWriteUnsafe$default(this.deflatedBytes, null, 1, null);
        r2.resizeBuffer(r02);     // Catch: Throwable -> L12
        CloseableKt.closeFinally(r2, null);
    L18:
        Buffer r5 = this.deflatedBytes;
        r6.write(r5, r5.size());
        return;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        CloseableKt.closeFinally(r2, th);
        throw th;
    L17:
        r1.writeByte(0);
        goto L18
    }
}
