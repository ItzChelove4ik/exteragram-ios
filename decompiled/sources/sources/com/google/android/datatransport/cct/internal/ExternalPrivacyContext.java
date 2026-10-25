package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_ExternalPrivacyContext;

/* loaded from: classes4.dex */
public abstract class ExternalPrivacyContext {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract ExternalPrivacyContext build();

        public abstract Builder setPrequest(ExternalPRequestContext r1);
    }

    public ExternalPrivacyContext() {
    }

    public static Builder builder() {
        return new AutoValue_ExternalPrivacyContext.Builder();
    }

    public abstract ExternalPRequestContext getPrequest();
}
