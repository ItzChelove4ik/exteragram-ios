package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzgv implements ObjectEncoder {
    static final zzgv zza = null;
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

    static {
        zza = new zzgv();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("screenName");
        zzbz r1 = new zzbz();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("sessionId");
        zzbz r12 = new zzbz();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("timestampMs");
        zzbz r13 = new zzbz();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("options");
        zzbz r14 = new zzbz();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("elementName");
        zzbz r15 = new zzbz();
        r15.zza(5);
        zzf = r05.withProperty(r15.zzb()).build();
        FieldDescriptor.Builder r06 = FieldDescriptor.builder("isAutoCaptureMode");
        zzbz r16 = new zzbz();
        r16.zza(6);
        zzg = r06.withProperty(r16.zzb()).build();
        FieldDescriptor.Builder r07 = FieldDescriptor.builder("pageIndex");
        zzbz r17 = new zzbz();
        r17.zza(7);
        zzh = r07.withProperty(r17.zzb()).build();
        FieldDescriptor.Builder r08 = FieldDescriptor.builder("appliedToAllPages");
        zzbz r18 = new zzbz();
        r18.zza(8);
        zzi = r08.withProperty(r18.zzb()).build();
        FieldDescriptor.Builder r09 = FieldDescriptor.builder("errorCode");
        zzbz r19 = new zzbz();
        r19.zza(9);
        zzj = r09.withProperty(r19.zzb()).build();
        FieldDescriptor.Builder r010 = FieldDescriptor.builder("callerAppId");
        zzbz r110 = new zzbz();
        r110.zza(10);
        zzk = r010.withProperty(r110.zzb()).build();
        FieldDescriptor.Builder r011 = FieldDescriptor.builder("cleanUpStrokeSize");
        zzbz r111 = new zzbz();
        r111.zza(11);
        zzl = r011.withProperty(r111.zzb()).build();
    }

    private zzgv() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        throw null;
    }
}
