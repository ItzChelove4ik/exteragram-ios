package j$.nio.file;

import j$.nio.file.attribute.BasicFileAttributes;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemLoopException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class p implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    public final boolean f252a;

    /* renamed from: b, reason: collision with root package name */
    public final LinkOption[] f253b;

    /* renamed from: c, reason: collision with root package name */
    public final int f254c;
    public final ArrayDeque d;
    public boolean e;

    static {
    }

    public p(Collection r5, int r6) {
        this.d = new ArrayDeque();
        Iterator r52 = r5.iterator();
        boolean r1 = false;
    L4:
        if (r52.hasNext() == false) goto L10;
        if (l.f241a[((FileVisitOption) r52.next()).ordinal()] != 1) goto L9;
        r1 = true;
        goto L4
    L9:
        throw new AssertionError("Should not get here");
    L10:
        if (r6 < 0) goto L17;
        this.f252a = r1;
        if (r1 == false) goto L14;
        LinkOption[] r53 = new LinkOption[0];
    L15:
        this.f253b = r53;
        this.f254c = r6;
        return;
    L14:
        r53 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
        goto L15
    L17:
        b.a("'maxDepth' is negative");
        throw null;
    }

    public final BasicFileAttributes a(Path r3) {
        return Files.readAttributes(r3, BasicFileAttributes.class, this.f253b);
    L5:
        e = move-exception;
        if (this.f252a == true) goto L9;
        throw e;
    L9:
        return Files.readAttributes(r3, BasicFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
    }

    public final n b() {
        m r0 = (m) this.d.peek();
        if (r0 != null) goto L6;
        return null;
    L6:
        if (r0.e == true) goto L17;
        Iterator r2 = r0.d;
        if (r2.hasNext() == false) goto L14;
        Path r22 = (Path) r2.next();     // Catch: DirectoryIteratorException -> L12
    L15:
        IOException r3 = null;
    L18:
        if (r22 == null) goto L32;
        n r23 = c(r22, true);
        if (r23 == null) goto L6;
        return r23;
    L32:
        r0.f245c.close();     // Catch: IOException -> L21
    L25:
        this.d.pop();
        return new n(o.END_DIRECTORY, r0.f243a, null, r3);
    L21:
        e = move-exception;
        if (r3 != null) goto L24;
        r3 = e;
        goto L25
    L24:
        r3.addSuppressed(e);
        goto L25
    L14:
        r22 = null;
    L12:
        e = move-exception;
        r3 = e.getCause();
        r22 = null;
        goto L18
    L17:
        r22 = null;
        r3 = null;
        goto L18
    }

    public final n c(Path r7, boolean r8) {
        BasicFileAttributes r1 = a(r7);     // Catch: SecurityException -> L36 IOException -> L40
    L5:
        if (this.d.size() >= this.f254c) goto L35;
        if (r1.isDirectory() == false) goto L35;
        if (this.f252a == false) goto L46;
        Object r2 = r1.fileKey();
        Iterator r3 = this.d.iterator();
    L13:
        if (r3.hasNext() == false) goto L46;
        m r4 = (m) r3.next();
        Object r5 = r4.f244b;
        if (r2 == null) goto L44;
        if (r5 == null) goto L44;
        if (r2.equals(r5) == false) goto L13;
    L23:
        return new n(o.ENTRY, r7, null, new FileSystemLoopException(r7.toString()));
    L44:
        if (Files.isSameFile(r7, r4.f243a) == false) goto L13;
    L46:
        DirectoryStream<Path> r82 = Files.newDirectoryStream(r7);     // Catch: SecurityException -> L27 IOException -> L31
        this.d.push(new m(r7, r1.fileKey(), r82));
        return new n(o.START_DIRECTORY, r7, r1, null);
    L31:
        e = move-exception;
        return new n(o.ENTRY, r7, null, e);
    L27:
        e = move-exception;
        if (r8 == true) goto L38;
        throw e;
    L38:
        return null;
    L35:
        return new n(o.ENTRY, r7, r1, null);
    L40:
        e = move-exception;
        return new n(o.ENTRY, r7, null, e);
    L36:
        e = move-exception;
        if (r8 == true) goto L38;
        throw e;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.e == false) goto L5;
        return;
    L5:
        if (this.d.isEmpty() == true) goto L11;
        if (this.d.isEmpty() == true) goto L5;
        ((m) this.d.pop()).f245c.close();     // Catch: IOException -> L13
        goto L5
    L11:
        this.e = true;
    }
}
