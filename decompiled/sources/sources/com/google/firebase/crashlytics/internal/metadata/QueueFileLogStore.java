package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.metadata.QueueFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;

/* loaded from: classes.dex */
class QueueFileLogStore implements FileLogStore {
    private static final Charset UTF_8 = null;
    private QueueFile logFile;
    private final int maxLogSize;
    private final File workingFile;

    /* renamed from: com.google.firebase.crashlytics.internal.metadata.QueueFileLogStore$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements QueueFile.ElementReader {
        final /* synthetic */ QueueFileLogStore this$0;
        final /* synthetic */ byte[] val$logBytes;
        final /* synthetic */ int[] val$offsetHolder;

        public AnonymousClass1(QueueFileLogStore r1, byte[] r2, int[] r3) {
            this.this$0 = r1;
            this.val$logBytes = r2;
            this.val$offsetHolder = r3;
        }

        @Override // com.google.firebase.crashlytics.internal.metadata.QueueFile.ElementReader
        public void read(InputStream r4, int r5) {
            r4.read(this.val$logBytes, this.val$offsetHolder[0], r5);     // Catch: Throwable -> L5
            int[] r3 = this.val$offsetHolder;     // Catch: Throwable -> L5
            r3[0] = r3[0] + r5;     // Catch: Throwable -> L5
            r4.close();
            return;
        L5:
            th = move-exception;
            r4.close();
            throw th;
        }
    }

    /* loaded from: classes5.dex */
    public static class LogBytes {
        public final byte[] bytes;
        public final int offset;

        public LogBytes(byte[] r1, int r2) {
            this.bytes = r1;
            this.offset = r2;
        }
    }

    static {
        UTF_8 = Charset.forName("UTF-8");
    }

    public QueueFileLogStore(File r1, int r2) {
        this.workingFile = r1;
        this.maxLogSize = r2;
    }

    private void doWriteToLog(long r5, String r7) {
        if (this.logFile == null) goto L17;
        if (r7 != null) goto L21;
        r7 = "null";
    L21:
        int r2 = this.maxLogSize / 4;     // Catch: IOException -> L18
        if (r7.length() <= r2) goto L10;
        r7 = "...".concat(r7.substring(r7.length() - r2));     // Catch: IOException -> L18
    L10:
        String r72 = r7.replaceAll("\r", " ").replaceAll("\n", " ");     // Catch: IOException -> L18
        byte[] r52 = String.format(Locale.US, "%d %s%n", new Object[]{Long.valueOf(r5), r72}).getBytes(UTF_8);     // Catch: IOException -> L18
        this.logFile.add(r52);     // Catch: IOException -> L18
    L11:
        if (this.logFile.isEmpty() == true) goto L25;
        if (this.logFile.usedBytes() <= this.maxLogSize) goto L26;
        this.logFile.remove();     // Catch: IOException -> L18
        goto L11
    L26:
        return;
    L25:
        return;
    L18:
        e = move-exception;
        Logger.getLogger().e("There was a problem writing to the Crashlytics log.", e);
        return;
    }

    private LogBytes getLogBytes() {
        if (this.workingFile.exists() == true) goto L5;
        return null;
    L5:
        openLogFile();
        QueueFile r0 = this.logFile;
        if (r0 != null) goto L8;
        return null;
    L8:
        int[] r2 = {0};
        byte[] r02 = new byte[r0.usedBytes()];
        this.logFile.forEach(new AnonymousClass1(this, r02, r2));     // Catch: IOException -> L11
    L14:
        return new LogBytes(r02, r2[0]);
    L11:
        e = move-exception;
        Logger.getLogger().e("A problem occurred while reading the Crashlytics log file.", e);
        goto L14
    }

    private void openLogFile() {
        if (this.logFile != null) goto L11;
        this.logFile = new QueueFile(this.workingFile);     // Catch: IOException -> L6
        return;
    L6:
        e = move-exception;
        Logger.getLogger().e("Could not open log file: " + this.workingFile, e);
        return;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void closeLogFile() {
        CommonUtils.closeOrLog(this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void deleteLogFile() {
        closeLogFile();
        this.workingFile.delete();
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public byte[] getLogAsBytes() {
        LogBytes r3 = getLogBytes();
        if (r3 != null) goto L6;
        return null;
    L6:
        int r0 = r3.offset;
        byte[] r1 = new byte[r0];
        System.arraycopy(r3.bytes, 0, r1, 0, r0);
        return r1;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public String getLogAsString() {
        byte[] r2 = getLogAsBytes();
        if (r2 != null) goto L5;
        return null;
    L5:
        return new String(r2, UTF_8);
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void writeToLog(long r1, String r3) {
        openLogFile();
        doWriteToLog(r1, r3);
    }
}
