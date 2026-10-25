package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes4.dex */
final class zztv extends LazyInstanceMap {
    public /* synthetic */ zztv(zztu r1) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object r5) {
        zztd r52 = (zztd) r5;
        MlKitContext r0 = MlKitContext.getInstance();
        zzte r1 = new zzte(MlKitContext.getInstance().getApplicationContext(), r52);
        String r53 = r52.zzb();
        return new zztl(r0.getApplicationContext(), (SharedPrefManager) r0.get(SharedPrefManager.class), r1, r53);
    }
}
