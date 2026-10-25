package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzlj implements ObjectEncoder {
    static final zzlj zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;
    private static final FieldDescriptor zze = null;

    static {
        zza = new zzlj();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("width");
        zzbz r1 = new zzbz();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("height");
        zzbz r12 = new zzbz();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("startX");
        zzbz r13 = new zzbz();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("startY");
        zzbz r14 = new zzbz();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
    }

    private zzlj() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzrv r22 = (zzrv) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zzd());
        r32.add(zzc, r22.zza());
        r32.add(zzd, r22.zzb());
        r32.add(zze, r22.zzc());
    }
}
