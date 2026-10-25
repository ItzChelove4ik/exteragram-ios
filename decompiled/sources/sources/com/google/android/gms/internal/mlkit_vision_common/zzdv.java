package com.google.android.gms.internal.mlkit_vision_common;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzdv implements ObjectEncoder {
    static final zzdv zza = null;
    private static final FieldDescriptor zzb = null;
    private static final FieldDescriptor zzc = null;

    static {
        zza = new zzdv();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("errorCode");
        zzae r1 = new zzae();
        r1.zza(1);
        zzb = r0.withProperty(r1.zzb()).build();
        FieldDescriptor.Builder r02 = FieldDescriptor.builder("options");
        zzae r12 = new zzae();
        r12.zza(2);
        zzc = r02.withProperty(r12.zzb()).build();
    }

    private zzdv() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        throw null;
    }
}
