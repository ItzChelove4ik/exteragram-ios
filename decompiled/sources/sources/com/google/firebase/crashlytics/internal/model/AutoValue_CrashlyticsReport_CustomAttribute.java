package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_CustomAttribute extends CrashlyticsReport.CustomAttribute {
    private final String key;
    private final String value;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_CustomAttribute$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.CustomAttribute.Builder {
        private String key;
        private String value;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute.Builder
        public CrashlyticsReport.CustomAttribute build() {
            String r0 = this.key;
            if (r0 == null) goto L9;
            String r1 = this.value;
            if (r1 == null) goto L9;
            return new AutoValue_CrashlyticsReport_CustomAttribute(r0, r1, null);
        L9:
            StringBuilder r02 = new StringBuilder();
            if (this.key != null) goto L13;
            r02.append(" key");
        L13:
            if (this.value != null) goto L15;
            r02.append(" value");
        L15:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r02);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute.Builder
        public CrashlyticsReport.CustomAttribute.Builder setKey(String r1) {
            if (r1 == null) goto L5;
            this.key = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null key");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute.Builder
        public CrashlyticsReport.CustomAttribute.Builder setValue(String r1) {
            if (r1 == null) goto L5;
            this.value = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null value");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_CustomAttribute(String r1, String r2) {
        this.key = r1;
        this.value = r2;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_CustomAttribute(String r1, String r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.CustomAttribute) == false) goto L12;
        CrashlyticsReport.CustomAttribute r52 = (CrashlyticsReport.CustomAttribute) r5;
        if (this.key.equals(r52.getKey()) == false) goto L12;
        if (this.value.equals(r52.getValue()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute
    public String getKey() {
        return this.key;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.CustomAttribute
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        int r0 = (this.key.hashCode() ^ 1000003) * 1000003;
        return this.value.hashCode() ^ r0;
    }

    public String toString() {
        return "CustomAttribute{key=" + this.key + ", value=" + this.value + "}";
    }
}
