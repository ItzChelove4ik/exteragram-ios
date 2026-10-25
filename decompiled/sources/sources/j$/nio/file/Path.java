package j$.nio.file;

import java.io.File;
import java.net.URI;
import java.util.Iterator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface Path extends Comparable<Path>, Iterable<Path> {
    default p0 Q(q0 r2, n0... r3) {
        return q(r2, r3, new o0[0]);
    }

    @Override // java.lang.Comparable
    /* bridge */ /* synthetic */ default int compareTo(Path r1) {
        return u(r1);
    }

    default boolean endsWith(String r3) {
        return y(getFileSystem().getPath(r3, new String[0]));
    }

    boolean equals(Object r1);

    Path getFileName();

    FileSystem getFileSystem();

    Path getName(int r1);

    int getNameCount();

    Path getParent();

    Path getRoot();

    boolean isAbsolute();

    default Iterator iterator() {
        return new x(this);
    }

    Path normalize();

    p0 q(q0 r1, n0[] r2, o0... r3);

    Path relativize(Path r1);

    Path resolve(Path r1);

    default Path resolve(String r3) {
        return resolve(getFileSystem().getPath(r3, new String[0]));
    }

    default Path resolveSibling(String r3) {
        return z(getFileSystem().getPath(r3, new String[0]));
    }

    boolean startsWith(Path r1);

    default boolean startsWith(String r3) {
        return startsWith(getFileSystem().getPath(r3, new String[0]));
    }

    Path subpath(int r1, int r2);

    Path toAbsolutePath();

    default File toFile() {
        if (getFileSystem() != j.f233a) goto L7;
        return new File(toString());
    L7:
        throw new UnsupportedOperationException("Path not associated with default file system.");
    }

    Path toRealPath(LinkOption... r1);

    String toString();

    URI toUri();

    int u(Path r1);

    boolean y(Path r1);

    default Path z(Path r1) {
        r1.getClass();
        Path r0 = getParent();
        if (r0 != null) goto L6;
        return r1;
    L6:
        return r0.resolve(r1);
    }
}
