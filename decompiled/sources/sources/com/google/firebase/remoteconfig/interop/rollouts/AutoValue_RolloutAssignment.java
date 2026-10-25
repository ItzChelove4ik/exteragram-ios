package com.google.firebase.remoteconfig.interop.rollouts;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment;

/* loaded from: classes5.dex */
final class AutoValue_RolloutAssignment extends RolloutAssignment {
    private final String parameterKey;
    private final String parameterValue;
    private final String rolloutId;
    private final long templateVersion;
    private final String variantId;

    /* renamed from: com.google.firebase.remoteconfig.interop.rollouts.AutoValue_RolloutAssignment$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends RolloutAssignment.Builder {
        private String parameterKey;
        private String parameterValue;
        private String rolloutId;
        private byte set$0;
        private long templateVersion;
        private String variantId;

        public Builder() {
        }

        @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment.Builder
        public RolloutAssignment build() {
            if (this.set$0 == 1) goto L5;
        L15:
            StringBuilder r0 = new StringBuilder();
            if (this.rolloutId != null) goto L19;
            r0.append(" rolloutId");
        L19:
            if (this.variantId != null) goto L22;
            r0.append(" variantId");
        L22:
            if (this.parameterKey != null) goto L25;
            r0.append(" parameterKey");
        L25:
            if (this.parameterValue != null) goto L28;
            r0.append(" parameterValue");
        L28:
            if ((this.set$0 & 1) != 0) goto L30;
            r0.append(" templateVersion");
        L30:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        L5:
            if (this.rolloutId == null) goto L15;
            if (this.variantId == null) goto L15;
            if (this.parameterKey == null) goto L15;
            if (this.parameterValue == null) goto L15;
            return new AutoValue_RolloutAssignment(this.rolloutId, this.variantId, this.parameterKey, this.parameterValue, this.templateVersion, null);
        }

        @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment.Builder
        public RolloutAssignment.Builder setParameterKey(String r1) {
            if (r1 == null) goto L5;
            this.parameterKey = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null parameterKey");
            return null;
        }

        @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment.Builder
        public RolloutAssignment.Builder setParameterValue(String r1) {
            if (r1 == null) goto L5;
            this.parameterValue = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null parameterValue");
            return null;
        }

        @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment.Builder
        public RolloutAssignment.Builder setRolloutId(String r1) {
            if (r1 == null) goto L5;
            this.rolloutId = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null rolloutId");
            return null;
        }

        @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment.Builder
        public RolloutAssignment.Builder setTemplateVersion(long r1) {
            this.templateVersion = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment.Builder
        public RolloutAssignment.Builder setVariantId(String r1) {
            if (r1 == null) goto L5;
            this.variantId = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null variantId");
            return null;
        }
    }

    private AutoValue_RolloutAssignment(String r1, String r2, String r3, String r4, long r5) {
        this.rolloutId = r1;
        this.variantId = r2;
        this.parameterKey = r3;
        this.parameterValue = r4;
        this.templateVersion = r5;
    }

    public /* synthetic */ AutoValue_RolloutAssignment(String r1, String r2, String r3, String r4, long r5, AnonymousClass1 r7) {
        this(r1, r2, r3, r4, r5);
    }

    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof RolloutAssignment) == false) goto L18;
        RolloutAssignment r62 = (RolloutAssignment) r6;
        if (this.rolloutId.equals(r62.getRolloutId()) == false) goto L18;
        if (this.variantId.equals(r62.getVariantId()) == false) goto L18;
        if (this.parameterKey.equals(r62.getParameterKey()) == false) goto L18;
        if (this.parameterValue.equals(r62.getParameterValue()) == false) goto L18;
        if (this.templateVersion != r62.getTemplateVersion()) goto L18;
        return true;
    L18:
        return false;
    }

    @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment
    public String getParameterKey() {
        return this.parameterKey;
    }

    @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment
    public String getParameterValue() {
        return this.parameterValue;
    }

    @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment
    public String getRolloutId() {
        return this.rolloutId;
    }

    @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment
    public long getTemplateVersion() {
        return this.templateVersion;
    }

    @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment
    public String getVariantId() {
        return this.variantId;
    }

    public int hashCode() {
        int r0 = (((((((this.rolloutId.hashCode() ^ 1000003) * 1000003) ^ this.variantId.hashCode()) * 1000003) ^ this.parameterKey.hashCode()) * 1000003) ^ this.parameterValue.hashCode()) * 1000003;
        long r1 = this.templateVersion;
        return ((int) (r1 ^ (r1 >>> 32))) ^ r0;
    }

    public String toString() {
        return "RolloutAssignment{rolloutId=" + this.rolloutId + ", variantId=" + this.variantId + ", parameterKey=" + this.parameterKey + ", parameterValue=" + this.parameterValue + ", templateVersion=" + this.templateVersion + "}";
    }
}
