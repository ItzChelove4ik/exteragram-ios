package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzfb implements ObjectEncoder {
    static final zzfb zza = null;
    private static final FieldDescriptor zzb = null;

    static {
        zza = new zzfb();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("identifiedLanguage");
        zzai r1 = new zzai();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
    }

    private zzfb() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        ((ObjectEncoderContext) r2).add(zzb, ((zzja) r1).zza());
    }
}
