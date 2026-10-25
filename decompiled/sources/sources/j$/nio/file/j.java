package j$.nio.file;

import java.net.URI;
import java.security.AccessController;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class j {

    /* renamed from: a, reason: collision with root package name */
    public static final FileSystem f233a = null;

    static {
        f233a = ((j$.nio.file.spi.d) AccessController.doPrivileged(new i())).j(URI.create("file:///"));
    }
}
