package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes4.dex */
final class zzls extends LazyInstanceMap {
    public /* synthetic */ zzls(zzlr r1) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object r5) {
        zzlc r52 = (zzlc) r5;
        MlKitContext r0 = MlKitContext.getInstance();
        zzld r1 = new zzld(MlKitContext.getInstance().getApplicationContext(), r52);
        String r53 = r52.zzb();
        return new zzli(r0.getApplicationContext(), (SharedPrefManager) r0.get(SharedPrefManager.class), r1, r53);
    }
}
