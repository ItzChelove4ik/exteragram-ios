package com.google.firebase.sessions;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;

/* loaded from: classes.dex */
public final class AutoSessionEventEncoder implements Configurator {
    public static final Configurator CONFIG = null;

    public static final class AndroidApplicationInfoEncoder implements ObjectEncoder<AndroidApplicationInfo> {
        private static final FieldDescriptor APPBUILDVERSION_DESCRIPTOR = null;
        private static final FieldDescriptor APPPROCESSDETAILS_DESCRIPTOR = null;
        private static final FieldDescriptor CURRENTPROCESSDETAILS_DESCRIPTOR = null;
        private static final FieldDescriptor DEVICEMANUFACTURER_DESCRIPTOR = null;
        static final AndroidApplicationInfoEncoder INSTANCE = null;
        private static final FieldDescriptor PACKAGENAME_DESCRIPTOR = null;
        private static final FieldDescriptor VERSIONNAME_DESCRIPTOR = null;

        static {
            INSTANCE = new AndroidApplicationInfoEncoder();
            PACKAGENAME_DESCRIPTOR = FieldDescriptor.of("packageName");
            VERSIONNAME_DESCRIPTOR = FieldDescriptor.of("versionName");
            APPBUILDVERSION_DESCRIPTOR = FieldDescriptor.of("appBuildVersion");
            DEVICEMANUFACTURER_DESCRIPTOR = FieldDescriptor.of("deviceManufacturer");
            CURRENTPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.of("currentProcessDetails");
            APPPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.of("appProcessDetails");
        }

        private AndroidApplicationInfoEncoder() {
        }

