package kotlin.io.path;

import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlin/io/path/DefaultCopyActionContext;", "Lkotlin/io/path/CopyActionContext;", "<init>", "()V", "j$/nio/file/Path", "target", _UrlKt.FRAGMENT_ENCODE_SET, "followLinks", "Lkotlin/io/path/CopyActionResult;", "copyToIgnoringExistingDirectory", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Z)Lkotlin/io/path/CopyActionResult;", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalPathApi
/* loaded from: classes5.dex */
final class DefaultCopyActionContext implements CopyActionContext {
    public static final DefaultCopyActionContext INSTANCE = null;

    static {
        INSTANCE = new DefaultCopyActionContext();
    }

    private DefaultCopyActionContext() {
    }

    @Override // kotlin.io.path.CopyActionContext
    public CopyActionResult copyToIgnoringExistingDirectory(Path r2, Path r3, boolean r4) {
        LinkOption[] r1 = LinkFollowing.INSTANCE.toLinkOptions(r4);
        LinkOption[] r42 = (LinkOption[]) Arrays.copyOf(r1, r1.length);
        if (Files.isDirectory(r2, (LinkOption[]) Arrays.copyOf(r42, r42.length)) == true) goto L5;
    L7:
        CopyOption[] r12 = (CopyOption[]) Arrays.copyOf(r1, r1.length);
        Files.copy(r2, r3, (CopyOption[]) Arrays.copyOf(r12, r12.length));
    L9:
        return CopyActionResult.CONTINUE;
    L5:
        if (Files.isDirectory(r3, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)) == false) goto L7;
        Unit r13 = Unit.INSTANCE;
        goto L9
    }
}
