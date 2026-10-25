package com.google.firebase.crashlytics.internal.common;

import android.app.ApplicationExitInfo;
import android.content.Context;
import android.system.OsConstants;
import androidx.work.impl.utils.ForceStopRunnable$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.metadata.EventMetadata;
import com.google.firebase.crashlytics.internal.metadata.LogFileManager;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.send.DataTransportCrashlyticsReportSender;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.concurrent.Executor;
import java.util.function.Predicate;

/* loaded from: classes.dex */
public class SessionReportingCoordinator {
    private final CrashlyticsWorkers crashlyticsWorkers;
    private final CrashlyticsReportDataCapture dataCapture;
    private final IdManager idManager;
    private final LogFileManager logFileManager;
    private final UserMetadata reportMetadata;
    private final CrashlyticsReportPersistence reportPersistence;
    private final DataTransportCrashlyticsReportSender reportsSender;

    public static /* synthetic */ void $r8$lambda$GDz_lcqQNqo8aFPcI70zJauOLvQ(SessionReportingCoordinator r2, CrashlyticsReport.Session.Event r3, EventMetadata r4, boolean r5) {
        r2.getClass();
        Logger.getLogger().d("disk worker: log non-fatal event to persistence");
        r2.reportPersistence.persistEvent(r3, r4.getSessionId(), r5);
    }

    public static /* synthetic */ boolean $r8$lambda$QF1kRPr0KNFr9oZS58J5qfeKzRk(SessionReportingCoordinator r0, Task r1) {
        return r0.onReportSendComplete(r1);
    }

    /* renamed from: $r8$lambda$R1-tCvrdoV7ST1LcRxcdKjC8LlM, reason: not valid java name */
    public static /* synthetic */ boolean m1939$r8$lambda$R1tCvrdoV7ST1LcRxcdKjC8LlM(ApplicationExitInfo r5) {
        if (r5.getReason() == 3) goto L5;
    L9:
        boolean r0 = false;
    L11:
        if (r5.getReason() == 2) goto L13;
    L15:
        boolean r52 = false;
    L16:
        if (r0 == true) goto L20;
        if (r52 == true) goto L20;
        return false;
    L20:
        return true;
    L13:
        if (r5.getStatus() != OsConstants.SIGKILL) goto L15;
        r52 = true;
        goto L16
    L5:
        if (r5.getDescription() == null) goto L9;
        if (r5.getDescription().contains("OOM") == false) goto L9;
        r0 = true;
        goto L11
    }

    public static /* synthetic */ Optional $r8$lambda$Rfl_5_2r1xWUeUwKRDWON5F7hQs(SessionReportingCoordinator r0, String r1, List r2) {
        if (r0.isOom(r1, r2) == false) goto L7;
        return Optional.of(7);
    L7:
        return Optional.empty();
    }

    /* renamed from: $r8$lambda$k5-vUpO7S3MnCt5_GNIszOb4PPo, reason: not valid java name */
    public static /* synthetic */ int m1940$r8$lambda$k5vUpO7S3MnCt5_GNIszOb4PPo(CrashlyticsReport.CustomAttribute r0, CrashlyticsReport.CustomAttribute r1) {
        return r0.getKey().compareTo(r1.getKey());
    }

    public static /* synthetic */ void $r8$lambda$tLKEED_fZ5q8qr0jb2TYwmyQRmM(SessionReportingCoordinator r2, String r3, Integer r4) {
        r2.reportPersistence.persistProfilingManagerInfo(CrashlyticsReport.ProfilingManagerInfo.builder().setProfilingTrigger(CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger.builder().setTrigger(r4.intValue()).build()).build(), r3);
    }

    public static /* synthetic */ boolean $r8$lambda$y8gAiV5CanpLtY1gVVIhhCoI4mA(ApplicationExitInfo r1) {
        if (r1.getReason() != 6) goto L6;
        return true;
    L6:
        return false;
    }

    public SessionReportingCoordinator(CrashlyticsReportDataCapture r1, CrashlyticsReportPersistence r2, DataTransportCrashlyticsReportSender r3, LogFileManager r4, UserMetadata r5, IdManager r6, CrashlyticsWorkers r7) {
        this.dataCapture = r1;
        this.reportPersistence = r2;
        this.reportsSender = r3;
        this.logFileManager = r4;
        this.reportMetadata = r5;
        this.idManager = r6;
        this.crashlyticsWorkers = r7;
    }

