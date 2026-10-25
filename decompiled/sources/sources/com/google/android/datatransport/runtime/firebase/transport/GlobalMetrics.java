package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.proto.Protobuf;

/* loaded from: classes4.dex */
public final class GlobalMetrics {
    private static final GlobalMetrics DEFAULT_INSTANCE = null;
    private final StorageMetrics storage_metrics_;

    public static final class Builder {
        private StorageMetrics storage_metrics_;

        public Builder() {
            this.storage_metrics_ = null;
        }

        public GlobalMetrics build() {
            return new GlobalMetrics(this.storage_metrics_);
        }

        public Builder setStorageMetrics(StorageMetrics r1) {
            this.storage_metrics_ = r1;
            return this;
        }
    }

    static {
        DEFAULT_INSTANCE = new Builder().build();
    }

    public GlobalMetrics(StorageMetrics r1) {
        this.storage_metrics_ = r1;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Protobuf(tag = 1)
    public StorageMetrics getStorageMetricsInternal() {
        return this.storage_metrics_;
    }
}
