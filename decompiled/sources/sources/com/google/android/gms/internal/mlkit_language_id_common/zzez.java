package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzez implements ObjectEncoder {
    static final zzez zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;
    private static final FieldDescriptor zze = null;

    static {
        zza = new zzez();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzai r1 = new zzai();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("options");
        zzai r12 = new zzai();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("identifyLanguageResult");
        zzai r13 = new zzai();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("identifyPossibleLanguagesResult");
        zzai r14 = new zzai();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
    }

    private zzez() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzjf r22 = (zzjf) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zza());
        r32.add(zzc, r22.zzb());
        r32.add(zzd, r22.zzc());
        FieldDescriptor r1 = zze;
        r22.zzd();
        r32.add(r1, null);
    }
}