    private CrashlyticsReport.Session.Event addLogsAndCustomKeysToEvent(CrashlyticsReport.Session.Event r2, LogFileManager r3, UserMetadata r4) {
        return addLogsCustomKeysAndEventKeysToEvent(r2, r3, r4, Collections.EMPTY_MAP);
    }

    private CrashlyticsReport.Session.Event addLogsCustomKeysAndEventKeysToEvent(CrashlyticsReport.Session.Event r2, LogFileManager r3, UserMetadata r4, Map<String, String> r5) {
        CrashlyticsReport.Session.Event.Builder r1 = r2.toBuilder();
        String r32 = r3.getLogString();
        if (r32 == null) goto L5;
        r1.setLog(CrashlyticsReport.Session.Event.Log.builder().setContent(r32).build());
    L6:
        List<CrashlyticsReport.CustomAttribute> r33 = getSortedCustomAttributes(r4.getCustomKeys(r5));
        List<CrashlyticsReport.CustomAttribute> r42 = getSortedCustomAttributes(r4.getInternalKeys());
        if (r33.isEmpty() == true) goto L9;
    L10:
        r1.setApp(r2.getApp().toBuilder().setCustomAttributes(r33).setInternalKeys(r42).build());
    L12:
        return r1.build();
    L9:
        if (r42.isEmpty() == true) goto L12;
    L5:
        Logger.getLogger().v("No log data to include with this event.");
        goto L6
    }

    private CrashlyticsReport.Session.Event addMetaDataToEvent(CrashlyticsReport.Session.Event r3, Map<String, String> r4) {
        return addRolloutsStateToEvent(addLogsCustomKeysAndEventKeysToEvent(r3, this.logFileManager, this.reportMetadata, r4), this.reportMetadata);
    }

    private CrashlyticsReport.Session.Event addRolloutsStateToEvent(CrashlyticsReport.Session.Event r1, UserMetadata r2) {
        List<CrashlyticsReport.Session.Event.RolloutAssignment> r0 = r2.getRolloutsState();
        if (r0.isEmpty() == false) goto L5;
        return r1;
    L5:
        CrashlyticsReport.Session.Event.Builder r12 = r1.toBuilder();
        r12.setRollouts(CrashlyticsReport.Session.Event.RolloutsState.builder().setRolloutAssignments(r0).build());
        return r12.build();
    }

    private static CrashlyticsReport.ApplicationExitInfo convertApplicationExitInfo(ApplicationExitInfo r5) {
        String r0 = null;
        InputStream r1 = r5.getTraceInputStream();     // Catch: IOException -> L7
        if (r1 == null) goto L10;
        r0 = convertInputStreamToString(r1);     // Catch: IOException -> L7
    L10:
        return CrashlyticsReport.ApplicationExitInfo.builder().setImportance(r5.getImportance()).setProcessName(r5.getProcessName()).setReasonCode(r5.getReason()).setTimestamp(r5.getTimestamp()).setPid(r5.getPid()).setPss(r5.getPss()).setRss(r5.getRss()).setTraceFile(r0).build();
    L7:
        e = move-exception;
        Logger.getLogger().w("Could not get input trace in application exit info: " + r5.toString() + " Error: " + e);
        goto L10
    }

    public static String convertInputStreamToString(InputStream r4) {
        BufferedInputStream r0 = new BufferedInputStream(r4);
        ByteArrayOutputStream r42 = new ByteArrayOutputStream();     // Catch: Throwable -> L15
        byte[] r1 = new byte[8192];     // Catch: Throwable -> L9
    L6:
        int r2 = r0.read(r1);     // Catch: Throwable -> L9
        if (r2 == (-1)) goto L11;
        r42.write(r1, 0, r2);     // Catch: Throwable -> L9
        goto L6
    L11:
        String r12 = r42.toString(StandardCharsets.UTF_8.name());     // Catch: Throwable -> L9
        r42.close();     // Catch: Throwable -> L15
        r0.close();
        return r12;
    L9:
        th = move-exception;
        r42.close();     // Catch: Throwable -> L19
    L21:
        throw th;     // Catch: Throwable -> L15
    L19:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L24
    L26:
        throw th;
    L24:
        th = move-exception;
        th.addSuppressed(th);
        goto L26
    }

