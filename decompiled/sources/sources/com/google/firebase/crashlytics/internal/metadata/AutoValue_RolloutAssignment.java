package com.google.firebase.crashlytics.internal.metadata;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
final class AutoValue_RolloutAssignment extends RolloutAssignment {
    private final String parameterKey;
    private final String parameterValue;
    private final String rolloutId;
    private final long templateVersion;
    private final String variantId;

    public AutoValue_RolloutAssignment(String r2, String r3, String r4, String r5, long r6) {
        if (r2 == null) goto L18;
        this.rolloutId = r2;
        if (r3 == null) goto L16;
        this.parameterKey = r3;
        if (r4 == null) goto L14;
        this.parameterValue = r4;
        if (r5 == null) goto L12;
        this.variantId = r5;
        this.templateVersion = r6;
        return;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null variantId");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null parameterValue");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("Null parameterKey");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("Null rolloutId");
        throw null;
    }

    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof RolloutAssignment) == false) goto L18;
        RolloutAssignment r62 = (RolloutAssignment) r6;
        if (this.rolloutId.equals(r62.getRolloutId()) == false) goto L18;
        if (this.parameterKey.equals(r62.getParameterKey()) == false) goto L18;
        if (this.parameterValue.equals(r62.getParameterValue()) == false) goto L18;
        if (this.variantId.equals(r62.getVariantId()) == false) goto L18;
        if (this.templateVersion != r62.getTemplateVersion()) goto L18;
        return true;
    L18:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public String getParameterKey() {
        return this.parameterKey;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public String getParameterValue() {
        return this.parameterValue;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public String getRolloutId() {
        return this.rolloutId;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public long getTemplateVersion() {
        return this.templateVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.RolloutAssignment
    public String getVariantId() {
        return this.variantId;
    }

    public int hashCode() {
        int r0 = (((((((this.rolloutId.hashCode() ^ 1000003) * 1000003) ^ this.parameterKey.hashCode()) * 1000003) ^ this.parameterValue.hashCode()) * 1000003) ^ this.variantId.hashCode()) * 1000003;
        long r1 = this.templateVersion;
        return ((int) (r1 ^ (r1 >>> 32))) ^ r0;
    }

    public String toString() {
        return "RolloutAssignment{rolloutId=" + this.rolloutId + ", parameterKey=" + this.parameterKey + ", parameterValue=" + this.parameterValue + ", variantId=" + this.variantId + ", templateVersion=" + this.templateVersion + "}";
    }
}
