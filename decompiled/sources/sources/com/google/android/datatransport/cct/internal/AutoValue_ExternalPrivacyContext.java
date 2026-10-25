package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ExternalPrivacyContext;

/* loaded from: classes4.dex */
final class AutoValue_ExternalPrivacyContext extends ExternalPrivacyContext {
    private final ExternalPRequestContext prequest;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_ExternalPrivacyContext$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends ExternalPrivacyContext.Builder {
        private ExternalPRequestContext prequest;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.ExternalPrivacyContext.Builder
        public ExternalPrivacyContext build() {
            return new AutoValue_ExternalPrivacyContext(this.prequest, null);
        }

        @Override // com.google.android.datatransport.cct.internal.ExternalPrivacyContext.Builder
        public ExternalPrivacyContext.Builder setPrequest(ExternalPRequestContext r1) {
            this.prequest = r1;
            return this;
        }
    }

    private AutoValue_ExternalPrivacyContext(ExternalPRequestContext r1) {
        this.prequest = r1;
    }

    public /* synthetic */ AutoValue_ExternalPrivacyContext(ExternalPRequestContext r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r4) {
        if (r4 != this) goto L6;
        return true;
    L6:
        if ((r4 instanceof ExternalPrivacyContext) == false) goto L15;
        ExternalPrivacyContext r42 = (ExternalPrivacyContext) r4;
        ExternalPRequestContext r3 = this.prequest;
        if (r3 != null) goto L14;
        if (r42.getPrequest() != null) goto L12;
        return true;
    L12:
        return false;
    L14:
        return r3.equals(r42.getPrequest());
    L15:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.ExternalPrivacyContext
    public ExternalPRequestContext getPrequest() {
        return this.prequest;
    }

    public int hashCode() {
        ExternalPRequestContext r1 = this.prequest;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return r12 ^ 1000003;
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public String toString() {
        return "ExternalPrivacyContext{prequest=" + this.prequest + "}";
    }
}
