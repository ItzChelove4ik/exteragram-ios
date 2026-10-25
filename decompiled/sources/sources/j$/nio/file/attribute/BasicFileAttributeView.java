package j$.nio.file.attribute;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface BasicFileAttributeView extends FileAttributeView {
    void a(FileTime r1, FileTime r2, FileTime r3);

    BasicFileAttributes readAttributes();
}
