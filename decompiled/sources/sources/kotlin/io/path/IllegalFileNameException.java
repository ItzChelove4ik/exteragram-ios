package kotlin.io.path;

import j$.nio.file.Path;
import java.nio.file.FileSystemException;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/io/path/IllegalFileNameException;", "Ljava/nio/file/FileSystemException;", "j$/nio/file/Path", "file", "other", _UrlKt.FRAGMENT_ENCODE_SET, "message", "<init>", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Ljava/lang/String;)V", "(Lj$/nio/file/Path;)V", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class IllegalFileNameException extends FileSystemException {
    public IllegalFileNameException(Path r2) {
        this(r2, null, null);
    }

    public IllegalFileNameException(Path r1, Path r2, String r3) {
        String r12 = r1.toString();
        if (r2 == null) goto L5;
        String r22 = r2.toString();
    L6:
        super(r12, r22, r3);
        return;
    L5:
        r22 = null;
        goto L6
    }
}
