package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch extends CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch {
    private final String arch;
    private final String buildId;
    private final String libraryName;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder {
        private String arch;
        private String buildId;
        private String libraryName;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder
        public CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch build() {
            String r0 = this.arch;
            if (r0 == null) goto L11;
            String r1 = this.libraryName;
            if (r1 == null) goto L11;
            String r2 = this.buildId;
            if (r2 == null) goto L11;
            return new AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch(r0, r1, r2, null);
        L11:
            StringBuilder r02 = new StringBuilder();
            if (this.arch != null) goto L15;
            r02.append(" arch");
        L15:
            if (this.libraryName != null) goto L18;
            r02.append(" libraryName");
        L18:
            if (this.buildId != null) goto L20;
            r02.append(" buildId");
        L20:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r02);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder
        public CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder setArch(String r1) {
            if (r1 == null) goto L5;
            this.arch = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null arch");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder
        public CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder setBuildId(String r1) {
            if (r1 == null) goto L5;
            this.buildId = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null buildId");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder
        public CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder setLibraryName(String r1) {
            if (r1 == null) goto L5;
            this.libraryName = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null libraryName");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch(String r1, String r2, String r3) {
        this.arch = r1;
        this.libraryName = r2;
        this.buildId = r3;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch(String r1, String r2, String r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch) == false) goto L14;
        CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch r52 = (CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch) r5;
        if (this.arch.equals(r52.getArch()) == false) goto L14;
        if (this.libraryName.equals(r52.getLibraryName()) == false) goto L14;
        if (this.buildId.equals(r52.getBuildId()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch
    public String getArch() {
        return this.arch;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch
    public String getBuildId() {
        return this.buildId;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch
    public String getLibraryName() {
        return this.libraryName;
    }

    public int hashCode() {
        int r0 = (((this.arch.hashCode() ^ 1000003) * 1000003) ^ this.libraryName.hashCode()) * 1000003;
        return this.buildId.hashCode() ^ r0;
    }

    public String toString() {
        return "BuildIdMappingForArch{arch=" + this.arch + ", libraryName=" + this.libraryName + ", buildId=" + this.buildId + "}";
    }
}
