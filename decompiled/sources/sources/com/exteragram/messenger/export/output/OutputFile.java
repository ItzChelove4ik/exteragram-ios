package com.exteragram.messenger.export.output;

import android.util.Log;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.export.output.AbstractWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.NativeByteBuffer;

/* loaded from: classes4.dex */
public class OutputFile {
    public final File _file;
    private boolean _inStats;
    private long _offset;
    private final Stats _stats;

    public static class Stats {
        private final AtomicLong _bytes;
        private final AtomicInteger _files;

        public Stats() {
            this._files = new AtomicInteger(0);
            this._bytes = new AtomicLong(0);
        }

        public long bytesCount() {
            return this._bytes.get();
        }

        public int filesCount() {
            return this._files.get();
        }

        public void incrementBytes(int r3) {
            this._bytes.addAndGet(r3);
        }

        public void incrementFiles() {
            this._files.getAndIncrement();
        }
    }

    public static /* synthetic */ String $r8$lambda$ApE4zRWH4xloOxmAXEkNaQI5bA4(String r1, String r2, Integer r3) {
        return r1 + " (" + r3 + ")" + r2;
    }

    public OutputFile(String r3, Stats r4) {
        this._inStats = false;
        this._offset = 0;
        File r0 = new File(r3);
        this._file = r0;
    L9:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    L4:
        if (r0.getPath().contains("/") == false) goto L6;
        r0.getParentFile().mkdirs();     // Catch: IOException -> L9
    L6:
        r0.createNewFile();     // Catch: IOException -> L9
        this._stats = r4;
    }

