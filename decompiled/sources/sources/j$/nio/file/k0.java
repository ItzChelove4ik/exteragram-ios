package j$.nio.file;

import j$.nio.file.attribute.FileAttribute;
import java.nio.file.InvalidPathException;
import java.security.AccessController;
import java.security.SecureRandom;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class k0 {

    /* renamed from: a, reason: collision with root package name */
    public static final Path f238a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final boolean f239b = false;

    /* renamed from: c, reason: collision with root package name */
    public static final SecureRandom f240c = null;

    static {
        if (System.getSecurityManager() != null) goto L5;
        String r0 = System.getProperty("java.io.tmpdir");
    L6:
        FileSystem r2 = j.f233a;
        f238a = r2.getPath(r0, new String[0]);
        f239b = r2.h().contains("posix");
        f240c = new SecureRandom();
        return;
    L5:
        j$.sun.security.action.a r02 = new j$.sun.security.action.a();
        r02.f279b = "java.io.tmpdir";
        r0 = (String) AccessController.doPrivileged(r02);
        goto L6
    }

    public static Path a(Path r5, String r6, String r7, boolean r8, FileAttribute[] r9) {
        if (r6 != null) goto L5;
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if (r7 != null) goto L9;
        if (r8 == false) goto L8;
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    L8:
        r7 = ".tmp";
    L9:
        Path r0 = f238a;
        if (r5 != null) goto L13;
        r5 = r0;
    L13:
        if (f239b == true) goto L15;
    L34:
        SecurityManager r1 = System.getSecurityManager();
    L54:
        Path r2 = b(r6, r7, r5);     // Catch: InvalidPathException -> L48
        if (r8 == false) goto L42;
        return Files.createDirectory(r2, r9);
    L42:
        return Files.createFile(r2, r9);
    L39:
        e = move-exception;
        if (r5 != r0) goto L47;
        if (r1 == null) goto L47;
        throw new SecurityException("Unable to create temporary file or directory");
    L47:
        throw e;
    L48:
        e = move-exception;
        if (r1 == null) goto L52;
        b.a("Invalid prefix or suffix");
        return null;
    L52:
        throw e;
    L15:
        if (r5.getFileSystem() != j.f233a) goto L34;
        if (r9.length != 0) goto L22;
        if (r8 == false) goto L20;
        j$.desugar.sun.nio.fs.h r92 = j0.f235b;
    L21:
        r9 = new FileAttribute[]{r92};
        goto L34
    L20:
        r92 = j0.f234a;
        goto L21
    L22:
        int r12 = 0;
    L24:
        if (r12 >= r9.length) goto L29;
        if (r9[r12].name().equals("posix:permissions") == true) goto L34;
        r12 = r12 + 1;
        goto L24
    L29:
        int r13 = r9.length;
        FileAttribute[] r3 = new FileAttribute[r13 + 1];
        System.arraycopy(r9, 0, r3, 0, r9.length);
        if (r8 == false) goto L32;
        j$.desugar.sun.nio.fs.h r93 = j0.f235b;
    L33:
        r3[r13] = r93;
        r9 = r3;
        goto L34
    L32:
        r93 = j0.f234a;
        goto L33
    }

    public static Path b(String r12, String r13, Path r14) {
        long r0 = f240c.nextLong();
        if (r0 != 0) goto L6;
        String r02 = MVEL.VERSION_SUB;
    L13:
        Path r122 = r14.getFileSystem().getPath(r12 + r02 + r13, new String[0]);
        if (r122.getParent() == null) goto L16;
        b.a("Invalid prefix or suffix");
        return null;
    L16:
        return r14.resolve(r122);
    L6:
        if (r0 <= 0) goto L8;
        r02 = Long.toString(r0, 10);
        goto L13
    L8:
        char[] r4 = new char[64];
        long r6 = (r0 >>> 1) / 5;
        int r1 = 63;
        r4[63] = Character.forDigit((int) (r0 - (r6 * 10)), 10);
    L10:
        if (r6 <= 0) goto L12;
        r1 = r1 - 1;
        r4[r1] = Character.forDigit((int) (r6 % 10), 10);
        r6 = r6 / 10;
        goto L10
    L12:
        r02 = new String(r4, r1, 64 - r1);
        goto L13
    }
}
