package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.AutoValue_EventInternal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class EventInternal {

    public static abstract class Builder {
        public Builder() {
        }

        public final Builder addMetadata(String r2, int r3) {
            getAutoMetadata().put(r2, String.valueOf(r3));
            return this;
        }

        public final Builder addMetadata(String r2, long r3) {
            getAutoMetadata().put(r2, String.valueOf(r3));
            return this;
        }

        public final Builder addMetadata(String r2, String r3) {
            getAutoMetadata().put(r2, r3);
            return this;
        }

        public abstract EventInternal build();

        public abstract Map<String, String> getAutoMetadata();

        public abstract Builder setAutoMetadata(Map<String, String> r1);

        public abstract Builder setCode(Integer r1);

        public abstract Builder setEncodedPayload(EncodedPayload r1);

        public abstract Builder setEventMillis(long r1);

        public abstract Builder setExperimentIdsClear(byte[] r1);

        public abstract Builder setExperimentIdsEncrypted(byte[] r1);

        public abstract Builder setProductId(Integer r1);

        public abstract Builder setPseudonymousId(String r1);

        public abstract Builder setTransportName(String r1);

        public abstract Builder setUptimeMillis(long r1);
    }

    public EventInternal() {
    }

    public static Builder builder() {
        return new AutoValue_EventInternal.Builder().setAutoMetadata(new HashMap());
    }

    public final String get(String r1) {
        String r0 = getAutoMetadata().get(r1);
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r0;
    }

    public abstract Map<String, String> getAutoMetadata();

    public abstract Integer getCode();

    public abstract EncodedPayload getEncodedPayload();

    public abstract long getEventMillis();

    public abstract byte[] getExperimentIdsClear();

    public abstract byte[] getExperimentIdsEncrypted();

    public final int getInteger(String r1) {
        String r0 = getAutoMetadata().get(r1);
        if (r0 != null) goto L7;
        return 0;
    L7:
        return Integer.valueOf(r0).intValue();
    }

    public final long getLong(String r1) {
        String r0 = getAutoMetadata().get(r1);
        if (r0 != null) goto L7;
        return 0;
    L7:
        return Long.valueOf(r0).longValue();
    }

    public final Map<String, String> getMetadata() {
        return Collections.unmodifiableMap(getAutoMetadata());
    }

    public abstract Integer getProductId();

    public abstract String getPseudonymousId();

    public abstract String getTransportName();

    public abstract long getUptimeMillis();

    public Builder toBuilder() {
        return new AutoValue_EventInternal.Builder().setTransportName(getTransportName()).setCode(getCode()).setProductId(getProductId()).setPseudonymousId(getPseudonymousId()).setExperimentIdsClear(getExperimentIdsClear()).setExperimentIdsEncrypted(getExperimentIdsEncrypted()).setEncodedPayload(getEncodedPayload()).setEventMillis(getEventMillis()).setUptimeMillis(getUptimeMillis()).setAutoMetadata(new HashMap(getAutoMetadata()));
    }
}
