package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
final /* synthetic */ class zzz implements zzhg {
    static final /* synthetic */ zzz zza = null;

    static {
        zza = new zzz();
    }

    private /* synthetic */ zzz() {
    }

    @Override // com.google.android.gms.internal.cast.zzhg
    public final /* synthetic */ Object zza() {
        int r0 = zzaa.$r8$clinit;
        return ((CastContext) Preconditions.checkNotNull(CastContext.getSharedInstance())).getCastOptions().getReceiverApplicationId();
    }
}
