package j$.nio.file;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeView;
import j$.nio.file.attribute.FileTime;
import j$.nio.file.attribute.PosixFilePermission;
import j$.nio.file.attribute.UserPrincipal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import okhttp3.internal.url._UrlKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class Files {

    /* renamed from: a, reason: collision with root package name */
    public static final Set f181a = null;

    static {
        f181a = j$.desugar.sun.nio.fs.g.q(new Object[]{StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE});
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(InputStream r6, Path r7, CopyOption... r8) {
        Objects.requireNonNull(r6);
        int r0 = r8.length;
        int r2 = 0;
        boolean r3 = false;
    L4:
        if (r2 >= r0) goto L13;
        CopyOption r32 = r8[r2];
        if (r32 != StandardCopyOption.REPLACE_EXISTING) goto L8;
        r2 = r2 + 1;
        r3 = true;
        goto L4
    L8:
        if (r32 != null) goto L12;
        throw new NullPointerException("options contains 'null'");
    L12:
        throw new UnsupportedOperationException(r32 + " not supported");
    L13:
        if (r3 == true) goto L44;
    L18:
        e = null;
    L42:
        OutputStream r72 = newOutputStream(r7, new OpenOption[]{StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE});     // Catch: FileAlreadyExistsException -> L35
    L28:
        th = move-exception;
        if (r72 != null) goto L40;
    L34:
        throw th;
    L40:
        r72.close();     // Catch: Throwable -> L32
    L32:
        th = move-exception;
        th.addSuppressed(th);
        goto L34
    L22:
        if ((r6 instanceof InputStreamRetargetInterface) == false) goto L24;
        ((InputStreamRetargetInterface) r6).transferTo(r72);     // Catch: Throwable -> L28
    L25:
        if (r72 == null) goto L48;
        r72.close();
        return;
    L48:
        return;
    L24:
        DesugarInputStream.transferTo(r6, r72);     // Catch: Throwable -> L28
    L35:
        e = move-exception;
        if (e == null) goto L38;
        throw e;
    L38:
        throw e;
    L44:
        deleteIfExists(r7);     // Catch: SecurityException -> L16
    L16:
        e = e;
        goto L42
    }

    public static j$.nio.file.spi.d b(Path r0) {
        return r0.getFileSystem().g();
    }

    public static Path copy(Path r2, Path r3, CopyOption... r4) {
        j$.nio.file.spi.d r0 = b(r2);
        if (b(r3).equals(r0) == false) goto L6;
        r0.b(r2, r3, r4);
        return r3;
    L6:
        j$.desugar.sun.nio.fs.g.g(r2, r3, r4);
        return r3;
    }

    public static Path createDirectories(Path r5, FileAttribute<?>... r6) {
        createDirectory(r5, r6);     // Catch: FileAlreadyExistsException -> L4 IOException -> L9
    L7:
        return r5;
    L4:
        e = move-exception;
    L35:
        e = move-exception;
        throw e;
    L6:
        if (isDirectory(r5, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}) == true) goto L7;
        throw e;     // Catch: IOException -> L9 FileAlreadyExistsException -> L35
    L40:
        r5 = r5.toAbsolutePath();     // Catch: SecurityException -> L12
        e = null;
    L13:
        Path r2 = r5.getParent();
    L14:
        if (r2 == null) goto L18;
        r2.getFileSystem().g().a(r2, new a[0]);     // Catch: NoSuchFileException -> L17
    L17:
        r2 = r2.getParent();
    L18:
        if (r2 != null) goto L23;
        if (e == null) goto L21;
        throw e;
    L21:
        throw new FileSystemException(r5.toString(), null, "Unable to determine if root directory exists");
    L23:
        Iterator r0 = r2.relativize(r5).iterator();
    L25:
        if (r0.hasNext() == false) goto L34;
        r2 = r2.resolve((Path) r0.next());
        createDirectory(r2, r6);     // Catch: FileAlreadyExistsException -> L29
    L29:
        e = move-exception;
        if (isDirectory(r2, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}) == true) goto L25;
        throw e;
    L34:
        return r5;
    L12:
        e = e;
        goto L13
    }

    public static Path createDirectory(Path r1, FileAttribute<?>... r2) {
        b(r1).c(r1, r2);
        return r1;
    }

    public static Path createFile(Path r2, FileAttribute<?>... r3) {
        Set r0 = f181a;
        b(r2).q(r2, r0, r3).close();
        return r2;
    }

    public static Path createLink(Path r1, Path r2) {
        b(r1).d(r1, r2);
        return r1;
    }

    public static Path createSymbolicLink(Path r1, Path r2, FileAttribute<?>... r3) {
        b(r1).e(r1, r2, r3);
        return r1;
    }

    public static Path createTempDirectory(Path r2, String r3, FileAttribute<?>... r4) {
        Objects.requireNonNull(r2);
        return k0.a(r2, r3, null, true, r4);
    }

    public static Path createTempDirectory(String r2, FileAttribute<?>... r3) {
        return k0.a(null, r2, null, true, r3);
    }

    public static Path createTempFile(Path r1, String r2, String r3, FileAttribute<?>... r4) {
        Objects.requireNonNull(r1);
        return k0.a(r1, r2, r3, false, r4);
    }

    public static Path createTempFile(String r2, String r3, FileAttribute<?>... r4) {
        return k0.a(null, r2, r3, false, r4);
    }

    public static void delete(Path r1) {
        b(r1).f(r1);
    }

    public static boolean deleteIfExists(Path r1) {
        return b(r1).g(r1);
    }

    public static boolean exists(Path r6, LinkOption... r7) {
        if (r7.length != 0) goto L18;
        b(r6);
    L18:
        int r1 = r7.length;     // Catch: IOException -> L17
        int r3 = 0;
        boolean r4 = true;
    L7:
        if (r3 >= r1) goto L13;
        LinkOption r42 = r7[r3];     // Catch: IOException -> L17
        if (r42 != LinkOption.NOFOLLOW_LINKS) goto L11;
        r3 = r3 + 1;     // Catch: IOException -> L17
        r4 = false;
        goto L7
    L11:
        r42.getClass();     // Catch: IOException -> L17
        throw new AssertionError("Should not get here");     // Catch: IOException -> L17
    L13:
        if (r4 == false) goto L15;
        b(r6).a(r6, new a[0]);     // Catch: IOException -> L17
    L16:
        return true;
    L15:
        readAttributes(r6, BasicFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});     // Catch: IOException -> L17
    L17:
        return false;
    }

    public static Object getAttribute(Path r1, String r2, LinkOption... r3) {
        if (r2.indexOf(42) < 0) goto L5;
    L15:
        b.a(r2);
        return null;
    L5:
        if (r2.indexOf(44) >= 0) goto L15;
        Map<String, Object> r12 = readAttributes(r1, r2, r3);
        int r32 = r2.indexOf(58);
        if (r32 == (-1)) goto L14;
        if (r32 != r2.length()) goto L12;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L12:
        r2 = r2.substring(r32 + 1);
    L14:
        return r12.get(r2);
    }

    public static <V extends FileAttributeView> V getFileAttributeView(Path r1, Class<V> r2, LinkOption... r3) {
        return (V) b(r1).h(r1, r2, r3);
    }

    public static FileStore getFileStore(Path r1) {
        return b(r1).i(r1);
    }

    public static FileTime getLastModifiedTime(Path r1, LinkOption... r2) {
        return readAttributes(r1, BasicFileAttributes.class, r2).lastModifiedTime();
    }

    public static UserPrincipal getOwner(Path r1, LinkOption... r2) {
        j$.nio.file.attribute.p r12 = (j$.nio.file.attribute.p) getFileAttributeView(r1, j$.nio.file.attribute.p.class, r2);
        if (r12 == null) goto L7;
        return r12.getOwner();
    L7:
        throw new UnsupportedOperationException();
    }

    public static Set<PosixFilePermission> getPosixFilePermissions(Path r1, LinkOption... r2) {
        return j$.desugar.sun.nio.fs.g.m(((j$.nio.file.attribute.u) readAttributes(r1, j$.nio.file.attribute.u.class, r2)).f210a.permissions());
    }

    public static boolean isDirectory(Path r1, LinkOption... r2) {
        if (r2.length != 0) goto L9;
        b(r1);
    L9:
        return readAttributes(r1, BasicFileAttributes.class, r2).isDirectory();
    L7:
        return false;
    }

    public static boolean isExecutable(Path r2) {
        a[] r0 = {a.EXECUTE};
        b(r2).a(r2, r0);     // Catch: IOException -> L6
        return true;
    L6:
        return false;
    }

    public static boolean isHidden(Path r1) {
        return b(r1).m(r1);
    }

    public static boolean isReadable(Path r2) {
        a[] r0 = {a.READ};
        b(r2).a(r2, r0);     // Catch: IOException -> L6
        return true;
    L6:
        return false;
    }

    public static boolean isRegularFile(Path r1, LinkOption... r2) {
        if (r2.length != 0) goto L9;
        b(r1);
    L9:
        return readAttributes(r1, BasicFileAttributes.class, r2).isRegularFile();
    L7:
        return false;
    }

    public static boolean isSameFile(Path r1, Path r2) {
        return b(r1).n(r1, r2);
    }

    public static boolean isSymbolicLink(Path r2) {
        return readAttributes(r2, BasicFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}).isSymbolicLink();
    L4:
        return false;
    }

    public static boolean isWritable(Path r2) {
        a[] r0 = {a.WRITE};
        b(r2).a(r2, r0);     // Catch: IOException -> L6
        return true;
    L6:
        return false;
    }

    public static Stream<Path> list(Path r2) {
        DirectoryStream<Path> r22 = newDirectoryStream(r2);
        return (Stream) StreamSupport.stream(Spliterators.spliteratorUnknownSize(new q(r22.iterator()), 1), false).onClose(new s(r22));
    L5:
        e = move-exception;
        r22.close();     // Catch: IOException -> L10
    L12:
        throw e;
    L10:
        e = move-exception;
        e.addSuppressed(e);     // Catch: Throwable -> L13
        goto L12
    }

    public static Path move(Path r5, Path r6, CopyOption... r7) {
        j$.nio.file.spi.d r0 = b(r5);
        if (b(r6).equals(r0) == false) goto L6;
        r0.o(r5, r6, r7);
        return r6;
    L6:
        int r02 = r7.length;
        CopyOption[] r1 = new CopyOption[r02 + 2];
        int r2 = 0;
    L7:
        if (r2 >= r02) goto L13;
        CopyOption r3 = r7[r2];
        if (r3 == StandardCopyOption.ATOMIC_MOVE) goto L12;
        r1[r2] = r3;
        r2 = r2 + 1;
        goto L7
    L12:
        throw new AtomicMoveNotSupportedException(null, null, "Atomic move between providers is not supported");
    L13:
        r1[r02] = LinkOption.NOFOLLOW_LINKS;
        r1[r02 + 1] = StandardCopyOption.COPY_ATTRIBUTES;
        j$.desugar.sun.nio.fs.g.g(r5, r6, r1);
        delete(r5);
        return r6;
    }

    public static BufferedReader newBufferedReader(Path r2, Charset r3) {
        CharsetDecoder r32 = r3.newDecoder();
        return new BufferedReader(new InputStreamReader(newInputStream(r2, new OpenOption[0]), r32));
    }

    public static DirectoryStream<Path> newDirectoryStream(Path r2) {
        return b(r2).r(r2, u.f266a);
    }

    public static DirectoryStream<Path> newDirectoryStream(Path r3, String r4) {
        if (r4.equals("*") == true) goto L5;
        FileSystem r0 = r3.getFileSystem();
        j$.desugar.sun.nio.fs.h r1 = new j$.desugar.sun.nio.fs.h(r0.b("glob:".concat(r4)), 1);
        return r0.g().r(r3, r1);
    L5:
        return newDirectoryStream(r3);
    }

    public static InputStream newInputStream(Path r1, OpenOption... r2) {
        return b(r1).v(r1, r2);
    }

    public static OutputStream newOutputStream(Path r1, OpenOption... r2) {
        return b(r1).w(r1, r2);
    }

    public static boolean notExists(Path r6, LinkOption... r7) {
        int r2 = r7.length;     // Catch: IOException -> L16 NoSuchFileException -> L15
        boolean r4 = true;
        int r3 = 0;
    L4:
        if (r3 >= r2) goto L10;
        LinkOption r42 = r7[r3];     // Catch: IOException -> L16 NoSuchFileException -> L15
        if (r42 != LinkOption.NOFOLLOW_LINKS) goto L8;
        r3 = r3 + 1;     // Catch: IOException -> L16 NoSuchFileException -> L15
        r4 = false;
        goto L4
    L8:
        r42.getClass();     // Catch: IOException -> L16 NoSuchFileException -> L15
        throw new AssertionError("Should not get here");     // Catch: IOException -> L16 NoSuchFileException -> L15
    L10:
        if (r4 == false) goto L13;
        b(r6).a(r6, new a[0]);     // Catch: IOException -> L16 NoSuchFileException -> L15
        return false;
    L13:
        readAttributes(r6, BasicFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});     // Catch: IOException -> L16 NoSuchFileException -> L15
    L15:
        return true;
    L14:
        return false;
    }

    public static byte[] readAllBytes(Path r8) {
        int r1 = 0;
        SeekableByteChannel r82 = b(r8).q(r8, Collections.EMPTY_SET, new FileAttribute[0]);
        InputStream r0 = Channels.newInputStream(r82);     // Catch: Throwable -> L30
        long r2 = r82.size();     // Catch: Throwable -> L34
        if (r2 > 2147483639) goto L33;
        int r22 = (int) r2;
        byte[] r3 = new byte[r22];     // Catch: Throwable -> L34
    L9:
        int r4 = r0.read(r3, r1, r22 - r1);     // Catch: Throwable -> L34
        if (r4 > 0) goto L11;
        if (r4 < 0) goto L24;
        int r42 = r0.read();     // Catch: Throwable -> L34
        if (r42 < 0) goto L24;
        if (r22 > (2147483639 - r22)) goto L19;
        r22 = Math.max(r22 << 1, 8192);     // Catch: Throwable -> L34
    L21:
        r3 = Arrays.copyOf(r3, r22);     // Catch: Throwable -> L34
        r3[r1] = (byte) r42;     // Catch: Throwable -> L34
        r1 = r1 + 1;     // Catch: Throwable -> L34
        goto L9
    L19:
        if (r22 == 2147483639) goto L23;
        r22 = 2147483639;
        goto L21
    L23:
        throw new OutOfMemoryError("Required array size too large");     // Catch: Throwable -> L34
    L24:
        if (r22 == r1) goto L27;
        r3 = Arrays.copyOf(r3, r1);     // Catch: Throwable -> L34
    L27:
        r0.close();     // Catch: Throwable -> L30
        r82.close();
        return r3;
    L11:
        r1 = r1 + r4;     // Catch: Throwable -> L34
        goto L9
    L33:
        throw new OutOfMemoryError("Required array size too large");     // Catch: Throwable -> L34
    L34:
        th = move-exception;
        if (r0 != null) goto L49;
    L40:
        throw th;     // Catch: Throwable -> L30
    L49:
        r0.close();     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L30
    L30:
        th = move-exception;
        if (r82 != null) goto L47;
    L46:
        throw th;
    L47:
        r82.close();     // Catch: Throwable -> L44
    L44:
        th = move-exception;
        th.addSuppressed(th);
        goto L46
    }

    public static List<String> readAllLines(Path r1, Charset r2) {
        BufferedReader r12 = newBufferedReader(r1, r2);
        ArrayList r22 = new ArrayList();     // Catch: Throwable -> L10
    L4:
        String r0 = r12.readLine();     // Catch: Throwable -> L10
        if (r0 == null) goto L6;
        r22.add(r0);     // Catch: Throwable -> L10
        goto L4
    L6:
        r12.close();
        return r22;
    L10:
        th = move-exception;
        if (r12 != null) goto L19;
    L16:
        throw th;
    L19:
        r12.close();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        th.addSuppressed(th);
        goto L16
    }

    public static <A extends BasicFileAttributes> A readAttributes(Path r1, Class<A> r2, LinkOption... r3) {
        return (A) b(r1).x(r1, r2, r3);
    }

    public static Map<String, Object> readAttributes(Path r1, String r2, LinkOption... r3) {
        return b(r1).y(r1, r2, r3);
    }

    public static Path readSymbolicLink(Path r1) {
        return b(r1).z(r1);
    }

    public static Path setAttribute(Path r1, String r2, Object r3, LinkOption... r4) {
        b(r1).A(r1, r2, r3, r4);
        return r1;
    }

    public static Path setLastModifiedTime(Path r2, FileTime r3) {
        BasicFileAttributeView r0 = (BasicFileAttributeView) getFileAttributeView(r2, BasicFileAttributeView.class, new LinkOption[0]);
        Objects.requireNonNull(r3);
        r0.a(r3, null, null);
        return r2;
    }

    public static Path setOwner(Path r2, UserPrincipal r3) {
        j$.nio.file.attribute.p r0 = (j$.nio.file.attribute.p) getFileAttributeView(r2, j$.nio.file.attribute.p.class, new LinkOption[0]);
        if (r0 == null) goto L7;
        r0.b(r3);
        return r2;
    L7:
        throw new UnsupportedOperationException();
    }

    public static Path setPosixFilePermissions(Path r2, Set<PosixFilePermission> r3) {
        j$.nio.file.attribute.t r0 = (j$.nio.file.attribute.t) getFileAttributeView(r2, j$.nio.file.attribute.t.class, new LinkOption[0]);
        if (r0 == null) goto L7;
        r0.f209a.setPermissions(j$.desugar.sun.nio.fs.g.m(r3));
        return r2;
    L7:
        throw new UnsupportedOperationException();
    }

    public static long size(Path r2) {
        return readAttributes(r2, BasicFileAttributes.class, new LinkOption[0]).size();
    }

    public static Stream<Path> walk(Path r1, FileVisitOption... r2) {
        k r0 = new k(r1, r2);
        return ((Stream) StreamSupport.stream(Spliterators.spliteratorUnknownSize(r0, 1), false).onClose(new s(r0))).map(new t());
    L5:
        e = move-exception;
        r0.close();
        throw e;
    }

    public static Path walkFileTree(Path r5, Set<FileVisitOption> r6, int r7, FileVisitor<? super Path> r8) {
        p r0 = new p(r6, r7);
    L15:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L49
    L51:
        throw th;
    L49:
        th = move-exception;
        th.addSuppressed(th);
        goto L51
    L4:
        if (r0.e == true) goto L46;
        n r62 = r0.c(r5, false);     // Catch: Throwable -> L15
    L6:
        int[] r72 = r.f258a;     // Catch: Throwable -> L15
        o r1 = r62.f247a;     // Catch: Throwable -> L15
        BasicFileAttributes r2 = r62.f249c;     // Catch: Throwable -> L15
        IOException r3 = r62.d;     // Catch: Throwable -> L15
        Path r63 = r62.f248b;     // Catch: Throwable -> L15
        int r73 = r72[r1.ordinal()];     // Catch: Throwable -> L15
        if (r73 != 1) goto L9;
        if (r3 != null) goto L30;
        FileVisitResult r64 = r8.visitFile(r63, r2);     // Catch: Throwable -> L15
    L31:
        Objects.requireNonNull(r64);     // Catch: Throwable -> L15
        if (r64 != FileVisitResult.CONTINUE) goto L34;
    L41:
        r62 = r0.b();     // Catch: Throwable -> L15
        if (r62 != null) goto L6;
    L43:
        r0.close();
        return r5;
    L34:
        if (r64 == FileVisitResult.TERMINATE) goto L43;
        if (r64 != FileVisitResult.SKIP_SIBLINGS) goto L41;
        if (r0.d.isEmpty() == true) goto L41;
        ((m) r0.d.peek()).e = true;     // Catch: Throwable -> L15
        goto L41
    L30:
        r64 = r8.visitFileFailed(r63, r3);     // Catch: Throwable -> L15
        goto L31
    L9:
        if (r73 != 2) goto L11;
        r64 = r8.preVisitDirectory(r63, r2);     // Catch: Throwable -> L15
        if (r64 == FileVisitResult.SKIP_SUBTREE) goto L24;
        if (r64 != FileVisitResult.SKIP_SIBLINGS) goto L31;
    L24:
        if (r0.d.isEmpty() == true) goto L31;
        ((m) r0.d.pop()).f245c.close();     // Catch: Throwable -> L15 IOException -> L52
        goto L31
    L11:
        if (r73 != 3) goto L18;
        r64 = r8.postVisitDirectory(r63, r3);     // Catch: Throwable -> L15
        if (r64 != FileVisitResult.SKIP_SIBLINGS) goto L31;
        r64 = FileVisitResult.CONTINUE;     // Catch: Throwable -> L15
        goto L31
    L18:
        throw new AssertionError("Should not get here");     // Catch: Throwable -> L15
    L46:
        throw new IllegalStateException("Closed");     // Catch: Throwable -> L15
    }

    public static Path write(Path r2, Iterable<? extends CharSequence> r3, Charset r4, OpenOption... r5) {
        Objects.requireNonNull(r3);
        CharsetEncoder r42 = r4.newEncoder();
        OutputStream r52 = newOutputStream(r2, r5);
        BufferedWriter r0 = new BufferedWriter(new OutputStreamWriter(r52, r42));     // Catch: Throwable -> L15
        Iterator<? extends CharSequence> r32 = r3.iterator();     // Catch: Throwable -> L9
    L5:
        if (r32.hasNext() == false) goto L11;
        r0.append(r32.next());     // Catch: Throwable -> L9
        r0.newLine();     // Catch: Throwable -> L9
        goto L5
    L11:
        r0.close();     // Catch: Throwable -> L15
        if (r52 == null) goto L14;
        r52.close();
    L14:
        return r2;
    L9:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L19
    L21:
        throw th;     // Catch: Throwable -> L15
    L19:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        if (r52 != null) goto L29;
    L27:
        throw th;
    L29:
        r52.close();     // Catch: Throwable -> L25
    L25:
        th = move-exception;
        th.addSuppressed(th);
        goto L27
    }

    public static Path write(Path r4, byte[] r5, OpenOption... r6) {
        Objects.requireNonNull(r5);
        OutputStream r62 = newOutputStream(r4, r6);
        int r0 = r5.length;     // Catch: Throwable -> L7
        int r1 = r0;
    L4:
        if (r1 <= 0) goto L9;
        int r2 = Math.min(r1, 8192);     // Catch: Throwable -> L7
        r62.write(r5, r0 - r1, r2);     // Catch: Throwable -> L7
        r1 = r1 - r2;
        goto L4
    L9:
        if (r62 == null) goto L11;
        r62.close();
    L11:
        return r4;
    L7:
        th = move-exception;
        if (r62 != null) goto L18;
    L17:
        throw th;
    L18:
        r62.close();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        th.addSuppressed(th);
        goto L17
    }
}
