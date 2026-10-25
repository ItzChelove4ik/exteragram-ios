package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes4.dex */
final class zzajb implements ObjectEncoder {
    static final zzajb zza = null;

    static {
        zza = new zzajb();
        FieldDescriptor.Builder r0 = FieldDescriptor.builder("userSelectedArea");
        zzpr r1 = new zzpr();
        r1.zza(1);
        r0.withProperty(r1.zzb()).build();
    }

    private zzajb() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        ObjectEncoderContext r22 = (ObjectEncoderContext) r2;
        throw null;
    }
}
