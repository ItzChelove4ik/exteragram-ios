package okio;

import c.f$$ExternalSyntheticBUOutline1;
import j$.nio.channels.DesugarChannels;
import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.OpenOption;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.StandardOpenOption;
import j$.nio.file.attribute.FileAttribute;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.PathsKt;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Path;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\u0007*\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ'\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\u0006\u0010\n\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000e\u0010\u0016J\u001f\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0017\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0006H\u0016¢\u0006\u0004\b!\u0010\"J\u001f\u0010$\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u000bH\u0016¢\u0006\u0004\b$\u0010%J\u001f\u0010&\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u000bH\u0016¢\u0006\u0004\b&\u0010%J\u001f\u0010(\u001a\u00020'2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u000bH\u0016¢\u0006\u0004\b(\u0010)J\u001f\u0010+\u001a\u00020'2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0016¢\u0006\u0004\b+\u0010,J\u001f\u0010-\u001a\u00020'2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u000bH\u0016¢\u0006\u0004\b-\u0010)J\u001f\u0010.\u001a\u00020'2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0016¢\u0006\u0004\b.\u0010,J\u000f\u0010/\u001a\u00020'H\u0016¢\u0006\u0004\b/\u00100J\u000f\u00102\u001a\u000201H\u0016¢\u0006\u0004\b2\u00103R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00104¨\u00065"}, d2 = {"Lokio/NioFileSystemWrappingFileSystem;", "Lokio/NioSystemFileSystem;", "j$/nio/file/FileSystem", "nioFileSystem", "<init>", "(Lj$/nio/file/FileSystem;)V", "Lokio/Path;", "j$/nio/file/Path", "resolve", "(Lokio/Path;)Lj$/nio/file/Path;", "dir", _UrlKt.FRAGMENT_ENCODE_SET, "throwOnFailure", _UrlKt.FRAGMENT_ENCODE_SET, "list", "(Lokio/Path;Z)Ljava/util/List;", "path", "canonicalize", "(Lokio/Path;)Lokio/Path;", "Lokio/FileMetadata;", "metadataOrNull", "(Lokio/Path;)Lokio/FileMetadata;", "(Lokio/Path;)Ljava/util/List;", "listOrNull", "file", "Lokio/FileHandle;", "openReadOnly", "(Lokio/Path;)Lokio/FileHandle;", "mustCreate", "mustExist", "openReadWrite", "(Lokio/Path;ZZ)Lokio/FileHandle;", "Lokio/Source;", "source", "(Lokio/Path;)Lokio/Source;", "Lokio/Sink;", "sink", "(Lokio/Path;Z)Lokio/Sink;", "appendingSink", _UrlKt.FRAGMENT_ENCODE_SET, "createDirectory", "(Lokio/Path;Z)V", "target", "atomicMove", "(Lokio/Path;Lokio/Path;)V", "delete", "createSymlink", "close", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Lj$/nio/file/FileSystem;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNioFileSystemWrappingFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NioFileSystemWrappingFileSystem.kt\nokio/NioFileSystemWrappingFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,196:1\n1634#2,3:197\n1#3:200\n37#4,2:201\n37#4,2:203\n37#4,2:205\n*S KotlinDebug\n*F\n+ 1 NioFileSystemWrappingFileSystem.kt\nokio/NioFileSystemWrappingFileSystem\n*L\n77#1:197,3\n104#1:201,2\n125#1:203,2\n138#1:205,2\n*E\n"})
/* loaded from: classes5.dex */
public final class NioFileSystemWrappingFileSystem extends NioSystemFileSystem {
    private final j$.nio.file.FileSystem nioFileSystem;

    public NioFileSystemWrappingFileSystem(j$.nio.file.FileSystem r1) {
        this.nioFileSystem = r1;
    }

    private final List<Path> list(Path r5, boolean r6) {
        j$.nio.file.Path r4 = resolve(r5);
        List r42 = PathsKt.listDirectoryEntries$default(r4, null, 1, null);     // Catch: Exception -> L10
        ArrayList r52 = new ArrayList();
        Iterator r43 = r42.iterator();
    L6:
        if (r43.hasNext() == false) goto L8;
        j$.nio.file.Path r62 = (j$.nio.file.Path) r43.next();
        r52.add(Path.Companion.get$default(Path.Companion, r62, false, 1, null));
        goto L6
    L8:
        CollectionsKt.sort(r52);
        return r52;
    L10:
        if (r6 == true) goto L12;
    L16:
        return null;
    L12:
        if (Files.exists(r4, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == true) goto L15;
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r5);
        return null;
    L15:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("failed to list ", r5);
        goto L16
    }

    private final j$.nio.file.Path resolve(Path r2) {
        return this.nioFileSystem.getPath(r2.toString(), new String[0]);
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public Sink appendingSink(Path r3, boolean r4) {
        List r0 = CollectionsKt.createListBuilder();
        r0.add(StandardOpenOption.APPEND);
        if (r4 == true) goto L5;
        r0.add(StandardOpenOption.CREATE);
    L5:
        List r42 = CollectionsKt.build(r0);
        j$.nio.file.Path r2 = resolve(r3);
        StandardOpenOption[] r32 = (StandardOpenOption[]) r42.toArray(new StandardOpenOption[0]);
        OpenOption[] r33 = (OpenOption[]) Arrays.copyOf(r32, r32.length);
        return Okio.sink(Files.newOutputStream(r2, (OpenOption[]) Arrays.copyOf(r33, r33.length)));
    }

    @Override // okio.NioSystemFileSystem, okio.JvmSystemFileSystem, okio.FileSystem
    public void atomicMove(Path r4, Path r5) {
        Files.move(resolve(r4), resolve(r5), (CopyOption[]) Arrays.copyOf(new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING}, 2));     // Catch: UnsupportedOperationException -> L4 NoSuchFileException -> L6
        return;
    L4:
        Model$$ExternalSyntheticBUOutline0.m("atomic move not supported");
        return;
    L6:
        e = move-exception;
        throw new FileNotFoundException(e.getMessage());
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public Path canonicalize(Path r5) {
        return Path.Companion.get$default(Path.Companion, resolve(r5).toRealPath(new LinkOption[0]), false, 1, null);
    L4:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r5);
        return null;
    }

