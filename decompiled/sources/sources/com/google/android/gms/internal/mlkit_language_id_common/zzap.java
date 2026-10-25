package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzap implements ObjectEncoder {
    public static final /* synthetic */ zzap zza = null;

    static {
        zza = new zzap();
    }

    private /* synthetic */ zzap() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final void encode(Object r1, Object r2) {
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        int r0 = zzaq.$r8$clinit;
        throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(r1.getClass().getCanonicalName())));
    }
}
