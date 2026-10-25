package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzkp implements ObjectEncoder {
    static final zzkp zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;

    static {
        zza = new zzkp();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("errorCode");
        zzbz r1 = new zzbz();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("subjectSegmenterOptions");
        zzbz r12 = new zzbz();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
    }

    private zzkp() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzqz r22 = (zzqz) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zza());
        r32.add(zzc, r22.zzb());
    }
}
