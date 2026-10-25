package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_ApplicationExitInfo;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_CustomAttribute;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload_File;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_ProfilingManagerInfo;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_ProfilingManagerInfo_ProfilingTrigger;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Application;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Device;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Device;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Log;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_RolloutsState;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_OperatingSystem;
import com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_User;
import java.nio.charset.Charset;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class CrashlyticsReport {
    private static final Charset UTF_8 = null;

    public static abstract class ApplicationExitInfo {

        public static abstract class BuildIdMappingForArch {

            /* loaded from: classes5.dex */
            public static abstract class Builder {
                public Builder() {
                }

                public abstract BuildIdMappingForArch build();

                public abstract Builder setArch(String r1);

                public abstract Builder setBuildId(String r1);

                public abstract Builder setLibraryName(String r1);
            }

            public BuildIdMappingForArch() {
            }

            public static Builder builder() {
                return new AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch.Builder();
            }

            public abstract String getArch();

            public abstract String getBuildId();

            public abstract String getLibraryName();
        }

        /* loaded from: classes5.dex */
        public static abstract class Builder {
            public Builder() {
            }

            public abstract ApplicationExitInfo build();

            public abstract Builder setBuildIdMappingForArch(List<BuildIdMappingForArch> r1);

            public abstract Builder setImportance(int r1);

            public abstract Builder setPid(int r1);

            public abstract Builder setProcessName(String r1);

            public abstract Builder setPss(long r1);

            public abstract Builder setReasonCode(int r1);

            public abstract Builder setRss(long r1);

            public abstract Builder setTimestamp(long r1);

            public abstract Builder setTraceFile(String r1);
        }

        public ApplicationExitInfo() {
        }

        public static Builder builder() {
            return new AutoValue_CrashlyticsReport_ApplicationExitInfo.Builder();
        }

        public abstract List<BuildIdMappingForArch> getBuildIdMappingForArch();

        public abstract int getImportance();

        public abstract int getPid();

        public abstract String getProcessName();

        public abstract long getPss();

        public abstract int getReasonCode();

        public abstract long getRss();

        public abstract long getTimestamp();

        public abstract String getTraceFile();
    }

    public static abstract class Builder {
        public Builder() {
        }

        public abstract CrashlyticsReport build();

        public abstract Builder setAppExitInfo(ApplicationExitInfo r1);

        public abstract Builder setAppQualitySessionId(String r1);

        public abstract Builder setBuildVersion(String r1);

        public abstract Builder setDisplayVersion(String r1);

        public abstract Builder setFirebaseAuthenticationToken(String r1);

        public abstract Builder setFirebaseInstallationId(String r1);

        public abstract Builder setGmpAppId(String r1);

        public abstract Builder setInstallationUuid(String r1);

        public abstract Builder setNdkPayload(FilesPayload r1);

        public abstract Builder setPlatform(int r1);

        public abstract Builder setSdkVersion(String r1);

        public abstract Builder setSession(Session r1);
    }

    public static abstract class CustomAttribute {

        /* loaded from: classes5.dex */
        public static abstract class Builder {
            public Builder() {
            }

            public abstract CustomAttribute build();

            public abstract Builder setKey(String r1);

            public abstract Builder setValue(String r1);
        }

        public CustomAttribute() {
        }

        public static Builder builder() {
            return new AutoValue_CrashlyticsReport_CustomAttribute.Builder();
        }

        public abstract String getKey();

        public abstract String getValue();
    }

    public static abstract class FilesPayload {

        /* loaded from: classes5.dex */
        public static abstract class Builder {
            public Builder() {
            }

            public abstract FilesPayload build();

            public abstract Builder setFiles(List<File> r1);

            public abstract Builder setOrgId(String r1);
        }

        public static abstract class File {

            /* loaded from: classes5.dex */
            public static abstract class Builder {
                public Builder() {
                }

                public abstract File build();

                public abstract Builder setContents(byte[] r1);

                public abstract Builder setFilename(String r1);
            }

            public File() {
            }

            public static Builder builder() {
                return new AutoValue_CrashlyticsReport_FilesPayload_File.Builder();
            }

            public abstract byte[] getContents();

            public abstract String getFilename();
        }

        public FilesPayload() {
        }

        public static Builder builder() {
            return new AutoValue_CrashlyticsReport_FilesPayload.Builder();
        }

        public abstract List<File> getFiles();

        public abstract String getOrgId();
    }

    /* loaded from: classes5.dex */
    public static abstract class ProfilingManagerInfo {

        public static abstract class Builder {
            public Builder() {
            }

            public abstract ProfilingManagerInfo build();

            public abstract Builder setProfilingTrigger(ProfilingTrigger r1);
        }

        public static abstract class ProfilingTrigger {

            public static abstract class Builder {
                public Builder() {
                }

                public abstract ProfilingTrigger build();

                public abstract Builder setTrigger(int r1);
            }

            public ProfilingTrigger() {
            }

            public static Builder builder() {
                return new AutoValue_CrashlyticsReport_ProfilingManagerInfo_ProfilingTrigger.Builder();
            }

            public abstract int getTrigger();
        }

        public ProfilingManagerInfo() {
        }

        public static Builder builder() {
            return new AutoValue_CrashlyticsReport_ProfilingManagerInfo.Builder();
        }

        public abstract ProfilingTrigger getProfilingTrigger();
    }

    public static abstract class Session {

        public static abstract class Application {

            public static abstract class Builder {
                public Builder() {
                }

                public abstract Application build();

                public abstract Builder setDevelopmentPlatform(String r1);

                public abstract Builder setDevelopmentPlatformVersion(String r1);

                public abstract Builder setDisplayVersion(String r1);

                public abstract Builder setIdentifier(String r1);

                public abstract Builder setInstallationUuid(String r1);

                public abstract Builder setVersion(String r1);
            }

            public static abstract class Organization {
            }

            public Application() {
            }

            public static Builder builder() {
                return new AutoValue_CrashlyticsReport_Session_Application.Builder();
            }

            public abstract String getDevelopmentPlatform();

            public abstract String getDevelopmentPlatformVersion();

            public abstract String getDisplayVersion();

            public abstract String getIdentifier();

            public abstract String getInstallationUuid();

            public abstract Organization getOrganization();

            public abstract String getVersion();
        }

        public static abstract class Builder {
            public Builder() {
            }

            public abstract Session build();

            public abstract Builder setApp(Application r1);

            public abstract Builder setAppQualitySessionId(String r1);

            public abstract Builder setCrashed(boolean r1);

            public abstract Builder setDevice(Device r1);

            public abstract Builder setEndedAt(Long r1);

            public abstract Builder setEvents(List<Event> r1);

            public abstract Builder setGenerator(String r1);

            public abstract Builder setGeneratorType(int r1);

            public abstract Builder setIdentifier(String r1);

            public Builder setIdentifierFromUtf8Bytes(byte[] r3) {
                return setIdentifier(new String(r3, CrashlyticsReport.access$000()));
            }

            public abstract Builder setOs(OperatingSystem r1);

            public abstract Builder setStartedAt(long r1);

            public abstract Builder setUser(User r1);
        }

        public static abstract class Device {

            public static abstract class Builder {
                public Builder() {
                }

                public abstract Device build();

                public abstract Builder setArch(int r1);

                public abstract Builder setCores(int r1);

                public abstract Builder setDiskSpace(long r1);

                public abstract Builder setManufacturer(String r1);

                public abstract Builder setModel(String r1);

                public abstract Builder setModelClass(String r1);

                public abstract Builder setRam(long r1);

                public abstract Builder setSimulator(boolean r1);

                public abstract Builder setState(int r1);
            }

            public Device() {
            }

            public static Builder builder() {
                return new AutoValue_CrashlyticsReport_Session_Device.Builder();
            }

            public abstract int getArch();

            public abstract int getCores();

            public abstract long getDiskSpace();

            public abstract String getManufacturer();

            public abstract String getModel();

            public abstract String getModelClass();

            public abstract long getRam();

            public abstract int getState();

            public abstract boolean isSimulator();
        }

        public static abstract class Event {

            public static abstract class Application {

                /* loaded from: classes5.dex */
                public static abstract class Builder {
                    public Builder() {
                    }

                    public abstract Application build();

                    public abstract Builder setAppProcessDetails(List<ProcessDetails> r1);

                    public abstract Builder setBackground(Boolean r1);

                    public abstract Builder setCurrentProcessDetails(ProcessDetails r1);

                    public abstract Builder setCustomAttributes(List<CustomAttribute> r1);

                    public abstract Builder setExecution(Execution r1);

                    public abstract Builder setInternalKeys(List<CustomAttribute> r1);

                    public abstract Builder setUiOrientation(int r1);
                }

                public static abstract class Execution {

                    public static abstract class BinaryImage {

                        /* loaded from: classes5.dex */
                        public static abstract class Builder {
                            public Builder() {
                            }

                            public abstract BinaryImage build();

                            public abstract Builder setBaseAddress(long r1);

                            public abstract Builder setName(String r1);

                            public abstract Builder setSize(long r1);

                            public abstract Builder setUuid(String r1);

                            public Builder setUuidFromUtf8Bytes(byte[] r3) {
                                return setUuid(new String(r3, CrashlyticsReport.access$000()));
                            }
                        }

                        public BinaryImage() {
                        }

                        public static Builder builder() {
                            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage.Builder();
                        }

                        public abstract long getBaseAddress();

                        public abstract String getName();

                        public abstract long getSize();

                        public abstract String getUuid();

                        public byte[] getUuidUtf8Bytes() {
                            String r1 = getUuid();
                            if (r1 != null) goto L5;
                            return null;
                        L5:
                            return r1.getBytes(CrashlyticsReport.access$000());
                        }
                    }

                    /* loaded from: classes5.dex */
                    public static abstract class Builder {
                        public Builder() {
                        }

                        public abstract Execution build();

                        public abstract Builder setAppExitInfo(ApplicationExitInfo r1);

                        public abstract Builder setBinaries(List<BinaryImage> r1);

                        public abstract Builder setException(Exception r1);

                        public abstract Builder setProfilingManagerInfo(ProfilingManagerInfo r1);

                        public abstract Builder setSignal(Signal r1);

                        public abstract Builder setThreads(List<Thread> r1);
                    }

                    public static abstract class Exception {

                        /* loaded from: classes5.dex */
                        public static abstract class Builder {
                            public Builder() {
                            }

                            public abstract Exception build();

                            public abstract Builder setCausedBy(Exception r1);

                            public abstract Builder setFrames(List<Thread.Frame> r1);

                            public abstract Builder setOverflowCount(int r1);

                            public abstract Builder setReason(String r1);

                            public abstract Builder setType(String r1);
                        }

                        public Exception() {
                        }

                        public static Builder builder() {
                            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception.Builder();
                        }

                        public abstract Exception getCausedBy();

                        public abstract List<Thread.Frame> getFrames();

                        public abstract int getOverflowCount();

                        public abstract String getReason();

                        public abstract String getType();
                    }

                    public static abstract class Signal {

                        /* loaded from: classes5.dex */
                        public static abstract class Builder {
                            public Builder() {
                            }

                            public abstract Signal build();

                            public abstract Builder setAddress(long r1);

                            public abstract Builder setCode(String r1);

                            public abstract Builder setName(String r1);
                        }

                        public Signal() {
                        }

                        public static Builder builder() {
                            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal.Builder();
                        }

                        public abstract long getAddress();

                        public abstract String getCode();

                        public abstract String getName();
                    }

                    public static abstract class Thread {

                        /* loaded from: classes5.dex */
                        public static abstract class Builder {
                            public Builder() {
                            }

                            public abstract Thread build();

                            public abstract Builder setFrames(List<Frame> r1);

                            public abstract Builder setImportance(int r1);

                            public abstract Builder setName(String r1);
                        }

                        public static abstract class Frame {

                            /* loaded from: classes5.dex */
                            public static abstract class Builder {
                                public Builder() {
                                }

                                public abstract Frame build();

                                public abstract Builder setFile(String r1);

                                public abstract Builder setImportance(int r1);

                                public abstract Builder setOffset(long r1);

                                public abstract Builder setPc(long r1);

                                public abstract Builder setSymbol(String r1);
                            }

                            public Frame() {
                            }

                            public static Builder builder() {
                                return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame.Builder();
                            }

                            public abstract String getFile();

                            public abstract int getImportance();

                            public abstract long getOffset();

                            public abstract long getPc();

                            public abstract String getSymbol();
                        }

                        public Thread() {
                        }

                        public static Builder builder() {
                            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread.Builder();
                        }

                        public abstract List<Frame> getFrames();

                        public abstract int getImportance();

                        public abstract String getName();
                    }

                    public Execution() {
                    }

                    public static Builder builder() {
                        return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution.Builder();
                    }

                    public abstract ApplicationExitInfo getAppExitInfo();

                    public abstract List<BinaryImage> getBinaries();

                    public abstract Exception getException();

                    public abstract ProfilingManagerInfo getProfilingManagerInfo();

                    public abstract Signal getSignal();

                    public abstract List<Thread> getThreads();

                    public abstract Builder toBuilder();
                }

                public static abstract class ProcessDetails {

                    public static abstract class Builder {
                        public Builder() {
                        }

                        public abstract ProcessDetails build();

                        public abstract Builder setDefaultProcess(boolean r1);

                        public abstract Builder setImportance(int r1);

                        public abstract Builder setPid(int r1);

                        public abstract Builder setProcessName(String r1);
                    }

                    public ProcessDetails() {
                    }

                    public static Builder builder() {
                        return new AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails.Builder();
                    }

                    public abstract int getImportance();

                    public abstract int getPid();

                    public abstract String getProcessName();

                    public abstract boolean isDefaultProcess();
                }

                public Application() {
                }

                public static Builder builder() {
                    return new AutoValue_CrashlyticsReport_Session_Event_Application.Builder();
                }

                public abstract List<ProcessDetails> getAppProcessDetails();

                public abstract Boolean getBackground();

                public abstract ProcessDetails getCurrentProcessDetails();

                public abstract List<CustomAttribute> getCustomAttributes();

                public abstract Execution getExecution();

                public abstract List<CustomAttribute> getInternalKeys();

                public abstract int getUiOrientation();

                public abstract Builder toBuilder();
            }

            /* loaded from: classes5.dex */
            public static abstract class Builder {
                public Builder() {
                }

                public abstract Event build();

                public abstract Builder setApp(Application r1);

                public abstract Builder setDevice(Device r1);

                public abstract Builder setLog(Log r1);

                public abstract Builder setRollouts(RolloutsState r1);

                public abstract Builder setTimestamp(long r1);

                public abstract Builder setType(String r1);
            }

            public static abstract class Device {

                /* loaded from: classes5.dex */
                public static abstract class Builder {
                    public Builder() {
                    }

                    public abstract Device build();

                    public abstract Builder setBatteryLevel(Double r1);

                    public abstract Builder setBatteryVelocity(int r1);

                    public abstract Builder setDiskUsed(long r1);

                    public abstract Builder setOrientation(int r1);

                    public abstract Builder setProximityOn(boolean r1);

                    public abstract Builder setRamUsed(long r1);
                }

                public Device() {
                }

                public static Builder builder() {
                    return new AutoValue_CrashlyticsReport_Session_Event_Device.Builder();
                }

                public abstract Double getBatteryLevel();

                public abstract int getBatteryVelocity();

                public abstract long getDiskUsed();

                public abstract int getOrientation();

                public abstract long getRamUsed();

                public abstract boolean isProximityOn();
            }

            public static abstract class Log {

                /* loaded from: classes5.dex */
                public static abstract class Builder {
                    public Builder() {
                    }

                    public abstract Log build();

                    public abstract Builder setContent(String r1);
                }

                public Log() {
                }

                public static Builder builder() {
                    return new AutoValue_CrashlyticsReport_Session_Event_Log.Builder();
                }

                public abstract String getContent();
            }

            public static abstract class RolloutAssignment {

                /* loaded from: classes5.dex */
                public static abstract class Builder {
                    public Builder() {
                    }

                    public abstract RolloutAssignment build();

                    public abstract Builder setParameterKey(String r1);

                    public abstract Builder setParameterValue(String r1);

                    public abstract Builder setRolloutVariant(RolloutVariant r1);

                    public abstract Builder setTemplateVersion(long r1);
                }

                public static abstract class RolloutVariant {

                    /* loaded from: classes5.dex */
                    public static abstract class Builder {
                        public Builder() {
                        }

                        public abstract RolloutVariant build();

                        public abstract Builder setRolloutId(String r1);

                        public abstract Builder setVariantId(String r1);
                    }

                    public RolloutVariant() {
                    }

                    public static Builder builder() {
                        return new AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant.Builder();
                    }

                    public abstract String getRolloutId();

                    public abstract String getVariantId();
                }

                public RolloutAssignment() {
                }

                public static Builder builder() {
                    return new AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment.Builder();
                }

                public abstract String getParameterKey();

                public abstract String getParameterValue();

                public abstract RolloutVariant getRolloutVariant();

                public abstract long getTemplateVersion();
            }

            public static abstract class RolloutsState {

                /* loaded from: classes5.dex */
                public static abstract class Builder {
                    public Builder() {
                    }

                    public abstract RolloutsState build();

                    public abstract Builder setRolloutAssignments(List<RolloutAssignment> r1);
                }

                public RolloutsState() {
                }

                public static Builder builder() {
                    return new AutoValue_CrashlyticsReport_Session_Event_RolloutsState.Builder();
                }

                public abstract List<RolloutAssignment> getRolloutAssignments();
            }

            public Event() {
            }

            public static Builder builder() {
                return new AutoValue_CrashlyticsReport_Session_Event.Builder();
            }

            public abstract Application getApp();

            public abstract Device getDevice();

            public abstract Log getLog();

            public abstract RolloutsState getRollouts();

            public abstract long getTimestamp();

            public abstract String getType();

            public abstract Builder toBuilder();
        }

        public static abstract class OperatingSystem {

            public static abstract class Builder {
                public Builder() {
                }

                public abstract OperatingSystem build();

                public abstract Builder setBuildVersion(String r1);

                public abstract Builder setJailbroken(boolean r1);

                public abstract Builder setPlatform(int r1);

                public abstract Builder setVersion(String r1);
            }

            public OperatingSystem() {
            }

            public static Builder builder() {
                return new AutoValue_CrashlyticsReport_Session_OperatingSystem.Builder();
            }

            public abstract String getBuildVersion();

            public abstract int getPlatform();

            public abstract String getVersion();

            public abstract boolean isJailbroken();
        }

        public static abstract class User {

            /* loaded from: classes5.dex */
            public static abstract class Builder {
                public Builder() {
                }

                public abstract User build();

                public abstract Builder setIdentifier(String r1);
            }

            public User() {
            }

            public static Builder builder() {
                return new AutoValue_CrashlyticsReport_Session_User.Builder();
            }

            public abstract String getIdentifier();
        }

        public Session() {
        }

        public static Builder builder() {
            return new AutoValue_CrashlyticsReport_Session.Builder().setCrashed(false);
        }

        public abstract Application getApp();

        public abstract String getAppQualitySessionId();

        public abstract Device getDevice();

        public abstract Long getEndedAt();

        public abstract List<Event> getEvents();

        public abstract String getGenerator();

        public abstract int getGeneratorType();

        public abstract String getIdentifier();

        public byte[] getIdentifierUtf8Bytes() {
            return getIdentifier().getBytes(CrashlyticsReport.access$000());
        }

        public abstract OperatingSystem getOs();

        public abstract long getStartedAt();

        public abstract User getUser();

        public abstract boolean isCrashed();

        public abstract Builder toBuilder();

        public Session withAppQualitySessionId(String r1) {
            return toBuilder().setAppQualitySessionId(r1).build();
        }

        public Session withEvents(List<Event> r1) {
            return toBuilder().setEvents(r1).build();
        }

        public Session withSessionEndFields(long r1, boolean r3, String r4) {
            Builder r0 = toBuilder();
            r0.setEndedAt(Long.valueOf(r1));
            r0.setCrashed(r3);
            if (r4 == null) goto L6;
            r0.setUser(User.builder().setIdentifier(r4).build());
        L6:
            return r0.build();
        }
    }

    static {
        UTF_8 = Charset.forName("UTF-8");
    }

    public CrashlyticsReport() {
    }

    public static /* synthetic */ Charset access$000() {
        return UTF_8;
    }

    public static Builder builder() {
        return new AutoValue_CrashlyticsReport.Builder();
    }

    public abstract ApplicationExitInfo getAppExitInfo();

    public abstract String getAppQualitySessionId();

    public abstract String getBuildVersion();

    public abstract String getDisplayVersion();

    public abstract String getFirebaseAuthenticationToken();

    public abstract String getFirebaseInstallationId();

    public abstract String getGmpAppId();

    public abstract String getInstallationUuid();

    public abstract FilesPayload getNdkPayload();

    public abstract int getPlatform();

    public abstract String getSdkVersion();

    public abstract Session getSession();

    public abstract Builder toBuilder();

    public CrashlyticsReport withAppQualitySessionId(String r3) {
        Builder r0 = toBuilder().setAppQualitySessionId(r3);
        if (getSession() == null) goto L6;
        r0.setSession(getSession().withAppQualitySessionId(r3));
    L6:
        return r0.build();
    }

    public CrashlyticsReport withApplicationExitInfo(ApplicationExitInfo r1) {
        if (r1 != null) goto L5;
        return this;
    L5:
        return toBuilder().setAppExitInfo(r1).build();
    }

    public CrashlyticsReport withEvents(List<Session.Event> r2) {
        if (getSession() != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Reports without sessions cannot have events added to them.");
        return null;
    L5:
        return toBuilder().setSession(getSession().withEvents(r2)).build();
    }

    public CrashlyticsReport withFirebaseAuthenticationToken(String r1) {
        return toBuilder().setFirebaseAuthenticationToken(r1).build();
    }

    public CrashlyticsReport withFirebaseInstallationId(String r1) {
        return toBuilder().setFirebaseInstallationId(r1).build();
    }

    public CrashlyticsReport withNdkPayload(FilesPayload r2) {
        return toBuilder().setSession(null).setNdkPayload(r2).build();
    }

    public CrashlyticsReport withSessionEndFields(long r3, boolean r5, String r6) {
        Builder r0 = toBuilder();
        if (getSession() == null) goto L6;
        r0.setSession(getSession().withSessionEndFields(r3, r5, r6));
    L6:
        return r0.build();
    }
}
