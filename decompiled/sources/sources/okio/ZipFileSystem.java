package okio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Path;
import okio.internal.FixedLengthSource;
import okio.internal.ZipEntry;
import okio.internal.ZipFilesKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J \u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0016J\u0018\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0016J \u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010!\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0018\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010$\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0003H\u0016J\u0018\u0010&\u001a\u00020#2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0018\u0010'\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lokio/ZipFileSystem;", "Lokio/FileSystem;", "zipPath", "Lokio/Path;", "fileSystem", "entries", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/internal/ZipEntry;", "comment", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokio/Path;Lokio/FileSystem;Ljava/util/Map;Ljava/lang/String;)V", "canonicalize", "path", "canonicalizeInternal", "metadataOrNull", "Lokio/FileMetadata;", "openReadOnly", "Lokio/FileHandle;", "file", "openReadWrite", "mustCreate", _UrlKt.FRAGMENT_ENCODE_SET, "mustExist", "list", _UrlKt.FRAGMENT_ENCODE_SET, "dir", "listOrNull", "throwOnFailure", "source", "Lokio/Source;", "sink", "Lokio/Sink;", "appendingSink", "createDirectory", _UrlKt.FRAGMENT_ENCODE_SET, "atomicMove", "target", "delete", "createSymlink", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nZipFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZipFileSystem.kt\nokio/ZipFileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,142:1\n58#2,4:143\n58#2,22:147\n66#2,10:169\n62#2,3:179\n77#2,3:182\n58#2,22:185\n*S KotlinDebug\n*F\n+ 1 ZipFileSystem.kt\nokio/ZipFileSystem\n*L\n55#1:143,4\n56#1:147,22\n55#1:169,10\n55#1:179,3\n55#1:182,3\n99#1:185,22\n*E\n"})
/* loaded from: classes5.dex */
public final class ZipFileSystem extends FileSystem {
    private static final Companion Companion = null;
    private static final Path ROOT = null;
    private final String comment;
    private final Map<Path, ZipEntry> entries;
    private final FileSystem fileSystem;
    private final Path zipPath;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lokio/ZipFileSystem$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        ROOT = Path.Companion.get$default(Path.Companion, "/", false, 1, null);
    }

    public ZipFileSystem(Path r1, FileSystem r2, Map<Path, ZipEntry> r3, String r4) {
        this.zipPath = r1;
        this.fileSystem = r2;
        this.entries = r3;
        this.comment = r4;
    }

    private final Path canonicalizeInternal(Path r2) {
        return ROOT.resolve(r2, true);
    }

    private final List<Path> list(Path r2, boolean r3) {
        Path r0 = canonicalizeInternal(r2);
        ZipEntry r1 = this.entries.get(r0);
        if (r1 != null) goto L10;
        if (r3 == true) goto L7;
        return null;
    L7:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("not a directory: ", r2);
        return null;
    L10:
        return CollectionsKt.toList(r1.getChildren());
    }

    @Override // okio.FileSystem
    public Sink appendingSink(Path r1, boolean r2) {
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void atomicMove(Path r1, Path r2) {
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public Path canonicalize(Path r2) {
        Path r0 = canonicalizeInternal(r2);
        if (this.entries.containsKey(r0) == false) goto L6;
        return r0;
    L6:
        throw new FileNotFoundException(String.valueOf(r2));
    }

    @Override // okio.FileSystem
    public void createDirectory(Path r1, boolean r2) {
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void createSymlink(Path r1, Path r2) {
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void delete(Path r1, boolean r2) {
        throw new IOException("zip file systems are read-only");
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
    public FileMetadata metadataOrNull(Path r14) {
        ZipEntry r142 = this.entries.get(canonicalizeInternal(r14));
        Long r1 = null;
        if (r142 != null) goto L6;
        return null;
    L6:
        if (r142.getOffset() == (-1)) goto L44;
        FileHandle r13 = this.fileSystem.openReadOnly(this.zipPath);
        BufferedSource r2 = Okio.buffer(r13.source(r142.getOffset()));     // Catch: Throwable -> L24
        r142 = ZipFilesKt.readLocalHeader(r2, r142);     // Catch: Throwable -> L16
        if (r2 != null) goto L56;
    L15:
        Throwable th = null;
    L27:
        if (th != null) goto L34;
        if (r13 != null) goto L54;
    L33:
        Throwable r132 = null;
    L41:
        if (r132 == null) goto L44;
        throw r132;
    L54:
        r13.close();     // Catch: Throwable -> L31
    L31:
        th = move-exception;
        r132 = th;
        goto L41
    L34:
        throw th;     // Catch: Throwable -> L24
    L56:
        r2.close();     // Catch: Throwable -> L13
    L13:
        th = th;
        goto L27
    L16:
        th = move-exception;
        if (r2 != null) goto L52;
    L26:
        th = th;
        r142 = null;
        goto L27
    L52:
        r2.close();     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        if (r13 != null) goto L50;
    L40:
        r132 = th;
        r142 = null;
        goto L41
    L50:
        r13.close();     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
    L44:
        boolean r3 = !r142.isDirectory();
        boolean r4 = r142.isDirectory();
        if (r142.isDirectory() == true) goto L49;
        r1 = Long.valueOf(r142.getSize());
    L49:
        return new FileMetadata(r3, r4, null, r1, r142.getCreatedAtMillis$okio(), r142.getLastModifiedAtMillis$okio(), r142.getLastAccessedAtMillis$okio(), null, 128, null);
    }

    @Override // okio.FileSystem
    public FileHandle openReadOnly(Path r1) {
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override // okio.FileSystem
    public FileHandle openReadWrite(Path r1, boolean r2, boolean r3) {
        throw new IOException("zip entries are not writable");
    }

    @Override // okio.FileSystem
    public Sink sink(Path r1, boolean r2) {
        throw new IOException("zip file systems are read-only");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r7v8 */
    @Override // okio.FileSystem
    public Source source(Path r7) {
        Path r0 = canonicalizeInternal(r7);
        ZipEntry r02 = this.entries.get(r0);
        BufferedSource th = null;
        if (r02 == null) goto L24;
        FileHandle r6 = this.fileSystem.openReadOnly(this.zipPath);
        BufferedSource r72 = Okio.buffer(r6.source(r02.getOffset()));     // Catch: Throwable -> L11
        if (r6 != null) goto L28;
    L10:
        BufferedSource r5 = th;
        th = r72;
        th = r5;
    L17:
        if (th != 0) goto L23;
        ZipFilesKt.skipLocalHeader(th);
        if (r02.getCompressionMethod() != 0) goto L22;
        return new FixedLengthSource(th, r02.getSize(), true);
    L22:
        return new FixedLengthSource(new InflaterSource(new FixedLengthSource(th, r02.getCompressedSize(), true), new Inflater(true)), r02.getSize(), false);
    L23:
        throw th;
    L28:
        r6.close();     // Catch: Throwable -> L9
    L9:
        th = th;
    L11:
        th = th;
        if (r6 == null) goto L17;
        r6.close();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L17
    L24:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("no such file: ", r7);
        return null;
    }
}
