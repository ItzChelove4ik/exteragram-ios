package kotlin.io.path;

import j$.nio.file.FileVisitOption;
import j$.nio.file.LinkOption;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\n\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012¨\u0006\u0014"}, d2 = {"Lkotlin/io/path/LinkFollowing;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "followLinks", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/LinkOption", "toLinkOptions", "(Z)[Lj$/nio/file/LinkOption;", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/FileVisitOption", "toVisitOptions", "(Z)Ljava/util/Set;", "nofollowLinkOption", "[Lj$/nio/file/LinkOption;", "followLinkOption", "nofollowVisitOption", "Ljava/util/Set;", "followVisitOption", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class LinkFollowing {
    public static final LinkFollowing INSTANCE = null;
    private static final LinkOption[] followLinkOption = null;
    private static final Set<FileVisitOption> followVisitOption = null;
    private static final LinkOption[] nofollowLinkOption = null;
    private static final Set<FileVisitOption> nofollowVisitOption = null;

    static {
        INSTANCE = new LinkFollowing();
        nofollowLinkOption = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
        followLinkOption = new LinkOption[0];
        nofollowVisitOption = SetsKt.emptySet();
        followVisitOption = SetsKt.setOf(FileVisitOption.FOLLOW_LINKS);
    }

    private LinkFollowing() {
    }

    public final LinkOption[] toLinkOptions(boolean r1) {
        if (r1 == false) goto L6;
        return followLinkOption;
    L6:
        return nofollowLinkOption;
    }

    public final Set<FileVisitOption> toVisitOptions(boolean r1) {
        if (r1 == false) goto L6;
        return followVisitOption;
    L6:
        return nofollowVisitOption;
    }
}
