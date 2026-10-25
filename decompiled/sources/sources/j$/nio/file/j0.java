package j$.nio.file;

import j$.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class j0 {

    /* renamed from: a, reason: collision with root package name */
    public static final j$.desugar.sun.nio.fs.h f234a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final j$.desugar.sun.nio.fs.h f235b = null;

    static {
        PosixFilePermission r0 = PosixFilePermission.OWNER_READ;
        PosixFilePermission r1 = PosixFilePermission.OWNER_WRITE;
        HashSet r3 = new HashSet(EnumSet.of(r0, r1));
        Iterator r2 = r3.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        ((PosixFilePermission) r2.next()).getClass();
        goto L4
    L6:
        f234a = new j$.desugar.sun.nio.fs.h(r3, 4);
        HashSet r12 = new HashSet(EnumSet.of(r0, r1, PosixFilePermission.OWNER_EXECUTE));
        Iterator r02 = r12.iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        ((PosixFilePermission) r02.next()).getClass();
        goto L8
    L10:
        f235b = new j$.desugar.sun.nio.fs.h(r12, 4);
    }
}
