package com.google.android.gms.internal.mlkit_vision_label;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes4.dex */
final class zznz extends LazyInstanceMap {
    public /* synthetic */ zznz(zzny r1) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object r5) {
        zznh r52 = (zznh) r5;
        MlKitContext r0 = MlKitContext.getInstance();
        zzni r1 = new zzni(MlKitContext.getInstance().getApplicationContext(), r52);
        String r53 = r52.zzb();
        return new zznp(r0.getApplicationContext(), (SharedPrefManager) r0.get(SharedPrefManager.class), r1, r53);
    }
}
