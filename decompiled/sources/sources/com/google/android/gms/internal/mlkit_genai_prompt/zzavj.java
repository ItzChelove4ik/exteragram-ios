package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes4.dex */
final class zzavj extends LazyInstanceMap {
    public /* synthetic */ zzavj(byte[] r1) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object r5) {
        zzaux r52 = (zzaux) r5;
        MlKitContext r0 = MlKitContext.getInstance();
        zzauy r1 = new zzauy(MlKitContext.getInstance().getApplicationContext(), r52);
        String r53 = r52.zza();
        return new zzavc(r0.getApplicationContext(), (SharedPrefManager) r0.get(SharedPrefManager.class), r1, r53);
    }
}
