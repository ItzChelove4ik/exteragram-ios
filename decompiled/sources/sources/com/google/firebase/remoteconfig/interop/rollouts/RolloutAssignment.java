package com.google.firebase.remoteconfig.interop.rollouts;

import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.remoteconfig.interop.rollouts.AutoValue_RolloutAssignment;

/* loaded from: classes5.dex */
public abstract class RolloutAssignment {
    public static final DataEncoder ROLLOUT_ASSIGNMENT_JSON_ENCODER = null;

    public static abstract class Builder {
        public Builder() {
        }

        public abstract RolloutAssignment build();

        public abstract Builder setParameterKey(String r1);

        public abstract Builder setParameterValue(String r1);

        public abstract Builder setRolloutId(String r1);

        public abstract Builder setTemplateVersion(long r1);

        public abstract Builder setVariantId(String r1);
    }

    static {
        ROLLOUT_ASSIGNMENT_JSON_ENCODER = new JsonDataEncoderBuilder().configureWith(AutoRolloutAssignmentEncoder.CONFIG).build();
    }

    public RolloutAssignment() {
    }

    public static Builder builder() {
        return new AutoValue_RolloutAssignment.Builder();
    }

    public abstract String getParameterKey();

    public abstract String getParameterValue();

    public abstract String getRolloutId();

    public abstract long getTemplateVersion();

    public abstract String getVariantId();
}
