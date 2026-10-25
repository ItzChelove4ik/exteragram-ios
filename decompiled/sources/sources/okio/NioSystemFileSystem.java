package okio;

import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileTime;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Path;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005*\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\rH\u0004¢\u0006\u0004\b\u000b\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0015\u0010\u0014J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lokio/NioSystemFileSystem;", "Lokio/JvmSystemFileSystem;", "<init>", "()V", "j$/nio/file/attribute/FileTime", _UrlKt.FRAGMENT_ENCODE_SET, "zeroToNull", "(Lj$/nio/file/attribute/FileTime;)Ljava/lang/Long;", "Lokio/Path;", "path", "Lokio/FileMetadata;", "metadataOrNull", "(Lokio/Path;)Lokio/FileMetadata;", "j$/nio/file/Path", "nioPath", "(Lj$/nio/file/Path;)Lokio/FileMetadata;", "source", "target", _UrlKt.FRAGMENT_ENCODE_SET, "atomicMove", "(Lokio/Path;Lokio/Path;)V", "createSymlink", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNioSystemFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NioSystemFileSystem.kt\nokio/NioSystemFileSystem\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,92:1\n1#2:93\n*E\n"})
/* loaded from: classes5.dex */
public class NioSystemFileSystem extends JvmSystemFileSystem {
    public NioSystemFileSystem() {
    }

    private final Long zeroToNull(FileTime r5) {
        Long r4 = Long.valueOf(r5.toMillis());
        if (r4.longValue() == 0) goto L5;
        return r4;
    L5:
        return null;
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public void atomicMove(Path r3, Path r4) {
        Files.move(r3.toNioPath(), r4.toNioPath(), new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING});     // Catch: UnsupportedOperationException -> L4 NoSuchFileException -> L6
        return;
    L4:
        Model$$ExternalSyntheticBUOutline0.m("atomic move not supported");
        return;
    L6:
        e = move-exception;
        throw new FileNotFoundException(e.getMessage());
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public void createSymlink(Path r1, Path r2) {
        Files.createSymbolicLink(r1.toNioPath(), r2.toNioPath(), new FileAttribute[0]);
    }

    public final FileMetadata metadataOrNull(j$.nio.file.Path r14) {
        Long r0 = null;
        BasicFileAttributes r1 = Files.readAttributes(r14, BasicFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});     // Catch: Throwable -> L25
        if (r1.isSymbolicLink() == false) goto L7;
        j$.nio.file.Path r142 = Files.readSymbolicLink(r14);
    L8:
        boolean r3 = r1.isRegularFile();
        boolean r4 = r1.isDirectory();
        if (r142 == null) goto L11;
        Path r5 = Path.Companion.get$default(Path.Companion, r142, false, 1, null);
    L12:
        Long r6 = Long.valueOf(r1.size());
        FileTime r143 = r1.creationTime();
        if (r143 == null) goto L15;
        Long r7 = zeroToNull(r143);
    L16:
        FileTime r144 = r1.lastModifiedTime();
        if (r144 == null) goto L19;
        Long r8 = zeroToNull(r144);
    L20:
        FileTime r145 = r1.lastAccessTime();
        if (r145 == null) goto L24;
        r0 = zeroToNull(r145);
    L24:
        return new FileMetadata(r3, r4, r5, r6, r7, r8, r0, null, 128, null);
    L19:
        r8 = null;
        goto L20
    L15:
        r7 = null;
        goto L16
    L11:
        r5 = null;
        goto L12
    L7:
        r142 = null;
    L25:
        return null;
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public FileMetadata metadataOrNull(Path r1) {
        return metadataOrNull(r1.toNioPath());
    }

    @Override // okio.JvmSystemFileSystem
    public String toString() {
        return "NioSystemFileSystem";
    }
}
