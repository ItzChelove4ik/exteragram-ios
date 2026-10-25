package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzkq implements ObjectEncoder {
    static final zzkq zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;
    private static final FieldDescriptor zze = null;
    private static final FieldDescriptor zzf = null;

    static {
        zza = new zzkq();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzbz r1 = new zzbz();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("imageInfo");
        zzbz r12 = new zzbz();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("subjectSegmenterOptions");
        zzbz r13 = new zzbz();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("subjectInfos");
        zzbz r14 = new zzbz();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("qualityScores");
        zzbz r15 = new zzbz();
        r15.zza(5);
        zzf = r05.withProperty(r15.zzb()).build();
    }

    private zzkq() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzrc r22 = (zzrc) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zzd());
        r32.add(zzc, r22.zzc());
        r32.add(zzd, r22.zze());
        r32.add(zze, r22.zzb());
        r32.add(zzf, r22.zza());
    }
}
