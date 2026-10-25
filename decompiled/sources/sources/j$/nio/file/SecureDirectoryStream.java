package j$.nio.file;

import j$.nio.file.attribute.FileAttributeView;
import java.nio.file.DirectoryStream;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface SecureDirectoryStream<T> extends DirectoryStream<T> {
    void deleteDirectory(T r1);

    void deleteFile(T r1);

    <V extends FileAttributeView> V getFileAttributeView(T r1, Class<V> r2, LinkOption... r3);

    SecureDirectoryStream<T> newDirectoryStream(T r1, LinkOption... r2);
}
