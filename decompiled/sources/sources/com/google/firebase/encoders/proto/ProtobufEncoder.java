package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ProtobufEncoder {
    private final ObjectEncoder<Object> fallbackEncoder;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;

    public static final class Builder implements EncoderConfig<Builder> {
        private static final ObjectEncoder<Object> DEFAULT_FALLBACK_ENCODER = null;
        private ObjectEncoder<Object> fallbackEncoder;
        private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
        private final Map<Class<?>, ValueEncoder<?>> valueEncoders;

        /* renamed from: $r8$lambda$HcoNKxmNqN-9duQx_r41_DwbVtE, reason: not valid java name */
        public static /* synthetic */ void m1953$r8$lambda$HcoNKxmNqN9duQx_r41_DwbVtE(Object r2, ObjectEncoderContext r3) {
            throw new EncodingException("Couldn't find encoder for type " + r2.getClass().getCanonicalName());
        }

        static {
            DEFAULT_FALLBACK_ENCODER = new ProtobufEncoder$Builder$$ExternalSyntheticLambda0();
        }

        public Builder() {
            this.objectEncoders = new HashMap();
            this.valueEncoders = new HashMap();
            this.fallbackEncoder = DEFAULT_FALLBACK_ENCODER;
        }

        public ProtobufEncoder build() {
            return new ProtobufEncoder(new HashMap(this.objectEncoders), new HashMap(this.valueEncoders), this.fallbackEncoder);
        }

        public Builder configureWith(Configurator r1) {
            r1.configure(this);
            return this;
        }

        @Override // com.google.firebase.encoders.config.EncoderConfig
        public /* bridge */ /* synthetic */ EncoderConfig registerEncoder(Class r1, ObjectEncoder r2) {
            return registerEncoder(r1, r2);
        }

        @Override // com.google.firebase.encoders.config.EncoderConfig
        public <U> Builder registerEncoder(Class<U> r2, ObjectEncoder<? super U> r3) {
            this.objectEncoders.put(r2, r3);
            this.valueEncoders.remove(r2);
            return this;
        }
    }

    public ProtobufEncoder(Map<Class<?>, ObjectEncoder<?>> r1, Map<Class<?>, ValueEncoder<?>> r2, ObjectEncoder<Object> r3) {
        this.objectEncoders = r1;
        this.valueEncoders = r2;
        this.fallbackEncoder = r3;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void encode(Object r4, OutputStream r5) {
        new ProtobufDataEncoderContext(r5, this.objectEncoders, this.valueEncoders, this.fallbackEncoder).encode(r4);
    }

    public byte[] encode(Object r2) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        encode(r2, r0);     // Catch: IOException -> L6
    L5:
        return r0.toByteArray();
    }
}
