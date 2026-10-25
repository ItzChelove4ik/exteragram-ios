package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzadd implements ObjectEncoder {
    static final zzadd zza = null;

    static {
        zza = new zzadd();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("errorCode");
        zzpr r1 = new zzpr();
        r1.zza(1);
        r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("isColdCall");
        zzpr r12 = new zzpr();
        r12.zza(2);
        r02.withProperty(r12.zzb()).build();
        FieldDescriptor.Builder r03 = FieldDescriptor.builder("imageInfo");
        zzpr r13 = new zzpr();
        r13.zza(3);
        r03.withProperty(r13.zzb()).build();
        FieldDescriptor.Builder r04 = FieldDescriptor.builder("detectorOptions");
        zzpr r14 = new zzpr();
        r14.zza(4);
        r04.withProperty(r14.zzb()).build();
    }

    private zzadd() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        throw null;
    }
}
