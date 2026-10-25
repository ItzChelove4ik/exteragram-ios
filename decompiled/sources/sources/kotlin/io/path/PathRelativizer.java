package kotlin.io.path;

import c.f$$ExternalSyntheticBUOutline1;
import j$.nio.file.Path;
import j$.nio.file.Paths;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\n\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\n \t*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\n \t*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lkotlin/io/path/PathRelativizer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "path", "base", "tryRelativeTo", "(Lj$/nio/file/Path;Lj$/nio/file/Path;)Lj$/nio/file/Path;", "kotlin.jvm.PlatformType", "emptyPath", "Lj$/nio/file/Path;", "parentPath", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class PathRelativizer {
    public static final PathRelativizer INSTANCE = null;
    private static final Path emptyPath = null;
    private static final Path parentPath = null;

    static {
        INSTANCE = new PathRelativizer();
        emptyPath = Paths.get(_UrlKt.FRAGMENT_ENCODE_SET, new String[0]);
        parentPath = Paths.get("..", new String[0]);
    }

    private PathRelativizer() {
    }

    public final Path tryRelativeTo(Path r7, Path r8) {
        Path r6 = r8.normalize();
        Path r72 = r7.normalize();
        Path r82 = r6.relativize(r72);
        int r0 = Math.min(r6.getNameCount(), r72.getNameCount());
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L13;
        Path r4 = r6.getName(r2);
        Path r5 = parentPath;
        if (Intrinsics.areEqual(r4, r5) == false) goto L13;
        if (Intrinsics.areEqual(r72.getName(r2), r5) == false) goto L10;
        r2 = r2 + 1;
        goto L4
    L10:
        f$$ExternalSyntheticBUOutline1.m("Unable to compute relative path");
        return null;
    L13:
        if (Intrinsics.areEqual(r72, r6) == false) goto L15;
    L17:
        String r62 = r82.toString();
        if (StringsKt.endsWith$default(r62, r82.getFileSystem().getSeparator(), false, 2, null) == true) goto L20;
        return r82;
    L20:
        return r82.getFileSystem().getPath(StringsKt.dropLast(r62, r82.getFileSystem().getSeparator().length()), new String[0]);
    L15:
        if (Intrinsics.areEqual(r6, emptyPath) == false) goto L17;
        return r72;
    }
}
