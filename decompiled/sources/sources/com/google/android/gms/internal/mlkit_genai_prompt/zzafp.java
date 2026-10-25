package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzafp implements ObjectEncoder {
    static final zzafp zza = null;

    static {
        zza = new zzafp();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("mode");
        zzpr r1 = new zzpr();
        r1.zza(1);
        r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("landmark");
        zzpr r12 = new zzpr();
        r12.zza(2);
        r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("classification");
        zzpr r13 = new zzpr();
        r13.zza(3);
        r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("prominentFaceOnly");
        zzpr r14 = new zzpr();
        r14.zza(4);
        r04.withProperty(r14.zzb()).build();
        FieldDescriptor.Builder r05 = FieldDescriptor.builder("tracking");
        zzpr r15 = new zzpr();
        r15.zza(5);
        r05.withProperty(r15.zzb()).build();
        FieldDescriptor.Builder r06 = FieldDescriptor.builder("minFaceSize");
        zzpr r16 = new zzpr();
        r16.zza(6);
        r06.withProperty(r16.zzb()).build();
    }

    private zzafp() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        throw null;
    }
}
