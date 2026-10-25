package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.DevelopmentPlatformProvider;

/* loaded from: classes.dex */
public abstract class StaticSessionData {

    public static abstract class AppData {
        public AppData() {
        }

        public static AppData create(String r7, String r8, String r9, String r10, int r11, DevelopmentPlatformProvider r12) {
            return new AutoValue_StaticSessionData_AppData(r7, r8, r9, r10, r11, r12);
        }

        public abstract String appIdentifier();

        public abstract int deliveryMechanism();

        public abstract DevelopmentPlatformProvider developmentPlatformProvider();

        public abstract String installUuid();

        public abstract String versionCode();

        public abstract String versionName();
    }

    public static abstract class DeviceData {
        public DeviceData() {
        }

        public static DeviceData create(int r12, String r13, int r14, long r15, long r17, boolean r19, int r20, String r21, String r22) {
            return new AutoValue_StaticSessionData_DeviceData(r12, r13, r14, r15, r17, r19, r20, r21, r22);
        }

        public abstract int arch();

        public abstract int availableProcessors();

        public abstract long diskSpace();

        public abstract boolean isEmulator();

        public abstract String manufacturer();

        public abstract String model();

        public abstract String modelClass();

        public abstract int state();

        public abstract long totalRam();
    }

    public static abstract class OsData {
        public OsData() {
        }

        public static OsData create(String r1, String r2, boolean r3) {
            return new AutoValue_StaticSessionData_OsData(r1, r2, r3);
        }

        public abstract boolean isRooted();

        public abstract String osCodeName();

        public abstract String osRelease();
    }

    public StaticSessionData() {
    }

    public static StaticSessionData create(AppData r1, OsData r2, DeviceData r3) {
        return new AutoValue_StaticSessionData(r1, r2, r3);
    }

    public abstract AppData appData();

    public abstract DeviceData deviceData();

    public abstract OsData osData();
}
