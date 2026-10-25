package kotlin.io;

import java.io.File;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0086\u0080\u0004\u001a\u000e\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0086\u0080\u0004¨\u0006\u0007"}, d2 = {"walk", "Lkotlin/io/FileTreeWalk;", "Ljava/io/File;", "direction", "Lkotlin/io/FileWalkDirection;", "walkTopDown", "walkBottomUp", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/FilesKt")
/* loaded from: classes.dex */
class FilesKt__FileTreeWalkKt extends FilesKt__FileReadWriteKt {
    public FilesKt__FileTreeWalkKt() {
    }

    public static final FileTreeWalk walk(File r1, FileWalkDirection r2) {
        return new FileTreeWalk(r1, r2);
    }

    public static /* synthetic */ FileTreeWalk walk$default(File r0, FileWalkDirection r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = FileWalkDirection.TOP_DOWN;
    L6:
        return walk(r0, r1);
    }

    public static final FileTreeWalk walkBottomUp(File r1) {
        return walk(r1, FileWalkDirection.BOTTOM_UP);
    }

    public static final FileTreeWalk walkTopDown(File r1) {
        return walk(r1, FileWalkDirection.TOP_DOWN);
    }
}
