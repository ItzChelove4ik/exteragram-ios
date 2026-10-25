package okio;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Path;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J \u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J \u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u0018\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0018\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010 \u001a\u00020!H\u0016J\f\u0010\"\u001a\u00020\u001b*\u00020\u0005H\u0002J\f\u0010#\u001a\u00020\u001b*\u00020\u0005H\u0002¨\u0006$"}, d2 = {"Lokio/JvmSystemFileSystem;", "Lokio/FileSystem;", "<init>", "()V", "canonicalize", "Lokio/Path;", "path", "metadataOrNull", "Lokio/FileMetadata;", "list", _UrlKt.FRAGMENT_ENCODE_SET, "dir", "listOrNull", "throwOnFailure", _UrlKt.FRAGMENT_ENCODE_SET, "openReadOnly", "Lokio/FileHandle;", "file", "openReadWrite", "mustCreate", "mustExist", "source", "Lokio/Source;", "sink", "Lokio/Sink;", "appendingSink", "createDirectory", _UrlKt.FRAGMENT_ENCODE_SET, "atomicMove", "target", "delete", "createSymlink", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "requireExist", "requireCreate", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJvmSystemFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JvmSystemFileSystem.kt\nokio/JvmSystemFileSystem\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,158:1\n11896#2,3:159\n*S KotlinDebug\n*F\n+ 1 JvmSystemFileSystem.kt\nokio/JvmSystemFileSystem\n*L\n77#1:159,3\n*E\n"})
/* loaded from: classes5.dex */
public class JvmSystemFileSystem extends FileSystem {
    public JvmSystemFileSystem() {
    }

    private final List<Path> list(Path r4, boolean r5) {
        File r3 = r4.toFile();
        String[] r0 = r3.list();
        if (r0 == null) goto L5;
        ArrayList r32 = new ArrayList();
        int r52 = r0.length;
        int r1 = 0;
    L13:
        if (r1 >= r52) goto L15;
        r32.add(r4.resolve(r0[r1]));
        r1 = r1 + 1;
        goto L13
    L15:
        CollectionsKt.sort(r32);
        return r32;
    L5:
        if (r5 == true) goto L7;
    L11:
        return null;
    L7:
        if (r3.exists() == true) goto L10;
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r4);
        return null;
    L10:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("failed to list ", r4);
        goto L11
    }

    private final void requireCreate(Path r1) {
        if (exists(r1) == true) goto L5;
        return;
    L5:
        JvmSystemFileSystem$$ExternalSyntheticBUOutline0.m(r1, " already exists.");
    }

    private final void requireExist(Path r1) {
        if (exists(r1) == false) goto L5;
        return;
    L5:
        JvmSystemFileSystem$$ExternalSyntheticBUOutline0.m(r1, " doesn't exist.");
    }

    @Override // okio.FileSystem
    public Sink appendingSink(Path r1, boolean r2) {
        if (r2 == false) goto L5;
        requireExist(r1);
    L5:
        return Okio.sink(r1.toFile(), true);
    }

    @Override // okio.FileSystem
    public void atomicMove(Path r3, Path r4) {
        if (r3.toFile().renameTo(r4.toFile()) == false) goto L6;
        return;
    L6:
        throw new IOException("failed to move " + r3 + " to " + r4);
    }

    @Override // okio.FileSystem
    public Path canonicalize(Path r4) {
        File r3 = r4.toFile().getCanonicalFile();
        if (r3.exists() == false) goto L7;
        return Path.Companion.get$default(Path.Companion, r3, false, 1, null);
    L7:
        throw new FileNotFoundException("no such file");
    }

    @Override // okio.FileSystem
    public void createDirectory(Path r2, boolean r3) {
        if (r2.toFile().mkdir() == true) goto L14;
        FileMetadata r1 = metadataOrNull(r2);
        if (r1 != null) goto L7;
    L12:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("failed to create directory: ", r2);
        return;
    L7:
        if (r1.isDirectory() != true) goto L12;
        if (r3 == false) goto L15;
        JvmSystemFileSystem$$ExternalSyntheticBUOutline0.m(r2, " already exists.");
        return;
    L15:
        return;
    }

    @Override // okio.FileSystem
    public void createSymlink(Path r1, Path r2) {
        throw new IOException("unsupported");
    }

    @Override // okio.FileSystem
    public void delete(Path r2, boolean r3) {
        if (Thread.interrupted() == true) goto L15;
        File r1 = r2.toFile();
        if (r1.delete() == false) goto L7;
        return;
    L7:
        if (r1.exists() == true) goto L12;
        if (r3 == false) goto L17;
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r2);
        return;
    L17:
        return;
    L12:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("failed to delete ", r2);
        return;
    L15:
        throw new InterruptedIOException("interrupted");
    }

    @Override // okio.FileSystem
    public List<Path> list(Path r2) {
        return list(r2, true);
    }

    @Override // okio.FileSystem
    public List<Path> listOrNull(Path r2) {
        return list(r2, false);
    }

    @Override // okio.FileSystem
    public FileMetadata metadataOrNull(Path r12) {
        File r11 = r12.toFile();
        boolean r1 = r11.isFile();
        boolean r2 = r11.isDirectory();
        long r3 = r11.lastModified();
        long r5 = r11.length();
        if (r1 == true) goto L14;
        if (r2 == true) goto L14;
        if (r3 != 0) goto L14;
        if (r5 != 0) goto L14;
        if (r11.exists() == true) goto L14;
        return null;
    L14:
        return new FileMetadata(r1, r2, null, Long.valueOf(r5), null, Long.valueOf(r3), null, null, 128, null);
    }

    @Override // okio.FileSystem
    public FileHandle openReadOnly(Path r3) {
        return new JvmFileHandle(false, new RandomAccessFile(r3.toFile(), "r"));
    }

    @Override // okio.FileSystem
    public FileHandle openReadWrite(Path r1, boolean r2, boolean r3) {
        if (r2 == false) goto L7;
        if (r3 == false) goto L7;
        f$$ExternalSyntheticBUOutline1.m("Cannot require mustCreate and mustExist at the same time.");
        return null;
    L7:
        if (r2 == false) goto L9;
        requireCreate(r1);
    L9:
        if (r3 == false) goto L12;
        requireExist(r1);
    L12:
        return new JvmFileHandle(true, new RandomAccessFile(r1.toFile(), "rw"));
    }

    @Override // okio.FileSystem
    public Sink sink(Path r2, boolean r3) {
        if (r3 == false) goto L5;
        requireCreate(r2);
    L5:
        return Okio.sink$default(r2.toFile(), false, 1, null);
    }

    @Override // okio.FileSystem
    public Source source(Path r1) {
        return Okio.source(r1.toFile());
    }

    public String toString() {
        return "JvmSystemFileSystem";
    }
}
