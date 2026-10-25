package com.google.android.gms.internal.cast;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public abstract class zzwh extends zzwi {
    private final ListenableFuture zza;

    public zzwh(ListenableFuture r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.cast.zzhn
    public final /* synthetic */ Object zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.cast.zzwg
    public final /* synthetic */ Future zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.cast.zzwi
    public final ListenableFuture zzc() {
        return this.zza;
    }
}
