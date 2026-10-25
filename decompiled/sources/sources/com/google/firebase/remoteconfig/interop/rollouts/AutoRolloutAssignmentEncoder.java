package com.google.firebase.remoteconfig.interop.rollouts;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;

/* loaded from: classes5.dex */
public final class AutoRolloutAssignmentEncoder implements Configurator {
    public static final Configurator CONFIG = null;

    public static final class RolloutAssignmentEncoder implements ObjectEncoder<RolloutAssignment> {
        static final RolloutAssignmentEncoder INSTANCE = null;
        private static final FieldDescriptor PARAMETERKEY_DESCRIPTOR = null;
        private static final FieldDescriptor PARAMETERVALUE_DESCRIPTOR = null;
        private static final FieldDescriptor ROLLOUTID_DESCRIPTOR = null;
        private static final FieldDescriptor TEMPLATEVERSION_DESCRIPTOR = null;
        private static final FieldDescriptor VARIANTID_DESCRIPTOR = null;

        static {
            INSTANCE = new RolloutAssignmentEncoder();
            ROLLOUTID_DESCRIPTOR = FieldDescriptor.of("rolloutId");
            VARIANTID_DESCRIPTOR = FieldDescriptor.of("variantId");
            PARAMETERKEY_DESCRIPTOR = FieldDescriptor.of("parameterKey");
            PARAMETERVALUE_DESCRIPTOR = FieldDescriptor.of("parameterValue");
            TEMPLATEVERSION_DESCRIPTOR = FieldDescriptor.of("templateVersion");
        }

        private RolloutAssignmentEncoder() {
        }

        public void encode(RolloutAssignment r3, ObjectEncoderContext r4) {
            r4.add(ROLLOUTID_DESCRIPTOR, r3.getRolloutId());
            r4.add(VARIANTID_DESCRIPTOR, r3.getVariantId());
            r4.add(PARAMETERKEY_DESCRIPTOR, r3.getParameterKey());
            r4.add(PARAMETERVALUE_DESCRIPTOR, r3.getParameterValue());
            r4.add(TEMPLATEVERSION_DESCRIPTOR, r3.getTemplateVersion());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((RolloutAssignment) r1, (ObjectEncoderContext) r2);
        }
    }

    static {
        CONFIG = new AutoRolloutAssignmentEncoder();
    }

    private AutoRolloutAssignmentEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> r2) {
        RolloutAssignmentEncoder r1 = RolloutAssignmentEncoder.INSTANCE;
        r2.registerEncoder(RolloutAssignment.class, r1);
        r2.registerEncoder(AutoValue_RolloutAssignment.class, r1);
    }
}
