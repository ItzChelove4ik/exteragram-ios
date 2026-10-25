package okio;

import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0018\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00152\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J \u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u0007H\u0016J\u0018\u0010 \u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0017H\u0016J\u0018\u0010\"\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u0018\u0010#\u001a\u00020$2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0017H\u0016J\u0018\u0010%\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H\u0016J\u0018\u0010'\u001a\u00020$2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u0018\u0010(\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H\u0016J\b\u0010)\u001a\u00020$H\u0016J\b\u0010*\u001a\u00020\nH\u0016R\u0013\u0010\u0002\u001a\u00020\u00018\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005¨\u0006+"}, d2 = {"Lokio/ForwardingFileSystem;", "Lokio/FileSystem;", "delegate", "<init>", "(Lokio/FileSystem;)V", "()Lokio/FileSystem;", "onPathParameter", "Lokio/Path;", "path", "functionName", _UrlKt.FRAGMENT_ENCODE_SET, "parameterName", "onPathResult", "canonicalize", "metadataOrNull", "Lokio/FileMetadata;", "list", _UrlKt.FRAGMENT_ENCODE_SET, "dir", "listOrNull", "listRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", _UrlKt.FRAGMENT_ENCODE_SET, "openReadOnly", "Lokio/FileHandle;", "file", "openReadWrite", "mustCreate", "mustExist", "source", "Lokio/Source;", "sink", "Lokio/Sink;", "appendingSink", "createDirectory", _UrlKt.FRAGMENT_ENCODE_SET, "atomicMove", "target", "delete", "createSymlink", "close", "toString", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nForwardingFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ForwardingFileSystem.kt\nokio/ForwardingFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,252:1\n1634#2,3:253\n1634#2,3:256\n*S KotlinDebug\n*F\n+ 1 ForwardingFileSystem.kt\nokio/ForwardingFileSystem\n*L\n170#1:253,3\n178#1:256,3\n*E\n"})
/* loaded from: classes5.dex */
public abstract class ForwardingFileSystem extends FileSystem {
    private final FileSystem delegate;

    public static Path $r8$lambda$uSGFIfEGqOOIQR48FjtdbUCanJ4(ForwardingFileSystem r1, Path r2) {
        return r1.onPathResult(r2, "listRecursively");
    }

    public ForwardingFileSystem(FileSystem r1) {
        this.delegate = r1;
    }

    @Override // okio.FileSystem
    public Sink appendingSink(Path r3, boolean r4) {
        Path r32 = onPathParameter(r3, "appendingSink", "file");
        return this.delegate.appendingSink(r32, r4);
    }

    @Override // okio.FileSystem
    public void atomicMove(Path r3, Path r4) {
        Path r32 = onPathParameter(r3, "atomicMove", "source");
        Path r42 = onPathParameter(r4, "atomicMove", "target");
        this.delegate.atomicMove(r32, r42);
    }

    @Override // okio.FileSystem
    public Path canonicalize(Path r3) {
        Path r32 = onPathParameter(r3, "canonicalize", "path");
        return onPathResult(this.delegate.canonicalize(r32), "canonicalize");
    }

    @Override // okio.FileSystem, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    @Override // okio.FileSystem
    public void createDirectory(Path r3, boolean r4) {
        Path r32 = onPathParameter(r3, "createDirectory", "dir");
        this.delegate.createDirectory(r32, r4);
    }

    @Override // okio.FileSystem
    public void createSymlink(Path r3, Path r4) {
        Path r32 = onPathParameter(r3, "createSymlink", "source");
        Path r42 = onPathParameter(r4, "createSymlink", "target");
        this.delegate.createSymlink(r32, r42);
    }

    @JvmName(name = "delegate")
    public final FileSystem delegate() {
        return this.delegate;
    }

    @Override // okio.FileSystem
    public void delete(Path r3, boolean r4) {
        Path r32 = onPathParameter(r3, "delete", "path");
        this.delegate.delete(r32, r4);
    }

    @Override // okio.FileSystem
    public List<Path> list(Path r4) {
        Path r42 = onPathParameter(r4, "list", "dir");
        List<Path> r43 = this.delegate.list(r42);
        ArrayList r0 = new ArrayList();
        Iterator<T> r44 = r43.iterator();
    L4:
        if (r44.hasNext() == false) goto L6;
        r0.add(onPathResult((Path) r44.next(), "list"));
        goto L4
    L6:
        CollectionsKt.sort(r0);
        return r0;
    }

    @Override // okio.FileSystem
    public List<Path> listOrNull(Path r4) {
        Path r42 = onPathParameter(r4, "listOrNull", "dir");
        List<Path> r43 = this.delegate.listOrNull(r42);
        if (r43 != null) goto L6;
        return null;
    L6:
        ArrayList r0 = new ArrayList();
        Iterator<T> r44 = r43.iterator();
    L8:
        if (r44.hasNext() == false) goto L10;
        r0.add(onPathResult((Path) r44.next(), "listOrNull"));
        goto L8
    L10:
        CollectionsKt.sort(r0);
        return r0;
    }

    @Override // okio.FileSystem
    public Sequence<Path> listRecursively(Path r3, boolean r4) {
        Path r32 = onPathParameter(r3, "listRecursively", "dir");
        return SequencesKt.map(this.delegate.listRecursively(r32, r4), new ForwardingFileSystem$$ExternalSyntheticLambda0(this));
    }

    @Override // okio.FileSystem
    public FileMetadata metadataOrNull(Path r14) {
        Path r142 = onPathParameter(r14, "metadataOrNull", "path");
        FileMetadata r2 = this.delegate.metadataOrNull(r142);
        if (r2 != null) goto L7;
        return null;
    L7:
        if (r2.getSymlinkTarget() != null) goto L10;
        return r2;
    L10:
        return FileMetadata.copy$default(r2, false, false, onPathResult(r2.getSymlinkTarget(), "metadataOrNull"), null, null, null, null, null, Opcodes.INVOKE_POLYMORPHIC_RANGE, null);
    }

    public Path onPathParameter(Path r1, String r2, String r3) {
        return r1;
    }

    public Path onPathResult(Path r1, String r2) {
        return r1;
    }

    @Override // okio.FileSystem
    public FileHandle openReadOnly(Path r3) {
        Path r32 = onPathParameter(r3, "openReadOnly", "file");
        return this.delegate.openReadOnly(r32);
    }

    @Override // okio.FileSystem
    public FileHandle openReadWrite(Path r3, boolean r4, boolean r5) {
        Path r32 = onPathParameter(r3, "openReadWrite", "file");
        return this.delegate.openReadWrite(r32, r4, r5);
    }

    @Override // okio.FileSystem
    public Sink sink(Path r3, boolean r4) {
        Path r32 = onPathParameter(r3, "sink", "file");
        return this.delegate.sink(r32, r4);
    }

    @Override // okio.FileSystem
    public Source source(Path r3) {
        Path r32 = onPathParameter(r3, "source", "file");
        return this.delegate.source(r32);
    }

    public String toString() {
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + '(' + this.delegate + ')';
    }
}
