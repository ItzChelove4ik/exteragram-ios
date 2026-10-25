package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes4.dex */
public final class zzaa extends zzam {
    private final CastStateListener zza;

    public zzaa(CastStateListener r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final void zzc(int r1) {
        this.zza.onCastStateChanged(r1);
    }
}
