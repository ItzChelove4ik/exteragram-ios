package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzfa implements ObjectEncoder {
    static final zzfa zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;

    static {
        zza = new zzfa();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("confidence");
        zzai r1 = new zzai();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("languageCode");
        zzai r12 = new zzai();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
    }

    private zzfa() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzix r22 = (zzix) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zza());
        r32.add(zzc, r22.zzb());
    }
}
