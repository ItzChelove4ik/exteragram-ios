package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.firebase.crashlytics.internal.ProcessDetailsProvider;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.mvel2.MVEL;

/* loaded from: classes.dex */
public class CrashlyticsReportDataCapture {
    private static final Map<String, Integer> ARCHITECTURES_BY_NAME = null;
    static final String GENERATOR = null;
    private final AppData appData;
    private final Context context;
    private final IdManager idManager;
    private final ProcessDetailsProvider processDetailsProvider;
    private final SettingsProvider settingsProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;

    static {
        HashMap r0 = new HashMap();
        ARCHITECTURES_BY_NAME = r0;
        r0.put("armeabi", 5);
        r0.put("armeabi-v7a", 6);
        r0.put("arm64-v8a", 9);
        r0.put("x86", 0);
        r0.put("x86_64", 1);
        GENERATOR = String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{"20.1.0"});
    }

    public CrashlyticsReportDataCapture(Context r2, IdManager r3, AppData r4, StackTraceTrimmingStrategy r5, SettingsProvider r6) {
        this.processDetailsProvider = ProcessDetailsProvider.INSTANCE;
        this.context = r2;
        this.idManager = r3;
        this.appData = r4;
        this.stackTraceTrimmingStrategy = r5;
        this.settingsProvider = r6;
    }

    private CrashlyticsReport.ApplicationExitInfo addBuildIdInfo(CrashlyticsReport.ApplicationExitInfo r5) {
        if (this.settingsProvider.getSettingsSync().featureFlagData.collectBuildIds == true) goto L5;
    L11:
        List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> r4 = null;
    L13:
        return CrashlyticsReport.ApplicationExitInfo.builder().setImportance(r5.getImportance()).setProcessName(r5.getProcessName()).setReasonCode(r5.getReasonCode()).setTimestamp(r5.getTimestamp()).setPid(r5.getPid()).setPss(r5.getPss()).setRss(r5.getRss()).setTraceFile(r5.getTraceFile()).setBuildIdMappingForArch(r4).build();
    L5:
        if (this.appData.buildIdInfoList.size() <= 0) goto L11;
        ArrayList r0 = new ArrayList();
        Iterator<BuildIdInfo> r42 = this.appData.buildIdInfoList.iterator();
    L8:
        if (r42.hasNext() == false) goto L10;
        BuildIdInfo r1 = r42.next();
        r0.add(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.builder().setLibraryName(r1.getLibraryName()).setArch(r1.getArch()).setBuildId(r1.getBuildId()).build());
        goto L8
    L10:
        r4 = Collections.unmodifiableList(r0);
        goto L13
    }

    private CrashlyticsReport.Builder buildReportData() {
        return CrashlyticsReport.builder().setSdkVersion("20.1.0").setGmpAppId(this.appData.googleAppId).setInstallationUuid(this.idManager.getInstallIds().getCrashlyticsInstallId()).setFirebaseInstallationId(this.idManager.getInstallIds().getFirebaseInstallationId()).setFirebaseAuthenticationToken(this.idManager.getInstallIds().getFirebaseAuthenticationToken()).setBuildVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setPlatform(4);
    }

    private static long ensureNonNegative(long r3) {
        if (r3 <= 0) goto L5;
        return r3;
    L5:
        return 0;
    }

    private static int getDeviceArchitecture() {
        String r0 = Build.CPU_ABI;
        if (TextUtils.isEmpty(r0) == false) goto L5;
        return 7;
    L5:
        Integer r02 = ARCHITECTURES_BY_NAME.get(r0.toLowerCase(Locale.US));
        if (r02 != null) goto L9;
        return 7;
    L9:
        return r02.intValue();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.BinaryImage populateBinaryImageData() {
        return CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.builder().setBaseAddress(0).setSize(0).setName(this.appData.packageName).setUuid(this.appData.buildId).build();
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> populateBinaryImagesList() {
        return Collections.singletonList(populateBinaryImageData());
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int r3, CrashlyticsReport.ApplicationExitInfo r4) {
        if (r4.getImportance() == 100) goto L5;
        boolean r0 = true;
    L7:
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(Boolean.valueOf(r0)).setCurrentProcessDetails(processDetailsFromApplicationExitInfo(r4)).setUiOrientation(r3).setExecution(populateExecutionData(r4)).build();
    L5:
        r0 = false;
        goto L7
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int r7, TrimmedThrowableData r8, Thread r9, int r10, int r11, boolean r12) {
        CrashlyticsReport.Session.Event.Application.ProcessDetails r0 = this.processDetailsProvider.getCurrentProcessDetails(this.context);
        if (r0.getImportance() > 0) goto L5;
        Boolean r1 = null;
    L11:
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(r1).setCurrentProcessDetails(r0).setAppProcessDetails(this.processDetailsProvider.getAppProcessDetails(this.context)).setUiOrientation(r7).setExecution(populateExecutionData(r8, r9, r10, r11, r12)).build();
    L5:
        if (r0.getImportance() == 100) goto L7;
        boolean r13 = true;
    L8:
        r1 = Boolean.valueOf(r13);
        goto L11
    L7:
        r13 = false;
        goto L8
    }

    private CrashlyticsReport.Session.Event.Device populateEventDeviceData(int r8) {
        BatteryState r0 = BatteryState.get(this.context);
        Float r1 = r0.getBatteryLevel();
        if (r1 == null) goto L5;
        Double r12 = Double.valueOf(r1.doubleValue());
    L6:
        int r02 = r0.getBatteryVelocity();
        boolean r2 = CommonUtils.getProximitySensorEnabled(this.context);
        long r3 = ensureNonNegative(CommonUtils.calculateTotalRamInBytes(this.context) - CommonUtils.calculateFreeRamInBytes(this.context));
        long r5 = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        return CrashlyticsReport.Session.Event.Device.builder().setBatteryLevel(r12).setBatteryVelocity(r02).setProximityOn(r2).setOrientation(r8).setRamUsed(r3).setDiskUsed(r5).build();
    L5:
        r12 = null;
        goto L6
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData r2, int r3, int r4) {
        return populateExceptionData(r2, r3, r4, 0);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData r6, int r7, int r8, int r9) {
        String r0 = r6.className;
        String r1 = r6.localizedMessage;
        StackTraceElement[] r2 = r6.stacktrace;
        int r3 = 0;
        if (r2 != null) goto L6;
        r2 = new StackTraceElement[0];
    L6:
        TrimmedThrowableData r62 = r6.cause;
        if (r9 < r8) goto L11;
        TrimmedThrowableData r4 = r62;
    L9:
        if (r4 == null) goto L11;
        r4 = r4.cause;
        r3 = r3 + 1;
    L11:
        CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder r02 = CrashlyticsReport.Session.Event.Application.Execution.Exception.builder().setType(r0).setReason(r1).setFrames(populateFramesList(r2, r7)).setOverflowCount(r3);
        if (r62 == null) goto L16;
        if (r3 != 0) goto L16;
        r02.setCausedBy(populateExceptionData(r62, r7, r8, r9 + 1));
    L16:
        return r02.build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(CrashlyticsReport.ApplicationExitInfo r2) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setAppExitInfo(r2).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(TrimmedThrowableData r2, Thread r3, int r4, int r5, boolean r6) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setThreads(populateThreadsList(r2, r3, r4, r6)).setException(populateExceptionData(r2, r4, r5)).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame populateFrameData(StackTraceElement r7, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder r8) {
        long r0 = 0;
        if (r7.isNativeMethod() == false) goto L5;
        long r2 = Math.max(r7.getLineNumber(), 0);
    L6:
        String r6 = r7.getClassName() + "." + r7.getMethodName();
        String r4 = r7.getFileName();
        if (r7.isNativeMethod() == true) goto L12;
        if (r7.getLineNumber() <= 0) goto L12;
        r0 = r7.getLineNumber();
    L12:
        return r8.setPc(r2).setSymbol(r6).setFile(r4).setOffset(r0).build();
    L5:
        r2 = 0;
        goto L6
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> populateFramesList(StackTraceElement[] r6, int r7) {
        ArrayList r0 = new ArrayList();
        int r1 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        r0.add(populateFrameData(r6[r2], CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.builder().setImportance(r7)));
        r2 = r2 + 1;
        goto L3
    L6:
        return Collections.unmodifiableList(r0);
    }

    private CrashlyticsReport.Session.Application populateSessionApplicationData() {
        return CrashlyticsReport.Session.Application.builder().setIdentifier(this.idManager.getAppIdentifier()).setVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setInstallationUuid(this.idManager.getInstallIds().getCrashlyticsInstallId()).setDevelopmentPlatform(this.appData.developmentPlatformProvider.getDevelopmentPlatform()).setDevelopmentPlatformVersion(this.appData.developmentPlatformProvider.getDevelopmentPlatformVersion()).build();
    }

    private CrashlyticsReport.Session populateSessionData(String r2, long r3) {
        return CrashlyticsReport.Session.builder().setStartedAt(r3).setIdentifier(r2).setGenerator(GENERATOR).setApp(populateSessionApplicationData()).setOs(populateSessionOperatingSystemData()).setDevice(populateSessionDeviceData()).setGeneratorType(3).build();
    }

    private CrashlyticsReport.Session.Device populateSessionDeviceData() {
        StatFs r0 = new StatFs(Environment.getDataDirectory().getPath());
        int r1 = getDeviceArchitecture();
        int r2 = Runtime.getRuntime().availableProcessors();
        long r3 = CommonUtils.calculateTotalRamInBytes(this.context);
        long r5 = r0.getBlockCount() * r0.getBlockSize();
        boolean r10 = CommonUtils.isEmulator();
        int r02 = CommonUtils.getDeviceState();
        String r7 = Build.MANUFACTURER;
        return CrashlyticsReport.Session.Device.builder().setArch(r1).setModel(Build.MODEL).setCores(r2).setRam(r3).setDiskSpace(r5).setSimulator(r10).setState(r02).setManufacturer(r7).setModelClass(Build.PRODUCT).build();
    }

    private CrashlyticsReport.Session.OperatingSystem populateSessionOperatingSystemData() {
        return CrashlyticsReport.Session.OperatingSystem.builder().setPlatform(3).setVersion(Build.VERSION.RELEASE).setBuildVersion(Build.VERSION.CODENAME).setJailbroken(CommonUtils.isRooted()).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Signal populateSignalData() {
        return CrashlyticsReport.Session.Event.Application.Execution.Signal.builder().setName(MVEL.VERSION_SUB).setCode(MVEL.VERSION_SUB).setAddress(0).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread r2, StackTraceElement[] r3) {
        return populateThreadData(r2, r3, 0);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread r2, StackTraceElement[] r3, int r4) {
        return CrashlyticsReport.Session.Event.Application.Execution.Thread.builder().setName(r2.getName()).setImportance(r4).setFrames(populateFramesList(r3, r4)).build();
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.Thread> populateThreadsList(TrimmedThrowableData r3, Thread r4, int r5, boolean r6) {
        ArrayList r0 = new ArrayList();
        r0.add(populateThreadData(r4, r3.stacktrace, r5));
        if (r6 == false) goto L11;
        Iterator<Map.Entry<Thread, StackTraceElement[]>> r32 = Thread.getAllStackTraces().entrySet().iterator();
    L6:
        if (r32.hasNext() == false) goto L11;
        Map.Entry<Thread, StackTraceElement[]> r52 = r32.next();
        Thread r62 = r52.getKey();
        if (r62.equals(r4) == true) goto L6;
        r0.add(populateThreadData(r62, this.stackTraceTrimmingStrategy.getTrimmedStackTrace(r52.getValue())));
    L11:
        return Collections.unmodifiableList(r0);
    }

    private CrashlyticsReport.Session.Event.Application.ProcessDetails processDetailsFromApplicationExitInfo(CrashlyticsReport.ApplicationExitInfo r3) {
        return this.processDetailsProvider.buildProcessDetails(r3.getProcessName(), r3.getPid(), r3.getImportance());
    }

    public CrashlyticsReport.Session.Event captureAnrEventData(CrashlyticsReport.ApplicationExitInfo r5) {
        int r0 = this.context.getResources().getConfiguration().orientation;
        return CrashlyticsReport.Session.Event.builder().setType("anr").setTimestamp(r5.getTimestamp()).setApp(populateEventApplicationData(r0, addBuildIdInfo(r5))).setDevice(populateEventDeviceData(r0)).build();
    }

    public CrashlyticsReport.Session.Event captureEventData(Throwable r3, Thread r4, String r5, long r6, int r8, int r9, boolean r10) {
        int r0 = this.context.getResources().getConfiguration().orientation;
        TrimmedThrowableData r32 = TrimmedThrowableData.makeTrimmedThrowableData(r3, this.stackTraceTrimmingStrategy);
        return CrashlyticsReport.Session.Event.builder().setType(r5).setTimestamp(r6).setApp(populateEventApplicationData(r0, r32, r4, r8, r9, r10)).setDevice(populateEventDeviceData(r0)).build();
    }

    public CrashlyticsReport captureReportData(String r2, long r3) {
        return buildReportData().setSession(populateSessionData(r2, r3)).build();
    }
}