    public static String PrepareRelativePath(String r4, String r5) {
        if (new File(r4 + "/" + r5).exists() == true) goto L5;
        return r5;
    L5:
        int r0 = r5.indexOf(46);
        int r1 = 0;
        String r2 = r5.substring(0, r0);
        if (r0 < 0) goto L8;
        String r52 = r5.substring(r0);
    L9:
        OutputFile$$ExternalSyntheticLambda0 r02 = new OutputFile$$ExternalSyntheticLambda0(r2, r52);
    L10:
        r1 = r1 + 1;
        String r53 = (String) r02.run(Integer.valueOf(r1));
        if (new File(r4 + r53).exists() == true) goto L10;
        return r53;
    L8:
        r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    public boolean empty() {
        if (this._offset != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public long size() {
        return this._offset;
    }

    public AbstractWriter.Result writeBlock(String r2) {
        AbstractWriter.Result r1 = writeBlockAttempt(r2);
        if (r1.isSuccess() == false) goto L5;
        return r1;
    L5:
        DexMaker$$ExternalSyntheticBUOutline0.m("result is not success for block: ", r2);
        return null;
    }

    public AbstractWriter.Result writeBlock(NativeByteBuffer r2) {
        AbstractWriter.Result r1 = writeBlockAttempt(r2);
        if (r1.isSuccess() == false) goto L5;
        return r1;
    L5:
        DexMaker$$ExternalSyntheticBUOutline0.m("result is not success for block: ", r2);
        return null;
    }

    public AbstractWriter.Result writeBlock(byte[] r2) {
        AbstractWriter.Result r1 = writeBlockAttempt(r2);
        if (r1.isSuccess() == false) goto L5;
        return r1;
    L5:
        DexMaker$$ExternalSyntheticBUOutline0.m("result is not success for block: ", r2);
        return null;
    }

    public AbstractWriter.Result writeBlockAttempt(String r9) {
        Stats r0 = this._stats;
        if (r0 != null) goto L5;
    L7:
        int r02 = r9.length();
        if (r02 != 0) goto L47;
        Log.e("exteraGram", "size of block to write was zero!");
        return AbstractWriter.Result.Success();
    L47:
        FileOutputStream r3 = new FileOutputStream(this._file, true);     // Catch: Throwable -> L26 Exception -> L28
        r3.write(r9.getBytes());     // Catch: Throwable -> L17 Exception -> L19
        this._offset += r02;
        Stats r8 = this._stats;     // Catch: Throwable -> L17 Exception -> L19
        if (r8 == null) goto L42;
        r8.incrementBytes(r02);     // Catch: Throwable -> L17 Exception -> L19
    L42:
        r3.close();     // Catch: IOException -> L23
        return AbstractWriter.Result.Success();
    L23:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L19:
        e = e;
    L30:
        FileLog.e(e);     // Catch: Throwable -> L17
        if (r3 == null) goto L38;
        r3.close();     // Catch: IOException -> L34
    L38:
        return AbstractWriter.Result.Error();
    L17:
        th = th;
    L39:
        if (r3 == null) goto L41;
        r3.close();     // Catch: IOException -> L34
    L41:
        throw th;
    L34:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L28:
        e = e;
        r3 = null;
    L26:
        th = th;
        r3 = null;
        goto L39
    L5:
        if (this._inStats == true) goto L7;
        this._inStats = true;
        r0.incrementFiles();
        goto L7
    }

    public AbstractWriter.Result writeBlockAttempt(NativeByteBuffer r9) {
        Stats r0 = this._stats;
        if (r0 != null) goto L5;
    L7:
        int r02 = r9.buffer.limit();
        if (r02 != 0) goto L41;
        Log.e("exteraGram", "size of block to write was zero!");
        return AbstractWriter.Result.Success();
    L41:
        RandomAccessFile r2 = new RandomAccessFile(this._file, "rws");     // Catch: IOException -> L17
        r2.seek(r2.length());     // Catch: IOException -> L15
    L40:
        FileChannel r3 = r2.getChannel();     // Catch: Throwable -> L23 Exception -> L25
        r3.write(r9.buffer);     // Catch: Throwable -> L23 Exception -> L25
        this._offset += r02;
        Stats r8 = this._stats;     // Catch: Throwable -> L23 Exception -> L25
        if (r8 == null) goto L27;
        r8.incrementBytes(r02);     // Catch: Throwable -> L23 Exception -> L25
    L27:
        r3.close();     // Catch: Throwable -> L23 Exception -> L25
        r2.close();     // Catch: IOException -> L31
        return AbstractWriter.Result.Success();
    L23:
        th = move-exception;
        r2.close();     // Catch: IOException -> L31
        throw th;
    L31:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L25:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L23
        r2.close();     // Catch: IOException -> L31
        return AbstractWriter.Result.Error();
    L15:
        e = e;
    L19:
        FileLog.e(e);
    L17:
        e = e;
        r2 = null;
        goto L19
    L5:
        if (this._inStats == true) goto L7;
        this._inStats = true;
        r0.incrementFiles();
        goto L7
    }

    public AbstractWriter.Result writeBlockAttempt(byte[] r9) {
        Stats r0 = this._stats;
        if (r0 != null) goto L5;
    L7:
        int r02 = r9.length;
        if (r02 != 0) goto L11;
        Log.e("exteraGram", "size of block to write was zero!");
        return AbstractWriter.Result.Success();
    L11:
        FileOutputStream r2 = null;
        FileOutputStream r3 = new FileOutputStream(this._file, true);     // Catch: Throwable -> L26 Exception -> L28
        r3.write(r9);     // Catch: Throwable -> L17 Exception -> L19
        this._offset += r02;
        Stats r8 = this._stats;     // Catch: Throwable -> L17 Exception -> L19
        if (r8 == null) goto L46;
        r8.incrementBytes(r02);     // Catch: Throwable -> L17 Exception -> L19
    L46:
        r3.close();     // Catch: IOException -> L23
        return AbstractWriter.Result.Success();
    L23:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L19:
        e = e;
    L30:
        FileLog.e(e);     // Catch: Throwable -> L17
        if (r3 == null) goto L38;
        r3.close();     // Catch: IOException -> L34
    L38:
        return AbstractWriter.Result.Error();
    L34:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L28:
        e = e;
        r3 = null;
    L26:
        th = th;
    L39:
        if (0 == 0) goto L41;
        r2.close();     // Catch: IOException -> L34
    L41:
        throw th;
    L17:
        th = th;
        goto L39
    L5:
        if (this._inStats == true) goto L7;
        this._inStats = true;
        r0.incrementFiles();
        goto L7
    }
}
