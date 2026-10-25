package j$.nio.file;

import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.security.AccessController;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class Paths {
    public static Path get(String r1, String... r2) {
        return j.f233a.getPath(r1, r2);
    }

    public static Path get(URI r5) {
        String r0 = r5.getScheme();
        if (r0 != null) goto L5;
        b.a("Missing scheme");
        return null;
    L5:
        if (r0.equalsIgnoreCase("file") == false) goto L9;
        return j.f233a.g().k(r5);
    L9:
        if (j$.nio.file.spi.d.f264b != null) goto L25;
        j$.nio.file.spi.d r1 = j.f233a.g();
        Object r2 = j$.nio.file.spi.d.f263a;
        monitor-enter(r2);
    L17:
        th = move-exception;
        throw th;
    L13:
        if (j$.nio.file.spi.d.f264b == null) goto L15;
    L21:
        monitor-exit(r2);     // Catch: Throwable -> L17
        goto L25
    L15:
        if (j$.nio.file.spi.d.f265c == true) goto L20;
        j$.nio.file.spi.d.f265c = true;     // Catch: Throwable -> L17
        List r3 = (List) AccessController.doPrivileged(new j$.nio.file.spi.a());     // Catch: Throwable -> L17
        r3.add(0, r1);     // Catch: Throwable -> L17
        j$.nio.file.spi.d.f264b = Collections.unmodifiableList(r3);     // Catch: Throwable -> L17
        goto L21
    L20:
        throw new Error("Circular loading of installed providers detected");     // Catch: Throwable -> L17
    L25:
        Iterator r12 = j$.nio.file.spi.d.f264b.iterator();
    L27:
        if (r12.hasNext() == false) goto L33;
        j$.nio.file.spi.d r22 = (j$.nio.file.spi.d) r12.next();
        if (r22.l().equalsIgnoreCase(r0) == false) goto L27;
        return r22.k(r5);
    L33:
        throw new FileSystemNotFoundException("Provider \"" + r0 + "\" not installed");
    }
}
