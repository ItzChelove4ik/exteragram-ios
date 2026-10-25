package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_ExperimentIds;

/* loaded from: classes4.dex */
public abstract class ExperimentIds {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract ExperimentIds build();

        public abstract Builder setClearBlob(byte[] r1);

        public abstract Builder setEncryptedBlob(byte[] r1);
    }

    public ExperimentIds() {
    }

    public static Builder builder() {
        return new AutoValue_ExperimentIds.Builder();
    }

    public abstract byte[] getClearBlob();

    public abstract byte[] getEncryptedBlob();
}
