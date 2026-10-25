package com.google.android.gms.internal.vision;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.vision.zzhe;
import com.google.android.gms.internal.vision.zzhf;

/* loaded from: classes5.dex */
public abstract class zzhe<MessageType extends zzhf<MessageType, BuilderType>, BuilderType extends zzhe<MessageType, BuilderType>> implements zzkn {
    public zzhe() {
    }

    public abstract BuilderType zza(MessageType r1);

    public abstract BuilderType zza(byte[] r1, int r2, int r3, zzio r4);

    @Override // com.google.android.gms.internal.vision.zzkn
    public final /* synthetic */ zzkn zza(zzkk r2) {
        if (zzr().getClass().isInstance(r2) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("mergeFrom(MessageLite) can only merge messages of the same type.");
        return null;
    L5:
        return zza((zzhf) r2);
    }
}
