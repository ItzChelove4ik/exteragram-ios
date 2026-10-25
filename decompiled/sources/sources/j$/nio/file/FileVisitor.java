package j$.nio.file;

import j$.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface FileVisitor<T> {
    FileVisitResult postVisitDirectory(Object r1, IOException r2);

    FileVisitResult preVisitDirectory(Object r1, BasicFileAttributes r2);

    FileVisitResult visitFile(Object r1, BasicFileAttributes r2);

    FileVisitResult visitFileFailed(Object r1, IOException r2);
}
