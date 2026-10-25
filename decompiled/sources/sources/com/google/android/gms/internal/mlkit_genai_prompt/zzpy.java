package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpy implements ObjectEncoder {
    static final /* synthetic */ zzpy zza = null;

    static {
        zza = new zzpy();
    }

    private /* synthetic */ zzpy() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* synthetic */ void encode(Object r1, Object r2) {
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        int r0 = zzpz.$r8$clinit;
        throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(r1.getClass().getCanonicalName())));
    }
}