    @Override // okio.FileSystem, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.nioFileSystem.close();
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public void createDirectory(Path r4, boolean r5) {
        FileMetadata r0 = metadataOrNull(r4);
        if (r0 == null) goto L7;
        boolean r2 = true;
        if (r0.isDirectory() != true) goto L7;
    L8:
        if (r2 == false) goto L20;
        if (r5 == false) goto L20;
        JvmSystemFileSystem$$ExternalSyntheticBUOutline0.m(r4, " already exists.");
        return;
    L20:
        Files.createDirectory(resolve(r4), (FileAttribute[]) Arrays.copyOf(new FileAttribute[0], 0));     // Catch: IOException -> L15
        return;
    L15:
        e = move-exception;
        if (r2 == false) goto L19;
        return;
    L19:
        throw new IOException("failed to create directory: " + r4, e);
    L7:
        r2 = false;
        goto L8
    }

    @Override // okio.NioSystemFileSystem, okio.JvmSystemFileSystem, okio.FileSystem
    public void createSymlink(Path r2, Path r3) {
        Files.createSymbolicLink(resolve(r2), resolve(r3), (FileAttribute[]) Arrays.copyOf(new FileAttribute[0], 0));
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public void delete(Path r2, boolean r3) {
        if (Thread.interrupted() == true) goto L17;
        j$.nio.file.Path r1 = resolve(r2);
        Files.delete(r1);     // Catch: IOException -> L7 NoSuchFileException -> L12
        return;
    L12:
        if (r3 == true) goto L14;
        return;
    L14:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r2);
        return;
    L8:
        if (Files.exists(r1, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == false) goto L20;
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("failed to delete ", r2);
        return;
    L20:
        return;
    L17:
        throw new InterruptedIOException("interrupted");
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public List<Path> list(Path r2) {
        return list(r2, true);
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public List<Path> listOrNull(Path r2) {
        return list(r2, false);
    }

    @Override // okio.NioSystemFileSystem, okio.JvmSystemFileSystem, okio.FileSystem
    public FileMetadata metadataOrNull(Path r1) {
        return metadataOrNull(resolve(r1));
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public FileHandle openReadOnly(Path r4) {
        return new NioFileSystemFileHandle(false, DesugarChannels.open(resolve(r4), new OpenOption[]{StandardOpenOption.READ}));
    L5:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r4);
        return null;
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public FileHandle openReadWrite(Path r4, boolean r5, boolean r6) {
        if (r5 == false) goto L8;
        if (r6 == false) goto L8;
        f$$ExternalSyntheticBUOutline1.m("Cannot require mustCreate and mustExist at the same time.");
        return null;
    L8:
        List r1 = CollectionsKt.createListBuilder();
        r1.add(StandardOpenOption.READ);
        r1.add(StandardOpenOption.WRITE);
        if (r5 == false) goto L11;
        r1.add(StandardOpenOption.CREATE_NEW);
    L13:
        List r52 = CollectionsKt.build(r1);
        j$.nio.file.Path r3 = resolve(r4);     // Catch: NoSuchFileException -> L17
        StandardOpenOption[] r53 = (StandardOpenOption[]) r52.toArray(new StandardOpenOption[0]);     // Catch: NoSuchFileException -> L17
        return new NioFileSystemFileHandle(true, DesugarChannels.open(r3, (OpenOption[]) Arrays.copyOf(r53, r53.length)));
    L17:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r4);
        return null;
    L11:
        if (r6 == true) goto L13;
        r1.add(StandardOpenOption.CREATE);
        goto L13
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public Sink sink(Path r2, boolean r3) {
        List r0 = CollectionsKt.createListBuilder();
        if (r3 == false) goto L5;
        r0.add(StandardOpenOption.CREATE_NEW);
    L5:
        List r32 = CollectionsKt.build(r0);
        j$.nio.file.Path r1 = resolve(r2);     // Catch: NoSuchFileException -> L8
        StandardOpenOption[] r33 = (StandardOpenOption[]) r32.toArray(new StandardOpenOption[0]);     // Catch: NoSuchFileException -> L8
        OpenOption[] r34 = (OpenOption[]) Arrays.copyOf(r33, r33.length);     // Catch: NoSuchFileException -> L8
        return Okio.sink(Files.newOutputStream(r1, (OpenOption[]) Arrays.copyOf(r34, r34.length)));
    L8:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r2);
        return null;
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public Source source(Path r3) {
        return Okio.source(Files.newInputStream(resolve(r3), (OpenOption[]) Arrays.copyOf(new OpenOption[0], 0)));
    L4:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r3);
        return null;
    }

    @Override // okio.NioSystemFileSystem, okio.JvmSystemFileSystem
    public String toString() {
        return Reflection.getOrCreateKotlinClass(this.nioFileSystem.getClass()).getSimpleName();
    }
}
