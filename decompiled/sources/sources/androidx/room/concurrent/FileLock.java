package androidx.room.concurrent;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nR\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/room/concurrent/FileLock;", _UrlKt.FRAGMENT_ENCODE_SET, "filename", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;)V", "lockFilename", "lockChannel", "Ljava/nio/channels/FileChannel;", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "unlock", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class FileLock {
    private FileChannel lockChannel;
    private final String lockFilename;

    public FileLock(String r2) {
        this.lockFilename = r2 + ".lck";
    }

    public final void lock() {
        if (this.lockChannel != null) goto L22;
        File r0 = new File(this.lockFilename);     // Catch: Throwable -> L8
        File r1 = r0.getParentFile();     // Catch: Throwable -> L8
        if (r1 == null) goto L10;
        r1.mkdirs();     // Catch: Throwable -> L8
    L10:
        FileChannel r02 = new FileOutputStream(r0).getChannel();     // Catch: Throwable -> L8
        this.lockChannel = r02;     // Catch: Throwable -> L8
        if (r02 == null) goto L21;
        r02.lock();     // Catch: Throwable -> L8
        return;
    L21:
        return;
    L8:
        th = move-exception;
        FileChannel r12 = this.lockChannel;
        if (r12 == null) goto L17;
        r12.close();
    L17:
        this.lockChannel = null;
        throw new IllegalStateException("Unable to lock file: '" + this.lockFilename + "'.", th);
    }

    public final void unlock() {
        FileChannel r0 = this.lockChannel;
        if (r0 != null) goto L12;
        return;
    L12:
        r0.close();     // Catch: Throwable -> L9
        this.lockChannel = null;
        return;
    L9:
        th = move-exception;
        this.lockChannel = null;
        throw th;
    }
}
