package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Arrays;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_FilesPayload_File extends CrashlyticsReport.FilesPayload.File {
    private final byte[] contents;
    private final String filename;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_FilesPayload_File$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.FilesPayload.File.Builder {
        private byte[] contents;
        private String filename;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder
        public CrashlyticsReport.FilesPayload.File build() {
            String r0 = this.filename;
            if (r0 == null) goto L9;
            byte[] r1 = this.contents;
            if (r1 == null) goto L9;
            return new AutoValue_CrashlyticsReport_FilesPayload_File(r0, r1, null);
        L9:
            StringBuilder r02 = new StringBuilder();
            if (this.filename != null) goto L13;
            r02.append(" filename");
        L13:
            if (this.contents != null) goto L15;
            r02.append(" contents");
        L15:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r02);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder
        public CrashlyticsReport.FilesPayload.File.Builder setContents(byte[] r1) {
            if (r1 == null) goto L5;
            this.contents = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null contents");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder
        public CrashlyticsReport.FilesPayload.File.Builder setFilename(String r1) {
            if (r1 == null) goto L5;
            this.filename = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null filename");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_FilesPayload_File(String r1, byte[] r2) {
        this.filename = r1;
        this.contents = r2;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_FilesPayload_File(String r1, byte[] r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.FilesPayload.File) == false) goto L16;
        CrashlyticsReport.FilesPayload.File r52 = (CrashlyticsReport.FilesPayload.File) r5;
        if (this.filename.equals(r52.getFilename()) == false) goto L16;
        byte[] r4 = this.contents;
        if ((r52 instanceof AutoValue_CrashlyticsReport_FilesPayload_File) == false) goto L12;
        byte[] r53 = ((AutoValue_CrashlyticsReport_FilesPayload_File) r52).contents;
    L14:
        if (Arrays.equals(r4, r53) == false) goto L16;
        return true;
    L12:
        r53 = r52.getContents();
    L16:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File
    public byte[] getContents() {
        return this.contents;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File
    public String getFilename() {
        return this.filename;
    }

    public int hashCode() {
        int r0 = (this.filename.hashCode() ^ 1000003) * 1000003;
        return Arrays.hashCode(this.contents) ^ r0;
    }

    public String toString() {
        return "File{filename=" + this.filename + ", contents=" + Arrays.toString(this.contents) + "}";
    }
}
