package j$.desugar.sun.nio.fs;

import j$.nio.file.FileSystem;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.n0;
import j$.nio.file.o0;
import j$.nio.file.p0;
import j$.nio.file.q0;
import j$.nio.file.x;
import java.io.File;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class o implements Path {
    public static final Pattern h = null;

    /* renamed from: a, reason: collision with root package name */
    public final FileSystem f167a;

    /* renamed from: b, reason: collision with root package name */
    public final String f168b;

    /* renamed from: c, reason: collision with root package name */
    public final List f169c;
    public final boolean d;
    public final String e;
    public final String f;
    public volatile byte[] g;

    static {
        h = Pattern.compile("/+");
    }

    public o(FileSystem r11, String r12, String r13, String r14) {
        boolean r3 = r12.startsWith("/");
        if (r12.isEmpty() == false) goto L6;
        List r122 = Collections.singletonList(_UrlKt.FRAGMENT_ENCODE_SET);
    L7:
        this(r11, r3, r122, r13, r14);
        return;
    L6:
        r122 = (List) Arrays.stream(h.split(r12)).filter(new n()).collect(new j$.util.stream.e(new j$.util.stream.a(), new j$.util.stream.b(), new j$.util.stream.c(), new j$.util.stream.d(), j$.util.stream.f.f494a));
        goto L7
    }

    public o(FileSystem r1, boolean r2, List r3, String r4, String r5) {
        this.f167a = r1;
        this.d = r2;
        this.f169c = r3;
        if (r2 == false) goto L5;
        String r12 = "/";
    L6:
        this.f168b = r12.concat(g.o(r3));
        this.e = r4;
        this.f = r5;
        return;
    L5:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public final o D() {
        int r0 = this.f169c.size();
        if (r0 != 0) goto L5;
        return null;
    L5:
        if (r0 == 1) goto L7;
    L9:
        StringBuilder r2 = new StringBuilder();
        if (this.d == false) goto L12;
        r2.append("/");
    L12:
        List r1 = this.f169c;
        r2.append(g.o(r1.subList(0, r0 - 1)));
        String r02 = r2.toString();
        return new o(this.f167a, r02, this.e, this.f);
    L7:
        if (this.d == true) goto L9;
        return null;
    }

    public final o I(Path r5) {
        if ((r5 instanceof o) == false) goto L11;
        if (((o) r5).d == false) goto L9;
        return (o) r5;
    L9:
        return new o(this.f167a, this.f168b + "/" + r5, this.e, this.f);
    L11:
        throw new IllegalArgumentException(String.format("Expected to resolve paths on the same file system as DesugarUnixPath, but gets %s (%s).", new Object[]{r5, r5.getFileSystem()}));
    }

    public final o K(Path r2) {
        Objects.requireNonNull(r2);
        if ((r2 instanceof o) == false) goto L11;
        o r1 = D();
        if (r1 != null) goto L9;
        return (o) r2;
    L9:
        return r1.I(r2);
    L11:
        throw new IllegalArgumentException(String.format("Expected to resolve paths on the same file system as DesugarUnixPath, but gets %s (%s).", new Object[]{r2, r2.getFileSystem()}));
    }

    public final o S() {
        if (this.d == false) goto L5;
        return this;
    L5:
        FileSystem r1 = this.f167a;
        String r2 = this.e;
        return new o(r1, r2, r2, this.f).I(this);
    }

    @Override // j$.nio.file.Path, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Path r1) {
        return u(r1);
    }

    @Override // j$.nio.file.Path
    public final boolean endsWith(String r5) {
        return y(new o(this.f167a, r5, this.e, this.f));
    }

    @Override // j$.nio.file.Path
    public final boolean equals(Object r3) {
        if ((r3 instanceof o) == true) goto L6;
        return false;
    L6:
        if (u((o) r3) != 0) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // j$.nio.file.Path
    public final Path getFileName() {
        if (this.f169c.isEmpty() == false) goto L9;
        if (this.d == false) goto L10;
        return null;
    L10:
        return this;
    L9:
        return new o(this.f167a, (String) this.f169c.get(r2.size() - 1), this.e, this.f);
    }

    @Override // j$.nio.file.Path
    public final FileSystem getFileSystem() {
        return this.f167a;
    }

    @Override // j$.nio.file.Path
    public final /* bridge */ /* synthetic */ Path getName(int r1) {
        return s(r1);
    }

    @Override // j$.nio.file.Path
    public final int getNameCount() {
        return this.f169c.size();
    }

    @Override // j$.nio.file.Path
    public final /* bridge */ /* synthetic */ Path getParent() {
        return D();
    }

    @Override // j$.nio.file.Path
    public final Path getRoot() {
        if (this.d == false) goto L6;
        FileSystem r1 = this.f167a;
        String r2 = this.f;
        return new o(r1, r2, this.e, r2);
    L6:
        return null;
    }

    public final int hashCode() {
        return this.f168b.hashCode();
    }

    @Override // j$.nio.file.Path
    public final boolean isAbsolute() {
        return this.d;
    }

    @Override // j$.nio.file.Path, java.lang.Iterable
    public final Iterator iterator() {
        return new x(this);
    }

    @Override // j$.nio.file.Path
    public final Path normalize() {
        ArrayDeque r0 = new ArrayDeque();
        Iterator r1 = this.f169c.iterator();
    L4:
        if (r1.hasNext() == false) goto L11;
        String r2 = (String) r1.next();
        r2.getClass();
        if (r2.equals(".") == true) goto L4;
        if (r2.equals("..") == false) goto L9;
        r0.removeLast();
        goto L4
    L9:
        r0.add(r2);
        goto L4
    L11:
        FileSystem r22 = this.f167a;
        if (this.d == false) goto L14;
        String r3 = "/";
    L16:
        return new o(r22, r3.concat(g.o(r0)), this.e, this.f);
    L14:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L16
    }

    @Override // j$.nio.file.Path
    public final p0 q(q0 r1, n0[] r2, o0... r3) {
        throw new UnsupportedOperationException("Watch Service is not supported");
    }

    @Override // j$.nio.file.Path
    public final Path relativize(Path r10) {
        if ((r10 instanceof o) == false) goto L22;
        if (this.d != ((o) r10).d) goto L19;
        List r102 = ((o) r10).f169c;
        int r0 = this.f169c.size();
        int r1 = r102.size();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L12;
        if (r2 >= r1) goto L12;
        if (((String) this.f169c.get(r2)).equals(r102.get(r2)) == false) goto L12;
        r2 = r2 + 1;
    L12:
        ArrayList r6 = new ArrayList();
        int r3 = r2;
    L13:
        if (r3 >= r0) goto L15;
        r6.add("..");
        r3 = r3 + 1;
    L15:
        if (r2 >= r1) goto L18;
        r6.add((String) r102.get(r2));
        r2 = r2 + 1;
        goto L15
    L18:
        return new o(this.f167a, false, r6, this.e, this.f);
    L19:
        j$.nio.file.b.a("'other' is different type of Path in absolute property.");
        return null;
    L22:
        throw new IllegalArgumentException(String.format("Expected to resolve paths on the same file system as DesugarUnixPath, but gets %s (%s).", new Object[]{r10, r10.getFileSystem()}));
    }

    @Override // j$.nio.file.Path
    public final /* bridge */ /* synthetic */ Path resolve(Path r1) {
        return I(r1);
    }

    @Override // j$.nio.file.Path
    public final Path resolveSibling(String r5) {
        return K(new o(this.f167a, r5, this.e, this.f));
    }

    public final o s(int r4) {
        if (r4 < 0) goto L8;
        if (r4 >= this.f169c.size()) goto L8;
        return new o(this.f167a, (String) this.f169c.get(r4), this.e, this.f);
    L8:
        throw new IllegalArgumentException(String.format("Requested name for index (%d) is out of bound in \n%s.", new Object[]{Integer.valueOf(r4), this}));
    }

    @Override // j$.nio.file.Path
    public final boolean startsWith(Path r6) {
        Objects.requireNonNull(r6);
        if ((r6 instanceof o) == true) goto L6;
        return false;
    L6:
        if (this.d == r6.isAbsolute()) goto L8;
        return false;
    L8:
        int r0 = r6.getNameCount();
        if (this.f169c.size() >= r0) goto L11;
        return false;
    L11:
        int r2 = 0;
    L12:
        if (r2 >= r0) goto L17;
        if (s(r2).equals(r6.getName(r2)) == false) goto L15;
        r2 = r2 + 1;
        goto L12
    L15:
        return false;
    L17:
        return true;
    }

    @Override // j$.nio.file.Path
    public final boolean startsWith(String r5) {
        return startsWith(new o(this.f167a, r5, this.e, this.f));
    }

    @Override // j$.nio.file.Path
    public final Path subpath(int r4, int r5) {
        return new o(this.f167a, g.o(this.f169c.subList(r4, r5)), this.e, this.f);
    }

    @Override // j$.nio.file.Path
    public final /* bridge */ /* synthetic */ Path toAbsolutePath() {
        return S();
    }

    @Override // j$.nio.file.Path
    public final File toFile() {
        return new File(this.f168b);
    }

    @Override // j$.nio.file.Path
    public final Path toRealPath(LinkOption[] r4) {
        this.f167a.g().a(this, new j$.nio.file.a[]{j$.nio.file.a.READ});
        if (Arrays.asList(r4).contains(LinkOption.NOFOLLOW_LINKS) == false) goto L7;
        return S();
    L7:
        return new o(this.f167a, toFile().getCanonicalPath(), this.e, this.f);
    }

    @Override // j$.nio.file.Path
    public final String toString() {
        return this.f168b;
    }

    @Override // j$.nio.file.Path
    public final URI toUri() {
        char[] r0 = p.f172c;
        o r02 = S();
        if (r02.g != null) goto L5;
        r02.g = r02.f168b.getBytes(q.f173a);
    L5:
        byte[] r03 = r02.g;
        StringBuilder r1 = new StringBuilder("file:///");
        int r3 = 1;
    L7:
        if (r3 >= r03.length) goto L21;
        char r4 = (char) (r03[r3] & UByte.MAX_VALUE);
        long r5 = p.f170a;
        long r7 = p.f171b;
        if (r4 >= '@') goto L14;
        if ((r5 & (1 << r4)) != 0) goto L17;
    L18:
        r1.append('%');
        char[] r52 = p.f172c;
        r1.append(r52[(r4 >> 4) & 15]);
        r1.append(r52[r4 & 15]);
    L19:
        r3 = r3 + 1;
    L17:
        r1.append(r4);
        goto L19
    L14:
        if (r4 >= 128) goto L18;
        if (((1 << (r4 - '@')) & r7) == 0) goto L18;
    L21:
        if (r1.charAt(r1.length() - 1) != '/') goto L23;
    L30:
        return new URI(r1.toString());
    L27:
        e = move-exception;
        throw new AssertionError(e);
    L23:
        if (toFile().isDirectory() == false) goto L30;
        r1.append('/');
        goto L30
    }

    @Override // j$.nio.file.Path
    public final int u(Path r1) {
        return this.f168b.compareTo(((o) r1).f168b);
    }

    @Override // j$.nio.file.Path
    public final boolean y(Path r7) {
        Objects.requireNonNull(r7);
        if ((r7 instanceof o) == true) goto L6;
        return false;
    L6:
        if (r7.isAbsolute() == true) goto L8;
        int r0 = r7.getNameCount();
        if (this.f169c.size() >= r0) goto L12;
        return false;
    L12:
        int r2 = this.f169c.size();
        int r3 = r0 - 1;
    L13:
        if (r3 < 0) goto L18;
        if (s((r3 - r0) + r2).equals(r7.getName(r3)) == false) goto L16;
        r3 = r3 - 1;
        goto L13
    L16:
        return false;
    L18:
        return true;
    L8:
        return equals(r7);
    }

    @Override // j$.nio.file.Path
    public final /* bridge */ /* synthetic */ Path z(Path r1) {
        return K(r1);
    }
}
