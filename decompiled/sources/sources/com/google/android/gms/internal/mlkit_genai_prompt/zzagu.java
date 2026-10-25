package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzagu implements ObjectEncoder {
    static final zzagu zza = null;

    static {
        zza = new zzagu();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("status");
        zzpr r1 = new zzpr();
        r1.zza(1);
        r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("options");
        zzpr r12 = new zzpr();
        r12.zza(2);
        r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("model");
        zzpr r13 = new zzpr();
        r13.zza(3);
        r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("language");
        zzpr r14 = new zzpr();
        r14.zza(4);
        r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("segmentationRequest");
        zzpr r15 = new zzpr();
        r15.zza(5);
        r05.withProperty(r15.zzb()).build();
        FieldDescriptor.Builder r06 = FieldDescriptor.builder("segmentationResult");
        zzpr r16 = new zzpr();
        r16.zza(6);
        r06.withProperty(r16.zzb()).build();
        FieldDescriptor.Builder r07 = FieldDescriptor.builder("aggregatedSegmentations");
        zzpr r17 = new zzpr();
        r17.zza(7);
        r07.withProperty(r17.zzb()).build();
        FieldDescriptor.Builder r08 = FieldDescriptor.builder("durationMs");
        zzpr r18 = new zzpr();
        r18.zza(8);
        r08.withProperty(r18.zzb()).build();
        FieldDescriptor.Builder r09 = FieldDescriptor.builder("nativeSegmentationException");
        zzpr r19 = new zzpr();
        r19.zza(9);
        r09.withProperty(r19.zzb()).build();
        FieldDescriptor.Builder r010 = FieldDescriptor.builder("downloadErrorCodes");
        zzpr r110 = new zzpr();
        r110.zza(10);
        r010.withProperty(r110.zzb()).build();
        FieldDescriptor.Builder r011 = FieldDescriptor.builder("domain");
        zzpr r111 = new zzpr();
        r111.zza(11);
        r011.withProperty(r111.zzb()).build();
    }

    private zzagu() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        throw null;
    }
}
