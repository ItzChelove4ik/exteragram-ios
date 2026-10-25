package kotlin.io.path;

import j$.nio.file.FileVisitResult;
import j$.nio.file.Files;
import j$.nio.file.Path;
import j$.nio.file.SimpleFileVisitor;
import j$.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\b\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u0017R\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lkotlin/io/path/DirectoryEntriesReader;", "j$/nio/file/SimpleFileVisitor", "j$/nio/file/Path", _UrlKt.FRAGMENT_ENCODE_SET, "followLinks", "<init>", "(Z)V", "Lkotlin/io/path/PathNode;", "directoryNode", _UrlKt.FRAGMENT_ENCODE_SET, "readEntries", "(Lkotlin/io/path/PathNode;)Ljava/util/List;", "dir", "j$/nio/file/attribute/BasicFileAttributes", "attrs", "j$/nio/file/FileVisitResult", "preVisitDirectory", "(Lj$/nio/file/Path;Lj$/nio/file/attribute/BasicFileAttributes;)Lj$/nio/file/FileVisitResult;", "file", "visitFile", "Z", "getFollowLinks", "()Z", "Lkotlin/io/path/PathNode;", "Lkotlin/collections/ArrayDeque;", "entries", "Lkotlin/collections/ArrayDeque;", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPathTreeWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathTreeWalk.kt\nkotlin/io/path/DirectoryEntriesReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,180:1\n1#2:181\n*E\n"})
/* loaded from: classes5.dex */
final class DirectoryEntriesReader extends SimpleFileVisitor<Path> {
    private PathNode directoryNode;
    private ArrayDeque<PathNode> entries;
    private final boolean followLinks;

    public DirectoryEntriesReader(boolean r1) {
        this.followLinks = r1;
        this.entries = new ArrayDeque();
    }

    public final boolean getFollowLinks() {
        return this.followLinks;
    }

    public FileVisitResult preVisitDirectory(Path r4, BasicFileAttributes r5) {
        PathNode r0 = new PathNode(r4, r5.fileKey(), this.directoryNode);
        this.entries.add(r0);
        return super.preVisitDirectory(r4, r5);
    }

    @Override // j$.nio.file.SimpleFileVisitor, j$.nio.file.FileVisitor
    public /* bridge */ /* synthetic */ FileVisitResult preVisitDirectory(Object r1, BasicFileAttributes r2) {
        return preVisitDirectory((Path) r1, r2);
    }

    public final List<PathNode> readEntries(PathNode r3) {
        this.directoryNode = r3;
        Files.walkFileTree(r3.getPath(), LinkFollowing.INSTANCE.toVisitOptions(this.followLinks), 1, this);
        this.entries.removeFirst();
        ArrayDeque<PathNode> r32 = this.entries;
        this.entries = new ArrayDeque();
        return r32;
    }

    public FileVisitResult visitFile(Path r4, BasicFileAttributes r5) {
        PathNode r0 = new PathNode(r4, null, this.directoryNode);
        this.entries.add(r0);
        return super.visitFile(r4, r5);
    }

    @Override // j$.nio.file.SimpleFileVisitor, j$.nio.file.FileVisitor
    public /* bridge */ /* synthetic */ FileVisitResult visitFile(Object r1, BasicFileAttributes r2) {
        return visitFile((Path) r1, r2);
    }
}
