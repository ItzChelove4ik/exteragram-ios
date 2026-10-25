package org.simplifiles.internal.files;

import j$.nio.file.Path;
import j$.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.exception.UnsafePathException;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/simplifiles/internal/files/SafePathResolver;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "root", _UrlKt.FRAGMENT_ENCODE_SET, "path", "resolveInside", "(Lj$/nio/file/Path;Ljava/lang/String;)Lj$/nio/file/Path;", "Lkotlin/text/Regex;", "WINDOWS_ABSOLUTE_PATH", "Lkotlin/text/Regex;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafePathResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafePathResolver.kt\norg/simplifiles/internal/files/SafePathResolver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,40:1\n1807#2,3:41\n*S KotlinDebug\n*F\n+ 1 SafePathResolver.kt\norg/simplifiles/internal/files/SafePathResolver\n*L\n20#1:41,3\n*E\n"})
/* loaded from: classes.dex */
public final class SafePathResolver {
    public static final SafePathResolver INSTANCE = null;
    private static final Regex WINDOWS_ABSOLUTE_PATH = null;

    static {
        INSTANCE = new SafePathResolver();
        WINDOWS_ABSOLUTE_PATH = new Regex("^[A-Za-z]:[/\\\\].*");
    }

    private SafePathResolver() {
    }

    public final Path resolveInside(Path r11, String r12) {
        if (StringsKt.isBlank(r12) == true) goto L35;
        if (StringsKt.startsWith$default(r12, "/", false, 2, null) == true) goto L33;
        if (StringsKt.startsWith$default(r12, "\\", false, 2, null) == true) goto L33;
        if (WINDOWS_ABSOLUTE_PATH.matches(r12) == true) goto L33;
        List r10 = StringsKt.split$default(r12, new char[]{'/', '\\'}, false, 0, 6, null);
        if ((r10 instanceof Collection) == true) goto L13;
    L15:
        Iterator r102 = r10.iterator();
    L17:
        if (r102.hasNext() == false) goto L23;
        if (Intrinsics.areEqual((String) r102.next(), "..") == false) goto L17;
        throw new UnsafePathException(r12, "path must not contain parent traversal");
    L23:
        Path r103 = Paths.get(r12, new String[0]);
        if (r103.isAbsolute() == true) goto L31;
        Path r112 = r11.toAbsolutePath().normalize();
        Path r104 = r112.resolve(r103).normalize();
        if (r104.startsWith(r112) == false) goto L29;
        return r104;
    L29:
        throw new UnsafePathException(r12, "path escapes root");
    L31:
        throw new UnsafePathException(r12, "path must be relative");
    L13:
        if (r10.isEmpty() == false) goto L15;
    L33:
        throw new UnsafePathException(r12, "path must be relative");
    L35:
        throw new UnsafePathException(r12, "path must not be blank");
    }
}