    public static SessionReportingCoordinator create(Context r8, IdManager r9, FileStore r10, AppData r11, LogFileManager r12, UserMetadata r13, StackTraceTrimmingStrategy r14, SettingsProvider r15, OnDemandCounter r16, CrashlyticsAppQualitySessionsSubscriber r17, CrashlyticsWorkers r18) {
        return new SessionReportingCoordinator(new CrashlyticsReportDataCapture(r8, r9, r11, r14, r15), new CrashlyticsReportPersistence(r10, r15, r17), DataTransportCrashlyticsReportSender.create(r8, r15, r16), r12, r13, r9, r18);
    }

    private CrashlyticsReportWithSessionId ensureHasFid(CrashlyticsReportWithSessionId r3) {
        if (r3.getReport().getFirebaseInstallationId() != null) goto L5;
    L8:
        FirebaseInstallationId r2 = this.idManager.fetchTrueFid(true);
        return CrashlyticsReportWithSessionId.create(r3.getReport().withFirebaseInstallationId(r2.getFid()).withFirebaseAuthenticationToken(r2.getAuthToken()), r3.getSessionId(), r3.getReportFile());
    L5:
        if (r3.getReport().getFirebaseAuthenticationToken() == null) goto L8;
        return r3;
    }

    private ApplicationExitInfo findRelevantApplicationExitInfo(String r4, List<ApplicationExitInfo> r5, Predicate<ApplicationExitInfo> r6) {
        long r3 = this.reportPersistence.getStartTimestampMillis(r4);
        Iterator<ApplicationExitInfo> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L10;
        ApplicationExitInfo r0 = ForceStopRunnable$$ExternalSyntheticApiModelOutline0.m(r52.next());
        if (r0.getTimestamp() < r3) goto L4;
        if (r6.test(r0) == false) goto L4;
        return r0;
    L10:
        return null;
    }

    private static List<CrashlyticsReport.CustomAttribute> getSortedCustomAttributes(Map<String, String> r4) {
        ArrayList r0 = new ArrayList();
        r0.ensureCapacity(r4.size());
        Iterator<Map.Entry<String, String>> r42 = r4.entrySet().iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        Map.Entry<String, String> r1 = r42.next();
        r0.add(CrashlyticsReport.CustomAttribute.builder().setKey(r1.getKey()).setValue(r1.getValue()).build());
        goto L4
    L6:
        Collections.sort(r0, new SessionReportingCoordinator$$ExternalSyntheticLambda4());
        return Collections.unmodifiableList(r0);
    }

    private boolean onReportSendComplete(Task<CrashlyticsReportWithSessionId> r3) {
        if (r3.isSuccessful() == false) goto L10;
        CrashlyticsReportWithSessionId r2 = r3.getResult();
        Logger.getLogger().d("Crashlytics report successfully enqueued to DataTransport: " + r2.getSessionId());
        File r22 = r2.getReportFile();
        if (r22.delete() == false) goto L7;
        Logger.getLogger().d("Deleted report file: " + r22.getPath());
        return true;
    L7:
        Logger.getLogger().w("Crashlytics could not delete report file: " + r22.getPath());
        return true;
    L10:
        Logger.getLogger().w("Crashlytics report could not be enqueued to DataTransport", r3.getException());
        return false;
    }

    private void persistEvent(Throwable r11, Thread r12, String r13, EventMetadata r14, boolean r15) {
        boolean r0 = r13.equals("crash");
        CrashlyticsReport.Session.Event r112 = addMetaDataToEvent(this.dataCapture.captureEventData(r11, r12, r13, r14.getTimestamp(), 4, 8, r15), r14.getAdditionalCustomKeys());
        if (r15 == true) goto L6;
        this.crashlyticsWorkers.diskWrite.submit(new SessionReportingCoordinator$$ExternalSyntheticLambda1(this, r112, r14, r0));
        return;
    L6:
        this.reportPersistence.persistEvent(r112, r14.getSessionId(), r0);
    }

