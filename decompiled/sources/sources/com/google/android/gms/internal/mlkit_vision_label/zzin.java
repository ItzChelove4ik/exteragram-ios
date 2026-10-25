package com.google.android.gms.internal.mlkit_vision_label;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzin implements ObjectEncoder {
    static final zzin zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;

    static {
        zza = new zzin();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("result");
        zzci r1 = new zzci();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("ok");
        zzci r12 = new zzci();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
    }

    private zzin() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        throw null;
    }
}
