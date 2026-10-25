package j$.desugar.sun.nio.fs;

import j$.nio.file.CopyOption;
import j$.nio.file.FileStore;
import j$.nio.file.FileSystem;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeView;
import j$.nio.file.attribute.FileTime;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class m extends j$.nio.file.spi.d {
    public final String e;
    public final String f;
    public volatile i g;

    public m(String r1) {
        this.e = r1;
        this.f = "/";
    }

    public static void B(URI r2) {
        if (r2.getScheme().equalsIgnoreCase("file") == true) goto L5;
        j$.nio.file.b.a("URI does not match this provider");
        return;
    L5:
        if (r2.getRawAuthority() != null) goto L23;
        String r0 = r2.getPath();
        if (r0 != null) goto L9;
        j$.nio.file.b.a("Path component is undefined");
        return;
    L9:
        if (r0.equals("/") == true) goto L11;
        j$.nio.file.b.a("Path component should be '/'");
        return;
    L11:
        if (r2.getRawQuery() == null) goto L13;
        j$.nio.file.b.a("Query component present");
        return;
    L13:
        if (r2.getRawFragment() != null) goto L15;
        return;
    L15:
        j$.nio.file.b.a("Fragment component present");
        return;
    L23:
        j$.nio.file.b.a("Authority component present");
    }

    public static boolean C(CopyOption[] r4, StandardCopyOption r5) {
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    @Override // j$.nio.file.spi.d
    public final void A(Path r2, String r3, Object r4, LinkOption... r5) {
        int r1 = r3.indexOf(":");
        if (r1 == (-1)) goto L8;
        String r52 = r3.substring(0, r1);
        if ("basic".equals(r52) == false) goto L22;
        r3 = r3.substring(r1 + 1);
        goto L8
    L22:
        j$.nio.file.b.e("Requested attribute type for: ", r52, " is not available.");
        return;
    L8:
        b r12 = new b(r2);
        if (r3.equals("lastModifiedTime") == false) goto L13;
        r12.a((FileTime) r4, null, null);
        return;
    L13:
        if (r3.equals("lastAccessTime") == false) goto L17;
        r12.a(null, (FileTime) r4, null);
        return;
    L17:
        if (r3.equals("creationTime") == false) goto L21;
        FileTime r42 = (FileTime) r4;
        return;
    L21:
        throw new IllegalArgumentException("'basic:" + r3 + "' not recognized");
    }

    @Override // j$.nio.file.spi.d
    public final void a(Path r7, j$.nio.file.a... r8) {
        File r6 = r7.toFile();
        if (r6.exists() == false) goto L23;
        int r0 = r8.length;
        int r2 = 0;
        boolean r3 = true;
    L5:
        if (r2 >= r0) goto L18;
        int r4 = j.f162a[r8[r2].ordinal()];
        if (r4 != 1) goto L9;
        boolean r42 = r6.canRead();
    L14:
        r3 = r3 & r42;
    L17:
        r2 = r2 + 1;
        goto L5
    L9:
        if (r4 != 2) goto L11;
        r42 = r6.canWrite();
        goto L14
    L11:
        if (r4 != 3) goto L17;
        r42 = r6.canExecute();
        goto L14
    L18:
        if (r3 == false) goto L21;
        return;
    L21:
        throw new IOException(String.format("Unable to access file %s", new Object[]{r7}));
    L23:
        throw new NoSuchFileException(r7.toString());
    }

    @Override // j$.nio.file.spi.d
    public final void b(Path r3, Path r4, CopyOption... r5) {
        if (C(r5, StandardCopyOption.REPLACE_EXISTING) == true) goto L10;
        if (Files.exists(r4, new LinkOption[0]) == false) goto L10;
        throw new FileAlreadyExistsException(r4.toString());
    L10:
        if (C(r5, StandardCopyOption.ATOMIC_MOVE) == true) goto L37;
        FileInputStream r2 = new FileInputStream(r3.toFile());
        FileOutputStream r32 = new FileOutputStream(r4.toFile());     // Catch: Throwable -> L24
        byte[] r52 = new byte[8192];     // Catch: Throwable -> L19
    L15:
        int r1 = r2.read(r52, 0, 8192);     // Catch: Throwable -> L19
        if (r1 < 0) goto L21;
        r32.write(r52, 0, r1);     // Catch: Throwable -> L19
        goto L15
    L21:
        r32.close();     // Catch: Throwable -> L24
        r2.close();
        return;
    L19:
        th = move-exception;
        r32.close();     // Catch: Throwable -> L28
    L30:
        throw th;     // Catch: Throwable -> L24
    L28:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L33
    L35:
        throw th;
    L33:
        th = move-exception;
        th.addSuppressed(th);
        goto L35
    L37:
        throw new UnsupportedOperationException("Unsupported copy option");
    }

    @Override // j$.nio.file.spi.d
    public final void c(Path r1, FileAttribute... r2) {
        if (r1.getParent() == null) goto L10;
        if (Files.exists(r1.getParent(), new LinkOption[0]) == true) goto L10;
        throw new NoSuchFileException(r1.toString());
    L10:
        if (r1.toFile().mkdirs() == false) goto L13;
        return;
    L13:
        throw new FileAlreadyExistsException(r1.toString());
    }

    @Override // j$.nio.file.spi.d
    public final void d(Path r1, Path r2) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.spi.d
    public final void e(Path r1, Path r2, FileAttribute... r3) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.spi.d
    public final void f(Path r2) {
        a(r2, new j$.nio.file.a[0]);     // Catch: IOException -> L6
        g(r2);
        return;
    L7:
        throw new NoSuchFileException(r2.toString());
    }

    @Override // j$.nio.file.spi.d
    public final boolean g(Path r1) {
        return r1.toFile().delete();
    }

    @Override // j$.nio.file.spi.d
    public final FileAttributeView h(Path r1, Class r2, LinkOption... r3) {
        r2.getClass();
        if (r2 == BasicFileAttributeView.class) goto L5;
        return null;
    L5:
        return (FileAttributeView) r2.cast(new b(r1));
    }

    @Override // j$.nio.file.spi.d
    public final FileStore i(Path r1) {
        throw new SecurityException("getFileStore");
    }

    @Override // j$.nio.file.spi.d
    public final FileSystem j(URI r3) {
        B(r3);
        i r32 = this.g;
        if (r32 != null) goto L14;
        monitor-enter(this);
        i r33 = this.g;     // Catch: Throwable -> L8
        if (r33 != null) goto L10;
        r33 = new i(this, this.e, this.f);     // Catch: Throwable -> L8
        this.g = r33;     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
        return r33;
    L8:
        th = move-exception;
        throw th;
    L14:
        return r32;
    }

    @Override // j$.nio.file.spi.d
    public final Path k(URI r12) {
        i r0 = this.g;
        String r1 = this.e;
        String r11 = this.f;
        char[] r2 = p.f172c;
        if (r12.isAbsolute() == true) goto L5;
        j$.nio.file.b.a("URI is not absolute");
        return null;
    L5:
        if (r12.isOpaque() == true) goto L53;
        String r22 = r12.getScheme();
        if (r22 != null) goto L9;
    L51:
        j$.nio.file.b.a("URI scheme is not \"file\"");
        return null;
    L9:
        if (r22.equalsIgnoreCase("file") == false) goto L51;
        if (r12.getRawAuthority() == null) goto L13;
        j$.nio.file.b.a("URI has an authority component");
        return null;
    L13:
        if (r12.getRawFragment() == null) goto L15;
        j$.nio.file.b.a("URI has a fragment component");
        return null;
    L15:
        if (r12.getRawQuery() != null) goto L45;
        String r122 = r12.getRawPath();
        int r23 = r122.length();
        if (r23 != 0) goto L19;
        j$.nio.file.b.a("URI path component is empty");
        return null;
    L19:
        if (r122.endsWith("/") == true) goto L21;
    L23:
        byte[] r4 = new byte[r23];
        int r5 = 0;
        int r6 = 0;
    L24:
        if (r5 >= r23) goto L39;
        int r7 = r5 + 1;
        char r8 = r122.charAt(r5);
        if (r8 != '%') goto L32;
        int r82 = r5 + 2;
        r5 = r5 + 3;
        byte r72 = (byte) ((p.a(r122.charAt(r7)) << 4) | p.a(r122.charAt(r82)));
        if (r72 != 0) goto L36;
        j$.nio.file.b.a("Nul character not allowed");
        return null;
    L36:
        r4[r6] = r72;
        r6 = r6 + 1;
        goto L24
    L32:
        if (r8 == 0) goto L37;
        if (r8 >= 128) goto L37;
        r72 = (byte) r8;
        r5 = r7;
    L37:
        j$.nio.file.b.a("Bad escape");
        return null;
    L39:
        if (r6 == r23) goto L42;
        r4 = Arrays.copyOf(r4, r6);
    L42:
        return new o(r0, new String(r4, q.f173a), r1, r11);
    L21:
        if (r23 <= 1) goto L23;
        r23 = r23 - 1;
        goto L23
    L45:
        j$.nio.file.b.a("URI has a query component");
        return null;
    L53:
        j$.nio.file.b.a("URI is not hierarchical");
        return null;
    }

    @Override // j$.nio.file.spi.d
    public final String l() {
        return "file";
    }

    @Override // j$.nio.file.spi.d
    public final boolean m(Path r1) {
        return r1.toFile().isHidden();
    }

    @Override // j$.nio.file.spi.d
    public final boolean n(Path r3, Path r4) {
        if (r3.equals(r4) == false) goto L6;
        return true;
    L6:
        a(r3, new j$.nio.file.a[0]);
        a(r4, new j$.nio.file.a[0]);
        return r3.toFile().equals(r4.toFile());
    }

    @Override // j$.nio.file.spi.d
    public final void o(Path r1, Path r2, CopyOption... r3) {
        if (C(r3, StandardCopyOption.REPLACE_EXISTING) == true) goto L10;
        if (Files.exists(r2, new LinkOption[0]) == false) goto L10;
        throw new FileAlreadyExistsException(r2.toString());
    L10:
        if (C(r3, StandardCopyOption.COPY_ATTRIBUTES) == true) goto L14;
        r1.toFile().renameTo(r2.toFile());
        return;
    L14:
        throw new UnsupportedOperationException("Unsupported copy option");
    }

    @Override // j$.nio.file.spi.d
    public final j$.nio.channels.a p(Path r1, Set r2, ExecutorService r3, FileAttribute... r4) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.spi.d
    public final SeekableByteChannel q(Path r1, Set r2, FileAttribute... r3) {
        return s(r1, r2, r3);
    }

    @Override // j$.nio.file.spi.d
    public final DirectoryStream r(Path r3, DirectoryStream.Filter r4) {
        k r0 = new k();
        r0.f163a = new l(this, r3, r4);
        return r0;
    }

    @Override // j$.nio.file.spi.d
    public final FileChannel s(Path r1, Set r2, FileAttribute... r3) {
        if (r1.toFile().isDirectory() == true) goto L7;
        return g.z(r1, r2);
    L7:
        throw new UnsupportedOperationException("The desugar library does not support creating a file channel on a directory: " + r1);
    }

    @Override // j$.nio.file.spi.d
    public final FileSystem u(URI r1, Map r2) {
        B(r1);
        throw new FileSystemAlreadyExistsException();
    }

    @Override // j$.nio.file.spi.d
    public final BasicFileAttributes x(Path r2, Class r3, LinkOption... r4) {
        if (r3 != BasicFileAttributes.class) goto L7;
        return (BasicFileAttributes) r3.cast(((BasicFileAttributeView) h(r2, BasicFileAttributeView.class, r4)).readAttributes());
    L7:
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.spi.d
    public final Map y(Path r6, String r7, LinkOption... r8) {
        int r5 = r7.indexOf(":");
        int r1 = 0;
        if (r5 != (-1)) goto L5;
        String[] r52 = r7.split(",");
    L8:
        b r72 = new b(r6);
        Set r62 = b.f147b;
        a r82 = new a();
        r82.f144a = new HashSet();
        r82.f145b = new HashMap();
        int r2 = r52.length;
    L9:
        if (r1 >= r2) goto L19;
        String r3 = r52[r1];
        if (r3.equals("*") == false) goto L14;
        r82.f146c = true;
    L16:
        r1 = r1 + 1;
        goto L9
    L14:
        if (((HashSet) r62).contains(r3) == false) goto L18;
        ((HashSet) r82.f144a).add(r3);
        goto L16
    L18:
        throw new IllegalArgumentException("'" + r3 + "' not recognized");
    L19:
        BasicFileAttributes r53 = r72.readAttributes();
        if (r82.b("size") == false) goto L23;
        r82.a("size", Long.valueOf(((c) r53).h));
    L23:
        if (r82.b("creationTime") == false) goto L26;
        r82.a("creationTime", ((c) r53).f151c);
    L26:
        if (r82.b("lastAccessTime") == false) goto L29;
        r82.a("lastAccessTime", ((c) r53).f150b);
    L29:
        if (r82.b("lastModifiedTime") == false) goto L32;
        r82.a("lastModifiedTime", ((c) r53).f149a);
    L32:
        if (r82.b("fileKey") == false) goto L35;
        r82.a("fileKey", ((c) r53).i);
    L35:
        if (r82.b("isDirectory") == false) goto L38;
        r82.a("isDirectory", Boolean.valueOf(((c) r53).e));
    L38:
        if (r82.b("isRegularFile") == false) goto L41;
        r82.a("isRegularFile", Boolean.valueOf(((c) r53).d));
    L41:
        if (r82.b("isSymbolicLink") == false) goto L44;
        r82.a("isSymbolicLink", Boolean.valueOf(((c) r53).f));
    L44:
        if (r82.b("isOther") == false) goto L47;
        r82.a("isOther", Boolean.valueOf(((c) r53).g));
    L47:
        return Collections.unmodifiableMap(r82.f145b);
    L5:
        String r83 = r7.substring(0, r5);
        if ("basic".equals(r83) == false) goto L48;
        r52 = r7.substring(r5 + 1).split(",");
        goto L8
    L48:
        j$.nio.file.b.e("Requested attribute type for: ", r83, " is not available.");
        return null;
    }

    @Override // j$.nio.file.spi.d
    public final Path z(Path r4) {
        return new o(this.g, r4.toFile().getCanonicalPath(), this.e, this.f);
    }
}
