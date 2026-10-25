package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
class CrashlyticsAppQualitySessionsStore {
    private static final FilenameFilter AQS_SESSION_ID_FILE_FILTER = null;
    private static final Comparator<File> FILE_RECENCY_COMPARATOR = null;
    private String appQualitySessionId;
    private final FileStore fileStore;
    private String sessionId;

    public static /* synthetic */ boolean $r8$lambda$HC5Mvw2MulN443jfZvOCMhQyBGk(File r0, String r1) {
        return r1.startsWith("aqs.");
    }

    /* renamed from: $r8$lambda$xv-WaFZfflOHyBgiuHW8_GpUrcE, reason: not valid java name */
    public static /* synthetic */ int m1932$r8$lambda$xvWaFZfflOHyBgiuHW8_GpUrcE(File r2, File r3) {
        return Long.compare(r3.lastModified(), r2.lastModified());
    }

    static {
        AQS_SESSION_ID_FILE_FILTER = new CrashlyticsAppQualitySessionsStore$$ExternalSyntheticLambda0();
        FILE_RECENCY_COMPARATOR = new CrashlyticsAppQualitySessionsStore$$ExternalSyntheticLambda1();
    }

    public CrashlyticsAppQualitySessionsStore(FileStore r2) {
        this.sessionId = null;
        this.appQualitySessionId = null;
        this.fileStore = r2;
    }

    private static void persist(FileStore r1, String r2, String r3) {
        if (r2 == null) goto L12;
        if (r3 == null) goto L13;
        r1.getSessionFile(r2, "aqs.".concat(r3)).createNewFile();     // Catch: IOException -> L7
        return;
    L7:
        e = move-exception;
        Logger.getLogger().w("Failed to persist App Quality Sessions session id.", e);
        return;
    L13:
        return;
    }

    public static String readAqsSessionIdFile(FileStore r1, String r2) {
        List<File> r12 = r1.getSessionFiles(r2, AQS_SESSION_ID_FILE_FILTER);
        if (r12.isEmpty() == false) goto L7;
        Logger.getLogger().w("Unable to read App Quality Sessions session id.");
        return null;
    L7:
        return ((File) Collections.min(r12, FILE_RECENCY_COMPARATOR)).getName().substring(4);
    }

    public synchronized String getAppQualitySessionId(String r2) {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (Objects.equals(this.sessionId, r2) == false) goto L10;
        String r22 = this.appQualitySessionId;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r22;
    L10:
        String r23 = readAqsSessionIdFile(this.fileStore, r2);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r23;
    }

    public synchronized void rotateAppQualitySessionId(String r3) {
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L4:
        if (Objects.equals(this.appQualitySessionId, r3) == true) goto L9;
        persist(this.fileStore, this.sessionId, r3);     // Catch: Throwable -> L7
        this.appQualitySessionId = r3;     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
    }

    public synchronized void rotateSessionId(String r3) {
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L4:
        if (Objects.equals(this.sessionId, r3) == true) goto L9;
        persist(this.fileStore, r3, this.appQualitySessionId);     // Catch: Throwable -> L7
        this.sessionId = r3;     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
    }
}
