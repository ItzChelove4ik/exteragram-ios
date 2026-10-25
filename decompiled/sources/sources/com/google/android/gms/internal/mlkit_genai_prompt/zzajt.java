package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzajt implements ObjectEncoder {
    static final zzajt zza = null;
    private static final FieldDescriptor zzb = null;

    static {
        zza = new zzajt();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("options");
        zzpr r1 = new zzpr();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
    }

    private zzajt() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        ((ObjectEncoderContext) r2).add(zzb, ((zzarh) r1).zza());
    }
}
