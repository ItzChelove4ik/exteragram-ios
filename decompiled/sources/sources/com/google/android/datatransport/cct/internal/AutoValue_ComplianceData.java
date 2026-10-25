package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ComplianceData;

/* loaded from: classes4.dex */
final class AutoValue_ComplianceData extends ComplianceData {
    private final ExternalPrivacyContext privacyContext;
    private final ComplianceData.ProductIdOrigin productIdOrigin;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_ComplianceData$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends ComplianceData.Builder {
        private ExternalPrivacyContext privacyContext;
        private ComplianceData.ProductIdOrigin productIdOrigin;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.ComplianceData.Builder
        public ComplianceData build() {
            return new AutoValue_ComplianceData(this.privacyContext, this.productIdOrigin, null);
        }

        @Override // com.google.android.datatransport.cct.internal.ComplianceData.Builder
        public ComplianceData.Builder setPrivacyContext(ExternalPrivacyContext r1) {
            this.privacyContext = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.ComplianceData.Builder
        public ComplianceData.Builder setProductIdOrigin(ComplianceData.ProductIdOrigin r1) {
            this.productIdOrigin = r1;
            return this;
        }
    }

    private AutoValue_ComplianceData(ExternalPrivacyContext r1, ComplianceData.ProductIdOrigin r2) {
        this.privacyContext = r1;
        this.productIdOrigin = r2;
    }

    public /* synthetic */ AutoValue_ComplianceData(ExternalPrivacyContext r1, ComplianceData.ProductIdOrigin r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ComplianceData) == false) goto L22;
        ComplianceData r52 = (ComplianceData) r5;
        ExternalPrivacyContext r1 = this.privacyContext;
        if (r1 != null) goto L13;
        if (r52.getPrivacyContext() != null) goto L22;
    L14:
        ComplianceData.ProductIdOrigin r4 = this.productIdOrigin;
        if (r4 != null) goto L20;
        if (r52.getProductIdOrigin() != null) goto L22;
    L21:
        return true;
    L20:
        if (r4.equals(r52.getProductIdOrigin()) == false) goto L22;
    L13:
        if (r1.equals(r52.getPrivacyContext()) == true) goto L14;
    L22:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.ComplianceData
    public ExternalPrivacyContext getPrivacyContext() {
        return this.privacyContext;
    }

    @Override // com.google.android.datatransport.cct.internal.ComplianceData
    public ComplianceData.ProductIdOrigin getProductIdOrigin() {
        return this.productIdOrigin;
    }

    public int hashCode() {
        ExternalPrivacyContext r0 = this.privacyContext;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (r02 ^ 1000003) * 1000003;
        ComplianceData.ProductIdOrigin r3 = this.productIdOrigin;
        if (r3 == null) goto L11;
        r1 = r3.hashCode();
    L11:
        return r03 ^ r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "ComplianceData{privacyContext=" + this.privacyContext + ", productIdOrigin=" + this.productIdOrigin + "}";
    }
}
