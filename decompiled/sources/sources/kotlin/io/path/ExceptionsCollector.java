package kotlin.io.path;

import c.f$$ExternalSyntheticBUOutline1;
import j$.nio.file.Path;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\nJ\u0019\u0010\u000f\u001a\u00020\b2\n\u0010\u000e\u001a\u00060\fj\u0002`\r¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R$\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00028\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0013\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015R!\u0010\u0017\u001a\f\u0012\b\u0012\u00060\fj\u0002`\r0\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010\n¨\u0006 "}, d2 = {"Lkotlin/io/path/ExceptionsCollector;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "limit", "<init>", "(I)V", "j$/nio/file/Path", "name", _UrlKt.FRAGMENT_ENCODE_SET, "enterEntry", "(Lj$/nio/file/Path;)V", "exitEntry", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "collect", "(Ljava/lang/Exception;)V", "I", "value", "totalExceptions", "getTotalExceptions", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "collectedExceptions", "Ljava/util/List;", "getCollectedExceptions", "()Ljava/util/List;", "path", "Lj$/nio/file/Path;", "getPath", "()Lj$/nio/file/Path;", "setPath", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class ExceptionsCollector {
    private final List<Exception> collectedExceptions;
    private final int limit;
    private Path path;
    private int totalExceptions;

    public ExceptionsCollector() {
        this(0, 1, null);
    }

    public ExceptionsCollector(int r1) {
        this.limit = r1;
        this.collectedExceptions = new ArrayList();
    }

    public /* synthetic */ ExceptionsCollector(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 64;
    L5:
        this(r1);
    }

    public final void collect(Exception r3) {
        this.totalExceptions++;
        if (this.collectedExceptions.size() < this.limit) goto L5;
        return;
    L5:
        if (this.path == null) goto L7;
        r3 = (FileSystemException) new FileSystemException(String.valueOf(this.path)).initCause(r3);
    L7:
        this.collectedExceptions.add(r3);
    }

    public final void enterEntry(Path r2) {
        Path r0 = this.path;
        if (r0 == null) goto L5;
        Path r22 = r0.resolve(r2);
    L6:
        this.path = r22;
        return;
    L5:
        r22 = null;
        goto L6
    }

    public final void exitEntry(Path r3) {
        Path r0 = this.path;
        Path r1 = null;
        if (r0 == null) goto L5;
        Path r02 = r0.getFileName();
    L7:
        if (Intrinsics.areEqual(r3, r02) == false) goto L13;
        Path r32 = this.path;
        if (r32 == null) goto L11;
        r1 = r32.getParent();
    L11:
        this.path = r1;
        return;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return;
    L5:
        r02 = null;
        goto L7
    }

    public final List<Exception> getCollectedExceptions() {
        return this.collectedExceptions;
    }

    public final Path getPath() {
        return this.path;
    }

    public final int getTotalExceptions() {
        return this.totalExceptions;
    }

    public final void setPath(Path r1) {
        this.path = r1;
    }
}
