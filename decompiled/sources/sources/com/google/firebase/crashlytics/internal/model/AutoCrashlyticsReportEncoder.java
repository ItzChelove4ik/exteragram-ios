package com.google.firebase.crashlytics.internal.model;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;

/* loaded from: classes.dex */
public final class AutoCrashlyticsReportEncoder implements Configurator {
    public static final Configurator CONFIG = null;

    public static final class CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> {
        private static final FieldDescriptor ARCH_DESCRIPTOR = null;
        private static final FieldDescriptor BUILDID_DESCRIPTOR = null;
        static final CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder INSTANCE = null;
        private static final FieldDescriptor LIBRARYNAME_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder();
            ARCH_DESCRIPTOR = FieldDescriptor.of("arch");
            LIBRARYNAME_DESCRIPTOR = FieldDescriptor.of("libraryName");
            BUILDID_DESCRIPTOR = FieldDescriptor.of("buildId");
        }

        private CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder() {
        }

        public void encode(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch r2, ObjectEncoderContext r3) {
            r3.add(ARCH_DESCRIPTOR, r2.getArch());
            r3.add(LIBRARYNAME_DESCRIPTOR, r2.getLibraryName());
            r3.add(BUILDID_DESCRIPTOR, r2.getBuildId());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportApplicationExitInfoEncoder implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo> {
        private static final FieldDescriptor BUILDIDMAPPINGFORARCH_DESCRIPTOR = null;
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = null;
        static final CrashlyticsReportApplicationExitInfoEncoder INSTANCE = null;
        private static final FieldDescriptor PID_DESCRIPTOR = null;
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = null;
        private static final FieldDescriptor PSS_DESCRIPTOR = null;
        private static final FieldDescriptor REASONCODE_DESCRIPTOR = null;
        private static final FieldDescriptor RSS_DESCRIPTOR = null;
        private static final FieldDescriptor TIMESTAMP_DESCRIPTOR = null;
        private static final FieldDescriptor TRACEFILE_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportApplicationExitInfoEncoder();
            PID_DESCRIPTOR = FieldDescriptor.of("pid");
            PROCESSNAME_DESCRIPTOR = FieldDescriptor.of("processName");
            REASONCODE_DESCRIPTOR = FieldDescriptor.of("reasonCode");
            IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
            PSS_DESCRIPTOR = FieldDescriptor.of("pss");
            RSS_DESCRIPTOR = FieldDescriptor.of("rss");
            TIMESTAMP_DESCRIPTOR = FieldDescriptor.of("timestamp");
            TRACEFILE_DESCRIPTOR = FieldDescriptor.of("traceFile");
            BUILDIDMAPPINGFORARCH_DESCRIPTOR = FieldDescriptor.of("buildIdMappingForArch");
        }

        private CrashlyticsReportApplicationExitInfoEncoder() {
        }

        public void encode(CrashlyticsReport.ApplicationExitInfo r3, ObjectEncoderContext r4) {
            r4.add(PID_DESCRIPTOR, r3.getPid());
            r4.add(PROCESSNAME_DESCRIPTOR, r3.getProcessName());
            r4.add(REASONCODE_DESCRIPTOR, r3.getReasonCode());
            r4.add(IMPORTANCE_DESCRIPTOR, r3.getImportance());
            r4.add(PSS_DESCRIPTOR, r3.getPss());
            r4.add(RSS_DESCRIPTOR, r3.getRss());
            r4.add(TIMESTAMP_DESCRIPTOR, r3.getTimestamp());
            r4.add(TRACEFILE_DESCRIPTOR, r3.getTraceFile());
            r4.add(BUILDIDMAPPINGFORARCH_DESCRIPTOR, r3.getBuildIdMappingForArch());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.ApplicationExitInfo) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportCustomAttributeEncoder implements ObjectEncoder<CrashlyticsReport.CustomAttribute> {
        static final CrashlyticsReportCustomAttributeEncoder INSTANCE = null;
        private static final FieldDescriptor KEY_DESCRIPTOR = null;
        private static final FieldDescriptor VALUE_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportCustomAttributeEncoder();
            KEY_DESCRIPTOR = FieldDescriptor.of(PluginsConstants.Settings.KEY);
            VALUE_DESCRIPTOR = FieldDescriptor.of("value");
        }

        private CrashlyticsReportCustomAttributeEncoder() {
        }

        public void encode(CrashlyticsReport.CustomAttribute r2, ObjectEncoderContext r3) {
            r3.add(KEY_DESCRIPTOR, r2.getKey());
            r3.add(VALUE_DESCRIPTOR, r2.getValue());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.CustomAttribute) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportEncoder implements ObjectEncoder<CrashlyticsReport> {
        private static final FieldDescriptor APPEXITINFO_DESCRIPTOR = null;
        private static final FieldDescriptor APPQUALITYSESSIONID_DESCRIPTOR = null;
        private static final FieldDescriptor BUILDVERSION_DESCRIPTOR = null;
        private static final FieldDescriptor DISPLAYVERSION_DESCRIPTOR = null;
        private static final FieldDescriptor FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR = null;
        private static final FieldDescriptor FIREBASEINSTALLATIONID_DESCRIPTOR = null;
        private static final FieldDescriptor GMPAPPID_DESCRIPTOR = null;
        private static final FieldDescriptor INSTALLATIONUUID_DESCRIPTOR = null;
        static final CrashlyticsReportEncoder INSTANCE = null;
        private static final FieldDescriptor NDKPAYLOAD_DESCRIPTOR = null;
        private static final FieldDescriptor PLATFORM_DESCRIPTOR = null;
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR = null;
        private static final FieldDescriptor SESSION_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportEncoder();
            SDKVERSION_DESCRIPTOR = FieldDescriptor.of("sdkVersion");
            GMPAPPID_DESCRIPTOR = FieldDescriptor.of("gmpAppId");
            PLATFORM_DESCRIPTOR = FieldDescriptor.of("platform");
            INSTALLATIONUUID_DESCRIPTOR = FieldDescriptor.of("installationUuid");
            FIREBASEINSTALLATIONID_DESCRIPTOR = FieldDescriptor.of("firebaseInstallationId");
            FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR = FieldDescriptor.of("firebaseAuthenticationToken");
            APPQUALITYSESSIONID_DESCRIPTOR = FieldDescriptor.of("appQualitySessionId");
            BUILDVERSION_DESCRIPTOR = FieldDescriptor.of("buildVersion");
            DISPLAYVERSION_DESCRIPTOR = FieldDescriptor.of("displayVersion");
            SESSION_DESCRIPTOR = FieldDescriptor.of("session");
            NDKPAYLOAD_DESCRIPTOR = FieldDescriptor.of("ndkPayload");
            APPEXITINFO_DESCRIPTOR = FieldDescriptor.of("appExitInfo");
        }

        private CrashlyticsReportEncoder() {
        }

        public void encode(CrashlyticsReport r2, ObjectEncoderContext r3) {
            r3.add(SDKVERSION_DESCRIPTOR, r2.getSdkVersion());
            r3.add(GMPAPPID_DESCRIPTOR, r2.getGmpAppId());
            r3.add(PLATFORM_DESCRIPTOR, r2.getPlatform());
            r3.add(INSTALLATIONUUID_DESCRIPTOR, r2.getInstallationUuid());
            r3.add(FIREBASEINSTALLATIONID_DESCRIPTOR, r2.getFirebaseInstallationId());
            r3.add(FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR, r2.getFirebaseAuthenticationToken());
            r3.add(APPQUALITYSESSIONID_DESCRIPTOR, r2.getAppQualitySessionId());
            r3.add(BUILDVERSION_DESCRIPTOR, r2.getBuildVersion());
            r3.add(DISPLAYVERSION_DESCRIPTOR, r2.getDisplayVersion());
            r3.add(SESSION_DESCRIPTOR, r2.getSession());
            r3.add(NDKPAYLOAD_DESCRIPTOR, r2.getNdkPayload());
            r3.add(APPEXITINFO_DESCRIPTOR, r2.getAppExitInfo());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportFilesPayloadEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload> {
        private static final FieldDescriptor FILES_DESCRIPTOR = null;
        static final CrashlyticsReportFilesPayloadEncoder INSTANCE = null;
        private static final FieldDescriptor ORGID_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportFilesPayloadEncoder();
            FILES_DESCRIPTOR = FieldDescriptor.of("files");
            ORGID_DESCRIPTOR = FieldDescriptor.of("orgId");
        }

        private CrashlyticsReportFilesPayloadEncoder() {
        }

        public void encode(CrashlyticsReport.FilesPayload r2, ObjectEncoderContext r3) {
            r3.add(FILES_DESCRIPTOR, r2.getFiles());
            r3.add(ORGID_DESCRIPTOR, r2.getOrgId());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.FilesPayload) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportFilesPayloadFileEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload.File> {
        private static final FieldDescriptor CONTENTS_DESCRIPTOR = null;
        private static final FieldDescriptor FILENAME_DESCRIPTOR = null;
        static final CrashlyticsReportFilesPayloadFileEncoder INSTANCE = null;

        static {
            INSTANCE = new CrashlyticsReportFilesPayloadFileEncoder();
            FILENAME_DESCRIPTOR = FieldDescriptor.of("filename");
            CONTENTS_DESCRIPTOR = FieldDescriptor.of("contents");
        }

        private CrashlyticsReportFilesPayloadFileEncoder() {
        }

        public void encode(CrashlyticsReport.FilesPayload.File r2, ObjectEncoderContext r3) {
            r3.add(FILENAME_DESCRIPTOR, r2.getFilename());
            r3.add(CONTENTS_DESCRIPTOR, r2.getContents());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.FilesPayload.File) r1, (ObjectEncoderContext) r2);
        }
    }

