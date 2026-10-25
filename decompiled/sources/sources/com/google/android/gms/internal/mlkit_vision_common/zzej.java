package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzej implements ObjectEncoder {
    static final zzej zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;
    private static final FieldDescriptor zze = null;
    private static final FieldDescriptor zzf = null;
    private static final FieldDescriptor zzg = null;
    private static final FieldDescriptor zzh = null;

    static {
        zza = new zzej();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("durationMs");
        zzae r1 = new zzae();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("imageSource");
        zzae r12 = new zzae();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("imageFormat");
        zzae r13 = new zzae();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("imageByteSize");
        zzae r14 = new zzae();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("imageWidth");
        zzae r15 = new zzae();
        r15.zza(5);
        zzf = r05.withProperty(r15.zzb()).build();
        FieldDescriptor.Builder r06 = FieldDescriptor.builder("imageHeight");
        zzae r16 = new zzae();
        r16.zza(6);
        zzg = r06.withProperty(r16.zzb()).build();
        FieldDescriptor.Builder r07 = FieldDescriptor.builder("rotationDegrees");
        zzae r17 = new zzae();
        r17.zza(7);
        zzh = r07.withProperty(r17.zzb()).build();
    }

    private zzej() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zziq r22 = (zziq) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zzg());
        r32.add(zzc, r22.zzb());
        r32.add(zzd, r22.zza());
        r32.add(zze, r22.zzc());
        r32.add(zzf, r22.zze());
        r32.add(zzg, r22.zzd());
        r32.add(zzh, r22.zzf());
    }
}
