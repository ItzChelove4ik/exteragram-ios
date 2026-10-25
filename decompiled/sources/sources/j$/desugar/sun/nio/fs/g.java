package j$.desugar.sun.nio.fs;

import com.google.android.gms.cast.CastStatusCodes;
import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileTime;
import j$.nio.file.attribute.PosixFilePermission;
import j$.nio.file.attribute.t;
import j$.nio.file.attribute.u;
import j$.nio.file.attribute.v;
import j$.nio.file.i0;
import j$.nio.file.l0;
import j$.nio.file.m0;
import j$.nio.file.n0;
import j$.nio.file.w;
import j$.nio.file.y;
import j$.nio.file.z;
import j$.time.ZoneId;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class g {
    public g() {
    }

    public static j$.time.a A() {
        return new j$.time.a(ZoneId.systemDefault());
    }

    public static String a(long r9, String r11, Locale r12) {
        TimeZone r0 = TimeZone.getTimeZone("UTC");
        SimpleDateFormat r1 = new SimpleDateFormat(r11, r12);
        r1.setTimeZone(r0);
        Calendar r2 = Calendar.getInstance();
        r2.setTimeZone(r0);
        r2.set(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED, 1, (int) r9, 0, 0, 0);
        return r1.format(r2.getTime());
    }

    public static String b(long r9, String r11, Locale r12) {
        TimeZone r0 = TimeZone.getTimeZone("UTC");
        SimpleDateFormat r1 = new SimpleDateFormat(r11, r12);
        r1.setTimeZone(r0);
        Calendar r2 = Calendar.getInstance();
        r2.setTimeZone(r0);
        r2.set(0, (int) r9, 0, 0, 0, 0);
        return r1.format(r2.getTime());
    }

    public static FileTime c(java.nio.file.attribute.FileTime r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        return FileTime.fromMillis(r2.toMillis());
    }

    public static /* synthetic */ StandardOpenOption d(j$.nio.file.StandardOpenOption r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if (r1 != j$.nio.file.StandardOpenOption.READ) goto L10;
        return StandardOpenOption.READ;
    L10:
        if (r1 != j$.nio.file.StandardOpenOption.WRITE) goto L14;
        return StandardOpenOption.WRITE;
    L14:
        if (r1 != j$.nio.file.StandardOpenOption.APPEND) goto L18;
        return StandardOpenOption.APPEND;
    L18:
        if (r1 != j$.nio.file.StandardOpenOption.TRUNCATE_EXISTING) goto L22;
        return StandardOpenOption.TRUNCATE_EXISTING;
    L22:
        if (r1 != j$.nio.file.StandardOpenOption.CREATE) goto L26;
        return StandardOpenOption.CREATE;
    L26:
        if (r1 != j$.nio.file.StandardOpenOption.CREATE_NEW) goto L30;
        return StandardOpenOption.CREATE_NEW;
    L30:
        if (r1 != j$.nio.file.StandardOpenOption.DELETE_ON_CLOSE) goto L34;
        return StandardOpenOption.DELETE_ON_CLOSE;
    L34:
        if (r1 != j$.nio.file.StandardOpenOption.SPARSE) goto L38;
        return StandardOpenOption.SPARSE;
    L38:
        if (r1 != j$.nio.file.StandardOpenOption.SYNC) goto L42;
        return StandardOpenOption.SYNC;
    L42:
        return StandardOpenOption.DSYNC;
    }

    public static java.nio.file.attribute.FileTime e(FileTime r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        return java.nio.file.attribute.FileTime.fromMillis(r2.toMillis());
    }

    public static Object f(Object r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof Path) == false) goto L10;
        return z.s((Path) r1);
    L10:
        if ((r1 instanceof java.nio.file.Path) == true) goto L12;
        return r1;
    L12:
        return y.s((java.nio.file.Path) r1);
    }

    public static void g(Path r9, Path r10, CopyOption... r11) {
        int r0 = r11.length;
        int r3 = 0;
        boolean r5 = false;
        boolean r6 = false;
        boolean r4 = true;
    L3:
        if (r3 >= r0) goto L16;
        CopyOption r7 = r11[r3];
        if (r7 != StandardCopyOption.REPLACE_EXISTING) goto L8;
        r5 = true;
    L13:
        r3 = r3 + 1;
        goto L3
    L8:
        if (r7 != LinkOption.NOFOLLOW_LINKS) goto L11;
        r4 = false;
        goto L13
    L11:
        if (r7 != StandardCopyOption.COPY_ATTRIBUTES) goto L14;
        r6 = true;
        goto L13
    L14:
        r7.getClass();
        j$.nio.file.b.e("'", r7, "' is not a recognized copy option");
        return;
    L16:
        if (r4 == false) goto L18;
        LinkOption[] r112 = new LinkOption[0];
    L19:
        BasicFileAttributes r113 = Files.readAttributes(r9, BasicFileAttributes.class, r112);
        if (r113.isSymbolicLink() == true) goto L52;
        if (r5 == false) goto L24;
        Files.deleteIfExists(r10);
    L26:
        if (r113.isDirectory() == false) goto L28;
        Files.createDirectory(r10, new FileAttribute[0]);
    L31:
        if (r6 == true) goto L55;
        return;
    L55:
        ((BasicFileAttributeView) Files.getFileAttributeView(r10, BasicFileAttributeView.class, new LinkOption[0])).a(r113.lastModifiedTime(), r113.lastAccessTime(), r113.creationTime());     // Catch: Throwable -> L35
        return;
    L35:
        th = move-exception;
        Files.delete(r10);     // Catch: Throwable -> L38
    L40:
        throw th;
    L38:
        th = move-exception;
        th.addSuppressed(th);
        goto L40
    L28:
        InputStream r92 = Files.newInputStream(r9, new OpenOption[0]);
        Files.a(r92, r10, new CopyOption[0]);     // Catch: Throwable -> L42
        r92.close();
    L42:
        th = move-exception;
        if (r92 != null) goto L57;
    L48:
        throw th;
    L57:
        r92.close();     // Catch: Throwable -> L46
    L46:
        th = move-exception;
        th.addSuppressed(th);
        goto L48
    L24:
        if (Files.exists(r10, new LinkOption[0]) == false) goto L26;
        throw new FileAlreadyExistsException(r10.toString());
    L52:
        throw new IOException("Copying of symbolic links not supported");
    L18:
        r112 = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
        goto L19
    }

    public static Class h(Class r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (r3 != java.nio.file.attribute.BasicFileAttributeView.class) goto L8;
        return BasicFileAttributeView.class;
    L8:
        if (r3 != BasicFileAttributeView.class) goto L12;
        return java.nio.file.attribute.BasicFileAttributeView.class;
    L12:
        if (r3 != PosixFileAttributeView.class) goto L14;
        return t.class;
    L14:
        if (r3 != t.class) goto L18;
        return PosixFileAttributeView.class;
    L18:
        if (r3 != FileOwnerAttributeView.class) goto L20;
        return j$.nio.file.attribute.p.class;
    L20:
        if (r3 != j$.nio.file.attribute.p.class) goto L24;
        return FileOwnerAttributeView.class;
    L24:
        if (r3 != j$.nio.file.attribute.g.class) goto L28;
        return DosFileAttributeView.class;
    L28:
        if (r3 != DosFileAttributeView.class) goto L31;
        return j$.nio.file.attribute.g.class;
    L31:
        if (r3 != v.class) goto L35;
        return UserDefinedFileAttributeView.class;
    L35:
        if (r3 != UserDefinedFileAttributeView.class) goto L38;
        return v.class;
    L38:
        if (r3 != j$.nio.file.attribute.a.class) goto L42;
        return AclFileAttributeView.class;
    L42:
        if (r3 != AclFileAttributeView.class) goto L44;
        return j$.nio.file.attribute.a.class;
    L44:
        j$.util.a.a("java.nio.file.attribute.FileAttributeView", r3);
        throw null;
    }

    public static Class i(Class r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (r3 != java.nio.file.attribute.BasicFileAttributes.class) goto L8;
        return BasicFileAttributes.class;
    L8:
        if (r3 != BasicFileAttributes.class) goto L12;
        return java.nio.file.attribute.BasicFileAttributes.class;
    L12:
        if (r3 != PosixFileAttributes.class) goto L14;
        return u.class;
    L14:
        if (r3 != u.class) goto L18;
        return PosixFileAttributes.class;
    L18:
        if (r3 != j$.nio.file.attribute.h.class) goto L22;
        return DosFileAttributes.class;
    L22:
        if (r3 != DosFileAttributes.class) goto L24;
        return j$.nio.file.attribute.h.class;
    L24:
        j$.util.a.a("java.nio.file.attribute.BasicFileAttributes", r3);
        throw null;
    }

    public static Map j(Map r4) {
        if (r4 != null) goto L4;
    L11:
        return r4;
    L4:
        if (r4.isEmpty() == true) goto L11;
        HashMap r0 = new HashMap();
        Iterator r1 = r4.keySet().iterator();
    L8:
        if (r1.hasNext() == false) goto L10;
        String r2 = (String) r1.next();
        r0.put(r2, k(r4.get(r2)));
        goto L8
    L10:
        return r0;
    }

    public static Object k(Object r3) {
        if ((r3 instanceof java.nio.file.attribute.FileTime) == false) goto L11;
        return c((java.nio.file.attribute.FileTime) r3);
    L7:
        e = move-exception;
        j$.util.a.a("java.nio.file.attribute.FileTime", e);
        throw null;
    L11:
        if ((r3 instanceof FileTime) == true) goto L19;
        return r3;
    L19:
        return e((FileTime) r3);
    L15:
        e = move-exception;
        j$.util.a.a("java.nio.file.attribute.FileTime", e);
        throw null;
    }

    public static Set l(Set r5) {
        if (r5 != null) goto L4;
    L31:
        return r5;
    L4:
        if (r5.isEmpty() == true) goto L31;
        HashSet r0 = new HashSet();
        Object r1 = r5.iterator().next();
        if ((r1 instanceof OpenOption) == false) goto L18;
        Iterator r52 = r5.iterator();
    L10:
        if (r52.hasNext() == false) goto L28;
        r0.add(w.a((OpenOption) r52.next()));
    L14:
        e = move-exception;
        j$.util.a.a("java.nio.file.OpenOption", e);
        throw null;
    L28:
        return r0;
    L18:
        if ((r1 instanceof java.nio.file.OpenOption) == false) goto L29;
        Iterator r53 = r5.iterator();
    L21:
        if (r53.hasNext() == false) goto L28;
        r0.add(j$.nio.file.v.a((java.nio.file.OpenOption) r53.next()));
    L25:
        e = move-exception;
        j$.util.a.a("java.nio.file.OpenOption", e);
        throw null;
    L29:
        j$.util.a.a("java.nio.file.OpenOption", r1.getClass());
        throw null;
    }

    public static Set m(Set r5) {
        if (r5 != null) goto L4;
    L85:
        return r5;
    L4:
        if (r5.isEmpty() == true) goto L85;
        HashSet r0 = new HashSet();
        Object r1 = r5.iterator().next();
        if ((r1 instanceof PosixFilePermission) == false) goto L45;
        Iterator r52 = r5.iterator();
    L10:
        if (r52.hasNext() == false) goto L82;
        PosixFilePermission r12 = (PosixFilePermission) r52.next();     // Catch: ClassCastException -> L41
        if (r12 != null) goto L16;
        java.nio.file.attribute.PosixFilePermission r13 = null;
    L40:
        r0.add(r13);
        goto L10
    L16:
        if (r12 != PosixFilePermission.OWNER_READ) goto L19;
        r13 = java.nio.file.attribute.PosixFilePermission.OWNER_READ;
        goto L40
    L19:
        if (r12 != PosixFilePermission.OWNER_WRITE) goto L22;
        r13 = java.nio.file.attribute.PosixFilePermission.OWNER_WRITE;
        goto L40
    L22:
        if (r12 != PosixFilePermission.OWNER_EXECUTE) goto L25;
        r13 = java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE;
        goto L40
    L25:
        if (r12 != PosixFilePermission.GROUP_READ) goto L28;
        r13 = java.nio.file.attribute.PosixFilePermission.GROUP_READ;
        goto L40
    L28:
        if (r12 != PosixFilePermission.GROUP_WRITE) goto L31;
        r13 = java.nio.file.attribute.PosixFilePermission.GROUP_WRITE;
        goto L40
    L31:
        if (r12 != PosixFilePermission.GROUP_EXECUTE) goto L34;
        r13 = java.nio.file.attribute.PosixFilePermission.GROUP_EXECUTE;
        goto L40
    L34:
        if (r12 != PosixFilePermission.OTHERS_READ) goto L37;
        r13 = java.nio.file.attribute.PosixFilePermission.OTHERS_READ;
        goto L40
    L37:
        if (r12 != PosixFilePermission.OTHERS_WRITE) goto L39;
        r13 = java.nio.file.attribute.PosixFilePermission.OTHERS_WRITE;
        goto L40
    L39:
        r13 = java.nio.file.attribute.PosixFilePermission.OTHERS_EXECUTE;
    L41:
        e = move-exception;
        j$.util.a.a("java.nio.file.attribute.PosixFilePermission", e);
        throw null;
    L82:
        return r0;
    L45:
        if ((r1 instanceof java.nio.file.attribute.PosixFilePermission) == false) goto L83;
        Iterator r53 = r5.iterator();
    L48:
        if (r53.hasNext() == false) goto L82;
        java.nio.file.attribute.PosixFilePermission r14 = (java.nio.file.attribute.PosixFilePermission) r53.next();     // Catch: ClassCastException -> L79
        if (r14 != null) goto L54;
        PosixFilePermission r15 = null;
    L78:
        r0.add(r15);
        goto L48
    L54:
        if (r14 != java.nio.file.attribute.PosixFilePermission.OWNER_READ) goto L57;
        r15 = PosixFilePermission.OWNER_READ;
        goto L78
    L57:
        if (r14 != java.nio.file.attribute.PosixFilePermission.OWNER_WRITE) goto L60;
        r15 = PosixFilePermission.OWNER_WRITE;
        goto L78
    L60:
        if (r14 != java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE) goto L63;
        r15 = PosixFilePermission.OWNER_EXECUTE;
        goto L78
    L63:
        if (r14 != java.nio.file.attribute.PosixFilePermission.GROUP_READ) goto L66;
        r15 = PosixFilePermission.GROUP_READ;
        goto L78
    L66:
        if (r14 != java.nio.file.attribute.PosixFilePermission.GROUP_WRITE) goto L69;
        r15 = PosixFilePermission.GROUP_WRITE;
        goto L78
    L69:
        if (r14 != java.nio.file.attribute.PosixFilePermission.GROUP_EXECUTE) goto L72;
        r15 = PosixFilePermission.GROUP_EXECUTE;
        goto L78
    L72:
        if (r14 != java.nio.file.attribute.PosixFilePermission.OTHERS_READ) goto L75;
        r15 = PosixFilePermission.OTHERS_READ;
        goto L78
    L75:
        if (r14 != java.nio.file.attribute.PosixFilePermission.OTHERS_WRITE) goto L77;
        r15 = PosixFilePermission.OTHERS_WRITE;
        goto L78
    L77:
        r15 = PosixFilePermission.OTHERS_EXECUTE;
    L79:
        e = move-exception;
        j$.util.a.a("java.nio.file.attribute.PosixFilePermission", e);
        throw null;
    L83:
        j$.util.a.a("java.nio.file.attribute.PosixFilePermission", r1.getClass());
        throw null;
    }

    public static boolean n(Object r1) {
        if ((r1 instanceof Set) == false) goto L12;
        Set r12 = (Set) r1;
        if (r12.isEmpty() == true) goto L15;
        Object r13 = r12.iterator().next();
        if ((r13 instanceof PosixFilePermission) == false) goto L9;
        return true;
    L9:
        if ((r13 instanceof java.nio.file.attribute.PosixFilePermission) == false) goto L16;
        return true;
    L16:
        return false;
    L15:
        return false;
    L12:
        return false;
    }

    public static /* synthetic */ String o(Iterable r2) {
        StringBuilder r0 = new StringBuilder();
        Iterator r22 = r2.iterator();
        if (r22.hasNext() == false) goto L8;
    L4:
        r0.append((CharSequence) r22.next());
        if (r22.hasNext() == false) goto L8;
        r0.append("/");
    L8:
        return r0.toString();
    }

    public static /* synthetic */ List p(Object[] r4) {
        ArrayList r0 = new ArrayList(r4.length);
        int r1 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        Object r3 = r4[r2];
        Objects.requireNonNull(r3);
        r0.add(r3);
        r2 = r2 + 1;
        goto L3
    L6:
        return Collections.unmodifiableList(r0);
    }

    public static /* synthetic */ Set q(Object[] r5) {
        HashSet r0 = new HashSet(r5.length);
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        Object r3 = r5[r2];
        Objects.requireNonNull(r3);
        if (r0.add(r3) == false) goto L8;
        r2 = r2 + 1;
        goto L3
    L8:
        throw new IllegalArgumentException("duplicate element: " + r3);
    L10:
        return Collections.unmodifiableSet(r0);
    }

    public static /* synthetic */ LinkOption[] r(java.nio.file.LinkOption[] r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.length;
        LinkOption[] r2 = new LinkOption[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L12;
        if (r5[r3] != null) goto L10;
        LinkOption r4 = null;
    L11:
        r2[r3] = r4;
        r3 = r3 + 1;
        goto L6
    L10:
        r4 = LinkOption.NOFOLLOW_LINKS;
        goto L11
    L12:
        return r2;
    }

    public static n0[] s(WatchEvent.Kind[] r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = r6.length;
        n0[] r2 = new n0[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L27;
        WatchEvent.Kind r4 = r6[r3];
        if (r4 != null) goto L11;
        n0 r42 = null;
    L26:
        r2[r3] = r42;
        r3 = r3 + 1;
        goto L6
    L11:
        if (r4 != StandardWatchEventKinds.ENTRY_CREATE) goto L14;
        r42 = i0.f231b;
        goto L26
    L14:
        if (r4 != StandardWatchEventKinds.ENTRY_DELETE) goto L17;
        r42 = i0.f232c;
        goto L26
    L17:
        if (r4 != StandardWatchEventKinds.ENTRY_MODIFY) goto L20;
        r42 = i0.d;
        goto L26
    L20:
        if (r4 != StandardWatchEventKinds.OVERFLOW) goto L23;
        r42 = i0.f230a;
        goto L26
    L23:
        if ((r4 instanceof m0) == false) goto L25;
        r42 = ((m0) r4).f246a;
        goto L26
    L25:
        r42 = new l0(r4);
        goto L26
    L27:
        return r2;
    }

    public static FileAttribute[] t(java.nio.file.attribute.FileAttribute[] r7) {
        if (r7 != null) goto L5;
        return null;
    L5:
        int r1 = r7.length;
        FileAttribute[] r2 = new FileAttribute[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L18;
        java.nio.file.attribute.FileAttribute r4 = r7[r3];
        if (r4 != null) goto L11;
        FileAttribute r5 = null;
    L17:
        r2[r3] = r5;
        r3 = r3 + 1;
        goto L6
    L11:
        if (n(r4.value()) == false) goto L14;
        r5 = new h(r4, 3);
        goto L17
    L14:
        if ((r4 instanceof j$.nio.file.attribute.j) == false) goto L16;
        r5 = ((j$.nio.file.attribute.j) r4).f200a;
        goto L17
    L16:
        r5 = new j$.nio.file.attribute.i(r4);
        goto L17
    L18:
        return r2;
    }

    public static /* synthetic */ java.nio.file.CopyOption[] u(CopyOption[] r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = r6.length;
        java.nio.file.CopyOption[] r2 = new java.nio.file.CopyOption[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L27;
        CopyOption r4 = r6[r3];
        if (r4 != null) goto L11;
        java.nio.file.CopyOption r42 = null;
    L26:
        r2[r3] = r42;
        r3 = r3 + 1;
        goto L6
    L11:
        if ((r4 instanceof j$.nio.file.c) == false) goto L14;
        r42 = ((j$.nio.file.c) r4).f217a;
        goto L26
    L14:
        if ((r4 instanceof LinkOption) == false) goto L17;
        r42 = java.nio.file.LinkOption.NOFOLLOW_LINKS;
        goto L26
    L17:
        if ((r4 instanceof StandardCopyOption) == false) goto L25;
        StandardCopyOption r43 = (StandardCopyOption) r4;
        if (r43 != StandardCopyOption.REPLACE_EXISTING) goto L22;
        r42 = java.nio.file.StandardCopyOption.REPLACE_EXISTING;
        goto L26
    L22:
        if (r43 != StandardCopyOption.COPY_ATTRIBUTES) goto L24;
        r42 = java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
        goto L26
    L24:
        r42 = java.nio.file.StandardCopyOption.ATOMIC_MOVE;
        goto L26
    L25:
        r42 = new j$.nio.file.d(r4);
        goto L26
    L27:
        return r2;
    }

    public static /* synthetic */ java.nio.file.LinkOption[] v(LinkOption[] r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.length;
        java.nio.file.LinkOption[] r2 = new java.nio.file.LinkOption[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L12;
        if (r5[r3] != null) goto L10;
        java.nio.file.LinkOption r4 = null;
    L11:
        r2[r3] = r4;
        r3 = r3 + 1;
        goto L6
    L10:
        r4 = java.nio.file.LinkOption.NOFOLLOW_LINKS;
        goto L11
    L12:
        return r2;
    }

    public static WatchEvent.Kind[] w(n0[] r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = r6.length;
        WatchEvent.Kind[] r2 = new WatchEvent.Kind[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L27;
        n0 r4 = r6[r3];
        if (r4 != null) goto L11;
        WatchEvent.Kind r42 = null;
    L26:
        r2[r3] = r42;
        r3 = r3 + 1;
        goto L6
    L11:
        if (r4 != i0.f231b) goto L14;
        r42 = StandardWatchEventKinds.ENTRY_CREATE;
        goto L26
    L14:
        if (r4 != i0.f232c) goto L17;
        r42 = StandardWatchEventKinds.ENTRY_DELETE;
        goto L26
    L17:
        if (r4 != i0.d) goto L20;
        r42 = StandardWatchEventKinds.ENTRY_MODIFY;
        goto L26
    L20:
        if (r4 != i0.f230a) goto L23;
        r42 = StandardWatchEventKinds.OVERFLOW;
        goto L26
    L23:
        if ((r4 instanceof l0) == false) goto L25;
        r42 = ((l0) r4).f242a;
        goto L26
    L25:
        r42 = new m0(r4);
        goto L26
    L27:
        return r2;
    }

    public static java.nio.file.attribute.FileAttribute[] x(FileAttribute[] r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = r6.length;
        java.nio.file.attribute.FileAttribute[] r2 = new java.nio.file.attribute.FileAttribute[r1];
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L18;
        FileAttribute r4 = r6[r3];
        if (r4 != null) goto L11;
        java.nio.file.attribute.FileAttribute r5 = null;
    L17:
        r2[r3] = r5;
        r3 = r3 + 1;
        goto L6
    L11:
        if (n(r4.value()) == false) goto L14;
        r5 = new j$.nio.file.attribute.k(r4);
        goto L17
    L14:
        if ((r4 instanceof j$.nio.file.attribute.i) == false) goto L16;
        r5 = ((j$.nio.file.attribute.i) r4).f199a;
        goto L17
    L16:
        r5 = new j$.nio.file.attribute.j(r4);
        goto L17
    L18:
        return r2;
    }

    public static char y(String r1, int r2) {
        if (r2 < r1.length()) goto L5;
        return 0;
    L5:
        return r1.charAt(r2);
    }

    public static FileChannel z(Path r5, Set r6) {
        Iterator r0 = r6.iterator();
    L4:
        if (r0.hasNext() == false) goto L7;
        ((OpenOption) r0.next()).getClass();
        goto L4
    L7:
        if (r5.toFile().exists() == false) goto L16;
        if (r6.contains(j$.nio.file.StandardOpenOption.CREATE_NEW) == false) goto L23;
        if (r6.contains(j$.nio.file.StandardOpenOption.WRITE) == false) goto L23;
        throw new FileAlreadyExistsException(r5.toString());
    L23:
        if (r6.contains(j$.nio.file.StandardOpenOption.READ) == true) goto L25;
    L29:
        j$.nio.file.StandardOpenOption r02 = j$.nio.file.StandardOpenOption.APPEND;
        if (r6.contains(r02) == true) goto L32;
    L36:
        File r2 = r5.toFile();
        j$.nio.file.StandardOpenOption r3 = j$.nio.file.StandardOpenOption.WRITE;
        if (r6.contains(r3) == true) goto L42;
        if (r6.contains(r02) == true) goto L42;
        String r4 = "r";
    L48:
        RandomAccessFile r1 = new RandomAccessFile(r2, r4);
        if (r6.contains(j$.nio.file.StandardOpenOption.TRUNCATE_EXISTING) == false) goto L54;
        if (r6.contains(r3) == false) goto L54;
        r1.setLength(0);
    L54:
        if (r6.contains(r02) == false) goto L56;
    L59:
        FileChannel r12 = r1.getChannel();
        int r22 = e.e;
        if ((r12 instanceof e) == false) goto L63;
        r12 = ((e) r12).f153a;
    L63:
        return new e(r12, r6.contains(j$.nio.file.StandardOpenOption.DELETE_ON_CLOSE), r6.contains(r02), r5);
    L56:
        if (r6.contains(j$.nio.file.StandardOpenOption.DELETE_ON_CLOSE) == true) goto L59;
        return r1.getChannel();
    L42:
        if (r6.contains(j$.nio.file.StandardOpenOption.SYNC) == false) goto L45;
        r4 = "rws";
        goto L48
    L45:
        if (r6.contains(j$.nio.file.StandardOpenOption.DSYNC) == false) goto L47;
        r4 = "rwd";
        goto L48
    L47:
        r4 = "rw";
        goto L48
    L32:
        if (r6.contains(j$.nio.file.StandardOpenOption.TRUNCATE_EXISTING) == false) goto L36;
        j$.nio.file.b.a("APPEND + TRUNCATE_EXISTING not allowed");
        return null;
    L25:
        if (r6.contains(j$.nio.file.StandardOpenOption.APPEND) == false) goto L29;
        j$.nio.file.b.a("READ + APPEND not allowed");
        return null;
    L16:
        if (r6.contains(j$.nio.file.StandardOpenOption.CREATE) == true) goto L23;
        if (r6.contains(j$.nio.file.StandardOpenOption.CREATE_NEW) == true) goto L23;
        throw new NoSuchFileException(r5.toString());
    }
}
