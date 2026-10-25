package j$.nio.file;

import java.security.PrivilegedAction;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class i implements PrivilegedAction {
    @Override // java.security.PrivilegedAction
    public final Object run() {
        j$.nio.file.spi.d r7 = j$.adapter.b.f143a;
        String r0 = System.getProperty("java.nio.file.spi.DefaultFileSystemProvider");
        if (r0 == null) goto L15;
        String[] r02 = r0.split(",");
        int r1 = r02.length;
        int r3 = 0;
    L5:
        if (r3 >= r1) goto L15;
        r7 = (j$.nio.file.spi.d) Class.forName(r02[r3], true, ClassLoader.getSystemClassLoader()).getDeclaredConstructor(new Class[]{j$.nio.file.spi.d.class}).newInstance(new Object[]{r7});     // Catch: Exception -> L12
        if (r7.l().equals("file") == false) goto L11;
        r3 = r3 + 1;     // Catch: Exception -> L12
        goto L5
    L11:
        throw new Error("Default provider must use scheme 'file'");     // Catch: Exception -> L12
    L12:
        e = move-exception;
        throw new Error(e);
    L15:
        return r7;
    }
}
