package com.google.android.gms.cast.framework;

import android.os.Bundle;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zzi implements OnSuccessListener {
    private final /* synthetic */ CastContext zza;

    public /* synthetic */ zzi(CastContext r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r1) {
        this.zza.zzg((Bundle) r1);
    }
}
