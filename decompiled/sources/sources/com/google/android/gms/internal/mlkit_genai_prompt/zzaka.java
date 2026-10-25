package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzaka implements ObjectEncoder {
    static final zzaka zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;
    private static final FieldDescriptor zze = null;
    private static final FieldDescriptor zzf = null;
    private static final FieldDescriptor zzg = null;
    private static final FieldDescriptor zzh = null;

    static {
        zza = new zzaka();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("featureVersion");
        zzpr r1 = new zzpr();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("errorCode");
        zzpr r12 = new zzpr();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("isColdCall");
        zzpr r13 = new zzpr();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("inputSize");
        zzpr r14 = new zzpr();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("outputSize");
        zzpr r15 = new zzpr();
        r15.zza(5);
        zzf = r05.withProperty(r15.zzb()).build();
        FieldDescriptor.Builder r06 = FieldDescriptor.builder("latencyMs");
        zzpr r16 = new zzpr();
        r16.zza(6);
        zzg = r06.withProperty(r16.zzb()).build();
        FieldDescriptor.Builder r07 = FieldDescriptor.builder("firstTokenLatencyMs");
        zzpr r17 = new zzpr();
        r17.zza(7);
        zzh = r07.withProperty(r17.zzb()).build();
    }

    private zzaka() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzart r22 = (zzart) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zza());
        r32.add(zzc, r22.zzb());
        r32.add(zzd, r22.zzc());
        r32.add(zze, r22.zzd());
        r32.add(zzf, r22.zze());
        r32.add(zzg, r22.zzf());
        r32.add(zzh, r22.zzg());
    }
}
