package j$.desugar.sun.nio.fs;

import j$.nio.file.Path;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileTime;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class b implements BasicFileAttributeView {

    /* renamed from: b, reason: collision with root package name */
    public static final Set f147b = null;

    /* renamed from: a, reason: collision with root package name */
    public final Path f148a;

    static {
        String[] r0 = {"size", "creationTime", "lastAccessTime", "lastModifiedTime", "fileKey", "isDirectory", "isRegularFile", "isSymbolicLink", "isOther"};
        Charset r1 = q.f173a;
        HashSet r12 = new HashSet();
        int r2 = 0;
    L4:
        if (r2 >= 9) goto L6;
        r12.add(r0[r2]);
        r2 = r2 + 1;
        goto L4
    L6:
        f147b = r12;
    }

    public b(Path r1) {
        this.f148a = r1;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    public final void a(FileTime r13, FileTime r14, FileTime r15) {
        if (r13 != null) goto L5;
        if (r14 != null) goto L5;
        return;
    L5:
        Path r12 = this.f148a;
        r12.getFileSystem().g().a(r12, new j$.nio.file.a[]{j$.nio.file.a.WRITE});
        File r142 = r12.toFile();
        r13.getClass();
        TimeUnit r0 = TimeUnit.MILLISECONDS;
        Objects.requireNonNull(r0, "unit");
        if (r0 == null) goto L8;
        long r02 = r13.f188a;
    L21:
        if (r142.setLastModified(r02) == false) goto L24;
        return;
    L24:
        throw new IOException("File.setLastModified did not succeed on " + r12);
    L8:
        r02 = r0.convert(r13.f189b.getEpochSecond(), TimeUnit.SECONDS);
        if (r02 == Long.MIN_VALUE) goto L21;
        if (r02 == LongCompanionObject.MAX_VALUE) goto L21;
        long r6 = r13.f189b.getNano() / 1000000;
        long r8 = r02 + r6;
        if (((r6 ^ r8) & (r02 ^ r8)) < 0) goto L16;
        r02 = r8;
        goto L21
    L16:
        if (r02 >= 0) goto L18;
        r02 = Long.MIN_VALUE;
        goto L21
    L18:
        r02 = Long.MAX_VALUE;
        goto L21
    }

    @Override // j$.nio.file.attribute.b
    public final String name() {
        return "basic";
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    public final BasicFileAttributes readAttributes() {
        Path r0 = this.f148a;
        r0.getFileSystem().g().a(r0, new j$.nio.file.a[0]);
        File r02 = r0.toFile();
        long r3 = r02.lastModified();
        Objects.requireNonNull(TimeUnit.MILLISECONDS, "unit");
        FileTime r7 = new FileTime(r3);
        boolean r10 = r02.isFile();
        boolean r11 = r02.isDirectory();
    L9:
        boolean r12 = false;
    L11:
        if (r10 == true) goto L15;
        if (r11 == true) goto L15;
        if (r12 == true) goto L15;
        boolean r13 = true;
    L17:
        return new c(r7, r7, r7, r10, r11, r12, r13, r02.length(), Integer.valueOf(r02.hashCode()));
    L15:
        r13 = false;
        goto L17
    L4:
        if (r02.getParent() != null) goto L6;
        File r4 = r02;
    L7:
        r12 = !r4.getCanonicalFile().equals(r4.getAbsoluteFile());
        goto L11
    L6:
        r4 = new File(r02.getParentFile().getCanonicalFile(), r02.getName());     // Catch: IOException -> L9
        goto L7
    }
}