    public void finalizeSessionWithNativeEvent(String r3, List<NativeSessionFile> r4, CrashlyticsReport.ApplicationExitInfo r5) {
        Logger.getLogger().d("SessionReportingCoordinator#finalizeSessionWithNativeEvent");
        ArrayList r0 = new ArrayList();
        Iterator<NativeSessionFile> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L8;
        CrashlyticsReport.FilesPayload.File r1 = r42.next().asFilePayload();
        if (r1 == null) goto L4;
        r0.add(r1);
        goto L4
    L8:
        this.reportPersistence.finalizeSessionWithNativeEvent(r3, CrashlyticsReport.FilesPayload.builder().setFiles(Collections.unmodifiableList(r0)).build(), r5);
    }

    public void finalizeSessions(long r1, String r3) {
        this.reportPersistence.finalizeReports(r3, r1);
    }

    public boolean hasReportsToSend() {
        return this.reportPersistence.hasFinalizedReports();
    }

    public boolean isOom(String r2, List<ApplicationExitInfo> r3) {
        if (findRelevantApplicationExitInfo(r2, r3, new SessionReportingCoordinator$$ExternalSyntheticLambda7()) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public SortedSet<String> listSortedOpenSessionIds() {
        return this.reportPersistence.getOpenSessionIds();
    }

    public void onBeginSession(String r2, long r3) {
        CrashlyticsReport r22 = this.dataCapture.captureReportData(r2, r3);
        this.reportPersistence.persistReport(r22);
    }

    public void persistFatalEvent(Throwable r4, Thread r5, String r6, long r7) {
        Logger.getLogger().v("Persisting fatal event for session " + r6);
        persistEvent(r4, r5, "crash", new EventMetadata(r6, r7), true);
    }

    public void persistNonFatalEvent(Throwable r9, Thread r10, EventMetadata r11) {
        Logger.getLogger().v("Persisting non-fatal event for session " + r11.getSessionId());
        persistEvent(r9, r10, "error", r11, false);
    }

    public void persistProfilingManagerInfo(String r2, List<Integer> r3, List<ApplicationExitInfo> r4) {
        SessionReportingCoordinator$$ExternalSyntheticBackport0.m(r3.stream().findFirst(), new SessionReportingCoordinator$$ExternalSyntheticLambda5(this, r2, r4)).ifPresent(new SessionReportingCoordinator$$ExternalSyntheticLambda6(this, r2));
    }

    public void persistRelevantAppExitInfoEvent(String r4, List<ApplicationExitInfo> r5, LogFileManager r6, UserMetadata r7) {
        ApplicationExitInfo r52 = findRelevantApplicationExitInfo(r4, r5, new SessionReportingCoordinator$$ExternalSyntheticLambda3());
        if (r52 != null) goto L6;
        Logger.getLogger().v("No relevant ApplicationExitInfo occurred during session: " + r4);
        return;
    L6:
        CrashlyticsReport.Session.Event r53 = this.dataCapture.captureAnrEventData(convertApplicationExitInfo(r52));
        Logger.getLogger().d("Persisting anr for session " + r4);
        this.reportPersistence.persistEvent(addRolloutsStateToEvent(addLogsAndCustomKeysToEvent(r53, r6, r7), r7), r4, true);
    }

    public void removeAllReports() {
        this.reportPersistence.deleteAllReports();
    }

    public Task<Void> sendReports(Executor r2) {
        return sendReports(r2, null);
    }

    public Task<Void> sendReports(Executor r6, String r7) {
        List<CrashlyticsReportWithSessionId> r0 = this.reportPersistence.loadFinalizedReports();
        ArrayList r1 = new ArrayList();
        Iterator<CrashlyticsReportWithSessionId> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L15;
        CrashlyticsReportWithSessionId r2 = r02.next();
        if (r7 == null) goto L9;
        if (r7.equals(r2.getSessionId()) == false) goto L4;
    L9:
        DataTransportCrashlyticsReportSender r3 = this.reportsSender;
        CrashlyticsReportWithSessionId r22 = ensureHasFid(r2);
        if (r7 == null) goto L12;
        boolean r4 = true;
    L13:
        r1.add(r3.enqueueReport(r22, r4).continueWith(r6, new SessionReportingCoordinator$$ExternalSyntheticLambda2(this)));
        goto L4
    L12:
        r4 = false;
        goto L13
    L15:
        return Tasks.whenAll(r1);
    }
}
