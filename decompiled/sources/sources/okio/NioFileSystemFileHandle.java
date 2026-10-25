package okio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0014¢\u0006\u0004\b\t\u0010\nJ/\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0014¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0014¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016¨\u0006\u0017"}, d2 = {"Lokio/NioFileSystemFileHandle;", "Lokio/FileHandle;", _UrlKt.FRAGMENT_ENCODE_SET, "readWrite", "Ljava/nio/channels/FileChannel;", "fileChannel", "<init>", "(ZLjava/nio/channels/FileChannel;)V", _UrlKt.FRAGMENT_ENCODE_SET, "protectedSize", "()J", "fileOffset", _UrlKt.FRAGMENT_ENCODE_SET, "array", _UrlKt.FRAGMENT_ENCODE_SET, "arrayOffset", "byteCount", "protectedRead", "(J[BII)I", _UrlKt.FRAGMENT_ENCODE_SET, "protectedClose", "()V", "Ljava/nio/channels/FileChannel;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class NioFileSystemFileHandle extends FileHandle {
    private final FileChannel fileChannel;

    public NioFileSystemFileHandle(boolean r1, FileChannel r2) {
        super(r1);
        this.fileChannel = r2;
    }

    @Override // okio.FileHandle
    public synchronized void protectedClose() {
        monitor-enter(this);
        this.fileChannel.close();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // okio.FileHandle
    public synchronized int protectedRead(long r2, byte[] r4, int r5, int r6) {
        monitor-enter(this);
        this.fileChannel.position(r2);     // Catch: Throwable -> L12
        ByteBuffer r22 = ByteBuffer.wrap(r4, r5, r6);     // Catch: Throwable -> L12
        int r3 = 0;
    L4:
        if (r3 >= r6) goto L14;
        int r42 = this.fileChannel.read(r22);     // Catch: Throwable -> L12
        if (r42 == (-1)) goto L8;
        r3 = r3 + r42;
        goto L4
    L8:
        if (r3 != 0) goto L14;
        monitor-exit(this);
        return -1;
    L14:
        monitor-exit(this);
        return r3;
    L12:
        th = move-exception;
        throw th;
    }

    @Override // okio.FileHandle
    public synchronized long protectedSize() {
        monitor-enter(this);
        long r0 = this.fileChannel.size();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }
}
