package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;

/* loaded from: classes4.dex */
final class zzao implements ValueEncoderContext {
    private boolean zza;
    private boolean zzb;
    private FieldDescriptor zzc;
    private final zzak zzd;

    public zzao(zzak r2) {
        this.zza = false;
        this.zzb = false;
        this.zzd = r2;
    }

    private final void zzb() {
        if (this.zza == true) goto L7;
        this.zza = true;
        return;
    L7:
        throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(String r4) {
        zzb();
        this.zzd.zzc(this.zzc, r4, this.zzb);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(boolean r4) {
        zzb();
        zzak r0 = this.zzd;
        FieldDescriptor r1 = this.zzc;
        boolean r2 = this.zzb;
        r0.zzd(r1, r4 ? 1 : 0, r2);
        return this;
    }

    public final void zza(FieldDescriptor r2, boolean r3) {
        this.zza = false;
        this.zzc = r2;
        this.zzb = r3;
    }
}
