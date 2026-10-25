package com.google.android.gms.internal.mlkit_vision_label;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzhc implements ObjectEncoder {
    static final zzhc zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;

    static {
        zza = new zzhc();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("maxLabels");
        zzci r1 = new zzci();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("confidenceThreshold");
        zzci r12 = new zzci();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("customLocalModelOptions");
        zzci r13 = new zzci();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
    }

    private zzhc() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, null);
        r32.add(zzc, ((zzlg) r2).zza());
        r32.add(zzd, null);
    }
}
