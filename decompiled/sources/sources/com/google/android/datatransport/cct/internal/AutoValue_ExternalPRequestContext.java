package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ExternalPRequestContext;

/* loaded from: classes4.dex */
final class AutoValue_ExternalPRequestContext extends ExternalPRequestContext {
    private final Integer originAssociatedProductId;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_ExternalPRequestContext$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends ExternalPRequestContext.Builder {
        private Integer originAssociatedProductId;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.ExternalPRequestContext.Builder
        public ExternalPRequestContext build() {
            return new AutoValue_ExternalPRequestContext(this.originAssociatedProductId, null);
        }

        @Override // com.google.android.datatransport.cct.internal.ExternalPRequestContext.Builder
        public ExternalPRequestContext.Builder setOriginAssociatedProductId(Integer r1) {
            this.originAssociatedProductId = r1;
            return this;
        }
    }

    private AutoValue_ExternalPRequestContext(Integer r1) {
        this.originAssociatedProductId = r1;
    }

    public /* synthetic */ AutoValue_ExternalPRequestContext(Integer r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r4) {
        if (r4 != this) goto L6;
        return true;
    L6:
        if ((r4 instanceof ExternalPRequestContext) == false) goto L15;
        ExternalPRequestContext r42 = (ExternalPRequestContext) r4;
        Integer r3 = this.originAssociatedProductId;
        if (r3 != null) goto L14;
        if (r42.getOriginAssociatedProductId() != null) goto L12;
        return true;
    L12:
        return false;
    L14:
        return r3.equals(r42.getOriginAssociatedProductId());
    L15:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.ExternalPRequestContext
    public Integer getOriginAssociatedProductId() {
        return this.originAssociatedProductId;
    }

    public int hashCode() {
        Integer r1 = this.originAssociatedProductId;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return r12 ^ 1000003;
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public String toString() {
        return "ExternalPRequestContext{originAssociatedProductId=" + this.originAssociatedProductId + "}";
    }
}
