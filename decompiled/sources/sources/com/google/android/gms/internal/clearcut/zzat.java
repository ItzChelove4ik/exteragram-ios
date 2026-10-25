package com.google.android.gms.internal.clearcut;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.clearcut.zzas;
import com.google.android.gms.internal.clearcut.zzat;

/* loaded from: classes4.dex */
public abstract class zzat<MessageType extends zzas<MessageType, BuilderType>, BuilderType extends zzat<MessageType, BuilderType>> implements zzdp {
    public zzat() {
    }

    public abstract BuilderType zza(MessageType r1);

    @Override // com.google.android.gms.internal.clearcut.zzdp
    public final /* synthetic */ zzdp zza(zzdo r2) {
        if (zzbe().getClass().isInstance(r2) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("mergeFrom(MessageLite) can only merge messages of the same type.");
        return null;
    L5:
        return zza((zzas) r2);
    }
}