    /* loaded from: classes5.dex */
    public static final class CrashlyticsReportProfilingManagerInfoEncoder implements ObjectEncoder<CrashlyticsReport.ProfilingManagerInfo> {
        static final CrashlyticsReportProfilingManagerInfoEncoder INSTANCE = null;
        private static final FieldDescriptor PROFILINGTRIGGER_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportProfilingManagerInfoEncoder();
            PROFILINGTRIGGER_DESCRIPTOR = FieldDescriptor.of("profilingTrigger");
        }

        private CrashlyticsReportProfilingManagerInfoEncoder() {
        }

        public void encode(CrashlyticsReport.ProfilingManagerInfo r1, ObjectEncoderContext r2) {
            r2.add(PROFILINGTRIGGER_DESCRIPTOR, r1.getProfilingTrigger());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.ProfilingManagerInfo) r1, (ObjectEncoderContext) r2);
        }
    }

    /* loaded from: classes5.dex */
    public static final class CrashlyticsReportProfilingManagerInfoProfilingTriggerEncoder implements ObjectEncoder<CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger> {
        static final CrashlyticsReportProfilingManagerInfoProfilingTriggerEncoder INSTANCE = null;
        private static final FieldDescriptor TRIGGER_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportProfilingManagerInfoProfilingTriggerEncoder();
            TRIGGER_DESCRIPTOR = FieldDescriptor.of("trigger");
        }

        private CrashlyticsReportProfilingManagerInfoProfilingTriggerEncoder() {
        }

        public void encode(CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger r1, ObjectEncoderContext r2) {
            r2.add(TRIGGER_DESCRIPTOR, r1.getTrigger());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application> {
        private static final FieldDescriptor DEVELOPMENTPLATFORMVERSION_DESCRIPTOR = null;
        private static final FieldDescriptor DEVELOPMENTPLATFORM_DESCRIPTOR = null;
        private static final FieldDescriptor DISPLAYVERSION_DESCRIPTOR = null;
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = null;
        private static final FieldDescriptor INSTALLATIONUUID_DESCRIPTOR = null;
        static final CrashlyticsReportSessionApplicationEncoder INSTANCE = null;
        private static final FieldDescriptor ORGANIZATION_DESCRIPTOR = null;
        private static final FieldDescriptor VERSION_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionApplicationEncoder();
            IDENTIFIER_DESCRIPTOR = FieldDescriptor.of("identifier");
            VERSION_DESCRIPTOR = FieldDescriptor.of("version");
            DISPLAYVERSION_DESCRIPTOR = FieldDescriptor.of("displayVersion");
            ORGANIZATION_DESCRIPTOR = FieldDescriptor.of("organization");
            INSTALLATIONUUID_DESCRIPTOR = FieldDescriptor.of("installationUuid");
            DEVELOPMENTPLATFORM_DESCRIPTOR = FieldDescriptor.of("developmentPlatform");
            DEVELOPMENTPLATFORMVERSION_DESCRIPTOR = FieldDescriptor.of("developmentPlatformVersion");
        }

        private CrashlyticsReportSessionApplicationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Application r2, ObjectEncoderContext r3) {
            r3.add(IDENTIFIER_DESCRIPTOR, r2.getIdentifier());
            r3.add(VERSION_DESCRIPTOR, r2.getVersion());
            r3.add(DISPLAYVERSION_DESCRIPTOR, r2.getDisplayVersion());
            FieldDescriptor r1 = ORGANIZATION_DESCRIPTOR;
            r2.getOrganization();
            r3.add(r1, null);
            r3.add(INSTALLATIONUUID_DESCRIPTOR, r2.getInstallationUuid());
            r3.add(DEVELOPMENTPLATFORM_DESCRIPTOR, r2.getDevelopmentPlatform());
            r3.add(DEVELOPMENTPLATFORMVERSION_DESCRIPTOR, r2.getDevelopmentPlatformVersion());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Application) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionApplicationOrganizationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application.Organization> {
        private static final FieldDescriptor CLSID_DESCRIPTOR = null;
        static final CrashlyticsReportSessionApplicationOrganizationEncoder INSTANCE = null;

        static {
            INSTANCE = new CrashlyticsReportSessionApplicationOrganizationEncoder();
            CLSID_DESCRIPTOR = FieldDescriptor.of("clsId");
        }

        private CrashlyticsReportSessionApplicationOrganizationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Application.Organization r1, ObjectEncoderContext r2) {
            throw null;
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
            encode(null, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Device> {
        private static final FieldDescriptor ARCH_DESCRIPTOR = null;
        private static final FieldDescriptor CORES_DESCRIPTOR = null;
        private static final FieldDescriptor DISKSPACE_DESCRIPTOR = null;
        static final CrashlyticsReportSessionDeviceEncoder INSTANCE = null;
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR = null;
        private static final FieldDescriptor MODELCLASS_DESCRIPTOR = null;
        private static final FieldDescriptor MODEL_DESCRIPTOR = null;
        private static final FieldDescriptor RAM_DESCRIPTOR = null;
        private static final FieldDescriptor SIMULATOR_DESCRIPTOR = null;
        private static final FieldDescriptor STATE_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionDeviceEncoder();
            ARCH_DESCRIPTOR = FieldDescriptor.of("arch");
            MODEL_DESCRIPTOR = FieldDescriptor.of("model");
            CORES_DESCRIPTOR = FieldDescriptor.of("cores");
            RAM_DESCRIPTOR = FieldDescriptor.of("ram");
            DISKSPACE_DESCRIPTOR = FieldDescriptor.of("diskSpace");
            SIMULATOR_DESCRIPTOR = FieldDescriptor.of("simulator");
            STATE_DESCRIPTOR = FieldDescriptor.of("state");
            MANUFACTURER_DESCRIPTOR = FieldDescriptor.of("manufacturer");
            MODELCLASS_DESCRIPTOR = FieldDescriptor.of("modelClass");
        }

        private CrashlyticsReportSessionDeviceEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Device r3, ObjectEncoderContext r4) {
            r4.add(ARCH_DESCRIPTOR, r3.getArch());
            r4.add(MODEL_DESCRIPTOR, r3.getModel());
            r4.add(CORES_DESCRIPTOR, r3.getCores());
            r4.add(RAM_DESCRIPTOR, r3.getRam());
            r4.add(DISKSPACE_DESCRIPTOR, r3.getDiskSpace());
            r4.add(SIMULATOR_DESCRIPTOR, r3.isSimulator());
            r4.add(STATE_DESCRIPTOR, r3.getState());
            r4.add(MANUFACTURER_DESCRIPTOR, r3.getManufacturer());
            r4.add(MODELCLASS_DESCRIPTOR, r3.getModelClass());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Device) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEncoder implements ObjectEncoder<CrashlyticsReport.Session> {
        private static final FieldDescriptor APPQUALITYSESSIONID_DESCRIPTOR = null;
        private static final FieldDescriptor APP_DESCRIPTOR = null;
        private static final FieldDescriptor CRASHED_DESCRIPTOR = null;
        private static final FieldDescriptor DEVICE_DESCRIPTOR = null;
        private static final FieldDescriptor ENDEDAT_DESCRIPTOR = null;
        private static final FieldDescriptor EVENTS_DESCRIPTOR = null;
        private static final FieldDescriptor GENERATORTYPE_DESCRIPTOR = null;
        private static final FieldDescriptor GENERATOR_DESCRIPTOR = null;
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEncoder INSTANCE = null;
        private static final FieldDescriptor OS_DESCRIPTOR = null;
        private static final FieldDescriptor STARTEDAT_DESCRIPTOR = null;
        private static final FieldDescriptor USER_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEncoder();
            GENERATOR_DESCRIPTOR = FieldDescriptor.of("generator");
            IDENTIFIER_DESCRIPTOR = FieldDescriptor.of("identifier");
            APPQUALITYSESSIONID_DESCRIPTOR = FieldDescriptor.of("appQualitySessionId");
            STARTEDAT_DESCRIPTOR = FieldDescriptor.of("startedAt");
            ENDEDAT_DESCRIPTOR = FieldDescriptor.of("endedAt");
            CRASHED_DESCRIPTOR = FieldDescriptor.of("crashed");
            APP_DESCRIPTOR = FieldDescriptor.of(Common.ASSET_APP);
            USER_DESCRIPTOR = FieldDescriptor.of("user");
            OS_DESCRIPTOR = FieldDescriptor.of("os");
            DEVICE_DESCRIPTOR = FieldDescriptor.of("device");
            EVENTS_DESCRIPTOR = FieldDescriptor.of("events");
            GENERATORTYPE_DESCRIPTOR = FieldDescriptor.of("generatorType");
        }

        private CrashlyticsReportSessionEncoder() {
        }

        public void encode(CrashlyticsReport.Session r3, ObjectEncoderContext r4) {
            r4.add(GENERATOR_DESCRIPTOR, r3.getGenerator());
            r4.add(IDENTIFIER_DESCRIPTOR, r3.getIdentifierUtf8Bytes());
            r4.add(APPQUALITYSESSIONID_DESCRIPTOR, r3.getAppQualitySessionId());
            r4.add(STARTEDAT_DESCRIPTOR, r3.getStartedAt());
            r4.add(ENDEDAT_DESCRIPTOR, r3.getEndedAt());
            r4.add(CRASHED_DESCRIPTOR, r3.isCrashed());
            r4.add(APP_DESCRIPTOR, r3.getApp());
            r4.add(USER_DESCRIPTOR, r3.getUser());
            r4.add(OS_DESCRIPTOR, r3.getOs());
            r4.add(DEVICE_DESCRIPTOR, r3.getDevice());
            r4.add(EVENTS_DESCRIPTOR, r3.getEvents());
            r4.add(GENERATORTYPE_DESCRIPTOR, r3.getGeneratorType());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application> {
        private static final FieldDescriptor APPPROCESSDETAILS_DESCRIPTOR = null;
        private static final FieldDescriptor BACKGROUND_DESCRIPTOR = null;
        private static final FieldDescriptor CURRENTPROCESSDETAILS_DESCRIPTOR = null;
        private static final FieldDescriptor CUSTOMATTRIBUTES_DESCRIPTOR = null;
        private static final FieldDescriptor EXECUTION_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventApplicationEncoder INSTANCE = null;
        private static final FieldDescriptor INTERNALKEYS_DESCRIPTOR = null;
        private static final FieldDescriptor UIORIENTATION_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventApplicationEncoder();
            EXECUTION_DESCRIPTOR = FieldDescriptor.of("execution");
            CUSTOMATTRIBUTES_DESCRIPTOR = FieldDescriptor.of("customAttributes");
            INTERNALKEYS_DESCRIPTOR = FieldDescriptor.of("internalKeys");
            BACKGROUND_DESCRIPTOR = FieldDescriptor.of("background");
            CURRENTPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.of("currentProcessDetails");
            APPPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.of("appProcessDetails");
            UIORIENTATION_DESCRIPTOR = FieldDescriptor.of("uiOrientation");
        }

        private CrashlyticsReportSessionEventApplicationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application r2, ObjectEncoderContext r3) {
            r3.add(EXECUTION_DESCRIPTOR, r2.getExecution());
            r3.add(CUSTOMATTRIBUTES_DESCRIPTOR, r2.getCustomAttributes());
            r3.add(INTERNALKEYS_DESCRIPTOR, r2.getInternalKeys());
            r3.add(BACKGROUND_DESCRIPTOR, r2.getBackground());
            r3.add(CURRENTPROCESSDETAILS_DESCRIPTOR, r2.getCurrentProcessDetails());
            r3.add(APPPROCESSDETAILS_DESCRIPTOR, r2.getAppProcessDetails());
            r3.add(UIORIENTATION_DESCRIPTOR, r2.getUiOrientation());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Application) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> {
        private static final FieldDescriptor BASEADDRESS_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder INSTANCE = null;
        private static final FieldDescriptor NAME_DESCRIPTOR = null;
        private static final FieldDescriptor SIZE_DESCRIPTOR = null;
        private static final FieldDescriptor UUID_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder();
            BASEADDRESS_DESCRIPTOR = FieldDescriptor.of("baseAddress");
            SIZE_DESCRIPTOR = FieldDescriptor.of("size");
            NAME_DESCRIPTOR = FieldDescriptor.of("name");
            UUID_DESCRIPTOR = FieldDescriptor.of("uuid");
        }

        private CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage r3, ObjectEncoderContext r4) {
            r4.add(BASEADDRESS_DESCRIPTOR, r3.getBaseAddress());
            r4.add(SIZE_DESCRIPTOR, r3.getSize());
            r4.add(NAME_DESCRIPTOR, r3.getName());
            r4.add(UUID_DESCRIPTOR, r3.getUuidUtf8Bytes());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Application.Execution.BinaryImage) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution> {
        private static final FieldDescriptor APPEXITINFO_DESCRIPTOR = null;
        private static final FieldDescriptor BINARIES_DESCRIPTOR = null;
        private static final FieldDescriptor EXCEPTION_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventApplicationExecutionEncoder INSTANCE = null;
        private static final FieldDescriptor PROFILINGMANAGERINFO_DESCRIPTOR = null;
        private static final FieldDescriptor SIGNAL_DESCRIPTOR = null;
        private static final FieldDescriptor THREADS_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionEncoder();
            THREADS_DESCRIPTOR = FieldDescriptor.of("threads");
            EXCEPTION_DESCRIPTOR = FieldDescriptor.of("exception");
            APPEXITINFO_DESCRIPTOR = FieldDescriptor.of("appExitInfo");
            PROFILINGMANAGERINFO_DESCRIPTOR = FieldDescriptor.of("profilingManagerInfo");
            SIGNAL_DESCRIPTOR = FieldDescriptor.of("signal");
            BINARIES_DESCRIPTOR = FieldDescriptor.of("binaries");
        }

        private CrashlyticsReportSessionEventApplicationExecutionEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution r2, ObjectEncoderContext r3) {
            r3.add(THREADS_DESCRIPTOR, r2.getThreads());
            r3.add(EXCEPTION_DESCRIPTOR, r2.getException());
            r3.add(APPEXITINFO_DESCRIPTOR, r2.getAppExitInfo());
            r3.add(PROFILINGMANAGERINFO_DESCRIPTOR, r2.getProfilingManagerInfo());
            r3.add(SIGNAL_DESCRIPTOR, r2.getSignal());
            r3.add(BINARIES_DESCRIPTOR, r2.getBinaries());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Application.Execution) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Exception> {
        private static final FieldDescriptor CAUSEDBY_DESCRIPTOR = null;
        private static final FieldDescriptor FRAMES_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder INSTANCE = null;
        private static final FieldDescriptor OVERFLOWCOUNT_DESCRIPTOR = null;
        private static final FieldDescriptor REASON_DESCRIPTOR = null;
        private static final FieldDescriptor TYPE_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder();
            TYPE_DESCRIPTOR = FieldDescriptor.of("type");
            REASON_DESCRIPTOR = FieldDescriptor.of("reason");
            FRAMES_DESCRIPTOR = FieldDescriptor.of("frames");
            CAUSEDBY_DESCRIPTOR = FieldDescriptor.of("causedBy");
            OVERFLOWCOUNT_DESCRIPTOR = FieldDescriptor.of("overflowCount");
        }

        private CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Exception r2, ObjectEncoderContext r3) {
            r3.add(TYPE_DESCRIPTOR, r2.getType());
            r3.add(REASON_DESCRIPTOR, r2.getReason());
            r3.add(FRAMES_DESCRIPTOR, r2.getFrames());
            r3.add(CAUSEDBY_DESCRIPTOR, r2.getCausedBy());
            r3.add(OVERFLOWCOUNT_DESCRIPTOR, r2.getOverflowCount());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Application.Execution.Exception) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionSignalEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Signal> {
        private static final FieldDescriptor ADDRESS_DESCRIPTOR = null;
        private static final FieldDescriptor CODE_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventApplicationExecutionSignalEncoder INSTANCE = null;
        private static final FieldDescriptor NAME_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionSignalEncoder();
            NAME_DESCRIPTOR = FieldDescriptor.of("name");
            CODE_DESCRIPTOR = FieldDescriptor.of("code");
            ADDRESS_DESCRIPTOR = FieldDescriptor.of("address");
        }

        private CrashlyticsReportSessionEventApplicationExecutionSignalEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Signal r3, ObjectEncoderContext r4) {
            r4.add(NAME_DESCRIPTOR, r3.getName());
            r4.add(CODE_DESCRIPTOR, r3.getCode());
            r4.add(ADDRESS_DESCRIPTOR, r3.getAddress());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Application.Execution.Signal) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionThreadEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread> {
        private static final FieldDescriptor FRAMES_DESCRIPTOR = null;
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventApplicationExecutionThreadEncoder INSTANCE = null;
        private static final FieldDescriptor NAME_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadEncoder();
            NAME_DESCRIPTOR = FieldDescriptor.of("name");
            IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
            FRAMES_DESCRIPTOR = FieldDescriptor.of("frames");
        }

        private CrashlyticsReportSessionEventApplicationExecutionThreadEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread r2, ObjectEncoderContext r3) {
            r3.add(NAME_DESCRIPTOR, r2.getName());
            r3.add(IMPORTANCE_DESCRIPTOR, r2.getImportance());
            r3.add(FRAMES_DESCRIPTOR, r2.getFrames());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Application.Execution.Thread) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> {
        private static final FieldDescriptor FILE_DESCRIPTOR = null;
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder INSTANCE = null;
        private static final FieldDescriptor OFFSET_DESCRIPTOR = null;
        private static final FieldDescriptor PC_DESCRIPTOR = null;
        private static final FieldDescriptor SYMBOL_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder();
            PC_DESCRIPTOR = FieldDescriptor.of("pc");
            SYMBOL_DESCRIPTOR = FieldDescriptor.of("symbol");
            FILE_DESCRIPTOR = FieldDescriptor.of("file");
            OFFSET_DESCRIPTOR = FieldDescriptor.of("offset");
            IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
        }

        private CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame r3, ObjectEncoderContext r4) {
            r4.add(PC_DESCRIPTOR, r3.getPc());
            r4.add(SYMBOL_DESCRIPTOR, r3.getSymbol());
            r4.add(FILE_DESCRIPTOR, r3.getFile());
            r4.add(OFFSET_DESCRIPTOR, r3.getOffset());
            r4.add(IMPORTANCE_DESCRIPTOR, r3.getImportance());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationProcessDetailsEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.ProcessDetails> {
        private static final FieldDescriptor DEFAULTPROCESS_DESCRIPTOR = null;
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventApplicationProcessDetailsEncoder INSTANCE = null;
        private static final FieldDescriptor PID_DESCRIPTOR = null;
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventApplicationProcessDetailsEncoder();
            PROCESSNAME_DESCRIPTOR = FieldDescriptor.of("processName");
            PID_DESCRIPTOR = FieldDescriptor.of("pid");
            IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
            DEFAULTPROCESS_DESCRIPTOR = FieldDescriptor.of("defaultProcess");
        }

        private CrashlyticsReportSessionEventApplicationProcessDetailsEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.ProcessDetails r2, ObjectEncoderContext r3) {
            r3.add(PROCESSNAME_DESCRIPTOR, r2.getProcessName());
            r3.add(PID_DESCRIPTOR, r2.getPid());
            r3.add(IMPORTANCE_DESCRIPTOR, r2.getImportance());
            r3.add(DEFAULTPROCESS_DESCRIPTOR, r2.isDefaultProcess());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Application.ProcessDetails) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Device> {
        private static final FieldDescriptor BATTERYLEVEL_DESCRIPTOR = null;
        private static final FieldDescriptor BATTERYVELOCITY_DESCRIPTOR = null;
        private static final FieldDescriptor DISKUSED_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventDeviceEncoder INSTANCE = null;
        private static final FieldDescriptor ORIENTATION_DESCRIPTOR = null;
        private static final FieldDescriptor PROXIMITYON_DESCRIPTOR = null;
        private static final FieldDescriptor RAMUSED_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventDeviceEncoder();
            BATTERYLEVEL_DESCRIPTOR = FieldDescriptor.of("batteryLevel");
            BATTERYVELOCITY_DESCRIPTOR = FieldDescriptor.of("batteryVelocity");
            PROXIMITYON_DESCRIPTOR = FieldDescriptor.of("proximityOn");
            ORIENTATION_DESCRIPTOR = FieldDescriptor.of("orientation");
            RAMUSED_DESCRIPTOR = FieldDescriptor.of("ramUsed");
            DISKUSED_DESCRIPTOR = FieldDescriptor.of("diskUsed");
        }

        private CrashlyticsReportSessionEventDeviceEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Device r3, ObjectEncoderContext r4) {
            r4.add(BATTERYLEVEL_DESCRIPTOR, r3.getBatteryLevel());
            r4.add(BATTERYVELOCITY_DESCRIPTOR, r3.getBatteryVelocity());
            r4.add(PROXIMITYON_DESCRIPTOR, r3.isProximityOn());
            r4.add(ORIENTATION_DESCRIPTOR, r3.getOrientation());
            r4.add(RAMUSED_DESCRIPTOR, r3.getRamUsed());
            r4.add(DISKUSED_DESCRIPTOR, r3.getDiskUsed());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Device) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event> {
        private static final FieldDescriptor APP_DESCRIPTOR = null;
        private static final FieldDescriptor DEVICE_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventEncoder INSTANCE = null;
        private static final FieldDescriptor LOG_DESCRIPTOR = null;
        private static final FieldDescriptor ROLLOUTS_DESCRIPTOR = null;
        private static final FieldDescriptor TIMESTAMP_DESCRIPTOR = null;
        private static final FieldDescriptor TYPE_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventEncoder();
            TIMESTAMP_DESCRIPTOR = FieldDescriptor.of("timestamp");
            TYPE_DESCRIPTOR = FieldDescriptor.of("type");
            APP_DESCRIPTOR = FieldDescriptor.of(Common.ASSET_APP);
            DEVICE_DESCRIPTOR = FieldDescriptor.of("device");
            LOG_DESCRIPTOR = FieldDescriptor.of("log");
            ROLLOUTS_DESCRIPTOR = FieldDescriptor.of("rollouts");
        }

        private CrashlyticsReportSessionEventEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event r3, ObjectEncoderContext r4) {
            r4.add(TIMESTAMP_DESCRIPTOR, r3.getTimestamp());
            r4.add(TYPE_DESCRIPTOR, r3.getType());
            r4.add(APP_DESCRIPTOR, r3.getApp());
            r4.add(DEVICE_DESCRIPTOR, r3.getDevice());
            r4.add(LOG_DESCRIPTOR, r3.getLog());
            r4.add(ROLLOUTS_DESCRIPTOR, r3.getRollouts());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventLogEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Log> {
        private static final FieldDescriptor CONTENT_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventLogEncoder INSTANCE = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventLogEncoder();
            CONTENT_DESCRIPTOR = FieldDescriptor.of("content");
        }

        private CrashlyticsReportSessionEventLogEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Log r1, ObjectEncoderContext r2) {
            r2.add(CONTENT_DESCRIPTOR, r1.getContent());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.Log) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventRolloutAssignmentEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutAssignment> {
        static final CrashlyticsReportSessionEventRolloutAssignmentEncoder INSTANCE = null;
        private static final FieldDescriptor PARAMETERKEY_DESCRIPTOR = null;
        private static final FieldDescriptor PARAMETERVALUE_DESCRIPTOR = null;
        private static final FieldDescriptor ROLLOUTVARIANT_DESCRIPTOR = null;
        private static final FieldDescriptor TEMPLATEVERSION_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventRolloutAssignmentEncoder();
            ROLLOUTVARIANT_DESCRIPTOR = FieldDescriptor.of("rolloutVariant");
            PARAMETERKEY_DESCRIPTOR = FieldDescriptor.of("parameterKey");
            PARAMETERVALUE_DESCRIPTOR = FieldDescriptor.of("parameterValue");
            TEMPLATEVERSION_DESCRIPTOR = FieldDescriptor.of("templateVersion");
        }

        private CrashlyticsReportSessionEventRolloutAssignmentEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.RolloutAssignment r3, ObjectEncoderContext r4) {
            r4.add(ROLLOUTVARIANT_DESCRIPTOR, r3.getRolloutVariant());
            r4.add(PARAMETERKEY_DESCRIPTOR, r3.getParameterKey());
            r4.add(PARAMETERVALUE_DESCRIPTOR, r3.getParameterValue());
            r4.add(TEMPLATEVERSION_DESCRIPTOR, r3.getTemplateVersion());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.RolloutAssignment) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant> {
        static final CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder INSTANCE = null;
        private static final FieldDescriptor ROLLOUTID_DESCRIPTOR = null;
        private static final FieldDescriptor VARIANTID_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder();
            ROLLOUTID_DESCRIPTOR = FieldDescriptor.of("rolloutId");
            VARIANTID_DESCRIPTOR = FieldDescriptor.of("variantId");
        }

        private CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant r2, ObjectEncoderContext r3) {
            r3.add(ROLLOUTID_DESCRIPTOR, r2.getRolloutId());
            r3.add(VARIANTID_DESCRIPTOR, r2.getVariantId());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionEventRolloutsStateEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutsState> {
        private static final FieldDescriptor ASSIGNMENTS_DESCRIPTOR = null;
        static final CrashlyticsReportSessionEventRolloutsStateEncoder INSTANCE = null;

        static {
            INSTANCE = new CrashlyticsReportSessionEventRolloutsStateEncoder();
            ASSIGNMENTS_DESCRIPTOR = FieldDescriptor.of("assignments");
        }

        private CrashlyticsReportSessionEventRolloutsStateEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.RolloutsState r1, ObjectEncoderContext r2) {
            r2.add(ASSIGNMENTS_DESCRIPTOR, r1.getRolloutAssignments());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.Event.RolloutsState) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionOperatingSystemEncoder implements ObjectEncoder<CrashlyticsReport.Session.OperatingSystem> {
        private static final FieldDescriptor BUILDVERSION_DESCRIPTOR = null;
        static final CrashlyticsReportSessionOperatingSystemEncoder INSTANCE = null;
        private static final FieldDescriptor JAILBROKEN_DESCRIPTOR = null;
        private static final FieldDescriptor PLATFORM_DESCRIPTOR = null;
        private static final FieldDescriptor VERSION_DESCRIPTOR = null;

        static {
            INSTANCE = new CrashlyticsReportSessionOperatingSystemEncoder();
            PLATFORM_DESCRIPTOR = FieldDescriptor.of("platform");
            VERSION_DESCRIPTOR = FieldDescriptor.of("version");
            BUILDVERSION_DESCRIPTOR = FieldDescriptor.of("buildVersion");
            JAILBROKEN_DESCRIPTOR = FieldDescriptor.of("jailbroken");
        }

        private CrashlyticsReportSessionOperatingSystemEncoder() {
        }

        public void encode(CrashlyticsReport.Session.OperatingSystem r2, ObjectEncoderContext r3) {
            r3.add(PLATFORM_DESCRIPTOR, r2.getPlatform());
            r3.add(VERSION_DESCRIPTOR, r2.getVersion());
            r3.add(BUILDVERSION_DESCRIPTOR, r2.getBuildVersion());
            r3.add(JAILBROKEN_DESCRIPTOR, r2.isJailbroken());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.OperatingSystem) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class CrashlyticsReportSessionUserEncoder implements ObjectEncoder<CrashlyticsReport.Session.User> {
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = null;
        static final CrashlyticsReportSessionUserEncoder INSTANCE = null;

        static {
            INSTANCE = new CrashlyticsReportSessionUserEncoder();
            IDENTIFIER_DESCRIPTOR = FieldDescriptor.of("identifier");
        }

        private CrashlyticsReportSessionUserEncoder() {
        }

        public void encode(CrashlyticsReport.Session.User r1, ObjectEncoderContext r2) {
            r2.add(IDENTIFIER_DESCRIPTOR, r1.getIdentifier());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((CrashlyticsReport.Session.User) r1, (ObjectEncoderContext) r2);
        }
    }

    static {
        CONFIG = new AutoCrashlyticsReportEncoder();
    }

    private AutoCrashlyticsReportEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> r2) {
        CrashlyticsReportEncoder r1 = CrashlyticsReportEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.class, r1);
        r2.registerEncoder(AutoValue_CrashlyticsReport.class, r1);
        CrashlyticsReportSessionEncoder r12 = CrashlyticsReportSessionEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.class, r12);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session.class, r12);
        CrashlyticsReportSessionApplicationEncoder r13 = CrashlyticsReportSessionApplicationEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Application.class, r13);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Application.class, r13);
        CrashlyticsReportSessionApplicationOrganizationEncoder r14 = CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Application.Organization.class, r14);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Application_Organization.class, r14);
        CrashlyticsReportSessionUserEncoder r15 = CrashlyticsReportSessionUserEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.User.class, r15);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_User.class, r15);
        CrashlyticsReportSessionOperatingSystemEncoder r16 = CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.OperatingSystem.class, r16);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_OperatingSystem.class, r16);
        CrashlyticsReportSessionDeviceEncoder r17 = CrashlyticsReportSessionDeviceEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Device.class, r17);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Device.class, r17);
        CrashlyticsReportSessionEventEncoder r18 = CrashlyticsReportSessionEventEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.class, r18);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event.class, r18);
        CrashlyticsReportSessionEventApplicationEncoder r19 = CrashlyticsReportSessionEventApplicationEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Application.class, r19);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application.class, r19);
        CrashlyticsReportSessionEventApplicationExecutionEncoder r110 = CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.class, r110);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution.class, r110);
        CrashlyticsReportSessionEventApplicationExecutionThreadEncoder r111 = CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.class, r111);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread.class, r111);
        CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder r112 = CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.class, r112);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame.class, r112);
        CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder r113 = CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Exception.class, r113);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception.class, r113);
        CrashlyticsReportApplicationExitInfoEncoder r114 = CrashlyticsReportApplicationExitInfoEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.ApplicationExitInfo.class, r114);
        r2.registerEncoder(AutoValue_CrashlyticsReport_ApplicationExitInfo.class, r114);
        CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder r115 = CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.class, r115);
        r2.registerEncoder(AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch.class, r115);
        CrashlyticsReportProfilingManagerInfoEncoder r116 = CrashlyticsReportProfilingManagerInfoEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.ProfilingManagerInfo.class, r116);
        r2.registerEncoder(AutoValue_CrashlyticsReport_ProfilingManagerInfo.class, r116);
        CrashlyticsReportProfilingManagerInfoProfilingTriggerEncoder r117 = CrashlyticsReportProfilingManagerInfoProfilingTriggerEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger.class, r117);
        r2.registerEncoder(AutoValue_CrashlyticsReport_ProfilingManagerInfo_ProfilingTrigger.class, r117);
        CrashlyticsReportSessionEventApplicationExecutionSignalEncoder r118 = CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Signal.class, r118);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal.class, r118);
        CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder r119 = CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.class, r119);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage.class, r119);
        CrashlyticsReportCustomAttributeEncoder r120 = CrashlyticsReportCustomAttributeEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.CustomAttribute.class, r120);
        r2.registerEncoder(AutoValue_CrashlyticsReport_CustomAttribute.class, r120);
        CrashlyticsReportSessionEventApplicationProcessDetailsEncoder r121 = CrashlyticsReportSessionEventApplicationProcessDetailsEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Application.ProcessDetails.class, r121);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails.class, r121);
        CrashlyticsReportSessionEventDeviceEncoder r122 = CrashlyticsReportSessionEventDeviceEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Device.class, r122);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Device.class, r122);
        CrashlyticsReportSessionEventLogEncoder r123 = CrashlyticsReportSessionEventLogEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.Log.class, r123);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Log.class, r123);
        CrashlyticsReportSessionEventRolloutsStateEncoder r124 = CrashlyticsReportSessionEventRolloutsStateEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.RolloutsState.class, r124);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_RolloutsState.class, r124);
        CrashlyticsReportSessionEventRolloutAssignmentEncoder r125 = CrashlyticsReportSessionEventRolloutAssignmentEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.RolloutAssignment.class, r125);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment.class, r125);
        CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder r126 = CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.class, r126);
        r2.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant.class, r126);
        CrashlyticsReportFilesPayloadEncoder r127 = CrashlyticsReportFilesPayloadEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.FilesPayload.class, r127);
        r2.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload.class, r127);
        CrashlyticsReportFilesPayloadFileEncoder r128 = CrashlyticsReportFilesPayloadFileEncoder.INSTANCE;
        r2.registerEncoder(CrashlyticsReport.FilesPayload.File.class, r128);
        r2.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload_File.class, r128);
    }
}
