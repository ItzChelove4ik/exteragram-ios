package com.android.billingclient.api;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzcu implements ActivityResultCallback {
    public final /* synthetic */ ProxyBillingActivityV2 zza;

    public /* synthetic */ zzcu(ProxyBillingActivityV2 r1) {
        this.zza = r1;
    }

    @Override // androidx.activity.result.ActivityResultCallback
    public final void onActivityResult(Object r1) {
        this.zza.zzb((ActivityResult) r1);
    }
}
