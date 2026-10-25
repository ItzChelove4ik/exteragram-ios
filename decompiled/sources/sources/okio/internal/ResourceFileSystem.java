package okio.internal;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.Sink;
import okio.Source;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 02\u00020\u0001:\u00010B#\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u0018\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\n2\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fH\u0016J \u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0016J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\fH\u0016J\u0018\u0010!\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0005H\u0016J\u0018\u0010#\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0005H\u0016J\u0018\u0010$\u001a\u00020%2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0005H\u0016J\u0018\u0010&\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010'\u001a\u00020\fH\u0016J\u0018\u0010(\u001a\u00020%2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0005H\u0016J\u0018\u0010)\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010'\u001a\u00020\fH\u0016J\f\u0010*\u001a\u00020+*\u00020\fH\u0002J\u001e\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u000b0\n*\u00020\u0003H\u0002J\u001a\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020.H\u0002J\u001a\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020.H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u000b0\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u00061"}, d2 = {"Lokio/internal/ResourceFileSystem;", "Lokio/FileSystem;", "classLoader", "Ljava/lang/ClassLoader;", "indexEagerly", _UrlKt.FRAGMENT_ENCODE_SET, "systemFileSystem", "<init>", "(Ljava/lang/ClassLoader;ZLokio/FileSystem;)V", "roots", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", "Lokio/Path;", "getRoots", "()Ljava/util/List;", "roots$delegate", "Lkotlin/Lazy;", "canonicalize", "path", "canonicalizeInternal", "list", "dir", "listOrNull", "openReadOnly", "Lokio/FileHandle;", "file", "openReadWrite", "mustCreate", "mustExist", "metadataOrNull", "Lokio/FileMetadata;", "source", "Lokio/Source;", "sink", "Lokio/Sink;", "appendingSink", "createDirectory", _UrlKt.FRAGMENT_ENCODE_SET, "atomicMove", "target", "delete", "createSymlink", "toRelativePath", _UrlKt.FRAGMENT_ENCODE_SET, "toClasspathRoots", "toFileRoot", "Ljava/net/URL;", "toJarRoot", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nResourceFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResourceFileSystem.kt\nokio/internal/ResourceFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,215:1\n774#2:216\n865#2,2:217\n1563#2:219\n1634#2,3:220\n774#2:223\n865#2,2:224\n1563#2:226\n1634#2,3:227\n1617#2,9:230\n1869#2:239\n1870#2:241\n1626#2:242\n1617#2,9:243\n1869#2:252\n1870#2:254\n1626#2:255\n1#3:240\n1#3:253\n*S KotlinDebug\n*F\n+ 1 ResourceFileSystem.kt\nokio/internal/ResourceFileSystem\n*L\n75#1:216\n75#1:217,2\n76#1:219\n76#1:220,3\n91#1:223\n91#1:224,2\n92#1:226\n92#1:227,3\n178#1:230,9\n178#1:239\n178#1:241\n178#1:242\n179#1:243,9\n179#1:252\n179#1:254\n179#1:255\n178#1:240\n179#1:253\n*E\n"})
/* loaded from: classes5.dex */
public final class ResourceFileSystem extends FileSystem {
    private static final Companion Companion = null;
    private static final Path ROOT = null;
    private final ClassLoader classLoader;
    private final Lazy roots$delegate;
    private final FileSystem systemFileSystem;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lokio/internal/ResourceFileSystem$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ROOT", "Lokio/Path;", "getROOT", "()Lokio/Path;", "removeBase", "base", "keepPath", _UrlKt.FRAGMENT_ENCODE_SET, "path", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ boolean access$keepPath(Companion r0, Path r1) {
            return r0.keepPath(r1);
        }

        private final boolean keepPath(Path r2) {
            return !StringsKt.endsWith(r2.name(), ".class", true);
        }

        public final Path getROOT() {
            return ResourceFileSystem.access$getROOT$cp();
        }

        public final Path removeBase(Path r7, Path r8) {
            String r82 = r8.toString();
            return getROOT().resolve(StringsKt.replace$default(StringsKt.removePrefix(r7.toString(), r82), '\\', '/', false, 4, null));
        }
    }

    public static boolean $r8$lambda$JLwKVsMM0AyplhHMXqN1dQMlhHM(ZipEntry r1) {
        return Companion.access$keepPath(Companion, r1.getCanonicalPath());
    }

    public static List $r8$lambda$facOLvgH430tWEbMJ6M5fXJ61zo(ResourceFileSystem r1) {
        return r1.toClasspathRoots(r1.classLoader);
    }

    static {
        Companion = new Companion(null);
        ROOT = Path.Companion.get$default(Path.Companion, "/", false, 1, null);
    }

    public ResourceFileSystem(ClassLoader r1, boolean r2, FileSystem r3) {
        this.classLoader = r1;
        this.systemFileSystem = r3;
        this.roots$delegate = LazyKt.lazy(new ResourceFileSystem$$ExternalSyntheticLambda0(this));
        if (r2 == false) goto L6;
        getRoots().size();
        return;
    }

    public /* synthetic */ ResourceFileSystem(ClassLoader r1, boolean r2, FileSystem r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 4) == 0) goto L5;
        r3 = FileSystem.SYSTEM;
    L5:
        this(r1, r2, r3);
    }

    public static final /* synthetic */ Path access$getROOT$cp() {
        return ROOT;
    }

    private final Path canonicalizeInternal(Path r2) {
        return ROOT.resolve(r2, true);
    }

    private final List<Pair<FileSystem, Path>> getRoots() {
        return (List) this.roots$delegate.getValue();
    }

    private final List<Pair<FileSystem, Path>> toClasspathRoots(ClassLoader r7) {
        ArrayList r0 = Collections.list(r7.getResources(_UrlKt.FRAGMENT_ENCODE_SET));
        ArrayList r1 = new ArrayList();
        int r2 = r0.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L7;
        Object r5 = r0.get(r4);
        r4 = r4 + 1;
        Pair<FileSystem, Path> r52 = toFileRoot((URL) r5);
        if (r52 == null) goto L3;
        r1.add(r52);
        goto L3
    L7:
        ArrayList r72 = Collections.list(r7.getResources("META-INF/MANIFEST.MF"));
        ArrayList r02 = new ArrayList();
        int r22 = r72.size();
    L8:
        if (r3 >= r22) goto L13;
        Object r42 = r72.get(r3);
        r3 = r3 + 1;
        Pair<FileSystem, Path> r43 = toJarRoot((URL) r42);
        if (r43 == null) goto L8;
        r02.add(r43);
        goto L8
    L13:
        return CollectionsKt.plus(r1, r02);
    }

    private final Pair<FileSystem, Path> toFileRoot(URL r5) {
        if (Intrinsics.areEqual(r5.getProtocol(), "file") == true) goto L6;
        return null;
    L6:
        return TuplesKt.to(this.systemFileSystem, Path.Companion.get$default(Path.Companion, new File(r5.toURI()), false, 1, null));
    }

    private final Pair<FileSystem, Path> toJarRoot(URL r9) {
        String r0 = r9.toString();
        if (StringsKt.startsWith$default(r0, "jar:file:", false, 2, null) == true) goto L5;
        return null;
    L5:
        int r92 = StringsKt.lastIndexOf$default(r0, "!", 0, false, 6, null);
        if (r92 != (-1)) goto L9;
        return null;
    L9:
        return TuplesKt.to(ZipFilesKt.openZip(Path.Companion.get$default(Path.Companion, new File(URI.create(r0.substring(4, r92))), false, 1, null), this.systemFileSystem, new ResourceFileSystem$$ExternalSyntheticLambda1()), ROOT);
    }

    private final String toRelativePath(Path r1) {
        return canonicalizeInternal(r1).relativeTo(ROOT).toString();
    }

    @Override // okio.FileSystem
    public Sink appendingSink(Path r1, boolean r2) {
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void atomicMove(Path r1, Path r2) {
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public Path canonicalize(Path r1) {
        return canonicalizeInternal(r1);
    }

    @Override // okio.FileSystem
    public void createDirectory(Path r1, boolean r2) {
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void createSymlink(Path r1, Path r2) {
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void delete(Path r1, boolean r2) {
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public List<Path> list(Path r12) {
        String r0 = toRelativePath(r12);
        LinkedHashSet r1 = new LinkedHashSet();
        Iterator<Pair<FileSystem, Path>> r11 = getRoots().iterator();
        boolean r3 = false;
    L4:
        if (r11.hasNext() == false) goto L17;
        Pair<FileSystem, Path> r4 = r11.next();
        FileSystem r5 = r4.component1();
        Path r42 = r4.component2();
        List<Path> r52 = r5.list(r42.resolve(r0));     // Catch: IOException -> L22
        ArrayList r6 = new ArrayList();     // Catch: IOException -> L22
        Iterator<T> r53 = r52.iterator();     // Catch: IOException -> L22
    L7:
        if (r53.hasNext() == false) goto L12;
        Object r7 = r53.next();     // Catch: IOException -> L22
        if (Companion.access$keepPath(Companion, (Path) r7) == false) goto L7;
        r6.add(r7);     // Catch: IOException -> L22
        goto L7
    L12:
        ArrayList r54 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r6, 10));     // Catch: IOException -> L22
        int r72 = r6.size();     // Catch: IOException -> L22
        int r8 = 0;
    L13:
        if (r8 >= r72) goto L15;
        Object r9 = r6.get(r8);     // Catch: IOException -> L22
        r8 = r8 + 1;     // Catch: IOException -> L22
        r54.add(Companion.removeBase((Path) r9, r42));     // Catch: IOException -> L22
        goto L13
    L15:
        CollectionsKt.addAll(r1, r54);     // Catch: IOException -> L22
        r3 = true;
        goto L4
    L17:
        if (r3 == true) goto L19;
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("file not found: ", r12);
        return null;
    L19:
        return CollectionsKt.toList(r1);
    }

    @Override // okio.FileSystem
    public List<Path> listOrNull(Path r11) {
        String r112 = toRelativePath(r11);
        LinkedHashSet r0 = new LinkedHashSet();
        Iterator<Pair<FileSystem, Path>> r10 = getRoots().iterator();
        boolean r2 = false;
    L3:
        ArrayList r4 = null;
        if (r10.hasNext() == false) goto L19;
        Pair<FileSystem, Path> r3 = r10.next();
        FileSystem r5 = r3.component1();
        Path r32 = r3.component2();
        List<Path> r52 = r5.listOrNull(r32.resolve(r112));
        if (r52 == null) goto L17;
        ArrayList r42 = new ArrayList();
        Iterator<T> r53 = r52.iterator();
    L9:
        if (r53.hasNext() == false) goto L13;
        Object r6 = r53.next();
        if (Companion.access$keepPath(Companion, (Path) r6) == false) goto L9;
        r42.add(r6);
        goto L9
    L13:
        ArrayList r54 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r42, 10));
        int r62 = r42.size();
        int r7 = 0;
    L14:
        if (r7 >= r62) goto L16;
        Object r8 = r42.get(r7);
        r7 = r7 + 1;
        r54.add(Companion.removeBase((Path) r8, r32));
        goto L14
    L16:
        r4 = r54;
    L17:
        if (r4 == null) goto L3;
        CollectionsKt.addAll(r0, r4);
        r2 = true;
        goto L3
    L19:
        if (r2 == true) goto L21;
        return null;
    L21:
        return CollectionsKt.toList(r0);
    }

    @Override // okio.FileSystem
    public FileMetadata metadataOrNull(Path r4) {
        if (Companion.access$keepPath(Companion, r4) == true) goto L5;
        return null;
    L5:
        String r42 = toRelativePath(r4);
        Iterator<Pair<FileSystem, Path>> r3 = getRoots().iterator();
    L7:
        if (r3.hasNext() == false) goto L12;
        Pair<FileSystem, Path> r0 = r3.next();
        FileMetadata r02 = r0.component1().metadataOrNull(r0.component2().resolve(r42));
        if (r02 == null) goto L7;
        return r02;
    L12:
        return null;
    }

    @Override // okio.FileSystem
    public FileHandle openReadOnly(Path r6) {
        if (Companion.access$keepPath(Companion, r6) == false) goto L12;
        String r0 = toRelativePath(r6);
        Iterator<Pair<FileSystem, Path>> r5 = getRoots().iterator();
    L6:
        if (r5.hasNext() == false) goto L10;
        Pair<FileSystem, Path> r3 = r5.next();
        FileSystem r4 = r3.component1();
        return r4.openReadOnly(r3.component2().resolve(r0));
    L10:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("file not found: ", r6);
        return null;
    L12:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("file not found: ", r6);
        return null;
    }

    @Override // okio.FileSystem
    public FileHandle openReadWrite(Path r1, boolean r2, boolean r3) {
        throw new IOException("resources are not writable");
    }

    @Override // okio.FileSystem
    public Sink sink(Path r1, boolean r2) {
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public Source source(Path r6) {
        if (Companion.access$keepPath(Companion, r6) == false) goto L13;
        Path r0 = ROOT;
        Path r02 = Path.resolve$default(r0, r6, false, 2, null).relativeTo(r0);
        URL r5 = this.classLoader.getResource(r02.toString());
        if (r5 == null) goto L11;
        URLConnection r52 = r5.openConnection();
        if ((r52 instanceof JarURLConnection) == false) goto L10;
        ((JarURLConnection) r52).setUseCaches(false);
    L10:
        return Okio.source(r52.getInputStream());
    L11:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("file not found: ", r6);
        return null;
    L13:
        ZipFileSystem$$ExternalSyntheticBUOutline1.m("file not found: ", r6);
        return null;
    }
}
