package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzgd implements ObjectEncoder {
    static final zzgd zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;
    private static final FieldDescriptor zzd = null;
    private static final FieldDescriptor zze = null;
    private static final FieldDescriptor zzf = null;
    private static final FieldDescriptor zzg = null;
    private static final FieldDescriptor zzh = null;
    private static final FieldDescriptor zzi = null;
    private static final FieldDescriptor zzj = null;
    private static final FieldDescriptor zzk = null;
    private static final FieldDescriptor zzl = null;
    private static final FieldDescriptor zzm = null;
    private static final FieldDescriptor zzn = null;
    private static final FieldDescriptor zzo = null;

    static {
        zza = new zzgd();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("appId");
        zzai r1 = new zzai();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("appVersion");
        zzai r12 = new zzai();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("firebaseProjectId");
        zzai r13 = new zzai();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("mlSdkVersion");
        zzai r14 = new zzai();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzai r15 = new zzai();
        r15.zza(5);
        zzf = r05.withProperty(r15.zzb()).build();
        FieldDescriptor.Builder r06 = FieldDescriptor.builder("gcmSenderId");
        zzai r16 = new zzai();
        r16.zza(6);
        zzg = r06.withProperty(r16.zzb()).build();
        FieldDescriptor.Builder r07 = FieldDescriptor.builder("apiKey");
        zzai r17 = new zzai();
        r17.zza(7);
        zzh = r07.withProperty(r17.zzb()).build();
        FieldDescriptor.Builder r08 = FieldDescriptor.builder("languages");
        zzai r18 = new zzai();
        r18.zza(8);
        zzi = r08.withProperty(r18.zzb()).build();
        FieldDescriptor.Builder r09 = FieldDescriptor.builder("mlSdkInstanceId");
        zzai r19 = new zzai();
        r19.zza(9);
        zzj = r09.withProperty(r19.zzb()).build();
        FieldDescriptor.Builder r010 = FieldDescriptor.builder("isClearcutClient");
        zzai r110 = new zzai();
        r110.zza(10);
        zzk = r010.withProperty(r110.zzb()).build();
        FieldDescriptor.Builder r011 = FieldDescriptor.builder("isStandaloneMlkit");
        zzai r111 = new zzai();
        r111.zza(11);
        zzl = r011.withProperty(r111.zzb()).build();
        FieldDescriptor.Builder r012 = FieldDescriptor.builder("isJsonLogging");
        zzai r112 = new zzai();
        r112.zza(12);
        zzm = r012.withProperty(r112.zzb()).build();
        FieldDescriptor.Builder r013 = FieldDescriptor.builder("buildLevel");
        zzai r113 = new zzai();
        r113.zza(13);
        zzn = r013.withProperty(r113.zzb()).build();
        FieldDescriptor.Builder r014 = FieldDescriptor.builder("optionalModuleVersion");
        zzai r114 = new zzai();
        r114.zza(14);
        zzo = r014.withProperty(r114.zzb()).build();
    }

    private zzgd() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r3, Object r4) {
        zzke r32 = (zzke) r3;
        ObjectEncoderContext r42 = (ObjectEncoderContext) r4;
        r42.add(zzb, r32.zzg());
        r42.add(zzc, r32.zzh());
        r42.add(zzd, null);
        r42.add(zze, r32.zzj());
        r42.add(zzf, r32.zzk());
        r42.add(zzg, null);
        r42.add(zzh, null);
        r42.add(zzi, r32.zza());
        r42.add(zzj, r32.zzi());
        r42.add(zzk, r32.zzb());
        r42.add(zzl, r32.zzd());
        r42.add(zzm, r32.zzc());
        r42.add(zzn, r32.zze());
        r42.add(zzo, r32.zzf());
    }
}
