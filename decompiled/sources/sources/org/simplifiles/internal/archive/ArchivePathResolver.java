package org.simplifiles.internal.archive;

import j$.nio.file.Path;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.exception.UnsafeArchivePathException;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/simplifiles/internal/archive/ArchivePathResolver;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "root", _UrlKt.FRAGMENT_ENCODE_SET, "path", _UrlKt.FRAGMENT_ENCODE_SET, "allowAbsolute", "resolve", "(Lj$/nio/file/Path;Ljava/lang/String;Z)Lj$/nio/file/Path;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ArchivePathResolver {
    public static final ArchivePathResolver INSTANCE = null;

    static {
        INSTANCE = new ArchivePathResolver();
    }

    private ArchivePathResolver() {
    }

    public static /* synthetic */ Path resolve$default(ArchivePathResolver r0, Path r1, String r2, boolean r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = false;
    L6:
        return r0.resolve(r1, r2, r3);
    }

    public final Path resolve(Path r3, String r4, boolean r5) {
        Path r2 = r3.toAbsolutePath().normalize();
        ArchivePathAnalysis r32 = ArchivePathAnalyzer.INSTANCE.analyze(r4);
        String r0 = r32.getNormalizedPath();
        if (r32.isAbsolute() == false) goto L9;
        if (r5 == true) goto L9;
        throw new UnsafeArchivePathException(r4, "path must be relative");
    L9:
        if (r32.getContainsParentTraversal() == true) goto L21;
        if (r32.isEmpty() == true) goto L19;
        if (r0 == null) goto L19;
        Path r33 = r2.resolve(r0).normalize();
        if (r33.startsWith(r2) == false) goto L17;
        return r33;
    L17:
        throw new UnsafeArchivePathException(r4, "path escapes archive root");
    L19:
        throw new UnsafeArchivePathException(r4, "path must not be empty");
    L21:
        throw new UnsafeArchivePathException(r4, "path must not contain parent traversal");
    }
}
