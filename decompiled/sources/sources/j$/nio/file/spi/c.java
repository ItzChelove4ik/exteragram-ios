package j$.nio.file.spi;

import j$.desugar.sun.nio.fs.g;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.l;
import j$.nio.file.attribute.m;
import j$.nio.file.attribute.n;
import j$.nio.file.attribute.o;
import j$.nio.file.attribute.p;
import j$.nio.file.attribute.t;
import j$.nio.file.attribute.v;
import j$.nio.file.b0;
import j$.nio.file.e;
import j$.nio.file.f;
import j$.nio.file.h;
import j$.nio.file.y;
import j$.nio.file.z;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessMode;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class c extends FileSystemProvider {

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f261b = 0;

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f262a;

    public /* synthetic */ c(d r1) {
        this.f262a = r1;
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ void checkAccess(Path r7, AccessMode[] r8) {
        d r6 = this.f262a;
        j$.nio.file.Path r72 = y.s(r7);
        j$.nio.file.a[] r0 = null;
        if (r8 == null) goto L19;
        int r1 = r8.length;
        j$.nio.file.a[] r2 = new j$.nio.file.a[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L18;
        AccessMode r4 = r8[r3];
        if (r4 != null) goto L11;
        j$.nio.file.a r42 = null;
    L17:
        r2[r3] = r42;
        r3 = r3 + 1;
        goto L6
    L11:
        if (r4 != AccessMode.READ) goto L14;
        r42 = j$.nio.file.a.READ;
        goto L17
    L14:
        if (r4 != AccessMode.WRITE) goto L16;
        r42 = j$.nio.file.a.WRITE;
        goto L17
    L16:
        r42 = j$.nio.file.a.EXECUTE;
        goto L17
    L18:
        r0 = r2;
    L19:
        r6.a(r72, r0);
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ void copy(Path r5, Path r6, CopyOption[] r7) {
        d r4 = this.f262a;
        j$.nio.file.Path r52 = y.s(r5);
        j$.nio.file.Path r62 = y.s(r6);
        if (r7 != null) goto L5;
        j$.nio.file.CopyOption[] r72 = null;
    L9:
        r4.b(r52, r62, r72);
        return;
    L5:
        int r0 = r7.length;
        j$.nio.file.CopyOption[] r1 = new j$.nio.file.CopyOption[r0];
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r1[r2] = j$.nio.file.c.a(r7[r2]);
        r2 = r2 + 1;
        goto L6
    L8:
        r72 = r1;
        goto L9
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ void createDirectory(Path r1, FileAttribute[] r2) {
        this.f262a.c(y.s(r1), g.t(r2));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ void createLink(Path r1, Path r2) {
        this.f262a.d(y.s(r1), y.s(r2));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ void createSymbolicLink(Path r1, Path r2, FileAttribute[] r3) {
        this.f262a.e(y.s(r1), y.s(r2), g.t(r3));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ void delete(Path r1) {
        this.f262a.f(y.s(r1));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ boolean deleteIfExists(Path r1) {
        return this.f262a.g(y.s(r1));
    }

    public final /* synthetic */ boolean equals(Object r2) {
        d r1 = this.f262a;
        if ((r2 instanceof c) == false) goto L6;
        r2 = ((c) r2).f262a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ FileAttributeView getFileAttributeView(Path r1, Class r2, LinkOption[] r3) {
        j$.nio.file.attribute.FileAttributeView r0 = this.f262a.h(y.s(r1), g.h(r2), g.r(r3));
        if (r0 != null) goto L7;
        return null;
    L7:
        if ((r0 instanceof l) == false) goto L11;
        return ((l) r0).f202a;
    L11:
        if ((r0 instanceof BasicFileAttributeView) == false) goto L27;
        BasicFileAttributeView r02 = (BasicFileAttributeView) r0;
        if ((r02 instanceof j$.nio.file.attribute.c) == false) goto L17;
        return ((j$.nio.file.attribute.c) r02).f193a;
    L17:
        if ((r02 instanceof j$.nio.file.attribute.g) == false) goto L21;
        return ((j$.nio.file.attribute.g) r02).f197a;
    L21:
        if ((r02 instanceof t) == false) goto L25;
        return ((t) r02).f209a;
    L25:
        return new j$.nio.file.attribute.d(r02);
    L27:
        if ((r0 instanceof p) == false) goto L43;
        p r03 = (p) r0;
        if ((r03 instanceof n) == false) goto L33;
        return ((n) r03).f204a;
    L33:
        if ((r03 instanceof j$.nio.file.attribute.a) == false) goto L37;
        return ((j$.nio.file.attribute.a) r03).f192a;
    L37:
        if ((r03 instanceof t) == false) goto L41;
        return ((t) r03).f209a;
    L41:
        return new o(r03);
    L43:
        if ((r0 instanceof v) == false) goto L47;
        return ((v) r0).f211a;
    L47:
        return new m(r0);
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ FileStore getFileStore(Path r1) {
        j$.nio.file.FileStore r0 = this.f262a.i(y.s(r1));
        int r12 = f.f223b;
        if (r0 != null) goto L7;
        return null;
    L7:
        if ((r0 instanceof e) == false) goto L11;
        return ((e) r0).f221a;
    L11:
        return new f(r0);
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ FileSystem getFileSystem(URI r1) {
        return h.a(this.f262a.j(r1));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ Path getPath(URI r1) {
        return z.s(this.f262a.k(r1));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ String getScheme() {
        return this.f262a.l();
    }

    public final /* synthetic */ int hashCode() {
        return this.f262a.hashCode();
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ boolean isHidden(Path r1) {
        return this.f262a.m(y.s(r1));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ boolean isSameFile(Path r1, Path r2) {
        return this.f262a.n(y.s(r1), y.s(r2));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ void move(Path r5, Path r6, CopyOption[] r7) {
        d r4 = this.f262a;
        j$.nio.file.Path r52 = y.s(r5);
        j$.nio.file.Path r62 = y.s(r6);
        if (r7 != null) goto L5;
        j$.nio.file.CopyOption[] r72 = null;
    L9:
        r4.o(r52, r62, r72);
        return;
    L5:
        int r0 = r7.length;
        j$.nio.file.CopyOption[] r1 = new j$.nio.file.CopyOption[r0];
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r1[r2] = j$.nio.file.c.a(r7[r2]);
        r2 = r2 + 1;
        goto L6
    L8:
        r72 = r1;
        goto L9
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ AsynchronousFileChannel newAsynchronousFileChannel(Path r1, Set r2, ExecutorService r3, FileAttribute[] r4) {
        j$.nio.channels.a r0 = this.f262a.p(y.s(r1), g.l(r2), r3, g.t(r4));
        int r12 = j$.nio.channels.b.f178a;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.f177a;
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ SeekableByteChannel newByteChannel(Path r1, Set r2, FileAttribute[] r3) {
        return this.f262a.q(y.s(r1), g.l(r2), g.t(r3));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final DirectoryStream newDirectoryStream(Path r3, DirectoryStream.Filter r4) {
        return new b0(this.f262a.r(y.s(r3), new j$.desugar.sun.nio.fs.h(r4, 2)));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ FileChannel newFileChannel(Path r1, Set r2, FileAttribute[] r3) {
        return this.f262a.s(y.s(r1), g.l(r2), g.t(r3));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ FileSystem newFileSystem(URI r1, Map r2) {
        return h.a(this.f262a.u(r1, r2));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ FileSystem newFileSystem(Path r1, Map r2) {
        return h.a(this.f262a.t(y.s(r1), r2));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ InputStream newInputStream(Path r5, OpenOption[] r6) {
        d r4 = this.f262a;
        j$.nio.file.Path r52 = y.s(r5);
        if (r6 != null) goto L5;
        j$.nio.file.OpenOption[] r62 = null;
    L10:
        return r4.v(r52, r62);
    L5:
        int r0 = r6.length;
        j$.nio.file.OpenOption[] r1 = new j$.nio.file.OpenOption[r0];
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r1[r2] = j$.nio.file.v.a(r6[r2]);
        r2 = r2 + 1;
        goto L6
    L8:
        r62 = r1;
        goto L10
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ OutputStream newOutputStream(Path r5, OpenOption[] r6) {
        d r4 = this.f262a;
        j$.nio.file.Path r52 = y.s(r5);
        if (r6 != null) goto L5;
        j$.nio.file.OpenOption[] r62 = null;
    L10:
        return r4.w(r52, r62);
    L5:
        int r0 = r6.length;
        j$.nio.file.OpenOption[] r1 = new j$.nio.file.OpenOption[r0];
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r1[r2] = j$.nio.file.v.a(r6[r2]);
        r2 = r2 + 1;
        goto L6
    L8:
        r62 = r1;
        goto L10
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ BasicFileAttributes readAttributes(Path r1, Class r2, LinkOption[] r3) {
        return j$.nio.file.attribute.f.a(this.f262a.x(y.s(r1), g.i(r2), g.r(r3)));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ Map readAttributes(Path r1, String r2, LinkOption[] r3) {
        return g.j(this.f262a.y(y.s(r1), r2, g.r(r3)));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ Path readSymbolicLink(Path r1) {
        return z.s(this.f262a.z(y.s(r1)));
    }

    @Override // java.nio.file.spi.FileSystemProvider
    public final /* synthetic */ void setAttribute(Path r1, String r2, Object r3, LinkOption[] r4) {
        this.f262a.A(y.s(r1), r2, g.k(r3), g.r(r4));
    }
}
