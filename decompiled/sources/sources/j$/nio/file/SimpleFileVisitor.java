package j$.nio.file;

import j$.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class SimpleFileVisitor<T> implements FileVisitor<T> {
    public SimpleFileVisitor() {
    }

    @Override // j$.nio.file.FileVisitor
    public FileVisitResult postVisitDirectory(T r1, IOException r2) {
        Objects.requireNonNull(r1);
        if (r2 == null) goto L5;
        throw r2;
    L5:
        return FileVisitResult.CONTINUE;
    }

    @Override // j$.nio.file.FileVisitor
    public FileVisitResult preVisitDirectory(T r1, BasicFileAttributes r2) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r2);
        return FileVisitResult.CONTINUE;
    }

    @Override // j$.nio.file.FileVisitor
    public FileVisitResult visitFile(T r1, BasicFileAttributes r2) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r2);
        return FileVisitResult.CONTINUE;
    }

    @Override // j$.nio.file.FileVisitor
    public FileVisitResult visitFileFailed(T r1, IOException r2) {
        Objects.requireNonNull(r1);
        throw r2;
    }
}
