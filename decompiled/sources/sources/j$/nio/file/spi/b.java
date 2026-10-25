package j$.nio.file.spi;

import j$.desugar.sun.nio.fs.g;
import j$.desugar.sun.nio.fs.h;
import j$.nio.file.CopyOption;
import j$.nio.file.FileStore;
import j$.nio.file.FileSystem;
import j$.nio.file.LinkOption;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeView;
import j$.nio.file.attribute.l;
import j$.nio.file.attribute.m;
import j$.nio.file.attribute.n;
import j$.nio.file.attribute.o;
import j$.nio.file.attribute.t;
import j$.nio.file.attribute.v;
import j$.nio.file.b0;
import j$.nio.file.e;
import j$.nio.file.f;
import j$.nio.file.w;
import j$.nio.file.y;
import j$.nio.file.z;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessMode;
import java.nio.file.DirectoryStream;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class b extends d {
    public static final /* synthetic */ int f = 0;
    public final /* synthetic */ FileSystemProvider e;

    public /* synthetic */ b(FileSystemProvider r1) {
        this.e = r1;
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ void A(Path r1, String r2, Object r3, LinkOption[] r4) {
        this.e.setAttribute(z.s(r1), r2, g.k(r3), g.v(r4));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ void a(Path r7, j$.nio.file.a[] r8) {
        FileSystemProvider r6 = this.e;
        java.nio.file.Path r72 = z.s(r7);
        AccessMode[] r0 = null;
        if (r8 == null) goto L19;
        int r1 = r8.length;
        AccessMode[] r2 = new AccessMode[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L18;
        j$.nio.file.a r4 = r8[r3];
        if (r4 != null) goto L11;
        AccessMode r42 = null;
    L17:
        r2[r3] = r42;
        r3 = r3 + 1;
        goto L6
    L11:
        if (r4 != j$.nio.file.a.READ) goto L14;
        r42 = AccessMode.READ;
        goto L17
    L14:
        if (r4 != j$.nio.file.a.WRITE) goto L16;
        r42 = AccessMode.WRITE;
        goto L17
    L16:
        r42 = AccessMode.EXECUTE;
        goto L17
    L18:
        r0 = r2;
    L19:
        r6.checkAccess(r72, r0);
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ void b(Path r1, Path r2, CopyOption[] r3) {
        this.e.copy(z.s(r1), z.s(r2), g.u(r3));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ void c(Path r1, FileAttribute[] r2) {
        this.e.createDirectory(z.s(r1), g.x(r2));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ void d(Path r1, Path r2) {
        this.e.createLink(z.s(r1), z.s(r2));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ void e(Path r1, Path r2, FileAttribute[] r3) {
        this.e.createSymbolicLink(z.s(r1), z.s(r2), g.x(r3));
    }

    public final /* synthetic */ boolean equals(Object r2) {
        FileSystemProvider r1 = this.e;
        if ((r2 instanceof b) == false) goto L6;
        r2 = ((b) r2).e;
    L6:
        return r1.equals(r2);
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ void f(Path r1) {
        this.e.delete(z.s(r1));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ boolean g(Path r1) {
        return this.e.deleteIfExists(z.s(r1));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ FileAttributeView h(Path r1, Class r2, LinkOption[] r3) {
        java.nio.file.attribute.FileAttributeView r0 = this.e.getFileAttributeView(z.s(r1), g.h(r2), g.v(r3));
        if (r0 != null) goto L7;
        return null;
    L7:
        if ((r0 instanceof m) == false) goto L11;
        return ((m) r0).f203a;
    L11:
        if ((r0 instanceof BasicFileAttributeView) == false) goto L27;
        BasicFileAttributeView r02 = (BasicFileAttributeView) r0;
        if ((r02 instanceof j$.nio.file.attribute.d) == false) goto L17;
        return ((j$.nio.file.attribute.d) r02).f194a;
    L17:
        if ((r02 instanceof DosFileAttributeView) == false) goto L21;
        return new j$.nio.file.attribute.g((DosFileAttributeView) r02);
    L21:
        if ((r02 instanceof PosixFileAttributeView) == false) goto L25;
        return new t((PosixFileAttributeView) r02);
    L25:
        return new j$.nio.file.attribute.c(r02);
    L27:
        if ((r0 instanceof FileOwnerAttributeView) == false) goto L43;
        FileOwnerAttributeView r03 = (FileOwnerAttributeView) r0;
        if ((r03 instanceof o) == false) goto L33;
        return ((o) r03).f205a;
    L33:
        if ((r03 instanceof AclFileAttributeView) == false) goto L37;
        return new j$.nio.file.attribute.a((AclFileAttributeView) r03);
    L37:
        if ((r03 instanceof PosixFileAttributeView) == false) goto L41;
        return new t((PosixFileAttributeView) r03);
    L41:
        return new n(r03);
    L43:
        if ((r0 instanceof UserDefinedFileAttributeView) == false) goto L47;
        return new v((UserDefinedFileAttributeView) r0);
    L47:
        return new l(r0);
    }

    public final /* synthetic */ int hashCode() {
        return this.e.hashCode();
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ FileStore i(Path r1) {
        java.nio.file.FileStore r0 = this.e.getFileStore(z.s(r1));
        if (r0 != null) goto L7;
        return null;
    L7:
        if ((r0 instanceof f) == false) goto L11;
        return ((f) r0).f224a;
    L11:
        return new e(r0);
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ FileSystem j(URI r1) {
        return j$.nio.file.g.i(this.e.getFileSystem(r1));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ Path k(URI r1) {
        return y.s(this.e.getPath(r1));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ String l() {
        return this.e.getScheme();
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ boolean m(Path r1) {
        return this.e.isHidden(z.s(r1));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ boolean n(Path r1, Path r2) {
        return this.e.isSameFile(z.s(r1), z.s(r2));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ void o(Path r1, Path r2, CopyOption[] r3) {
        this.e.move(z.s(r1), z.s(r2), g.u(r3));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ j$.nio.channels.a p(Path r1, Set r2, ExecutorService r3, FileAttribute[] r4) {
        AsynchronousFileChannel r0 = this.e.newAsynchronousFileChannel(z.s(r1), g.l(r2), r3, g.x(r4));
        if (r0 != null) goto L7;
        return null;
    L7:
        return new j$.nio.channels.a(r0);
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ SeekableByteChannel q(Path r1, Set r2, FileAttribute[] r3) {
        return this.e.newByteChannel(z.s(r1), g.l(r2), g.x(r3));
    }

    @Override // j$.nio.file.spi.d
    public final DirectoryStream r(Path r3, DirectoryStream.Filter r4) {
        return new b0(this.e.newDirectoryStream(z.s(r3), new h(r4, 2)));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ FileChannel s(Path r1, Set r2, FileAttribute[] r3) {
        return this.e.newFileChannel(z.s(r1), g.l(r2), g.x(r3));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ FileSystem t(Path r1, Map r2) {
        return j$.nio.file.g.i(this.e.newFileSystem(z.s(r1), r2));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ FileSystem u(URI r1, Map r2) {
        return j$.nio.file.g.i(this.e.newFileSystem(r1, r2));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ InputStream v(Path r5, OpenOption[] r6) {
        FileSystemProvider r4 = this.e;
        java.nio.file.Path r52 = z.s(r5);
        if (r6 != null) goto L5;
        java.nio.file.OpenOption[] r62 = null;
    L10:
        return r4.newInputStream(r52, r62);
    L5:
        int r0 = r6.length;
        java.nio.file.OpenOption[] r1 = new java.nio.file.OpenOption[r0];
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r1[r2] = w.a(r6[r2]);
        r2 = r2 + 1;
        goto L6
    L8:
        r62 = r1;
        goto L10
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ OutputStream w(Path r5, OpenOption[] r6) {
        FileSystemProvider r4 = this.e;
        java.nio.file.Path r52 = z.s(r5);
        if (r6 != null) goto L5;
        java.nio.file.OpenOption[] r62 = null;
    L10:
        return r4.newOutputStream(r52, r62);
    L5:
        int r0 = r6.length;
        java.nio.file.OpenOption[] r1 = new java.nio.file.OpenOption[r0];
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r1[r2] = w.a(r6[r2]);
        r2 = r2 + 1;
        goto L6
    L8:
        r62 = r1;
        goto L10
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ BasicFileAttributes x(Path r1, Class r2, LinkOption[] r3) {
        return j$.nio.file.attribute.e.a(this.e.readAttributes(z.s(r1), g.i(r2), g.v(r3)));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ Map y(Path r1, String r2, LinkOption[] r3) {
        return g.j(this.e.readAttributes(z.s(r1), r2, g.v(r3)));
    }

    @Override // j$.nio.file.spi.d
    public final /* synthetic */ Path z(Path r1) {
        return y.s(this.e.readSymbolicLink(z.s(r1)));
    }
}
