package com.google.android.gms.internal.mlkit_vision_label;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzfz implements ObjectEncoder {
    static final zzfz zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;
    private static final FieldDescriptor zze = null;

    static {
        zza = new zzfz();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("imageFormat");
        zzci r1 = new zzci();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("originalImageSize");
        zzci r12 = new zzci();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("compressedImageSize");
        zzci r13 = new zzci();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("isOdmlImage");
        zzci r14 = new zzci();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
    }

    private zzfz() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzjt r22 = (zzjt) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zza());
        r32.add(zzc, r22.zzb());
        r32.add(zzd, null);
        r32.add(zze, null);
    }
}
