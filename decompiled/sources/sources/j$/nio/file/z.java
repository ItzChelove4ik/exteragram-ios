package j$.nio.file;

import java.io.File;
import java.net.URI;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class z implements java.nio.file.Path {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Path f272a;

    public /* synthetic */ z(Path r1) {
        this.f272a = r1;
    }

    public static /* synthetic */ java.nio.file.Path s(Path r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof y) == false) goto L10;
        return ((y) r1).f271a;
    L10:
        return new z(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(java.nio.file.Path r1) {
        return this.f272a.compareTo(j$.desugar.sun.nio.fs.g.f(r1));
    }

    @Override // java.nio.file.Path
    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public final /* synthetic */ int compareTo2(java.nio.file.Path r1) {
        return this.f272a.u(y.s(r1));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ boolean endsWith(String r1) {
        return this.f272a.endsWith(r1);
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ boolean endsWith(java.nio.file.Path r1) {
        return this.f272a.y(y.s(r1));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ boolean equals(Object r2) {
        Path r1 = this.f272a;
        if ((r2 instanceof z) == false) goto L6;
        r2 = ((z) r2).f272a;
    L6:
        return r1.equals(r2);
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ void forEach(Consumer<? super java.nio.file.Path> r1) {
        this.f272a.forEach(r1);
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path getFileName() {
        return s(this.f272a.getFileName());
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.FileSystem getFileSystem() {
        return h.a(this.f272a.getFileSystem());
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path getName(int r1) {
        return s(this.f272a.getName(r1));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ int getNameCount() {
        return this.f272a.getNameCount();
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path getParent() {
        return s(this.f272a.getParent());
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path getRoot() {
        return s(this.f272a.getRoot());
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ int hashCode() {
        return this.f272a.hashCode();
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ boolean isAbsolute() {
        return this.f272a.isAbsolute();
    }

    @Override // java.nio.file.Path, java.lang.Iterable
    public final Iterator iterator() {
        return new d0(this.f272a.iterator());
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path normalize() {
        return s(this.f272a.normalize());
    }

    @Override // java.nio.file.Path, java.nio.file.Watchable
    public final /* synthetic */ WatchKey register(WatchService r1, WatchEvent.Kind[] r2) {
        p0 r0 = this.f272a.Q(q0.a(r1), j$.desugar.sun.nio.fs.g.s(r2));
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.f255a;
    }

    @Override // java.nio.file.Path, java.nio.file.Watchable
    public final /* synthetic */ WatchKey register(WatchService r7, WatchEvent.Kind[] r8, WatchEvent.Modifier[] r9) {
        Path r6 = this.f272a;
        q0 r72 = q0.a(r7);
        n0[] r82 = j$.desugar.sun.nio.fs.g.s(r8);
        if (r9 != null) goto L5;
        o0[] r2 = null;
    L12:
        p0 r62 = r6.q(r72, r82, r2);
        if (r62 != null) goto L16;
        return null;
    L16:
        return r62.f255a;
    L5:
        int r1 = r9.length;
        r2 = new o0[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L12;
        WatchEvent.Modifier r4 = r9[r3];
        if (r4 != null) goto L10;
        o0 r5 = null;
    L11:
        r2[r3] = r5;
        r3 = r3 + 1;
        goto L6
    L10:
        r5 = new o0(r4);
        goto L11
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path relativize(java.nio.file.Path r1) {
        return s(this.f272a.relativize(y.s(r1)));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path resolve(String r1) {
        return s(this.f272a.resolve(r1));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path resolve(java.nio.file.Path r1) {
        return s(this.f272a.resolve(y.s(r1)));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path resolveSibling(String r1) {
        return s(this.f272a.resolveSibling(r1));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path resolveSibling(java.nio.file.Path r1) {
        return s(this.f272a.z(y.s(r1)));
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Spliterator<java.nio.file.Path> spliterator() {
        return this.f272a.spliterator();
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ boolean startsWith(String r1) {
        return this.f272a.startsWith(r1);
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ boolean startsWith(java.nio.file.Path r1) {
        return this.f272a.startsWith(y.s(r1));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path subpath(int r1, int r2) {
        return s(this.f272a.subpath(r1, r2));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path toAbsolutePath() {
        return s(this.f272a.toAbsolutePath());
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ File toFile() {
        return this.f272a.toFile();
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ java.nio.file.Path toRealPath(java.nio.file.LinkOption[] r1) {
        return s(this.f272a.toRealPath(j$.desugar.sun.nio.fs.g.r(r1)));
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ String toString() {
        return this.f272a.toString();
    }

    @Override // java.nio.file.Path
    public final /* synthetic */ URI toUri() {
        return this.f272a.toUri();
    }
}
