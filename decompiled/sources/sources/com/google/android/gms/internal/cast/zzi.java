package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zzi implements OnSuccessListener {
    private final /* synthetic */ zzj zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ int zzc;
    private final /* synthetic */ SharedPreferences zzd;

    public /* synthetic */ zzi(zzj r1, String r2, int r3, SharedPreferences r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r4) {
        this.zza.zzc(this.zzb, this.zzc, this.zzd, (Bundle) r4);
    }
}
