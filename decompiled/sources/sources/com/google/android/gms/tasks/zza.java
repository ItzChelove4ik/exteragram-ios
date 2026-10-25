package com.google.android.gms.tasks;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zza implements OnSuccessListener {
    final /* synthetic */ OnTokenCanceledListener zza;

    public zza(zzb r1, OnTokenCanceledListener r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* bridge */ /* synthetic */ void onSuccess(Object r1) {
        Void r12 = (Void) r1;
        this.zza.onCanceled();
    }
}
