package java.nio.file;

import java.io.Closeable;
import java.util.Iterator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface DirectoryStream<T> extends Closeable, Iterable<T> {

    /* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
    public interface Filter<T> {
        boolean accept(T r1);
    }

    Iterator<T> iterator();
}
