package com.google.firebase.installations;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/* loaded from: classes5.dex */
class CrossProcessLock {
    private final FileChannel channel;
    private final FileLock lock;

    private CrossProcessLock(FileChannel r1, FileLock r2) {
        this.channel = r1;
        this.lock = r2;
    }

    public static CrossProcessLock acquire(Context r4, String r5) {
        FileChannel r42 = new RandomAccessFile(new File(r4.getFilesDir(), r5), "rw").getChannel();     // Catch: OverlappingFileLockException -> L19 Error -> L21 Throwable -> L23
        FileLock r52 = r42.lock();     // Catch: OverlappingFileLockException -> L13 Throwable -> L15 IOException -> L17
        return new CrossProcessLock(r42, r52);
    L11:
        e = e;
    L25:
        Log.e("CrossProcessLock", "encountered error while creating and acquiring the lock, ignoring", e);
        if (r52 != null) goto L35;
    L28:
        if (r42 != null) goto L33;
    L30:
        return null;
    L33:
        r42.close();     // Catch: IOException -> L32
        goto L30
    L35:
        r52.release();     // Catch: IOException -> L31
    L9:
        e = e;
    L7:
        e = e;
    L15:
        e = e;
        r52 = null;
    L23:
        e = e;
        r42 = null;
        r52 = null;
        goto L25
    }

    public void releaseAndClose() {
        this.lock.release();     // Catch: IOException -> L4
        this.channel.close();     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        Log.e("CrossProcessLock", "encountered error while releasing, ignoring", e);
    }
}
