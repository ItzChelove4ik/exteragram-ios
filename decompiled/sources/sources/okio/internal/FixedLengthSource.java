package okio.internal;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Source;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J\u0014\u0010\u000f\u001a\u00020\u0010*\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokio/internal/FixedLengthSource;", "Lokio/ForwardingSource;", "delegate", "Lokio/Source;", "size", _UrlKt.FRAGMENT_ENCODE_SET, "truncate", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokio/Source;JZ)V", "bytesReceived", "read", "sink", "Lokio/Buffer;", "byteCount", "truncateToSize", _UrlKt.FRAGMENT_ENCODE_SET, "newSize", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FixedLengthSource extends ForwardingSource {
    private long bytesReceived;
    private final long size;
    private final boolean truncate;

    public FixedLengthSource(Source r1, long r2, boolean r4) {
        super(r1);
        this.size = r2;
        this.truncate = r4;
    }

    private final void truncateToSize(Buffer r1, long r2) {
        Buffer r0 = new Buffer();
        r0.writeAll(r1);
        r1.write(r0, r2);
        r0.clear();
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(Buffer r10, long r11) {
        long r0 = this.bytesReceived;
        long r2 = this.size;
        if (r0 <= r2) goto L6;
        r11 = 0;
    L11:
        long r112 = super.read(r10, r11);
        if (r112 == (-1)) goto L14;
        this.bytesReceived += r112;
    L14:
        long r1 = this.bytesReceived;
        long r3 = this.size;
        if (r1 >= r3) goto L18;
        if (r112 != (-1)) goto L18;
    L20:
        if (r112 <= 0) goto L25;
        if (r1 <= r3) goto L25;
        truncateToSize(r10, r10.size() - (this.bytesReceived - this.size));
    L25:
        throw new IOException("expected " + this.size + " bytes but got " + this.bytesReceived);
    L18:
        if (r1 > r3) goto L20;
        return r112;
    L6:
        if (this.truncate == false) goto L11;
        long r22 = r2 - r0;
        if (r22 != 0) goto L10;
        return -1;
    L10:
        r11 = Math.min(r11, r22);
        goto L11
    }
}
