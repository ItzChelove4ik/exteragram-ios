package com.google.firebase.messaging;

import com.google.firebase.encoders.proto.ProtobufEncoder;

/* loaded from: classes5.dex */
public abstract class ProtoEncoderDoNotUse {
    private static final ProtobufEncoder ENCODER = null;

    static {
        ENCODER = ProtobufEncoder.builder().configureWith(AutoProtoEncoderDoNotUseEncoder.CONFIG).build();
    }

    public static byte[] encode(Object r1) {
        return ENCODER.encode(r1);
    }
}
