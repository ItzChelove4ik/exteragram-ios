package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.io.File;

/* loaded from: classes.dex */
public class LogFileManager {
    private static final NoopLogStore NOOP_LOG_STORE = null;
    private FileLogStore currentLog;
    private final FileStore fileStore;

    /* renamed from: com.google.firebase.crashlytics.internal.metadata.LogFileManager$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class NoopLogStore implements FileLogStore {
        private NoopLogStore() {
        }

        public /* synthetic */ NoopLogStore(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
        public void closeLogFile() {
        }

        @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
        public void deleteLogFile() {
        }

        @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
        public byte[] getLogAsBytes() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
        public String getLogAsString() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
        public void writeToLog(long r1, String r3) {
        }
    }

    static {
        NOOP_LOG_STORE = new NoopLogStore(null);
    }

    public LogFileManager(FileStore r1) {
        this.fileStore = r1;
        this.currentLog = NOOP_LOG_STORE;
    }

    public LogFileManager(FileStore r1, String r2) {
        this(r1);
        setCurrentSession(r2);
    }

    private File getWorkingFileForSession(String r2) {
        return this.fileStore.getSessionFile(r2, "userlog");
    }

    public void clearLog() {
        this.currentLog.deleteLogFile();
    }

    public byte[] getBytesForLog() {
        return this.currentLog.getLogAsBytes();
    }

    public String getLogString() {
        return this.currentLog.getLogAsString();
    }

    public final void setCurrentSession(String r2) {
        this.currentLog.closeLogFile();
        this.currentLog = NOOP_LOG_STORE;
        if (r2 != null) goto L5;
        return;
    L5:
        setLogFile(getWorkingFileForSession(r2), 65536);
    }

    public void setLogFile(File r2, int r3) {
        this.currentLog = new QueueFileLogStore(r2, r3);
    }

    public void writeToLog(long r1, String r3) {
        this.currentLog.writeToLog(r1, r3);
    }
}
