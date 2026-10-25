package com.google.android.datatransport.runtime;

import com.google.firebase.encoders.proto.ProtobufEncoder;

/* loaded from: classes4.dex */
public abstract class ProtoEncoderDoNotUse {
    private static final ProtobufEncoder ENCODER = null;

    static {
        ENCODER = ProtobufEncoder.builder().configureWith(AutoProtoEncoderDoNotUseEncoder.CONFIG).build();
    }

    public static byte[] encode(Object r1) {
        return ENCODER.encode(r1);
    }
}
