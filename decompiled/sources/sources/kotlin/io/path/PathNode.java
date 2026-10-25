package kotlin.io.path;

import com.exteragram.messenger.plugins.PluginsConstants;
import j$.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0010(\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00018\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00008\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R*\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lkotlin/io/path/PathNode;", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/Path", "path", PluginsConstants.Settings.KEY, "parent", "<init>", "(Lj$/nio/file/Path;Ljava/lang/Object;Lkotlin/io/path/PathNode;)V", "Lj$/nio/file/Path;", "getPath", "()Lj$/nio/file/Path;", "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", "Lkotlin/io/path/PathNode;", "getParent", "()Lkotlin/io/path/PathNode;", _UrlKt.FRAGMENT_ENCODE_SET, "contentIterator", "Ljava/util/Iterator;", "getContentIterator", "()Ljava/util/Iterator;", "setContentIterator", "(Ljava/util/Iterator;)V", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class PathNode {
    private Iterator<PathNode> contentIterator;
    private final Object key;
    private final PathNode parent;
    private final Path path;

    public PathNode(Path r1, Object r2, PathNode r3) {
        this.path = r1;
        this.key = r2;
        this.parent = r3;
    }

    public final Iterator<PathNode> getContentIterator() {
        return this.contentIterator;
    }

    public final Object getKey() {
        return this.key;
    }

    public final PathNode getParent() {
        return this.parent;
    }

    public final Path getPath() {
        return this.path;
    }

    public final void setContentIterator(Iterator<PathNode> r1) {
        this.contentIterator = r1;
    }
}