        public void encode(AndroidApplicationInfo r2, ObjectEncoderContext r3) {
            r3.add(PACKAGENAME_DESCRIPTOR, r2.getPackageName());
            r3.add(VERSIONNAME_DESCRIPTOR, r2.getVersionName());
            r3.add(APPBUILDVERSION_DESCRIPTOR, r2.getAppBuildVersion());
            r3.add(DEVICEMANUFACTURER_DESCRIPTOR, r2.getDeviceManufacturer());
            r3.add(CURRENTPROCESSDETAILS_DESCRIPTOR, r2.getCurrentProcessDetails());
            r3.add(APPPROCESSDETAILS_DESCRIPTOR, r2.getAppProcessDetails());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((AndroidApplicationInfo) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ApplicationInfoEncoder implements ObjectEncoder<ApplicationInfo> {
        private static final FieldDescriptor ANDROIDAPPINFO_DESCRIPTOR = null;
        private static final FieldDescriptor APPID_DESCRIPTOR = null;
        private static final FieldDescriptor DEVICEMODEL_DESCRIPTOR = null;
        static final ApplicationInfoEncoder INSTANCE = null;
        private static final FieldDescriptor LOGENVIRONMENT_DESCRIPTOR = null;
        private static final FieldDescriptor OSVERSION_DESCRIPTOR = null;
        private static final FieldDescriptor SESSIONSDKVERSION_DESCRIPTOR = null;

        static {
            INSTANCE = new ApplicationInfoEncoder();
            APPID_DESCRIPTOR = FieldDescriptor.of("appId");
            DEVICEMODEL_DESCRIPTOR = FieldDescriptor.of("deviceModel");
            SESSIONSDKVERSION_DESCRIPTOR = FieldDescriptor.of("sessionSdkVersion");
            OSVERSION_DESCRIPTOR = FieldDescriptor.of("osVersion");
            LOGENVIRONMENT_DESCRIPTOR = FieldDescriptor.of("logEnvironment");
            ANDROIDAPPINFO_DESCRIPTOR = FieldDescriptor.of("androidAppInfo");
        }

        private ApplicationInfoEncoder() {
        }

        public void encode(ApplicationInfo r2, ObjectEncoderContext r3) {
            r3.add(APPID_DESCRIPTOR, r2.getAppId());
            r3.add(DEVICEMODEL_DESCRIPTOR, r2.getDeviceModel());
            r3.add(SESSIONSDKVERSION_DESCRIPTOR, r2.getSessionSdkVersion());
            r3.add(OSVERSION_DESCRIPTOR, r2.getOsVersion());
            r3.add(LOGENVIRONMENT_DESCRIPTOR, r2.getLogEnvironment());
            r3.add(ANDROIDAPPINFO_DESCRIPTOR, r2.getAndroidAppInfo());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((ApplicationInfo) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class DataCollectionStatusEncoder implements ObjectEncoder<DataCollectionStatus> {
        private static final FieldDescriptor CRASHLYTICS_DESCRIPTOR = null;
        static final DataCollectionStatusEncoder INSTANCE = null;
        private static final FieldDescriptor PERFORMANCE_DESCRIPTOR = null;
        private static final FieldDescriptor SESSIONSAMPLINGRATE_DESCRIPTOR = null;

        static {
            INSTANCE = new DataCollectionStatusEncoder();
            PERFORMANCE_DESCRIPTOR = FieldDescriptor.of("performance");
            CRASHLYTICS_DESCRIPTOR = FieldDescriptor.of("crashlytics");
            SESSIONSAMPLINGRATE_DESCRIPTOR = FieldDescriptor.of("sessionSamplingRate");
        }

        private DataCollectionStatusEncoder() {
        }

        public void encode(DataCollectionStatus r3, ObjectEncoderContext r4) {
            r4.add(PERFORMANCE_DESCRIPTOR, r3.getPerformance());
            r4.add(CRASHLYTICS_DESCRIPTOR, r3.getCrashlytics());
            r4.add(SESSIONSAMPLINGRATE_DESCRIPTOR, r3.getSessionSamplingRate());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((DataCollectionStatus) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ProcessDetailsEncoder implements ObjectEncoder<ProcessDetails> {
        private static final FieldDescriptor DEFAULTPROCESS_DESCRIPTOR = null;
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = null;
        static final ProcessDetailsEncoder INSTANCE = null;
        private static final FieldDescriptor PID_DESCRIPTOR = null;
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = null;

        static {
            INSTANCE = new ProcessDetailsEncoder();
            PROCESSNAME_DESCRIPTOR = FieldDescriptor.of("processName");
            PID_DESCRIPTOR = FieldDescriptor.of("pid");
            IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
            DEFAULTPROCESS_DESCRIPTOR = FieldDescriptor.of("defaultProcess");
        }

        private ProcessDetailsEncoder() {
        }

        public void encode(ProcessDetails r2, ObjectEncoderContext r3) {
            r3.add(PROCESSNAME_DESCRIPTOR, r2.getProcessName());
            r3.add(PID_DESCRIPTOR, r2.getPid());
            r3.add(IMPORTANCE_DESCRIPTOR, r2.getImportance());
            r3.add(DEFAULTPROCESS_DESCRIPTOR, r2.isDefaultProcess());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((ProcessDetails) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class SessionEventEncoder implements ObjectEncoder<SessionEvent> {
        private static final FieldDescriptor APPLICATIONINFO_DESCRIPTOR = null;
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR = null;
        static final SessionEventEncoder INSTANCE = null;
        private static final FieldDescriptor SESSIONDATA_DESCRIPTOR = null;

        static {
            INSTANCE = new SessionEventEncoder();
            EVENTTYPE_DESCRIPTOR = FieldDescriptor.of("eventType");
            SESSIONDATA_DESCRIPTOR = FieldDescriptor.of("sessionData");
            APPLICATIONINFO_DESCRIPTOR = FieldDescriptor.of("applicationInfo");
        }

        private SessionEventEncoder() {
        }

        public void encode(SessionEvent r2, ObjectEncoderContext r3) {
            r3.add(EVENTTYPE_DESCRIPTOR, r2.getEventType());
            r3.add(SESSIONDATA_DESCRIPTOR, r2.getSessionData());
            r3.add(APPLICATIONINFO_DESCRIPTOR, r2.getApplicationInfo());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((SessionEvent) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class SessionInfoEncoder implements ObjectEncoder<SessionInfo> {
        private static final FieldDescriptor DATACOLLECTIONSTATUS_DESCRIPTOR = null;
        private static final FieldDescriptor EVENTTIMESTAMPUS_DESCRIPTOR = null;
        private static final FieldDescriptor FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR = null;
        private static final FieldDescriptor FIREBASEINSTALLATIONID_DESCRIPTOR = null;
        private static final FieldDescriptor FIRSTSESSIONID_DESCRIPTOR = null;
        static final SessionInfoEncoder INSTANCE = null;
        private static final FieldDescriptor SESSIONID_DESCRIPTOR = null;
        private static final FieldDescriptor SESSIONINDEX_DESCRIPTOR = null;

        static {
            INSTANCE = new SessionInfoEncoder();
            SESSIONID_DESCRIPTOR = FieldDescriptor.of("sessionId");
            FIRSTSESSIONID_DESCRIPTOR = FieldDescriptor.of("firstSessionId");
            SESSIONINDEX_DESCRIPTOR = FieldDescriptor.of("sessionIndex");
            EVENTTIMESTAMPUS_DESCRIPTOR = FieldDescriptor.of("eventTimestampUs");
            DATACOLLECTIONSTATUS_DESCRIPTOR = FieldDescriptor.of("dataCollectionStatus");
            FIREBASEINSTALLATIONID_DESCRIPTOR = FieldDescriptor.of("firebaseInstallationId");
            FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR = FieldDescriptor.of("firebaseAuthenticationToken");
        }

        private SessionInfoEncoder() {
        }

        public void encode(SessionInfo r3, ObjectEncoderContext r4) {
            r4.add(SESSIONID_DESCRIPTOR, r3.getSessionId());
            r4.add(FIRSTSESSIONID_DESCRIPTOR, r3.getFirstSessionId());
            r4.add(SESSIONINDEX_DESCRIPTOR, r3.getSessionIndex());
            r4.add(EVENTTIMESTAMPUS_DESCRIPTOR, r3.getEventTimestampUs());
            r4.add(DATACOLLECTIONSTATUS_DESCRIPTOR, r3.getDataCollectionStatus());
            r4.add(FIREBASEINSTALLATIONID_DESCRIPTOR, r3.getFirebaseInstallationId());
            r4.add(FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR, r3.getFirebaseAuthenticationToken());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((SessionInfo) r1, (ObjectEncoderContext) r2);
        }
    }

    static {
        CONFIG = new AutoSessionEventEncoder();
    }

    private AutoSessionEventEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> r2) {
        r2.registerEncoder(SessionEvent.class, SessionEventEncoder.INSTANCE);
        r2.registerEncoder(SessionInfo.class, SessionInfoEncoder.INSTANCE);
        r2.registerEncoder(DataCollectionStatus.class, DataCollectionStatusEncoder.INSTANCE);
        r2.registerEncoder(ApplicationInfo.class, ApplicationInfoEncoder.INSTANCE);
        r2.registerEncoder(AndroidApplicationInfo.class, AndroidApplicationInfoEncoder.INSTANCE);
        r2.registerEncoder(ProcessDetails.class, ProcessDetailsEncoder.INSTANCE);
    }
}
