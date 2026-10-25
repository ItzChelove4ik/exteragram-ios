package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzcg implements ObjectEncoder {
    public static final /* synthetic */ zzcg zza = null;

    static {
        zza = new zzcg();
    }

    private /* synthetic */ zzcg() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final void encode(Object r1, Object r2) {
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        int r0 = zzch.$r8$clinit;
        throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(r1.getClass().getCanonicalName())));
    }
}
