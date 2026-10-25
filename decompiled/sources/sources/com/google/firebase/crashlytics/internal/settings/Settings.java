package com.google.firebase.crashlytics.internal.settings;

/* loaded from: classes.dex */
public class Settings {
    public final int cacheDuration;
    public final long expiresAtMillis;
    public final FeatureFlagData featureFlagData;
    public final double onDemandBackoffBase;
    public final int onDemandBackoffStepDurationSeconds;
    public final double onDemandUploadRatePerMinute;
    public final SessionData sessionData;
    public final int settingsVersion;

    public static class FeatureFlagData {
        public final boolean collectAnrs;
        public final boolean collectBuildIds;
        public final boolean collectReports;

        public FeatureFlagData(boolean r1, boolean r2, boolean r3) {
            this.collectReports = r1;
            this.collectAnrs = r2;
            this.collectBuildIds = r3;
        }
    }

    public static class SessionData {
        public final int maxCompleteSessionsCount;
        public final int maxCustomExceptionEvents;

        public SessionData(int r1, int r2) {
            this.maxCustomExceptionEvents = r1;
            this.maxCompleteSessionsCount = r2;
        }
    }

    public Settings(long r1, SessionData r3, FeatureFlagData r4, int r5, int r6, double r7, double r9, int r11) {
        this.expiresAtMillis = r1;
        this.sessionData = r3;
        this.featureFlagData = r4;
        this.settingsVersion = r5;
        this.cacheDuration = r6;
        this.onDemandUploadRatePerMinute = r7;
        this.onDemandBackoffBase = r9;
        this.onDemandBackoffStepDurationSeconds = r11;
    }

    public boolean isExpired(long r3) {
        if (this.expiresAtMillis >= r3) goto L6;
        return true;
    L6:
        return false;
    }
}
