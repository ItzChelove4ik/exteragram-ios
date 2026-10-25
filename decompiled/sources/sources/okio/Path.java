package okio;

import j$.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0007\u0018\u0000 32\b\u0012\u0004\u0012\u00020\u00000\u0001:\u00013B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\n\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b\b\u0010\u000bJ\u001f\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\b\u0010\u000eJ\u0015\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001b\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u001aH\u0096\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001f\u0010 R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010#R\u0013\u0010&\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0011\u0010+\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0013\u0010.\u001a\u0004\u0018\u00010-8G¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0011\u00100\u001a\u00020\u00028G¢\u0006\u0006\u001a\u0004\b0\u0010#R\u0011\u00101\u001a\u00020\u00068G¢\u0006\u0006\u001a\u0004\b1\u0010 R\u0013\u00102\u001a\u0004\u0018\u00010\u00008G¢\u0006\u0006\u001a\u0004\b2\u0010%¨\u00064"}, d2 = {"Lokio/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/ByteString;", "bytes", "<init>", "(Lokio/ByteString;)V", _UrlKt.FRAGMENT_ENCODE_SET, "child", "resolve", "(Ljava/lang/String;)Lokio/Path;", "div", "(Lokio/Path;)Lokio/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "normalize", "(Lokio/Path;Z)Lokio/Path;", "other", "relativeTo", "Ljava/io/File;", "toFile", "()Ljava/io/File;", "j$/nio/file/Path", "toNioPath", "()Lj$/nio/file/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "compareTo", "(Lokio/Path;)I", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Lokio/ByteString;", "getBytes$okio", "()Lokio/ByteString;", "getRoot", "()Lokio/Path;", "root", _UrlKt.FRAGMENT_ENCODE_SET, "getSegmentsBytes", "()Ljava/util/List;", "segmentsBytes", "isAbsolute", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "volumeLetter", "()Ljava/lang/Character;", "nameBytes", "name", "parent", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Path.kt\nokio/Path\n+ 2 Path.kt\nokio/internal/-Path\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,132:1\n39#2,3:133\n47#2,28:136\n53#2,22:168\n106#2:190\n111#2:191\n116#2,6:192\n133#2,5:198\n143#2:203\n148#2,25:204\n188#2:229\n193#2,11:230\n198#2,6:241\n193#2,11:247\n198#2,6:258\n222#2,41:264\n267#2:305\n281#2:306\n286#2:307\n291#2:308\n296#2:309\n1563#3:164\n1634#3,3:165\n*S KotlinDebug\n*F\n+ 1 Path.kt\nokio/Path\n*L\n44#1:133,3\n47#1:136,28\n50#1:168,22\n53#1:190\n56#1:191\n60#1:192,6\n64#1:198,5\n68#1:203\n72#1:204,25\n75#1:229\n78#1:230,11\n81#1:241,6\n87#1:247,11\n90#1:258,6\n95#1:264,41\n97#1:305\n104#1:306\n106#1:307\n108#1:308\n110#1:309\n47#1:164\n47#1:165,3\n*E\n"})
/* loaded from: classes5.dex */
public final class Path implements Comparable<Path> {
    public static final Companion Companion = null;

