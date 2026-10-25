package com.google.android.gms.internal.mlkit_vision_label;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzcp implements ObjectEncoder {
    public static final /* synthetic */ zzcp zza = null;

    static {
        zza = new zzcp();
    }

    private /* synthetic */ zzcp() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final void encode(Object r1, Object r2) {
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        int r0 = zzcq.$r8$clinit;
        throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(r1.getClass().getCanonicalName())));
    }
}
