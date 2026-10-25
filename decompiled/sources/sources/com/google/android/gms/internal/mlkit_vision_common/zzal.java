package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzal implements ObjectEncoder {
    public static final /* synthetic */ zzal zza = null;

    static {
        zza = new zzal();
    }

    private /* synthetic */ zzal() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final void encode(Object r1, Object r2) {
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        int r0 = zzam.$r8$clinit;
        throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(r1.getClass().getCanonicalName())));
    }
}
