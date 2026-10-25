package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.proto.Protobuf;

/* loaded from: classes4.dex */
public final class StorageMetrics {
    private static final StorageMetrics DEFAULT_INSTANCE = null;
    private final long current_cache_size_bytes_;
    private final long max_cache_size_bytes_;

    public static final class Builder {
        private long current_cache_size_bytes_;
        private long max_cache_size_bytes_;

        public Builder() {
            this.current_cache_size_bytes_ = 0;
            this.max_cache_size_bytes_ = 0;
        }

        public StorageMetrics build() {
            return new StorageMetrics(this.current_cache_size_bytes_, this.max_cache_size_bytes_);
        }

        public Builder setCurrentCacheSizeBytes(long r1) {
            this.current_cache_size_bytes_ = r1;
            return this;
        }

        public Builder setMaxCacheSizeBytes(long r1) {
            this.max_cache_size_bytes_ = r1;
            return this;
        }
    }

    static {
        DEFAULT_INSTANCE = new Builder().build();
    }

    public StorageMetrics(long r1, long r3) {
        this.current_cache_size_bytes_ = r1;
        this.max_cache_size_bytes_ = r3;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Protobuf(tag = 1)
    public long getCurrentCacheSizeBytes() {
        return this.current_cache_size_bytes_;
    }

    @Protobuf(tag = 2)
    public long getMaxCacheSizeBytes() {
        return this.max_cache_size_bytes_;
    }
}