    @JvmField
    public static final String DIRECTORY_SEPARATOR = null;
    private final ByteString bytes;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\n\u001a\u00020\u0007*\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\r\u001a\u00020\u0007*\u00020\u000b2\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\fJ\u001d\u0010\r\u001a\u00020\u0007*\u00020\u000e2\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lokio/Path$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "normalize", "Lokio/Path;", "get", "(Ljava/lang/String;Z)Lokio/Path;", "toPath", "Ljava/io/File;", "(Ljava/io/File;Z)Lokio/Path;", "toOkioPath", "j$/nio/file/Path", "(Lj$/nio/file/Path;Z)Lokio/Path;", "DIRECTORY_SEPARATOR", "Ljava/lang/String;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ Path get$default(Companion r0, j$.nio.file.Path r1, boolean r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = false;
        L6:
            return r0.get(r1, r2);
        }

        public static /* synthetic */ Path get$default(Companion r0, File r1, boolean r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = false;
        L6:
            return r0.get(r1, r2);
        }

        public static /* synthetic */ Path get$default(Companion r0, String r1, boolean r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r2 = false;
        L6:
            return r0.get(r1, r2);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name = "get")
        public final Path get(j$.nio.file.Path r1, boolean r2) {
            return get(r1.toString(), r2);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name = "get")
        public final Path get(File r1, boolean r2) {
            return get(r1.toString(), r2);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name = "get")
        public final Path get(String r1, boolean r2) {
            return okio.internal.Path.commonToPath(r1, r2);
        }
    }

    static {
        Companion = new Companion(null);
        DIRECTORY_SEPARATOR = File.separator;
    }

    public Path(ByteString r1) {
        this.bytes = r1;
    }

    public static /* synthetic */ Path resolve$default(Path r0, Path r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return r0.resolve(r1, r2);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Path r1) {
        return compareTo2(r1);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Path r1) {
        return getBytes$okio().compareTo2(r1.getBytes$okio());
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof Path) == true) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(((Path) r2).getBytes$okio(), getBytes$okio()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final ByteString getBytes$okio() {
        return this.bytes;
    }

    public final Path getRoot() {
        int r0 = okio.internal.Path.access$rootLength(this);
        if (r0 != (-1)) goto L7;
        return null;
    L7:
        return new Path(getBytes$okio().substring(0, r0));
    }

    public final List<ByteString> getSegmentsBytes() {
        ArrayList r0 = new ArrayList();
        int r1 = okio.internal.Path.access$rootLength(this);
        if (r1 != (-1)) goto L6;
        r1 = 0;
    L10:
        int r2 = getBytes$okio().size();
        int r4 = r1;
    L11:
        if (r1 >= r2) goto L19;
        if (getBytes$okio().getByte(r1) != 47) goto L15;
    L16:
        r0.add(getBytes$okio().substring(r4, r1));
        r4 = r1 + 1;
    L17:
        r1 = r1 + 1;
        goto L11
    L15:
        if (getBytes$okio().getByte(r1) != 92) goto L17;
    L19:
        if (r4 >= getBytes$okio().size()) goto L21;
        r0.add(getBytes$okio().substring(r4, getBytes$okio().size()));
    L21:
        return r0;
    L6:
        if (r1 >= getBytes$okio().size()) goto L10;
        if (getBytes$okio().getByte(r1) != 92) goto L10;
        r1 = r1 + 1;
        goto L10
    }

    public int hashCode() {
        return getBytes$okio().hashCode();
    }

    public final boolean isAbsolute() {
        if (okio.internal.Path.access$rootLength(this) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    @JvmName(name = "name")
    public final String name() {
        return nameBytes().utf8();
    }

    @JvmName(name = "nameBytes")
    public final ByteString nameBytes() {
        int r0 = okio.internal.Path.access$getIndexOfLastSlash(this);
        if (r0 == (-1)) goto L7;
        return ByteString.substring$default(getBytes$okio(), r0 + 1, 0, 2, null);
    L7:
        if (volumeLetter() == null) goto L13;
        if (getBytes$okio().size() != 2) goto L13;
        return ByteString.EMPTY;
    L13:
        return getBytes$okio();
    }

    @JvmName(name = "parent")
    public final Path parent() {
        if (Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getDOT$p()) == false) goto L5;
    L41:
        return null;
    L5:
        if (Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getSLASH$p()) == true) goto L41;
        if (Intrinsics.areEqual(getBytes$okio(), okio.internal.Path.access$getBACKSLASH$p()) == true) goto L41;
        if (okio.internal.Path.access$lastSegmentIsDotDot(this) == true) goto L41;
        int r0 = okio.internal.Path.access$getIndexOfLastSlash(this);
        if (r0 == 2) goto L14;
    L20:
        if (r0 != 1) goto L25;
        if (getBytes$okio().startsWith(okio.internal.Path.access$getBACKSLASH$p()) == false) goto L25;
        return null;
    L25:
        if (r0 == (-1)) goto L27;
    L33:
        if (r0 == (-1)) goto L35;
        if (r0 != 0) goto L40;
        return new Path(ByteString.substring$default(getBytes$okio(), 0, 1, 1, null));
    L40:
        return new Path(ByteString.substring$default(getBytes$okio(), 0, r0, 1, null));
    L35:
        return new Path(okio.internal.Path.access$getDOT$p());
    L27:
        if (volumeLetter() == null) goto L33;
        if (getBytes$okio().size() != 2) goto L32;
        return null;
    L32:
        return new Path(ByteString.substring$default(getBytes$okio(), 0, 2, 1, null));
    L14:
        if (volumeLetter() == null) goto L20;
        if (getBytes$okio().size() != 3) goto L19;
        return null;
    L19:
        return new Path(ByteString.substring$default(getBytes$okio(), 0, 3, 1, null));
    }

    public final Path relativeTo(Path r9) {
        if (Intrinsics.areEqual(getRoot(), r9.getRoot()) == false) goto L35;
        List<ByteString> r0 = getSegmentsBytes();
        List<ByteString> r2 = r9.getSegmentsBytes();
        int r3 = Math.min(r0.size(), r2.size());
        int r5 = 0;
    L5:
        if (r5 >= r3) goto L9;
        if (Intrinsics.areEqual(r0.get(r5), r2.get(r5)) == false) goto L9;
        r5 = r5 + 1;
    L9:
        if (r5 != r3) goto L15;
        if (getBytes$okio().size() != r9.getBytes$okio().size()) goto L15;
        return Companion.get$default(Companion, ".", false, 1, null);
    L15:
        if (r2.subList(r5, r2.size()).indexOf(okio.internal.Path.access$getDOT_DOT$p()) != (-1)) goto L33;
        if (Intrinsics.areEqual(r9.getBytes$okio(), okio.internal.Path.access$getDOT$p()) == false) goto L19;
        return this;
    L19:
        Buffer r1 = new Buffer();
        ByteString r92 = okio.internal.Path.access$getSlash(r9);
        if (r92 != null) goto L24;
        r92 = okio.internal.Path.access$getSlash(this);
        if (r92 != null) goto L24;
        r92 = okio.internal.Path.access$toSlash(DIRECTORY_SEPARATOR);
    L24:
        int r8 = r2.size();
        int r22 = r5;
    L25:
        if (r22 >= r8) goto L27;
        r1.write(okio.internal.Path.access$getDOT_DOT$p());
        r1.write(r92);
        r22 = r22 + 1;
        goto L25
    L27:
        int r82 = r0.size();
    L28:
        if (r5 >= r82) goto L31;
        r1.write(r0.get(r5));
        r1.write(r92);
        r5 = r5 + 1;
        goto L28
    L31:
        return okio.internal.Path.toPath(r1, false);
    L33:
        throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + r9).toString());
    L35:
        throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + r9).toString());
    }

    @JvmName(name = "resolve")
    public final Path resolve(String r2) {
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(new Buffer().writeUtf8(r2), false), false);
    }

    @JvmName(name = "resolve")
    public final Path resolve(Path r2) {
        return okio.internal.Path.commonResolve(this, r2, false);
    }

    public final Path resolve(Path r1, boolean r2) {
        return okio.internal.Path.commonResolve(this, r1, r2);
    }

    public final File toFile() {
        return new File(toString());
    }

    public final j$.nio.file.Path toNioPath() {
        return Paths.get(toString(), new String[0]);
    }

    public String toString() {
        return getBytes$okio().utf8();
    }

    @JvmName(name = "volumeLetter")
    public final Character volumeLetter() {
        if (ByteString.indexOf$default(getBytes$okio(), okio.internal.Path.access$getSLASH$p(), 0, 2, null) == (-1)) goto L6;
        return null;
    L6:
        if (getBytes$okio().size() >= 2) goto L9;
        return null;
    L9:
        if (getBytes$okio().getByte(1) == 58) goto L11;
        return null;
    L11:
        char r5 = (char) getBytes$okio().getByte(0);
        if ('a' > r5) goto L17;
        if (r5 >= '{') goto L17;
    L21:
        return Character.valueOf(r5);
    L17:
        if ('A' <= r5) goto L19;
    L22:
        return null;
    L19:
        if (r5 >= '[') goto L22;
        goto L22
    }
}
