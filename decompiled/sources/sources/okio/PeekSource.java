package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokio/PeekSource;", "Lokio/Source;", "upstream", "Lokio/BufferedSource;", "<init>", "(Lokio/BufferedSource;)V", "buffer", "Lokio/Buffer;", "expectedSegment", "Lokio/Segment;", "expectedPos", _UrlKt.FRAGMENT_ENCODE_SET, "closed", _UrlKt.FRAGMENT_ENCODE_SET, "pos", _UrlKt.FRAGMENT_ENCODE_SET, "read", "sink", "byteCount", "timeout", "Lokio/Timeout;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPeekSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PeekSource.kt\nokio/PeekSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,74:1\n1#2:75\n*E\n"})
/* loaded from: classes5.dex */
public final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    public PeekSource(BufferedSource r1) {
        this.upstream = r1;
        Buffer r12 = r1.getBuffer();
        this.buffer = r12;
        Segment r13 = r12.head;
        this.expectedSegment = r13;
        if (r13 == null) goto L5;
        int r14 = r13.pos;
    L6:
        this.expectedPos = r14;
        return;
    L5:
        r14 = -1;
        goto L6
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    @Override // okio.Source
    public long read(Buffer r9, long r10) {
        if (r10 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r10);
        return 0;
    L5:
        if (this.closed == true) goto L29;
        Segment r3 = this.expectedSegment;
        if (r3 == null) goto L16;
        Segment r4 = this.buffer.head;
        if (r3 == r4) goto L11;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Peek source is invalid because upstream source was used");
        return 0;
    L11:
        if (this.expectedPos != r4.pos) goto L13;
    L16:
        if (r10 != 0) goto L19;
        return 0;
    L19:
        if (this.upstream.request(this.pos + 1) == true) goto L23;
        return -1;
    L23:
        if (this.expectedSegment != null) goto L27;
        Segment r0 = this.buffer.head;
        if (r0 == null) goto L27;
        this.expectedSegment = r0;
        this.expectedPos = r0.pos;
    L27:
        long r6 = Math.min(r10, this.buffer.size() - this.pos);
        this.buffer.copyTo(r9, this.pos, r6);
        this.pos += r6;
        return r6;
    L29:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return 0;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.upstream.timeout();
    }
}
