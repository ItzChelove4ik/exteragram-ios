package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.AutoValue_EventStoreConfig;

/* loaded from: classes.dex */
abstract class EventStoreConfig {
    static final EventStoreConfig DEFAULT = null;

    public static abstract class Builder {
        public Builder() {
        }

        public abstract EventStoreConfig build();

        public abstract Builder setCriticalSectionEnterTimeoutMs(int r1);

        public abstract Builder setEventCleanUpAge(long r1);

        public abstract Builder setLoadBatchSize(int r1);

        public abstract Builder setMaxBlobByteSizePerRow(int r1);

        public abstract Builder setMaxStorageSizeInBytes(long r1);
    }

    static {
        DEFAULT = builder().setMaxStorageSizeInBytes(10485760).setLoadBatchSize(200).setCriticalSectionEnterTimeoutMs(10000).setEventCleanUpAge(604800000).setMaxBlobByteSizePerRow(81920).build();
    }

    public EventStoreConfig() {
    }

    public static Builder builder() {
        return new AutoValue_EventStoreConfig.Builder();
    }

    public abstract int getCriticalSectionEnterTimeoutMs();

    public abstract long getEventCleanUpAge();

    public abstract int getLoadBatchSize();

    public abstract int getMaxBlobByteSizePerRow();

    public abstract long getMaxStorageSizeInBytes();
}
