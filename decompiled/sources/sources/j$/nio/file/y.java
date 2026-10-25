package j$.nio.file;

import java.io.File;
import java.net.URI;
import java.nio.file.WatchEvent;
import java.nio.file.WatchService;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class y implements Path {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.Path f271a;

    public /* synthetic */ y(java.nio.file.Path r1) {
        this.f271a = r1;
    }

    public static /* synthetic */ Path s(java.nio.file.Path r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof z) == false) goto L10;
        return ((z) r1).f272a;
    L10:
        return new y(r1);
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ p0 Q(q0 r1, n0[] r2) {
        java.nio.file.Path r0 = this.f271a;
        if (r1 != null) goto L5;
        WatchService r12 = null;
    L7:
        return p0.a(r0.register(r12, j$.desugar.sun.nio.fs.g.w(r2)));
    L5:
        r12 = r1.f257a;
        goto L7
    }

    @Override // j$.nio.file.Path, java.lang.Comparable
    public final /* synthetic */ int compareTo(Path r1) {
        return this.f271a.compareTo(j$.desugar.sun.nio.fs.g.f(r1));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ boolean endsWith(String r1) {
        return this.f271a.endsWith(r1);
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.Path r1 = this.f271a;
        if ((r2 instanceof y) == false) goto L6;
        r2 = ((y) r2).f271a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ void forEach(Consumer<? super Path> r1) {
        this.f271a.forEach(r1);
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path getFileName() {
        return s(this.f271a.getFileName());
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ FileSystem getFileSystem() {
        return g.i(this.f271a.getFileSystem());
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path getName(int r1) {
        return s(this.f271a.getName(r1));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ int getNameCount() {
        return this.f271a.getNameCount();
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path getParent() {
        return s(this.f271a.getParent());
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path getRoot() {
        return s(this.f271a.getRoot());
    }

    public final /* synthetic */ int hashCode() {
        return this.f271a.hashCode();
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ boolean isAbsolute() {
        return this.f271a.isAbsolute();
    }

    @Override // j$.nio.file.Path, java.lang.Iterable
    public final Iterator iterator() {
        return new d0(this.f271a.iterator());
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path normalize() {
        return s(this.f271a.normalize());
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ p0 q(q0 r6, n0[] r7, o0[] r8) {
        java.nio.file.Path r5 = this.f271a;
        WatchEvent.Modifier[] r0 = null;
        if (r6 != null) goto L5;
        WatchService r62 = null;
    L6:
        WatchEvent.Kind<?>[] r72 = j$.desugar.sun.nio.fs.g.w(r7);
        if (r8 == null) goto L18;
        int r1 = r8.length;
        WatchEvent.Modifier[] r2 = new WatchEvent.Modifier[r1];
        int r3 = 0;
    L10:
        if (r3 >= r1) goto L16;
        o0 r4 = r8[r3];
        if (r4 != null) goto L14;
        WatchEvent.Modifier r42 = null;
    L15:
        r2[r3] = r42;
        r3 = r3 + 1;
        goto L10
    L14:
        r42 = r4.f251a;
        goto L15
    L16:
        r0 = r2;
    L18:
        return p0.a(r5.register(r62, r72, r0));
    L5:
        r62 = r6.f257a;
        goto L6
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path relativize(Path r1) {
        return s(this.f271a.relativize(z.s(r1)));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path resolve(Path r1) {
        return s(this.f271a.resolve(z.s(r1)));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path resolve(String r1) {
        return s(this.f271a.resolve(r1));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path resolveSibling(String r1) {
        return s(this.f271a.resolveSibling(r1));
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Spliterator<Path> spliterator() {
        return this.f271a.spliterator();
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ boolean startsWith(Path r1) {
        return this.f271a.startsWith(z.s(r1));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ boolean startsWith(String r1) {
        return this.f271a.startsWith(r1);
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path subpath(int r1, int r2) {
        return s(this.f271a.subpath(r1, r2));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path toAbsolutePath() {
        return s(this.f271a.toAbsolutePath());
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ File toFile() {
        return this.f271a.toFile();
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path toRealPath(LinkOption[] r1) {
        return s(this.f271a.toRealPath(j$.desugar.sun.nio.fs.g.v(r1)));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ String toString() {
        return this.f271a.toString();
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ URI toUri() {
        return this.f271a.toUri();
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ int u(Path r1) {
        return this.f271a.compareTo(z.s(r1));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ boolean y(Path r1) {
        return this.f271a.endsWith(z.s(r1));
    }

    @Override // j$.nio.file.Path
    public final /* synthetic */ Path z(Path r1) {
        return s(this.f271a.resolveSibling(z.s(r1)));
    }
}
