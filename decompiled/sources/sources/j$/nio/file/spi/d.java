package j$.nio.file.spi;

import j$.desugar.sun.nio.fs.g;
import j$.nio.file.CopyOption;
import j$.nio.file.FileStore;
import j$.nio.file.FileSystem;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.StandardOpenOption;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeView;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.DirectoryStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class d {

    /* renamed from: a, reason: collision with root package name */
    public static final Object f263a = null;

    /* renamed from: b, reason: collision with root package name */
    public static volatile List f264b = null;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f265c = false;
    public static final Set d = null;

    static {
        f263a = new Object();
        d = g.q(new Object[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE});
    }

    public d() {
        SecurityManager r0 = System.getSecurityManager();
        if (r0 == null) goto L6;
        r0.checkPermission(new RuntimePermission("fileSystemProvider"));
    }

    public abstract void A(Path r1, String r2, Object r3, LinkOption... r4);

    public abstract void a(Path r1, j$.nio.file.a... r2);

    public abstract void b(Path r1, Path r2, CopyOption... r3);

    public abstract void c(Path r1, FileAttribute... r2);

    public abstract void d(Path r1, Path r2);

    public abstract void e(Path r1, Path r2, FileAttribute... r3);

    public abstract void f(Path r1);

    public abstract boolean g(Path r1);

    public abstract FileAttributeView h(Path r1, Class r2, LinkOption... r3);

    public abstract FileStore i(Path r1);

    public abstract FileSystem j(URI r1);

    public abstract Path k(URI r1);

    public abstract String l();

    public abstract boolean m(Path r1);

    public abstract boolean n(Path r1, Path r2);

    public abstract void o(Path r1, Path r2, CopyOption... r3);

    public abstract j$.nio.channels.a p(Path r1, Set r2, ExecutorService r3, FileAttribute... r4);

    public abstract SeekableByteChannel q(Path r1, Set r2, FileAttribute... r3);

    public abstract DirectoryStream r(Path r1, DirectoryStream.Filter r2);

    public abstract FileChannel s(Path r1, Set r2, FileAttribute... r3);

    public FileSystem t(Path r1, Map r2) {
        throw new UnsupportedOperationException();
    }

    public abstract FileSystem u(URI r1, Map r2);

    public InputStream v(Path r5, OpenOption... r6) {
        if (r6.length <= 0) goto L13;
        int r4 = r6.length;
        int r1 = 0;
    L5:
        if (r1 >= r4) goto L13;
        OpenOption r2 = r6[r1];
        if (r2 == StandardOpenOption.APPEND) goto L11;
        if (r2 == StandardOpenOption.WRITE) goto L11;
        r1 = r1 + 1;
    L11:
        j$.nio.file.b.e("'", r2, "' not allowed");
        return null;
    L13:
        Set r42 = Files.f181a;
        if (r6.length != 0) goto L16;
        Set r43 = Collections.EMPTY_SET;
    L18:
        return Channels.newInputStream(Files.b(r5).q(r5, r43, new FileAttribute[0]));
    L16:
        r43 = new HashSet();
        Collections.addAll(r43, r6);
        goto L18
    }

    public OutputStream w(Path r7, OpenOption... r8) {
        if (r8.length != 0) goto L5;
        Set r82 = d;
    L14:
        return Channels.newOutputStream(q(r7, r82, new FileAttribute[0]));
    L5:
        HashSet r0 = new HashSet();
        int r2 = r8.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L12;
        OpenOption r4 = r8[r3];
        if (r4 == StandardOpenOption.READ) goto L10;
        r0.add(r4);
        r3 = r3 + 1;
        goto L6
    L10:
        j$.nio.file.b.a("READ not allowed");
        return null;
    L12:
        r0.add(StandardOpenOption.WRITE);
        r82 = r0;
        goto L14
    }

    public abstract BasicFileAttributes x(Path r1, Class r2, LinkOption... r3);

    public abstract Map y(Path r1, String r2, LinkOption... r3);

    public abstract Path z(Path r1);
}
