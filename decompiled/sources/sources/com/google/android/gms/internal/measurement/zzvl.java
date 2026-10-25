package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final class zzvl extends AbstractFuture {
    private final int zza;

    public zzvl(int r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final boolean setFuture(ListenableFuture r1) {
        return super.setFuture(r1);
    }

    public final /* synthetic */ int zza() {
        return this.zza;
    }
}
