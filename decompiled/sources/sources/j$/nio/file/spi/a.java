package j$.nio.file.spi;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class a implements PrivilegedAction {
    @Override // java.security.PrivilegedAction
    public final Object run() {
        ArrayList r6 = new ArrayList();
        Iterator r0 = ServiceLoader.load(d.class, ClassLoader.getSystemClassLoader()).iterator();
    L4:
        if (r0.hasNext() == false) goto L13;
        d r1 = (d) r0.next();
        String r2 = r1.l();
        if (r2.equalsIgnoreCase("file") == true) goto L4;
        int r3 = r6.size();
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L12;
        Object r5 = r6.get(r4);
        r4 = r4 + 1;
        if (((d) r5).l().equalsIgnoreCase(r2) == false) goto L8;
    L12:
        r6.add(r1);
        goto L4
    L13:
        return r6;
    }
}
