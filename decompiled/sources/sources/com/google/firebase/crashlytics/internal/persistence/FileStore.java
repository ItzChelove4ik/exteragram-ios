package com.google.firebase.crashlytics.internal.persistence;

import android.content.Context;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.ProcessDetailsProvider;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class FileStore {
    private final File crashlyticsDir;
    private final File filesDir;
    private final File nativeReportsDir;
    private final File priorityReportsDir;
    final String processName;
    private final File reportsDir;
    private final File sessionsDir;

    public static /* synthetic */ boolean $r8$lambda$HmP8MQOXpRxpTLnqACFXr9djRFI(String r0, File r1, String r2) {
        return r2.startsWith(r0);
    }

    public FileStore(Context r4) {
        String r0 = ProcessDetailsProvider.INSTANCE.getCurrentProcessDetails(r4).getProcessName();
        this.processName = r0;
        File r42 = r4.getFilesDir();
        this.filesDir = r42;
        if (useV3FileSystem() == false) goto L5;
        String r02 = ".crashlytics.v3" + File.separator + sanitizeName(r0);
    L6:
        File r43 = prepareBaseDir(new File(r42, r02));
        this.crashlyticsDir = r43;
        this.sessionsDir = prepareBaseDir(new File(r43, "open-sessions"));
        this.reportsDir = prepareBaseDir(new File(r43, "reports"));
        this.priorityReportsDir = prepareBaseDir(new File(r43, "priority-reports"));
        this.nativeReportsDir = prepareBaseDir(new File(r43, "native-reports"));
        return;
    L5:
        r02 = ".com.google.firebase.crashlytics.files.v1";
        goto L6
    }

    private void cleanupFileSystemDir(String r3) {
        File r0 = new File(this.filesDir, r3);
        if (r0.exists() == true) goto L5;
        return;
    L5:
        if (recursiveDelete(r0) == false) goto L9;
        Logger.getLogger().d("Deleted previous Crashlytics file system: " + r0.getPath());
        return;
    }

    private void cleanupFileSystemDirs(String r4) {
        if (this.filesDir.exists() == false) goto L9;
        String[] r42 = this.filesDir.list(new FileStore$$ExternalSyntheticLambda0(r4));
        if (r42 == null) goto L11;
        int r0 = r42.length;
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L12;
        cleanupFileSystemDir(r42[r1]);
        r1 = r1 + 1;
        goto L7
    L12:
        return;
    L11:
        return;
    }

    private File getSessionDir(String r2) {
        return prepareDir(new File(this.sessionsDir, r2));
    }

    private static synchronized File prepareBaseDir(File r5) {
        monitor-enter(FileStore.class);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (r5.exists() == false) goto L14;
        if (r5.isDirectory() == false) goto L10;
        monitor-exit(FileStore.class);
        return r5;
    L10:
        Logger.getLogger().d("Unexpected non-directory file: " + r5 + "; deleting file and creating new directory.");     // Catch: Throwable -> L11
        r5.delete();     // Catch: Throwable -> L11
    L14:
        if (r5.mkdirs() == true) goto L16;
        Logger.getLogger().e("Could not create Crashlytics-specific directory: " + r5);     // Catch: Throwable -> L11
    L16:
        monitor-exit(FileStore.class);
        return r5;
    }

    private static File prepareDir(File r0) {
        r0.mkdirs();
        return r0;
    }

    public static boolean recursiveDelete(File r4) {
        File[] r0 = r4.listFiles();
        if (r0 == null) goto L8;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L8;
        recursiveDelete(r0[r2]);
        r2 = r2 + 1;
    L8:
        return r4.delete();
    }

    private static <T> List<T> safeArrayToList(T[] r0) {
        if (r0 != null) goto L6;
        return Collections.EMPTY_LIST;
    L6:
        return Arrays.asList(r0);
    }

    public static String sanitizeName(String r2) {
        if (r2.length() <= 40) goto L7;
        return CommonUtils.sha1(r2);
    L7:
        return r2.replaceAll("[^a-zA-Z0-9.]", "_");
    }

    private boolean useV3FileSystem() {
        return !this.processName.isEmpty();
    }

    public void cleanupPreviousFileSystems() {
        cleanupFileSystemDir(".com.google.firebase.crashlytics");
        cleanupFileSystemDir(".com.google.firebase.crashlytics-ndk");
        if (useV3FileSystem() == false) goto L6;
        cleanupFileSystemDir(".com.google.firebase.crashlytics.files.v1");
        cleanupFileSystemDirs(".com.google.firebase.crashlytics.files.v2" + File.pathSeparator);
        return;
    }

    public boolean deleteSessionFiles(String r2) {
        return recursiveDelete(new File(this.sessionsDir, r2));
    }

    public List<String> getAllOpenSessionIds() {
        return safeArrayToList(this.sessionsDir.list());
    }

    public File getCommonFile(String r2) {
        return new File(this.crashlyticsDir, r2);
    }

    public List<File> getCommonFiles(FilenameFilter r1) {
        return safeArrayToList(this.crashlyticsDir.listFiles(r1));
    }

    public File getNativeReport(String r2) {
        return new File(this.nativeReportsDir, r2);
    }

    public List<File> getNativeReports() {
        return safeArrayToList(this.nativeReportsDir.listFiles());
    }

    public File getNativeSessionDir(String r2) {
        return prepareDir(new File(getSessionDir(r2), "native"));
    }

    public File getPriorityReport(String r2) {
        return new File(this.priorityReportsDir, r2);
    }

    public List<File> getPriorityReports() {
        return safeArrayToList(this.priorityReportsDir.listFiles());
    }

    public File getReport(String r2) {
        return new File(this.reportsDir, r2);
    }

    public List<File> getReports() {
        return safeArrayToList(this.reportsDir.listFiles());
    }

    public File getSessionFile(String r2, String r3) {
        return new File(getSessionDir(r2), r3);
    }

    public List<File> getSessionFiles(String r1, FilenameFilter r2) {
        return safeArrayToList(getSessionDir(r1).listFiles(r2));
    }
}
