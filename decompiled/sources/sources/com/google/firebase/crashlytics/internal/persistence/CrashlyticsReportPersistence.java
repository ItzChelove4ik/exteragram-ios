package com.google.firebase.crashlytics.internal.persistence;

import android.os.Build;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsAppQualitySessionsSubscriber;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class CrashlyticsReportPersistence {
    private static final FilenameFilter EVENT_FILE_FILTER = null;
    private static final int EVENT_NAME_LENGTH = 0;
    private static final Comparator<? super File> LATEST_SESSION_ID_FIRST_COMPARATOR = null;
    private static final CrashlyticsReportJsonTransform TRANSFORM = null;
    private static final Charset UTF_8 = null;
    private final AtomicInteger eventCounter;
    private final FileStore fileStore;
    private final CrashlyticsAppQualitySessionsSubscriber sessionsSubscriber;
    private final SettingsProvider settingsProvider;

    /* renamed from: $r8$lambda$0vuRQ5n19dBD9KH7-Ol5vfH0ZZ0, reason: not valid java name */
    public static /* synthetic */ Optional m1946$r8$lambda$0vuRQ5n19dBD9KH7Ol5vfH0ZZ0(File r2) {
        return Optional.of(TRANSFORM.profilingManagerInfoFromJson(readTextFile(r2)));
    L4:
        e = move-exception;
        Logger.getLogger().w("Unable to read the Profiling Manager file ", e);
        return Optional.empty();
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event $r8$lambda$8iUI8wc_FwuExXlMT9At9xdFNyY(CrashlyticsReport.Session.Event r2, CrashlyticsReport.ProfilingManagerInfo r3) {
        return r2.toBuilder().setApp(r2.getApp().toBuilder().setExecution(r2.getApp().getExecution().toBuilder().setProfilingManagerInfo(r3).build()).build()).build();
    }

    public static /* synthetic */ boolean $r8$lambda$8lAF3QdCpuPjTnrZq1F4l5AJNTw(File r0, String r1) {
        return r1.startsWith("event");
    }

    /* renamed from: $r8$lambda$Ef09-Fis7I_z-AXvERJDwHbq9g8, reason: not valid java name */
    public static /* synthetic */ boolean m1947$r8$lambda$Ef09Fis7I_zAXvERJDwHbq9g8(File r0, String r1) {
        return isNormalPriorityEventFile(r0, r1);
    }

    /* renamed from: $r8$lambda$KEiHBBF_z04XQMHnQYJTj-31PpI, reason: not valid java name */
    public static /* synthetic */ int m1948$r8$lambda$KEiHBBF_z04XQMHnQYJTj31PpI(File r0, File r1) {
        return oldestEventFileFirst(r0, r1);
    }

    public static /* synthetic */ int $r8$lambda$NSPVlEyn36LQVAfqfD_0lrx3NUE(File r0, File r1) {
        return r1.getName().compareTo(r0.getName());
    }

    static {
        UTF_8 = Charset.forName("UTF-8");
        EVENT_NAME_LENGTH = 15;
        TRANSFORM = new CrashlyticsReportJsonTransform();
        LATEST_SESSION_ID_FIRST_COMPARATOR = new CrashlyticsReportPersistence$$ExternalSyntheticLambda0();
        EVENT_FILE_FILTER = new CrashlyticsReportPersistence$$ExternalSyntheticLambda1();
    }

    public CrashlyticsReportPersistence(FileStore r3, SettingsProvider r4, CrashlyticsAppQualitySessionsSubscriber r5) {
        this.eventCounter = new AtomicInteger(0);
        this.fileStore = r3;
        this.settingsProvider = r4;
        this.sessionsSubscriber = r5;
    }

    private SortedSet<String> capAndGetOpenSessions(String r6) {
        this.fileStore.cleanupPreviousFileSystems();
        SortedSet<String> r0 = getOpenSessionIds();
        if (r6 == null) goto L6;
        r0.remove(r6);
    L6:
        if (r0.size() > 8) goto L9;
    L11:
        return r0;
    L9:
        if (r0.size() <= 8) goto L11;
        String r62 = r0.last();
        Logger.getLogger().d("Removing session over cap: " + r62);
        this.fileStore.deleteSessionFiles(r62);
        r0.remove(r62);
        goto L9
    }

    private static int capFilesCount(List<File> r2, int r3) {
        int r0 = r2.size();
        Iterator<File> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L9;
        File r1 = r22.next();
        if (r0 <= r3) goto L9;
        FileStore.recursiveDelete(r1);
        r0 = r0 - 1;
    L9:
        return r0;
    }

    private void capFinalizedReports() {
        int r0 = this.settingsProvider.getSettingsSync().sessionData.maxCompleteSessionsCount;
        List<File> r2 = getAllFinalizedReportFiles();
        int r1 = r2.size();
        if (r1 <= r0) goto L9;
        Iterator<File> r22 = r2.subList(r0, r1).iterator();
    L7:
        if (r22.hasNext() == false) goto L11;
        r22.next().delete();
        goto L7
    L11:
        return;
    }

    private static long convertTimestampFromSecondsToMs(long r2) {
        return r2 * 1000;
    }

    private CrashlyticsReport.Session.Event decorateWithProfilingManagerInfoIfFatal(String r3, CrashlyticsReport.Session.Event r4) {
        if (Build.VERSION.SDK_INT >= 37) goto L5;
    L8:
        return r4;
    L5:
        if (isFatalEvent(r4) == false) goto L8;
        return (CrashlyticsReport.Session.Event) Optional.of(this.fileStore.getSessionFile(r3, "profiling-manager-info")).filter(new CrashlyticsReportPersistence$$ExternalSyntheticLambda2()).flatMap(new CrashlyticsReportPersistence$$ExternalSyntheticLambda3()).map(new CrashlyticsReportPersistence$$ExternalSyntheticLambda4(r4)).orElse(r4);
    }

    private void deleteFiles(List<File> r1) {
        Iterator<File> r0 = r1.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().delete();
        goto L4
    }

    private static String generateEventFilename(int r2, boolean r3) {
        String r22 = String.format(Locale.US, "%010d", new Object[]{Integer.valueOf(r2)});
        if (r3 == false) goto L5;
        String r32 = "_";
    L7:
        return "event" + r22 + r32;
    L5:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }

    private List<File> getAllFinalizedReportFiles() {
        ArrayList r0 = new ArrayList();
        r0.addAll(this.fileStore.getPriorityReports());
        r0.addAll(this.fileStore.getNativeReports());
        Comparator<? super File> r1 = LATEST_SESSION_ID_FIRST_COMPARATOR;
        Collections.sort(r0, r1);
        List<File> r2 = this.fileStore.getReports();
        Collections.sort(r2, r1);
        r0.addAll(r2);
        return r0;
    }

    private static String getEventNameWithoutPriority(String r2) {
        return r2.substring(0, EVENT_NAME_LENGTH);
    }

    private static boolean isFatalEvent(CrashlyticsReport.Session.Event r2) {
        if (r2.getType().equals("crash") == false) goto L5;
        return true;
    L5:
        if (r2.getType().equals("ndk-crash") == true) goto L11;
        return false;
    L11:
        return true;
    }

    private static boolean isHighPriorityEventFile(String r1) {
        if (r1.startsWith("event") == true) goto L5;
        return false;
    L5:
        if (r1.endsWith("_") == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isNormalPriorityEventFile(File r0, String r1) {
        if (r1.startsWith("event") == true) goto L5;
        return false;
    L5:
        if (r1.endsWith("_") == true) goto L10;
        return true;
    L10:
        return false;
    }

    private static int oldestEventFileFirst(File r0, File r1) {
        return getEventNameWithoutPriority(r0.getName()).compareTo(getEventNameWithoutPriority(r1.getName()));
    }

    private static String readTextFile(File r4) {
        byte[] r0 = new byte[8192];
        ByteArrayOutputStream r1 = new ByteArrayOutputStream();
        FileInputStream r2 = new FileInputStream(r4);
    L16:
        int r42 = r2.read(r0);     // Catch: Throwable -> L6
        if (r42 <= 0) goto L8;
        r1.write(r0, 0, r42);     // Catch: Throwable -> L6
        goto L16
    L8:
        String r43 = new String(r1.toByteArray(), UTF_8);     // Catch: Throwable -> L6
        r2.close();
        return r43;
    L6:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L13
    L15:
        throw th;
    L13:
        th = move-exception;
        th.addSuppressed(th);
        goto L15
    }

    private void synthesizeNativeReportFile(File r4, CrashlyticsReport.FilesPayload r5, String r6, CrashlyticsReport.ApplicationExitInfo r7) {
        String r0 = this.sessionsSubscriber.getAppQualitySessionId(r6);
        CrashlyticsReportJsonTransform r1 = TRANSFORM;     // Catch: IOException -> L5
        writeTextFile(this.fileStore.getNativeReport(r6), r1.reportToJson(r1.reportFromJson(readTextFile(r4)).withNdkPayload(r5).withApplicationExitInfo(r7).withAppQualitySessionId(r0)));     // Catch: IOException -> L5
        return;
    L5:
        e = move-exception;
        Logger.getLogger().w("Could not synthesize final native report file for " + r4, e);
    }

    private void synthesizeReport(String r10, long r11) {
        List<File> r0 = this.fileStore.getSessionFiles(r10, EVENT_FILE_FILTER);
        if (r0.isEmpty() == false) goto L6;
        Logger.getLogger().v("Session " + r10 + " has no events.");
        return;
    L6:
        Collections.sort(r0);
        ArrayList r3 = new ArrayList();
        Iterator<File> r1 = r0.iterator();
    L7:
        boolean r6 = false;
    L9:
        if (r1.hasNext() == false) goto L21;
        File r4 = r1.next();
        r3.add(decorateWithProfilingManagerInfoIfFatal(r10, TRANSFORM.eventFromJson(readTextFile(r4))));     // Catch: IOException -> L16
        if (r6 == false) goto L14;
    L18:
        r6 = true;
        goto L9
    L14:
        if (isHighPriorityEventFile(r4.getName()) == false) goto L7;
    L16:
        e = move-exception;
        Logger.getLogger().w("Could not add event to report for " + r4, e);
        goto L9
    L21:
        if (r3.isEmpty() == false) goto L24;
        Logger.getLogger().w("Could not parse event files for session " + r10);
        return;
    L24:
        synthesizeReportFile(this.fileStore.getSessionFile(r10, "report"), r3, r11, r6, UserMetadata.readUserId(r10, this.fileStore), this.sessionsSubscriber.getAppQualitySessionId(r10));
    }

    private void synthesizeReportFile(File r4, List<CrashlyticsReport.Session.Event> r5, long r6, boolean r8, String r9, String r10) {
        CrashlyticsReportJsonTransform r1 = TRANSFORM;     // Catch: IOException -> L10
        CrashlyticsReport r52 = r1.reportFromJson(readTextFile(r4)).withSessionEndFields(r6, r8, r9).withAppQualitySessionId(r10).withEvents(r5);     // Catch: IOException -> L10
        CrashlyticsReport.Session r62 = r52.getSession();     // Catch: IOException -> L10
        if (r62 != null) goto L6;
        return;
    L6:
        Logger.getLogger().d("appQualitySessionId: " + r10);     // Catch: IOException -> L10
        FileStore r3 = this.fileStore;
        if (r8 == false) goto L12;
        File r32 = r3.getPriorityReport(r62.getIdentifier());     // Catch: IOException -> L10
    L13:
        writeTextFile(r32, r1.reportToJson(r52));     // Catch: IOException -> L10
        return;
    L12:
        r32 = r3.getReport(r62.getIdentifier());     // Catch: IOException -> L10
    L10:
        e = move-exception;
        Logger.getLogger().w("Could not synthesize final report file for " + r4, e);
    }

    private int trimEvents(String r2, int r3) {
        List<File> r1 = this.fileStore.getSessionFiles(r2, new CrashlyticsReportPersistence$$ExternalSyntheticLambda5());
        Collections.sort(r1, new CrashlyticsReportPersistence$$ExternalSyntheticLambda6());
        return capFilesCount(r1, r3);
    }

    private static void writeTextFile(File r2, String r3) {
        OutputStreamWriter r0 = new OutputStreamWriter(new FileOutputStream(r2), UTF_8);
        r0.write(r3);     // Catch: Throwable -> L6
        r0.close();
        return;
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        th.addSuppressed(th);
        goto L11
    }

    private static void writeTextFile(File r3, String r4, long r5) {
        OutputStreamWriter r0 = new OutputStreamWriter(new FileOutputStream(r3), UTF_8);
        r0.write(r4);     // Catch: Throwable -> L6
        r3.setLastModified(convertTimestampFromSecondsToMs(r5));     // Catch: Throwable -> L6
        r0.close();
        return;
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        th.addSuppressed(th);
        goto L11
    }

    public void deleteAllReports() {
        deleteFiles(this.fileStore.getReports());
        deleteFiles(this.fileStore.getPriorityReports());
        deleteFiles(this.fileStore.getNativeReports());
    }

    public void finalizeReports(String r5, long r6) {
        Iterator<String> r52 = capAndGetOpenSessions(r5).iterator();
    L4:
        if (r52.hasNext() == false) goto L6;
        String r0 = r52.next();
        Logger.getLogger().v("Finalizing report for session " + r0);
        synthesizeReport(r0, r6);
        this.fileStore.deleteSessionFiles(r0);
        goto L4
    L6:
        capFinalizedReports();
    }

    public void finalizeSessionWithNativeEvent(String r5, CrashlyticsReport.FilesPayload r6, CrashlyticsReport.ApplicationExitInfo r7) {
        File r0 = this.fileStore.getSessionFile(r5, "report");
        Logger.getLogger().d("Writing native session report for " + r5 + " to file: " + r0);
        synthesizeNativeReportFile(r0, r6, r5, r7);
    }

    public SortedSet<String> getOpenSessionIds() {
        return new TreeSet(this.fileStore.getAllOpenSessionIds()).descendingSet();
    }

    public long getStartTimestampMillis(String r2) {
        return this.fileStore.getSessionFile(r2, "start-time").lastModified();
    }

    public boolean hasFinalizedReports() {
        if (this.fileStore.getReports().isEmpty() == true) goto L5;
        return true;
    L5:
        if (this.fileStore.getPriorityReports().isEmpty() == true) goto L7;
        return true;
    L7:
        if (this.fileStore.getNativeReports().isEmpty() == false) goto L14;
        return false;
    L14:
        return true;
    }

    public List<CrashlyticsReportWithSessionId> loadFinalizedReports() {
        List<File> r6 = getAllFinalizedReportFiles();
        ArrayList r0 = new ArrayList();
        Iterator<File> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L10;
        File r1 = r62.next();
        r0.add(CrashlyticsReportWithSessionId.create(TRANSFORM.reportFromJson(readTextFile(r1)), r1.getName(), r1));     // Catch: IOException -> L8
    L8:
        e = move-exception;
        Logger.getLogger().w("Could not load report file " + r1 + "; deleting", e);
        r1.delete();
        goto L4
    L10:
        return r0;
    }

    public void persistEvent(CrashlyticsReport.Session.Event r4, String r5, boolean r6) {
        int r0 = this.settingsProvider.getSettingsSync().sessionData.maxCustomExceptionEvents;
        writeTextFile(this.fileStore.getSessionFile(r5, generateEventFilename(this.eventCounter.getAndIncrement(), r6)), TRANSFORM.eventToJson(r4));     // Catch: IOException -> L5
    L7:
        trimEvents(r5, r0);
        return;
    L5:
        e = move-exception;
        Logger.getLogger().w("Could not persist event for session " + r5, e);
        goto L7
    }

    public void persistProfilingManagerInfo(CrashlyticsReport.ProfilingManagerInfo r3, String r4) {
        writeTextFile(this.fileStore.getSessionFile(r4, "profiling-manager-info"), TRANSFORM.profilingManagerInfoToJson(r3));     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        Logger.getLogger().w("Could not persist Profiling Manager info " + r4, e);
    }

    public void persistReport(CrashlyticsReport r5) {
        CrashlyticsReport.Session r0 = r5.getSession();
        if (r0 != null) goto L6;
        Logger.getLogger().d("Could not get session for report");
        return;
    L6:
        String r1 = r0.getIdentifier();
        writeTextFile(this.fileStore.getSessionFile(r1, "report"), TRANSFORM.reportToJson(r5));     // Catch: IOException -> L9
        writeTextFile(this.fileStore.getSessionFile(r1, "start-time"), _UrlKt.FRAGMENT_ENCODE_SET, r0.getStartedAt());     // Catch: IOException -> L9
        return;
    L9:
        e = move-exception;
        Logger.getLogger().d("Could not persist report for session " + r1, e);
    }
}
