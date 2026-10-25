package okio;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.internal._JavaIoKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokio/InputStreamSource;", "Lokio/Source;", PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/InputStream;", "timeout", "Lokio/Timeout;", "<init>", "(Ljava/io/InputStream;Lokio/Timeout;)V", "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJvmOkio.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmOkio.kt\nokio/InputStreamSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,234:1\n1#2:235\n85#3:236\n*S KotlinDebug\n*F\n+ 1 JvmOkio.kt\nokio/InputStreamSource\n*L\n93#1:236\n*E\n"})
/* loaded from: classes5.dex */
class InputStreamSource implements Source {
    private final InputStream input;
    private final Timeout timeout;

    public InputStreamSource(InputStream r1, Timeout r2) {
        this.input = r1;
        this.timeout = r2;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.input.close();
    }

    @Override // okio.Source
    public long read(Buffer r4, long r5) {
        if (r5 != 0) goto L5;
        return 0;
    L5:
        if (r5 >= 0) goto L23;
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r5);
        return 0;
    L23:
        this.timeout.throwIfReached();     // Catch: AssertionError -> L15
        Segment r0 = r4.writableSegment$okio(1);     // Catch: AssertionError -> L15
        int r3 = this.input.read(r0.data, r0.limit, (int) Math.min(r5, 8192 - r0.limit));     // Catch: AssertionError -> L15
        if (r3 == (-1)) goto L9;
        r0.limit += r3;
        long r02 = r3;
        r4.setSize$okio(r4.size() + r02);     // Catch: AssertionError -> L15
        return r02;
    L9:
        if (r0.pos != r0.limit) goto L24;
        r4.head = r0.pop();     // Catch: AssertionError -> L15
        SegmentPool.recycle(r0);     // Catch: AssertionError -> L15
        return -1;
    L24:
        return -1;
    L15:
        e = move-exception;
        if (_JavaIoKt.isAndroidGetsocknameError(e) == true) goto L19;
        throw e;
    L19:
        throw new IOException(e);
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.timeout;
    }

    public String toString() {
        return "source(" + this.input + ')';
    }
}
