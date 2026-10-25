package kotlin.io.path;

import j$.nio.file.FileVisitResult;
import j$.nio.file.Path;
import j$.nio.file.SimpleFileVisitor;
import j$.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001By\u0012\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0010J\u001f\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J!\u0010\u0016\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u0016\u0010\u0015R(\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017R(\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0017R(\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0017R*\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0017¨\u0006\u0018"}, d2 = {"Lkotlin/io/path/FileVisitorImpl;", "j$/nio/file/SimpleFileVisitor", "j$/nio/file/Path", "Lkotlin/Function2;", "j$/nio/file/attribute/BasicFileAttributes", "j$/nio/file/FileVisitResult", "onPreVisitDirectory", "onVisitFile", "Ljava/io/IOException;", "onVisitFileFailed", "onPostVisitDirectory", "<init>", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "dir", "attrs", "preVisitDirectory", "(Lj$/nio/file/Path;Lj$/nio/file/attribute/BasicFileAttributes;)Lj$/nio/file/FileVisitResult;", "file", "visitFile", "exc", "visitFileFailed", "(Lj$/nio/file/Path;Ljava/io/IOException;)Lj$/nio/file/FileVisitResult;", "postVisitDirectory", "Lkotlin/jvm/functions/Function2;", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class FileVisitorImpl extends SimpleFileVisitor<Path> {
    private final Function2<Path, IOException, FileVisitResult> onPostVisitDirectory;
    private final Function2<Path, BasicFileAttributes, FileVisitResult> onPreVisitDirectory;
    private final Function2<Path, BasicFileAttributes, FileVisitResult> onVisitFile;
    private final Function2<Path, IOException, FileVisitResult> onVisitFileFailed;

    /* JADX WARN: Multi-variable type inference failed */
    public FileVisitorImpl(Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> r1, Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> r2, Function2<? super Path, ? super IOException, ? extends FileVisitResult> r3, Function2<? super Path, ? super IOException, ? extends FileVisitResult> r4) {
        this.onPreVisitDirectory = r1;
        this.onVisitFile = r2;
        this.onVisitFileFailed = r3;
        this.onPostVisitDirectory = r4;
    }

    public FileVisitResult postVisitDirectory(Path r2, IOException r3) {
        Function2<Path, IOException, FileVisitResult> r0 = this.onPostVisitDirectory;
        if (r0 == null) goto L9;
        FileVisitResult r02 = r0.invoke(r2, r3);
        if (r02 == null) goto L9;
        return r02;
    L9:
        return super.postVisitDirectory(r2, r3);
    }

    @Override // j$.nio.file.SimpleFileVisitor, j$.nio.file.FileVisitor
    public /* bridge */ /* synthetic */ FileVisitResult postVisitDirectory(Object r1, IOException r2) {
        return postVisitDirectory((Path) r1, r2);
    }

    public FileVisitResult preVisitDirectory(Path r2, BasicFileAttributes r3) {
        Function2<Path, BasicFileAttributes, FileVisitResult> r0 = this.onPreVisitDirectory;
        if (r0 == null) goto L9;
        FileVisitResult r02 = r0.invoke(r2, r3);
        if (r02 == null) goto L9;
        return r02;
    L9:
        return super.preVisitDirectory(r2, r3);
    }

    @Override // j$.nio.file.SimpleFileVisitor, j$.nio.file.FileVisitor
    public /* bridge */ /* synthetic */ FileVisitResult preVisitDirectory(Object r1, BasicFileAttributes r2) {
        return preVisitDirectory((Path) r1, r2);
    }

    public FileVisitResult visitFile(Path r2, BasicFileAttributes r3) {
        Function2<Path, BasicFileAttributes, FileVisitResult> r0 = this.onVisitFile;
        if (r0 == null) goto L9;
        FileVisitResult r02 = r0.invoke(r2, r3);
        if (r02 == null) goto L9;
        return r02;
    L9:
        return super.visitFile(r2, r3);
    }

    @Override // j$.nio.file.SimpleFileVisitor, j$.nio.file.FileVisitor
    public /* bridge */ /* synthetic */ FileVisitResult visitFile(Object r1, BasicFileAttributes r2) {
        return visitFile((Path) r1, r2);
    }

    public FileVisitResult visitFileFailed(Path r2, IOException r3) {
        Function2<Path, IOException, FileVisitResult> r0 = this.onVisitFileFailed;
        if (r0 == null) goto L9;
        FileVisitResult r02 = r0.invoke(r2, r3);
        if (r02 == null) goto L9;
        return r02;
    L9:
        return super.visitFileFailed(r2, r3);
    }

    @Override // j$.nio.file.SimpleFileVisitor, j$.nio.file.FileVisitor
    public /* bridge */ /* synthetic */ FileVisitResult visitFileFailed(Object r1, IOException r2) {
        return visitFileFailed((Path) r1, r2);
    }
}
