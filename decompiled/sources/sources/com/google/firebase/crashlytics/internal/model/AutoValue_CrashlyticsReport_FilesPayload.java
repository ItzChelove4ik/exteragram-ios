package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_FilesPayload extends CrashlyticsReport.FilesPayload {
    private final List<CrashlyticsReport.FilesPayload.File> files;
    private final String orgId;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.FilesPayload.Builder {
        private List<CrashlyticsReport.FilesPayload.File> files;
        private String orgId;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder
        public CrashlyticsReport.FilesPayload build() {
            List<CrashlyticsReport.FilesPayload.File> r0 = this.files;
            if (r0 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: files");
            return null;
        L5:
            return new AutoValue_CrashlyticsReport_FilesPayload(r0, this.orgId, null);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder
        public CrashlyticsReport.FilesPayload.Builder setFiles(List<CrashlyticsReport.FilesPayload.File> r1) {
            if (r1 == null) goto L5;
            this.files = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null files");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder
        public CrashlyticsReport.FilesPayload.Builder setOrgId(String r1) {
            this.orgId = r1;
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_FilesPayload(List<CrashlyticsReport.FilesPayload.File> r1, String r2) {
        this.files = r1;
        this.orgId = r2;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_FilesPayload(List r1, String r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.FilesPayload) == false) goto L17;
        CrashlyticsReport.FilesPayload r52 = (CrashlyticsReport.FilesPayload) r5;
        if (this.files.equals(r52.getFiles()) == false) goto L17;
        String r4 = this.orgId;
        if (r4 != null) goto L15;
        if (r52.getOrgId() != null) goto L17;
    L16:
        return true;
    L15:
        if (r4.equals(r52.getOrgId()) == true) goto L16;
    L17:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload
    public List<CrashlyticsReport.FilesPayload.File> getFiles() {
        return this.files;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload
    public String getOrgId() {
        return this.orgId;
    }

    public int hashCode() {
        int r0 = (this.files.hashCode() ^ 1000003) * 1000003;
        String r2 = this.orgId;
        if (r2 != null) goto L5;
        int r22 = 0;
    L7:
        return r22 ^ r0;
    L5:
        r22 = r2.hashCode();
        goto L7
    }

    public String toString() {
        return "FilesPayload{files=" + this.files + ", orgId=" + this.orgId + "}";
    }
}
