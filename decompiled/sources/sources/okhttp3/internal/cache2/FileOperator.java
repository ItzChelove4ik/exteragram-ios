package okhttp3.internal.cache2;

import java.nio.channels.FileChannel;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tJ\u001e\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/cache2/FileOperator;", _UrlKt.FRAGMENT_ENCODE_SET, "fileChannel", "Ljava/nio/channels/FileChannel;", "<init>", "(Ljava/nio/channels/FileChannel;)V", "write", _UrlKt.FRAGMENT_ENCODE_SET, "pos", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/Buffer;", "byteCount", "read", "sink", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FileOperator {
    private final FileChannel fileChannel;

    public FileOperator(FileChannel r1) {
        this.fileChannel = r1;
    }

    public final void read(long r10, Buffer r12, long r13) {
        if (r13 < 0) goto L10;
        long r4 = r10;
        long r6 = r13;
    L6:
        if (r6 <= 0) goto L8;
        long r102 = this.fileChannel.transferTo(r4, r6, r12);
        r4 = r4 + r102;
        r6 = r6 - r102;
        goto L6
    L8:
        return;
    L10:
        throw new IndexOutOfBoundsException();
    }

    public final void write(long r10, Buffer r12, long r13) {
        if (r13 < 0) goto L12;
        if (r13 > r12.size()) goto L12;
        long r5 = r10;
        long r7 = r13;
    L8:
        if (r7 <= 0) goto L10;
        long r102 = this.fileChannel.transferFrom(r12, r5, r7);
        r5 = r5 + r102;
        r7 = r7 - r102;
        goto L8
    L10:
        return;
    L12:
        throw new IndexOutOfBoundsException();
    }
}
