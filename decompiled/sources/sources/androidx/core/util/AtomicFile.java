package androidx.core.util;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class AtomicFile {
    private final File mBaseName;
    private final File mLegacyBackupName;
    private final File mNewName;

    public AtomicFile(File r4) {
        this.mBaseName = r4;
        this.mNewName = new File(r4.getPath() + ".new");
        this.mLegacyBackupName = new File(r4.getPath() + ".bak");
    }

    private static void rename(File r3, File r4) {
        if (r4.isDirectory() == false) goto L8;
        if (r4.delete() == true) goto L8;
        Log.e("AtomicFile", "Failed to delete file which is a directory " + r4);
    L8:
        if (r3.renameTo(r4) == true) goto L11;
        Log.e("AtomicFile", "Failed to rename " + r3 + " to " + r4);
        return;
    }

    private static boolean sync(FileOutputStream r0) {
        r0.getFD().sync();     // Catch: IOException -> L5
        return true;
    L5:
        return false;
    }

    public void failWrite(FileOutputStream r3) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (sync(r3) == true) goto L15;
        Log.e("AtomicFile", "Failed to sync file output stream");
    L15:
        r3.close();     // Catch: IOException -> L9
    L12:
        if (this.mNewName.delete() == true) goto L17;
        Log.e("AtomicFile", "Failed to delete new file " + this.mNewName);
        return;
    L17:
        return;
    L9:
        e = move-exception;
        Log.e("AtomicFile", "Failed to close file output stream", e);
        goto L12
    }

    public void finishWrite(FileOutputStream r3) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (sync(r3) == true) goto L13;
        Log.e("AtomicFile", "Failed to sync file output stream");
    L13:
        r3.close();     // Catch: IOException -> L9
    L11:
        rename(this.mNewName, this.mBaseName);
        return;
    L9:
        e = move-exception;
        Log.e("AtomicFile", "Failed to close file output stream", e);
        goto L11
    }

    public File getBaseFile() {
        return this.mBaseName;
    }

    public FileOutputStream startWrite() {
        if (this.mLegacyBackupName.exists() == false) goto L16;
        rename(this.mLegacyBackupName, this.mBaseName);
    L16:
        return new FileOutputStream(this.mNewName);
    L8:
        if (this.mNewName.getParentFile().mkdirs() == true) goto L18;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Failed to create directory for ", this.mNewName);
        return null;
    L18:
        return new FileOutputStream(this.mNewName);
    L11:
        e = move-exception;
        throw new IOException("Failed to create new file " + this.mNewName, e);
    }
}
