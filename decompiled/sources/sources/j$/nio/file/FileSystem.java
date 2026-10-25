package j$.nio.file;

import java.io.Closeable;
import java.util.Set;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class FileSystem implements Closeable {
    public abstract Iterable a();

    public abstract g0 b(String r1);

    public abstract Iterable c();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract j$.nio.file.attribute.y d();

    public abstract boolean e();

    public abstract q0 f();

    public abstract j$.nio.file.spi.d g();

    public abstract Path getPath(String r1, String... r2);

    public abstract String getSeparator();

    public abstract Set h();

    public abstract boolean isOpen();
}
