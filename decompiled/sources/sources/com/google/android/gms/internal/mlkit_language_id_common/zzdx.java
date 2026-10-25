package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzdx implements ObjectEncoder {
    static final zzdx zza = null;
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

    static {
        zza = new zzdx();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("durationMs");
        zzai r1 = new zzai();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("errorCode");
        zzai r12 = new zzai();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("isColdCall");
        zzai r13 = new zzai();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("autoManageModelOnBackground");
        zzai r14 = new zzai();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("autoManageModelOnLowMemory");
        zzai r15 = new zzai();
        r15.zza(5);
        zzf = r05.withProperty(r15.zzb()).build();
        FieldDescriptor.Builder r06 = FieldDescriptor.builder("isNnApiEnabled");
        zzai r16 = new zzai();
        r16.zza(6);
        zzg = r06.withProperty(r16.zzb()).build();
        FieldDescriptor.Builder r07 = FieldDescriptor.builder("eventsCount");
        zzai r17 = new zzai();
        r17.zza(7);
        zzh = r07.withProperty(r17.zzb()).build();
        FieldDescriptor.Builder r08 = FieldDescriptor.builder("otherErrors");
        zzai r18 = new zzai();
        r18.zza(8);
        zzi = r08.withProperty(r18.zzb()).build();
        FieldDescriptor.Builder r09 = FieldDescriptor.builder("remoteConfigValueForAcceleration");
        zzai r19 = new zzai();
        r19.zza(9);
        zzj = r09.withProperty(r19.zzb()).build();
        FieldDescriptor.Builder r010 = FieldDescriptor.builder("isAccelerated");
        zzai r110 = new zzai();
        r110.zza(10);
        zzk = r010.withProperty(r110.zzb()).build();
    }

    private zzdx() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r2, Object r3) {
        zzho r22 = (zzho) r2;
        ObjectEncoderContext r32 = (ObjectEncoderContext) r3;
        r32.add(zzb, r22.zzc());
        r32.add(zzc, r22.zza());
        r32.add(zzd, r22.zzb());
        r32.add(zze, null);
        r32.add(zzf, null);
        r32.add(zzg, null);
        r32.add(zzh, null);
        r32.add(zzi, null);
        r32.add(zzj, null);
        r32.add(zzk, null);
    }
}
