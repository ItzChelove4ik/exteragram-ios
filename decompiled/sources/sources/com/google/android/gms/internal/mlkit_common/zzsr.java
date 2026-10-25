package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes4.dex */
final class zzsr extends LazyInstanceMap {
    public /* synthetic */ zzsr(zzsq r1) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object r5) {
        zzsb r52 = (zzsb) r5;
        MlKitContext r0 = MlKitContext.getInstance();
        zzsc r1 = new zzsc(MlKitContext.getInstance().getApplicationContext(), r52);
        String r53 = r52.zzb();
        return new zzsh(r0.getApplicationContext(), (SharedPrefManager) r0.get(SharedPrefManager.class), r1, r53);
    }
}
