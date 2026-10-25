package j$.desugar.sun.nio.fs;

import j$.nio.file.Path;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class e extends FileChannel implements SeekableByteChannel {
    public static final /* synthetic */ int e = 0;

    /* renamed from: a, reason: collision with root package name */
    public final FileChannel f153a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f154b;

    /* renamed from: c, reason: collision with root package name */
    public final boolean f155c;
    public final Path d;

    public e(FileChannel r1, boolean r2, boolean r3, Path r4) {
        this.f153a = r1;
        this.f154b = r2;
        this.f155c = r3;
        if (r2 == true) goto L6;
        r4 = null;
    L6:
        this.d = r4;
    }

    @Override // java.nio.channels.FileChannel
    public final void force(boolean r1) {
        this.f153a.force(r1);
    }

    @Override // java.nio.channels.spi.AbstractInterruptibleChannel
    public final void implCloseChannel() {
        this.f153a.close();
        if (this.f154b == false) goto L6;
        this.d.toFile().delete();
        return;
    }

    @Override // java.nio.channels.FileChannel
    public final FileLock lock(long r7, long r9, boolean r11) {
        FileLock r72 = this.f153a.lock(r7, r9, r11);
        if (r72 != null) goto L7;
        return null;
    L7:
        return new f(r72, this);
    }

    @Override // java.nio.channels.FileChannel
    public final MappedByteBuffer map(FileChannel.MapMode r1, long r2, long r4) {
        return this.f153a.map(r1, r2, r4);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public final long position() {
        return this.f153a.position();
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public final FileChannel position(long r2) {
        FileChannel r1 = this.f153a.position(r2);
        if ((r1 instanceof e) == false) goto L7;
        return (e) r1;
    L7:
        return new e(r1, false, false, null);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public final /* bridge */ /* synthetic */ SeekableByteChannel position(long r1) {
        return position(r1);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer r1) {
        return this.f153a.read(r1);
    }

    @Override // java.nio.channels.FileChannel
    public final int read(ByteBuffer r1, long r2) {
        return this.f153a.read(r1, r2);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.ScatteringByteChannel
    public final long read(ByteBuffer[] r1, int r2, int r3) {
        return this.f153a.read(r1, r2, r3);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public final long size() {
        return this.f153a.size();
    }

    @Override // java.nio.channels.FileChannel
    public final long transferFrom(ReadableByteChannel r1, long r2, long r4) {
        return this.f153a.transferFrom(r1, r2, r4);
    }

    @Override // java.nio.channels.FileChannel
    public final long transferTo(long r1, long r3, WritableByteChannel r5) {
        return this.f153a.transferTo(r1, r3, r5);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public final FileChannel truncate(long r2) {
        FileChannel r1 = this.f153a.truncate(r2);
        if ((r1 instanceof e) == false) goto L7;
        return (e) r1;
    L7:
        return new e(r1, false, false, null);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public final /* bridge */ /* synthetic */ SeekableByteChannel truncate(long r1) {
        return truncate(r1);
    }

    @Override // java.nio.channels.FileChannel
    public final FileLock tryLock(long r7, long r9, boolean r11) {
        FileLock r72 = this.f153a.tryLock(r7, r9, r11);
        if (r72 != null) goto L7;
        return null;
    L7:
        return new f(r72, this);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer r3) {
        boolean r0 = this.f155c;
        FileChannel r2 = this.f153a;
        if (r0 == false) goto L7;
        return r2.write(r3, r2.size());
    L7:
        return r2.write(r3);
    }

    @Override // java.nio.channels.FileChannel
    public final int write(ByteBuffer r1, long r2) {
        return this.f153a.write(r1, r2);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.GatheringByteChannel
    public final long write(ByteBuffer[] r1, int r2, int r3) {
        return this.f153a.write(r1, r2, r3);
    }
}
