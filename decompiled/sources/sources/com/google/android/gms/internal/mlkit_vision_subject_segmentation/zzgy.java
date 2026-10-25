package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzgy implements ObjectEncoder {
    static final zzgy zza = null;
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
        zza = new zzgy();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("source");
        zzbz r1 = new zzbz();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("appliedFilter");
        zzbz r12 = new zzbz();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("isAutoCaptureManuallyTriggered");
        zzbz r13 = new zzbz();
        r13.zza(3);
        zzd = r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("isRotated");
        zzbz r14 = new zzbz();
        r14.zza(4);
        zze = r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("hasLowConfidenceProposedCorners");
        zzbz r15 = new zzbz();
        r15.zza(5);
        zzf = r05.withProperty(r15.zzb()).build();
        FieldDescriptor.Builder r06 = FieldDescriptor.builder("autoCaptureTriggerLatencyMs");
        zzbz r16 = new zzbz();
        r16.zza(6);
        zzg = r06.withProperty(r16.zzb()).build();
        FieldDescriptor.Builder r07 = FieldDescriptor.builder("galleryImportProcessingMs");
        zzbz r17 = new zzbz();
        r17.zza(7);
        zzh = r07.withProperty(r17.zzb()).build();
        FieldDescriptor.Builder r08 = FieldDescriptor.builder("imageWidth");
        zzbz r18 = new zzbz();
        r18.zza(8);
        zzi = r08.withProperty(r18.zzb()).build();
        FieldDescriptor.Builder r09 = FieldDescriptor.builder("imageHeight");
        zzbz r19 = new zzbz();
        r19.zza(9);
        zzj = r09.withProperty(r19.zzb()).build();
        FieldDescriptor.Builder r010 = FieldDescriptor.builder("proposedCorners");
        zzbz r110 = new zzbz();
        r110.zza(10);
        zzk = r010.withProperty(r110.zzb()).build();
        FieldDescriptor.Builder r011 = FieldDescriptor.builder("adjustedCorners");
        zzbz r111 = new zzbz();
        r111.zza(11);
        zzl = r011.withProperty(r111.zzb()).build();
        FieldDescriptor.Builder r012 = FieldDescriptor.builder("isShadowRemoved");
        zzbz r112 = new zzbz();
        r112.zza(12);
        zzm = r012.withProperty(r112.zzb()).build();
        FieldDescriptor.Builder r013 = FieldDescriptor.builder("numOfAppliedCleanUpStrokes");
        zzbz r113 = new zzbz();
        r113.zza(13);
        zzn = r013.withProperty(r113.zzb()).build();
        FieldDescriptor.Builder r014 = FieldDescriptor.builder("numOfAttemptedCleanUpStrokes");
        zzbz r114 = new zzbz();
        r114.zza(14);
        zzo = r014.withProperty(r114.zzb()).build();
    }

    private zzgy() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        throw null;
    }
}
