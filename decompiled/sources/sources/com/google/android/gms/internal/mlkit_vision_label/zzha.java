package com.google.android.gms.internal.mlkit_vision_label;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzha implements ObjectEncoder {
    static final zzha zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;
    private static final FieldDescriptor zze = null;
    private static final FieldDescriptor zzf = null;

    static {
        zza = new zzha();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzci r1 = new zzci();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("options");
        zzci r12 = new zzci();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("imageInfo");
        zzci r13 = new zzci();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("labelCount");
        zzci r14 = new zzci();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("highestConfidence");
        zzci r15 = new zzci();
        r15.zza(5);
        zzf = r05.withProperty(r15.zzb()).build();
    }

    private zzha() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzla r22 = (zzla) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zzb());
        r32.add(zzc, r22.zzc());
        r32.add(zzd, r22.zza());
        r32.add(zze, null);
        r32.add(zzf, null);
    }
}
