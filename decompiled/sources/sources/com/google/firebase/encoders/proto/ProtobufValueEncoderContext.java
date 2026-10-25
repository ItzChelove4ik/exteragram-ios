package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;

/* loaded from: classes5.dex */
class ProtobufValueEncoderContext implements ValueEncoderContext {
    private boolean encoded;
    private FieldDescriptor field;
    private final ProtobufDataEncoderContext objEncoderCtx;
    private boolean skipDefault;

    public ProtobufValueEncoderContext(ProtobufDataEncoderContext r2) {
        this.encoded = false;
        this.skipDefault = false;
        this.objEncoderCtx = r2;
    }

    private void checkNotUsed() {
        if (this.encoded == true) goto L7;
        this.encoded = true;
        return;
    L7:
        throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(String r4) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, r4, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(boolean r4) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, r4, this.skipDefault);
        return this;
    }

    public void resetContext(FieldDescriptor r2, boolean r3) {
        this.encoded = false;
        this.field = r2;
        this.skipDefault = r3;
    }
}
