package org.simplifiles.files;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import j$.nio.file.Path;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\n\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0015R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0015¨\u0006\u0018"}, d2 = {"Lorg/simplifiles/files/BoundedInputStream;", "Ljava/io/InputStream;", "delegate", _UrlKt.FRAGMENT_ENCODE_SET, "maxBytes", "j$/nio/file/Path", "path", "<init>", "(Ljava/io/InputStream;JLj$/nio/file/Path;)V", _UrlKt.FRAGMENT_ENCODE_SET, "read", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "buffer", "offset", "length", "([BII)I", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "Ljava/io/InputStream;", "J", "Lj$/nio/file/Path;", "readBytes", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class BoundedInputStream extends InputStream implements InputStreamRetargetInterface {
    private final InputStream delegate;
    private final long maxBytes;
    private final Path path;
    private long readBytes;

    public BoundedInputStream(InputStream r1, long r2, Path r4) {
        this.delegate = r1;
        this.maxBytes = r2;
        this.path = r4;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    @Override // java.io.InputStream
    public int read() {
        long r0 = this.readBytes;
        long r2 = this.maxBytes;
        InputStream r1 = this.delegate;
        if (r0 == r2) goto L5;
        int r02 = r1.read();
        if (r02 < 0) goto L13;
        this.readBytes++;
    L13:
        return r02;
    L5:
        if (r1.read() >= 0) goto L8;
        return -1;
    L8:
        SimpliFile$$ExternalSyntheticBUOutline1.m("File exceeds read limit of ", this.maxBytes, this.path);
        return 0;
    }

    @Override // java.io.InputStream
    public int read(byte[] r7, int r8, int r9) {
        if (r9 != 0) goto L5;
        return 0;
    L5:
        long r0 = this.readBytes;
        long r2 = this.maxBytes;
        if (r0 == r2) goto L8;
        int r72 = this.delegate.read(r7, r8, (int) Math.min(r9, r2 - r0));
        if (r72 <= 0) goto L16;
        this.readBytes += r72;
    L16:
        return r72;
    L8:
        if (this.delegate.read() >= 0) goto L11;
        return -1;
    L11:
        SimpliFile$$ExternalSyntheticBUOutline1.m("File exceeds read limit of ", this.maxBytes, this.path);
        return 0;
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
