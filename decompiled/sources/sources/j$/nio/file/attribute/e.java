package j$.nio.file.attribute;

import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class e implements BasicFileAttributes {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ java.nio.file.attribute.BasicFileAttributes f195a;

    public /* synthetic */ e(java.nio.file.attribute.BasicFileAttributes r1) {
        this.f195a = r1;
    }

    public static /* synthetic */ BasicFileAttributes a(java.nio.file.attribute.BasicFileAttributes r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if ((r1 instanceof f) == false) goto L10;
        return ((f) r1).f196a;
    L10:
        if ((r1 instanceof DosFileAttributes) == false) goto L14;
        return new h((DosFileAttributes) r1);
    L14:
        if ((r1 instanceof PosixFileAttributes) == false) goto L18;
        return new u((PosixFileAttributes) r1);
    L18:
        return new e(r1);
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ FileTime creationTime() {
        return j$.desugar.sun.nio.fs.g.c(this.f195a.creationTime());
    }

    public final /* synthetic */ boolean equals(Object r2) {
        java.nio.file.attribute.BasicFileAttributes r1 = this.f195a;
        if ((r2 instanceof e) == false) goto L6;
        r2 = ((e) r2).f195a;
    L6:
        return r1.equals(r2);
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ Object fileKey() {
        return this.f195a.fileKey();
    }

    public final /* synthetic */ int hashCode() {
        return this.f195a.hashCode();
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ boolean isDirectory() {
        return this.f195a.isDirectory();
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ boolean isOther() {
        return this.f195a.isOther();
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ boolean isRegularFile() {
        return this.f195a.isRegularFile();
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ boolean isSymbolicLink() {
        return this.f195a.isSymbolicLink();
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ FileTime lastAccessTime() {
        return j$.desugar.sun.nio.fs.g.c(this.f195a.lastAccessTime());
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ FileTime lastModifiedTime() {
        return j$.desugar.sun.nio.fs.g.c(this.f195a.lastModifiedTime());
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public final /* synthetic */ long size() {
        return this.f195a.size();
    }
}
