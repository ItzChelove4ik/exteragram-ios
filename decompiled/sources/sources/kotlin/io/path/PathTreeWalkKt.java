package kotlin.io.path;

import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a'\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0013\u0010\n\u001a\u00020\t*\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"j$/nio/file/Path", "path", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/LinkOption", "linkOptions", _UrlKt.FRAGMENT_ENCODE_SET, "keyOf", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Ljava/lang/Object;", "Lkotlin/io/path/PathNode;", _UrlKt.FRAGMENT_ENCODE_SET, "createsCycle", "(Lkotlin/io/path/PathNode;)Z", "kotlin-stdlib-jdk7"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class PathTreeWalkKt {
    public static final /* synthetic */ boolean access$createsCycle(PathNode r0) {
        return createsCycle(r0);
    }

    public static final /* synthetic */ Object access$keyOf(Path r0, LinkOption[] r1) {
        return keyOf(r0, r1);
    }

    private static final boolean createsCycle(PathNode r4) {
        PathNode r0 = r4.getParent();
    L3:
        if (r0 == null) goto L15;
        if (r0.getKey() != null) goto L7;
    L18:
        if (Files.isSameFile(r0.getPath(), r4.getPath()) == false) goto L14;
        return true;
    L14:
        r0 = r0.getParent();
        goto L3
    L7:
        if (r4.getKey() == null) goto L18;
        if (Intrinsics.areEqual(r0.getKey(), r4.getKey()) == false) goto L14;
        return true;
    L15:
        return false;
    }

    private static final Object keyOf(Path r2, LinkOption[] r3) {
        LinkOption[] r32 = (LinkOption[]) Arrays.copyOf(r3, r3.length);     // Catch: Throwable -> L4
        return Files.readAttributes(r2, BasicFileAttributes.class, (LinkOption[]) Arrays.copyOf(r32, r32.length)).fileKey();
    L4:
        return null;
    }
}
