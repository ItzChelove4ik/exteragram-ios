package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzec implements ObjectEncoder {
    static final zzec zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;

    static {
        zza = new zzec();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("identifyLanguageConfidenceThreshold");
        zzai r1 = new zzai();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("identifyAllLanguagesConfidenceThreshold");
        zzai r12 = new zzai();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("confidenceThreshold");
        zzai r13 = new zzai();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
    }

    private zzec() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, null);
        r32.add(zzc, null);
        r32.add(zzd, ((zzhu) r2).zza());
    }
}
