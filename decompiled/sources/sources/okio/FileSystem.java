package okio;

import java.io.Closeable;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import okhttp3.internal.url._UrlKt;
import okio.Path;
import okio.internal.ResourceFileSystem;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000b\b&\u0018\u0000 72\u00060\u0001j\u0002`\u0002:\u00017B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0006J\u0012\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0007\u001a\u00020\u0006H&J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H&J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H&J \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\fH\u0016J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u000f\u001a\u00020\u0006J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006H&J$\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\fH&J\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0006H&JG\u0010\u001c\u001a\u0002H\u001d\"\u0004\b\u0000\u0010\u001d2\u0006\u0010\u0016\u001a\u00020\u00062\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u0002H\u001d0\u001f¢\u0006\u0002\b!H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\"\u0010#J\u001a\u0010$\u001a\u00020%2\u0006\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\fH&J\u000e\u0010$\u001a\u00020%2\u0006\u0010\u0016\u001a\u00020\u0006JQ\u0010&\u001a\u0002H\u001d\"\u0004\b\u0000\u0010\u001d2\u0006\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\f2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u0002H\u001d0\u001f¢\u0006\u0002\b!H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0004\b)\u0010*J\u001a\u0010+\u001a\u00020%2\u0006\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\fH&J\u000e\u0010+\u001a\u00020%2\u0006\u0010\u0016\u001a\u00020\u0006J\u001a\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\fH&J\u000e\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u0006J\u0018\u0010.\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\fJ\u000e\u0010.\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u0006J\u0018\u0010/\u001a\u00020-2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H&J\u0018\u00101\u001a\u00020-2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0016J\u001a\u00102\u001a\u00020-2\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\fH&J\u000e\u00102\u001a\u00020-2\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\fH\u0016J\u000e\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020\u0006J\u0018\u00105\u001a\u00020-2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H&J\b\u00106\u001a\u00020-H\u0016\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00068"}, d2 = {"Lokio/FileSystem;", "Ljava/io/Closeable;", "Lokio/Closeable;", "<init>", "()V", "canonicalize", "Lokio/Path;", "path", "metadata", "Lokio/FileMetadata;", "metadataOrNull", "exists", _UrlKt.FRAGMENT_ENCODE_SET, "list", _UrlKt.FRAGMENT_ENCODE_SET, "dir", "listOrNull", "listRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", "openReadOnly", "Lokio/FileHandle;", "file", "openReadWrite", "mustCreate", "mustExist", "source", "Lokio/Source;", "read", "T", "readerAction", "Lkotlin/Function1;", "Lokio/BufferedSource;", "Lkotlin/ExtensionFunctionType;", "-read", "(Lokio/Path;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sink", "Lokio/Sink;", "write", "writerAction", "Lokio/BufferedSink;", "-write", "(Lokio/Path;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "appendingSink", "createDirectory", _UrlKt.FRAGMENT_ENCODE_SET, "createDirectories", "atomicMove", "target", "copy", "delete", "deleteRecursively", "fileOrDirectory", "createSymlink", "close", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileSystem.kt\nokio/FileSystem\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,191:1\n58#2,22:192\n58#2,22:214\n*S KotlinDebug\n*F\n+ 1 FileSystem.kt\nokio/FileSystem\n*L\n73#1:192,22\n95#1:214,22\n*E\n"})
/* loaded from: classes5.dex */
public abstract class FileSystem implements Closeable {
    public static final Companion Companion = null;

    @JvmField
    public static final FileSystem RESOURCES = null;

    @JvmField
    public static final FileSystem SYSTEM = null;

    @JvmField
    public static final Path SYSTEM_TEMPORARY_DIRECTORY = null;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\b\u001a\u00020\u0005*\u00020\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\n¨\u0006\u000f"}, d2 = {"Lokio/FileSystem$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/FileSystem", "Lokio/FileSystem;", "get", "(Lj$/nio/file/FileSystem;)Lokio/FileSystem;", "asOkioFileSystem", "SYSTEM", "Lokio/FileSystem;", "Lokio/Path;", "SYSTEM_TEMPORARY_DIRECTORY", "Lokio/Path;", "RESOURCES", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        @JvmName(name = "get")
        public final FileSystem get(j$.nio.file.FileSystem r1) {
            return new NioFileSystemWrappingFileSystem(r1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* renamed from: -write$default, reason: not valid java name */
    public static /* synthetic */ Object m3758write$default(FileSystem r2, Path r3, boolean r4, Function1 r5, int r6, Object r7) {
        Object th = null;
        if (r7 == null) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: write");
        return null;
    L5:
        if ((r6 & 2) == 0) goto L7;
        r4 = false;
    L7:
        BufferedSink r22 = Okio.buffer(r2.sink(r3, r4));
        Object r42 = r5.invoke(r22);     // Catch: Throwable -> L15
        InlineMarker.finallyStart(1);
        if (r22 != null) goto L32;
    L14:
        InlineMarker.finallyEnd(1);
        Object r1 = th;
        th = r42;
        ?? r43 = r1;
    L23:
        if (r43 != 0) goto L25;
        return th;
    L25:
        throw r43;
    L32:
        r22.close();     // Catch: Throwable -> L13
    L13:
        th = th;
    L15:
        th = move-exception;
        InlineMarker.finallyStart(1);
        if (r22 != null) goto L28;
    L22:
        InlineMarker.finallyEnd(1);
        r43 = th;
        goto L23
    L28:
        r22.close();     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L22
    }

    static {
        Companion = new Companion(null);
        Class.forName("java.nio.file.Files");     // Catch: ClassNotFoundException -> L5
        FileSystem r0 = new NioSystemFileSystem();     // Catch: ClassNotFoundException -> L5
    L6:
        SYSTEM = r0;
        SYSTEM_TEMPORARY_DIRECTORY = Path.Companion.get$default(Path.Companion, System.getProperty("java.io.tmpdir"), false, 1, null);
        RESOURCES = new ResourceFileSystem(ResourceFileSystem.class.getClassLoader(), false, null, 4, null);
        return;
    L5:
        r0 = new JvmSystemFileSystem();
        goto L6
    }

    public FileSystem() {
    }

    public static /* synthetic */ Sink appendingSink$default(FileSystem r0, Path r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: appendingSink");
        return null;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = false;
    L7:
        return r0.appendingSink(r1, r2);
    }

    public static /* synthetic */ void createDirectories$default(FileSystem r0, Path r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: createDirectories");
        return;
    L4:
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        r0.createDirectories(r1, r2);
    }

    public static /* synthetic */ void createDirectory$default(FileSystem r0, Path r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: createDirectory");
        return;
    L4:
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        r0.createDirectory(r1, r2);
    }

    public static /* synthetic */ void delete$default(FileSystem r0, Path r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: delete");
        return;
    L4:
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        r0.delete(r1, r2);
    }

    public static /* synthetic */ void deleteRecursively$default(FileSystem r0, Path r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: deleteRecursively");
        return;
    L4:
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        r0.deleteRecursively(r1, r2);
    }

    @JvmStatic
    @JvmName(name = "get")
    public static final FileSystem get(j$.nio.file.FileSystem r1) {
        return Companion.get(r1);
    }

    public static /* synthetic */ Sequence listRecursively$default(FileSystem r0, Path r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: listRecursively");
        return null;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = false;
    L7:
        return r0.listRecursively(r1, r2);
    }

    public static /* synthetic */ FileHandle openReadWrite$default(FileSystem r1, Path r2, boolean r3, boolean r4, int r5, Object r6) {
        if (r6 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: openReadWrite");
        return null;
    L4:
        if ((r5 & 2) == 0) goto L7;
        r3 = false;
    L7:
        if ((r5 & 4) == 0) goto L10;
        r4 = false;
    L10:
        return r1.openReadWrite(r2, r3, r4);
    }

    public static /* synthetic */ Sink sink$default(FileSystem r0, Path r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: sink");
        return null;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = false;
    L7:
        return r0.sink(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    @JvmName(name = "-read")
    /* renamed from: -read, reason: not valid java name */
    public final <T> T m3759read(Path r3, Function1<? super BufferedSource, ? extends T> r4) {
        BufferedSource r2 = Okio.buffer(source(r3));
        T th = null;
        T r42 = r4.invoke(r2);     // Catch: Throwable -> L10
        InlineMarker.finallyStart(1);
        if (r2 != null) goto L21;
    L9:
        InlineMarker.finallyEnd(1);
        T r1 = th;
        th = r42;
        ?? r43 = r1;
    L18:
        if (r43 != 0) goto L20;
        return th;
    L20:
        throw r43;
    L21:
        r2.close();     // Catch: Throwable -> L8
    L8:
        th = th;
    L10:
        th = move-exception;
        InlineMarker.finallyStart(1);
        if (r2 != null) goto L23;
    L17:
        InlineMarker.finallyEnd(1);
        r43 = th;
        goto L18
    L23:
        r2.close();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L17
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    @JvmName(name = "-write")
    /* renamed from: -write, reason: not valid java name */
    public final <T> T m3760write(Path r2, boolean r3, Function1<? super BufferedSink, ? extends T> r4) {
        BufferedSink r1 = Okio.buffer(sink(r2, r3));
        T th = null;
        T r42 = r4.invoke(r1);     // Catch: Throwable -> L10
        InlineMarker.finallyStart(1);
        if (r1 != null) goto L21;
    L9:
        InlineMarker.finallyEnd(1);
        ?? r43 = th;
        th = r42;
    L18:
        if (r43 != 0) goto L20;
        return th;
    L20:
        throw r43;
    L21:
        r1.close();     // Catch: Throwable -> L8
    L8:
        th = th;
    L10:
        th = move-exception;
        InlineMarker.finallyStart(1);
        if (r1 != null) goto L23;
    L17:
        InlineMarker.finallyEnd(1);
        r43 = th;
        goto L18
    L23:
        r1.close();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L17
    }

    public final Sink appendingSink(Path r2) {
        return appendingSink(r2, false);
    }

    public abstract Sink appendingSink(Path r1, boolean r2);

    public abstract void atomicMove(Path r1, Path r2);

    public abstract Path canonicalize(Path r1);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public void copy(Path r1, Path r2) {
        okio.internal.FileSystem.commonCopy(this, r1, r2);
    }

    public final void createDirectories(Path r2) {
        createDirectories(r2, false);
    }

    public final void createDirectories(Path r1, boolean r2) {
        okio.internal.FileSystem.commonCreateDirectories(this, r1, r2);
    }

    public final void createDirectory(Path r2) {
        createDirectory(r2, false);
    }

    public abstract void createDirectory(Path r1, boolean r2);

    public abstract void createSymlink(Path r1, Path r2);

    public final void delete(Path r2) {
        delete(r2, false);
    }

    public abstract void delete(Path r1, boolean r2);

    public final void deleteRecursively(Path r2) {
        deleteRecursively(r2, false);
    }

    public void deleteRecursively(Path r1, boolean r2) {
        okio.internal.FileSystem.commonDeleteRecursively(this, r1, r2);
    }

    public final boolean exists(Path r1) {
        return okio.internal.FileSystem.commonExists(this, r1);
    }

    public abstract List<Path> list(Path r1);

    public abstract List<Path> listOrNull(Path r1);

    public final Sequence<Path> listRecursively(Path r2) {
        return listRecursively(r2, false);
    }

    public Sequence<Path> listRecursively(Path r1, boolean r2) {
        return okio.internal.FileSystem.commonListRecursively(this, r1, r2);
    }

    public final FileMetadata metadata(Path r1) {
        return okio.internal.FileSystem.commonMetadata(this, r1);
    }

    public abstract FileMetadata metadataOrNull(Path r1);

    public abstract FileHandle openReadOnly(Path r1);

    public final FileHandle openReadWrite(Path r2) {
        return openReadWrite(r2, false, false);
    }

    public abstract FileHandle openReadWrite(Path r1, boolean r2, boolean r3);

    public final Sink sink(Path r2) {
        return sink(r2, false);
    }

    public abstract Sink sink(Path r1, boolean r2);

    public abstract Source source(Path r1);
}
