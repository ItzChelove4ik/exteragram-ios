package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.SessionState;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zzcb implements OnSuccessListener {
    private final /* synthetic */ zzce zza;

    public /* synthetic */ zzcb(zzce r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r1) {
        this.zza.zzj((SessionState) r1);
    }
}
